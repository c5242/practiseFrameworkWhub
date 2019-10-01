package pageobjects.loginext;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import util.CommonMethods;

public class Maps extends CommonMethods {

public Maps(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

@FindBy(xpath="//input[@aria-label='Search Google Maps']")
public WebElement searchBox;
	
	public void gotoMaps() throws Exception
	{
		if(driver==null)
				throw new driverNullException("Driver is not initliazed");
		driver.get("http://maps.google.com");
		searchBox.sendKeys("LogiNext powai");
		
	}

}
