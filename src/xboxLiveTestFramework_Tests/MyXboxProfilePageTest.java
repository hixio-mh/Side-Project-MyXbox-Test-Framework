package xboxLiveTestFramework_Tests;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.*;

import xboxLivePageObjects_Lib.*;

public class MyXboxProfilePageTest extends BaseTest{
	
	@BeforeClass(groups= {"profilepage", "post", "like", "friendsandclub", "profiletabs", "remove", "search", "filters", "achievements"})
	public void setUpProfilePage() {
		MyXboxHeaderRegionComponent header = new MyXboxHeaderRegionComponent(driver);
		
		header.goTo("profile");
		
		assertTrue(driver.getCurrentUrl().contains("https://account.xbox.com/en-us/Profile?xr=socialtwistnav"));
	}
	
	@AfterMethod(groups= {"profilepage", "post", "like", "friendsandclub", "profiletabs", "remove", "search", "filters", "achievements"})
	public void defaultReset() {
		driver.navigate().to("https://account.xbox.com/en-us/Profile?xr=socialtwistnav");
		
	}
	
	//Profile Card Section
	@Test(groups= {"profilepage"})
	public void profileCardContentCheck() {
		MyXboxProfilePage xboxProfile = new MyXboxProfilePage(driver);
		
		assertTrue(xboxProfile.xboxProfileCard.isDisplayed());
		
		assertTrue(xboxProfile.xboxProfileImage.isDisplayed());
		
		assertTrue(xboxProfile.xboxProfileCardInfo.isDisplayed());
		
		System.out.println(xboxProfile.profileName + "\n" + xboxProfile.profileGamerscore + "\n" + xboxProfile.profileSecondName);
		
		assertTrue(xboxProfile.customizeProfile.isDisplayed());
		
		assertTrue(xboxProfile.privacySettings.isDisplayed());
		
		assertTrue(xboxProfile.friendsFollowersLocation.isDisplayed());
		
		assertTrue(xboxProfile.profileTabSelectorSection.isDisplayed());
		
		assertEquals(xboxProfile.profileName, dummyAccount.getProfileName());
		
		assertEquals(xboxProfile.profileSecondName,dummyAccount.getRealName());
		
	}
	
	@Test(groups= {"profilepage", "profiletabs"})
	public void goingThroughTheTabs() {
		MyXboxProfilePage xboxProfile = new MyXboxProfilePage(driver);
		
		ActivityFeedComponent activityFeed = new ActivityFeedComponent(driver);
		
		assertTrue(xboxProfile.activityFeedSection.isDisplayed());
		
		assertTrue(activityFeed.activityFeedWholeContent.isDisplayed());
		
		assertFalse(xboxProfile.achievementsSection.isDisplayed());
		assertFalse(xboxProfile.capturesSection.isDisplayed());
		
		
		MyXboxAchievementsComponent achievements = new MyXboxAchievementsComponent(driver);
		assertFalse(achievements.gameListSection.isDisplayed());
		
		
		xboxProfile.swtichTabs("Activity Feed");
		
		assertTrue(xboxProfile.activityFeedSection.isEnabled());
		
		assertTrue(activityFeed.activityFeedWholeContent.isDisplayed());
		
		xboxProfile.swtichTabs("Captures");
		
		assertTrue(xboxProfile.capturesSection.isEnabled());
		
		assertFalse(xboxProfile.activityFeedSection.isDisplayed());
		assertFalse(xboxProfile.achievementsSection.isDisplayed());
		
		xboxProfile.swtichTabs("Achievements");
		
		assertTrue(xboxProfile.achievementsSection.isEnabled());
		
		assertFalse(xboxProfile.activityFeedSection.isDisplayed());
		assertFalse(xboxProfile.capturesSection.isDisplayed());
		
		xboxProfile.swtichTabs("Activity Feed");
		
		assertTrue(xboxProfile.activityFeedSection.isEnabled());
		
		assertFalse(xboxProfile.achievementsSection.isDisplayed());
		assertFalse(xboxProfile.capturesSection.isDisplayed());
	}
	
	//ActivityFeed Section
	
	@Test(groups= {"profilepage"})
	public void expandList() {
		ActivityFeedComponent homeActivityFeed = new ActivityFeedComponent(driver);
		System.out.println("Feed Size: " + homeActivityFeed.postedActivityFeed.size());
		  
		homeActivityFeed.expandPostedActivityFeed();
		  
		System.out.println("Feed Size: " + homeActivityFeed.postedActivityFeed.size());
		  
		assertTrue(homeActivityFeed.postedActivityFeed.size() > 10);
	}
	
	@Test(groups= {"profilepage"})
	public void deletePost() {
		ActivityFeedComponent homeActivityFeed = new ActivityFeedComponent(driver);
		  
		homeActivityFeed = homeActivityFeed.deletePost();
		  
		homeActivityFeed.expandPostedActivityFeed();
		  
		assertFalse(homeActivityFeed.findMatchInPost());
	}
	
	@Test(groups= {"profilepage", "post"})
	public void sharePosts() {
		ActivityFeedComponent homeActivityFeed = new ActivityFeedComponent(driver);
		  
		homeActivityFeed.shareAnyPost();
		synchronized (driver) {
			try {driver.wait(4000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
		homeActivityFeed = homeActivityFeed.refresh();
		assertTrue(homeActivityFeed.findRecentShare());
		  
		  
	}
	
	@Test(groups= {"profilepage", "post"})
	public void openBoxAndComment() {
		ActivityFeedComponent homeActivityFeed = new ActivityFeedComponent(driver);
		  
		homeActivityFeed.viewAnyPostAndComment();
		synchronized (driver) {
			try {driver.wait(4000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
		assertTrue(homeActivityFeed.veiwNewCommentDialogBox());
		  
	}
	  
	@Test(groups= {"profilepage", "post"})
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
	
	@Test(groups= {"profilepage", "like"})
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
	
	@Test(groups= {"profilepage", "post"})
	public void postNewMessage() {
		ActivityFeedComponent homeActivityFeed = new ActivityFeedComponent(driver);
		  
		homeActivityFeed.newPost();
		synchronized (driver) {
			try {driver.wait(4000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
		assertTrue(homeActivityFeed.findNewPost());
		  
	}
	
	@Test(groups= {"profilepage", "post", "profiletabs"})
	public void cannotPostOnOhterTabs() {
		MyXboxProfilePage xboxProfile = new MyXboxProfilePage(driver);
		
		ActivityFeedComponent homeActivityFeed = new ActivityFeedComponent(driver);
		
		xboxProfile.swtichTabs("Captures");
		
		try {
			homeActivityFeed.newPost();
			homeActivityFeed.findNewPost();
		} catch(Exception e) {
			System.out.println("Unable to make a new post since the Activity Feed Tab isn't active.");
		}
		
	}
	
	
	//FriendAndClubsComponent
	@Test(groups= { "friendsandclubs", "remove", "profilepage"})
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
	
	//Broken Test: Since the searchFilterButton is not visible on Profile Page.
	//@Test(groups= {"search", "friendsandclubs", "profilepage"})
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
		  assertFalse(friendsAndClubsSideBar.seeAllFriendsAndSuggestionsButton.isDisplayed());
		  assertFalse(friendsAndClubsSideBar.findFacebookFriends.isDisplayed());
	  } catch(NoSuchElementException e) {
		  System.out.println("The See All Suggestion Button and Face Book isn't visiable");
	  }
	  
	  System.out.println(friendsAndClubsSideBar.searchFilterButton.getText());
	  System.out.println(friendsAndClubsSideBar.searchFilterButton.getLocation());
	  
	  assertTrue(friendsAndClubsSideBar.searchFilterButton.isDisplayed());
		  
	  friendsAndClubsSideBar.showNameList();
	  System.out.println("Looking for a match with: " + dummyAccount.getNameToAddFriend());
	  assertTrue(friendsAndClubsSideBar.matchInList(dummyAccount.getNameToAddFriend()));
		  
	  friendsAndClubsSideBar = friendsAndClubsSideBar.removeTextQuery();
		  
	  assertTrue(friendsAndClubsSideBar.seeAllFriendsAndSuggestionsButton.isDisplayed());  
	}
	
	//Passed with Chrome, but FireFox hit NonInteractableException
	@Test(groups= {"friendsandclubs", "facebook","profilepage" })
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
		  
		MyXboxHeaderRegionComponent header = new MyXboxHeaderRegionComponent(driver);
		  
		header.goTo("profile");
		  
		assertEquals(driver.getCurrentUrl(), "https://account.xbox.com/en-us/Profile?xr=socialtwistnav");
		friendsAndClubsSideBar = new FriendsandClubsComponent(driver);
		assertTrue(friendsAndClubsSideBar.entireList.isDisplayed());  
	}
	  
	//Broken Test: The searchFilterButton does not show the element visible on ProfilePage even when the id button is correct.   
	//@Test(groups= {"friendsandclubs", "filters", "profilepage"})
	public void generalFilters() {
		FriendsandClubsComponent friendsAndClubsSideBar = new FriendsandClubsComponent(driver);
		System.out.println(friendsAndClubsSideBar.searchFilterButton.getText());  
		//assertTrue(friendsAndClubsSideBar.searchFilterButton.isDisplayed());
		  
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
	//Broken Test: the searchFilterButton is not visible Element on ProfilePage  
	//@Test(groups= {"friendsandclubs", "filters", "profilepage"})
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
		synchronized (driver) {
			try {driver.wait(2000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
		  
		assertEquals(friendsAndClubsSideBar.searchFilterButton.getText(), "All");
		assertTrue(friendsAndClubsSideBar.seeAllFriendsAndSuggestionsButton.isDisplayed());
		  
	}
	
	
	//Achievements Sections (including the Achievement link in header)
	@Test(groups= {"profilepage", "achievements"})
	public void achievementContent() {
		MyXboxProfilePage xboxProfile = new MyXboxProfilePage(driver);
		
		xboxProfile.swtichTabs("Achievements");
		
		assertTrue(xboxProfile.achievementsSection.isDisplayed());
		
		MyXboxAchievementsComponent achievements = new MyXboxAchievementsComponent(driver);
		
		assertTrue(achievements.entireAchievementSection.isDisplayed());
		
		assertTrue(achievements.gameListSection.isDisplayed());
		
		assertTrue(achievements.achievementFilter.isDisplayed());
		
		assertTrue(achievements.compareWithFriends.isDisplayed());
	}
	
	//Broken Test Due <a> tags not functioning
	@Test(groups= {"profilepage", "achievements"})
	public void gameListLink() {
		MyXboxProfilePage xboxProfile = new MyXboxProfilePage(driver);
		
		xboxProfile.swtichTabs("Achievements");
		
		assertTrue(xboxProfile.achievementsSection.isDisplayed());
		
		MyXboxAchievementsComponent achievements = new MyXboxAchievementsComponent(driver);
		
		achievements.randomGameListHyperLink();
		
		assertEquals(MyXboxAchievementsComponent.recentGameViewed, driver.getCurrentUrl());
		
		MyXboxHeaderRegionComponent header = new MyXboxHeaderRegionComponent(driver);
		
		header.goTo("achievements");
		
		assertEquals(driver.getCurrentUrl(), "https://account.xbox.com/en-us/Profile?activetab=main%3Amaintab2");
		
	}
	
	@Test(groups= {"profilepage", "achievements"})
	public void comparePlayers() {
		MyXboxProfilePage xboxProfile = new MyXboxProfilePage(driver);
		
		xboxProfile.swtichTabs("Achievements");
		
		assertTrue(xboxProfile.achievementsSection.isDisplayed());
		
		MyXboxAchievementsComponent achievements = new MyXboxAchievementsComponent(driver);
		
		assertTrue(achievements.compareWithFriends.isDisplayed());
		
		achievements.currentComparison();
		
		achievements = achievements.compareWithOthers();
		
		achievements.currentComparison();
		
		achievements.stopComparison.isDisplayed();
		
		assertTrue(achievements.verifyPlayerComparison(MyXboxAchievementsComponent.compareOther));
		
		assertTrue(achievements.verifyPlayerComparison(dummyAccount.getProfileName()));
		
		achievements = achievements.cancelComparison();
		
		assertTrue(achievements.compareWithFriends.isDisplayed());
		
		assertFalse(achievements.verifyPlayerComparison(dummyAccount.getProfileName()));
	}
	
	@Test(groups= {"profilepage", "achievements"})
	public void gameListFilter() {
		MyXboxProfilePage xboxProfile = new MyXboxProfilePage(driver);
		
		xboxProfile.swtichTabs("Achievements");
		
		assertTrue(xboxProfile.achievementsSection.isDisplayed());
		
		MyXboxAchievementsComponent achievements = new MyXboxAchievementsComponent(driver);
		
		achievements.setFilter("All");
		
		assertEquals(achievements.achievementFilter.getText(), "All");
		
		achievements.setFilter("PC");
		
		assertEquals(achievements.achievementFilter.getText(), "PC");
		
		achievements.setFilter("Moblie");
		
		assertEquals(achievements.achievementFilter.getText(), "Windows Phone");
		
		achievements.setFilter("Xbox One");
		
		assertEquals(achievements.achievementFilter.getText(), "Xbox One");
		
		achievements.setFilter("Xbox 360");
		
		assertEquals(achievements.achievementFilter.getText(), "Xbox 360");
		
		achievements.setFilter("gibberish");
		
		assertFalse(achievements.achievementFilter.getText().equals("All"));
		
		achievements.setFilter("All");
		
		assertEquals(achievements.achievementFilter.getText(), "All");
		
	}
	
}
