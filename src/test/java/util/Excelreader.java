package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Excelreader
{

String path="F://seleniumdatareader//Book1.xlsx";

//@Test(dataProvider="getdata1")
public void testdata(Map<String,String> listofMap)
{
	System.out.println("Device transfer: "+listofMap.get("Device Features"));
	}

@Test
public void test() throws IOException
{
	HashMap <String, List <String>> myMap=getListOfValuesInSingleMap("ListOFHeadPhones", path);
	System.out.println( myMap.keySet());
	}

/**
 * get whole data from sheet using sheetname
 * @return
 * @throws Exception 
 */
@DataProvider
Object[][] getdata1() throws Exception
{

	return usingList("TC03",path);
}



public Object[][] usingList(String sheetName,String path)throws Exception
{
		


	FileInputStream files = new FileInputStream(path);
	XSSFWorkbook workbook = new XSSFWorkbook(files);
	int index = workbook.getSheetIndex(sheetName);
	if (index == -1)
		throw new InvalidSheetNameException("Please check the sheet Name");

	XSSFSheet sheet = workbook.getSheet(sheetName);
	XSSFRow row = sheet.getRow(0);
	int rows= sheet.getLastRowNum();
	
	System.out.println("Total no of rows in sheet are :" + sheet.getLastRowNum()
	+ "\n\"Total no of Columns in sheet are :" + row.getLastCellNum());
	Object[][] test = new Object[row.getLastCellNum()-1][1];;
	;
	//List<Map<String,String>> listofMap=new ArrayList<Map<String,String>>();
	int k = 0;
	for ( int j = 1; j <row.getLastCellNum(); j++)
	{
		Map<String, String> myMap = new HashMap<String, String>();
		
		for ( int i = 1; i < rows +1; i++)
		{
			Row r =CellUtil.getRow(i, sheet);
			String key=CellUtil.getCell(r, 0).getStringCellValue();
			String Values=CellUtil.getCell(r, j).getStringCellValue();
			myMap.put(key, Values);
		}
	
		test[k][0] = myMap;
		k++;
	}
	return test;

	}
/**
 * get values using key name from sheet
 */

	HashMap<String, List<String>> getListOfValuesInSingleMap(String sheetName,String path) throws IOException
	{

		FileInputStream files = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(files);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		XSSFRow firstRow = null;
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();;
		String values;
		List<String> listOfValues= new ArrayList<String>();
		for ( int i = 1; i <= sheet.getLastRowNum(); i++)
		{	
			
		
				firstRow=sheet.getRow(i);
			
			for ( int j = 0; j < firstRow.getLastCellNum(); j++)
			{
				
				;
				String keyValue = sheet.getRow(0).getCell(j).getStringCellValue();
				if (firstRow.getCell(j).getCellType() == Cell.CELL_TYPE_NUMERIC)

					values = String.valueOf(firstRow.getCell(j).getNumericCellValue());
				else

					values = firstRow.getCell(j).getStringCellValue();

				System.out.println("Keys: " + keyValue + " Column values: " + values);
				listOfValues.add(values);
				map.put(keyValue, listOfValues);

			}
			

		}

		return map;
	}



}
