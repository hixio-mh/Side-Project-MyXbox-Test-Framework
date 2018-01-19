package xboxLivePageObjects_Lib;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class FriendsandClubsComponent extends PageObject {
	
	@FindBy(id="tertiaryR1")
	private WebElement friendsAndClubsSection;
	
	@FindBy(name="friendsSearch")
	private WebElement entireFriendSearchBar;
	
	public WebElement findPeopleOrClubs = entireFriendSearchBar.findElement(By.tagName("input"));
	public List<WebElement> searchOrDeleteText = entireFriendSearchBar.findElements(By.tagName("button"));
	
	
	//General filter button
	@FindBy(id="All")
	public WebElement searchFilterButton;

	
	//@FindBy(className="c-context-menu")
	private WebElement allFilter; 
	
	@FindBy(id="ClubsFirst")
	private WebElement clubsFirstFilter;
	
	@FindBy(id="FriendsFirst")
	private WebElement friendsFirstFilter;
	
	@FindBy(id="Followers")
	private WebElement followersFilter;
	
	@FindBy(id="RecentPlayers")
	private WebElement recentPlayersFilter;
	
	@FindBy(id="Mixer")
	private WebElement mixerFilter;
	
	//Entire friendlist(including friends, suggestions, and facebook link)
	@FindBy(id="friends")
	public WebElement entireList;
	
	public List<WebElement> gatherEntireList;
	
	//When see all filter is selected
	@FindBy(id="friendsseeallactionlink")
	public WebElement seeAllFriendsAndSuggestionsButton;
	
	@FindBy(xpath="//*[@id=\"All people\"]")
	public WebElement allPeopleFilterButton;
	
	@FindBy(id="AllPeople")
	public WebElement allPeopleFilter;
	
	@FindBy(id="PeopleYouMayKnow")
	public WebElement peopleYouMayKnowFilter;
	
	@FindBy(id="VIP")
	public WebElement vIPFilter;
	
	//Going back to general filter options
	@FindBy(id="friendsbackactionlink")
	public WebElement backSeeAllButton;
	//To store a list of names
	public List<String> nameList;
	
	//Rework:put the option to view profile, remove suggestion profile, into one List.
	public List<WebElement> suggestedAccounts;
	//
	public List<WebElement> generalAccountList;
	
	
	
	public static String recentlyViewed = "";
	public static String recentlyViewedURL = "";
	public static String recentSuggestedProfileRemoved = "";
	
	public WebElement findFacebookFriends;
	
	
	public FriendsandClubsComponent (WebDriver driver) {
		super(driver);
		gatherEntireList = entireList.findElements(By.className("friendList"));
		createSortFriendList();
		gatherNameList();
	}
	
	
	//create the lists for both suggestedAccounts and friend/results lists
	public void createSortFriendList() {
		if (suggestedAccounts != null) {
			suggestedAccounts.clear();
		}
		if (generalAccountList != null) {
			generalAccountList.clear();
		}
		for(WebElement sort: gatherEntireList) {
			try {
				if(sort.findElement(By.className("friendListTitleText")).getText().contains("Offline") ||
						sort.findElement(By.className("friendListTitleText")).getText().contains("Online")) {
					if (generalAccountList != null) {
						generalAccountList.addAll(sort.findElements(By.cssSelector(".xboxpeople.default")));
					}
					else {
						generalAccountList = sort.findElements(By.cssSelector(".xboxpeople.default"));
					}
				}
				if(sort.findElement(By.className("friendListTitleText")).getText().contains("Suggestions") ||
						sort.findElement(By.className("friendListTitleText")).getText().contains("Suggested for You")) {
					suggestedAccounts = sort.findElements(By.cssSelector(".xboxpeople.default"));
				}
			  //When filters remove the default lists.
			} catch(NoSuchElementException e) { }
		}
	}
	
	public void gatherNameList() {
		nameList = new ArrayList<String>();
		nameList.clear();
		try {
			for(WebElement e : generalAccountList) {
				String xboxAccountName = e.findElement(By.className("xboxprofileinfo")).findElement(By.className("name")).getText();
				nameList.add(xboxAccountName);
			}
		} catch (NullPointerException r) { }
		if (suggestedAccounts != null) {
			for(WebElement e : suggestedAccounts) {
				String xboxAccountName = e.findElement(By.className("xboxprofileinfo")).findElement(By.className("name")).getText();
				nameList.add(xboxAccountName);
			}
		}
	}
	
	public FriendsandClubsComponent removeSuggestedAccount() {
		Random rand = new Random();
		int randNum = rand.nextInt(suggestedAccounts.size());
		int counterWEList = 0;
		for (WebElement e : suggestedAccounts) {
			if(counterWEList == randNum) {
				recentSuggestedProfileRemoved = e.findElement(By.className("xboxprofileinfo")).findElement(By.className("name")).getText();
				e.findElement(By.id("removesuggestionactionbutton")).click();
				synchronized (driver) {
					  try {driver.wait(4000);} 
					  catch (InterruptedException wait) { wait.printStackTrace();}
				  }
				System.out.println("Profile " + getRecentSuggestedProfileRemoved()+ " was removed from suggestions");
				break;
			}
			counterWEList++;
		}
		return new FriendsandClubsComponent(driver);
	}
	
		public FriendsandClubsComponent viewAccount () {
			List<WebElement> combinedLists = generalAccountList;
			combinedLists.addAll(suggestedAccounts);
			Random rand = new Random();
			int randNum = rand.nextInt(combinedLists.size());
			int counterWEList = 0;
			for (WebElement e : combinedLists) {
				if(counterWEList == randNum) {
					recentlyViewed = e.findElement(By.className("xboxprofileinfo")).findElement(By.className("name")).getText();
					recentlyViewedURL = e.findElement(By.tagName("a")).getAttribute("href");
					e.findElement(By.tagName("a")).click();
					synchronized (driver) {
						  try {driver.wait(4000);} 
						  catch (InterruptedException wait) { wait.printStackTrace();}
					  }
					System.out.println("Viewing Profile: " + getRecentProfileViewed());
					System.out.println("Viewing Profile's URL: "+ recentlyViewedURL);
					break;
				}
				counterWEList++;
			}
			return new FriendsandClubsComponent(driver);
		}
	
	
	public void showNameList() {
		System.out.println("Checking list from the Friends & Club Side Bar: ");
		for(int i=0; i < nameList.size(); i++) {
			System.out.println("Profile "+ (i+1) +": " + nameList.get(i).toString());
		}
	}
	
	public FriendsandClubsComponent searchByText (String targetname) {
		findPeopleOrClubs.sendKeys(targetname);
		for(WebElement r : searchOrDeleteText) {
			if(r.getText().equalsIgnoreCase("Find people or clubs")) {
				r.click();
				break;
			}
		}
		synchronized (driver) {
			  try {driver.wait(2000);} 
			  catch (InterruptedException e) { e.printStackTrace();}
		}
		return new FriendsandClubsComponent(driver);
	}
	//Currently method not used due to recent website update
	public FriendsandClubsComponent removeTextQuery() {
		searchOrDeleteText = entireFriendSearchBar.findElements(By.tagName("button"));
		for(WebElement r : searchOrDeleteText) {
			if(r.getAttribute("title").equalsIgnoreCase("Cancel")) {
				r.click();
				break;
			}
		}
		synchronized (driver) {
			  try {driver.wait(2000);} 
			  catch (InterruptedException e) { e.printStackTrace();}
		  }
		return new FriendsandClubsComponent(driver);
	}
	
	
	
	public boolean matchInList(String targetname) {
		boolean matchFound = false;
		int matches = 0;
		for(int i=0; i < nameList.size(); i++) {
			System.out.println(nameList.get(i));
			if(targetname.equalsIgnoreCase(nameList.get(i).toString())) {
				matches++;
				System.out.println("Profile " + (i+1) + ": " + nameList.get(i));
			}
		}
		if(matches > 0) {
			System.out.println("We found a match with " + targetname);
			matchFound = true;
		} else {
			System.out.println(nameList.toString());
		}
		return matchFound;
	}
	
	
	
	public FriendsandClubsComponent generalFilter(String specificFilter) {
		searchFilterButton.click();
		if (specificFilter.equalsIgnoreCase("All")) {
			this.allFilter = searchFilterButton.findElement(By.id("All"));
			this.allFilter.click();
		}
		else if(specificFilter.equalsIgnoreCase("Clubs first")) {
			this.clubsFirstFilter.click();
		}
		else if(specificFilter.equalsIgnoreCase("Friends first")) {
			this.friendsFirstFilter.click();
		}
		else if(specificFilter.equalsIgnoreCase("Followers")) {
			this.followersFilter.click();
		}
		else if(specificFilter.equalsIgnoreCase("Recent players")) {
			this.recentPlayersFilter.click();
		}
		else if(specificFilter.equalsIgnoreCase("Mixer")) {
			this.mixerFilter.click();
		}
		else {
			this.searchFilterButton.click();
			System.out.println("There is no filter by that specification. Please try again.");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return new FriendsandClubsComponent(driver);
	}
	
	
	public FriendsandClubsComponent seeAllSuggestions() {
		seeAllFriendsAndSuggestionsButton.click();
		synchronized (driver) {
			  try {driver.wait(7000);} 
			  catch (InterruptedException e) { e.printStackTrace();}
		}
		return new FriendsandClubsComponent(driver);
	}
	
	
	public FriendsandClubsComponent allPeopleFilter (String specificFilter) {
		allPeopleFilterButton.click();
		if(specificFilter.equalsIgnoreCase("All people")) {
			this.allPeopleFilter.click();
		}
		else if(specificFilter.equalsIgnoreCase("People you may know")) {
			this.peopleYouMayKnowFilter.click();
		}
		else if(specificFilter.equalsIgnoreCase("VIP")) {
			this.vIPFilter.click();
		}
		else {
			System.out.println("There is no filter by that specification. Please try again.");
			allPeopleFilterButton.click();
		}
		synchronized (driver) {
			  try {driver.wait(5000);} 
			  catch (InterruptedException e) { e.printStackTrace();}
		}
		
		return new FriendsandClubsComponent(driver);
	}
	
	public String getRecentProfileViewed() {
		return recentlyViewed;
	}

	public String getRecentSuggestedProfileRemoved() {
		return recentSuggestedProfileRemoved;
	}
	
	public FriendsandClubsComponent goBackToGeneralFilter() {
		backSeeAllButton.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return new FriendsandClubsComponent(driver);
	}
	
	public void findFaceBookFriends() {
		findFacebookFriends = entireList.findElement(By.partialLinkText("Find facebook friends"));
		findFacebookFriends.click();
	}

}