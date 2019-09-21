package testcases.wallethub;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageobjects.wallethub.LoginPage;
import pageobjects.wallethub.WalletHubHomePage;
import pageobjects.wallethub.WalletHubProfilePage;
import util.Dataprovider;
import util.WTestBase;

public class DataProviderTest_WalletHubLogin extends WTestBase{

	public LoginPage login;
	public WalletHubHomePage homePage;
	public WalletHubProfilePage profilePage;
	String reviewString;
	String TestCaseName;
	
	
	
	@BeforeTest
	public void configuration() throws FileNotFoundException
	{
		readProperties();
		getDriver();
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
	
	@AfterMethod ()
	public void ScreenShot(ITestResult it)
	{
		//we can have screenshot code for failed test cases
		System.out.println(TestCaseName+" Finished ");
	}
	
	@AfterClass
	public void driverClean() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.quit();
	}

		@Test(priority=0,dataProvider="Usernamedata",dataProviderClass=Dataprovider.class)
		public void login(String user,String pass) throws FileNotFoundException
		{
			login=PageFactory.initElements(driver, LoginPage.class);
			homePage=login.loginWalletHub(user,pass);			
		}
}