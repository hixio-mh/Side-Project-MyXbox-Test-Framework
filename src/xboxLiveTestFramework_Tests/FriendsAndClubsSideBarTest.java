package xboxLiveTestFramework_Tests;


import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.*;

import xboxLivePageObjects_Lib.*;

public class FriendsAndClubsSideBarTest extends BaseTest {
 
	@BeforeClass(groups = {"frinendsandclubs", "search", "remove", "filters"})
	public void contentCheck() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println(driver.getCurrentUrl());
		assertTrue(driver.getCurrentUrl().equals("https://account.xbox.com/en-us/social?xr=socialtwistnav") 
				|| driver.getCurrentUrl().equals("https://account.xbox.com/en-US/social?xr=shellnav&wa=wsignin1.0"));
			
		
		FriendsandClubsComponent friendsAndClubsSideBar = new FriendsandClubsComponent(driver);
		assertTrue(friendsAndClubsSideBar.seeAllFriendsAndSuggestionsButton.isDisplayed());
		assertTrue(friendsAndClubsSideBar.searchFilterButton.isDisplayed());
	}
 
	
  @AfterMethod(groups = {"friendsandclubs"})
  public void startPoint(){
		driver.get("https://account.xbox.com/en-us/social?xr=socialtwistnav");
		synchronized (driver) {
			  try {driver.wait(4000);} 
			  catch (InterruptedException e) { e.printStackTrace();}
		  }
	}
  
  @Test(groups= { "friendsandclubs", "remove"})
  public void removeSuggestions() {
	  
	  FriendsandClubsComponent friendsAndClubsSideBar = new FriendsandClubsComponent(driver);
	  friendsAndClubsSideBar.showNameList();
	  friendsAndClubsSideBar = friendsAndClubsSideBar.removeSuggestedAccount();
	  synchronized (driver) {
		  try {driver.wait(2000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  
	  friendsAndClubsSideBar.showNameList();
	  assertFalse(friendsAndClubsSideBar.matchInList(friendsAndClubsSideBar.getRecentSuggestedProfileRemoved()));
	
	 
  }
  
  @Test(groups= {"search", "friendsandclubs"})
  public void findByText()  {
	  FriendsandClubsComponent friendsAndClubsSideBar = new FriendsandClubsComponent(driver);
	  friendsAndClubsSideBar.showNameList();
	  synchronized (driver) {
		  try {driver.wait(2000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  assertTrue(friendsAndClubsSideBar.findPeopleOrClubs.isDisplayed());
	  
	  friendsAndClubsSideBar = friendsAndClubsSideBar.searchByText(dummyAccount.getNameToAddFriend());
	  
	  try {
	  assertFalse(friendsAndClubsSideBar.seeAllFriendsAndSuggestionsButton.isSelected());
	  assertFalse(friendsAndClubsSideBar.findFacebookFriends.isDisplayed());
	  } catch(NoSuchElementException e) {
		  System.out.println("The See All Suggestion Button and Face Book isn't visiable");
	  }
	  assertTrue(friendsAndClubsSideBar.searchFilterButton.isDisplayed());
	  
	  friendsAndClubsSideBar.showNameList();
	  System.out.println("Looking for a match with: " + dummyAccount.getNameToAddFriend());
	  assertTrue(friendsAndClubsSideBar.matchInList(dummyAccount.getNameToAddFriend()));
	  
	  //Asserting the delete text in the search bar is displayed and can be deleted.
	  
	  friendsAndClubsSideBar = friendsAndClubsSideBar.generalFilter("All");
	  
	  //Ensure that we returned to default state.
	  assertTrue(friendsAndClubsSideBar.seeAllFriendsAndSuggestionsButton.isDisplayed());
	  
  }
  
  @Test(groups= {"friendsandclubs", "view"})
  public void viewAccounts() {
	  FriendsandClubsComponent friendsAndClubsSideBar = new FriendsandClubsComponent(driver);
	  
	  friendsAndClubsSideBar.showNameList();
	  friendsAndClubsSideBar = friendsAndClubsSideBar.viewAccount();
	  synchronized (driver) {
		  try {driver.wait(2000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  
	//TODO work on the Profile page object
  }
  
  
  @Test(groups= {"friendsandclubs", "facebook"})
  public void navigatingToFaceBook() {
	  FriendsandClubsComponent friendsAndClubsSideBar = new FriendsandClubsComponent(driver);
	  
	  
	  friendsAndClubsSideBar.findFaceBookFriends();
	  assertEquals(driver.getCurrentUrl(),"https://account.xbox.com/en-us/friends/facebookfriends");
	  assertEquals(driver.findElement(By.className("landingHeader")).getText(), "You can now connect with your "
	  		+ "Facebook friends on Xbox Live. Click below to get started.");
	  assertEquals(driver.findElement(By.id("startFriendFinder")).getText(), "Find Friends");
	  
	  driver.navigate().back();
	  
	  assertEquals(driver.getCurrentUrl(), "https://account.xbox.com/en-us/social?xr=socialtwistnav");
	  friendsAndClubsSideBar = new FriendsandClubsComponent(driver);
	  assertTrue(friendsAndClubsSideBar.entireList.isDisplayed());
	  
	  
  }
  
  
  @Test(groups= {"friendsandclubs", "filters"})
  public void generalFilters() {
	  FriendsandClubsComponent friendsAndClubsSideBar = new FriendsandClubsComponent(driver);
	  
	  assertTrue(friendsAndClubsSideBar.searchFilterButton.isDisplayed());
	  
	  friendsAndClubsSideBar = friendsAndClubsSideBar.generalFilter("All");
	  
	  assertTrue(friendsAndClubsSideBar.searchFilterButton.isDisplayed());
	  assertEquals(friendsAndClubsSideBar.searchFilterButton.getText(), "All");
	  
	  
	  friendsAndClubsSideBar = friendsAndClubsSideBar.generalFilter("ClubsFirst");
	  assertEquals(friendsAndClubsSideBar.searchFilterButton.getText(), "Clubs first");
	  
	  friendsAndClubsSideBar = friendsAndClubsSideBar.generalFilter("FriendsFirst");
	  assertEquals(friendsAndClubsSideBar.searchFilterButton.getText(), "Friends first");
	  
	  friendsAndClubsSideBar = friendsAndClubsSideBar.generalFilter("Followers");
	  assertEquals(friendsAndClubsSideBar.searchFilterButton.getText(), "Followers");
	  
	  friendsAndClubsSideBar = friendsAndClubsSideBar.generalFilter("RecentPlayers");
	  assertEquals(friendsAndClubsSideBar.searchFilterButton.getText(), "Recent players");
	  
	  friendsAndClubsSideBar = friendsAndClubsSideBar.generalFilter("Mixer");
	  assertEquals(friendsAndClubsSideBar.searchFilterButton.getText(), "Mixer");
	  assertEquals(friendsAndClubsSideBar.entireList.findElement(By.className("name")).getText(), "Link your XBL account with Mixer "
	  																					+ "and connect to your friends on Mixer today");
	  friendsAndClubsSideBar = friendsAndClubsSideBar.generalFilter("");
	  try {
	  assertEquals(friendsAndClubsSideBar.searchFilterButton.getText(), "All");
	  } catch (AssertionError e) {
		  System.out.println("There wasn't a match to the word from the last filter attempt.");
	  }
	  
	  friendsAndClubsSideBar = friendsAndClubsSideBar.generalFilter("All");
	  assertTrue(friendsAndClubsSideBar.seeAllFriendsAndSuggestionsButton.isDisplayed());
	  
  }
  
  @Test(groups= {"friendsandclubs", "filters"})
  public void seeAllFilter() {
	  FriendsandClubsComponent friendsAndClubsSideBar = new FriendsandClubsComponent(driver);
	  
	  assertTrue(friendsAndClubsSideBar.seeAllFriendsAndSuggestionsButton.isDisplayed());
	  
	  friendsAndClubsSideBar = friendsAndClubsSideBar.seeAllSuggestions();
	  
	  assertTrue(friendsAndClubsSideBar.backSeeAllButton.isDisplayed());
	  assertTrue(friendsAndClubsSideBar.allPeopleFilterButton.isDisplayed());
	  assertEquals(friendsAndClubsSideBar.allPeopleFilterButton.getText(), "All people");
	  assertTrue(friendsAndClubsSideBar.suggestedAccounts.size() > 2);
	  
	  friendsAndClubsSideBar = friendsAndClubsSideBar.allPeopleFilter("People you may know");
	  
	  assertEquals(friendsAndClubsSideBar.allPeopleFilterButton.getText(), "People you may know");
	  
	  friendsAndClubsSideBar = friendsAndClubsSideBar.allPeopleFilter("VIP");
	  
	  assertEquals(friendsAndClubsSideBar.allPeopleFilterButton.getText(), "VIP");
	  
	  friendsAndClubsSideBar = friendsAndClubsSideBar.allPeopleFilter("All people");
	  
	  assertEquals(friendsAndClubsSideBar.allPeopleFilterButton.getText(), "All people");
	  
	  friendsAndClubsSideBar = friendsAndClubsSideBar.goBackToGeneralFilter();
	  
	  assertEquals(friendsAndClubsSideBar.searchFilterButton.getText(), "All");
	  assertTrue(friendsAndClubsSideBar.seeAllFriendsAndSuggestionsButton.isDisplayed());
	  
  }
  
  
  //TODO test a method that an account can be search, found, viewed and added as a friend to appear in the friend list
  //then remove friend and verify that the profile is gone from the friendslist.
  
  //TODO test a method by calling all previous methods on Webpages: Profile, Achievements, and Friends (This method is all done first on the home page.)
  
}
