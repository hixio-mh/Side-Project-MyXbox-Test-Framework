package xboxLivePageObjects_Lib;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyXboxHeaderRegionComponent extends PageObject {
	
	
	@FindBy(id="headerRegion")
	public WebElement microsoftHeaderRegion;
	
	@FindBy(id="uhfCatLogo")
	public WebElement xboxLogo;
	
	@FindBy(id="shellmenu_42")
	public WebElement home;
	
	@FindBy(id="shellmenu_43")
	public WebElement profile;
	
	@FindBy(id="shellmenu_44")
	public WebElement achievements;
	
	@FindBy(id="shellmenu_45")
	public WebElement friends;
	
	@FindBy(id="shellmenu_46")
	public WebElement messages;
	
	@FindBy(id="shellmenu_47")
	public WebElement myGames;
	
	@FindBy(id="shellmenu_48")
	public WebElement clubs;
	
	@FindBy(id="shellmenu_49")
	public WebElement trendingOnXboxLive;
	
	public WebElement scrollRight;
	
	public WebElement scrollLeft;
	
	public List<WebElement> scrollOptions;
	
	
	public Map<String, WebElement> myXboxHeaderRegionBar = new HashMap<String, WebElement>();
	
	
	public MyXboxHeaderRegionComponent (WebDriver driver) {
		super(driver);
		myXboxHeaderRegionBar.put("logo", xboxLogo);
		myXboxHeaderRegionBar.put("home", home);
		myXboxHeaderRegionBar.put("profile", profile);
		myXboxHeaderRegionBar.put("achievements", achievements);
		myXboxHeaderRegionBar.put("friends", friends);
		myXboxHeaderRegionBar.put("messages", messages);
		myXboxHeaderRegionBar.put("my games", myGames);
		myXboxHeaderRegionBar.put("clubs", clubs);
		myXboxHeaderRegionBar.put("trending on xbox live", trendingOnXboxLive);
		
	}
	
	public boolean isIntiliazed() {
		return microsoftHeaderRegion.isDisplayed();
	}
	
	public MyXboxHeaderRegionComponent goTo(String pageLocation) {
		boolean found = false;
		OUTER:
		while (found == false) {
			REDO:
			for(String match:myXboxHeaderRegionBar.keySet() ) {
			
				try {
					if(match.equalsIgnoreCase(pageLocation)) {
						myXboxHeaderRegionBar.get(pageLocation).click();
						found = true;
						break OUTER;
					}
				} catch (ElementNotVisibleException e) {
					scrollRight();
					break REDO;
				}
			}
		}
		synchronized (driver) {
			try {driver.wait(4000);} 
			catch (InterruptedException r) { r.printStackTrace();}
		}
		return new MyXboxHeaderRegionComponent(driver);
	}
	
	public void scrollArrows() {
		scrollOptions = microsoftHeaderRegion.findElements(By.tagName("button"));
	}
	
	public void scrollRight() {
		scrollArrows();
		for (WebElement e : scrollOptions) {
			if(e.getAttribute("title").equals("Scroll right")) {
				e.click();
				break;
			}
		}
	}
	
	public void scrollLeft() {
		scrollArrows();
		for (WebElement e : scrollOptions) {
			if(e.getAttribute("title").equals("Scroll Left")) {
				e.click();
				break;
			}
		}
	}
	

}
