package walletHubPageObjects;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import pageobjects.facebook.FaceBookHomePage;
import util.CommonMethods;

public class LoginPage extends CommonMethods
{

	public LoginPage(WebDriver dr)
	{
		super(dr);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//input[@id='email']")
	public WebElement userName;

	@FindBy(id = "pass")
	public WebElement password;

	@FindBy(xpath = "//input[@type='submit']")
	public WebElement loginButton;

	@FindBy(xpath = "//div[@data-click='profile_icon']/a/span/span")
	public WebElement writeOnWall;

	@FindBy(xpath = "//br[@data-text='true']/ancestor::span/..")
	public WebElement writeOnWallPopUp;

	@FindBy(xpath = "//button[@data-testid='react-composer-post-button' and @type='submit']")
	public WebElement shareButton;

	@FindBy(xpath = "//div[@id='userNav']/ul/li/a/span")
	public WebElement nameLogo;

	@FindBy(xpath = "//span[contains(text(),'Logn')]")
	public WebElement loginB;

	@FindBy(xpath = "//input[@placeholder='Email Address']")
	public WebElement emailField;

	@FindBy(xpath = "//input[@placeholder='Password']")
	public WebElement passwordField;

	@FindBy(xpath = "//button[@type='button']/span")
	public WebElement buttonLogin;

	@FindBy(xpath = "//div[contains(@class,'brgm-list-box')]/span[contains(text(),'Ashwini')]")
	public WebElement userNameDisplay;




	// takes login credentials from properties file and passes to method
	public WalletHubHomePage loginWalletHub(String user, String pass) throws FileNotFoundException
		{
			readProperties();
			driver.get(prop.getProperty("URL"));
			waitForElementToLoad(loginB);
			clickOnWebElement(loginB);
			waitForElementToLoad(emailField).sendKeys(user);
			waitForElementToLoad(passwordField).sendKeys(pass);
			clickOnWebElement(buttonLogin);
			Assert.assertTrue(waitForElementToLoad(userNameDisplay).isDisplayed(), "Login Seems to be failed");
			return PageFactory.initElements(driver, WalletHubHomePage.class);

		}




	public FaceBookHomePage doLogin(String user, String pass) throws FileNotFoundException
		{
			readProperties();
			driver.get(prop.getProperty("FURL"));
			userName.sendKeys(user);
			password.sendKeys(pass);
			waitForElementToLoad(loginButton);
			clickOnWebElement(loginButton);
			Assert.assertEquals(nameLogo.isDisplayed(), true);
			waitForElementToLoad(writeOnWall);
			clickOnWebElement(writeOnWall);
			try
			{
				Thread.sleep(5000);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.findElement(By.xpath("//a[@aria-label='More Post Options']/div/i")).click();
			movetoElementOffSetUsingActions(writeOnWallPopUp, 699, 320);
			writeOnWallPopUp.sendKeys("Hello Friends");
			return PageFactory.initElements(driver, FaceBookHomePage.class);

		}

}
