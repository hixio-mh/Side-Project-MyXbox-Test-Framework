package xboxLivePageObjects_Lib;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;


public class PageObject {
	protected WebDriver driver;
	public Actions act;
	
	PageObject(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
	}
	
}
