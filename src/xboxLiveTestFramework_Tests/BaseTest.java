package xboxLiveTestFramework_Tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import xboxLivePageObjects_Lib.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;

import DummyAccounts_Lib.*;

public class BaseTest {
	Driver driver;
	DummyTestAccount dummyAccount = new DummyTestAccount();
	String browserName = getParamater("browser");

  @BeforeClass(alwaysRun = true)
  public void setUp() {
	  
	  driver = new Driver(browserName);
	  driver.manage().window().maximize();
  }
  
  private String getParamater(String name) {
	  String value = System.getProperty(name);
	  if(value == null) {
		  throw new RuntimeException(name + "missing parameter");
	  }
	  return value;
  }
  
  public String getBrowserName() {
	  return browserName;
  }
  
  @BeforeClass(alwaysRun = true)
  public void setUpAccount() {
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
	  
	  assertEquals(signInConfirmationComponent.confirmationSignIn(), dummyAccount.getProfileName());
	  
	  synchronized (driver) {
		  try {driver.wait(4000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
  }
  
  @BeforeTest(alwaysRun = true)
  public void beforeTest()  {
	  System.out.println("This is the before step:");
	 
  }
  
  
  @AfterClass(alwaysRun = true)
  public void cleanUp() {
	  driver.manage().deleteAllCookies();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() {
	  driver.close();
  }

}
