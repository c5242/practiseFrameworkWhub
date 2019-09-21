/**
 * 
 */
package testcases.amazon.datadrivenTest;

import java.lang.reflect.Method;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pageobjects.amazon.LogintoAmzaon;
import util.WTestBase;

/**
 * @author mypc
 *
 */
public class AmzaonSelectFilters_DataDriven extends WTestBase
	{
		/**
		 * Author@AshwiniMore
		 * 
		 * @throws Exception
		 */
		LogintoAmzaon login;

		@BeforeClass
		public void Bclass() throws Exception
			{

				setExtentReport(this.getClass().getSimpleName().toString());
				readProperties();
				getDriver();
				driverSpecificOperations();

			}

		@AfterClass
		public void test2() throws Exception
			{

				extent.flush();
			}

		@BeforeMethod
		public void beforeMethod(Method m) throws Exception
			{

				test = extent.createTest(m.getName());
				test.log( Status.INFO , m.getName().toUpperCase()+" Started");
				test.assignCategory(this.getClass().getSimpleName().toString());
			}

		@AfterMethod
		public void afterMethod(Method m) throws Exception
			{
				test.log( Status.INFO , m.getName().toUpperCase() +" Finished");
				
			}
		@Test
		public void SampleTest1(Method m) throws Exception
			{

				login = PageFactory.initElements(driver, LogintoAmzaon.class);
				login.amzaonSampleTest();
			}

		@Test
		public void SampleTest2() throws Exception
			{

				test.log(Status.PASS, "test 2 Passed");

			}

	}
