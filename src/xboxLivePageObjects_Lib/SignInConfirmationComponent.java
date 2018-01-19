package xboxLivePageObjects_Lib;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInConfirmationComponent extends PageObject {
	
	@FindBy(id="meControl")
	private WebElement accountName;
	
	@FindBy(id="meControlDropdown")
	private WebElement accountDropdownMenu;
	
	@FindBy(id="msame_si1")
	private WebElement signOut;
	
	public SignInConfirmationComponent(WebDriver driver) {
		super(driver);
	}
	
	public boolean isInitialized() {
		return accountName.isDisplayed();
	}
	
	public String confirmationSignIn() {
		return accountName.findElement(By.cssSelector(".msame_Header_name.msame_TxtTrunc")).getText();
	}
	
	public void signOut() throws InterruptedException {
		accountName.click();
		synchronized (driver) {
			driver.wait(3000);
		}
		//Maybe do a try/catch for and the catch is ElementNotVisibleExceptions
		signOut.click();
	}
	
	

}
