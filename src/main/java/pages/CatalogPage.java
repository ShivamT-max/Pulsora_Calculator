package pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;
import utilities.CSVDataReader;
import utilities.Constants;
import utilities.Data;
import utilities.GlobalKeys;
import utilities.GlobalVariables;

public class CatalogPage extends TestBase {
	protected CatalogPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	@FindBy(xpath = "(//span[text()='TestAutMetric']//parent::div//parent::div//parent::div//following-sibling::div//span)[1] ")
	private WebElement eleValueField;
	@FindBy(xpath = "(//p[text()='Data']//parent::div//following-sibling::div//span)[1]") // p[text()='Data']//parent::grid//following-sibling::div//span[2]--->old
	private WebElement eleValueInMetricDetails;
	@FindBy(xpath = "//p[text()='Data']//parent::div//following-sibling::div//input") // p[text()='Data']//parent::grid//following-sibling::div//input--->old
	private WebElement txtInputValue;
	@FindBy(xpath = "//button/*[@data-testid='CloseIcon']")
	private WebElement btnCloseInMetricDetails;
	@FindBy(xpath = "//button[text()='Publish']")
	private WebElement btnPublish;
	@FindBy(xpath = "//div/div[text()='Unable to Publish']")
	private WebElement msgUnableToPublish;
//    @FindBy(xpath = "//button[text()='Ok']")
//    private WebElement btnOk;
	@FindBy(xpath = "//button[text()='Confirm']")
	private WebElement btnConfirm;
	@FindBy(xpath = "//*[text()='Data published successfully!']")
	private WebElement msgSuccessDataPublish;
	@FindBy(xpath = "//button[text()='Done']")
	private WebElement btnDone;
	@FindBy(xpath = "//div[@col-id='metricValue' and @role='gridcell']//span[1]")
	private WebElement MetricUpdatedVal;
	@FindBy(xpath = "(//div[@col-id='value'])[2]")
	private WebElement btnAuditLogValue;
	@FindBy(xpath = "(//tbody/tr/th[3])[1]")
	private WebElement btnUploadedEvidenceName;
	@FindBy(xpath = "//div[@col-id='Audit' and @role!='columnheader']")
	private WebElement btnAuditLog;
	@FindBy(xpath = "//*[@data-testid='CloseIcon' and @aria-label='Close']")
	private WebElement btnSectionClose;
	@FindBy(xpath = "//span[contains(text(),'Completed')]")
	private WebElement weCompletedCatalog;
	@FindBy(xpath = "//span[contains(text(),'Data not available')]")
	private WebElement weDataNotAvailableCatalog;
	@FindBy(xpath = "//span[contains(text(),'Required')]")
	private WebElement weRequiredCatalog;
	@FindBy(xpath = "//span[contains(text(),'Unanswered')]")
	private WebElement weUnAnsweredCatalaog;
	@FindBy(xpath = "//span[contains(text(),'Validations')]")
	private WebElement weValidationsCatalog;

	public MenuBarPage returnMenuPage() {
		return new MenuBarPage(driver, data);
	}

	public void validateCreateCatalogInPulsESG() {
		try {
			clickOn(btnCreateCatalog, "Create Catalog Button");
			clickOn(btnForwardIcon, "Forward Icon Button");
			clickOn(btnForwardIcon, "Forward Icon Button");
			GlobalVariables.catalogName = Constants.catalogName + generateRandomString(3) + generateRandomNumber(3);
			enterText(txtCatalogName, "CatalogName", GlobalVariables.catalogName);
			enterText(txtCatalogDescription, "CatalogDescricption",
					Constants.catalogDesc + "_GlobalVariables.catalogName");
			clickOn(drpSelectCatalogType, "CatalogType");
			WebElement selectCatalogType = driver
					.findElement(By.xpath("//div/ul/li[text()='" + Constants.catalogType + "']"));
			clickOn(selectCatalogType, "Select Catalog Type List");
			clickOn(btnCreate, "Create button");
			clickOn(btnClose, "Close button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void enterMetricValueDataInCatalog(String metricName, String metricValue, String metricType) {
		try {
			WebElement weMetricValue = driver.findElement(By.xpath("//span[text()='" + metricName
					+ "']//ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']"));
			switch (metricType) {
			case "Number":
				clickOn(weMetricValue, "MetricValueFor" + metricName);
				WebElement weMetricData = driver
						.findElement(By.xpath("//p[text()='Data']//parent::grid//following-sibling::div//span[2]"));
				clickOn(weMetricData, "Metric Data");
				sleep(3000);
				WebElement MetricValueData = driver
						.findElement(By.xpath("//p[text()='Data']//parent::grid//following-sibling::div//input"));
				MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				MetricValueData.sendKeys(Keys.BACK_SPACE);
				enterText(MetricValueData, "Metric Value Data", metricValue);
				clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
				break;
			case "Integer":
				clickOn(weMetricValue, "MetricValueFor" + metricName);
				weMetricData = driver
						.findElement(By.xpath("//p[text()='Data']//parent::grid//following-sibling::div//span[2]"));
				clickOn(weMetricData, "Metric Data");
				sleep(2000);
				MetricValueData = driver
						.findElement(By.xpath("//p[text()='Data']//parent::grid//following-sibling::div//input"));
				MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				MetricValueData.sendKeys(Keys.BACK_SPACE);
				enterText(MetricValueData, "Metric Value Data", metricValue);
				clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
				break;
			case "Text":
				clickOn(weMetricValue, "MetricValueFor" + metricName);
				weMetricData = driver
						.findElement(By.xpath("//p[text()='Data']//parent::grid//following-sibling::div//span[2]"));
				clickOn(weMetricData, "Metric Data");
				sleep(2000);
				MetricValueData = driver
						.findElement(By.xpath("//p[text()='Data']//parent::grid//following-sibling::div//input"));
				MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				MetricValueData.sendKeys(Keys.BACK_SPACE);
				enterText(MetricValueData, "Metric Value Data", metricValue);
				clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
				break;
			case "Description":
				clickOn(weMetricValue, "MetricValueFor" + metricName);
				weMetricData = driver
						.findElement(By.xpath("//p[text()='Data']//parent::grid//following-sibling::div//span[1]"));
				clickOn(weMetricData, "Metric Data");
				sleep(2000);
				MetricValueData = driver
						.findElement(By.xpath("(//p[text()='Data']//parent::grid//following-sibling::div[1]//p)[1]"));
				MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				MetricValueData.sendKeys(Keys.BACK_SPACE);
				enterText(MetricValueData, "Metric Value Data", metricValue);
				clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
				break;
			case "Boolean":
				clickOn(weMetricValue, "MetricValueFor" + metricName);
				weMetricData = driver
						.findElement(By.xpath("//p[text()='Data']//parent::grid//following-sibling::div//span[2]"));
				clickOn(weMetricData, "Metric Data");
				MetricValueData = driver
						.findElement(By.xpath("//span[text()='" + metricValue + "']//preceding-sibling::span/input"));
				clickOn(MetricValueData, metricValue);
				clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
				break;
			case "MultiSelect":
				clickOn(weMetricValue, "MetricValueFor" + metricName);
				weMetricData = driver
						.findElement(By.xpath("//p[text()='Data']//parent::grid//following-sibling::div//span[2]"));
				clickOn(weMetricData, "Metric Data");
				String arrMultiSelect[] = metricValue.split(",");
				sleep(2000);
				for (int i = 0; i < arrMultiSelect.length; i++) {
					MetricValueData = driver.findElement(
							By.xpath("//span[text()='" + arrMultiSelect[i] + "']//preceding-sibling::span[1]/input"));
					WebElement chkBoxValue = driver.findElement(By.xpath("//span[text()='" + arrMultiSelect[i]
							+ "']//preceding-sibling::span[1]/*[contains(@data-testid,'CheckBox')]"));
					if (chkBoxValue.getAttribute("data-testid").contains("BlankIcon"))
						clickOn(MetricValueData, metricValue);
				}
				clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
				break;
			case "SingleSelect":
				clickOn(weMetricValue, "MetricValueFor" + metricName);
				weMetricData = driver
						.findElement(By.xpath("//p[text()='Data']//parent::grid//following-sibling::div//span[2]"));
				clickOn(weMetricData, "Metric Data");
				sleep(2000);
				MetricValueData = driver.findElement(
						By.xpath("//span[text()='" + metricValue + "']//preceding-sibling::span[1]/input"));
				clickOn(MetricValueData, metricValue);
				clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			default:
				info("Metric Type not listed ");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidatepublishDRFromPortCoWithEvidenceUpload() {
		try {
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
			}
			String xpathMetricValue = "(//span[text()='" + data.get("MetricName")
					+ "']//parent::div//parent::div//parent::div//following-sibling::div//span)[1]";
			clickOnElementWithDynamicXpath(xpathMetricValue, "Metric Value Field");
			clickOn(eleValueInMetricDetails, " Value Field In Metric details");
			sleep(3000);
			txtInputValue.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			txtInputValue.sendKeys(Keys.BACK_SPACE);
			GlobalVariables.inputValue = generateRandomNumber(3);
			enterText(txtInputValue, "Input Value", GlobalVariables.inputValue);
			takeScreenshot(driver);
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Actions builder = new Actions(driver);
			WebElement myElement = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(btnEvidence));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
			builder.moveToElement(myElement).click().build().perform();
			Thread.sleep(3000);
			System.out.println(waitForElement(btnBrowseFiles));
			clickOn(btnBrowseFiles, "Browse files");
			String path = System.getProperty("user.dir");
			String filePath = path + "\\src\\test\\resources\\DataRequestFiles\\sample1.txt";
			ProcessBuilder pb = new ProcessBuilder(path + "\\src\\test\\resources\\DataRequestFiles\\fileUpload.exe",
					filePath);
			pb.start();
			clickOn(btnUpload, "Upload button");
			clickOn(btnClose, "Close button");
			sleep(3000);
			System.out.println("Uploading");
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ADD);
			robot.keyPress(KeyEvent.VK_ADD);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			clickOn(btnPublish, "publish button");
			clickOn(btnConfirm, "Confirm button");
			if (isElementPresent(msgSuccessDataPublish)) {
				passed("Sucessfully validate DR publish Sucess Message");
				takeScreenshot(driver);
			} else {
				failed(driver, "Failed To validate Publish success message");
			}
			clickOn(btnDone, "Done button");
		} catch (Exception e) {
			System.out.println("Exception+++++++++" + e.getMessage());
			ValidatepublishDRFromPortCoWithEvidenceUpload();
		}
	}

	public void ValidatepublishDRFromPortCo() {
		try {
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
			}
			Thread.sleep(3000);
			String xpathMetricValue = "(//span[text()='" + data.get("MetricName")
					+ "']//parent::div//parent::div//parent::div//following-sibling::div//span)[1]";
			clickOnElementWithDynamicXpath(xpathMetricValue, "Metric Value Field");
			clickOn(eleValueInMetricDetails, " Value Field In Metric details");
			txtInputValue.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			txtInputValue.sendKeys(Keys.BACK_SPACE);
			enterText(txtInputValue, "Input Value", data.get("InputValue"));
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			clickOn(btnPublish, "publish button");
			clickOn(btnConfirm, "Confirm button");
			if (isElementPresent(msgSuccessDataPublish)) {
				passed("Sucessfully validate DR publish Sucess Message");
			} else {
				failed(driver, "Failed To validate Publish success message");
			}
			clickOn(btnDone, "Done button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidatepublishDRFromPortCoForTwoMetrics() {
		try {
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
			}
			String xpathMetric1Value = "(//span[text()='" + data.get("MetricName1")
					+ "']//parent::div//parent::div//parent::div//following-sibling::div//span)[1]";
			clickOnElementWithDynamicXpath(xpathMetric1Value, "Metric Value Field for" + data.get("MetricName1"));
			clickOn(eleValueInMetricDetails, " Value Field In Metric details");
			txtInputValue.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			txtInputValue.sendKeys(Keys.BACK_SPACE);
			enterText(txtInputValue, "Input Value", data.get("InputValue"));
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			String xpathMetric2Value = "(//span[text()='" + data.get("MetricName2")
					+ "']//parent::div//parent::div//parent::div//following-sibling::div//span)[1]";
			clickOnElementWithDynamicXpath(xpathMetric2Value, "Metric Value Field for" + data.get("MetricName2"));
			clickOn(eleValueInMetricDetails, " Value Field In Metric details");
			txtInputValue.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			txtInputValue.sendKeys(Keys.BACK_SPACE);
			enterText(txtInputValue, "Input Value", data.get("InputValue"));
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			clickOn(btnPublish, "publish button");
			clickOn(btnConfirm, "Confirm button");
			if (isElementPresent(msgSuccessDataPublish)) {
				passed("Sucessfully validate DR publish Sucess Message");
			} else {
				failed(driver, "Failed To validate Publish success message");
			}
			clickOn(btnDone, "Done button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateUpdatedMetricValueInCatalogPage() {
		try {
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand  Data request Link");
			String uiMetricValue = MetricUpdatedVal.getText();
			if (uiMetricValue.trim().equals(data.get("InputValue").trim())) {
				passed("Successfully validated Entered input Value from portCo");
			} else {
				failed(driver, "Failed To Validate Entered Input Value from PortCo");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateEvidenceAndAuditLogInCatalogPage() {
		try {
			String xpathCatalogName = "//article//*[contains(text(),'" + data.get("CatalogName") + "')]";
			clickOnElementWithDynamicXpath(xpathCatalogName, "Catalog Name");
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand  Data request Link");
			clickOn(btnAuditLog, "Audit Log Biutton");
			String uiMAuditValue = MetricUpdatedVal.getText();
			if (uiMAuditValue.trim().equals(GlobalVariables.inputValue.trim())) {
				passed("Successfully validated Entered input Value In Audit Log");
				takeScreenshot(driver);
			} else {
				failed(driver, "Failed To Validate Entered Input Value In Audit Log");
			}
			waitForElement(btnSectionClose);
			clickOn(btnSectionClose, "Audit Log Close ");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateUpdatedMetricValueInCatalog() {
		try {
			String xpathCatalogName = "//article//*[contains(text(),'" + data.get("CatalogName") + "')]";
			clickOnElementWithDynamicXpath(xpathCatalogName, "Catalog Name");
			clickOn(btnViewOrganizationData, "View Organization Data");
			String xpathOrgName = "//span[@class='orgName' and text()='Organization']//following-sibling::div/p[contains(text(),'"
					+ data.get("PortCoEntName") + "')]";
			clickOnElementWithDynamicXpath(xpathOrgName, "Organization Name");
			clickOn(btnFilter, "Filter button");
			verifyIfElementPresent(drpPeriod, "Period Filters", "Period Filters");
			clickOn(drpPeriod, "Period Dropdown");
			WebElement wePeriodName = driver
					.findElement(By.xpath("//ul[@role='listbox']/li[text()='" + data.get("Period") + "']"));
			clickOn(wePeriodName, data.get("Period"));
			clickOn(drpDataRequest, "Period Dropdown");
			WebElement weDataRequestName = driver.findElement(
					By.xpath("//ul[@role='listbox']/li[text()='" + GlobalVariables.dataRequestName + "']"));
			clickOn(weDataRequestName, GlobalVariables.dataRequestName);
			clickOn(btnClose, "Close Button");
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
			}
			String xpathMetricValue = "(//span[text()='" + data.get("MetricName")
					+ "']//parent::div//parent::div//parent::div//following-sibling::div//span)[1]";
			String catalogMetricValue = driver.findElement(By.xpath(xpathMetricValue)).getText();
			if (catalogMetricValue.equals(GlobalVariables.metricValue)) {
				passed("Successfully Validated Updated Metric Value in Catalog As" + catalogMetricValue);
			} else {
				failed(driver, "Failed To Validate Updated Metric Value ,Expected As" + GlobalVariables.metricValue
						+ "Actual is" + catalogMetricValue);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//div/*[contains(text(),'Catalogs')]")
	private WebElement lblCatalogs;
	@FindBy(xpath = "//button/*[@aria-label='Create Catalog' and @data-testid='AddOutlinedIcon']")
	private WebElement btnCreateCatalog;
	@FindBy(xpath = "//*[@data-testid='ArrowForwardIosIcon']")
	private WebElement btnForwardIcon;
	@FindBy(xpath = "//div/input[@name='catalogName']")
	private WebElement txtCatalogName;
	@FindBy(xpath = "//div[contains(text(),'Provides a detailed description of the Catalog and its contents')]/following-sibling::div//p")
	private WebElement txtCatalogDescription;
	@FindBy(xpath = "//div[contains(@aria-labelledby,'select-type')]")
	private WebElement drpSelectCatalogType;
	@FindBy(xpath = "//button[text()='Create']")
	private WebElement btnCreate;
	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement btnCancel;
	@FindBy(xpath = "//button[text()='Close']")
	private WebElement btnClose;
	@FindBy(xpath = "//button//*[@aria-label='Save & Close']")
	private WebElement btnTopCloseMetric;
	@FindBy(xpath = "//button[text()='Upload']//parent::div//following-sibling::div/button[text()='Close']")
	private WebElement btnUploadClose;

	public void validateCreateCatalogTopicAndMetrics() {
		try {
			clickOn(btnCreateCatalog, "Create Catalog Button");
			clickOn(btnForwardIcon, "Forward Icon Button");
			clickOn(btnForwardIcon, "Forward Icon Button");
			GlobalVariables.catalogName = Constants.catalogName + generateRandomString(3) + generateRandomNumber(3);
			enterText(txtCatalogName, "CatalogName", GlobalVariables.catalogName);
			enterText(txtCatalogDescription, "CatalogDescricption",
					Constants.catalogDesc + "_GlobalVariables.catalogName");
			clickOn(drpSelectCatalogType, "CatalogType");
			WebElement selectCatalogType = driver
					.findElement(By.xpath("//div/ul/li[text()='" + Constants.catalogType + "']"));
			clickOn(selectCatalogType, "Select Catalog Type List");
			clickOn(btnCreate, "Create button");
			waitForElement(btnClose);
			Thread.sleep(3000);
			String strCrtCatalogSuccMsg = driver
					.findElement(By.xpath("//*[@data-testid='CloseIcon']/following-sibling::div[1]")).getText();
			if (strCrtCatalogSuccMsg.trim().contains(
					"will be created! Would like to proceed adding Topics/Metrics to the catalog?\n\nPlease click Add Topics/Metrics to proceed")) {
				passed("Successfully created Catalog" + GlobalVariables.catalogName);
			} else {
				failed(driver, "Unable to create Catalog");
			}
			clickOn(btnClose, "Close button");
			ValidateCreateTopicAndMetricForCatalog();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateCreateTopicAndMetricForCatalog() {
		try {
			waitForElement(lblCatalogs);
			WebElement weCreatedCatalogName = driver
					.findElement(By.xpath("//section/article/*[text()='" + GlobalVariables.catalogName + "']"));
			waitForElement(weCreatedCatalogName);
			clickOn(weCreatedCatalogName, "catalog Name");
			verifyAddTopic(data.get("TopicName"));
			verifyAddMetric();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean selectExisitingCatalogCard(String catalogName) {
		boolean blnSelected = false;
		try {
			WebElement weCatalogCard = driver.findElement(By.xpath("//h4[text()='" + catalogName + "']"));
			clickOn(weCatalogCard, catalogName);
			WebElement weCatalogNameHeader = driver.findElement(By.xpath("//h6[text()='" + catalogName + "']"));
			blnSelected = verifyIfElementPresent(weCatalogNameHeader, catalogName + " Catalog", "Catalog Detail Page");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blnSelected;
	}

	@FindBy(xpath = "//*[@aria-label='Go to previous page']")
	private WebElement btnGoToPreviousPage;
	@FindBy(xpath = "//*[@aria-label='Go to next page']")
	private WebElement btnGoToNextPage;
	@FindBy(xpath = "//nav[@aria-label='pagination navigation']/following-sibling::article[text()='/']/following-sibling::article")
	private WebElement weTotalPageCount;

	public boolean searchAndSelectCatalogCard(String catalogName) {
		clickOnCatalogInCatalogPage(catalogName);
		return true;
	}
	public void selectPeriod_And_TasksInCatalogFilters(String periodName, String tasksName) {
		clickOn(btnFilter, "Filter");
		verifyIfElementPresent(lblFilters, "Filters", "Filters");
		verifyIfElementPresent(drpPeriod, "Period Filters", "Period Filters");
		clickOn(drpPeriod, "Period Dropdown");
		WebElement wePeriodName = driver.findElement(By.xpath("//ul[@role='tree']/li/div/div//div[text()='"+periodName+"']"));
		clickOn(wePeriodName, periodName);
		sleep(500);
		clickOn(drpTasks, "Tasks Dropdown");
		WebElement weTasksName = driver.findElement(By.xpath("//ul//li[text()='"+tasksName+"']"));
		clickOn(weTasksName, tasksName);
		sleep(1000);
		WebElement weDDNameAutoSelect = driver.findElement(By.xpath("//h6[text()='Data Destination']/parent::div/parent::div/following-sibling::div//div/p"));
		if(weDDNameAutoSelect.getText().equals(tasksName)) {
			passed("Succesfully validated Automatically selection of DD Name when Tasks selected in Filter");	
		}else {
			failed(driver, "failed to validate Succesfully validated Automatically selection of DD Name when Tasks selected in Filter");
		}
		clickOn(btnClose, "Filters Close");
	}
	public void selectTasksInCatalogFilter(String tasksName) {
		clickOn(btnFilter, "Filter");
		verifyIfElementPresent(lblFilters, "Filters", "Filters");
		verifyIfElementPresent(drpPeriod, "Period Filters", "Period Filters");
		clickOn(drpTasks, "Tasks Dropdown");
		WebElement weTasksName = driver.findElement(By.xpath("//ul//li[text()='"+tasksName+"']"));
		clickOn(weTasksName, tasksName);
		clickOn(btnClose, "Filters Close");
	}
	
	public boolean searchAndSelectCatalogCard_old(String catalogName) {
		int totalPageCount = 0;
		boolean blnFnd = false;
		try {
			if (isElementPresent(weTotalPageCount)) {
				totalPageCount = Integer.parseInt(weTotalPageCount.getText());
				for (int i = 1; i <= totalPageCount; i++) {
					if (totalPageCount > 1) {
						List<WebElement> lstCatalogHeaders = driver.findElements(By.xpath(
								"//article[contains(text(),'Created Date : ')]/parent::article/following-sibling::article/h4"));
						for (int j = 0; j < lstCatalogHeaders.size(); j++) {
							if (lstCatalogHeaders.get(j).getText().trim().equalsIgnoreCase(catalogName)) {
								blnFnd = selectExisitingCatalogCard(catalogName);
								return blnFnd;
							}
							System.out.println(lstCatalogHeaders.get(j).getText());
						}
						if (blnFnd == false) {
							try {
								if (isElementPresent(btnGoToNextPage)) {
									clickOn(btnGoToNextPage, "Next Button Pagination" + catalogName);
								}
								// if(verifyIfElementPresent(btnGoToNextPage, "btnGoToNextPage", "Catalog
								// Pagination"))
								// clickOn(btnGoToNextPage, "Next Button Pagination"+catalogName );
							} catch (Exception e) {
							}
							Thread.sleep(2000);
						}
					} else {
						blnFnd = selectExisitingCatalogCard(catalogName);
					}
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
		return blnFnd;
	}

	@FindBy(xpath = "//div[@aria-label='Expand']")
	private WebElement btnExpand;
	@FindBy(xpath = "//div[@aria-label='Catalog Setup']")
	private WebElement btnConfigureCatalog;
	@FindBy(xpath = "//b[contains(text(),'Custom')]/parent::li")
	private WebElement weCustomTopic;
	@FindBy(xpath = "//div[@id='mui-component-select-esgMarker']")
	private WebElement drpESGMarker;
	@FindBy(xpath = "//div[contains(@id,'esgMarker')]")
	private WebElement drpESGMarkerQA;
	@FindBy(xpath = "//input[@id='topicName']")
	private WebElement txtTopicName;
	@FindBy(xpath = "//input[@id='topicName']/ancestor::label/parent::div/following-sibling::div/button[text()='Save']")
	private WebElement btnSaveTopic;
	@FindBy(xpath = "//div[contains(text(),'Topic Saved Successfully!')]")
	private WebElement weMsgTopicSavedSuccessfully;
	@FindBy(xpath = "//button[contains(text(),'Ok')]")
	private WebElement btnOk;
	@FindBy(xpath = "//button[contains(text(),'Add Metric')]")
	private WebElement btnAddMetric;
	@FindBy(xpath = "//span[contains(text(),'Topics')]/following-sibling::b")
	private WebElement weTopicsCount;

	public void verifyAddTopic(String topicName) {
		try {
			List<String> topic = new ArrayList<>();
			clickOn(btnExpand, "Expand");
			clickOn(btnConfigureCatalog, "btnConfigureCatalog");
			jsClick(weCustomTopic, "Custom Topic");
			jsClick(btnAddTopic, "btnAddTopic");
			clickOn(drpESGMarker, "drpESGMarker");
			WebElement lstEsgMarker = driver.findElement(By.xpath(
					"//div[@id='menu-esgMarker']//div//li[contains(text(),'" + data.get("ESGMarker") + "')][1]"));
			clickOn(lstEsgMarker, "ESG Marker");
			enterText(txtTopicName, "Topic Name", topicName);
			clickOn(btnSaveTopic, "Save Topic");
			if (!topic.contains(topicName)) {
				verifyIfElementPresent(weMsgTopicSavedSuccessfully, "Topic Saved Successfully", "Create Topic");
				clickOn(btnOk, "Ok");
			}
			topic.add(topicName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//button[contains(text(),'Add Topic')]")
	private WebElement btnAddTopic;
	@FindBy(xpath = "//h6[contains(text(),'Metric Details')]")
	private WebElement lblMetricDetails;
	@FindBy(xpath = "//div[@id='mui-component-select-type']")
	private WebElement drpInputType;
	@FindBy(xpath = "//div[@id='mui-component-select-topicId']")
	private WebElement drpTopic;
	@FindBy(xpath = "//input[@name='metric']")
	private WebElement txtMetricName;
	@FindBy(xpath = "//div[@id='mui-component-select-metricType']")
	private WebElement drpMetricType;
	@FindBy(xpath = "//input[@name='inputValue']")
	private WebElement txtInputOptions;
	@FindBy(xpath = "//div[@id='mui-component-select-metricUoM']")
	private WebElement drpUnit;
	@FindBy(xpath = "//p[text()='Instruction']//following-sibling::div//p")
	private WebElement txtInstructions;
	@FindBy(xpath = "//div[@id='mui-component-select-validationMethodRule']")
	private WebElement drpValidationMethod;
	@FindBy(xpath = "//input[@value='Expression']/parent::div/parent::div/following-sibling::div//input[@name='expressionValue']")
	private WebElement txtExpressionValue;
	@FindBy(xpath = "//input[@value='Expression']/parent::div/parent::div/following-sibling::div//input[@placeholder='Custom Message']")
	private WebElement txtCustomMessage;
	@FindBy(xpath = "//input[@value='Total']/parent::div/parent::div/following-sibling::div/div[@id='multiSelectMetrics']//div[text()='SelectMultiple']")
	private WebElement drpTotalMultiSelect;
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement btnSaveMetric;
	@FindBy(xpath = "//input[@name='minValue']")
	private WebElement txtThresholdMinVal;
	@FindBy(xpath = "//input[@name='maxValue']")
	private WebElement txtThresholdMaxValue;
	@FindBy(xpath = "//div[@id='mui-component-select-aggregation']") // yogesh change
	private WebElement drpAggregationRules;
	@FindBy(xpath = "//div[contains(text(),'Metric Detail Saved Successfully!')]")
	private WebElement weMsgMetricSavedSuccessfully;

	public void verifyAddMetric() {
		try {
			jsClick(btnAddMetric, "Add Metric");
			verifyIfElementPresent(lblMetricDetails, "Metric Details", "Create Metric");
			clickOn(drpInputType, "Input Type dropdown");
			WebElement lstInputType = driver.findElement(
					By.xpath("//div[@id='menu-type']//ul/li[contains(text(),'" + data.get("InputType") + "')]"));
			clickOn(lstInputType, "Input Type");
			clickOn(drpTopic, "Topic dropdown");
			WebElement lstTopics = driver
					.findElement(By.xpath("//div[@id='menu-topicId']//li[text()='" + data.get("TopicName") + "']"));
			clickOn(lstTopics, "Topic Values");
			enterText(txtMetricName, "Mtric Name", data.get("MetricName"));
			clickOn(drpMetricType, "Metric Type");
			WebElement lstMetricTypes = driver.findElement(By
					.xpath("//div[@id='menu-metricType']//ul/li[contains(text(),'" + data.get("MetricTypes") + "')]"));
			jsClick(lstMetricTypes, "Topic Values");
			enterText(txtInputOptions, "Input Options", "Test Input Options");
			clickOn(drpUnit, "Unit dropdown");
			WebElement lstUnits = driver.findElement(
					By.xpath("//div[@id='menu-metricUoM']//ul/li[contains(text(),'" + data.get("Unit") + "')]"));
			clickOn(lstUnits, "Unit Values");
			clickOn(txtInstructions, "Instructions");
			enterText(txtInstructions, "Instructions", data.get("Instructions"));
			// Validations
			clickOn(drpValidationMethod, "Validation Method dropdown");
			if (data.get("ValidationMethod").trim().equalsIgnoreCase("Explanation Mandatory")) {
				WebElement lstExplanationMandatory = driver.findElement(By.xpath(
						"//div[@id='menu-validationMethodRule']//ul/li[contains(text(),'Explanation Mandatory')]"));
				jsClick(lstExplanationMandatory, "Validation Method Values");
				String weExplanationMandatoryValue = driver.findElement(By.xpath(
						"//input[@value='Explanation Mandatory']/parent::div/parent::div/following-sibling::div//p"))
						.getText();
				if (weExplanationMandatoryValue.trim().equalsIgnoreCase("True")) {
					passed("Explanation Mandatory values displayed as True");
				} else {
					failed(driver, "Explanation Mandatory values is not displayed as True");
				}
			} else if (data.get("ValidationMethod").trim().equalsIgnoreCase("Mandatory Evidence")) {
				WebElement lstMandatoryEvidence = driver.findElement(By
						.xpath("//div[@id='menu-validationMethodRule']//ul/li[contains(text(),'Mandatory Evidence')]"));
				jsClick(lstMandatoryEvidence, "Validation Method Values");
				String weExplanationMandatoryValue = driver.findElement(By.xpath(
						"//input[@value='Mandatory Evidence']/parent::div/parent::div/following-sibling::div//p"))
						.getText();
				if (weExplanationMandatoryValue.trim().equalsIgnoreCase("Required")) {
					passed("Explanation Mandatory values displayed as Required");
				} else {
					failed(driver, "Explanation Mandatory values is not displayed as Required");
				}
			} else if (data.get("ValidationMethod").trim().equalsIgnoreCase("Expression")) {
				WebElement lstExpression = driver.findElement(
						By.xpath("//div[@id='menu-validationMethodRule']//ul/li[contains(text(),'Expression')]"));
				jsClick(lstExpression, "Validation Method Values");
				jsClick(txtExpressionValue, "ExpressionValue");
				enterText(txtExpressionValue, "ExpressionValue", "Test ExpressionValue");
				enterText(txtCustomMessage, "CustomMessage", "Test txtCustomMessage");
			} else if (data.get("ValidationMethod").trim().equalsIgnoreCase("Total")) {
				WebElement lstTotal = driver.findElement(
						By.xpath("//div[@id='menu-validationMethodRule']//ul/li[contains(text(),'Total')]"));
				jsClick(lstTotal, "Validation Method Values");
				jsClick(drpTotalMultiSelect, "Validation Method Values");
			} else if (data.get("ValidationMethod").trim().equalsIgnoreCase("Threshold")) {
				WebElement lstThreshold = driver
						.findElement(By.xpath("//div[@id='menu-validationMethodRule']//ul/li[text()='Threshold']"));
				jsClick(lstThreshold, "Validation Method Values");
				enterText(txtThresholdMinVal, "Threshold Min Val", "Threshold");
				enterText(txtThresholdMaxValue, "Threshold Min Val", "Threshold");
			} else if (data.get("ValidationMethod").trim().equalsIgnoreCase("Threshold % of Previous Value")) {
				WebElement lstThresholdPercentage = driver.findElement(By.xpath(
						"//div[@id='menu-validationMethodRule']//ul/li[text()='Threshold % of Previous Value']"));
				jsClick(lstThresholdPercentage, "Validation Method Values");
				enterText(txtThresholdMinVal, "Threshold Min Val", "Threshold");
				enterText(txtThresholdMaxValue, "Threshold Min Val", "Threshold");
			} else if (data.get("ValidationMethod").trim()
					.equalsIgnoreCase("Threshold % of Value for Previous Period")) {
				WebElement lstThresholdPercentage = driver.findElement(By.xpath(
						"//div[@id='menu-validationMethodRule']//ul/li[text()='Threshold % of Value for Previous Period']"));
				jsClick(lstThresholdPercentage, "Validation Method Values");
				enterText(txtThresholdMinVal, "Threshold Min Val", "Threshold");
				enterText(txtThresholdMaxValue, "Threshold Min Val", "Threshold");
			}
			clickOn(btnSaveMetric, "Save Metric");
			verifyIfElementPresent(weMsgMetricSavedSuccessfully, "Metric Saved Successfully", "Create Metric");
			clickOn(btnOk, "Ok");
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyMetricInputTypeElements() {
		try {
			Random rnd = new Random();
			int max = 10000, min = 1, num1;
			num1 = rnd.nextInt((max - min) + 1) + min;
			String[] elements = { "Boolean", "Text", "MultiSelect", "SingleSelect", "Number", "Integer", "Description",
					"Table" };
			verifyAddTopic(data.get("TopicName") + num1);
			jsClick(btnAddMetric, "Add Metric");
			verifyIfElementPresent(lblMetricDetails, "Metric Details", "Create Metric");
			clickOn(drpInputType, "Input Type dropdown");
			List<WebElement> lstInputTypes = driver.findElements(By.xpath("//div[@id='menu-type']//ul/li"));
			if (lstInputTypes.size() == elements.length) {
				passed("Input elements are displayed as expected and values are ==> " + elements);
			} else {
				failed(driver, "Input elements are not displayed as expected and expected values are ==> " + elements
						+ " actual values are ==> " + lstInputTypes.toArray());
			}
			for (int i = 0; i < lstInputTypes.size(); i++) {
				String sInputType = lstInputTypes.get(i).getAttribute("data-value");
				if (sInputType.equals(elements[i])) {
					passed("Input elements is displayed as expected and value is ==> " + elements);
				} else {
					failed(driver, "Input elements is not displayed as expected and expected value is ==> " + elements
							+ " actual value is ==> " + sInputType);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//button[@aria-label='Filter']")
	private WebElement btnFilter;
	@FindBy(xpath = "//span[text()='Filters']")
	private WebElement lblFilters;
	@FindBy(xpath = "//h6[text()='Period']/parent::div/parent::div/following-sibling::div//div")
	private WebElement drpPeriod;
	// @FindBy(xpath =
	// "//h6[text()='Period']/parent::div/parent::div/following-sibling::div//div/*[@data-testid='ArrowDropDownIcon']")
	//@FindBy(xpath = "//h6[text()='Period']/parent::div/parent::div/following-sibling::div//div/*[@data-testid='ArrowDropDownIcon']")
	@FindBy(xpath = "//h6[text()='Task']/parent::div/parent::div/following-sibling::div//div")
	private WebElement drpTasks;


	public void selectPeriodFromCatalogDetailsPage(String periodName) {
		try {
			clickOn(btnFilter, "Filter");
			verifyIfElementPresent(lblFilters, "Filters", "Filters");
			verifyIfElementPresent(drpPeriod, "Period Filters", "Period Filters");
			clickOn(drpPeriod, "Period Dropdown");
			System.out.println("sdf");
			WebElement wePeriodName = driver
					.findElement(By.xpath("//ul[@role='tree']/li/div/div//div[text()='" + periodName + "']"));
			clickOn(wePeriodName, periodName);
			clickOn(btnClose, "Filters Close");
			sleep(1000);
		} catch (Exception e) {
			failed(driver, "Exception Caught As" + e.getMessage());
		}
	}

	public void catalogFilters(String periodName) {
		try {
			clickOn(btnFilter, "Filter");
			verifyIfElementPresent(lblFilters, "Filters", "Filters");
			if (periodName != "") {
				verifyIfElementPresent(drpPeriod, "Period Filters", "Period Filters");
				clickOn(drpPeriod, "Period Dropdown");
				WebElement wePeriodName = driver.findElement(By.xpath("//li//div[text()='" + periodName + "']"));
				clickOn(wePeriodName, periodName);
			}
			clickOn(btnClose, "Filters Close");
		} catch (Exception e) {
			failed(driver, "Exception Caught As" + e.getMessage());
		}
	}

	public void catalogFilters(String periodName, String sDDorDR, String dataRequestOrDataDestination) {
		try {
			clickOn(btnFilter, "Filter");
			verifyIfElementPresent(lblFilters, "Filters", "Filters");
			if (periodName != "") {
				verifyIfElementPresent(drpPeriod, "Period Filters", "Period Filters");
				clickOn(drpPeriod, "Period Dropdown");
				WebElement wePeriodName = driver.findElement(By.xpath("//li//div[text()='" + periodName + "']"));
				clickOn(wePeriodName, periodName);
			}
			if (sDDorDR != "") {
				if (sDDorDR.equals("DR") || sDDorDR.equals("Data Request")) {
					if (dataRequestOrDataDestination != "") {
						verifyIfElementPresent(drpDataRequest, "Period Filters", "Period Filters");
						clickOn(drpDataRequest, "Period Dropdown");
						WebElement wePeriodName = driver.findElement(
								By.xpath("//ul[@role='listbox']/li[text()='" + dataRequestOrDataDestination + "']"));
						clickOn(wePeriodName, periodName);
					}
				} else {
					verifyIfElementPresent(drpDataDestination, "Period Filters", "Period Filters");
					clickOn(drpDataDestination, "Period Dropdown");
					WebElement weDataDestination = driver
							.findElement(By.xpath("//li//div[text()='" + periodName + "']"));
					clickOn(weDataDestination, dataRequestOrDataDestination);
				}
			}
			clickOn(btnClose, "Filters Close");
		} catch (Exception e) {
			failed(driver, "Exception Caught As" + e.getMessage());
		}
	}

	@FindBy(xpath = "//h6[text()='Data Request']/parent::div/parent::div/following-sibling::div//div[@role='button']")
	private WebElement drpDataRequest;
	@FindBy(xpath = "//h6[text()='Metric Groups']/parent::div/parent::div/following-sibling::div//div[@id='multiSelect']")
	private WebElement drpMetricGroups;
	@FindBy(xpath = "//h6[text()='Data Destination']/parent::div/parent::div/following-sibling::div//div[@role='button']")
	private WebElement drpDataDestination;

	public void catalogFilters(String periodName, String DDorDR, String dataRequestOrDataDestination,
			List<String> metricGroups) {
		try {
			clickOn(btnFilter, "Filter");
			verifyIfElementPresent(lblFilters, "Filters", "Filters");
			catalogFilters(periodName);
			catalogFilters(periodName, DDorDR, dataRequestOrDataDestination);
			if (metricGroups.size() != 0) {
				clickOn(btnFilter, "Filter");
				verifyIfElementPresent(drpMetricGroups, "Period Filters", "Period Filters");
				for (int i = 0; i < metricGroups.size(); i++) {
					clickOn(drpMetricGroups, "Period Dropdown");
				}
			}
			clickOn(btnClose, "Filters Close");
		} catch (Exception e) {
			failed(driver, "Exception Caught As" + e.getMessage());
		}
	}

	@FindBy(xpath = "//span[contains(text(),'Completed:')]")
	private WebElement weCompletedFilter;
	@FindBy(xpath = "//span[contains(text(),'Required:')]")
	private WebElement weRequiredFilter;
	@FindBy(xpath = "//span[contains(text(),'Unanswered:')]")
	private WebElement weUnansweredFilter;
	@FindBy(xpath = "//span[contains(text(),'No Data Available:')]")
	private WebElement weNoDataAvailableFilter;
	@FindBy(xpath = "//span[contains(text(),'Metric Validation Errors:')]")
	private WebElement weMetricValidationErrorsFilter;

	public void catalogStatusFilters() {
	}

	int completedCount, requiredCount, unansweredCount, noDataAvailableCount, metricValidationErrorsCount;

	public HashMap<String, Integer> getCatalogFilterStatusCounts() {
		HashMap<String, Integer> filterStatusCount = new HashMap<>();
		String completed, required, unanswered, noDataAvailable, metricValidationErrors;
		String[] completedArr, requiredArr, unansweredArr, noDataAvailableArr, metricValidationErrorsArr;
		completed = weCompletedFilter.getText();
		required = weRequiredFilter.getText();
		unanswered = weUnansweredFilter.getText();
		noDataAvailable = weNoDataAvailableFilter.getText();
		metricValidationErrors = weMetricValidationErrorsFilter.getText();
		completedArr = completed.split(" ");
		requiredArr = required.split(" ");
		unansweredArr = unanswered.split(" ");
		noDataAvailableArr = noDataAvailable.split(" ");
		metricValidationErrorsArr = metricValidationErrors.split(" ");
		completedCount = Integer.parseInt(completedArr[1]);
		requiredCount = Integer.parseInt(requiredArr[1]);
		unansweredCount = Integer.parseInt(unansweredArr[1]);
		noDataAvailableCount = Integer.parseInt(noDataAvailableArr[3]);
		metricValidationErrorsCount = Integer.parseInt(metricValidationErrorsArr[3]);
		System.out.println("");
		filterStatusCount.put("completed", completedCount);
		filterStatusCount.put("required", requiredCount);
		filterStatusCount.put("unanswered", unansweredCount);
		filterStatusCount.put("noDataAvailable", noDataAvailableCount);
		filterStatusCount.put("metricValidationErrors", metricValidationErrorsCount);
		return filterStatusCount;
	}

	@FindBy(xpath = "//h6[text()='Additional Configuration']")
	private WebElement lblAdditionalConfiguration;

	public void verifyAddingMetricsForTotalValidationMethod() {
		try {
			String topicName = "Test Topic1";
			WebElement lstInputType, lstTopics;
			addMetric(topicName, "Number A1");
			addMetric(topicName, "Number b1");
			// Metric Total
			jsClick(btnAddMetric, "Add Metric");
			verifyIfElementPresent(lblMetricDetails, "Metric Details", "Create Metic");
			clickOn(drpInputType, "Input Type dropdown");
			lstInputType = driver.findElement(By.xpath("//div[@id='menu-type']//ul/li[contains(text(),'Number')]"));
			clickOn(lstInputType, "INput Type");
			clickOn(drpTopic, "Topic dropdown");
			lstTopics = driver.findElement(By.xpath("//div[@id='menu-topicId']//li[text()='Test Topic1']"));
			clickOn(lstTopics, "Topic Values");
			enterText(txtMetricName, "Mtric Name", "Total Nunber Value T1: ");
			clickOn(txtInstructions, "Instructions");
			enterText(txtInstructions, "Instructions", "Calculate total of numbers");
			// Total VLidation Method
			clickOn(drpValidationMethod, "Validation Method dropdown");
			WebElement lstTotal = driver
					.findElement(By.xpath("//div[@id='menu-validationMethodRule']//ul/li[contains(text(),'Total')]"));
			jsClick(lstTotal, "Validation Method Values");
			jsClick(drpTotalMultiSelect, "drp Total Multi Select");
			Actions builder = new Actions(driver);
			builder.moveToElement(drpTotalMultiSelect).click().build().perform();
			System.out.println(driver.getPageSource());
			Thread.sleep(2000);
			WebElement slcMulitpleNumA = driver.findElement(By.xpath("//*[text()='Number A1']"));
			// jsClick(slcMulitpleNumA, "Number A");
			builder.moveToElement(slcMulitpleNumA).click().build().perform();
			Thread.sleep(2000);
			WebElement slcMulitpleNumB = driver.findElement(By.xpath("//*[text()='Number B1']"));
			// jsClick(slcMulitpleNumA, "Number B");
			// builder.moveToElement(drpTotalMultiSelect).click().build().perform();
			builder.moveToElement(slcMulitpleNumB).click().build().perform();
			Thread.sleep(2000);
			clickOn(lblAdditionalConfiguration, "lblAdditionalConfiguration");
			System.out.println(driver.getPageSource());
			// jsClick(drpAggregationRules, "drp Aggregation Rules");
			builder.moveToElement(drpAggregationRules).click().build().perform();
			WebElement lstExplanationMandatory = driver
					.findElement(By.xpath("//div[@id='menu-aggregation']//ul/li[contains(text(),'Sum')]"));
			jsClick(lstExplanationMandatory, "Aggregation Rules Values");
			clickOn(btnSaveMetric, "Save Metric");
			verifyIfElementPresent(weMsgMetricSavedSuccessfully, "Metric Saved Successfully", "Create Metric");
			clickOn(btnOk, "Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addMetricOld(String topicName, String metricName) {
		WebElement lstInputType, lstTopics;
		try {
			// Metric N number
			jsClick(btnAddMetric, "Add Metric");
			verifyIfElementPresent(lblMetricDetails, "Metric Details", "Create Metic");
			clickOn(drpInputType, "Input Type dropdown");
			lstInputType = driver.findElement(By.xpath("//div[@id='menu-type']//ul/li[contains(text(),'Number')]"));
			clickOn(lstInputType, "INput Type");
			clickOn(drpTopic, "Topic dropdown");
			lstTopics = driver.findElement(By.xpath("//div[@id='menu-topicId']//li[text()='" + topicName + "']"));
			clickOn(lstTopics, "Topic Values");
			enterText(txtMetricName, "Mtric Name", metricName);
			clickOn(txtInstructions, "Instructions");
			enterText(txtInstructions, "Instructions", "Enter value for " + metricName);
			clickOn(btnSaveMetric, "Save Metric");
			verifyIfElementPresent(weMsgMetricSavedSuccessfully, "Metric Saved Successfully", "Create Metric");
			clickOn(btnOk, "Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addMetricForExpressionValidationMethods(String topicName, String metricName, String expressions,
			String inputType) {
		WebElement lstInputType, lstTopics;
		try {
			// Metric Total
			jsClick(btnAddMetric, "Add Metric");
			verifyIfElementPresent(lblMetricDetails, "Metric Details", "Create Metic");
			clickOn(drpInputType, "Input Type dropdown");
			lstInputType = driver
					.findElement(By.xpath("//div[@id='menu-type']//ul/li[contains(text(),'" + inputType + "')]"));
			clickOn(lstInputType, "INput Type");
			clickOn(drpTopic, "Topic dropdown");
			lstTopics = driver.findElement(By.xpath("//div[@id='menu-topicId']//li[text()='" + topicName + "']"));
			clickOn(lstTopics, "Topic Values");
			enterText(txtMetricName, "Mtric Name", metricName);
			clickOn(txtInstructions, "Instructions");
			enterText(txtInstructions, "Instructions", "Enter value for " + metricName);
			// Total VLidation Method
			clickOn(drpValidationMethod, "Validation Method dropdown");
			WebElement lstTotal = driver.findElement(
					By.xpath("//div[@id='menu-validationMethodRule']//ul/li[contains(text(),'Expression')]"));
			jsClick(lstTotal, "Validation Method Values");
			enterText(txtExpressionValue, "ExpressionValue", expressions);
			enterText(txtCustomMessage, "ExpressionValue", "Value should be between 0 to 9");
			clickOn(btnSaveMetric, "Save Metric");
			verifyIfElementPresent(weMsgMetricSavedSuccessfully, "Metric Saved Successfully", "Create Metric");
			clickOn(btnOk, "Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyAdditionOfTwoMetricsOfTypeTotalValidationMethod() {
		String strTopic, strMetric;
		Random rnd = new Random();
		int max = 1000, min = 1, num1, num2, total;
		num1 = rnd.nextInt((max - min) + 1) + min;
		num2 = rnd.nextInt((max - min) + 1) + min;
		// strTopic = data.get("TopicName")
		strTopic = "Test Topic1";
		if (!navigateToCatalogPage()) {
			return;
		}
		if (!selectExisitingCatalogCard("TestAutCatalog_dav651")) {
			return;
		}
		selectPeriodFromCatalogDetailsPage("202226");
		WebElement expandTopic = driver.findElement(By.xpath("//span[text()='" + strTopic
				+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
		clickOn(expandTopic, "Expand Topic ==> " + strTopic);
		SelectMetricValueForRespectiveMetric("Number A1");
		enterDataValueForNumberType(num1);
		SelectMetricValueForRespectiveMetric("Number B1");
		enterDataValueForNumberType(num2);
		driver.navigate().refresh();
		selectPeriodFromCatalogDetailsPage("202226");
		expandTopic = driver.findElement(By.xpath("//span[text()='" + strTopic
				+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
		clickOn(expandTopic, "Expand Topic ==> " + strTopic);
		WebElement weTotalVal = driver.findElement(By.xpath(
				"//span[text()='Total Nunber Value T1:']/ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']//span[1]"));
		String totalval = weTotalVal.getText();
		total = Integer.parseInt(totalval);
		Assert.assertEquals(total, num1 + num2);
	}

	public void verifyTotalValidationMethod() {
		HashMap<String, Integer> InitialStatusCount = new HashMap<>();
		int unansweredCnt, completedCnt;
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		verifyAddTopic("Test Topic1");
		verifyAddingMetricsForTotalValidationMethod();
		if (!navigateToCatalogPage()) {
			return;
		}
		if (!selectExisitingCatalogCard("threshold_1311_pr_04")) {
			return;
		}
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		if (unansweredCnt == 3) {
			passed("unanswered count is 3");
		} else {
			failed(driver, "unanswered count is not 3");
		}
		verifyAdditionOfTwoMetricsOfTypeTotalValidationMethod();
		if (!navigateToCatalogPage()) {
			return;
		}
		if (!selectExisitingCatalogCard("TestAutCatalog_dav651")) {
			return;
		}
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		completedCnt = InitialStatusCount.get("completed");
		if (completedCnt == 3) {
			passed("completed count is 3");
		} else {
			failed(driver, "completed count is not 3");
		}
		unansweredCnt = InitialStatusCount.get("unanswered");
		if (unansweredCnt == 0) {
			passed("unanswered count is 0");
		} else {
			failed(driver, "unanswered count is not 0");
		}
	}

	public void verifyTotalValidationMethodForMulitpleNumbers() {
		HashMap<String, Integer> InitialStatusCount = new HashMap<>();
		LinkedHashMap<String, Integer> metrics = new LinkedHashMap<>();
		int unansweredCnt, completedCnt;
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		verifyAddTopic("Test Topic1");
		metrics = verifyAddingMultipleMetricsForTotalValidationMethod();
		if (!navigateToCatalogPage()) {
			return;
		}
		if (!selectExisitingCatalogCard("TestAutCatalog_zjw209")) {
			return;
		}
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		if (unansweredCnt == metrics.size() + 1) {
			passed("unanswered count is " + metrics.size() + 1);
		} else {
			failed(driver, "unanswered count is not " + metrics.size() + 1);
		}
		verifyAdditionOfMultipleMetricsOfTypeTotalValidationMethod(metrics);
		if (!navigateToCatalogPage()) {
			return;
		}
		if (!selectExisitingCatalogCard("TestAutCatalog_zjw209")) {
			return;
		}
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		completedCnt = InitialStatusCount.get("completed");
		if (completedCnt == metrics.size() + 1) {
			passed("completed count is " + completedCnt);
		} else {
			failed(driver, "completed count is not " + metrics.size() + 1);
		}
		unansweredCnt = InitialStatusCount.get("unanswered");
		if (unansweredCnt == 0) {
			passed("unanswered count is 0");
		} else {
			failed(driver, "unanswered count is not 0");
		}
	}

	public LinkedHashMap<String, Integer> verifyAddingMultipleMetricsForTotalValidationMethod() {
		LinkedHashMap<String, Integer> metrics = new LinkedHashMap<>();
		try {
			Random rnd = new Random();
			String topicName = "Test Topic1";
			String totalMetricName = "Total Nunber Value :";
			WebElement lstInputType, lstTopics;
			int max = 1000, min = 1, num, num1, num2, total;
			int metricMin = 2, metricMax = 10, metricsCount;
			metricsCount = rnd.nextInt((metricMax - metricMin) + 1) + metricMin;
			for (int i = 1; i <= metricsCount; i++) {
				num = rnd.nextInt((max - min) + 1) + min;
				metrics.put("Number " + i, num);
				addMetric(topicName, "Number " + i);
			}
			// Metric Total
			jsClick(btnAddMetric, "Add Metric");
			verifyIfElementPresent(lblMetricDetails, "Metric Details", "Create Metic");
			clickOn(drpInputType, "Input Type dropdown");
			lstInputType = driver.findElement(By.xpath("//div[@id='menu-type']//ul/li[contains(text(),'Number')]"));
			clickOn(lstInputType, "INput Type");
			clickOn(drpTopic, "Topic dropdown");
			lstTopics = driver.findElement(By.xpath("//div[@id='menu-topicId']//li[text()='" + topicName + "']"));
			clickOn(lstTopics, "Topic Values");
			enterText(txtMetricName, "Mtric Name", totalMetricName);
			clickOn(txtInstructions, "Instructions");
			enterText(txtInstructions, "Instructions", "Calculate total of numbers of " + metricsCount + " metrics");
			// Total VLidation Method
			clickOn(drpValidationMethod, "Validation Method dropdown");
			WebElement lstTotal = driver
					.findElement(By.xpath("//div[@id='menu-validationMethodRule']//ul/li[contains(text(),'Total')]"));
			jsClick(lstTotal, "Validation Method Values");
			jsClick(drpTotalMultiSelect, "drp Total Multi Select");
			Actions builder = new Actions(driver);
			builder.moveToElement(drpTotalMultiSelect).click().build().perform();
			Thread.sleep(2000);
			for (int j = 1; j <= metricsCount; j++) {
				WebElement slcMulitpleNum = driver.findElement(By.xpath("//*[text()='" + "Number " + j + "']"));
				builder.moveToElement(slcMulitpleNum).click().build().perform();
				Thread.sleep(2000);
				System.out.println("696 " + "Number " + j);
			}
			// WebElement we =
			// driver.findElement(By.xpath("//input[@value='Total']/parent::div/parent::div/following-sibling::div/div[@id='multiSelectMetrics']//div[text()='Number
			// "+metricsCount+"']"));
			WebElement we = driver.findElement(By.xpath(
					"//input[@value='Total']/parent::div/parent::div/following-sibling::div/div[@id='multiSelectMetrics']//div[contains(@class,'indicatorContainer')][2]"));
			builder.moveToElement(we).click().build().perform();
			System.out.println("700 ");
			// jsClick(we, "701");
			Actions action = new Actions(driver);
			// action.sendKeys(Keys.ESCAPE);
			action.doubleClick(txtInstructions);
			System.out.println("700 ");
			// System.out.println("701 ");
			// //jsClick(txtMetricName, "txtMetricName");
			// clickOn(lblMetricDetails, "Metric details");
			// jsClick(lblAdditionalConfiguration, "lblAdditionalConfiguration");
			// //jsClick(drpAggregationRules, "drp Aggregation Rules");
			// //builder.moveToElement(drpAggregationRules).click().build().perform();
			// jsClick(drpAggregationRules, "drpAggregationRules");
			builder.moveToElement(drpAggregationRules).click().build().perform();
			WebElement lstExplanationMandatory = driver
					.findElement(By.xpath("//div[@id='menu-aggregation']//ul/li[contains(text(),'Sum')]"));
			jsClick(lstExplanationMandatory, "Aggregation Rules Values");
			clickOn(btnSaveMetric, "Save Metric");
			verifyIfElementPresent(weMsgMetricSavedSuccessfully, "Metric Saved Successfully", "Create Metric");
			clickOn(btnOk, "Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return metrics;
	}

	int actTotalValue = 0;

	public void verifyAdditionOfMultipleMetricsOfTypeTotalValidationMethod(LinkedHashMap<String, Integer> metrics) {
		String strTopic, strMetric;
		int total = 0;
		String totalMetricName = "Total Nunber Value :";
		// Random rnd = new Random();
		// int max = 1000,min=1,num1,num2,total;
		// num1 = rnd.nextInt((max - min) + 1) + min;
		// num2 = rnd.nextInt((max - min) + 1) + min;
		// strTopic = data.get("TopicName")
		strTopic = "Test Topic1";
		if (!navigateToCatalogPage()) {
			return;
		}
		if (!selectExisitingCatalogCard("TestAutCatalog_zjw209")) {
			return;
		}
		selectPeriodFromCatalogDetailsPage("202226");
		WebElement expandTopic = driver.findElement(By.xpath("//span[text()='" + strTopic
				+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
		clickOn(expandTopic, "Expand Topic ==> " + strTopic);
		metrics.forEach((key, value) -> {
			SelectMetricValueForRespectiveMetric(key);
			enterDataValueForNumberType(value);
			actTotalValue += value;
		});
		driver.navigate().refresh();
		selectPeriodFromCatalogDetailsPage("202226");
		expandTopic = driver.findElement(By.xpath("//span[text()='" + strTopic
				+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
		clickOn(expandTopic, "Expand Topic ==> " + strTopic);
		WebElement weTotalVal = driver.findElement(By.xpath("//span[text()='" + totalMetricName
				+ "']/ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']//span[1]"));
		String totalval = weTotalVal.getText();
		total = Integer.parseInt(totalval);
		Assert.assertEquals(total, actTotalValue);
	}

	public void verifyTotalValidationMethodError() {
		HashMap<String, Integer> InitialStatusCount = new HashMap<>();
		int unansweredCnt, completedCnt;
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		verifyAddTopic("Test Topic1");
		verifyAddingMetricsForTotalValidationMethod();
		if (!navigateToCatalogPage()) {
			return;
		}
		if (!selectExisitingCatalogCard("TestAutCatalog_qbb625")) {
			return;
		}
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		if (unansweredCnt == 3) {
			passed("unanswered count is 3");
		} else {
			failed(driver, "unanswered count is not 3");
		}
		// verifyAdditionOfTwoMetricsOfTypeTotalValidationMethod();
		String strTopic = "Test Topic1";
		WebElement expandTopic = driver.findElement(By.xpath("//span[text()='" + strTopic
				+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
		clickOn(expandTopic, "Expand Topic ==> " + strTopic);
		SelectMetricValueForRespectiveMetric("Number A1");
		enterDataValueForNumberType(1);
		// driver.navigate().refresh();
		// selectPeriodFromCatalogDetailsPage("202226");
		expandTopic = driver.findElement(By.xpath("//span[text()='" + strTopic
				+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
		clickOn(expandTopic, "Expand Topic ==> " + strTopic);
		WebElement weTotalVal = driver.findElement(By.xpath(
				"//span[text()='Total Nunber Value T1:']/ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']//span[1]"));
		String totalval = weTotalVal.getText();
		if (!navigateToCatalogPage()) {
			return;
		}
		if (!selectExisitingCatalogCard("TestAutCatalog_dav651")) {
			return;
		}
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		completedCnt = InitialStatusCount.get("completed");
		if (completedCnt == 2) {
			passed("completed count is 3");
		} else {
			failed(driver, "completed count is not 3");
		}
		unansweredCnt = InitialStatusCount.get("unanswered");
		if (unansweredCnt == 1) {
			passed("unanswered count is 0");
		} else {
			failed(driver, "unanswered count is not 0");
		}
	}

	public void verifyMandatoryEvidenceValidationMethod() {
		HashMap<String, Integer> InitialStatusCount = new HashMap<>();
		int unansweredCnt, completedCnt, metricValidationErrorsCnt;
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		verifyAddTopic("Test Topic1");
		createMandatoryEvidenceMetricsValidationMethod();
		if (!navigateToCatalogPage()) {
			return;
		}
		if (!selectExisitingCatalogCard("TestAutCatalog_zjw209")) {
			return;
		}
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		metricValidationErrorsCnt = InitialStatusCount.get("metricValidationErrors");
		if (unansweredCnt == 1) {
			passed("unanswered count is 1");
		} else {
			failed(driver, "unanswered count is not 1");
		}
		if (metricValidationErrorsCnt == 1) {
			passed("unanswered count is 1");
		} else {
			failed(driver, "unanswered count is not 1");
		}
	}

	public void createMandatoryEvidenceMetricsValidationMethod() {
		// Metric A number
		try {
			// Metric Total
			jsClick(btnAddMetric, "Add Metric");
			verifyIfElementPresent(lblMetricDetails, "Metric Details", "Create Metic");
			clickOn(drpInputType, "Input Type dropdown");
			WebElement lstInputType = driver
					.findElement(By.xpath("//div[@id='menu-type']//ul/li[contains(text(),'Number')]"));
			clickOn(lstInputType, "INput Type");
			clickOn(drpTopic, "Topic dropdown");
			WebElement lstTopics = driver.findElement(By.xpath("//div[@id='menu-topicId']//li[text()='Test Topic1']"));
			clickOn(lstTopics, "Topic Values");
			enterText(txtMetricName, "Mtric Name", "Test MandatoryEvidenceMetric");
			clickOn(txtInstructions, "Instructions");
			enterText(txtInstructions, "Instructions", "Calculate total of numbers");
			// Total VLidation Method
			clickOn(drpValidationMethod, "Validation Method dropdown");
			WebElement lstTotal = driver.findElement(
					By.xpath("//div[@id='menu-validationMethodRule']//ul/li[contains(text(),'Mandatory Evidence')]"));
			jsClick(lstTotal, "Validation Method Values");
			clickOn(btnSaveMetric, "Save Metric");
			verifyIfElementPresent(weMsgMetricSavedSuccessfully, "Metric Saved Successfully", "Create Metric");
			clickOn(btnOk, "Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyExpresessionValidationMethod() {
		HashMap<String, Integer> InitialStatusCount = new HashMap<>();
		int unansweredCnt, completedCnt, metricValidationErrorsCnt;
		WebElement expandTopic;
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		verifyAddTopic("Test Topic1");
		createExpressionMetricsValidationMethod();
		if (!navigateToCatalogPage()) {
			return;
		}
		if (!selectExisitingCatalogCard("TestAutCatalog_dav651")) {
			return;
		}
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		metricValidationErrorsCnt = InitialStatusCount.get("metricValidationErrors");
		if (unansweredCnt == 1) {
			passed("unanswered count is 1");
		} else {
			failed(driver, "unanswered count is not 1");
		}
		if (metricValidationErrorsCnt == 1) {
			passed("unanswered count is 1");
		} else {
			failed(driver, "unanswered count is not 1");
		}
		String strTopic, strMetric;
		// strTopic = data.get("TopicName")
		strTopic = "Test Topic1";
		strMetric = "Test Expression Metric";
		// div[text()=' Test Topic1']
		expandTopic = driver.findElement(By.xpath("//div[text()=' " + strTopic
				+ "']/parent::span//preceding-sibling::span//ancestor::span[@ref='eValue']/preceding-sibling::span[@class='ag-group-contracted']/span"));
		if (!isElementPresent(expandTopic)) {
			expandTopic = driver.findElement(By.xpath("//*[text()='" + strTopic
					+ "']/parent::span//preceding-sibling::span/preceding-sibling::span[@class='ag-group-contracted']/span"));
		}
		clickOn(expandTopic, "Expand Topic ==> " + strTopic);
		// WebElement weTotalVal = driver.findElement(By.xpath("//span[text()='Total
		// Nunber Value
		// T1:']/ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']//span[1]"));
		// String totalval = weTotalVal.getText();
		SelectMetricValueForRespectiveMetric(strMetric);
		enterDataValueForNumberType(100);
		// verifyAdditionOfTwoMetricsOfTypeTotalValidationMethod();
		WebElement weErr = driver.findElement(By.xpath("//span[text()='" + strMetric
				+ "']/ancestor::div[@col-id='metricName']//following-sibling::div//*[@aria-label='Value should be between 0 to 9']"));
		verifyIfElementPresent(weErr, "Metric Error for Expression as ==> Value should be between 0 to 9 ",
				"Catalog Page");
		// if(!navigateToCatalogPage()){
		// return;
		// }
		// if(!selectExisitingCatalogCard("TestAutCatalog_dav651")){
		// return;
		// }
		// selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		completedCnt = InitialStatusCount.get("completed");
		if (completedCnt == 1) {
			passed("completed count is 3");
		} else {
			failed(driver, "completed count is not 3");
		}
		unansweredCnt = InitialStatusCount.get("unanswered");
		if (unansweredCnt == 0) {
			passed("unanswered count is 0");
		} else {
			failed(driver, "unanswered count is not 0");
		}
		metricValidationErrorsCnt = InitialStatusCount.get("metricValidationErrors");
		if (metricValidationErrorsCnt == 1) {
			passed("metricValidationErrorsCnt count is 1");
		} else {
			failed(driver, "metricValidationErrorsCnt count is not 1");
		}
	}

	public void createExpressionMetricsValidationMethod() {
		// Metric A number
		try {
			// Metric Total
			jsClick(btnAddMetric, "Add Metric");
			verifyIfElementPresent(lblMetricDetails, "Metric Details", "Create Metic");
			clickOn(drpInputType, "Input Type dropdown");
			WebElement lstInputType = driver
					.findElement(By.xpath("//div[@id='menu-type']//ul/li[contains(text(),'Number')]"));
			clickOn(lstInputType, "INput Type");
			clickOn(drpTopic, "Topic dropdown");
			WebElement lstTopics = driver.findElement(By.xpath("//div[@id='menu-topicId']//li[text()='Test Topic1']"));
			clickOn(lstTopics, "Topic Values");
			enterText(txtMetricName, "Mtric Name", "Test Expression Metric");
			clickOn(txtInstructions, "Instructions");
			enterText(txtInstructions, "Instructions", "Calculate total of numbers");
			// Total VLidation Method
			clickOn(drpValidationMethod, "Validation Method dropdown");
			WebElement lstTotal = driver.findElement(
					By.xpath("//div[@id='menu-validationMethodRule']//ul/li[contains(text(),'Expression')]"));
			jsClick(lstTotal, "Validation Method Values");
			enterText(txtExpressionValue, "ExpressionValue", "[0-9]");
			enterText(txtCustomMessage, "ExpressionValue", "Value should be between 0 to 9");
			clickOn(btnSaveMetric, "Save Metric");
			verifyIfElementPresent(weMsgMetricSavedSuccessfully, "Metric Saved Successfully", "Create Metric");
			clickOn(btnOk, "Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// https://pulsesg.atlassian.net/wiki/spaces/QK/pages/28836218/Validation+Methods
	public void verifyExpresessionValidationMethodForMultipleMetricsForNumAndIntegerType() {
		HashMap<String, Integer> InitialStatusCount = new HashMap<>();
		int unansweredCnt, completedCnt, metricValidationErrorsCnt;
		WebElement expandTopic;
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		verifyAddTopic("Test Topic1");
		createExpressionValidationMethodForMultipleMetricsForNumAndIntegerType();
		if (!navigateToCatalogPage()) {
			return;
		}
		if (!selectExisitingCatalogCard("TestAutCatalog_dav651")) {
			return;
		}
		// TODO: add verificaiton after checking with test team
		// selectPeriodFromCatalogDetailsPage("202226");
		// InitialStatusCount = getCatalogFilterStatusCounts();
		// unansweredCnt = InitialStatusCount.get("unanswered");
		// metricValidationErrorsCnt = InitialStatusCount.get("metricValidationErrors");
		// if(unansweredCnt==1){
		// passed("unanswered count is 1");
		// }else{
		// failed(driver,"unanswered count is not 1");
		// }
		//
		// if(metricValidationErrorsCnt==1){
		// passed("unanswered count is 1");
		// }else{
		// failed(driver,"unanswered count is not 1");
		// }
		// String strTopic,strMetric;
		// //strTopic = data.get("TopicName")
		// strTopic = "Test Topic1";
		// strMetric = "Test Expression Metric";
		// //div[text()=' Test Topic1']
		// expandTopic = driver.findElement(By.xpath("//div[text()='
		// "+strTopic+"']/parent::span//preceding-sibling::span//ancestor::span[@ref='eValue']/preceding-sibling::span[@class='ag-group-contracted']/span"));
		// //if error not present, change the xpath
		// if(!isElementPresent(expandTopic)){
		// expandTopic =
		// driver.findElement(By.xpath("//*[text()='"+strTopic+"']/parent::span//preceding-sibling::span/preceding-sibling::span[@class='ag-group-contracted']/span"));
		// }
		//
		// clickOn(expandTopic, "Expand Topic ==> "+strTopic);
		// // WebElement weTotalVal = driver.findElement(By.xpath("//span[text()='Total
		// Nunber Value
		// T1:']/ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']//span[1]"));
		// // String totalval = weTotalVal.getText();
		//
		// SelectMetricValueForRespectiveMetric(strMetric);
		// enterDataValueForNumberType(100);
		// //verifyAdditionOfTwoMetricsOfTypeTotalValidationMethod();
		//
		// WebElement weErr =
		// driver.findElement(By.xpath("//span[text()='"+strMetric+"']/ancestor::div[@col-id='metricName']//following-sibling::div//*[@aria-label='Value
		// should be between 0 to 9']"));
		// verifyIfElementPresent(weErr, "Metric Error for Expression as ==> Value
		// should be between 0 to 9 ", "Catalog Page");
		// // if(!navigateToCatalogPage()){
		// // return;
		// // }
		// // if(!selectExisitingCatalogCard("TestAutCatalog_dav651")){
		// // return;
		// // }
		// // selectPeriodFromCatalogDetailsPage("202226");
		// InitialStatusCount = getCatalogFilterStatusCounts();
		// completedCnt = InitialStatusCount.get("completed");
		// if(completedCnt==1){
		// passed("completed count is 3");
		// }else{
		// failed(driver,"completed count is not 3");
		// }
		// unansweredCnt = InitialStatusCount.get("unanswered");
		// if(unansweredCnt==0){
		// passed("unanswered count is 0");
		// }else{
		// failed(driver,"unanswered count is not 0");
		// }
		// metricValidationErrorsCnt = InitialStatusCount.get("metricValidationErrors");
		// if(metricValidationErrorsCnt==1){
		// passed("metricValidationErrorsCnt count is 1");
		// }else{
		// failed(driver,"metricValidationErrorsCnt count is not 1");
		// }
	}

	public void createExpressionValidationMethodForMultipleMetricsForNumAndIntegerType() {
		// Metric A number
		try {
			String strTopic, strMetric;
			strTopic = "Test Topic1";
			String[] numRegEx1 = { "[0-9]", "[]?", "[]+", "[]*", "[]{n}", "[]{n,}", "[]{y,z}", "[8 9]{0-9}{9}" };
			LinkedHashMap<String, String> numRegEx = new LinkedHashMap<>();
			numRegEx.put("0 to 9", "[0-9]");
			numRegEx.put("Occurs 0 or 1 time", "[]?");
			numRegEx.put("Occurs 1 or more time", "[]+");
			numRegEx.put("Occurs 0 or more time", "[]*");
			numRegEx.put("Occurs n time", "[]{n}");
			numRegEx.put("Occurs n or more time", "[]{n,}");
			numRegEx.put("Occurs atleast y time but less than z time", "[]{y,z}");
			numRegEx.put("Mobile Number - Start with 8 or 9 and total digits =10", "[8 9]{0-9}{9}");
			numRegEx.forEach((key, value) -> {
				addMetricForExpressionValidationMethods(strTopic, key, value, "Integer");
			});
			// addMetricForExpressionValidationMethods();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyExpresessionValidationMethodForMultipleMetricsForTextType() {
		HashMap<String, Integer> InitialStatusCount = new HashMap<>();
		int unansweredCnt, completedCnt, metricValidationErrorsCnt;
		WebElement expandTopic;
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		verifyAddTopic("Test Topic1");
		createExpressionValidationMethodForMultipleMetricsForTextType();
		if (!navigateToCatalogPage()) {
			return;
		}
		if (!selectExisitingCatalogCard("TestAutCatalog_qbb625")) {
			return;
		}
		// TODO: add verificaiton after checking with test team
		// selectPeriodFromCatalogDetailsPage("202226");
		// InitialStatusCount = getCatalogFilterStatusCounts();
		// unansweredCnt = InitialStatusCount.get("unanswered");
		// metricValidationErrorsCnt = InitialStatusCount.get("metricValidationErrors");
		// if(unansweredCnt==1){
		// passed("unanswered count is 1");
		// }else{
		// failed(driver,"unanswered count is not 1");
		// }
		//
		// if(metricValidationErrorsCnt==1){
		// passed("unanswered count is 1");
		// }else{
		// failed(driver,"unanswered count is not 1");
		// }
		// String strTopic,strMetric;
		// //strTopic = data.get("TopicName")
		// strTopic = "Test Topic1";
		// strMetric = "Test Expression Metric";
		// //div[text()=' Test Topic1']
		// expandTopic = driver.findElement(By.xpath("//div[text()='
		// "+strTopic+"']/parent::span//preceding-sibling::span//ancestor::span[@ref='eValue']/preceding-sibling::span[@class='ag-group-contracted']/span"));
		// //if error not present, change the xpath
		// if(!isElementPresent(expandTopic)){
		// expandTopic =
		// driver.findElement(By.xpath("//*[text()='"+strTopic+"']/parent::span//preceding-sibling::span/preceding-sibling::span[@class='ag-group-contracted']/span"));
		// }
		//
		// clickOn(expandTopic, "Expand Topic ==> "+strTopic);
		// // WebElement weTotalVal = driver.findElement(By.xpath("//span[text()='Total
		// Nunber Value
		// T1:']/ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']//span[1]"));
		// // String totalval = weTotalVal.getText();
		//
		// SelectMetricValueForRespectiveMetric(strMetric);
		// enterDataValueForNumberType(100);
		// //verifyAdditionOfTwoMetricsOfTypeTotalValidationMethod();
		//
		// WebElement weErr =
		// driver.findElement(By.xpath("//span[text()='"+strMetric+"']/ancestor::div[@col-id='metricName']//following-sibling::div//*[@aria-label='Value
		// should be between 0 to 9']"));
		// verifyIfElementPresent(weErr, "Metric Error for Expression as ==> Value
		// should be between 0 to 9 ", "Catalog Page");
		// // if(!navigateToCatalogPage()){
		// // return;
		// // }
		// // if(!selectExisitingCatalogCard("TestAutCatalog_dav651")){
		// // return;
		// // }
		// // selectPeriodFromCatalogDetailsPage("202226");
		// InitialStatusCount = getCatalogFilterStatusCounts();
		// completedCnt = InitialStatusCount.get("completed");
		// if(completedCnt==1){
		// passed("completed count is 3");
		// }else{
		// failed(driver,"completed count is not 3");
		// }
		// unansweredCnt = InitialStatusCount.get("unanswered");
		// if(unansweredCnt==0){
		// passed("unanswered count is 0");
		// }else{
		// failed(driver,"unanswered count is not 0");
		// }
		// metricValidationErrorsCnt = InitialStatusCount.get("metricValidationErrors");
		// if(metricValidationErrorsCnt==1){
		// passed("metricValidationErrorsCnt count is 1");
		// }else{
		// failed(driver,"metricValidationErrorsCnt count is not 1");
		// }
	}

	public void createExpressionValidationMethodForMultipleMetricsForTextType() {
		// Metric A number
		try {
			String strTopic, strMetric;
			strTopic = "Test Topic1";
			LinkedHashMap<String, String> textRegEx = new LinkedHashMap<>();
			textRegEx.put("a,b or c", "[abc]");
			textRegEx.put("any character except a,b,c", "[^abc]");
			textRegEx.put("a to z lowercase", "[a-z]");
			textRegEx.put("A to Z uppercase", "[A-Z]");
			textRegEx.put("a to z A to Z", "[a-z A-Z]");
			textRegEx.put("First character uppercase, contains lower case alphabets", "[A-Z][a-z]*[0-9][a-z]*");
			textRegEx.put("Email id", "[a-zA-Z0-9_-]+[@][a-z]+[.][a-z]{2,3}");
			textRegEx.forEach((key, value) -> {
				addMetricForExpressionValidationMethods(strTopic, key, value, "Text");
			});
			// addMetricForExpressionValidationMethods();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyThresholdValidationMethod() {
		HashMap<String, Integer> InitialStatusCount = new HashMap<>();
		int unansweredCnt, completedCnt, metricValidationErrorsCnt;
		WebElement expandTopic;
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		verifyAddTopic("Test Topic1");
		createThresholdMetricsValidationMethod();
		if (!navigateToCatalogPage()) {
			return;
		}
		if (!selectExisitingCatalogCard("TestAutCatalog_zjw209")) {
			return;
		}
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		metricValidationErrorsCnt = InitialStatusCount.get("metricValidationErrors");
		if (unansweredCnt == 1) {
			passed("unanswered count is 0");
		} else {
			failed(driver, "unanswered count is not 0");
		}
		if (metricValidationErrorsCnt == 0) {
			passed("unanswered count is 0");
		} else {
			failed(driver, "unanswered count is not 0");
		}
		String strTopic, strMetric;
		// strTopic = data.get("TopicName")
		strTopic = "Test Topic1";
		strMetric = "Test Threshold Metric";
		// div[text()=' Test Topic1']
		expandTopic = driver.findElement(By.xpath("//div[text()=' " + strTopic
				+ "']/parent::span//preceding-sibling::span//ancestor::span[@ref='eValue']/preceding-sibling::span[@class='ag-group-contracted']/span"));
		clickOn(expandTopic, "Expand Topic ==> " + strTopic);
		// WebElement weTotalVal = driver.findElement(By.xpath("//span[text()='Total
		// Nunber Value
		// T1:']/ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']//span[1]"));
		// String totalval = weTotalVal.getText();
		SelectMetricValueForRespectiveMetric(strMetric);
		enterDataValueForNumberType(90);
		// verifyAdditionOfTwoMetricsOfTypeTotalValidationMethod();
		// span[text()='test
		// thteshold']/ancestor::div[@col-id='metricName']//following-sibling::div//*[@aria-label='Threshold
		// Values - metric should be between (10.0 and 90.0)']
		WebElement weErr = driver.findElement(By.xpath("//span[text()='" + strMetric
				+ "']/ancestor::div[@col-id='metricName']//following-sibling::div//*[@aria-label='Threshold Values - metric should be between (10.0 and 90.0)']"));
		verifyIfElementPresent(weErr, "Metric Error for Threshold as ==> Value should be between 10 to 80 ",
				"Catalog Page");
		// if(!navigateToCatalogPage()){
		// return;
		// }
		// if(!selectExisitingCatalogCard("TestAutCatalog_dav651")){
		// return;
		// }
		// selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		completedCnt = InitialStatusCount.get("completed");
		if (completedCnt == 1) {
			passed("completed count is 3");
		} else {
			failed(driver, "completed count is not 3");
		}
		unansweredCnt = InitialStatusCount.get("unanswered");
		if (unansweredCnt == 0) {
			passed("unanswered count is 0");
		} else {
			failed(driver, "unanswered count is not 0");
		}
		metricValidationErrorsCnt = InitialStatusCount.get("metricValidationErrors");
		if (metricValidationErrorsCnt == 1) {
			passed("metricValidationErrorsCnt count is 1");
		} else {
			failed(driver, "metricValidationErrorsCnt count is not 1");
		}
	}

	public void createThresholdMetricsValidationMethod() {
		// Metric A number
		try {
			// Metric Total
			jsClick(btnAddMetric, "Add Metric");
			verifyIfElementPresent(lblMetricDetails, "Metric Details", "Create Metic");
			clickOn(drpInputType, "Input Type dropdown");
			WebElement lstInputType = driver
					.findElement(By.xpath("//div[@id='menu-type']//ul/li[contains(text(),'Number')]"));
			clickOn(lstInputType, "INput Type");
			clickOn(drpTopic, "Topic dropdown");
			WebElement lstTopics = driver.findElement(By.xpath("//div[@id='menu-topicId']//li[text()='Test Topic1']"));
			clickOn(lstTopics, "Topic Values");
			enterText(txtMetricName, "Mtric Name", "Test Threshold Metric");
			clickOn(txtInstructions, "Instructions");
			enterText(txtInstructions, "Instructions", "Threshold Min value and Max value");
			// Total VLidation Method
			clickOn(drpValidationMethod, "Validation Method dropdown");
			WebElement lstTotal = driver.findElement(
					By.xpath("//div[@id='menu-validationMethodRule']//ul/li[contains(text(),'Threshold')]"));
			jsClick(lstTotal, "Validation Method Values");
			enterText(txtThresholdMinVal, "Threshold Min Value", "10");
			enterText(txtThresholdMaxValue, "Threshold Max Value", "80");
			// enterText(txtCustomMessage, "ExpressionValue", "Value should be between 0 to
			// 9");
			clickOn(btnSaveMetric, "Save Metric");
			verifyIfElementPresent(weMsgMetricSavedSuccessfully, "Metric Saved Successfully", "Create Metric");
			clickOn(btnOk, "Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyThresholdPercentageOfPreviousValueMetricsValidationMethod() {
		HashMap<String, Integer> InitialStatusCount = new HashMap<>();
		int unansweredCnt, completedCnt, metricValidationErrorsCnt;
		WebElement expandTopic;
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		verifyAddTopic("Test Topic1");
		Random rnd = new Random();
		double max = 100, min = 5, num1, num2, total, thresholdMinPercent, thresholdMaxPercent;
		// num2 = rnd.nextInt((max - min) + 1) + min;
		num2 = ThreadLocalRandom.current().nextDouble(min, max);
		num1 = ThreadLocalRandom.current().nextDouble(min, max);
		num1 = Math.round(num1 * 100) / 100.0d;
		num2 = Math.round(num2 * 100) / 100.0d;
		while (num1 >= num2) {
			num1 = ThreadLocalRandom.current().nextDouble(min, num2);
			num1 = Math.round(num1 * 100) / 100.0d;
			if (num1 != num2) {
				break;
			}
		}
		thresholdMinPercent = num1;
		thresholdMaxPercent = num2;
		// thresholdMinPercent = 20;
		// thresholdMaxPercent = 50;
		createThresholdPercentageOfPreviousValueMetricsValidationMethod(thresholdMinPercent, thresholdMaxPercent);
		// createThresholdPercentageOfPreviousValueMetricsValidationMethod(20,50);
		if (!navigateToCatalogPage()) {
			return;
		}
		if (!selectExisitingCatalogCard("threshold_test_13_11")) {
			return;
		}
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		metricValidationErrorsCnt = InitialStatusCount.get("metricValidationErrors");
		if (unansweredCnt == 1) {
			passed("unanswered count is 0");
		} else {
			failed(driver, "unanswered count is not 0");
		}
		if (metricValidationErrorsCnt == 0) {
			passed("unanswered count is 0");
		} else {
			failed(driver, "unanswered count is not 0");
		}
		String strTopic, strMetric;
		// strTopic = data.get("TopicName")
		strTopic = "Test Topic1";
		strMetric = "Test Threshold Percentage Of Previous Value Metric8";
		// div[text()=' Test Topic1']
		expandTopic = driver.findElement(By.xpath("//span[text()='" + strTopic
				+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
		clickOn(expandTopic, "Expand Topic ==> " + strTopic);
		verifyThresholdPreviousValue(strMetric, thresholdMinPercent, thresholdMaxPercent);
		InitialStatusCount = getCatalogFilterStatusCounts();
		completedCnt = InitialStatusCount.get("completed");
		if (completedCnt == 1) {
			passed("completed count is 3");
		} else {
			failed(driver, "completed count is not 3");
		}
		unansweredCnt = InitialStatusCount.get("unanswered");
		if (unansweredCnt == 0) {
			passed("unanswered count is 0");
		} else {
			failed(driver, "unanswered count is not 0");
		}
		metricValidationErrorsCnt = InitialStatusCount.get("metricValidationErrors");
		if (metricValidationErrorsCnt == 0) {
			passed("metricValidationErrorsCnt count is 1");
		} else {
			failed(driver, "metricValidationErrorsCnt count is not 1");
		}
	}

	public void createThresholdPercentageOfPreviousValueMetricsValidationMethod(double min, double max) {
		try {
			jsClick(btnAddMetric, "Add Metric");
			verifyIfElementPresent(lblMetricDetails, "Metric Details", "Create Metic");
			clickOn(drpInputType, "Input Type dropdown");
			WebElement lstInputType = driver
					.findElement(By.xpath("//div[@id='menu-type']//ul/li[contains(text(),'Number')]"));
			clickOn(lstInputType, "INput Type");
			clickOn(drpTopic, "Topic dropdown");
			WebElement lstTopics = driver.findElement(By.xpath("//div[@id='menu-topicId']//li[text()='Test Topic1']"));
			clickOn(lstTopics, "Topic Values");
			enterText(txtMetricName, "Mtric Name", "Test Threshold Percentage Of Previous Value Metric8");
			clickOn(txtInstructions, "Instructions");
			enterText(txtInstructions, "Instructions", "Threshold Min % and Max %");
			// Total VLidation Method
			clickOn(drpValidationMethod, "Validation Method dropdown");
			WebElement lstTotal = driver.findElement(By.xpath(
					"//div[@id='menu-validationMethodRule']//ul/li[contains(text(),'Threshold % of Previous Value')]"));
			jsClick(lstTotal, "Validation Method Values");
			enterText(txtThresholdMinVal, "Threshold Min Value", Double.toString(min));
			enterText(txtThresholdMaxValue, "Threshold Max Value", Double.toString(max));
			// enterText(txtCustomMessage, "ExpressionValue", "Value should be between 0 to
			// 9");
			clickOn(btnSaveMetric, "Save Metric");
			verifyIfElementPresent(weMsgMetricSavedSuccessfully, "Metric Saved Successfully", "Create Metric");
			clickOn(btnOk, "Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyThresholdPreviousValue(String strMetric, double thresholdMinPercent, double thresholdMaxPercent) {
		try {
			double max = 100, min = 5, num1, num2, initialVal, lowerVal, higherVal, resultLow, resultHigh;
			// num2 = rnd.nextInt((max - min) + 1) + min;
			double dataVal = ThreadLocalRandom.current().nextDouble(min, max);
			dataVal = Math.round(dataVal * 100) / 100.0d;
			// enter initial val
			SelectMetricValueForRespectiveMetric(strMetric);
			enterDataValueForNumberType(dataVal);
			Random rnd = new Random();
			int minIteration = 2, maxIteration = 10, iterations;
			iterations = rnd.nextInt((maxIteration - minIteration) + 1) + minIteration;
			iterations = 10;
			for (int i = 0; i < iterations; i++) {
				resultLow = thresholdMinPercent / 100;
				resultLow = resultLow * dataVal;
				lowerVal = dataVal - resultLow;
				resultHigh = thresholdMaxPercent / 100;
				resultHigh = resultHigh * dataVal;
				higherVal = dataVal + resultHigh;
				dataVal = ThreadLocalRandom.current().nextDouble(lowerVal, higherVal);
				dataVal = Math.round(dataVal * 100) / 100.0d;
				SelectMetricValueForRespectiveMetric(strMetric);
				enterDataValueForNumberType(dataVal);
				System.out.println("lowerVal " + lowerVal);
				System.out.println("higherVal " + higherVal);
				System.out.println("dataVal " + dataVal);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyThresholdPercentageOfPreviousValueMetricsValidationMethodForInvalidDataVal() {
		HashMap<String, Integer> InitialStatusCount = new HashMap<>();
		int unansweredCnt, completedCnt, metricValidationErrorsCnt;
		WebElement expandTopic;
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		verifyAddTopic("Test Topic1");
		Random rnd = new Random();
		double max = 100, min = 5, num1, num2, total, thresholdMinPercent, thresholdMaxPercent;
		// num2 = rnd.nextInt((max - min) + 1) + min;
		num2 = ThreadLocalRandom.current().nextDouble(min, max);
		num1 = ThreadLocalRandom.current().nextDouble(min, max);
		num1 = Math.round(num1 * 100) / 100.0d;
		num2 = Math.round(num2 * 100) / 100.0d;
		while (num1 >= num2) {
			num1 = ThreadLocalRandom.current().nextDouble(min, num2);
			num1 = Math.round(num1 * 100) / 100.0d;
			if (num1 != num2) {
				break;
			}
		}
		thresholdMinPercent = num1;
		thresholdMaxPercent = num2;
		// thresholdMinPercent = 20;
		// thresholdMaxPercent = 50;
		createThresholdPercentageOfPreviousValueMetricsValidationMethodForInvalidDataVal(thresholdMinPercent,
				thresholdMaxPercent);
		// createThresholdPercentageOfPreviousValueMetricsValidationMethod(20,50);
		if (!navigateToCatalogPage()) {
			return;
		}
		if (!selectExisitingCatalogCard("threshold_1311_02")) {
			return;
		}
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		metricValidationErrorsCnt = InitialStatusCount.get("metricValidationErrors");
		if (unansweredCnt == 1) {
			passed("unanswered count is 0");
		} else {
			failed(driver, "unanswered count is not 0");
		}
		if (metricValidationErrorsCnt == 0) {
			passed("unanswered count is 0");
		} else {
			failed(driver, "unanswered count is not 0");
		}
		String strTopic, strMetric;
		// strTopic = data.get("TopicName")
		strTopic = "Test Topic1";
		strMetric = "Test Threshold Percentage Of Previous Value Metric17";
		// div[text()=' Test Topic1']
		expandTopic = driver.findElement(By.xpath("//span[text()='" + strTopic
				+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
		clickOn(expandTopic, "Expand Topic ==> " + strTopic);
		verifyThresholdPreviousValueForInvalidDataVal(strMetric, thresholdMinPercent, thresholdMaxPercent);
		InitialStatusCount = getCatalogFilterStatusCounts();
		completedCnt = InitialStatusCount.get("completed");
		if (completedCnt == 1) {
			passed("completed count is 3");
		} else {
			failed(driver, "completed count is not 3");
		}
		unansweredCnt = InitialStatusCount.get("unanswered");
		if (unansweredCnt == 0) {
			passed("unanswered count is 0");
		} else {
			failed(driver, "unanswered count is not 0");
		}
		metricValidationErrorsCnt = InitialStatusCount.get("metricValidationErrors");
		if (metricValidationErrorsCnt == 0) {
			passed("metricValidationErrorsCnt count is 1");
		} else {
			failed(driver, "metricValidationErrorsCnt count is not 1");
		}
	}

	public void createThresholdPercentageOfPreviousValueMetricsValidationMethodForInvalidDataVal(double min,
			double max) {
		try {
			jsClick(btnAddMetric, "Add Metric");
			verifyIfElementPresent(lblMetricDetails, "Metric Details", "Create Metic");
			clickOn(drpInputType, "Input Type dropdown");
			WebElement lstInputType = driver
					.findElement(By.xpath("//div[@id='menu-type']//ul/li[contains(text(),'Number')]"));
			clickOn(lstInputType, "INput Type");
			clickOn(drpTopic, "Topic dropdown");
			WebElement lstTopics = driver.findElement(By.xpath("//div[@id='menu-topicId']//li[text()='Test Topic1']"));
			clickOn(lstTopics, "Topic Values");
			enterText(txtMetricName, "Mtric Name", "Test Threshold Percentage Of Previous Value Metric17");
			clickOn(txtInstructions, "Instructions");
			enterText(txtInstructions, "Instructions", "Threshold Min % and Max %");
			// Total VLidation Method
			clickOn(drpValidationMethod, "Validation Method dropdown");
			WebElement lstTotal = driver.findElement(By.xpath(
					"//div[@id='menu-validationMethodRule']//ul/li[contains(text(),'Threshold % of Previous Value')]"));
			jsClick(lstTotal, "Validation Method Values");
			enterText(txtThresholdMinVal, "Threshold Min Value", Double.toString(min));
			enterText(txtThresholdMaxValue, "Threshold Max Value", Double.toString(max));
			// enterText(txtCustomMessage, "ExpressionValue", "Value should be between 0 to
			// 9");
			clickOn(btnSaveMetric, "Save Metric");
			verifyIfElementPresent(weMsgMetricSavedSuccessfully, "Metric Saved Successfully", "Create Metric");
			clickOn(btnOk, "Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyThresholdPreviousValueForInvalidDataVal(String strMetric, double thresholdMinPercent,
			double thresholdMaxPercent) {
		try {
			double max = 100, min = 5, num1, num2, initialVal, lowerVal, higherVal, resultLow, resultHigh;
			double dataVal = ThreadLocalRandom.current().nextDouble(min, max);
			dataVal = Math.round(dataVal * 100) / 100.0d;
			double prevdataVal;
			selectPeriodFromCatalogDetailsPage("202226");
			SelectMetricValueForRespectiveMetric(strMetric);
			enterDataValueForNumberType(dataVal);
			Random rnd = new Random();
			int minIteration = 2, maxIteration = 10, iterations;
			int rangeRnd = 0;
			iterations = rnd.nextInt((maxIteration - minIteration) + 1) + minIteration;
			iterations = 10;
			String[] thresholdRange = { "lower", "higher" };
			String range;
			for (int i = 0; i < iterations; i++) {
				range = thresholdRange[rnd.nextInt(thresholdRange.length)];
				resultLow = thresholdMinPercent / 100;
				resultLow = resultLow * dataVal;
				lowerVal = dataVal - resultLow;
				resultHigh = thresholdMaxPercent / 100;
				resultHigh = resultHigh * dataVal;
				higherVal = dataVal + resultHigh;
				prevdataVal = dataVal;
				double expectedDataVal;
				expectedDataVal = ThreadLocalRandom.current().nextDouble(lowerVal, higherVal);
				expectedDataVal = Math.round(expectedDataVal * 100) / 100.0d;
				if (range == "lower") {
					dataVal = lowerVal - 5;
				} else {
					dataVal = higherVal + 5;
				}
				selectPeriodFromCatalogDetailsPage("202207");
				SelectMetricValueForRespectiveMetric(strMetric);
				enterDataValueForNumberType(dataVal);
				Thread.sleep(3000);
				String xpath = "Threshold % of Previous Value - metric should not be " + thresholdMinPercent
						+ "% (i.e., " + lowerVal + ") lower than " + prevdataVal + " and should not be "
						+ thresholdMaxPercent + "% (i.e.," + higherVal + ") higher than " + prevdataVal;
				// WebElement weErr =
				// driver.findElement(By.xpath("//*[contains(@aria-label,'"+xpath+"')]"));
				// verifyIfElementPresent(weErr, "Error Message", "Cataog Details page");
				//
				// if(verifyIfElementPresent(weErr, "Error Message", "Cataog Details page")){
				// passed("Error message displayed as expected "+xpath);
				// }else{
				// weErr = driver.findElement(By.xpath("//*[contains(@aria-label,'Threshold % of
				// Previous Value - metric should not be ')]"));
				// failed(driver, "Error message displayed as expected ==? "+xpath+" and actual
				// value is ==> "+weErr.getAttribute("aria-label"));
				// }
				System.out.println("lowerVal " + lowerVal);
				System.out.println("higherVal " + higherVal);
				System.out.println("dataVal " + dataVal);
				prevdataVal = dataVal;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyThresholdPercentageOfValueForPreviousPeriodMetricsValidationMethod() {
		HashMap<String, Integer> InitialStatusCount = new HashMap<>();
		int unansweredCnt, completedCnt, metricValidationErrorsCnt;
		WebElement expandTopic;
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		verifyAddTopic("Test Topic1");
		Random rnd = new Random();
		double max = 100, min = 5, num1, num2, total, thresholdMinPercent, thresholdMaxPercent;
		// num2 = rnd.nextInt((max - min) + 1) + min;
		num2 = ThreadLocalRandom.current().nextDouble(min, max);
		num1 = ThreadLocalRandom.current().nextDouble(min, max);
		num1 = Math.round(num1 * 100) / 100.0d;
		num2 = Math.round(num2 * 100) / 100.0d;
		while (num1 >= num2) {
			num1 = ThreadLocalRandom.current().nextDouble(min, num2);
			num1 = Math.round(num1 * 100) / 100.0d;
			if (num1 != num2) {
				break;
			}
		}
		thresholdMinPercent = num1;
		thresholdMaxPercent = num2;
		// thresholdMinPercent = 20;
		// thresholdMaxPercent = 50;
		createThresholdPercentageOfValueForPreviousPeriodMetricsValidationMethod(thresholdMinPercent,
				thresholdMaxPercent);
		// createThresholdPercentageOfPreviousValueMetricsValidationMethod(20,50);
		if (!navigateToCatalogPage()) {
			return;
		}
		if (!selectExisitingCatalogCard("threshold_1311_pr_02")) {
			return;
		}
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		metricValidationErrorsCnt = InitialStatusCount.get("metricValidationErrors");
		if (unansweredCnt == 1) {
			passed("unanswered count is 0");
		} else {
			failed(driver, "unanswered count is not 0");
		}
		if (metricValidationErrorsCnt == 0) {
			passed("unanswered count is 0");
		} else {
			failed(driver, "unanswered count is not 0");
		}
		String strTopic, strMetric;
		// strTopic = data.get("TopicName")
		strTopic = "Test Topic1";
		strMetric = "Test Threshold Percentage Of Previous Value Metric24";
		// div[text()=' Test Topic1']
		expandTopic = driver.findElement(By.xpath("//span[text()='" + strTopic
				+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
		clickOn(expandTopic, "Expand Topic ==> " + strTopic);
		verifyThresholdPercentageOfValueForPreviousPeriod(strMetric, thresholdMinPercent, thresholdMaxPercent);
		InitialStatusCount = getCatalogFilterStatusCounts();
		completedCnt = InitialStatusCount.get("completed");
		if (completedCnt == 1) {
			passed("completed count is 3");
		} else {
			failed(driver, "completed count is not 3");
		}
		unansweredCnt = InitialStatusCount.get("unanswered");
		if (unansweredCnt == 0) {
			passed("unanswered count is 0");
		} else {
			failed(driver, "unanswered count is not 0");
		}
		metricValidationErrorsCnt = InitialStatusCount.get("metricValidationErrors");
		if (metricValidationErrorsCnt == 0) {
			passed("metricValidationErrorsCnt count is 1");
		} else {
			failed(driver, "metricValidationErrorsCnt count is not 1");
		}
	}

	public void createThresholdPercentageOfValueForPreviousPeriodMetricsValidationMethod(double min, double max) {
		try {
			jsClick(btnAddMetric, "Add Metric");
			verifyIfElementPresent(lblMetricDetails, "Metric Details", "Create Metic");
			clickOn(drpInputType, "Input Type dropdown");
			WebElement lstInputType = driver
					.findElement(By.xpath("//div[@id='menu-type']//ul/li[contains(text(),'Number')]"));
			clickOn(lstInputType, "INput Type");
			clickOn(drpTopic, "Topic dropdown");
			WebElement lstTopics = driver.findElement(By.xpath("//div[@id='menu-topicId']//li[text()='Test Topic1']"));
			clickOn(lstTopics, "Topic Values");
			enterText(txtMetricName, "Mtric Name", "Test Threshold Percentage Of Previous Value Metric24");
			clickOn(txtInstructions, "Instructions");
			enterText(txtInstructions, "Instructions", "Threshold Min % and Max %");
			// Total VLidation Method
			clickOn(drpValidationMethod, "Validation Method dropdown");
			WebElement lstTotal = driver.findElement(By.xpath(
					"//div[@id='menu-validationMethodRule']//ul/li[contains(text(),'Threshold % of Previous Value')]"));
			jsClick(lstTotal, "Validation Method Values");
			enterText(txtThresholdMinVal, "Threshold Min Value", Double.toString(min));
			enterText(txtThresholdMaxValue, "Threshold Max Value", Double.toString(max));
			// enterText(txtCustomMessage, "ExpressionValue", "Value should be between 0 to
			// 9");
			clickOn(btnSaveMetric, "Save Metric");
			verifyIfElementPresent(weMsgMetricSavedSuccessfully, "Metric Saved Successfully", "Create Metric");
			clickOn(btnOk, "Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyThresholdPercentageOfValueForPreviousPeriod(String strMetric, double thresholdMinPercent,
			double thresholdMaxPercent) {
		try {
			HashMap<String, Integer> InitialStatusCount = new HashMap<>();
			double max = 100, min = 5, num1, num2, initialVal, lowerVal, higherVal, resultLow, resultHigh;
			double dataVal = ThreadLocalRandom.current().nextDouble(min, max);
			dataVal = Math.round(dataVal * 100) / 100.0d;
			double prevdataVal;
			selectPeriodFromCatalogDetailsPage("202226");
			SelectMetricValueForRespectiveMetric(strMetric);
			enterDataValueForNumberType(dataVal);
			Random rnd = new Random();
			int minIteration = 2, maxIteration = 10, iterations;
			int rangeRnd = 0;
			iterations = rnd.nextInt((maxIteration - minIteration) + 1) + minIteration;
			iterations = 10;
			String range;
			for (int i = 0; i < iterations; i++) {
				resultLow = thresholdMinPercent / 100;
				resultLow = resultLow * dataVal;
				lowerVal = dataVal - resultLow;
				resultHigh = thresholdMaxPercent / 100;
				resultHigh = resultHigh * dataVal;
				higherVal = dataVal + resultHigh;
				prevdataVal = dataVal;
				double expectedDataVal;
				dataVal = ThreadLocalRandom.current().nextDouble(lowerVal, higherVal);
				dataVal = Math.round(dataVal * 100) / 100.0d;
				selectPeriodFromCatalogDetailsPage("202207");
				SelectMetricValueForRespectiveMetric(strMetric);
				enterDataValueForNumberType(dataVal);
				InitialStatusCount = getCatalogFilterStatusCounts();
				int metricValidationErrorsCnt = InitialStatusCount.get("metricValidationErrors");
				if (metricValidationErrorsCnt == 0) {
					passed("Error is not displayed for data value==> " + dataVal);
				} else {
					WebElement weErr = null;
					String xpath = "//*[contains(@aria-label,'Threshold % of Previous Value - metric should not be ')]";
					weErr = driver.findElement(By.xpath(xpath));
					failed(driver, "Error message is displayed for data value ==> " + dataVal
							+ " and displayed error is ==> " + weErr.getAttribute("aria-label"));
				}
				// TODO: reduce exception wait time
				// Thread.sleep(3000);
				// WebElement weErr = null;
				// String xpath = "//*[contains(@aria-label,'Threshold % of Previous Value -
				// metric should not be ')]";
				// try {
				// WebDriverWait wait = new WebDriverWait(driver, 5);
				//
				// weErr =
				// wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath))));
				// if(weErr==null){
				// passed("Error is not displayed for data value==> " + dataVal);
				// }else {
				// failed(driver, "Error message is displayed for data value ==> " + dataVal + "
				// and displayed error is ==> "+weErr.getAttribute("aria-label"));
				// }
				// weErr = driver.findElement(By.xpath(xpath));
				// if (!isElementPresent(weErr)) {
				// passed("Error is not displayed for data value==> " + dataVal);
				// } else {
				// failed(driver, "Error message is displayed for data value ==> " + dataVal + "
				// and displayed error is ==> "+weErr.getAttribute("aria-label"));
				// }
				// } catch (NoSuchElementException e) {
				// passed("Error is not displayed for data value==> " + dataVal);
				// }
				System.out.println("lowerVal " + lowerVal);
				System.out.println("higherVal " + higherVal);
				System.out.println("dataVal " + dataVal);
				prevdataVal = dataVal;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyThresholdPercentageOfValueForPreviousPeriodMetricsValidationMethodForInvalidDataVal() {
		HashMap<String, Integer> InitialStatusCount = new HashMap<>();
		int unansweredCnt, completedCnt, metricValidationErrorsCnt;
		WebElement expandTopic;
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		verifyAddTopic("Test Topic1");
		Random rnd = new Random();
		double max = 100, min = 5, num1, num2, total, thresholdMinPercent, thresholdMaxPercent;
		// num2 = rnd.nextInt((max - min) + 1) + min;
		num2 = ThreadLocalRandom.current().nextDouble(min, max);
		num1 = ThreadLocalRandom.current().nextDouble(min, max);
		num1 = Math.round(num1 * 100) / 100.0d;
		num2 = Math.round(num2 * 100) / 100.0d;
		while (num1 >= num2) {
			num1 = ThreadLocalRandom.current().nextDouble(min, num2);
			num1 = Math.round(num1 * 100) / 100.0d;
			if (num1 != num2) {
				break;
			}
		}
		thresholdMinPercent = num1;
		thresholdMaxPercent = num2;
		// thresholdMinPercent = 20;
		// thresholdMaxPercent = 50;
		createThresholdPercentageOfValueForPreviousPeriodMetricsValidationMethodForInvalidDataVal(thresholdMinPercent,
				thresholdMaxPercent);
		// createThresholdPercentageOfPreviousValueMetricsValidationMethod(20,50);
		if (!navigateToCatalogPage()) {
			return;
		}
		if (!selectExisitingCatalogCard("threshold_1311_pr_04")) {
			return;
		}
		selectPeriodFromCatalogDetailsPage("202226");
		InitialStatusCount = getCatalogFilterStatusCounts();
		unansweredCnt = InitialStatusCount.get("unanswered");
		metricValidationErrorsCnt = InitialStatusCount.get("metricValidationErrors");
		if (unansweredCnt == 1) {
			passed("unanswered count is 0");
		} else {
			failed(driver, "unanswered count is not 0");
		}
		if (metricValidationErrorsCnt == 0) {
			passed("unanswered count is 0");
		} else {
			failed(driver, "unanswered count is not 0");
		}
		String strTopic, strMetric;
		// strTopic = data.get("TopicName")
		strTopic = "Test Topic1";
		strMetric = "Test Threshold Metric6";
		// div[text()=' Test Topic1']
		expandTopic = driver.findElement(By.xpath("//span[text()='" + strTopic
				+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
		clickOn(expandTopic, "Expand Topic ==> " + strTopic);
		verifyThresholdPercentageOfValueForPreviousPeriodForInvalidDataVal(strMetric, thresholdMinPercent,
				thresholdMaxPercent);
		InitialStatusCount = getCatalogFilterStatusCounts();
		completedCnt = InitialStatusCount.get("completed");
		if (completedCnt == 1) {
			passed("completed count is 3");
		} else {
			failed(driver, "completed count is not 3");
		}
		unansweredCnt = InitialStatusCount.get("unanswered");
		if (unansweredCnt == 0) {
			passed("unanswered count is 0");
		} else {
			failed(driver, "unanswered count is not 0");
		}
		metricValidationErrorsCnt = InitialStatusCount.get("metricValidationErrors");
		if (metricValidationErrorsCnt == 0) {
			passed("metricValidationErrorsCnt count is 1");
		} else {
			failed(driver, "metricValidationErrorsCnt count is not 1");
		}
	}

	public void createThresholdPercentageOfValueForPreviousPeriodMetricsValidationMethodForInvalidDataVal(double min,
			double max) {
		try {
			jsClick(btnAddMetric, "Add Metric");
			verifyIfElementPresent(lblMetricDetails, "Metric Details", "Create Metic");
			clickOn(drpInputType, "Input Type dropdown");
			WebElement lstInputType = driver
					.findElement(By.xpath("//div[@id='menu-type']//ul/li[contains(text(),'Number')]"));
			clickOn(lstInputType, "INput Type");
			clickOn(drpTopic, "Topic dropdown");
			WebElement lstTopics = driver.findElement(By.xpath("//div[@id='menu-topicId']//li[text()='Test Topic1']"));
			clickOn(lstTopics, "Topic Values");
			enterText(txtMetricName, "Mtric Name", "Test Threshold Metric6");
			clickOn(txtInstructions, "Instructions");
			enterText(txtInstructions, "Instructions", "Threshold Min % and Max %");
			// Total VLidation Method
			clickOn(drpValidationMethod, "Validation Method dropdown");
			WebElement lstTotal = driver.findElement(By.xpath(
					"//div[@id='menu-validationMethodRule']//ul/li[contains(text(),'Threshold % of Previous Value')]"));
			jsClick(lstTotal, "Validation Method Values");
			enterText(txtThresholdMinVal, "Threshold Min Value", Double.toString(min));
			enterText(txtThresholdMaxValue, "Threshold Max Value", Double.toString(max));
			// enterText(txtCustomMessage, "ExpressionValue", "Value should be between 0 to
			// 9");
			clickOn(btnSaveMetric, "Save Metric");
			verifyIfElementPresent(weMsgMetricSavedSuccessfully, "Metric Saved Successfully", "Create Metric");
			clickOn(btnOk, "Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyThresholdPercentageOfValueForPreviousPeriodForInvalidDataVal(String strMetric,
			double thresholdMinPercent, double thresholdMaxPercent) {
		try {
			double max = 100, min = 5, num1, num2, initialVal, lowerVal, higherVal, resultLow, resultHigh;
			double dataVal = ThreadLocalRandom.current().nextDouble(min, max);
			dataVal = Math.round(dataVal * 100) / 100.0d;
			double prevdataVal;
			selectPeriodFromCatalogDetailsPage("202226");
			SelectMetricValueForRespectiveMetric(strMetric);
			enterDataValueForNumberType(dataVal);
			Random rnd = new Random();
			int minIteration = 2, maxIteration = 10, iterations;
			int rangeRnd = 0;
			iterations = rnd.nextInt((maxIteration - minIteration) + 1) + minIteration;
			iterations = 10;
			String[] thresholdRange = { "lower", "higher" };
			String range;
			for (int i = 0; i < iterations; i++) {
				range = thresholdRange[rnd.nextInt(thresholdRange.length)];
				resultLow = thresholdMinPercent / 100;
				resultLow = resultLow * dataVal;
				lowerVal = dataVal - resultLow;
				resultHigh = thresholdMaxPercent / 100;
				resultHigh = resultHigh * dataVal;
				higherVal = dataVal + resultHigh;
				prevdataVal = dataVal;
				double expectedDataVal;
				expectedDataVal = ThreadLocalRandom.current().nextDouble(lowerVal, higherVal);
				expectedDataVal = Math.round(expectedDataVal * 100) / 100.0d;
				if (range == "lower") {
					dataVal = lowerVal - 5;
				} else {
					dataVal = higherVal + 5;
				}
				dataVal = Math.round(dataVal * 100) / 100.0d;
				selectPeriodFromCatalogDetailsPage("202207");
				SelectMetricValueForRespectiveMetric(strMetric);
				enterDataValueForNumberType(dataVal);
				Thread.sleep(3000);
				if (i != 0) {
					String xpath = "Threshold % of Previous Value - metric should not be " + thresholdMinPercent
							+ "% (i.e., " + lowerVal + ") lower than " + prevdataVal + " and should not be "
							+ thresholdMaxPercent + "% (i.e.," + higherVal + ") higher than " + prevdataVal;
					WebElement weErr = driver.findElement(By.xpath("//*[contains(@aria-label,'" + xpath + "')]"));
					verifyIfElementPresent(weErr, "Error Message", "Cataog Details page");
					if (verifyIfElementPresent(weErr, "Error Message", "Cataog Details page")) {
						passed("Error message displayed as expected " + xpath);
					} else {
						weErr = driver.findElement(By.xpath(
								"//*[contains(@aria-label,'Threshold % of Previous Value - metric should not be ')]"));
						failed(driver, "Error message displayed as expected ==? " + xpath + " and actual value is ==> "
								+ weErr.getAttribute("aria-label"));
					}
				}
				System.out.println("lowerVal " + lowerVal);
				System.out.println("higherVal " + higherVal);
				System.out.println("dataVal " + dataVal);
				prevdataVal = dataVal;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//div[contains(@class,'MuiDrawer-pape')]/div//*[local-name() = 'svg']")
	private WebElement weHamburgerMenu;
	@FindBy(xpath = "//div[contains(@class,'MuiPaper-elevation')]/ul//div[contains(text(),'Catalogs')]")
	private WebElement weCatalogsMenu;

	public void SelectMetricValueForRespectiveMetric(String metric) {
		String strTopic, strMetric;
		// strTopic = data.get("TopicName")
		strTopic = "Test Topic1";
		// strMetric = data.get("MetricName")
		// strMetric = "Number A1";
		strMetric = metric;
		//
		// span[text()='Test_Metric_1010']
		WebElement weMetric = driver.findElement(By.xpath("//span[text()='" + strMetric + "']"));
		verifyIfElementPresent(weMetric, "Topic ==> " + strMetric, "Topics and Metric Details");
//	WebElement weMetricVal = driver.findElement(By.xpath("//span[text()='" + strMetric
//		+ "']/ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']//span[1]"));
		WebElement weMetricVal = driver.findElement(By.xpath("//span[text()='" + strMetric
				+ "']/ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']//div"));
		jsClick(weMetricVal, "Metric Val");
		// span[@aria-label="Test_Metric_1010"]/following-sibling::div/span[text()='Goals']
//	WebElement weMetricDetailsPopupUp = driver.findElement(By.xpath("//p[text()='" + strMetric + "']"));
//	verifyIfElementPresent(weMetricDetailsPopupUp, "Goals popup for ==> " + strMetric, "Goals popup Details");
	}

	@FindBy(xpath = "//span[text()='Audit Log']")
	private WebElement weAuditLogHeader;

	public boolean SelectAuditForRespectiveMetric(String metricName) {
		boolean blnSucc = false;
		try {
			String strTopic, strMetric;
			// strTopic = data.get("TopicName")
			strTopic = "Test Topic1";
			// strMetric = data.get("MetricName")
			// strMetric = "Number A1";
			strMetric = metricName;
			//
			// span[text()='Test_Metric_1010']
			WebElement weMetric = driver.findElement(By.xpath("//span[text()='" + strMetric + "']"));
			verifyIfElementPresent(weMetric, "Topic ==> " + strMetric, "Topics and Metric Details");
			WebElement weAuditLog = driver.findElement(By.xpath("//span[text()='" + strMetric
					+ "']/ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='Audit']//span[1]"));
			Actions builder = new Actions(driver);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weAuditLog);
			WebElement myElement = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(weAuditLog));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
			builder.moveToElement(weAuditLog).click().build().perform();
			// clickOn(weAuditLog, "Audit Log");
			// span[@aria-label="Test_Metric_1010"]/following-sibling::div/span[text()='Goals']
			WebElement weMetricValPopupUp = driver.findElement(
					By.xpath("//div[@class='title' and text()='Metric']/following-sibling::div[@aria-label='"
							+ metricName + "']"));
			blnSucc = verifyIfElementPresent(weMetricValPopupUp, "Audit log popup displayed for ==> " + strMetric,
					"Audit log popup Details");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blnSucc;
	}

	@FindBy(xpath = "//input[@id='numberBox']")
	private WebElement txtDataNumberBox;
	@FindBy(xpath = "//p[text()='Data']/parent::grid/following-sibling::div/div/span[2]")
	private WebElement weDataValue;
	@FindBy(xpath = "//*[@data-testid='CloseIcon']")
	private WebElement btnCloseIcon;

	public void enterDataValueForNumberType(int value) {
		try {
//			Actions builder = new Actions(driver);
//			WebElement dataValue = driver
//					.findElement(By.xpath("//p[text()='Data']/parent::div//div[@data-gramm='false']"));
//			waitForElement(dataValue);
//			builder.moveToElement(dataValue).doubleClick().perform();
//			sleep(1000);
//			String valueConevrtToString = Integer.toString(value);
//			enterText(txtDataNumberBox, "mertics value", valueConevrtToString);
//			System.out.println("dsvf");
			clickOn(weDataValue, "Data Value");
			if (!isElementPresent(txtDataNumberBox)) {
				clickOn(weDataValue, "Data Value");
				enterText(txtDataNumberBox, "Data Value Text field", Integer.toString(value));
			} else {
				// txtDataNumberBox.clear();
				txtDataNumberBox.sendKeys(Keys.CONTROL + "a");
				txtDataNumberBox.sendKeys(Keys.DELETE);
				enterText(txtDataNumberBox, "Data Value Text field", Integer.toString(value));
			}
			clickOn(btnCloseIcon, "Close icon");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enterDataValueForNumberType(double value) {
		clickOn(weDataValue, "Data Value");
		enterText(txtDataNumberBox, "Data Value Text field", Double.toString(value));
		clickOn(btnCloseIcon, "Close icon");
	}

	/*******************
	 * Goals Start
	 ******************************************************************************************/
	@FindBy(xpath = "//button/*[local-name()='svg' and @data-testid='ChevronRightIcon']")
	private WebElement btnExpandAllTopics;
	@FindBy(xpath = "//button/*[local-name()='svg'and @data-testid='KeyboardArrowDownIcon']")
	private WebElement btnCollapaseAllTopics;

	public void SelectGoalsFromMetric() {
		// if(isElementPresent(btnExpandAllTopics)){
		// clickOn(btnExpandAllTopics, "Expand All Topics");
		// }else{
		// clickOn(btnCollapaseAllTopics, "Collapase All Topics");
		// clickOn(btnExpandAllTopics, "Expand All Topics");
		// }
		WebElement expandTopic = driver.findElement(By.xpath("//span[text()='" + data.get("TopicName")
				+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
		sleep(1000);
		clickOn(expandTopic, "Expand Topic ==> " + data.get("TopicName"));
		sleep(2000);
		// span[text()='Test_Metric_1010']
		System.out.println(data.get("MetricName"));
		WebElement weMetric = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName") + "']"));
		verifyIfElementPresent(weMetric, "Topic ==> " + data.get("MetricName"), "Topics and Metric Details");
		sleep(1000);
		//// span[text()='" + data.get("MetricName")+
		//// "']/ancestor::div[@col-id='metricName']//following-sibling::div//button[@aria-label='Add
		//// Goal']/*
		WebElement wegoals = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName")
				+ "']/ancestor::div[@col-id='metricName']//following-sibling::div"));
		Actions builder = new Actions(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", wegoals);
		WebElement myElement = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(wegoals));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
		sleep(1000);
		builder.moveToElement(wegoals).click().build().perform();
		// span[@aria-label="Test_Metric_1010"]/following-sibling::div/span[text()='Goals']
		//// span[@aria-label='" + data.get("MetricName") +
		// "']/following-sibling::div/span[text()='Goals']
		WebElement goalsPopupUp = driver.findElement(
				By.xpath("//p[text()='Goals']//parent::div/following-sibling::div/*[@data-testid='ExpandMoreIcon']"));
		verifyIfElementPresent(goalsPopupUp, "Goals popup for ==> " + data.get("MetricName"), "Goals popup Details");
		clickOn(goalsPopupUp, "Goals Drop down");
		WebElement btnAddGoalsRHP = driver.findElement(By.xpath(
				"//p[text()='Goals']/parent::div/parent::div/following-sibling::div//div[@aria-labelledby='panel1bh-header']//*[@data-testid='AddIcon']"));
		sleep(1000);
		clickElement(btnAddGoalsRHP, "clicked on Add Goals");
	}

	@FindBy(xpath = "//span[contains(text(),'No goals')]")
	private WebElement weNoGoalsText;
	@FindBy(xpath = "//*[text()='Add']")
	private WebElement weAddGoalForNoGoalsText;

	public boolean verifyCreateGoals() {
		boolean blnCreated = false;
		try {
			// selectExisitingCatalogCard(data.get("Catalog"));
			selectPeriodFromCatalogDetailsPage(data.get("Period"));
			SelectGoalsFromMetric();
			// selectAndEditGoals(data.get("GoalName"));
			String goalName = data.get("GoalName");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MMHH:mm:ss");
			Date date = new Date();
			System.out.println(formatter.format(date));
			String sNow = formatter.format(date).replaceAll("[-:/.//s]", "");
			if (goalName == "") {
				goalName = "TestGoal_" + sNow;
			}
//			if (isElementPresent(btnAddGoals)) {
//				clickOn(btnAddGoals, "Add Goals");
//			} else {
//				clickOn(weAddGoalForNoGoalsText, "Add Goals");
//			}
			EnterDetailsInGoalsPage();
			clickOn(btnSave, "Save");
			Thread.sleep(4000);
			WebElement weMetric = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName") + "']"));
			verifyIfElementPresent(weMetric, "Topic ==> " + data.get("MetricName"), "Topics and Metric Details");
			// WebElement goals =
			// driver.findElement(By.xpath("//span[text()='"+data.get("MetricName")+"']/ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='Goals']//button"));
			//
			// clickOn(goals, "Goals for ==> "+data.get("TopicName"));
			// SelectGoalsFromMetric();
			// span[@aria-label="Test_Metric_1010"]/following-sibling::div/span[text()='Goals']
			/*
			 * WebElement wegoals = driver.findElement(By.xpath("//span[text()='" +
			 * data.get("MetricName") +
			 * "']/ancestor::div[@col-id='metricName']//following-sibling::div//button[@aria-label='Add Goal']"
			 * )); Actions builder = new Actions(driver); ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView();", wegoals); WebElement
			 * myElement = new WebDriverWait(driver,
			 * 20).until(ExpectedConditions.visibilityOf(wegoals)); ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView();", myElement);
			 * builder.moveToElement(wegoals).click().build().perform(); Thread.sleep(4000);
			 * WebElement goalsPopupUp = driver.findElement(By.xpath("//span[@aria-label='"
			 * + data.get("MetricName") +
			 * "']/following-sibling::div/span[text()='Goals']"));
			 * verifyIfElementPresent(goalsPopupUp, "Goals popup for ==> " +
			 * data.get("MetricName"), "Goals popup Details"); Thread.sleep(4000)
			 */;
			WebElement weGoal = driver.findElement(By.xpath("//div[text()='" + GlobalVariables.goalName + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weGoal);
			blnCreated = verifyIfElementPresent(weGoal, GlobalVariables.goalName + " goal name ", "Goals Detaisl page");
//			WebElement goalsPopupUpClose = driver.findElement(
//					By.xpath("//div[contains(@class,'paperAnchorRight')]//*[local-name()='svg' and @fill='none']"));
//			if (isElementPresent(goalsPopupUpClose)) {
//				clickOn(goalsPopupUpClose, "goalsPopupUpClose");
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blnCreated;
	}

	public void selectAndEditGoal(String goalName) {
		try {
			WebElement weGoal = driver.findElement(By.xpath("//div[text()='" + goalName + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weGoal);
			jsClick(weGoal, goalName);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", btnEditGoals);
			clickOn(btnEditGoals, "Edit Goals");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean verifyUpdateGoals() {
		boolean blnUpdated = false;
		try {
			// selectExisitingCatalogCard(data.get("Catalog"));
			// SelectGoalsFromMetric();
			WebElement wegoals = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName")
					+ "']/ancestor::div[@col-id='metricName']//following-sibling::div"));
			Actions builder = new Actions(driver);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", wegoals);
			WebElement myElement = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(wegoals));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
			sleep(1000);
			builder.moveToElement(wegoals).click().build().perform();
			// span[@aria-label="Test_Metric_1010"]/following-sibling::div/span[text()='Goals']
			//// span[@aria-label='" + data.get("MetricName") +
			// "']/following-sibling::div/span[text()='Goals']
			WebElement goalsPopupUp = driver.findElement(By
					.xpath("//p[text()='Goals']//parent::div/following-sibling::div/*[@data-testid='ExpandMoreIcon']"));
			verifyIfElementPresent(goalsPopupUp, "Goals popup for ==> " + data.get("MetricName"),
					"Goals popup Details");
			clickOn(goalsPopupUp, "Goals Drop down");
//			WebElement btnAddGoalsRHP = driver.findElement(By.xpath("//p[text()='Goals']/parent::div/parent::div/following-sibling::div//div[@aria-labelledby='panel1bh-header']//*[@data-testid='AddIcon']"));
//			sleep(1000);
//			clickElement(btnAddGoalsRHP, "clicked on Add Goals");
			Thread.sleep(2000);
			/*
			 * WebElement wegoals = driver.findElement(By.xpath("//span[text()='" +
			 * data.get("MetricName") +
			 * "']/ancestor::div[@col-id='metricName']//following-sibling::div//button[@aria-label='Add Goal']"
			 * )); Actions builder = new Actions(driver); ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView();", wegoals); WebElement
			 * myElement = new WebDriverWait(driver,
			 * 20).until(ExpectedConditions.visibilityOf(wegoals)); ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView();", myElement);
			 * builder.moveToElement(wegoals).click().build().perform(); Thread.sleep(2000);
			 * WebElement goalsPopupUp = driver.findElement(By.xpath("//span[@aria-label='"
			 * + data.get("MetricName") +
			 * "']/following-sibling::div/span[text()='Goals']"));
			 * verifyIfElementPresent(goalsPopupUp, "Goals popup for ==> " +
			 * data.get("MetricName"), "Goals popup Details");
			 */
			selectAndEditGoal(GlobalVariables.goalName);
			System.out.println("Print screen");
			EnterUpdatedDetailsInGoalsPage();
			clickOn(btnUpdate, "Update");
			Thread.sleep(3000);
			WebElement weMetric = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName") + "']"));
			verifyIfElementPresent(weMetric, "Topic ==> " + data.get("MetricName"), "Topics and Metric Details");
			wegoals = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName")
					+ "']/ancestor::div[@col-id='metricName']//following-sibling::div//button[@aria-label='Add Goal']"));
			builder = new Actions(driver);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", wegoals);
			myElement = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(wegoals));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
			builder.moveToElement(wegoals).click().build().perform();
			Thread.sleep(2000);
			goalsPopupUp = driver.findElement(By.xpath("//span[@aria-label='" + data.get("MetricName")
					+ "']/following-sibling::div/span[text()='Goals']"));
			verifyIfElementPresent(goalsPopupUp, "Goals popup for ==> " + data.get("MetricName"),
					"Goals popup Details");
			Thread.sleep(2000);
			WebElement weGoal = driver.findElement(By.xpath("//div[text()='" + GlobalVariables.goalName + "']"));
			blnUpdated = verifyIfElementPresent(weGoal, GlobalVariables.goalName + " goal name ", "Goals Detaisl page");
			WebElement goalsPopupUpClose = driver.findElement(
					By.xpath("//div[contains(@class,'paperAnchorRight')]//*[local-name()='svg' and @fill='none']"));
			if (isElementPresent(goalsPopupUpClose)) {
				clickOn(goalsPopupUpClose, "goalsPopupUpClose");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blnUpdated;
	}

	public String EnterUpdatedDetailsInGoalsPage() {
		String goalName = null;
		try {
			goalName = GlobalVariables.goalName + "_Update";
			txtGoalName.sendKeys(Keys.CONTROL + "a");
			txtGoalName.sendKeys(Keys.DELETE);
			System.out.println(goalName);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", txtGoalName);
			enterText(txtGoalName, "Goal Name", goalName);
			goalsDetails.put("Goal Name", goalName);
			GlobalVariables.goalName = goalName;
			if (data.get("BaseValueType").trim().equals("SingleSelect")) {
				WebElement weBaseDateVal;
				WebElement weDrpBaseDateVal = driver
						.findElement(By.xpath("//div[contains(@id,'select-baseDataValue')]"));
				clickOn(weDrpBaseDateVal, "BaseDataValue");
				Thread.sleep(1500);
				if (!data.get("BaseValueSingleSelectValue").equals("")) {
					weBaseDateVal = driver.findElement(By.xpath("//div[@id='menu-baseDataValue']//ul/li[text()='"
							+ data.get("BaseValueSingleSelectValue") + "']"));
					// clickOn(weBaseDateVal, "Selected"+weBaseDateVal.getText());
					// goalsDetails.put("Base Value", weBaseDateVal.getText());
				} else {
					List<WebElement> lstBaseDateValues = driver
							.findElements(By.xpath("//div[@id='menu-baseDataValue']//ul/li"));
					Random rnd = new Random();
					int max = lstBaseDateValues.size(), min = 1, num;
					num = rnd.nextInt((max - min) + 1) + min;
					weBaseDateVal = driver.findElement(By.xpath("//div[@id='menu-baseDataValue']//ul/li[" + num + "]"));
				}
				String val = weBaseDateVal.getText();
				clickOn(weBaseDateVal, "Selected " + val);
				goalsDetails.put("Base Value", val);
			} else {
				txtBaseValue.sendKeys(Keys.CONTROL + "a");
				txtBaseValue.sendKeys(Keys.DELETE);
				enterText(txtBaseValue, "Base Value", "100");
				goalsDetails.put("Base Value", "100");
			}
			clickOn(txtBaseDate, "BaseDate");
			if (data.get("UpdateBaseDate") == "") {
				localDate = LocalDate.now().plusDays(0);
				DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat targetlFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
				Date format = originalFormat.parse(localDate.toString());
				formattedDate = targetlFormat.format(format);
				clickOn(txtBaseDate, "BaseDate");
				String[] getReqFormat = formattedDate.split(" ");
				String day = getReqFormat[0].trim();
				if (Integer.parseInt(day) > 0 && Integer.parseInt(day) < 10) {
					day = day.replace("0", "");
				}
				String Month = getReqFormat[1].trim();
				String Year = getReqFormat[2].trim();
				Select lstMonth = new Select(slctmonth);
				lstMonth.selectByVisibleText(Month);
				Select lstYear = new Select(slctYear);
				lstYear.selectByVisibleText(Year);
				List<WebElement> lst = driver
						.findElements(By.xpath("//div[@class='react-datepicker__month']//div[text()='" + day + "']"));
				if (lst.size() > 1) {
					String classVal = "";
					for (int i = 0; i < lst.size(); i++) {
						classVal = lst.get(i).getAttribute("class");
						if (!classVal.contains("outside-month")) {
							clickOn(lst.get(i), day);
						}
					}
				} else {
					WebElement weDay = driver.findElement(
							By.xpath("//div[@class='react-datepicker__month']//div[text()='" + day + "']"));
					clickOn(weDay, day);
				}
			} else {
//				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");
//				DateFormat targetlFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
//				Date format = originalFormat.parse(data.get("UpdateBaseDate"));
//				formattedDate = targetlFormat.format(format);
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
				String ecc = dtf.format(now);
				todaysDate = ecc.split(" ");
				enterText(txtBaseDate, "Base Date", todaysDate[0]);
			}
			clickOn(lblBaseDate, todaysDate[0]);
			System.out.println(todaysDate[0]);
			goalsDetails.put("Base Date", formattedDate);
			if (data.get("TargetValueType").trim().equals("SingleSelect")) {
				WebElement weTargetDateVal;
				WebElement weDrpBaseDateVal = driver
						.findElement(By.xpath("//div[contains(@id,'select-targetDataValue')]"));
				clickOn(weDrpBaseDateVal, "Target Value");
				Thread.sleep(1500);
				if (!data.get("TargetValueSingleSelectValue").equals("")) {
					weTargetDateVal = driver.findElement(By.xpath("//div[@id='menu-targetDataValue']//ul/li[text()='"
							+ data.get("TargetValueSingleSelectValue") + "']"));
					// clickOn(weTargetDateVal, "Selected"+weTargetDateVal.getText());
					// goalsDetails.put("Target Value", weTargetDateVal.getText());
				} else {
					List<WebElement> lstweTargetDateVals = driver
							.findElements(By.xpath("//div[@id='menu-targetDataValue']//ul/li"));
					Random rnd = new Random();
					int max = lstweTargetDateVals.size(), min = 1, num;
					num = rnd.nextInt((max - min) + 1) + min;
					weTargetDateVal = driver
							.findElement(By.xpath("//div[@id='menu-targetDataValue']//ul/li[" + num + "]"));
				}
				String val = weTargetDateVal.getText();
				clickOn(weTargetDateVal, "Selected " + val);
				goalsDetails.put("Target Value", val);
			} else {
				txtTargetValue.sendKeys(Keys.CONTROL + "a");
				txtTargetValue.sendKeys(Keys.DELETE);
				enterText(txtTargetValue, "Target Value", "300");
				goalsDetails.put("Target Value", "300");
			}
			clickOn(txtTargetDate, "Target Date");
			if (data.get("UpdateTargetDate") == "") {
				localDate = LocalDate.now().plusDays(0);
				DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat targetlFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
				Date format = originalFormat.parse(localDate.toString());
				formattedDate = targetlFormat.format(format);
				clickOn(txtTargetDate, "Target");
				String[] getReqFormat = formattedDate.split(" ");
				String day = getReqFormat[0].trim();
				if (Integer.parseInt(day) > 0 && Integer.parseInt(day) < 10) {
					day = day.replace("0", "");
				}
				String Month = getReqFormat[1].trim();
				String Year = getReqFormat[2].trim();
				Select lstMonth = new Select(slctmonth);
				lstMonth.selectByVisibleText(Month);
				Select lstYear = new Select(slctYear);
				lstYear.selectByVisibleText(Year);
				List<WebElement> lst = driver
						.findElements(By.xpath("//div[@class='react-datepicker__month']//div[text()='" + day + "']"));
				if (lst.size() > 1) {
					String classVal = "";
					for (int i = 0; i < lst.size(); i++) {
						classVal = lst.get(i).getAttribute("class");
						if (!classVal.contains("outside-month")) {
							clickOn(lst.get(i), day);
						}
					}
				} else {
					WebElement weDay = driver.findElement(
							By.xpath("//div[@class='react-datepicker__month']//div[text()='" + day + "']"));
					clickOn(weDay, day);
				}
			} else {
//				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");
//				DateFormat targetlFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
//				Date format = originalFormat.parse(data.get("UpdateTargetDate"));
//				formattedDate = targetlFormat.format(format);
				enterText(txtTargetDate, "Target Date", todaysDate[0]);
			}
			clickOn(lblTargetDate, "Target Date");
			goalsDetails.put("Target Date", todaysDate[0]);
			txtStrategy.sendKeys(Keys.CONTROL + "a");
			txtStrategy.sendKeys(Keys.DELETE);
			enterText(txtStrategy, "Strategy", data.get("UpdateStrategy"));
			txtObjective.sendKeys(Keys.CONTROL + "a");
			txtObjective.sendKeys(Keys.DELETE);
			enterText(txtObjective, "Objective", data.get("UpdateObjective"));
			txtImpactArea.sendKeys(Keys.CONTROL + "a");
			txtImpactArea.sendKeys(Keys.DELETE);
			enterText(txtImpactArea, "ImpactArea", data.get("UpdateImpactArea"));
			txtNotes.sendKeys(Keys.CONTROL + "a");
			txtNotes.sendKeys(Keys.DELETE);
			enterText(txtNotes, "Notes", data.get("UpdateNotes"));
			goalsDetails.put("Strategy", data.get("UpdateStrategy"));
			goalsDetails.put("Objective", data.get("UpdateObjective"));
			goalsDetails.put("Impact Area", data.get("UpdateImpactArea"));
			goalsDetails.put("Notes", data.get("UpdateNotes"));
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goalName;
	}

	@FindBy(xpath = "//*[text()='You want to delete the goal?']/parent::div/following-sibling::div/button[text()='Delete']")
	private WebElement btnDeleteGoalConfirm;

	public void selectAndDeleteGoal(String goalName) {
		try {
			WebElement weGoal = driver.findElement(By.xpath("//div[text()='" + goalName + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weGoal);
			jsClick(weGoal, goalName);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", btnDeleteGoals);
			clickOn(btnDeleteGoals, "Delete Goals");
			clickOn(btnDeleteGoalConfirm, "Delete Confirm Goal");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyDeleteGoals() {
		boolean blnDeleted = true;
		try {
			// selectExisitingCatalogCard(data.get("Catalog"));
			// SelectGoalsFromMetric();
			WebElement expandTopic = driver.findElement(By.xpath("//span[text()='" + data.get("TopicName")
					+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
			clickOn(expandTopic, "Expand Topic ==> " + data.get("TopicName"));
			WebElement wegoals = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName")
					+ "']/ancestor::div[@col-id='metricName']//following-sibling::div//button[@aria-label='Add Goal']"));
			Actions builder = new Actions(driver);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", wegoals);
			WebElement myElement = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(wegoals));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
			builder.moveToElement(wegoals).click().build().perform();
			Thread.sleep(2000);
			WebElement goalsPopupUp = driver.findElement(By.xpath("//span[@aria-label='" + data.get("MetricName")
					+ "']/following-sibling::div/span[text()='Goals']"));
			verifyIfElementPresent(goalsPopupUp, "Goals popup for ==> " + data.get("MetricName"),
					"Goals popup Details");
			selectAndDeleteGoal(GlobalVariables.goalName);
			Thread.sleep(3000);
			WebElement weMetric = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName") + "']"));
			verifyIfElementPresent(weMetric, "Topic ==> " + data.get("MetricName"), "Topics and Metric Details");
			wegoals = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName")
					+ "']/ancestor::div[@col-id='metricName']//following-sibling::div//button[@aria-label='Add Goal']"));
			builder = new Actions(driver);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", wegoals);
			myElement = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(wegoals));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
			builder.moveToElement(wegoals).click().build().perform();
			Thread.sleep(2000);
			goalsPopupUp = driver.findElement(By.xpath("//span[@aria-label='" + data.get("MetricName")
					+ "']/following-sibling::div/span[text()='Goals']"));
			verifyIfElementPresent(goalsPopupUp, "Goals popup for ==> " + data.get("MetricName"),
					"Goals popup Details");
			Thread.sleep(2000);
			try {
				WebElement weGoal = driver.findElement(By.xpath("//div[text()='" + GlobalVariables.goalName + "']"));
				blnDeleted = verifyIfElementPresent(weGoal, GlobalVariables.goalName + " goal name ",
						"Goals Detaisl page");
			} catch (Exception e) {
				blnDeleted = false;
			}
			if (!blnDeleted) {
				passed("Deleted Goal => " + GlobalVariables.goalName);
			} else {
				passed("Unable to Delete Goal => " + GlobalVariables.goalName);
			}
			WebElement goalsPopupUpClose = driver.findElement(
					By.xpath("//div[contains(@class,'paperAnchorRight')]//*[local-name()='svg' and @fill='none']"));
			if (isElementPresent(goalsPopupUpClose)) {
				clickOn(goalsPopupUpClose, "goalsPopupUpClose");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean verifyCancelCreateGoals() {
		boolean blnCreated = true;
		try {
			// selectExisitingCatalogCard(data.get("Catalog"));
			selectPeriodFromCatalogDetailsPage(data.get("Period"));
			SelectGoalsFromMetric();
			// selectAndEditGoals(data.get("GoalName"));
			String goalName = data.get("GoalName");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MMHH:mm:ss");
			Date date = new Date();
			System.out.println(formatter.format(date));
			String sNow = formatter.format(date).replaceAll("[-:/.//s]", "");
			if (goalName == "") {
				goalName = "TestGoal_" + sNow;
			}
//			if (isElementPresent(btnAddGoals)) {
//				clickOn(btnAddGoals, "Add Goals");
//			} else {
//				clickOn(weAddGoalForNoGoalsText, "Add Goals");
//			}
			EnterDetailsInGoalsPage();
			clickOn(btnCancel, "Cancel");
			Thread.sleep(2000);
			WebElement weMetric = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName") + "']"));
			verifyIfElementPresent(weMetric, "Topic ==> " + data.get("MetricName"), "Topics and Metric Details");
			// WebElement goals =
			// driver.findElement(By.xpath("//span[text()='"+data.get("MetricName")+"']/ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='Goals']//button"));
			//
			// clickOn(goals, "Goals for ==> "+data.get("TopicName"));
			// SelectGoalsFromMetric();
			// span[@aria-label="Test_Metric_1010"]/following-sibling::div/span[text()='Goals']
			/*
			 * WebElement wegoals = driver.findElement(By.xpath("//span[text()='" +
			 * data.get("MetricName") +
			 * "']/ancestor::div[@col-id='metricName']//following-sibling::div//button[@aria-label='Add Goal']"
			 * )); Actions builder = new Actions(driver); ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView();", wegoals); WebElement
			 * myElement = new WebDriverWait(driver,
			 * 20).until(ExpectedConditions.visibilityOf(wegoals)); ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView();", myElement);
			 * builder.moveToElement(wegoals).click().build().perform(); Thread.sleep(4000);
			 * WebElement goalsPopupUp = driver.findElement(By.xpath("//span[@aria-label='"
			 * + data.get("MetricName") +
			 * "']/following-sibling::div/span[text()='Goals']"));
			 * verifyIfElementPresent(goalsPopupUp, "Goals popup for ==> " +
			 * data.get("MetricName"), "Goals popup Details"); Thread.sleep(4000);
			 */
			try {
				WebElement weGoal = driver.findElement(By.xpath("//div[text()='" + GlobalVariables.goalName + "']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weGoal);
				blnCreated = verifyIfElementPresent(weGoal, GlobalVariables.goalName + " goal name ",
						"Goals Detaisl page");
			} catch (NoSuchElementException e) {
				blnCreated = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blnCreated;
	}

	@FindBy(xpath = "//input[@name='goalName']")
	private WebElement txtGoalName;
	@FindBy(xpath = "//input[@name='baseDataValue']")
	private WebElement txtBaseValue;
	@FindBy(xpath = "//input[@name='targetDataValue']")
	private WebElement txtTargetValue;
	@FindBy(xpath = "//input[@name='baseDate']")
	private WebElement txtBaseDate;
	@FindBy(xpath = "//input[@name='targetDate']")
	private WebElement txtTargetDate;
	@FindBy(xpath = "//input[@name='strategy']")
	private WebElement txtStrategy;
	@FindBy(xpath = "//input[@name='objective']")
	private WebElement txtObjective;
	@FindBy(xpath = "//input[@name='impactArea']")
	private WebElement txtImpactArea;
	@FindBy(xpath = "//input[@name='notes']")
	private WebElement txtNotes;
	@FindBy(xpath = "//button[text()='Update']")
	private WebElement btnUpdate;
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement btnSave;
	@FindBy(xpath = "//span[text()='Base Date']")
	private WebElement lblBaseDate;
	@FindBy(xpath = "//span[text()='Target Date']")
	private WebElement lblTargetDate;
	String sGoalName;
	HashMap<String, String> goalsDetails = new HashMap<>();
	String ecc = null;
	String[] todaysDate;

	public String EnterDetailsInGoalsPage() {
		String goalName = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String ecc = dtf.format(now);
		todaysDate = ecc.split(" ");
		try {
			LocalDate localDate;
			String formattedDate = null;
			goalName = data.get("GoalName");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MMHH:mm:ss");
			Date date = new Date();
			System.out.println(formatter.format(date));
			String sNow = formatter.format(date).replaceAll("[-:/.//s]", "");
			if (goalName == "") {
				goalName = "TestGoal_" + sNow;
			}
			enterText(txtGoalName, "Goal Name", goalName);
			goalsDetails.put("Goal Name", goalName);
			GlobalVariables.goalName = goalName;
			if (data.get("BaseValueType").trim().equals("SingleSelect")) {
				WebElement weBaseDateVal;
				// div[contains(@id,'select-baseDataValue')]
				WebElement weDrpBaseDateVal = driver.findElement(
						By.xpath("//span[text()='Base Value']/following-sibling::div/div/div[@role='button']"));
				clickOn(weDrpBaseDateVal, "BaseDataValue");
				Thread.sleep(1500);
				if (!data.get("BaseValueSingleSelectValue").equals("")) {
					weBaseDateVal = driver.findElement(By.xpath("//div[@id='menu-baseDataValue']//ul/li[text()='"
							+ data.get("BaseValueSingleSelectValue") + "']"));
					// clickOn(weBaseDateVal, "Selected"+weBaseDateVal.getText());
					// goalsDetails.put("Base Value", weBaseDateVal.getText());
				} else {
					List<WebElement> lstBaseDateValues = driver
							.findElements(By.xpath("//div[@id='menu-baseDataValue']//ul/li"));
					Random rnd = new Random();
					int max = lstBaseDateValues.size(), min = 1, num;
					num = rnd.nextInt((max - min) + 1) + min;
					weBaseDateVal = driver.findElement(By.xpath("//div[@id='menu-baseDataValue']//ul/li[" + num + "]"));
				}
				String val = weBaseDateVal.getText();
				clickOn(weBaseDateVal, "Selected " + val);
				goalsDetails.put("Base Value", val);
			} else {
				enterText(txtBaseValue, "Base Value", "100");
				goalsDetails.put("Base Value", "100");
			}
			clickOn(txtBaseDate, "BaseDate");
			if (data.get("BaseDate") == "") {
				localDate = LocalDate.now().plusDays(0);
				DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat targetlFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
				Date format = originalFormat.parse(localDate.toString());
				formattedDate = targetlFormat.format(format);
				clickOn(txtBaseDate, "BaseDate");
				String[] getReqFormat = formattedDate.split(" ");
				String day = getReqFormat[0].trim();
				if (Integer.parseInt(day) > 0 && Integer.parseInt(day) < 10) {
					day = day.replace("0", "");
				}
				String Month = getReqFormat[1].trim();
				String Year = getReqFormat[2].trim();
				Select lstMonth = new Select(slctmonth);
				lstMonth.selectByVisibleText(Month);
				Select lstYear = new Select(slctYear);
				lstYear.selectByVisibleText(Year);
				List<WebElement> lst = driver
						.findElements(By.xpath("//div[@class='react-datepicker__month']//div[text()='" + day + "']"));
				if (lst.size() > 1) {
					String classVal = "";
					for (int i = 0; i < lst.size(); i++) {
						classVal = lst.get(i).getAttribute("class");
						if (!classVal.contains("outside-month")) {
							clickOn(lst.get(i), day);
							break;
						}
					}
				} else {
					WebElement weDay = driver.findElement(
							By.xpath("//div[@class='react-datepicker__month']//div[text()='" + day + "']"));
					clickOn(weDay, day);
					goalsDetails.put("Base Date", "" + day);
				}
			} else {
//				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");
//				DateFormat targetlFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
//				Date format = originalFormat.parse(data.get("BaseDate"));
//				formattedDate = targetlFormat.format(format);
				/*
				 * DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				 * LocalDateTime now = LocalDateTime.now(); String ecc = dtf.format(now);
				 * todaysDate = ecc.split(" ");
				 */
				enterText(txtBaseDate, "Base Date", todaysDate[0]);
			}
			clickOn(lblBaseDate, "BaseDate");
			System.out.println(todaysDate[0]);
			goalsDetails.put("Base Date", todaysDate[0]);
			if (data.get("TargetValueType").trim().equals("SingleSelect")) {
				WebElement weTargetDateVal;
				WebElement weDrpBaseDateVal = driver.findElement(
						By.xpath("//span[text()='Target Value']/following-sibling::div/div/div[@role='button']"));
				clickOn(weDrpBaseDateVal, "Target Value");
				Thread.sleep(1500);
				if (!data.get("TargetValueSingleSelectValue").equals("")) {
					weTargetDateVal = driver.findElement(By.xpath("//div[@id='menu-targetDataValue']//ul/li[text()='"
							+ data.get("TargetValueSingleSelectValue") + "']"));
					// clickOn(weTargetDateVal, "Selected"+weTargetDateVal.getText());
					// goalsDetails.put("Target Value", weTargetDateVal.getText());
				} else {
					List<WebElement> lstweTargetDateVals = driver
							.findElements(By.xpath("//div[@id='menu-targetDataValue']//ul/li"));
					Random rnd = new Random();
					int max = lstweTargetDateVals.size(), min = 1, num;
					num = rnd.nextInt((max - min) + 1) + min;
					weTargetDateVal = driver
							.findElement(By.xpath("//div[@id='menu-targetDataValue']//ul/li[" + num + "]"));
				}
				String val = weTargetDateVal.getText();
				clickOn(weTargetDateVal, "Selected " + val);
				goalsDetails.put("Target Value", val);
			} else {
				enterText(txtTargetValue, "Target Value", "300");
				goalsDetails.put("Target Value", "300");
			}
			clickOn(txtTargetDate, "Target Date");
			if (data.get("TargetDate") == "") {
				localDate = LocalDate.now().plusDays(0);
				DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat targetlFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
				Date format = originalFormat.parse(localDate.toString());
				formattedDate = targetlFormat.format(format);
				clickOn(txtTargetDate, "Target");
				String[] getReqFormat = formattedDate.split(" ");
				String day = getReqFormat[0].trim();
				if (Integer.parseInt(day) > 0 && Integer.parseInt(day) < 10) {
					day = day.replace("0", "");
				}
				String Month = getReqFormat[1].trim();
				String Year = getReqFormat[2].trim();
				Select lstMonth = new Select(slctmonth);
				lstMonth.selectByVisibleText(Month);
				Select lstYear = new Select(slctYear);
				lstYear.selectByVisibleText(Year);
				List<WebElement> lst = driver
						.findElements(By.xpath("//div[@class='react-datepicker__month']//div[text()='" + day + "']"));
				if (lst.size() > 1) {
					String classVal = "";
					for (int i = 0; i < lst.size(); i++) {
						classVal = lst.get(i).getAttribute("class");
						if (!classVal.contains("outside-month")) {
							clickOn(lst.get(i), day);
							break;
						}
					}
				} else {
					WebElement weDay = driver.findElement(
							By.xpath("//div[@class='react-datepicker__month']//div[text()='" + day + "']"));
					clickOn(weDay, day);
				}
			} else {
//				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");
//				DateFormat targetlFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
//				Date format = originalFormat.parse(data.get("TargetDate"));
//				formattedDate = targetlFormat.format(format);
				enterText(txtTargetDate, "Target Date", todaysDate[0]);
			}
			clickOn(lblTargetDate, "Target Date");
			goalsDetails.put("Target Date", todaysDate[0]);
			enterText(txtStrategy, "Strategy", data.get("Strategy"));
			enterText(txtObjective, "Objective", data.get("Objective"));
			enterText(txtImpactArea, "ImpactArea", data.get("ImpactArea"));
			// enterText(txtNotes, "Notes", data.get("Notes"));
			goalsDetails.put("Strategy", data.get("Strategy"));
			goalsDetails.put("Objective", data.get("Objective"));
			goalsDetails.put("Impact Area", data.get("ImpactArea"));
			// goalsDetails.put("Notes", data.get("Notes"));
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goalName;
	}

	@FindBy(xpath = "//*[@data-testid='AddIcon']")
	private WebElement btnAddGoals;
	@FindBy(xpath = "//*[@data-testid='EditOutlinedIcon']")
	private WebElement btnEditGoals;
	@FindBy(xpath = "//*[@data-testid='DeleteOutlineOutlinedIcon']")
	private WebElement btnDeleteGoals;

	public void selectAndEditGoals(String goalName) {
		passed("Xpath==> " + "//div[text()='" + goalName + "']");
		WebElement weGoal = driver.findElement(By.xpath("//div[text()='" + goalName + "']"));
		clickOn(weGoal, goalName);
		clickOn(btnEditGoals, "Edit Goals");
	}

	@FindBy(xpath = "//div[@class='react-datepicker']")
	private WebElement weCalender;
	@FindBy(xpath = "//select[@class='react-datepicker__month-select']")
	private WebElement slctmonth;
	@FindBy(xpath = "//select[@class='react-datepicker__year-select']")
	private WebElement slctYear;

	public void SelectDate(String date, String dateField) {
		try {
			System.out.println(date);
			DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			DateFormat targetlFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
			String formattedDate = null;
			try {
				Date format = originalFormat.parse(date);
				formattedDate = targetlFormat.format(format);
				System.out.println("formattedDate" + formattedDate);
			} catch (ParseException e) {
				System.out.println("Exception caught  as" + e.getMessage());
			}
			String[] getReqFormat = formattedDate.split(" ");
			String day = getReqFormat[0].trim();
			String Month = getReqFormat[1].trim();
			String Year = getReqFormat[2].trim();
			Select lstMonth = new Select(slctmonth);
			lstMonth.selectByVisibleText(Month);
			Select lstYear = new Select(slctYear);
			lstYear.selectByVisibleText(Year);
			WebElement weDay = driver
					.findElement(By.xpath("//div[@class='react-datepicker__month']//div[text()='" + day + "']"));
			clickOn(weDay, day);
			takeScreenshot(driver);
			passed("Selected " + date + " date for " + dateField);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	String goalName = "";

	public void VerifyTheBaseDateValueForNumberTypeInGoalsScreen1() {
		try {
			selectExisitingCatalogCard(data.get("Catalog"));
			selectPeriodFromCatalogDetailsPage(data.get("Period"));
			SelectGoalsFromMetric();
			clickOn(btnAddGoals, "Add Goals");
			EnterDetailsInGoalsPage();
			LocalDate localDate = LocalDate.now().plusDays(5);
			System.out.println(localDate.toString());
			DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat targetlFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
			String formattedDate = null;
			Date format = originalFormat.parse(localDate.toString());
			formattedDate = targetlFormat.format(format);
			System.out.println("formattedDate" + formattedDate);
			clickOn(txtBaseDate, "BaseDate");
			String[] getReqFormat = formattedDate.split(" ");
			String day = getReqFormat[0].trim();
			if (Integer.parseInt(day) > 0 && Integer.parseInt(day) < 10) {
				day = day.replace("0", "");
			}
			String Month = getReqFormat[1].trim();
			String Year = getReqFormat[2].trim();
			Select lstMonth = new Select(slctmonth);
			lstMonth.selectByVisibleText(Month);
			Select lstYear = new Select(slctYear);
			lstYear.selectByVisibleText(Year);
			WebElement weDay = driver
					.findElement(By.xpath("//div[@class='react-datepicker__month']//div[text()='" + day + "']"));
			clickOn(weDay, day);
			clickOn(lblBaseDate, "BaseDate");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void VerifyTheBaseDateValueForNumberTypeInGoalsScreen() {
		try {
			// selectExisitingCatalogCard(data.get("Catalog"));
			// selectPeriodFromCatalogDetailsPage(data.get("Period"));
			SelectGoalsFromMetric();
			verifyThatBaseDateIsAcceptingBackDated();
			verifyThatBaseDateIsAcceptingFutureDated();
			verifyThatBaseDateIsAcceptingCurrentSystemDate();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void verifyThatBaseDateIsAcceptingBackDated() {
		String goalName;
		try {
			// clickOn(btnAddGoals, "Add Goals");
			WebElement btnAddGoalsRHP = driver.findElement(By.xpath(
					"//p[text()='Goals']/parent::div/parent::div/following-sibling::div//div[@aria-labelledby='panel1bh-header']//*[@data-testid='AddIcon']"));
			clickOn(btnAddGoalsRHP, "clicked on Add Goals");
			goalName = EnterDetailsInGoalsPage();
			LocalDate localDate = LocalDate.now().plusDays(-10);
			System.out.println(localDate.toString());
			selectDateFromReactCalendar(txtBaseDate, localDate, "BaseDate");
			clickOn(lblBaseDate, "BaseDate");
			localDate = LocalDate.now().plusDays(0);
			selectDateFromReactCalendar(txtTargetDate, localDate, "TargetDate");
			clickOn(lblTargetDate, "BaseDate");
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnSave);
			clickOn(btnSave, "Save");
			Thread.sleep(3000);
			WebElement weMetric = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName") + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weMetric);
			verifyIfElementPresent(weMetric, "Metric ==> " + data.get("MetricName"), "Topics and Metric Details");
			Actions toolAct = new Actions(driver);
			toolAct.moveToElement(weMetric).build().perform();
			WebElement toolTipElement = driver.findElement(By.cssSelector(".MuiTooltip-tooltip"));
			String toolTipText = toolTipElement.getText();
			if (toolTipText.trim().equals(data.get("MetricName"))) {
				passed("Selected Metric value is displayed in Tool Tip ==> " + toolTipElement.getText());
			} else {
				failed(driver, "Selected Metric value is not displayed in Tool Tip ==> " + toolTipElement.getText());
			}
			WebElement goals = driver.findElement(By.xpath("//div[text()='" + goalName + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", goals);
			verifyElementAndHighlightIfExists(goals, goalName, data.get("MetricName"));
			clickOn(goals, "Goals for ==> " + data.get("MetricName"));
			verifyIfElementPresent(goals, goalName + " goal name", "Goals Detaisl page");
//			WebElement goals = driver.findElement(By.xpath("(//span[text()='" + data.get("MetricName")
//					+ "']/ancestor::div[@col-id='metricName']//following-sibling::div)[11]//button"));
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", goals);
//			clickOn(goals, "Goals for ==> " + data.get("MetricName"));
//			Thread.sleep(5000);
//			WebElement goalsPopupUp = driver.findElement(By.xpath("//span[@aria-label='" + data.get("MetricName")
//					+ "']/following-sibling::div/span[text()='Goals']"));
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", goalsPopupUp);
//			verifyIfElementPresent(goalsPopupUp, "Goals popup for ==> " + data.get("MetricName"),
//					"Goals popup Details");
//			WebElement weGoal = driver.findElement(By.xpath("//div[text()='" + goalName + "']"));
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weGoal);
//			verifyIfElementPresent(weGoal, goalName + " goal name", "Goals Detaisl page");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyThatBaseDateIsAcceptingFutureDated() {
		String goalName;
		try {
			// clickOn(btnAddGoals, "Add Goals");
			WebElement btnAddGoalsRHP = driver.findElement(By.xpath(
					"//p[text()='Goals']/parent::div/parent::div/following-sibling::div//div[@aria-labelledby='panel1bh-header']//*[@data-testid='AddIcon']"));
			clickElement(btnAddGoalsRHP, "clicked on Add Goals");
			Thread.sleep(3000);
			goalName = EnterDetailsInGoalsPage();
			LocalDate localDate = LocalDate.now().plusDays(5);
			System.out.println(localDate.toString());
			selectDateFromReactCalendar(txtBaseDate, localDate, "BaseDate");
			clickOn(lblBaseDate, "BaseDate");
			localDate = LocalDate.now().plusDays(6);
			selectDateFromReactCalendar(txtTargetDate, localDate, "TargetDate");
			clickOn(lblTargetDate, "BaseDate");
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnSave);
			clickOn(btnSave, "Save");
			Thread.sleep(3000);
			WebElement goals = driver.findElement(By.xpath("//div[text()='" + goalName + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", goals);
			verifyElementAndHighlightIfExists(goals, goalName, data.get("MetricName"));
			clickOn(goals, "Goals for ==> " + data.get("MetricName"));
			verifyIfElementPresent(goals, goalName + " goal name", "Goals Detaisl page");
			/*
			 * WebElement weMetric = driver.findElement(By.xpath("//span[text()='" +
			 * data.get("MetricName") + "']")); ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView();", weMetric);
			 * verifyIfElementPresent(weMetric, "Metric ==> " + data.get("MetricName"),
			 * "Topics and Metric Details"); WebElement goals =
			 * driver.findElement(By.xpath("(//span[text()='" + data.get("MetricName") +
			 * "']/ancestor::div[@col-id='metricName']//following-sibling::div)[11]//button"
			 * )); ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView();", goals);
			 * clickOn(goals, "Goals for ==> " + data.get("MetricName")); WebElement
			 * goalsPopupUp = driver.findElement(By.xpath("//span[@aria-label='" +
			 * data.get("MetricName") + "']/following-sibling::div/span[text()='Goals']"));
			 * ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
			 * goalsPopupUp); verifyIfElementPresent(goalsPopupUp, "Goals popup for ==> " +
			 * data.get("MetricName"), "Goals popup Details"); WebElement weGoal =
			 * driver.findElement(By.xpath("//div[text()='" + goalName + "']"));
			 * ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
			 * weGoal); verifyIfElementPresent(weGoal, goalName + " goal name",
			 * "Goals Detaisl page");
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyThatBaseDateIsAcceptingCurrentSystemDate() {
		String goalName;
		try {
			// clickOn(btnAddGoals, "Add Goals");
			WebElement btnAddGoalsRHP = driver.findElement(By.xpath(
					"//p[text()='Goals']/parent::div/parent::div/following-sibling::div//div[@aria-labelledby='panel1bh-header']//*[@data-testid='AddIcon']"));
			clickOn(btnAddGoalsRHP, "clicked on Add Goals");
			goalName = EnterDetailsInGoalsPage();
			LocalDate localDate = LocalDate.now().plusDays(0);
			System.out.println(localDate.toString());
			selectDateFromReactCalendar(txtBaseDate, localDate, "BaseDate");
			clickOn(lblBaseDate, "BaseDate");
			localDate = LocalDate.now().plusDays(0);
			selectDateFromReactCalendar(txtTargetDate, localDate, "TargetDate");
			clickOn(lblTargetDate, "BaseDate");
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnSave);
			clickOn(btnSave, "Save");
			Thread.sleep(3000);
			WebElement goals = driver.findElement(By.xpath("//div[text()='" + goalName + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", goals);
			verifyElementAndHighlightIfExists(goals, goalName, data.get("MetricName"));
			clickOn(goals, "Goals for ==> " + data.get("MetricName"));
			verifyIfElementPresent(goals, goalName + " goal name", "Goals Detaisl page");
			/*
			 * WebElement weMetric = driver.findElement(By.xpath("//span[text()='" +
			 * data.get("MetricName") + "']")); ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView();", weMetric);
			 * verifyIfElementPresent(weMetric, "Metric ==> " + data.get("MetricName"),
			 * "Topics and Metric Details"); WebElement goals =
			 * driver.findElement(By.xpath("(//span[text()='" + data.get("MetricName") +
			 * "']/ancestor::div[@col-id='metricName']//following-sibling::div)[11]//button"
			 * )); ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView();", goals);
			 * clickOn(goals, "Goals for ==> " + data.get("MetricName")); WebElement
			 * goalsPopupUp = driver.findElement(By.xpath("//span[@aria-label='" +
			 * data.get("MetricName") + "']/following-sibling::div/span[text()='Goals']"));
			 * ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
			 * goalsPopupUp); verifyIfElementPresent(goalsPopupUp, "Goals popup for ==> " +
			 * data.get("MetricName"), "Goals popup Details"); WebElement weGoal =
			 * driver.findElement(By.xpath("//div[text()='" + goalName + "']"));
			 * ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
			 * weGoal); verifyIfElementPresent(weGoal, goalName + " goal name",
			 * "Goals Detaisl page");
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyTargetDateShouldBeLessThanBaseDate() {
		try {
			String goalName = data.get("GoalName");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MMHH:mm:ss");
			Date date = new Date();
			System.out.println(formatter.format(date));
			String sNow = formatter.format(date).replaceAll("[-:/.//s]", "");
			if (goalName == "") {
				goalName = "TestGoal_" + sNow;
			}
			clickOn(btnAddGoals, "Add Goals");
			EnterDetailsInGoalsPage();
			enterText(txtGoalName, "Goal Name", goalName);
			LocalDate baseDate = LocalDate.now().plusDays(0);
			selectDateFromReactCalendar(txtBaseDate, baseDate, "BaseDate");
			clickOn(lblBaseDate, "BaseDate");
			LocalDate targetDate = LocalDate.now().plusDays(-1);
			DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat targetlFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
			Date format = originalFormat.parse(targetDate.toString());
			formattedDate = targetlFormat.format(format);
			clickOn(txtTargetDate, "TargetDate");
			String[] getReqFormat = formattedDate.split(" ");
			String day = getReqFormat[0].trim();
			if (Integer.parseInt(day) > 0 && Integer.parseInt(day) < 10) {
				day = day.replace("0", "");
			}
			String Month = getReqFormat[1].trim();
			String Year = getReqFormat[2].trim();
			Select lstMonth = new Select(slctmonth);
			lstMonth.selectByVisibleText(Month);
			Select lstYear = new Select(slctYear);
			lstYear.selectByVisibleText(Year);
			clickOn(txtTargetDate, "TargetDate");
			Thread.sleep(3000);
			WebElement weDay = driver.findElement(By.xpath("//div[@class='react-datepicker__month']//div[text()='" + day
					+ "'and contains(@aria-label,'Not available')]"));
			if (isElementPresent(weDay)) {
				passed("Base Date is " + baseDate + " Target date is " + targetDate
						+ " and Target Date is disabled for less than base date");
			} else {
				failed(driver, "Base Date is " + baseDate + " Target date is " + targetDate
						+ " and Target Date is not disabled for less than base date");
			}
			clickOn(lblTargetDate, "TargetDate");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	LocalDate localDate;
	String formattedDate = null;
	String day;

	public void selectDateFromReactCalendar(WebElement weDateField, LocalDate Date, String fieldName) {
		try {
			if (data.get(fieldName) == "") {
				DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat targetlFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
				Date format = originalFormat.parse(Date.toString());
				formattedDate = targetlFormat.format(format);
				clickOn(weDateField, fieldName);
				String[] getReqFormat = formattedDate.split(" ");
				String day = getReqFormat[0].trim();
				if (Integer.parseInt(day) > 0 && Integer.parseInt(day) < 10) {
					day = day.replace("0", "");
				}
				String Month = getReqFormat[1].trim();
				String Year = getReqFormat[2].trim();
				Select lstMonth = new Select(slctmonth);
				lstMonth.selectByVisibleText(Month);
				Select lstYear = new Select(slctYear);
				lstYear.selectByVisibleText(Year);
				WebElement weDay = driver.findElement(By.xpath("//div[@class='react-datepicker__month']//div[text()='"
						+ day + "'and contains(@aria-label,'Choose')]"));
				clickOn(weDay, day);
				System.out.println("test");
				this.day = day;
			} else {
				enterText(weDateField, "Target Date", data.get(fieldName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/************************************ End of Goals *************/
	@FindBy(xpath = "//div[@aria-label='Workflow Monitor']")
	private WebElement btnWorkflowMonitor;
	@FindBy(xpath = "//article[text()='Workflow Monitor']")
	private WebElement weWorkflowMonitorHeader;

	public boolean navigateToWorkFlowMonitor() {
		boolean blnNavigate = false;
		try {
			waitForElement(btnWorkflowMonitor);
			clickOn(btnWorkflowMonitor, "Workflow Monitor");
			waitForElement(weWorkflowMonitorHeader);
			if (isElementPresent(weWorkflowMonitorHeader)) {
				passed("User Successfully Navigated To Workflow Monitor Page");
				blnNavigate = true;
			} else {
				failed(driver, "Failed To Navigate To Workflow Monitor Page");
			}
		} catch (Exception e) {
			failed(driver, "Exception Caught As" + e.getMessage());
		}
		return blnNavigate;
	}

	public void verifyNavigattionToWorkflowMonitor() {
		navigateToWorkFlowMonitor();
	}

	@FindBy(xpath = "//div[@aria-label='View Organization Data']")
	private WebElement btnViewOrganizationData;
	@FindBy(xpath = "//span[@class='orgName' and text()='Organization']")
	private WebElement lblOrganization;

	public void clickOnViewOrganizationData() {
		boolean blnNavigate = false;
		try {
			clickOn(btnViewOrganizationData, "View Organization Data");
			if (isElementPresent(lblOrganization)) {
				passed("Organization setion is displayed");
				blnNavigate = true;
			} else {
				failed(driver, "Organization setion is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getOrganizationNames() {
		List<WebElement> lstOrgNamesSection = driver
				.findElements(By.xpath("//span[@class='orgName' and text()='Organization']//following-sibling::div/p"));
		passed("Orgnization names are");
		for (int i = 0; i < lstOrgNamesSection.size(); i++) {
			passed(lstOrgNamesSection.get(i).getText());
			if (!lstOrgNamesSection.get(i).getText().equalsIgnoreCase("All")) {
				clickOn(lstOrgNamesSection.get(i), lstOrgNamesSection.get(i).getText());
				WebElement weOrgName = driver
						.findElement(By.xpath("//span[text()='Organization : ']/following-sibling::div/span"));
				if (lstOrgNamesSection.get(i).getText().trim().equalsIgnoreCase(weOrgName.getAttribute("aria-label"))) {
					passed("Selected Organization names " + weOrgName.getAttribute("aria-label")
							+ " is displayed at Header section");
				} else {
					failed(driver, "Selected Organization names " + weOrgName.getText()
							+ " is not displayed at Header section");
				}
			}
		}
	}

	public void verifyViewOrganizationDataButton() {
		clickOnViewOrganizationData();
		getOrganizationNames();
	}

	@FindBy(xpath = "//button[text()='Refresh']")
	private WebElement btnRefresh;

	public void verifyAuditTrailForAMetric(LinkedList<String> metricData) {
		List<WebElement> val = driver.findElements(By.xpath(
				"//span[text()='Version']/ancestor::div[@ref='headerRoot']/following-sibling::div//div[@role='rowgroup']/div"));
		if (val.size() == metricData.size()) {
			for (int i = val.size(); i >= 1; i--) {
				WebElement weAuditVal = driver.findElement(By.xpath(
						"//span[text()='Version']/ancestor::div[@ref='headerRoot']/following-sibling::div//div[@role='rowgroup']/div["
								+ i + "]/div[4]"));
				if (weAuditVal.getText().trim().equals(metricData.get(i - 1))) {
					System.out.println("weAuditVal.getText().trim() " + weAuditVal.getText().trim());
				} else {
					System.out.println("metricData.get(i) " + metricData.get(i));
					System.out.println("metricData.get(i) " + metricData.get(i));
				}
			}
		} else {
			failed(driver, "Metric data and audit log rows count are not matching");
		}
	}

	public void validateAuditTrailForAMetric() {
		boolean blnSelected = false;
		try {
			String strTopic = data.get("TopicName");
			selectPeriodFromCatalogDetailsPage(data.get("Period"));
			WebElement expandTopic = driver.findElement(By.xpath("//span[text()='" + strTopic
					+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
			clickOn(expandTopic, "Expand Topic ==> " + strTopic);
			if (SelectAuditForRespectiveMetric(data.get("Metric"))) {
				LinkedList<String> metricData = new LinkedList<>();
				metricData.add("5.272811000000001");
				metricData.add("32.81");
				verifyAuditTrailForAMetric(metricData);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//div[@class='dialogTitle']/span[text()='Evidence']")
	private WebElement weEvidenceTitle;
	@FindBy(xpath = "//span[text()='Period']/following-sibling::div/div[@role='button']")
	private WebElement weDrpPeriod;
	@FindBy(xpath = "//button[text()='Upload Evidence']")
	private WebElement btnUploadEvidence;
	@FindBy(xpath = "//button[text()='Existing Evidence']")
	private WebElement btnExistingEvidence;
	@FindBy(xpath = "//button[text()='Browse Files']")
	private WebElement btnBrowseFiles;
	@FindBy(xpath = "//input[@id='file_note_0']")
	private WebElement txtEvidenceNotes;
	@FindBy(xpath = "//button[text()='Upload']")
	private WebElement btnUpload;
	@FindBy(xpath = "//button[text()='Close']")
	private WebElement btnUploadDone;

	public void verifyUploadEvidence() {
		String strTopic = data.get("TopicName");
		selectPeriodFromCatalogDetailsPage(data.get("Period"));
		WebElement expandTopic = driver.findElement(By.xpath("//span[text()='" + strTopic
				+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
		clickOn(expandTopic, "Expand Topic ==> " + strTopic);
		if (SelectEvidenceForRespectiveMetric(data.get("MetricName"))) {
			SelectEvidenceForRespectiveMetric(data.get("MetricName"));
			uploadEvidence();
			enterText(txtEvidenceNotes, "Notes", "TestNotes");
			clickOn(btnUpload, "Upload");
			clickOn(btnUploadDone, "Upload");
			if (!isElementPresent(btnUpload)) {
				passed("Uploaded Evidence succussfully");
			} else {
				passed("Unable to Upload Evidence");
			}
		} else {
			failed(driver, "Unable to Select Metric");
		}
	}

	@FindBy(xpath = "//div/article/i")
	private WebElement btnEvidence;

	public boolean SelectEvidenceForRespectiveMetric(String metricName) {
		boolean blnSucc = false;
		try {
			String strMetric;
			strMetric = metricName;
			System.out.println();
			WebElement weMetric = driver.findElement(By.xpath("//span[text()='" + strMetric + "']"));
			verifyIfElementPresent(weMetric, "Topic ==> " + strMetric, "Topics and Metric Details");
			WebElement weEvidence = driver.findElement(By.xpath("//span[text()='" + strMetric
					+ "']/ancestor::div[@col-id='metricName']//following-sibling::div[14]//i//*[name()='svg']"));
			Actions builder = new Actions(driver);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weEvidence);
			WebElement myElement = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(weEvidence));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
			builder.moveToElement(weEvidence).click().build().perform();
			Thread.sleep(3000);
			WebElement weMetricValPopupUp = driver.findElement(
					By.xpath("//span[text()='Evidence']/ancestor::h2/following-sibling::div//span[@aria-label='"
							+ metricName + "']"));
			blnSucc = verifyIfElementPresent(weMetricValPopupUp, "Evidence popup displayed for ==> " + strMetric,
					"Evidence popup Details");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blnSucc;
	}

	public void verifyDownloadAndViewEvidence() {
		String strTopic = data.get("TopicName");
		selectPeriodFromCatalogDetailsPage(data.get("Period"));
		WebElement expandTopic = driver.findElement(By.xpath("//span[text()='" + strTopic
				+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
		clickOn(expandTopic, "Expand Topic ==> " + strTopic);
		SelectEvidenceForRespectiveMetric(data.get("MetricName"));
		downloadAndViewEvidence();
		// }
	}

	public void downloadAndViewEvidence() {
		try {
			File file;
			File dir;
			String dirPath = System.getProperty("user.dir") + "\\Ext\\Artifacts\\Catalog\\DownloadEvidences";
			dir = new File(dirPath);
			FileUtils.cleanDirectory(dir);
			clickOn(btnExistingEvidence, "Existing Evidence");
			List<WebElement> lstEvidenceRow = driver
					.findElements(By.xpath("//table[@aria-label='simple table']/tbody/tr"));
			if (lstEvidenceRow.size() > 0) {
				passed(lstEvidenceRow.size() + " evidences are available");
				WebElement weDownload = driver.findElement(By.xpath(
						"//table[@aria-label='simple table']/tbody/tr[1]//*[@data-testid='FileDownloadOutlinedIcon']"));
				clickOn(weDownload, "download");
				Thread.sleep(5000);
				List<String> results = new ArrayList<String>();
				File[] files = new File(dirPath).listFiles();
				System.out.println(files.length);
				if (files.length > 0) {
					for (File file1 : files) {
						if (file1.isFile()) {
							results.add(file1.getName());
							if (file1.length() > 0) {
								passed("File downloaded successfully and file size is +" + file1.length() + " Bytes");
							} else {
								failed(driver, "Unable to view downloaded file");
							}
						}
					}
				} else {
					failed(driver, "File not downloaded");
				}
			} else {
				info("Zero Evidences are available");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyDownloadMetrics() {
		String strTopic = data.get("TopicName");
		selectPeriodFromCatalogDetailsPage(data.get("Period"));
		WebElement expandTopic = driver.findElement(By.xpath("//span[text()='" + strTopic
				+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
		clickOn(expandTopic, "Expand Topic ==> " + strTopic);
		WebElement weMetric = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName") + "']"));
		if (verifyIfElementPresent(weMetric, "Metric ==> " + data.get("MetricName"), "Topics and Metric Details")) {
			downloadMetrics();
		} else {
			failed(driver, "Provided Catalog does't have metrics to download");
		}
	}

	@FindBy(xpath = "//button[@aria-label='Download Metrics']")
	private WebElement btnDownloadMetrics;
	@FindBy(xpath = "//button[text()='Download Metrics (.csv)']")
	private WebElement btnDownloadMetricsCSV;
	@FindBy(xpath = "//button[text()='Download Metrics (.pdf)']")
	private WebElement btnDownloadMetricsPDF;
	@FindBy(xpath = "//button[text()='Download Evidence File (.ZIP)")
	private WebElement btnDownloadMetricsZip;

	public void downloadMetrics() {
		try {
			File file;
			File dir;
			String dirPath = System.getProperty("user.dir") + "\\Ext\\Artifacts\\Catalog\\DownloadMetrics";
			dir = new File(dirPath);
			FileUtils.cleanDirectory(dir);
			clickOn(btnDownloadMetrics, "download Metrics");
			clickOn(btnDownloadMetricsCSV, "download Metrics in CSV format");
			Thread.sleep(5000);
			List<String> results = new ArrayList<String>();
			File[] files = new File(dirPath).listFiles();
			System.out.println(files.length);
			if (files.length > 0) {
				for (File file1 : files) {
					if (file1.isFile()) {
						results.add(file1.getName());
						if (file1.length() > 157) {
							passed("File downloaded successfully and file size is +" + file1.length() + " Bytes");
						} else {
							failed(driver, "Unable to view downloaded file");
						}
					}
				}
			} else {
				failed(driver, "File not downloaded");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyDownloadMetricsReport() {
		String strTopic = data.get("TopicName");
		selectPeriodFromCatalogDetailsPage(data.get("Period"));
		WebElement expandTopic = driver.findElement(By.xpath("//span[text()='" + strTopic
				+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
		clickOn(expandTopic, "Expand Topic ==> " + strTopic);
		// if(SelectEvidenceForRespectiveMetric(data.get("MetricName"))){
		WebElement weMetric = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName") + "']"));
		if (verifyIfElementPresent(weMetric, "Metric ==> " + data.get("MetricName"), "Topics and Metric Details")) {
			downloadMetricsReport();
		} else {
			failed(driver, "Provided Catalog does't have metrics to download");
		}
	}

	public void downloadMetricsReport() {
		try {
			File file;
			File dir;
			String dirPath = System.getProperty("user.dir") + "\\Ext\\Artifacts\\Catalog\\MetricReport";
			dir = new File(dirPath);
			FileUtils.cleanDirectory(dir);
			clickOn(btnDownloadMetrics, "download Metrics");
			clickOn(btnDownloadMetricsPDF, "download Metrics in PDF format");
			Thread.sleep(5000);
			List<String> results = new ArrayList<String>();
			File[] files = new File(dirPath).listFiles();
			System.out.println(files.length);
			if (files.length > 0) {
				for (File file1 : files) {
					if (file1.isFile()) {
						results.add(file1.getName());
						if (file1.length() > 0
								&& file1.getName().trim().equalsIgnoreCase("Catalog - Metrics Report.pdf")) {
							passed("Catalog - Metrics Report.pdf File downloaded successfully and file size is +"
									+ file1.length() + " Bytes");
						} else {
							failed(driver, "Unable to download Metric report file");
						}
					}
				}
			} else {
				failed(driver, "File not downloaded");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//span[text()='100% Complete']")
	private WebElement btnUploadMetricsDataStatus;
	@FindBy(xpath = "//button[@aria-label='Upload Data']")
	private WebElement btnUploadMetricsData;
	@FindBy(xpath = "//button[@aria-label='Download Catalog']")
	private WebElement btnDownLoadMetricsData;
	@FindBy(xpath = "//li/button[contains(text(),'Download Metrics (.csv)')]")
	private WebElement btnListDownLoadMetricsData;

	// li/button[contains(text(),'Download Metrics (.csv)')]
	public void verifyuploadMetrics() {
		try {
			String strTopic = data.get("TopicName");
			selectPeriodFromCatalogDetailsPage(data.get("Period"));
			/// ((JavascriptExecutor)driver).executeScript("document.body.style.zoom='70%';");
			WebElement expandTopic = driver.findElement(By.xpath("//span[text()='" + strTopic
					+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
			clickOn(expandTopic, "Expand Topic ==> " + strTopic);
			// if(SelectEvidenceForRespectiveMetric(data.get("MetricName"))){
			// SelectEvidenceForRespectiveMetric(data.get("MetricName"));
			clickOn(btnUploadMetricsData, "btnUploadMetricsData");
			uploadMetrics();
			clickOn(btnUpload, "Upload");
			verifyIfElementPresent(btnUploadMetricsDataStatus, "Metric ==> " + data.get("MetricName"),
					"Upload Metric data");
			clickOn(btnUpload, "Upload");
			if (isElementPresent(btnUpload)) {
				failed(driver, "Unable to upload metric data");
			} else {
				passed("Successfully uploaded Metric data");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void uploadMetrics() {
		try {
			clickOn(btnBrowseFiles, "Browse Files");
			String path = System.getProperty("user.dir");
			System.out.println(path);
			String filePath = path + "\\Ext\\Artifacts\\Catalog\\UploadMetrics\\pulsesg_catalog.csv";
			ProcessBuilder pb = new ProcessBuilder(path + "\\Ext\\fileUpload.exe", filePath);
			pb.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//-----------DS  

	public int getAuditLogRowCount(String metricName, String isExpandTopicRequired) {
		int rowCnt = 0;
		try {
			if (isExpandTopicRequired.equals("Yes")) {
				String xpathCatalogName = "//article//*[contains(text(),'" + data.get("CatalogName") + "')]";
				clickOnElementWithDynamicXpath(xpathCatalogName, "Catalog Name");
				String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
						+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand  Data request Link");
			}
			SelectAuditForRespectiveMetric(metricName);
			rowCnt = returnListAuditLogCount();
			clickOn(btnSectionClose, "Close Audit Log");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCnt;
	}

	public int returnListAuditLogCount() {
		List<WebElement> val = driver.findElements(By.xpath(
				"//span[text()='Version']/ancestor::div[@ref='headerRoot']/following-sibling::div//div[@role='rowgroup']/div"));
		return val.size();
	}

	@FindBy(xpath = "//div[@aria-label='Input Field Mapping']")
	private WebElement btnInputMaps;
	// div[text()='Input Map']
	@FindBy(xpath = "//h6[text()='Input Field Mapping']")
	private WebElement weInputMapHeader;
	// div[text()='Input
	// Map']/parent::h6/parent::div/following-sibling::div//button/*[@data-testid='AddOutlinedIcon']
	@FindBy(xpath = "//button//*[@data-testid='AddOutlinedIcon']")
	private WebElement btnAddInputMap;
	@FindBy(xpath = "//h2[contains(text(),'Create Input Map')]")
	private WebElement weCreateInputMapHeader;
	@FindBy(xpath = "//input[@name='inputMapName']")
	private WebElement txtinputMapName;
	@FindBy(xpath = "//p[text()='Data Source Name']/following-sibling::div/div/div[1]//div")
	private WebElement drpDataSourceName;
	@FindBy(xpath = "(//p[text()='Datasource Name']/following-sibling::div/div/div//div[@aria-hidden=\"true\"]/*)[2]")
	private WebElement drpDataSourceNameExpand;
	@FindBy(xpath = "//p[text()='S3 Upload successfully triggered']")
	private WebElement weS3UploadSuccessfullyTriggered;

	public void createInputMap_old(String inputMapName, String sDataSourceName, ArrayList<String> lstMetricName) {
		try {
			clickOn(btnExpand, "Expand");
			clickOn(btnInputMaps, "Input Map");
			verifyIfElementPresent(weInputMapHeader, "Input Maps", "Input Maps");
			clickOn(btnAddInputMap, "Input Map");
			verifyIfElementPresent(weCreateInputMapHeader, "Create Input Map", "Create Input Map");
			enterText(txtinputMapName, "Input Map Name", inputMapName);
			clickOn(drpDataSourceName, "Data Source Name");
			WebElement drpDataSourceNameValues = driver.findElement(By.xpath("//*[text()='" + sDataSourceName + "']"));
			clickOn(drpDataSourceNameValues, "Data Source Name values");
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
			}
			sleep(1000);
			String xpathTopicNamechkbox = "//span[text()= '" + data.get("TopicName")
					+ "']//preceding-sibling::span[@ref='eCheckbox']//input";
			for (String metricName : lstMetricName) {
				xpathTopicNamechkbox = "//span[text()='" + data.get("TopicName") + "']"
						+ "/ancestor::div[contains(@aria-label,'Press SPACE')]/following-sibling::div//span[text()='"
						+ metricName + "']"
						+ "/parent::div/parent::div/parent::div/preceding-sibling::div//input[@type='checkbox']";
				Thread.sleep(2000);
				WebElement chkMetric = driver.findElement(By.xpath(xpathTopicNamechkbox));
				Thread.sleep(2000);
				jsClick(chkMetric, "Topic Selection");
				Thread.sleep(2000);
			}
			// lickOnElementWithDynamicXpath(xpathTopicNamechkbox, "TopicName CheckBox");
			clickOn(btnUpload, "Upload");
			String path = System.getProperty("user.dir");
			System.out.println(path);
			String filePath = null;
			String choice = data.get("InputMapPortcoName");
			switch (choice) {
			case "SISECAM":
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\SISECAM\\Sisecam_GHG_v1.py";
				break;
			case "SompoHD":
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\SompoHD\\SompoHD.py";
				break;
			case "Scope1":
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\Scope1\\Scope1.py";
				break;
			case "EMP":
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\EMP\\employee.py";
				break;
			case "JSON":
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\json\\saudi_aramco_json_scrip_v2.py";
				break;
			}
			ProcessBuilder pb = new ProcessBuilder(path + "\\src\\test\\resources\\DataSourceFiles\\fileUpload.exe",
					filePath);
			Thread.sleep(3000);
			pb.start();
			Thread.sleep(8000);
			waitForElement(weS3UploadSuccessfullyTriggered);
			if (isElementPresent(weS3UploadSuccessfullyTriggered)) {
				passed("Successfully file uploaded");
			} else {
				failed(driver, "File upload failed");
			}
			clickOn(btnCreate, "Create");
			// span[text()='Test_IM_01']/parent::div/parent::div/parent::div//*[@data-testid="FileUploadOutlinedIcon"]
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createInputMap(String inputMapName, String sDataSourceName, ArrayList<String> lstMetricName) {
		try {
			clickOn(btnExpand, "Expand");
			clickOn(btnInputMaps, "Input Map");
			verifyIfElementPresent(weInputMapHeader, "Input Maps", "Input Maps");
			clickOn(btnAddInputMap, "Input Map");
			verifyIfElementPresent(weCreateInputMapHeader, "Create Input Map", "Create Input Map");
			enterText(txtinputMapName, "Input Map Name", inputMapName);
			clickOn(drpDataSourceName, "Data Source Name");
			sleep(1000);
//			enterText(drpDataSourceName, "Data Source Name", sDataSourceName);
			WebElement drpDataSourceNameValues = driver.findElement(By.xpath("//*[text()='" + sDataSourceName + "']"));
			clickOn(drpDataSourceNameValues, "Data Source Name values");
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
			}
			sleep(1000);
			String xpathTopicNamechkbox = "//span[text()= '" + data.get("TopicName")
					+ "']//preceding-sibling::span[@ref='eCheckbox']//input";
			for (String metricName : lstMetricName) {
				xpathTopicNamechkbox = "//span[text()='" + data.get("TopicName") + "']"
						+ "/ancestor::div[contains(@aria-label,'Press SPACE')]/following-sibling::div//span[text()='"
						+ metricName + "']"
						+ "/parent::div/parent::div/parent::div/preceding-sibling::div//input[@type='checkbox']";
				Thread.sleep(2000);
				WebElement chkMetric = driver.findElement(By.xpath(xpathTopicNamechkbox));
				Thread.sleep(2000);
				jsClick(chkMetric, "Topic Selection");
				Thread.sleep(2000);
			}
			// lickOnElementWithDynamicXpath(xpathTopicNamechkbox, "TopicName CheckBox");
			clickOn(btnUpload, "Upload");
			String path = System.getProperty("user.dir");
			System.out.println(path);
			String filePath = null;
			String choice = data.get("InputMapPortcoName");
			switch (choice) {
			case "SISECAM":
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\SISECAM\\Sisecam_GHG_v1.py";
				break;
			case "SompoHD":
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\SompoHD\\SompoHD.py";
				break;
			case "Scope1":
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\Scope1\\Scope1.py";
				break;
			case "EMP":
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\EMP\\employee.py";
				break;
			case "JSON":
				filePath = path + "\\src\\test\\resources\\DataSourceFiles\\json\\saudi_aramco_json_scrip_v2.py";
				break;
			}
			ProcessBuilder pb = new ProcessBuilder(path + "\\src\\test\\resources\\DataSourceFiles\\fileUpload.exe",
					filePath);
			Thread.sleep(3000);
			pb.start();
			Thread.sleep(8000);
			waitForElement(weS3UploadSuccessfullyTriggered);
			if (isElementPresent(weS3UploadSuccessfullyTriggered)) {
				passed("Successfully file uploaded");
			} else {
				failed(driver, "File upload failed");
			}
			clickOn(btnCreate, "Create");
			// span[text()='Test_IM_01']/parent::div/parent::div/parent::div//*[@data-testid="FileUploadOutlinedIcon"]
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//p[text()='Target System']//following-sibling::div")
	private WebElement drpTargetSystemDSType;

	public void createInputMapWorkday(String inputMapName, String sDataSourceName, ArrayList<String> lstTopicName) {
		try {
			clickOn(btnExpand, "Expand");
			clickOn(btnInputMaps, "Input Map");
			verifyIfElementPresent(weInputMapHeader, "Input Maps", "Input Maps");
			clickOn(btnAddInputMap, "Input Map");
			verifyIfElementPresent(weCreateInputMapHeader, "Create Input Map", "Create Input Map");
			enterText(txtinputMapName, "Input Map Name", inputMapName);
			clickOn(drpDataSourceName, "Data Source Name");
			WebElement drpDataSourceNameValues = driver.findElement(By.xpath("//*[text()='" + sDataSourceName + "']"));
			clickOn(drpDataSourceNameValues, "Data Source Name values");
			sleep(1000);
			clickOn(drpDataSourceNameExpand, "Data Source Name values");
			waitForElement(drpTargetSystemDSType);
			clickOn(drpTargetSystemDSType, "DropDown DS Type Target System");
			WebElement wedrpTargetType = driver
					.findElement(By.xpath("//li[text()='" + data.get("DS Target System Type") + "']"));
			clickOn(wedrpTargetType, "Data Source Name values");
			String xpathTopicNamechkbox = "//span[text()= '" + data.get("TopicName")
					+ "']//preceding-sibling::span[@ref='eCheckbox']//input";
			for (String topicName : lstTopicName) {
				xpathTopicNamechkbox = "//span[text()= '" + topicName
						+ "']//preceding-sibling::span[@ref='eCheckbox']//input";
				Thread.sleep(2000);
				WebElement chkMetric = driver.findElement(By.xpath(xpathTopicNamechkbox));
				Thread.sleep(2000);
				jsClick(chkMetric, "Topic Selection");
				Thread.sleep(2000);
			}
			// lickOnElementWithDynamicXpath(xpathTopicNamechkbox, "TopicName CheckBox");
			clickOn(btnUpload, "Upload");
			String path = System.getProperty("user.dir");
			System.out.println(path);
			String filePath = null;
			String choice = data.get("InputMapPortcoName");
			switch (choice) {
			case "Workday":
				if (data.get("DS Target System Type").equals("Catalog")) {
					filePath = path + "\\src\\test\\resources\\DataSourceFiles\\Workday\\WorkerPythonScript.py";
				} else if (data.get("DS Target System Type").equals("Calculator")) {
					filePath = path
							+ "\\src\\test\\resources\\DataSourceFiles\\Workday\\CalculatorPythonScript_2023_4_24.py";
				}
				break;
			}
			ProcessBuilder pb = new ProcessBuilder(path + "\\src\\test\\resources\\DataSourceFiles\\fileUpload.exe",
					filePath);
			Thread.sleep(3000);
			pb.start();
			Thread.sleep(8000);
			waitForElement(weS3UploadSuccessfullyTriggered);
			if (isElementPresent(weS3UploadSuccessfullyTriggered)) {
				passed("Successfully file uploaded");
			} else {
				failed(driver, "File upload failed");
			}
			clickOn(btnCreate, "Create");
			// span[text()='Test_IM_01']/parent::div/parent::div/parent::div//*[@data-testid="FileUploadOutlinedIcon"]
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//h2[text()='Execute Input Map']")
	private WebElement weExecuteInputMapHeader;
	@FindBy(xpath = "//span[text()='Organization']/div/div")
	private WebElement drpOrganization;
	@FindBy(xpath = "//span[text()='Period: ']/div/div")
	private WebElement drpPeriodDropDown;
	@FindBy(xpath = "//button[text()='Execute']")
	private WebElement btnExecute;
	@FindBy(xpath = "//div[text()='InputMap triggered successfully!']")
	private WebElement weInputMapSuccessfullyTriggered;

	public void excuteInputMap(String inputMapName) {
		try {
			WebElement btnExecuteInputMap = driver.findElement(By.xpath("//span[text()='" + inputMapName
					+ "']/parent::div/parent::div/parent::div//following-sibling::div//div/button[@aria-label='trigger']"));
			clickOn(btnExecuteInputMap, "Execute Input Map");
			verifyIfElementPresent(weExecuteInputMapHeader, "Execute Input Map Header", "Execute Input Map Header");
//					clickOn(drpOrganization, "Organization");
			//
//					WebElement drpOrganizationValues = driver.findElement(By.xpath("//li[text()='"+data.get("Organization")+"']"));
//					clickOn(drpOrganizationValues, "Organization Values");
			clickOn(drpPeriodDropDown, "Period");
			WebElement drpPeriodValues = driver.findElement(By.xpath("//li[text()='" + data.get("Period") + "']"));
			clickOn(drpPeriodValues, "Organization Values");
			clickOn(btnExecute, "Execute");
			/// TODO: add message validai
			waitForElement(weInputMapSuccessfullyTriggered);
			if (isElementPresent(weInputMapSuccessfullyTriggered)) {
				passed("Successfully file uploaded");
			} else {
				failed(driver, "File upload failed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//*[@data-testid='CloseIcon']")
	private WebElement btnCloseExecutionHistory;

	public void refreshPage() {
		try {
			driver.navigate().refresh();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickonCatalog(String catalogName) {
		WebElement weCatalogName = driver.findElement(By.xpath("//*[text()='" + catalogName + "']"));
		clickOn(weCatalogName, "weCatalogName");
	}

	public HashMap<String, String> getCatalogMetricsDataPostExecutionOfInputMap(ArrayList<String> lstMetricName) {
		HashMap<String, String> metricDataMap = new HashMap<>();
		try {
			navigateToCatalogPage();
//	    clickonCatalog(data.get("CatalogName"));
			searchAndSelectCatalogCard(data.get("CatalogName"));
			selectPeriodFromCatalogDetailsPage(data.get("Period"));
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
			}
			for (String metricName : lstMetricName) {
				SelectMetricValueForRespectiveMetric(metricName);
				WebElement weData1 = driver.findElement(By.xpath("//p[text()='Data']/parent::div/div//p"));
				sleep(1000);
				waitForElement(weData1);
				clickOn(weData1, "weData");
				WebElement weData = driver.findElement(By.xpath("//input[@id='numberBox']"));
				sleep(1000);
				System.out.println(weData.getAttribute("value"));
				metricDataMap.put(metricName, weData.getAttribute("value"));
				clickOn(btnCancel, "btnCancel");
				waitForElement(btnCloseIcon);
				clickOn(btnCloseIcon, "Close icon");
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return metricDataMap;
	}

	public void verifyViewInputMapHistory(String inputMapName) {
		int rowCnt = 0;
		try {
			// span[text()='" + inputMapName +
			// "']/parent::div/parent::div/following-sibling::div/button/*[@data-testid='VisibilityOutlinedIcon']
			WebElement weViewInputMapIcon = driver.findElement(By.xpath("//span[text()='" + inputMapName
					+ "']/parent::div/parent::div//parent::div//following-sibling::div/button/*[@data-testid='VisibilityOutlinedIcon']"));
			clickOn(weViewInputMapIcon, "View Input Map Icon");
			WebElement weDataLoadHistoryPopUp = driver
					.findElement(By.xpath("//h2[text()='" + inputMapName + " - Execution History']"));
			verifyElementAndHighlightIfExists(weDataLoadHistoryPopUp, "Execution History", "Execution History");
			int r = 0;
			boolean blnSucc = false;
			while (r != 30) {
				List<WebElement> lstRows = driver.findElements(By.xpath(
						"//div[@ref='headerRoot']/parent::div/div[@ref='eBodyViewport']//div[@role='rowgroup']/div[@role='row']"));
				rowCnt = lstRows.size();
				if (rowCnt > 0) {
					passed("Execution is audited in Execution history popup");
					break;
				} else {
					Thread.sleep(2000);
					r++;
					clickOn(btnRefresh, "Refresh");
					info("Clicked refresh button " + r);
				}
			}
			if (blnSucc) {
				failed(driver, "Execution is not audited in Execution history popup");
			}
			clickOn(btnCloseExecutionHistory, "Close Data History");
			;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	@FindBy(xpath = "//*[@aria-label='Save & Close']")
	private WebElement btnsaveAndClose;

	public void verifyCreatedGoalsDetails() {
		try {
			// selectAndEditGoals(goalsDetails.get("Goal Name"));
			// SelectGoalsFromMetric();
			Thread.sleep(3000);
			/*
			 * WebElement wegoals = driver.findElement(By.xpath("//span[text()='" +
			 * data.get("MetricName") +
			 * "']/ancestor::div[@col-id='metricName']//following-sibling::div//button[@aria-label='Add Goal']"
			 * )); Actions builder = new Actions(driver); ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView();", wegoals); WebElement
			 * myElement = new WebDriverWait(driver,
			 * 20).until(ExpectedConditions.visibilityOf(wegoals)); ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView();", myElement);
			 * builder.moveToElement(wegoals).click().build().perform(); Thread.sleep(3000);
			 */
			// span[@aria-label="Test_Metric_1010"]/following-sibling::div/span[text()='Goals']
			WebElement weGoal = driver.findElement(By.xpath("//div[text()='" + GlobalVariables.goalName + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weGoal);
			clickOn(weGoal, data.get("MetricName") + "goal name=>>" + GlobalVariables.goalName);
//			WebElement goalsPopupUp = driver.findElement(By.xpath("//span[@aria-label='" + data.get("MetricName")
//					+ "']/following-sibling::div/span[text()='Goals']"));
//			verifyIfElementPresent(goalsPopupUp, "Goals popup for ==> " + data.get("MetricName"),
//					"Goals popup Details");
			Thread.sleep(3000);
//			WebElement weGoalExpand = driver.findElement(
//					By.xpath("//div[text()='" + goalsDetails.get("Goal Name") + "']//following-sibling::div"));
//			// clickOn(weGoalExpand, "goals details");
//			jsClick(weGoalExpand, "goals details");
			Thread.sleep(3000);
			String[] lblNames = { "Base Value", "Base Date", "Target Value", "Target Date", "Strategy", "Objective",
					"Impact Area" };
			WebElement weGoalsData;
			for (int i = 0; i < lblNames.length; i++) {
				weGoalsData = driver.findElement(By.xpath("//span[text()='" + goalsDetails.get("Goal Name")
						+ "']/parent::div/following-sibling::div/span[text()='" + lblNames[i]
						+ "']/following-sibling::span[1]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", weGoalsData);
				if (lblNames[i].contains("Date")) {
					SimpleDateFormat fromUser = new SimpleDateFormat("dd MMMM yyyy");
					SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");
					String reformattedStr = "";
					try {
						reformattedStr = myFormat.format(fromUser.parse(goalsDetails.get(lblNames[i]).trim()));
					} catch (Exception e) {
						fromUser = new SimpleDateFormat("yyyy/MM/dd");
						reformattedStr = myFormat.format(fromUser.parse(goalsDetails.get(lblNames[i]).trim()));
					}
					if (weGoalsData.getText().trim().equals(reformattedStr)) {
						passed(goalsDetails.get(lblNames[i]) + " value is matching");
					} else {
						failed(driver, goalsDetails.get(lblNames[i]) + " value is not matching");
					}
				} else if (weGoalsData.getText().trim().equals(goalsDetails.get(lblNames[i]).trim())) {
					passed(goalsDetails.get(lblNames[i]) + " value is matching");
				} else {
					failed(driver, goalsDetails.get(lblNames[i]) + " value is not matching");
				}
			}
			/*
			 * WebElement goalsPopupUpClose = driver.findElement( By.
			 * xpath("//div[contains(@class,'paperAnchorRight')]//*[local-name()='svg' and @fill='none']"
			 * )); if (isElementPresent(goalsPopupUpClose)) { clickOn(goalsPopupUpClose,
			 * "goalsPopupUpClose"); }
			 */
			sleep(1000);
			clickOn(btnsaveAndClose, "close metrics RHP");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void expandTopic() {
		try {
			WebElement expandTopic = null;
			for (int i = 0; i < 5; i++) {
				try {
					expandTopic = driver.findElement(By.xpath("//span[text()='" + data.get("TopicName")
							+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
					break;
				} catch (NoSuchElementException e) {
					Thread.sleep(5000);
					driver.navigate().refresh();
					waitForIsClickable(btnFilter);
					selectPeriodFromCatalogDetailsPage(data.get("Period"));
				}
			}
			clickOn(expandTopic, "Expand Topic ==> " + data.get("TopicName"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int enterMetricDataLessThanBaseValueToCheckGoalProgress(String inputType) {
		int metricData = 0;
		try {
			int baseValue, targetValue;
			baseValue = Integer.parseInt(data.get("BaseValue"));
			targetValue = Integer.parseInt(data.get("TargetValue"));
			Random rnd = new Random();
			int max = 10000, min = 1, num1;
			// num1 = rnd.nextInt((max - min) + 1) + min;
			metricData = rnd.nextInt((baseValue - min) + 1) + min;
			WebElement weMetric = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName") + "']"));
			verifyIfElementPresent(weMetric, "Topic ==> " + data.get("MetricName"), "Topics and Metric Details");
			SelectMetricValueForRespectiveMetric(data.get("MetricName"));
			if (inputType.equals("Number")) {
				enterDataValueForNumberType(metricData);
			} else {
				enterDataValueForIntegerType(metricData);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return metricData;
	}

	public int enterMetricDataGreaterThanBaseValueToCheckGoalProgress(String inputType) {
		int metricData = 0;
		try {
			int baseValue, targetValue;
			baseValue = Integer.parseInt(data.get("BaseValue"));
			targetValue = Integer.parseInt(data.get("TargetValue"));
			Random rnd = new Random();
			int max = baseValue + 1000, min = 1, num1;
			// num1 = rnd.nextInt((max - min) + 1) + min;
			metricData = rnd.nextInt((max - baseValue) + 1) + baseValue;
			WebElement weMetric = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName") + "']"));
			verifyIfElementPresent(weMetric, "Topic ==> " + data.get("MetricName"), "Topics and Metric Details");
			SelectMetricValueForRespectiveMetric(data.get("MetricName"));
			if (inputType.equals("Number")) {
				enterDataValueForNumberType(metricData);
			} else {
				enterDataValueForIntegerType(metricData);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return metricData;
	}

	String goalBaseValue = "", goalTargetValue, goalMetricDataValue, goalProgressPerc;

	public String setMetricBaseAndTargetValues(int row) {
		try {
			HashMap<Integer, String> data = new HashMap<>();
			data.put(1, "YES,NO,YES,0");
			data.put(2, "YES,NO,NO,100");
			data.put(3, "YES,YES,YES,100");
			data.put(4, "NO,YES,YES, 100");
			data.put(5, "NO,YES,NO, 0");
			data.put(6, "NO, NO, YES, 0");
			String dataSet = data.get(row);
			String[] dataArr = dataSet.split(",");
			goalBaseValue = dataArr[0];
			goalTargetValue = dataArr[1];
			goalMetricDataValue = dataArr[2];
			goalProgressPerc = dataArr[3];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goalProgressPerc;
	}

	public HashMap<String, String> getCatalogMetricsDataPostExecutionOfInputMapWorkDay(ArrayList<String> lstTopicName) {
		HashMap<String, String> metricDataMap = new HashMap<>();
		try {
			navigateToCatalogPage();
			searchAndSelectCatalogCard(data.get("CatalogName"));
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
			}
			for (String metricName : lstTopicName) {
				SelectMetricValueForRespectiveMetric(metricName);
				WebElement weData = driver.findElement(By.xpath("//p[text()='Data']/parent::div/div//p"));
				System.out.println(weData.getText());
				metricDataMap.put(metricName, weData.getText());
				clickOn(btnCloseIcon, "Close icon");
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return metricDataMap;
	}

	@FindBy(xpath = "//p[text()='Data']//following-sibling::div/div/span[1]")
	private WebElement weDataValueSingleSelect;

	public void selectDataValueForSingleSelectType() {
		try {
			// clickOn(weDataValueSingleSelect, "Data Value");
			jsClick(weDataValueSingleSelect, "Data Value");
			WebElement rdDataValue = driver.findElement(
					By.xpath("//span[text()='" + goalMetricDataValue + "']/preceding-sibling::span//input"));
			clickOn(rdDataValue, "Data Value");
			clickOn(btnCloseIcon, "Close icon");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectMetricDataToCheckGoalProgressForSingleSelectInputType() {
		try {
			WebElement weMetric = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName") + "']"));
			verifyIfElementPresent(weMetric, "Topic ==> " + data.get("MetricName"), "Topics and Metric Details");
			SelectMetricValueForRespectiveMetric(data.get("MetricName"));
			selectDataValueForSingleSelectType();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyCreateGoalsForSingleSelectAndEnterBaseAndTargetValue() {
		try {
			// selectExisitingCatalogCard(data.get("Catalog"));
			selectPeriodFromCatalogDetailsPage(data.get("Period"));
			SelectGoalsFromMetric();
			// selectAndEditGoals(data.get("GoalName"));
			String goalName = data.get("GoalName");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MMHH:mm:ss");
			Date date = new Date();
			System.out.println(formatter.format(date));
			String sNow = formatter.format(date).replaceAll("[-:/.//s]", "");
			if (goalName == "") {
				goalName = "TestGoal_" + sNow;
			}
//			if (isElementPresent(btnAddGoals)) {
//				clickOn(btnAddGoals, "Add Goals");
//			} else {
//				clickOn(weAddGoalForNoGoalsText, "Add Goals");
//			}
			EnterDetailsInGoalsPage();
			WebElement weBaseDateVal;
			WebElement weDrpBaseDateVal = driver.findElement(
					By.xpath("//span[text()='Base Value']/following-sibling::div/div/div[@role='button']"));
			clickOn(weDrpBaseDateVal, "BaseDataValue");
			Thread.sleep(1500);
			weBaseDateVal = driver
					.findElement(By.xpath("//div[@id='menu-baseDataValue']//ul/li[text()='" + goalBaseValue + "']"));
			String val = weBaseDateVal.getText();
			clickOn(weBaseDateVal, "Selected " + val);
			WebElement weTargetDateVal;
			WebElement weDrpTargetDateVal = driver.findElement(
					By.xpath("//span[text()='Target Value']/following-sibling::div/div/div[@role='button']"));
			clickOn(weDrpTargetDateVal, "Target Value");
			Thread.sleep(1500);
			weTargetDateVal = driver.findElement(
					By.xpath("//div[@id='menu-targetDataValue']//ul/li[text()='" + goalTargetValue + "']"));
			val = weTargetDateVal.getText();
			clickOn(weTargetDateVal, "Selected " + val);
			Thread.sleep(3000);
			clickOn(btnSave, "Save");
			Thread.sleep(3000);
			WebElement weMetric = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName") + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", weMetric);
			verifyIfElementPresent(weMetric, "Topic ==> " + data.get("MetricName"), "Topics and Metric Details");
			// WebElement goals =
			// driver.findElement(By.xpath("//span[text()='"+data.get("MetricName")+"']/ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='Goals']//button"));
			//
			// clickOn(goals, "Goals for ==> "+data.get("TopicName"));
			// SelectGoalsFromMetric();
			// span[@aria-label="Test_Metric_1010"]/following-sibling::div/span[text()='Goals']
			/*
			 * WebElement wegoals = driver.findElement(By.xpath("//span[text()='" +
			 * data.get("MetricName") +
			 * "']/ancestor::div[@col-id='metricName']//following-sibling::div//button[@aria-label='Add Goal']"
			 * )); Actions builder = new Actions(driver); ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView();", wegoals); WebElement
			 * myElement = new WebDriverWait(driver,
			 * 20).until(ExpectedConditions.visibilityOf(wegoals)); ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView();", myElement);
			 * builder.moveToElement(wegoals).click().build().perform(); Thread.sleep(3000);
			 * WebElement goalsPopupUp = driver.findElement(By.xpath("//span[@aria-label='"
			 * + data.get("MetricName") +
			 * "']/following-sibling::div/span[text()='Goals']")); ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView(true);", goalsPopupUp);
			 * verifyIfElementPresent(goalsPopupUp, "Goals popup for ==> " +
			 * data.get("MetricName"), "Goals popup Details"); Thread.sleep(3000);
			 * WebElement weGoal = driver.findElement(By.xpath("//div[text()='" +
			 * GlobalVariables.goalName + "']")); ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView(true);", weGoal);
			 * verifyIfElementPresent(weGoal, GlobalVariables.goalName + " goal name ",
			 * "Goals Detaisl page"); WebElement goalsPopupUpClose = driver.findElement( By.
			 * xpath("//div[contains(@class,'paperAnchorRight')]//*[local-name()='svg' and @fill='none']"
			 * )); if (isElementPresent(goalsPopupUpClose)) { ((JavascriptExecutor)
			 * driver).executeScript("arguments[0].scrollIntoView(true);",
			 * goalsPopupUpClose); clickOn(goalsPopupUpClose, "goalsPopupUpClose"); }
			 */
			WebElement weGoal = driver.findElement(By.xpath("//div[text()='" + GlobalVariables.goalName + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weGoal);
			verifyIfElementPresent(weGoal, GlobalVariables.goalName + " goal name ", "Goals Detaisl page");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int enterMetricDataEqualsToBaseValueToCheckGoalProgress(String inputType) {
		int metricData = 0;
		try {
			int baseValue, targetValue;
			baseValue = Integer.parseInt(data.get("BaseValue"));
			targetValue = Integer.parseInt(data.get("TargetValue"));
			Random rnd = new Random();
			int max = 10000, min = 100, num1;
			// metricData = rnd.nextInt((targetValue - baseValue) + 1) + baseValue;
			metricData = baseValue;
			WebElement weMetric = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName") + "']"));
			verifyIfElementPresent(weMetric, "Topic ==> " + data.get("MetricName"), "Topics and Metric Details");
			SelectMetricValueForRespectiveMetric(data.get("MetricName"));
			if (inputType.equals("Number")) {
				enterDataValueForNumberType(metricData);
			} else {
				enterDataValueForIntegerType(metricData);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return metricData;
	}

	@FindBy(xpath = "//input[@id='integerBox']")
	private WebElement inputDataVAlue;

	public void enterData() {
		try {
			WebElement dataValue = driver
					.findElement(By.xpath("//p[text()='Data']/parent::div//div[@data-gramm='false']"));
			waitForElement(dataValue);
			Actions builder = new Actions(driver);
			builder.moveToElement(dataValue).doubleClick().perform();
			String randomInteger = generateRandomNumber(2);
			sleep(1000);
			enterText(inputDataVAlue, "mertics value", randomInteger);
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
	}

	@FindBy(xpath = "//input[@id='integerBox']")
	private WebElement txtDataIntegerBox;

	public void enterDataValueForIntegerType(int value) {
		try {
//			Actions builder = new Actions(driver);
//			WebElement dataValue = driver
//					.findElement(By.xpath("//p[text()='Data']/parent::div//div[@data-gramm='false']"));
//			waitForElement(dataValue);
//			builder.moveToElement(dataValue).doubleClick().perform();
//			sleep(1000);
//			String valueConevrtToString = Integer.toString(value);
//			enterText(inputDataVAlue, "mertics value", valueConevrtToString);
			clickOn(weDataValue, "Data Value");
			if (!isElementPresent(txtDataIntegerBox)) {
				clickOn(weDataValue, "Data Value");
				enterText(txtDataIntegerBox, "Data Value Text field", Integer.toString(value));
			} else {
				// txtDataNumberBox.clear();
				txtDataIntegerBox.sendKeys(Keys.CONTROL + "a");
				txtDataIntegerBox.sendKeys(Keys.DELETE);
				enterText(txtDataIntegerBox, "Data Value Text field", Integer.toString(value));
			}
			clickOn(btnCloseIcon, "Close icon");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// -----------------------New Catalog Publish
	// blocker-----------------------------------------------------------------------------------

	public void clickOnCatalogInCatalogPage(String catalogName) {
		String xpathCatalogName = "//article//*[@aria-label='" + catalogName + "']";
		System.out.println(xpathCatalogName);
		WaitForElementWithDynamicXpath(xpathCatalogName);
		if (isElementPresentDynamicXpath(xpathCatalogName)) {
			clickOnElementWithDynamicXpath(xpathCatalogName, "Catalog Name");
		} else {
			validateCreateCatalogWithCustomName(catalogName);
			clickOnElementWithDynamicXpath(xpathCatalogName, "Catalog Name");
		}
	}

	public void scrollToCatalogView(String catalogName) {
		String xpathCatalogName = "//article//*[text()='" + catalogName + "']";
		WebElement weCatalog = driver.findElement(By.xpath(xpathCatalogName));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", weCatalog);
		sleep(500);
	}

	public void validateCreateCatalogWithCustomName(String catalogName) {
		try {
			clickOn(btnCreateCatalog, "Create Catalog Button");
			clickOn(btnForwardIcon, "Forward Icon Button");
			clickOn(btnForwardIcon, "Forward Icon Button");
			GlobalVariables.catalogName = catalogName;
			enterText(txtCatalogName, "CatalogName", GlobalVariables.catalogName);
			enterText(txtCatalogDescription, "CatalogDescricption",
					Constants.catalogDesc + "_GlobalVariables.catalogName");
			clickOn(drpSelectCatalogType, "CatalogType");
			WebElement selectCatalogType = driver
					.findElement(By.xpath("//div/ul/li[text()='" + Constants.catalogType + "']"));
			clickOn(selectCatalogType, "Select Catalog Type List");
			clickOn(btnCreate, "Create button");
			waitForElement(btnClose);
			Thread.sleep(3000);
			String strCrtCatalogSuccMsg = driver
					.findElement(By.xpath("//*[@data-testid='CloseIcon']/following-sibling::div[1]")).getText();
			clickOn(btnClose, "Close button");
			// ValidateCreateTopicAndMetricForCatalog(); // undo comment.
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
	}

	WebElement weExpand;

	public void expandTopicInCatalog(String TopicName) {
		try {
			String xpathExpandDataRequest = "//*[text()='" + TopicName
					+ "']//parent::span//parent::span[@ref='eValue']//preceding-sibling::span[@ref='eContracted']";
			WaitForElementWithDynamicXpath(xpathExpandDataRequest);
			weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
//			scrollTo(btnAddTopic);
			sleep(500);
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOn(weExpand, "Expand Topic");
//				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Topic");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void expandTopicInCatalogPublishBlocker(String topicName) {
		try {
			String xpathExpandDataRequest = "//div[text()='Unable to Publish']//following::span[contains(text(),'"
					+ topicName
					+ "')]//parent::span//parent::span[@ref='eValue']//preceding-sibling::span[@ref='eContracted']";
			WaitForElementWithDynamicXpath(xpathExpandDataRequest);
			weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Topic");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateTopicNameinPublishBlocker(String topicName) {
		String xpathTopicName = "//div[text()='Unable to Publish']//following::span[contains(text(),'" + topicName
				+ "')]";
		WaitForElementWithDynamicXpath(xpathTopicName);
		if (isElementPresentDynamicXpath(xpathTopicName)) {
			passed("Successfully Validated Topic name in Publish Blocker popup as " + topicName);
		} else {
			failed(driver, "Failed to validate topic Name in Publish Blocker popup  Expected as " + topicName);
		}
	}

	public void validateMetricNameinPublishBlocker(String metricName) {
		String xpathMetricName = "//div[text()='Unable to Publish']//following::div/span[text()='" + metricName + "']";
		WaitForElementWithDynamicXpath(xpathMetricName);
		if (isElementPresentDynamicXpath(xpathMetricName)) {
			passed("Successfully Validated Metric name in Publish Blocker popup as " + metricName);
		} else {
			failed(driver, "Failed to validate Metric Name in Publish Blocker popup  Expected as " + metricName);
		}
	}

	public void contractTopicInCatalog(String TopicName) {
		try {
			String xpathExpandDataRequest = "//*[contains(text(),'" + TopicName
					+ "')]//parent::span//parent::span[@ref='eValue']//preceding-sibling::span[@ref='eContracted']";
			WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			String classExpand = weExpand.getAttribute("class");
			if (classExpand.contains("hidden")) {
				String xpathContractDataRequest = "//*[contains(text(),'" + TopicName
						+ "')]//parent::span//parent::span[@ref='eValue']//preceding-sibling::span[@ref='eExpanded']";
				clickOnElementWithDynamicXpath(xpathContractDataRequest, "Contract  Topic");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void clickOnMetricName(String metricName) {
		try {
			((JavascriptExecutor) driver)
					.executeScript("document.querySelector('div[ref=eBodyViewport]').scrollTop=-2000");
			WebElement weMetricValue = driver.findElement(By.xpath("//span[text()='" + metricName
					+ "']//ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']"));
//	    Actions builder = new Actions(driver);
//	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weMetricValue);
//	    WebElement myElement = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(weMetricValue));
//	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
//	    builder.moveToElement(weMetricValue).click().build().perform();
			waitForElement(weMetricValue);
			jsClick(weMetricValue, "MetricValueFor" + "");
		} catch (Exception e) {
			System.out.println("Exception here *************************>");
			clickOnMetricName(metricName);
		}
	}

	public void enterDataForNumInputType(String metricValue) {
		try {
			WebElement weMetricData = driver.findElement(By.xpath("//p[text()='Data']//following-sibling::div//p"));
			waitForElement(weMetricData);
			jsClick(weMetricData, "Metric Data");
			sleep(3000);
			WebElement MetricValueData = driver
					.findElement(By.xpath("(//p[text()='Data']//following-sibling::div//input)[1]"));
			waitForElement(MetricValueData);
			MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			MetricValueData.sendKeys(Keys.BACK_SPACE);
			enterText(MetricValueData, "Metric Value Data", metricValue);
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			enterDataForNumInputType(data.get("CustomMetric5Value"));
		}
	}

	public void clickOnDataForIntegerInputType() {
		try {
			WebElement weMetricData = driver
					.findElement(By.xpath("//p[text()='Data']//parent::div//following::div[@class='ql-editor']"));
			waitForElement(weMetricData);
			jsClick(weMetricData, "Metric Data");
			sleep(1000);
		} catch (Exception e) {
			System.out.println("No Element Preset in Data Input");
			clickOnDataForIntegerInputType();
		}
	}

	public void clickOnDataForNumberInputType() {
		try {
			WebElement weMetricData = driver
					.findElement(By.xpath("//p[text()='Data']//following-sibling::div//p//parent::div"));
			waitForElement(weMetricData);
			jsClick(weMetricData, "Metric Data");
			sleep(500);
		} catch (Exception e) {
			System.out.println("No Element Preset in Data Input");
			clickOnDataForIntegerInputType();
		}
	}

	@FindBy(xpath = "//span[text()='Click here to view values']")
	private WebElement btnTable;

	// ----------------------Pending
	public void clickOnDataForSingleSelectInputType() {
		try {
			WebElement weMetricData = driver.findElement(By.xpath("//p[text()='Data']//following-sibling::div"));
			waitForElement(weMetricData);
			jsClick(weMetricData, "Metric Data");
			sleep(500);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
//	    System.out.println("Exception comming here------------------->");
//	    clickOnDataForIntegerInputType();
		}
	}

	public void clickOnClosebuttonInMetricDetailsRHP() {
		clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
	}

	public void enterDataForIntegerInputType(String metricValue) {
		try {
			WebElement MetricValueData = driver
					.findElement(By.xpath("(//p[text()='Data']//following-sibling::div//input)[1]"));
			waitForElement(MetricValueData);
			MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			MetricValueData.sendKeys(Keys.BACK_SPACE);
			enterText(MetricValueData, "Metric Value Data", metricValue);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void enterDataForIntegerInputType_ChildMetric(String relatedMetricName,String metricValue) {
		try {
			WebElement MetricValueData = driver
					.findElement(By.xpath("//p[text()='Related Metrics']//following::p[text()='"+relatedMetricName+"']/following::div//input[1]"));
			waitForElement(MetricValueData);
			MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			MetricValueData.sendKeys(Keys.BACK_SPACE);
			enterText(MetricValueData, "Metric Value Data", metricValue);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void enterDataForIntegerInputTypeasPrimaryMetric(String metricName , String metricValueChild) {
		try {
			clickOnMetricName(metricName);
			clickOnDataForNumberInputType();
			clearInputTypeTextBox();
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			sleep(500);
			clickOnMetricName(metricName);
			clickOnDataForNumberInputType();
			WebElement MetricValueData = driver
					.findElement(By.xpath("(//p[text()='Data']//following-sibling::div//input)[1]"));
			waitForElement(MetricValueData);
			MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			MetricValueData.sendKeys(Keys.BACK_SPACE);
			sleep(500);
			enterText(MetricValueData, "Metric Value Data", metricValueChild);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void validateFormulaParentMetricValue(String metricName, String metricValue) {
		try {
			driver.navigate().refresh();
			sleep(1000);
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(metricName);
			WebElement MetricValueData = driver
					.findElement(By.xpath("//p[text()='Data']//following-sibling::div//p"));
			if(MetricValueData.getText().trim().equals(metricValue)) {
				passed("Successfully validated formula parent metric value for metric " + metricName + " as " + metricValue);
			}else {
				failed(driver, "Failed to validate formula parent metric value for metric " + metricName + " as " + metricValue);
			}
		} catch (Exception e) {
		}
	}

	
	public void enterDataSingleSelectType(String metricValue) {
		try {
			WebElement MetricValueData = driver
					.findElement(By.xpath("//p[text()='Data']//parent::div//div//span[text()='" + metricValue
							+ "']//parent::div//span//input"));
			waitForElement(MetricValueData);
			clickOn(MetricValueData, metricValue);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void enterDataForSingleSelectInputTypeRelatedMetrics(String metricValue) {
		try {
			clickOnResetChildMetricButton(data.get("RelatedMetricName"));
			WebElement MetricValueData = driver.findElement(
					By.xpath("//p[text()='Related Metrics']//following::p[text()='"+data.get("RelatedMetricName")+"']//parent::div//parent::div//parent::div//parent::div//parent::div//span[text()='"+metricValue+"']//preceding::input[1]"));
			scrollToViewElement(MetricValueData);
			waitForElement(MetricValueData);
			clickOn(MetricValueData, metricValue);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}	
	
	public void enterDataForTextInputType(String metricValue) {
		try {
			WebElement weMetricData = driver.findElement(By.xpath("//p[text()='Data']//following-sibling::div/span"));
			jsClick(weMetricData, "Metric Data");
			sleep(2000);
			WaitForElementWithDynamicXpath("//p[text()='Data']//following-sibling::div//input");
			WebElement MetricValueData = driver
					.findElement(By.xpath("//p[text()='Data']//following-sibling::div//input"));
			MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			MetricValueData.sendKeys(Keys.BACK_SPACE);
			enterText(MetricValueData, "Metric Value Data", metricValue);
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			enterDataForTextInputType(data.get("CustomMetric2Value"));
		}
	}

	public void enterDataForDescriptionInputType(String metricName, String metricValue) {
		try {
			WebElement weMetricData = driver.findElement(By.xpath("//p[text()='Data']//following-sibling::div/span"));
			jsClick(weMetricData, "Metric Data");
			WaitForElementWithDynamicXpath("//p[text()='Data']//following::div[contains(@class,'ql-editor')]//p");
			WebElement MetricValueData = driver
					.findElement(By.xpath("//p[text()='Data']//following::div[contains(@class,'ql-editor')]//p"));
			MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			MetricValueData.sendKeys(Keys.BACK_SPACE);
			enterText(MetricValueData, "Metric Value Data", metricValue);
			jsClick(btnCloseInMetricDetails, "Metric Details Close Button");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			enterDataForTextInputType(data.get("CustomMetric7Value"));
		}
	}

	public void enterDataForBooleanInputType_old(String metricValue) {
		try {
//	    WebElement weMetricData = driver.findElement(By.xpath("//p[text()='Data']//following-sibling::div/span"));
//	    jsClick(weMetricData, "Metric Data");
			WaitForElementWithDynamicXpath("//span//input[@value='" + metricValue + "']");
			WebElement MetricValueData = driver.findElement(By.xpath("//span//input[@value='" + metricValue + "']"));
			clickOn(MetricValueData, metricValue);
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
//   	    WebElement weMetricData = driver.findElement(By.xpath("//p[text()='Data']//following-sibling::div/span"));
//   	    jsClick(weMetricData, "Metric Data");
			if (metricValue.equals("NA")) {
			} else {
				WaitForElementWithDynamicXpath("//span//input[@value='" + metricValue + "']");
				WebElement MetricValueData1 = driver
						.findElement(By.xpath("//span//input[@value='" + metricValue + "']"));
				clickOn(MetricValueData1, metricValue);
			}
			// clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			enterDataForBooleanInputType(data.get("CustomMetric1Value"));
		}
	}

	public void enterDataForBooleanInputType(String metricValue) {
		try {
//   	    WebElement weMetricData = driver.findElement(By.xpath("//p[text()='Data']//following-sibling::div/span"));
//   	    jsClick(weMetricData, "Metric Data");
//		WaitForElementWithDynamicXpath("//span//input[@value='" + metricValue + "']");
			clickOn(btnReset, "btnReset");
			sleep(1000);
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			sleep(1500);
			clickOnMetricName(data.get("MetricName"));
			sleep(1000);
			WebElement MetricValueData = driver.findElement(By.xpath("//span//input[@value='" + metricValue + "']"));
//		jsClick(MetricValueData, metricValue);
			MetricValueData.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			enterDataForBooleanInputType(metricValue);
		}
	}

	@FindBy(xpath = "//span[text()='Data not available']//parent::span//preceding-sibling::span//input")
	private WebElement chkDataNotAvailable;
	@FindBy(xpath = "//button//span[text()='Next']")
	private WebElement btnNext;
	@FindBy(xpath = " //button//span[text()='Prev']")
	private WebElement btnPrev;

	public void clickOnDataNotAvailablecheckBoxToCheck() {
		try {
			WebElement chkBoxValue = driver.findElement(By.xpath(
					"//span[text()='Data not available']//parent::span//preceding-sibling::span//input//following-sibling::*"));
			if (chkBoxValue.getAttribute("data-testid").contains("CheckBoxOutlineBlankIcon")) {
				clickOn(chkDataNotAvailable, "Data Not avaialble Check box ");
			}
		} catch (Exception e) {
			System.out.println("Exception Here &&&&&&&&");
			clickOnDataNotAvailablecheckBoxToCheck();
		}
	}

	public void clickOnDataNotAvailablecheckBoxToUncheck() {
		try {
			WebElement chkBoxValue = driver.findElement(By.xpath(
					"//span[text()='Data not available']//parent::span//preceding-sibling::span//input//following-sibling::*"));
			if (chkBoxValue.getAttribute("data-testid").contains("CheckBoxIcon")) {
				clickOn(chkDataNotAvailable, "Data Not avaialble Check box ");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
			System.out.println("Exception Here &&&&&&&&");
			clickOnDataNotAvailablecheckBoxToUncheck();
		}
	}

	@FindBy(xpath = "//p[text()='Related Metrics']//following::span[text()='Data not available']//parent::span//preceding-sibling::span//input")
	private WebElement chkDataNotAvailableRelatedMetric;

	public void clickOnDataNotAvailablecheckBoxTocheckForRelatedMetric() {
		try {
			WebElement chkBoxValue = driver.findElement(By.xpath(
					"//p[text()='Related Metrics']//following::span[text()='Data not available']//parent::span//preceding-sibling::span//input//following-sibling::*"));
			Actions act = new Actions(driver);
			if (chkBoxValue.getAttribute("data-testid").contains("CheckBoxOutlineBlankIcon")) {
				clickOn(chkDataNotAvailableRelatedMetric, "Data Not avaialble Check box for Related Metric");
				sleep(500);
				act.moveToElement(chkBoxValue).perform();
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
			System.out.println("Exception Here &&&&&&&&");
			clickOnDataNotAvailablecheckBoxTocheckForRelatedMetric();
		}
	}

	public void clickOnDataNotAvailablecheckBoxTocheck() {//
		try {
			WebElement chkBoxValue = driver.findElement(By.xpath(
					"//span[text()='Data not available']//parent::span//preceding-sibling::span//input//following-sibling::*"));
			if (chkBoxValue.getAttribute("data-testid").contains("CheckBoxOutlineBlankIcon")) {
				clickOn(chkDataNotAvailable, "Data Not avaialble Check box ");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
			System.out.println("Exception Here &&&&&&&&");
			clickOnDataNotAvailablecheckBoxToUncheck();
		}
	}

	public void clearInputTypeTextBox() {
		try {
			WaitForElementWithDynamicXpath("//p[text()='Data']//following-sibling::div//input");
			WebElement MetricValueData = driver
					.findElement(By.xpath("//p[text()='Data']//following-sibling::div//input"));
			MetricValueData.click();
			MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			MetricValueData.sendKeys(Keys.BACK_SPACE);
			MetricValueData.clear();
		} catch (Exception e) {
			clearInputTypeTextBox();
		}
	}

	@FindBy(xpath = "//p[text()='Evidence']//parent::div")
	private WebElement btnEvidenceInMetricDetailsRHP;
	@FindBy(xpath = "//*[@data-testid='AddIcon']")
	private WebElement btnAddEvidenceInMetricDetailsRHP;
	@FindBy(xpath = "//th//*[@fill='none']")
	private WebElement btnDeleteEvidence;
	@FindBy(xpath = "//button[text()='Yes']")
	private WebElement btnDeleteYes;
	@FindBy(xpath = "//*[text()='Explanation']//parent::div//following-sibling::div//p//parent::div")
	private WebElement txtExplanation;
	@FindBy(xpath = "(//*[text()='Explanation']//parent::div//following-sibling::div//textarea//following-sibling::span//following-sibling::div)[1]")
	private WebElement txtareaExplanation;
	@FindBy(xpath = "//*[text()='Explanation']//parent::div//following-sibling::div//p")
	private WebElement txtareaExplanationText;
	@FindBy(xpath = "//*[text()='Data']//following::div[@class='errorList' or @class='errorListContent']")
	private WebElement txtErrorMessage;

	public void uploadEvidence() {
		try {
			waitForElement(btnBrowseFiles);
			System.out.println(waitForElement(btnBrowseFiles));
			clickOnElement(btnBrowseFiles, "Browse Files");
			String path = System.getProperty("user.dir");
			System.out.println(path);
			String filePath = path + "\\src\\test\\resources\\DataRequestFiles\\" + data.get("EvidenceFileName");
			ProcessBuilder pb = new ProcessBuilder(path + "\\src\\test\\resources\\DataRequestFiles\\fileUpload.exe",
					filePath);
			pb.start();
			waitForElement(btnUpload);
			clickOnElement(btnUpload, "Upload button");
			waitForElement(btnUploadClose);
			clickOn(btnUploadClose, "Upload Close button");
			sleep(3000);
		} catch (Exception e) {
			System.out.println("Expection At Uploading---->" + e.getMessage());
			uploadEvidence();
		}
	}

	public void enterExplanationInExplanationTextBox() {
		try {
			waitForElement(txtExplanation);
			clickOn(txtExplanation, "Explanation Text");
			enterText(txtExplanation, "Explanation Text Box", data.get("ExplanationText"));
		} catch (Exception e) {
			System.out.println("Exception At EnterExplanation Text");
			enterExplanationInExplanationTextBox();
		}
	}

	public void enterExplanationInExplanationTextBoxSingleSelect() {
		try {
			if (Boolean.parseBoolean(data.get("IsCustomMessage"))) {
				if (isElementPresentDynamicXpath(
						"//*[text()='" + Constants.customErrorMessageExplanationMandatory + "']")) {
					passed("Successfully Validated Explanation   Mandatory  Error Custom Message As"
							+ Constants.customErrorMessageExplanationMandatory);
				} else {
					failed(driver, "Failed To Validate Explanation Mandatory Error message");
				}
			}
			if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
				if (isElementPresentDynamicXpath(
						"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
					passed("Successfully Validated Explanation   Mandatory  Error statdared Message As"
							+ Constants.standardErrorMessageExplanationMandatory);
				} else {
					failed(driver, "Failed To Validate Explanation Mandatory Error message");
				}
			}
			enterText(txtExplanation, "Explanation Text Box", data.get("ExplanationText"));
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void clearExplanationInExplanationTextBox() {
		try {
			waitForElement(txtExplanation);
			clickOn(txtExplanation, "Explanation Text");
			sleep(1000);
			// clearText(txtExplanation);
		} catch (Exception e) {
			System.out.println("Exception At EnterExplanation Text");
			enterExplanationInExplanationTextBox();
		}
	}

	@FindBy(xpath = "//input[@id='others']")
	private WebElement txtOthers;

	public void enterTextandValidateOthersCheckBoxInputTypeMetricPublishBlocker() {
		try {
			if (Boolean.parseBoolean(data.get("Other"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
				sleep(1000);
				clickOnDataNotAvailablecheckBoxToUncheck();
				clickOnDataForSingleSelectInputType();
				sleep(3000);
				WaitForElementWithDynamicXpath("//*[text()='" + Constants.inputTypeRequiredErrorMessage + "']");
				if ((isElementPresentDynamicXpath("//*[text()='" + Constants.inputTypeRequiredErrorMessage + "']"))) {
					passed("Successfully Validated Data Required Error Message As"
							+ Constants.inputTypeRequiredErrorMessage);
				} else {
					failed(driver, "Failed To Validate Data Required  Error message");
				}
				Thread.sleep(3000);
				WebElement MetricValueData = driver.findElement(
						By.xpath("//p[text()='Data']//parent::div//span[text()='Other']//parent::div/span"));
				waitForElement(MetricValueData);
				clickOn(MetricValueData, "click on other Check Box");
				waitForElement(txtOthers);
				enterText(txtOthers, "txtOthers", data.get("OthersText"));
				sleep(1000);
				if (!(isElementPresentDynamicXpath("//*[text()='" + Constants.inputTypeRequiredErrorMessage + "']"))) {
					passed("Successfully Validated Data Required Error Message is disapeared ");
				} else {
					failed(driver, "Failed To Validate Data Required  Error message disappear");
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	// -----------------------------------------------MultiSelect
	// InputTypeValidations------------------------------
	public void clickOnDataForMultiSelectInputType() {
		try {
			WebElement weMetricData = driver.findElement(By.xpath("//p[text()='Data']//following-sibling::div"));
			jsClick(weMetricData, "Metric Data");
			sleep(500);
		} catch (Exception e) {
			System.out.println("Exception comming AT ClickonMulti------------------->");
			clickOnDataForMultiSelectInputType();
		}
	}

	public void clearDataInMultiselect() {
		try {
			List<WebElement> lstchkBoxInput = driver.findElements(By.xpath("//span/input"));
			List<WebElement> lstchkBoxSelctValue = driver
					.findElements(By.xpath("//span/input[1]//following-sibling::*"));
			for (int i = 0; i < lstchkBoxSelctValue.size() - 1; i++) {
				if (lstchkBoxSelctValue.get(i).getAttribute("data-testid").contains("CheckBoxIcon")) {
					clickOn(lstchkBoxInput.get(i), "Checkbox index-->" + i);
				}
			}
		} catch (Exception e) {
//	    System.out.println("Exception comming At ClearData------------------->");
			clearDataInMultiselect();
		}
	}

	public void clearDataInMultiselectWithDNACheckbox() {
		clickOnDataNotAvailablecheckBoxToCheck();
		clickOnDataNotAvailablecheckBoxToUncheck();
	}

	public void enterDataForMultiInputType(String metricValue) {
		try {
			String[] arrMultiSelect = null;
			if (metricValue.contains(",")) {
				arrMultiSelect = metricValue.split(",");
				sleep(2000);
				for (int i = 0; i < arrMultiSelect.length; i++) {
					WaitForElementWithDynamicXpath("//p[text()='Data']//following-sibling::div//span[text()='"
							+ arrMultiSelect[i] + "']//preceding::input[1]");
					WebElement MetricValueData = driver
							.findElement(By.xpath("//p[text()='Data']//following-sibling::div//span[text()='"
									+ arrMultiSelect[i] + "']//preceding::input[1]"));
					WebElement chkBoxValue = driver
							.findElement(By.xpath("//p[text()='Data']//following-sibling::div//span[text()='"
									+ arrMultiSelect[i] + "']//preceding::input[1]//following-sibling::*"));
					if (chkBoxValue.getAttribute("data-testid").contains("CheckBoxOutlineBlankIcon")) {
						clickOn(MetricValueData, metricValue);
					}
				}
			} else {
				WebElement MetricValueData = driver
						.findElement(By.xpath("//p[text()='Data']//following-sibling::div//span[text()='" + metricValue
								+ "']//preceding::input[1]"));
				WebElement chkBoxValue = driver
						.findElement(By.xpath("//p[text()='Data']//following-sibling::div//span[text()='" + metricValue
								+ "']//preceding::input[1]//following-sibling::*"));
				if (chkBoxValue.getAttribute("data-testid").contains("CheckBoxOutlineBlankIcon")) {
					clickOn(MetricValueData, metricValue);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	// -----------------------Table Input Type

	@FindBy(xpath = "//button[text()='Add']")
	private WebElement btnAddInTable;
	@FindBy(xpath = "//div/div/*[@fill='none']")
	private WebElement btnDeleteInTable;
	@FindBy(xpath = "//button[text()='Cancel']//following-sibling::button[text()='Save']")
	private WebElement btnSaveInTable;
	@FindBy(xpath = "//*[@fill='none']//following::input[@name='0Boolean']//following-sibling::*[@data-testid='ArrowDropDownIcon']")
	private WebElement btnTableDrpDown;
	@FindBy(xpath = "//input[@id='1Name']")
	private WebElement txtNameTable;
	@FindBy(xpath = "//*[text()='Metric Data']/*[@data-testid='CloseIcon']")
	private WebElement btnTableCloseIcon;

	public void clickOnDataLinkForTableInputType() {
		try {
			WebElement weMetricData = driver
					.findElement(By.xpath("(//p[text()='Data']//following-sibling::div//span)[2]"));
			waitForElement(weMetricData);
			jsClick(weMetricData, "Metric Data");
			sleep(3000);
		} catch (Exception e) {
			System.out.println("Exception comming At Table Input Data Link");
			clickOnDataLinkForTableInputType();
		}
	}

	@FindBy(xpath = "//input[@id='0Name']")
	private WebElement txtNameTableFixedRow;
	@FindBy(xpath = "//div[@id='0Gender']")
	private WebElement drpBooleanGenderFixedRow;
	@FindBy(xpath = "//div[@id='0IsActive']")
	private WebElement drpBooleanIsActiveFixedRow;
	@FindBy(xpath = "//div[@id='0FavCar']")
	private WebElement drpMultiSelectFavCarFixedRow;
	@FindBy(xpath = "//input[@id='0Age']")
	private WebElement txtAgeTableFixedRow;
	@FindBy(xpath = "//div[@id='1Gender']")
	private WebElement drpBooleanGender;
	@FindBy(xpath = "//div[@id='1IsActive']")
	private WebElement drpBooleanIsActive;
	@FindBy(xpath = "//div[@id='1FavCar']")
	private WebElement drpMultiSelectFavCar;
	@FindBy(xpath = "//input[@id='1Age']")
	private WebElement txtAgeTable;
	@FindBy(xpath = "//*[contains(text(),'Duplicate record detected. Please enter Name column(s) as unique values across all records.')]")
	private WebElement txtToastErrorTableMessage;
	@FindBy(xpath = "//*[contains(text(),'Metric detail saved successfully')]")
	private WebElement txtTableToastMessage;
	@FindBy(xpath = "//h2[text()='Metric Data']")
	private WebElement lblMetricDataTable;
	@FindBy(xpath = "//p[text()='FavCar']//following-sibling::div//input")
	private WebElement txtFavCarMultiSelect;
	@FindBy(xpath = "(//p[text()='Name']//parent::div//parent::div//parent::div//div/*)[1]")
	private WebElement btnDeleteMetricValue;

	public void clearDataInTable() {
		try {
			Thread.sleep(2000);
			List<WebElement> lstDeleteButtons = driver.findElements(By.xpath("//div/div/*[@fill='none']"));
			System.out.println(lstDeleteButtons.size());
			if (lstDeleteButtons.size() > 0) {
				for (int i = 0; i < lstDeleteButtons.size(); i++) {
					waitForElement(lstDeleteButtons.get(i));
					clickOnElement(lstDeleteButtons.get(i), "Table data delete ");
				}
			}
			List<WebElement> lstDeleteButtons1 = driver.findElements(By.xpath("//div/div/*[@fill='none']"));
			if (lstDeleteButtons1.size() > 0) {
				for (int i = 0; i < lstDeleteButtons1.size(); i++) {
					waitForElement(lstDeleteButtons1.get(i));
					clickOnElement(lstDeleteButtons1.get(i), "Table data delete ");
				}
			}
			waitForElement(btnSaveInTable);
			clickOn(btnSaveInTable, "Save Button");
			clickOn(btnTableCloseIcon, "Close Button");
		} catch (Exception e) {
			System.out.println("Exception comming At Table Delete");
			clearDataInTable();
		}
	}

	public void enterDataForDescInputType(String metricValue) {
		try {
			WebElement MetricValueData = driver.findElement(
					By.xpath("//p[text()='Data']//following-sibling::div//textarea//following-sibling::div//div/p"));
			System.out.println(waitForElement(MetricValueData));
//	    if (MetricValueData.getText().isEmpty()) {
//	    } else {
//		MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
//		MetricValueData.sendKeys(Keys.BACK_SPACE);
//	    }
			if (metricValue.equals("NA")) {
				enterText(MetricValueData, "Metric Value Data", "");
			} else {
				MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				MetricValueData.sendKeys(Keys.BACK_SPACE);
				sleep(3000);
				MetricValueData.sendKeys(metricValue);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			enterDataForDescInputType(metricValue);
		}
	}

	public void clearDescInputTypeTextBox() {
		try {
			WaitForElementWithDynamicXpath("//p[text()='Data']/parent::div/div/div/div/div[2]/div/p");
			WebElement MetricValueData = driver
					.findElement(By.xpath("//p[text()='Data']/parent::div/div/div/div/div[2]/div/p"));
			if (MetricValueData.getText().isEmpty()) {
			} else {
				MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				MetricValueData.sendKeys(Keys.BACK_SPACE);
				MetricValueData.clear();
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
			clearDescInputTypeTextBox();
		}
	}

	public void ValidatePublishBlocker(String topicName) {
		switch (topicName) {
		case "Int":
			ValidateIntInputTypeMetricPublishBlocker_DRFlow();
			break;
		case "Num":
			ValidateNumInputTypeMetricPublishBlocker_DRFlow();
			break;
		case "Text":
			ValidateTextInputTypeMetricPublishBlocker_DRFlow();
			break;
		case "SingleSelect":
			ValidateSingleSelectInputTypeMetricPublishBlocker_DRFlow();
			break;
		case "MultiSelect":
			ValidateMultiSelectInputTypeMetricPublishBlocker_DRFlow();
			break;
		case "Table":
			ValidateTableInputTypeMetricPublishBlocker_DRFlow();
			break;
		case "Desc":
			ValidateDescInputTypeMetricPublishBlocker_DRFlow();
			break;
		case "Boolean":
			ValidateBooleanInputTypeMetricPublishBlocker_DRFlow();
			break;
		default:
		}
	}

	public void ValidateNumInputTypeMetricPublishBlocker_DRFlow() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			sleep(3000);
			clickOnDataForIntegerInputType();
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			} else {
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
					clickOnDataNotAvailablecheckBoxToUncheck();
				}
				clearInputTypeTextBox();
				enterDataForIntegerInputType(data.get("RHPMetricValue"));
			}
			if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
				if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
					WaitForElementWithDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']");
					if (isElementPresentDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']")) {
						passed("Successfully Validated Mandatory Evidence   Error Message "
								+ Constants.standardErrorMessageMandatoryEvidence);
					} else {
						failed(driver, "Failed To Validate Mandatory Evidence Error message");
					}
				}
				if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
					WaitForElementWithDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']");
					if (isElementPresentDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
						passed("Successfully Validated Mandatory Explanation   Error Message "
								+ Constants.standardErrorMessageExplanationMandatory);
					} else {
						failed(driver, "Failed To Validate Mandatory Explanation  Error message");
					}
				}
			}
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))
					&& Boolean.parseBoolean(data.get("IsDataRequiredMessage"))) {
				clickOnDataForIntegerInputType();
				clickOnDataNotAvailablecheckBoxToUncheck();
				clickOnDataForIntegerInputType();
				clearInputTypeTextBox();
				WaitForElementWithDynamicXpath("//*[text()='" + Constants.RequiredErrorMessageWithDNA + "']");
				if ((isElementPresentDynamicXpath("//*[text()='" + Constants.RequiredErrorMessageWithDNA + "']"))) {
					passed("Successfully Validated Data Required Error Message As"
							+ Constants.RequiredErrorMessageWithDNA);
				} else {
					failed(driver, "Failed To Validate Data Required  Error message");
				}
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			validateMetricValidationsonAGgridofCatalog();
			clickOn(btnPublish, "Publish Button");
			waitForElement(msgUnableToPublish);
			if (isElementPresent(msgUnableToPublish)) {
				passed("Successfully Validated Publish blocker");
			} else {
				failed(driver, "Failed To Validate Publish Blocker");
			}
			validateTopicNameinPublishBlocker(data.get("TopicName"));
			expandTopicInCatalogPublishBlocker(data.get("TopicName"));
			if (Boolean.parseBoolean(data.get("IsRelatedMetric"))) {
				validateMetricNameinPublishBlocker(data.get("RelatedMetricName"));
			} else {
				validateMetricNameinPublishBlocker(data.get("MetricName"));
			}
			waitForElement(btnOk);
			clickOn(btnOk, "Ok button");
			contractTopicInCatalog(data.get("TopicName"));
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateTextInputTypeMetricPublishBlocker_DRFlow() {
		try {
			sleep(1000);
//		Actions act = new Actions(driver);
//		act.moveToElement(weExpand).perform();
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			sleep(3000);
			clickOnDataForIntegerInputType();
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			} else {
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
					clickOnDataNotAvailablecheckBoxToUncheck();
				}
				clearInputTypeTextBox();
				enterDataForIntegerInputType(data.get("RHPMetricValue"));
			}
			if (isNumber(data.get("RHPMetricValue"))) {
				clearInputTypeTextBox();
				enterDataForIntegerInputType(data.get("RHPMetricValue"));
				clickOn(btnNext, "Button Next Metrics");
				clickOn(btnPrev, "Button Previous Metrics");
				clickOnDataForIntegerInputType();
				WaitForElementWithDynamicXpath("//*[text()='" + Constants.inputTypeRequiredErrorMessageText + "']");
				if (isElementPresentDynamicXpath("//*[text()='" + Constants.inputTypeRequiredErrorMessageText + "']")) {
					passed("Successfully Validated  [a-z] Error message" + Constants.inputTypeRequiredErrorMessageText);
				} else {
					failed(driver, "Failed To Validate [a-z] Error message ");
				}
			}
			if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
				if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
					WaitForElementWithDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']");
					if (isElementPresentDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']")) {
						passed("Successfully Validated Mandatory Evidence   Error Message "
								+ Constants.standardErrorMessageMandatoryEvidence);
					} else {
						failed(driver, "Failed To Validate Mandatory Evidence Error message");
					}
				}
				if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
					WaitForElementWithDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']");
					if (isElementPresentDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
						passed("Successfully Validated Mandatory Explanation   Error Message "
								+ Constants.standardErrorMessageExplanationMandatory);
					} else {
						failed(driver, "Failed To Validate Mandatory Explanation  Error message");
					}
				}
			}
			if (!Boolean.parseBoolean(data.get("DataNotAvailable"))
					&& Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataForIntegerInputType();
				clearInputTypeTextBox();
				WaitForElementWithDynamicXpath("//*[text()='" + Constants.RequiredErrorMessageWithDNA + "']");
				if ((isElementPresentDynamicXpath("//*[text()='" + Constants.RequiredErrorMessageWithDNA + "']"))) {
					passed("Successfully Validated Data Required Error Message As"
							+ Constants.RequiredErrorMessageWithDNA);
				} else {
					failed(driver, "Failed To Validate Data Required  Error message");
				}
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			validateMetricValidationsonAGgridofCatalog();
			clickOn(btnPublish, "Publish Button");
			waitForElement(msgUnableToPublish);
			if (isElementPresent(msgUnableToPublish)) {
				passed("Successfully Validated Publish blocker");
			} else {
				failed(driver, "Failed To Validate Publish Blocker");
			}
			validateTopicNameinPublishBlocker(data.get("TopicName"));
			expandTopicInCatalogPublishBlocker(data.get("TopicName"));
			if (Boolean.parseBoolean(data.get("IsRelatedMetric"))) {
				validateMetricNameinPublishBlocker(data.get("RelatedMetricName"));
			} else {
				validateMetricNameinPublishBlocker(data.get("MetricName"));
			}
			waitForElement(btnOk);
			clickOn(btnOk, "Ok button");
			contractTopicInCatalog(data.get("TopicName"));
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateDescInputTypeMetricPublishBlocker_DRFlow() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			sleep(1000);
			clickOnDataForDescInputType();
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			} else {
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
					clickOnDataNotAvailablecheckBoxTocheck();
					clickOnDataNotAvailablecheckBoxToUncheck();
				}
				enterDataForDescInputType(data.get("RHPMetricValue"));
			}
			if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
				if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
					WaitForElementWithDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']");
					if (isElementPresentDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']")) {
						passed("Successfully Validated Mandatory Evidence   Error Message "
								+ Constants.standardErrorMessageMandatoryEvidence);
					} else {
						failed(driver, "Failed To Validate Mandatory Evidence Error message");
					}
				}
				if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
					WaitForElementWithDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']");
					if (isElementPresentDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
						passed("Successfully Validated Mandatory Explanation   Error Message "
								+ Constants.standardErrorMessageExplanationMandatory);
					} else {
						failed(driver, "Failed To Validate Mandatory Explanation  Error message");
					}
				}
			}
			if (Boolean.parseBoolean(data.get("IsStandardReuiredMessage"))) {
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
					clickOnDataForDescInputType();
					clickOnDataNotAvailablecheckBoxToUncheck();
				}
				WaitForElementWithDynamicXpath("//*[text()='" + Constants.RequiredErrorMessageWithoutDNA + "']");
				if (isElementPresentDynamicXpath("//*[text()='" + Constants.RequiredErrorMessageWithoutDNA + "']")) {
					passed("Successfully Validated Mandatory Explanation   Error Message "
							+ Constants.RequiredErrorMessageWithoutDNA);
				} else {
					failed(driver, "Failed To Validate Mandatory Explanation  Error message");
				}
			}
			if (Boolean.parseBoolean(data.get("IsEmptyMessage"))) {
				if (!isElementPresentDynamicXpath(
						"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']")) {
					passed("Successfully Validated No Error message");
				} else {
					failed(driver, "Failed To Validate Empty Error message");
				}
				if (!isElementPresentDynamicXpath(
						"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
					passed("Successfully Validated No Error message ");
				} else {
					failed(driver, "Failed To Validate Empty  Error message");
				}
			}
			if (Boolean.parseBoolean(data.get("IsDataRequiredMessage"))) {
				WaitForElementWithDynamicXpath("//*[text()='" + Constants.RequiredErrorMessageWithoutDNA + "']");
				if (isElementPresentDynamicXpath("//*[text()='" + Constants.RequiredErrorMessageWithoutDNA + "']")) {
					passed("Successfully Validated Required Error message");
				} else {
					failed(driver, "Failed To Validate Required Error message");
				}
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			clickOn(btnPublish, "Publish Button");
			if (isElementPresent(msgUnableToPublish)) {
				passed("Successfully Validated Publish blocker");
			} else {
				failed(driver, "Failed To Validate Publish Blocker");
			}
			validateTopicNameinPublishBlocker(data.get("TopicName"));
			expandTopicInCatalogPublishBlocker(data.get("TopicName"));
			if (Boolean.parseBoolean(data.get("IsRelatedMetric"))) {
				validateMetricNameinPublishBlocker(data.get("RelatedMetricName"));
			} else {
				validateMetricNameinPublishBlocker(data.get("MetricName"));
			}
			waitForElement(btnOk);
			clickOn(btnOk, "Ok button");
			contractTopicInCatalog(data.get("TopicName"));
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void clickOnDataForMultiSelectInputTypeForRelatedMetrics() {
		try {
			WebElement weMetricData = driver.findElement(
					By.xpath("//p[text()='Related Metrics']//following::p[text()='Data']//following::div[1]"));
			jsClick(weMetricData, "Metric Data for Related Metric");
			sleep(3000);
		} catch (Exception e) {
			System.out.println("Exception comming AT ClickonMulti------------------->");
			clickOnDataForMultiSelectInputTypeForRelatedMetrics();
		}
	}

	public void clearDataInMultiselectForRelatedMetrics() {
		try {
			List<WebElement> lstchkBoxInput = driver
					.findElements(By.xpath("//p[text()='Related Metrics']//following::span/input"));
			List<WebElement> lstchkBoxSelctValue = driver.findElements(
					By.xpath("//p[text()='Related Metrics']//following::span/input[1]//following-sibling::*"));
			for (int i = 0; i < lstchkBoxSelctValue.size() - 1; i++) {
				if (lstchkBoxSelctValue.get(i).getAttribute("data-testid").contains("CheckBoxIcon")) {
					clickOn(lstchkBoxInput.get(i), "Checkbox index-->" + i);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception comming At ClearData------------------->");
			clearDataInMultiselectForRelatedMetrics();
		}
	}

	public void enterDataForMultiInputTypeRelatedMetrics(String metricValue) {
		try {
			String[] arrMultiSelect = null;
			if (metricValue.contains(",")) {
				arrMultiSelect = metricValue.split(",");
				sleep(2000);
				for (int i = 0; i < arrMultiSelect.length; i++) {
					WaitForElementWithDynamicXpath(
							"//p[text()='Related Metrics']//following::p[text()='Data']//following::div//span[text()='"
									+ arrMultiSelect[i] + "']//preceding::input[1]");
					WebElement MetricValueData = driver.findElement(By.xpath(
							"//p[text()='Related Metrics']//following::p[text()='Data']//following::div//span[text()='"
									+ arrMultiSelect[i] + "']//preceding::input[1]"));
					WebElement chkBoxValue = driver.findElement(By.xpath(
							"//p[text()='Related Metrics']//following::p[text()='Data']//following::div//span[text()='"
									+ arrMultiSelect[i] + "']//preceding::input[1]//following-sibling::*"));
					if (chkBoxValue.getAttribute("data-testid").contains("CheckBoxOutlineBlankIcon")) {
						clickOn(MetricValueData, metricValue);
					}
				}
			} else {
				WaitForElementWithDynamicXpath(
						"//p[text()='Related Metrics']//following::p[text()='Data']//following::div//span[text()='"
								+ metricValue + "']//preceding::input[1]");
				WebElement MetricValueData = driver.findElement(By.xpath(
						"//p[text()='Related Metrics']//following::p[text()='Data']//following::div//span[text()='"
								+ metricValue + "']//preceding::input[1]"));
				WebElement chkBoxValue = driver.findElement(By.xpath(
						"//p[text()='Related Metrics']//following::p[text()='Data']//following::div//span[text()='"
								+ metricValue + "']//preceding::input[1]//following-sibling::*"));
				if (chkBoxValue.getAttribute("data-testid").contains("CheckBoxOutlineBlankIcon")) {
					clickOn(MetricValueData, metricValue);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			enterDataForMultiInputTypeRelatedMetrics(metricValue);
		}
	}

	public void ValidateMultiSelectInputTypeMetricPublishBlocker_DRFlow() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForMultiSelectInputType();
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			} else {
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
					clickOnDataNotAvailablecheckBoxTocheck();
					clickOnDataNotAvailablecheckBoxToUncheck();
				} else {
					clickOnDataNotAvailablecheckBoxToUncheck();
					clearDataInMultiselect();
				}
				enterDataForMultiInputType(data.get("RHPMetricValue"));
			}
			if (!Boolean.parseBoolean(data.get("IsRelatedMetric"))) {
				if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message "
									+ Constants.standardErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error message");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated Mandatory Explanation   Error Message "
									+ Constants.standardErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation  Error message");
						}
					}
				}
				if (Boolean.parseBoolean(data.get("IsCustomMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						// *[contains(text(),'" + Constants.customErrorMessageMandatoryEvidence + "')]
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message "
									+ Constants.customErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error message");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageExplanationMandatory + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated Mandatory Explanation   Error Message "
									+ Constants.customErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation  Error message");
						}
					}
				}
				if (Boolean.parseBoolean(data.get("IsEmptyMessage"))) {
					if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
						if (!isElementPresentDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated No Error message");
						} else {
							failed(driver, "Failed To Validate Empty Error message");
						}
						if (!isElementPresentDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated No Error message ");
						} else {
							failed(driver, "Failed To Validate Empty  Error message");
						}
					}
				}
			} else {
				if (data.get("RelatedMetricInputType").equals("Description")) {
					if (Boolean.parseBoolean(data.get("IsDataRequiredMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.RequiredErrorMessageWithoutDNA + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.RequiredErrorMessageWithoutDNA + "']")) {
							passed("Successfully Validated Required Error message in Related Metric");
						} else {
							failed(driver, "Failed To Validate Required Error message in Related Metric");
						}
					}
				} else {
					clickOnDataForMultiSelectInputTypeForRelatedMetrics();
					if (Boolean.parseBoolean(data.get("RelatedMetricDataNotAvailable"))) {
						clickOnDataNotAvailablecheckBoxTocheckForRelatedMetric();
					} else {
						clearDataInMultiselectForRelatedMetrics();
						enterDataForMultiInputTypeRelatedMetrics(data.get("RelatedMetricRHPValue"));
					}
					if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
						if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
							WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
									+ Constants.standardErrorMessageMandatoryEvidence + "']");
							if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
									+ Constants.standardErrorMessageMandatoryEvidence + "']")) {
								passed("Successfully Validated Mandatory Evidence   Error Message in Related Metric "
										+ Constants.standardErrorMessageMandatoryEvidence);
							} else {
								failed(driver, "Failed To Validate Mandatory Evidence Error Message in Related Metric");
							}
						}
						if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
							WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
									+ Constants.standardErrorMessageExplanationMandatory + "']");
							if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
									+ Constants.standardErrorMessageExplanationMandatory + "']")) {
								passed("Successfully Validated Mandatory Explanation   Error Message in Related Metric "
										+ Constants.standardErrorMessageExplanationMandatory);
							} else {
								failed(driver,
										"Failed To Validate Mandatory Explanation  Error Message in Related Metric");
							}
						}
					}
					if (Boolean.parseBoolean(data.get("IsCustomMessage"))) {
						if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
							WaitForElementWithDynamicXpath(
									"//p[text()='Related Metrics']//following::*[contains(text()='"
											+ Constants.customErrorMessageMandatoryEvidence + "')]");
							if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
									+ Constants.customErrorMessageMandatoryEvidence + "']")) {
								passed("Successfully Validated Mandatory Evidence   Error Message in Related Metric "
										+ Constants.customErrorMessageMandatoryEvidence);
							} else {
								failed(driver, "Failed To Validate Mandatory Evidence Error Message in Related Metric");
							}
						}
						if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
							WaitForElementWithDynamicXpath(
									"//p[text()='Related Metrics']//following::*[contains(text()='"
											+ Constants.customErrorMessageExplanationMandatory + "')]");
							if (isElementPresentDynamicXpath(
									"//p[text()='Related Metrics']//following::*[contains(text()='"
											+ Constants.customErrorMessageExplanationMandatory + "')]")) {
								passed("Successfully Validated Mandatory Explanation   Error Message in Related Metric "
										+ Constants.customErrorMessageExplanationMandatory);
							} else {
								failed(driver,
										"Failed To Validate Mandatory Explanation  Error Message in Related Metric");
							}
						}
					}
				}
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			validateMetricValidationsonAGgridofCatalog();
			clickOn(btnPublish, "Publish Button");
			if (isElementPresent(msgUnableToPublish)) {
				passed("Successfully Validated Publish blocker");
			} else {
				failed(driver, "Failed To Validate Publish Blocker");
			}
			validateTopicNameinPublishBlocker(data.get("TopicName"));
			expandTopicInCatalogPublishBlocker(data.get("TopicName"));
			if (Boolean.parseBoolean(data.get("IsRelatedMetric"))) {
				validateMetricNameinPublishBlocker(data.get("RelatedMetricName"));
			} else {
				validateMetricNameinPublishBlocker(data.get("MetricName"));
			}
			waitForElement(btnOk);
			clickOn(btnOk, "Ok button");
			contractTopicInCatalog(data.get("TopicName"));
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateIntInputTypeMetricPublishBlocker_DRFlow() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			sleep(3000);
			clickOnDataForIntegerInputType();
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			} else {
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
					clickOnDataNotAvailablecheckBoxToUncheck();
				}
				clearInputTypeTextBox();
				enterDataForIntegerInputType(data.get("RHPMetricValue"));
			}
			if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
				if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
					WaitForElementWithDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']");
					if (isElementPresentDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']")) {
						passed("Successfully Validated Mandatory Evidence   Error Message "
								+ Constants.standardErrorMessageMandatoryEvidence);
					} else {
						failed(driver, "Failed To Validate Mandatory Evidence Error message");
					}
				}
				if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
					WaitForElementWithDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']");
					if (isElementPresentDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
						passed("Successfully Validated Mandatory Explanation   Error Message "
								+ Constants.standardErrorMessageExplanationMandatory);
					} else {
						failed(driver, "Failed To Validate Mandatory Explanation  Error message");
					}
				}
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			validateMetricValidationsonAGgridofCatalog();
			clickOn(btnPublish, "Publish Button");
			waitForElement(msgUnableToPublish);
			if (isElementPresent(msgUnableToPublish)) {
				passed("Successfully Validated Publish blocker");
			} else {
				failed(driver, "Failed To Validate Publish Blocker");
			}
			validateTopicNameinPublishBlocker(data.get("TopicName"));
			expandTopicInCatalogPublishBlocker(data.get("TopicName"));
			if (Boolean.parseBoolean(data.get("IsRelatedMetric"))) {
				validateMetricNameinPublishBlocker(data.get("RelatedMetricName"));
			} else {
				validateMetricNameinPublishBlocker(data.get("MetricName"));
			}
			waitForElement(btnOk);
			clickOn(btnOk, "Ok button");
			contractTopicInCatalog(data.get("TopicName"));
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateTableInputTypeMetricPublishBlocker_DRFlow() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			sleep(3000);
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			}
			if (Boolean.parseBoolean(data.get("IsDataRequiredMessage"))) {
				clickOnDataLinkForTableInputType();
				clearDataInTable();
				Thread.sleep(3000);
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
					WaitForElementWithDynamicXpath("//*[text()='" + Constants.RequiredErrorMessageWithDNA + "']");
					if ((isElementPresentDynamicXpath("//*[text()='" + Constants.RequiredErrorMessageWithDNA + "']"))) {
						passed("Successfully Validated Data Required Error Message As"
								+ Constants.RequiredErrorMessageWithDNA);
					} else {
						failed(driver, "Failed To Validate Data Required  Error message");
					}
				} else {
					WaitForElementWithDynamicXpath("//*[text()='" + Constants.RequiredErrorMessageWithoutDNA + "']");
					if ((isElementPresentDynamicXpath(
							"//*[text()='" + Constants.RequiredErrorMessageWithoutDNA + "']"))) {
						passed("Successfully Validated Data Required Error Message As"
								+ Constants.RequiredErrorMessageWithoutDNA);
					} else {
						failed(driver, "Failed To Validate Data Required  Error message");
					}
				}
			}
			if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
				if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
					clearExplanationInExplanationTextBox();
					if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
						clickOnDataNotAvailablecheckBoxToCheck();
					}
					if (isElementPresentDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
						passed("Successfully Validated Explanation   Mandatory  Error  Message As"
								+ Constants.standardErrorMessageExplanationMandatory);
					} else {
						failed(driver, "Failed To Validate Explanation Mandatory Error message");
					}
				}
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			clickOn(btnPublish, "Publish Button");
			if (isElementPresent(msgUnableToPublish)) {
				passed("Successfully Validated Publish blocker");
			} else {
				failed(driver, "Failed To Validate Publish Blocker");
			}
			validateTopicNameinPublishBlocker(data.get("TopicName"));
			expandTopicInCatalogPublishBlocker(data.get("TopicName"));
			if (Boolean.parseBoolean(data.get("IsRelatedMetric"))) {
				validateMetricNameinPublishBlocker(data.get("RelatedMetricName"));
			} else {
				validateMetricNameinPublishBlocker(data.get("MetricName"));
			}
			waitForElement(btnOk);
			clickOn(btnOk, "Ok button");
			contractTopicInCatalog(data.get("TopicName"));
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateSingleSelectInputTypeMetricPublishBlocker_DRFlow() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForSingleSelectInputType();
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			} else {
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
//					clickOnDataNotAvailablecheckBoxTocheck();
//					clickOnDataNotAvailablecheckBoxToUncheck();
					waitForElement(btnReset);
					clickOn(btnReset, "btnReset");
				}
				enterDataSingleSelectType(data.get("RHPMetricValue"));
			}
			if (!Boolean.parseBoolean(data.get("IsRelatedMetric"))) {
				if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message "
									+ Constants.standardErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error message");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
							WaitForElementWithDynamicXpath(
									"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']");
							if (isElementPresentDynamicXpath(
									"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
								passed("Successfully Validated Mandatory Explanation   Error Message "
										+ Constants.standardErrorMessageExplanationMandatory);
							} else {
								failed(driver, "Failed To Validate Mandatory Explanation  Error message");
							}
						}
						if (!Boolean.parseBoolean(data.get("IsDNAPresent"))) {
							WaitForElementWithDynamicXpath(
									"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']");
							if (isElementPresentDynamicXpath(
									"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
								passed("Successfully Validated Mandatory Explanation   Error Message "
										+ Constants.standardErrorMessageExplanationMandatory);
							} else {
								failed(driver, "Failed To Validate Mandatory Explanation  Error message");
							}
						}
					}
				}
				if (Boolean.parseBoolean(data.get("IsCustomMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[(text()='" + Constants.customErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message "
									+ Constants.customErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error message");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageExplanationMandatory + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated Mandatory Explanation   Error Message "
									+ Constants.customErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation  Error message");
						}
					}
				}
				if (Boolean.parseBoolean(data.get("IsEmptyMessage"))) {
					if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
//						clickOnDataNotAvailablecheckBoxTocheck();
//						clickOnDataNotAvailablecheckBoxToUncheck();
						waitForElement(btnReset);
						clickOn(btnReset, "btnReset");
					}
					if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
						if (!isElementPresentDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated No Error message");
						} else {
							failed(driver, "Failed To Validate Empty Error message");
						}
						if (!isElementPresentDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated No Error message ");
						} else {
							failed(driver, "Failed To Validate Empty  Error message");
						}
					}
				}
				if (Boolean.parseBoolean(data.get("IsEmptyMessage"))) {
					if (Boolean.parseBoolean(data.get("IsCustomMessage"))) {
						if (!isElementPresentDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated No Error message");
						} else {
							failed(driver, "Failed To Validate Empty Error message");
						}
						if (!isElementPresentDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated No Error message ");
						} else {
							failed(driver, "Failed To Validate Empty  Error message");
						}
					}
				}
			} else {
				if (Boolean.parseBoolean(data.get("RelatedMetricDataNotAvailable"))) {
					clickOnDataNotAvailablecheckBoxTocheckForRelatedMetric();
				} else {
					enterDataForSingleSelectInputTypeRelatedMetrics(data.get("RelatedMetricRHPValue"));
				}
				if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message in Related Metric "
									+ Constants.standardErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error Message in Related Metric");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageExplanationMandatory + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated Mandatory Explanation   Error Message in Related Metric "
									+ Constants.standardErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation  Error Message in Related Metric");
						}
					}
				}
				if (Boolean.parseBoolean(data.get("IsCustomMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.customErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.customErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message in Related Metric "
									+ Constants.customErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error Message in Related Metric");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.customErrorMessageExplanationMandatory + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.customErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated Mandatory Explanation   Error Message in Related Metric "
									+ Constants.customErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation  Error Message in Related Metric");
						}
					}
				}
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			validateMetricValidationsonAGgridofCatalog();
			clickOn(btnPublish, "Publish Button");
			if (isElementPresent(msgUnableToPublish)) {
				passed("Successfully Validated Publish blocker");
			} else {
				failed(driver, "Failed To Validate Publish Blocker");
			}
			validateTopicNameinPublishBlocker(data.get("TopicName"));
			expandTopicInCatalogPublishBlocker(data.get("TopicName"));
			if (Boolean.parseBoolean(data.get("IsRelatedMetric"))) {
				validateMetricNameinPublishBlocker(data.get("RelatedMetricName"));
			} else {
				validateMetricNameinPublishBlocker(data.get("MetricName"));
			}
			waitForElement(btnOk);
			clickOn(btnOk, "Ok button");
			contractTopicInCatalog(data.get("TopicName"));
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void clickOnDataForRelatedMetricInputType() {
		try {
			WebElement weMetricData = driver
					.findElement(By.xpath("(//div[@id='panel1bh-content']//grid//grid/following-sibling::div/div)[1]"));
			waitForElement(weMetricData);
			jsClick(weMetricData, "Metric Data");
			sleep(3000);
		} catch (Exception e) {
			System.out.println("Exception comming here------------------->");
			clickOnDataForDescInputType();
		}
	}

	@FindBy(xpath = "//span[text()='Reset']")
	private WebElement btnReset;

	public void ValidateBooleanInputTypeMetricPublishBlocker_DRFlow() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForDescInputType();
			sleep(1000);
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			} else {
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
//					clickOnDataNotAvailablecheckBoxTocheck();
//					clickOnDataNotAvailablecheckBoxToUncheck();
					waitForElement(btnReset);
					clickOn(btnReset, "btnReset");
				}
				// clickOnDataForDescInputType();
				enterDataForBooleanInputType(data.get("RHPMetricValue"));
			}
			if (!Boolean.parseBoolean(data.get("IsRelatedMetric"))) {
				if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message "
									+ Constants.standardErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error message");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated Mandatory Explanation   Error Message "
									+ Constants.standardErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation  Error message");
						}
					}
				}
				if (Boolean.parseBoolean(data.get("IsCustomMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message "
									+ Constants.customErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error message");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageExplanationMandatory + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated Mandatory Explanation   Error Message "
									+ Constants.customErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation Error message");
						}
					}
				}
				if (Boolean.parseBoolean(data.get("IsEmptyMessage"))) {
					clickOn(btnReset, "btnReset");
					sleep(2000);
					if (!isElementPresentDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']")) {
						passed("Successfully Validated No Error message");
					} else {
						failed(driver, "Failed To Validate Empty Error message");
					}
					if (!isElementPresentDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
						passed("Successfully Validated No Error message ");
					} else {
						failed(driver, "Failed To Validate Empty  Error message");
					}
				}
			} else {
				clickOnDataForRelatedMetricInputType();
				if (Boolean.parseBoolean(data.get("RelatedMetricDataNotAvailable"))) {
					clickOnDataNotAvailablecheckBoxTocheckForRelatedMetric();
				} else {
					enterDataForBooleanInputTypeRelatedMetrics(data.get("RelatedMetricRHPValue"));
				}
				if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message in Related Metric "
									+ Constants.standardErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error Message in Related Metric");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageExplanationMandatory + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated Mandatory Explanation   Error Message in Related Metric "
									+ Constants.standardErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation  Error Message in Related Metric");
						}
					}
				}
				if (Boolean.parseBoolean(data.get("IsCustomMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.customErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.customErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message in Related Metric "
									+ Constants.customErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error Message in Related Metric");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.customErrorMessageExplanationMandatory + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.customErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated Mandatory Explanation   Error Message in Related Metric "
									+ Constants.customErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation  Error Message in Related Metric");
						}
					}
				}
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			validateMetricValidationsonAGgridofCatalog();
			clickOn(btnPublish, "Publish Button");
			if (isElementPresent(msgUnableToPublish)) {
				passed("Successfully Validated Publish blocker");
			} else {
				failed(driver, "Failed To Validate Publish Blocker");
			}
			validateTopicNameinPublishBlocker(data.get("TopicName"));
			expandTopicInCatalogPublishBlocker(data.get("TopicName"));
			if (Boolean.parseBoolean(data.get("IsRelatedMetric"))) {
				validateMetricNameinPublishBlocker(data.get("RelatedMetricName"));
			} else {
				validateMetricNameinPublishBlocker(data.get("MetricName"));
			}
			waitForElement(btnOk);
			clickOn(btnOk, "Ok button");
			contractTopicInCatalog(data.get("TopicName"));
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void enterDataForBooleanInputTypeRelatedMetrics(String metricValue) {
		try {
			Actions mouseHandle = new Actions(driver);
			if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
				WebElement MetricValueData1 = driver.findElement(By.xpath(
						"(//*[text()='Related Metrics']//following::span//input[@value='" + metricValue + "'])[1]"));
				mouseHandle.moveToElement(MetricValueData1).perform();
				clickOn(MetricValueData1, metricValue);
			} else {
				WebElement MetricValueData1 = driver.findElement(By.xpath(
						"(//*[text()='Related Metrics']//following::span//input[@value='" + metricValue + "'])[2]"));
				mouseHandle.moveToElement(MetricValueData1).perform();
				clickOn(MetricValueData1, metricValue);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			enterDataForBooleanInputType(data.get("CustomMetric1Value"));
		}
	}
	// --------------------------------------Sanity All Input Types of
	// data--------------------------------------------------------------------------------------------------------------
//btnRefresh

	@FindBy(xpath = "//table/tbody/tr[not(contains(@aria-label,'Evidence file deleted'))]/th[@value='originalFileName']")
	private WebElement txtUploadedFileName;
	@FindBy(xpath = "//p[text()='Audit']//parent::div")
	private WebElement btnAuditLogInMetricDetailsRHP;
	@FindBy(xpath = "(//p[text()='Audit']//parent::div//following::div[@role='rowgroup']/div/div[@col-id='value'and @role='gridcell'])[1]")
	private WebElement txtLatestAuditLogInMetricDetailsValue;

	public void enterExplanationText() {
		try {
			sleep(3000);
			clearExplanationInExplanationTextBox();
			sleep(3000);
			System.out.println(waitForElement(txtareaExplanation));
			if (txtareaExplanationText.getText().isEmpty()) {
			} else {
				txtareaExplanationText.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				txtareaExplanationText.sendKeys(Keys.BACK_SPACE);
			}
			sleep(3000);
			txtareaExplanationText.sendKeys(data.get("ExplanationText"));
			sleep(3000);
		} catch (Exception e) {
			System.out.println("Explanation text error");
			enterExplanationText();
		}
	}
	public void enterExplanationTextRandom() {
		try {
			waitForElement(txtExplanation);
			scrollToViewElement(txtExplanation);
			clearExplanationInExplanationTextBox();
			System.out.println(waitForElement(txtareaExplanation));
			if (txtareaExplanationText.getText().isEmpty()) {
			} else {
				txtareaExplanationText.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				txtareaExplanationText.sendKeys(Keys.BACK_SPACE);
			}
			sleep(500);
			String explanation = data.get("ExplanationText") + generateRandomString(3);
			txtareaExplanationText.sendKeys(explanation);
			sleep(500);
		} catch (Exception e) {
			System.out.println("Explanation text error");
			enterExplanationText();
		}
	}
	public void clearenterExplanationText() {
		try {
			sleep(500);
			clearExplanationInExplanationTextBox();
			sleep(500);
			System.out.println(waitForElement(txtareaExplanation));
			if (txtareaExplanationText.getText().isEmpty()) {
			} else {
				sleep(500);
				txtareaExplanationText.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				sleep(500);
				txtareaExplanationText.sendKeys(Keys.BACK_SPACE);
				sleep(500);
			}
		} catch (Exception e) {
			System.out.println("Explanation text error");
			enterExplanationText();
		}
	}

	public void validateUploadedEvidenceInMetricDetaisRHP() {
		waitForElement(btnEvidenceInMetricDetailsRHP);
		sleep(1000);
		// clickOnElement(btnEvidenceInMetricDetailsRHP, "Evidence button");
		waitForElement(txtUploadedFileName);
		if (txtUploadedFileName.getText().equals(data.get("EvidenceFileName"))) {
			passed("Successfully Validated Uploaded Evidence as" + txtUploadedFileName.getText());
		} else {
			failed(driver, "Failed To validate Uploaded Evidence");
		}
	}

	public void clickOnCloseMetricButton() {
		jsClick(btnClose, "Button Close");
	}

	public void validateAuditLogForEnteredValueInMetricDetailsRHP() {
		try {
			waitForElement(btnAuditLogInMetricDetailsRHP);
			clickOn(btnAuditLogInMetricDetailsRHP, "Audit button In RHP");
			waitForElement(btnRefresh);
			List<WebElement> noOfRecords = driver.findElements(By.xpath(
					"//p[text()='Audit']//parent::div//following::div[@role='rowgroup']/div/div[@col-id='value'and @role='gridcell']"));
			int intialRecorsCount = noOfRecords.size();
			int afterRefreshRecorsCount;
			List<WebElement> noOfRecordsAfterRefresh;
			do {
				clickOn(btnRefresh, "Refresh button");
				noOfRecordsAfterRefresh = driver.findElements(By.xpath(
						"//p[text()='Audit']//parent::div//following::div[@role='rowgroup']/div/div[@col-id='value'and @role='gridcell']"));
				afterRefreshRecorsCount = noOfRecordsAfterRefresh.size();
			} while (afterRefreshRecorsCount == intialRecorsCount);
			waitForElement(txtLatestAuditLogInMetricDetailsValue);
			if (txtLatestAuditLogInMetricDetailsValue.getText().contains(data.get("RHPMetricValue"))) {
				passed("Successfully validated  AuditLog Update with Entered value As"
						+ txtLatestAuditLogInMetricDetailsValue.getText());
			} else {
				failed(driver,
						"Failed to validate AuditLog Update with Entered value Expected is "
								+ data.get("RHPMetricValue") + "But Actual is"
								+ txtLatestAuditLogInMetricDetailsValue.getText());
			}
			waitForElement(btnAuditLogInMetricDetailsRHP);
			clickOnElement(btnAuditLogInMetricDetailsRHP, "AuditLog Expand Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateIntDataTypeMetricDataEntryAndEvidenceUpload() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForIntegerInputType();
			enterDataForIntegerInputType(data.get("RHPMetricValue"));
			enterExplanationText();
			validateAuditLogForEnteredValueInMetricDetailsRHP();
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
			validateUploadedEvidenceInMetricDetaisRHP();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateNumDataTypeMetricDataEntryAndEvidenceUpload() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForNumberInputType();
			enterDataForIntegerInputType(data.get("RHPMetricValue"));
			enterExplanationText();
			validateAuditLogForEnteredValueInMetricDetailsRHP();
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
			validateUploadedEvidenceInMetricDetaisRHP();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateTextDataTypeMetricDataEntryAndEvidenceUpload() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForNumberInputType();
			if (Boolean.parseBoolean(data.get("IsCondOrNonCondValidation"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
				clearExplanationInExplanationTextBox();
				sleep(3000);
				System.out.println(waitForElement(txtareaExplanation));
				if (txtareaExplanationText.getText().isEmpty()) {
				} else {
					txtareaExplanationText.sendKeys(Keys.chord(Keys.CONTROL, "a"));
					txtareaExplanationText.sendKeys(Keys.BACK_SPACE);
				}
				WaitForElementWithDynamicXpath(
						"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']");
				if (isElementPresentDynamicXpath(
						"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
					passed("Successfully Validated Mandatory Explanation   Error Message "
							+ Constants.standardErrorMessageExplanationMandatory);
				} else {
					failed(driver, "Failed To Validate Mandatory Explanation  Error message");
				}
				clickOnDataNotAvailablecheckBoxToUncheck();
			}
			enterDataForIntegerInputType(data.get("RHPMetricValue"));
			enterExplanationText();
			validateAuditLogForEnteredValueInMetricDetailsRHP();
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
			validateUploadedEvidenceInMetricDetaisRHP();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateDescDataTypeMetricDataEntryAndEvidenceUpload() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForDescInputType();
			enterDataForDescInputType(data.get("RHPMetricValue"));
			enterExplanationText();
			validateAuditLogForEnteredValueInMetricDetailsRHP();
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
			validateUploadedEvidenceInMetricDetaisRHP();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateSingleSelctDataTypeMetricDataEntryAndEvidenceUpload() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForSingleSelectInputType();
			enterDataSingleSelectType(data.get("RHPMetricValue"));
			enterExplanationText();
			validateAuditLogForEnteredValueInMetricDetailsRHP();
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
			validateUploadedEvidenceInMetricDetaisRHP();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateBooleanDataTypeMetricDataEntryAndEvidenceUpload() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForSingleSelectInputType();
			enterDataForBooleanInputType(data.get("RHPMetricValue"));
			enterExplanationText();
			validateAuditLogForEnteredValueInMetricDetailsRHP();
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
			validateUploadedEvidenceInMetricDetaisRHP();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//p[text()='Related Metrics']//following::*[@class='errorWithEvidence']")
	private WebElement txtErrorMessageMandatoryRelated;
	@FindBy(xpath = "//p[text()='Data']//following::*[@class='errorWithEvidence']")
	private WebElement txtErrorMessageMandatoryNonRelated;

	public void ValidateMultiSelctDataTypeMetricDataEntryAndEvidenceUpload() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForMultiSelectInputType();
			clearDataInMultiselect();
			enterDataForMultiInputType(data.get("RHPMetricValue"));
			if (!Boolean.parseBoolean(data.get("IsRelatedMetric"))) {
				if (Boolean.parseBoolean(data.get("IsCondOrNonCondValidation"))) {
					clearExplanationInExplanationTextBox();
					sleep(3000);
					System.out.println(waitForElement(txtareaExplanation));
					if (txtareaExplanationText.getText().isEmpty()) {
					} else {
						txtareaExplanationText.sendKeys(Keys.chord(Keys.CONTROL, "a"));
						txtareaExplanationText.sendKeys(Keys.BACK_SPACE);
					}
					waitForElement(txtErrorMessageMandatoryNonRelated);
					if (txtErrorMessageMandatoryNonRelated.getText().replaceAll("\\s", "")
							.equals(Constants.customErrorMessageExplanationMandatory.replaceAll("\\s", ""))) {
						passed("Successfully Validated Mandatory Explanation   Error Message "
								+ Constants.customErrorMessageExplanationMandatory);
					} else {
						failed(driver, "Failed To Validate Mandatory Explanation  Error message");
					}
				}
				enterExplanationText();
				validateAuditLogForEnteredValueInMetricDetailsRHP();
				deleteUploadedEvidenceInMetricDetailsRHP();
				uploadEvidenceInMetricDetailsRHP();
				validateUploadedEvidenceInMetricDetaisRHP();
			} else {
				clickOnDataForMultiSelectInputTypeForRelatedMetrics();
				clearDataInMultiselectForRelatedMetrics();
				enterDataForMultiInputTypeRelatedMetrics(data.get("RelatedMetricRHPValue"));
				if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message in Related Metric "
									+ Constants.standardErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error Message in Related Metric");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageExplanationMandatory + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated Mandatory Explanation   Error Message in Related Metric "
									+ Constants.standardErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation  Error Message in Related Metric");
						}
					}
				}
				if (Boolean.parseBoolean(data.get("IsCustomMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.customErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.customErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message in Related Metric "
									+ Constants.customErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error Message in Related Metric");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
//						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
//								+ Constants.customErrorMessageExplanationMandatory + "']");
//						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
//								+ Constants.customErrorMessageExplanationMandatory + "']")) {
//							passed("Successfully Validated Mandatory Explanation   Error Message in Related Metric "
//									+ Constants.customErrorMessageExplanationMandatory);
//						} else {
//							failed(driver, "Failed To Validate Mandatory Explanation  Error Message in Related Metric");
//						}
						waitForElement(txtErrorMessageMandatoryRelated);
						if (txtErrorMessageMandatoryRelated.getText().replaceAll("\\s", "")
								.equals(Constants.customErrorMessageExplanationMandatory.replaceAll("\\s", ""))) {
							passed("Successfully Validated Mandatory Explanation   Error Message "
									+ Constants.customErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation  Error message");
						}
					}
				}
				validateAuditLogForEnteredValueInMetricDetailsRHP();
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateBooleanTypeMetricDataEntryAndEvidenceUpload() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForDescInputType();
			enterDataForBooleanInputType(data.get("RHPMetricValue"));
			enterExplanationText();
			validateAuditLogForEnteredValueInMetricDetailsRHP();
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
			validateUploadedEvidenceInMetricDetaisRHP();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateCatalogMetricInputTypeEntries(String inputType) {
		switch (inputType) {
		case "Integer":
			ValidateIntDataTypeMetricDataEntryAndEvidenceUpload();
			break;
		case "Number":
			ValidateNumDataTypeMetricDataEntryAndEvidenceUpload();
			break;
		case "Text":
			ValidateTextDataTypeMetricDataEntryAndEvidenceUpload();
			break;
		case "SingleSelect":
			ValidateSingleSelctDataTypeMetricDataEntryAndEvidenceUpload();
			break;
		case "MultiSelect":
			ValidateMultiSelctDataTypeMetricDataEntryAndEvidenceUpload();
			break;
		case "Table":
			ValidateTableDataTypeMetricDataEntryAndEvidenceUpload();
			break;
		case "Description":
			ValidateDescDataTypeMetricDataEntryAndEvidenceUpload();
			break;
		case "Boolean":
			ValidateBooleanTypeMetricDataEntryAndEvidenceUpload();
			break;
		default:
		}
	}

	public void ValidateEnterPeriodDataForAggregations() {
		try {
			String strPeriods = data.get("SelectPeriod");
			String[] arrPeriods = strPeriods.split(",");
			for (int i = 0; i < arrPeriods.length; i++) {
				selectPeriod_ByExpandingParentPeriod(arrPeriods[i]);
				sleep(3000);
				clickOnMetricName(data.get("MetricName"));
				sleep(3000);
				if (data.get("DataType").equals("Integer")) {
					clickOnDataForIntegerInputType();
					enterDataForIntegerInputType(data.get(arrPeriods[i]));
				} else if (data.get("DataType").equals("Number")) {
					clickOnDataForNumberInputType();
					enterDataForIntegerInputType(data.get(arrPeriods[i]));
				} else {
					clickOnDataForDescInputType();
					enterDataForBooleanInputType(data.get(arrPeriods[i]));
				}
				clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//button[@aria-label='Refresh Metrics']/*")
	private WebElement btnRefreshAggregate;
	@FindBy(xpath = "//span[contains(text(),'Aggregated on')]")
	private WebElement txtAgregateon;

	public void ValidatePeriodAggregations() {
		try {
			sleep(3000);
			String strValPeriods = data.get("ValidatePeriod");
			String[] arrValPeriods = strValPeriods.split(",");
			for (int i = 0; i < arrValPeriods.length; i++) {
				selectPeriod_ToValidatePeriodAggregations(arrValPeriods[i]);
				clickOn(btnRefreshAggregate, "btnRefreshAggregate");
				sleep(5000);
				driver.navigate().refresh();
				expandTopicInCatalog(data.get("TopicName"));
				selectPeriod_ToValidatePeriodAggregations(arrValPeriods[i]);
				expandTopicInCatalog(data.get("TopicName"));
				sleep(5000);
				String xpathMetricValue = "(//span[text()='" + data.get("MetricName")
						+ "']//following::div[@col-id='metricValue']//span)[1]";
				String catalogMetricValue = driver.findElement(By.xpath(xpathMetricValue)).getText();
				if (catalogMetricValue.equals(data.get(arrValPeriods[i]))) {
					passed("Successfully Validated Aggregated Catalog Metric Value As" + catalogMetricValue);
				} else {
					failed(driver, "Failed To Validate Aggregated Catalog Metric Value Expected"
							+ data.get(arrValPeriods[i]) + "But Actual is " + catalogMetricValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void selectPeriod(String period) {
		try {
			if (!period.equals("NA")) {
				clickOn(btnFilter, "Filter button");
				verifyIfElementPresent(drpPeriod, "Period Filters", "Period Filters");
				clickOn(drpPeriod, "Period Dropdown");
				WebElement wePeriodName = driver
						.findElement(By.xpath("//ul[@role='listbox']/li[text()='" + period + "']"));
				clickOn(wePeriodName, period);
				waitForElement(btnClose);
				clickOn(btnClose, "Close Button");
			}
		} catch (Exception e) {
		}
	}
	public void selectPeriod_ToValidatePeriodAggregations(String period) {
		try {
			if (!period.equals("NA")) {
				clickOn(btnFilter, "Filter button");
				verifyIfElementPresent(drpPeriod, "Period Filters", "Period Filters");
				clickOn(drpPeriod, "Period Dropdown");
				WebElement weParentPeriodName_Year = driver
						.findElement(By.xpath("//ul/li//div[text()='FY23']//parent::div/div/*[@data-testid='ChevronRightIcon']"));
				clickOn(weParentPeriodName_Year, period);	
				WebElement wePeriodName = driver
						.findElement(By.xpath("//ul//div[text()='" + period + "']"));
				clickOn(wePeriodName, period);
				waitForElement(btnClose);
				clickOn(btnClose, "Close Button");
			}
		} catch (Exception e) {
		}
	}
	public void selectPeriod_ByExpandingParentPeriod(String period) {
		try {
			Actions movetoElement = new Actions(driver);
			List<String> Quarter1List =new ArrayList<>();
			List<String> Quarter2List =new ArrayList<>();
			List<String> Quarter3List =new ArrayList<>();
			List<String> Quarter4List =new ArrayList<>();
			Quarter1List.add("JAN23");
			Quarter1List.add("FEB23");			
			Quarter1List.add("MAR23");
			Quarter2List.add("APR23");
			Quarter2List.add("MAY23");			
			Quarter2List.add("JUN23");
			Quarter3List.add("JUL23");
			Quarter3List.add("AUG23");			
			Quarter3List.add("SEP23");	
			Quarter4List.add("OCT23");
			Quarter4List.add("NOV23");			
			Quarter4List.add("DEC23");
			String 	Quarter = null ; 
			if (!period.equals("NA")) {
				clickOn(btnFilter, "Filter button");
				verifyIfElementPresent(drpPeriod, "Period Filters", "Period Filters");
				clickOn(drpPeriod, "Period Dropdown");
				WebElement weParentPeriodName_Year = driver
						.findElement(By.xpath("//ul/li//div[text()='FY23']//parent::div/div/*[@data-testid='ChevronRightIcon']"));
				clickOn(weParentPeriodName_Year, period);
				if(Boolean.parseBoolean(data.get("Quarter Parent Metrics"))) {
						if(Quarter1List.contains(period)) {
							Quarter= "Q1-23";
						}else if (Quarter2List.contains(period)) {
							Quarter= "Q2-23";
						}else if (Quarter3List.contains(period)) {
							Quarter= "Q3-23";
						}else if (Quarter4List.contains(period)) {
							Quarter= "Q4-23";
						}
							WebElement wePeriodName_Quarter = driver
									.findElement(By.xpath("//ul/li//div[text()='"+Quarter+"']//parent::div/div/*[@data-testid='ChevronRightIcon']"));
							clickOn(wePeriodName_Quarter, period);	
				}
				WebElement wePeriodName = driver
						.findElement(By.xpath("//ul//div[text()='" + period + "']"));
				waitForElement(wePeriodName);
				movetoElement.moveToElement(wePeriodName).perform();
				clickOn(wePeriodName, period);
				waitForElement(btnClose);
				clickOn(btnClose, "Close Button");
			}
		} catch (Exception e) {
		}
	}

	public void publishDRInCatalogPage() {
		try {
			waitForElement(btnPublish);
			clickOn(btnPublish, "publish button");
			clickOn(btnConfirm, "Confirm button");
			if (isElementPresent(msgSuccessDataPublish)) {
				passed("Sucessfully validate DR publish Sucess Message");
			} else {
				failed(driver, "Failed To validate Publish success message");
			}
			clickOn(btnDone, "Done button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	// Goals

	@FindBy(xpath = "//button[text()='Internal']")
	private WebElement btnInternal;
	@FindBy(xpath = "//button[text()='External']")
	private WebElement btnExternalComments;
	@FindBy(id = "subject")
	private WebElement txtSubject;
	@FindBy(xpath = "//textarea[@placeholder='Write comment']")
	private WebElement txtWriteComent;
	@FindBy(xpath = "//button[text()='Submit']")
	private WebElement btnSubmit;
	@FindBy(xpath = "//button[text()='Reply']")
	private WebElement btnReply;

	public void validateCreateCatalogTopicAndMetrics(String CatalogName) {
		try {
			clickOn(btnCreateCatalog, "Create Catalog Button");
			clickOn(btnForwardIcon, "Forward Icon Button");
			clickOn(btnForwardIcon, "Forward Icon Button");
			if (CatalogName == "") {
				GlobalVariables.catalogName = Constants.catalogName + generateRandomString(3) + generateRandomNumber(3);
				enterText(txtCatalogName, "CatalogName", GlobalVariables.catalogName);
			} else {
				enterText(txtCatalogName, "CatalogName", CatalogName);
				GlobalVariables.catalogName = CatalogName;
			}
			enterText(txtCatalogDescription, "CatalogDescricption",
					Constants.catalogDesc + "_" + GlobalVariables.catalogName);
			clickOn(drpSelectCatalogType, "CatalogType");
			WebElement selectCatalogType = driver
					.findElement(By.xpath("//div/ul/li[text()='" + Constants.catalogType + "']"));
			clickOn(selectCatalogType, "Select Catalog Type List");
			clickOn(btnCreate, "Create button");
			waitForElement(btnClose);
			Thread.sleep(3000);
			String strCrtCatalogSuccMsg = driver
					.findElement(By.xpath("//*[@data-testid='CloseIcon']/following-sibling::div[1]")).getText();
			if (strCrtCatalogSuccMsg.trim().contains(
					"will be created! Would like to proceed adding Topics/Metrics to the catalog?\n\nPlease click Add Topics/Metrics to proceed")) {
				passed("Successfully created Catalog" + GlobalVariables.catalogName);
			} else {
				failed(driver, "Unable to create Catalog");
			}
			clickOn(btnClose, "Close button");
			ValidateCreateTopicAndMetricForCatalog();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void closeButton() {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnClose);
			// clickOn(btnClose, "Close button");
			jsClick(btnClose, "Close button");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean verifyCommentsScreenWhenThereAreNoCommentsCreated() {
		boolean blnCreated = false;
		try {
			selectPeriodFromCatalogDetailsPage(data.get("Period"));
			if (SelectCommentsFromMetric(data.get("MetricName"))) {
				clickOn(btnInternal, "Internal Comments");
				WebElement weCommentsCount = driver.findElement(By.xpath(
						"//button[text()='Internal']/parent::div/parent::div/parent::div/following::div[1]/div/span"));
				String strCommentsCount = weCommentsCount.getText();
				String[] arrCommentsCount = strCommentsCount.split("");
				strCommentsCount = arrCommentsCount[0];
				if (strCommentsCount.trim().equals("0")) {
					passed("Internal Comments is '0' count for Metric = > " + data.get("MetricName"));
				} else {
					failed(driver, "Internal Comments is not '0' count for Metric = > " + data.get("MetricName"));
				}
				verifyElementAndHighlightIfExists(txtSubject, "Subject", "Internal Comment");
				verifyElementAndHighlightIfExists(txtWriteComent, "Write Comment", "Internal Comment");
				String sSubmitBtnStatus = btnSubmit.getAttribute("disabled");
				String sSubjectValue = txtSubject.getAttribute("value");
				String sWriteComment = txtWriteComent.getText();
				if (sSubjectValue.trim().equals("")) {
					passed("Subject text field Value is blank as expected");
				} else {
					failed(driver, "by default Subject text field having value and value is => " + sSubjectValue);
				}
				if (sWriteComment.trim().equals("")) {
					passed("Write Comment textarea field Value is blank as expected");
				} else {
					failed(driver,
							"by default Write Comment textarea field having value and value is => " + sWriteComment);
				}
				if (sSubmitBtnStatus.trim().equals("true")) {
					passed("Submit button is dispalyed");
				} else {
					failed(driver, "Submit button is enabled");
				}
				clickOn(btnExternalComments, "External Comments");
				weCommentsCount = driver.findElement(By.xpath(
						"//button[text()='Internal']/parent::div/parent::div/parent::div/following::div[1]/div/span"));
				strCommentsCount = weCommentsCount.getText();
				arrCommentsCount = strCommentsCount.split("");
				strCommentsCount = arrCommentsCount[0];
				if (strCommentsCount.trim().equals("0")) {
					passed("External Comments is '0' count for Metric = > " + data.get("MetricName"));
				} else {
					failed(driver, "External Comments is not '0' count for Metric = > " + data.get("MetricName"));
				}
				WebElement weMessage1 = driver
						.findElement(By.xpath("//div[text()='You can create/view the comments by:']"));
				WebElement weMessage2 = driver.findElement(
						By.xpath("//div[text()='1. Selecting a Data Destination from Data Destination filter (or)']"));
				WebElement weMessage3 = driver.findElement(
						By.xpath("//div[text()='2. Selecting an Organization from View Organization Data filter.']"));
				if (verifyElementAndHighlightIfExists(weMessage1, "Message: 'You can create/view the comments by:'",
						"External Comment")
						&& verifyElementAndHighlightIfExists(weMessage2,
								"Message: '1. Selecting a Data Destination from Data Destination filter (or)'",
								"External Comment")
						&& verifyElementAndHighlightIfExists(weMessage3,
								"Message: '2. Selecting an Organization from View Organization Data filter.'",
								"External Comment")) {
					passed("Default External Comments are displayed when there is no Data Destination is created for the Catalog and Metric and Message are: "
							+ weMessage1.getText() + ", " + weMessage2.getText() + ", " + weMessage3.getText());
				} else {
					failed(driver,
							"Default External Comments are not displayed when there is no Data Destination is created for the Catalog and Metric and present Message are: "
									+ weMessage1.getText() + ", " + weMessage2.getText() + ", " + weMessage3.getText());
				}
				clickOn(btnSectionClose, "Close Comments");
			} else {
				failed(driver, "Unable to select Comments for Metric => " + data.get("MetricName"));
			}
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
		return blnCreated;
	}

	public boolean SelectCommentsFromMetric(String metricName) {
		boolean blnSucc = false;
		try {
			WebElement expandTopic = driver.findElement(By.xpath("//span[text()='" + data.get("TopicName")
					+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
			clickOn(expandTopic, "Expand Topic ==> " + data.get("TopicName"));
			Thread.sleep(2000);
			Actions act = new Actions(driver);
			WebElement weMetric = null;
			int i = 0;
			while (i < 20) {
				try {
					weMetric = driver.findElement(By.xpath("//span[text()='" + metricName + "']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weMetric);
					break;
				} catch (NoSuchElementException e) {
					act.sendKeys(Keys.PAGE_DOWN).build().perform();
					i++;
				}
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weMetric);
			verifyIfElementPresent(weMetric, "Topic ==> " + data.get("MetricName"), "Topics and Metric Details");
			WebElement weComments = driver.findElement(By.xpath("//span[text()='" + metricName
					+ "']/ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='Comments']//button"));
			Actions builder = new Actions(driver);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weComments);
			WebElement myElement = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(weComments));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
			builder.moveToElement(weComments).click().build().perform();
			// span[@aria-label="Test_Metric_1010"]/following-sibling::div/span[text()='Goals']
			WebElement commentsPopupUp = driver.findElement(By.xpath("//div[text()='Comments']"));
			blnSucc = verifyIfElementPresent(commentsPopupUp,
					"comments RightHandSide panel displayed for ==> " + metricName, "comments Details");
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
		return blnSucc;
	}

	String commentName = "";

	public boolean verifyCreateInternalComments(String userName) {
		boolean blnCreated = false;
		String strBeforeCreateCommentsCount = null, strAfterCreateCommentsCount;
		int iBeforeCreateCommentsCount = 0;
		try {
			selectPeriodFromCatalogDetailsPage(data.get("Period"));
			if (SelectCommentsFromMetric(GlobalVariables.catalogDetails.get("MetricName"))) {
				WebElement weCommentsCount = driver.findElement(By.xpath(
						"//button[text()='Internal']/parent::div/parent::div/parent::div/following::div[1]/div/span"));
				String strCommentsCount = weCommentsCount.getText();
				String[] arrCommentsCount = strCommentsCount.split("");
				strBeforeCreateCommentsCount = arrCommentsCount[0];
				enterDetailsInCommentsPage();
				clickOn(btnSubmit, "Submit comment");
				Thread.sleep(10000);
				weCommentsCount = driver.findElement(By.xpath(
						"//button[text()='Internal']/parent::div/parent::div/parent::div/following::div[1]/div/span"));
				strCommentsCount = weCommentsCount.getText();
				arrCommentsCount = strCommentsCount.split("");
				strAfterCreateCommentsCount = arrCommentsCount[0];
				iBeforeCreateCommentsCount = Integer.parseInt(strBeforeCreateCommentsCount) + 1;
				if (Integer.toString(iBeforeCreateCommentsCount).trim().equals(strAfterCreateCommentsCount.trim())) {
					passed("Created Comments count is matching as expeted and present comment count is => "
							+ strAfterCreateCommentsCount);
				} else {
					failed(driver, "Created Comments count is not matching as expected and present comment count is => "
							+ strAfterCreateCommentsCount);
				}
				String xpath = "//span[text()='" + userName
						+ "']/parent::div/parent::div/following-sibling::div[text()='"
						+ GlobalVariables.commentsDetails.get("Subject") + "']//following-sibling::div[text()='"
						+ GlobalVariables.commentsDetails.get("Write Comment") + "']";
				WebElement weCreatedComment = driver.findElement(By.xpath(xpath));
				blnCreated = verifyIfElementPresent(weCreatedComment,
						"Created comment " + GlobalVariables.commentsDetails.get("Subject"), "Comment");
				clickOn(btnSectionClose, "Close Comments");
			} else {
				failed(driver, "Unable to select Comments for Metric => " + data.get("MetricName"));
			}
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
		return blnCreated;
	}

	// HashMap<String, String> commentsDetails = new HashMap<>();
	public String enterDetailsInCommentsPage() {
		String subject = null, comments = null;
		try {
			LocalDate localDate;
			String formattedDate = null;
			subject = data.get("Subject");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MMHH:mm:ss");
			Date date = new Date();
			String sNow = formatter.format(date).replaceAll("[-:/.//s]", "");
			if (subject == "") {
				subject = "TestComment_" + sNow;
			}
			enterText(txtSubject, "Subject Name", subject);
			GlobalVariables.commentsDetails.put("Subject", subject);
			GlobalVariables.commentName = subject;
			comments = data.get("WriteComment");
			if (comments == "") {
				comments = "This is a sample comments for testing. Comment is " + subject;
			}
			enterText(txtWriteComent, "Write Comment", comments);
			GlobalVariables.commentsDetails.put("Write Comment", comments);
			Thread.sleep(2000);
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
		return subject;
	}

	public String enterReplyCommentsInCommentsPage(String CommentSentBy) {
		String replyComment = null, comments = null;
		try {
			LocalDate localDate;
			String formattedDate = null;
			replyComment = data.get("ReplyComment");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MMHH:mm:ss");
			Date date = new Date();
			String sNow = formatter.format(date).replaceAll("[-:/.//s]", "");
			if (replyComment == "") {
				replyComment = "This is sample reply comment for Metric comment and comment dates on " + sNow;
			}
			WebElement weReplyComment = driver
					.findElement(By.xpath("//div[text()='" + GlobalVariables.commentsDetails.get("Subject")
							+ "']/following-sibling::div//textarea[@placeholder='Reply to this comment']"));
			enterText(weReplyComment, "Reply Comment", replyComment);
			GlobalVariables.commentsDetails.put("ReplyComment", replyComment);
			GlobalVariables.alLReplyComments.add(replyComment);
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
		return replyComment;
	}

	public boolean validateCreateMetricforExisitingCatalogAndTopic(String CatalogName) {
		boolean blnFnd = false, blnSucc = false;
		try {
			// if(searchAndSelectCatalogCard(CatalogName)){
			GlobalVariables.catalogDetails.put("CatalogName", CatalogName);
			List<String> topic = new ArrayList<>();
			clickOn(btnExpand, "Expand");
			clickOn(btnConfigureCatalog, "btnConfigureCatalog");
			System.out.println(btnConfigureCatalog);
			jsClick(weCustomTopic, "Custom Topic");
			String metricName = data.get("MetricName");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MMHH:mm:ss");
			Date date = new Date();
			String sNow = formatter.format(date).replaceAll("[-:/.//s]", "");
			if (metricName == "") {
				metricName = "TestMetric_" + sNow;
			}
			blnSucc = addMetric(data.get("TopicName"), metricName);
			GlobalVariables.catalogDetails.put("TopicName", data.get("TopicName"));
			GlobalVariables.catalogDetails.put("MetricName", metricName);
			closeButton();
			/*
			 * }else{
			 * failed(driver,data.get("CatalogName")+" provided catalog does't exists"); }
			 */
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
		return blnSucc;
	}

	public boolean addMetric(String topicName, String metricName) {
		WebElement lstInputType, lstTopics;
		boolean blnSucc = false;
		try {
			// Metric N number
			jsClick(btnAddMetric, "Add Metric");
			verifyIfElementPresent(lblMetricDetails, "Metric Details", "Create Metic");
			clickOn(drpInputType, "Input Type dropdown");
			lstInputType = driver.findElement(By.xpath("//div[@id='menu-type']//ul/li[contains(text(),'Number')]"));
			clickOn(lstInputType, "INput Type");
			clickOn(drpTopic, "Topic dropdown");
			lstTopics = driver.findElement(By.xpath("//div[@id='menu-topicId']//li[text()='" + topicName + "']"));
			clickOn(lstTopics, "Topic Values");
			enterText(txtMetricName, "Mtric Name", metricName);
			clickOn(txtInstructions, "Instructions");
			enterText(txtInstructions, "Instructions", "Enter value for " + metricName);
			clickOn(btnSaveMetric, "Save Metric");
//			blnSucc = verifyIfElementPresent(weMsgMetricSavedSuccessfully, "Metric Saved Successfully",
//					"Create Metric");
			if (btnOk.isDisplayed()) {
				blnSucc = true;
			}
			clickOn(btnOk, "Ok");
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
		return blnSucc;
	}

	public boolean verifyReplyToComments(String commentSentBy, String commentRepliedBy) {
		boolean blnCreated = false;
		String strBeforeCreateCommentsCount = null, strAfterCreateCommentsCount, repliedComments;
		int iBeforeCreateCommentsCount = 0;
		try {
			selectPeriodFromCatalogDetailsPage(data.get("Period"));
			if (SelectCommentsFromMetric(GlobalVariables.catalogDetails.get("MetricName"))) {
				Thread.sleep(4000);
				WebElement weCommentsCount = driver.findElement(By.xpath(
						"//button[text()='Internal']/parent::div/parent::div/parent::div/following::div[1]/div/span"));
				String strCommentsCount = weCommentsCount.getText();
				String[] arrCommentsCount = strCommentsCount.split("");
				strBeforeCreateCommentsCount = arrCommentsCount[0];
				iBeforeCreateCommentsCount = Integer.parseInt(strBeforeCreateCommentsCount) + 1;
				if (strBeforeCreateCommentsCount.trim().equals("1")) {
					passed("Created Comments count is matching as expected and present comment count is => "
							+ strBeforeCreateCommentsCount);
				} else {
					failed(driver, "Created Comments count is not matching as expected and present comment count is => "
							+ strBeforeCreateCommentsCount);
				}
				WebElement weCommentSentBy = driver
						.findElement(By.xpath("//div[text()='" + GlobalVariables.commentsDetails.get("Subject")
								+ "']/preceding-sibling::div//span[text()='" + commentSentBy + "']"));
				verifyElementAndHighlightIfExists(weCommentSentBy, "Comment Sent by", "Comment Page");
				repliedComments = enterReplyCommentsInCommentsPage(commentSentBy);
				clickOn(btnReply, "Reply comment");
				Thread.sleep(8000);
				/*
				 * "//span[@class='hideRelpy']/parent::div/parent::div/following-sibling::div/div//span[@aria-label='"
				 * + commentRepliedBy +
				 * "']/parent::div/parent::div/following-sibling::div[text()='" +
				 * repliedComments + "']"
				 */
				String xpathReplied = "//span[text()='" + data.get("EsgAdminUserName")
						+ "']//parent::div/parent::div//following-sibling::div//div[contains(text(),'" + repliedComments
						+ "')]";
				WebElement weRepliedComment = driver.findElement(By.xpath(xpathReplied));
				blnCreated = verifyElementAndHighlightIfExists(weRepliedComment, "Replied comment", "Comment Page");
				clickOn(btnSectionClose, "Close Comments");
				System.out.println();
			} else {
				failed(driver, "Unable to select Comments for Metric => " + data.get("MetricName"));
			}
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
		return blnCreated;
	}

	@FindBy(xpath = "//*[@data-testid='MoreVertIcon']")
	private WebElement btnKebabMenu;
	@FindBy(xpath = "//div[text()='Edit Comment']")
	private WebElement btnEditComment;

	public boolean verifyEditCreatedInternalComments(String userName) {
		boolean blnCreated = false;
		String strBeforeCreateCommentsCount = null, strAfterCreateCommentsCount;
		int iBeforeCreateCommentsCount = 0;
		try {
			System.out.println();
			driver.navigate().refresh();
			selectPeriodFromCatalogDetailsPage(data.get("Period"));
			if (SelectCommentsFromMetric(GlobalVariables.catalogDetails.get("MetricName"))) {
				WebElement weCommentsCount = driver.findElement(By.xpath(
						"//button[text()='Internal']/parent::div/parent::div/parent::div/following::div[1]/div/span"));
				String strCommentsCount = weCommentsCount.getText();
				String[] arrCommentsCount = strCommentsCount.split(" ");
				strBeforeCreateCommentsCount = arrCommentsCount[0];
				clickOn(btnKebabMenu, "Kebab Menu comment");
				clickOn(btnEditComment, "Edit Comment");
				String subject = txtSubject.getAttribute("value") + "_Edited";
				txtSubject.clear();
				txtSubject.sendKeys(Keys.CONTROL + "a");
				txtSubject.sendKeys(Keys.DELETE);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].value='';", txtSubject);
				enterText(txtSubject, "Subject Name", subject);
				GlobalVariables.commentsDetails.put("Subject", subject);
				GlobalVariables.commentName = subject;
				String comments = txtWriteComent.getText() + "_Edited";
				txtWriteComent.clear();
				txtWriteComent.sendKeys(Keys.CONTROL + "a");
				txtWriteComent.sendKeys(Keys.DELETE);
				enterText(txtWriteComent, "Write Comment", comments);
				GlobalVariables.commentsDetails.put("Write Comment", comments);
				clickOn(btnSubmit, "Submit comment");
				Thread.sleep(4000);
				weCommentsCount = driver.findElement(By.xpath(
						"//button[text()='Internal']/parent::div/parent::div/parent::div/following::div[1]/div/span"));
				strCommentsCount = weCommentsCount.getText();
				arrCommentsCount = strCommentsCount.split(" ");
				strAfterCreateCommentsCount = arrCommentsCount[0];
				iBeforeCreateCommentsCount = Integer.parseInt(strBeforeCreateCommentsCount);
				if (arrCommentsCount[0].trim().equals(strAfterCreateCommentsCount.trim())) {
					passed("Created Comments count is matching as expeted and present comment count is => "
							+ strAfterCreateCommentsCount);
				} else {
					failed(driver, "Created Comments count is not matching as expected and present comment count is => "
							+ strAfterCreateCommentsCount);
				}
				String xpath = "//span[text()='" + userName
						+ "']/parent::div/parent::div/following-sibling::div[text()='"
						+ GlobalVariables.commentsDetails.get("Subject") + "']//following-sibling::div[text()='"
						+ GlobalVariables.commentsDetails.get("Write Comment") + "']";
				WebElement weCreatedComment = driver.findElement(By.xpath(xpath));
				blnCreated = verifyIfElementPresent(weCreatedComment,
						"Created comment " + GlobalVariables.commentsDetails.get("Subject"), "Comment");
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnSectionClose);
				((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'",
						btnSectionClose);
				clickOn(btnSectionClose, "Close Comments");
			} else {
				failed(driver, "Unable to select Comments for Metric => " + data.get("MetricName"));
			}
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
		return blnCreated;
	}

	@FindBy(xpath = "//*[@data-testid='SearchIcon']")
	private WebElement btnSrchComment;
	@FindBy(xpath = "//input[@placeholder='Search' and @type='search']")
	private WebElement txtSearchComment;

	public boolean verifySearchCommentFromCommentsPage(String searchComment) {
		boolean blnSrch = false;
		try {
			clickOn(btnSrchComment, "Search Icon");
			clickOn(txtSearchComment, "Search Icon");
			enterText(txtSearchComment, "Search Comment", searchComment);
			Thread.sleep(5000);
			WebElement weSrchedComment = driver.findElement(By.xpath("//div[text()='" + searchComment + "']"));
			if (verifyElementAndHighlightIfExists(weSrchedComment, "Searched COmments", "Comments Page")) {
				passed("Searched comments ==> " + searchComment + " is displayed as expected");
				blnSrch = true;
			} else {
				failed(driver, "Searched comments ==> " + searchComment + " is not displayed as expected");
			}
			clickOn(btnSectionClose, "Close Comments");
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
		return blnSrch;
	}

	public boolean verifySearchCommentForInvalidCommentsFromCommentsPage(String searchComment) {
		boolean blnSrch = false;
		try {
			clickOn(btnSrchComment, "Search Icon");
			clickOn(txtSearchComment, "Search Icon");
			enterText(txtSearchComment, "Search Comment", searchComment);
			Thread.sleep(5000);
			try {
				WebElement weSrchedComment = driver.findElement(By.xpath("//div[text()='" + searchComment + "']"));
				if (isElementPresent(weSrchedComment)) {
					failed(driver, "Searched comments ==> " + searchComment + " is displayed as expected");
					blnSrch = false;
				} else {
					passed("Searched comments ==> " + searchComment + " is not displayed as expected");
				}
			} catch (NoSuchElementException e) {
				passed("Searched comments ==> " + searchComment + " is not displayed as expected");
			}
			WebElement weCommentCount = driver.findElement(By.xpath(
					"//button[text()='Internal']/parent::div/parent::div/parent::div/following-sibling::div//span[contains(text(),'Comment')]"));
			if (isElementPresent(weCommentCount) && weCommentCount.getText().trim().contains("0")) {
				passed("Displayed Comments Count as 0 for invalid search comments");
				takeScreenshot(driver);
				blnSrch = true;
			} else {
				failed(driver, "Displayed Comments Count as " + weCommentCount.getText().trim()
						+ " for invalid search comments");
			}
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
		return blnSrch;
	}

	public void verifyRepliedCommentsChronologicalOrder() {
		try {
			List<WebElement> alLReplyElements = new ArrayList<>();
			List<String> obtainedReplyComments = new ArrayList<>();
			boolean blnSucc = true;
			driver.navigate().refresh();
			selectPeriodFromCatalogDetailsPage(data.get("Period"));
			if (SelectCommentsFromMetric(GlobalVariables.catalogDetails.get("MetricName"))) {
				alLReplyElements = driver.findElements(By
						.xpath("//span[@class='hideRelpy']/parent::div/parent::div/following-sibling::div/div/div[2]"));
				for (int i = 1; i <= alLReplyElements.size(); i++) {
					WebElement we = driver.findElement(
							By.xpath("//span[@class='hideRelpy']/parent::div/parent::div/following-sibling::div/div["
									+ i + "]/div[2]"));
					obtainedReplyComments.add(we.getText());
				}
				if (GlobalVariables.alLReplyComments.size() == obtainedReplyComments.size()) {
					for (int j = 0; j < obtainedReplyComments.size(); j++) {
						if (!GlobalVariables.alLReplyComments.get(j).trim().equals(obtainedReplyComments.get(j))) {
							failed(driver, "Replied comments are not in chronological order ");
							blnSucc = false;
							break;
						}
					}
				} else {
					failed(driver, "Unable to select Comments for Metric => " + data.get("MetricName"));
				}
				if (blnSucc) {
					passed("Replied comments are in chronological order ");
					takeScreenshot(driver);
				}
				clickOn(btnSectionClose, "Close Comments");
			} else {
				failed(driver, "Unable to select Comments for Metric => " + data.get("MetricName"));
			}
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
	}

	/***************************************/
	public void verifyExternalCommentsDefaultMessages() {
		String strCommentsCount;
		String[] arrCommentsCount;
		try {
			clickOn(btnExternalComments, "External Comments");
			WebElement weMessage1 = driver
					.findElement(By.xpath("//div[text()='You can create/view the comments by:']"));
			WebElement weMessage2 = driver.findElement(
					By.xpath("//div[text()='1. Selecting a Data Destination from Data Destination filter (or)']"));
			WebElement weMessage3 = driver.findElement(
					By.xpath("//div[text()='2. Selecting an Organization from View Organization Data filter.']"));
			if (verifyElementAndHighlightIfExists(weMessage1, "Message: 'You can create/view the comments by:'",
					"External Comment")
					&& verifyElementAndHighlightIfExists(weMessage2,
							"Message: '1. Selecting a Data Destination from Data Destination filter (or)'",
							"External Comment")
					&& verifyElementAndHighlightIfExists(weMessage3,
							"Message: '2. Selecting an Organization from View Organization Data filter.'",
							"External Comment")) {
				passed("Default External Comments are displayed when there is no Data Destination is created for the Catalog and Metric and Message are: "
						+ weMessage1.getText() + ", " + weMessage2.getText() + ", " + weMessage3.getText());
			} else {
				failed(driver,
						"Default External Comments are not displayed when there is no Data Destination is created for the Catalog and Metric and present Message are: "
								+ weMessage1.getText() + ", " + weMessage2.getText() + ", " + weMessage3.getText());
			}
			clickOn(btnSectionClose, "Close Comments");
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
	}

	public boolean verifyCreateExternalComments(String userName) {
		System.out.println("blnCreated");
		boolean blnCreated = false;
		String strBeforeCreateCommentsCount = null, strAfterCreateCommentsCount;
		int iBeforeCreateCommentsCount = 0;
		try {
			WebElement weDDName = driver
					.findElement(By.xpath("//span[text()='Data Destination : ']/following-sibling::div/span"));
			String sDataDestinatoinName = weDDName.getAttribute("aria-label");
			if (sDataDestinatoinName.trim().equals(GlobalVariables.dataRequestName)) {
				passed("Data Request generated  Data Destination name is selected by default and Data Destination name is => "
						+ sDataDestinatoinName.trim());
			} else {
				failed(driver,
						"Data Request generated  Data Destination name is not selected by default and Actual Data Destination name is  => '"
								+ sDataDestinatoinName.trim() + "' , Expected Data Destination name is => '"
								+ GlobalVariables.dataRequestName + "'");
			}
			WebElement wePeriodVal = driver
					.findElement(By.xpath("//span[text()='Period: ']/following-sibling::span/span"));
			String sPeriodVal = wePeriodVal.getText();
			if (sPeriodVal.trim().equals(data.get("PeriodDesc"))) {
				passed("Period is selected by default and Period value is => " + sPeriodVal.trim());
			} else {
				failed(driver, "Period is selected by default and Actual Period Value is  => '" + sPeriodVal.trim()
						+ "' , Expected Period Value is => '" + data.get("PeriodDesc") + "'");
			}
			Thread.sleep(3000);
			if (SelectCommentsFromMetric(GlobalVariables.catalogDetails.get("MetricName"))) {
				clickOn(btnExternalComments, "External Comments");
				Thread.sleep(3000);
				String btnExternalClassVal = btnExternalComments.getAttribute("class");
				if (!btnExternalClassVal.contains("Mui-selected")) {
					failed(driver, "External Comments is not selected");
				}
				WebElement weCommentsCount = driver.findElement(By.xpath(
						"//button[text()='Internal']/parent::div/parent::div/parent::div/following::div[1]/div/span"));
				String strCommentsCount = weCommentsCount.getText();
				String[] arrCommentsCount = strCommentsCount.split("");
				strBeforeCreateCommentsCount = arrCommentsCount[0];
				enterDetailsInCommentsPage();
				clickOn(btnSubmit, "Submit comment");
				Thread.sleep(4000);
				weCommentsCount = driver.findElement(By.xpath(
						"//button[text()='Internal']/parent::div/parent::div/parent::div/following::div[1]/div/span"));
				strCommentsCount = weCommentsCount.getText();
				arrCommentsCount = strCommentsCount.split("");
				strAfterCreateCommentsCount = arrCommentsCount[0];
				iBeforeCreateCommentsCount = Integer.parseInt(strBeforeCreateCommentsCount) + 1;
				if (Integer.toString(iBeforeCreateCommentsCount).trim().equals(strAfterCreateCommentsCount.trim())) {
					passed("Created Comments count is matching as expeted and present comment count is => "
							+ strAfterCreateCommentsCount);
				} else {
					failed(driver, "Created Comments count is not matching as expected and present comment count is => "
							+ strAfterCreateCommentsCount);
				}
				String xpath = "//span[text()='" + userName
						+ "']/parent::div/parent::div/following-sibling::div[text()='"
						+ GlobalVariables.commentsDetails.get("Subject") + "']//following-sibling::div[text()='"
						+ GlobalVariables.commentsDetails.get("Write Comment") + "']";
				WebElement weCreatedComment = driver.findElement(By.xpath(xpath));
				blnCreated = verifyIfElementPresent(weCreatedComment,
						"Created comment " + GlobalVariables.commentsDetails.get("Subject"), "Comment");
				// jsClick(btnSectionClose, "Close Comments");
				btnSectionClose.click();
			} else {
				failed(driver, "Unable to select Comments for Metric => " + data.get("MetricName"));
			}
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
		return blnCreated;
	}

	public boolean selectOrganizationInCatalogDetailsPage(String orgName) {
		boolean blnSucc = false;
		try {
			clickOnViewOrganizationData();
			WebElement weOrgName = driver.findElement(By.xpath("//div/p[text()='" + orgName + "']"));
			clickOn(weOrgName, "Org Name => " + orgName);
			WebElement selectedOrgName = driver
					.findElement(By.xpath("//span[text()='Organization : ']/following-sibling::div/span"));
			if (selectedOrgName.getAttribute("aria-label").trim().equals(orgName)) {
				passed("Selected orgName as => " + orgName);
				blnSucc = true;
			} else {
				failed(driver, "Unable to select orgname as ==> " + orgName);
			}
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
		return blnSucc;
	}

	@FindBy(xpath = "//button[text()='Clear']")
	private WebElement btnClear;

	public boolean selectDataRequestFromCatalogDetailsFilter(String dataRequestName) {
		boolean blnSucc = false;
		try {
			Thread.sleep(4000);
			clickOn(btnFilter, "Filter");
			verifyIfElementPresent(lblFilters, "Filters", "Filters");
			Thread.sleep(1000);
			btnClear.click();
			clickOn(btnClose, "Filters Close");
			WebElement weOrgName = driver
					.findElement(By.xpath("//div/p[text()='" + data.get("1stLevelApprovalOrgName") + "']"));
			clickOn(weOrgName, "Org Name => " + data.get("1stLevelApprovalOrgName"));
			clickOn(btnFilter, "Filter");
			WebElement weDataRequestDropdown = driver.findElement(
					By.xpath("//h6[text()='Data Request']/parent::div/parent::div/following-sibling::div//div/div"));
			clickOn(weDataRequestDropdown, "Data Request Filter");
			Thread.sleep(1000);
			WebElement weDataRequestName = driver.findElement(
					By.xpath("//ul/li[text()='None']/following-sibling::li[text()='" + dataRequestName + "']"));
			clickOn(weDataRequestName, "Data Request Name");
			Thread.sleep(1000);
			clickOn(btnClose, "Filters Close");
			Thread.sleep(1000);
			WebElement selectedDataRequestName = driver
					.findElement(By.xpath("//span[text()='Data Request : ']/following-sibling::div/span"));
			if (selectedDataRequestName.getAttribute("aria-label").trim().equals(dataRequestName)) {
				;
				blnSucc = true;
			}
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
		return blnSucc;
	}

	public boolean verifyReplyExternalComments(String commentSentBy, String commentRepliedBy) {
		String strBeforeCreateCommentsCount = null, strAfterCreateCommentsCount, repliedComments;
		boolean blnCreated = false, blnSucc = false;
		int iBeforeCreateCommentsCount = 0;
		try {
			if (selectOrganizationInCatalogDetailsPage(data.get("1stLevelApprovalOrgName"))) {
				selectPeriodFromCatalogDetailsPage(data.get("PeriodDesc"));
				if (selectDataRequestFromCatalogDetailsFilter(GlobalVariables.dataRequestName)) {
					WebElement expandTopic = driver.findElement(By.xpath("//span[text()='" + data.get("TopicName")
							+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
					clickOn(expandTopic, "Expand Topic ==> " + data.get("TopicName"));
					WebElement weMetric = driver.findElement(
							By.xpath("//span[text()='" + GlobalVariables.catalogDetails.get("MetricName") + "']"));
					verifyIfElementPresent(weMetric, "Topic ==> " + data.get("MetricName"),
							"Topics and Metric Details");
					WebElement weCommentsDotIcon = driver.findElement(By.xpath("//span[text()='"
							+ GlobalVariables.catalogDetails.get("MetricName")
							+ "']/ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='Comments']//button//span[contains(@class,'MuiBadge-dot')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weCommentsDotIcon);
					verifyIfElementPresent(weCommentsDotIcon, "comments dot icon", "Topics and Metric Details");
					if (weCommentsDotIcon.getCssValue("background-color").trim().equals("rgba(254, 80, 80, 1)")) {
						passed("Comments dot icon is displayed in Red color");
					} else {
						failed(driver, "Comments dot icon is not displayed in Red color");
					}
					WebElement weComments = driver.findElement(By.xpath("//span[text()='"
							+ GlobalVariables.catalogDetails.get("MetricName")
							+ "']/ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='Comments']//button"));
					Actions builder = new Actions(driver);
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weComments);
					WebElement myElement = new WebDriverWait(driver, 20)
							.until(ExpectedConditions.visibilityOf(weComments));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
					builder.moveToElement(weComments).click().build().perform();
					// span[@aria-label="Test_Metric_1010"]/following-sibling::div/span[text()='Goals']
					WebElement commentsPopupUp = driver.findElement(By.xpath("//div[text()='Comments']"));
					blnSucc = verifyIfElementPresent(commentsPopupUp, "comments RightHandSide panel displayed for ==> "
							+ GlobalVariables.catalogDetails.get("MetricName"), "comments Details");
					if (blnSucc) {
						Thread.sleep(4000);
						clickOn(btnExternalComments, "External Comments");
						Thread.sleep(4000);
						WebElement weCommentsCount = driver.findElement(By.xpath(
								"//button[text()='Internal']/parent::div/parent::div/parent::div/following::div[1]/div/span"));
						String strCommentsCount = weCommentsCount.getText();
						String[] arrCommentsCount = strCommentsCount.split("");
						strBeforeCreateCommentsCount = arrCommentsCount[0];
						iBeforeCreateCommentsCount = Integer.parseInt(strBeforeCreateCommentsCount) + 1;
						if (strBeforeCreateCommentsCount.trim().equals("1")) {
							passed("Created Comments count is matching as expected and present comment count is => "
									+ strBeforeCreateCommentsCount);
						} else {
							failed(driver,
									"Created Comments count is not matching as expected and present comment count is => "
											+ strBeforeCreateCommentsCount);
						}
						WebElement weCommentSentBy = driver
								.findElement(By.xpath("//div[text()='" + GlobalVariables.commentsDetails.get("Subject")
										+ "']/preceding-sibling::div//span[text()='" + commentSentBy + "']"));
						verifyElementAndHighlightIfExists(weCommentSentBy, "Comment Sent by", "Comment Page");
						repliedComments = enterReplyCommentsInCommentsPage(commentSentBy);
						clickOn(btnReply, "Reply comment");
						Thread.sleep(8000);
						/*
						 * "//span[@class='hideRelpy']/parent::div/parent::div/following-sibling::div/div//span[@aria-label='"
						 * + commentRepliedBy +
						 * "']/parent::div/parent::div/following-sibling::div[text()='" +
						 * repliedComments + "']"
						 */
						String xpathDynamics = "//span[text()='" + data.get("1stLevelApproverPortCoId")
								+ "']/parent::div/parent::div/following-sibling::div//following-sibling::div[contains(text(),'"
								+ repliedComments + "')]";
						WebElement weRepliedComment = driver.findElement(By.xpath(xpathDynamics));
						blnCreated = verifyElementAndHighlightIfExists(weRepliedComment, "Replied comment",
								"Comment Page");
						clickOn(btnSectionClose, "Close Comments");
						// btnSectionClose.click();
						System.out.println();
					} else {
						failed(driver, "Unable to select Comments for Metric => " + data.get("MetricName"));
					}
				} else {
					failed(driver, "Umable to select data requeust from filter and data request name is => "
							+ GlobalVariables.dataRequestName);
				}
			} else {
				failed(driver, "Umable to select Organization and Organization name is => "
						+ data.get("1stLevelApprovalOrgName"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blnCreated;
	}

	public boolean verifyReplyExternalCommentsAtPortCoUser(String commentSentBy, String commentRepliedBy) {
		String strBeforeCreateCommentsCount = null, strAfterCreateCommentsCount, repliedComments;
		boolean blnCreated = false, blnSucc = false;
		int iBeforeCreateCommentsCount = 0;
		try {
			WebElement expandTopic = driver.findElement(By.xpath("//span[text()='" + data.get("TopicName")
					+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
			clickOn(expandTopic, "Expand Topic ==> " + data.get("TopicName"));
			WebElement weMetric = driver
					.findElement(By.xpath("//span[text()='" + GlobalVariables.catalogDetails.get("MetricName") + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weMetric);
			verifyIfElementPresent(weMetric, "Topic ==> " + data.get("MetricName"), "Topics and Metric Details");
			WebElement weCommentsDotIcon = driver.findElement(By.xpath("//span[text()='"
					+ GlobalVariables.catalogDetails.get("MetricName")
					+ "']/ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='Comments']//button//span[contains(@class,'MuiBadge-dot')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weCommentsDotIcon);
			verifyIfElementPresent(weCommentsDotIcon, "comments dot icon", "Topics and Metric Details");
			if (weCommentsDotIcon.getCssValue("background-color").trim().equals("rgba(254, 80, 80, 1)")) {
				passed("Comments dot icon is displayed in Red color");
			} else {
				failed(driver, "Comments dot icon is not displayed in Red color");
			}
			WebElement weComments = driver.findElement(By.xpath("//span[text()='"
					+ GlobalVariables.catalogDetails.get("MetricName")
					+ "']/ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='Comments']//button"));
			Actions builder = new Actions(driver);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weComments);
			WebElement myElement = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(weComments));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
			builder.moveToElement(weComments).click().build().perform();
			WebElement commentsPopupUp = driver.findElement(By.xpath("//div[text()='Comments']"));
			blnSucc = verifyIfElementPresent(commentsPopupUp, "comments RightHandSide panel displayed for ==> "
					+ GlobalVariables.catalogDetails.get("MetricName"), "comments Details");
			if (blnSucc) {
				Thread.sleep(4000);
				clickOn(btnExternalComments, "External Comments");
				Thread.sleep(4000);
				WebElement weCommentsCount = driver.findElement(By.xpath(
						"//button[text()='Internal']/parent::div/parent::div/parent::div/following::div[1]/div/span"));
				String strCommentsCount = weCommentsCount.getText();
				String[] arrCommentsCount = strCommentsCount.split("");
				strBeforeCreateCommentsCount = arrCommentsCount[0];
				iBeforeCreateCommentsCount = Integer.parseInt(strBeforeCreateCommentsCount) + 1;
				if (strBeforeCreateCommentsCount.trim().equals("1")) {
					passed("Created Comments count is matching as expected and present comment count is => "
							+ strBeforeCreateCommentsCount);
				} else {
					failed(driver, "Created Comments count is not matching as expected and present comment count is => "
							+ strBeforeCreateCommentsCount);
				}
				WebElement weCommentSentBy = driver
						.findElement(By.xpath("//div[text()='" + GlobalVariables.commentsDetails.get("Subject")
								+ "']/preceding-sibling::div//span[text()='" + commentSentBy + "']"));
				verifyElementAndHighlightIfExists(weCommentSentBy, "Comment Sent by", "Comment Page");
				repliedComments = enterReplyCommentsInCommentsPage(commentSentBy);
				clickOn(btnReply, "Reply comment");
				Thread.sleep(8000);
				/*
				 * "//span[@class='hideRelpy']/parent::div/parent::div/following-sibling::div/div//span[@aria-label='"
				 * + commentRepliedBy +
				 * "']/parent::div/parent::div/following-sibling::div[text()='" +
				 * repliedComments + "']"
				 */
				String dynamicXpath = "//span[text()='" + data.get("1stLevelApproverPortCoId")
						+ "']/parent::div/parent::div/following-sibling::div//following-sibling::div[contains(text(),'"
						+ repliedComments + "')]";
				WebElement weRepliedComment = driver.findElement(By.xpath(dynamicXpath));
				blnCreated = verifyElementAndHighlightIfExists(weRepliedComment, "Replied comment", "Comment Page");
				clickOn(btnSectionClose, "Close Comments");
				System.out.println();
			} else {
				failed(driver, "Unable to select Comments for Metric => " + data.get("MetricName"));
			}
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
		return blnCreated;
	}

	public void ValidateEnterDRFromPortCoWithPeriod(String period) {
		try {
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
			}
			Thread.sleep(3000);
			String xpathMetricValue = "(//span[text()='" + data.get("MetricName")
					+ "']//parent::div//parent::div//parent::div//following-sibling::div//span)[1]";
			clickOnElementWithDynamicXpath(xpathMetricValue, "Metric Value Field");
			if (data.get("DataType").equals("Integer")) {
				clickOnDataForIntegerInputType();
				enterDataForIntegerInputType(data.get(period));
			} else if (data.get("DataType").equals("Number")) {
				clickOnDataForNumberInputType();
				enterDataForIntegerInputType(data.get(period));
			} else {
				clickOnDataForDescInputType();
				enterDataForBooleanInputType(data.get(period));
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidatePeriodEnteredValuesAtCatalogInvestCo() {
		try {
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
			}
			sleep(3000);
			String strValPeriods = data.get("SelectPeriod");
			String[] arrValPeriods = strValPeriods.split(",");
			for (int i = 0; i < arrValPeriods.length; i++) {
				selectPeriod(arrValPeriods[i]);
				if (!classExpand.contains("hidden")) {
					clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
				}
				String xpathMetricValue = "(//span[text()='" + data.get("MetricName")
						+ "']//following::div[@col-id='metricValue']//span)[1]";
				String catalogMetricValue = driver.findElement(By.xpath(xpathMetricValue)).getText();
				if (catalogMetricValue.equals(data.get(arrValPeriods[i]))) {
					passed("Successfully Validated Aggregated Catalog Metric Value As" + catalogMetricValue);
				} else {
					failed(driver, "Failed To Validate Aggregated Catalog Metric Value Expected"
							+ data.get(arrValPeriods[i]) + "But Actual is " + catalogMetricValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ValidateEnterDRRevisedInputFromPortCo(String inputValue) {
		try {
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
			}
			Thread.sleep(3000);
			String xpathMetricValue = "(//span[text()='" + data.get("MetricName")
					+ "']//parent::div//parent::div//parent::div//following-sibling::div//span)[1]";
			clickOnElementWithDynamicXpath(xpathMetricValue, "Metric Value Field");
			clickOn(eleValueInMetricDetails, " Value Field In Metric details");
			txtInputValue.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			txtInputValue.sendKeys(Keys.BACK_SPACE);
			enterText(txtInputValue, "Input Value", inputValue);
			clickOn(btnSave, "Save Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validatePublishDRFromPortCo() {
		try {
			clickOn(btnPublish, "publish button");
			clickOn(btnConfirm, "Confirm button");
			if (isElementPresent(msgSuccessDataPublish)) {
				passed("Sucessfully validate DR publish Sucess Message");
			} else {
				failed(driver, "Failed To validate Publish success message");
			}
			clickOn(btnDone, "Done button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(lblCatalogs);
			if (isElementPresent(lblCatalogs)) {
				passed("User Successfully Navigated To Catalog Page");
			} else {
				failed(driver, "Failed To Navigate To catalog Page");
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validatePublishDRFromPortCo1() {
		clickOn(btnPublish, "publish button");
		clickOn(btnConfirm, "Confirm button");
		if (isElementPresent(msgSuccessDataPublish)) {
			passed("Sucessfully validate DR publish Sucess Message");
		} else {
			failed(driver, "Failed To validate Publish success message");
		}
		clickOn(btnDone, "Done button");
	}

	public void validateRevisedMetricValueInCatalog() {
		try {
			String xpathCatalogName = "//article//*[contains(text(),'" + data.get("CatalogName") + "')]";
			clickOnElementWithDynamicXpath(xpathCatalogName, "Catalog Name");
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand  Data request Link");
			String metricName = data.get("MetricName");
			String xpathMetricValue = "//span[text()='" + metricName
					+ "']//ancestor::div[@col-id='metricName']//parent::div//child::div[@col-id='metricValue']//span";
			WebElement weMetricValue = driver.findElement(By.xpath(xpathMetricValue));
			if (weMetricValue.getText().trim().equals(data.get("RevisedInputValue").trim())) {
				passed("Successfully validated  Revised Metric Value In Catalog Metric As" + weMetricValue.getText());
			} else {
				failed(driver, "Failed To Validate  Revised Metric Value In Catalog Metric");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void clickonDropValidationMetricMethod(String validationMethod) {
		try {
			clickOn(drpValidationCatalog, "Drop Down Validation Catalog");
			WebElement wedrpMetricValidations = driver
					.findElement(By.xpath("//li[contains(text(),'" + validationMethod + "')]"));
			clickOn(wedrpMetricValidations, "Drop Down value Validation Catalog");
		} catch (Exception e) {
		}
	}

	public void validateMetricValidationsonAGgridofCatalog() {
		try {
//    		 String metricValidationMethod = data.get("Catalog Validations");
//    		    String[] arrmetricValidationMethod = metricValidationMethod.split(",");
//    		    for (int i = 0; i < arrmetricValidationMethod.length; i++) {
//    		    	clickonDropValidationMetricMethod(arrmetricValidationMethod[i]);
//    		    		    ((JavascriptExecutor) driver)
//    		    		    .executeScript("document.querySelector('div[ref=eBodyViewport]').scrollTop=500");	
//    		    expandTopicInCatalog(data.get("TopicName"));
//    		    String xpathdrpMetricvalueValidations = "//span[text()='" + data.get("MetricName") + "']/ancestor::div[@col-id='metricName']//following-sibling::div//img";
//    		    clickOnElementWithDynamicXpath(xpathdrpMetricvalueValidations, " value Validation Catalog");
//    		    if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
//    			    if (arrmetricValidationMethod[i].equals("Mandatory Evidence")) {
//    				WaitForElementWithDynamicXpath("//div[contains(text(),'"+ Constants.standardErrorMessageMandatoryEvidence + "')]");
//    				if (isElementPresentDynamicXpath("//div[contains(text(),'"+ Constants.standardErrorMessageMandatoryEvidence + "')]")) {
//    				    passed("Successfully Validated Mandatory Evidence   Error Message on Metric Grid "
//    					    + Constants.standardErrorMessageMandatoryEvidence);
//    				} else {
//    				    failed(driver, "Failed To Validate Mandatory Evidence Error Message on Metriv Grid");
//    				}
//    			    }
//    			    if (arrmetricValidationMethod[i].equals("Explanation Mandatory")) {
//    				WaitForElementWithDynamicXpath("//div[contains(text(),'" + Constants.standardErrorMessageExplanationMandatory + "')]");
//    				if (isElementPresentDynamicXpath("//div[contains(text(),'" + Constants.standardErrorMessageExplanationMandatory + "')]")) {
//    				    passed("Successfully Validated Mandatory Explanation   Error Message on Metric Grid "
//    					    + Constants.standardErrorMessageMandatoryEvidence);
//    				} else {
//    				    failed(driver, "Failed To Validate Mandatory Explanation   Error Message on Metriv Grid");
//    				}
//    			    }
//    			    
//    			    if ((isNumber(data.get("RHPMetricValue")) && data.get("TopicName").equals("Text"))) {
//    					WaitForElementWithDynamicXpath("//div[contains(text(),'" + Constants.inputTypeRequiredErrorMessageText + "')]");
//        				if (isElementPresentDynamicXpath("//div[contains(text(),'" + Constants.inputTypeRequiredErrorMessageText + "')]")) {
//        				    passed("Successfully Validated Expression Text  Error Message on Metric Grid "
//        					    + Constants.inputTypeRequiredErrorMessageText);
//        				} else {
//        				    failed(driver, "Failed To Validate Expression Text Error Message on Metriv Grid");
//        				}
//    				    }  
//    		    } 
//    			if (Boolean.parseBoolean(data.get("IsCustomMessage"))) {
//    			    if (arrmetricValidationMethod[i].equals("Mandatory Evidence")) {
//    				WaitForElementWithDynamicXpath("//div[contains(text(),'"+ Constants.customErrorMessageMandatoryEvidence + "')]");
//    				if (isElementPresentDynamicXpath("//div[contains(text(),'"+ Constants.customErrorMessageMandatoryEvidence + "')]")) {
//    				    passed("Successfully Validated Mandatory Evidence   Error Message on Metric Grid "
//    					    + Constants.customErrorMessageMandatoryEvidence);
//    				} else {
//    				    failed(driver, "Failed To Validate Mandatory Evidence Error Message on Metriv Grid");
//    				}
//    			    }
//    			    if (arrmetricValidationMethod[i].equals("Explanation Mandatory")) {
//    				WaitForElementWithDynamicXpath("//div[contains(text(),'" + Constants.customErrorMessageExplanationMandatory + "')]");
//    				if (isElementPresentDynamicXpath("//div[contains(text(),'" + Constants.customErrorMessageExplanationMandatory + "')]")) {
//    				    passed("Successfully Validated Mandatory Explanation   Error Message on Metric Grid "
//    					    + Constants.customErrorMessageExplanationMandatory);
//    				} else {
//    				    failed(driver, "Failed To Validate Mandatory Explanation   Error Message on Metriv Grid");
//    				}
//    			    } 
//    		    
//    			}
//    		    }
//    		    
//       		 clickOn(drpValidationCatalog, "Drop Down Validation Catalog");
//    		    WebElement wedrpMetricValidations = driver.findElement(By.xpath("//li[contains(text(),'None')]"));
//		    clickOn(wedrpMetricValidations, "Drop Down None value Validation Catalog");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	// ------------------------PM Smoke checklist
	// ---------------------------------------------------------

	@FindBy(xpath = "//span[contains(text(),'Validations')]//parent::div//following-sibling::*[@data-testid='KeyboardArrowDownIcon']")
	private WebElement drpValidationCatalog;

	public void enterDataForIntegerInputTypewithoutnextforth(String metricValue) {
		try {
			WebElement MetricValueData = driver
					.findElement(By.xpath("(//p[text()='Data']//following-sibling::div//input)[1]"));
			waitForElement(MetricValueData);
			MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			MetricValueData.sendKeys(Keys.BACK_SPACE);
			enterText(MetricValueData, "Metric Value Data", metricValue);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			enterDataForIntegerInputType(metricValue);
		}
	}

	@FindBy(xpath = "//span[text()='Click here to ...']")
	private WebElement btnValuetable;
	@FindBy(xpath = "//span[text()='Click here to view values']")
	private WebElement btnValuetableRHP;
	@FindBy(xpath = "//p[text()='Boolean']")
	private WebElement lblBoolean;
	@FindBy(xpath = "//p[text()='Text']")
	private WebElement lblText;
	@FindBy(xpath = "//div[@id='0Boolean']")
	private WebElement drpBoolean;
	@FindBy(xpath = "//input[@id='0Text']")
	private WebElement txtText;
	@FindBy(xpath = "//h2//*[@data-testid='CloseIcon']")
	private WebElement btnSectionCloseold;
	@FindBy(xpath = "//*[@data-testid='CloseIcon']")
	private WebElement btnSectionCloseRHP;

	public void validateUpdatedDatabackForth(String metricValue) {
		try {
			sleep(500);
			clickOn(btnNext, "btnNext");
			if (isElementPresent(txtTableToastMessage)) {
				passed("Successfully validated Metric data is saved toast ");
			} else {
				failed(driver, "Failed to valiadate Metric data is saved toast ");
			}
			sleep(1000);
			clickOn(btnPrev, "btnPrev");
			sleep(500);
			if (data.get("MetricName").equals("SingleSelect") || data.get("MetricName").equals("Boolean")) {
				WebElement MetricValueData = driver
						.findElement(By.xpath("//p[text()='Data']//parent::div//div//span[text()='" + metricValue
								+ "']//parent::div//span//input"));
				String metricValuerhp = MetricValueData.getAttribute("value");
				if (metricValuerhp.equals(metricValue)) {
					passed("Successfully validated metric value on the RHP" + metricValue);
				} else {
					failed(driver, "Failed to validate updated metric RHP value " + metricValue);
				}
			} else {
				WebElement metricValuerhp = driver
						.findElement(By.xpath("//p[text()='Data']//following-sibling::div/span/div/div/div/p"));
				if (metricValuerhp.getText().equals(metricValue)) {
					passed("Successfully validated metric value on the RHP" + metricValue);
				} else {
					failed(driver, "Failed to validate updated metric RHP value " + metricValue);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void enterDataForIntegerInputTypeNextForth(String metricValue) {
		try {
			if (data.get("MetricName").equals("Text")) {
				WebElement MetricValueData = driver
						.findElement(By.xpath("(//p[text()='Data']//following-sibling::div//input)[1]"));
				waitForElement(MetricValueData);
				MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				sleep(500);
				MetricValueData.sendKeys(Keys.BACK_SPACE);
				sleep(1500);
				enterText(MetricValueData, "Metric Value Data", metricValue);
				clickOn(btnNext, "btnNext");
				sleep(500);
				clickOn(btnPrev, "btnPrev");
				sleep(500);
				if (isElementPresentDynamicXpath("//*[text()='" + Constants.inputTypeRequiredErrorMessageText + "']")) {
					passed("Successfully Validated  [a-z] Error message" + Constants.inputTypeRequiredErrorMessageText);
				} else {
					failed(driver, "Failed To Validate [a-z] Error message ");
				}
			} else {
				WebElement MetricValueData = driver
						.findElement(By.xpath("(//p[text()='Data']//following-sibling::div//input)[1]"));
				waitForElement(MetricValueData);
				MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				sleep(500);
				MetricValueData.sendKeys(Keys.BACK_SPACE);
				sleep(500);
				enterText(MetricValueData, "Metric Value Data", metricValue);
				validateUpdatedDatabackForth(metricValue);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			enterDataForIntegerInputType(metricValue);
		}
	}

	public void validateMetricValueUpdateinMetricGrid(String metricValue) {
//		clickOn(btnSectionClose, "AuditLog Close Button");
		WebElement metricValueDatagrid = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName")
				+ "']/ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']/div/div/span"));
		if (metricValueDatagrid.getText().equals(metricValue)) {
			passed("Successfully validated metric value on the grid " + metricValue);
		} else {
			failed(driver, "Failed to validate updated metric grid value " + metricValue);
		}
	}
	public void ValidateIntDataTypeMetricDataEntryAndEvidenceUploadNextForth() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForIntegerInputType();
			enterDataForIntegerInputTypeNextForth(data.get("RHPMetricValue"));
			validateInstructionIncatalogView(Constants.InstructionCatalog);
			validateInstructionIncatalogView("https://docs.google.com/document/d/1QkUfoLFrHKcr7_oeNFO25zxRUHE-H3LcULK07otWhKs/edit?hl=en");
			enterExplanationTextRandom();
			validateAuditLogForEnteredValueInMetricDetailsRHP();
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
			validateUploadedEvidenceInMetricDetaisRHP();
			clickOnCloseMetricButton();
			validateMetricValueUpdateinMetricGrid(data.get("RHPMetricValue"));
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void deleteUploadedEvidenceInMetricDetailsRHP() {
		try {
			waitForElement(btnEvidenceInMetricDetailsRHP);
			clickOnElement(btnEvidenceInMetricDetailsRHP, "Evidence Expand Button");
			sleep(1000);
			// waitForElement(btnDeleteEvidence);
			if (isElementPresent(btnDeleteEvidence)) {
				List<WebElement> lstDeleteButtons = driver.findElements(By.xpath("//th//*[@fill='none']"));
				if (lstDeleteButtons.size() > 0) {
					for (WebElement deleteEvidence : lstDeleteButtons) {
						clickOnElement(deleteEvidence, "deleteEvidence");
						waitForElement(btnDeleteYes);
						clickOn(btnDeleteYes, "Delete Yes button");
					}
				}
			}
			sleep(500);
			waitForElement(btnEvidenceInMetricDetailsRHP);
			clickOn(btnEvidenceInMetricDetailsRHP, "Evidence Expand Button");
		} catch (Exception e) {
			System.out.println("Expection At delete Evidence---->");
		}
	}

	public void uploadEvidenceInMetricDetailsRHP() {
		try {
			waitForElement(btnEvidenceInMetricDetailsRHP);
			sleep(1000);
			clickOnElement(btnEvidenceInMetricDetailsRHP, "Evidence button");
			sleep(1000);
			waitForElement(btnAddEvidenceInMetricDetailsRHP);
			sleep(1000);
			clickOnElement(btnAddEvidenceInMetricDetailsRHP, "Add Evidence Button");
			sleep(1000);
			uploadEvidencerhp();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
			System.out.println("Expection At **** Evidence---->");
			uploadEvidenceInMetricDetailsRHP();
		}
	}

	@FindBy(xpath = "//button[text()='Upload']//parent::div//following-sibling::div/button[text()='Close']")
	private WebElement btnDoneUploadFile;

	public void uploadEvidencerhp() {
		try {
			waitForElement(btnBrowseFiles);
			System.out.println(waitForElement(btnBrowseFiles));
			clickOnElement(btnBrowseFiles, "Browse Files");
			String path = System.getProperty("user.dir");
			System.out.println(path);
			String filePath = path + "\\src\\test\\resources\\DataRequestFiles\\" + data.get("EvidenceFileName");
			ProcessBuilder pb = new ProcessBuilder(path + "\\src\\test\\resources\\DataRequestFiles\\fileUpload.exe",
					filePath);
			pb.start();
			waitForElement(btnUpload);
			clickOn(btnUpload, "btnUpload");
			waitForElement(btnDoneUploadFile);
			clickOn(btnDoneUploadFile, "btnDoneUploadFile");
			sleep(3000);
		} catch (Exception e) {
			System.out.println("Expection At Uploading---->" + e.getMessage());
			uploadEvidence();
		}
	}

	public void ValidateNumDataTypeMetricDataEntryAndEvidenceUploadUpdateNextForth() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForNumberInputType();
			enterDataForIntegerInputTypeNextForth(data.get("RHPMetricValue"));
			clickOnMetricName(data.get("MetricName"));
			enterExplanationTextRandom();
			validateAuditLogForEnteredValueInMetricDetailsRHP();
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
			validateUploadedEvidenceInMetricDetaisRHP();
			clickOnCloseMetricButton();
			validateMetricValueUpdateinMetricGrid(data.get("RHPMetricValue"));
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateTextDataTypeMetricDataEntryAndEvidenceUploadUpdateNextForth() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForNumberInputType();
			if (Boolean.parseBoolean(data.get("IsCondOrNonCondValidation"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
				clearExplanationInExplanationTextBox();
				sleep(3000);
				System.out.println(waitForElement(txtareaExplanation));
				if (txtareaExplanationText.getText().isEmpty()) {
				} else {
					txtareaExplanationText.sendKeys(Keys.chord(Keys.CONTROL, "a"));
					txtareaExplanationText.sendKeys(Keys.BACK_SPACE);
				}
				WaitForElementWithDynamicXpath(
						"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']");
				if (isElementPresentDynamicXpath(
						"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
					passed("Successfully Validated Mandatory Explanation   Error Message "
							+ Constants.standardErrorMessageExplanationMandatory);
				} else {
					failed(driver, "Failed To Validate Mandatory Explanation  Error message");
				}
				clickOnDataNotAvailablecheckBoxToUncheck();
			}
			enterDataForIntegerInputTypeNextForth(data.get("RHPMetricValue"));
			clickOnMetricName(data.get("MetricName"));
			enterExplanationTextRandom();
			validateAuditLogForEnteredValueInMetricDetailsRHP();
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
			validateUploadedEvidenceInMetricDetaisRHP();
			clickOnCloseMetricButton();
			validateMetricValueUpdateinMetricGrid(data.get("RHPMetricValue"));
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//button[@aria-label='Table']")
	private WebElement lbltableDescinput;

	public void enterDataForDescInputTypeNextForth(String metricValue) {
		try {
			waitForElement(lbltableDescinput);
			WebElement MetricValueData = driver
					.findElement(By.xpath("//p[text()='Data']//following-sibling::div//div/p"));
			waitForElement(MetricValueData);
			if (metricValue.equals("NA")) {
				enterText(MetricValueData, "Metric Value Data", metricValue);
			} else {
				MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				MetricValueData.sendKeys(Keys.BACK_SPACE);
//				enterText(MetricValueData, "Metric Value Data", metricValue);
				sleep(500);
				MetricValueData.sendKeys(metricValue);
				sleep(1000);
				validateUpdatedDatabackForth(metricValue);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void clickOnDataForDescInputType() {
		try {
			WebElement weMetricData = driver.findElement(By.xpath("//p[text()='Data']//following-sibling::div//span"));
			waitForElement(weMetricData);
			clickOn(weMetricData, "Metric Data");
			sleep(500);
		} catch (Exception e) {
			System.out.println("Exception comming here------------------->");
//			clickOnDataForDescInputType();
		}
	}

	public void ValidateDescDataTypeMetricDataEntryAndEvidenceUploadUpdateNextForth() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForDescInputType();
			WebElement MetricValueData = driver
					.findElement(By.xpath("//p[text()='Data']//following-sibling::div//div/p"));
			enterText(MetricValueData, "Metric Value Data", "ABC");
			clickOn(btnNext, "btnNext");
			sleep(500);
			clickOn(btnPrev, "btnPrev");
			sleep(500);
			clickOnDataForDescInputType();
			enterDataForDescInputTypeNextForth(data.get("RHPMetricValue"));
			validateAuditLogForEnteredValueInMetricDetailsRHP();
			enterExplanationTextRandom();
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
			validateUploadedEvidenceInMetricDetaisRHP();
			clickOnCloseMetricButton();
			validateMetricValueUpdateinMetricGrid(data.get("RHPMetricValue"));
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void clickOnDataForTableInputType() {
		try {
			clickOn(btnTable, "btn Table");
			sleep(1000);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//button[text()='External']")
	private WebElement btnExternal;
	@FindBy(xpath = "//h2/*[@data-testid='CloseIcon' and @aria-label='Close']")
	private WebElement btnSectionCloseTableMetric;
	String[] lblTableMetricDataNamesExpected;

	public void validateDataInputValuesinMetricsTablevalueRHP() {
		clickOn(btnNext, "btnNext");
		sleep(1000);
		clickOn(btnPrev, "btnPrev");
		clickOn(btnValuetableRHP, "Table Value Input Button");
		ValidateDataInputValuesinMetricsvalue();
	}

	public void validateDataInputValuesinMetricsTablevalueGrid(String metricName) {
		WebElement metricValueDatagrid = driver.findElement(By.xpath("//span[text()='" + metricName
				+ "']/ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']/div/div/span"));
		clickOn(metricValueDatagrid, "metricValueDatagrid");
		ValidateDataInputValuesinMetricsvalue();
	}

	public void ValidateDataInputValuesinMetricsvalue() {
		try {
			String[] lblTableMetricDataNames = { "Name", "Gender", "IsActive", "FavCar", "Age" };
			String[] lblTableMetricDataNamesExpected = { Constants.textMetricTableValue,
					Constants.drpGenderBooleanValue, Constants.drpDownIsActiveValue, Constants.drpMultiSelectFavCar,
					Constants.texttxtAge };
			for (int k = 0; k < lblTableMetricDataNames.length; k++) {
				if (lblTableMetricDataNames[k].equals("Name") || lblTableMetricDataNames[k].equals("Age")) {
					WebElement weTableMetricValue1 = driver.findElement(By.xpath("//p[text()='"
							+ lblTableMetricDataNames[k] + "']//following-sibling::div//div//div//input"));
					String weTableMetricValue = weTableMetricValue1.getAttribute("value");
					Thread.sleep(500);
					if (weTableMetricValue.contains(lblTableMetricDataNamesExpected[k])) {
						passed("Successfully Validated In Activity Details As" + weTableMetricValue);
					} else {
						failed(driver, "Failed To validate  In Activity Details Expected As "
								+ lblTableMetricDataNamesExpected[k] + "But Actual is" + weTableMetricValue);
					}
				} else if (lblTableMetricDataNames[k].equals("FavCar")) {
					WebElement weTableMetricValue = driver.findElement(By.xpath("//p[text()='"
							+ lblTableMetricDataNames[k] + "']//following-sibling::div//div[@aria-label='Audi']"));
					Thread.sleep(500);
					if (weTableMetricValue.getText().trim().contains(lblTableMetricDataNamesExpected[k])) {
						passed("Successfully Validated  In Activity Details As" + weTableMetricValue.getText());
					} else {
						failed(driver, "Failed To validate  In Activity Details Expected As "
								+ lblTableMetricDataNamesExpected[k] + "But Actual is" + weTableMetricValue.getText());
					}
				} else {
					WebElement weTableMetricValue = driver.findElement(By.xpath(
							"//p[text()='" + lblTableMetricDataNames[k] + "']//following-sibling::div//div//div"));
					Thread.sleep(500);
					if (weTableMetricValue.getText().trim().contains(lblTableMetricDataNamesExpected[k])) {
						passed("Successfully Validated  In Activity Details As" + weTableMetricValue.getText());
					} else {
						failed(driver, "Failed To validate  In Activity Details Expected As "
								+ lblTableMetricDataNamesExpected[k] + "But Actual is" + weTableMetricValue.getText());
					}
				}
			}
			clickOn(btnSectionCloseTableMetric, "Close Table Values");
			sleep(1000);
			if (isElementPresent(btnSectionCloseRHP)) {
				clickOn(btnSectionCloseRHP, "btnSectionCloseRHP");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void enterDataForTableInputType(String metricValue) {
		try {
			GlobalVariables.textMetricTableValue = "Mteric Value " + generateRandomString(4);
			if (data.get("MetricName").equals("TableFixedRows")) {
				if (isElementPresent(btnAddInTable)) {
					failed(driver, "The  Add Button Table Metric Value is Able to Click");
				} else {
					passed("Successfully Validated that  Add Button Table Metric is Disabled");
				}
				txtNameTableFixedRow.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				txtNameTableFixedRow.sendKeys(Keys.BACK_SPACE);
				txtNameTableFixedRow.sendKeys(Constants.textMetricTableValue);
				clickOn(drpBooleanGenderFixedRow, "Table Boolean DropDown");
				WebElement weDropDownGenderValue = driver
						.findElement(By.xpath("//li[text()='" + Constants.drpGenderBooleanValue + "']"));
				clickOn(weDropDownGenderValue, "DropDown Value");
				clickOn(drpBooleanIsActiveFixedRow, "Table Boolean DropDown");
				WebElement weDropDownIsActiveValue = driver
						.findElement(By.xpath("//li[text()='" + Constants.drpDownIsActiveValue + "']"));
				clickOn(weDropDownIsActiveValue, " DropDown IsActive Value");
				verifyIfElementPresent(drpMultiSelectFavCarFixedRow, "drpMultiSelectFavCarFixedRow",
						"drpMultiSelectFavCarFixedRow");
				txtAgeTableFixedRow.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				txtAgeTableFixedRow.sendKeys(Keys.BACK_SPACE);
				txtAgeTableFixedRow.sendKeys(Constants.texttxtAge);
				waitForElement(btnSaveInTable);
				clickOn(btnSaveInTable, "Save Button");
				if (txtTableToastMessage.getText().equals(Constants.tableMetricSavedToastMessage)) {
					passed("Successfully Validated Table Metric value Saved Successfully Toast  message");
				} else {
					fail("Failed to validated Table Metric value Saved Successfully Toast  message Actual is "
							+ txtTableToastMessage.getText());
				}
			} else {
				verifyIfElementPresent(lblMetricDataTable, "lblMetricDataTable", "lblMetricDataTable");
				jsClick(btnAddInTable, "Add button In Table");
				txtNameTable.sendKeys(Constants.textMetricTableValue);
				clickOn(drpBooleanGender, "Table Boolean DropDown");
				WebElement weDropDownGenderValue = driver
						.findElement(By.xpath("//li[text()='" + Constants.drpGenderBooleanValue + "']"));
				clickOn(weDropDownGenderValue, "DropDown Value");
				clickOn(drpBooleanIsActive, "Table Boolean DropDown");
				WebElement weDropDownIsActiveValue = driver
						.findElement(By.xpath("//li[text()='" + Constants.drpDownIsActiveValue + "']"));
				clickOn(weDropDownIsActiveValue, " DropDown IsActive Value");
				txtAgeTable.sendKeys(Constants.texttxtAge);
				waitForElement(btnSaveInTable);
				clickOn(btnSaveInTable, "Save Button");
				if (txtToastErrorTableMessage.getText().equals(Constants.tableUniqueKeyErrorMessage)) {
					passed("Successfully Validated Table Unique Key Error message");
				} else {
					fail("Failed to validatedTable Unique Key Error message Actual is "
							+ txtToastErrorTableMessage.getText());
				}
				clickOn(btnCancel, "Cancel Button Table Metric Value");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateAuditLogForEnteredValueInMetricDetailsRHPTable() {
		try {
			if (!data.get("MetricName").equals("TableNonFixedAndUniqueKey")) {
				waitForElement(btnAuditLogInMetricDetailsRHP);
				clickOn(btnAuditLogInMetricDetailsRHP, "Audit button In RHP");
				waitForElement(btnRefresh);
				List<WebElement> noOfRecords = driver.findElements(By.xpath(
						"//p[text()='Audit']//parent::div//following::div[@role='rowgroup']/div/div[@col-id='value'and @role='gridcell']"));
				int intialRecorsCount = noOfRecords.size();
				int afterRefreshRecorsCount;
				List<WebElement> noOfRecordsAfterRefresh;
				do {
					clickOn(btnRefresh, "Refresh button");
					noOfRecordsAfterRefresh = driver.findElements(By.xpath(
							"//p[text()='Audit']//parent::div//following::div[@role='rowgroup']/div/div[@col-id='value'and @role='gridcell']"));
					afterRefreshRecorsCount = noOfRecordsAfterRefresh.size();
				} while (afterRefreshRecorsCount == intialRecorsCount);
				waitForElement(txtLatestAuditLogInMetricDetailsValue);
				System.out.println("Entered Boolean data is " + data.get("RHPMetricValue"));
				System.out.println("Entered Text data is " + GlobalVariables.textMetricTableValue);
				if (data.get("InputType").equals("Table")) {
					String[] lblTableMetricDataNamesExpected = { Constants.textMetricTableValue,
							Constants.drpGenderBooleanValue, Constants.drpDownIsActiveValue,
							Constants.drpMultiSelectFavCar, Constants.texttxtAge };
					for (int j = 0; j < lblTableMetricDataNamesExpected.length; j++) {
						if (txtLatestAuditLogInMetricDetailsValue.getText()
								.contains(lblTableMetricDataNamesExpected[j])) {
							passed("Successfully validated  AuditLog Update with Entered value As"
									+ txtLatestAuditLogInMetricDetailsValue.getText());
						} else {
							failed(driver,
									"Failed to validate AuditLog Update with Entered value Expected is "
											+ lblTableMetricDataNamesExpected[j] + "But Actual is"
											+ txtLatestAuditLogInMetricDetailsValue.getText());
						}
					}
				} else {
					if (txtLatestAuditLogInMetricDetailsValue.getText().contains(data.get("RHPMetricValue"))
							&& txtLatestAuditLogInMetricDetailsValue.getText()
									.contains(GlobalVariables.textMetricTableValue)) {
						passed("Successfully validated  AuditLog Update with Entered value As"
								+ txtLatestAuditLogInMetricDetailsValue.getText());
					} else {
						failed(driver,
								"Failed to validate AuditLog Update with Entered value Expected is "
										+ data.get("RHPMetricValue") + "But Actual is"
										+ txtLatestAuditLogInMetricDetailsValue.getText());
					}
				}
				waitForElement(btnAuditLogInMetricDetailsRHP);
				clickOnElement(btnAuditLogInMetricDetailsRHP, "AuditLog Expand Button");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateTableDataTypeMetricDataEntryAndEvidenceUpload() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForTableInputType();
			enterDataForTableInputType(data.get("RHPMetricValue"));
			enterExplanationTextRandom();
			validateAuditLogForEnteredValueInMetricDetailsRHPTable();
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
			validateUploadedEvidenceInMetricDetaisRHP();
			validateDataInputValuesinMetricsTablevalueRHP();
			validateDataInputValuesinMetricsTablevalueGrid(data.get("MetricName"));
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void enterDataSingleSelectTypeNextForth(String metricValue) {
		try {
			clickOn(btnReset, "Reset Button Single Select");
			jsClick(btnClose, "Button Close");
			sleep(1000);
			clickOnMetricName(data.get("MetricName"));
			WebElement MetricValueData = driver
					.findElement(By.xpath("//p[text()='Data']//parent::div//div//span[text()='" + metricValue
							+ "']//parent::div//span//input"));
			waitForElement(MetricValueData);
			clickOn(MetricValueData, metricValue);
			validateUpdatedDatabackForth(metricValue);
//			clickOnMetricName(data.get("MetricName"));
//			clickOn(MetricValueData, metricValue);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateSingleSelctDataTypeMetricDataEntryAndEvidenceUploadUpdateNextForth() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			enterDataSingleSelectTypeNextForth(data.get("RHPMetricValue"));
			enterExplanationTextRandom();
			
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
			validateUploadedEvidenceInMetricDetaisRHP();
			clickOnCloseMetricButton();
			validateMetricValueUpdateinMetricGrid(data.get("RHPMetricValue"));
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void enterDataForBooleanInputTypeNextForth(String metricValue) {
		try {
			clickOn(btnReset, "Reset Button Single Select");
			jsClick(btnClose, "Button Close");
			sleep(1000);
			clickOnMetricName(data.get("MetricName"));
			sleep(500);
			WebElement MetricValueData = driver.findElement(By.xpath("//span//input[@value='" + metricValue + "']"));
			MetricValueData.click();
			validateUpdatedDatabackForth(metricValue);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			enterDataForBooleanInputTypeNextForth(metricValue);
		}
	}

	public void validateUpdatednextandforthForMultiInputType(String metricValue) {
		try {
			clickOn(btnNext, "btnNext");
			if (isElementPresent(txtTableToastMessage)) {
				passed("Successfully validated Metric data is saved toast ");
			} else {
				failed(driver, "Failed to valiadate Metric data is saved toast ");
			}
			sleep(1000);
			clickOn(btnPrev, "btnPrev");
			sleep(1000);
			String[] arrMultiSelect = null;
			arrMultiSelect = metricValue.split(",");
			sleep(2000);
			for (int i = 0; i < arrMultiSelect.length; i++) {
				sleep(1000);
				WebElement chkBoxValue = driver
						.findElement(By.xpath("//p[text()='Data']//parent::div//div//span[text()='" + arrMultiSelect[i]
								+ "']//parent::div//span//input"));
				if (chkBoxValue.getAttribute("value").equals(arrMultiSelect[i])) {
					passed("Succesfully validated multi select metric upated value in RHP " + arrMultiSelect[i]);
				} else {
					failed(driver, "Failed to validate multi select updated value in RHP " + arrMultiSelect[i]);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void enterDataForMultiInputTypeNextForth(String metricValue) {
		try {
			String[] arrMultiSelect = null;
			if (metricValue.contains(",")) {
				arrMultiSelect = metricValue.split(",");
				sleep(2000);
				for (int i = 0; i < arrMultiSelect.length; i++) {
					WaitForElementWithDynamicXpath("//p[text()='Data']//following-sibling::div//span[text()='"
							+ arrMultiSelect[i] + "']//preceding::input[1]");
					WebElement MetricValueData = driver
							.findElement(By.xpath("//p[text()='Data']//following-sibling::div//span[text()='"
									+ arrMultiSelect[i] + "']//preceding::input[1]"));
					WebElement chkBoxValue = driver
							.findElement(By.xpath("//p[text()='Data']//following-sibling::div//span[text()='"
									+ arrMultiSelect[i] + "']//preceding::input[1]//following-sibling::*"));
					if (chkBoxValue.getAttribute("data-testid").contains("CheckBoxOutlineBlankIcon")) {
						clickOn(MetricValueData, metricValue);
					}
				}
				validateUpdatednextandforthForMultiInputType(metricValue);
			} else {
				WebElement MetricValueData = driver
						.findElement(By.xpath("//p[text()='Data']//following-sibling::div//span[text()='" + metricValue
								+ "']//preceding::input[1]"));
				WebElement chkBoxValue = driver
						.findElement(By.xpath("//p[text()='Data']//following-sibling::div//span[text()='" + metricValue
								+ "']//preceding::input[1]//following-sibling::*"));
				clickOnDataForMultiSelectInputType();
				if (chkBoxValue.getAttribute("data-testid").contains("CheckBoxOutlineBlankIcon")) {
					clickOn(MetricValueData, metricValue);
					validateUpdatednextandforthForMultiInputType(metricValue);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			enterDataForMultiInputTypeNextForth(metricValue);
		}
	}

	public void enterDataForMultiInputTypewithoutnextforth(String metricValue) {
		try {
			String[] arrMultiSelect = null;
			if (metricValue.contains(",")) {
				arrMultiSelect = metricValue.split(",");
				sleep(2000);
				for (int i = 0; i < arrMultiSelect.length; i++) {
					WaitForElementWithDynamicXpath("//p[text()='Data']//following-sibling::div//span[text()='"
							+ arrMultiSelect[i] + "']//preceding::input[1]");
					WebElement MetricValueData = driver
							.findElement(By.xpath("//p[text()='Data']//following-sibling::div//span[text()='"
									+ arrMultiSelect[i] + "']//preceding::input[1]"));
					WebElement chkBoxValue = driver
							.findElement(By.xpath("//p[text()='Data']//following-sibling::div//span[text()='"
									+ arrMultiSelect[i] + "']//preceding::input[1]//following-sibling::*"));
					if (chkBoxValue.getAttribute("data-testid").contains("CheckBoxOutlineBlankIcon")) {
						clickOn(MetricValueData, metricValue);
					}
				}
			} else {
				WebElement MetricValueData = driver
						.findElement(By.xpath("//p[text()='Data']//following-sibling::div//span[text()='" + metricValue
								+ "']//preceding::input[1]"));
				WebElement chkBoxValue = driver
						.findElement(By.xpath("//p[text()='Data']//following-sibling::div//span[text()='" + metricValue
								+ "']//preceding::input[1]//following-sibling::*"));
				clickOnDataForMultiSelectInputType();
				if (chkBoxValue.getAttribute("data-testid").contains("CheckBoxOutlineBlankIcon")) {
					clickOn(MetricValueData, metricValue);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			enterDataForMultiInputTypewithoutnextforth(metricValue);
		}
	}

	public void ValidateMultiSelctDataTypeMetricDataEntryAndEvidenceUploadUpdateNextForth() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForMultiSelectInputType();
			clickOnResetAndSave();
			clickOnDataForMultiSelectInputType();
			enterDataForMultiInputTypeNextForth(data.get("RHPMetricValue"));
//    	    clickOnMetricName(data.get("MetricName"));
			if (!Boolean.parseBoolean(data.get("IsRelatedMetric"))) {
				if (Boolean.parseBoolean(data.get("IsCondOrNonCondValidation"))) {
					clearExplanationInExplanationTextBox();
					sleep(3000);
					System.out.println(waitForElement(txtareaExplanation));
					if (txtareaExplanationText.getText().isEmpty()) {
					} else {
						txtareaExplanationText.sendKeys(Keys.chord(Keys.CONTROL, "a"));
						txtareaExplanationText.sendKeys(Keys.BACK_SPACE);
					}
					waitForElement(txtErrorMessageMandatoryNonRelated);
					if (txtErrorMessageMandatoryNonRelated.getText().replaceAll("\\s", "")
							.equals(Constants.customErrorMessageExplanationMandatory.replaceAll("\\s", ""))) {
						passed("Successfully Validated Mandatory Explanation   Error Message "
								+ Constants.customErrorMessageExplanationMandatory);
					} else {
						failed(driver, "Failed To Validate Mandatory Explanation  Error message");
					}
				}
				enterExplanationTextRandom();
				validateAuditLogForEnteredValueInMetricDetailsRHP();
				deleteUploadedEvidenceInMetricDetailsRHP();
				uploadEvidenceInMetricDetailsRHP();
				validateUploadedEvidenceInMetricDetaisRHP();
			} else {
				clickOnDataForMultiSelectInputTypeForRelatedMetrics();
				clearDataInMultiselectForRelatedMetrics();
				enterDataForMultiInputTypeRelatedMetrics(data.get("RelatedMetricRHPValue"));
				if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message in Related Metric "
									+ Constants.standardErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error Message in Related Metric");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageExplanationMandatory + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated Mandatory Explanation   Error Message in Related Metric "
									+ Constants.standardErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation  Error Message in Related Metric");
						}
					}
				}
				if (Boolean.parseBoolean(data.get("IsCustomMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.customErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.customErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message in Related Metric "
									+ Constants.customErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error Message in Related Metric");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
//    						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
//    								+ Constants.customErrorMessageExplanationMandatory + "']");
//    						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
//    								+ Constants.customErrorMessageExplanationMandatory + "']")) {
//    							passed("Successfully Validated Mandatory Explanation   Error Message in Related Metric "
//    									+ Constants.customErrorMessageExplanationMandatory);
//    						} else {
//    							failed(driver, "Failed To Validate Mandatory Explanation  Error Message in Related Metric");
//    						}
						waitForElement(txtErrorMessageMandatoryRelated);
						if (txtErrorMessageMandatoryRelated.getText().replaceAll("\\s", "")
								.equals(Constants.customErrorMessageExplanationMandatory.replaceAll("\\s", ""))) {
							passed("Successfully Validated Mandatory Explanation   Error Message "
									+ Constants.customErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation  Error message");
						}
					}
				}
				validateAuditLogForEnteredValueInMetricDetailsRHP();
			}
			clickOnCloseMetricButton();
			validateMetricValueUpdateinMetricGrid(data.get("RHPMetricValue"));
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateBooleanTypeMetricDataEntryAndEvidenceUploadUpdateNextForth() {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForDescInputType();
			enterDataForBooleanInputTypeNextForth(data.get("RHPMetricValue"));
			clickOnMetricName(data.get("MetricName"));
			enterExplanationTextRandom();
			validateAuditLogForEnteredValueInMetricDetailsRHP();
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
			validateUploadedEvidenceInMetricDetaisRHP();
			clickOnCloseMetricButton();
			validateMetricValueUpdateinMetricGrid(data.get("RHPMetricValue"));
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	public void clickOnResetAndSave() {
		clickOn(btnReset, "btnReset");
		clickOn(btnNext, "btnNext");
		sleep(500);
		clickOn(btnPrev, "btnPrev");
	}

	public void ValidateCatalogMetricInputTypeEntriesUpdateNextForth(String inputType) {
		switch (inputType) {
		case "Integer":
			ValidateIntDataTypeMetricDataEntryAndEvidenceUploadNextForth();
			break;
		case "Number":
			ValidateNumDataTypeMetricDataEntryAndEvidenceUploadUpdateNextForth();
			break;
		case "Text":
			ValidateTextDataTypeMetricDataEntryAndEvidenceUploadUpdateNextForth();
			break;
		case "SingleSelect":
			ValidateSingleSelctDataTypeMetricDataEntryAndEvidenceUploadUpdateNextForth();
			break;
		case "MultiSelect":
			ValidateMultiSelctDataTypeMetricDataEntryAndEvidenceUploadUpdateNextForth();
			break;
		case "Table":
			ValidateTableDataTypeMetricDataEntryAndEvidenceUpload();
			break;
		case "Description":
			ValidateDescDataTypeMetricDataEntryAndEvidenceUploadUpdateNextForth();
			break;
		case "Boolean":
			ValidateBooleanTypeMetricDataEntryAndEvidenceUploadUpdateNextForth();
			break;
		default:
		}
	}

	public void validateCatalogStatusForDRCompleteApproval(String statusCompleted, String dataNotAvailablecount,
			String reqdata, String unansweredadata, String validatainData) {
		try {
			Thread.sleep(1000);
			waitForElement(weCompletedCatalog);
			verifyElementAndHighlightIfExists(weCompletedCatalog, "Catalog Metric chips Completed status",
					"Catalog chips Status");
			if (weCompletedCatalog.getText().contains(statusCompleted)) {
				passed("Successfully validated the catalog Completed Statust count as " + weCompletedCatalog.getText());
			} else {
				failed(driver,
						"Failed to validate the catalog Completed Statust count is " + weCompletedCatalog.getText());
			}
			verifyElementAndHighlightIfExists(weDataNotAvailableCatalog,
					"Catalog Metric chips DataNotAvailable Catalog status", "Catalog chips Status");
			if (weDataNotAvailableCatalog.getText().contains(dataNotAvailablecount)) {
				passed("Successfully validated the catalog DataNotAvailable Statust count as "
						+ weDataNotAvailableCatalog.getText());
			} else {
				failed(driver, "Failed to validate the catalog DataNotAvailable Statust count is "
						+ weDataNotAvailableCatalog.getText());
			}
			verifyElementAndHighlightIfExists(weRequiredCatalog, "Catalog Metric chips Required Catalog Catalog status",
					"Catalog chips Status");
			if (weRequiredCatalog.getText().contains(reqdata)) {
				passed("Successfully validated the catalog Required data Statust count as "
						+ weRequiredCatalog.getText());
			} else {
				failed(driver,
						"Failed to validate the catalog Required data Statust count is " + weRequiredCatalog.getText());
			}
			verifyElementAndHighlightIfExists(weUnAnsweredCatalaog,
					"Catalog Metric chips UnAnswered Catalog Catalog status", "Catalog chips Status");
			if (weUnAnsweredCatalaog.getText().contains(unansweredadata)) {
				passed("Successfully validated the catalog UnAnswered data Statust count as "
						+ weUnAnsweredCatalaog.getText());
			} else {
				failed(driver,
						"Failed to validate the UnAnswered  data Statust count is " + weUnAnsweredCatalaog.getText());
			}
			verifyElementAndHighlightIfExists(weValidationsCatalog, "Catalog Metric chips Validations  Catalog status",
					"Catalog chips Status");
			if (weValidationsCatalog.getText().contains(validatainData)) {
				passed("Successfully validated the catalog Validations data Statust count as "
						+ weValidationsCatalog.getText());
			} else {
				failed(driver,
						"Failed to validate the Validations data Statust count is " + weValidationsCatalog.getText());
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateRequiredDataforcatalogClearData() {
		try {
			String[] metricName = { "Number", "Text", "Integer", "MultiSelect" };
			for (int i = 0; i < metricName.length; i++) {
				String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
						+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
				WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
				String classExpand = weExpand.getAttribute("class");
				if (!classExpand.contains("hidden")) {
					clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
				}
				Thread.sleep(3000);
				String xpathMetricValue = "(//span[text()='" + metricName[i]
						+ "']//parent::div//parent::div//parent::div//following-sibling::div//span)[1]";
				clickOnElementWithDynamicXpath(xpathMetricValue, "Metric Value Field");
				if (metricName[i].equals("MultiSelect")) {
					clickOnDataForMultiSelectInputType();
					clearDataInMultiselect();
					clearenterExplanationText();
					deleteUploadedEvidenceInMetricDetailsRHP();
				} else if (metricName[i].equals("Integer")) {
					clickOnDataForIntegerInputType();
					WebElement MetricValueData = driver
							.findElement(By.xpath("(//p[text()='Data']//following-sibling::div//input)[1]"));
					waitForElement(MetricValueData);
					MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
					sleep(1000);
					MetricValueData.sendKeys(Keys.BACK_SPACE);
					clearenterExplanationText();
					deleteUploadedEvidenceInMetricDetailsRHP();
				} else {
					clickOnDataForNumberInputType();
					WebElement MetricValueData = driver
							.findElement(By.xpath("(//p[text()='Data']//following-sibling::div//input)[1]"));
					waitForElement(MetricValueData);
					MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
					sleep(500);
					MetricValueData.sendKeys(Keys.BACK_SPACE);
					clearenterExplanationText();
					deleteUploadedEvidenceInMetricDetailsRHP();
				}
				clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			}
			validateMetricAccordingtoChips("Required","4","Number");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateDataNotAvailableDataCatalogChips() {
		try {
			String[] metricName = { "Number", "Text", "Integer", "MultiSelect" };
			for (int i = 0; i < metricName.length; i++) {
				String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
						+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
				WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
				String classExpand = weExpand.getAttribute("class");
				if (!classExpand.contains("hidden")) {
					clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
				}
				Thread.sleep(3000);
				String xpathMetricValue = "(//span[text()='" + metricName[i]
						+ "']//parent::div//parent::div//parent::div//following-sibling::div//span)[1]";
				clickOnElementWithDynamicXpath(xpathMetricValue, "Metric Value Field");
				clickOnDataNotAvailablecheckBoxTocheck();
				if (metricName[i].equals("Number")) {
					enterExplanationText();
				}
				clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			}
			validateMetricAccordingtoChips("Data not available","4","Integer");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateEnterDataPositiveValuesCatalogChips() {
		try {
			String[] metricName = { "Number", "Text", "Integer", "MultiSelect" };
			for (int i = 0; i < metricName.length; i++) {
				String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
						+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
				WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
				String classExpand = weExpand.getAttribute("class");
				if (!classExpand.contains("hidden")) {
					clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
				}
				Thread.sleep(3000);
				String xpathMetricValue = "(//span[text()='" + metricName[i]
						+ "']//parent::div//parent::div//parent::div//following-sibling::div//span)[1]";
				clickOnElementWithDynamicXpath(xpathMetricValue, "Metric Value Field");
				if (metricName[i].equals("MultiSelect")) {
					clickOnDataNotAvailablecheckBoxToUncheck();
					clickOnDataForMultiSelectInputType();
					clearDataInMultiselect();
					enterDataForMultiInputTypewithoutnextforth("yes");
				} else if (metricName[i].equals("Integer")) {
					deleteUploadedEvidenceInMetricDetailsRHP();
					uploadEvidenceInMetricDetailsRHP();
				} else if (metricName[i].equals("Number")) {
					enterExplanationText();
				} else if (metricName[i].equals("Text")) {
					clickOnDataForIntegerInputType();
					enterDataForIntegerInputTypewithoutnextforth("a");
				}
				clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			}
			validateMetricAccordingtoChips("Completed","4","Text");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateEnterDRFromPortCoenterData() {
		try {
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
			}
			Thread.sleep(3000);
			String xpathMetricValue = "(//span[text()='" + data.get("MetricName")
					+ "']//parent::div//parent::div//parent::div//following-sibling::div//span)[1]";
			clickOnElementWithDynamicXpath(xpathMetricValue, "Metric Value Field");
			if (data.get("MetricName").equals("MultiSelect")) {
				clickOnDataNotAvailablecheckBoxToUncheck();
				clickOnDataForMultiSelectInputType();
				clearDataInMultiselect();
				enterDataForMultiInputTypewithoutnextforth(data.get("RHPMetricValue"));
				clickOnDataForMultiSelectInputTypeForRelatedMetrics();
				clearDataInMultiselectForRelatedMetrics();
				enterDataForMultiInputTypeRelatedMetrics(data.get("RelatedMetricRHPValue"));
			} else {
				clickOnDataNotAvailablecheckBoxToUncheck();
				if (data.get("MetricName").equals("Integer")) {
					clickOnDataForIntegerInputType();
				} else {
					clickOnDataForNumberInputType();
				}
				enterDataForIntegerInputTypewithoutnextforth(data.get("RHPMetricValue"));
				clearenterExplanationText();
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//span[text()='None']//parent::li")
	private WebElement listNone;
	WebElement metricNameNumber;

	public void validateMetricDropDownValidations() {
		try {
			String[] validations = { "Explanation Mandatory", "Expression", "Mandatory Evidence", "Related Metric" };
			for (int i = 0; i < validations.length; i++) {
				clickOn(weValidationsCatalog, "weValidationsCatalog DropDown");
				WebElement drpTasksStatusValue = driver.findElement(
						By.xpath("//*[contains(text(),'" + validations[i] + "')]//following-sibling::span"));
				if (drpTasksStatusValue.getText().contains("1")) {
					passed("Successfully validated " + validations[i] + " validation in metric validation  dropdown");
				} else {
					failed(driver,
							"Failed to validate " + validations[i] + " validation in metric validation dropdown");
				}
				clickOn(drpTasksStatusValue, "drpTasksStatusValue");
				if (validations[i].equals("Explanation Mandatory")) {
					metricNameNumber = driver.findElement(By.xpath("//span[@aria-label='Number']"));
				} else if (validations[i].equals("Expression")) {
					metricNameNumber = driver.findElement(By.xpath("//span[@aria-label='Text']"));
				} else if (validations[i].equals("Mandatory Evidence")) {
					metricNameNumber = driver.findElement(By.xpath("//span[@aria-label='Integer']"));
				} else if (validations[i].equals("Related Metric")) {
					metricNameNumber = driver.findElement(By.xpath("//span[@aria-label='MultiSelect']"));
				}
				if (isElementPresent(metricNameNumber)) {
					passed("Successfully Validated metric name with Validation Method "
							+ metricNameNumber.getAttribute("aria-label"));
				} else {
					failed(driver, "Failed to validate metric name with Validation Method"
							+ metricNameNumber.getAttribute("aria-label"));
				}
			}
			clickOn(weValidationsCatalog, "weValidationsCatalog DropDown");
			clickOn(listNone, "dropNone");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	// -----------------catalog Validations Configuration
	// ----------------------------------------
	String edittopic;

	public void validateEditTopicBefore() {
		edittopic = data.get("TopicName") + generateRandomString(4);
		editTopicName(data.get("TopicName"), edittopic);
		sleep(2000);
	}

	public void validateEditTopicAfter() {
		Actions builder = new Actions(driver);
		builder.moveToElement(btnUploadDone).perform();
//		validateTopicandMetriccounts(0, 0);
		clickOn(btnUploadDone, "btnUploadDone");
		editTopicName(edittopic, data.get("TopicName"));
		sleep(2000);
	}

	public void editTopicNameMultipleTopics() {
		try {
			List<String> topic = new ArrayList<>();
			clickOn(btnExpand, "Expand");
			clickOn(btnConfigureCatalog, "btnConfigureCatalog");
			jsClick(weCustomTopic, "Custom Topic");
			topic.add(data.get("TopicName"));
			for (int i = 0; i < topic.size(); i++) {
				edittopic = topic + generateRandomString(4);
				WebElement listTopicNames = driver.findElement(By.xpath("//span[text()='" + topic + "']"));
				clickOn(listTopicNames, "Clicked on topic Name" + topic);
				enterText(txtTopicName, "Topic Name", edittopic);
			}
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
	}

	public void editTopicName(String topicOldName, String newTopicName) {
		try {
			clickOn(btnExpand, "Expand");
			clickOn(btnConfigureCatalog, "btnConfigureCatalog");
			jsClick(weCustomTopic, "Custom Topic");
			WebElement listTopicNames = driver.findElement(By.xpath("//span[text()='" + topicOldName + "']"));
			Actions builder = new Actions(driver);
			sleep(500);
			scrollToViewElement(btnUploadDone);
			sleep(500);
			builder.moveToElement(btnUploadDone).perform();
			clickOn(listTopicNames, "Clicked on topic Name" + topicOldName);
			txtTopicName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			txtTopicName.sendKeys(Keys.BACK_SPACE);
			txtTopicName.clear();
			enterText(txtTopicName, "Topic Name", newTopicName);
			clickOn(btnUpdate, "btnUpdate");
			driver.navigate().refresh();
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
	}

	public void singletoggleOnCatalogConfig(String toggleName) {
		WebElement wetoggleName = driver
				.findElement(By.xpath("//p[text()='" + toggleName + "']//following-sibling::span/span"));
		String classToggle = wetoggleName.getAttribute("class");
		if (!classToggle.contains("checked")) {
			clickOn(wetoggleName, "Clicked on " + toggleName + "toggle");
		}
	}

	public void clickOnAggregationsrule(String aggregationType) {
		clickOn(drpAggregationRules, "Clicked on drpAggregationRules");
		WebElement aggregationMethod = driver
				.findElement(By.xpath("//li[@role='option'][text()='" + aggregationType + "']"));
		clickOn(aggregationMethod, "Clicked on Aggregation type " + aggregationType);
	}

	@FindBy(xpath = "//*[text()='All']//parent::ul//span//li")
	private WebElement weAllTopic;
	@FindBy(xpath = "//input[@type='search']")
	private WebElement txtSearch;
	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement txtSearchStanderedMetric;
	@FindBy(xpath = "//span[text()='Topics: ']//parent::span")
	private WebElement lblTopicsCount;
	@FindBy(xpath = "//span[text()='Metrics: ']//parent::span")
	private WebElement lblMetricsCount;

	public void validateTopicandMetriccounts(int topicCount, int metricCount) {
		Actions builder = new Actions(driver);
		builder.moveToElement(btnUploadDone).perform();
		verifyElementAndHighlightIfExists(txtSearch, "Search Feild in Catalog Config for in metric label",
				"Search field in catalog Config");
		builder.moveToElement(btnUploadDone).perform();
		verifyElementAndHighlightIfExists(lblTopicsCount, "Topics count in config catalog ", "Topics Count");
		builder.moveToElement(btnUploadDone).perform();
		verifyElementAndHighlightIfExists(lblMetricsCount, "Metrics count in config catalog ", "Metrics Count");
		builder.moveToElement(btnUploadDone).perform();
		WebElement weTopicCount = driver.findElement(
				By.xpath("//span[text()='Topics: ']//following-sibling::span[text()='" + topicCount + "']"));
		if (isElementPresent(weTopicCount)) {
			passed("Succesfully validated Topic Counts as" + topicCount);
		} else {
			failed(driver, "Failed to validateTopic Counts as" + topicCount);
		}
		WebElement weMetricCount = driver.findElement(
				By.xpath("//span[text()='Metrics: ']//following-sibling::span[text()='" + metricCount + "']"));
		if (isElementPresent(weMetricCount)) {
			passed("Succesfully validated Metrics Counts as" + metricCount);
		} else {
			failed(driver, "Failed to Metrics Counts as" + metricCount);
		}
	}

	@FindBy(xpath = "//*[text()='Standards']//following-sibling::span//span")
	private WebElement chkboxSatndaerdTopicLables;
	@FindBy(xpath = "//button[text()='S / I']")
	private WebElement btnStanderedSI;
	@FindBy(xpath = "//button[text()='Select All']")
	private WebElement btnSelectAllStandard;
	@FindBy(xpath = "//button[text()='Reset']")
	private WebElement btnResetStandard;

	public void verifyconfigpagelables_TopicandMetricCounts(String metricTypes, int topicCount, int metricCount) {
		switch (metricTypes) {
		case "All":
			clickOn(weAllTopic, "Click on  All Topic Label in Config Catalog");
			Actions builder = new Actions(driver);
			builder.moveToElement(btnUploadDone).perform();
			validateTopicandMetriccounts(topicCount, metricCount);
			break;
		case "Custom":
			jsClick(weCustomTopic, "Custom Topic");
			validateTopicandMetriccounts(topicCount, metricCount);
			verifyElementAndHighlightIfExists(btnAddTopic, "Add topic Button Catalog Config ", "Add Topic Button");
			verifyElementAndHighlightIfExists(btnAddMetric, "Add metric Button Catalog Config ", "Add metric Button");
			verifyElementAndHighlightIfExists(btnUploadDone, "Close Catalog Config Button Catalog Config ",
					"Close catalog Config Button");
			break;
		case "Standards":
			List<WebElement> weStanderdTopicLabels = driver
					.findElements(By.xpath("//*[text()='Standards']//following-sibling::span//li"));
			for (int i = 0; i < weStanderdTopicLabels.size(); i++) {
				clickOn(weStanderdTopicLabels.get(i), "Clicked on Standared Metrics");
				List<WebElement> weStanderdTopicLabelsChkBox = driver
						.findElements(By.xpath("//*[text()='Standards']//following-sibling::span//span"));
				if (!weStanderdTopicLabelsChkBox.get(i).getAttribute("class").contains("invisible")) {
					passed("Successfully validated check mark on Selected Standered Metric Type");
				} else {
					failed(driver, "Failed to validate check mark on Selected Standered Metric Type");
				}
				jsClick(btnStanderedSI, "btnStanderedSI");
				verifyElementAndHighlightIfExists(txtSearchStanderedMetric,
						"Search Feild in Catalog Config for metric label - Standared",
						"Search field in catalog Config  - Standared");
				verifyElementAndHighlightIfExists(btnSelectAllStandard,
						"Select All Button in Catalog Config in metric label - Standared",
						"Select All Button in catalog Config  - Standared");
				verifyElementAndHighlightIfExists(btnResetStandard,
						"Reset Button in Catalog Config in metric label - Standared",
						"Reset Button in catalog Config  - Standared");
				verifyElementAndHighlightIfExists(btnSaveMetric,
						"Save Button in Catalog Config in metric label - Standared",
						"Save Button in catalog Config  - Standared");
				verifyElementAndHighlightIfExists(btnUploadDone, "Close Catalog Config Button Catalog Config ",
						"Close catalog Config Button");
			}
			break;
		default:
			break;
		}
	}

	@FindBy(xpath = "//*[text()='Metric Updated Successfully!']")
	private WebElement txtToastMessageUpdateMetric;
	@FindBy(xpath = "//button[text()='Add']")
	private WebElement btnAddValidation;
	@FindBy(xpath = "//button[text()='Remove']")
	private WebElement btnRemove;
	@FindBy(xpath = "//div[@id='mui-component-select-tableInputType']")
	private WebElement drpTablInpuType;
	@FindBy(xpath = "//p[text()='Validation Method']//following-sibling::div/div[@aria-disabled='true']")
	private WebElement inputExistValidation;
	@FindBy(xpath = "//button/*[@aria-label='Close']")
	private WebElement btncloseMteric;
	@FindBy(xpath = "//button[text()='Yes']")
	private WebElement yesDeleteButton;
	@FindBy(xpath = "//*[text()='Metric Deleted Successfully']")
	private WebElement txtToastMessageDeleteMetric;

	public void verifyAdd_EditMetricwithMultipleValidations() {
		// add metric
		try {
			waitForElement(weCustomTopic);
			jsClick(weCustomTopic, "Custom Topic");
			String updatedTopicName = edittopic;
			Actions builder = new Actions(driver);
//			verifyconfigpagelables_TopicandMetricCounts(data.get("MetricType"), 1, 0);
//			sleep(500);
			builder.moveToElement(btnUploadDone).perform();
			builder.moveToElement(btnAddMetric).click().build().perform();
//			jsClick(btnAddMetric, "Add Metric");
			verifyIfElementPresent(lblMetricDetails, "Metric Details", "Create Metric");
			sleep(500);
			clickOn(drpInputType, "Input Type dropdown");
			WebElement lstInputType = driver.findElement(
					By.xpath("//div[@id='menu-type']//ul/li[contains(text(),'" + data.get("InputType") + "')]"));
			clickOn(lstInputType, data.get("InputType"));
			if (Boolean.parseBoolean(data.get("Data not available"))) {
				WebElement dataNotAvailableChkboc = driver.findElement(By
						.xpath("//p[text()='Data not available']//parent::div//input[@type='checkbox']//parent::span"));
				clickOn(dataNotAvailableChkboc, "Data not available Enabled Button Data Request");
			}
			if (data.get("InputType").equals("Table")) {
				clickOn(drpTablInpuType, "Table Input Type dropdown");
				// " + data.get("TableInput") + "
				WebElement lstTableInputvalue = driver
						.findElement(By.xpath("//div[@id='menu-tableInputType']//li[text()='Text']"));
				clickOn(lstTableInputvalue, "Table Input Value");
			}
			clickOn(drpTopic, "Topic dropdown");
			WebElement lstTopics = driver
					.findElement(By.xpath("//div[@id='menu-topicId']//li[text()='" + updatedTopicName + "']"));
			clickOn(lstTopics, "Topic Values");
			enterText(txtMetricName, "Mtric Name", data.get("MetricName"));
			clickOn(drpMetricType, "Metric Type");
			WebElement lstMetricTypes = driver.findElement(By
					.xpath("//div[@id='menu-metricType']//ul/li[contains(text(),'" + data.get("MetricTypes") + "')]"));
			jsClick(lstMetricTypes, data.get("MetricTypes"));
			enterText(txtInputOptions, "Input Options", data.get("Input Values"));
			if (data.get("InputType").equals("Integer") || data.get("InputType").equals("Number")) {
				clickOn(drpUnit, "Unit dropdown");
				sleep(1000);
				WebElement lstUnits = driver.findElement(
						By.xpath("//div[@id='menu-metricUoM']//ul/li[contains(text(),'" + data.get("Unit") + "')]"));
				sleep(1000);
				clickOn(lstUnits, "Unit Values");
			}
//			if (data.get("InputType").equals("Number") && data.get("MetricName").equals("Number1")) {
//				singletoggleOnCatalogConfig("Related metric");
//			} else if (data.get("InputType").equals("Number")) {
//				singletoggleOnCatalogConfig("Required");
//				singletoggleOnCatalogConfig("Allow Unit Conversion");
//			}
//			if (data.get("InputType").equals("Integer")) {
//				singletoggleOnCatalogConfig("Allow Unit Conversion");
//			}
//			if (data.get("InputType").equals("SingleSelect")) {
//				singletoggleOnCatalogConfig("Metric Groups");
//			}
//			if (data.get("InputType").equals("MultiSelect")) {
//				singletoggleOnCatalogConfig("Required");
//				singletoggleOnCatalogConfig("Data not available");
//			}
//			if (data.get("InputType").equals("Boolean")) {
//				singletoggleOnCatalogConfig("Explanation");
//			}
//			if (data.get("InputType").equals("Table")) {
//				singletoggleOnCatalogConfig("Fixed Rows");
//			}
//			if (data.get("InputType").equals("Description")) {
//				singletoggleOnCatalogConfig("Data not available");
//			}
//			clickOn(txtInstructions, "Instructions");
//			enterText(txtInstructions, "Instructions", data.get("Instructions"));
//			if (data.get("InputType").equals("Number")) {
//				clickOnAggregationsrule("Average");
//			}
//			if (data.get("InputType").equals("Integer")) {
//				clickOnAggregationsrule("Sum");
//			}
//			if (data.get("InputType").equals("Boolean")) {
//				clickOnAggregationsrule("Any of");
//			}
			verifyElementAndHighlightIfExists(btnCancel, "Cance button is available on mteric Details",
					"Cance button metric");
			clickOn(btnSaveMetric, "Save Metric");
			verifyIfElementPresent(weMsgMetricSavedSuccessfully, "Metric Saved Successfully", "Create Metric");
			clickOn(btnOk, "Ok");
//			sleep(1000);
//			verifyconfigpagelables_TopicandMetricCounts(data.get("MetricType"), 1, 1);
			sleep(500);
			builder.moveToElement(btnUploadDone).perform();
			jsClick(btnUploadDone, "btnUploadDone");
			// ----------------------Configure Validation eith Edit
			// Metric------------------------
			// Method-------------------------------
			String metricValidationMethods = data.get("ValidationMethod");
			if (!metricValidationMethods.isEmpty()) {
				editConfiguration_CatalogRHP_WithMultiplevalidations(updatedTopicName, metricValidationMethods);
			} else {
				WebElement expandTopicupdated = driver.findElement(By.xpath("//span[text()='" + updatedTopicName
						+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
				clickOn(expandTopicupdated, "Expand Topic ==> " + updatedTopicName);
				clickOnMetricName(data.get("MetricName"));
				WebElement chkBoxValue = driver.findElement(By.xpath(
						"//span[text()='Data not available']//parent::span//preceding-sibling::span//input//following-sibling::*"));
				if (chkBoxValue.getAttribute("data-testid").contains("CheckBoxOutlineBlankIcon")) {
					clickOn(chkDataNotAvailable, "Data Not avaialble Check box ");
				}
				if (isElementPresent(chkDataNotAvailable)) {
					passed("Successfully validated data not available checkBox is refleting on RHP for the metric --->   "
							+ data.get("MetricName"));
				} else {
					failed(driver,
							"Failed to validate data not available checkBox is refleting on RHP for the metric --->   "
									+ data.get("MetricName"));
				}
			}
			// ----------------------Deleting and Update Existing Metric
			// ------------------------------------
			validatUpdateMetric(updatedTopicName);
			validateDeleteMetric(editMetricName);
//			clickOn(weAllTopic, "Click on  All Topic Label in Config Catalog");
//			validateTopicandMetriccounts(0, 0);
			// validateDeleteMetric(editMetricName);
//			clickOn(weAllTopic, "Click on  All Topic Label in Config Catalog");
//			validateTopicandMetriccounts(0, 0);
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
	}

	public void editConfiguration_CatalogRHP_WithMultiplevalidations(String updatedTopicName,
			String metricValidationMethods) {
		try {
			String[] metricValidationMethod = metricValidationMethods.split(",");
			for (int i = 0; i < metricValidationMethod.length; i++) {
				clickOn(btnExpand, "Expand");
				clickOn(btnConfigureCatalog, "btnConfigureCatalog");
				jsClick(weCustomTopic, "Custom Topic");
				WebElement expandTopicupdated = driver.findElement(By.xpath("//span[text()='" + updatedTopicName
						+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
				sleep(1000);
				Actions builder = new Actions(driver);
				builder.moveToElement(btnUploadDone).perform();
				clickOn(expandTopicupdated, "Expand Topic ==> " + updatedTopicName);
				WebElement configMetricName = driver
						.findElement(By.xpath("//span[text()='" + data.get("MetricName") + "']"));
				clickOn(configMetricName, "Clicked on Metric in Configuration" + data.get("MetricName"));
				verifyIfElementPresent(lblMetricDetails, "Metric Details", "Create Metric");
				if (Boolean.parseBoolean(data.get("InputValuesAvailabilty"))) {
					txtInputOptions.sendKeys(Keys.chord(Keys.CONTROL, "a"));
					txtInputOptions.sendKeys(Keys.BACK_SPACE);
					enterText(txtInputOptions, "txtInputOptions", data.get("Input Values"));
				}
				sleep(500);
				WebElement myElement = new WebDriverWait(driver, 20)
						.until(ExpectedConditions.visibilityOf(txtInstructions));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
				builder.moveToElement(myElement).click().build().perform();
				clickOn(txtInstructions, "Instructions");
				enterText(txtInstructions, "Instructions", "EditInstructions");
				if (metricValidationMethod[i].equals("Explanation Mandatory")
						|| metricValidationMethod[i].equals("Mandatory Evidence")
						|| metricValidationMethod[i].equals("Explanation mandatory (Data not available selected)")) {
					if (isElementPresent(inputExistValidation)) {
						clickOn(btnRemove, "btnRemove");
					}
					if (metricValidationMethod[i].equals("Explanation mandatory (Data not available selected)")) {
						clickOnDataNotAvailableWithExplanation();
					}
					builder.moveToElement(myElement).click().build().perform();
					clickOn(drpValidationMethod, "Validation Method dropdown");
					WebElement lstExplanationMandatory = driver
							.findElement(By.xpath("//div[@id='menu-validationMethodRule']//ul/li[contains(text(),'"
									+ metricValidationMethod[i] + "')]"));
					jsClick(lstExplanationMandatory, "Validation Method Values");
					if (metricValidationMethod[i].equals("Explanation mandatory (Data not available selected)")) {
						verifyDisabilityofDNA_EXPL_Toggle();
					}
					if (Boolean.parseBoolean(data.get("InputValuesAvailabilty"))) {
						if (data.get("InputType").equals("Boolean")) {
							WebElement drpMetricValue = driver
									.findElement(By.xpath("//div[text()='Metric Value']//following-sibling::div/div"));
							clickOn(drpMetricValue, "drp Validation Method Values");
							WebElement lstMetricValue = driver
									.findElement(By.xpath("//li[text()='" + data.get("Metric Value") + "']"));
							clickOn(lstMetricValue, data.get("Metric Value"));
						} else {
							WebElement drpMetricValue = driver.findElement(By.xpath("//div[@id='multiSelect']"));
							clickOn(drpMetricValue, "drp Validation Method Values");
							WebElement lstMetricValue = driver.findElement(
									By.xpath("//li[@data-value='" + data.get("Metric Value") + "']//span/input"));
							clickOn(lstMetricValue, data.get("Metric Value"));
							lstMetricValue.sendKeys(Keys.ESCAPE);
						}
					}
				} else if (metricValidationMethod[i].trim().equalsIgnoreCase("Expression")) {
					if (isElementPresent(inputExistValidation)) {
						clickOn(btnRemove, "btnRemove");
					}
					clickOn(drpValidationMethod, "Validation Method dropdown");
					WebElement lstExpression = driver.findElement(
							By.xpath("//div[@id='menu-validationMethodRule']//ul/li[contains(text(),'Expression')]"));
					jsClick(lstExpression, "Validation Method Values");
					jsClick(txtExpressionValue, "ExpressionValue");
					enterText(txtExpressionValue, "ExpressionValue", "[a-z]");
//				enterText(txtCustomMessage, "CustomMessage", Constants.inputTypeRequiredErrorMessageText);
				}
				clickOn(btnUpdate, "btnUpdate");
				verifyIfElementPresent(txtToastMessageUpdateMetric, "Metric Updated  Successfully", "Update Metric");
//				clickOn(btncloseMteric, "btncloseMteric");
				waitForElement(btnUploadDone);
				clickOn(btnUploadDone, "btnUploadDone");
				// -----------------------Validation Catalog RHP--------------------------
				validateIConfiguredMetricsWithValidations(data.get("InputType"), updatedTopicName,
						metricValidationMethod[i]);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void validateDeleteMetric(String metricName) {
		WebElement deleteMetric = driver.findElement(By.xpath("//span[text()='" + metricName
				+ "']//parent::div//parent::div//parent::div//following-sibling::div/div/div/*"));
		clickOn(deleteMetric, "Delete Button Metric");
		waitForElement(yesDeleteButton);
		clickOn(yesDeleteButton, "Yes Confirmation Delete Button");
		if (isElementPresent(txtToastMessageDeleteMetric)) {
			passed("Successfully Deleted the metric" + metricName);
		} else {
			failed(driver, "Failed to delete Metric" + metricName);
		}
	}

	public void clickOnDataNotAvailableWithExplanation() {
		String classMetricGroupsToggle = weExplanationToggle.getAttribute("class");
		if (Boolean.parseBoolean(data.get("DNAWithExplanation"))) {
			if (!classMetricGroupsToggle.contains("checked")) {
				clickOn(weExplanationToggle, "weExplanationToggle");
			}
			String classMetricGroupsToggleDNA = weDNAToggle.getAttribute("class");
			if (Boolean.parseBoolean(data.get("Data not available"))) {
				if (!classMetricGroupsToggleDNA.contains("checked")) {
					clickOn(weDNAToggle, "weExplanationToggle");
				}
			}
		}
	}

	public void verifyDisabilityofDNA_EXPL_Toggle() {
		if (weDNAToggle.getAttribute("class").contains("disabled")) {
			passed("Successfully validated DNA Toggle is Disbaled after  selecting DNA validation");
		} else {
			failed(driver, "Failed to validate DNA Toggle is Disbaled after  selecting DNA validation");
		}
		if (weExplanationToggle.getAttribute("class").contains("disabled")) {
			passed("Successfully validated Explanation Toggle is Disbaled after  selecting DNA validation");
		} else {
			failed(driver, "Failed to validate Explanation Toggle is Disbaled after  selecting DNA validation");
		}
	}

	String editMetricName;

	public void validatUpdateMetric(String updatedTopicName) {
		editMetricName = data.get("MetricName") + generateRandomString(4);
		clickOn(btnExpand, "Expand");
		clickOn(btnConfigureCatalog, "btnConfigureCatalog");
		jsClick(weCustomTopic, "Custom Topic");
		WebElement expandTopicupdated = driver.findElement(By.xpath("//span[text()='" + updatedTopicName
				+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
		sleep(1000);
		Actions builder = new Actions(driver);
		builder.moveToElement(btnUploadDone).perform();
		clickOn(expandTopicupdated, "Expand Topic ==> " + updatedTopicName);
		WebElement configMetricName = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName") + "']"));
		clickOn(configMetricName, "Clicked on Metric in Configuration" + data.get("MetricName"));
		txtMetricName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		txtMetricName.sendKeys(Keys.BACK_SPACE);
		enterText(txtMetricName, "Mtric Name", editMetricName);
		clickOn(btnUpdate, "btnUpdate");
		verifyIfElementPresent(txtToastMessageUpdateMetric, "Metric Updated  Successfully", "Update Metric");
		clickOn(btncloseMteric, "btncloseMteric");
		WebElement expandTopicupdated1 = driver.findElement(By.xpath("//span[text()='" + updatedTopicName
				+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
		sleep(1000);
		builder.moveToElement(btnUploadDone).perform();
		clickOn(expandTopicupdated1, "Expand Topic ==> " + updatedTopicName);
		WebElement editconfigMetricName = driver.findElement(By.xpath("//span[text()='" + editMetricName + "']"));
		if (isElementPresent(editconfigMetricName)) {
			passed("Successfully validated updated Metric Name " + editconfigMetricName.getText());
		} else {
			failed(driver, "Failed to validate updated Metric Name " + editconfigMetricName.getText());
		}
	}

	public void validateIConfiguredMetricsWithValidations(String inputType, String updatedTopicName,
			String metricValidationMethod) {
		WebElement expandTopicupdated = driver.findElement(By.xpath("//span[text()='" + updatedTopicName
				+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
		clickOn(expandTopicupdated, "Expand Topic ==> " + updatedTopicName);
		clickOnMetricName(data.get("MetricName"));
		switch (inputType) {
		case "Integer":
			validateintConfigValidations(metricValidationMethod);
			break;
		case "Number":
			validateintConfigValidations(metricValidationMethod);
			break;
		case "Text":
			validateintConfigValidationsText(metricValidationMethod);
			break;
		case "SingleSelect":
			validateSingleSelectConfigValidations(metricValidationMethod);
			break;
		case "MultiSelect":
			validateMultiSelectConfigValidations(metricValidationMethod);
			break;
		case "Table":
			ValidateTableDataTypeMetricDataEntryAndEvidenceUpload();
			break;
		case "Description":
			ValidateDescDataTypeMetricDataEntryAndEvidenceUploadUpdateNextForth();
			break;
		case "Boolean":
			validateBooleanConfigValidations(metricValidationMethod);
			break;
		default:
		}
	}

	public void validateSingleSelectConfigValidations(String metricValidationMethod) {
		try {
			clickOnDataForSingleSelectInputType();
			enterDataSingleSelectType(data.get("Metric Value"));
			clickOnUpdatedMetricCloseButton();
			clickOnMetricName(data.get("MetricName"));
			sleep(500);
			validateintConfigValidationsinRHP(metricValidationMethod);
			clickOnUpdatedMetricCloseButton();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateMultiSelectConfigValidations(String metricValidationMethod) {
		try {
			clickOnDataForMultiSelectInputType();
//			clearDataInMultiselect();
			enterDataForMultiInputType(data.get("Metric Value"));
			clickOnUpdatedMetricCloseButton();
			clickOnMetricName(data.get("MetricName"));
			sleep(500);
			validateintConfigValidationsinRHP(metricValidationMethod);
			clickOnUpdatedMetricCloseButton();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateBooleanConfigValidations(String metricValidationMethod) {
		try {
			clickOnDataForMultiSelectInputType();
			clickOnDataForDescInputType();
			enterDataForBooleanInputType(data.get("Metric Value"));
			clickOnUpdatedMetricCloseButton();
			clickOnMetricName(data.get("MetricName"));
			sleep(500);
			validateintConfigValidationsinRHP(metricValidationMethod);
			clickOnUpdatedMetricCloseButton();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateintConfigValidations(String metricValidationMethod) {
		try {
			clickOnDataForIntegerInputType();
			enterDataForIntegerInputType(data.get("Metric Value"));
			clickOnUpdatedMetricCloseButton();
			clickOnMetricName(data.get("MetricName"));
			sleep(500);
			validateintConfigValidationsinRHP(metricValidationMethod);
			clickOnUpdatedMetricCloseButton();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateintConfigValidationsText(String metricValidationMethod) {
		try {
			clickOnDataForIntegerInputType();
			enterDataForIntegerInputType(data.get("Metric Value"));
			sleep(1000);
			clickOnUpdatedMetricCloseButton();
			clickOnMetricName(data.get("MetricName"));
			sleep(1000);
			validateintConfigValidationsinRHP(metricValidationMethod);
			clickOnUpdatedMetricCloseButton();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void clickOnUpdatedMetricCloseButton() {
		sleep(500);
		//    btnCancel
		clickOn(btnTopCloseMetric, "Button Close");
	}

	public void validateintConfigValidationsinRHP(String metricValidationMethod) {
		if (metricValidationMethod.equals("Explanation Mandatory")) {
			if (Boolean.parseBoolean(data.get("Data not available"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			}
			WebElement standeredErroeMessage = driver.findElement(
					By.xpath("//div[text()='" + Constants.standardErrorMessageExplanationMandatory + "']"));
			if (isElementPresent(standeredErroeMessage)) {
				passed("Successfully validated " + Constants.standardErrorMessageExplanationMandatory
						+ " Error Meassage ");
			} else {
				failed(driver, "Failed to validate " + Constants.standardErrorMessageExplanationMandatory
						+ " Error Meassage ");
			}
		} else if (metricValidationMethod.equals("Mandatory Evidence")) {
			WebElement standeredErroeMessage = driver
					.findElement(By.xpath("//div[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']"));
			if (isElementPresent(standeredErroeMessage)) {
				passed("Successfully validated " + Constants.standardErrorMessageMandatoryEvidence
						+ " Error Meassage ");
			} else {
				failed(driver,
						"Failed to validate " + Constants.standardErrorMessageMandatoryEvidence + " Error Meassage ");
			}
		} else if (metricValidationMethod.equals("Expression")) {
			clickOn(btnSave, "Save Metric");
			WebElement standeredErroeMessage = driver
					.findElement(By.xpath("//div[text()='" + Constants.inputTypeRequiredErrorMessageText + "']"));
			if (isElementPresent(standeredErroeMessage)) {
				passed("Successfully validated " + Constants.inputTypeRequiredErrorMessageText + " Error Meassage ");
			} else {
				failed(driver,
						"Failed to validate " + Constants.inputTypeRequiredErrorMessageText + " Error Meassage ");
			}
		} else if (metricValidationMethod.equals("Explanation mandatory (Data not available selected)")) {
			if (Boolean.parseBoolean(data.get("Data not available"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			}
			WebElement standeredErroeMessage = driver.findElement(
					By.xpath("//div[text()='" + Constants.standardErrorMessageExplanationMandatory + "']"));
			if (isElementPresent(standeredErroeMessage)) {
				passed("Successfully validated " + Constants.standardErrorMessageExplanationMandatory
						+ " Error Meassage ");
			} else {
				failed(driver, "Failed to validate " + Constants.standardErrorMessageExplanationMandatory
						+ " Error Meassage ");
			}
		}
	}

	public void clickOnCatalogInCatalogPage() {
		searchAndSelectCatalogCard(data.get("CatalogName"));
//        			String xpathCatalogName = "//article//*[text()='" + data.get("CatalogName") + "']";
//        			clickOnElementWithDynamicXpath(xpathCatalogName, "Catalog Name");
	}

	public void navigateToConfigureMetricInCatalogPage() {
		try {
			clickOn(btnExpand, "Catalog Config Expand button");
			clickOn(btnConfigureCatalog, "Configure catalog");
			jsClick(weCustomTopic, "Custom Topic");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//button[@aria-label='Upload Data']")
	private WebElement btnUploadData;
	@FindBy(xpath = "//button[@aria-label='Download Catalog']")
	private WebElement btnDownloadCatalog;
	@FindBy(xpath = "//div/li/button[contains(text(),'.csv')]")
	private WebElement btnDownloadCSVFile;

	public void uploadMetricDataInCatalog() {
		try {
			File folder = new File(
					System.getProperty("user.dir") + "\\src\\test\\resources\\DataRequestFiles\\TiffanyCatalogData");
			FileUtils.cleanDirectory(folder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitForElement(btnDownloadCatalog);
		clickOn(btnDownloadCatalog, "download catalog");
		System.out.println(waitForElement(btnDownloadCSVFile));
		jsClick(btnDownloadCSVFile, "CSV file Download");
		GlobalVariables.inputValue = generateRandomNumber(3);
		System.out.println("Uploaded value------>" + GlobalVariables.inputValue);
		sleep(6000);
		String filePath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\DataRequestFiles\\TiffanyCatalogData\\Catalog - Metrics Report.csv";
		CSVDataReader.writeDataIntoCSV(filePath, "Data", "City supply", 1, GlobalVariables.inputValue);
		CSVDataReader.writeDataIntoCSV(filePath, "Data", "Surface water (watercourse)", 1, GlobalVariables.inputValue);
		sleep(3000);
		waitForElement(btnUploadData);
		clickOn(btnUploadData, "Upload data button");
		sleep(3000);
		System.out.println(waitForElement(btnBrowseFiles));
		clickOn(btnBrowseFiles, "Browse files");
		String path = System.getProperty("user.dir");
		ProcessBuilder pb = new ProcessBuilder(path + "\\src\\test\\resources\\DataRequestFiles\\fileUpload.exe",
				filePath);
		try {
			pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sleep(3000);
		waitForElement(btnUpload);
		clickOn(btnUpload, "Upload");
		sleep(3000);
		clickOn(btnClose, "Close button");
		sleep(3000);
		File folder = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\DataRequestFiles\\TiffanyCatalogData");
	}

	List<String> metricsNames = new ArrayList<>();

	public void createMetricDetails() {
		try {
			sleep(1000);
			jsClick(btnAddMetric, "Add Metric");
			verifyIfElementPresent(lblMetricDetails, "Metric Details", "Create Metric");
			sleep(5000);
			clickOn(drpInputType, "Input Type dropdown");
			WebElement lstInputType = driver.findElement(
					By.xpath("//div[@id='menu-type']//ul/li[contains(text(),'" + data.get("InputType") + "')]"));
			clickOn(lstInputType, "Input Type");
			clickOn(drpTopic, "Topic dropdown");
			WebElement lstTopics = driver
					.findElement(By.xpath("//div[@id='menu-topicId']//li[text()='" + data.get("TopicName") + "']"));
			clickOn(lstTopics, "Topic Values");
			// String rndmMeticsName = "Test Metric"+generateRandomNumber(3);
			enterText(txtMetricName, "Mtric Name", data.get("MetricName"));
			metricsNames.add(data.get("MetricName"));
			// enterText(txtMetricName, "Mtric Name", data.get("MetricName"));
			clickOn(drpMetricType, "Metric Type");
			List<WebElement> drpMetricsTypes = driver.findElements(By.xpath("//ul[@role='listbox']//li"));
			if (drpMetricsTypes.size() == 8) {
				passed("Successfully validated total Number of metrics type " + drpMetricsTypes.size());
			} else {
				failed(driver, "Failed to validated total Number of metrics type " + drpMetricsTypes.size());
			}
			for (WebElement ii : drpMetricsTypes) {
				if (verifyElementAndHighlightIfExists(ii, ii.getText(), "Catalog config Page")) {
					passed("Successfulyy validated Metrics Type =>" + ii.getText());
				} else {
					failed(driver, "Failed to validate Metrics Type =>" + ii.getText());
				}
			}
			WebElement lstMetricTypes = driver.findElement(By
					.xpath("//div[@id='menu-metricType']//ul/li[contains(text(),'" + data.get("MetricTypes") + "')]"));
			// clickOn(lstTopics, "Topic Values");
			jsClick(lstMetricTypes, "Topic Values");
			clickOn(btnSaveMetric, "Save Metric");
			verifyIfElementPresent(weMsgMetricSavedSuccessfully, "Metric Saved Successfully", "Create Metric");
			clickOn(btnOk, "Ok");
			Thread.sleep(1000);
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
	}

	@FindBy(xpath = "//span[text()='Metric Name']/parent::div")
	private WebElement headerMetricName;
	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement inputSearch;
	@FindBy(xpath = "//button[contains(text(),'S / I')]")
	private WebElement btnChevronRightIcon;
	@FindBy(xpath = "//span[text()='Sector / Industry']")
	private WebElement lblSectorIndustry;
	@FindBy(xpath = "//label[@aria-label='Infrastructure']/parent::div/parent::div/following-sibling::div/*")
	private WebElement drpStandardMetrics;
	@FindBy(xpath = "//label[@aria-label='Resource Transformation']/parent::div/parent::div/following-sibling::div/*")
	private WebElement drpStandardMetricsResourceTrans;
	@FindBy(xpath = "//label[@aria-label='Infrastructure']/parent::div/parent::div/parent::div/following-sibling::div//label[@aria-label='Water Utilities & Services']/span")
	private WebElement westandardMetricsName;
	@FindBy(xpath = "//label[@aria-label='Resource Transformation']/parent::div/parent::div/parent::div/following-sibling::div//label[@aria-label='Chemicals']/span")
	private WebElement weStandardMetricsResourceTrans;
	@FindBy(xpath = "//button[text()='Select Sector / Industry']")
	private WebElement weSelectSectorIndustry;
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement btnSaveStandardMetrics;
	@FindBy(xpath = "//button[text()='Close']")
	private WebElement btnCLoseConfig;
	@FindBy(xpath = "(//b[text()='All']/parent::ul/span/li/*)[1]")
	private WebElement btnAll;
	@FindBy(xpath = "//span[@aria-label='Previous']")
	private WebElement bnprevious;
	@FindBy(xpath = "//span[@aria-label='Next']")
	private WebElement btnNext1;
	@FindBy(xpath = "//button/*[@data-testid='ChevronRightIcon']")
	private WebElement btnChevronRight;
	@FindBy(xpath = "//*[@aria-label='View Organization Data']")
	private WebElement btnViewOrg;
	@FindBy(xpath = "//div[@ref='eLabel']/span[text()='Topic Name']")
	private WebElement weheadingTopicName;
	@FindBy(xpath = "//*[@data-testid='NavigateNextIcon']")
	private WebElement btnNextPagination;
	@FindBy(xpath = "//*[@data-testid='NavigateBeforeIcon']")
	private WebElement btnPreviousPagination;

	public void validateStandardMetrics() {
		Actions act = new Actions(driver);
		clickOnStandardMetrics();
		// -----------------SASB Validation
		act.moveToElement(drpStandardMetricsResourceTrans).perform();
		clickElement(drpStandardMetricsResourceTrans, "Resources Transformation Standard Metrics");
		act.moveToElement(weStandardMetricsResourceTrans).perform();
		verifyIfElementPresent(weStandardMetricsResourceTrans, "ResourceTrans", "Cataolg config page");
		clickElement(weStandardMetricsResourceTrans, "ResourceTrans");
		clickOn(weSelectSectorIndustry, "Select Sector and industry");
		List<WebElement> configTopicNames = driver.findElements(By.xpath(
				"//div[@ref='eContainer' and @role='rowgroup']//div[@role='row']/div/span//span[@ref='eValue']"));
		if (configTopicNames.size() != 0) {
			passed("Successfully validated first page of standard metrics");
		} else {
			failed(driver, "Failed to validated first page of standard metrics");
		}
		clickElement(btnNextPagination, "Button next");
		configTopicNames = driver.findElements(By.xpath(
				"//div[@ref='eContainer' and @role='rowgroup']//div[@role='row']/div/span//span[@ref='eValue']"));
		if (configTopicNames.size() != 0) {
			passed("Successfully validated Second page of standard metrics");
		} else {
			failed(driver, "Failed to validated Second page of standard metrics");
		}
		clickOn(btnPreviousPagination, "Button Previous");
		WebElement selectStandardTopicResource = driver.findElement(By.xpath(
				"//span[text()='Operational Safety, Emergency Preparedness & Response(RT-CH)']//parent::span//span[@ref='eContracted']/span"));
		act.moveToElement(selectStandardTopicResource).perform();
		clickOn(selectStandardTopicResource, "Operational Safety, Emergency Preparedness & Response(RT-CH)");
		sleep(3000);
		List<WebElement> listOfStandarTopic = driver.findElements(
				By.xpath("//div[@role='row']//div[@col-id='metricName']//div//img/following-sibling::span"));
		for (int i = 0; i < listOfStandarTopic.size(); i++) {
			WebElement opi = driver.findElement(
					By.xpath("(//div[@role='row']//div[@col-id='metricName']//div//img/following-sibling::span)["
							+ (i + 1) + "]"));
			verifyElementAndHighlightIfExists(opi, "Standard Metrics", "Cataolg config page");
		}
		// -------------------
		act.moveToElement(btnAll).click().perform();
		clickOnStandardMetrics();
		act.moveToElement(drpStandardMetrics).click().perform();
		sleep(1000);
		clickElement(drpStandardMetrics, "Infrastructure Standard Metrics");//
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", westandardMetricsName);
		verifyIfElementPresent(westandardMetricsName, "Water Utilities & Services", "Cataolg config page");
		clickElement(westandardMetricsName, "Water utilites & Services");
		clickOn(weSelectSectorIndustry, "Select Sector and industry");
		WebElement selectStandardTopic = driver.findElement(
				By.xpath("//span[text()='Water Supply Resilience(IF-WU)']//parent::span//div[@ref='eWrapper']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", selectStandardTopic);
		clickOn(selectStandardTopic, "Water Supply Resilience");
		sleep(3000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", btnSaveStandardMetrics);
		clickOn(btnSaveStandardMetrics, "Save standard Metrics");
		sleep(2000);
		// jsClick(btnAll, "All Topics");
		act.moveToElement(btnAll).click().perform();
		sleep(1000);
		sleep(1000);
		WebElement drpTopicName = driver.findElement(By.xpath("//span[text()='" + data.get("TopicName")
				+ "']//parent::span//span[@class='ag-group-contracted' and @ref='eContracted']"));
		if (!drpTopicName.getAttribute("class").contains("hidden")) {
			clickElement(drpTopicName, "Topic Exapnded");
		}
		String standardMetricsName = "Water Supply Resilience";
		drpTopicName = driver.findElement(By.xpath("//span[text()='" + standardMetricsName
				+ "']//parent::span//span[@class='ag-group-contracted' and @ref='eContracted']"));
		if (!drpTopicName.getAttribute("class").contains("hidden")) {
			clickElement(drpTopicName, "Topic Exapnded");
		}
		List<String> eddrf = new ArrayList<>();
		List<WebElement> customMetrics = driver.findElements(By.xpath("//span[text()='" + data.get("TopicName")
				+ "']//parent::span//parent::div/parent::div//following-sibling::div//div[@col-id='metricName']//span"));
		for (int i = 0; i < customMetrics.size(); i++) {
			eddrf.add(customMetrics.get(i).getText());
		}
		clickOn(btnCLoseConfig, "Close config");
		clickElement(btnChevronRight, "Expand All Metrics");
		List<WebElement> listOfTopicNamesCatalogViewScreen = driver.findElements(
				By.xpath("//div[@ref='eContainer' and @role='rowgroup']//div//div[@col-id='metricName']"));
		for (int i = 0; i < listOfTopicNamesCatalogViewScreen.size(); i++) {
			if (listOfTopicNamesCatalogViewScreen.get(i).getText().equals(eddrf.get(i))) {
				passed("Successfully validated sort in catalog view screen as same in config screen. View Screen"
						+ listOfTopicNamesCatalogViewScreen.get(i).getText() + " Config Screen" + eddrf.get(i));
			} else {
				failed(driver, "Failed to validated sort in catalog view screen as same in config screen. View Screen"
						+ listOfTopicNamesCatalogViewScreen.get(i).getText() + " Config Screen" + eddrf.get(i));
			}
		}
		clickOn(btnViewOrg, "View Org Data");
		clickElement(btnChevronRight, "Expand All Metrics");
		listOfTopicNamesCatalogViewScreen = driver.findElements(
				By.xpath("//div[@ref='eContainer' and @role='rowgroup']//div//div[@col-id='metricName']"));
		for (int i = 0; i < listOfTopicNamesCatalogViewScreen.size(); i++) {
			if (listOfTopicNamesCatalogViewScreen.get(i).getText().equals(eddrf.get(i))) {
				passed("Successfully validated sort in catalog View Org screen as same in config screen. View Org Screen"
						+ listOfTopicNamesCatalogViewScreen.get(i).getText() + " Config Screen" + eddrf.get(i));
			} else {
				failed(driver,
						"Failed to validated sort in catalog View Org screen as same in config screen. View Org Screen"
								+ listOfTopicNamesCatalogViewScreen.get(i).getText() + " Config Screen" + eddrf.get(i));
			}
		}
		clickOn(btnExpand, "Catalog Config Expand button");
		clickOn(btnConfigureCatalog, "Configure catalog");
		sleep(2000);
		// String standardMetricsName = "Water Supply Resilience";
		WebElement drpTopicName1 = driver.findElement(By.xpath("//span[text()='" + standardMetricsName
				+ "']//parent::span//span[@class='ag-group-contracted' and @ref='eContracted']"));
		if (!drpTopicName1.getAttribute("class").contains("hidden")) {
			clickElement(drpTopicName1, "Topic Exapnded");
		}
		if (sortingStandardMetrics(2, 5)) {
			passed("Successfully validated change Standard metrics Order");
		} else {
			failed(driver, "Failed to validated change Standard metrics Order");
		}
		enterText(inputSearch, "Search", "Nothing Should Come");
		List<WebElement> topicNames = driver
				.findElements(By.xpath("//div[@ref='eContainer'and @role='rowgroup']//div[@col-id='metricName']"));
		if (topicNames.size() == 0) {
			passed("Successfully validated Search Functionality by entering wrong text");
		} else {
			failed(driver, "Failed to validate Search Functionality by entering wrong text");
		}
		inputSearch.clear();
		enterText(inputSearch, "Search", "Volume of recycled water delivered");
		topicNames = driver
				.findElements(By.xpath("//div[@ref='eContainer'and @role='rowgroup']//div[@col-id='metricName']"));
		for (int k = 0; k < topicNames.size(); k++) {
			String parentName = "Volume of recycled water delivered";
			WebElement searchedMetricsName = driver.findElement(By.xpath(
					"//div[@ref='eContainer'and @role='rowgroup']//div[@col-id='metricName']//span[text()='Volume of recycled water delivered']"));
			if (k == 1) {
				String metricNmae = topicNames.get(k).getText();
				System.out.println(metricNmae);
				if (metricNmae.equals(parentName)) {
					passed("Successfully Displayed Searched Metrics");
					verifyElementAndHighlightIfExists(searchedMetricsName, "Searched metrics", "Catalog config Screen");
				} else {
					fail("Failed to Displayed Searched Metrics");
				}
			}
		}
//		jsClick(weCustomTopic, "Custom Topic");
		sleep(2000);
		inputSearch.clear();
		driver.navigate().refresh();
		List<String> topicNamesOrder = new ArrayList<>();
		List<WebElement> beforeSortTopicName = driver.findElements(By.xpath(
				"//div[@col-id='ag-Grid-AutoColumn-topicName']//span[@ref='eValue']//parent::span//parent::div/parent::div[@role='row']"));
		waitForElementList(beforeSortTopicName);
		for (int i = 0; i < beforeSortTopicName.size(); i++) {
			topicNamesOrder.add(beforeSortTopicName.get(i).getAttribute("row-index"));
		}
		clickOn(weheadingTopicName, "Clicked on Topic Name Header");
		clickOn(weheadingTopicName, "Clicked on Topic Name Header");
		List<WebElement> afterSortTopicName = driver.findElements(By.xpath(
				"//div[@col-id='ag-Grid-AutoColumn-topicName']//span[@ref='eValue']//parent::span//parent::div/parent::div[@role='row']"));
		Collections.reverse(topicNamesOrder);
		for (int i = 0; i < afterSortTopicName.size(); i++) {
			if (afterSortTopicName.get(i).getAttribute("row-index").equals(topicNamesOrder.get(i))) {
				passed("Successfully Validated Sort Order of Topic Name in Catalog Config Screen as actual is "
						+ afterSortTopicName.get(i).getText() + "and expected is " + topicNamesOrder.get(i));
			} else {
				failed(driver, "Failed to Validated Sort Order of Topic Name in Catalog Config Screen as actual is "
						+ afterSortTopicName.get(i).getText() + "and expected is " + topicNamesOrder.get(i));
			}
		}
		clickOn(weheadingTopicName, "Clicked on Topic Name Header");
		drpTopicName = driver.findElement(By.xpath("//span[text()='" + data.get("TopicName")
				+ "']//parent::span//span[@class='ag-group-contracted' and @ref='eContracted']"));
		if (!drpTopicName.getAttribute("class").contains("hidden")) {
			clickElement(drpTopicName, "Topic Exapnded");
		}
		drpTopicName = driver.findElement(By.xpath("//span[text()='" + standardMetricsName
				+ "']//parent::span//span[@class='ag-group-contracted' and @ref='eContracted']"));
		if (!drpTopicName.getAttribute("class").contains("hidden")) {
			clickElement(drpTopicName, "Topic Exapnded");
		}
		WebElement customMetrics11 = driver.findElement(By.xpath("(//span[text()='" + data.get("TopicName")
				+ "']//parent::span//parent::div/parent::div//following-sibling::div//div[@col-id='metricName']//span)"));
		clickElement(customMetrics11, "Open Custom Metrics RHP");
		WebElement metricsNameRHP = driver.findElement(By.xpath("//input[@name='metric']"));
		for (int k = 0; k <= eddrf.size(); k++) {
			if (k == 0) {
				if (bnprevious.getAttribute("class").contains("btnDisabled")) {
					passed("Successfully validated previou btn");
				} else {
					failed(driver, "Failed to vali");
				}
			} else if (k == eddrf.size()) {
				if (btnNext1.getAttribute("class").contains("btnDisabled")) {
					passed("Successfully validated Next btn is disabled");
				} else {
					failed(driver, "Failed to validated Next btn is disabled");
				}
			} else {
				clickOn(btnNext1, "");
				String cdsf = metricsNameRHP.getAttribute("value");
				if (cdsf.equals(eddrf.get(k))) {
					passed("Successfully Validated Next button as actual is" + cdsf + "and expected is "
							+ eddrf.get(k));
				} else {
					failed(driver,
							"Failed to validate Next button as actual is" + cdsf + "and expected is " + eddrf.get(k));
				}
			}
		}
	}

	public void clickOnStandardMetrics() {
		try {
			List<WebElement> standardMetrics = driver
					.findElements(By.xpath("//*[text()='Standards']//following-sibling::span/li/img"));
			for (int i = 0; i <= standardMetrics.size(); i++) {
				String srcValue = standardMetrics.get(i).getAttribute("src");
				if (srcValue.contains("SASB")) {
					WebElement xc = driver.findElement(
							By.xpath("(//*[text()='Standards']//following-sibling::span/li/img)[" + (i + 1) + "]"));
					verifyElementAndHighlightIfExists(xc, "Standard Metrics", "Catalog Config page");
					clickElement(xc, "Created Standard Metrics");
					break;
				}
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", btnChevronRightIcon);
			verifyElementAndHighlightIfExists(btnChevronRightIcon, "Chevron Icon", "Cataolg config page");
			clickOn(btnChevronRightIcon, "Standard metrics Open right");
			sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", lblSectorIndustry);
			verifyElementAndHighlightIfExists(lblSectorIndustry, "Sector Industry label", "Cataolg config page");
		} catch (Exception e) {
			failed(driver, "Exception Caught" + " " + e.getMessage());
		}
	}

	@FindBy(xpath = "//p[text()='Required']//following-sibling::span/span[@aria-label='Metric data value mandatory']")
	private WebElement weRequiredToggle;
	@FindBy(xpath = "//p[text()='Data not available']//following-sibling::span/span[@aria-label='Data not available']")
	private WebElement weDNAToggle;
	@FindBy(xpath = "//p[text()='Metric Groups']//following-sibling::span/span[@aria-label='Metric groups']")
	private WebElement weMetricGroupsToggle;
	@FindBy(xpath = "//p[text()='Explanation']//following-sibling::span/span[@aria-label='Explanation']")
	private WebElement weExplanationToggle;
	@FindBy(xpath = "//p[text()='Related metric']//following-sibling::span/span[@aria-label='Related metric']")
	private WebElement weRelatedMetricToggle;
	@FindBy(xpath = "//p[text()='Other']//following-sibling::span/span[@aria-label='Other']")
	private WebElement weOtherToggle;
	@FindBy(xpath = "//p[text()='Fixed Rows']//following-sibling::span/span[@aria-label='Fixed table row']")
	private WebElement weFixedRowsToggle;
	@FindBy(xpath = "//p[text()='Allow Unit Conversion']//following-sibling::span/span[@aria-label='Allow Unit Conversion']")
	private WebElement weAllowUnitConversionToggle;
	@FindBy(xpath = "//div[@id='mui-component-select-tableInputType']")
	private WebElement weTableInputType;
	@FindBy(xpath = "//input[@name='tableInputValue']")
	private WebElement txtTableInputValue;
	@FindBy(xpath = "//input[@name='tableInputLabel']")
	private WebElement txTableInputLabel;
	@FindBy(xpath = "//div[@id='menu-tableInputType']//ul/li")
	private WebElement weListTableInputTypes;
	@FindBy(xpath = "//button/*[@data-testid='VpnKeyIcon']")
	private WebElement btnUniqueKey;

	public void validateMetricDetailsForAllInputTypesInCatalogConfig() {
		try {
			jsClick(btnAddMetric, "Add Metric");
			verifyIfElementPresent(lblMetricDetails, "Metric Details", "Create Metric");
			takeScreenshot(driver);
			clickOn(drpInputType, "Input Type dropdown");
			WebElement lstInputType = driver.findElement(
					By.xpath("//div[@id='menu-type']//ul/li[contains(text(),'" + data.get("InputType") + "')]"));
			clickOn(lstInputType, "Input Type");
			validateToggleOptionsInCatalogConfigMetricDetails();
			if (data.get("InputType").equals("Table")) {
				validateTableAllInputTypes();
			}
			clickOn(drpTopic, "Topic dropdown");
			WebElement lstTopics = driver
					.findElement(By.xpath("//div[@id='menu-topicId']//li[text()='" + data.get("TopicName") + "']"));
			clickOn(lstTopics, "Topic Values");
			enterText(txtMetricName, "Mtric Name", data.get("MetricName"));
			validateMetricTypeOptionsInCatalogConfigMetricDetails();
			if (data.get("InputOption").equals("NA")) {
				// do nothing
			} else {
				if (!btnSaveMetric.isEnabled()) {
					passed("Successfully Validated  Save button is disable without Entering Input Options for "
							+ data.get("InputType") + " Input Type");
				} else {
					failed(driver, "Failed to validate  Save button is disable without Entering Input Options for  "
							+ data.get("InputType") + " Input Type");
				}
				enterText(txtInputOptions, "Input Options", data.get("InputOption"));
				if (btnSaveMetric.isEnabled()) {
					passed("Successfully Validated  Save button is enable after Entering Input Options for "
							+ data.get("InputType") + " Input Type");
				} else {
					failed(driver, "Failed to validate  Save button is enable  after Entering Input Options for  "
							+ data.get("InputType") + " Input Type");
				}
			}
			if (Boolean.parseBoolean(data.get("AllowUnitConversionToggle"))) {
				validateUnitTypeValidationsInCatalogConfigMetricDetails();
			}
			clickOn(txtInstructions, "Instructions");
			enterText(txtInstructions, "Instructions", "AutTestInstructions");
			if (data.get("InputType").equals("Integer") || data.get("InputType").equals("Number")) {
				validateValidationMethodOptionsInCatalogConfigMetricdetails();
				// validateSelectValidationMethodsForNumericValidationsInCatalogConfigMetricDetails();
			} else {
				validateValidationMethodOptionsInCatalogConfigMetricdetails();
				// validateSelectValidationMethodsInCatalogConfigMetricDetails();
			}
			validateValidationAggregationRulesInMetricdetails();
			clickOn(btnCloseIcon, "Metric details close");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateTableAllInputTypes() {
		try {
			waitForElement(weTableInputType);
			clickOn(weTableInputType, "Table Input types");
			List<WebElement> lstTableMetriInputcTypes = driver
					.findElements(By.xpath("//div[@id='menu-tableInputType']//ul/li"));
			for (int i = 0; i < lstTableMetriInputcTypes.size(); i++) {
				if (lstTableMetriInputcTypes.get(i).getText().trim().equals(Constants.tableInputTypes[i])) {
					passed("Successfully validate Table  Metric  input type Option as" + Constants.tableInputTypes[i]);
				} else {
					failed(driver,
							"Failed To validate Table Metric input type option Expected as"
									+ Constants.tableInputTypes[i - 1] + "But Actual is"
									+ lstTableMetriInputcTypes.get(i).getText());
				}
			}
			clickOn(lstTableMetriInputcTypes.get(0), "First Metric Type");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateUnitTypeValidationsInCatalogConfigMetricDetails() {
		try {
			waitForElement(drpUnit);
			if (isElementPresent(drpUnit)) {
				passed("Successfully validated Unit dropdown presence for " + data.get("Input Type")
						+ " Input Type In metric details");
			} else {
				failed(driver,
						"Failed To Validate Unit dropdown  presence for " + data.get("InputType") + " Input Type");
			}
			waitForElement(weAllowUnitConversionToggle);
			scrollTo(weAllowUnitConversionToggle);
			clickOn(weAllowUnitConversionToggle, "Allow Unit conversion Toggle");
			if (!btnSaveMetric.isEnabled()) {
				passed("Successfully Validated For Allow Unit conversion Toggle On Save button is disable without selecting Units dropdown for "
						+ data.get("InputType") + " Input Type");
			} else {
				failed(driver,
						"Failed to validate For Allow Unit conversion Toggle On Save button is disable without selecting Units dropdown for "
								+ data.get("InputType") + " Input Type");
			}
			clickOn(drpUnit, "Unit dropdown");
			WebElement lstUnits = driver
					.findElement(By.xpath("//div[@id='menu-metricUoM']//ul/li[contains(text(),'acre')]"));
			clickOn(lstUnits, "Unit Values");
			if (btnSaveMetric.isEnabled()) {
				passed("Successfully Validated For Allow Unit conversion Toggle On Save button is enable with selecting Units dropdown for "
						+ data.get("InputType") + " Input Type");
			} else {
				failed(driver,
						"Failed to validate For Allow Unit conversion Toggle On Save button is enable with selecting Units dropdown for "
								+ data.get("InputType") + " Input Type");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectUnitInUnitDropdown(String unitName) {
		try {
			sleep(5000);
			waitForElement(drpUnit);
			clickOn(drpUnit, "Unit dropdown");
			WebElement lstUnits = driver
					.findElement(By.xpath("//div[@id='menu-metricUoM']//ul/li[contains(text(),'" + unitName + "')]"));
			clickOn(lstUnits, "Unit Values");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateMetricTypeOptionsInCatalogConfigMetricDetails() {
		try {
			clickOn(drpMetricType, "Metric Type");
			sleep(2000);
			WebElement firstMetrictype = driver.findElement(By.xpath("//div[@id='menu-metricType']//ul/li/em"));
			if (firstMetrictype.getText().trim().equals(Constants.OptionNone)) {
				passed("Successfully validate Metric type Option as" + Constants.OptionNone);
			} else {
				failed(driver, "Failed To validate Metric type option Expected as" + Constants.OptionNone
						+ "But Actual is" + firstMetrictype.getText());
			}
			List<WebElement> lstMetricTypes = driver.findElements(By.xpath("//div[@id='menu-metricType']//ul/li"));
			for (int i = 1; i < lstMetricTypes.size(); i++) {
				if (lstMetricTypes.get(i).getText().trim().equals(Constants.metricTypeOptions[i - 1])) {
					passed("Successfully validate Metric type Option as" + Constants.metricTypeOptions[i - 1]);
				} else {
					failed(driver, "Failed To validate Metric type option Expected as"
							+ Constants.metricTypeOptions[i - 1] + "But Actual is" + lstMetricTypes.get(i).getText());
				}
			}
			clickOn(firstMetrictype, "Metric Type None");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateToggleOptionsInCatalogConfigMetricDetails() {
		try {
			if (Boolean.parseBoolean(data.get("RequiredToggle"))) {
				if (isElementPresent(weRequiredToggle)) {
					passed("Successfully validated Required toggle is presence for " + data.get("InputType")
							+ " Input Type");
				} else {
					failed(driver, "Failed To Validate Required toggle  presence for " + data.get("InputType")
							+ " Input Type");
				}
			}
			if (Boolean.parseBoolean(data.get("DNAToggle"))) {
				if (isElementPresent(weDNAToggle)) {
					passed("Successfully validated DNA  toggle is presence for " + data.get("InputType")
							+ " Input Type");
				} else {
					failed(driver,
							"Failed To Validate DNA toggle  presence for " + data.get("InputType") + " Input Type");
				}
			}
			if (Boolean.parseBoolean(data.get("FixedRowsToggle"))) {
				if (isElementPresent(weFixedRowsToggle)) {
					passed("Successfully validated FixedRowsToggle   is presence for " + data.get("InputType")
							+ " Input Type");
				} else {
					failed(driver, "Failed To Validate FixedRowsToggle   presence for " + data.get("InputType")
							+ " Input Type");
				}
			}
			if (Boolean.parseBoolean(data.get("MetricGroupsToggle"))) {
				if (isElementPresent(weMetricGroupsToggle)) {
					passed("Successfully validated MetricGroupsToggle toggle is presence for " + data.get("InputType")
							+ " Input Type");
				} else {
					failed(driver, "Failed To Validate MetricGroupsToggle  toggle  presence for "
							+ data.get("InputType") + " Input Type");
				}
			}
			if (Boolean.parseBoolean(data.get("RelatedMetricToggle"))) {
				if (isElementPresent(weRelatedMetricToggle)) {
					passed("Successfully validated RelatedMetricToggle toggle is presence for " + data.get("InputType")
							+ " Input Type");
				} else {
					failed(driver, "Failed To Validate RelatedMetricToggle toggle  presence for "
							+ data.get("InputType") + " Input Type");
				}
			}
			if (Boolean.parseBoolean(data.get("OtherToggle"))) {
				if (isElementPresent(weRelatedMetricToggle)) {
					passed("Successfully validated OtherToggle toggle is presence for " + data.get("InputType")
							+ " Input Type");
				} else {
					failed(driver, "Failed To Validate OtherToggle toggle  presence for " + data.get("InputType")
							+ " Input Type");
				}
			}
			if (Boolean.parseBoolean(data.get("ExplanationToggle"))) {
				if (isElementPresent(weExplanationToggle)) {
					passed("Successfully validated Explanation toggle is presence for " + data.get("InputType")
							+ " Input Type");
				} else {
					failed(driver, "Failed To Validate Explanation toggle  presence for " + data.get("InputType")
							+ " Input Type");
				}
			}
			if (Boolean.parseBoolean(data.get("AllowUnitConversionToggle"))) {
				if (isElementPresent(weRelatedMetricToggle)) {
					passed("Successfully validated AllowUnitConversionToggle toggle is presence for "
							+ data.get("InputType") + " Input Type");
				} else {
					failed(driver, "Failed To Validate AllowUnitConversionToggle toggle  presence for "
							+ data.get("InputType") + " Input Type");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateValidationMethodOptionsInCatalogConfigMetricdetails() {
		try {
			if (!(data.get("ValidationMethod").equals("NA"))) {
				scrollTo(drpValidationMethod);
				clickOn(drpValidationMethod, "Validation Method");
				takeScreenshot(driver);
				sleep(2000);
				List<WebElement> lstValidationMethods = driver
						.findElements(By.xpath("//div[@id='menu-validationMethodRule']//ul/li"));
				for (int i = 0; i < lstValidationMethods.size(); i++) {
					if (data.get("ValidationMethod").equals("standardValidationMethods")) {
						if (lstValidationMethods.get(i).getText().trim()
								.equals(Constants.standardValidationMethods[i])) {
							passed("Successfully validate Validation Method Type as"
									+ Constants.standardValidationMethods[i]);
						} else {
							failed(driver,
									"Failed To validate Validation Method Type Expected as"
											+ Constants.standardValidationMethods[i] + "But Actual is"
											+ lstValidationMethods.get(i).getText());
						}
					} else if (data.get("ValidationMethod").equals("numericValidationMethods")) {
						if (lstValidationMethods.get(i).getText().trim()
								.equals(Constants.numericValidationMethods[i])) {
							passed("Successfully validate Validation Method Type as"
									+ Constants.numericValidationMethods[i]);
						} else {
							failed(driver,
									"Failed To validate Validation Method Type Expected as"
											+ Constants.numericValidationMethods[i] + "But Actual is"
											+ lstValidationMethods.get(i).getText());
						}
					} else if (data.get("ValidationMethod").equals("textValidationMethods")) {
						if (lstValidationMethods.get(i).getText().trim().equals(Constants.textValidationMethods[i])) {
							passed("Successfully validate Validation Method Type as"
									+ Constants.numericValidationMethods[i]);
						} else {
							failed(driver,
									"Failed To validate Validation Method Type Expected as"
											+ Constants.numericValidationMethods[i] + "But Actual is"
											+ lstValidationMethods.get(i).getText());
						}
					}
				}
				Actions action = new Actions(driver);
				action.sendKeys(Keys.ESCAPE).build().perform();
				sleep(2000);
				scrollTo(weDNAToggle);
				clickOn(weDNAToggle, "DNA Toggle");
				scrollTo(drpValidationMethod);
				clickOn(drpValidationMethod, "Validation Method");
				WebElement explanationDNAValidationMethod = driver
						.findElement(By.xpath("//div[@id='menu-validationMethodRule']//ul/li[contains(text(),'"
								+ Constants.DNA_ExplanationMethod + "')]"));
				if (isElementPresent(explanationDNAValidationMethod)) {
					passed("Successfully Validated DNA  Explanation Method Option in Validation method options ");
				} else {
					failed(driver, "Failed To validate DNA  Explanation Method Option in Validation method options ");
				}
				sleep(2000);
				action.sendKeys(Keys.ESCAPE).build().perform();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//button[text()='Add']")
	private WebElement btnAddValidationMethod;

	public void validateSelectValidationMethodsInCatalogConfigMetricDetails() {
		try {
			// Need to change for Int and Num
			takeScreenshot(driver);
			List<WebElement> lstValidationMethods = driver
					.findElements(By.xpath("//div[@id='menu-validationMethodRule']//ul/li"));
			for (int i = 0; i < lstValidationMethods.size(); i++) {
				List<WebElement> weAvaialbleValidationMethods = driver
						.findElements(By.xpath("//div[@id='menu-validationMethodRule']//ul/li"));
				clickOn(weAvaialbleValidationMethods.get(0), weAvaialbleValidationMethods.get(0).getText());
				System.out.println(i);
				if (i < lstValidationMethods.size() - 1) {
					scrollTo(btnAddValidationMethod);
					clickOn(btnAddValidationMethod, "Add Validation Method");
					if (isElementPresent(btnAddValidationMethod)) {
						passed("Successfully validated Add button for remaing validation methods");
					} else {
						failed(driver, "Failed To validate  Add button for remaing validation methods ");
					}
					WebElement drpdown = driver.findElement(By.xpath("//input[@value='" + data.get("InputType")
							+ "']//preceding-sibling::div[@id='mui-component-select-validationMethodRule']"));
					clickOn(drpdown, "Dropdown Validation Method");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateSelectValidationMethodsForNumericValidationsInCatalogConfigMetricDetails() {
		try {
			List<WebElement> weAvaialbleValidationMethods = driver
					.findElements(By.xpath("//div[@id='menu-validationMethodRule']//ul/li"));
			clickOn(weAvaialbleValidationMethods.get(0), weAvaialbleValidationMethods.get(0).getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateValidationAggregationRulesInMetricdetails() {
		try {
			scrollTo(drpAggregationRules);
			clickOn(drpAggregationRules, "Aggregation rules");
			sleep(1000);
			takeScreenshot(driver);
			List<WebElement> lstAggregationRules = driver
					.findElements(By.xpath("//div[@id='mui-component-select-aggregation']//following::ul/li"));
			WebElement firtstAggregationOption = driver
					.findElement(By.xpath("//div[@id='mui-component-select-aggregation']//following::ul/li/em"));
			if (firtstAggregationOption.getText().trim().equals(Constants.OptionNone)) {
				passed("Successfully validate Aggregation Rule Option as" + Constants.OptionNone);
			} else {
				failed(driver, "Failed To validate Aggregation Rule option Expected as" + Constants.OptionNone
						+ "But Actual is" + firtstAggregationOption.getText());
			}
			for (int i = 1; i < lstAggregationRules.size(); i++) {
				if (data.get("AggregationRules").equals("booleanAggregationRules")) {
					if (lstAggregationRules.get(i).getText().trim().equals(Constants.booleanAggregationRules[i - 1])) {
						passed("Successfully validate Aggregation Rule as" + Constants.booleanAggregationRules[i - 1]);
					} else {
						failed(driver,
								"Failed To validate Aggregation Rule Expected as"
										+ Constants.booleanAggregationRules[i - 1] + "But Actual is"
										+ lstAggregationRules.get(i).getText());
					}
				} else if (data.get("AggregationRules").equals("numericAggregationRules")) {
					if (lstAggregationRules.get(i).getText().trim().equals(Constants.numericAggregationRules[i - 1])) {
						passed("Successfully validate Aggregation Rule as" + Constants.numericAggregationRules[i - 1]);
					} else {
						failed(driver,
								"Failed To validate Aggregation Rule Expected as"
										+ Constants.numericAggregationRules[i - 1] + "But Actual is"
										+ lstAggregationRules.get(i).getText());
					}
				} else if (data.get("AggregationRules").equals("none")) {
					if (firtstAggregationOption.getText().trim().equals(Constants.OptionNone)) {
						passed("Successfully validate Aggregation Rule Option as" + Constants.OptionNone);
					} else {
						failed(driver, "Failed To validate Aggregation Rule option Expected as" + Constants.OptionNone
								+ "But Actual is" + firtstAggregationOption.getText());
					}
				}
			}
			clickOn(firtstAggregationOption, "None");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectAggregationRule(String aggRule) {
		try {
			if (!aggRule.equals("NA")) {
				scrollTo(drpAggregationRules);
				clickOn(drpAggregationRules, "Aggregation rules");
				sleep(1000);
				takeScreenshot(driver);
				WebElement lstAggregationRule = driver.findElement(By.xpath(
						"//div[@id='mui-component-select-aggregation']//following::ul/li[text()='" + aggRule + "']"));
				clickOn(lstAggregationRule, aggRule);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterTableInputType(String inputType, String inputOption) {
		try {
			waitForElement(weTableInputType);
			clickOn(weTableInputType, "Table Input types");
			WebElement lstTableInputType = driver
					.findElement(By.xpath("//div[@id='menu-tableInputType']//ul/li[text()='" + inputType + "']"));
			clickOn(lstTableInputType, "Table Input Type");
			enterText(txTableInputLabel, "Input Label", inputType);
			enterText(txtTableInputValue, "InputValue", inputOption);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Yogesh
	public void ValidatepublishDRFromPortCoWithCSVFileUpload() {
		try {
			clickOn(btnDownLoadMetricsData, "Download Metrics");
			clickOn(btnListDownLoadMetricsData, "List CSV  Metrics");
			String path = System.getProperty("user.dir");
			String filePath = path
					+ "\\src\\test\\resources\\DataRequestFiles\\EventsFile\\Catalog - Metrics Report.csv";
			GlobalVariables.inputValue = generateRandomNumber(3);
			sleep(3000);
			CSVDataReader.writeDataIntoCSV(filePath, "Data", "Natural Gas", 1, GlobalVariables.inputValue);
			sleep(3000);
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
			}
			clickOn(btnUploadMetricsData, "btnUploadMetricsData");
			sleep(3000);
			System.out.println(waitForElement(btnBrowseFiles));
			clickOn(btnBrowseFiles, "Browse files");
			ProcessBuilder pb = new ProcessBuilder(path + "\\src\\test\\resources\\DataRequestFiles\\fileUpload.exe",
					filePath);
			pb.start();
			sleep(3000);
			waitForElement(btnUpload);
			clickOn(btnUpload, "Upload");
			sleep(3000);
			verifyIfElementPresent(btnUploadMetricsDataStatus, "Metric ==> " + data.get("MetricName"),
					"Upload Metric data");
			clickOn(btnClose, "Close button");
			sleep(3000);
			File folder = new File(
					System.getProperty("user.dir") + "\\src\\test\\resources\\DataRequestFiles\\EventsFile");
			FileUtils.cleanDirectory(folder);
			String xpathMetricValue = "//span[text()='" + data.get("MetricName")
					+ "']//ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']//span";
			WebElement metricValue = driver.findElement(By.xpath(xpathMetricValue));
			if (metricValue.getText().equals(GlobalVariables.inputValue)) {
				passed("Successfully validated Uploaded Metric Value as" + metricValue.getText());
			} else {
				failed(driver, "Failed to validate Uploaded Metric Value Expected as" + GlobalVariables.inputValue
						+ "Actual is" + metricValue.getText());
			}
			clickOn(btnPublish, "publish button");
			clickOn(btnConfirm, "Confirm button");
			if (isElementPresent(msgSuccessDataPublish)) {
				passed("Sucessfully validate DR publish Sucess Message");
			} else {
				failed(driver, "Failed To validate Publish success message");
			}
			clickOn(btnDone, "Done button");
		} catch (Exception e) {
			System.out.println("Exception+++++++++" + e.getMessage());
			ValidatepublishDRFromPortCoWithCSVFileUpload();
		}
	}
	// --------------------------New Code Yogesh

	public void createTopicInCatalogConfigPage(String topicName) {
		scrollTo(btnAddTopic);
		jsClick(btnAddTopic, "btnAddTopic");
		clickOn(drpESGMarkerQA, "drpESGMarker");
		sleep(3000);
		WebElement lstEsgMarker = driver
				.findElement(By.xpath("//ul//li[contains(text(),'" + data.get("ESGMarker") + "')][1]"));
		clickOn(lstEsgMarker, "ESG Marker");
		enterText(txtTopicName, "Topic Name", topicName);
		clickOn(btnSaveTopic, "Save Topic");
		clickOn(btnOk, "OK button");
	}

	public void expandTopicInCatalogPage(String TopicName) {
		try {
			String xpathExpandDataRequest = "//span/span[text()='" + TopicName
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			System.out.println(waitForElement(weExpand));
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void createMetricWithMetricDetailsInCatalogPage(String MetricName) {
		try {
			scrollTo(btnAddMetric);
			jsClick(btnAddMetric, "Add Metric");
			verifyIfElementPresent(lblMetricDetails, "Metric Details", "Create Metric");
			takeScreenshot(driver);
			clickOn(drpInputType, "Input Type dropdown");
			WebElement lstInputType = driver.findElement(
					By.xpath("//div[@id='menu-type']//ul/li[contains(text(),'" + data.get("InputType") + "')]"));
			clickOn(lstInputType, data.get("InputType"));
			if (data.get("InputType").equals("Table")) {
				enterTableInputType(data.get("TableInputType"), data.get("InputOption"));
			}
			sleep(3000);
			waitForElement(drpTopic);
			clickOn(drpTopic, "Topic dropdown");
			WebElement lstTopic = driver
					.findElement(By.xpath("//div[@id='menu-topicId']//li[text()='" + data.get("TopicName") + "']"));
			clickOn(lstTopic, "Topic Values");
			enterText(txtMetricName, "Mtric Name", MetricName);
			if (data.get("InputOption").equals("NA") || data.get("InputType").equals("Table")) {
				// do nothing
			} else {
				enterText(txtInputOptions, "Input Options", data.get("InputOption"));
			}
			if (data.get("Instruction").equals("NA")) {
				// do nothing
			} else {
				clickOn(txtInstructions, "Instructions");
				enterText(txtInstructions, "Instructions", data.get("Instruction"));
			}
			enableToggleOptionsInCatalogMetricDetailsconfigPage();
			if (Boolean.parseBoolean(data.get("AllowUnitConversionToggle"))) {
				selectUnitInUnitDropdown(data.get("UnitName"));
			}
			selectValidationMethodInCatalogMetricDetailsConfigPage(data.get("ValidationMethod"));
			selectMetricGroupsInCatalogMetricDetailsConfigPage(data.get("MetricGroups"));
			selectAggregationRule(data.get("AggregationRule"));
			waitForElement(btnSave);
			clickOn(btnSaveMetric, "Clicked on Save Metric Button");
			clickOn(btnOk, "OK button");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyTogglecursorMessage(String toggleMeassage) {
		WebElement txtToggleCursorMessage = driver.findElement(By.xpath("//div[text()='" + toggleMeassage + "']"));
		if (isElementPresent(txtToggleCursorMessage)) {
			passed("Succesfully validated Required Cursor Meassage as " + toggleMeassage);
		} else {
			failed(driver, "Failed to validate Required Cursor Meassage  " + toggleMeassage);
		}
	}

	public void enableToggleOptionsInCatalogMetricDetailsconfigPage() {
		try {
			if (Boolean.parseBoolean(data.get("RequiredToggle"))) {
				waitForElement(weRequiredToggle);
				String classRequiredToggle = weRequiredToggle.getAttribute("class");
				if (!classRequiredToggle.contains("checked")) {
					clickOn(weRequiredToggle, "Required Toggle");
					verifyTogglecursorMessage("Metric data value mandatory");
				}
			}
			if (Boolean.parseBoolean(data.get("DNAToggle"))) {
				clickOn(weDNAToggle, "DNA Toggle");
				verifyTogglecursorMessage("Data not available");
			}
			String classMetricGroupsToggle = weMetricGroupsToggle.getAttribute("class");
			if (Boolean.parseBoolean(data.get("MetricGroupsToggle"))) {
				if (!classMetricGroupsToggle.contains("checked")) {
					clickOn(weMetricGroupsToggle, "Metric Groups Toggle");
					verifyTogglecursorMessage("Metric groups");
				}
			} else {
				if (classMetricGroupsToggle.contains("checked")) {
					clickOn(weMetricGroupsToggle, "Metric Groups Toggle");
					verifyTogglecursorMessage("Metric groups");
				}
			}
			if (Boolean.parseBoolean(data.get("RelatedMetricToggle"))) {
				clickOn(weRelatedMetricToggle, "Related Metric Toggle");
				verifyTogglecursorMessage("Related metric");
			}
			if (Boolean.parseBoolean(data.get("OtherToggle"))) {
				clickOn(weOtherToggle, "Other Toggle");
				verifyTogglecursorMessage("Other");
			}
			String classExplanationToggle = weExplanationToggle.getAttribute("class");
			if (Boolean.parseBoolean(data.get("ExplanationToggle"))) {
				if (!classExplanationToggle.contains("checked")) {
					clickOn(weExplanationToggle, "Explanation Toggle");
					verifyTogglecursorMessage("Explanation");
				}
			}
			if (Boolean.parseBoolean(data.get("AllowUnitConversionToggle"))) {
				clickOn(weAllowUnitConversionToggle, "Allow Unit conversion Toggle");
				verifyTogglecursorMessage("Allow Unit Conversion");
			}
			System.out.println(data.get("FixedRowsToggle"));
			if (Boolean.parseBoolean(data.get("FixedRowsToggle"))) {
				clickOn(weFixedRowsToggle, "Fixed Rows Toggle");
				verifyTogglecursorMessage("Fixed table row");
			}
			if (Boolean.parseBoolean(data.get("UniqueKey"))) {
				clickOn(btnUniqueKey, "UniqueKey");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isTopicExist(String topicName) {
		String xpathTopicName = "//div[@role='row']//span[contains(text(),'" + topicName + "')]";
		return isElementPresentDynamicXpath(xpathTopicName);
	}

	public boolean isMetricExist(String metricName) {
		expandTopicInCatalog(data.get("TopicName"));
		String xpathMetricName = "//div[@role='row']//span[contains(text(),'" + metricName + "')]";
		return isElementPresentDynamicXpath(xpathMetricName);
	}

	@FindBy(xpath = "//input[@placeholder='Please provide custom message']")
	private WebElement txtValidationCustomMessage;
	@FindBy(xpath = "//div[contains(text(),'Related Metrics')]//following-sibling::div")
	private WebElement lstRelatedMetrics;

	public void selectValidationMethodInCatalogMetricDetailsConfigPage(String validationMethodName) {
		try {
			if (data.get("ValidationMethod").equals("NA")) {
				// Do nothing
			} else {
				scrollTo(drpValidationMethod);
				clickOn(drpValidationMethod, "Validation Method");
				String xpathValidationMethod = "//ul/li[contains(text(),'" + validationMethodName + "')]";
				clickOnElementWithDynamicXpath(xpathValidationMethod, validationMethodName);
				if (data.get("ValidationMethod").equals("Related Metric")) {
					WebElement drpMetricValue = driver.findElement(
							By.xpath("//div[@class='realtedGrid']//input[@value!='Equals']//preceding-sibling::div"));
					clickOn(drpMetricValue, "drp Validation Method Values");
					WebElement lstMetricValue = driver
							.findElement(By.xpath("//li[text()='" + data.get("ValidationMeticValue") + "']"));
					clickOn(lstMetricValue, data.get("Metric Value"));
					if (!data.get("RelatedMetricName").equals("NA")) {
						clickOn(lstRelatedMetrics, "List Related Metrics");
						WebElement drpChildMetric = driver.findElement(By.xpath(
								"//div[contains(text(),'Related Metrics')]//following-sibling::div//following::div[text()='"
										+ data.get("RelatedMetricName") + "']"));
						waitForElement(drpChildMetric);
						clickOn(drpChildMetric, data.get("RelatedMetricName"));
						drpChildMetric.sendKeys(Keys.ESCAPE);
					}
				} else if (data.get("ValidationMethod").equals("Explanation mandatory (Data not available selected)")) {
					// do Nothing
				} else {
					if (data.get("InputType").equals("Boolean")) {
						WebElement drpMetricValue = driver
								.findElement(By.xpath("//div[text()='Metric Value']//following-sibling::div/div"));
						clickOn(drpMetricValue, "drp Validation Method Values");
						WebElement lstMetricValue = driver
								.findElement(By.xpath("//li[text()='" + data.get("ValidationMeticValue") + "']"));
						clickOn(lstMetricValue, data.get("Metric Value"));
					} else if (data.get("InputType").equals("MultiSelect")
							|| data.get("InputType").equals("SingleSelect")) {
						WebElement drpMetricValue = driver.findElement(By.xpath("//div[@id='multiSelect']"));
						clickOn(drpMetricValue, "drp Validation Method Values");
						WebElement lstMetricValue = driver.findElement(
								By.xpath("//li[@data-value='" + data.get("ValidationMeticValue") + "']//span/input"));
						clickOn(lstMetricValue, data.get("Metric Value"));
						lstMetricValue.sendKeys(Keys.ESCAPE);
					}
				}
				if (data.get("ValidationMessage").equals("NA")) { // custom message
					// do Nothing
				} else {
					enterText(txtValidationCustomMessage, "", data.get("ValidationMessage"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//*[contains(text(),'Metric Groups')]//parent::div//following-sibling::div/div[@id='retrieveTag']//input[@type='text']//parent::div")
	private WebElement dropdownMetricGroups;

	public void selectMetricGroupsInCatalogMetricDetailsConfigPage(String metricGroupNames) {
		try {
			if (!metricGroupNames.equals("NA")) {
				waitForElement(dropdownMetricGroups);
				scrollTo(dropdownMetricGroups);
				sleep(2000);
				if (metricGroupNames.contains(",")) {
					String[] arrMetricGroups = metricGroupNames.split(",");
					for (int i = 0; i < arrMetricGroups.length; i++) {
						clickOn(dropdownMetricGroups, "Metric Groups");
						String xpathListMetricGroup = "//div[@id='react-select-2-listbox']//div[text()='"
								+ arrMetricGroups[i] + "']";
						WebElement weMG = driver.findElement(By.xpath(xpathListMetricGroup));
						waitForElement(weMG);
						jsClick(weMG, xpathListMetricGroup);
						new Actions(driver).sendKeys(Keys.ESCAPE).build().perform();
						new Actions(driver).sendKeys(Keys.TAB).build().perform();
					}
				} else {
					clickOn(dropdownMetricGroups, "Metric Groups");
					String metricGroup = metricGroupNames;
					String xpathListMetricGroup = "//div[@id='react-select-2-listbox']//div[text()='" + metricGroup
							+ "']";
					WebElement weMG = driver.findElement(By.xpath(xpathListMetricGroup));
					waitForElement(weMG);
					jsClick(weMG, xpathListMetricGroup);
					new Actions(driver).sendKeys(Keys.ESCAPE).build().perform();
					new Actions(driver).sendKeys(Keys.TAB).build().perform();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOnCloseConfigButton() {
		sleep(500);
		Actions builder = new Actions(driver);
		builder.moveToElement(btnUploadDone).perform();
		clickOn(btnUploadDone, "btnUploadDone");
	}

	public void clickOnResetChildMetricButton(String relatedMetricName) {
		WebElement childReset = driver.findElement(By.xpath("//p[text()='"+relatedMetricName+"']//parent::div//parent::div//parent::div//parent::div//parent::div//span[text()='Reset']"));
		clickOn(childReset, "Child Metric Reset Button");
		clickOn(btnNext, "Button Next Metrics");
		sleep(1500);
		clickOn(btnPrev, "Button Previous Metrics");
		sleep(500);
	}
	public void validateAddedMetricValidations(String SheetName) {
		Data data;
		ArrayList<String> datasets;
		data = new Data(SheetName);
		datasets = data.getDataSets();
		data.setColIndex();
		List<String> logInDetails = new ArrayList();
		for (String dataset : datasets) {
			data.setIndex_Multiple(dataset);
			switch (data.get("InputType")) {
			case "Integer":
				validateint_InputTypeMetricValidations(data.get("MetricName"));
				break;
			case "Number":
				validateNum_InputTypeMetricValidations(data.get("MetricName"));
				break;
			case "Text":
				validateText_InputTypeMetricValidations(data.get("MetricName"));
				break;
			case "SingleSelect":
				validateSingleSelect_InputTypeMetricValidations(data.get("MetricName"));
				break;
			case "MultiSelect":
				validateMultiSelect_InputTypeMetricValidations(data.get("MetricName"));
				break;
			case "Boolean":
				validateBoolean_InputTypeMetricValidations(data.get("MetricName"));
				break;
			}
			datasetEnd();
		}
	}
	

	public void validateAddedMetricValidationsWithMultipleValidations() {
			switch (data.get("InputType")) {
			case "Integer":
				validateint_InputTypeMetricValidations(data.get("MetricName"));
				break;
			case "Number":
				validateNum_InputTypeMetricValidations(data.get("MetricName"));
				break;
			case "Text":
				validateText_InputTypeMetricValidations(data.get("MetricName"));
				break;
			case "SingleSelect":
				validateSingleSelect_InputTypeMetricValidations(data.get("MetricName"));
				break;
			case "MultiSelect":
				validateMultiSelect_InputTypeMetricValidations(data.get("MetricName"));
				break;
			case "Boolean":
				validateBoolean_InputTypeMetricValidations(data.get("MetricName"));
				break;
			}
			datasetEnd();
	}
	
	
	public void enterDataInMetricDetailsRHP() {
		switch (data.get("InputType")) {
		case "Integer":
			validateint_InputTypeMetricValidations(data.get("MetricName"));
			break;
		case "Number":
			enterDataForIntegerInputType(data.get("RHPMetricValue"));
			break;
		case "Text":
			enterDataForIntegerInputType(data.get("RHPMetricValue"));
			break;
		case "SingleSelect":
			enterDataSingleSelectType(data.get("RHPMetricValue"));
			break;
		case "MultiSelect":
			enterDataForMultiInputType(data.get("RHPMetricValue"));
			break;
		case "Boolean":
			enterDataForBooleanInputType(data.get("RHPMetricValue"));
			break;
		}
		datasetEnd();
}
	public void enterDataInMetricDetailsRHP_ChildMetric() {
		switch (data.get("ChildMetricInputType")) {
		case "Integer":
			enterDataForIntegerInputType_ChildMetric(data.get("RelatedMetricName"),data.get("RHPMetricValue"));
			break;
		case "Number":
			enterDataForIntegerInputType_ChildMetric(data.get("RelatedMetricName"),data.get("RHPMetricValue"));
			break;
		case "Text":
			enterDataForIntegerInputType_ChildMetric(data.get("RelatedMetricName"),data.get("RHPMetricValue"));
			break;
		case "SingleSelect":
			enterDataForSingleSelectInputTypeRelatedMetrics(data.get("RelatedMetricRHPValue"));
			break;
		case "MultiSelect":
			enterDataForSingleSelectInputTypeRelatedMetrics(data.get("RelatedMetricRHPValue"));
			break;
		case "Boolean":
			enterDataForSingleSelectInputTypeRelatedMetrics(data.get("RelatedMetricRHPValue"));
			break;
		}
		datasetEnd();
}
	public void validateAddedMetricValidationsWithDifferantMetricValidations() {
			switch (data.get("InputType")) {
			case "Integer":
				validateint_InputTypeMetricValidations(data.get("MetricName"));
				break;
			case "Number":
				validateNum_InputTypeMetricValidations(data.get("MetricName"));
				break;
			case "Text":
				validateText_InputTypeMetricValidations(data.get("MetricName"));
				break;
			case "SingleSelect":
				validateSingleSelect_InputTypeMetricValidations(data.get("MetricName"));
				break;
			case "MultiSelect":
				validateMultiSelect_InputTypeMetricValidations(data.get("MetricName"));
				break;
			case "Boolean":
				validateBoolean_InputTypeMetricValidations(data.get("MetricName"));
				break;
			}
		}
	

	public void validateint_InputTypeMetricValidations(String metricName) {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(metricName);
			clickOnDataForIntegerInputType();
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			} else {
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
					clickOnDataNotAvailablecheckBoxToUncheck();
				}
				clearInputTypeTextBox();
				enterDataForIntegerInputType(data.get("RHPMetricValue"));
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			clickOnMetricName(metricName);
			if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
				if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
					WaitForElementWithDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']");
					if (isElementPresentDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']")) {
						passed("Successfully Validated Mandatory Evidence   Error Message "
								+ Constants.standardErrorMessageMandatoryEvidence + " for the metric "
								+ data.get("MetricName"));
					} else {
						failed(driver, "Failed To Validate Mandatory Evidence Error message");
					}
				}
				if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
					WaitForElementWithDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']");
					if (isElementPresentDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
						passed("Successfully Validated Mandatory Explanation   Error Message "
								+ Constants.standardErrorMessageExplanationMandatory + " for the metric "
								+ data.get("MetricName"));
					} else {
						failed(driver, "Failed To Validate Mandatory Explanation  Error message");
					}
				}
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
		} catch (Exception e) {
			failed(driver, "Failed To Validate Metric: " + e.getMessage());
		}
	}

	public void validateNum_InputTypeMetricValidations(String metricName) {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnMetricName(metricName);
				clickOnDataForNumberInputType();
				clickOnDataNotAvailablecheckBoxTocheck();
			} else if(Boolean.parseBoolean(data.get("IsFormulaValidation"))) {
				enterDataForIntegerInputTypeasPrimaryMetric("Num1","23");
				clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
				enterDataForIntegerInputTypeasPrimaryMetric("Num2","23");
				clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			}else if(Boolean.parseBoolean(data.get("Threshold Percentage ValueError"))) {
					enterDataForIntegerInputTypeasPrimaryMetric("NumThresholdPercentage","55");
					clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
					clickOnMetricName(metricName);
					clickOnDataForNumberInputType();
					WebElement MetricValueData = driver
							.findElement(By.xpath("(//p[text()='Data']//following-sibling::div//input)[1]"));
					waitForElement(MetricValueData);
					MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
					MetricValueData.sendKeys(Keys.BACK_SPACE);
					sleep(500);
					enterText(MetricValueData, "Metric Value Data", "5");
			} else {
				clickOnMetricName(metricName);
				clickOnDataForNumberInputType();
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
					clickOnDataNotAvailablecheckBoxToUncheck();
				}
				clearInputTypeTextBox();
				clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
				clickOnMetricName(metricName);
				clickOnDataForNumberInputType();
				enterDataForIntegerInputType(data.get("RHPMetricValue"));
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			clickOnMetricName(metricName);
			if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
				if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
					WaitForElementWithDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']");
					if (isElementPresentDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']")) {
						passed("Successfully Validated Mandatory Evidence   Error Message "
								+ Constants.standardErrorMessageMandatoryEvidence + " for the metric "
								+ data.get("MetricName"));
					} else {
						failed(driver, "Failed To Validate Mandatory Evidence Error message");
					}
				}
				if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
					WaitForElementWithDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']");
					if (isElementPresentDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
						passed("Successfully Validated Mandatory Explanation   Error Message "
								+ Constants.standardErrorMessageExplanationMandatory + " for the metric "
								+ data.get("MetricName"));
					} else {
						failed(driver, "Failed To Validate Mandatory Explanation  Error message");
					}
				}
				validateThresholdErrorMessage(data.get("Threshold ValueError"),data.get("Threshold Validation Config dara"));
				validateThresholdPercentageErrorMessage(data.get("Threshold Percentage ValueError"),"55","10% (i.e., 49.5)","20% (i.e.,66)");
			}
			if(Boolean.parseBoolean(data.get("IsFormulaValidation"))) {
				validateFormulaParentMetricValue(metricName,"46");
			}
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))
					&& Boolean.parseBoolean(data.get("IsDataRequiredMessage"))) {
				clickOnDataForIntegerInputType();
				clickOnDataNotAvailablecheckBoxToUncheck();
				clickOnDataForIntegerInputType();
				clearInputTypeTextBox();
				WaitForElementWithDynamicXpath("//*[text()='" + Constants.RequiredErrorMessageWithDNA + "']");
				if ((isElementPresentDynamicXpath("//*[text()='" + Constants.RequiredErrorMessageWithDNA + "']"))) {
					passed("Successfully Validated Data Required Error Message As"
							+ Constants.RequiredErrorMessageWithDNA);
				} else {
					failed(driver, "Failed To Validate Data Required  Error message");
				}
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
		} catch (Exception e) {
			failed(driver, "Failed To Validate Metric: " + e.getMessage());
		}
	}

	public void validateThresholdErrorMessage(String IsThresholdError ,String ThresholdconfigValidation) {
		try {
			if (Boolean.parseBoolean(IsThresholdError)) {
				WaitForElementWithDynamicXpath(
						"//*[text()='Threshold Values - metric should be between "+ThresholdconfigValidation+"']");
				if (isElementPresentDynamicXpath(
						"//*[text()='Threshold Values - metric should be between "+ThresholdconfigValidation+"']")) {
					passed("Successfully Validated Threshold Error Message for the metric "
							+ data.get("MetricName") + "as" + ThresholdconfigValidation);
				} else {
					failed(driver, "Failed To Validate Trhreshold  Error message as " + ThresholdconfigValidation);
				}
			}
		} catch (Exception e) {
		}
	}
	public void validateThresholdPercentageErrorMessage(String IsThreshodPercentageldError ,String PreviousValue,String ThresholdconfigValidationMinPerce,String ThresholdconfigValidationMaxPerce) {
		try {
				if (Boolean.parseBoolean(IsThreshodPercentageldError)) {
					WebElement we1 = driver.findElement(By.xpath("//*[text()='Threshold % of Previous Value - metric should not be "+ThresholdconfigValidationMinPerce+" lower than "+PreviousValue+" and should not be "+ThresholdconfigValidationMaxPerce+" higher than "+PreviousValue+"']"));
					waitForElement(we1);
					if (isElementPresentDynamicXpath(
						"//*[text()='Threshold % of Previous Value - metric should not be "+ThresholdconfigValidationMinPerce+" lower than "+PreviousValue+" and should not be "+ThresholdconfigValidationMaxPerce+" higher than "+PreviousValue+"']")) {
					passed("Successfully Validated Threshold Error Message for the metric "
							+ data.get("MetricName") + "as" + we1.getText() );
				} else {
					failed(driver, "Failed To Validate Trhreshold  Error message as " + we1.getText());
				}
			}
		} catch (Exception e) {
		}
	}
	public void validateText_InputTypeMetricValidations(String metricName) {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(metricName);
			clickOnDataForNumberInputType();
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			} else {
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
					clickOnDataNotAvailablecheckBoxToUncheck();
				}
				clearInputTypeTextBox();
				enterDataForIntegerInputType(data.get("RHPMetricValue"));
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			clickOnMetricName(metricName);
			if (isNumber(data.get("RHPMetricValue"))) {
				clearInputTypeTextBox();
				enterDataForIntegerInputType(data.get("RHPMetricValue"));
				clickOn(btnNext, "Button Next Metrics");
				clickOn(btnPrev, "Button Previous Metrics");
				clickOnDataForIntegerInputType();
				WaitForElementWithDynamicXpath("//*[text()='" + Constants.inputTypeRequiredErrorMessageText + "']");
				if (isElementPresentDynamicXpath("//*[text()='" + Constants.inputTypeRequiredErrorMessageText + "']")) {
					passed("Successfully Validated  [a-z] Error message" + Constants.inputTypeRequiredErrorMessageText);
				} else {
					failed(driver, "Failed To Validate [a-z] Error message ");
				}
			}
			if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
				if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
					WaitForElementWithDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']");
					if (isElementPresentDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']")) {
						passed("Successfully Validated Mandatory Evidence   Error Message "
								+ Constants.standardErrorMessageMandatoryEvidence);
					} else {
						failed(driver, "Failed To Validate Mandatory Evidence Error message");
					}
				}
				if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
					WaitForElementWithDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']");
					if (isElementPresentDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
						passed("Successfully Validated Mandatory Explanation   Error Message "
								+ Constants.standardErrorMessageExplanationMandatory);
					} else {
						failed(driver, "Failed To Validate Mandatory Explanation  Error message");
					}
				}
			}
			if (!Boolean.parseBoolean(data.get("DataNotAvailable"))
					&& Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataForIntegerInputType();
				clearInputTypeTextBox();
				WaitForElementWithDynamicXpath("//*[text()='" + Constants.RequiredErrorMessageWithDNA + "']");
				if ((isElementPresentDynamicXpath("//*[text()='" + Constants.RequiredErrorMessageWithDNA + "']"))) {
					passed("Successfully Validated Data Required Error Message As"
							+ Constants.RequiredErrorMessageWithDNA);
				} else {
					failed(driver, "Failed To Validate Data Required  Error message");
				}
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
		} catch (Exception e) {
			failed(driver, "Failed To Validate Metric: " + e.getMessage());
		}
	}

	public void validateSingleSelect_InputTypeMetricValidations(String metricName) {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(metricName);
			clickOnDataForSingleSelectInputType();
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			} else {
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
					clickOn(btnReset, "btnReset");
				}
				enterDataSingleSelectType(data.get("RHPMetricValue"));
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			clickOnMetricName(metricName);
			if (!Boolean.parseBoolean(data.get("IsRelatedMetric"))) {
				if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message "
									+ Constants.standardErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error message");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
							WaitForElementWithDynamicXpath(
									"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']");
							if (isElementPresentDynamicXpath(
									"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
								passed("Successfully Validated Mandatory Explanation   Error Message "
										+ Constants.standardErrorMessageExplanationMandatory);
							} else {
								failed(driver, "Failed To Validate Mandatory Explanation  Error message");
							}
						}
						if (!Boolean.parseBoolean(data.get("IsDNAPresent"))) {
							WaitForElementWithDynamicXpath(
									"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']");
							if (isElementPresentDynamicXpath(
									"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
								passed("Successfully Validated Mandatory Explanation   Error Message "
										+ Constants.standardErrorMessageExplanationMandatory);
							} else {
								failed(driver, "Failed To Validate Mandatory Explanation  Error message");
							}
						}
					}
				}
				if (Boolean.parseBoolean(data.get("IsCustomMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[(text()='" + Constants.customErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message "
									+ Constants.customErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error message");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageExplanationMandatory + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated Mandatory Explanation   Error Message "
									+ Constants.customErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation  Error message");
						}
					}
				}
				if (Boolean.parseBoolean(data.get("IsEmptyMessage"))) {
					if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
						clickOn(btnReset, "btnReset");
					}
					if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
						if (!isElementPresentDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated No Error message");
						} else {
							failed(driver, "Failed To Validate Empty Error message");
						}
						if (!isElementPresentDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated No Error message ");
						} else {
							failed(driver, "Failed To Validate Empty  Error message");
						}
					}
				}
				if (Boolean.parseBoolean(data.get("IsEmptyMessage"))) {
					if (Boolean.parseBoolean(data.get("IsCustomMessage"))) {
						if (!isElementPresentDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated No Error message");
						} else {
							failed(driver, "Failed To Validate Empty Error message");
						}
						if (!isElementPresentDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated No Error message ");
						} else {
							failed(driver, "Failed To Validate Empty  Error message");
						}
					}
				}
			} else {
				if (Boolean.parseBoolean(data.get("RelatedMetricDataNotAvailable"))) {
					clickOnDataNotAvailablecheckBoxTocheckForRelatedMetric();
				} else {
					enterDataForSingleSelectInputTypeRelatedMetrics(data.get("RelatedMetricRHPValue"));
				}
				if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message in Related Metric "
									+ Constants.standardErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error Message in Related Metric");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageExplanationMandatory + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated Mandatory Explanation   Error Message in Related Metric "
									+ Constants.standardErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation  Error Message in Related Metric");
						}
					}
				}
				if (Boolean.parseBoolean(data.get("IsCustomMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.customErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.customErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message in Related Metric "
									+ Constants.customErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error Message in Related Metric");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.customErrorMessageExplanationMandatory + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.customErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated Mandatory Explanation   Error Message in Related Metric "
									+ Constants.customErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation  Error Message in Related Metric");
						}
					}
				}
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
		} catch (Exception e) {
			failed(driver, "Failed To Validate Metric: " + e.getMessage());
		}
	}

	public void validateMultiSelect_InputTypeMetricValidations(String metricName) {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(metricName);
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			} else {
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
					clickOnDataNotAvailablecheckBoxTocheck();
					clickOnDataNotAvailablecheckBoxToUncheck();
				} else {
					clickOnDataNotAvailablecheckBoxToUncheck();
				}
				waitForElement(btnReset);
				clickOn(btnReset, "btnReset");
				clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
				clickOnMetricName(metricName);
				enterDataForMultiInputType(data.get("RHPMetricValue"));
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
			clickOnMetricName(metricName);
			if (!Boolean.parseBoolean(data.get("IsRelatedMetric"))) {
				if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message "
									+ Constants.standardErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error message");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated Mandatory Explanation   Error Message "
									+ Constants.standardErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation  Error message");
						}
					}
				}
				if (Boolean.parseBoolean(data.get("IsCustomMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						// *[contains(text(),'" + Constants.customErrorMessageMandatoryEvidence + "')]
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message "
									+ Constants.customErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error message");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageExplanationMandatory + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated Mandatory Explanation   Error Message "
									+ Constants.customErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation  Error message");
						}
					}
				}
				if (Boolean.parseBoolean(data.get("IsEmptyMessage"))) {
					if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
						if (!isElementPresentDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated No Error message");
						} else {
							failed(driver, "Failed To Validate Empty Error message");
						}
						if (!isElementPresentDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated No Error message ");
						} else {
							failed(driver, "Failed To Validate Empty  Error message");
						}
					}
				}
			} else {
				if (data.get("RelatedMetricInputType").equals("Description")) {
					if (Boolean.parseBoolean(data.get("IsDataRequiredMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.RequiredErrorMessageWithoutDNA + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.RequiredErrorMessageWithoutDNA + "']")) {
							passed("Successfully Validated Required Error message in Related Metric");
						} else {
							failed(driver, "Failed To Validate Required Error message in Related Metric");
						}
					}
				} else {
					clickOnDataForMultiSelectInputTypeForRelatedMetrics();
					if (Boolean.parseBoolean(data.get("RelatedMetricDataNotAvailable"))) {
						clickOnDataNotAvailablecheckBoxTocheckForRelatedMetric();
					} else {
						clearDataInMultiselectForRelatedMetrics();
						enterDataForMultiInputTypeRelatedMetrics(data.get("RelatedMetricRHPValue"));
					}
					if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
						if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
							WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
									+ Constants.standardErrorMessageMandatoryEvidence + "']");
							if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
									+ Constants.standardErrorMessageMandatoryEvidence + "']")) {
								passed("Successfully Validated Mandatory Evidence   Error Message in Related Metric "
										+ Constants.standardErrorMessageMandatoryEvidence);
							} else {
								failed(driver, "Failed To Validate Mandatory Evidence Error Message in Related Metric");
							}
						}
						if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
							WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
									+ Constants.standardErrorMessageExplanationMandatory + "']");
							if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
									+ Constants.standardErrorMessageExplanationMandatory + "']")) {
								passed("Successfully Validated Mandatory Explanation   Error Message in Related Metric "
										+ Constants.standardErrorMessageExplanationMandatory);
							} else {
								failed(driver,
										"Failed To Validate Mandatory Explanation  Error Message in Related Metric");
							}
						}
					}
					if (Boolean.parseBoolean(data.get("IsCustomMessage"))) {
						if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
							WaitForElementWithDynamicXpath(
									"//p[text()='Related Metrics']//following::*[contains(text()='"
											+ Constants.customErrorMessageMandatoryEvidence + "')]");
							if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
									+ Constants.customErrorMessageMandatoryEvidence + "']")) {
								passed("Successfully Validated Mandatory Evidence   Error Message in Related Metric "
										+ Constants.customErrorMessageMandatoryEvidence);
							} else {
								failed(driver, "Failed To Validate Mandatory Evidence Error Message in Related Metric");
							}
						}
						if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
							WaitForElementWithDynamicXpath(
									"//p[text()='Related Metrics']//following::*[contains(text()='"
											+ Constants.customErrorMessageExplanationMandatory + "')]");
							if (isElementPresentDynamicXpath(
									"//p[text()='Related Metrics']//following::*[contains(text()='"
											+ Constants.customErrorMessageExplanationMandatory + "')]")) {
								passed("Successfully Validated Mandatory Explanation   Error Message in Related Metric "
										+ Constants.customErrorMessageExplanationMandatory);
							} else {
								failed(driver,
										"Failed To Validate Mandatory Explanation  Error Message in Related Metric");
							}
						}
					}
				}
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
		} catch (Exception e) {
			failed(driver, "Failed To Validate Metric: " + e.getMessage());
		}
	}

	public void validateBoolean_InputTypeMetricValidations(String metricName) {
		try {
			expandTopicInCatalog(data.get("TopicName"));
			clickOnMetricName(metricName);
			sleep(1000);
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
				clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
				clickOnMetricName(metricName);
			} else {
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
					waitForElement(btnReset);
					clickOn(btnReset, "btnReset");
				}
				enterDataForBooleanInputType(data.get("RHPMetricValue"));
			}
			sleep(1000);
			if (!Boolean.parseBoolean(data.get("IsRelatedMetric"))) {
				if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message "
									+ Constants.standardErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error message");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated Mandatory Explanation   Error Message "
									+ Constants.standardErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation  Error message");
						}
					}
				}
				if (Boolean.parseBoolean(data.get("IsCustomMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message "
									+ Constants.customErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error message");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						WaitForElementWithDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageExplanationMandatory + "']");
						if (isElementPresentDynamicXpath(
								"//*[text()='" + Constants.customErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated Mandatory Explanation   Error Message "
									+ Constants.customErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation Error message");
						}
					}
				}
				if (Boolean.parseBoolean(data.get("IsEmptyMessage"))) {
					clickOn(btnReset, "btnReset");
					sleep(2000);
					if (!isElementPresentDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageMandatoryEvidence + "']")) {
						passed("Successfully Validated No Error message");
					} else {
						failed(driver, "Failed To Validate Empty Error message");
					}
					if (!isElementPresentDynamicXpath(
							"//*[text()='" + Constants.standardErrorMessageExplanationMandatory + "']")) {
						passed("Successfully Validated No Error message ");
					} else {
						failed(driver, "Failed To Validate Empty  Error message");
					}
				}
			} else {
				clickOnDataForRelatedMetricInputType();
				if (Boolean.parseBoolean(data.get("RelatedMetricDataNotAvailable"))) {
					clickOnDataNotAvailablecheckBoxTocheckForRelatedMetric();
				} else {
					enterDataForBooleanInputTypeRelatedMetrics(data.get("RelatedMetricRHPValue"));
				}
				if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message in Related Metric "
									+ Constants.standardErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error Message in Related Metric");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageExplanationMandatory + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.standardErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated Mandatory Explanation   Error Message in Related Metric "
									+ Constants.standardErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation  Error Message in Related Metric");
						}
					}
				}
				if (Boolean.parseBoolean(data.get("IsCustomMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.customErrorMessageMandatoryEvidence + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.customErrorMessageMandatoryEvidence + "']")) {
							passed("Successfully Validated Mandatory Evidence   Error Message in Related Metric "
									+ Constants.customErrorMessageMandatoryEvidence);
						} else {
							failed(driver, "Failed To Validate Mandatory Evidence Error Message in Related Metric");
						}
					}
					if (Boolean.parseBoolean(data.get("IsExplanationErrorMessage"))) {
						WaitForElementWithDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.customErrorMessageExplanationMandatory + "']");
						if (isElementPresentDynamicXpath("//p[text()='Related Metrics']//following::*[text()='"
								+ Constants.customErrorMessageExplanationMandatory + "']")) {
							passed("Successfully Validated Mandatory Explanation   Error Message in Related Metric "
									+ Constants.customErrorMessageExplanationMandatory);
						} else {
							failed(driver, "Failed To Validate Mandatory Explanation  Error Message in Related Metric");
						}
					}
				}
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
		} catch (Exception e) {
			failed(driver, "Failed To Validate Metric: " + e.getMessage());
		}
	}

	public void validateDeleteMultipleMetric(String fileName) {
		try {
			Data data;
			ArrayList<String> datasets;
			data = new Data(fileName);
			datasets = data.getDataSets();
			data.setColIndex();
			for (String dataset : datasets) {
				data.setIndex_Multiple(dataset);
				Actions builder = new Actions(driver);
				builder.moveToElement(btnUploadDone).perform();
				expandTopicInCatalogPage(data.get("TopicName"));
				WebElement deleteMetric = driver.findElement(By.xpath("//span[text()='" + data.get("MetricName")
						+ "']//parent::div//parent::div//parent::div//following-sibling::div/div/div/*"));
				clickOn(deleteMetric, "Delete Button Metric");
				waitForElement(yesDeleteButton);
				clickOn(yesDeleteButton, "Yes Confirmation Delete Button");
				if (isElementPresent(txtToastMessageDeleteMetric)) {
					passed("Successfully Deleted the metric" + data.get("MetricName"));
				} else {
					failed(driver, "Failed to delete Metric" + data.get("MetricName"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void expandTopicCatalogConfigPage(String topicName) {
		jsClick(weCustomTopic, "Custom Topic");
		sleep(1000);
		Actions builder = new Actions(driver);
		builder.moveToElement(btnUploadDone).perform();
		WebElement expandTopicupdated = driver.findElement(By.xpath("//span[text()='" + topicName
				+ "']/parent::span//preceding-sibling::span[@class='ag-group-contracted']/span"));
		WebElement expandclass = driver
				.findElement(By.xpath("//span[text()='" + topicName + "']/parent::span//preceding-sibling::span"));
		String classExpand = expandclass.getAttribute("class");
		if (!classExpand.contains("hidden")) {
			clickOn(expandTopicupdated, "Expand Topic ==> " + topicName);
		}
	}
	public String getColourHexaValueofChips(WebElement wexpath,String attribute) {
			String cssValue = wexpath.getCssValue(attribute);
			String cssValue1 = Color.fromString(cssValue).asHex();
		return cssValue1;
	}
	
	public void validateChips_ErrorsdrpCatalogRegression() {
		String[] chipsCatalog = {"Completed","Data not available","Required","Unanswered"};
		for (int i = 0; i < chipsCatalog.length; i++) {
			WebElement weChips = driver.findElement(By.xpath("//span[contains(text(),'"+chipsCatalog[i]+"')]"));
			String initialColour = getColourHexaValueofChips(weChips,"color");
			sleep(500);
			jsClick(weChips, "Clicked to Select "+ chipsCatalog[i] + " Chip ");
			sleep(500);
			String afterClickColour = getColourHexaValueofChips(weChips,"color");
			if(!initialColour.equals(afterClickColour)) {
				passed("Succesfully validated Changing of "+chipsCatalog[i]+" color initail color code is " + initialColour + " After click colour code is " + afterClickColour);
			}else {
				failed(driver, "Failed to validate Changing of colorof chips " + chipsCatalog[i]);
			}
			jsClick(weChips, "Clicked to UnSelect "+ chipsCatalog[i] + " Chip ");
		}
	}
	
	public void validateplaceofChipsAfterZoomIn() {
		try {
			String afterLocationname = null;
			String initialLocationname = null;
			 String initialLocation;
			String afterZoomLocation;
			Hashtable<String, Dimension> locationofChips = new Hashtable<>();
			Hashtable<String, Rectangle> locationofChipsafterZoomin = new Hashtable<>();
			Hashtable<String, Dimension> initialsizeofChips = new Hashtable<>();
			Hashtable<String, Dimension> sizeofChipsafterZoomin = new Hashtable<>();
			String[] chipsCatalog = {"Completed","Data not available","Required","Unanswered"};
			for (int i = 0; i < chipsCatalog.length; i++) {
				WebElement weChips = driver.findElement(By.xpath("//span[contains(text(),'"+chipsCatalog[i]+"')]"));
				initialLocationname = "Chips"+chipsCatalog[i];
				System.out.println(weChips.getRect().getX());
				System.out.println(weChips.getRect().getY());
				System.out.println(weChips.getRect().getPoint());
//				System.out.println(weChips.getSize());
//				Dimension dimension = weChips.getRect().getDimension();
//			    locationofChips.put(initialLocationname, weChips.getRect().getDimension());
//			    initialsizeofChips.put(initialLocationname, weChips.getSize());
			}
			((JavascriptExecutor) driver).executeScript("document.body.style.zoom='130%';");
			sleep(500);
			for (int i = 0; i < chipsCatalog.length; i++) {
				WebElement weChips = driver.findElement(By.xpath("//span[contains(text(),'"+chipsCatalog[i]+"')]"));
				afterLocationname = "Chips"+chipsCatalog[i];
				System.out.println("---------------------------------");
				System.out.println(weChips.getRect().getX());
				System.out.println(weChips.getRect().getY());
				System.out.println(weChips.getRect().getWidth());
				System.out.println(weChips.getRect().getPoint());
				System.out.println(weChips.getRect().getHeight());
//				System.out.println(weChips.getRect());
//				System.out.println(weChips.getSize());
//				locationofChipsafterZoomin.put(initialLocationname, weChips.getRect());
//				sizeofChipsafterZoomin.put(initialLocationname, weChips.getSize());
				
//			    afterZoomLocation = getColourHexaValueofChips(weChips,"padding-left");
//			    locationofChipsafterZoom.put(afterLocationname, afterZoomLocation);
			}
//			if(locationofChips.equals(locationofChipsafterZoom)) {
//				passed("Successfully Validated that after zoomin the location of chips are not changing");
//			}else {
//				failed(driver, "Failed to  Validate that after zoomin the location of chips are not changing");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void validateChipsCountWithValidations() {
		try {
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
			}
			Thread.sleep(3000);
			String xpathMetricValue = "(//span[text()='" + data.get("MetricName")
					+ "']//parent::div//parent::div//parent::div//following-sibling::div//span)[1]";
			clickOnElementWithDynamicXpath(xpathMetricValue, "Metric Value Field");
			if (data.get("MetricName").equals("MultiSelect")) {
                     clickOn(btnReset, "btnReset");
			} else {
				if (data.get("MetricName").equals("Integer")) {
					clickOnDataNotAvailablecheckBoxTocheck();
				} else if (data.get("MetricName").equals("Number")) {
					clickOnDataForNumberInputType();
					enterDataForIntegerInputTypewithoutnextforth(data.get("RHPMetricValue"));
				}else if (data.get("MetricName").equals("Text")) {
					clickOnDataForNumberInputType();
					enterDataForIntegerInputTypewithoutnextforth("23");
				}
			}
			clickOn(btnCloseInMetricDetails, "Metric Details Close Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	public void validateMetricDropDownValidationInCatalogPage(String validationMethod,String ErrorCounts,String metricName) {
		try {
				clickOn(weValidationsCatalog, "weValidationsCatalog DropDown");
				WebElement drpTasksStatusValue = driver.findElement(
						By.xpath("//*[contains(text(),'" + validationMethod + "')]//following-sibling::span"));
				if (drpTasksStatusValue.getText().contains(ErrorCounts)) {
					passed("Successfully validated Counts of " + validationMethod + " validation in metric validation  dropdown as " + ErrorCounts);
				} else {
					failed(driver,
							"Failed to validate " + validationMethod + " validation in metric validation dropdown Expected Error Counts " + ErrorCounts);
				}
				clickOn(drpTasksStatusValue, "drpTasksStatusValue");
					metricNameNumber = driver.findElement(By.xpath("//span[@aria-label='"+metricName+"']"));
				if (isElementPresent(metricNameNumber)) {
					passed("Successfully Validated metric name with Validation Method "
							+ metricNameNumber.getAttribute("aria-label"));
				} else {
					failed(driver, "Failed to validate metric name with Validation Method"
							+ metricNameNumber.getAttribute("aria-label"));
				}	
			clickOn(weValidationsCatalog, "weValidationsCatalog DropDown");
			clickOn(listNone, "dropNone");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//nav[@aria-label='Device settings']//parent::span//following-sibling::div//span[text()='Clear']")
	private WebElement clearCatalogchips;

	public void validateMetricAccordingtoChips(String chipName, String ChipsCounts, String metricName) {
		try {
			WebElement weChips = driver.findElement(By.xpath("//span[contains(text(),'" + chipName + "')]"));
			jsClick(weChips, "Clicked to Select " + chipName + " Chip ");
			sleep(500);
			if (weChips.getText().contains(ChipsCounts)) {
				passed("Successfully Validated metric count  in Chips Validation as " + weChips.getText());
			} else {
				failed(driver, "Failed to validate metric Count in Chips Count as " + ChipsCounts);
			}
			metricNameNumber = driver.findElement(By.xpath("//span[@aria-label='" + metricName + "']"));
			if (isElementPresent(metricNameNumber)) {
				passed("Successfully Validated metric name with Validation Method "
						+ metricNameNumber.getAttribute("aria-label"));
			} else {
				failed(driver, "Failed to validate metric name with Validation Method"
						+ metricNameNumber.getAttribute("aria-label"));
			}
			sleep(500);
			jsClick(clearCatalogchips, "Clicked to Clear Button validation Filter");
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				passed("Successfully validated After Clicking Clear validations and Chips its going to Initial Stage");
			} else {
				failed(driver,
						"Failed to validate After Clicking Clear validations and Chips its going to Initial Stage");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	// List<String> orderOfTopic = new ArrayList<>();
	public void configPageCatalogSortOrder(String topic) {
		try {
			clickOn(btnExpand, "Catalog Config Expand button");
			clickOn(btnConfigureCatalog, "Configure catalog");
			List<String> nameOfTopic = new ArrayList<>();
			nameOfTopic.add("TestAutTopic");
			// nameOfTopic.add("TestAutTpoic");
			for (int i = 0; i < nameOfTopic.size(); i++) {
				WebElement topicNMe = driver.findElement(By.xpath("//span[text()='" + nameOfTopic.get(i)
						+ "']//parent::span//parent::div/parent::div[@role='row']"));
				GlobalKeys.orderOfTopic.add(topicNMe.getAttribute("row-index"));
			}
			System.out.println(GlobalKeys.orderOfTopic);
			// expandTopicCatalogConfigPage(topic);
		} catch (Exception e) {
			failed(driver, "Exception Caught" + e.getMessage());
		}
	}

	public void validateSortOrderInCatalogConfigScreen() {
		try {
			String xpath1 = "//span[text()='" + data.get("TopicName")
					+ "']//parent::span//span[@class='ag-group-contracted' and @ref='eContracted']";
			WebElement drpTopicName = driver.findElement(By.xpath(xpath1));
			if (!drpTopicName.getAttribute("class").contains("hidden")) {
				sleep(1000);
				clickElement(drpTopicName, "Topic Exapnded");
			}
//			List<String> beforeSorted = new ArrayList<>();
//			for (int w = 1; w <= 4; w++) {
//				WebElement sortOrderText = driver.findElement(By.xpath(
//						"//div[@row-index='" + w + "']//div[@col-id='metricName']//span"));
//				beforeSorted.add(sortOrderText.getText());
//			}
//			System.out.println("Ascending Order of metrics Name ==>"+beforeSorted.toString());
//			clickElement(headerMetricName, "Metrics name Header");
//			clickElement(headerMetricName, "Metrics name Header");
//			List<String> afterSorted = new ArrayList<>();
//			for (int w = 1; w <= 4; w++) {
//				WebElement sortOrderText = driver
//						.findElement(By.xpath("//div[@row-index='" + w + "']//div[@col-id='metricName']//span"));
//				afterSorted.add(sortOrderText.getText());
//			}
//			System.out.println("Descending Order of metrics Name ==>"+afterSorted.toString());
//			Collections.reverse(beforeSorted);
//			if (beforeSorted.equals(afterSorted)) {
//				passed("Successfully validated change metrics order");
//			}
			if (sortingForConfigCatalog(1, 4)) {
				passed("Successfully validated change metrics order");
			} else {
				failed(driver, "Failed to validated change metrics order");
			}
			enterText(inputSearch, "Search", "D_Test_Auto_Metrics_4");
			List<WebElement> topicNames = driver
					.findElements(By.xpath("//div[@ref='eContainer'and @role='rowgroup']//div[@col-id='metricName']"));
			for (int k = 0; k < topicNames.size(); k++) {
				String parentName = "D_Test_Auto_Metrics_4";
				if (k == 1) {
					String metricNmae = topicNames.get(k).getText();
					if (metricNmae.equals(parentName)) {
						passed("Successfully Displayed Searched Metrics");
					} else {
						fail("Failed to Displayed Searched Metrics");
					}
				}
			}
//			
//			
//		}
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
	}
//--------------------------new code

	boolean boolVal;

	public boolean sortingStandardMetrics(int a, int b) {
		try {
			clickElement(headerMetricName, "Metrics name Header");
			List<String> beforeSortOrderValue = new ArrayList<>();
			for (int w = a; w <= b; w++) {
				WebElement sortOrderText = driver
						.findElement(By.xpath("//div[@row-index='" + w + "']//div[@col-id='metricName']//span"));
				beforeSortOrderValue.add(sortOrderText.getText());
			}
			System.out.println("Ascending Order of metrics Name ==>" + beforeSortOrderValue.toString());
			clickElement(headerMetricName, "Metrics name Header");
			List<String> AfterSortOrderValue = new ArrayList<>();
			for (int w = a; w <= b; w++) {
				WebElement sortOrderText = driver
						.findElement(By.xpath("//div[@row-index='" + w + "']//div[@col-id='metricName']//span"));
				AfterSortOrderValue.add(sortOrderText.getText());
			}
			System.out.println("Descending Order of metrics Name ==>" + AfterSortOrderValue.toString());
			Collections.reverse(AfterSortOrderValue);
			boolVal = beforeSortOrderValue.equals(AfterSortOrderValue);
			return boolVal;
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
			return boolVal;
		}
	}
	public boolean navigateToCatalogPage() {
		boolean blnSucc = false;
		try {
			waitForElement(weCatalogsMenu);
			clickOn(weCatalogsMenu, "Cataloge Menu");
			waitForElement(lblCatalogs);
			if (isElementPresent(lblCatalogs)) {
				passed("User Successfully Navigated To Catalog Page");
				blnSucc = true;
			} else {
				failed(driver, "Failed To Navigate To catalog Page");
			}
		} catch (Exception e) {
			failed(driver, "Failed To Navigate To catalog Page, Exception occured: " + e.getMessage());
		}
		return blnSucc;
	}

	public boolean sortingForConfigCatalog(int a, int b) {
		try {
			List<String> beforeSortOrderValue = new ArrayList<>();
			for (int w = a; w <= b; w++) {
				WebElement sortOrderText = driver
						.findElement(By.xpath("//div[@row-index='" + w + "']//div[@col-id='metricName']//span"));
				beforeSortOrderValue.add(sortOrderText.getText());
			}
			System.out.println("Ascending Order of metrics Name ==>" + beforeSortOrderValue.toString());
			clickElement(headerMetricName, "Metrics name Header");
			clickElement(headerMetricName, "Metrics name Header");
			List<String> AfterSortOrderValue = new ArrayList<>();
			for (int w = a; w <= b; w++) {
				WebElement sortOrderText = driver
						.findElement(By.xpath("//div[@row-index='" + w + "']//div[@col-id='metricName']//span"));
				AfterSortOrderValue.add(sortOrderText.getText());
			}
			System.out.println("Descending Order of metrics Name ==>" + AfterSortOrderValue.toString());
			Collections.reverse(AfterSortOrderValue);
			boolVal = beforeSortOrderValue.equals(AfterSortOrderValue);
			return boolVal;
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
			return boolVal;
		}
	}
	public void createMetricWithMetricValidationMethodssInCatalogPage(String MetricName) {
		try {
			String validationMethods = data.get("ValidationMethod");
			String[] validationMethod = validationMethods.split(",");
			for (int i = 0; i < validationMethod.length; i++) {
			scrollTo(btnAddMetric);
			jsClick(btnAddMetric, "Add Metric");
			verifyIfElementPresent(lblMetricDetails, "Metric Details", "Create Metric");
			takeScreenshot(driver);
			clickOn(drpInputType, "Input Type dropdown");
			WebElement lstInputType = driver.findElement(
					By.xpath("//div[@id='menu-type']//ul/li[contains(text(),'" + data.get("InputType") + "')]"));
			clickOn(lstInputType, data.get("InputType"));
			if (data.get("InputType").equals("Table")) {
				enterTableInputType(data.get("TableInputType"), data.get("InputOption"));
			}
			sleep(3000);
			waitForElement(drpTopic);
			clickOn(drpTopic, "Topic dropdown");
			WebElement lstTopic = driver
					.findElement(By.xpath("//div[@id='menu-topicId']//li[text()='" + data.get("TopicName") + "']"));
			clickOn(lstTopic, "Topic Values");
			enterText(txtMetricName, "Mtric Name", MetricName);
			if (data.get("InputOption").equals("NA") || data.get("InputType").equals("Table")) {
				// do nothing
			} else {
				enterText(txtInputOptions, "Input Options", data.get("InputOption"));
			}
			if (data.get("Instruction").equals("NA")) {
				// do nothing
			} else {
				clickOn(txtInstructions, "Instructions");
				enterText(txtInstructions, "Instructions", data.get("Instruction"));
			}
			enableToggleOptionsInCatalogMetricDetailsconfigPage();
			if (Boolean.parseBoolean(data.get("AllowUnitConversionToggle"))) {
				selectUnitInUnitDropdown(data.get("UnitName"));
			}
			selectValidationMethodInCatalogMetricDetailsConfigPage(data.get("ValidationMethod"));
			selectAggregationRule(data.get("AggregationRule"));
			waitForElement(btnSave);
			clickOn(btnSaveMetric, "Clicked on Save Metric Button");
			clickOn(btnOk, "OK button");
			clickOnCloseConfigButton();
			validateAddedMetricValidationsWithDifferantMetricValidations();
			navigateToConfigureMetricInCatalogPage();
			expandTopicInCatalog(data.get("TopicName"));
			validateDeleteMetric(data.get("MetricName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void validateInstructionIncatalogView(String instructions) {
		try {
			WebElement instructionText = driver.findElement(By.xpath("//p[text()='Instructions']//parent::div//span//div//*[contains(text(),'"+instructions+"')]"));
		verifyElementAndHighlightIfExists(instructionText, instructions, "Catalog RHP Instructions");
			if(isElementPresent(instructionText)) {
			passed("Successfully valiodated instruction in the catalog RHP ");
		}else {
			failed(driver, "Failed to valiodate instruction in the catalog RHP ");
		}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
// end
