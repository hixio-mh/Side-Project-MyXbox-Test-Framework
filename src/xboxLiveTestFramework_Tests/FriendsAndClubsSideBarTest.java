package xboxLiveTestFramework_Tests;


import static org.testng.Assert.*;

import org.testng.annotations.*;

import xboxLivePageObjects_Lib.*;

public class FriendsAndClubsSideBarTest extends BaseTest {
  @Test(groups= {"search", "friendsandclubs", "remove"})
  public void signInToSetUp() {
	  driver.navigate().to("https://www.xbox.com/en-US/live");
	  synchronized (driver) {
		  try {driver.wait(2000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  XboxLiveMainPage xboxLiveMainPage = new XboxLiveMainPage(driver);
	  xboxLiveMainPage.selectMyXbox("Home");
	  
	  SignInPage signInPage = new SignInPage(driver);
	  signInPage.enterEmail(dummyAccount.getEmail());
	  signInPage.enterPW(dummyAccount.getPW());
	  
	  SignInConfirmationComponent signInConfirmationComponent = signInPage.submit();
	  synchronized (driver) {
		  try {driver.wait(2000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  assertEquals(signInConfirmationComponent.confirmationSignIn(), "PopulousToast73");
	  synchronized (driver) {
		  try {driver.wait(5000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
  }
  
  @BeforeMethod(alwaysRun=true)
  public void waitForASec() {
	  synchronized (driver) {
		  try {driver.wait(5000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
  }
  
  @Test(groups= {"search", "friendsandclubs"})
  public void findByText() {
	  
	  FriendsandClubsComponent friendsAndClubsSideBar = new FriendsandClubsComponent(driver);
	  
	  friendsAndClubsSideBar = friendsAndClubsSideBar.searchByText(dummyAccount.getNameToAddFriend());
	  assertTrue(friendsAndClubsSideBar.matchInList(dummyAccount.getNameToAddFriend()));
	  
  }
  
  @Test(groups= {"friendsandclubs", "remove"})
  public void removeSuggestions() {
	  synchronized (driver) {
		  try {driver.wait(4000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  //Work on removal,view and add friends in suggested list
	  FriendsandClubsComponent friendsAndClubsSideBar = new FriendsandClubsComponent(driver);
	  //need to work removal by choose of numbers not names.
	  friendsAndClubsSideBar = friendsAndClubsSideBar.removeSuggestedAccount();
	  synchronized (driver) {
		  try {driver.wait(2000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  
  }
  
}
