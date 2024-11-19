package pages;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;
import utilities.Constants;
import utilities.Data;
import utilities.GMail;
import utilities.GlobalVariables;
import utilities.Util;

public class DataSourcePage extends TestBase {
	static XSSFWorkbook book;
	static XSSFSheet sheet;
	private MenuBarPage MenuBarPage;
	private GoalsPage GoalsPage;
	private HomePage HomePage;
	private CatalogPage CatalogsPage;
	private DataRequestPage DataRequestPage;
	private pages.SignInPage SignInPage;
	private pages.TasksPage TasksPage;

	protected DataSourcePage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	public CatalogPage returnCatalogPage() {
		return new CatalogPage(driver, data);
	}

	public MenuBarPage returnMenuPage() {
		return new MenuBarPage(driver, data);
	}

	@FindBy(xpath = "//article//*[contains(text(),'Data Source')]")
	private WebElement lblDataSource;

	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(lblDataSource);
			if (isElementPresent(lblDataSource)) {
				// passed("User Successfully Navigated To Data Source Page");
			} else {
				failed(driver, "Failed To Navigate To Data Source  Page");
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//p[text()='Excel']")
	private WebElement weExcel;
	@FindBy(xpath = "//p[text()='CSV']")
	private WebElement weCSV;
	@FindBy(xpath = "//p[text()='JSON']")
	private WebElement weJson;
	@FindBy(xpath = "//p[text()='Workday']")
	private WebElement weWorkday;

	public void selectFileType(String fileType) {
		switch (fileType) {
		case "EXCEL":
			clickOn(weExcel, "Excel");
			break;
		case "CSV":
			clickOn(weCSV, "CSV");
			break;
		case "JSON":
			clickOn(weJson, "JSON");
			break;
		case "Workday":
			clickOn(weWorkday, "Workday");
			break;
		}
	}

	@FindBy(xpath = "//div[@aria-label='Create Data Source']")
	private WebElement btnCreatDataSource;
	@FindBy(xpath = "//button[text()='Skip Now']")
	private WebElement btnSkipNow;
	@FindBy(xpath = "//input[@name='DataSourceName']")
	private WebElement txtDataSourceName;
	@FindBy(xpath = "//button[text()='Upload File']")
	private WebElement btnFileUpload;
	@FindBy(xpath = "//button[text()='Browse Files']")
	private WebElement btnBrowseFiles;
	@FindBy(xpath = "//button[text()='Create']")
	private WebElement BtnCreate;
	@FindBy(xpath = "//button[text()='Next']")
	private WebElement btnNext;
	@FindBy(xpath = "//div[contains(text(),'API')]")
	private WebElement weAPI;
	@FindBy(xpath = "//p[text()='File Manual Upload']")
	private WebElement weFileManualUpload;
	@FindBy(xpath = "//p[text()='FTP File Upload']")
	private WebElement weFTPFileUpload;
	@FindBy(xpath = "//*[text()='S3 Upload successfully triggered']")
	private WebElement weS3UploadSuccessfull;
	@FindBy(xpath = "//span[text()='Unique Key']")
	private WebElement weUniqueKey;
	@FindBy(xpath = "//span[text()='Attribute Names']")
	private WebElement weAttributeName;
	@FindBy(xpath = "//span[text()='Attribute Type']")
	private WebElement weAttributeType;
	@FindBy(xpath = "//button[text()='Ok']")
	private WebElement btnOk;

	public boolean CreatDataSource() throws AWTException, IOException {
		boolean blnCreated = false;
		try {
			waitForElement(btnCreatDataSource);
			clickOn(btnCreatDataSource, "creat Data source");
			clickOn(btnSkipNow, "Skip");
			verifyIfElementPresent(weAPI, "api", "Creat Data source");
			verifyIfElementPresent(weFileManualUpload, "file Manual Upload", "Creat Data source");
			verifyIfElementPresent(weFTPFileUpload, "FTP file upload", "Creat Data source");
			selectFileType("EXCEL");
			waitForElement(txtDataSourceName);
			String dataSourceName = "TestAuto" + generateRandomString(8);
			enterText(txtDataSourceName, "Data source name", dataSourceName);
			clickOn(btnFileUpload, "File upload");
			clickOn(btnBrowseFiles, "Browse files");
			String path = System.getProperty("user.dir");
			System.out.println(path);
			String filePath = path + "\\src\\test\\resources\\DataSourceFiles\\SompoHD.xlsx";
			ProcessBuilder pb = new ProcessBuilder(path + "\\src\\test\\resources\\DataSourceFiles\\fileUpload.exe",
					filePath);
			pb.start();
			waitForElement(weS3UploadSuccessfull);
			if (isElementPresent(weS3UploadSuccessfull)) {
				passed("Successfully file uploaded");
			} else {
				failed(driver, "File upload failed");
			}
			clickOn(btnNext, "Next");
			waitForElement(BtnCreate);
			verifyIfElementPresent(weUniqueKey, "uniqueKey", "Preview");
			verifyIfElementPresent(weAttributeName, "attributeName", "Preview");
			verifyIfElementPresent(weAttributeType, "attributeType", "Preview");
			clickOn(BtnCreate, "Create");
			// jsClick(BtnCreate, "Create");
			waitForElement(btnOk);
			clickOn(btnOk, "Ok");
			waitForElement(btnCreatDataSource);
			WebElement createdDataSource = driver.findElement(By.xpath("//*[text()='" + dataSourceName + "']"));
			if (isElementPresent(createdDataSource)) {
				passed("Successfully Datasource created");
				blnCreated = true;
			} else {
				failed(driver, "Failed to creat data source");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught in method CreatDataSource " + e.getMessage());
		}
		return blnCreated;
	}

	public boolean CreatDataSource(String filePath) {
		boolean blnCreated = false;
		try {
			waitForElement(btnCreatDataSource);
			clickOn(btnCreatDataSource, "creat Data source");
			clickOn(btnSkipNow, "Skip");
			verifyIfElementPresent(weAPI, "api", "Creat Data source");
			verifyIfElementPresent(weFileManualUpload, "file Manual Upload", "Creat Data source");
			verifyIfElementPresent(weFTPFileUpload, "FTP file upload", "Creat Data source");
			selectFileType("EXCEL");
			waitForElement(txtDataSourceName);
			String dataSourceName = "TestAuto" + generateRandomString(8);
			enterText(txtDataSourceName, "Data source name", dataSourceName);
			GlobalVariables.dataSourceName = dataSourceName;
			clickOn(btnFileUpload, "File upload");
			clickOn(btnBrowseFiles, "Browse files");
			String path = System.getProperty("user.dir");
			ProcessBuilder pb = new ProcessBuilder(path + "\\src\\test\\resources\\DataSourceFiles\\fileUpload.exe",
					filePath);
			Thread.sleep(8000);
			pb.start();
			Thread.sleep(8000);
			waitForElement(weS3UploadSuccessfull);
			if (isElementPresent(weS3UploadSuccessfull)) {
				passed("Successfully file uploaded");
			} else {
				failed(driver, "File upload failed");
			}
			clickOn(btnNext, "Next");
			waitForElement(BtnCreate);
			verifyIfElementPresent(weUniqueKey, "uniqueKey", "Preview");
			verifyIfElementPresent(weAttributeName, "attributeName", "Preview");
			verifyIfElementPresent(weAttributeType, "attributeType", "Preview");
			clickOn(BtnCreate, "Create");
			waitForElement(btnOk);
			clickOn(btnOk, "Ok");
			waitForElement(btnCreatDataSource);
			WebElement createdDataSource = driver.findElement(By.xpath("//*[text()='" + dataSourceName + "']"));
			if (isElementPresent(createdDataSource)) {
				passed("Successfully Datasource created");
				blnCreated = true;
			} else {
				failed(driver, "Failed to creat data source");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught in method CreatDataSource " + e.getMessage());
		}
		return blnCreated;
	}

	public boolean CreatDataSourceUsingJSON(String filePath) {
		boolean blnCreated = false;
		try {
			waitForElement(btnCreatDataSource);
			clickOn(btnCreatDataSource, "creat Data source");
			clickOn(btnSkipNow, "Skip");
			verifyIfElementPresent(weAPI, "api", "Creat Data source");
			verifyIfElementPresent(weFileManualUpload, "file Manual Upload", "Creat Data source");
			verifyIfElementPresent(weFTPFileUpload, "FTP file upload", "Creat Data source");
			selectFileType("JSON");
			waitForElement(txtDataSourceName);
			String dataSourceName = "TestAuto" + generateRandomString(8);
			enterText(txtDataSourceName, "Data source name", dataSourceName);
			GlobalVariables.dataSourceName = dataSourceName;
			clickOn(btnFileUpload, "File upload");
			clickOn(btnBrowseFiles, "Browse files");
			String path = System.getProperty("user.dir");
			ProcessBuilder pb = new ProcessBuilder(path + "\\src\\test\\resources\\DataSourceFiles\\fileUpload.exe",
					filePath);
			Thread.sleep(8000);
			pb.start();
			Thread.sleep(8000);
			waitForElement(weS3UploadSuccessfull);
			if (isElementPresent(weS3UploadSuccessfull)) {
				passed("Successfully file uploaded");
			} else {
				failed(driver, "File upload failed");
			}
			clickOn(btnNext, "Next");
			waitForElement(BtnCreate);
			verifyIfElementPresent(weUniqueKey, "uniqueKey", "Preview");
			verifyIfElementPresent(weAttributeName, "attributeName", "Preview");
			verifyIfElementPresent(weAttributeType, "attributeType", "Preview");
			clickOn(BtnCreate, "Create");
			// jsClick(BtnCreate, "Create");
			waitForElement(btnOk);
			clickOn(btnOk, "Ok");
			waitForElement(btnCreatDataSource);
			WebElement createdDataSource = driver.findElement(By.xpath("//*[text()='" + dataSourceName + "']"));
			if (isElementPresent(createdDataSource)) {
				passed("Successfully Datasource created");
				blnCreated = true;
			} else {
				failed(driver, "Failed to creat data source");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught in method CreatDataSource " + e.getMessage());
		}
		return blnCreated;
	}

	@FindBy(xpath = "//input[@name='UserName']")
	private WebElement txtUsernameDSWorkday;
	@FindBy(xpath = "//input[@name='Passwoed']")
	private WebElement txtPasswordDSWorkday;
	@FindBy(xpath = "//input[@name='refreshToken']")
	private WebElement txtRefreshTokenDSWorkday;
	@FindBy(xpath = "//input[@name='ApiTokenUrl']")
	private WebElement txtTokenendpointURLDSWorkday;
	@FindBy(xpath = "//input[@name='ApiUrl']")
	private WebElement txtURLDSWorkday;
	@FindBy(xpath = "//div[@id='DataSourceType']")
	private WebElement drpDataSourceTypeWQLQuery;
	@FindBy(xpath = "//p[text()='Workday - Workers']//parent::div//button")
	private WebElement btnPreview;
	@FindBy(xpath = "//p[text()='Workday - Supplier Invoices']//parent::div//button")
	private WebElement btnPreviewSupplier;
	@FindBy(xpath = "//*[@name='WqlQuery']")
	private WebElement txtWQLQuery;
	@FindBy(xpath = "//*[text()='CompensationGrade']")
	private WebElement lblCompenstationDS;

	public boolean CreatDataSourceUsingWorkday() {
		boolean blnCreated = false;
		try {
			waitForElement(btnCreatDataSource);
			clickOn(btnCreatDataSource, "creat Data source");
			clickOn(btnSkipNow, "Skip");
			verifyIfElementPresent(weAPI, "api", "Creat Data source");
			verifyIfElementPresent(weFileManualUpload, "file Manual Upload", "Creat Data source");
			verifyIfElementPresent(weFTPFileUpload, "FTP file upload", "Creat Data source");
			selectFileType("Workday");
			waitForElement(txtDataSourceName);
			String dataSourceName = "TestAuto" + generateRandomString(8);
			enterText(txtDataSourceName, "Data source name", dataSourceName);
			GlobalVariables.dataSourceName = dataSourceName;
			clickOn(btnNext, "Next");
			enterText(txtUsernameDSWorkday, "tUsername DS Workday", Constants.dsWorkDayUsername);
			enterText(txtPasswordDSWorkday, "Password DS Workday", Constants.dsWorkDayPassword);
			enterText(txtRefreshTokenDSWorkday, "Refresh Token DS Workday", Constants.dsWorkDayRefreshtoken);
			enterText(txtTokenendpointURLDSWorkday, "Token end point URL DS Workday",
					Constants.dsWorkDayTokenendpointURL);
			enterText(txtURLDSWorkday, "Data URL DS Workday", Constants.dsWorkDayURL);
			clickOn(btnNext, "Next");

	        clickOn(drpDataSourceTypeWQLQuery, "DropDown Data Source Type");
	        WebElement dataSourceType = driver.findElement(By.xpath("//li[text()='" + data.get("DataSource Type") + "']"));
	        		clickOn(dataSourceType, data.get("DataSource Type"));
                if(data.get("DS Target System Type").equals("Calculator")) {
			    waitForElement(txtWQLQuery);
			    sleep(500);
			    txtWQLQuery.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			    sleep(500);
			    txtWQLQuery.sendKeys(Keys.BACK_SPACE);
			    sleep(500);
			    enterText(txtWQLQuery, "Metric Value Data", Constants.WQLQueryDS);
				clickOn(btnNext, "Next");
				clickOn(btnPreviewSupplier, "btn Preview Data Workday Source");
			}else {
				clickOn(btnNext, "Next");
			clickOn(btnPreview, "btn Preview Data Workday Source");	
			}
			sleep(500);
//	        waitForElement(lblCompenstationDS);
			clickOn(drpDataSourceTypeWQLQuery, "DropDown Data Source Type");
			WebElement dataSourceType1 = driver
					.findElement(By.xpath("//li[text()='" + data.get("DataSource Type") + "']"));
			clickOn(dataSourceType1, data.get("DataSource Type"));
			clickOn(btnNext, "Next");
			clickOn(btnPreview, "btn Preview Data Workday Source");
			sleep(500);
			waitForElement(lblCompenstationDS);
			clickOn(BtnCreate, "Btn Create Data Source");
			waitForElement(btnOk);
			clickOn(btnOk, "Data Source Button Ok");
			WebElement createdDataSource = driver.findElement(By.xpath("//*[text()='" + dataSourceName + "']"));
			if (isElementPresent(createdDataSource)) {
				passed("Successfully Datasource created");
				blnCreated = true;
			} else {
				failed(driver, "Failed to creat data source");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught in method CreatDataSource " + e.getMessage());
		}
		return blnCreated;
	}

	// Without arguments
	public void fileUpload() {
		try {
			clickOn(btnFileUpload, "File upload");
			clickOn(btnBrowseFiles, "Browse files");
			String path = System.getProperty("user.dir");
			System.out.println(path);
			String filePath = path + "\\src\\test\\resources\\DataSourceFiles\\SompoHD.xlsx";
			ProcessBuilder pb = new ProcessBuilder(path + "\\src\\test\\resources\\DataSourceFiles\\fileUpload.exe",
					filePath);
			pb.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// with arguments
	public void fileUpload(String pathOfFileToUpload, String pathOfAutoItScript) {
		try {
			clickOn(btnFileUpload, "File upload");
			clickOn(btnBrowseFiles, "Browse files");
			String path = System.getProperty("user.dir");
			System.out.println(path);
			ProcessBuilder pb = new ProcessBuilder(pathOfAutoItScript, pathOfFileToUpload);
			pb.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//span[text()='Full Load']/preceding-sibling::span/input[@type='radio']")
	private WebElement rdbFullLoad;
	@FindBy(xpath = "//span[text()='Incremental Load']/preceding-sibling::span/input[@type='radio']")
	private WebElement rdbIncrementalLoad;
	@FindBy(xpath = "//label[text()='Continue']")
	private WebElement btnContinue;
	@FindBy(xpath = "//button[text()='Refresh']")
	private WebElement btnRefresh;
	@FindBy(xpath = "//*[@data-testid='CloseIcon']")
	private WebElement btnCloseDataHistory;
	private boolean lst4;

	public void verifySISECAMFullLoadDSInputMapAggSUM() {
		try {
			WebElement weViewDataSourceIcon;
			int initialDSHistoryCount = 0, actualDSHistoryCount = 0;
			;
			String path = System.getProperty("user.dir");
			System.out.println(path);
			String filePath = path + "\\src\\test\\resources\\DataSourceFiles\\SISECAM\\SISE_FILE1.xlsx";
			if (CreatDataSource(filePath)) {
				String dataSourceName;
				dataSourceName = GlobalVariables.dataSourceName;
				WebElement weDataSourceCard = driver
						.findElement(By.xpath("//span[@aria-label='" + dataSourceName + "']"));
				Actions builder = new Actions(driver);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weDataSourceCard);
				WebElement myElement = new WebDriverWait(driver, 20)
						.until(ExpectedConditions.visibilityOf(weDataSourceCard));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
				builder.moveToElement(weDataSourceCard).build().perform();
				initialDSHistoryCount = getViewDataSourceHistoryRowCount(dataSourceName);
				path = System.getProperty("user.dir");
				System.out.println(path);
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\SISECAM\\SISE_FILE2.xlsx";
				uploadDataSourceFile(dataSourceName, filePath);
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\SISECAM\\SISE_FILE3.xlsx";
				uploadDataSourceFile(dataSourceName, filePath);
				weViewDataSourceIcon = driver.findElement(By.xpath("//span[@aria-label='" + dataSourceName
						+ "']/ancestor::span/parent::div//*[@data-testid='VisibilityOutlinedIcon']"));
				clickOn(weViewDataSourceIcon, "View Data Source");
				WebElement weDataLoadHistoryPopUp = driver
						.findElement(By.xpath("//h2[text()='" + dataSourceName + "- Data Load History']"));
				verifyElementAndHighlightIfExists(weDataLoadHistoryPopUp, "Data Load History", "Data Load History");
				int r = 0;
				while (r != 30) {
					WebElement weStatus = driver.findElement(By.xpath(
							"//div[@ref='gridHeader']/following-sibling::div[@ref='eBodyViewport']//div[@role='rowgroup']/div[1]/div[@col-id='status']//descendant::span[4]"));
					String status = weStatus.getText();
					if (!status.trim().equals("COMPLETE")) {
						Thread.sleep(2000);
						r++;
						clickOn(btnRefresh, "Refresh");
					} else {
						break;
					}
					info("Clicked refresh button " + r);
				}
				clickOn(btnCloseDataHistory, "Close Data History");
				;
				actualDSHistoryCount = getViewDataSourceHistoryRowCount(dataSourceName);
				int currentCount = initialDSHistoryCount + 2;
				if (currentCount == actualDSHistoryCount) {
					passed("Data Source Histroy record is updated post uploading Data Source and record count is ==> "
							+ actualDSHistoryCount);
				} else {
					failed(driver,
							"Data Source Histroy record is not updated post uploading Data Source Current count is "
									+ actualDSHistoryCount);
				}
				validateInputMap();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//div[@aria-label='Calculate or Estimate GHG Emissions']//*")
	private WebElement btnMetricCalc;
	// div[text()='Calculate
	// Emissions']//parent::div//parent::div//*[@data-testid='RadioButtonUncheckedIcon']
	@FindBy(xpath = "//input[@name='row-radio-buttons-group']//following-sibling::span")
	private WebElement btncheckBoxCalc;
	@FindBy(xpath = "//div[text()='Calculate Emissions']")
	private WebElement lblCalculatorEmissions;
	@FindBy(xpath = "//button[text()='Start']")
	private WebElement bntStart;
	@FindBy(xpath = "//li[text()='Scope 2 - Indirect Emissions']")
	private WebElement lblScope2GHGCalc;
	@FindBy(xpath = "//button[text()='Generate']")
	private WebElement btnGenerate;
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement btnSaveCalc;
	@FindBy(xpath = "//button[text()='Confirm']")
	private WebElement btnConfirmCalc;
	String[] splittedValue;

	public void validateInputMap() {
		try {
			HashMap<String, String> metricDataMap = new HashMap<>();
			double num1, num2, num3, num4, num5, num6, num7;
			ArrayList<String> Lst = new ArrayList<>();
			HashSet<String> Lstsompo = new HashSet<>();
			int initialAuditRowCntForMetric1 = 0, initialAuditRowCntForMetric2 = 0, initialAuditRowCntForMetric3 = 0,
					currentAuditRowCntForMetric1 = 0, currentAuditRowCntForMetric2 = 0,
					currentAuditRowCntForMetric3 = 0;
			MenuBarPage = returnMenuPage();
			CatalogsPage = MenuBarPage.clickOnCatalogsMenu();
			String path = System.getProperty("user.dir");
			System.out.println(path);
			String filePath = null;
			ArrayList<String> lstMetricName = new ArrayList<>();
			ArrayList<String> lstTopicName = new ArrayList<>();
			String choice = data.get("InputMapPortcoName");
			switch (choice) {
			case "SISECAM":
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\SISECAM\\Sisecam_GHG_v1.py";
				CatalogsPage.searchAndSelectCatalogCard(data.get("CatalogName"));
				CatalogsPage.selectPeriodFromCatalogDetailsPage(data.get("Period"));
				initialAuditRowCntForMetric1 = CatalogsPage.getAuditLogRowCount(data.get("Metric1"), "Yes");
				initialAuditRowCntForMetric2 = CatalogsPage.getAuditLogRowCount(data.get("Metric2"), "No");
				GlobalVariables.inputMapName = "Test_DS_" + generateRandomString(3) + generateRandomNumber(3);
				lstMetricName.add(data.get("Metric1"));
				lstMetricName.add(data.get("Metric2"));
				CatalogsPage.createInputMap(GlobalVariables.inputMapName, GlobalVariables.dataSourceName,
						lstMetricName);
				CatalogsPage.excuteInputMap(GlobalVariables.inputMapName);
				CatalogsPage.verifyViewInputMapHistory(GlobalVariables.inputMapName);
				metricDataMap = CatalogsPage.getCatalogMetricsDataPostExecutionOfInputMap(lstMetricName);
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\SISECAM\\SISE_FILE3.xlsx";
				Lst = Util.getTestData(filePath, "Sheet1", "Scope 1 CO2 Emission Intensity (ton / gross production)");
				num1 = getSumOfList(Lst);
				if (metricDataMap.get("NUM1").contains("Please enter data here")
						|| metricDataMap.get("NUM2").contains("Please enter data here")) {
					failed(driver,
							"Metric data is not updated with values and current metric data for NUM1 value is ==> "
									+ metricDataMap.get("NUM1") + ", NUM2==> " + metricDataMap.get("NUM2"));
				}
				if (num1 == Double.parseDouble(metricDataMap.get("NUM1"))) {
					passed(data.get("Metric1") + " Metric value is matching as expected and value is ==> " + num1);
				} else {
					failed(driver, data.get("Metric1") + " Metric value is not matching as expected and value is ==> "
							+ num1 + " But Actual is " + Double.parseDouble(metricDataMap.get("NUM1")));
				}
				Lst = Util.getTestData(filePath, "Sheet1", "Scope 2 CO2 Emission");
				num2 = getSumOfList(Lst);
				if (num2 == Double.parseDouble(metricDataMap.get("NUM2"))) {
					passed(data.get("Metric2") + " Metric value is matching as expected and value is ==> " + num2);
				} else {
					failed(driver, data.get("Metric2") + " Metric value is not matching as expected and value is ==> "
							+ num2 + " But Actual is " + Double.parseDouble(metricDataMap.get("NUM2")));
				}
				currentAuditRowCntForMetric1 = CatalogsPage.getAuditLogRowCount(data.get("Metric1"), "No");
				currentAuditRowCntForMetric2 = CatalogsPage.getAuditLogRowCount(data.get("Metric2"), "No");
				if ((initialAuditRowCntForMetric1 + 1) == currentAuditRowCntForMetric1) {
					passed(data.get("Metric1") + " Metric is audited");
				} else {
					failed(driver, data.get("Metric1") + " Metric is not audited");
				}
				if ((initialAuditRowCntForMetric2 + 1) == currentAuditRowCntForMetric2) {
					passed(data.get("Metric2") + " Metric is audited");
				} else {
					failed(driver, data.get("Metric2") + " Metric is not audited");
				}
				break;
			case "SompoHD":
				CatalogsPage.searchAndSelectCatalogCard(data.get("CatalogName"));
				initialAuditRowCntForMetric1 = CatalogsPage.getAuditLogRowCount(data.get("Metric1"), "Yes");
				GlobalVariables.inputMapName = "Test_DS_" + generateRandomString(3) + generateRandomNumber(3);
				lstMetricName.add(data.get("Metric1"));
				CatalogsPage.createInputMap(GlobalVariables.inputMapName, GlobalVariables.dataSourceName,
						lstMetricName);
				CatalogsPage.excuteInputMap(GlobalVariables.inputMapName);
				CatalogsPage.verifyViewInputMapHistory(GlobalVariables.inputMapName);
				metricDataMap = CatalogsPage.getCatalogMetricsDataPostExecutionOfInputMap(lstMetricName);
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\SompoHD\\sompohd_File4.xlsx";
				Lstsompo = Util.getTestData1(filePath, "Sheet1", "consumption");
				num3 = getSumOfList1(Lstsompo);
				if (metricDataMap.get("NUM3-AVG").contains("Please enter data here")) {
					failed(driver,
							"Metric data is not updated with values and current metric data for NUM3-AVG value is ==> "
									+ metricDataMap.get("NUM3-AVG"));
				}
				if (num3 == Double.parseDouble(metricDataMap.get("NUM3-AVG"))) {
					passed(data.get("Metric1") + " Metric value is matching as expected and value is ==> " + num3);
				} else {
					failed(driver,
							data.get("Metric1") + " Metric value is not matching as expected and value is ==> " + num3);
				}
				currentAuditRowCntForMetric1 = CatalogsPage.getAuditLogRowCount(data.get("Metric1"), "No");
				if ((initialAuditRowCntForMetric1 + 1) == currentAuditRowCntForMetric1) {
					passed(data.get("Metric1") + " Metric is audited");
				} else {
					failed(driver, data.get("Metric1") + " Metric is not audited");
				}
				break;
			case "Scope1":
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\Scope1\\Scope1.py";
				CatalogsPage.searchAndSelectCatalogCard(data.get("CatalogName"));
				initialAuditRowCntForMetric1 = CatalogsPage.getAuditLogRowCount(data.get("Metric1"), "Yes");
				lstMetricName.add(data.get("Metric1"));
				GlobalVariables.inputMapName = "Test_DS_" + generateRandomString(3) + generateRandomNumber(3);
				CatalogsPage.createInputMap(GlobalVariables.inputMapName, GlobalVariables.dataSourceName,
						lstMetricName);
				CatalogsPage.excuteInputMap(GlobalVariables.inputMapName);
				CatalogsPage.verifyViewInputMapHistory(GlobalVariables.inputMapName);
				metricDataMap = CatalogsPage.getCatalogMetricsDataPostExecutionOfInputMap(lstMetricName);
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\Scope1\\SP1_File1.xlsx";
				ArrayList<String> Lst1 = new ArrayList<>();
				ArrayList<String> Lst2 = new ArrayList<>();
				ArrayList<String> Lst3 = new ArrayList<>();
				ArrayList<String> Lst4 = new ArrayList<>();
				ArrayList<Integer> ListDScope1 = new ArrayList<>();
				// Lst = Util.getTestData(filePath, "Sheet1", "EmissionFactor");
				Lst1 = Util.getTestData(filePath, "Sheet1", "EmissionFactor");
				Lst2 = Util.getTestData(filePath, "Sheet1", "MultiplicationFactor");
				Lst3 = Util.getTestData(filePath, "Sheet1", "Period to Date");
//				try {
				for (int i = 0; i < Lst1.size(); i++) {
					double numlist1 = Double.parseDouble(Lst1.get(i));
					double numlist2 = Double.parseDouble(Lst2.get(i));
					double numlist3 = Double.parseDouble(Lst3.get(i));
					double product = numlist1 * numlist2 * numlist3;
					Lst4.add(Double.toString(product));
				}
//				}catch (Exception e) {
//		         break;
//				}
				num4 = getSumOfList(Lst4);
				sleep(500);
				if (metricDataMap.get("NUM4").contains("Please enter data here")) {
					failed(driver,
							"Metric data is not updated with values and current metric data for NUM4 value is ==> "
									+ metricDataMap.get("NUM4"));
				}
				if (num4 == Double.parseDouble(metricDataMap.get("NUM4"))) {
					passed(data.get("Metric1") + " Metric value is matching as expected and value is ==> " + num4);
				} else {
					failed(driver,
							data.get("Metric1") + " Metric value is not matching as expected and value is ==> " + num4);
				}
				currentAuditRowCntForMetric1 = CatalogsPage.getAuditLogRowCount(data.get("Metric1"), "No");
				if ((initialAuditRowCntForMetric1 + 1) == currentAuditRowCntForMetric1) {
					passed(data.get("Metric1") + " Metric is audited");
				} else {
					failed(driver, data.get("Metric1") + " Metric is not audited");
				}
				break;
			case "EMP":
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\EMP\\EMP.py";
				CatalogsPage.searchAndSelectCatalogCard(data.get("CatalogName"));
				initialAuditRowCntForMetric1 = CatalogsPage.getAuditLogRowCount(data.get("Metric1"), "Yes");
				initialAuditRowCntForMetric2 = CatalogsPage.getAuditLogRowCount(data.get("Metric2"), "No");
				initialAuditRowCntForMetric3 = CatalogsPage.getAuditLogRowCount(data.get("Metric3"), "No");
				GlobalVariables.inputMapName = "Test_DS_" + generateRandomString(3) + generateRandomNumber(3);
				lstMetricName.add(data.get("Metric1"));
				lstMetricName.add(data.get("Metric2"));
				lstMetricName.add(data.get("Metric3"));
				CatalogsPage.createInputMap(GlobalVariables.inputMapName, GlobalVariables.dataSourceName,
						lstMetricName);
				CatalogsPage.excuteInputMap(GlobalVariables.inputMapName);
				CatalogsPage.verifyViewInputMapHistory(GlobalVariables.inputMapName);
				metricDataMap = CatalogsPage.getCatalogMetricsDataPostExecutionOfInputMap(lstMetricName);
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\EMP\\emp_File3.xlsx";
				Lst = Util.getTestData(filePath, "Sheet1", "Age");
				num5 = getMaxOfList(Lst);
				if (metricDataMap.get("NUM5").contains("Please enter data here")
						|| metricDataMap.get("NUM6").contains("Please enter data here")
						|| metricDataMap.get("NUM7").contains("Please enter data here")) {
					failed(driver,
							"Metric data is not updated with values and current metric data value for Num5 is ==> "
									+ metricDataMap.get("NUM5") + ", Num6 is ==> " + metricDataMap.get("NUM6")
									+ "Num47 is ==> " + metricDataMap.get("NUM7"));
				}
				if (num5 == Double.parseDouble(metricDataMap.get("NUM5"))) {
					passed(data.get("Metric1") + " Metric value is matching as expected and value is ==> " + num5);
				} else {
					// failed(driver,"value are not matching");
					failed(driver,
							data.get("Metric1") + " Metric value is not matching as expected and value is ==> " + num5);
				}
				Lst = Util.getTestData(filePath, "Sheet1", "Age");
				num6 = getMinOfList(Lst);
				if (num6 == Double.parseDouble(metricDataMap.get("NUM6"))) {
					passed(data.get("Metric2") + " Metric value is matching as expected and value is ==> " + num6);
				} else {
					failed(driver,
							data.get("Metric2") + " Metric value is not matching as expected and value is ==> " + num6);
				}
				Lst = Util.getTestData(filePath, "Sheet1", "Age");
				num7 = getAvgOfList(Lst);
				if (num7 == Double.parseDouble(metricDataMap.get("NUM7"))) {
					passed(data.get("Metric3") + " Metric value is matching as expected and value is ==> " + num7);
				} else {
					failed(driver,
							data.get("Metric3") + " Metric value is not matching as expected and value is ==> " + num7);
				}
				currentAuditRowCntForMetric1 = CatalogsPage.getAuditLogRowCount(data.get("Metric1"), "No");
				currentAuditRowCntForMetric2 = CatalogsPage.getAuditLogRowCount(data.get("Metric2"), "No");
				currentAuditRowCntForMetric3 = CatalogsPage.getAuditLogRowCount(data.get("Metric3"), "No");
				if ((initialAuditRowCntForMetric1 + 1) == currentAuditRowCntForMetric1) {
					passed(data.get("Metric1") + "Metric is audited");
				} else {
					failed(driver, data.get("Metric1") + "Metric is not audited");
				}
				if ((initialAuditRowCntForMetric2 + 1) == currentAuditRowCntForMetric2) {
					passed(data.get("Metric2") + "Metric is audited");
				} else {
					failed(driver, data.get("Metric2") + "Metric is not audited");
				}
				if ((initialAuditRowCntForMetric3 + 1) == currentAuditRowCntForMetric3) {
					passed(data.get("Metric3") + "Metric is audited");
				} else {
					failed(driver, data.get("Metric3") + "Metric is not audited");
				}
				break;
			case "JSON":
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\Json\\saudi_aramco_json_scrip_v2.py";
				CatalogsPage.searchAndSelectCatalogCard(data.get("CatalogName"));
				initialAuditRowCntForMetric1 = CatalogsPage.getAuditLogRowCount(data.get("Metric1"), "Yes");
				initialAuditRowCntForMetric2 = CatalogsPage.getAuditLogRowCount(data.get("Metric2"), "No");
				initialAuditRowCntForMetric3 = CatalogsPage.getAuditLogRowCount(data.get("Metric3"), "No");
				GlobalVariables.inputMapName = "Test_DS_" + generateRandomString(3) + generateRandomNumber(3);
				lstMetricName.add(data.get("Metric1"));
				lstMetricName.add(data.get("Metric2"));
				lstMetricName.add(data.get("Metric3"));
				CatalogsPage.createInputMap(GlobalVariables.inputMapName, GlobalVariables.dataSourceName,
						lstMetricName);
				CatalogsPage.excuteInputMap(GlobalVariables.inputMapName);
				CatalogsPage.verifyViewInputMapHistory(GlobalVariables.inputMapName);
				metricDataMap = CatalogsPage.getCatalogMetricsDataPostExecutionOfInputMap(lstMetricName);
				filePath = path + "\\src\\\\test\\resources\\DataSourceFiles\\Json\\saudi_aramco_json_scrip_v2.py";
				if (9 == Double.parseDouble(metricDataMap.get("NUM5"))) {
					passed(data.get("Metric1") + " Metric value is matching as expected and value is ==> " + 9);
				} else {
					// failed(driver,"value are not matching");
					failed(driver,
							data.get("Metric1") + " Metric value is not matching as expected and value is ==> " + 9);
				}
				if (6 == Double.parseDouble(metricDataMap.get("NUM6"))) {
					passed(data.get("Metric2") + " Metric value is matching as expected and value is ==> " + 6);
				} else {
					// failed(driver,"value are not matching");
					failed(driver,
							data.get("Metric2") + " Metric value is not matching as expected and value is ==> " + 6);
				}
				if (8 == Double.parseDouble(metricDataMap.get("NUM7"))) {
					passed(data.get("Metric3") + " Metric value is matching as expected and value is ==> " + 8);
				} else {
					// failed(driver,"value are not matching");
					failed(driver,
							data.get("Metric3") + " Metric value is not matching as expected and value is ==> " + 8);
				}
				break;
			case "Workday":
				if (data.get("DS Target System Type").equals("Catalog")) {
					filePath = path + "\\src\\test\\resources\\DataSourceFiles\\Workday\\WorkerPythonScript.py";
				} else if (data.get("DS Target System Type").equals("Calculator")) {
					filePath = path
							+ "\\src\\test\\resources\\DataSourceFiles\\Workday\\CalculatorPythonScript_2023_4_24.py";
				}
				GlobalVariables.inputMapName = "Test_DS_" + generateRandomString(3) + generateRandomNumber(3);
				CatalogsPage.navigateToCatalogPage();
				CatalogsPage.searchAndSelectCatalogCard(data.get("CatalogName"));
				CatalogsPage.selectPeriodFromCatalogDetailsPage(data.get("Period"));
				if(data.get("DS Target System Type").equals("Catalog")) {
				lstTopicName.add(data.get("Topic1"));
				lstTopicName.add(data.get("Topic2"));
				lstTopicName.add(data.get("Topic3"));
				}else if(data.get("DS Target System Type").equals("Calculator")) {
				if (data.get("DS Target System Type").equals("Catalog")) {
					lstTopicName.add(data.get("Topic1"));
					lstTopicName.add(data.get("Topic2"));
					lstTopicName.add(data.get("Topic3"));
				} else if (data.get("DS Target System Type").equals("Calculator")) {
					lstTopicName.add(data.get("Topic1"));
				}
				CatalogsPage.createInputMapWorkday(GlobalVariables.inputMapName, GlobalVariables.dataSourceName,
						lstTopicName);
				CatalogsPage.excuteInputMap(GlobalVariables.inputMapName);
				CatalogsPage.verifyViewInputMapHistory(GlobalVariables.inputMapName);
				CatalogsPage.navigateToCatalogPage();
				CatalogsPage.searchAndSelectCatalogCard(data.get("CatalogName"));

				CatalogsPage.selectPeriodFromCatalogDetailsPage(data.get("Period"));
				for (String topicNameList : lstTopicName) {
//					CatalogsPage.expandTopicInCatalog(topicNameList);
					((JavascriptExecutor) driver)
							.executeScript("document.querySelector('div[ref=eBodyViewport]').scrollTop=2000");
					String xpathEcatalogName = "//span/span[text()='" + topicNameList
							+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
					WebElement weExpand = driver.findElement(By.xpath(xpathEcatalogName));
					String classExpand = weExpand.getAttribute("class");
					if (!classExpand.contains("hidden")) {
						clickOnElementWithDynamicXpath(xpathEcatalogName, "Expand Data requestLink");
					}
				}
				if (data.get("DS Target System Type").equals("Catalog")) {
//				String[] metricDataExpextedValues = {"37","Singapore,France,Finland,Belgium,United States of America,Netherlands,Australia,Germany,South Africa,India,None,Spain,Hong Kong,Canada,Norway,Sweden,United Kingdom,Denmark,Czechia,Japan,Malaysia,Thailand,Mexico,Poland,New Zealand,Austria,Korea, Republic of,China,Luxembourg,Brazil,Ireland,Philippines,United Arab Emirates,Switzerland,Russian Federation,Indonesia,Italy","1","110","13"};
					String[] metricDataValues = { "Number of countries of operations", "Location of operations",
							"Total number of employees by region: Asia", "Total number of employees by region: Canada",
							"Total number of employees by region: EMEA (Excluding Poland)",
							"Total number of employees by region: India",
							"Total number of employees by region: Latin America",
							"Total number of employees by region: Poland", "Total number of employees by region: U.S.",
							"Total number of employees by region:",
							"Total number of employees by employment type by gender: Female-Full time",
							"Total number of employees by employment type by gender: Female- Part time",
							"Total number of employees by employment type by gender: Female- Grand total",
							"Total number of employees by employment type by gender: Male-Full time",
							"Total number of employees by employment type by gender: Male- Part time",
							"Total number of employees by employment type by gender: Male - Grand total",
							"Total number of employees by employment type by gender: Unknown-Full time",
							"Total number of employees by employment type by gender: Unknown- Part time",
							"Total number of employees by employment type - Full time",
							"Total number of employees by employment type - Part time",
							"Total number of employees by employment type - Grand total",
							"Total number of employees by employment type by gender: Unknown- Grand total",
							"Percentage of gender and racial/ethnic group representation for Board of Directors-Global Female Ratio",
							"Percentage of gender and racial/ethnic group representation for Board of Directors-Global male Ratio",
							"Percentage of gender and racial/ethnic group representation for Board of Directors- U.S. Minority Ratio",
							"Percentage of gender and racial/ethnic group representation for All Employees-Global Female Ratio",
							"Percentage of gender and racial/ethnic group representation for All Employees-Global male Ratio",
							"Percentage of gender and racial/ethnic group representation for All Employees-U.S. Minority Ratio",
							"Percentage of gender and racial/ethnic group representation for Senior Management-Global Female Ratio",
							"Percentage of gender and racial/ethnic group representation for Senior Management-U.S. Minority Ratio",
							"Percentage of gender and racial/ethnic group representation for Senior Management-Global Male Ratio",
							"Percentage of gender and racial/ethnic group representation for Management-Global Female Ratio",
							"Percentage of gender and racial/ethnic group representation for Management-Global male Ratio",
							"Percentage of gender and racial/ethnic group representation for Management-U.S. Minority Ratio",
							"Percentage of gender and racial/ethnic group representation for Professionals-Global Female Ratio",
							"Percentage of gender and racial/ethnic group representation for Professionals-Global male Ratio",
							"Percentage of gender and racial/ethnic group representation for Professionals-U.S. Minority Ratio" };
					((JavascriptExecutor) driver)
							.executeScript("document.querySelector('div[ref=eBodyViewport]').scrollTop=-2000");
					for (int j = 0; j < metricDataValues.length; j++) {
						WebElement wemetricValue = driver.findElement(By.xpath("(//span[text()='" + metricDataValues[j]
								+ "']//following::div[@col-id='metricValue']//span)[1]"));
						Thread.sleep(500);
						Actions act = new Actions(driver);
						Thread.sleep(500);
						act.moveToElement(wemetricValue).perform();
						if (!wemetricValue.getText().trim().isEmpty()) {
							passed("Successfully Validated Updated DS Metric Value  In metic Value Details As"
									+ wemetricValue.getText());
						} else {
							System.out.println(wemetricValue.getText());
							failed(driver, "Failed To validate Updated DS Metric Value  In metic Value But Actual is"
									+ wemetricValue.getText());
						}
					}
				} else if (data.get("DS Target System Type").equals("Calculator")) {
					clickOn(btnMetricCalc, "Calculator Button Metric Value");
					waitForElement(lblCalculatorEmissions);
//					jsClick(btncheckBoxCalc, "Calculator Type Data Source");
					clickOn(lblCalculatorEmissions, "Calculator Type Data Source");
					clickOn(bntStart, "Button Start DS Calc");
					waitForElement(lblScope2GHGCalc);
					sleep(3000);
					clickOn(btnGenerate, "generate button");
					sleep(8000);
					WebElement totalco2Value = driver
							.findElement(By.xpath("//span[contains(text(),'Location Based Emissions(tCO2e)')]"));
					String beforeGertValue = totalco2Value.getText();
					splittedValue = beforeGertValue.split("-");
					GlobalVariables.ghgCalcValueDataSource = Double.parseDouble(splittedValue[1]);
					System.out.println(GlobalVariables.ghgCalcValueDataSource);
					System.out.println(Arrays.toString(splittedValue));
					waitForElement(btnSaveCalc);
					clickOn(btnSaveCalc, "Button Save GHG Scope Calculator");
					clickOn(btnConfirmCalc, "Button Confirm Calc Data Source Value");
					sleep(1000);
					String xpathEcatalogName = "//span/span[text()='" + data.get("TopicName")
							+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
					WebElement weExpand = driver.findElement(By.xpath(xpathEcatalogName));
					String classExpand = weExpand.getAttribute("class");
					if (!classExpand.contains("hidden")) {
						clickOnElementWithDynamicXpath(xpathEcatalogName, "Expand Data requestLink");
					}
					String[] metricDataValues = { "SP2_DS_CALC" };
					for (int j = 0; j < metricDataValues.length; j++) {
						WebElement wemetricValue = driver.findElement(By.xpath("(//span[text()='" + metricDataValues[j]
								+ "']//following::div[@col-id='metricValue']//span)[1]"));
						Thread.sleep(500);
						if (wemetricValue.getText().trim().equals(GlobalVariables.ghgCalcValueDataSource)) {
							passed("Successfully Validated DS Metric Value Updated as GHG Calculator Value As"
									+ wemetricValue.getText());
						} else {
							System.out.println("Actual Metruc Value is" + wemetricValue.getText());
							failed(driver,
									"Failed To validate DS Metric Value Updated as GHG Calculator Value Expected as"
											+ GlobalVariables.ghgCalcValueDataSource + " But Actual is"
											+ wemetricValue.getText());
						}
					}
				}
				break;
			}
			/*
			 * 
			 * CatalogsPage.searchAndSelectCatalogCard(data.get("CatalogName"));
			 * initialAuditRowCntForMetric1 =
			 * CatalogsPage.getAuditLogRowCount(data.get("Metric1"),"Yes");
			 * initialAuditRowCntForMetric2 =
			 * CatalogsPage.getAuditLogRowCount(data.get("Metric2"),"No");
			 * 
			 * GlobalVariables.inputMapName = "Test_DS_" + generateRandomString(3) +
			 * generateRandomNumber(3);
			 * CatalogsPage.createInputMap(GlobalVariables.inputMapName,GlobalVariables.
			 * dataSourceName); CatalogsPage.excuteInputMap(GlobalVariables.inputMapName);
			 * CatalogsPage.verifyViewInputMapHistory(GlobalVariables.inputMapName);
			 * CatalogsPage.validateCatalogMetricsPostExecutionOfInputMap();
			 * 
			 * currentAuditRowCntForMetric1 =
			 * CatalogsPage.getAuditLogRowCount(data.get("Metric1"),"No");
			 * currentAuditRowCntForMetric2 =
			 * CatalogsPage.getAuditLogRowCount(data.get("Metric2"),"No");
			 */
			// TODO: validation for values
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ArrayList<String> multiply(ArrayList<String> lst1, ArrayList<String> lst2, ArrayList<String> lst3) {
		// TODO Auto-generated method stub
		return null;
	}

	public void uploadDataSourceFile(String dataSourceName, String filePath) {
		try {
			WebElement weDataSourceCard = driver.findElement(By.xpath("//span[@aria-label='" + dataSourceName + "']"));
			Actions builder = new Actions(driver);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weDataSourceCard);
			WebElement myElement = new WebDriverWait(driver, 20)
					.until(ExpectedConditions.visibilityOf(weDataSourceCard));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
			builder.moveToElement(weDataSourceCard).build().perform();
			WebElement weUploadDataSource = driver.findElement(By.xpath("//span[@aria-label='" + dataSourceName
					+ "']/ancestor::span/parent::div//*[@data-testid='FileUploadOutlinedIcon']"));
			clickOn(weUploadDataSource, "Upload Data Source");
			if (data.get("Load Type").equals("Full Load")) {
				clickOn(rdbFullLoad, "Full Load");
			} else if (data.get("Load Type").equals("Incremental Load")) {
				clickOn(rdbIncrementalLoad, "Incremental Load");
			}
			clickOn(btnContinue, "Continue");
			// File 2
			String path = System.getProperty("user.dir");
			System.out.println(path);
			info("Uploading file==> " + filePath);
			ProcessBuilder pb = new ProcessBuilder(path + "\\src\\test\\resources\\DataSourceFiles\\fileUpload.exe",
					filePath);
			Thread.sleep(9000);
			pb.start();
			Thread.sleep(10000);
			info("Uploaded file==> " + filePath);
			waitForElement(weS3UploadSuccessfull);
			if (isElementPresent(weS3UploadSuccessfull)) {
				passed("Successfully file uploaded");
			} else {
				failed(driver, "File upload failed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getViewDataSourceHistoryRowCount(String dataSourceName) {
		int rowCnt = 0;
		try {
			WebElement weViewDataSourceIcon = driver.findElement(By.xpath("//span[@aria-label='" + dataSourceName
					+ "']/ancestor::span/parent::div//*[@data-testid='VisibilityOutlinedIcon']"));
			clickOn(weViewDataSourceIcon, "View Data Source");
			WebElement weDataLoadHistoryPopUp = driver
					.findElement(By.xpath("//h2[text()='" + dataSourceName + "- Data Load History']"));
			verifyElementAndHighlightIfExists(weDataLoadHistoryPopUp, "Data Load History", "Data Load History");
			List<WebElement> lstRows = driver.findElements(By.xpath(
					"//div[@ref='gridHeader']/following-sibling::div[@ref='eBodyViewport']//div[@role='rowgroup']/div"));
			rowCnt = lstRows.size();
			Thread.sleep(2000);
			clickOn(btnCloseDataHistory, "Close Data History");
			;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCnt;
	}

	public boolean verifyElementAndHighlightIfExists(WebElement element, String elemName, String pageName) {
		boolean blnFnd = false;
		try {
			if (isElementPresent(element)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
				passed(elemName + "Element is displayed in " + pageName + " page");
				blnFnd = true;
			} else {
				failed(driver, elemName + "Element is not displayed in " + pageName + " page");
			}
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
		return blnFnd;
	}

	public void verifySompoHDIncrementalLoadDSInputMapAggAvg() {
		try {
			WebElement weViewDataSourceIcon;
			int initialDSHistoryCount = 0, actualDSHistoryCount = 0;
			;
			String path = System.getProperty("user.dir");
			System.out.println(path);
			// String filePath =
			// path+"\\src\\test\\resources\\DataSourceFiles\\SompoHD\\sompohd_File1.xlsx";
			String filePath = path + "\\src\\test\\resources\\DataSourceFiles\\SompoHD\\sompohd_File4.xlsx";
			if (CreatDataSource(filePath)) {
				String dataSourceName;
				dataSourceName = GlobalVariables.dataSourceName;
				WebElement weDataSourceCard = driver
						.findElement(By.xpath("//span[@aria-label='" + dataSourceName + "']"));
				Actions builder = new Actions(driver);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weDataSourceCard);
				WebElement myElement = new WebDriverWait(driver, 20)
						.until(ExpectedConditions.visibilityOf(weDataSourceCard));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
				builder.moveToElement(weDataSourceCard).build().perform();
				initialDSHistoryCount = getViewDataSourceHistoryRowCount(dataSourceName);
				path = System.getProperty("user.dir");
				System.out.println(path);
//				filePath = path+"\\src\\test\\resources\\DataSourceFiles\\SompoHD\\sompohd_File2.xlsx";
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\SompoHD\\sompohd_File4.xlsx";
				uploadDataSourceFile(dataSourceName, filePath);
//				filePath = path+"\\src\\test\\resources\\DataSourceFiles\\SompoHD\\sompohd_File4.xlsx";
//				uploadDataSourceFile(dataSourceName,filePath);
//				
//				filePath = path+"\\src\\test\\resources\\DataSourceFiles\\SompoHD\\sompohd_File4.xlsx";
//				uploadDataSourceFile(dataSourceName,filePath);
				weViewDataSourceIcon = driver.findElement(By.xpath("//span[@aria-label='" + dataSourceName
						+ "']/ancestor::span/parent::div//*[@data-testid='VisibilityOutlinedIcon']"));
				clickOn(weViewDataSourceIcon, "View Data Source");
				WebElement weDataLoadHistoryPopUp = driver
						.findElement(By.xpath("//h2[text()='" + dataSourceName + "- Data Load History']"));
				verifyElementAndHighlightIfExists(weDataLoadHistoryPopUp, "Data Load History", "Data Load History");
				int r = 0;
				while (r != 10) {
					WebElement weStatus = driver.findElement(By.xpath(
							"//div[@ref='gridHeader']/following-sibling::div[@ref='eBodyViewport']//div[@role='rowgroup']/div[1]/div[@col-id='status']//descendant::span[4]"));
					String status = weStatus.getText();
					if (!status.trim().equals("COMPLETE")) {
						Thread.sleep(2000);
						r++;
					} else {
						break;
					}
					clickOn(btnRefresh, "Refresh");
				}
				clickOn(btnCloseDataHistory, "Close Data History");
				;
				actualDSHistoryCount = getViewDataSourceHistoryRowCount(dataSourceName);
				// int currentCount=initialDSHistoryCount+4;
				int currentCount = initialDSHistoryCount + 1;
				if (currentCount == actualDSHistoryCount) {
					passed("Data Source Histroy record is updated post uploading Data Source and record count is ==> "
							+ actualDSHistoryCount);
				} else {
					failed(driver,
							"Data Source Histroy record is not updated post uploading Data Source Current count is "
									+ actualDSHistoryCount);
				}
				validateInputMap();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyScope1IncrementalLoadDSInputMapAggMedian() {
		try {
			WebElement weViewDataSourceIcon;
			int initialDSHistoryCount = 0, actualDSHistoryCount = 0;
			;
			String path = System.getProperty("user.dir");
			System.out.println(path);
			String filePath = path + "\\src\\test\\resources\\DataSourceFiles\\Scope1\\SP1_File1.xlsx";
			if (CreatDataSource(filePath)) {
				String dataSourceName;
				dataSourceName = GlobalVariables.dataSourceName;
				WebElement weDataSourceCard = driver
						.findElement(By.xpath("//span[@aria-label='" + dataSourceName + "']"));
				Actions builder = new Actions(driver);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weDataSourceCard);
				WebElement myElement = new WebDriverWait(driver, 20)
						.until(ExpectedConditions.visibilityOf(weDataSourceCard));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
				builder.moveToElement(weDataSourceCard).build().perform();
				initialDSHistoryCount = getViewDataSourceHistoryRowCount(dataSourceName);
				path = System.getProperty("user.dir");
				System.out.println(path);
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\Scope1\\SP1_File1.xlsx";
				uploadDataSourceFile(dataSourceName, filePath);
				weViewDataSourceIcon = driver.findElement(By.xpath("//span[@aria-label='" + dataSourceName
						+ "']/ancestor::span/parent::div//*[@data-testid='VisibilityOutlinedIcon']"));
				clickOn(weViewDataSourceIcon, "View Data Source");
				WebElement weDataLoadHistoryPopUp = driver
						.findElement(By.xpath("//h2[text()='" + dataSourceName + "- Data Load History']"));
				verifyElementAndHighlightIfExists(weDataLoadHistoryPopUp, "Data Load History", "Data Load History");
				Thread.sleep(2000);
				int r = 0;
				while (r != 30) {
					WebElement weStatus = driver.findElement(By.xpath(
							"//div[@ref='gridHeader']/following-sibling::div[@ref='eBodyViewport']//div[@role='rowgroup']/div[1]/div[@col-id='status']//descendant::span[4]"));
					String status = weStatus.getText();
					if (!status.trim().equals("COMPLETE")) {
						Thread.sleep(2000);
						r++;
					} else {
						break;
					}
					clickOn(btnRefresh, "Refresh");
				}
				clickOn(btnCloseDataHistory, "Close Data History");
				;
				actualDSHistoryCount = getViewDataSourceHistoryRowCount(dataSourceName);
				int currentCount = initialDSHistoryCount + 1;
				if (currentCount == actualDSHistoryCount) {
					passed("Data Source Histroy record is updated post uploading Data Source and record count is ==> "
							+ actualDSHistoryCount);
				} else {
					failed(driver,
							"Data Source Histroy record is not updated post uploading Data Source Current count is "
									+ actualDSHistoryCount);
				}
				validateInputMap();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyEMPIncrementalLoadDSInputMapForQ4Period() {
		try {
			WebElement weViewDataSourceIcon;
			int initialDSHistoryCount = 0, actualDSHistoryCount = 0;
			;
			String path = System.getProperty("user.dir");
			System.out.println(path);
			String filePath = path + "\\src\\test\\resources\\DataSourceFiles\\EMP\\emp_File1.xlsx";
//			ArrayList<String> age = getTestData(filePath,"Sheet1","Age");
			if (CreatDataSource(filePath)) {
				String dataSourceName;
				dataSourceName = GlobalVariables.dataSourceName;
				WebElement weDataSourceCard = driver
						.findElement(By.xpath("//span[@aria-label='" + dataSourceName + "']"));
				Actions builder = new Actions(driver);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weDataSourceCard);
				WebElement myElement = new WebDriverWait(driver, 20)
						.until(ExpectedConditions.visibilityOf(weDataSourceCard));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
				builder.moveToElement(weDataSourceCard).build().perform();
				initialDSHistoryCount = getViewDataSourceHistoryRowCount(dataSourceName);
				path = System.getProperty("user.dir");
				System.out.println(path);
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\EMP\\emp_File2.xlsx";
				uploadDataSourceFile(dataSourceName, filePath);
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\EMP\\emp_File3.xlsx";
				uploadDataSourceFile(dataSourceName, filePath);
				weViewDataSourceIcon = driver.findElement(By.xpath("//span[@aria-label='" + dataSourceName
						+ "']/ancestor::span/parent::div//*[@data-testid='VisibilityOutlinedIcon']"));
				clickOn(weViewDataSourceIcon, "View Data Source");
				WebElement weDataLoadHistoryPopUp = driver
						.findElement(By.xpath("//h2[text()='" + dataSourceName + "- Data Load History']"));
				verifyElementAndHighlightIfExists(weDataLoadHistoryPopUp, "Data Load History", "Data Load History");
				int r = 0;
				while (r != 30) {
					WebElement weStatus = driver.findElement(By.xpath(
							"//div[@ref='gridHeader']/following-sibling::div[@ref='eBodyViewport']//div[@role='rowgroup']/div[1]/div[@col-id='status']//descendant::span[4]"));
					String status = weStatus.getText();
					if (!status.trim().equals("COMPLETE")) {
						Thread.sleep(2000);
						r++;
					} else {
						break;
					}
					clickOn(btnRefresh, "Refresh");
				}
				clickOn(btnCloseDataHistory, "Close Data History");
				;
				actualDSHistoryCount = getViewDataSourceHistoryRowCount(dataSourceName);
				int currentCount = initialDSHistoryCount + 2;
				if (currentCount == actualDSHistoryCount) {
					passed("Data Source Histroy record is updated post uploading Data Source and record count is ==> "
							+ actualDSHistoryCount);
				} else {
					failed(driver,
							"Data Source Histroy record is not updated post uploading Data Source Current count is "
									+ actualDSHistoryCount);
				}
				validateInputMap();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyEMPFullLoadLoadDSInputMapForJSON() {
		try {
			WebElement weViewDataSourceIcon;
			int initialDSHistoryCount = 0, actualDSHistoryCount = 0;
			;
			String path = System.getProperty("user.dir");
			System.out.println(path);
			String filePath = path + "\\src\\test\\resources\\DataSourceFiles\\Json\\jagdish_hr_001_123.json";
			if (CreatDataSourceUsingJSON(filePath)) {
				String dataSourceName;
				dataSourceName = GlobalVariables.dataSourceName;
				WebElement weDataSourceCard = driver
						.findElement(By.xpath("//span[@aria-label='" + dataSourceName + "']"));
				Actions builder = new Actions(driver);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weDataSourceCard);
				WebElement myElement = new WebDriverWait(driver, 20)
						.until(ExpectedConditions.visibilityOf(weDataSourceCard));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
				builder.moveToElement(weDataSourceCard).build().perform();
				initialDSHistoryCount = getViewDataSourceHistoryRowCount(dataSourceName);
				path = System.getProperty("user.dir");
				System.out.println(path);
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\Json\\jagdish_hr_001_123.json";
				uploadDataSourceFile(dataSourceName, filePath);
				weViewDataSourceIcon = driver.findElement(By.xpath("//span[@aria-label='" + dataSourceName
						+ "']/ancestor::span/parent::div//*[@data-testid='VisibilityOutlinedIcon']"));
				clickOn(weViewDataSourceIcon, "View Data Source");
				WebElement weDataLoadHistoryPopUp = driver
						.findElement(By.xpath("//h2[text()='" + dataSourceName + "- Data Load History']"));
				verifyElementAndHighlightIfExists(weDataLoadHistoryPopUp, "Data Load History", "Data Load History");
				Thread.sleep(2000);
				int r = 0;
				while (r != 10) {
					WebElement weStatus = driver.findElement(By.xpath(
							"//div[@ref='gridHeader']/following-sibling::div[@ref='eBodyViewport']//div[@role='rowgroup']/div[1]/div[@col-id='status']//descendant::span[4]"));
					String status = weStatus.getText();
					if (!status.trim().equals("COMPLETE")) {
						Thread.sleep(2000);
						r++;
					} else {
						break;
					}
					clickOn(btnRefresh, "Refresh");
				}
				clickOn(btnCloseDataHistory, "Close Data History");
				;
				actualDSHistoryCount = getViewDataSourceHistoryRowCount(dataSourceName);
				int currentCount = initialDSHistoryCount + 1;
				if (currentCount == actualDSHistoryCount) {
					passed("Data Source Histroy record is updated post uploading Data Source and record count is ==> "
							+ actualDSHistoryCount);
				} else {
					failed(driver,
							"Data Source Histroy record is not updated post uploading Data Source Current count is "
									+ actualDSHistoryCount);
				}
				validateInputMap();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//button[@aria-label='upload']/*")
	private WebElement btnTriggerDataSource;
	@FindBy(xpath = "//*[text()='Data processing initiated.']")
	private WebElement lblTriggerToastMessage;

	public void verifyDSInputMapForWorkday() {
		try {
			Actions builder = new Actions(driver);
			WebElement weViewDataSourceIcon;
			int initialDSHistoryCount = 0, actualDSHistoryCount = 0;
			;
			CreatDataSourceUsingWorkday();
			String dataSourceName;
			dataSourceName = GlobalVariables.dataSourceName;
			WebElement weDataSourceCard = driver.findElement(By.xpath("//span[@aria-label='" + dataSourceName + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weDataSourceCard);
			WebElement myElement1 = new WebDriverWait(driver, 20)
					.until(ExpectedConditions.visibilityOf(weDataSourceCard));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement1);
			builder.moveToElement(weDataSourceCard).build().perform();
			waitForElement(btnTriggerDataSource);
			clickOn(btnTriggerDataSource, "btn Trigger Data Source");
			waitForElement(lblTriggerToastMessage);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weDataSourceCard);
			WebElement myElement = new WebDriverWait(driver, 20)
					.until(ExpectedConditions.visibilityOf(weDataSourceCard));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
			builder.moveToElement(weDataSourceCard).build().perform();
			clickOn(weDataSourceCard, "btn View Data Source");
			int r = 0;
			while (r != 10) {
				WebElement weStatus = driver.findElement(By.xpath(
						"//div[@ref='gridHeader']/following-sibling::div[@ref='eBodyViewport']//div[@role='rowgroup']/div[1]/div[@col-id='status']//descendant::span[4]"));
				String status = weStatus.getText();
				if (!status.trim().equals("COMPLETE")) {
					Thread.sleep(2000);
					r++;
				} else {
					break;
				}
				clickOn(btnRefresh, "Refresh");
			}
			WebElement weStatus = driver.findElement(By.xpath(
					"//div[@ref='gridHeader']/following-sibling::div[@ref='eBodyViewport']//div[@role='rowgroup']/div[1]/div[@col-id='status']//descendant::span[4]"));
			String status = weStatus.getText();
			if (status.equals("COMPLETE")) {
				passed("Successfully validated DTriggered Data Source is coming in the History");
			} else {
				fail("Failed to  validated DTriggered Data Source is coming in the History");
			}
			clickOn(btnCloseDataHistory, "Close Data History");
			validateInputMap();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	String dataSourceName;
	public void CreatDataSourceSompoHD() throws AWTException, IOException {
		try {
			waitForElement(btnCreatDataSource);
			clickOn(btnCreatDataSource, "creat Data source");
			clickOn(btnSkipNow, "Skip");
			verifyIfElementPresent(weAPI, "api", "Creat Data source");
			verifyIfElementPresent(weFileManualUpload, "file Manual Upload", "Creat Data source");
			verifyIfElementPresent(weFTPFileUpload, "FTP file upload", "Creat Data source");
			selectFileType("EXCEL");
			waitForElement(txtDataSourceName);
			 dataSourceName = "TestAuto" + generateRandomString(8);
			 enterText(txtDataSourceName, "Data source name", dataSourceName);
//		 	clickOn(btnFileUpload, "File upload");
			clickOn(btnNext, "Next");
			clickOn(btnBrowseFiles, "Browse files");
			String path = System.getProperty("user.dir");
			System.out.println(path);
			String filePath = path + "\\src\\test\\resources\\DataSourceFiles\\sompohd_File4.xlsx";
			ProcessBuilder pb = new ProcessBuilder(path + "\\src\\test\\resources\\DataSourceFiles\\fileUpload.exe",
					filePath);
			sleep(1000);
			pb.start();
			waitForElement(weS3UploadSuccessfull);
			if (isElementPresent(weS3UploadSuccessfull)) {
				passed("Successfully file uploaded");
			} else {
				failed(driver, "File upload failed");
			}
			clickOn(btnNext, "Next");
			waitForElement(BtnCreate);
			verifyIfElementPresent(weUniqueKey, "uniqueKey", "Preview");
			verifyIfElementPresent(weAttributeName, "attributeName", "Preview");
			verifyIfElementPresent(weAttributeType, "attributeType", "Preview");
			clickOn(BtnCreate, "Create");
			waitForElement(btnOk);
			clickOn(btnOk, "Ok");
			waitForElement(btnCreatDataSource);
			WebElement createdDataSource = driver.findElement(By.xpath("//*[text()='" + dataSourceName + "']"));
			if (isElementPresent(createdDataSource)) {
				passed("Successfully Datasource created");
			} else {
				failed(driver, "Failed to creat data source");
			}	
		} catch (Exception e) {
			failed(driver, "Exception caught in method CreatDataSource " + e.getMessage());
		}
	}
	
	public void validateNotifications() {
		try {
		CreatDataSourceSompoHD();
        clickOnNotificationBellbuttonInHomePage();
		String notificationAssignedDRText = "Data Source Executed - " + dataSourceName + "";
		validateNotificationTextInHomePageDataSource(notificationAssignedDRText);
		WebElement createdDataSource = driver.findElement(By.xpath("//*[text()='" + dataSourceName + "']"));
		if (isElementPresent(createdDataSource)) {
			passed("Successfully Validated navigation of notifications Open Button to  Created Data Source");
		} else {
			failed(driver, "Failed to Validate navigation of notifications Open Button to  Created Data Source");
		}
		HashMap<String, String> hm = GMail.getGmailDataForComplexBody("Data Source Executed - "+ dataSourceName +" ");
		String otpString = hm.get("body");
		MenuBarPage = returnMenuPage();
		CatalogsPage = MenuBarPage.clickOnCatalogsMenu();
		String path = System.getProperty("user.dir");
		System.out.println(path);
		String filePath = null;
		ArrayList<String> lstMetricName = new ArrayList<>();
		CatalogsPage.searchAndSelectCatalogCard(data.get("CatalogName"));
		GlobalVariables.inputMapName = "Test_DS_" + generateRandomString(3) + generateRandomNumber(3);
		lstMetricName.add(data.get("Metric1"));
		CatalogsPage.createInputMap(GlobalVariables.inputMapName, GlobalVariables.dataSourceName,
				lstMetricName);
		CatalogsPage.excuteInputMap(GlobalVariables.inputMapName);
		CatalogsPage.verifyViewInputMapHistory(GlobalVariables.inputMapName);
        clickOnNotificationBellbuttonInHomePage();
		String notificationAssignedDRText1 = "Input Map "+GlobalVariables.inputMapName+" executed successfully";
		validateNotificationTextInHomePageDataSource(notificationAssignedDRText1);
		WebElement inputMapName = driver.findElement(By.xpath("//span[text()='"+GlobalVariables.inputMapName+"']"));
		if (isElementPresent(inputMapName)) {
			passed("Successfully Validated navigation of notifications Open Button to  Input Map Catalog");
		} else {
			failed(driver, "Failed to Validate navigation of notifications Open Button to  Input Map Catalog");
		}
		} catch (Exception e) {
			failed(driver, "Exception caught in method CreatDataSource " + e.getMessage());
		}
	}
	public void validateNotificationTextInHomePageDataSource(String notificationText) {
		try {
			waitForElement(btnNotification);
			if (isElementPresent(txtHeaderNotifications)) {
				//
			} else {
				clickOn(btnNotification, "Notifications");
			}
			String xpathNotification = "//div[text()='Notifications']//parent::div//following-sibling::div//span[contains(text(),\""
					+ notificationText + "\")]";
			sleep(3000);
			WaitForElementWithDynamicXpath(xpathNotification);
			if (isElementWithDynamicXpath(xpathNotification)) {
				passed("Successfully validated Notification text In notifications As"
						+ driver.findElement(By.xpath(xpathNotification)).getText());
			} else {
				failed(driver, "Failed To validate Notification text In notifications");
			}
			String xpathNotificationopenButton = "//div[text()='Notifications']//parent::div//following-sibling::div//span[contains(text(),\""
					+ notificationText + "\")]//parent::div//parent::div//following-sibling::div/button[text()='Open']";
			clickOnElementWithDynamicXpath(xpathNotificationopenButton, "Cliked on Notification Open Button");	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FindBy(xpath = "//button[@aria-label='Notification']")
	private WebElement btnNotification;
	@FindBy(xpath = "//div[text()='Notifications']")
	private WebElement txtHeaderNotifications;
	@FindBy(xpath = "//button/*[@data-testid='CloseIcon']")
	private WebElement btnNotifyClose;

	public void clickOnNotificationBellbuttonInHomePage() {
		driver.navigate().refresh();
		sleep(1000);
		driver.navigate().refresh();
		waitForElement(btnNotification);
		clickOn(btnNotification, "Notification button ");
	}
	public double getSumOfList(ArrayList<String> values) {
		double d = 0.0, dblVal = 0.0;
		try {
			for (String val : values) {
				if (val != "") {
					d = Double.parseDouble(val);
					dblVal = dblVal + d;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dblVal;
	}

	public double getSumOfList1(HashSet<String> values) {
		double d = 0.0, dblVal = 0.0;
		try {
			for (String val : values) {
				if (val != "") {
					d = Double.parseDouble(val);
					dblVal = dblVal + d;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dblVal;
	}

	public double getMinOfList(ArrayList<String> values) {
		double d = 0.0, min = 0.0;
		ArrayList<Double> dblArrLst = new ArrayList<>();
		try {
			for (String val : values) {
				if (val != "") {
					d = Double.parseDouble(val);
					dblArrLst.add(d);
				}
			}
			min = Collections.min(dblArrLst);
//			for (int i = 1; i < dblArrLst.size(); i++) {
//			    if (dblArrLst.get(i) < min) {
//			        min = dblArrLst.get(i);
//			    }
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return min;
	}

	public double getMaxOfList(ArrayList<String> values) {
		double d = 0.0, max = 0.0;
		ArrayList<Double> dblArrLst = new ArrayList<>();
		try {
			for (String val : values) {
				if (val != "") {
					d = Double.parseDouble(val);
					dblArrLst.add(d);
				}
			}
			System.out.println();
			max = Collections.max(dblArrLst);
//			for (int i = 1; i < dblArrLst.size(); i++) {
//			    if (dblArrLst.get(i) > max) {
//			    	max = dblArrLst.get(i);
//			    }
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return max;
	}

	public double getAvgOfList(ArrayList<String> values) {
		double d = 0.0, dblVal = 0.0;
		Double average = 0.0;
		ArrayList<Double> dblArrLst = new ArrayList<>();
		try {
			for (String val : values) {
				if (val != "") {
					d = Double.parseDouble(val);
					dblArrLst.add(d);
				}
			}
			average = dblArrLst.stream().mapToDouble(val -> val).average().orElse(0.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return average;
	}

	public static ArrayList<String> multiplyArrayLists(ArrayList<String> list1, ArrayList<String> list2,
			ArrayList<String> list3) {
		ArrayList<String> result = new ArrayList<String>();
		int size = Math.min(list1.size(), Math.min(list2.size(), list3.size()));
		for (int i = 0; i < size; i++) {
			double num1 = Double.parseDouble(list1.get(i));
			double num2 = Double.parseDouble(list2.get(i));
			double num3 = Double.parseDouble(list3.get(i));
			double product = num1 * num2 * num3;
			result.add(Double.toString(product));
		}
		return result;
	}
}
