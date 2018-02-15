package xboxLivePageObjects_Lib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class XboxLiveMainPage extends PageObject{
	
	@FindBy(id="shellmenu_85")
	private WebElement myXboxMenu;
	
	@FindBy(id="shellmenu_86")
	private WebElement myXboxHome;
	
	@FindBy(id="shellmenu_87")
	private WebElement myXboxProfile;
	
	@FindBy(id="shellmenu_88")
	private WebElement myXboxAchievements;
	
	@FindBy(id="shellmenu_89")
	private WebElement myXboxFriends;
	
	@FindBy(id="shellmenu_90")
	private WebElement myXboxMessages;
	
	@FindBy(id="shellmenu_91")
	private WebElement myXboxMyGames;
	
	@FindBy(id="shellmenu_92")
	private WebElement myXboxClubs;
	
	@FindBy(id="shellmenu_93")
	private WebElement myXboxTrendingOnXboxLive;
	
	public XboxLiveMainPage(Driver driver) {
		super(driver);
	}
	
	public boolean isInitialized() {
		return myXboxMenu.isDisplayed();
	}
	
	public void showMyXboxMenu() {
		this.myXboxMenu.click();
	}
	
	public boolean contentExists(String optionSelected) {
		
		if(optionSelected.equals("Home")) {
			return myXboxHome.isDisplayed();
		}
		else if(optionSelected.equals("Profile")) {
			return myXboxProfile.isDisplayed();
		}
		else if(optionSelected.equals("Achievements")) {
			return myXboxAchievements.isDisplayed();
		}
		else if(optionSelected.equals("Friends")) {
			return myXboxFriends.isDisplayed();
		}
		else if(optionSelected.equals("Messages")) {
			return myXboxMessages.isDisplayed();
		}
		else if(optionSelected.equals("My games")) {
			return myXboxMyGames.isDisplayed();
		}
		else if(optionSelected.equals("Clubs")) {
			return myXboxClubs.isDisplayed();
		}
		else if(optionSelected.equals("Trending on Xbox Live")) {
			return myXboxTrendingOnXboxLive.isDisplayed();
		}
		else {
			System.out.println("That option is unavailable");
			return false;
		}
		
	}
	
	public void selectMyXbox(String optionSelected) {
		this.myXboxMenu.click();
		synchronized (driver) {
			try {driver.wait(3000);} 
			catch (InterruptedException r) { r.printStackTrace();}
		}
		
		if(optionSelected.equals("Home")) {
			this.myXboxHome.click();
		}
		else if(optionSelected.equals("Profile")) {
			this.myXboxProfile.click();
		}
		else if(optionSelected.equals("Achievements")) {
			this.myXboxAchievements.click();
		}
		else if(optionSelected.equals("Friends")) {
			this.myXboxFriends.click();
		}
		else if(optionSelected.equals("Messages")) {
			this.myXboxMessages.click();
		}
		else if(optionSelected.equals("My games")) {
			this.myXboxMyGames.click();
		}
		else if(optionSelected.equals("Clubs")) {
			this.myXboxClubs.click();
		}
		else if(optionSelected.equals("Trending on Xbox Live")) {
			this.myXboxTrendingOnXboxLive.click();
		}
		else {
			System.out.println("That option is unavailable");
		}
	}
	

}
