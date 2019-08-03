package util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class WTestBase extends AbstractWebDriverEventListener {
	
	public WebDriver e_driver=null;
	public EventFiringWebDriver driver=null;
	public WTestBase base=null;
	public FileInputStream fileIn=null;
	public Properties prop=null;
	
	public Properties init() throws FileNotFoundException
	{
		//to read config properties keys
		prop= new Properties();
		fileIn= new FileInputStream(System.getProperty("user.dir")+"\\test\\resources\\config.properties");
		try {
			prop.load(fileIn);
			return prop;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			System.out.println("File not present at path");
			e.printStackTrace();
		}
		return null;
	}
	
	//initiliaze driver according to driver type
	public void initDriver()
	{
		if(prop.getProperty("browser").equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//test//resources//chromedriver.exe");
			ChromeOptions chromeOptions= new ChromeOptions();
			chromeOptions.addArguments("--disable-notifications");
			e_driver=new ChromeDriver(chromeOptions);
			driver= new EventFiringWebDriver(e_driver);
			base= new WTestBase();
			driver.register(base);
		}else if(prop.getProperty("browser").equalsIgnoreCase("firefox"))
		{
			// we can have code for firefox as above like i have written for chromdriver
		}
		
	}
	
	//driver specific code like settings wait and maximizing window
	public void driverSpecificOperations()
	{
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

}
