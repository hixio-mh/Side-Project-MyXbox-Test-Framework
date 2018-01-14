package xboxLiveTestFramework_Tests;

import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import xboxLivePageObjects_Lib.*;

public class SignInProfileTest extends BaseTest {
	
	
  @Test(groups = {"SignInAccount", "FunctionTests"})
  public void signUpFromMainToProfile() {
	  
	  driver.get("https://www.xbox.com/en-US/live");
	  synchronized (driver) {
		  try {driver.wait(2000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  XboxLiveMainPage xboxLiveMainPage = new XboxLiveMainPage(driver);
	  assertTrue(xboxLiveMainPage.isInitialized());
	  
	  xboxLiveMainPage.selectMyXbox("Profile");
	  
	  SignInPage signInPage = new SignInPage(driver);
	  assertTrue(signInPage.isInitialized());
	  
	  signInPage.enterEmail(dummyAccount.getEmail());
	  signInPage.enterPW(dummyAccount.getPW());
	  
	  SignInConfirmationComponent signInConfirmationComponent = signInPage.submit();
	  synchronized (driver) {
		  try {driver.wait(5000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  assertTrue(signInConfirmationComponent.isInitialized());
	  
	  assertEquals(signInConfirmationComponent.confirmationSignIn(), "PopulousToast73");
  }
  
  @Test(groups = "FunctionTests")
  public void signUpMainToXboxHome() {
	  
	  driver.get("https://www.xbox.com/en-US/live");
	  synchronized (driver) {
		  try {driver.wait(4000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  XboxLiveMainPage xboxLiveMainPage = new XboxLiveMainPage(driver);
	  
	  xboxLiveMainPage.selectMyXbox("Home");
	  
	  SignInPage signInPage = new SignInPage(driver);
	  assertTrue(signInPage.isInitialized());
	  
	  signInPage.enterEmail(dummyAccount.getEmail());
	  signInPage.enterPW(dummyAccount.getPW());
	  
	  SignInConfirmationComponent signInConfirmationComponent = signInPage.submit();
	  synchronized (driver) {
		  try {driver.wait(5000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  assertTrue(signInConfirmationComponent.isInitialized());
	  
	  assertEquals(signInConfirmationComponent.confirmationSignIn(), "PopulousToast73");
  }
  
  @Test(groups = "FunctionTests")
  public void signUpMainToXboxAchievements() {
	  
	  driver.get("https://www.xbox.com/en-US/live");
	  synchronized (driver) {
		  try {driver.wait(4000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  XboxLiveMainPage xboxLiveMainPage = new XboxLiveMainPage(driver);
	  
	  xboxLiveMainPage.selectMyXbox("Achievements");
	  
	  SignInPage signInPage = new SignInPage(driver);
	  assertTrue(signInPage.isInitialized());
	  
	  signInPage.enterEmail(dummyAccount.getEmail());
	  signInPage.enterPW(dummyAccount.getPW());
	  
	  SignInConfirmationComponent signInConfirmationComponent = signInPage.submit();
	  synchronized (driver) {
		  try {driver.wait(5000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  assertTrue(signInConfirmationComponent.isInitialized());
	  
	  assertEquals(signInConfirmationComponent.confirmationSignIn(), "PopulousToast73");
  }
  
  @Test(groups = "FunctionTests")
  public void signUpMainToXboxFriends() {
	  
	  driver.get("https://www.xbox.com/en-US/live");
	  synchronized (driver) {
		  try {driver.wait(4000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  XboxLiveMainPage xboxLiveMainPage = new XboxLiveMainPage(driver);
	  
	  xboxLiveMainPage.selectMyXbox("Friends");
	  
	  SignInPage signInPage = new SignInPage(driver);
	  assertTrue(signInPage.isInitialized());
	  
	  signInPage.enterEmail(dummyAccount.getEmail());
	  signInPage.enterPW(dummyAccount.getPW());
	  
	  SignInConfirmationComponent signInConfirmationComponent = signInPage.submit();
	  synchronized (driver) {
		  try {driver.wait(5000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  assertTrue(signInConfirmationComponent.isInitialized());
	  
	  assertEquals(signInConfirmationComponent.confirmationSignIn(), "PopulousToast73");
  }
  
  @Test(groups = "FunctionTests")
  public void signUpMainToXboxMessages() {
	  
	  driver.get("https://www.xbox.com/en-US/live");
	  synchronized (driver) {
		  try {driver.wait(2000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  XboxLiveMainPage xboxLiveMainPage = new XboxLiveMainPage(driver);
	  
	  xboxLiveMainPage.selectMyXbox("Messages");
	  
	  SignInPage signInPage = new SignInPage(driver);
	  assertTrue(signInPage.isInitialized());
	  
	  signInPage.enterEmail(dummyAccount.getEmail());
	  signInPage.enterPW(dummyAccount.getPW());
	  
	  SignInConfirmationComponent signInConfirmationComponent = signInPage.submit();
	  synchronized (driver) {
		  try {driver.wait(5000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  assertTrue(signInConfirmationComponent.isInitialized());
	  
	  assertEquals(signInConfirmationComponent.confirmationSignIn(), "PopulousToast73");
  }
  
  @Test(groups = "FunctionTests")
  public void signUpMainToXboxMyGames() {
	  
	  driver.get("https://www.xbox.com/en-US/live");
	  synchronized (driver) {
		  try {driver.wait(2000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  XboxLiveMainPage xboxLiveMainPage = new XboxLiveMainPage(driver);
	  
	  xboxLiveMainPage.selectMyXbox("My games");
	  
	  SignInPage signInPage = new SignInPage(driver);
	  assertTrue(signInPage.isInitialized());
	  
	  signInPage.enterEmail(dummyAccount.getEmail());
	  signInPage.enterPW(dummyAccount.getPW());
	  
	  SignInConfirmationComponent signInConfirmationComponent = signInPage.submit();
	  synchronized (driver) {
		  try {driver.wait(5000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  assertTrue(signInConfirmationComponent.isInitialized());
	  
	  assertEquals(signInConfirmationComponent.confirmationSignIn(), "PopulousToast73");
  }
  
  @Test(groups = "FunctionTests")
  public void signUpMainToXboxTrendOnLive() {
	  
	  driver.get("https://www.xbox.com/en-US/live");
	  synchronized (driver) {
		  try {driver.wait(2000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  XboxLiveMainPage xboxLiveMainPage = new XboxLiveMainPage(driver);
	  
	  xboxLiveMainPage.selectMyXbox("Trending on Xbox Live");
	  
	  SignInPage signInPage = new SignInPage(driver);
	  assertTrue(signInPage.isInitialized());
	  
	  signInPage.enterEmail(dummyAccount.getEmail());
	  signInPage.enterPW(dummyAccount.getPW());
	  
	  SignInConfirmationComponent signInConfirmationComponent = signInPage.submit();
	  //A lot of Flash Content on the trends side
	  synchronized (driver) {
		  try {driver.wait(80000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  assertTrue(signInConfirmationComponent.isInitialized());
	  
	  assertEquals(signInConfirmationComponent.confirmationSignIn(), "PopulousToast73");
  }
  
  
  @AfterMethod(groups = {"FunctionTests"})
  public void signOutAccount() throws InterruptedException {
	  SignInConfirmationComponent signMeOut = new SignInConfirmationComponent(driver);
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  signMeOut.signOut();
  }
  	
  	
  
}
