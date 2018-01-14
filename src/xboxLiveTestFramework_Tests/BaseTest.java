package xboxLiveTestFramework_Tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import xboxLivePageObjects_Lib.*;
import org.testng.annotations.AfterClass;

import DummyAccounts_Lib.*;

public class BaseTest {
	Driver driver;
	DummyTestAccount dummyAccount = new DummyTestAccount();
  

  @BeforeClass(alwaysRun = true)
  public void setUp() {
	  String browserName = getParamater("browser");
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
  
  @BeforeTest(alwaysRun = true)
  public void beforeTest() throws InterruptedException {
	  System.out.println("This is before the test step:");
	 
  }
  
  @AfterMethod(alwaysRun = true)
  public void cleanUp() {
	  driver.manage().deleteAllCookies();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() {
	  driver.close();
  }

}
