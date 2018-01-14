package xboxLivePageObjects_Lib;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.google.common.io.Files;

public class PageObject {
	protected WebDriver driver;
	
	PageObject(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void pageError(String methodError) {
		System.out.println("Error encountered on: " + driver.getCurrentUrl());
		File bugPic = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(bugPic, new File("./test-output/error-pics/"+methodError+".png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void pageWaitForThreeSec() throws InterruptedException {
		synchronized (driver) {
			driver.wait(3000);
		}
	}

}
