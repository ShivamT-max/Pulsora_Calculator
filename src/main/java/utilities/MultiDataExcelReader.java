package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MultiDataExcelReader {
	static XSSFWorkbook book;
	static XSSFSheet sheet;

	public static ArrayList getTestData(String sheetName, String colName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(new File(Constants.Path_TestData));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = new XSSFWorkbook(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		int colNum = returnColNumber(sheet, colName);
		ArrayList<String> lstColumnValues = new ArrayList();
		// System.out.println("LastRow"+sheet.getLastRowNum());
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			String columnValue = sheet.getRow(i).getCell(colNum).toString();
			lstColumnValues.add(columnValue);
		}
		return lstColumnValues;
	}

	public static int returnColNumber(XSSFSheet sheet, String colName) {
		int colNum;
		int lastColumnNum = sheet.getRow(0).getLastCellNum();
		// System.out.println(lastColumnNum);
		for (colNum = 0; colNum < lastColumnNum; colNum++) {
			if (sheet.getRow(0).getCell(colNum).getStringCellValue().equals(colName)) {
				return colNum;
			}
		}
		return colNum;
	}
}
