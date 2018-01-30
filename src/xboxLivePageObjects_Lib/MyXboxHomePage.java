package xboxLivePageObjects_Lib;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyXboxHomePage extends PageObject {
	//Entire Activity Feed and popular Mixer sections
	@FindBy(id="primaryR1")
	public WebElement entireHomePage;
	
	
	//The commands for See all, scroll left or right and selecting specific tab can be done in methods
	@FindBy(id="Xbox-XboxPopularFeed-wpydnul")
	public WebElement popularMixerStreamsSection;
	public List<WebElement> streamList; 
	
	public List<WebElement> scrollOptions = popularMixerStreamsSection.findElements(By.tagName("button"));
	
	public WebElement seeAll = popularMixerStreamsSection.findElement(By.partialLinkText("See all"));
	
	public WebElement headerMixerStreams = popularMixerStreamsSection.findElement(By.cssSelector(".c-heading-6.f-lean"));
	
	
	public MyXboxHomePage(WebDriver driver) {
		super(driver);
		streamList = popularMixerStreamsSection.findElements(By.tagName("li"));
	}
	
	public void seeAllMixerChannels() {
		seeAll.click();
	}
	
	public boolean pickRandomMixerChannel() {
		boolean foundALink = false;
		Random rand = new Random();
		int counter = 0;
		int randNum = rand.nextInt(streamList.size())+2;
		boolean notClicked = false;
		for (WebElement e : streamList) {
			if (counter == randNum) {
				while (notClicked == false) {
					try {
						e.findElement(By.tagName("a")).click();	
					}
					catch (ElementNotVisibleException r) {
						for(WebElement i : scrollOptions) {
							if (i.getAttribute("aria-label").equals("Next")) {
								i.click();
								break;
							}
						}
						break;
					}
					notClicked = true;
				}
				foundALink = true;
				break;
			}
			 
			counter++;
		}
		return foundALink;
	}
	
	public String getMixerHeader() {
		return headerMixerStreams.getText();
	}
	
	
	
	
	
	
	
}
