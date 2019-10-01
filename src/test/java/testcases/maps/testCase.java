package testcases.maps;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.loginext.Maps;
import pageobjects.loginext.driverNullException;
import pageobjects.wallethub.LoginPage;
import util.WTestBase;

public class testCase extends WTestBase {

	public Maps map;
	String TestCaseName;
	
	@BeforeClass
	public void configuration() throws Exception {
		setExtentReport(this.getClass().getSimpleName());

	}

	@BeforeMethod
	public void testbefore(Method method) {
		test = extent.createTest(method.getName());
		String tName = method.getName();
		String tClass = method.getDeclaringClass().getSimpleName();
		TestCaseName = tName + "-" + tClass;
		System.out.println(TestCaseName + " started...");
	}

	@AfterMethod()
	public void ScreenShot(ITestResult it) {
		// we can have screenshot code for failed test cases
		System.out.println(TestCaseName + " Finished ");
	}

	@AfterClass
	public void driverClean() throws InterruptedException {
		extent.flush();
		Thread.sleep(10000);
		driver.quit();
	}

	
	@Test
	public void mapTest() throws Exception
	{
		readProperties();
		getDriver();
		driverSpecificOperations();
		map = PageFactory.initElements(driver, Maps.class);
		map.gotoMaps();
	}
}
