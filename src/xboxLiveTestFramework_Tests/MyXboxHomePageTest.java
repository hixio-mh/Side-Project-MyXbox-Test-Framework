package xboxLiveTestFramework_Tests;

import org.testng.annotations.Test;

import xboxLivePageObjects_Lib.*;


import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.*;

public class MyXboxHomePageTest extends BaseTest {
	@AfterMethod(groups= {"homepage"})
	public void startPoint() {
	  driver.get("https://account.xbox.com/en-us/social?xr=socialtwistnav");
	}
	@BeforeClass(groups= {"homepage"})
	public void pause() {
		synchronized (driver) {
			  try {driver.wait(2000);} 
			  catch (InterruptedException e) { e.printStackTrace();}
		}
	}
	
	// Rework this to incorporate content tests and resolve unexpected errors or resolving a better stale element solution for ActivityFeed parts.
	
	  @Test(groups= {"homepage", "post"})
	  public void postNewMessage() {
		  //ActivityFeed Component instead
		  ActivityFeedComponent homeActivityFeed = new ActivityFeedComponent(driver);
		  
		  homeActivityFeed.newPost();
		  synchronized (driver) {
			  try {driver.wait(4000);} 
			  catch (InterruptedException e) { e.printStackTrace();}
		  }
		  assertTrue(homeActivityFeed.findNewPost());
		  
	  }
	  
	  @Test(groups= {"homepage", "like"})
	  public void postALike() {
		  ActivityFeedComponent homeActivityFeed = new ActivityFeedComponent(driver);
		  
		  homeActivityFeed.likeAnyPost();
		  
		  homeActivityFeed = homeActivityFeed.refresh();
		  synchronized (driver) {
			  try {driver.wait(4000);} 
			  catch (InterruptedException e) { e.printStackTrace();}
		  }
		  assertTrue(homeActivityFeed.findRecentLikes());
	  }
	  
	  @Test(groups= {"homepage", "post"})
	  public void openBoxAndComment() {
		  ActivityFeedComponent homeActivityFeed = new ActivityFeedComponent(driver);
		  
		  homeActivityFeed.viewAnyPostAndComment();
		  synchronized (driver) {
			  try {driver.wait(4000);} 
			  catch (InterruptedException e) { e.printStackTrace();}
		  }
		  assertTrue(homeActivityFeed.veiwNewCommentDialogBox());
		  
	  }
	  
	  @Test(groups= {"homepage", "post"})
	  public void openBoxAndCommentThenDelete() {
		  ActivityFeedComponent homeActivityFeed = new ActivityFeedComponent(driver);
		  
		  homeActivityFeed.viewAnyPostAndComment();
		  synchronized (driver) {
			  try {driver.wait(4000);} 
			  catch (InterruptedException e) { e.printStackTrace();}
		  }
		  assertTrue(homeActivityFeed.veiwNewCommentDialogBox());
		  
		  assertTrue(homeActivityFeed.deleteRecentComment());
		  
	  }
	  
	  @Test(groups= {"homepage", "post"})
	  public void sharePosts() {
		  ActivityFeedComponent homeActivityFeed = new ActivityFeedComponent(driver);
		  
		  homeActivityFeed.shareAnyPost();
		  synchronized (driver) {
			  try {driver.wait(4000);} 
			  catch (InterruptedException e) { e.printStackTrace();}
		  }
		  homeActivityFeed = homeActivityFeed.refresh();
		  assertTrue(homeActivityFeed.findRecentShare());
		  
		  homeActivityFeed.expandPostedActivityFeed();
		  homeActivityFeed.expandPostedActivityFeed();
		  
		  assertTrue(homeActivityFeed.originalPostShared(dummyAccount.getProfileName()));
		  
	  }
	  
	  @Test(groups= {"homepage"})
	  public void deletePost() {
		  ActivityFeedComponent homeActivityFeed = new ActivityFeedComponent(driver);
		  
		  homeActivityFeed = homeActivityFeed.deletePost();
		  
		  homeActivityFeed.expandPostedActivityFeed();
		  
		  assertFalse(homeActivityFeed.findMatchInPost());
	  }
	  
	  @Test(groups= {"homepage"})
	  public void expandList() {
		  ActivityFeedComponent homeActivityFeed = new ActivityFeedComponent(driver);
		  System.out.println("Feed Size: " + homeActivityFeed.postedActivityFeed.size());
		  
		  homeActivityFeed.expandPostedActivityFeed();
		  
		  System.out.println("Feed Size: " + homeActivityFeed.postedActivityFeed.size());
		  
		  assertTrue(homeActivityFeed.postedActivityFeed.size() > 9);
	  }
	  
	  @Test(groups= {"homepage"})
	  public void checkPostedLinks() {
		  ActivityFeedComponent homeActivityFeed = new ActivityFeedComponent(driver);
		  
		  homeActivityFeed.expandPostedActivityFeed();
		  
		  assertTrue(homeActivityFeed.hyperLinkDestination());
		  
	  }
	  
	  @Test(groups= {"homepage", "mixer"})
	  public void pickRandomMixerStream() {
		  MyXboxHomePage myXboxHomePage = new MyXboxHomePage(driver);
		  
		  assertTrue(myXboxHomePage.headerMixerStreams.isDisplayed());
		  
		  assertTrue(myXboxHomePage.pickRandomMixerChannel());
		  
	  }
	  
	  @Test(groups= {"homepage", "mixer"})
	  public void seeAllMixerStream() {
		  MyXboxHomePage myXboxHomePage = new MyXboxHomePage(driver);
		  
		  myXboxHomePage.seeAllMixerChannels();
		  
		  assertEquals(driver.getCurrentUrl(), "https://account.xbox.com/en-us/trendingstreams");
		  
		  driver.navigate().back();
		  
		  assertEquals(driver.getCurrentUrl(), "https://account.xbox.com/en-us/social?xr=socialtwistnav");
	  }
	  
	  
	  
	  //Friends and Clubs Component Portion
	  
	  @Test(groups= { "friendsandclubs", "remove", "homepage"})
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
	  
	  @Test(groups= {"search", "friendsandclubs", "homepage"})
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
	  
	  @Test(groups= {"friendsandclubs", "view", "homepage"})
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
	  
	  
	  @Test(groups= {"friendsandclubs", "facebook","homepage" })
	  public void navigatingToFaceBook() {
		  FriendsandClubsComponent friendsAndClubsSideBar = new FriendsandClubsComponent(driver);
		  synchronized (driver) {
			  try {driver.wait(4000);} 
			  catch (InterruptedException e) { e.printStackTrace();}
		  }
		  
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
	  
	  
	  @Test(groups= {"friendsandclubs", "filters", "homepage"})
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
	  
	  @Test(groups= {"friendsandclubs", "filters", "homepage"})
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
  
	  


}
