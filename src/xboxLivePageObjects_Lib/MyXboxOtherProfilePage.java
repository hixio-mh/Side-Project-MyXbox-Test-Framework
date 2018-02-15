package xboxLivePageObjects_Lib;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyXboxOtherProfilePage extends PageObject {
	
	//Program in progress
	
	@FindBy(id="primaryArea")
	public WebElement entireProfileWebPage;
	
	public String otherProfileURL = driver.getCurrentUrl();
	
	@FindBy(id="xboxsummary")
	public WebElement xboxProfileCard;
	
	@FindBy(id="left-side")
	public WebElement xboxProfileImage;
	
	@FindBy(id="right-side")
	public WebElement xboxProfileCardInfo;
	
	public String profileName = xboxProfileCardInfo.findElement(By.tagName("h3")).getText();
	
	public WebElement addFriend;
	
	public WebElement removeFriend;
	
	@FindBy(id="message")
	public WebElement messageButton;
	
	public WebElement moreButtonSection = xboxProfileCardInfo.findElement(By.className("c-action-menu"));
	
	public WebElement moreButton = moreButtonSection.findElement(By.className("c-action-trigger"));
	@FindBy(id="report")
	public WebElement reportButton;
	@FindBy(id="blockuseractionbutton")
	public WebElement blockButton;
	
	@FindBy(id="item-values")
	public WebElement friendsAndFollowersStats;
	
	
	public MyXboxOtherProfilePage (Driver driver) {
		super(driver);
	}
	
	public MyXboxOtherProfilePage addFriend() {
		
		addFriend = xboxProfileCardInfo.findElement(By.id("addfriendactionbutton"));
		addFriend.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return new MyXboxOtherProfilePage(driver);
	}
	
	public void checkAddFriendButton() {
		addFriend = xboxProfileCardInfo.findElement(By.id("addfriendactionbutton"));
	}
	
	public void checkRemoveFriendButton() {
		removeFriend = xboxProfileCardInfo.findElement(By.id("removefriendactionbutton"));
	}
	
	public MyXboxOtherProfilePage removeFriend() {
		removeFriend = xboxProfileCardInfo.findElement(By.id("removefriendactionbutton"));
		removeFriend.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return new MyXboxOtherProfilePage(driver);
	}
	
	public String getProfileName() {
		return profileName;
	}
	
	public void messageProfile() {
		messageButton.click();
	}
	
	public void openMoreMenu() {
		if (moreButton.getAttribute("aria-expanded").equals("false")) {
			moreButton.click();
			moreButton = moreButtonSection.findElement(By.cssSelector(".c-action-trigger.f-active"));
		}
		else {
			System.out.println("The More Menu is already open");
		}
	}
	
	public void closeMoreMenu() {
		if (moreButton.getAttribute("aria-expanded").equals("true")) {
			moreButton.click();
			moreButton = moreButtonSection.findElement(By.cssSelector(".c-action-trigger.x-hidden-focus"));
		}
		else {
			System.out.println("The More Menu isn't open.");
		}
	}
	
	

}
