package xboxLivePageObjects_Lib;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ActivityFeedComponent extends PageObject {

	@FindBy(id="primaryArea")
	public WebElement entireMyXboxWebPage;
	
	//activity feed section
	public WebElement activityFeedWholeContent = entireMyXboxWebPage.findElement(By.cssSelector(".xboxactivityfeed.fullwidth"));
		
	@FindBy(id="newpostinput")
	public WebElement generalNewPostInput;
	@FindBy(id="newpost")
	public WebElement generalPostButton;
	private static String randomGibberish;
	private static String recentGibberish = "";
	public static String recentlyViewedPost = "";
	
	public static String postURLLink = "";
		
	//Activity Feed posts
	public List<WebElement> postedActivityFeed;
		
	//setup a variables to help interact with posts on the main activity list.
		
	//dialog box section	
	@FindBy(id="xboxdialog")
	public WebElement viewSpecificFeedPost;
	
	public WebElement socialContentDialogList;
	public WebElement closeFeedPost = viewSpecificFeedPost.findElement(By.cssSelector(".c-glyph.glyph-cancel"));
	public WebElement viewSpecificFeedPostOptions;
	public WebElement mainCommentInput;
	public WebElement postMainComment;
		
	//check the className value for if conditions to unlike something and confirm a functional test passed.
	public WebElement likeButton;
	//This feature might need to be removed since the function appears disabled
	public WebElement commentButton;
		
	public WebElement deleteButton;	
	
	public WebElement shareActionButton;
	//After clicking on the share button
	@FindBy(id="feedsharedialog")
	public WebElement shareCommentSection;
	public WebElement createShareComment;
	public WebElement postShareComment;
	public WebElement closeShareSection;
		
	public WebElement socialDropDownMenu;
	public WebElement viewLikes;
	public WebElement viewComments;
	public WebElement viewShares;
	
	public List<WebElement> socialDialogList;
		
	//Expand the list after clicking "get more"	
	@FindBy(id="getmore")
	public WebElement getMore;
		
	//Variable for clicking on links to other sites
	public static String feedRecentViewedHyperlink = "";
		
		
	// This piece is to perform on Home, my profile, and other profile web page.
	public ActivityFeedComponent (WebDriver driver) {
		super(driver);
		//ActivityFeed Portion
		randomGibberish = randomSetup();
		postedActivityFeed = activityFeedWholeContent.findElements(By.className("xboxactivityfeeditem"));
			

			
	}
	
	public ActivityFeedComponent refresh () {
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		return new ActivityFeedComponent(driver);
	}
	
	//Creating a random set of string to reduce the chance of false asserts during test.
	public String randomSetup() {
		String catalog = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$^&()abcdefghijklmnopqrstuvwxyz";
		String newText = "";
		Random rand = new Random();
		while (newText.length() < 10) {
			int index = rand.nextInt(catalog.length());
			newText += catalog.charAt(index);
		}
		return newText;
	}
	//Rework on using the set of gibberish strings	
	public void newPost() {
		recentGibberish = randomGibberish;
		generalNewPostInput.sendKeys(randomGibberish);
		generalPostButton.click();
		postedActivityFeed.clear();
		synchronized (driver) {
			try {driver.wait(7000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
		postedActivityFeed = activityFeedWholeContent.findElements(By.className("xboxactivityfeeditem"));
	}
			
	public boolean findNewPost() {
		boolean found = false;
		int listCounter = 0;
		String retrievedText ="";
		int attempts = 0;
		
		OUTER:
		for(WebElement e : postedActivityFeed) {
			boolean stale = true;
			listCounter++;
			try {
				while(attempts < 2 && stale == true) {
					try {
						retrievedText = e.findElement(By.className("c-paragraph")).getText();
						stale = false;
						break;
					} catch (StaleElementReferenceException n) { }
					attempts++;
				}
						
				System.out.println(listCounter + ": " + retrievedText);
				if(retrievedText.trim().equals(recentGibberish)) {
					found = true;
					System.out.println("New message has been found");
					break OUTER;
				}
			} catch (NoSuchElementException moveAlong) {
				continue;
			}
		}
		return found;
	}
	
	//General Delete Post
	public ActivityFeedComponent deletePost() {
		int listCounter = 0;
		Random rand = new Random();
		int randNum = rand.nextInt(postedActivityFeed.size());
		for (WebElement e : postedActivityFeed) {
			if (listCounter == randNum) {
				recentlyViewedPost = e.getAttribute("data-shareroot");
				e.findElement(By.id("deleteactionlink")).click();
				declareDialogBoxElements();
				deleteButton.click();
				
				break;
			}
			listCounter++;
		}
		synchronized (driver) {
			try {driver.wait(4000);} 
			catch (InterruptedException r) { r.printStackTrace();}
		}
		return new ActivityFeedComponent(driver);
	}
	
	public boolean findMatchInPost () {
		boolean found = false;
		for(WebElement e : postedActivityFeed) {
			if (e.getAttribute("data-shareroot").equals(recentlyViewedPost)) {
				found = true;
				break;
			}
		}
		if (found == false) {
			System.out.println("No match was found in the Activity Feed with the provided data-shareroot.");
		}
		return found;
	}
	
		
	public void expandPostedActivityFeed() {
		getMore.click();
		synchronized (driver) {
			try {driver.wait(3000);} 
			catch (InterruptedException r) { r.printStackTrace();}
		}
		postedActivityFeed = activityFeedWholeContent.findElements(By.className("xboxactivityfeeditem"));
	}
	//Rework on code to incorporate the String obtaining data-shareroot.	
	public void viewAnyPost() {
		int feedIndex = 0;
		Random rand = new Random();
		int randNum = rand.nextInt(postedActivityFeed.size());
		for (WebElement e : postedActivityFeed) {
			if(feedIndex == randNum) {
				e.click();
				declareDialogBoxElements();
				recentlyViewedPost = e.getAttribute("data-shareroot");
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				closeFeedPost.click();
				break;
			}
			feedIndex++;
		}
	}
	//When the dialog box appears:
	public void declareDialogBoxElements() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if(viewSpecificFeedPost.getAttribute("aria-hidden").equals("false")) {
			
			socialContentDialogList = viewSpecificFeedPost.findElement(By.id("socialcontent"));
			
			viewSpecificFeedPostOptions = viewSpecificFeedPost.findElement(By.id("popupcontent"));
			
			mainCommentInput = viewSpecificFeedPostOptions.findElement(By.id("commentinput"));
			postMainComment = viewSpecificFeedPost.findElement(By.id("post"));
				
			//check the className value for if conditions to unlike something and confirm a functional test passed.
			
			likeButton = viewSpecificFeedPostOptions.findElement(By.id("likeactionlink"));
			
			shareActionButton = viewSpecificFeedPost.findElement(By.id("shareactionlink"));
			try {
				deleteButton = viewSpecificFeedPost.findElement(By.id("deleteactionlink"));
			} catch (NoSuchElementException moveAlong) {  }
			
			socialDropDownMenu = viewSpecificFeedPost.findElement(By.id("SocialDropDown"));
			viewLikes = viewSpecificFeedPostOptions.findElement(By.id("Like"));
			viewComments = viewSpecificFeedPostOptions.findElement(By.id("Comment"));
			viewShares = viewSpecificFeedPostOptions.findElement(By.id("Share"));
			
		}
		else {
			System.out.println("The dialog box is still hidden.");
		}
	}
	public void declareShareDialogBoxElements() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		createShareComment = shareCommentSection.findElement(By.id("newpostinput"));
		postShareComment = shareCommentSection.findElement(By.id("newpost"));
		closeShareSection = shareCommentSection.findElement(By.cssSelector(".c-glyph.glyph-cancel"));
		
	}
	//Without clicking on the specific feed and open on the dialog box or comment
	public void viewAnyPostAndRespond() {
			
	}
		
	public void viewAnyPostAndComment() {
		int feedIndex = 0;
		Random rand = new Random();
		int randNum = rand.nextInt(postedActivityFeed.size());
		for (WebElement e : postedActivityFeed) {
			if(feedIndex == randNum) {
				recentlyViewedPost = e.getAttribute("data-shareroot");
				e.findElement(By.id("commentactionlink")).click();
				declareDialogBoxElements();
				recentGibberish = randomGibberish;
				mainCommentInput.sendKeys(randomGibberish);
				postMainComment.click();
				synchronized (driver) {
					try {driver.wait(3000);} 
					catch (InterruptedException r) { r.printStackTrace();}
				}
				closeFeedPost.click();	
				break;
			}
			feedIndex++;
		}
	}
	//Check data share-dataroot first before going further
	public boolean veiwNewCommentDialogBox() {
		int posts = 1;
		int commentList = 1;
		boolean foundNewComment = false;
		Outer:
		for(WebElement e : postedActivityFeed) {
			if(e.getAttribute("data-shareroot").equals(recentlyViewedPost)) {
				e.click();
				declareDialogBoxElements();
				commentList = 1;
				if(!(socialDropDownMenu.getText().equalsIgnoreCase("Comments"))) {
					socialDropDownMenu.click();
					viewComments.click();
				}
				socialDialogList = socialContentDialogList.findElements(By.tagName("li"));
				for(WebElement f : socialDialogList) {
					if (f.findElement(By.className("comment")).getText().equals(recentGibberish)) {
						System.out.println("Found our recent post "+ f.findElement(By.className("comment")).getText()  + 
								" on Post: "+ posts + " Comment: " + commentList + " From: " + f.findElement(By.className("name")).getText());
						foundNewComment = true;
						closeFeedPost.click();
						break Outer;
					}
					commentList++;
				}
				closeFeedPost.click();
			}
			posts++;
			
		}
		return foundNewComment;
	}
	
	//Test for deleting specified comment inside the dialog box.
	public boolean deleteRecentComment() {
		boolean doesNotExist = false;
		int posts = 1;
		int commentList = 1;
		Outer:
		for(WebElement e : postedActivityFeed) {
			if(e.getAttribute("data-shareroot").equals(recentlyViewedPost)) {
				e.click();
				declareDialogBoxElements();
				commentList = 1;
				if(!(socialDropDownMenu.getText().equalsIgnoreCase("Comments"))) {
					socialDropDownMenu.click();
					viewComments.click();
				}
				socialDialogList = socialContentDialogList.findElements(By.tagName("li"));
				for(WebElement f : socialDialogList) {
					if (f.findElement(By.className("comment")).getText().equals(recentGibberish)) {
						System.out.println("Found our recent post "+ f.findElement(By.className("comment")).getText()  + 
								" on Post: "+ posts + " Comment: " + commentList + " From: " + f.findElement(By.className("name")).getText());
						f.findElement(By.id("deleteactionlink")).click();
						closeFeedPost.click();
						break Outer;
					}
					commentList++;
				}
			}
			posts++;
		}
		if (veiwNewCommentDialogBox() == false) {
			doesNotExist = true;
			System.out.println("Recent comment made was successfully deleted.");
		}
		return doesNotExist;
	}
	
	//Methods for selecting the like button
	public void likeAnyPost() {
		int feedIndex = 0;
		Random rand = new Random();
		int randNum = rand.nextInt(postedActivityFeed.size()/2);
		for (WebElement e : postedActivityFeed) {
			if(feedIndex == randNum) {
				recentlyViewedPost = e.getAttribute("data-shareroot");
				e.findElement(By.id("likeactionlink")).click();
				declareDialogBoxElements();
				if(likeButton.getAttribute("class").equals("ajaxbutton c-action-trigger c-glyph glyph-heart-fill profilecolor")) {
					likeButton.click();
					synchronized (driver) {
						try {driver.wait(5000);} 
						catch (InterruptedException r) { r.printStackTrace();}
					}
				}
				likeButton.click();
				synchronized (driver) {
					try {driver.wait(5000);} 
					catch (InterruptedException r) { r.printStackTrace();}
				}
				closeFeedPost.click();
				break;
			}
			feedIndex++;
		}
	}
	
	public boolean findRecentLikes() {
		int feedIndex = 0;
		boolean matches = false;
		for(WebElement e : postedActivityFeed) {
			if(e.getAttribute("data-shareroot").equals(recentlyViewedPost)) {
				if(e.findElement(By.id("likeactionlink")).getAttribute("class")
						.equals("ajaxbutton profilecolor c-action-trigger c-glyph glyph-heart-fill")) {
					matches = true;
					System.out.println("We found a post you liked on feed: " + feedIndex);
				}
			}
			feedIndex++;
		}
		if(matches == false) {
			System.out.println("We found no likes you made in any of the posts");
		}
		return matches;
	}
	
	
	
	//Mehtods for selecting the share button
	public ActivityFeedComponent shareAnyPost() {
		int feedIndex = 0;
		Random rand = new Random();
		int randNum = rand.nextInt(postedActivityFeed.size());
		for (WebElement e : postedActivityFeed) {
			if(feedIndex == randNum) {
				recentlyViewedPost = e.getAttribute("data-shareroot");
				e.findElement(By.id("shareactionlink")).click();
				declareDialogBoxElements();
				
				recentGibberish = randomGibberish;
				shareActionButton.click();
				
				declareShareDialogBoxElements();
				createShareComment.sendKeys(randomGibberish);
				postShareComment.click();
				synchronized (driver) {
					try {driver.wait(5000);} 
					catch (InterruptedException r) { r.printStackTrace();}
				}
				closeFeedPost.click();	
				break;
			}
			feedIndex++;
		}
		
		return new ActivityFeedComponent(driver);
	}
	
	public boolean findRecentShare() {
		boolean foundShare = false;
		int feedIndex = 1;
		for(WebElement e : postedActivityFeed) {
			try {
				if(e.findElement(By.className("message")).getText().equals(recentGibberish)) {
					System.out.println("Found shared post on Feed: " + feedIndex +
							" by " + e.findElement(By.className("name")).getText());
					foundShare =true;
					break;
				}
			}
			catch (NoSuchElementException r) {
				continue;
			}
			feedIndex++;
		}
		return foundShare;
	}
	
	public boolean originalPostShared(String profileName) {
		boolean foundOriginal = false;
		Outer:
		for(WebElement e : postedActivityFeed) {
			if (e.getAttribute("data-shareroot").equals(recentlyViewedPost)) {
				e.click();
				synchronized (driver) {
					try {driver.wait(2000);} 
					catch (InterruptedException r) { r.printStackTrace();}
				}
				declareDialogBoxElements();
				socialDropDownMenu.click();
				viewShares.click();
				socialDialogList = socialContentDialogList.findElements(By.tagName("li"));
				for(WebElement r : socialDialogList) {
					if(r.findElement(By.className("name")).getText().equals(profileName)) {
						System.out.println("Found the original post shared by: " + profileName);
						foundOriginal = true;
						break Outer;
					}
				}
			}
		}
		
		return foundOriginal;
	}
	//Rework to expand on using get more if currentlist offers none.
	public boolean hyperLinkDestination() {
		boolean uRLMatch = false;
		for (WebElement e: postedActivityFeed) {
			try {
				if(e.findElement(By.cssSelector(".actionlink.horizontal")).findElement(By.tagName("a")).isDisplayed()) {
					postURLLink = e.findElement(By.cssSelector(".actionlink.horizontal")).findElement(By.tagName("a")).getText();
					e.findElement(By.cssSelector(".actionlink.horizontal")).findElement(By.tagName("a")).click();
					if (postURLLink.equals(driver.getCurrentUrl())) {
						uRLMatch = true;
					}
					driver.navigate().back();
				}
			} catch (NoSuchElementException moveAlong) {
				continue;
			}
		}
		if (uRLMatch == false) {
			System.out.println("No match with url link posted in activity feed.");
		}
		return uRLMatch;
	}
	
}
