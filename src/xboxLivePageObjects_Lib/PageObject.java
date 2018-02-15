package xboxLivePageObjects_Lib;


import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;


public class PageObject {
	protected Driver driver;
	public Actions act;
	
	
	PageObject(Driver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
		
	}
	
}
