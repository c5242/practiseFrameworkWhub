/**
 * 
 */
package pageobjects.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import util.CommonMethods;

/**
 * @author mypc
 *
 * 
 */
public class LogintoAmzaon extends CommonMethods
{
	/**
	 * 
	 * author @AshwiniMore
	 */
	public LogintoAmzaon(WebDriver dr)
	{
		super( dr );
		// TODO Auto-generated constructor stub
	}

	// get the searched string
	@FindBy(xpath = "//div[@class='sg-col-inner']/div[contains(@class,'spacing-top-small')]/span[3]")
	public WebElement getSearchedString;


	/**
	 * 
	 * author @AshwiniMore
	 */

	public void amzaonSampleTest()
		{

			/*
			 * driver.get(url);//https://www.amazon.in Assert.assertTrue(
			 * "String is not searched",
			 * getSearchedString.getText().equals(searchString));
			 */
			driver.get( "https://www.amazon.in" );
			driver.findElement( By.id( "twotabsearchtextbox" ) ).sendKeys( "mobile" );
			driver.findElement( By.id( "twotabsearchtextbox" ) ).sendKeys( Keys.ENTER );
			WebElement getText = driver.findElement(
			        By.xpath( "//div[@class='sg-col-inner']/div[contains(@class,'spacing-top-smal')]/span[3]" ) );
			System.out.println( getText.getText() );
			try
			{
				Assert.assertEquals( getText.getText() , "mobilew" , "Strings does not match" );
			}
			catch ( AssertionError e )
			{
				test.log( Status.FAIL , e.getMessage().toString() );
				Assert.fail( "Test Failed" );
				e.printStackTrace();
			}

		}
	
	public void searchOnAmazon(String url, String searchString)
	{
			
	}
}
