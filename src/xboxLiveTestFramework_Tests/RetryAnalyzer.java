package xboxLiveTestFramework_Tests;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.google.common.io.Files;

public class RetryAnalyzer implements IRetryAnalyzer {
	int count = 0;
	private static int maxTry = 1;

	public boolean retry(ITestResult arg0) {
		if(count < maxTry) {
			count++;
			return true; //Retry for flaky tests with unexpected variables 
		} else {
			
			arg0.setStatus(ITestResult.FAILURE);
			return false; //Don't retry the failed test Capture Screenshot.
		}
	}
	
	public void extendReportsFailOperations (ITestResult arg0) {
		Object testClass = arg0.getInstance();
		WebDriver webDriver = ((BaseTest)testClass).driver;
		String methodName = arg0.getInstanceName();
		File bugPic = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(bugPic, new File("./surefire-reports/error-pics/"+ methodName + "_" +System.currentTimeMillis() + ".png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
