package xboxLivePageObjects_Lib;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
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
	public WebElement leftSideMessageListSection;
	
	public List<WebElement> leftSideMessageList = leftSideMessageListSection.findElements(By.tagName("li"));
	
	//Set a method to declare this list to avoid initialization issues.
	
	
	@FindBy(className="messageListContainer")
	public WebElement messageListContainer;
	
	
	
	public String randomMessage;
	public static String recentMessage = "";
	public String tooMuchInfo;
	
	//Create New Message Box
	public WebElement entireNewMessageBox;
	public WebElement newMessageBoxHeader;
	public WebElement entireNewMessageBoxCancel;
	public WebElement newMessageTextBox;
	public WebElement newMessageLengthPrompt;
	
	public WebElement newMessageActions;
	
	public WebElement newMessageSendMessageButton;
	public WebElement newMessageCancelMessageButton;
	
	public WebElement pickNewMessageRecipientSection;
	public WebElement pickRecipient;
	
	
	//Recipient section
	public WebElement newRecipientBoxSection;
	public WebElement newRecipientBoxTopCancelButton;
	
	public List <WebElement> recipientList;
	
	public WebElement gamerTagSearchBoxSection;
	public WebElement gamerTagSearchTextBox;
	public WebElement gamerTagSearchButton;
	public WebElement gamerTagSearchFailedMessage;
	
	public WebElement recipientListHeader;
	public WebElement gamerListSection;
	public List<WebElement> gamerList;
	public WebElement gamerListErrorMessage;
	
	public WebElement gamerTagAddButton;
	public WebElement gamerTagCancelButton;
	
	
	//Right side Message Section
	
	public WebElement rightSideMessageListSection = wholeMessageContent.findElement(By.cssSelector(".messageSection.sectionPanel"));
	
	public WebElement rightSideMessageList = rightSideMessageListSection.findElement(By.className("messageListContainer"));
	
	
	
	@FindBy(className="replyTextBox")
	public WebElement messageReplyTextBox;
	
	@FindBy(id="loadingOverlay")
	public WebElement loadingOverlay;
	
	
	
	
	public MyXboxMessagePage (Driver driver) {
		super(driver);
		randomMessage = randomMessageSetUp();
		tooMuchInfo = randomTooMuchInfoSetup();
		
	}
	
	public String randomMessageSetUp() {
		String catalog = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$^&()?/><;:'\"}{][=-_+`~.,.,.,.abcdefghijklmnopqrstuvwxyz";
		String newText = "";
		Random rand = new Random();
		while (newText.length() < 500) {
			int index = rand.nextInt(catalog.length());
			newText += catalog.charAt(index);
		}
		return newText;
	}
	
	public String randomTooMuchInfoSetup() {
		String catalog = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$^&()?/><;:'\"}{][=-_+`~.,.,.,.abcdefghijklmnopqrstuvwxyz";
		String newText = "";
		Random rand = new Random();
		while (newText.length() < 502) {
			int index = rand.nextInt(catalog.length());
			newText += catalog.charAt(index);
		}
		return newText;
	}
	
	//Right side Message list method.
	
	public void createNewMessageInReplyBox() {
		messageReplyTextBox.click();
		messageReplyTextBox.sendKeys(randomMessage);
	}
	//Work on project
	public void createNewMessageAndSendItToOneFriend() {
		newMessageButton.click();
		declareNewMessageBoxElements();
		pickRecipient.click();
		declareRecipientBoxElements();
		addSingleRecipient();
		gamerTagAddButton.click();
		newMessageTextBox.sendKeys(randomMessage);
		recentMessage = randomMessage;
		System.out.println(newMessageLengthPrompt.getText());
		newMessageSendMessageButton.click();	
		
		leftSideMessageList = leftSideMessageListSection.findElements(By.tagName("li"));
	}
	
	public void createNewMessageAndSendOverloadedToFriend() {
		newMessageButton.click();
		declareNewMessageBoxElements();
		pickRecipient.click();
		declareRecipientBoxElements();
		addSingleRecipient();
		gamerTagAddButton.click();
		newMessageTextBox.sendKeys(tooMuchInfo);
		recentMessage = tooMuchInfo;
		System.out.println(newMessageLengthPrompt.getText());
		newMessageSendMessageButton.click();	
		
		leftSideMessageList = leftSideMessageListSection.findElements(By.tagName("li"));
	}
	
	//Part of the createNewMessageMethod above
	public void declareNewMessageBoxElements() {
		entireNewMessageBox = wholeMessageContent.findElement(By.cssSelector(".modal.newMessageModal"));
		
		newMessageBoxHeader = entireNewMessageBox.findElement(By.className("modalHeader"));
		entireNewMessageBoxCancel = entireNewMessageBox.findElement(By.cssSelector(".closeModalIcon.xbox-font-icons.xbox-font-icons-CloseIcon"));
		
		pickNewMessageRecipientSection = entireNewMessageBox.findElement(By.className("receiptsLine"));
		pickRecipient = pickNewMessageRecipientSection.findElement(By.cssSelector(".addReceiptsIcon.xbox-font-icons.xbox-font-icons-AddIcon"));
		
		newMessageTextBox = entireNewMessageBox.findElement(By.id("textNewMessage"));
		newMessageLengthPrompt = entireNewMessageBox.findElement(By.className("messageLengthPrompt"));
		
		newMessageActions = entireNewMessageBox.findElement(By.id("newMessagesActions"));
		
		newMessageSendMessageButton = newMessageActions.findElement(By.className("sendMessage"));
		newMessageCancelMessageButton = newMessageActions.findElement(By.className("cancelMessage"));
		
	}
	
	//recipient box
	public void declareRecipientBoxElements() {
		newRecipientBoxSection = wholeMessageContent.findElement(By.cssSelector(".modal.recipientsModal"));
		
		newRecipientBoxTopCancelButton = newRecipientBoxSection.findElement(By.cssSelector(".closeModalIcon.xbox-font-icons.xbox-font-icons-CloseIcon"));
		
		gamerTagSearchBoxSection = newRecipientBoxSection.findElement(By.id("gamerTagSearchBox"));
		
		gamerTagSearchTextBox = gamerTagSearchBoxSection.findElement(By.id("gamerTagSearchTextBox"));
		gamerTagSearchButton = gamerTagSearchBoxSection.findElement(By.id("gamerTagSearchButton"));
		
		gamerTagSearchFailedMessage = gamerTagSearchBoxSection.findElement(By.id("gamerTagSearchFailedMessage"));
		
		recipientListHeader = newRecipientBoxSection.findElement(By.className("titleContent"));
		
		gamerListSection = newRecipientBoxSection.findElement(By.className("gamerList"));
		gamerList = gamerListSection.findElements(By.tagName("li"));
		
		gamerListErrorMessage = newRecipientBoxSection.findElement(By.className("errorMessage"));
		
		gamerTagAddButton = newRecipientBoxSection.findElement(By.className("addButton"));
		gamerTagCancelButton = newRecipientBoxSection.findElement(By.className("cancelDialogueConversation"));
		
	}
	
	public void addSingleRecipient() {
		int counter = 0;
		Random rand = new Random();
		int randNum = rand.nextInt(gamerList.size());
		for (WebElement e : gamerList) {
			if(counter == randNum) {
				e.click();
				break;
			}
			counter++;
		}
	}
	
	//method to assign the new gamerlist upon after entering text in the gamertag search box
	
	//method to sepearte from gamers from friend list and who's added based on searches
	
	//method to maximaze the recipent list to greater than 32 
	
	//Method to ensure search results are retained.
	

}
