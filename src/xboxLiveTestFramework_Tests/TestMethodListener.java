package xboxLiveTestFramework_Tests;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class TestMethodListener implements IInvokedMethodListener {
  
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		ITestNGMethod m = method.getTestMethod();
		if(null == m.getRetryAnalyzer()) {
			m.setRetryAnalyzer(new RetryAnalyzer());
		}
	}

	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
