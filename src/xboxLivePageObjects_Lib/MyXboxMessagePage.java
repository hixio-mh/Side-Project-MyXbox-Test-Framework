package xboxLivePageObjects_Lib;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyXboxMessagePage extends PageObject {
	
	
	//Incomplete Class (Needs more development.)
	@FindBy(className="mainContentContainer")
	public WebElement wholeMessageContent;
	
	@FindBy(className="messagesPanel")
	public WebElement messagesPanel;
	
	public WebElement messageListHeader = messagesPanel.findElement(By.cssSelector(".sectionHeader.messagesLabel"));
	
	@FindBy(className="newMessageLink")
	public WebElement newMessageButton;
	
	@FindBy(id="messageList")
	public WebElement messageList;
	
	@FindBy(className="messageListContainer")
	public WebElement messageListContainer;
	
	@FindBy(className="replyTextBox")
	public WebElement messageReplyTextBox;
	
	@FindBy(id="loadingOverlay")
	public WebElement loadingOverlay;
	
	public String randomMessage;
	public String tooMuchInfo;
	
	public WebElement entireNewMessageBox;
	public WebElement newMessageBoxHeader;
	public WebElement entireNewMessageBoxCancel;
	
	public WebElement newMessageTextBox;
	
	public WebElement newMessageActions;
	
	public WebElement newMessageSendMessageButton;
	public WebElement newMessageCancelMessageButton;
	
	public WebElement pickRecipient;
	
	public List <WebElement> recipientList;
	
	@FindBy(id="gamerTagSearchBox")
	public WebElement gamerTagSearchBoxSection;
	@FindBy(id="gamerTagSearchTextBox")
	public WebElement gamerTagSearchTextBox;
	@FindBy(id="gamerTagSearchButton")
	public WebElement gamerTagSearchButton;
	
	@FindBy(className="gamerList")
	public WebElement gamerListSection;
	public List<WebElement> gamerList;
	
	public WebElement gamerTagAddButton;
	public WebElement gamerTagCancelButton;
	
	
	
	
	
	public MyXboxMessagePage (WebDriver driver) {
		super(driver);
		randomMessage = randomMessageSetUp();
		tooMuchInfo = randomTooMuchInfoSetup();
		
	}
	
	public String randomMessageSetUp() {
		String catalog = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$^&().,.,.,.abcdefghijklmnopqrstuvwxyz";
		String newText = "";
		Random rand = new Random();
		while (newText.length() < 20) {
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
	
	public void createNewMessageInReplyBox() {
		messageReplyTextBox.click();
		messageReplyTextBox.sendKeys(randomMessage);
	}
	
	public void createNewMessageAndSendIt() {
		newMessageButton.click();
		
	}

}
