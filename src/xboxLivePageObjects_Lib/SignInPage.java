package xboxLivePageObjects_Lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends PageObject{
	@FindBy(name="loginfmt")
	private WebElement emailName;
	
	@FindBy(id="idSIButton9")
	private WebElement sunbmitButton;
	
	@FindBy(name="passwd")
	private WebElement pW;
	
	
	
	public SignInPage(WebDriver driver){
		super(driver);
	}
	
	public boolean isInitialized() {
		return emailName.isDisplayed();
	}
	
	public void enterEmail(String email) {
		this.emailName.clear();
		this.emailName.sendKeys(email);
		clickingSubmit();
		try { super.pageWaitForThreeSec();} 
		catch (InterruptedException e) {e.printStackTrace();}
	}
	
	public void clickingSubmit() {
		this.sunbmitButton.click();
	}
	
	public void enterPW(String pW) {
		this.pW.clear();
		this.pW.sendKeys(pW);
		try { super.pageWaitForThreeSec();} 
		catch (InterruptedException e) {e.printStackTrace();}
	}
	
	public void pageError() {
		super.pageError("Sign in");
	}
	
	public SignInConfirmationComponent submit() {
		clickingSubmit();
		return new SignInConfirmationComponent(driver);
	}

}
