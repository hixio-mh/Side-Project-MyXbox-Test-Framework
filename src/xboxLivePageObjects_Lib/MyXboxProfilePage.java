package xboxLivePageObjects_Lib;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyXboxProfilePage extends PageObject {
	
	//Maybe split off the activity feed portion as it's own separate Component object if enough className, ids, or names are identical across the 
	//the webpages where these features are applied
	
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
	public WebElement friendsAndFollowersStats;
	
	public WebElement profileTabSelectorSection = entireProfileWebPage.findElement(By.className("m-heading-4"));
	public List <WebElement> profileTabSelectionOptions = profileTabSelectorSection.findElements(By.tagName("a"));
	
	@FindBy(id="mainTab3")
	public WebElement activityFeedSection;
	
	@FindBy(id="mainTab2")
	public WebElement achievementsSection;
	
	@FindBy(id="mainTab4")
	public WebElement capturesSection;
	
	public MyXboxProfilePage (WebDriver driver) {
		super(driver);
		
	}
	
	//Profile Card Section
	public void customizeProfileInfo() {
		customizeProfile.click();
	}
	
	public void swtichTabs(String tab) {
		for (WebElement e : profileTabSelectionOptions) {
			if (e.getText().equals(tab)) {
				if (!e.getAttribute("aria-selected").equals("true")) {
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
	
	//Activity Feed Section (Maybe set a boolean that specific elements are displayed when selected for all three.
	
	//Achievement tab section
	
	
	//Capture feed tab
	
	
	
	
	
	
	//

}
