package xboxLiveTestFramework_Tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import xboxLivePageObjects_Lib.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;

import DummyAccounts_Lib.*;

@Listeners({TestMethodListener.class})
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
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  if (!signInConfirmationComponent.confirmationSignIn().equals(dummyAccount.getProfileName())) {
		  driver.navigate().refresh();
		  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  }
	  assertEquals(signInConfirmationComponent.confirmationSignIn(), dummyAccount.getProfileName());
	  
	  synchronized (driver) {
		  try {driver.wait(4000);} 
		  catch (InterruptedException e) { e.printStackTrace();}
	  }
  }
  
  @BeforeMethod(alwaysRun = true)
  public void beforeTest()  {
	  SignInConfirmationComponent signInConfirmationComponent = new SignInConfirmationComponent(driver);
	  System.out.println("This is the before step: " );
	  boolean notProperlyDisplayed = true;
	  while (notProperlyDisplayed == true) {
		  if (!(signInConfirmationComponent.confirmationSignIn().equals(dummyAccount.getProfileName()))) {
			  System.out.println("The webpage loaded incorrectly, refreshing: ");
			  driver.navigate().refresh();
			  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		  }
		  if (signInConfirmationComponent.confirmationSignIn().equals(dummyAccount.getProfileName())){
			  notProperlyDisplayed = false;
		  }
	  }
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
