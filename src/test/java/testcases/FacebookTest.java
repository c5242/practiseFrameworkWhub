package testcases;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobject.FaceBookHomePage;
import pageobject.LoginPage;
import util.WTestBase;

public class FacebookTest extends WTestBase {
	
	String TestCaseName;
	LoginPage login;
	
	@BeforeClass
	public void configuration() throws FileNotFoundException
	{
		init();
		initDriver();
		driverSpecificOperations();
	}
	@BeforeMethod
	public void testbefore(Method method)
	{
		String tName = method.getName();
		String tClass = method.getDeclaringClass().getSimpleName();
		TestCaseName= tName + "-" + tClass;
		System.out.println(TestCaseName+" started...");
	}
	
	@AfterMethod
	public void ScreenShot(ITestResult it)
	{
		//we can have screenshot code for failed test cases
		System.out.println(TestCaseName+" Finished ");
	}
	
	@AfterClass
	public void driverClean() throws InterruptedException
	{
		Thread.sleep(10000);
		driver.quit();
	}
		@Test
		public void login() throws FileNotFoundException
		{
			init();
			initDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			login=PageFactory.initElements(driver, LoginPage.class);
			login.doLogin(prop.getProperty("fusername"),prop.getProperty("fpassword"));
			
			
			
		}

}
