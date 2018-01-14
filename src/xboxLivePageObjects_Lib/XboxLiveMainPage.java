package xboxLivePageObjects_Lib;

import static org.testng.Assert.fail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class XboxLiveMainPage extends PageObject{
	
	@FindBy(id="shellmenu_86")
	private WebElement myXboxMenu;
	
	@FindBy(id="shellmenu_87")
	private WebElement myXboxHome;
	
	@FindBy(id="shellmenu_88")
	private WebElement myXboxProfile;
	
	@FindBy(id="shellmenu_89")
	private WebElement myXboxAchievements;
	
	@FindBy(id="shellmenu_90")
	private WebElement myXboxFriends;
	
	@FindBy(id="shellmenu_91")
	private WebElement myXboxMessages;
	
	@FindBy(id="shellmenu_92")
	private WebElement myXboxMyGames;
	
	@FindBy(id="shellmenu_93")
	private WebElement myXboxClubs;
	
	@FindBy(id="shellmenu_94")
	private WebElement myXboxTrendingOnXboxLive;
	
	public XboxLiveMainPage(WebDriver driver) {
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
			return this.myXboxHome.isDisplayed();
		}
		else if(optionSelected.equals("Profile")) {
			return this.myXboxProfile.isDisplayed();
		}
		else if(optionSelected.equals("Achievements")) {
			return this.myXboxAchievements.isDisplayed();
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
			pageError(optionSelected);
			return false;
		}
		
	}
	
	public void selectMyXbox(String optionSelected) {
		this.myXboxMenu.click();
		try { super.pageWaitForThreeSec();
		} catch (InterruptedException e) {e.printStackTrace();}
		
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
			pageError(optionSelected);
		}
	}
	
	public void pageError(String selectIssue) {
		selectIssue = "Variable_"+selectIssue;
		System.out.println("An Error was encountered when trying to select: " + selectIssue + ". \n");
		super.pageError(selectIssue);
		fail("Selection Failed");
	}

}
