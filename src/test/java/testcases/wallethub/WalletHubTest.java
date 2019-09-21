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
import org.testng.annotations.Test;

import pageobjects.wallethub.LoginPage;
import pageobjects.wallethub.WalletHubHomePage;
import pageobjects.wallethub.WalletHubProfilePage;
import util.WTestBase;

public class WalletHubTest extends WTestBase{

	public LoginPage login;
	public WalletHubHomePage homePage;
	public WalletHubProfilePage profilePage;
	String reviewString;
	String TestCaseName;
	
	
	
	@BeforeClass
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
		Thread.sleep(10000);
		driver.quit();
	}
		@Test(priority=0)
		public void login() throws FileNotFoundException
		{
			login=PageFactory.initElements(driver, LoginPage.class);
			homePage=login.loginWalletHub(prop.getProperty("username"),prop.getProperty("password"));			
		}
		
		//@Test(priority=1)
		public void hoverOverStars_Verify() throws FileNotFoundException
		{
			homePage.hoverOverStarAndValidate();
			homePage.hoverOver5Star();
			
		}
		//@Test(priority=2)
		public void writeReviewAndSubmit() throws FileNotFoundException
		{
			homePage.selectReviewCategory(prop.getProperty("reviewCategory"));
			reviewString=homePage.writeReviewAndSubmit();
			
		}
		
		//@Test(priority=3)
		public void gotoProfileAndVerifyReview() throws FileNotFoundException
		{
			profilePage=homePage.gotoProfile();
			profilePage.verifyReviewDisplayed(profilePage.listOfFeeds,reviewString);
			profilePage.clickOnTabsOnProfilePage(prop.getProperty("walletHubUsername"));
			profilePage.verifyReviewDisplayed(profilePage.listOfReviews,reviewString);
			
		}

}
