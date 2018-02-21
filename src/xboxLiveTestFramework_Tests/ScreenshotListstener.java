package xboxLiveTestFramework_Tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
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
	
	//StackTrace Portion
	File stackCapture;
	PrintStream printCapture;
	
	//SubFolder Portion
	File testFailSubFolder;
	
	public File onTestFailFolderSubLog(Driver driver, ITestResult result) {
		methodName = result.getMethod().getMethodName();
		calendar = Calendar.getInstance();
		formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		this.testFailSubFolder = new File(driver.testFailDir + "/subFolder_" + methodName + "_" +formatter.format(calendar.getTime()));
		this.testFailSubFolder.mkdir();
		return this.testFailSubFolder;
	}
	
	//Working on Text file Capture for test failures
	public File onTestFailureStack(Driver driver, ITestResult result) throws UnsupportedEncodingException{
		
		methodName = result.getMethod().getMethodName();
		calendar = Calendar.getInstance();
		formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		this.stackCapture = new File (this.testFailSubFolder + "/" +
				methodName + "_" + formatter.format(calendar.getTime()) + ".txt");
		
		try {
			this.stackCapture.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//Work on printing error logs into txt file.
		Throwable th = result.getThrowable();
	
		try {
			printCapture = new PrintStream(this.stackCapture);
			th.printStackTrace(printCapture);
			printCapture.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
		return this.stackCapture;
	}
	
	public void onTestFailure(Driver driver, ITestResult result) {
		testFailSubFolder = onTestFailFolderSubLog(driver, result);
		try {
			stackCapture = onTestFailureStack(driver, result);
		} catch (UnsupportedEncodingException e1) {
			
			e1.printStackTrace();
		}
		
		calendar = Calendar.getInstance();
		methodName = result.getMethod().getMethodName();
		formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
			
		File bugPic = driver.bugCapture.getScreenshotAs(OutputType.FILE);
			
		try {
			Files.copy(bugPic, new File(this.testFailSubFolder + "/" + methodName + "_screenshot_" +
					formatter.format(calendar.getTime()) + ".png"));
			} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
