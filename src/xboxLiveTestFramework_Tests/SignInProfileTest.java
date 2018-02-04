package xboxLiveTestFramework_Tests;

import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.*;

import xboxLivePageObjects_Lib.*;

public class SignInProfileTest extends BaseTest {
	Driver driver2;
	@BeforeClass(groups = {"SignInAccount", "signincontent"})
	public void openSeperateBrowser () throws InterruptedException {
		driver2 = new Driver(getBrowserName());
		driver2.get("https://www.xbox.com/en-US/live");
		driver2.manage().window().maximize();
		
	}
	
	@Test(groups= {"SignInAccount", "signincontent"} )
	public void mainPageContentCheck() {
		XboxLiveMainPage mainPageCheck = new XboxLiveMainPage(driver2);
		
		mainPageCheck.showMyXboxMenu();
		
		synchronized (driver2) {
			  try {driver2.wait(2000);} 
			  catch (InterruptedException e) { e.printStackTrace();}
		}
		
		assertTrue(mainPageCheck.contentExists("Home"));
		
		assertTrue(mainPageCheck.contentExists("Profile"));
		
		assertTrue(mainPageCheck.contentExists("Achievements"));
		
		assertTrue(mainPageCheck.contentExists("Friends"));
		
		assertTrue(mainPageCheck.contentExists("Messages"));
		
		assertTrue(mainPageCheck.contentExists("My games"));
		
		assertTrue(mainPageCheck.contentExists("Clubs"));
		
		assertTrue(mainPageCheck.contentExists("Trending on Xbox Live"));
	}
	
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
  
  @Test(groups= {"SignInAccount", "signincontent"})
  public void contentSignInPage() {
	  driver2.get("https://www.xbox.com/en-US/live");
	  synchronized (driver2) {
		  try {driver2.wait(4000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  XboxLiveMainPage xboxLiveMainPage = new XboxLiveMainPage(driver2);
	  
	  xboxLiveMainPage.selectMyXbox("Home");
	  
	  SignInPage signInPage = new SignInPage(driver2);
	  
	  assertTrue(signInPage.isInitialized());
	  
	  assertTrue(signInPage.signInBackGround.isDisplayed());
	  
	  assertTrue(signInPage.signInBackGroundLogo.isDisplayed());
	  
	  assertTrue(signInPage.signInBlock.isDisplayed());
	  
	  assertTrue(signInPage.microsoftLogo.isDisplayed());
	  
	  assertEquals(signInPage.getSignInHeader(), "Sign in");
	  
	  assertTrue(signInPage.emailName.isDisplayed());
	  
	  assertFalse(signInPage.getPWCell().isDisplayed());
	  
	  assertEquals(signInPage.emailName.getAttribute("placeholder"), "Email, phone, or Skype");
	  
	  assertTrue(signInPage.submitButton.isDisplayed());
	  
	  assertEquals(signInPage.getSubmitButtonText(),"Next");
	  
	  assertTrue(signInPage.signUpLink.isDisplayed());
	  
	  assertEquals(signInPage.signUpLink.getText(), "Create one!");
	  
	  signInPage.enterEmail(dummyAccount.getEmail());
	  
	  assertTrue(signInPage.signInBackGround.isDisplayed());
	  
	  assertTrue(signInPage.signInBackGroundLogo.isDisplayed());
	  
	  assertTrue(signInPage.signInBlock.isDisplayed());
	  
	  assertTrue(signInPage.microsoftLogo.isDisplayed());
	  
	  assertEquals(signInPage.getEmailBannerText(), dummyAccount.getEmail());
	  
	  assertEquals(signInPage.getEmailBannerTitle(), dummyAccount.getEmail());
	  
	  assertEquals(signInPage.getSignInHeader(), "Enter password");
	  
	  assertEquals(signInPage.getPWCell().getAttribute("placeholder"), "Password");
	  
	  assertTrue(signInPage.submitButton.isDisplayed());
	  
	  assertEquals(signInPage.getSubmitButtonText(),"Sign in");
	  
	  assertTrue(signInPage.signInBackButton.isDisplayed());
	  
	  assertTrue(signInPage.keepMeSignedInLabel.isDisplayed());
	  
	  assertEquals(signInPage.keepMeSignedInLabel(),"Keep me signed in");
	  
	  assertTrue(signInPage.keepMeSignedInCheckBox.isDisplayed());
	  
	  assertFalse(signInPage.keepMeSignedInCheckBox.isSelected());
	  
	  signInPage.keepMeSignedInChecked();
	  
	  assertTrue(signInPage.keepMeSignedInCheckBox.isSelected());
	  
	  signInPage.keepMeSignedInChecked();
	  
	  assertFalse(signInPage.keepMeSignedInCheckBox.isSelected());
	  
	  assertTrue(signInPage.forgotPWLink.isDisplayed());
	  
	  signInPage.changeEmailSignIn();
	  
	  assertEquals(signInPage.getSignInHeader(), "Sign in");
	  
	  assertEquals(signInPage.getSubmitButtonText(),"Next");
	  
	  driver2.navigate().back();
	  
	  assertEquals(driver2.getCurrentUrl(), "https://www.xbox.com/en-US/live");
  }
  
  @Test(groups= {"SignInAccount", "signincontent"})
  public void signInIncorrectEmail() {
	  driver2.get("https://www.xbox.com/en-US/live");
	  synchronized (driver2) {
		  try {driver2.wait(4000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
	  XboxLiveMainPage xboxLiveMainPage = new XboxLiveMainPage(driver2);
	  
	  xboxLiveMainPage.selectMyXbox("Home");
	  
	  SignInPage signInPage = new SignInPage(driver2);
	  
	  assertTrue(signInPage.isInitialized());
	  
	  signInPage.enterEmail("@op98$%^>>>>>......<<<<<");
	  
	  assertTrue(signInPage.signInError.isDisplayed());
	  
	  assertEquals(signInPage.signInErrorMessage(), "Enter a valid email address, phone number, or Skype name.");
	  
	  signInPage.emailName.clear();
	  
	  driver2.navigate().back();
	  
	  assertEquals(driver2.getCurrentUrl(), "https://www.xbox.com/en-US/live");
	  
  }
  
  @Test(groups= {"SignInAccount", "signincontent"})
  public void signInIncorrectPW() {
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
	  
	  signInPage.enterPW("honkhonkblargblarg");
	  signInPage = signInPage.pWFail();
	  
	  assertEquals(signInPage.pWErrorMessage(), "Your account or password is incorrect. If you don't remember your password, reset it now.");
	  
	  assertEquals(signInPage.pWErrorResetLink(), "reset it now.");
	  
	  driver2.navigate().to("https://www.xbox.com/en-US/live");
	  
	  assertEquals(driver2.getCurrentUrl(), "https://www.xbox.com/en-US/live");
	  
  }
  
  
  @AfterMethod(groups = {"SignInAccount"})
  public void signOutAccount() throws InterruptedException {
	  SignInConfirmationComponent signMeOut = new SignInConfirmationComponent(driver2);
	  if (signMeOut.confirmationSignIn().equals(dummyAccount.getProfileName())
			  || signMeOut.confirmationSignIn().equals(dummyAccount.getFirstRealName())) {
		  driver2.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  signMeOut.signOut();
	  }
  }
  
  @AfterClass(groups = {"SignInAccount", "signincontent"})
  public void tearDownSignInBrowser () {
	  driver2.close();
  }
  	
  	
  
}
