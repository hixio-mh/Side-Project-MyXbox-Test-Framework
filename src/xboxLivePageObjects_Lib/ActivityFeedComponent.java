package xboxLivePageObjects_Lib;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ActivityFeedComponent extends PageObject {

	@FindBy(id="primaryArea")
	public WebElement entireMyXboxWebPage;
	
	
	public WebElement activityFeedWholeContent = entireMyXboxWebPage.findElement(By.cssSelector(".xboxactivityfeed.fullwidth"));
		
	@FindBy(id="newpostinput")
	public WebElement generalNewPostInput;
	@FindBy(id="newpost")
	public WebElement generalPostButton;
	private static String randomGibberish;
	private static String recentGibberish = "";
	public static String recentlyViewedPost = "";
	public static String recentlyViewedMessage = "";
	
	public static String postURLLink = "";
		
	
	public List<WebElement> postedActivityFeed;
		
	
		
	
	@FindBy(id="xboxdialog")
	public WebElement viewSpecificFeedPost;
	public WebElement viewSpecificImagePost;
	
	public WebElement socialContentDialogList;
	public WebElement closeFeedPost = viewSpecificFeedPost.findElement(By.cssSelector(".c-glyph.glyph-cancel"));
	public WebElement viewSpecificFeedPostOptions;
	public WebElement mainCommentInput;
	public WebElement postMainComment;
		
	
	public WebElement likeButton;
	
	public WebElement commentButton;
		
	public WebElement deleteButton;	
	
	public WebElement postedHyperLink;
	
	public WebElement shareActionButton;
	
	//Broken code due to website updates
	//@FindBy(id="feedsharedialog")
	public WebElement shareCommentSection;
	public WebElement createShareComment;
	public WebElement postShareComment;
	public WebElement closeShareSection; 
		
	public WebElement socialDropDownMenu;
	public WebElement viewLikes;
	public WebElement viewComments;
	public WebElement viewShares;
	
	public List<WebElement> socialDialogList;
		
	@FindBy(id="getmore")
	public WebElement getMore;
		
	
	public static String feedRecentViewedHyperlink = "";
		
		
	
	public ActivityFeedComponent (Driver driver) {
		super(driver);
		randomGibberish = randomSetup();
		postedActivityFeed = activityFeedWholeContent.findElements(By.className("xboxactivityfeeditem"));
			

			
	}
	
	public ActivityFeedComponent refresh () {
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		return new ActivityFeedComponent(driver);
	}
	
	
	public String randomSetup() {
		String catalog = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$^&()abcdefghijklmnopqrstuvwxyz.,;:.,";
		String newText = "";
		Random rand = new Random();
		while (newText.length() < 10) {
			int index = rand.nextInt(catalog.length());
			newText += catalog.charAt(index);
		}
		return newText;
	}
		
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
						
				System.out.println((listCounter+1) + ": " + retrievedText);
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
		int randNum = rand.nextInt(postedActivityFeed.size()/2);
		for (WebElement e : postedActivityFeed) {
			if (listCounter == randNum) {
				recentlyViewedPost = e.getAttribute("data-shareroot");
				try {
					e.findElement(By.id("deleteactionlink")).click();
					declareDialogBoxElements();
					deleteButton.click();
					break;
				} catch(NoSuchElementException r) {
					randNum = rand.nextInt(postedActivityFeed.size()/2);
					continue;
				}
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
			try {driver.wait(5000);} 
			catch (InterruptedException r) { r.printStackTrace();}
		}
		postedActivityFeed = activityFeedWholeContent.findElements(By.className("xboxactivityfeeditem"));
	}
		
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
				break;
			}
			feedIndex++;
		}
	}
	
	public void closeOpenedPost() {
		if(viewSpecificFeedPost.getAttribute("aria-hidden").equals("false")) {
			closeFeedPost.click();
		}
		else {
			System.out.println("No dialog box was open to begin with.");
		}
	}
	
	public void declareDialogBoxElements() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if(viewSpecificFeedPost.getAttribute("aria-hidden").equals("false")) {
			
			viewSpecificImagePost = viewSpecificFeedPost.findElement(By.id("popupimage"));
			
			socialContentDialogList = viewSpecificFeedPost.findElement(By.id("socialcontent"));
			
			viewSpecificFeedPostOptions = viewSpecificFeedPost.findElement(By.id("popupcontent"));
			
			mainCommentInput = viewSpecificFeedPostOptions.findElement(By.id("commentinput"));
			postMainComment = viewSpecificFeedPost.findElement(By.id("post"));
				
			likeButton = viewSpecificFeedPostOptions.findElement(By.id("likeactionlink"));
			
			shareActionButton = viewSpecificFeedPost.findElement(By.id("shareactionlink"));
			
			
			try {
				deleteButton = viewSpecificFeedPostOptions.findElement(By.id("deleteactionlink"));
			} catch (NoSuchElementException moveAlong) {  }

			try {
				postedHyperLink = viewSpecificImagePost.findElement(By.tagName("xbox-action-link"))
						.findElement(By.tagName("a"));
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
		createShareComment = viewSpecificFeedPostOptions.findElement(By.id("newpostinput"));
		postShareComment = viewSpecificFeedPostOptions.findElement(By.id("newpost"));
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
				synchronized (driver) {
					try {driver.wait(3000);} 
					catch (InterruptedException r) { r.printStackTrace();}
				}
				socialDialogList = socialContentDialogList.findElements(By.tagName("li"));
				for(WebElement f : socialDialogList) {
					try {
						if (f.findElement(By.className("comment")).getText().equals(recentGibberish)) {
							System.out.println("Found our recent post "+ f.findElement(By.className("comment")).getText()  + 
									" on Post: "+ posts + " Comment: " + commentList + " From: " + f.findElement(By.className("name")).getText());
							foundNewComment = true;
							closeFeedPost.click();
							break Outer;
						}
					} catch (NoSuchElementException r) { }
					commentList++;
				}
				closeFeedPost.click();
			}
			posts++;
			
		}
		return foundNewComment;
	}
	
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
						synchronized (driver) {
							try {driver.wait(4000);} 
							catch (InterruptedException r) { r.printStackTrace();}
						}
						closeFeedPost.click();
						break Outer;
					}
					commentList++;
				}
			}
			posts++;
		}
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		if (veiwNewCommentDialogBox() == false) {
			doesNotExist = true;
			System.out.println("Recent comment made was successfully deleted.");
		}
		return doesNotExist;
	}
	
	public void likeAnyPost() {
		int feedIndex = 0;
		Random rand = new Random();
		int randNum = rand.nextInt(postedActivityFeed.size()/2);
		for (WebElement e : postedActivityFeed) {
			if(feedIndex == randNum) {
				recentlyViewedPost = e.getAttribute("data-shareroot");
				e.findElement(By.id("likeactionlink")).click();
				declareDialogBoxElements();
				if(likeButton.getAttribute("class").equals("ajaxbutton profilecolor c-action-trigger c-glyph glyph-heart-fill")) {
					likeButton.click();
					while(!(likeButton.getAttribute("class").equals("ajaxbutton  c-action-trigger c-glyph glyph-heart-fill"))){
						synchronized (driver) {
							try {driver.wait(5000);} 
							catch (InterruptedException r) { r.printStackTrace();}
						}
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
	
	
	
	public ActivityFeedComponent shareAnyPost() {
		int feedIndex = 0;
		Random rand = new Random();
		int randNum = rand.nextInt(postedActivityFeed.size()/2);
		for (WebElement e : postedActivityFeed) {
			if(feedIndex == randNum) {
				recentlyViewedPost = e.getAttribute("data-shareroot");
				
				try {
					recentlyViewedMessage = e.findElement(By.cssSelector(".feedmaincontent.userpost")).getText();
				} catch(NoSuchElementException r) {  }
				
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
				System.out.println(recentlyViewedPost);
				e.click();
				synchronized (driver) {
					try {driver.wait(2000);} 
					catch (InterruptedException r) { r.printStackTrace();}
				}
				declareDialogBoxElements();
				socialDropDownMenu.click();
				synchronized (driver) {
					try {driver.wait(2000);} 
					catch (InterruptedException r) { r.printStackTrace();}
				}
				Driver.js.executeScript("arguments[0].click();", viewShares);
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
		while(foundOriginal == false ) {
			expandPostedActivityFeed();
			Outer2:
			for (WebElement e : postedActivityFeed) {
				if (e.findElement(By.cssSelector("")).getText().equals(recentlyViewedMessage) &&
						e.findElement(By.className("message")) == null) {
					e.click();
					synchronized (driver) {
						try {driver.wait(2000);} 
						catch (InterruptedException r) { r.printStackTrace();}
					}
					declareDialogBoxElements();
					socialDropDownMenu.click();
					Driver.js.executeScript("arguments[0].click();", viewShares);
					socialDialogList = socialContentDialogList.findElements(By.tagName("li"));
					for(WebElement r : socialDialogList) {
						if(r.findElement(By.className("name")).getText().equals(profileName)) {
							System.out.println("Found the original post shared by: " + profileName);
							foundOriginal = true;
							closeFeedPost.click();
							break Outer2;
						}
						else {
							closeFeedPost.click();
							break Outer2;
						}
					}
				}
			}
		}
		
		return foundOriginal;
	}
	
	public boolean hyperLinkDestination() {
		boolean uRLMatch = false;
		for (WebElement e: postedActivityFeed) {
			outer:
			try {
				if(e.findElement(By.cssSelector(".c-group.f-wrap-items")).findElement(By.tagName("a")).isDisplayed()) {
					postURLLink = e.findElement(By.cssSelector(".c-group.f-wrap-items")).findElement(By.tagName("a")).getText();
					System.out.println(postURLLink);
					e.click();
					declareDialogBoxElements();
					synchronized (driver) {
						try {driver.wait(3000);} 
						catch (InterruptedException r) { r.printStackTrace();}
					}
					Driver.js.executeScript("arguments[0].click();", postedHyperLink);
					//Old Code (Could be useful in later updates)
					/*Set<String> browserTabs = (driver.getWindowHandles());
					String currentHandle = driver.getWindowHandle();
					for (String tab : browserTabs) {
						if (!tab.equals(currentHandle)) {
							driver.switchTo().window((tab));
						}
					} */
					synchronized (driver) {
						try {driver.wait(2000);} 
						catch (InterruptedException r) { r.printStackTrace();}
					}
					System.out.println(driver.getCurrentUrl());
					if (postURLLink.equals(driver.getCurrentUrl())) {
						uRLMatch = true;
					}
					//Part of old code
					/*driver.close();
					driver.switchTo().window(currentHandle); */
					driver.navigate().back();
					break;
				}
			} 
			catch (NoSuchElementException moveAlong) {
				break outer;
			}
		if (uRLMatch == false) {
			System.out.println("No match with url link posted in activity feed.");
		}
	}
	return uRLMatch;
	}
	
	
}
