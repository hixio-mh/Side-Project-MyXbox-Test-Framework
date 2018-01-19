package xboxLiveTestFramework_Tests;

import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.*;

import xboxLivePageObjects_Lib.*;

public class SignInProfileTest extends BaseTest {
	Driver driver2;
	@BeforeClass(groups = {"SignInAccount"})
	public void openSeperateBrowser () throws InterruptedException {
		driver2 = new Driver(getBrowserName());
		driver2.get("https://www.xbox.com/en-US/live");
		driver2.manage().window().maximize();
		
	}
	
	//Create test methods for failure to sign into an account, incorrect email, or bad creds.
	
  @Test(groups = {"SignInAccount"})
  public void signUpFromMainToProfile() {
	  
	  driver2.get("https://www.xbox.com/en-US/live");
	  synchronized (driver2) {
		  try {driver2.wait(2000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  XboxLiveMainPage xboxLiveMainPage = new XboxLiveMainPage(driver2);
	  assertTrue(xboxLiveMainPage.isInitialized());
	  
	  xboxLiveMainPage.selectMyXbox("Profile");
	  
	  SignInPage signInPage = new SignInPage(driver2);
	  assertTrue(signInPage.isInitialized());
	  
	  signInPage.enterEmail(dummyAccount.getEmail());
	  signInPage.enterPW(dummyAccount.getPW());
	  
	  SignInConfirmationComponent signInConfirmationComponent = signInPage.submit();
	  synchronized (driver2) {
		  try {driver2.wait(5000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  assertTrue(signInConfirmationComponent.isInitialized());
	  
	  assertEquals(signInConfirmationComponent.confirmationSignIn(), dummyAccount.getProfileName());
  }
  
  @Test(groups = "SignInAccount")
  public void signUpMainToXboxHome() {
	  
	  driver2.get("https://www.xbox.com/en-US/live");
	  synchronized (driver2) {
		  try {driver2.wait(4000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  XboxLiveMainPage xboxLiveMainPage = new XboxLiveMainPage(driver2);
	  
	  xboxLiveMainPage.selectMyXbox("Home");
	  
	  SignInPage signInPage = new SignInPage(driver2);
	  assertTrue(signInPage.isInitialized());
	  
	  signInPage.enterEmail(dummyAccount.getEmail());
	  signInPage.enterPW(dummyAccount.getPW());
	  
	  SignInConfirmationComponent signInConfirmationComponent = signInPage.submit();
	  synchronized (driver2) {
		  try {driver2.wait(5000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  assertTrue(signInConfirmationComponent.isInitialized());
	  
	  assertEquals(signInConfirmationComponent.confirmationSignIn(), dummyAccount.getProfileName());
  }
  
  @Test(groups = "SignInAccount")
  public void signUpMainToXboxAchievements() {
	  
	  driver2.get("https://www.xbox.com/en-US/live");
	  synchronized (driver2) {
		  try {driver2.wait(4000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  XboxLiveMainPage xboxLiveMainPage = new XboxLiveMainPage(driver2);
	  
	  xboxLiveMainPage.selectMyXbox("Achievements");
	  
	  SignInPage signInPage = new SignInPage(driver2);
	  assertTrue(signInPage.isInitialized());
	  
	  signInPage.enterEmail(dummyAccount.getEmail());
	  signInPage.enterPW(dummyAccount.getPW());
	  
	  SignInConfirmationComponent signInConfirmationComponent = signInPage.submit();
	  synchronized (driver2) {
		  try {driver2.wait(5000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  assertTrue(signInConfirmationComponent.isInitialized());
	  
	  assertEquals(signInConfirmationComponent.confirmationSignIn(), dummyAccount.getProfileName());
  }
  
  @Test(groups = "SignInAccount")
  public void signUpMainToXboxFriends() {
	  
	  driver2.get("https://www.xbox.com/en-US/live");
	  synchronized (driver2) {
		  try {driver2.wait(4000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  XboxLiveMainPage xboxLiveMainPage = new XboxLiveMainPage(driver2);
	  
	  xboxLiveMainPage.selectMyXbox("Friends");
	  
	  SignInPage signInPage = new SignInPage(driver2);
	  assertTrue(signInPage.isInitialized());
	  
	  signInPage.enterEmail(dummyAccount.getEmail());
	  signInPage.enterPW(dummyAccount.getPW());
	  
	  SignInConfirmationComponent signInConfirmationComponent = signInPage.submit();
	  synchronized (driver2) {
		  try {driver2.wait(5000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  assertTrue(signInConfirmationComponent.isInitialized());
	  
	  assertEquals(signInConfirmationComponent.confirmationSignIn(), dummyAccount.getProfileName());
  }
  
  @Test(groups = "SignInAccount")
  public void signUpMainToXboxMessages() {
	  
	  driver2.get("https://www.xbox.com/en-US/live");
	  synchronized (driver2) {
		  try {driver2.wait(2000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  XboxLiveMainPage xboxLiveMainPage = new XboxLiveMainPage(driver2);
	  
	  xboxLiveMainPage.selectMyXbox("Messages");
	  
	  SignInPage signInPage = new SignInPage(driver2);
	  assertTrue(signInPage.isInitialized());
	  
	  signInPage.enterEmail(dummyAccount.getEmail());
	  signInPage.enterPW(dummyAccount.getPW());
	  
	  SignInConfirmationComponent signInConfirmationComponent = signInPage.submit();
	  synchronized (driver2) {
		  try {driver2.wait(5000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  assertTrue(signInConfirmationComponent.isInitialized());
	  
	  assertEquals(signInConfirmationComponent.confirmationSignIn(), dummyAccount.getProfileName());
  }
  
  @Test(groups = "SignInAccount")
  public void signUpMainToXboxMyGames() {
	  
	  driver2.get("https://www.xbox.com/en-US/live");
	  synchronized (driver2) {
		  try {driver2.wait(2000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  XboxLiveMainPage xboxLiveMainPage = new XboxLiveMainPage(driver2);
	  
	  xboxLiveMainPage.selectMyXbox("My games");
	  
	  SignInPage signInPage = new SignInPage(driver2);
	  assertTrue(signInPage.isInitialized());
	  
	  signInPage.enterEmail(dummyAccount.getEmail());
	  signInPage.enterPW(dummyAccount.getPW());
	  
	  SignInConfirmationComponent signInConfirmationComponent = signInPage.submit();
	  synchronized (driver2) {
		  try {driver2.wait(5000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  assertTrue(signInConfirmationComponent.isInitialized());
	  
	  assertEquals(signInConfirmationComponent.confirmationSignIn(), dummyAccount.getProfileName());
  }
  
  @Test(groups = "SignInAccount")
  public void signUpMainToXboxTrendOnLive() {
	  
	  driver2.get("https://www.xbox.com/en-US/live");
	  synchronized (driver2) {
		  try {driver2.wait(2000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  XboxLiveMainPage xboxLiveMainPage = new XboxLiveMainPage(driver2);
	  
	  xboxLiveMainPage.selectMyXbox("Trending on Xbox Live");
	  
	  SignInPage signInPage = new SignInPage(driver2);
	  assertTrue(signInPage.isInitialized());
	  
	  signInPage.enterEmail(dummyAccount.getEmail());
	  signInPage.enterPW(dummyAccount.getPW());
	  
	  SignInConfirmationComponent signInConfirmationComponent = signInPage.submit();
	  //A lot of Flash Content on the trends side
	  synchronized (driver2) {
		  try {driver2.wait(80000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  assertTrue(signInConfirmationComponent.isInitialized());
	  
	  assertEquals(signInConfirmationComponent.confirmationSignIn(), dummyAccount.getProfileName());
  }
  
  
  @AfterMethod(groups = {"SignInAccount"})
  public void signOutAccount() throws InterruptedException {
	  SignInConfirmationComponent signMeOut = new SignInConfirmationComponent(driver2);
	  driver2.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  signMeOut.signOut();
  }
  
  @AfterClass(groups = {"SignInAccount"})
  public void tearDownSignInBrowser () {
	  driver2.close();
  }
  	
  	
  
}
