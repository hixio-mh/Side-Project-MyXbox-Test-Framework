package xboxLiveTestFramework_Tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.*;

import xboxLivePageObjects_Lib.*;

public class FriendsAndClubsSideBarTest extends BaseTest {
  @BeforeMethod(groups= "friendsandclubs")
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
		  try {driver.wait(2000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
  }
  
  @Test(groups= "friendsandclubs")
  public void findByText() {
	  FriendsandClubsComponent friendsAndClubsSideBar = new FriendsandClubsComponent(driver);
	  
	  friendsAndClubsSideBar.searchByText(dummyAccount.getNameToAddFriend());
	  
	  //Work on search text results.
	  
  }
  
  @Test(groups= "friendsandclubs")
  public void removeSuggestions() {
	  //Work on removal,view and add friends in suggested list
  }
  
}
