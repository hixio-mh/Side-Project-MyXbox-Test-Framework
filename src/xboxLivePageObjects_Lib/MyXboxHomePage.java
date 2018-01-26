package xboxLivePageObjects_Lib;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyXboxHomePage extends PageObject {
	//Entire Activity Feed and popular Mixer sections
	@FindBy(id="primaryR1")
	public WebElement entireActivityFeedAndMixer;
	
	//The commands for See all, scroll left or right and selecting specific tab can be done in methods
	@FindBy(id="Xbox-XboxPopularFeed-wpydnul")
	private WebElement popularMixerStreamsSection;
	private List<WebElement> streamList;
	
	
	
	@FindBy(id="newpostinput")
	private WebElement generalNewPostInput;
	@FindBy(id="newpost")
	private WebElement generalPostButton;
	
	public String randomGibberish;
	
	//Create the list during constructor and have Like, Share, Comment, Delete, Url hyperlinks, and Dialog Box in groups of methods.
	@FindBy(id="Xbox-XboxActivityFeed-720obsl")
	private WebElement homePageActivityFeed;
	private List<WebElement> postedActivityFeed;
	@FindBy(id="xboxdialog")
	private WebElement viewSpecificFeedPost;
	@FindBy(id="getmore")
	private WebElement getMore;
	
	
	
	
	//Rework since the ActivityFeedComponent is its own separate object.
	public MyXboxHomePage(WebDriver driver) {
		super(driver);
		streamList = popularMixerStreamsSection.findElements(By.className("f-active"));
		postedActivityFeed = entireActivityFeedAndMixer.findElements(By.className("xboxactivityfeeditem"));
		randomGibberish = randomSetup();
	}
	
	public void newPost(String message) {
		generalNewPostInput.sendKeys(message);
		generalPostButton.click();
		synchronized (driver) {
			  try {driver.wait(3000);} 
			  catch (InterruptedException e) { e.printStackTrace();}
		  }
		postedActivityFeed.clear();
		postedActivityFeed = homePageActivityFeed.findElements(By.className("xboxactivityfeeditem"));
	}
	
	public boolean findNewPost(String message) {
		boolean found = false;
		int listCounter = 0;
		OUTER:
		for(WebElement e : postedActivityFeed) {
			listCounter++;
			try {
				String retrievedText = e.findElement(By.className("c-paragraph")).getText();
				
				System.out.println(listCounter + ": " + retrievedText);
				if(retrievedText.trim().equals(message)) {
					found = true;
					System.out.println("New message has been found");
					break OUTER;
				}
			} catch (NoSuchElementException moveAlong) {
				continue;
			}
		}
		return found;
	}
	
	//Creating a random set of string to reduce the chance of false asserts during test.
	public String randomSetup() {
		String catalog = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$^&()";
		String newText = "";
		Random rand = new Random();
		while (newText.length() < 10) {
			int index = rand.nextInt(catalog.length());
			newText += catalog.charAt(index);
		}
		return newText;
	}

}
