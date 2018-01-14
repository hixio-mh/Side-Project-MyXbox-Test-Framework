package xboxLivePageObjects_Lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewOtherProfiles extends PageObject {
	
	//Work on this when for activity feed, captures, achievements, and friends.
	
	@FindBy(className="c-heading-3")
	private WebElement profileName;
	
	@FindBy(id="right-side")
	private WebElement profileInfo;
	
	@FindBy(id="addfriendactionbutton")
	private WebElement addFriendButton;

	
	public ViewOtherProfiles (WebDriver driver) {
		super(driver);
	}
	
	public String getProfile() {
		String profileNameIs = this.profileName.getText();
		return profileNameIs;
	}
	
	public void addFriendButton() {
		this.addFriendButton.click();
	}

	
}
