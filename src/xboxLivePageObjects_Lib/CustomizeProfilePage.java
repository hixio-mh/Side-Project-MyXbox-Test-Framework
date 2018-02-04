package xboxLivePageObjects_Lib;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomizeProfilePage extends PageObject {
	
	@FindBy(className="mainContentContainer")
	public WebElement entireCustomizeBodyPage;
	
	@FindBy(className="customizeprofileAvatar")
	public WebElement avatarImageSection;
	@FindBy(id="changeAvatarButton")
	public WebElement changeAvatarButton;
	
	@FindBy(className="leftTopSidePart")
	public WebElement profileImageAndNameSection;
	
	public WebElement currentProfilePic =  profileImageAndNameSection.findElement(By.className("currentGamerPic"));
	public String currentProfilePicSRC = currentProfilePic.getAttribute("src");
	public static String oldProfilePicSRC = "";
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
	public WebElement bioTextBox = profileBioSection.findElement(By.className("bioTextBox"));
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
	public String currentLocationText = profileLocationSection.findElement(By.cssSelector(".locationInfoArea.customizeProfileTextStyle")).getText();
	public static String recentLocationText = "";
	
	public static String recentTooMuchInfoText = "";
	
	@FindBy(id="loadingOverlay")
	public WebElement loadingUpdates;
	
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
		String catalog = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$^&().,.,.,.abcdefghijklmnopqrstuvwxyz";
		String newText = "";
		Random rand = new Random();
		while (newText.length() < 40) {
			int index = rand.nextInt(catalog.length());
			newText += catalog.charAt(index);
		}
		return newText;
	}
	
	public String randomBioInfoSetup() {
		String catalog = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$^&()..,.,.,.,.abcdefghijklmnopqrstuvwxyz";
		String newText = "";
		Random rand = new Random();
		while (newText.length() < 50) {
			int index = rand.nextInt(catalog.length());
			newText += catalog.charAt(index);
		}
		return newText;
	}
	
	public String randomTooMuchInfoSetup() {
		String catalog = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$^&().,.,.,.,.abcdefghijklmnopqrstuvwxyz";
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
		synchronized (driver) {
			try {driver.wait(3000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
	}
	
	public String getProfileName() {
		return profileName.getText();
	}
	
	public void changeGamerTag() {
		changeProfileName.click();
		synchronized (driver) {
			try {driver.wait(3000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
	}
	
	public void changeProfilePic() {
		changeGamerPic.click();
		oldProfilePicSRC = currentProfilePicSRC;
		synchronized (driver) {
			try {driver.wait(4000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
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
	
	public void recheckProfilePic() {
		currentProfilePicSRC = currentProfilePic.getAttribute("src");
	}
	
	public void declareProfilePicCatalog() {
		profilePicCatalog = profileEditPicSection.findElements(By.className("gamerPicItem"));
		
	}
	
	public void recheckCurrentLocationText() {
		currentLocationText = profileLocationSection.findElement(By.cssSelector(".locationInfoArea.customizeProfileTextStyle")).getText();
	}
	
	public void recheckCurrentBioText() {
		currentBioText = profileBioSection.findElement(By.cssSelector(".bioInfoArea.customizeProfileTextStyle")).getText();
	}
	
	
	public void enterLocationInfo() {
		editLocationButton.click();
		synchronized (driver) {
			try {driver.wait(5000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
		locationTextBox.clear();
		synchronized (driver) {
			try {driver.wait(3000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
		recentLocationText = newLocationInfo;
		locationTextBox.sendKeys(newLocationInfo);
		saveLocationInfo.click();
		waitOverLay();
	}
	
	public void enterButCancelLocation() {
		editLocationButton.click();
		synchronized (driver) {
			try {driver.wait(5000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
		locationTextBox.clear();
		synchronized (driver) {
			try {driver.wait(3000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
		recentLocationText = newLocationInfo;
		locationTextBox.sendKeys(newLocationInfo);
		cancelLocationInfo.click();
		waitOverLay();
	}
	
	public void enterBioInfo() {
		editBioButton.click();
		synchronized (driver) {
			try {driver.wait(5000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
		bioTextBox.clear();
		synchronized (driver) {
			try {driver.wait(3000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
		recentBioText = newBioInfo;
		bioTextBox.sendKeys(newBioInfo);
		saveBioInfo.click();
		waitOverLay();
	}
	
	public void enterButCancelBioInfo() {
		editBioButton.click();
		synchronized (driver) {
			try {driver.wait(5000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
		bioTextBox.clear();
		synchronized (driver) {
			try {driver.wait(3000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
		recentBioText = newBioInfo;
		bioTextBox.sendKeys(newBioInfo);
		cancelBioInfo.click();
		waitOverLay();
	}
	
	public void enterTooMuchInfoLocation() {
		editLocationButton.click();
		synchronized (driver) {
			try {driver.wait(5000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
		locationTextBox.clear();
		synchronized (driver) {
			try {driver.wait(3000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
		recentTooMuchInfoText = newTooMuchInfo;
		locationTextBox.sendKeys(newTooMuchInfo);
		saveLocationInfo.click();
		waitOverLay();
	}
	
	public void enterTooMuchInfoBio() {
		editBioButton.click();
		synchronized (driver) {
			try {driver.wait(5000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
		bioTextBox.clear();
		synchronized (driver) {
			try {driver.wait(3000);} 
			catch (InterruptedException e) { e.printStackTrace();}
		}
		recentTooMuchInfoText = newTooMuchInfo;
		bioTextBox.sendKeys(newTooMuchInfo);
		saveBioInfo.click();
		waitOverLay();
	}
	
	public void waitOverLay() {
		while (!(loadingUpdates.getAttribute("style").equals("display: none;"))) {
			synchronized (driver) {
				try {driver.wait(9000);} 
				catch (InterruptedException e) { e.printStackTrace();}
			}
		}

		
	}
	
	

}
