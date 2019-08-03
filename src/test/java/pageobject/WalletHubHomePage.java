package pageobject;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;
import util.CommonMethods;

public class WalletHubHomePage extends CommonMethods{

	public WalletHubHomePage(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}
	
	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890   ";
	private static final int RANDOM_STRING_LENGTH = 201;
	
	//hover over 4th star
	@FindBy(xpath="//review-star[@class='rvs-svg']/div/*[name()='svg'][4]")
	public WebElement hoverOverFourthStar;
	
	//highlited stars
	@FindBy(xpath="//review-star[@class='rvs-svg']/div/*[name()='svg'][4]/*[name()='g'][1]/*[local-name()='path' and contains(@stroke,'4ae0e1')]")
	public WebElement verifyIfStarsHighlighted;
	
	//hover over fifth star
	@FindBy(xpath="//review-star[@class='rvs-svg']/div/*[name()='svg'][5]")
	public WebElement hoverOverFifthStar;
	
	//review title for verification
	@FindBy(xpath="//h4[contains(text(),'Test Insurance')]")
	public WebElement validateReviewTitle;
	
	//select dropdown
	@FindBy(xpath="//div[@class='dropdown second']/span[@class='dropdown-placeholder']")
	public WebElement selectDropdown;
	
	//list of all review categories from dropdwom
	@FindBy(xpath="//div[@class='dropdown second']/ul[@class='dropdown-list ng-enter-element']/li")
	public List<WebElement> listOfReviewCategories;
	//health category
	@FindBy(xpath="//span[@class='dropdown-selected']")
	public WebElement reviewCategoryHealth;
	//review section input box
	@FindBy(xpath="//textarea[@class='textarea wrev-user-input validate']")
	public WebElement writeReviewSection;
	//submit button on review page
	@FindBy(xpath="//div[contains(text(),'Submit')]")
	public WebElement submitButton;
	//successful review posted message
	@FindBy(xpath="//h4[contains(text(),'Your review has been posted')]")
	public WebElement successfullReviewMessage;
	//profile username, its generic for all users
	@FindBy(xpath="//div[@class='brgm-button brgm-user brgm-list-box']/span[@class='brgm-list-title']")
	public WebElement profileUserName;
	//profile button from menu
	@FindBy(xpath="//a[@class='brgm-list-it' and contains(text(),'Profile')]")
	public WebElement profileButton;
	//review text
	@FindBy(xpath="//div[@class='feed-content']/p[@class='feeddesc']")
	public WebElement getReviewText;
	//profile page validator
	@FindBy(xpath="//div[@class='flink followers']/span")
	public WebElement verifyMyprofileDisplayed;
	
	
	//hover over 4th star and verify if they are highlighted
	public void hoverOverStarAndValidate()
	{
		movetoElementUsingActions(hoverOverFourthStar);
		Assert.assertTrue("Stars are not highlighted",waitForElementToLoad(verifyIfStarsHighlighted).isDisplayed());
		
	}
	//clicks on 5th star
	public void hoverOver5Star()
	{
		movetoElementUsingActions(hoverOverFifthStar);
		clickOnWebElement(hoverOverFifthStar);
		Assert.assertEquals(validateReviewTitle.isDisplayed(), true);
		
	}
	//takes reviewcategory parameter from properties file and selects and verify if selected, this can be read from excel file if there is large data
	public void selectReviewCategory(String reviewcategory)
	{
		clickOnWebElement(selectDropdown);
		for(int i=0;i<listOfReviewCategories.size();i++)
		{
			if(listOfReviewCategories.get(i).getText().equals(reviewcategory))
			{
				System.out.println("Review category matched");
				clickOnWebElement(listOfReviewCategories.get(i));
				System.out.println("Selcted review category succesfully");
				break;
			}
		}
		
		Assert.assertTrue("Review Category not selected", waitForElementToLoad(reviewCategoryHealth).isDisplayed());
	}
	//writes review with 200 random charcaters and submit review , returns review string
	public String writeReviewAndSubmit()
	{
		String reviewString=generateRandomString();
		waitForElementToLoad(writeReviewSection).sendKeys(reviewString);
		clickOnWebElement(submitButton);
		Assert.assertTrue("Review not posted",waitForElementToLoad(successfullReviewMessage).isDisplayed());
		return reviewString.trim();
	}
	
	
	
	
	//creates random string
	 public String generateRandomString(){
         
	        StringBuffer randStr = new StringBuffer();
	        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
	            int number = getRandomNumber();
	            char ch = CHAR_LIST.charAt(number);
	            randStr.append(ch);
	        }
	        return randStr.toString();
	    }
	//create random number for string creation
	 int getRandomNumber()
	 {
		 Random r = new Random();
		int n = r.nextInt(60);
		return n;
	 }
	 //cliks on profile menu and returns oject of Profile page
	 public WalletHubProfilePage gotoProfile()
	 {
		 movetoElementUsingActions(profileUserName);
		 clickOnWebElement(profileButton);
		 Assert.assertTrue("Profile Page not displayed", waitForElementToLoad(verifyMyprofileDisplayed).isDisplayed());
		 return PageFactory.initElements(driver, WalletHubProfilePage.class);
	 }
	 
	
}
