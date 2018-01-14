package xboxLivePageObjects_Lib;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//Look up original file on QA 101 site.
public class Driver implements WebDriver {
	
	WebDriver driver;
	String browserName;
	
	public Driver (String browserName) {
		this.browserName = browserName;
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./resources/webdrivers/chromedriver_win32/chromedriver.exe");
			this.driver = new ChromeDriver();
		}
		
		// TODO add the rest of the browser
		
		
	}

	public void close() {
		this.driver.close();
	}

	public WebElement findElement(By by) {
		return this.driver.findElement(by);
	}

	public List<WebElement> findElements(By by) {
		return this.driver.findElements(by);
	}

	public void get(String url) {
		this.driver.get(url);
	}

	public String getCurrentUrl() {
		return this.driver.getCurrentUrl();
	}

	public String getPageSource() {
		return this.driver.getPageSource();
	}

	public String getTitle() {
		return this.driver.getTitle();
	}

	public String getWindowHandle() {
		return this.driver.getWindowHandle();
	}

	public Set<String> getWindowHandles() {
		return this.driver.getWindowHandles();
	}

	public Options manage() {
		return this.driver.manage();
	}

	public Navigation navigate() {
		return this.driver.navigate();
	}

	public void quit() {
		this.driver.quit();
	}

	public TargetLocator switchTo() {
		return this.driver.switchTo();
	}

}
