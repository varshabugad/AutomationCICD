package Framework_Tutorial.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Framework_Tutorial.pageObjects.landingPage;
import Framework_Tutorial.resources.extentReporterNG;

public class Listeners extends baseTest implements ITestListener{
	ExtentReports extent = extentReporterNG.getReporterObject();
	ExtentTest test; 
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	public void OnTestStart(ITestResult result)
	{
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS, "Test is passed");
	}

	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getMethod().getMethodName());
		System.out.println("inside onTestFailure"+result.getMethod().getMethodName());
		//test.log(Status.FAIL, "Test is failed");
		extentTest.get().fail(result.getThrowable());
		try {
			driver =(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//if(driver==null)
	//lp.goTo();
	//initilizeDriver();
		String filepath=null;
		try {
			filepath= getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	}
	
	public void onFinish(ITestResult result)
	{
extent.flush();	}
}
