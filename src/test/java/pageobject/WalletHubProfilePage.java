package pageobject;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import junit.framework.Assert;
import util.CommonMethods;

public class WalletHubProfilePage extends CommonMethods{

	public WalletHubProfilePage(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//p[@class='feeddesc']")	
	public List<WebElement> listOfFeeds;
	
	@FindBy(xpath="//div[@class='profilenav']/ul/li/a/span[1]")
	public List<WebElement> profileTabs;
	
	@FindBy(xpath="//div[@class='reviews']/div/p")
	public List<WebElement> listOfReviews;
	
	 public void verifyReviewDisplayed(List<WebElement> listofFeedORReviews,String reviewString)
	 {
		
		 for(int i=0;i<listofFeedORReviews.size();i++)
		 {
			 System.out.println("Text of Review :"+listofFeedORReviews.get(i).getText()+"\n Length of Review: "+listofFeedORReviews.get(i).getText().length());
			 String reviewStringFromFeed=listofFeedORReviews.get(i).getText();
			 String stringWithoitSpaces=reviewStringFromFeed.replaceAll("\\s", "");
			 System.out.println("StringWithoutSpaces length:"+stringWithoitSpaces.length());
			if(stringWithoitSpaces.equals(reviewString))
			{
				System.out.println("Review Posted under Activity tab");
				return;
			}
		 }
		
		 Assert.fail("Review not present");
	 }

	public void clickOnTabsOnProfilePage(String username)
	{
		// TODO Auto-generated method stub
		String part1 ="https://wallethub.com/profile/"+username;
		String part2="/reviews/";
		driver.navigate().to(part1+part2);
		driver.findElement(By.linkText("Reviews")).click();
	
		
	}

}
