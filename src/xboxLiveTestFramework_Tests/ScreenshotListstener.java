package xboxLiveTestFramework_Tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.google.common.io.Files;

import xboxLivePageObjects_Lib.Driver;


public class ScreenshotListstener extends TestListenerAdapter {
	private String methodName;
	Calendar calendar;
	SimpleDateFormat formatter;
	FileWriter stackCapture;
	PrintWriter printCapture;
	
	//Working on Text file Capture for test failures
	public void onTestFailureStack(ITestResult result) throws IOException {
		
		methodName = result.getMethod().getMethodName();
		calendar = Calendar.getInstance();
		formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		stackCapture = new FileWriter (methodName + "_" + formatter.format(calendar) + ".txt" );
		printCapture = new PrintWriter(stackCapture);
		
		
	}
	
	public void onTestFailure(Driver driver, ITestResult result) {
		calendar = Calendar.getInstance();
		methodName = result.getMethod().getMethodName();
		formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
			
			File bugPic = driver.bugCapture.getScreenshotAs(OutputType.FILE);
			
			try {
				Files.copy(bugPic, new File("./target/surefire-reports/error-pics/"+ driver.testFailDir + "/" + methodName + "_" +formatter.format(calendar.getTime()) + ".png"));
			} catch(IOException e) {
				e.printStackTrace();
			}
	}
}
