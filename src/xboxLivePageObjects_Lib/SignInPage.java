package xboxLivePageObjects_Lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends PageObject{
	@FindBy(name="f1")
	public WebElement signInBackGround;
	
	@FindBy(className="background-logo-holder")
	public WebElement signInBackGroundLogo;
	
	public WebElement signInBlock = signInBackGround.findElement(By.cssSelector(".inner.app"));
	
	public WebElement microsoftLogo = signInBlock.findElement(By.className("logo"));
	
	@FindBy(id="loginHeader")
	public WebElement signInHeader;
	
	@FindBy(name="loginfmt")
	public WebElement emailName;
	
	@FindBy(id="idSIButton9")
	public WebElement submitButton;
	
	@FindBy(id="signup")
	public WebElement signUpLink;
	
	@FindBy(name="passwd")
	private WebElement pW;
	
	@FindBy(id="displayName")
	public WebElement emailBanner;
	
	@FindBy(id="idBtn_Back")
	public WebElement signInBackButton;
	
	@FindBy(id="idA_PWD_ForgotPassword")
	public WebElement forgotPWLink;
	
	@FindBy(id="idTb_PWD_KMSI_Cb")
	public WebElement keepMeSignedInCheckSection;
	
	@FindBy(id="idLbl_PWD_KMSI_Cb")
	public WebElement keepMeSignedInLabel;
	
	@FindBy(id="idChkBx_PWD_KMSI0Pwd")
	public WebElement keepMeSignedInCheckBox;
	
	@FindBy(id="usernameError")
	public WebElement signInError;
	
	@FindBy(id="idA_PWD_SignUp")
	public WebElement signINErrorSignUpLink;
	
	public WebElement pWErrorMessage;
	
	public WebElement pWResetLink;
	
	public SignInPage(Driver driver){
		super(driver);
	}
	
	public boolean isInitialized() {
		return emailName.isDisplayed();
	}
	
	public void enterEmail(String email) {
		this.emailName.clear();
		this.emailName.sendKeys(email);
		clickingSubmit();
		synchronized (driver) {
			try {driver.wait(3000);} 
			catch (InterruptedException r) { r.printStackTrace();}
		}
	}
	
	public void clickingSubmit() {
		this.submitButton.click();
	}
	
	public void enterPW(String pW) {
		this.pW.clear();
		this.pW.sendKeys(pW);
		synchronized (driver) {
			try {driver.wait(4000);} 
			catch (InterruptedException r) { r.printStackTrace();}
		}
	}
	
	public WebElement getPWCell() {
		return pW;
	}
	
	public String getSignInHeader() {
		return signInHeader.getText();
	}
	
	public String getSubmitButtonText () {
		return submitButton.getAttribute("value");
	}
	
	public void goToSignUp() {
		signUpLink.click();
	}
	
	public String signInErrorMessage() {
		return signInError.getText();
	}
	
	public void signInErrorSignUpOption() {
		signINErrorSignUpLink.click();
	}
	
	public String signInErrorSignUp() {
		return signINErrorSignUpLink.getText();
	}
	
	public String getEmailBannerText() {
		return emailBanner.getText();
	}
	
	public String getEmailBannerTitle() {
		return emailBanner.getAttribute("title");
	}
	
	public void changeEmailSignIn() {
		signInBackButton.click();
	}
	
	public String keepMeSignedInLabel() {
		return keepMeSignedInLabel.getText();
	}
	
	public void keepMeSignedInChecked() {
		keepMeSignedInCheckBox.click();
	}
	
	public SignInConfirmationComponent submit() {
		clickingSubmit();
		synchronized (driver) {
			try {driver.wait(8000);} 
			catch (InterruptedException r) { r.printStackTrace();}
		}
		return new SignInConfirmationComponent(driver);
	}
	
	public void intiPWError() {
		pWErrorMessage = signInBlock.findElement(By.id("passwordError"));
		pWResetLink = pWErrorMessage.findElement(By.id("idA_IL_ForgotPassword0"));
	}
	
	public SignInPage pWFail() {
		clickingSubmit();
		
		return new SignInPage(driver);
	}
	
	public String pWErrorMessage() {
		intiPWError();
		return pWErrorMessage.getText();
	}
	
	public String pWErrorResetLink() {
		intiPWError();
		return  pWResetLink.getText();
	}
	
	
	
	

}
