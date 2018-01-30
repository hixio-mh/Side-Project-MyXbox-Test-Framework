package xboxLivePageObjects_Lib;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomizeProfilePage extends PageObject {
	
	@FindBy(className="bodyContent")
	public WebElement entireCustomizeBodyPage;
	
	@FindBy(className="customizeprofileAvatar")
	public WebElement avatarImageSection;
	@FindBy(id="changeAvatarButton")
	public WebElement changeAvatarButton;
	
	@FindBy(className="leftTopSidePart")
	public WebElement profileImageAndNameSection;
	
	public WebElement currentProfilePic =  profileImageAndNameSection.findElement(By.className("currentGamerPic"));
	public String currentProfilePicSRC = currentProfilePic.getAttribute("src");
	@FindBy(id="changeGamerPicButton")
	public WebElement changeGamerPic;
	
	
	@FindBy(id="customizeProfileGamerTag")
	public WebElement customizeProfileName;
	@FindBy(id="gamerTagText")
	public WebElement profileName;
	@FindBy(id="changeGamerTagButton")
	public WebElement changeProfileName;
	
	public WebElement profileEditPicSection = entireCustomizeBodyPage.findElement(By.cssSelector(".modal.gamerPicChooserModal"));
	public WebElement closeProfileEditPicSection = profileEditPicSection.findElement(By.className("closeModalIcon"));
	public List<WebElement> profilePicCatalog;
	public WebElement saveNewProfilePic = profileEditPicSection.findElement(By.className("saveGamerPic"));
	
	@FindBy(id="customizeprofileBio")
	public WebElement profileBioSection;
	@FindBy(id="editBioButton")
	public WebElement editBioButton;
	public WebElement bioTextBox = profileBioSection.findElement(By.tagName("textarea"));
	@FindBy(id="saveBioButton")
	public WebElement saveBioInfo;
	@FindBy(id="cancelBioButton")
	public WebElement cancelBioInfo;
	public String currentBioText = profileBioSection.findElement(By.cssSelector(".bioInfoArea.customizeProfileTextStyle")).getText();
	public static String recentBioText = "";
	
	
	@FindBy(id="customizeprofileLocation")
	public WebElement profileLocationSection;
	@FindBy(id="changeProfileLocationButton")
	public WebElement editLocationButton;
	@FindBy(name="locationTextBox")
	public WebElement locationTextBox;
	@FindBy(id="saveProfileLocationButton")
	public WebElement saveLocationInfo;
	@FindBy(id="cancelProfileLocationButton")
	public WebElement cancelLocationInfo;
	public String currentLocationText = locationTextBox.getText();
	public static String recentLocationText = "";
	
	public static String recentTooMuchInfoText = "";
	
	public String newLocationInfo;
	public String newBioInfo;
	public String newTooMuchInfo;
	
	public CustomizeProfilePage (WebDriver driver) {
		super(driver);
		newLocationInfo = randomLocationInfoSetup();
		newBioInfo = randomBioInfoSetup();
		newTooMuchInfo = randomTooMuchInfoSetup();
		
	}
	
	public String randomLocationInfoSetup() {
		String catalog = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$^&()abcdefghijklmnopqrstuvwxyz";
		String newText = "";
		Random rand = new Random();
		while (newText.length() < 40) {
			int index = rand.nextInt(catalog.length());
			newText += catalog.charAt(index);
		}
		return newText;
	}
	
	public String randomBioInfoSetup() {
		String catalog = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$^&()abcdefghijklmnopqrstuvwxyz";
		String newText = "";
		Random rand = new Random();
		while (newText.length() < 50) {
			int index = rand.nextInt(catalog.length());
			newText += catalog.charAt(index);
		}
		return newText;
	}
	
	public String randomTooMuchInfoSetup() {
		String catalog = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$^&()abcdefghijklmnopqrstuvwxyz";
		String newText = "";
		Random rand = new Random();
		while (newText.length() < 501) {
			int index = rand.nextInt(catalog.length());
			newText += catalog.charAt(index);
		}
		return newText;
	}
	
	public void customizeAvatar() {
		changeAvatarButton.click();
	}
	
	public String getProfileName() {
		return profileName.getText();
	}
	
	public void changeGamerTag() {
		changeProfileName.click();
	}
	
	//Figure how retain distinct src pics.
	public void changeProfilePic() {
		changeGamerPic.click();
		declareProfilePicCatalog();
		int counter = 0;
		Random rand = new Random();
		int randNum = rand.nextInt(profilePicCatalog.size());
		for (WebElement e : profilePicCatalog) {
			if (randNum == counter) {
				e.click();
				saveNewProfilePic.click();
				break;
			}
			counter++;
		}
		
	}
	
	public void declareProfilePicCatalog() {
		profilePicCatalog = profileEditPicSection.findElements(By.className("gamerPicItem"));
		
	}
	
	//Put in Bio and location edit methods.
	
	public void enterLocationInfo() {
		editLocationButton.click();
		locationTextBox.clear();
		recentLocationText = newLocationInfo;
		locationTextBox.sendKeys(newLocationInfo);
		saveLocationInfo.click();
	}
	
	public void enterButCancelLocation() {
		editLocationButton.click();
		locationTextBox.clear();
		recentLocationText = newLocationInfo;
		locationTextBox.sendKeys(newLocationInfo);
		cancelLocationInfo.click();
	}
	
	public void enterBioInfo() {
		editBioButton.click();
		bioTextBox.clear();
		recentBioText = newBioInfo;
		bioTextBox.sendKeys(newBioInfo);
		saveBioInfo.click();
	}
	
	public void enterButCancelBioInfo() {
		editBioButton.click();
		bioTextBox.clear();
		recentBioText = newBioInfo;
		bioTextBox.sendKeys(newBioInfo);
		cancelBioInfo.click();
	}
	
	public void enterTooMuchInfoLocation() {
		editLocationButton.click();
		locationTextBox.clear();
		recentTooMuchInfoText = newTooMuchInfo;
		locationTextBox.sendKeys(newTooMuchInfo);
		saveLocationInfo.click();
	}
	
	public void enterTooMuchInfoBio() {
		editBioButton.click();
		bioTextBox.clear();
		recentTooMuchInfoText = newTooMuchInfo;
		bioTextBox.sendKeys(newTooMuchInfo);
		saveBioInfo.click();
	}
	
	

}
