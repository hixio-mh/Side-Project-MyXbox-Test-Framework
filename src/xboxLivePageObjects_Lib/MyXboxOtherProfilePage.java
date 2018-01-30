package xboxLivePageObjects_Lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyXboxOtherProfilePage extends PageObject {
	
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
	
	public WebElement moreButton = xboxProfileCardInfo.findElement(By.cssSelector(".c-action-trigger.x-hidden-focus"));
	@FindBy(id="report")
	public WebElement reportButton;
	@FindBy(id="blockuseractionbutton")
	public WebElement blockButton;
	
	@FindBy(id="item-values")
	public WebElement friendsAndFollowersStats;
	
	
	public MyXboxOtherProfilePage (WebDriver driver) {
		super(driver);
	}
	
	public MyXboxOtherProfilePage addFriend() {
		
		addFriend = xboxProfileCardInfo.findElement(By.id("addfriendactionbutton"));
		addFriend.click();
		
		return new MyXboxOtherProfilePage(driver);
	}
	
	public MyXboxOtherProfilePage removeFriend() {
		removeFriend = xboxProfileCardInfo.findElement(By.id("removefriendactionbutton"));
		removeFriend.click();
		
		return new MyXboxOtherProfilePage(driver);
	}
	
	public String getProfileName() {
		return profileName;
	}
	
	public void messageProfile() {
		messageButton.click();
	}
	
	public void openMoreMenu() {
		if (moreButton.getAttribute("id aria-expanded").equals("false")) {
			moreButton.click();
		}
		else {
			System.out.println("The More Menu is already open");
		}
	}
	
	public void closeMoreMenu() {
		if (moreButton.getAttribute("id aria-expanded").equals("true")) {
			moreButton.click();
		}
		else {
			System.out.println("The More Menu isn't open.");
		}
	}
	
	

}
