package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Data {
	XSSFSheet sheet = null;
	FileInputStream file = null;
	XSSFWorkbook workbook = null;
	List<List<Object>> data = null;
	int testCaseRow = -1;
	int testCaseDataRow = -1;

	public Data(String sheetName) {
		if (GlobalKeys.configData.get("TestDataType").equals(Constants.SigngleSetData)) {
			String path = GlobalKeys.configData.get("TestDataFile");
			this.data = Util.GetExcelTableInto2DArrayListString(path, sheetName);
		} else if (GlobalKeys.configData.get("TestDataType").equals(Constants.MultiSetData)) {
			String path = GlobalKeys.configData.get("MultiTestDataFile");
			this.data = Util.GetExcelTableInto2DArrayListString(path, sheetName);
		}
	}
	
//	public Data(String sheetName) {
//		String ApplicationEnvironment = GlobalKeys.configData.get("ApplicationEnvironment").toLowerCase();
//		String path= null;
//		if (GlobalKeys.configData.get("TestDataType").equals(Constants.SigngleSetData)) {
//			if(ApplicationEnvironment.equals("prod") || ApplicationEnvironment.equals("demo")) {
//				 path = GlobalKeys.configData.get("TestDataDemo_Prod");
//			}else if(ApplicationEnvironment.equals("preprod") || ApplicationEnvironment.equals("qa")){
//				 path = GlobalKeys.configData.get("TestDataPreProd_QA");
//			}else if(ApplicationEnvironment.equals("prod.eu")) {
//				 path = GlobalKeys.configData.get("TestDataProd.EU");
//			}else if(ApplicationEnvironment.equals("uat.eu")) {
//				 path = GlobalKeys.configData.get("TestDataUAT.EU");
//			}
//			this.data = Util.GetExcelTableInto2DArrayListString(path, sheetName);
//		}else if (GlobalKeys.configData.get("TestDataType").equals(Constants.MultiSetData)) {
//			if(ApplicationEnvironment.equals("prod") || ApplicationEnvironment.equals("demo")) {
//				 path = GlobalKeys.configData.get("MultipleTestDataDemo_Prod");
//			}else if( ApplicationEnvironment.equals("qa")){
//				 path = GlobalKeys.configData.get("MultipleTestDataQA");
//			}else if(ApplicationEnvironment.equals("preprod")) {
//				path = "./src/test/resources/TestData/MultipleData/MultipleTestDataPreProd.xlsx";
//			}
//		
//			else if(ApplicationEnvironment.equals("prod.eu")) {
//				 path = GlobalKeys.configData.get("MultipleTestDataProd.EU");
//			}else if(ApplicationEnvironment.equals("uat.eu")) {
//				 path = GlobalKeys.configData.get("MultipleTestDataUAT.EU");
//			}
//			this.data = Util.GetExcelTableInto2DArrayListString(path, sheetName);
//		}
//	}
//	
	
//	public Data(String sheetName) {
//		if (GlobalKeys.configData.get("TestDataType").equals(Constants.SigngleSetData) || Constants.funType.equals("CEF")) {
//			String path = GlobalKeys.configData.get("TestDataFile");
//			this.data = Util.GetExcelTableInto2DArrayListString(path, sheetName);
//		}else if (GlobalKeys.configData.get("TestDataType").equals(Constants.MultiSetData) && Constants.funType.equals("AR6")) {
//			String path = GlobalKeys.configData.get("MultiTestDataFileAR6");
//			this.data = Util.GetExcelTableInto2DArrayListString(path, sheetName);
//		}else if (GlobalKeys.configData.get("TestDataType").equals(Constants.MultiSetData)) {
//			String path = GlobalKeys.configData.get("MultiTestDataFile");
//			this.data = Util.GetExcelTableInto2DArrayListString(path, sheetName);
//		}
//	}

	public String get(String Label) {
		try {
			for (int i = 0; i < data.get(testCaseRow).size(); i++) {
				String colName = data.get(testCaseRow).get(i).toString();
				if (colName.equals(Label)) {
					return data.get(testCaseDataRow).get(i).toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	public Integer getNumericData(String Label) {
        try {
            for (int i = 0; i < data.get(testCaseRow).size(); i++) {
                String colName = data.get(testCaseRow).get(i).toString();
                if (colName.equals(Label)) {
                    int s1 = Integer.parseInt(data.get(testCaseDataRow).get(i).toString());
                    return s1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	public void setIndex(String testDataSet) {
		try {
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i).size() > 1) {
					if (data.get(i).get(1).toString().equals(testDataSet)) {
						this.testCaseDataRow = i;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setIndex_Multiple(String testDataSet) {
		try {
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i).size() > 1) {
					if (data.get(i).get(0).toString().equals(testDataSet)) {
						this.testCaseDataRow = i;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setColIndex(String testCaseName) {
		try {
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i).size() > 1) {
					if (data.get(i).get(1).toString().equals(testCaseName)) {
						this.testCaseRow = i;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getDataSets(String testCaseName) {
		ArrayList<String> dataSets = new ArrayList();
		try {
			GlobalKeys.elementLoadWaitTime = Integer.parseInt(GlobalKeys.configData.get("ElementLoadWaitTime"));
			GlobalKeys.implicitlyWaitTime = Integer.parseInt(GlobalKeys.configData.get("ImplicitlyWaitTime"));
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i).size() > 1) {
					if ((data.get(i).get(1).toString().contains(testCaseName))
							&& (data.get(i).get(2).toString().equalsIgnoreCase("yes"))) {
						dataSets.add(data.get(i).get(1).toString());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSets;
	}

	public ArrayList<String> getDataSets() {
		ArrayList<String> dataSets = new ArrayList();
		try {
			GlobalKeys.elementLoadWaitTime = Integer.parseInt(GlobalKeys.configData.get("ElementLoadWaitTime"));
			GlobalKeys.implicitlyWaitTime = Integer.parseInt(GlobalKeys.configData.get("ImplicitlyWaitTime"));
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i).size() > 1) {
					if (data.get(i).get(1).toString().equalsIgnoreCase("yes")) {
						dataSets.add(data.get(i).get(0).toString());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSets;
	}

	public void setColIndex() {
		try {
			this.testCaseRow = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void WriteUserEmailToConfigExcel(String UserEmail) {
		try {
			FileInputStream fs = new FileInputStream("./src/test/resources/TestData/TestConfiguration.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet Configsheet = workbook.getSheet("config");
			XSSFRow row;
			int lastRow = Configsheet.getLastRowNum();
			for (int i = 0; i <= lastRow; i++) {
				row = Configsheet.getRow(i);
				Cell cell = row.getCell(1);
				if (cell.getStringCellValue().equalsIgnoreCase("UserEmail")) {
					row.getCell(2).setCellValue(UserEmail);
					break;
				}
			}
			Map<String, Object[]> Configdata = new TreeMap<String, Object[]>();
			FileOutputStream out = new FileOutputStream(
					new File("./src/test/resources/TestData/TestConfiguration.xlsx"));
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
