package xboxLiveTestFramework_Tests;

import static org.testng.Assert.*;

import org.testng.annotations.*;

import xboxLivePageObjects_Lib.*;


public class MyXboxCustomizeProfileTest extends BaseTest{
  
 
  @BeforeClass(groups= {"customizeprofile"})
  public void beforeClass() {
	  MyXboxHeaderRegionComponent header = new MyXboxHeaderRegionComponent(driver);
		
	  header.goTo("profile");
		
	  assertTrue(driver.getCurrentUrl().contains("https://account.xbox.com/en-us/Profile?xr=socialtwistnav"));
	  
  }
  
  @AfterMethod(groups= {"customizeprofile"})
  public void resetDefault() {
	  driver.navigate().to("https://account.xbox.com/en-us/Profile?xr=socialtwistnav");
  }
  
  @Test(groups= {"customizeprofile"})
	public void goToCustomizePage() {
		MyXboxProfilePage xboxProfile = new MyXboxProfilePage(driver);
		
		xboxProfile.getLocationInfo();
		xboxProfile.getBioInfo();
		
		xboxProfile.customizeProfileInfo();
		
		
		assertTrue(driver.getCurrentUrl().equals("https://account.xbox.com/en-US/CustomizeProfile"));
		
		CustomizeProfilePage customize = new CustomizeProfilePage(driver);
		
		assertTrue(customize.entireCustomizeBodyPage.isDisplayed());
		
		assertTrue(customize.avatarImageSection.isDisplayed());
		
		assertTrue(customize.profileImageAndNameSection.isDisplayed());
		
		assertTrue(customize.changeGamerPic.isDisplayed());
		
		assertTrue(customize.customizeProfileName.isDisplayed());
		
		assertTrue(customize.profileName.isDisplayed());
		
		assertEquals(customize.profileName.getText(), dummyAccount.getProfileName());
		
		assertTrue(customize.changeProfileName.isDisplayed());
		
		assertTrue(customize.profileBioSection.isDisplayed());
		
		assertTrue(customize.editBioButton.isDisplayed());
		
		customize.editBioButton.click();
		
		assertTrue(customize.bioTextBox.isDisplayed());
		
		assertTrue(customize.saveBioInfo.isDisplayed());
		assertTrue(customize.cancelBioInfo.isDisplayed());
		
		customize.cancelBioInfo.click();
		
		assertTrue(customize.profileLocationSection.isDisplayed());
		
		assertTrue(customize.editLocationButton.isDisplayed());
		
		customize.editLocationButton.click();
		
		assertTrue(customize.locationTextBox.isDisplayed());
		
		assertTrue(customize.saveLocationInfo.isDisplayed());
		assertTrue(customize.cancelLocationInfo.isDisplayed());
		
		customize.cancelLocationInfo.click();
		
		assertEquals(xboxProfile.locationInfo, customize.currentLocationText);
		
		assertEquals(xboxProfile.bioInfo, customize.currentBioText);
		
		MyXboxHeaderRegionComponent header = new MyXboxHeaderRegionComponent(driver);
		
		header.goTo("profile");
			
		assertTrue(driver.getCurrentUrl().contains("https://account.xbox.com/en-us/Profile?xr=socialtwistnav"));
	}
  
  	@Test(groups= {"customizeprofile"})
  	public void clickAvatarLink() {
	  	MyXboxProfilePage xboxProfile = new MyXboxProfilePage(driver);
		
		xboxProfile.customizeProfileInfo();
		
		assertTrue(driver.getCurrentUrl().equals("https://account.xbox.com/en-US/CustomizeProfile"));
		
		CustomizeProfilePage customize = new CustomizeProfilePage(driver);
		
		assertTrue(customize.entireCustomizeBodyPage.isDisplayed());
		
		customize.customizeAvatar();
		
		assertEquals(driver.getCurrentUrl(), "https://live.xbox.com/en-US/AvatarEditor");
		
  	}
  	
  	@Test(groups= {"customizeprofile"})
  	public void clickEditGamerTagLink() {
  		MyXboxProfilePage xboxProfile = new MyXboxProfilePage(driver);
		
		xboxProfile.customizeProfileInfo();
		
		assertTrue(driver.getCurrentUrl().equals("https://account.xbox.com/en-US/CustomizeProfile"));
		
		CustomizeProfilePage customize = new CustomizeProfilePage(driver);
		
		assertTrue(customize.entireCustomizeBodyPage.isDisplayed());
		
		customize.changeGamerTag();
		
		assertEquals(driver.getCurrentUrl(), "https://account.xbox.com/en-US/changegamertag");
		
  	}
  	
  	@Test(groups= {"customizeprofile"})
  	public void editBioAndLocationInfo() {
  		MyXboxProfilePage xboxProfile = new MyXboxProfilePage(driver);
		
		xboxProfile.getLocationInfo();
		xboxProfile.getBioInfo();
		
		xboxProfile.customizeProfileInfo();
		
		assertTrue(driver.getCurrentUrl().equals("https://account.xbox.com/en-US/CustomizeProfile"));
		
		CustomizeProfilePage customize = new CustomizeProfilePage(driver);
		
		assertTrue(customize.entireCustomizeBodyPage.isDisplayed());
		
		customize.enterTooMuchInfoLocation();
		customize.recheckCurrentLocationText();
		
		assertFalse(CustomizeProfilePage.recentTooMuchInfoText.equals(customize.currentLocationText));
		
		customize.enterTooMuchInfoBio();
		customize.recheckCurrentBioText();
		
		assertFalse(CustomizeProfilePage.recentTooMuchInfoText.equals(customize.currentBioText));
		
		customize.enterButCancelLocation();
		customize.recheckCurrentLocationText();
		
		assertFalse(CustomizeProfilePage.recentLocationText.equals(customize.currentLocationText));
		
		customize.enterButCancelBioInfo();
		customize.recheckCurrentBioText();
		
		assertFalse(CustomizeProfilePage.recentBioText.equals(customize.currentBioText));
		
		customize.enterBioInfo();
		customize.recheckCurrentBioText();
		
		assertTrue(CustomizeProfilePage.recentBioText.equals(customize.currentBioText));
		
		customize.enterLocationInfo();
		customize.recheckCurrentLocationText();
		
		assertTrue(CustomizeProfilePage.recentLocationText.equals(customize.currentLocationText));
		
		synchronized (driver) {
			try {driver.wait(9000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
		
		MyXboxHeaderRegionComponent header = new MyXboxHeaderRegionComponent(driver);
		
		header.goTo("profile");
		
		assertEquals(driver.getCurrentUrl(), "https://account.xbox.com/en-us/Profile?xr=socialtwistnav");
		
		xboxProfile = new MyXboxProfilePage(driver);
		
		xboxProfile.getLocationInfo();
		xboxProfile.getBioInfo();
		
		assertEquals(customize.currentLocationText, xboxProfile.locationInfo);
		
		assertEquals(customize.currentBioText, xboxProfile.bioInfo);
  	}
  	
  	@Test(groups= {"customizeprofile"})
  	public void updateProfilePic(){
  		MyXboxProfilePage xboxProfile = new MyXboxProfilePage(driver);
		
		xboxProfile.customizeProfileInfo();
		
		assertTrue(driver.getCurrentUrl().equals("https://account.xbox.com/en-US/CustomizeProfile"));
		
		CustomizeProfilePage customize = new CustomizeProfilePage(driver);
		
		assertTrue(customize.entireCustomizeBodyPage.isDisplayed());
		
		customize.changeProfilePic();
		
		synchronized (driver) {
			try {driver.wait(5000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
		customize.recheckProfilePic();
		
		assertFalse(CustomizeProfilePage.oldProfilePicSRC.equals(customize.currentProfilePicSRC));
  	}
  

}
