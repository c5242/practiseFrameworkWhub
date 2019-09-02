/**
 * 
 */
package testcases.amazon;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import util.WTestBase;

/**
 * @author mypc
 *
 */
public class AmzaonFiltersUsingDataDriven extends WTestBase
{
	/**
	 * Author@AshwiniMore
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public void Bclass() throws Exception
		{
			setExtentReport(this.getClass().getSimpleName().toString());
		}




	@BeforeMethod
	public void Bmethod(Method m) throws Exception
		{
			test = extent.createTest(m.getName());
			test.assignCategory(this.getClass().getSimpleName().toString());
		}




	@Test
	public void test(Method m) throws Exception
		{

			test.log(Status.INFO, "HIHI 123");
			test.log(Status.INFO, "HIHI 12344");
			test.pass("Passed " + m.getName());

		}




	@Test
	public void test1() throws Exception
		{

			test.log(Status.PASS, "HIHI");

		}




	@Test
	public void test2() throws Exception
		{

			test.log(Status.FAIL, "HIHI test 3");
			extent.flush();
		}
}
