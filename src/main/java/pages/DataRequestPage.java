package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.google.common.collect.Ordering;

import base.TestBase;
import utilities.Constants;
import utilities.Data;
import utilities.GlobalVariables;

public class DataRequestPage extends TestBase {
	private HomePage HomePage;
	private MenuBarPage MenuBarPage;
	private GoalsPage GoalsPage;
	private CatalogPage CatalogsPage;
	private DataRequestPage DataRequestPage;
	private pages.SignInPage SignInPage;
	private pages.TasksPage TasksPage;
	private AdminPage adminPage;
	private EventsPage eventsPage;
	private Admin_AssigneeSetupConfiguration adminassigneeSetupConfiguration;

	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			Thread.sleep(3000);
			waitForElement(lblDataRequest);
			if (isElementPresent(lblDataRequest)) {
				// passed("User Successfully Navigated To Data Request Page");
			} else {
				failed(driver, "failed To Navigate To Data Request Page");
			}
			// takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	protected DataRequestPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	public MenuBarPage returnMenuPage() {
		return new MenuBarPage(driver, data);
	}

	@FindBy(xpath = "//article//*[contains(text(),'Request')]")
	private WebElement lblDataRequest;
	@FindBy(xpath = "//article/button/*")
	private WebElement btnPlusCreateDR;
	@FindBy(xpath = "//*[@data-testid='ArrowForwardIosIcon']")
	private WebElement btnForwardArrowIcon;
	@FindBy(xpath = "//input[@id='requestNameId']")
	private WebElement txtDRName;
	@FindBy(xpath = "//*[contains(text(),'Select Request Type')]//following-sibling::div")
	private WebElement lstSelectCatalog;
	@FindBy(xpath = "//span[contains(text(),'Data Collection Period')]//following-sibling::div")
	private WebElement lstDataCollectionPeriod;
	@FindBy(xpath = "//button[text()='Next']")
	private WebElement btnNext;
	@FindBy(xpath = "//button[text()='Confirm']")
	private WebElement btnConfirm;
	@FindBy(xpath = "//button[text()='Cancel']/parent::div/following-sibling::div/button")
	private WebElement btnConfirmDR;
	@FindBy(xpath = "//button[text()='Close']")
	private WebElement btnClose;
	@FindBy(xpath = "//button[text()='Back']")
	private WebElement btnBack;
	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement btnCancel;
	@FindBy(xpath = "//button[text()='Create']")
	private WebElement btnCreate;
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement btnSave;
	@FindBy(xpath = "//button[text()='Add All']")
	private WebElement btnAddAll;
	@FindBy(xpath = "//button[text()='Submit']")
	private WebElement btnSubmit;
	@FindBy(xpath = "//span[text()='Period']//parent::div//following-sibling::div//*[@data-testid='ArrowDropDownIcon']")
	private WebElement lstPeriodId;
	@FindBy(xpath = "//span[(text(),'Due Date')]//following::input")
	private WebElement txtDueDate;
	@FindBy(xpath = "//span[text()='Overview and Instructions']//parent::div/div")
	private WebElement txtOverviewAndInstructions;
	@FindBy(xpath = "//span[text()='Add Instructions']//parent::div//following::div[@contenteditable='true']")
	private WebElement txtAddInstructions;
	private WebElement btnIntiateWorkFlow;
	@FindBy(xpath = "//div[@aria-label='Workflow Monitor']")
	private WebElement btnWorkFlowMonitor;
	@FindBy(xpath = "//div[text()='Initiated Workflow Successfully!']")
	private WebElement msgSucessIntiateWorkFlow;
	@FindBy(xpath = "//div[(@col-id,'status')]//div[(text(),'Completed')]")
	private WebElement eleWorkflowStatusCompleted;
	@FindBy(xpath = "//div[(@col-id,'status')]//div[(text(),'In progress')]")
	private WebElement eleWorkflowStatusInProgress;
	//article/div[(text(),'Requests Completed:')]
	@FindBy(xpath = "//button[contains(text(),'Completed')]")
	private WebElement eleDRrequestCompletedPercentage;
	//article/div[(text(),'Requests In Progress')]
	@FindBy(xpath = "//button[contains(text(),'In progress')]")
	private WebElement eleDRrequestInProgressPercentage;
	@FindBy(xpath = "//div[@col-id='dataRequestName' and @role!='columnheader']//a")
	private WebElement lnkDRNameInWorkFlowMonitor;
	@FindBy(xpath = "//div[@col-id='dataRequestName' and @role!='columnheader']//div")
	private WebElement eleDRNameInWorkFlowMonitor;
	@FindBy(xpath = "//div[@col-id='companyName' and @role!='columnheader']")
	private WebElement eleOrgNameInWorkFlowMonitor;

	public void createDataRequestInDataRequestPage_old() {
		try {
			waitForElement(btnPlusCreateDR);
			clickOn(btnPlusCreateDR, "CreateData Request PlusIcon");
			clickOn(btnForwardArrowIcon, "Forward Arrow Icon");
			clickOn(btnForwardArrowIcon, "Forward Arrow Icon");
			clickOn(btnForwardArrowIcon, "Forward Arrow Icon");
			GlobalVariables.dataRequestName = "TestAutDRrand_" + generateRandomString(3) + generateRandomNumber(3);
			enterText(txtDRName, "Data RequestName", GlobalVariables.dataRequestName);
			clickOn(lstSelectCatalog, "Select CatalogName");
			GlobalVariables.catalogName = data.get("CatalogName");
			String xpathCatalog = "//ul/li[text()='" + data.get("CatalogName") + "']";
			System.out.println(WaitForElementWithDynamicXpath(xpathCatalog));
			System.out.println(xpathCatalog);
			clickOnElementWithDynamicXpath(xpathCatalog, "CatalogName");
			clickOn(btnNext, "Next Button");
			enterText(txtDueDate, data.get("DueDate"), "Due date");
			GlobalVariables.dueDate = dueDate.getAttribute("value");
			clickOn(btnNext, "Next Button");
			waitForElement(txtOverviewAndInstructions);
			enterText(txtOverviewAndInstructions, "OverViewInstructions", "Catalog Metric DR Flow");
			enterText(txtOverviewAndInstructions, "OverViewInstructions", " Test Instructions");
			clickOn(btnCreate, "Create Button");
			String xpathTopicNamechkbox = "//span[text()= '" + data.get("TopicName")
					+ "']//preceding-sibling::span[@ref='eCheckbox']//input";
			System.out.println(WaitForElementWithDynamicXpath(xpathTopicNamechkbox));
			WaitForElementWithDynamicXpath(xpathTopicNamechkbox);
			clickOnElementWithDynamicXpath(xpathTopicNamechkbox, "TopicName CheckBox");
			// clickOn(btnAddAll, "Add All button");
			clickOn(btnSave, "Save button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	//
	public void createDataRequestInDataRequestPage() {
		try {
			waitForElement(btnPlusCreateDR);
			clickOn(btnPlusCreateDR, "CreateData Request PlusIcon");
			GlobalVariables.dataRequestName = "TestAutDRrand_" + generateRandomString(3) + generateRandomNumber(3);
			enterText(txtDRNameLatest, "Data RequestName", GlobalVariables.dataRequestName);
			clickOn(lstSelectCatalog, "Select CatalogName");
			GlobalVariables.catalogName = data.get("CatalogName");
			String xpathCatalog = "//ul/li[text()='" + data.get("CatalogName") + "']";
			System.out.println(WaitForElementWithDynamicXpath(xpathCatalog));
			System.out.println(xpathCatalog);
			clickOnElementWithDynamicXpath(xpathCatalog, "CatalogName");
			//clickOn(lstSelectCatalog, "Select CatalogName");
			String xpathPeriod = "//ul/li[text()='" + data.get("PeriodDesc") + "']";
			clickOnElementWithDynamicXpath(xpathPeriod, data.get("PeriodDesc"));
			enterText(txtAddInstructions, "Add Instructions", " AutTest Instructions");
			clickOn(btnNext, "Next Button");
			String xpathTopicNamechkbox = "//span[text()= '" + data.get("TopicName")
					+ "']//preceding-sibling::span[@ref='eCheckbox']//input";
			System.out.println(WaitForElementWithDynamicXpath(xpathTopicNamechkbox));
			WaitForElementWithDynamicXpath(xpathTopicNamechkbox);
			clickOnElementWithDynamicXpath(xpathTopicNamechkbox, "TopicName CheckBox");
			clickOn(btnNext, "Next Button");
			String xpathOrgNamechkbox = "//span[text()= '" + data.get("OrgName")
					+ "']//preceding-sibling::div/div[@ref='eCheckbox']//input";
			clickOnElementWithDynamicXpath(xpathOrgNamechkbox, "organization");
			clickOn(btnNext, "Next Button");
			waitForElement(btnConfirm);
			clickOn(btnConfirm, "Confirm Button");
			clickOn(btnConfirmDR, "Confirm Button");
			waitForElement(btnClose);
			clickOn(btnClose, "Close Button");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createDataRequestInDataRequestPageWithAllTopics() {
		try {
			waitForElement(btnPlusCreateDR);
			clickOn(btnPlusCreateDR, "CreateData Request PlusIcon");
			clickOn(btnForwardArrowIcon, "Forward Arrow Icon");
			clickOn(btnForwardArrowIcon, "Forward Arrow Icon");
			clickOn(btnForwardArrowIcon, "Forward Arrow Icon");
			GlobalVariables.dataRequestName = "TestAutDRrand_" + generateRandomString(2) + generateRandomNumber(3);
			enterText(txtDRName, "Data RequestName", GlobalVariables.dataRequestName);
			clickOn(lstSelectCatalog, "Select CatalogName");
			GlobalVariables.catalogName = data.get("CatalogName");
			String xpathCatalog = "//ul/li[text()='" + data.get("CatalogName") + "']";
			System.out.println(xpathCatalog);
			clickOnElementWithDynamicXpath(xpathCatalog, "CatalogName");
			clickOn(btnNext, "Next Button");
			enterText(txtDueDate, data.get("DueDate"), "Due date");
			clickOn(btnNext, "Next Button");
			enterText(txtOverviewAndInstructions, "OverViewInstructions", " Test Instructions");
			clickOn(btnCreate, "Create Button");
			waitForElement(btnAddAll);
			clickOn(btnAddAll, "Add All button");
			clickOn(btnSave, "Save button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateCreatedDataRequestInDataRequestPage() {
		String xpathDRName = "//div/p[text()='" + GlobalVariables.dataRequestName + "']";
		WaitForElementWithDynamicXpath(xpathDRName);
		WebElement eleDRName = driver.findElement(By.xpath(xpathDRName));
		if (isElementPresent(eleDRName)) {
			passed("Successfully validated the  created DR in Data request Page");
			takeScreenshot(driver);
		} else {
			failed(driver, "Failed To validate the created DR in Data request Page");
		}
	}

	public void ValidateSortOrderInDRPage() {
		try {
			waitForElement(btnPlusCreateDR);
			clickOn(btnPlusCreateDR, "CreateData Request PlusIcon");
			clickOn(btnForwardArrowIcon, "Forward Arrow Icon");
			clickOn(btnForwardArrowIcon, "Forward Arrow Icon");
			clickOn(btnForwardArrowIcon, "Forward Arrow Icon");
			String dataRequestName = data.get("DataRequestName") + "rand_" + generateRandomString(2)
					+ generateRandomNumber(3);
			enterText(txtDRName, "Data RequestName", dataRequestName);
			clickOn(lstSelectCatalog, "Select CatalogName");
			String xpathCatalog = "//ul/li[text()='" + data.get("SortCatalogName") + "']";
			clickOnElementWithDynamicXpath(xpathCatalog, "CatalogName");
			clickOn(btnNext, "Next Button");
			enterText(txtDueDate, data.get("DueDate"), "Due date");
			clickOn(btnNext, "Next Button");
			enterText(txtOverviewAndInstructions, "OverViewInstructions", data.get("OverviewInstructions"));
			clickOn(btnCreate, "Create Button");
			String xpathTopicNamechkbox = "//span[text()= '" + data.get("TopicName")
					+ "']//preceding-sibling::span[@ref='eCheckbox']//input";
			System.out.println(WaitForElementWithDynamicXpath(xpathTopicNamechkbox));
			WaitForElementWithDynamicXpath(xpathTopicNamechkbox);
			clickOnElementWithDynamicXpath(xpathTopicNamechkbox, "TopicName CheckBox");
			// clickOn(btnAddAll, "Add All button");
			clickOn(btnSave, "Save button");
			String xpathDRName = "//div/p[text()='" + dataRequestName + "']";
			WebElement eleDRName = driver.findElement(By.xpath(xpathDRName));
			clickOn(eleDRName, dataRequestName);
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand  Data request Link");
			String[] headerNames = { "Metric Name", "Unit", "Instruction" };
			for (int i = 0; i < headerNames.length; i++) {
				WebElement eleSortNameHeader;
				if (headerNames[i].equals("Instruction")) {
					eleSortNameHeader = driver.findElement(By.xpath("//span[(text(),'" + headerNames[i]
							+ "s')]//ancestor::div[(@class,'sorted')]"));
				} else {
					eleSortNameHeader = driver.findElement(By.xpath("//span[(text(),'" + headerNames[i]
							+ "')]//ancestor::div[(@class,'sorted')]"));
				}
				if (eleSortNameHeader.getAttribute("class").contains("sorted-none")) {
					passed(" Successfully Validated Intial Sort Value");
				} else {
					failed(driver, " fails");
				}
				clickOn(eleSortNameHeader, "MetricName Header");
				String orderAsc = eleSortNameHeader.getAttribute("class").split("sorted-")[1];
				if (orderAsc.equals("asc")) {
					List<WebElement> eleMericNames;
					if (headerNames[i].equals("Metric Name")) {
						eleMericNames = driver
								.findElements(By.xpath("//span[@aria-label='Custom Metric']//following-sibling::span"));
					} else
						eleMericNames = driver
								.findElements(By.xpath("//span[@aria-label='Custom Metric']//following::div[@col-id='"
										+ headerNames[i].toLowerCase() + "']"));
					String orderArray[] = new String[eleMericNames.size()];
					ArrayList orderArrayList = new ArrayList();
					WebElement eleMericOrder;
					String eleName;
					for (int j = 0; j < eleMericNames.size(); j++) {
						eleName = eleMericNames.get(j).getText();
						if (headerNames[i].equals("Metric Name")) {
							eleMericOrder = driver.findElement(
									By.xpath("//span[@aria-label='Custom Metric']//following-sibling::span[text()='"
											+ eleName + "']//ancestor::div[@role='row']"));
						} else {
							eleMericOrder = driver
									.findElement(By.xpath("//span[@aria-label='Custom Metric']//following::div[text()='"
											+ eleName + "']//ancestor::div[@role='row']"));
						}
						int eleOrder = Integer.parseInt(eleMericOrder.getAttribute("row-index"));
						System.out.println(eleOrder);
						orderArray[eleOrder - 1] = eleName;
					}
					for (int j = 0; j < orderArray.length; j++) {
						orderArrayList.add(orderArray[j]);
					}
					boolean sortedAscending = Ordering.natural().isOrdered(orderArrayList);
					if (sortedAscending) {
						passed("Successfully validated the Ascending Order for the Header " + headerNames[i]);
					} else {
						failed(driver, "Failed To Validate the Ascending Order for the Header " + headerNames[i]);
					}
				}
				clickOn(eleSortNameHeader, "MetricName Header");
				String orderDesc = eleSortNameHeader.getAttribute("class").split("sorted-")[1];
				if (orderDesc.equals("desc")) {
					List<WebElement> eleMericNames;
					if (headerNames[i].equals("Metric Name")) {
						eleMericNames = driver
								.findElements(By.xpath("//span[@aria-label='Custom Metric']//following-sibling::span"));
					} else
						eleMericNames = driver
								.findElements(By.xpath("//span[@aria-label='Custom Metric']//following::div[@col-id='"
										+ headerNames[i].toLowerCase() + "']"));
					String orderArray[] = new String[eleMericNames.size()];
					ArrayList orderArrayList = new ArrayList();
					WebElement eleMericOrder;
					for (int j = 0; j < eleMericNames.size(); j++) {
						String eleName = eleMericNames.get(j).getText();
						if (headerNames[i].equals("Metric Name")) {
							eleMericOrder = driver.findElement(
									By.xpath("//span[@aria-label='Custom Metric']//following-sibling::span[text()='"
											+ eleName + "']//ancestor::div[@role='row']"));
						} else {
							eleMericOrder = driver
									.findElement(By.xpath("//span[@aria-label='Custom Metric']//following::div[text()='"
											+ eleName + "']//ancestor::div[@role='row']"));
						}
						int eleOrder = Integer.parseInt(eleMericOrder.getAttribute("row-index"));
						System.out.println(eleOrder);
						orderArray[eleOrder - 1] = eleName;
					}
					for (int j = orderArray.length - 1; j > 0; j--) {
						orderArrayList.add(orderArray[j]);
					}
					boolean sortedDescending = Ordering.natural().isOrdered(orderArrayList);
					if (sortedDescending) {
						passed("Successfully validated the Descending Order for the Header" + headerNames[i]);
					} else {
						failed(driver, "Failed To Validate the Descending Order for the Header" + headerNames[i]);
					}
				}
				clickOn(eleSortNameHeader, "MetricName Header");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void validateIntiateWorkFlow() {
		try {
			String xpathDRName = "//div/p[text()='" + GlobalVariables.dataRequestName + "']";
			WebElement eleDRName = driver.findElement(By.xpath(xpathDRName));
			clickOn(eleDRName, GlobalVariables.dataRequestName);
			clickOn(btnIntiateWorkFlow, "IntiateWorkFlow Button");
			String xpathOrgNamechkbox = "//span[text()= '" + data.get("OrgName")
					+ "']//preceding-sibling::div/div[@ref='eCheckbox']//input";
			clickOnElementWithDynamicXpath(xpathOrgNamechkbox, "organization");
			clickOn(btnSubmit, "Submit button");
			if (isElementPresent(msgSucessIntiateWorkFlow)) {
				passed("Successfully validated Sucessful DR Intiated Messege");
			} else {
				failed(driver, "Failed to validate Sucessful DR Intiated Messege");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

//	public void validateIntiateWorkFlow(String orgName) {
//		try {
//			String xpathDRName = "//div/p[text()='" + GlobalVariables.dataRequestName + "']";
//			WebElement eleDRName = driver.findElement(By.xpath(xpathDRName));
//			clickOn(eleDRName, GlobalVariables.dataRequestName);
//			clickOn(btnIntiateWorkFlow, "IntiateWorkFlow Button");
//			String xpathOrgNamechkbox = "//span[text()= '" + orgName
//					+ "']//preceding-sibling::div/div[@ref='eCheckbox']//input";
//			clickOnElementWithDynamicXpath(xpathOrgNamechkbox, "organization");
//			clickOn(btnSubmit, "Submit button");
//			if (isElementPresent(msgSucessIntiateWorkFlow)) {
//				passed("Successfully validated Sucessful DR Intiated Messege");
//				takeScreenshot(driver);
//			} else {
//				failed(driver, "Failed to validate Sucessful DR Intiated Messege");
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

	public void validateIntiateWorkFlowForAllOrganization() {
		try {
			String xpathDRName = "//div/p[text()='" + GlobalVariables.dataRequestName + "']";
			WebElement eleDRName = driver.findElement(By.xpath(xpathDRName));
			clickOn(eleDRName, GlobalVariables.dataRequestName);
			clickOn(btnIntiateWorkFlow, "IntiateWorkFlow Button");
			String xpathOrgNamechkbox;
			String[] orgLevels = { "NoLevelApprovalOrgName", "1stLevelApprovalOrgName", "2ndLevelApprovalOrgName" };
			for (int i = 0; i < orgLevels.length; i++) {
				xpathOrgNamechkbox = "//span[text()= '" + data.get(orgLevels[i])
						+ "']//preceding-sibling::div/div[@ref='eCheckbox']//input";
				clickOnElementWithDynamicXpath(xpathOrgNamechkbox, "organization");
			}
			clickOn(btnSubmit, "Submit button");
			if (isElementPresent(msgSucessIntiateWorkFlow)) {
				passed("Successfully validated Sucessful DR Intiated Messege");
			} else {
				failed(driver, "Failed to validate Sucessful DR Intiated Messege");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateApprovalDRFlowStatusForCompleteApproval() {
		try {
			Thread.sleep(5000);
			String xpathStatusCompleted = "//p[text()='" + GlobalVariables.dataRequestName
					+ "']//parent::div//following-sibling::div//div[contains(text(),'Completed')]";
			WebElement eleStatusCompleted = driver.findElement(By.xpath(xpathStatusCompleted));
			String strCompletedStatus = eleStatusCompleted.getText();
			String[] arrStrComplete = strCompletedStatus.split("/");
			if (arrStrComplete[0].contains("1") && arrStrComplete[1].contains("0")) {
				passed("Successfully validated the Data Request status for Complete Approval flow ");
			} else {
				failed(driver, "Failed to validate the Data Request status for Complete Approval flow ");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateApprovalDRFlowStatusForCompleteApproval(String completedCnt, String completedOutOf) {
		try {
			Thread.sleep(5000);
//			GlobalVariables.dataRequestName = "TestAutDRrand_lgv792";
			String xpathStatusCompleted = "//p[text()='" + GlobalVariables.dataRequestName
					+ "']//parent::div//following-sibling::div//div[contains(text(),'Completed')]";
			WebElement eleStatusCompleted = driver.findElement(By.xpath(xpathStatusCompleted));
			String strCompletedStatus = eleStatusCompleted.getText();
			String[] arrStrComplete = strCompletedStatus.split("/");
			if (arrStrComplete[0].contains(completedCnt) && arrStrComplete[1].contains(completedOutOf)) {
				passed("Successfully validated the Data Request status for Complete Approval flow ");
			} else {
				failed(driver, "Failed to validate the Data Request status for Complete Approval flow ");
			}
			System.out.println();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateApprovalDRFlowStatusWithoutCompleteApproval() {
		try {
			String xpathStatusCompleted = "//p[text()='" + GlobalVariables.dataRequestName
					+ "']//parent::div//following-sibling::div//div[contains(text(),'Completed')]";
			WebElement eleStatusCompleted = driver.findElement(By.xpath(xpathStatusCompleted));
			String strCompletedStatus = eleStatusCompleted.getText();
			String[] arrStrComplete = strCompletedStatus.split("/");
			if (arrStrComplete[0].contains("0") && arrStrComplete[1].contains("1")) {
				passed("Successfully validated the Data Request status for without complete approval flow");
			} else {
				failed(driver, "Failed to validate the Data Request status for without complete approval flow");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateApprovalDRFlowStatusWithoutCompleteApproval(String completedCnt, String completedOutOf) {
		try {
			String xpathStatusCompleted = "//p[text()='" + GlobalVariables.dataRequestName
					+ "']//parent::div//following-sibling::div//div[contains(text(),'Completed')]";
			WebElement eleStatusCompleted = driver.findElement(By.xpath(xpathStatusCompleted));
			String strCompletedStatus = eleStatusCompleted.getText();
			String[] arrStrComplete = strCompletedStatus.split("/");
			if (arrStrComplete[0].contains(completedCnt) && arrStrComplete[1].contains(completedOutOf)) {
				passed("Successfully validated the Data Request status for without complete approval flow");
			} else {
				failed(driver, "Failed to validate the Data Request status for without complete approval flow");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

//	public void NavigateToDRWorkFlowMonitor() {
//		try {
////			GlobalVariables.dataRequestName = "Test_AllInputDRFlow_Table"; // delete
//			String xpathDRName = "//div/p[text()='" + GlobalVariables.dataRequestName + "']";
//			WebElement eleDRName = driver.findElement(By.xpath(xpathDRName));
//			clickOn(eleDRName, GlobalVariables.dataRequestName);
//			clickOn(btnWorkFlowMonitor, "WorkFlow Monitor Button");
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

	public void ValidateDRNameLinkNavigationToValidPage() {
		try {
			String xpathNameInWorkflowMonitor = "//div/*[contains(text(),'" + data.get("DataRequestName") + "')]";
			clickOnElementWithDynamicXpath(xpathNameInWorkflowMonitor, "DR Name Link in WorkFlow Monitor");
			String xpathCatalogName = "//div/*[contains(text(),'" + data.get("CatalogName") + "')]";
			WebElement catalogName = driver.findElement(By.xpath(xpathCatalogName));
			if (isElementPresent(catalogName)) {
				passed("Successfully validated DR Link navigation To valid Page from WorkFlow Monitor ");
			} else {
				failed(driver, "Failed to validate DR Link navigation To valid Page from WorkFlow Monitor");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateDRNameLinkNavigationToValidPage(String orgName) {
		try {
			Thread.sleep(5000);
			String xpathNameInWorkflowMonitor = "//div[@col-id='companyName' and text()='" + orgName
					+ "']/parent::div/div[@col-id='dataRequestName']//a[text()='" + GlobalVariables.dataRequestName
					+ "']";
			clickOnElementWithDynamicXpath(xpathNameInWorkflowMonitor, "DR Name Link in WorkFlow Monitor");
			String xpathCatalogName = "//div/*[contains(text(),'" + data.get("CatalogName") + "')]";
			WebElement catalogName = driver.findElement(By.xpath(xpathCatalogName));
			if (isElementPresent(catalogName)) {
				passed("Successfully validated DR Link navigation To valid Page from WorkFlow Monitor ");
			} else {
				failed(driver, "Failed to validate DR Link navigation To valid Page from WorkFlow Monitor");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateDRNameLinkNavigationToValidPage_old(String orgName, String enterpriseName) {
		try {
			Thread.sleep(5000);
			String xpathNameInWorkflowMonitor = "//div[@col-id='companyName' and text()='" + orgName
					+ "']/parent::div/div[@col-id='dataRequestName']//a[text()='" + GlobalVariables.dataRequestName
					+ "']";
			clickOnElementWithDynamicXpath(xpathNameInWorkflowMonitor, "DR Name Link in WorkFlow Monitor");
			Thread.sleep(5000);
			String xpathCatalogName = "//div/*[contains(text(),'" + data.get("CatalogName") + "')]";
			WebElement catalogName = driver.findElement(By.xpath(xpathCatalogName));
			verifyElementAndHighlightIfExists(catalogName, "Navigated to = > " + data.get("CatalogName"),
					"Catalog Details Page");
			if (isElementPresent(catalogName)) {
				passed("Successfully validated DR Link navigation To valid Page from WorkFlow Monitor ");
			} else {
				failed(driver, "Failed to validate DR Link navigation To valid Page from WorkFlow Monitor");
			}
			String xpathSelectedOrgForRespectiveEnt = "//p[@class='addOrgName selectedName']";
			WebElement weSelectedOrgForRespectiveEnt = driver.findElement(By.xpath(xpathSelectedOrgForRespectiveEnt));
			verifyElementAndHighlightIfExists(weSelectedOrgForRespectiveEnt, "Selected org = > " + enterpriseName,
					"Catalog Details Page");
			String xpathDRname = "//span[text()='Data Request : ']/following-sibling::div/span[@aria-label='"
					+ GlobalVariables.dataRequestName + "']";
			WebElement weDRname = driver.findElement(By.xpath(xpathDRname));
			verifyElementAndHighlightIfExists(weDRname,
					"Selected data request name = > " + GlobalVariables.dataRequestName, "Catalog Details Page");
			String xpathPeriodName = "//span[text()='Period: ']/following-sibling::span/span[text()='"
					+ data.get("PeriodDesc") + "']";
			WebElement wePeriodName = driver.findElement(By.xpath(xpathPeriodName));
			verifyElementAndHighlightIfExists(wePeriodName, "Selected Period name = > " + data.get("PeriodDesc"),
					"Catalog Details Page");
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught at ValidateDRNameLinkNavigationToValidPage => " + e.getMessage());
		}
	}

	public void ValidateDRNameLinkNavigationToValidPage(String orgName, String enterpriseName) {
		try {
			Thread.sleep(5000);
			String xpathNameInWorkflowMonitor = "//div[@col-id='companyName' and text()='" + orgName
					+ "']/parent::div/div[@col-id='dataRequestName']//a[text()='" + GlobalVariables.dataRequestName
					+ "']";
			clickOnElementWithDynamicXpath(xpathNameInWorkflowMonitor, "DR Name Link in WorkFlow Monitor");
			Thread.sleep(5000);
			String xpathCatalogName = "//div/*[contains(text(),'" + data.get("CatalogName") + "')]";
			WebElement catalogName = driver.findElement(By.xpath(xpathCatalogName));
			verifyElementAndHighlightIfExists(catalogName, "Navigated to = > " + data.get("CatalogName"),
					"Catalog Details Page");
			if (isElementPresent(catalogName)) {
				passed("Successfully validated DR Link navigation To valid Page from WorkFlow Monitor ");
			} else {
				failed(driver, "Failed to validate DR Link navigation To valid Page from WorkFlow Monitor");
			}
			String xpathSelectedOrgForRespectiveEnt = "//p[@class='addOrgName selectedName']";
			WebElement weSelectedOrgForRespectiveEnt = driver.findElement(By.xpath(xpathSelectedOrgForRespectiveEnt));
			verifyElementAndHighlightIfExists(weSelectedOrgForRespectiveEnt, "Selected org = > " + enterpriseName,
					"Catalog Details Page");
			String xpathDRname = "//span[text()='Data Request : ']/following-sibling::div/span[@aria-label='"
					+ GlobalVariables.dataRequestName + "']";
			WebElement weDRname = driver.findElement(By.xpath(xpathDRname));
			verifyElementAndHighlightIfExists(weDRname,
					"Selected data request name = > " + GlobalVariables.dataRequestName, "Catalog Details Page");
			String xpathPeriodName = "//span[text()='Period: ']/following-sibling::span/span[text()='"
					+ data.get("PeriodDesc") + "']";
			WebElement wePeriodName = driver.findElement(By.xpath(xpathPeriodName));
			verifyElementAndHighlightIfExists(wePeriodName, "Selected Period name = > " + data.get("PeriodDesc"),
					"Catalog Details Page");
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand  Data request Link");
			String xpathMetricValue = "//span[text()='" + data.get("MetricName")
					+ "']//preceding::div[@col-id='metricName']//following::div[@col-id='metricValue' and @role='gridcell']//span";
			String uiMetricVal = driver.findElement(By.xpath(xpathMetricValue)).getText();
			if (uiMetricVal.equals(GlobalVariables.inputValue)) {
				passed("Successfully Validated portco  Entered metric value As" + uiMetricVal);
			} else {
				failed(driver, "Failed to validate portco Entered  metric value Expected as"
						+ GlobalVariables.inputValue + "But Actual is " + uiMetricVal);
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught at ValidateDRNameLinkNavigationToValidPage => " + e.getMessage());
		}
	}

	public void validateWorkFlowMonitorStatusForDRCompleteApproval() {
		try {
			Thread.sleep(5000);
			waitForElement(eleDRrequestInProgressPercentage);
			if (eleDRrequestInProgressPercentage.getText().contains("0")) {
				passed("Successfully validated the Data Request In Progress Percentage ");
			} else {
				// failed(driver, "Failed to validated the Data Request In Progress Percentage
				// ");
			}
			Thread.sleep(3000);
			waitForElement(eleDRrequestCompletedPercentage);
			if (eleDRrequestCompletedPercentage.getText().contains("100")) {
				passed("Successfully validated the Data Request Completed  Percentage ");
			} else {
				// failed(driver, "Failed to validated the Data Request Completed Percentage ");
			}
			String dataRequestNameInWorkFlowMonitor = lnkDRNameInWorkFlowMonitor.getText();
			if (dataRequestNameInWorkFlowMonitor.equals(GlobalVariables.dataRequestName)) {
				passed("Successfully validated the Data Request Name in  Work flow Monitor ");
			} else {
				// failed(driver, "Failed to validate Data Request Name in Work flow Monitor ");
			}
			// ((JavascriptExecutor)driver).executeScript("document.querySelector('div[ref=eViewport]').scrollLeft=2000");
			//
			//
			//
			// if (isElementPresent(eleWorkflowStatusCompleted)) {
			//
			// passed("Successfully validated the Data Request Status In WorkFlow Monitor
			// ");
			// } else {
			// failed(driver, "Failed to validateRequest Status In WorkFlow Monitor ");
			// }
			//
			// ((JavascriptExecutor)driver).executeScript("document.querySelector('div[ref=eViewport]').scrollLeft=-2000");
			//
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	//div[contains(text(),'Number of Requests')]
	@FindBy(xpath = "//button[contains(text(),'Total')]")
	private WebElement weNumberOfRequests;
	public void validateWorkFlowMonitorStatusForDRCompleteApproval(String numOfRequestsCnt, String requestsCompleted,
			String reqInProgressPercentage) {
		try {
			Thread.sleep(2000);
			waitForElement(weNumberOfRequests); //   ------- Modified to Total
			verifyElementAndHighlightIfExists(weNumberOfRequests, "DR Rrequest In Progress Percentage",
					"Workflow Monitor");
//			String sNumOfRequestsCnt = "Total: " + numOfRequestsCnt;
			if (weNumberOfRequests.getText().contains(numOfRequestsCnt)) {
				passed("Successfully validated the Data Request count and " + weNumberOfRequests.getText());
			} else {
				failed(driver, "Failed to validate the number of requests and current requsts count is "
						+ weNumberOfRequests.getText());
			}
			verifyElementAndHighlightIfExists(eleDRrequestInProgressPercentage, "DRrequest InProgressP ercentage",
					"Workflow Monitor");
//			String sProgressPercentage = "Requests In Progress: " + reqInProgressPercentage + "%";
			if (eleDRrequestInProgressPercentage.getText().contains(reqInProgressPercentage)) {
				passed("Successfully validated the Data Request In Progress Percentage and requests percentage is "
						+ eleDRrequestInProgressPercentage.getText());
			} else {
				failed(driver,
						"Failed to validate the Data Request In Progress Percentage and current requests percentage is "
								+ eleDRrequestInProgressPercentage.getText());
			}
			verifyElementAndHighlightIfExists(eleDRrequestCompletedPercentage, "DRrequest Completed Percentage",
					"Workflow Monitor");
//			String sRequestsCompleted = "Requests Completed: " + requestsCompleted + "%";
			if (eleDRrequestCompletedPercentage.getText().contains(requestsCompleted)) {
				passed("Successfully validated the Data Request Completed Percentage and present completed Percentage is "
						+ eleDRrequestCompletedPercentage.getText());
			} else {
				failed(driver,
						"Failed to validated the Data Request Completed  Percentage  and present completed Percentage is "
								+ eleDRrequestCompletedPercentage.getText());
			}
			verifyElementAndHighlightIfExists(lnkDRNameInWorkFlowMonitor, "DRName In WorkFlow Monitor",
					"Workflow Monitor");
			String dataRequestNameInWorkFlowMonitor = lnkDRNameInWorkFlowMonitor.getText();
			if (dataRequestNameInWorkFlowMonitor.equals(GlobalVariables.dataRequestName)) {
				passed("Successfully validated the Data Request Name in  Work flow Monitor ");
			} else {
				failed(driver, "Failed to validate Data Request Name in  Work flow Monitor ");
			}
			((JavascriptExecutor) driver).executeScript("document.body.style.zoom='100%';");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateWorkFlowMonitorStatusForDRWithoutCompleteApproval() {
		try {
			Thread.sleep(3000);
			waitForElement(eleDRrequestInProgressPercentage);
			if (eleDRrequestInProgressPercentage.getText().contains("100")) {
				passed("Successfully validated the Data Request In Progress Percentage ");
			} else {
				failed(driver, "Failed to validated the Data Request In Progress Percentage ");
			}
			Thread.sleep(3000);
			waitForElement(eleDRrequestCompletedPercentage);
			if (eleDRrequestCompletedPercentage.getText().contains("0")) {
				passed("Successfully validated the Data Request Completed  Percentage ");
			} else {
				failed(driver, "Failed to validated the Data Request Completed  Percentage  ");
			}
			String dataRequestNameInWorkFlowMonitor = eleDRNameInWorkFlowMonitor.getText();
			if (dataRequestNameInWorkFlowMonitor.equals(GlobalVariables.dataRequestName)) {
				passed("Successfully validated the Data Request Name in  Work flow Monitor ");
			} else {
				failed(driver, "Failed to validate Data Request Name in  Work flow Monitor ");
			}
			// ((JavascriptExecutor)driver).executeScript("document.querySelector('div[ref=eViewport]').scrollLeft=2000");
			//
			// if (isElementPresent(eleWorkflowStatusInProgress)) {
			//
			// passed("Successfully validated the Data Request Status In WorkFlow Monitor
			// ");
			// } else {
			// failed(driver, "Failed to validateRequest Status In WorkFlow Monitor ");
			//
			//
			// }
			// ((JavascriptExecutor)driver).executeScript("document.querySelector('div[ref=eViewport]').scrollLeft=-2000");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateWorkFlowMonitorStatusForDRWithoutCompleteApproval(String numOfRequestsCnt,
			String requestsCompleted, String reqInProgressPercentage) {
		try {
			Thread.sleep(10000);
			waitForElement(eleDRrequestInProgressPercentage);
			verifyElementAndHighlightIfExists(weNumberOfRequests, "DR Rrequest In Progress Percentage",
					"Workflow Monitor");
			String sNumOfRequestsCnt = "Number of Requests: " + numOfRequestsCnt;
			if (weNumberOfRequests.getText().contains(sNumOfRequestsCnt)) {
				passed("Successfully validated the Data Request count and " + weNumberOfRequests.getText());
			} else {
				failed(driver, "Failed to validate the number of requests and current requsts count is "
						+ weNumberOfRequests.getText());
			}
			verifyElementAndHighlightIfExists(eleDRrequestInProgressPercentage, "DRrequest In Progress Percentage",
					"Workflow Monitor");
			String sProgressPercentage = "Requests In Progress: " + reqInProgressPercentage + "%";
			if (eleDRrequestInProgressPercentage.getText().contains(sProgressPercentage)) {
				passed("Successfully validated the Data Request In Progress Percentage and requests percentage is "
						+ eleDRrequestInProgressPercentage.getText());
			} else {
				failed(driver,
						"Failed to validate the Data Request In Progress Percentage and current requests percentage is "
								+ eleDRrequestInProgressPercentage.getText());
			}
			verifyElementAndHighlightIfExists(eleDRrequestCompletedPercentage, "DRrequest Completed Percentage",
					"Workflow Monitor");
			String sRequestsCompleted = "Requests Completed: " + requestsCompleted + "%";
			if (eleDRrequestCompletedPercentage.getText().contains(sRequestsCompleted)) {
				passed("Successfully validated the Data Request Completed Percentage and present completed Percentage is "
						+ eleDRrequestCompletedPercentage.getText());
			} else {
				failed(driver,
						"Failed to validated the Data Request Completed  Percentage  and present completed Percentage is "
								+ eleDRrequestCompletedPercentage.getText());
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void createDRandAssigneSetupCOnfig(String flowName) {
		try {
			createDataRequestInDataRequestPage();
			validateCreatedDataRequestInDataRequestPage();
			MenuBarPage = returnMenuPage();
			adminPage = MenuBarPage.clickOnAdminMenu();
			adminassigneeSetupConfiguration = adminPage.clickOnAssigneeSetupConfigButton();
			switch (flowName) {
			case "No Level Approval Flow":
				adminassigneeSetupConfiguration.verifyCreateWorkflowApprovalSetupForNoLevel();
				break;
			case "One Level Approval Flow":
				adminassigneeSetupConfiguration.verifyCreateWorkflowApprovalSetupForOneLevel();
				break;
			case "Two Level Approval Flow":
				adminassigneeSetupConfiguration.verifyCreateWorkflowApprovalSetupForTwoLevel();
				break;
			case "Multi Level Approval Flow with Multiple approvers":
				adminassigneeSetupConfiguration.verifyCreateWorkflowApprovalSetupForMultiLevelWithMultipleApprovers();
				break;
			case "All with Two level Approval with Rejection":
			case "All Approval Flow":
				adminassigneeSetupConfiguration.verifyCreateWorkflowApprovalSetupForNoLevel();
				adminassigneeSetupConfiguration.verifyCreateWorkflowApprovalSetupForOneLevel();
				adminassigneeSetupConfiguration.verifyCreateWorkflowApprovalSetupForTwoLevel();
				break;
			default:
				warn("No Flow is slected");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void validationNoLevelApprovalFlow() {
		try {
			String flow = data.get("ApprovalWorkFlowLevels").trim();
			createDRandAssigneSetupCOnfig(flow);
			MenuBarPage = adminassigneeSetupConfiguration.returnMenuPage();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			validateIntiateWorkFlow(data.get("OrgName"));
			validateWorkFlowMonitorStatusForDRWithoutCompleteApproval("1", "0", "100");
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("PortCoUserName"), data.get("PortCoPassword"), true);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			CatalogsPage = TasksPage.clickOnCatalogNameInTasksPage();
			CatalogsPage.ValidatepublishDRFromPortCoWithEvidenceUpload();
			CatalogsPage = MenuBarPage.clickOnCatalogsMenu();
			CatalogsPage.validateEvidenceAndAuditLogInCatalogPage();
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp();
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			DataRequestPage.validateApprovalDRFlowStatusForCompleteApproval("1", "1");
			DataRequestPage.NavigateToDRWorkFlowMonitor();
			DataRequestPage.validateWorkFlowMonitorStatusForDRCompleteApproval("1", "100", "0");
			DataRequestPage.ValidateDRNameLinkNavigationToValidPage(data.get("NoLevelApprovalOrgName"),
					data.get("NoLevelApprovalEnterpriseName"));
			SignInPage = MenuBarPage.logOut();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validationOneLevelApprovalFlow() {
		try {
			String flow = data.get("ApprovalWorkFlowLevels").trim();
			createDRandAssigneSetupCOnfig(flow);
			MenuBarPage = adminassigneeSetupConfiguration.returnMenuPage();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			validateIntiateWorkFlow(data.get("1stLevelApprovalOrgName"));
			validateWorkFlowMonitorStatusForDRWithoutCompleteApproval("1", "0", "100");
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverPortCoId"),
					data.get("1stLevelApproverPortCoPassword"), true);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverId"),
					data.get("1stLevelApproverPassword"), true);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.ValidateOneLevelAprovalInTasksPage();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			DataRequestPage.validateApprovalDRFlowStatusForCompleteApproval("1", "1");
			DataRequestPage.NavigateToDRWorkFlowMonitor();
			DataRequestPage.validateWorkFlowMonitorStatusForDRCompleteApproval("1", "100", "0");
			DataRequestPage.ValidateDRNameLinkNavigationToValidPage(data.get("1stLevelApprovalOrgName"),
					data.get("1stLevelApprovalEnterpriseName"));
			SignInPage = MenuBarPage.logOut();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validationTwoLevelApprovalFlow() {
		try {
			String flow = data.get("ApprovalWorkFlowLevels").trim();
			createDRandAssigneSetupCOnfig(flow);
			MenuBarPage = adminassigneeSetupConfiguration.returnMenuPage();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			validateIntiateWorkFlow(data.get("2ndLevelApprovalOrgName"));
			validateWorkFlowMonitorStatusForDRWithoutCompleteApproval("1", "0", "100");
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("2ndLevelApproverPortCoId"),
					data.get("2ndLevelApproverPortCoPassword"), true);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			CatalogsPage = TasksPage.clickOnCatalogNameInTasksPage();
			CatalogsPage.ValidatepublishDRFromPortCoWithEvidenceUpload();
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverId"),
					data.get("1stLevelApproverPassword"), true);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.ValidateOneLevelAprovalInTasksPage();
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("2ndLevelApproverId"),
					data.get("2ndLevelApproverPassword"), true);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.ValidateOneLevelAprovalInTasksPage();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			DataRequestPage.validateApprovalDRFlowStatusForCompleteApproval("1", "1");
			DataRequestPage.NavigateToDRWorkFlowMonitor();
			DataRequestPage.validateWorkFlowMonitorStatusForDRCompleteApproval("1", "100", "0");
			DataRequestPage.ValidateDRNameLinkNavigationToValidPage(data.get("2ndLevelApprovalOrgName"),
					data.get("2ndLevelApprovalEnterpriseName"));
			SignInPage = MenuBarPage.logOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void validationTwoLevelApprovalFlowWithRejection() {
		try {
			String flow = data.get("ApprovalWorkFlowLevels").trim();
			createDRandAssigneSetupCOnfig(flow);
			MenuBarPage = adminassigneeSetupConfiguration.returnMenuPage();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			DataRequestPage.validateIntiateWorkFlowForAllOrganization();
			validateWorkFlowMonitorStatusForDRWithoutCompleteApproval("3", "0", "100");
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("2ndLevelApproverPortCoId"),
					data.get("2ndLevelApproverPortCoPassword"), true);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			CatalogsPage = TasksPage.clickOnCatalogNameInTasksPage();
			CatalogsPage.ValidatepublishDRFromPortCoWithEvidenceUpload();
			CatalogsPage.validateEvidenceAndAuditLogInCatalogPage();
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverId"),
					data.get("1stLevelApproverPassword"), true);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.ValidateOneLevelRejectionInTasksPage();
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("2ndLevelApproverPortCoId"),
					data.get("2ndLevelApproverPortCoPassword"), true);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			CatalogsPage = TasksPage.selectDataRequestReviewTask();
			CatalogsPage.ValidatepublishDRFromPortCoWithEvidenceUpload();
			CatalogsPage.validateEvidenceAndAuditLogInCatalogPage();
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverId"),
					data.get("1stLevelApproverPassword"), true);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.ValidateOneLevelAprovalInTasksPage();
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("2ndLevelApproverId"),
					data.get("2ndLevelApproverPassword"), true);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.ValidateOneLevelAprovalInTasksPage();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			DataRequestPage.validateApprovalDRFlowStatusForCompleteApproval();
			DataRequestPage.NavigateToDRWorkFlowMonitor();
			DataRequestPage.validateWorkFlowMonitorStatusForDRCompleteApproval("3", "100", "0");
			DataRequestPage.ValidateDRNameLinkNavigationToValidPage(data.get("2ndLevelApprovalOrgName"),
					data.get("2ndLevelApprovalEnterpriseName"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void validatDRforNoAndOneAndTwoLevelApprovaFlows() {
		String flow = data.get("ApprovalWorkFlowLevels").trim();
		createDRandAssigneSetupCOnfig(flow);
		MenuBarPage = adminassigneeSetupConfiguration.returnMenuPage();
		DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
		DataRequestPage.validateIntiateWorkFlowForAllOrganization();
		SignInPage = MenuBarPage.logOut();
		HomePage = SignInPage.SignInToPulsEsGApp(data.get("PortCoUserName"), data.get("PortCoPassword"), true);
		MenuBarPage = HomePage.returnMenuPage();
		MenuBarPage.clickOnHamburgerMenu();
		TasksPage = MenuBarPage.clickOnTasksMenu();
		CatalogsPage = TasksPage.clickOnCatalogNameInTasksPage();
		CatalogsPage.ValidatepublishDRFromPortCoWithEvidenceUpload();
		CatalogsPage = MenuBarPage.clickOnCatalogsMenu();
		CatalogsPage.validateEvidenceAndAuditLogInCatalogPage();
		SignInPage = MenuBarPage.logOut();
		HomePage = SignInPage.SignInToPulsEsGApp();
		MenuBarPage = HomePage.returnMenuPage();
		MenuBarPage.clickOnHamburgerMenu();
		DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
		DataRequestPage.validateApprovalDRFlowStatusForCompleteApproval("1", "2");
		DataRequestPage.NavigateToDRWorkFlowMonitor();
		DataRequestPage.validateWorkFlowMonitorStatusForDRCompleteApproval();
		DataRequestPage.ValidateDRNameLinkNavigationToValidPage(data.get("NoLevelApprovalOrgName"));
		SignInPage = MenuBarPage.logOut();
		// HomePage = SignInPage.SignInToPulsEsGApp();
		// MenuBarPage=HomePage.returnMenuPage();
		// MenuBarPage.clickOnHamburgerMenu();
		//
		// DataRequestPage=MenuBarPage.clickOnDataRequestMenu();
		// //1st level of aproval
		// DataRequestPage.validateIntiateWorkFlowForAllOrganization();
		// SignInPage=MenuBarPage.logOut();
		HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverPortCoId"),
				data.get("1stLevelApproverPortCoPassword"), true);
		MenuBarPage = HomePage.returnMenuPage();
		MenuBarPage.clickOnHamburgerMenu();
		TasksPage = MenuBarPage.clickOnTasksMenu();
		CatalogsPage = TasksPage.clickOnCatalogNameInTasksPage();
		// CatalogsPage.ValidatepublishDRFromPortCoForTwoMetrics();
		CatalogsPage.ValidatepublishDRFromPortCoWithEvidenceUpload();
		CatalogsPage.validateEvidenceAndAuditLogInCatalogPage();
		SignInPage = MenuBarPage.logOut();
		HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverId"), data.get("1stLevelApproverPassword"),
				true);
		MenuBarPage = HomePage.returnMenuPage();
		MenuBarPage.clickOnHamburgerMenu();
		TasksPage = MenuBarPage.clickOnTasksMenu();
		TasksPage.ValidateOneLevelAprovalInTasksPage();
		DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
		DataRequestPage.validateApprovalDRFlowStatusForCompleteApproval("2", "1");
		DataRequestPage.NavigateToDRWorkFlowMonitor();
		// DataRequestPage.validateWorkFlowMonitorStatusForDRCompleteApproval();
		DataRequestPage.ValidateDRNameLinkNavigationToValidPage(data.get("1stLevelApprovalOrgName"));
		SignInPage = MenuBarPage.logOut();
		// 2nd level of approval
		// DataRequestPage.validateIntiateWorkFlowForAllOrganization();
		// SignInPage=MenuBarPage.logOut();
		HomePage = SignInPage.SignInToPulsEsGApp(data.get("2ndLevelApproverPortCoId"),
				data.get("2ndLevelApproverPortCoPassword"), true);
		MenuBarPage = HomePage.returnMenuPage();
		MenuBarPage.clickOnHamburgerMenu();
		TasksPage = MenuBarPage.clickOnTasksMenu();
		CatalogsPage = TasksPage.clickOnCatalogNameInTasksPage();
		// CatalogsPage.ValidatepublishDRFromPortCoForTwoMetrics();
		CatalogsPage.ValidatepublishDRFromPortCoWithEvidenceUpload();
		CatalogsPage.validateEvidenceAndAuditLogInCatalogPage();
		SignInPage = MenuBarPage.logOut();
		HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverId"), data.get("1stLevelApproverPassword"),
				true);
		MenuBarPage = HomePage.returnMenuPage();
		MenuBarPage.clickOnHamburgerMenu();
		TasksPage = MenuBarPage.clickOnTasksMenu();
		TasksPage.ValidateOneLevelAprovalInTasksPage();
		DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
		DataRequestPage.validateApprovalDRFlowStatusForCompleteApproval("2", "1");
		DataRequestPage.NavigateToDRWorkFlowMonitor();
		// DataRequestPage.validateWorkFlowMonitorStatusForDRCompleteApproval();
		DataRequestPage.ValidateDRNameLinkNavigationToValidPage(data.get("1stLevelApprovalOrgName"));
		SignInPage = MenuBarPage.logOut();
		HomePage = SignInPage.SignInToPulsEsGApp(data.get("2ndLevelApproverId"), data.get("2ndLevelApproverPassword"),
				true);
		MenuBarPage = HomePage.returnMenuPage();
		MenuBarPage.clickOnHamburgerMenu();
		TasksPage = MenuBarPage.clickOnTasksMenu();
		TasksPage.ValidateOneLevelAprovalInTasksPage();
		DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
		DataRequestPage.validateApprovalDRFlowStatusForCompleteApproval("3", "0");
		DataRequestPage.NavigateToDRWorkFlowMonitor();
		// DataRequestPage.validateWorkFlowMonitorStatusForDRCompleteApproval();
		DataRequestPage.ValidateDRNameLinkNavigationToValidPage(data.get("2ndLevelApprovalOrgName"));
		SignInPage = MenuBarPage.logOut();
	}

	@FindBy(xpath = "//button[text()='Publish']")
	private WebElement btnPublish;

	public void validatDRforNoAndOneLevelApprovalAndTwoLevelRejectionFlow() {
		try {
			String flow = data.get("ApprovalWorkFlowLevels").trim(), metricData;
			createDRandAssigneSetupCOnfig(flow);
			MenuBarPage = adminassigneeSetupConfiguration.returnMenuPage();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			DataRequestPage.validateIntiateWorkFlowForAllOrganization();
			validateWorkFlowMonitorStatusForDRWithoutCompleteApproval("3", "0", "100");
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("PortCoUserName"), data.get("PortCoPassword"), true);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			CatalogsPage = TasksPage.clickOnCatalogNameInTasksPage();
			CatalogsPage.ValidatepublishDRFromPortCoWithEvidenceUpload();
			CatalogsPage = MenuBarPage.clickOnCatalogsMenu();
			CatalogsPage.validateEvidenceAndAuditLogInCatalogPage();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			if (!TasksPage.verifyIfDataRequestTaskIsExists()) {
				passed("Data Request Task card is not available post updaitng data value for Metrics");
			} else {
				failed(driver, "Data Request Task card is available post updaitng data value for Metrics");
			}
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("UserName"), data.get("Password"), false);// false
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			DataRequestPage.validateApprovalDRFlowStatusForCompleteApproval("1", "3");
			DataRequestPage.NavigateToDRWorkFlowMonitor();
			DataRequestPage.validateWorkFlowMonitorStatusForDRCompleteApproval("3", "33", "67");
			DataRequestPage.ValidateDRNameLinkNavigationToValidPage(data.get("NoLevelApprovalOrgName"),
					data.get("NoLevelApprovalEnterpriseName"));
			if (!isElementPresent(btnPublish)) {
				passed("Publish button is not present post publishing data at portco level : No level Approval");
			} else {
				failed(driver, "Publish button is present post publishing data at portco level : No level Approval");
			}
			SignInPage = MenuBarPage.logOut();
			info("********************1st Level Approval********************");
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverPortCoId"),
					data.get("1stLevelApproverPortCoPassword"), false);// false
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			CatalogsPage = TasksPage.clickOnCatalogNameInTasksPage();
			CatalogsPage.ValidatepublishDRFromPortCoWithEvidenceUpload();
			CatalogsPage = MenuBarPage.clickOnCatalogsMenu();
			CatalogsPage.validateEvidenceAndAuditLogInCatalogPage();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			if (!TasksPage.verifyIfDataRequestTaskIsExists()) {
				passed("Data Request Task card is not available post updaitng data value for Metrics");
			} else {
				failed(driver, "Data Request Task card is available post updaitng data value for Metrics");
			}
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverId"),
					data.get("1stLevelApproverPassword"), false);// false
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			int currentCount = TasksPage.getDataRequestApprovalCount();
			MenuBarPage = HomePage.returnMenuPage();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.ClickOnDataRequestApprovalTaskInTasksPage(data.get("1stLevelApprovalEnterpriseName"));
			TasksPage.ValidateOneLevelApprovalWithOrgEnteredDetails(data.get("PeriodDesc"),
					data.get("1stLevelApprovalOrgName"));
			TasksPage.ValidateAproveAllInTasksPage();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			// check task
			int postApprovalCurrentCount = TasksPage.getDataRequestApprovalCount();
			currentCount = currentCount - 1;
			if (postApprovalCurrentCount == currentCount) {
				passed("Data Request Approval card is not available post 1st level approval");
			} else {
				failed(driver, "Data Request Approval card is available post 1st level approval");
			}
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			DataRequestPage.validateApprovalDRFlowStatusForCompleteApproval("2", "3");
			DataRequestPage.NavigateToDRWorkFlowMonitor();
			DataRequestPage.validateWorkFlowMonitorStatusForDRCompleteApproval("3", "67", "33");
			DataRequestPage.ValidateDRNameLinkNavigationToValidPage(data.get("1stLevelApprovalOrgName"),
					data.get("1stLevelApprovalEnterpriseName"));
			SignInPage = MenuBarPage.logOut();
			/*------------------*/info("********************2nd Level Approval********************");// --------------------------------------
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("2ndLevelApproverPortCoId"),
					data.get("2ndLevelApproverPortCoPassword"), true);// false
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			CatalogsPage = TasksPage.clickOnCatalogNameInTasksPage();
			CatalogsPage.ValidatepublishDRFromPortCoWithEvidenceUpload();
			CatalogsPage.publishDRInCatalogPage();
			CatalogsPage = MenuBarPage.clickOnCatalogsMenu();
			CatalogsPage.validateEvidenceAndAuditLogInCatalogPage();
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverId"),
					data.get("1stLevelApproverPassword"), false);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			currentCount = TasksPage.getDataRequestApprovalCount();
			TasksPage.ClickOnDataRequestApprovalTaskInTasksPage(data.get("2ndLevelApprovalEnterpriseName"));
			TasksPage.ValidateOneLevelRejectionInTasksPage(data.get("PeriodDesc"));
			TasksPage = MenuBarPage.clickOnTasksMenu();
			Thread.sleep(5000);
			postApprovalCurrentCount = TasksPage.getDataRequestApprovalCount();
			currentCount = currentCount - 1;
			if (postApprovalCurrentCount == currentCount) {
				passed("Data Request Approval card is not available post 2nd level approval");
			} else {
				failed(driver, "Data Request Approval card is available post 2nd level approval");
			}
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("2ndLevelApproverPortCoId"),
					data.get("2ndLevelApproverPortCoPassword"), false);// false
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.clickOnDataReviewTaskInTasksPage();
			TasksPage.ValidateRejectDetailsAtPortco(data.get("PeriodDesc"));
			CatalogsPage = TasksPage.selectDataRequestReviewTask();// ReviewRequested button
			GlobalVariables.inputValue = generateRandomNumber(3);
			CatalogsPage.ValidateEnterDRRevisedInputFromPortCo(GlobalVariables.inputValue);
			CatalogsPage.validatePublishDRFromPortCo();
			CatalogsPage = MenuBarPage.clickOnCatalogsMenu();
			CatalogsPage.validateRevisedMetricValueInCatalog();
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverId"),
					data.get("1stLevelApproverPassword"), false);// false
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.ClickOnDataRequestApprovalTaskInTasksPage(data.get("2ndLevelApprovalEnterpriseName"));
			TasksPage.ValidateRevisedDetailsAtInvestCoDataAprovalTask(data.get("PeriodDesc"));
			TasksPage.ValidateTaskAprovalInTasksPage();
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("2ndLevelApproverId"),
					data.get("2ndLevelApproverPassword"), false);// false
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			currentCount = TasksPage.getDataRequestApprovalCount();
			TasksPage.ClickOnDataRequestApprovalTaskInTasksPage(data.get("2ndLevelApprovalEnterpriseName"));
			TasksPage.ValidateRevisedDetailsAtInvestCoDataAprovalTaskForSecondLevelApprover(data.get("PeriodDesc"));
			TasksPage.ValidateTaskAprovalInTasksPage();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			postApprovalCurrentCount = TasksPage.getDataRequestApprovalCount();
			currentCount = currentCount - 1;
			if (postApprovalCurrentCount == currentCount) {
				passed("Data Request Approval card is not available post 2nd level approval");
			} else {
				failed(driver, "Data Request Approval card is available post 2nd level approval");
			}
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			DataRequestPage.validateApprovalDRFlowStatusForCompleteApproval("3", "3");
			DataRequestPage.NavigateToDRWorkFlowMonitor();
			DataRequestPage.validateWorkFlowMonitorStatusForDRCompleteApproval("3", "100", "0");
			DataRequestPage.ValidateDRNameLinkNavigationToValidPage(data.get("2ndLevelApprovalOrgName"),
					data.get("2ndLevelApprovalEnterpriseName"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void ValidateDataRequestApprovalcompareTasksDetails() {
		try {
			MenuBarPage = returnMenuPage();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			DataRequestPage.createDRandAssigneSetupCOnfig("One Level Approval Flow");
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			DataRequestPage.validateIntiateWorkFlow(data.get("1stLevelApprovalOrgName"));
			adminPage = MenuBarPage.clickOnAdminMenu();
			eventsPage = adminPage.clickOnEvents();
			eventsPage.validateEventDetailsInEventsPage(GlobalVariables.dataRequestName, "Data Request Initiated",
					"Workflow", data.get("1stLevelApproverId"), "In Progress");
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("PortCoUserName"), data.get("PortCoPassword"), true);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			CatalogsPage = TasksPage.clickOnCatalogNameInTasksPage();
			CatalogsPage.ValidatepublishDRFromPortCoWithCSVFileUpload();
			adminPage = MenuBarPage.clickOnAdminMenu();
			eventsPage = adminPage.clickOnEvents();
			eventsPage.validateEventDetailsInEventsPage(GlobalVariables.dataRequestName, "Data Destination Published",
					"Workflow", data.get("PortCoUserName"), "In Progress");
			eventsPage.validateDownloadAndSearchEventsInEventsPage();
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverId"),
					data.get("1stLevelApproverPassword"), false);// false
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.ClickOnDataRequestApprovalTaskInTasksPage(data.get("1stLevelApprovalOrgName"));
			TasksPage.validateDetailsInApprovalTaskForCompareWithoutCompareTask(GlobalVariables.inputValue, "No value",
					data.get("CurrentPeriodDesc"), data.get("PreviousPeriodDesc"));
			TasksPage.rejectTaskWithCommentsInTasksPage();
			adminPage = MenuBarPage.clickOnAdminMenu();
			eventsPage = adminPage.clickOnEvents();
			eventsPage.validateEventDetailsInEventsPage(GlobalVariables.dataRequestName, "Data Request Rejected",
					"Workflow", data.get("UserName"), "In Progress");
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("PortCoUserName"), data.get("PortCoPassword"), false);// false
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.clickOnDataReviewTaskInTasksPage();
			CatalogsPage = TasksPage.selectDataRequestReviewTask();// ReviewRequested button
			GlobalVariables.inputRevised = generateRandomNumber(3);
			CatalogsPage.ValidateEnterDRRevisedInputFromPortCo(GlobalVariables.inputRevised);
			CatalogsPage.validatePublishDRFromPortCo();
			adminPage = MenuBarPage.clickOnAdminMenu();
			eventsPage = adminPage.clickOnEvents();
			eventsPage.validateEventDetailsInEventsPage(GlobalVariables.dataRequestName, "Data Destination Published",
					"Workflow", data.get("PortCoUserName"), "In Progress");
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverId"),
					data.get("1stLevelApproverPassword"), false);// false
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.ClickOnDataRequestApprovalTaskInTasksPage(data.get("1stLevelApprovalOrgName"));
			TasksPage.validateDetailsInApprovalTaskForCompareWithCompareTask(GlobalVariables.inputRevised,
					GlobalVariables.inputValue);
			TasksPage.ValidateTaskAprovalInTasksPage();
			adminPage = MenuBarPage.clickOnAdminMenu();
			eventsPage = adminPage.clickOnEvents();
			eventsPage.validateEventDetailsInEventsPage(GlobalVariables.dataRequestName, " Data Request Approved",
					"Workflow", data.get("1stLevelApproverId"), "Completed");
			SignInPage = MenuBarPage.logOut();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
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

	@FindBy(xpath = "//div[text()='In progress']//ancestor::div[@col-id='status']//following-sibling::div//div[@aria-label='Cancel Request']")
	private WebElement eleWorkflowInProgressCancelRequest;
	@FindBy(xpath = "//button[text()='Yes']")
	private WebElement btnYes;

	public void CancelRequestInWorkflowMonitorForInProgressRequest() {
		try {
			String xpathStatusCompleted = "//p[text()='" + GlobalVariables.dataRequestName
					+ "']//parent::div//following-sibling::div//div[contains(text(),'Completed')]";
			WebElement eleStatusCompleted = driver.findElement(By.xpath(xpathStatusCompleted));
			String strCompletedStatus = eleStatusCompleted.getText();
			String[] arrStrComplete = strCompletedStatus.split("/");
			if (arrStrComplete[1].contains("1")) {
				String xpathDRName = "//div/p[text()='" + GlobalVariables.dataRequestName + "']";
				WebElement eleDRName = driver.findElement(By.xpath(xpathDRName));
				clickOn(eleDRName, GlobalVariables.dataRequestName);
				clickOn(btnWorkFlowMonitor, "WorkFlowMonitor");
				waitForElement(eleWorkflowInProgressCancelRequest);
				if (isElementPresent(eleWorkflowInProgressCancelRequest)) {
					clickOn(eleWorkflowInProgressCancelRequest, "Cancel InProgress");
					clickOn(btnYes, "Yes Button");
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateDataRequestNameLinkNavigationToValidPage(String orgName, String catName) {
		try {
			Thread.sleep(5000);
			String xpathNameInWorkflowMonitor = "//div[@col-id='companyName' and text()='" + orgName
					+ "']/parent::div/div[@col-id='dataRequestName']//a[text()='" + GlobalVariables.dataRequestName
					+ "']";
			clickOnElementWithDynamicXpath(xpathNameInWorkflowMonitor, "DR Name Link in WorkFlow Monitor");
			String xpathCatalogName = "//div/*[contains(text(),'" + catName + "')]";
			WebElement catalogName = driver.findElement(By.xpath(xpathCatalogName));
			verifyElementAndHighlightIfExists(catalogName, "Navigated to = > " + data.get("CatalogName"),
					"Catalog Details Page");
			if (isElementPresent(catalogName)) {
				passed("Successfully validated DR Link navigation To valid Page from WorkFlow Monitor ");
			} else {
				failed(driver, "Failed to validate DR Link navigation To valid Page from WorkFlow Monitor");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	WebElement weExpand;

	public void expandTopicInCatalog(String TopicName) {
		try {
			String xpathExpandDataRequest = "//*[contains(text(),'" + TopicName
					+ "')]//parent::span//parent::span[@ref='eValue']//preceding-sibling::span[@ref='eContracted']";
			weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Topic");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void clickOnMetricName(String metricName) {
		try {
			((JavascriptExecutor) driver)
					.executeScript("document.querySelector('div[ref=eBodyViewport]').scrollTop=2000");
			WebElement weMetricValue = driver.findElement(By.xpath("//span[text()='" + metricName
					+ "']//ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']"));
			waitForElement(weMetricValue);
			jsClick(weMetricValue, "MetricValueFor" + "");
		} catch (Exception e) {
			System.out.println("Exception here *************************>");
			clickOnMetricName(metricName);
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
	String[] lblTableMetricDataNamesExpected;

	public void ValidateDataInputValuesinMetricsvalue() {
		try {
			Data data;
			ArrayList<String> datasets;
			data = new Data(Constants.CatalogDRMetricAllInputTypesEntry);
			datasets = data.getDataSets();
			data.setColIndex();
			List<String> logInDetails = new ArrayList();
			for (String dataset : datasets) {
				data.setIndex_Multiple(dataset);
				expandTopicInCatalog(data.get("TopicName"));
				System.out.println(data.get("RHPMetricValue"));
				String[] metricDataValues = { data.get("RHPMetricValue") };
				for (int j = 0; j < metricDataValues.length; j++) {
					if (data.get("InputType").equals("Table")) {
						clickOnMetricName(data.get("MetricName"));
						clickOn(btnValuetableRHP, "Table Value Input Button");
						String[] lblTableMetricDataNames = { "Name", "Gender", "IsActive", "FavCar", "Age" };
						String[] lblTableMetricDataNamesExpected = { Constants.textMetricTableValue,
								Constants.drpGenderBooleanValue, Constants.drpDownIsActiveValue,
								Constants.drpMultiSelectFavCar, Constants.texttxtAge };
						for (int k = 0; k < lblTableMetricDataNames.length; k++) {
							if (lblTableMetricDataNames[k].equals("Name") || lblTableMetricDataNames[k].equals("Age")) {
								WebElement weTableMetricValue1 = driver.findElement(By.xpath("//p[text()='"
										+ lblTableMetricDataNames[k] + "']//following-sibling::div//div//div//input"));
								String weTableMetricValue = weTableMetricValue1.getAttribute("value");
								Thread.sleep(500);
								if (weTableMetricValue.contains(lblTableMetricDataNamesExpected[k])) {
									passed("Successfully Validated In Activity Details As" + weTableMetricValue);
								} else {
									failed(driver,
											"Failed To validate  In Activity Details Expected As "
													+ lblTableMetricDataNamesExpected[k] + "But Actual is"
													+ weTableMetricValue);
								}
							} else if (lblTableMetricDataNames[k].equals("FavCar")) {
								WebElement weTableMetricValue = driver
										.findElement(By.xpath("//p[text()='" + lblTableMetricDataNames[k]
												+ "']//following-sibling::div//div[@aria-label='Audi']"));
								Thread.sleep(500);
								if (weTableMetricValue.getText().trim().contains(lblTableMetricDataNamesExpected[k])) {
									passed("Successfully Validated  In Activity Details As"
											+ weTableMetricValue.getText());
								} else {
									failed(driver,
											"Failed To validate  In Activity Details Expected As "
													+ lblTableMetricDataNamesExpected[k] + "But Actual is"
													+ weTableMetricValue.getText());
								}
							} else {
								WebElement weTableMetricValue = driver.findElement(By.xpath("//p[text()='"
										+ lblTableMetricDataNames[k] + "']//following-sibling::div//div//div"));
								Thread.sleep(500);
								if (weTableMetricValue.getText().trim().contains(lblTableMetricDataNamesExpected[k])) {
									passed("Successfully Validated  In Activity Details As"
											+ weTableMetricValue.getText());
								} else {
									failed(driver,
											"Failed To validate  In Activity Details Expected As "
													+ lblTableMetricDataNamesExpected[k] + "But Actual is"
													+ weTableMetricValue.getText());
								}
							}
						}
						clickOn(btnSectionCloseTableMetric, "Close Table Values");
						sleep(1000);
						if (isElementPresent(btnSectionCloseRHP)) {
							clickOn(btnSectionCloseRHP, "btnSectionCloseRHP");
						}
					} else {
						WebElement wemetricValue = driver
								.findElement(By.xpath("//div[@col-id='metricValue' and @role='gridcell']//span[text()='"
										+ metricDataValues[j] + "']"));
						Thread.sleep(500);
						if (wemetricValue.getText().trim().equals(metricDataValues[j])) {
							passed("Successfully Validated Metric Value " + metricDataValues[j]
									+ " In metic Value Details As" + wemetricValue.getText());
						} else {
							System.out.println(metricDataValues[j]);
							System.out.println(wemetricValue.getText());
							failed(driver,
									"Failed To validate Metric Value " + metricDataValues[j]
											+ " In metic Value Details Expected As " + metricDataValues[j]
											+ "But Actual is" + wemetricValue.getText());
						}
					}
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	// Comments Code
	@FindBy(xpath = "//div/button[contains(text(),'Total')]")
	private WebElement weTotal;
	
	public void validateDRRequestStatusInTrackRequestsPage(String total, String inProcessPercentage,
			String InProcessCount, String completedPercentage, String completedCount, String failedPercentage,
			String failedCount) {
		try {
			Thread.sleep(10000);
			waitForElement(weTotal);
			verifyElementAndHighlightIfExists(weTotal, "Total Count", "Track Requests");
			String strTotalCount = "Total:(" + total + ")";
			if (weTotal.getText().replaceAll("\\s", "").contains(strTotalCount.replaceAll("\\s", ""))) {
				passed("Successfully validated the Total Data Request count and " + weTotal.getText());
			} else {
				failed(driver, "Failed to validate the Total  number of requests count  Expected as " + strTotalCount
						+ "and  requsts count is " + weTotal.getText());
			}
			verifyElementAndHighlightIfExists(eleDRrequestInProcessPercentageLatest, "DRrequest In process Percentage",
					"Track Requests");
			String strInProcessPercentage = "In process:" + inProcessPercentage + "% (" + InProcessCount + ")";
			if (eleDRrequestInProcessPercentageLatest.getText().replaceAll("\\s", "")
					.equals(strInProcessPercentage.replaceAll("\\s", ""))) {
				passed("Successfully validated the Data Request In Progress Percentage  and count as"
						+ strInProcessPercentage);
			} else {
				failed(driver, "Failed to validate the Data Request In process Percentage and count Expected as"
						+ inProcessPercentage + " But actual is " + eleDRrequestInProcessPercentageLatest.getText());
			}
			verifyElementAndHighlightIfExists(eleDRrequestCompletedPercentageLatest, "DRrequest completed Percentage",
					"Track Requests");
			String strCompletedPercentage = "Completed:" + completedPercentage + "% (" + completedCount + ")";
			if (eleDRrequestCompletedPercentageLatest.getText().replaceAll("\\s", "")
					.equals(strCompletedPercentage.replaceAll("\\s", ""))) {
				passed("Successfully validated the Data Request  completed Percentage and  count  as"
						+ strCompletedPercentage + "% " + completedCount);
			} else {
				failed(driver, "Failed to validate the Data Request completed Percentage and  count Expected as"
						+ strCompletedPercentage + " But actual is " + eleDRrequestCompletedPercentageLatest.getText());
			}
			verifyElementAndHighlightIfExists(eleDRrequestFailedPercentageLatest, "DRrequest Failed Percentage",
					"Track Requests");
			String strFailedPercentage = "Failed:" + failedPercentage + "% (" + failedCount + ")";
			if (eleDRrequestFailedPercentageLatest.getText().replaceAll("\\s", "").replaceAll("\\s", "")
					.equals(strFailedPercentage.replaceAll("\\s", ""))) {
				passed("Successfully validated the Data Request  Failed Percentage as" + strFailedPercentage);
			} else {
				failed(driver, "Failed to validate the Data Request Failed Percentage and Failed Count Expected as"
						+ strFailedPercentage + " But actual is " + eleDRrequestFailedPercentageLatest.getText());
			}
			takeScreenshot(driver);
			if (InProcessCount.equals("1")) {
				String strStatusInProgress = "//div[@col-id='status']//div[contains(text(),'In Progress')]";
				WaitForElementWithDynamicXpath(strStatusInProgress);
				if (isElementPresentDynamicXpath(strStatusInProgress)) {
					passed("Successfully validated In progress status in Track requests Page");
				} else {
					failed(driver, "Failed To validate progress status in Track requests Page");
				}
			} else if (completedCount.equals("1")) {
				String strStatusInCompleted = "//div[@col-id='status']//div[contains(text(),'Completed')]";
				WaitForElementWithDynamicXpath(strStatusInCompleted);
				if (isElementPresentDynamicXpath(strStatusInCompleted)) {
					passed("Successfully validated In completed status in Track requests Page");
				} else {
					failed(driver, "Failed To validate completed status in Track requests Page");
				}
			}
			// In Progress - Data Request Task Created
			// if()
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//button[text()='External']")
	private WebElement btnExternal;
	@FindBy(xpath = "//*[@data-testid='CloseIcon' and @aria-label='Close']")
	private WebElement btnSectionClose;
	@FindBy(xpath = "//*[@data-testid='CloseIcon' and @aria-label='Close']")
	private WebElement btnSectionCloseTableMetric;

	public void verifyExternalCommentsIsDisabledUntilDataDestinationIsCreated() {
		boolean blnFnd = false;
		try {
			createDataRequestAndValidateInDataRequestPage("General");
			validateIntiateWorkFlow(data.get("OrgName"));
			MenuBarPage = returnMenuPage();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			DataRequestPage.NavigateToDRWorkFlowMonitor();
			DataRequestPage.validateDRRequestStatusInTrackRequestsPage("1", "100", "1", "0", "0", "0", "0");
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("PortCoUserName"), data.get("PortCoPassword"), true);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			/*
			 * String flow = data.get("ApprovalWorkFlowLevels").trim();
			 * //createDRandAssigneSetupCOnfig(flow); createDataRequestInDataRequestPage();
			 * validateCreatedDataRequestInDataRequestPage(); MenuBarPage =
			 * returnMenuPage(); //MenuBarPage =
			 * adminassigneeSetupConfiguration.returnMenuPage(); DataRequestPage =
			 * MenuBarPage.clickOnDataRequestMenu();
			 * validateIntiateWorkFlow(data.get("1stLevelApprovalOrgName")); SignInPage =
			 * MenuBarPage.logOut(); HomePage =
			 * SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverPortCoId"),
			 * data.get("1stLevelApproverPortCoPassword"), false); MenuBarPage =
			 * HomePage.returnMenuPage(); MenuBarPage.clickOnHamburgerMenu();
			 */
			CatalogsPage = MenuBarPage.clickOnCatalogsMenu();
//			CatalogsPage.scrollToCatalogView(data.get("CatalogName"));
			CatalogsPage.clickOnCatalogInCatalogPage(data.get("CatalogName"));
			CatalogsPage.refreshPage();
			CatalogsPage.selectPeriodFromCatalogDetailsPage(data.get("PeriodDesc"));
			if (CatalogsPage.SelectCommentsFromMetric(data.get("MetricName"))) {
				CatalogsPage.verifyExternalCommentsDefaultMessages();
			} else {
				failed(driver, "Unable to select Metric ==> " + data.get("MetricName"));
			}
			SignInPage = MenuBarPage.logOut();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean verifyExternalCommentsIsEnabledAndCreateExternalComments() {
		boolean blnFnd = false, blnSucc = false;
		try {
			String flow = data.get("ApprovalWorkFlowLevels").trim();
			createDataRequestInDataRequestPage();
			validateCreatedDataRequestInDataRequestPage();
			validateIntiateWorkFlow(data.get("1stLevelApprovalOrgName"));
			MenuBarPage = returnMenuPage();
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverPortCoId"),
					data.get("1stLevelApproverPortCoPassword"), true);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			CatalogsPage = TasksPage.clickOnCatalogNameInTasksPage();
			blnSucc = CatalogsPage.verifyCreateExternalComments(data.get("1stLevelApproverPortCoId"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blnSucc;
	}

	public void selectPeriod(String period) {
		try {
			clickOn(drpPeriod, "Period Dropdown");
			// jsClick(drpPeriod, "");
			WebElement wePeriodName = driver.findElement(By.xpath("//ul[@role='listbox']/li[text()='" + period + "']"));
			clickOn(wePeriodName, period);
		} catch (Exception e) {
		}
	}

	@FindBy(xpath = "//div[text()='FY23']")
	private WebElement drpPeriod;
	@FindBy(xpath = "//span[text()='Due Date']//following::input[@placeholder='yyyy/mm/dd']")
	private WebElement dueDate;

	public void createDataRequestWithPeriodInDataRequestPage(String period) {
		try {
			waitForElement(btnPlusCreateDR);
			clickOn(btnPlusCreateDR, "CreateData Request PlusIcon");
			GlobalVariables.dataRequestName = period + "_" + generateRandomString(2) + generateRandomNumber(3);
enterText(txtDRNameLatest, "Data RequestName", GlobalVariables.dataRequestName);
clickOn(lstSelectCatalog, "Select CatalogName");
GlobalVariables.catalogName = data.get("CatalogName");
String xpathCatalog = "//ul/li[text()='" + data.get("CatalogName") + "']";
System.out.println(WaitForElementWithDynamicXpath(xpathCatalog));
System.out.println(WaitForElementWithDynamicXpath(xpathCatalog));
scrollToViewElement(driver.findElement(By.xpath(xpathCatalog)));
clickOnElementWithDynamicXpath(xpathCatalog, "CatalogName");
clickOn(lstDataCollectionPeriod, "Select CatalogName");
String xpathPeriod = "//ul/li[text()='" + period + "']";
clickOnElementWithDynamicXpath(xpathPeriod, period);
enterText(txtAddInstructions, "Add Instructions", " AutTest Instructions");
scrollTo(btnCancelInDR);
clickOn(btnNext, "Next Button");
String xpathTopicNamechkbox = "//span[text()= '" + data.get("TopicName")
+ "']//preceding-sibling::span[@ref='eCheckbox']//input";
System.out.println(WaitForElementWithDynamicXpath(xpathTopicNamechkbox));
WaitForElementWithDynamicXpath(xpathTopicNamechkbox);
clickOnElementWithDynamicXpath(xpathTopicNamechkbox, "TopicName CheckBox");
			// clickOn(btnAddAll, "Add All button");
clickOn(btnNext, "Next Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	
	
	public void CreateAssigneeWorflowApprovalSetUpConfig() {
		try {
			MenuBarPage = returnMenuPage();
			adminPage = MenuBarPage.clickOnAdminMenu();
			adminassigneeSetupConfiguration = adminPage.clickOnAssigneeSetupConfigButton();
			adminassigneeSetupConfiguration.verifyCreateWorkflowApprovalSetupForOneLevel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void ValidateDataRequestPeriodAggregations() {
		try {
			String strSelectPeriods = data.get("SelectPeriod");
			String[] arrSelectPeriods = strSelectPeriods.split(",");
			for (int i = 0; i < arrSelectPeriods.length; i++) {
				createDataRequestWithPeriodInDataRequestPage(arrSelectPeriods[i]);
				CreateAssigneeWorflowApprovalSetUpConfig();
				MenuBarPage = adminassigneeSetupConfiguration.returnMenuPage();
				DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
				validateIntiateWorkFlow(data.get("1stLevelApprovalOrgName"));
				MenuBarPage.clickOnDataRequestMenu();
				sleep(1000);
				SignInPage = MenuBarPage.logOut();
				HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverPortCoId"),
						data.get("1stLevelApproverPortCoPassword"), true);
				MenuBarPage = HomePage.returnMenuPage();
				MenuBarPage.clickOnHamburgerMenu();
				sleep(1000);
				TasksPage = MenuBarPage.clickOnTasksMenu();
				CatalogsPage = MenuBarPage.clickOnCatalogsMenu();
				CatalogsPage.clickOnCatalogInCatalogPage(data.get("CatalogName"));
				CatalogsPage.selectTasksInCatalogFilter(GlobalVariables.dataRequestName);
				CatalogsPage.ValidateEnterDRFromPortCoWithPeriod(arrSelectPeriods[i]);
				CatalogsPage.publishDRInCatalogPage();
				sleep(1000);
				SignInPage = MenuBarPage.logOut();
				HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverId"),
						data.get("1stLevelApproverPassword"), true);
				MenuBarPage = HomePage.returnMenuPage();
				MenuBarPage.clickOnHamburgerMenu();
				TasksPage = MenuBarPage.clickOnTasksMenu();
				TasksPage.ClickOnDataRequestApprovalTaskInTasksPage(data.get("OrgName"));
				TasksPage.ValidateOneLevelApprovalWithOrgEnteredDetails(arrSelectPeriods[i], data.get("OrgName"));
				TasksPage.ValidateAproveAllInTasksPage();
				sleep(1000);
				SignInPage = MenuBarPage.logOut();
				HomePage = SignInPage.SignInToPulsEsGApp();
				MenuBarPage = HomePage.returnMenuPage();
				MenuBarPage.clickOnHamburgerMenu();
				DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateIntiateWorkFlowForMultiLevelOrganization(String orgName) {
		try {
			String xpathDRName = "//div/p[text()='" + GlobalVariables.dataRequestName + "']";
			WebElement eleDRName = driver.findElement(By.xpath(xpathDRName));
			clickOn(eleDRName, GlobalVariables.dataRequestName);
			clickOn(btnIntiateWorkFlow, "IntiateWorkFlow Button");
			String xpathOrgNamechkbox;
			xpathOrgNamechkbox = "//span[text()= '" + orgName
					+ "']//preceding-sibling::div/div[@ref='eCheckbox']//input";
			clickOnElementWithDynamicXpath(xpathOrgNamechkbox, "organization");
			clickOn(btnSubmit, "Submit button");
			if (isElementPresent(msgSucessIntiateWorkFlow)) {
				passed("Successfully validated Sucessful DR Intiated Messege");
			} else {
				failed(driver, "Failed to validate Sucessful DR Intiated Messege");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ClickOnDataRequestApprovalTaskInTasksPage(String senderEntName) {
		String xpathDataRequestApproval = "//p[contains(@aria-label,'" + senderEntName
				+ "')]//parent::div//preceding-sibling::div[contains(text(),'Data Request Approval')]";
		clickOnElementWithDynamicXpath(xpathDataRequestApproval, "Data Approval Task");
	}
	// -----------------------------PM Smoke
	// Checklist-----------------------------------

	public void ValidateCreateDataRequestandValidateChips() {
		try {
			createDataRequestAndValidateInDataRequestPage("General");
			createAssigneSetupCOnfigForDR("One Level Approval Flow");
//			CreateAssigneeWorflowApprovalSetUpConfig();
			MenuBarPage = adminassigneeSetupConfiguration.returnMenuPage();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			validateIntiateWorkFlow(data.get("OrgName"));
			sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createDataRequestWithPeriodInDataRequestPage() {
		try {
			waitForElement(btnPlusCreateDR);
			clickOn(btnPlusCreateDR, "CreateData Request PlusIcon");
			clickOn(btnForwardArrowIcon, "Forward Arrow Icon");
			clickOn(btnForwardArrowIcon, "Forward Arrow Icon");
			clickOn(btnForwardArrowIcon, "Forward Arrow Icon");
			GlobalVariables.dataRequestName = "DRChip" + "_" + generateRandomString(2) + generateRandomNumber(3);
			enterText(txtDRName, "Data RequestName", GlobalVariables.dataRequestName);
			clickOn(lstSelectCatalog, "Select CatalogName");
			GlobalVariables.catalogName = data.get("CatalogName");
			String xpathCatalog = "//ul/li[text()='" + data.get("CatalogName") + "']";
			System.out.println(xpathCatalog);
			clickOnElementWithDynamicXpath(xpathCatalog, "CatalogName");
			clickOn(btnNext, "Next Button");
//	    selectPeriodDR();
			GlobalVariables.dueDate = dueDate.getAttribute("value");
			clickOn(btnNext, "Next Button");
			waitForElement(txtOverviewAndInstructions);
			enterText(txtOverviewAndInstructions, "OverViewInstructions", "Catalog Metric DR Flow");
			enterText(txtOverviewAndInstructions, "OverViewInstructions", " Test Instructions");
			clickOn(btnCreate, "Create Button");
			sleep(100);
			String xpathTopicNamechkbox = "//span[text()= '" + data.get("TopicName")
					+ "']//preceding-sibling::span[@ref='eCheckbox']//input";
			System.out.println(WaitForElementWithDynamicXpath(xpathTopicNamechkbox));
			WaitForElementWithDynamicXpath(xpathTopicNamechkbox);
			clickOnElementWithDynamicXpath(xpathTopicNamechkbox, "TopicName CheckBox");
			// clickOn(btnAddAll, "Add All button");
			clickOn(btnSave, "Save button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateDRChipsinWorkflowmonitor(String orgName, String enterpriseName) {
		try {
			Thread.sleep(5000);
			String xpathNameInWorkflowMonitor = "//div[@col-id='companyName' and text()='" + orgName
					+ "']/parent::div/div[@col-id='dataRequestName']//a[text()='" + GlobalVariables.dataRequestName
					+ "']";
			clickOnElementWithDynamicXpath(xpathNameInWorkflowMonitor, "DR Name Link in WorkFlow Monitor");
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught at ValidateDRNameLinkNavigationToValidPage => " + e.getMessage());
		}
	}

	public void validationMultipleLevelApprovalFlowWithMultipleAprovers() {
		try {
			String flow = data.get("ApprovalWorkFlowLevels").trim();
			createDRandAssigneSetupCOnfig(flow);
			MenuBarPage = adminassigneeSetupConfiguration.returnMenuPage();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			validateIntiateWorkFlowForMultiLevelOrganization(data.get("NoLevelApprovalOrgName"));
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("PortCoUserName"), data.get("PortCoPassword"), true);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			CatalogsPage = TasksPage.clickOnCatalogNameInTasksPage();
			CatalogsPage.ValidatepublishDRFromPortCoWithEvidenceUpload();
			SignInPage = MenuBarPage.logOut();
			// validate tasks for two approvers
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverId"),
					data.get("1stLevelApproverPassword"), false);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.ClickOnDataRequestApprovalTaskInTasksPage(data.get("NoLevelApprovalEnterpriseName"));
			TasksPage.validateMultiLevelMultiappoverDetailsInTasksPage(data.get("PeriodDesc"));
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelMultiApproverId"),
					data.get("1stLevelMultiApproverPassword"), false);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.ClickOnDataRequestApprovalTaskInTasksPage(data.get("NoLevelApprovalEnterpriseName"));
			TasksPage.validateMultiLevelMultiappoverDetailsInTasksPage(data.get("PeriodDesc"));
			TasksPage.validateClaimTaskBy1stLevelApproverInTasksPage(data.get("1stLevelMultiApproverId"));
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverId"),
					data.get("1stLevelApproverPassword"), false);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.ClickOnDataRequestApprovalTaskInTasksPage(data.get("NoLevelApprovalEnterpriseName"));
			TasksPage.validateClaimedStatusBy1stLevelApproverInTasksPage(data.get("1stLevelMultiApproverUserName"));
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelMultiApproverId"),
					data.get("1stLevelMultiApproverPassword"), false);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.ClickOnDataRequestApprovalTaskInTasksPage(data.get("NoLevelApprovalEnterpriseName"));
			TasksPage.validateReleaseTaskBy1stLevelApproverInTasksPage();// -----
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverId"),
					data.get("1stLevelApproverPassword"), false);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.ClickOnDataRequestApprovalTaskInTasksPage(data.get("NoLevelApprovalEnterpriseName"));
			TasksPage.validateClaimTaskBy1stLevelApproverInTasksPage(data.get("1stLevelApproverId"));
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelMultiApproverId"),
					data.get("1stLevelMultiApproverPassword"), false);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.ClickOnDataRequestApprovalTaskInTasksPage(data.get("NoLevelApprovalEnterpriseName"));// ----lates
			TasksPage.validateClaimedStatusBy1stLevelApproverInTasksPage(data.get("1stLevelApproverUserName"));
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverId"),
					data.get("1stLevelApproverPassword"), false);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.ClickOnDataRequestApprovalTaskInTasksPage(data.get("NoLevelApprovalEnterpriseName"));
			TasksPage.ValidateAproveAllInTasksPage();
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("2ndLevelApproverId"),
					data.get("2ndLevelApproverPassword"), false);// false
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.ClickOnDataRequestApprovalTaskInTasksPage(data.get("NoLevelApprovalEnterpriseName"));
			TasksPage.ValidateDetailsInMultilevelapproverAprovalTaskForSecondLevelApprover(data.get("PeriodDesc"));
			TasksPage.ValidateTaskAprovalInTasksPage();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			DataRequestPage.validateApprovalDRFlowStatusForCompleteApproval("1", "1");
			DataRequestPage.NavigateToDRWorkFlowMonitor();
			DataRequestPage.validateWorkFlowMonitorStatusForDRCompleteApproval("1", "100", "0");
			DataRequestPage.ValidateDRNameLinkNavigationToValidPage(data.get("NoLevelApprovalOrgName"),
					data.get("NoLevelApprovalEnterpriseName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FindBy(xpath = "//*[text()='Add Request Title Here']//following::input[@name='name']")
	private WebElement txtDRNameLatest;
	@FindBy(xpath = "//span[text()='Assign to']//following::input//parent::div")
	private WebElement lstAssignTo;
	@FindBy(xpath = "//button[text()='Cancel']//parent::div//following-sibling::div/button[text()='Confirm']")
	private WebElement btnConfirm2;
	@FindBy(xpath = "//button[@aria-label='Cancel']")
	private WebElement btnCancelInDR;
	@FindBy(xpath = "//article/div[contains(text(),'Requests Failed')]")
	private WebElement eleDRrequestFailedPercentage;
	@FindBy(xpath = "//div/button[contains(text(),'Completed:')]")
	private WebElement eleDRrequestCompletedPercentageLatest;
	@FindBy(xpath = "//div/button[contains(text(),'In process')]")
	private WebElement eleDRrequestInProcessPercentageLatest;
	@FindBy(xpath = "//div/button[contains(text(),'Failed')]")
	private WebElement eleDRrequestFailedPercentageLatest;
	@FindBy(xpath = "//span[text()='Metric Group Match']/span//input//parent::span")
	private WebElement btnMetricGroupMatch;
	@FindBy(xpath = "//button[text()='Track Request']")
	private WebElement btnTrackRequest;
	@FindBy(xpath = "//div/*[@class='task-grid-padding']")
	private WebElement btnGridView;
	@FindBy(xpath = "//div[text()='Auto-select metrics']//input[@type='checkbox']")
	private WebElement btnAutoselecMetrics;

	public void NavigateToDRWorkFlowMonitor() {
		try {
//			GlobalVariables.dataRequestName = "TestAutDRrand_suw174";          // delete
			MenuBarPage = returnMenuPage();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			String xpathDRName = "//div/p[text()='" + GlobalVariables.dataRequestName + "']";
			WebElement eleDRName = driver.findElement(By.xpath(xpathDRName));
			new Actions(driver).moveToElement(eleDRName).build().perform();
			waitForElement(btnTrackRequest);
			clickOn(btnTrackRequest, "Track Request");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	public void createDataRequestAndValidateInDataRequestPage(boolean isAllTopics) {
		try {
			waitForElement(btnPlusCreateDR);
			clickOn(btnPlusCreateDR, "CreateData Request PlusIcon");
			GlobalVariables.dataRequestName = "TestAutDRrand_" + generateRandomString(3) + generateRandomNumber(3);
			enterText(txtDRNameLatest, "Data RequestName", GlobalVariables.dataRequestName);
			clickOn(lstSelectCatalog, "Select CatalogName");
			GlobalVariables.catalogName = data.get("CatalogName");
			String xpathCatalog = "//ul/li[text()='" + data.get("CatalogName") + "']";
			System.out.println(WaitForElementWithDynamicXpath(xpathCatalog));
			System.out.println(WaitForElementWithDynamicXpath(xpathCatalog));
			scrollToViewElement(driver.findElement(By.xpath(xpathCatalog)));
			clickOnElementWithDynamicXpath(xpathCatalog, "CatalogName");
			clickOn(lstDataCollectionPeriod, "Select CatalogName");
			String xpathPeriod = "//ul/li[text()='" + data.get("PeriodDesc") + "']";
			clickOnElementWithDynamicXpath(xpathPeriod, data.get("PeriodDesc"));
			enterText(txtAddInstructions, "Add Instructions", " AutTest Instructions");
			scrollTo(btnCancelInDR);
			clickOn(btnNext, "Next Button");
			if(isAllTopics) {
				List<WebElement> listCheckbxTopicNames = driver.findElements(By.xpath("//span[@ref='eCheckbox']//input"));
				for (int i = 0; i < listCheckbxTopicNames.size(); i++) {
					WebElement weTopicChkbox = driver.findElement(By.xpath("//span[@ref='eCheckbox']//input[@aria-label='Press Space to toggle row selection (unchecked)']")); 
//					jsClick(weTopicChkbox, "Clicked On Topic Check box");
					weTopicChkbox.click();
				}
			}else {
			if (data.get("TopicName").contains(",")) {
				String[] strTopicNames = data.get("TopicName").split(",");
				for (int i = 0; i < strTopicNames.length; i++) {
					String xpathTopicNamechkbox = "//span[text()= '" + strTopicNames[i]
							+ "']//preceding-sibling::span[@ref='eCheckbox']//input";
					System.out.println(WaitForElementWithDynamicXpath(xpathTopicNamechkbox));
					WaitForElementWithDynamicXpath(xpathTopicNamechkbox);
					clickOnElementWithDynamicXpath(xpathTopicNamechkbox, "TopicName CheckBox");
				}
			} else {
				String xpathTopicNamechkbox = "//span[text()= '" + data.get("TopicName")
						+ "']//preceding-sibling::span[@ref='eCheckbox']//input";
				System.out.println(WaitForElementWithDynamicXpath(xpathTopicNamechkbox));
				WaitForElementWithDynamicXpath(xpathTopicNamechkbox);
				clickOnElementWithDynamicXpath(xpathTopicNamechkbox, "TopicName CheckBox");
			}
			}
			sleep(1000);
//           scrollToViewElement(btnNext);
			clickOn(btnNext, "Next Button");
			String xpathOrgNamechkbox = "//span[text()= '" + data.get("OrgName")
					+ "']//preceding-sibling::div/div[@ref='eCheckbox']//input";
			clickOnElementWithDynamicXpath(xpathOrgNamechkbox, "organization");
			clickOn(btnNext, "Next Button");
			waitForElement(btnConfirm);
			clickOn(btnConfirm, "Confirm Button");
			clickOn(btnConfirm2, "Confirm2 Button");
			waitForElement(btnClose);
			clickOn(btnClose, "Close Button");
			validateCreatedDataRequestInDataRequestPage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	List<String> lsttopicNames = new ArrayList();
	public void createDataRequestAndValidateInDataRequestPagewithVerifyingSummary(boolean isAllTopics) {
		try {
			waitForElement(btnPlusCreateDR);
			clickOn(btnPlusCreateDR, "CreateData Request PlusIcon");
			GlobalVariables.dataRequestName = "TestAutDRrand_" + generateRandomString(3) + generateRandomNumber(3);
			enterText(txtDRNameLatest, "Data RequestName", GlobalVariables.dataRequestName);
			clickOn(lstSelectCatalog, "Select CatalogName");
			GlobalVariables.catalogName = data.get("CatalogName");
			String xpathCatalog = "//ul/li[text()='" + data.get("CatalogName") + "']";
			System.out.println(WaitForElementWithDynamicXpath(xpathCatalog));
			System.out.println(WaitForElementWithDynamicXpath(xpathCatalog));
			scrollToViewElement(driver.findElement(By.xpath(xpathCatalog)));
			clickOnElementWithDynamicXpath(xpathCatalog, "CatalogName");
			clickOn(lstDataCollectionPeriod, "Select CatalogName");
			String xpathPeriod = "//ul/li[text()='" + data.get("PeriodDesc") + "']";
			clickOnElementWithDynamicXpath(xpathPeriod, data.get("PeriodDesc"));
			enterText(txtAddInstructions, "Add Instructions", " AutTest Instructions");
			scrollTo(btnCancelInDR);
			clickOn(btnNext, "Next Button");
			if(isAllTopics) {
				List<WebElement> listCheckbxTopicNames = driver.findElements(By.xpath("//span[@ref='eCheckbox']//input"));
				for (int i = 0; i < listCheckbxTopicNames.size(); i++) {
					WebElement weTopicChkbox = driver.findElement(By.xpath("//span[@ref='eCheckbox']//input[@aria-label='Press Space to toggle row selection (unchecked)']")); 
//					jsClick(weTopicChkbox, "Clicked On Topic Check box");
					weTopicChkbox.click();
				}
				List<WebElement> listTopicName = driver.findElements(By.xpath("//span[@ref='eCheckbox']//parent::span/span[@ref='eValue']"));
				for (int i = 0; i < listTopicName.size(); i++) {
					String s1 = listTopicName.get(i).getText();
					lsttopicNames.add(s1);
				}
			}else {
			if (data.get("TopicName").contains(",")) {
				String[] strTopicNames = data.get("TopicName").split(",");
				for (int i = 0; i < strTopicNames.length; i++) {
					String xpathTopicNamechkbox = "//span[text()= '" + strTopicNames[i]
							+ "']//preceding-sibling::span[@ref='eCheckbox']//input";
					System.out.println(WaitForElementWithDynamicXpath(xpathTopicNamechkbox));
					WaitForElementWithDynamicXpath(xpathTopicNamechkbox);
					clickOnElementWithDynamicXpath(xpathTopicNamechkbox, "TopicName CheckBox");
				}
			} else {
				String xpathTopicNamechkbox = "//span[text()= '" + data.get("TopicName")
						+ "']//preceding-sibling::span[@ref='eCheckbox']//input";
				System.out.println(WaitForElementWithDynamicXpath(xpathTopicNamechkbox));
				WaitForElementWithDynamicXpath(xpathTopicNamechkbox);
				clickOnElementWithDynamicXpath(xpathTopicNamechkbox, "TopicName CheckBox");
			}
			}
			sleep(5000);
//           scrollToViewElement(btnNext);
			clickOn(btnNext, "Next Button");
			String xpathOrgNamechkbox = "//span[text()= '" + data.get("OrgName")
					+ "']//preceding-sibling::div/div[@ref='eCheckbox']//input";
			clickOnElementWithDynamicXpath(xpathOrgNamechkbox, "organization");
			clickOn(btnNext, "Next Button");
			validateDataReaquestSummary(isAllTopics,"Catalog","Organization Admin (Default)",1,3,15,1,data.get("OrgName"));
			scrollToViewElement(btnConfirm);
			clickOn(btnConfirm, "Confirm Button");
			clickOn(btnConfirm2, "Confirm2 Button");
			waitForElement(btnClose);
			clickOn(btnClose, "Close Button");
			validateCreatedDataRequestInDataRequestPage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void createDRandSigninWithProtcoandClickingonTasksGridWithSummaryHistory() {
		try {
			createDataRequestAndValidateInDataRequestPagewithVerifyingSummary(true);
			MenuBarPage = returnMenuPage();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			DataRequestPage.NavigateToDRWorkFlowMonitor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void createDRandSigninWithProtcoandClickingonTasksGrid() {
		try {
			createDataRequestAndValidateInDataRequestPage(true);
			MenuBarPage = returnMenuPage();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			DataRequestPage.NavigateToDRWorkFlowMonitor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void selectRecipientUserRole(String role) {
		try {
			String xpathReceptUserRole = "//span[text()='" + role + "']//parent::div";
			WaitForElementWithDynamicXpath(xpathReceptUserRole);
			clickOnElementWithDynamicXpath(xpathReceptUserRole, role);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FindBy(xpath = "//div[text()='Auto-select metrics']//input//parent::span")
	private WebElement btnAutoSelectMetrics;
	public void clickOnAutoSelectMetricButton() {
		try {
			waitForElement(btnAutoSelectMetrics);
			Actions action = new Actions(driver);
			action.moveToElement(btnAutoSelectMetrics).click().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FindBy(xpath = "//div[contains(text(),'Created request successfully!')]")
	private WebElement msgSuccessRequestCreate;
	@FindBy(xpath = "//*[text()='Allow Respondents To Publish Responses Multiple Times']//following::input[@type='checkbox']//parent::span")
	private WebElement btnAllowRespondentsPublishMultTimes;
	@FindBy(xpath = "//span[text()='Topic Name']//preceding::input[@type='checkbox']//parent::span")
	private WebElement btnAllMeticSelectCheckbox;
	@FindBy(xpath = "//button[@aria-label='Close']")
	private WebElement btnCloseAfterCreateDR;
	public void createDataRequestAndValidateInDataRequestPage(String drType) {
		try {
			waitForElement(btnPlusCreateDR);
			clickOn(btnPlusCreateDR, "CreateData Request PlusIcon");
			GlobalVariables.dataRequestName = "TestAutDRrand_" + generateRandomString(3) + generateRandomNumber(3);
			enterText(txtDRNameLatest, "Data RequestName", GlobalVariables.dataRequestName);
			System.out.println("txtDRNameLatest");
			clickOn(lstSelectCatalog, "Select CatalogName");
			GlobalVariables.catalogName = data.get("CatalogName");
			String xpathCatalog = "//ul/li[text()='" + data.get("CatalogName") + "']";
			System.out.println(WaitForElementWithDynamicXpath(xpathCatalog));
			System.out.println(WaitForElementWithDynamicXpath(xpathCatalog));
			scrollToViewElement(driver.findElement(By.xpath(xpathCatalog)));
			clickOnElementWithDynamicXpath(xpathCatalog, "CatalogName");
			clickOn(lstDataCollectionPeriod, "Select CatalogName");
			String weperiod = "//ul/li[text()='" + data.get("Period") + "']";
			System.out.println(WaitForElementWithDynamicXpath(weperiod));
			scrollToViewElement(driver.findElement(By.xpath(weperiod)));
			WebElement weperiodlist = driver.findElement(By.xpath("//ul/li[text()='" + data.get("Period") + "']"));
			clickOn(weperiodlist, data.get("Period"));
//			String xpathPeriod = "//ul/li[text()='" + data.get("PeriodDesc") + "']";
//			clickOnElementWithDynamicXpath(xpathPeriod, data.get("PeriodDesc"));
			if ((drType.equalsIgnoreCase("DealOrg"))) {
				selectRecipientUserRole(data.get("RecipientUserRole"));
			}
			enterText(txtAddInstructions, "Add Instructions", " AutTest Instructions");
			if (drType.equalsIgnoreCase("SingleDDPublish")) {
				// if disable need to add
				clickOn(btnAllowRespondentsPublishMultTimes, "AllowRespondentsPublishMultTimes");
			}
			scrollTo(btnCancelInDR);
			waitForElement(btnNext);
			clickOn(btnNext, "Next Button");
			if (drType.equalsIgnoreCase("General") || drType.equalsIgnoreCase("DealOrg")
					|| drType.equalsIgnoreCase("SingleDDPublish")) {
				String xpathTopicNamechkbox = "//span[text()= '" + data.get("TopicName")
						+ "']//preceding-sibling::span[@ref='eCheckbox']//input";
				System.out.println(WaitForElementWithDynamicXpath(xpathTopicNamechkbox));
				WaitForElementWithDynamicXpath(xpathTopicNamechkbox);
				clickOnElementWithDynamicXpath(xpathTopicNamechkbox, "TopicName CheckBox");
			} else if (drType.equalsIgnoreCase("AutoSelectMetrics")) {
				clickOnAutoSelectMetricButton();
			} else if (drType.equalsIgnoreCase("AllTopicAndMetricSelect")) {
				clickOn(btnAllMeticSelectCheckbox, "All metric Select Check box");
			}
			clickOn(btnNext, "Next Button");
			if (isElementPresent(msgSuccessRequestCreate)) {
				passed("Successfully validated Request created Sucess Message");
			} else {
				failed(driver, "Failed To validate Request created Sucess message");
			}
			waitForElement(btnCloseAfterCreateDR);
			clickOn(btnCloseAfterCreateDR, "Close button ");
			validateCreatedDataRequestInDataRequestPage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createAssigneSetupCOnfigForDR(String flowName) {
		try {
			MenuBarPage = returnMenuPage();
			adminPage = MenuBarPage.clickOnAdminMenu();
			
			
			adminassigneeSetupConfiguration = adminPage.clickOnAssigneeSetupConfigButton();
			switch (flowName) {
			case "No Level Approval Flow":
				adminassigneeSetupConfiguration.verifyCreateWorkflowApprovalSetupForNoLevel();
				break;
			case "One Level Approval Flow":
				adminassigneeSetupConfiguration.verifyCreateWorkflowApprovalSetupForOneLevel();
				break;
			default:
				warn("No Flow is slected");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void validateIntiateWorkFlow(String orgName) {
		try {
			String xpathDRName = "//div/p[text()='" + GlobalVariables.dataRequestName + "']";
			WebElement eleDRName = driver.findElement(By.xpath(xpathDRName));
			clickOn(eleDRName, GlobalVariables.dataRequestName);
			clickOn(btnNext, "Next Button");

			//scrollTo(btnCancelInDR);

			waitForElement(btnNext);
			clickOn(btnNext, "Next Button");
//			String xpathOrgNamechkbox = "//span[text()= '" + orgName
//					+ "']//preceding-sibling::div/div[@ref='eCheckbox']//input";
//			WaitForElementWithDynamicXpath(xpathOrgNamechkbox);
//			clickOnElementWithDynamicXpath(xpathOrgNamechkbox, "organization");
			WebElement ele = driver.findElement(By.xpath("//span[text()= '"+orgName+"']//preceding-sibling::div/div[@ref='eCheckbox']//input"));
			jsClick(ele, "organization");
			sleep(1000);
			waitForElement(btnNext);
			jsClick(btnNext, "Next Button");
			waitForElement(btnConfirm);
			clickOn(btnConfirm, "Confirm Button");
			clickOn(btnConfirm2, "Confirm2 Button");
			waitForElement(btnClose);
			clickOn(btnClose, "Close Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	public void createDataRequestAndValidateInDataRequestPageForTiffany(boolean isAllTopics) {
		try {
			waitForElement(btnPlusCreateDR);
			clickOn(btnPlusCreateDR, "CreateData Request PlusIcon");
			GlobalVariables.dataRequestName = "TestAutDRrand_" + generateRandomString(3) + generateRandomNumber(3);
			enterText(txtDRNameLatest, "Data RequestName", GlobalVariables.dataRequestName);
			clickOn(lstSelectCatalog, "Select CatalogName");
			GlobalVariables.catalogName = data.get("CatalogName");
			String xpathCatalog = "//ul/li[text()='" + data.get("CatalogName") + "']";
			System.out.println(WaitForElementWithDynamicXpath(xpathCatalog));
			System.out.println(WaitForElementWithDynamicXpath(xpathCatalog));
			scrollToViewElement(driver.findElement(By.xpath(xpathCatalog)));
			clickOnElementWithDynamicXpath(xpathCatalog, "CatalogName");
			clickOn(lstDataCollectionPeriod, "Select CatalogName");
			String xpathPeriod = "//ul/li[text()='" + data.get("PeriodDesc") + "']";
			clickOnElementWithDynamicXpath(xpathPeriod, data.get("PeriodDesc"));
			
			WebElement weRecipientRole = driver.findElement(By.xpath("//span[text()='Select Recipient User Role(s)']/parent::div/div//div/span[text()='"+data.get("RecipientRole")+"']"));
			jsClick(weRecipientRole, "Select recipient role"+data.get("RecipientRole"));
			
			enterText(txtAddInstructions, "Add Instructions", " AutTest Instructions");
			scrollTo(btnCancelInDR);
			clickOn(btnNext, "Next Button");
			if(isAllTopics) {
				List<WebElement> listCheckbxTopicNames = driver.findElements(By.xpath("//span[@ref='eCheckbox']//input"));
				for (int i = 0; i < listCheckbxTopicNames.size(); i++) {
					WebElement weTopicChkbox = driver.findElement(By.xpath("//span[@ref='eCheckbox']//input[@aria-label='Press Space to toggle row selection (unchecked)']")); 
//					jsClick(weTopicChkbox, "Clicked On Topic Check box");
					weTopicChkbox.click();
				}
			}else {
			if (data.get("TopicName").contains(",")) {
				String[] strTopicNames = data.get("TopicName").split(",");
				for (int i = 0; i < strTopicNames.length; i++) {
					String xpathTopicNamechkbox = "//span[text()= '" + strTopicNames[i]
							+ "']//preceding-sibling::span[@ref='eCheckbox']//input";
					System.out.println(WaitForElementWithDynamicXpath(xpathTopicNamechkbox));
					WaitForElementWithDynamicXpath(xpathTopicNamechkbox);
					clickOnElementWithDynamicXpath(xpathTopicNamechkbox, "TopicName CheckBox");
				}
			} else {
				String xpathTopicNamechkbox = "//span[text()= '" + data.get("TopicName")
						+ "']//preceding-sibling::span[@ref='eCheckbox']//input";
				System.out.println(WaitForElementWithDynamicXpath(xpathTopicNamechkbox));
				WaitForElementWithDynamicXpath(xpathTopicNamechkbox);
				clickOnElementWithDynamicXpath(xpathTopicNamechkbox, "TopicName CheckBox");
			}
			}
			sleep(1000);
//           scrollToViewElement(btnNext);
			clickOn(btnNext, "Next Button");
			/*
			 * waitForElement(btnConfirm); clickOn(btnConfirm, "Confirm Button");
			 * clickOn(btnConfirm2, "Confirm2 Button"); waitForElement(btnClose);
			 * clickOn(btnClose, "Close Button");
			 */
			//validateCreatedDataRequestInDataRequestPage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void validateIntiateWorkFlowTiffany(String orgName) {
        try {
            String xpathDRName = "//div/p[text()='" + GlobalVariables.dataRequestName + "']";
            WebElement eleDRName = driver.findElement(By.xpath(xpathDRName));
            clickOn(eleDRName, GlobalVariables.dataRequestName);
            clickOn(btnNext, "Next Button");
            waitForElement(btnNext);
            clickOn(btnNext, "Next Button");
            selectOrgsInCreationOfDRFromHierarchy(orgName);
            sleep(1000);
            waitForElement(btnNext);
            jsClick(btnNext, "Next Button");
            waitForElement(btnConfirm);
            clickOn(btnConfirm, "Confirm Button");
            clickOn(btnConfirm2, "Confirm2 Button");
            waitForElement(btnClose);
            clickOn(btnClose, "Close Button");
        } catch (Exception e) {
            failed(driver, "Exception caught " + e.getMessage());
        }
    }


	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement btnSearchOrg;
	
	public void selectOrgsInCreationOfDRFromHierarchy(String orgNames) {
	try {
		String[] organizations = orgNames.split(",");
		for (int i = 0; i < organizations.length; i++) {
			enterText(btnSearchOrg, "Search Org", organizations[i]);
			WebElement orgNameAssigneSetup = driver
					.findElement(By.xpath("//li[@role='treeitem']//div[text()='" + organizations[i] + "']"));
			clickOn(orgNameAssigneSetup, "Select org " + organizations[i]);
			WebElement orgRHPAssigneSetupCheckBox = driver
					.findElement(By.xpath("//span[text()='" + organizations[i] + "']//parent::div/div//input"));
			jsClick(orgRHPAssigneSetupCheckBox, "Selected org checkBox" + organizations[i]);
			sleep(2000);
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

	public void CreateAssigneeWorflowApprovalSetUpConfigTiffany() {
		try {
			MenuBarPage = returnMenuPage();
			adminPage = MenuBarPage.clickOnAdminMenu();
			adminassigneeSetupConfiguration = adminPage.clickOnAssigneeSetupConfigButtonTiffany();
			adminassigneeSetupConfiguration.verifyCreateWorkflowApprovalSetupForOneLevelTiffany();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void validateTiffanyDRFlow() {
		
		try {
			createDataRequestAndValidateInDataRequestPageForTiffany(false);
			CreateAssigneeWorflowApprovalSetUpConfigTiffany();
			MenuBarPage.clickOnDataRequestMenu();
			validateIntiateWorkFlowTiffany(data.get("OrgName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void ValidateDRNameLinkNavigationToValidPageTiffanyFlow() {
		try {
			String[] organizations = data.get("OrgName").split(",");
			for (int i = 0; i < organizations.length; i++) {
				if(i == 0) {
				enterText(btnSearchOrg, "Search Org", organizations[i]);
				WebElement orgNameAssigneSetup = driver
						.findElement(By.xpath("//li[@role='treeitem']//div[text()='" + organizations[i] + "']"));
				clickOn(orgNameAssigneSetup, "Select org " + organizations[i]);
			String xpathNameInWorkflowMonitor = "//div[@col-id='companyName' and text()='" + organizations[i]
					+ "']/parent::div/div[@col-id='dataRequestName']//a[text()='" + GlobalVariables.dataRequestName
					+ "']";
			clickOnElementWithDynamicXpath(xpathNameInWorkflowMonitor, "DR Name Link in WorkFlow Monitor");
			Thread.sleep(5000);
				}
				enterText(btnSearchOrg, "Search Org", organizations[i]);
				WebElement orgNameAssigneSetup = driver
						.findElement(By.xpath("//li[@role='treeitem']//div[text()='" + organizations[i] + "']"));
				clickOn(orgNameAssigneSetup, "Select org " + organizations[i]);
			String xpathCatalogName = "//div/*[contains(text(),'" + data.get("CatalogName") + "')]";
			WebElement catalogName = driver.findElement(By.xpath(xpathCatalogName));
			verifyElementAndHighlightIfExists(catalogName, "Navigated to = > " + data.get("CatalogName"),
					"Catalog Details Page");
			if (isElementPresent(catalogName)) {
				passed("Successfully validated DR Link navigation To valid Page from WorkFlow Monitor ");
			} else {
				failed(driver, "Failed to validate DR Link navigation To valid Page from WorkFlow Monitor");
			}
			String xpathDRname = "//span[text()='Data Request: ']/following-sibling::div//span[@aria-label='"
					+ GlobalVariables.dataRequestName + "']";
			WebElement weDRname = driver.findElement(By.xpath(xpathDRname));
			verifyElementAndHighlightIfExists(weDRname,
					"Selected data request name = > " + GlobalVariables.dataRequestName, "Catalog Details Page");
			String xpathPeriodName = "//span[text()='Period: ']/following-sibling::span/span[text()='"
					+ data.get("PeriodDesc") + "']";
			WebElement wePeriodName = driver.findElement(By.xpath(xpathPeriodName));
			verifyElementAndHighlightIfExists(wePeriodName, "Selected Period name = > " + data.get("PeriodDesc"),
					"Catalog Details Page");
			
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			WebElement xpathExpandDataRequest1 =driver.findElement(By.xpath(xpathExpandDataRequest));
			clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand  Data request Link");
			takeScreenshot(driver);
			validateUpdatedMetricValuesTiffanyDrFlow(data.get("MetricName"));
			}	
		} catch (Exception e) {
			failed(driver, "Exception caught at ValidateDRNameLinkNavigationToValidPage => " + e.getMessage());
		}
	}
	@FindBy(xpath = "//span[text()='Instructions']//parent::div//following-sibling::div//*[@data-testid='ExpandMoreIcon']")
	private WebElement btnExpandInstructions;
	@FindBy(xpath = "//span[text()='User Roles']//parent::div//following-sibling::div//*[@data-testid='ExpandMoreIcon']")
	private WebElement btnExpandUserRoles;
	@FindBy(xpath = "//span[text()='Catalog']//parent::div//following-sibling::div//*[@data-testid='ExpandMoreIcon']")
	private WebElement btnExpandCatalogsInDRSummary;
	@FindBy(xpath = "//span[text()='Selected Organizations']//parent::div//following-sibling::div//*[@data-testid='ExpandMoreIcon']")
	private WebElement btnExpandSelectedOrgsInDRSummary;
	public void validateDataReaquestSummary(boolean isAllTopics,String DRType ,String userrole ,int userRoleCount,int topicCount ,int metricCount,int OrgsCount,String orgName) {
		try {
//			GlobalVariables.dataRequestName = "TestAutDRrand_dyj976"; // delete
			WebElement txtDRNameSummary = driver.findElement(By.xpath("//div[text()='Data Request Title']//following-sibling::div[text()='"+GlobalVariables.dataRequestName+"']"));
			verifyElementAndHighlightIfExists(txtDRNameSummary, "DR Name Summary", "DR Name Summary");
			WebElement weDRType = driver.findElement(By.xpath("//div[text()='Data Request Type']//following-sibling::div[text()='"+DRType+"']"));
			verifyElementAndHighlightIfExists(weDRType, "Data Request", "Data Request Type");
			WebElement wePeriod = driver.findElement(By.xpath("//div[text()='Data Collection Period']//following-sibling::div[text()='"+data.get("PeriodDesc")+"']"));
			verifyElementAndHighlightIfExists(wePeriod, "Data Request", "Data Request Period");
			//  Occurrence
			WebElement weOccurrence = driver.findElement(By.xpath("//div[text()='Occurrence']//following-sibling::div[text()='OneTime']"));
			verifyElementAndHighlightIfExists(weOccurrence, "Data Request", "Data Request weOccurrence Type");
			// --- For future reference
//       	WebElement weDRDueDate = driver.findElement(By.xpath("//div[text()='Due Date']//following-sibling::div[text()='2023/11/09']"));
//        verifyElementAndHighlightIfExists(weDRDueDate, "Data Request", "Data Request Due Date");
			clickOn(btnExpandInstructions, "Clicked on Instructions Expand Button");
			// we can change accourding instruction what we are adding
			WebElement weInstructions = driver.findElement(By.xpath("//span[text()='Instructions']//parent::div//parent::div[@id='panel1a-header']//following-sibling::div//div/p[text()='AutTest Instructions']"));
			verifyElementAndHighlightIfExists(weInstructions, "Data Request", "Data Request Instructions");
			clickOn(btnExpandUserRoles, "Clicked on Expand button UserRoles");
			sleep(100);
			WebElement weUserRoleCounts = driver.findElement(By.xpath("//span[text()='User Roles']//parent::div//following-sibling::div//span[contains(text(),'"+userRoleCount+"')]"));
			verifyElementAndHighlightIfExists(weUserRoleCounts, "Data Request", "Data Request User Role Counts");
			WebElement weUserrole = driver.findElement(By.xpath("//span[text()='User Roles']//parent::div//parent::div[@id='panel1a-header']//following-sibling::div//div/*[text()='"+userrole+"']"));
			verifyElementAndHighlightIfExists(weUserrole, "Data Request", "Data Request User roles");
			clickOn(btnExpandCatalogsInDRSummary, "Clicked on Expand Button Catalogs In DR Summary");
			WebElement weTopicCount = driver.findElement(By.xpath("//span[text()='Catalog']//parent::div//following-sibling::div//span[text()='Topics']//parent::div//div/span[contains(text(),'"+topicCount+"')]"));
			verifyElementAndHighlightIfExists(weTopicCount, "Data Request", "Data Request Topic Count");
			WebElement weMetricCount = driver.findElement(By.xpath("//span[text()='Catalog']//parent::div//following-sibling::div//span[text()='Metrics']//parent::div//div/span[contains(text(),'"+topicCount+"')]"));
			verifyElementAndHighlightIfExists(weMetricCount, "Data Request", "Data Request Metric Count");
			if(isAllTopics) {
				for (int i = 0; i < lsttopicNames.size(); i++) {
					WebElement weTopicNamesSummary = driver.findElement(By.xpath("//span[text()='Catalog']//parent::div//parent::div[@id='panel1a-header']//following-sibling::div//div/span[text()='"+lsttopicNames.get(i)+"']"));
					verifyElementAndHighlightIfExists(weTopicNamesSummary, "Data Request", "Data Request Topic Names");
				}
			}else {
				if (data.get("TopicName").contains(",")) {
					String[] strTopicNames = data.get("TopicName").split(",");
					for (int i = 0; i < strTopicNames.length; i++) {
						WebElement weTopicNamesSummary = driver.findElement(By.xpath("//span[text()='Catalog']//parent::div//parent::div[@id='panel1a-header']//following-sibling::div//div/span[text()='"+strTopicNames[i]+"']"));
						verifyElementAndHighlightIfExists(weTopicNamesSummary, "Data Request", "Data Request Topic Names");	
					}
				} else {
					WebElement weTopicNamesSummary = driver.findElement(By.xpath("//span[text()='Catalog']//parent::div//parent::div[@id='panel1a-header']//following-sibling::div//div/span[text()='"+data.get("TopicName")+"']"));
					verifyElementAndHighlightIfExists(weTopicNamesSummary, "Data Request", "Data Request Topic Names");	
				}
			}
			clickOn(btnExpandSelectedOrgsInDRSummary, "Clicked on Selected Organization Expand Button");
			WebElement weOrgsCount = driver.findElement(By.xpath("//span[text()='Selected Organizations']//parent::div//following-sibling::div//span[contains(text(),'"+OrgsCount+"')]"));
			verifyElementAndHighlightIfExists(weOrgsCount, "Data Request", "Data Request User Organization Counts");
			WebElement weOrgNameSummary = driver.findElement(By.xpath("//span[text()='Selected Organizations']//parent::div//parent::div[@id='panel1a-header']//following-sibling::div//div/span[text()='"+orgName+"']"));
			verifyElementAndHighlightIfExists(weOrgNameSummary, "Data Request", "Data Request Organisation Names");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public void validateUpdatedMetricValuesTiffanyDrFlow(String metricName) {
		try {
			WebElement weMetricValue = driver.findElement(By.xpath("//span[text()='" + metricName + "']//parent::div"
					+ "//parent::div//parent::div//parent::div[@col-id='metricName']"
					+ "//following-sibling::div[@col-id='metricValue' and @role='gridcell']//span"));
			if (weMetricValue.getText().equals(data.get("RHPMetricValue"))) {
				passed("Successfully validated the values are updated in catalog through DR Flow as "
						+ weMetricValue.getText());
			} else {
				failed(driver, "Failed to validate the values are updated in catalog through DR Flow as "
						+ weMetricValue.getText());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
