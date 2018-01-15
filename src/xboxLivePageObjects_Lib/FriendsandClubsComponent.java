package xboxLivePageObjects_Lib;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class FriendsandClubsComponent extends PageObject {
	
	@FindBy(id="tertiaryR1")
	private WebElement friendsAndClubsSection;
	
	@FindBy(name="searchtext")
	private WebElement findPeopleOrClubs;
	
	@FindBy(xpath="//*[@id=\"Xbox-Friends-ku6a8ul\"]/div/div[2]/form/button")
	private WebElement searchButton;
	
	//General filter button
	@FindBy(xpath="//*[@id=\"All\"]")
	private WebElement searchFilterButton;
	
	@FindBy(id="All")
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
	
	//When see all filter is selected
	@FindBy(id="friendsseeallactionlink")
	private WebElement seeAllFriendsAndSuggestionsButton;
	
	@FindBy(xpath="//*[@id=\"All people\"]")
	private WebElement allPeopleFilterButton;
	
	@FindBy(id="AllPeople")
	private WebElement allPeopleFilter;
	
	@FindBy(id="PeopleYouMayKnow")
	private WebElement peopleYouMayKnowFilter;
	
	@FindBy(id="VIP")
	private WebElement vIPFilter;
	
	//Going back to general filter options
	@FindBy(id="friendsbackactionlink")
	private WebElement backSeeAllButton;
	//To store a list of names
	private List<String> nameList;
	
	//Rework:put the option to view profile, remove suggestion profile, or add friends into one List.
	private List<WebElement> suggestedAccounts;
	//Currently unused
	@FindBy(id="removesuggestionactionbutton")
	private WebElement removeAccountFromSuggestionsButton;

	//Currently unused
	@FindBy(id="addfriendactionbutton")
	private WebElement addFriendButton;
	
	
	
	@FindBy(xpath="//*[@id=\"friends\"]/div/ul/li[3]/div/a")
	private WebElement findFacebookFriends;
	
	
	public FriendsandClubsComponent (WebDriver driver) {
		super(driver);
		suggestedAccounts = friendsAndClubsSection.findElements(By.cssSelector(".xboxpeople.default"));
		this.nameList = new ArrayList<String>();
		this.nameList.clear();
		for(WebElement e : suggestedAccounts) {
			String xboxAccountName = e.findElement(By.className("xboxprofileinfo")).findElement(By.className("name")).getText();
			nameList.add(xboxAccountName);
		}
	}
	//rework (since suggested will be random on players. And to avoid more likely fail exceptions.
	public FriendsandClubsComponent removeSuggestedAccount() {
		Random rand = new Random();
		int randNum = rand.nextInt(suggestedAccounts.size());
		int counterWEList = 0;
		for (WebElement e : suggestedAccounts) {
			if(counterWEList == randNum) {
				e.findElement(By.id("removesuggestionactionbutton")).click();
				break;
			}
			counterWEList++;
		}
		return new FriendsandClubsComponent(driver);
	}
	//rework (since suggested will be random on players.)
	public FriendsandClubsComponent addSuggestedAccount (String targetname) {
		for (WebElement e : suggestedAccounts) {
			String xboxAccountName = driver.findElement(By.className("name")).getText();
			String xboxAccountSubName = driver.findElement(By.className("subname")).getText();
			if(targetname.equalsIgnoreCase(xboxAccountName) || targetname.equalsIgnoreCase(xboxAccountSubName)) {
				e.findElement(By.id("addfriendactionbutton")).click();
				break;
			}
		}
		return new FriendsandClubsComponent(driver);
	}
	
	public FriendsandClubsComponent viewSuggestedAccount (String targetname) {
		for (WebElement e : suggestedAccounts) {
			String xboxAccountName = driver.findElement(By.className("name")).getText();
			String xboxAccountSubName = driver.findElement(By.className("subname")).getText();
			if(targetname.equalsIgnoreCase(xboxAccountName) || targetname.equalsIgnoreCase(xboxAccountSubName)) {
				e.findElement(By.partialLinkText(xboxAccountName)).click();
				break;
			}
		}
		return new FriendsandClubsComponent(driver);
	}
	
	public FriendsandClubsComponent searchByText (String targetname) {
		findPeopleOrClubs.sendKeys(targetname);
		searchButton.click();
		
		return new FriendsandClubsComponent(driver);
	}
	
	//Create a method to view suggested/search results profiles and get names
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
			matchFound = true;
		} else {
			System.out.println(nameList.toString());
		}
		return matchFound;
	}
	
	public void showNameList() {
		for(int i=0; i < nameList.size(); i++) {
			System.out.println("Profile 1: " + nameList.get(i).toString());
		}
		
	}
	
	public FriendsandClubsComponent generalFilter(String specificFilter) {
		searchFilterButton.click();
		if (specificFilter.equalsIgnoreCase("All")) {
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
		return new FriendsandClubsComponent(driver);
	}
	
	
	public FriendsandClubsComponent seeAllSuggestions() {
		seeAllFriendsAndSuggestionsButton.click();
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
		return new FriendsandClubsComponent(driver);
	}
	
	public FriendsandClubsComponent goBackToGeneralFilter() {
		backSeeAllButton.click();
		return new FriendsandClubsComponent(driver);
	}
	
	public void findFaceBookFriends() {
		findFacebookFriends.click();
	}

}
