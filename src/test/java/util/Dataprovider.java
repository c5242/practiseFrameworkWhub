package util;

import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.annotations.DataProvider;

public class Dataprovider {
	
	@DataProvider(name="Usernamedata")
	public static Object[][] getDataproviderValues()
	{
		return new Object[][]{
				{"mashwini010@gmail.com","Job@1234"},
				{"mashwini010@gmail.com","Wrongpassword"}
		};
			
	}

}
