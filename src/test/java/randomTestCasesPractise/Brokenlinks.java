/**
 * 
 */
package randomTestCasesPractise;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpConnection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import util.WTestBase;

/**
 * @author @AshwiniMore
 *
 */
public class Brokenlinks extends WTestBase
{
	/**
	 * Author@AshwiniMore
	 * 
	 * @throws FileNotFoundException
	 */

	String URL = "";
	int count = 0;




	@Test
	public void brokenLinks() throws IOException
		{
			readProperties();
			getDriver();
			driverSpecificOperations();
			driver.get("https://www.epfindia.gov.in/site_en/For_Employees.php");
			List<WebElement> listLinks = driver.findElements(By.tagName("a"));
			for (int i = 0; i < listLinks.size(); i++)
			{
				WebElement link = listLinks.get(i);
				String url = link.getAttribute("href");
				verifyBrokenUrls(url);

			}
			System.out.println("Counter is: " + count);
		}




	void verifyBrokenUrls(String URL) throws IOException
		{

			URL url = new URL(URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(2000);
			try
			{
				conn.connect();
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(conn.getResponseCode() == 200)
			{

			} else if(conn.getResponseCode() < 200 || conn.getResponseCode() > 200)
			{
				System.out.println("Broken link is: " + URL);
				count++;
			}
		}

}
