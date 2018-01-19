package xboxLiveTestFramework_Tests;

import org.testng.annotations.Test;

import xboxLivePageObjects_Lib.*;


import static org.testng.Assert.*;

import org.testng.annotations.*;

public class MyXboxHomePageTest extends BaseTest {
	@AfterMethod(groups= {"homepage"})
	public void startPoint() {
	  driver.get("https://account.xbox.com/en-us/social?xr=socialtwistnav");
	}
	
	  @Test(groups= {"homepage"})
	  public void postNewMessage() {
		  
		  MyXboxHomePage myXboxHomePage = new MyXboxHomePage(driver);
		  
		  myXboxHomePage.newPost(myXboxHomePage.randomGibberish);
		  
		  synchronized (driver) {
			  try {driver.wait(5000);} 
			  catch (InterruptedException e) { e.printStackTrace();}
		  }
		  
		  assertTrue(myXboxHomePage.findNewPost(myXboxHomePage.randomGibberish));
	  }
  
	  


}
