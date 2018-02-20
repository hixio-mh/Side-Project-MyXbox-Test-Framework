package xboxLiveTestFramework_Tests;




import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class RetryAnalyzer extends BaseTest implements IRetryAnalyzer {
	int count = 0;
	private static int maxTry = 1;
	ScreenshotListstener picCapture = new ScreenshotListstener();

	public boolean retry(ITestResult arg0) {
		if(count < maxTry) {
			count++;
			return true; //Retry for flaky tests with unexpected variables 
		} else {
			
			picCapture.onTestFailure(driverThread.get(), arg0);
			return false; //Don't retry the failed test Capture Screenshot.
		}
	}
	
}
