package xboxLiveTestFramework_Tests;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	int count = 0;

	public boolean retry(ITestResult arg0) {
		if(count < 1) {
			count++;
			return true; //Retry for flaky tests with unexpected variables 
		} else {
			return false; //Don't retry the failed test.
		}
		
	}
}
