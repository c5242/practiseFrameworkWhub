package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.w3c.dom.html.HTMLAppletElement;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class WTestBase implements WebDriverEventListener
{

	public WebDriver e_driver = null;
	public EventFiringWebDriver driver = null;
	public WTestBase base = null;
	public Properties prop = null;
	public static com.aventstack.extentreports.ExtentTest test;
	public static com.aventstack.extentreports.ExtentReports extent;
	public static ExtentHtmlReporter htmlReporter;




	/**
	 * to read config properties keys Author@AshwiniMore
	 */
	public Properties readProperties() throws FileNotFoundException
		{

			prop = new Properties();
			FileInputStream fileIn = new FileInputStream(Constants.CONFIGPROPERTIES);
			try
			{
				prop.load(fileIn);
				return prop;
			} catch (IOException e)
			{
				// TODO Auto-generated catch block

				System.out.println("File not present at path");
				e.printStackTrace();
			}
			return null;
		}




	// initiliaze driver according to driver type
	public void getDriver() throws FileNotFoundException
		{

			if(prop.getProperty("browser").equalsIgnoreCase("Chrome"))
			{
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "//test//resources//chromedriver.exe");
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--disable-notifications");
				e_driver = new ChromeDriver(chromeOptions);
				driver = new EventFiringWebDriver(e_driver);
				base = new WTestBase();
				driver.register(base);
			} else if(prop.getProperty("browser").equalsIgnoreCase("firefox"))
			{
				// we can have code for firefox as above like i have written for
				// chromdriver
			}

		}




	// driver specific code like settings wait and maximizing window
	public void driverSpecificOperations()
		{
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}




	String dateFormatter()
		{
			String pattern = "dd-M-yyyy";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			String Date = dateFormat.format(new Date());
			return Date;

		}




	public com.aventstack.extentreports.ExtentReports setExtentReport(String className) throws Exception
		{
			Properties configProperty = new Properties();
			File file = new File(Constants.CONFIGPROPERTIES);
			FileInputStream fileIn = new FileInputStream(file);
			String reportName = className + "_" + dateFormatter() + ".html";
			htmlReporter = new ExtentHtmlReporter(Constants.EXTENTREPORTS_FILE_PATH + File.separator + reportName);
			extent = new com.aventstack.extentreports.ExtentReports();
			extent.attachReporter(htmlReporter);
			// set name of report under config file
			configProperty.load(fileIn);
			configProperty.setProperty("ExtentReport_FileName", reportName);
			FileOutputStream fileOut = new FileOutputStream(file);
			configProperty.store(fileOut, "Written Extent report Name");
			// extent.createTest(className, "Started class Name test");
			return extent;

		}




	/*
	 * 
	 * 
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#
	 * afterAlertDismiss(org.openqa.selenium.WebDriver)
	 */
	public void afterAlertDismiss(WebDriver arg0)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore

		}




	/*
	 * 
	 * 
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#
	 * afterChangeValueOf(org.openqa.selenium.WebElement,
	 * org.openqa.selenium.WebDriver, java.lang.CharSequence[])
	 */
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore

		}




	/*
	 * 
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#afterClickOn(
	 * org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver)
	 */
	public void afterClickOn(WebElement arg0, WebDriver arg1)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore

		}




	/*
	 * 
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#afterFindBy(org
	 * .openqa.selenium.By, org.openqa.selenium.WebElement,
	 * org.openqa.selenium.WebDriver)
	 */
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore
			test.log(Status.INFO, "Element Found Successfully " + removeUnwantedPrefix(arg1.toString()));

		}




	/*
	 * 
	 * 
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#
	 * afterGetScreenshotAs(org.openqa.selenium.OutputType, java.lang.Object)
	 */
	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore

		}




	/*
	 * 
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#afterGetText(
	 * org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver,
	 * java.lang.String)
	 */
	public void afterGetText(WebElement arg0, WebDriver arg1, String arg2)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore
			test.log(Status.INFO, "Text has been extracted " + removeUnwantedPrefix(arg1.toString())+" Text is -> "+arg2);

		}




	/*
	 * 
	 * 
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#
	 * afterNavigateBack(org.openqa.selenium.WebDriver)
	 */
	public void afterNavigateBack(WebDriver arg0)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore

		}




	/*
	 * 
	 * 
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#
	 * afterNavigateForward(org.openqa.selenium.WebDriver)
	 */
	public void afterNavigateForward(WebDriver arg0)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore

		}




	/*
	 * 
	 * 
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#
	 * afterNavigateRefresh(org.openqa.selenium.WebDriver)
	 */
	public void afterNavigateRefresh(WebDriver arg0)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore

		}




	/*
	 * 
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#afterNavigateTo
	 * (java.lang.String, org.openqa.selenium.WebDriver)
	 */
	public void afterNavigateTo(String arg0, WebDriver arg1)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore
			test.log(Status.INFO, "Navigated Successfully to  "+arg1+" and "+arg0);

		}




	/*
	 * 
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#afterScript(
	 * java.lang.String, org.openqa.selenium.WebDriver)
	 */
	public void afterScript(String arg0, WebDriver arg1)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore

		}




	/*
	 * 
	 * 
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#
	 * afterSwitchToWindow(java.lang.String, org.openqa.selenium.WebDriver)
	 */
	public void afterSwitchToWindow(String arg0, WebDriver arg1)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore

		}




	/*
	 * 
	 * 
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#
	 * beforeAlertAccept(org.openqa.selenium.WebDriver)
	 */
	public void beforeAlertAccept(WebDriver arg0)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore

		}




	/*
	 * 
	 * 
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#
	 * beforeAlertDismiss(org.openqa.selenium.WebDriver)
	 */
	public void beforeAlertDismiss(WebDriver arg0)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore

		}




	/*
	 * 
	 * 
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#
	 * beforeChangeValueOf(org.openqa.selenium.WebElement,
	 * org.openqa.selenium.WebDriver, java.lang.CharSequence[])
	 */
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore

		}




	/*
	 * 
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#beforeClickOn(
	 * org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver)
	 */
	public void beforeClickOn(WebElement arg0, WebDriver arg1)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore

		}




	/*
	 * 
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#beforeFindBy(
	 * org.openqa.selenium.By, org.openqa.selenium.WebElement,
	 * org.openqa.selenium.WebDriver)
	 */
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore
			test.log(Status.INFO, "Trying to find "+removeUnwantedPrefix( arg2.toString() )+" element");

		}




	/*
	 * 
	 * 
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#
	 * beforeGetScreenshotAs(org.openqa.selenium.OutputType)
	 */
	public <X> void beforeGetScreenshotAs(OutputType<X> arg0)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore

		}




	/*
	 * 
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#beforeGetText(
	 * org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver)
	 */
	public void beforeGetText(WebElement arg0, WebDriver arg1)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore
			test.log(Status.INFO, "Trying to find text of Element "+removeUnwantedPrefix(arg0.toString()));

		}




	/*
	 * 
	 * 
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#
	 * beforeNavigateBack(org.openqa.selenium.WebDriver)
	 */
	public void beforeNavigateBack(WebDriver arg0)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore

		}




	/*
	 * 
	 * 
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#
	 * beforeNavigateForward(org.openqa.selenium.WebDriver)
	 */
	public void beforeNavigateForward(WebDriver arg0)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore

		}




	/*
	 * 
	 * 
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#
	 * beforeNavigateRefresh(org.openqa.selenium.WebDriver)
	 */
	public void beforeNavigateRefresh(WebDriver arg0)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore

		}




	/*
	 * 
	 * 
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#
	 * beforeNavigateTo(java.lang.String, org.openqa.selenium.WebDriver)
	 */
	public void beforeNavigateTo(String arg0, WebDriver arg1)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore
			test.log(Status.INFO, "Navigating to "+removeUnwantedPrefix(arg1.toString()));

		}




	/*
	 * 
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#beforeScript(
	 * java.lang.String, org.openqa.selenium.WebDriver)
	 */
	public void beforeScript(String arg0, WebDriver arg1)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore

		}




	/*
	 * 
	 * 
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#
	 * beforeSwitchToWindow(java.lang.String, org.openqa.selenium.WebDriver)
	 */
	public void beforeSwitchToWindow(String arg0, WebDriver arg1)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore

		}




	/*
	 * 
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#onException(
	 * java.lang.Throwable, org.openqa.selenium.WebDriver)
	 */

	public String removeUnwantedPrefix(String element)
		{
			String returnedString;
			if(!element.contains("->"))
				returnedString="";
			else
			{
			int index = element.indexOf("->");
			returnedString=element.substring(index + 1);
			}
			return returnedString;
		}




	public void onException(Throwable arg0, WebDriver arg1)
		{
			/*
			 * String exception = arg0.getMessage().toString().toLowerCase();
			 * System.out.println("*********"+exception+"******************");
			 * if(exception.contains("invalid_selector_exception")); {
			 * test.log(Status.FAIL, exception); }
			 */
			test.log(Status.FAIL, "***TEST FAILED WITH EXCEPTION *****"+"\n"+arg0.getMessage().toString().toLowerCase());

		}




	public void afterAlertAccept(WebDriver arg0)
		{
			// TODO Auto-generated method stub
			// Author@AshwiniMore

		}

}
