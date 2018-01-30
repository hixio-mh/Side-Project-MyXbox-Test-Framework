package xboxLivePageObjects_Lib;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyXboxAchievementsComponent extends PageObject {
	
	@FindBy(id="mainTab2")
	public WebElement entireAchievementSection;
	
	@FindBy(id="xbox-TitleAggregation-2g3vz66")
	public WebElement xboxTitleSection;
	
	@FindBy(id="GamerAchievementsFilter")
	public WebElement achievementFilter;
	@FindBy(id="All")
	public WebElement allFilter;
	@FindBy(id="PC")
	public WebElement pcFilter;
	@FindBy(id="Mobile")
	public WebElement mobileFilter;
	@FindBy(id="XboxOne")
	public WebElement xboxOneFilter;
	@FindBy(id="Xbox360")
	public WebElement xbox360Filter;
	
	@FindBy(id="gameList")
	public WebElement gameListSection;
	public List<WebElement> gameList = gameListSection.findElements(By.tagName("li"));
	
	public static String recentGameViewed = "";
	
	@FindBy(id="comparelink")
	public WebElement compareWithFriends;
	public WebElement stopComparison;
	public List<WebElement> currentComparison;
	
	public static String compareOther = "";
	
	public WebElement compareSelector;
	public List<WebElement> compareList;
	public WebElement compareOKButton;
	public WebElement compareCancelButton;
	
	public MyXboxAchievementsComponent (WebDriver driver) {
		super(driver);
	}
	
	public MyXboxAchievementsComponent compareWithOthers() {
		compareWithFriends.click();
		pickAPlayer();
		int counter = 0;
		Random rand = new Random();
		int randNum = rand.nextInt(compareList.size());
		for (WebElement e : compareList) {
			if(counter == randNum) {
				e.click();
				compareOther = e.findElement(By.className("name")).getText();
				compareOKButton.click();
				break;
			}
			counter++;
		}
		return new MyXboxAchievementsComponent(driver);
	}
	
	public void pickAPlayer() {
		compareSelector = driver.findElement(By.id("sectiondialog"));
		if (compareSelector.getAttribute("aria-hidden").equals("false")){
			compareList = compareSelector.findElements(By.tagName("li"));
			compareOKButton = compareSelector.findElement(By.id("okbtn"));
			compareCancelButton = compareSelector.findElement(By.id("cancelbtn"));
		}
		else {
			System.out.println("The Selection dialog box is appearing as hidden.");
		}
		
	}
	
	public void randomGameListHyperLink() {
		int counter = 0;
		Random rand = new Random();
		int randNum = rand.nextInt(gameList.size());
		for (WebElement e : gameList) {
			if (counter == randNum) {
				recentGameViewed = e.findElement(By.tagName("a")).getAttribute("href");
				e.click();
				break;
			}
		}
	}
	
	
	public void setFilter(String filterType) {
		achievementFilter.click();
		if (filterType.equalsIgnoreCase("All")) {
			allFilter.click();
		}
		else if (filterType.equalsIgnoreCase("PC")) {
			pcFilter.click();
		}
		else if (filterType.equalsIgnoreCase("Moblie")) {
			mobileFilter.click();
		}
		else if (filterType.equalsIgnoreCase("Xbox One")) {
			xboxOneFilter.click();
		}
		else if (filterType.equalsIgnoreCase("Xbox 360")) {
			xbox360Filter.click();
		}
		else {
			achievementFilter.click();
			System.out.println("There are no filters of that specification.");
		}
		
	}
	
	public void currentComparison () {
		try {
			stopComparison = entireAchievementSection.findElement(By.id("stopcomparelink"));
			currentComparison = xboxTitleSection.findElements(By.className("xboxpeople"));
			
		}catch (NoSuchElementException r) {
			System.out.println("The request to compare hasn't been given yet.");
		}
		
		System.out.println("Current comparison is : " + currentComparison.get(0).findElement(By.tagName("a")).getAttribute("aria-label") 
				+ " and : " + currentComparison.get(1).findElement(By.tagName("a")).getAttribute("aria-label"));
		
	}
	
	public boolean verifyPlayerComparison(String profileName) {
		boolean match = false;
		if(currentComparison == null) {
			System.out.println("You haven't made a comparison with any player yet.");
			return match;
		}
		for(WebElement e : currentComparison) {
			if(profileName.equalsIgnoreCase(e.findElement(By.tagName("a")).getAttribute("aria-label"))) {
				System.out.println("We have a match for: " + profileName);
				match = true;
				break;
			}
		}
		return match;
	}
	
	public MyXboxAchievementsComponent cancelComparison() {
		if (currentComparison == null) {
			System.out.println("You haven't made a comparison with any player yet.");
		}
		else {
			stopComparison.click();
		}
		return new MyXboxAchievementsComponent (driver);
	}
	
}
