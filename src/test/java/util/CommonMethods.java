package util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class CommonMethods extends WTestBase
	{

		public WebDriver driver;
		WebDriverWait wait;

		public CommonMethods(WebDriver dr)
			{
				driver = dr;
			}

		// generic method to use in every pageobject to wait for webelement to
		// load
		// on page
		public WebElement waitForElementToLoad(WebElement element)
			{
				WebDriverWait wait = new WebDriverWait(driver, 30);
				// try

				wait.until(ExpectedConditions.visibilityOf(element));
				// } catch (Exception e)
				/*
				 * { // TODO Auto-generated catch block System.out.println(
				 * "Element not visible: " + element); e.printStackTrace(); }
				 */
				return element;

			}

		/**
		 * gets the name of the running method
		 * 
		 * author @AshwiniMore
		 */
		public String getNameOftheRunningMethod(Class name)
			{
				return Class.class.getEnclosingClass().getName();

			}

		// generic method to use in every pageobject to click on webelement
		public void clickOnWebElement(WebElement element)
			{
				if (element.isDisplayed())
					try {
						element.click();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						try {
							Assert.fail("ELement not clickable: " + element);
						} catch (AssertionError e1) {
							test.log(Status.FAIL, "Element Not clikable");
						}
						e.printStackTrace();
					}

			}

		// move to element using Actions
		public void movetoElementUsingActions(WebElement elem)
			{
				Actions act = new Actions(driver);
				act.moveToElement(elem).build().perform();
			}

		// move to element using offset
		public void movetoElementOffSetUsingActions(WebElement elem,
				int xOffset, int yOffset)
			{
				Actions act = new Actions(driver);
				act.moveToElement(elem, xOffset, yOffset).build().perform();
			}
	}
