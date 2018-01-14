package xboxLiveTestFramework_Tests;

import static org.testng.Assert.*;


import org.testng.annotations.*;

import xboxLivePageObjects_Lib.*;


@Test(groups = {"contentcheck", "mainpage"})
public class UnitContentTest extends BaseTest {
	XboxLiveMainPage mainPageCheck;
	
  @BeforeClass(groups = {"contentcheck", "mainpage", "signedout"})
  public void setupMainWebPage() {
	  driver.get("https://www.xbox.com/en-US/live");
	  mainPageCheck = new XboxLiveMainPage(driver);
	  assertTrue(mainPageCheck.isInitialized());
	  mainPageCheck.showMyXboxMenu();
	  synchronized (driver) {
		  try {driver.wait(2000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
  }
	
  @Test(groups = {"contentcheck", "mainpage", "signedout"})
  public void checkMainHome() {
	  assertTrue(mainPageCheck.contentExists("Home"));
  }
  
  @Test(groups = {"contentcheck", "mainpage", "signedout"})
  public void checkMainProfile() {
	  assertTrue(mainPageCheck.contentExists("Profile"));
  }
  
  @Test(groups = {"contentcheck", "mainpage", "signedout"})
  public void checkMainAchievements() {
	  assertTrue(mainPageCheck.contentExists("Achievements"));
  }
  
  @Test(groups = {"contentcheck", "mainpage", "signedout"})
  public void checkMainFriends() {
	  assertTrue(mainPageCheck.contentExists("Friends"));
  }
  
  @Test(groups = {"contentcheck", "mainpage", "signedout"})
  public void checkMainMessages() {
	  assertTrue(mainPageCheck.contentExists("Messages"));
  }
  
  @Test(groups = {"contentcheck", "mainpage", "signedout"})
  public void checkMainMyGame() {
	  assertTrue(mainPageCheck.contentExists("My games"));
  }
  
  @Test(groups = {"contentcheck", "mainpage", "signedout"})
  public void checkMainClubs() {
	  assertTrue(mainPageCheck.contentExists("Clubs"));
  }
  
  @Test(groups = {"contentcheck", "mainpage", "signedout"})
  public void checkMainTrending() {
	  assertTrue(mainPageCheck.contentExists("Trending on Xbox Live"));
  }
  
  @Test(groups = {"contentcheck", "mainpage", "signedout"})
  public void checkSignInComponent() {
	  SignInConfirmationComponent signInComponent = new SignInConfirmationComponent(driver);
	  assertNotEquals(signInComponent.confirmationSignIn(), "PopulousToast73");
  }
  
}
