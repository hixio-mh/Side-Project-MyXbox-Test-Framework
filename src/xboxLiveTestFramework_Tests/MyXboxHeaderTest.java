package xboxLiveTestFramework_Tests;

import static org.testng.Assert.*;

import org.openqa.selenium.Dimension;
import org.testng.annotations.*;

import xboxLivePageObjects_Lib.*;


public class MyXboxHeaderTest extends BaseTest {
	
	@AfterMethod (groups="header")
	public void beforeMethod() {
		driver.navigate().to("https://account.xbox.com/en-us/social?xr=socialtwistnav");
		synchronized (driver) {
			try {driver.wait(2000);} 
			catch (InterruptedException r) { r.printStackTrace();}
		}
	}
	
	@Test(groups="header")
	public void startFromHomePage() {
		MyXboxHeaderRegionComponent header = new MyXboxHeaderRegionComponent(driver);
		
		assertTrue(header.microsoftHeaderRegion.isDisplayed());
		
		assertTrue(header.xboxLogo.isDisplayed());
		assertTrue(header.home.isDisplayed());
		assertTrue(header.profile.isDisplayed());
		assertTrue(header.achievements.isDisplayed());
		assertTrue(header.friends.isDisplayed());
		assertTrue(header.messages.isDisplayed());
		assertTrue(header.clubs.isDisplayed());
		assertTrue(header.trendingOnXboxLive.isDisplayed());
		  
		header = header.goTo("home");
		
		assertEquals(driver.getCurrentUrl(), "https://account.xbox.com/en-us/social?xr=socialtwistnav");
		
		header = header.goTo("profile");
		
		assertEquals(driver.getCurrentUrl(), "https://account.xbox.com/en-us/Profile?xr=socialtwistnav");
		
		header = header.goTo("achievements");
		
		assertEquals(driver.getCurrentUrl(), "https://account.xbox.com/en-us/Profile?activetab=main%3Amaintab2");
		
		header = header.goTo("friends");
		
		assertEquals(driver.getCurrentUrl(), "https://account.xbox.com/en-us/Friends?xr=socialtwistnav");
		
		header = header.goTo("messages");
		
		assertEquals(driver.getCurrentUrl(), "https://account.xbox.com/en-us/SkypeMessages?xr=socialtwistnav");
		
		header = header.goTo("my games");
		
		assertEquals(driver.getCurrentUrl(), "https://account.xbox.com/en-us/MyGames?xr=socialtwistnav");
		
		header = header.goTo("clubs");
		
		assertEquals(driver.getCurrentUrl(), "https://account.xbox.com/en-us/clubs?xr=socialtwistnav");
		
		header = header.goTo("trending on xbox live");
		
		assertEquals(driver.getCurrentUrl(), "https://account.xbox.com/en-us/trendingtopics?xr=socialtwistnav");
		
		header = header.goTo("logo");
		
		assertEquals(driver.getCurrentUrl(), "https://www.xbox.com/en-US/?xr=mebarnav");
		
		assertTrue(header.xboxLogo.isDisplayed());
		
		XboxLiveMainPage xboxLiveMainPage = new XboxLiveMainPage(driver);
		
		xboxLiveMainPage.selectMyXbox("Home");
		
		assertEquals(driver.getCurrentUrl(), "https://account.xbox.com/en-US/social?xr=shellnav");
		
	}
	
	@Test(groups="header")
	public void headerArrorwTab() {
		MyXboxHeaderRegionComponent header = new MyXboxHeaderRegionComponent(driver);
		Dimension dimenson = new Dimension(1000, 800);
		
		driver.manage().window().setSize(dimenson);
		synchronized (driver) {
			try {driver.wait(5000);} 
			catch (InterruptedException r) { r.printStackTrace();}
		}
		header.scrollRight();
		
		header.scrollLeft();
		
		driver.manage().window().maximize();
	}
  

}
