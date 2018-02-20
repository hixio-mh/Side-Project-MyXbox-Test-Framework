package xboxLivePageObjects_Lib;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver implements WebDriver {
	
	protected WebDriver driver;
	String browserName;
	public static JavascriptExecutor js;
	public TakesScreenshot bugCapture;
	
	public File testFailDir;
	
	
	
	public Driver (String browserName) {
		this.browserName = browserName;
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./resources/webdrivers/chromedriver_win32/chromedriver.exe");
			this.driver = new ChromeDriver();
			js = (JavascriptExecutor) this.driver;
			bugCapture = (TakesScreenshot)driver;
			testFailDir = newFailCapDir();
		}
		
		
		if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./resources/webdrivers/geckodriver.exe");
			this.driver = new FirefoxDriver();
			js = (JavascriptExecutor) this.driver;
			bugCapture = (TakesScreenshot)driver;
			testFailDir = newFailCapDir();
		}
		// TODO add the rest of the browser
		
	}
	
	public File newFailCapDir() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		this.testFailDir = new File("./target/surefire-reports/error-pics/TestSuiteFailiures_"+formater.format(calendar));
		this.testFailDir.mkdir();
		return this.testFailDir;
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
