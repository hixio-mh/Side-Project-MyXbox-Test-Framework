package xboxLivePageObjects_Lib;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyXboxProfilePage extends PageObject {
	
	
	
	@FindBy(id="primaryArea")
	public WebElement entireProfileWebPage;
	
	@FindBy(id="xboxsummary")
	public WebElement xboxProfileCard;
	
	@FindBy(id="left-side")
	public WebElement xboxProfileImage;
	
	@FindBy(id="right-side")
	public WebElement xboxProfileCardInfo;
	
	public String profileGamerscore = xboxProfileCardInfo.findElement(By.cssSelector(".c-action-trigger.c-glyph.glyph-gamerscore"))
			.getAttribute("title");
	
	public String profileName = xboxProfileCardInfo.findElement(By.tagName("h3")).getText();
	public String profileSecondName = xboxProfileCardInfo.findElement(By.tagName("p")).getText();
	
	@FindBy(id="customize")
	public WebElement customizeProfile;
	
	@FindBy(id="privacy")
	public WebElement privacySettings;
	
	@FindBy(id="item-values")
	public WebElement friendsFollowersLocation;
	
	public List<WebElement> detailCardInfo = friendsFollowersLocation.findElements(By.className("item-value"));
	
	public String bioInfo = "";
	
	public String locationInfo = "";
	
	public WebElement profileTabSelectorSection = entireProfileWebPage.findElement(By.className("m-heading-4"));
	public List <WebElement> profileTabSelectionOptions = profileTabSelectorSection.findElements(By.tagName("a"));
	
	@FindBy(id="mainTab3")
	public WebElement activityFeedSection;
	
	@FindBy(id="mainTab2")
	public WebElement achievementsSection;
	
	@FindBy(id="mainTab4")
	public WebElement capturesSection;
	
	public MyXboxProfilePage (Driver driver) {
		super(driver);
		
	}
	
	public void customizeProfileInfo() {
		customizeProfile.click();
		synchronized (driver) {
			try {driver.wait(4000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
	}
	
	public void swtichTabs(String tab) {
		for (WebElement e : profileTabSelectionOptions) {
			if (e.getText().equals(tab)) {
				if (e.getAttribute("aria-selected") == null || !e.getAttribute("aria-selected").equals("true")) {
					e.click();
					break;
				}
				else {
					System.out.println("This requested tab  has already been selected");
					break;
				}
			}
		}
	}
	
	public void getLocationInfo() {
		for (WebElement e : detailCardInfo) {
			if(e.findElement(By.className("item-value-title")).getText().equals("Location")){
				locationInfo = e.findElement(By.className("item-value-data")).getText();
			}
		}
	}
	
	public void getBioInfo() {
		bioInfo = xboxProfileCardInfo.findElement(By.className("bio")).getText();
	}
	
}
