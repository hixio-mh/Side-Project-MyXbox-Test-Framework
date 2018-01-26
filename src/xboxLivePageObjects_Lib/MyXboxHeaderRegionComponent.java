package xboxLivePageObjects_Lib;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyXboxHeaderRegionComponent extends PageObject {
	//Redo on values.
	
	
	@FindBy(id="headerRegion")
	private WebElement microsoftHeaderRegion;
	
	@FindBy(id="uhfCatLogo")
	private WebElement xboxLogo;
	
	@FindBy(id="shellmenu_43")
	private WebElement home;
	
	@FindBy(id="shellmenu_44")
	private WebElement profile;
	
	@FindBy(id="shellmenu_45")
	private WebElement achievements;
	
	@FindBy(id="shellmenu_46")
	private WebElement friends;
	
	@FindBy(id="shellmenu_47")
	private WebElement messages;
	
	@FindBy(id="shellmenu_48")
	private WebElement myGames;
	
	@FindBy(id="shellmenu_49")
	private WebElement clubs;
	
	@FindBy(id="shellmenu_50")
	private WebElement trendingOnXboxLive;
	//In case during test we encounter a ElementNotVisiableException
	@FindBy(xpath="//*[@id=\"coreui-universalheader-lb6x37l\"]/header/div[2]/div/button[2]")
	private WebElement scrollRight;
	
	@FindBy(xpath="//*[@id=\"coreui-universalheader-lb6x37l\"]/header/div[2]/div/button[1]")
	private WebElement scrollLeft;
	
	private Map<String, WebElement> myXboxHeaderRegionBar = new HashMap<String, WebElement>();
	
	
	public MyXboxHeaderRegionComponent (WebDriver driver) {
		super(driver);
		this.myXboxHeaderRegionBar.put("logo", this.xboxLogo);
		this.myXboxHeaderRegionBar.put("home", this.home);
		this.myXboxHeaderRegionBar.put("profile", this.profile);
		this.myXboxHeaderRegionBar.put("achievements", this.achievements);
		this.myXboxHeaderRegionBar.put("friends", this.friends);
		this.myXboxHeaderRegionBar.put("messages", this.messages);
		this.myXboxHeaderRegionBar.put("my games", this.myGames);
		this.myXboxHeaderRegionBar.put("clubs", this.clubs);
		this.myXboxHeaderRegionBar.put("trending on xbox live", this.trendingOnXboxLive);
		
	}
	
	public boolean isIntiliazed() {
		return microsoftHeaderRegion.isDisplayed();
	}
	
	//Work on this and make it more cleaner for code. Too many loops.
	//Maybe switch to If case state or simple for loop.
	public void goTo(String pageLocation) {
		boolean found = false;
		OUTER:
		while (found == false) {
			REDO:
			for(String match:this.myXboxHeaderRegionBar.keySet() ) {
			
				try {
					if(match.equalsIgnoreCase(pageLocation)) {
						this.myXboxHeaderRegionBar.get(pageLocation).click();
						found = true;
						break OUTER;
					}
				} catch (ElementNotVisibleException e) {
					this.scrollRight.click();
					break REDO;
				}
			}
		}
	}
	

}
