package pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.select.Evaluator.IsEmpty;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import base.TestBase;
import utilities.Constants;
import utilities.Data;
import utilities.GlobalVariables;

public class TasksPage extends TestBase {
	protected TasksPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	public void ClickOnTasksChipsDataRequestApprovalTaskInTasksPage(String senderEntName) {
		String xpathDataRequestApproval = "//p[contains(@aria-label,'" + senderEntName
				+ "')]//parent::div//preceding-sibling::*[contains(text(),'Data Request Approval')]";
		WaitForElementWithDynamicXpath(xpathDataRequestApproval);
		clickOnElementWithDynamicXpath(xpathDataRequestApproval, "Data Approval Task");
		ValidateTasksChipsOneLevelApprovalWithOrgEnteredDetails();
	}

	@FindBy(xpath = "//button[@title='Needs Revision']")
	private WebElement btnNeedRevision;
	@FindBy(xpath = "//button[@title='Approve']")
	private WebElement btnApproveGrid;
	@FindBy(xpath = "//div[contains(text(),'Not Reviewed')]//following-sibling::span//*[@data-testid='KeyboardArrowDownIcon']")
	private WebElement drpTasksStatus;
	@FindBy(xpath = "//div[contains(@class,'MuiPaper-elevation')]/ul//div[contains(text(),'Tasks')]")
	private WebElement weTasksMenu;

	public void ValidateTasksChipsOneLevelApprovalWithOrgEnteredDetails() {
		try {
			Actions act = new Actions(driver);
			validateTasksDropDownStatus("Not Reviewed");
			act.moveToElement(btnClaimTask).perform();
			clickOn(btnClaimTask, "btnClaimTask");
			validateChipsTasksStatusForDRCompleteApproval("0", "0", "4");
			sleep(1000);
			act.moveToElement(btnReviseAll).perform();
			clickOn(btnReviseAll, "btnReviseAll");
			validateChipsTasksStatusForDRCompleteApproval("0", "4", "0");
			validateTasksDropDownStatus("Need Revision");
			sleep(1000);
			act.moveToElement(btnAproveAll).perform();
			sleep(1000);
			clickOn(btnAproveAll, "btnAproveAll");
			validateChipsTasksStatusForDRCompleteApproval("4", "0", "0");
			validateTasksDropDownStatus("Approved");
			sleep(1000);
			act.moveToElement(btnReviseAll).perform();
			clickOn(btnSubmit, "btnSubmit");
//    	    clickOn(weApproved, getCurrentDate());
			clickOn(btnYes, "btnYes");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateTasksDropDownStatus(String statusTasks) {
		try {
			clickOn(drpTasksStatus, "drpTasksStatus");
			WebElement drpTasksStatusValue = driver
					.findElement(By.xpath("//li//div[text()='" + statusTasks + "']//following-sibling::div"));
			if (drpTasksStatusValue.getText().contains("4")) {
				passed("Successfully validated chips count in thw dropdown");
			} else {
				failed(driver, "Failed to validate chips dropdown");
			}
			WebElement drpTasksStatus = driver.findElement(By.xpath("//li//div[text()='" + statusTasks + "']"));
			clickOn(drpTasksStatus, "drpTasksStatus");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//div[contains(text(),'Approved')]")
	private WebElement weApproved;
	@FindBy(xpath = "//div[contains(text(),'Need Revision')]")
	private WebElement weRevisison;
	@FindBy(xpath = "//div[contains(text(),'Not Reviewed')]")
	private WebElement weNotReviewd;

	public void validateChipsTasksStatusForDRCompleteApproval(String statusApproved, String statusRevision,
			String statusNotReviewed) {
		try {
			Thread.sleep(1000);
			waitForElement(weApproved);
			verifyElementAndHighlightIfExists(weApproved, "Tasks Approved chips Completed status",
					"Tasks chips Status");
			if (weApproved.getText().contains(statusApproved)) {
				passed("Successfully validated the Tasks Approved Status count as " + weApproved.getText());
			} else {
				failed(driver, "Failed to validate the Tasks Approved Status count is " + weApproved.getText());
			}
			verifyElementAndHighlightIfExists(weRevisison, "Tasks Revisison chips Completed status",
					"Tasks chips Status");
			if (weRevisison.getText().contains(statusRevision)) {
				passed("Successfully validated the Tasks Revisison Status count as " + weRevisison.getText());
			} else {
				failed(driver, "Failed to validate the Tasks Revisison Status count is " + weRevisison.getText());
			}
			verifyElementAndHighlightIfExists(weNotReviewd, "Tasks NotReviewd chips Completed status",
					"Tasks chips Status");
			if (weNotReviewd.getText().contains(statusNotReviewed)) {
				passed("Successfully validated the Tasks NotReviewd Status count as " + weNotReviewd.getText());
			} else {
				failed(driver, "Failed to validate the Tasks NotReviewd Status count is " + weNotReviewd.getText());
			}
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
	// Yogesh
	// Code-------------------------------------------------------------------------------------------

	@FindBy(xpath = "//*[contains(text(),'Task Management')]")
	private WebElement lblTasksPage;
	@FindBy(xpath = "//div[contains(text(),'Claim Successful')]")
	private WebElement msgClaimSuccess;
	@FindBy(xpath = "//span[contains(text(),'All approved successfully!')]")
	private WebElement msgSuccess;
	@FindBy(xpath = "//button[contains(text(),'Claim Task')]")
	private WebElement btnClaimTask;
	@FindBy(xpath = "//span[contains(text(),'Approve All')]//parent::button")
	private WebElement btnAproveAll;
	@FindBy(xpath = "//button[text()='Submit & Next']")
	private WebElement btnSubmit;
	@FindBy(xpath = "//button[contains(text(),'Yes')]")
	private WebElement btnYes;

	public CatalogPage clickOnCatalogNameInTasksPage() {
		try {
//			GlobalVariables.dataRequestName = "DRDR_0716"; // delete
			String xpathDataRequest = "//p[text()='Request Task']//preceding-sibling::div[text()='"
					+ GlobalVariables.dataRequestName + "']";
			clickOnElementWithDynamicXpath(xpathDataRequest, "Data Request Task");
			String xpathCataloglink = "//li//p[text()='" + data.get("CatalogName") + "']";
			clickOnElementWithDynamicXpath(xpathCataloglink, "catalog Name Link");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
		return new CatalogPage(driver, data);
	}

	public void ValidateOneLevelAprovalInTasksPage() {
		try {
			String xpathDataRequestApproval = "//p[contains(@aria-label,'" + data.get("PortCoEntName")
					+ "')]//parent::div//preceding-sibling::div[contains(text(),'Data Request Approval')]";
			clickOnElementWithDynamicXpath(xpathDataRequestApproval, "Data Approval Task");
			clickOn(btnClaimTask, "ClaimTask button");
			if (isElementPresent(msgClaimSuccess)) {
				passed("Successfully Validated Claim task success message");
			} else {
				failed(driver, "Failed To Validate  Claim task success message");
			}
			clickOn(btnAproveAll, "Aprove all button");
			if (isElementPresent(msgSuccess)) {
				passed("Successfully Validated Approve ALL success message");
			} else {
				failed(driver, "Failed To Validate  Approve ALL success message");
			}
			clickOn(btnSubmit, "Submit button");
			clickOn(btnYes, "ConfirmYes Button");
			Thread.sleep(5000);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateMultiLevelAprovalInTasksPage() {
		try {
			String xpathDataRequestApproval = "//p[contains(@aria-label,'" + data.get("PortCoEntName")
					+ "')]//parent::div//preceding-sibling::div[contains(text(),'Data Request Approval')]";
			clickOnElementWithDynamicXpath(xpathDataRequestApproval, "Data Approval Task");
			String approver1Name = data.get("FirstLevelApproverName");
			String approver2Name = data.get("SecondLevelApproverName");
			String xpathApprover1Status = "//div[contains(text(),'Approver(s):')]//following-sibling::div/div[1]//div[contains(text(),'"
					+ approver1Name + "')]//parent::div//preceding-sibling::div//div/div/div";
			String xpathApprover2Status = "//div[contains(text(),'Approver(s):')]//following-sibling::div/div[2]//div[contains(text(),'"
					+ approver2Name + "')]//parent::div//preceding-sibling::div//div/div/div";
			WebElement eleApprover1Status = driver.findElement(By.xpath(xpathApprover1Status));
			WebElement eleApprover2Status = driver.findElement(By.xpath(xpathApprover2Status));
			if (eleApprover1Status.getText().trim().equals("Approved")) {
				passed("Successfully Validated 1 st level Approver status in Tasks Page");
			} else {
				failed(driver, "failed to Validate 1 st level Approver status in Tasks Page");
			}
			if (eleApprover2Status.getText().trim().equals("In Progress")) {
				passed("Successfully Validated 2 nd level Approver status in Tasks Page");
			} else {
				failed(driver, "failed to Validate 2 nd level Approver status in Tasks Page");
			}
			clickOn(btnClaimTask, "ClaimTask button");
			if (isElementPresent(msgClaimSuccess)) {
				passed("Successfully Validated Claim task success message");
			} else {
				failed(driver, "Failed To Validate  Claim task success message");
			}
			clickOn(btnAproveAll, "Aprove all button");
			if (isElementPresent(msgSuccess)) {
				passed("Successfully Validated Approve ALL success message");
			} else {
				failed(driver, "Failed To Validate  Approve ALL success message");
			}
			clickOn(btnSubmit, "Submit button");
			clickOn(btnYes, "ConfirmYes Button");
			Thread.sleep(5000);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//span[contains(text(),'Revise All')]//parent::button")
	private WebElement btnReviseAll;

	public void ValidateOneLevelRejectionInTasksPage() {
		try {
			String xpathDataRequestApproval = "//p[contains(@aria-label,'" + data.get("PortCoEntName")
					+ "')]//parent::div//preceding-sibling::div[contains(text(),'Data Request Approval')]";
			clickOnElementWithDynamicXpath(xpathDataRequestApproval, "Data Approval Task");
			clickOn(btnClaimTask, "ClaimTask button");
			// if (isElementPresent(msgClaimSuccess)) {
			// passed("Successfully Validated Claim task success message");
			//
			// } else {
			// failed(driver, "Failed To Validate Claim task success message");
			// }
			//
			// clickOn(btnAproveAll, "Aprove all button");
			Thread.sleep(5000);
			clickOn(btnReviseAll, "Revise/Reject all button");
			//
			// if (isElementPresent(msgSuccess)) {
			// passed("Successfully Validated Approve ALL success message");
			//
			// } else {
			// failed(driver, "Failed To Validate Approve ALL success message");
			// }
			clickOn(btnSubmit, "Submit button");
			clickOn(btnYes, "ConfirmYes Button");
			Thread.sleep(5000);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
//    public CatalogPage selectDataRequestReviewTask() {
//	try {
//	    String xpathDataRequestApproval = "(//*[contains(@aria-label,'" + data.get("PortCoEntName")
//		    + "')]//parent::div//preceding-sibling::div[contains(text(),'Data Request Review')])[1]";
//	    clickOnElementWithDynamicXpath(xpathDataRequestApproval, "Data Request Review Task");
//	    String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
//		    + "']//parent::span//preceding-sibling::span[@ref='eContracted']";
//	    WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
//	    String classExpand = weExpand.getAttribute("class");
//	    if (!classExpand.contains("hidden")) {
//		clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
//	    }
//	    String xpathReviewRequested = "//span[text()='" + data.get("MetricName")
//		    + "']/parent::div/parent::div/following-sibling::div//button[text()='Review Requested']";
//	    clickOnElementWithDynamicXpath(xpathReviewRequested, "Review Request");
//	    Thread.sleep(5000);
//	} catch (Exception e) {
//	    failed(driver, "Exception caught " + e.getMessage());
//	}
//	return new CatalogPage(driver, data);
//    }

	public boolean verifyIfDataRequestTaskIsExists() {
		boolean blnFnd = false;
		try {
			String xpathDataRequest = "//p[text()='Data Request Task']//preceding-sibling::div[text()='"
					+ GlobalVariables.dataRequestName + "']";
			WebElement weTaskCard = driver.findElement(By.xpath(xpathDataRequest));
			if (isElementPresent(weTaskCard)) {
				blnFnd = true;
			} else {
				blnFnd = false;
			}
		} catch (Exception e) {
			blnFnd = false;
		}
		return blnFnd;
	}

	public int getDataRequestApprovalCount() {
		int count = 0;
		List<WebElement> lstCards;
		try {
			String xpathDataRequestApprovalCards = "//div[text()='Data Request Approval']";
			lstCards = driver.findElements(By.xpath(xpathDataRequestApprovalCards));
			count = lstCards.size();
		} catch (Exception e) {
			return count;
		}
		return count;
	}

	@FindBy(xpath = "// div[contains(text(),'Assigned by')]/div")
	private WebElement txtAssignedBy;
	@FindBy(xpath = "// div[contains(text(),'Assigned to')]/div")
	private WebElement txtAssignedTo;
	@FindBy(xpath = "// div[contains(text(),'Due Date')]/div")
	private WebElement txtDueDate;
	@FindBy(xpath = "// div[contains(text(),'Period')]/div")
	private WebElement txtPeriod;
	@FindBy(xpath = "// span[@role='gridcell']//span[@ref='eValue']")
	private WebElement txtTopicName;
	@FindBy(xpath = "//span[text()='Actions:']//following-sibling::button[text()='Release Task']")
	private WebElement btnReleaseTask;

	public void ValidateOneLevelApprovalWithOrgEnteredDetails(String period, String assignedByOrgName) {
		try {
			String xpathDataRequestApproval = "//p[contains(@aria-label,'" + assignedByOrgName
					+ "')]//parent::div//preceding-sibling::div[contains(text(),'Data Request Approval')]";
			clickOnElementWithDynamicXpath(xpathDataRequestApproval, "Data Approval Task");
			if (btnClaimTask.isEnabled()) {
				passed("Successfully Validated Claim button Enable");
			} else {
				failed(driver, "Failed To Validate  Claim Button Enable");
			}
			if (!btnAproveAll.isEnabled()) {
				passed("Successfully Validated Approve All button Disable");
			} else {
				failed(driver, "Failed To Validate Approve All button Disable");
			}
			if (!btnReviseAll.isEnabled()) {
				passed("Successfully Validated Revise All button Disable");
			} else {
				failed(driver, "Failed To Validate Revise All button Disable");
			}
			if (!btnSubmit.isEnabled()) {
				passed("Successfully Validated Submit  button Disable");
			} else {
				failed(driver, "Failed To Validate Submit button Disable");
			}
			if (btnClaimTask.isEnabled()) {
				passed("Successfully Validated Claim button Enable");
			} else {
				failed(driver, "Failed To Validate  Claim Button Enable");
			}
			clickOn(btnClaimTask, "ClaimTask button");
			if (isElementPresent(msgClaimSuccess)) {
				passed("Successfully Validated Claim task success message");
			} else {
				// failed(driver, "Failed To Validate Claim task success message");
			}
			if (isElementPresent(btnReleaseTask)) {
				if (btnReleaseTask.isEnabled()) {
					passed("Successfully Validated Release Task Button");
				} else {
					failed(driver, "Failed To Validate  Release Task Button");
				}
			} else {
				failed(driver, "Failed To Validate visibility of   Release Task Button ");
			}
			if (btnAproveAll.isEnabled()) {
				passed("Successfully Validated Approve All button Enable");
			} else {
				failed(driver, "Failed To Validate Approve All button Enable");
			}
			if (btnReviseAll.isEnabled()) {
				passed("Successfully Validated Revise All button Enable");
			} else {
				failed(driver, "Failed To Validate Revise All button Enable");
			}
			if (btnSubmit.isEnabled()) {
				passed("Successfully Validated Submit  button Enable");
			} else {
				failed(driver, "Failed To Validate Submit button Enable");
			}
			waitForElement(txtAssignedBy);
			if (txtAssignedBy.getText().equalsIgnoreCase(assignedByOrgName)) {
				passed("Successfully Validated Assigned By As" + txtAssignedBy.getText());
			} else {
				failed(driver, "Failed To Validate Assigned By Expected" + assignedByOrgName + "But Actual is"
						+ txtAssignedBy.getText());
			}
			waitForElement(txtAssignedTo);
			if (txtAssignedTo.getText().equalsIgnoreCase(data.get("1stLevelApproverId"))) {
				passed("Successfully Validated Assigned To As" + txtAssignedTo.getText());
			} else {
				failed(driver, "Failed To Validate Assigned To Expected" + data.get("1stLevelApproverId")
						+ "But Actual is" + txtAssignedTo.getText());
			}
			waitForElement(txtDueDate);
			if (txtDueDate.getText().equals(GlobalVariables.dueDate)) {
				passed("Successfully Validated DueDate As" + txtDueDate.getText());
			} else {
				failed(driver, "Failed To Validate Assigned To Expected" + GlobalVariables.dueDate + "But Actual is"
						+ txtDueDate.getText());
			}
			waitForElement(txtPeriod);
			if (txtPeriod.getText().replaceAll("\\s", "").equalsIgnoreCase(period.replaceAll("\\s", ""))) {
				passed("Successfully Validated Period As" + txtPeriod.getText());
			} else {
				failed(driver, "Failed To Validate Period Expected" + period + "But Actual is" + txtPeriod.getText());
			}
			waitForElement(txtTopicName);
			if (txtTopicName.getText().equals(data.get("TopicName"))) {
				passed("Successfully Validated Topic Name  As" + txtTopicName.getText());
			} else {
				failed(driver, "Failed To Validate Topic Name Expected As" + data.get("TopicName") + "But Actual is"
						+ txtTopicName.getText());
			}
			String metricName = data.get("MetricName");
			String xpathMeticName = "// div[@col-id='metricName']//div[@class='contentDiv']/span[contains(text(),'"
					+ metricName + "')]";
			if (isElementPresentDynamicXpath(xpathMeticName)) {
				passed("Successfully Validated Metric Name in Ag Grid of Tasks Page As" + metricName);
			} else {
				failed(driver, "Failed To validate Metric Name in Ag Grid of Tasks Page  ");
			}
			String xpathMeticValue = "//span[contains(text(),'" + metricName
					+ "')]//ancestor::div[@col-id='metricName']//parent::div//child::div";
			WebElement metricVal = driver.findElement(By.xpath(xpathMeticValue));
			if (isElementPresentDynamicXpath(xpathMeticValue)) {
				passed("Successfully Validated Metric Value in Ag Grid of Tasks Page As" + metricVal.getText());
			} else {
				failed(driver, "Failed To validate Metric Value in Ag Grid of Tasks Page  ");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ClickOnDataRequestApprovalTaskInTasksPage(String senderEntName) {
		String xpathDataRequestApproval = "//p[contains(@aria-label,'" + senderEntName
				+ "')]//parent::div//preceding-sibling::div[contains(text(),'Data Request Approval')]";
		WaitForElementWithDynamicXpath(xpathDataRequestApproval);
		clickOnElementWithDynamicXpath(xpathDataRequestApproval, "Data Approval Task");
	}

	public void validateMultiLevelMultiappoverDetailsInTasksPage(String period) {
		try {
			if (btnClaimTask.isEnabled()) {
				passed("Successfully Validated Claim button Enable");
			} else {
				failed(driver, "Failed To Validate  Claim Button Enable");
			}
			if (!btnAproveAll.isEnabled()) {
				passed("Successfully Validated Approve All button Disable");
			} else {
				failed(driver, "Failed To Validate Approve All button Disable");
			}
			if (!btnReviseAll.isEnabled()) {
				passed("Successfully Validated Revise All button Disable");
			} else {
				failed(driver, "Failed To Validate Revise All button Disable");
			}
			if (!btnSubmit.isEnabled()) {
				passed("Successfully Validated Submit  button Disable");
			} else {
				failed(driver, "Failed To Validate Submit button Disable");
			}
			waitForElement(txtAssignedBy);
			if (txtAssignedBy.getText().equalsIgnoreCase(data.get("NoLevelApprovalOrgName"))) {// Changed
				passed("Successfully Validated Assigned By As" + txtAssignedBy.getText());
			} else {
				failed(driver, "Failed To Validate Assigned By Expected" + data.get("NoLevelApprovalOrgName")
						+ "But Actual is" + txtAssignedBy.getText());
			}
			waitForElement(txtDueDate);
			if (txtDueDate.getText().equals(GlobalVariables.dueDate)) {
				passed("Successfully Validated DueDate As" + txtDueDate.getText());
			} else {
				failed(driver, "Failed To Validate DueDate  Expected" + GlobalVariables.dueDate + "But Actual is"
						+ txtDueDate.getText());
			}
			waitForElement(txtPeriod);
			if (txtPeriod.getText().replaceAll("\\s", "").equalsIgnoreCase(period.replaceAll("\\s", ""))) {
				passed("Successfully Validated Period As" + txtPeriod.getText());
			} else {
				failed(driver, "Failed To Validate Period Expected" + period + "But Actual is" + txtPeriod.getText());
			}
			waitForElement(txtTopicName);
			if (txtTopicName.getText().equals(data.get("TopicName"))) {
				passed("Successfully Validated Topic Name  As" + txtTopicName.getText());
			} else {
				failed(driver, "Failed To Validate Topic Name Expected As" + data.get("TopicName") + "But Actual is"
						+ txtTopicName.getText());
			}
			String metricName = data.get("MetricName");
			String xpathMeticName = "// div[@col-id='metricName']//div[@class='contentDiv']/span[contains(text(),'"
					+ metricName + "')]";
			if (isElementPresentDynamicXpath(xpathMeticName)) {
				passed("Successfully Validated Metric Name in Ag Grid of Tasks Page As" + metricName);
			} else {
				failed(driver, "Failed To validate Metric Name in Ag Grid of Tasks Page  ");
			}
			// displayData
			String xpathMetricValue = "// span[contains(text(),'" + metricName
					+ "')]//ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']//span[text()='"
					+ GlobalVariables.inputValue + "']";
			String xpathMetricValue_demo = "// span[contains(text(),'" + metricName
					+ "')]//ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='displayData']//span[text()='"
					+ GlobalVariables.inputValue + "']";
			if (isElementPresentDynamicXpath(xpathMetricValue)) {
				passed("Successfully Validated Metric Value in Ag Grid of Tasks Page As " + data.get("InputValue"));
			} else {
				failed(driver, "Failed To validate Metric Value in Ag Grid of Tasks Page  ");
			}
			String xpathStatusFirstLevelApprover = "// div[contains(text(),'" + data.get("1stLevelApproverUserName")
					+ "')]//parent::div//parent::div/div[1]//div[contains(text(),'In Progress')]";
			if (isElementPresentDynamicXpath(xpathStatusFirstLevelApprover)) {
				passed("Successfully Validated First Level Approver  status As In Progress");
			} else {
				failed(driver, "Failed To validate First Level Approver  status As In Progress");
			}
			String xpathStatusFirstLevelMultiApprover = "// div[contains(text(),'"
					+ data.get("1stLevelMultiApproverUserName")
					+ "')]//parent::div//parent::div/div[1]//div[contains(text(),'In Progress')]";
			if (isElementPresentDynamicXpath(xpathStatusFirstLevelMultiApprover)) {
				passed("Successfully Validated First Level Multi Approver  status As In Progress");
			} else {
				failed(driver, "Failed To validate First Level Multi Approver  status As In Progress");
			}
			if (isElementPresentDynamicXpath(xpathStatusFirstLevelApprover)) {
				passed("Successfully Validated First Level Approver  status As In Progress");
			} else {
				failed(driver, "Failed To validate First Level Approver  status As In Progress");
			}
			String xpathStatusSecondLevelApprover = "// div[contains(text(),'" + data.get("2ndLevelApproverUserName")
					+ "')]//parent::div//parent::div/div[2]//div[contains(text(),'Pending')]";
			if (isElementPresentDynamicXpath(xpathStatusSecondLevelApprover)) {
				passed("Successfully Validated Second Level Approver  status As Pending");
			} else {
				failed(driver, "Failed To validate Second  Level Approver  status As Pending");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateClaimTaskBy1stLevelApproverInTasksPage(String approverId) {
		waitForElement(btnClaimTask);
		if (isElementPresent(btnClaimTask)) {
			clickOn(btnClaimTask, "ClaimTask button");
		}
		if (isElementPresent(msgClaimSuccess)) {
			passed("Successfully Validated Claim task success message");
		} else {
			// failed(driver, "Failed To Validate Claim task success message");
		}
		if (isElementPresent(btnReleaseTask)) {
			if (btnReleaseTask.isEnabled()) {
				passed("Successfully Validated Release Task Button");
			} else {
				failed(driver, "Failed To Validate  Release Task Button");
			}
		} else {
			failed(driver, "Failed To Validate visibility of   Release Task Button ");
		}
		if (btnAproveAll.isEnabled()) {
			passed("Successfully Validated Approve All button Enable");
		} else {
			failed(driver, "Failed To Validate Approve All button Enable");
		}
		if (btnReviseAll.isEnabled()) {
			passed("Successfully Validated Revise All button Enable");
		} else {
			failed(driver, "Failed To Validate Revise All button Enable");
		}
		if (btnSubmit.isEnabled()) {
			passed("Successfully Validated Submit  button Enable");
		} else {
			failed(driver, "Failed To Validate Submit button Enable");
		}
		waitForElement(txtAssignedTo);
		if (txtAssignedTo.getText().equalsIgnoreCase(approverId)) {
			passed("Successfully Validated Assigned To As" + txtAssignedTo.getText());
		} else {
			failed(driver,
					"Failed To Validate Assigned To Expected" + approverId + "But Actual is" + txtAssignedTo.getText());
		}
	}

	public void validateClaimedStatusBy1stLevelApproverInTasksPage(String approverUserName) {
		try {
			if (!btnAproveAll.isEnabled()) {
				passed("Successfully Validated Approve All button Disable");
			} else {
				failed(driver, "Failed To Validate Approve All button Disable");
			}
			if (!btnReviseAll.isEnabled()) {
				passed("Successfully Validated Revise All button Disable");
			} else {
				failed(driver, "Failed To Validate Revise All button Disable");
			}
			if (!btnSubmit.isEnabled()) {
				passed("Successfully Validated Submit  button Disable");
			} else {
				failed(driver, "Failed To Validate Submit button Disable");
			}
			waitForElement(txtAssignedTo);
			if (txtAssignedTo.getText().equalsIgnoreCase(approverUserName)) {
				passed("Successfully Validated Assigned To As" + txtAssignedTo.getText());
			} else {
				failed(driver, "Failed To Validate Assigned To Expected" + approverUserName + "But Actual is"
						+ txtAssignedTo.getText());
			}
			String xpathClaimedtxt = "//span[text()='Actions:']//following-sibling::div/div[text()='Claimed']";
			if (isElementPresentDynamicXpath(xpathClaimedtxt)) {
				passed("Successfully validate Claimed Text In Actions Of DR Tasks Page");
			} else {
				failed(driver, "Failed To validate Claimed Text In Actions Of DR Tasks Page ");
			}
			String xpathClaimedUserName = "//span[text()='Actions:']//following-sibling::div/div[text()='"
					+ approverUserName + "']";
			if (isElementPresentDynamicXpath(xpathClaimedUserName)) {
				passed("Successfully validate Claimed User Name In Actions Of DR Tasks Page As" + approverUserName);
			} else {
				failed(driver, "Failed To validate Claimed User Name In Actions Of DR Tasks Page ");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//div[contains(text(),'Task Released Successfully')]")
	private WebElement msgReleaseSuccess;

	public void validateReleaseTaskBy1stLevelApproverInTasksPage() {
		try {
			waitForElement(btnReleaseTask);
			if (isElementPresent(btnReleaseTask)) {
				clickOn(btnReleaseTask, "Release Task button");
			}
			if (isElementPresent(msgReleaseSuccess)) {
				passed("Successfully Validated Release task success message");
			} else {
				// failed(driver, "Failed To Validate Release task success message");
			}
			if (isElementPresent(btnClaimTask)) {
				if (btnClaimTask.isEnabled()) {
					passed("Successfully Validated Claim Task Button");
				} else {
					failed(driver, "Failed To Validate  Claim Task Button");
				}
			} else {
				failed(driver, "Failed To Validate visibility of   claim Task Button ");
			}
			if (!btnAproveAll.isEnabled()) {
				passed("Successfully Validated Approve All button Disable");
			} else {
				failed(driver, "Failed To Validate Approve All button Disable");
			}
			if (!btnReviseAll.isEnabled()) {
				passed("Successfully Validated Revise All button Disable");
			} else {
				failed(driver, "Failed To Validate Revise All button Disable");
			}
			if (!btnSubmit.isEnabled()) {
				passed("Successfully Validated Submit  button Disable");
			} else {
				failed(driver, "Failed To Validate Submit button Disable");
			}
			waitForElement(txtAssignedTo);
			if (txtAssignedTo.getText().isEmpty()) {
				passed("Successfully Validated Assigned To As Empty");
			} else {
				failed(driver,
						"Failed To Validate Assigned To Expected  Empty But Actual is" + txtAssignedTo.getText());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ValidateAproveAllInTasksPage() {
		try {
			Actions act = new Actions(driver);
			act.moveToElement(btnAproveAll).perform();
			waitForElement(btnAproveAll);
			clickOn(btnAproveAll, "Aprove all button");
//			clickOn(btnAproveAll, "Aprove all button");
			if (isElementPresent(msgSuccess)) {
				passed("Successfully Validated Approve ALL success message");
			} else {
				failed(driver, "Failed To Validate Approve ALL success message");
			}
			sleep(1000);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			sleep(2000);
			act.moveToElement(btnSubmit).perform();
			System.out.println(waitForElement(btnSubmit));
			clickOn(btnSubmit, "Submit button");
			clickOn(btnYes, "ConfirmYes Button");
			sleep(2000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ADD);
			robot.keyPress(KeyEvent.VK_ADD);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateDetailsInMultilevelapproverAprovalTaskForSecondLevelApprover(String period) {
		try {
			waitForElement(btnClaimTask);
			if (isElementPresent(btnClaimTask)) {
				clickOn(btnClaimTask, "ClaimTask button");
			}
			waitForElement(txtAssignedBy);
			if (txtAssignedBy.getText().equalsIgnoreCase(data.get("NoLevelApprovalOrgName"))) {// changed
				passed("Successfully Validated Assigned By As" + txtAssignedBy.getText());
			} else {
				failed(driver, "Failed To Validate Assigned By Expected" + data.get("NoLevelApprovalOrgName")
						+ "But Actual is" + txtAssignedBy.getText());
			}
			waitForElement(txtAssignedTo);
			if (txtAssignedTo.getText().equalsIgnoreCase(data.get("2ndLevelApproverId"))) {
				passed("Successfully Validated Assigned To As" + txtAssignedTo.getText());
			} else {
				failed(driver, "Failed To Validate Assigned To Expected" + data.get("2ndLevelApproverId")
						+ "But Actual is" + txtAssignedTo.getText());
			}
			waitForElement(txtDueDate);
			if (txtDueDate.getText().equals(GlobalVariables.dueDate)) {
				passed("Successfully Validated DueDate As" + txtDueDate.getText());
			} else {
				failed(driver, "Failed To Validate DueDate Expected" + GlobalVariables.dueDate + "But Actual is"
						+ txtDueDate.getText());
			}
			waitForElement(txtPeriod);
			if (txtPeriod.getText().replaceAll("\\s", "").equalsIgnoreCase(period.replaceAll("\\s", ""))) {
				passed("Successfully Validated Period As" + txtAssignedTo.getText());
			} else {
				failed(driver, "Failed To Validate Period Expected" + period + "But Actual is" + txtPeriod.getText());
			}
			waitForElement(txtTopicName);
			if (txtTopicName.getText().equals(data.get("TopicName"))) {
				passed("Successfully Validated Topic Name  As" + txtTopicName.getText());
			} else {
				failed(driver, "Failed To Validate Topic Name Expected As" + data.get("TopicName") + "But Actual is"
						+ txtTopicName.getText());
			}
			String metricName = data.get("MetricName");
			String xpathMeticName = "// div[@col-id='metricName']//div[@class='contentDiv']/span[contains(text(),'"
					+ metricName + "')]";
			if (isElementPresentDynamicXpath(xpathMeticName)) {
				passed("Successfully Validated Metric Name in Ag Grid of Tasks Page As" + metricName);
			} else {
				failed(driver, "Failed To validate Metric Name in Ag Grid of Tasks Page  ");
			}
			String xpathMetricValue_demo = "// span[contains(text(),'" + metricName
					+ "')]//ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='displayData']//span[text()='"
					+ GlobalVariables.inputValue + "']";
			String xpathMetricValue = "// span[contains(text(),'" + metricName
					+ "')]//ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']//span[text()='"
					+ GlobalVariables.inputValue + "']";
			WebElement metricValue = driver.findElement(By.xpath(xpathMetricValue));
			if (metricValue.getText().equals(GlobalVariables.inputValue)) {
				passed("Successfully Validated Metric Value in Ag Grid of Tasks Page As " + GlobalVariables.inputValue);
			} else {
				failed(driver, "Failed To validate Metric Value in Ag Grid of Tasks Page  ");
			}
			String xpathStatusFirstLevelApprover = "// div[contains(text(),'" + data.get("1stLevelApproverUserName")
					+ "')]//parent::div//parent::div/div[1]//div[contains(text(),'Approved')]";
			if (isElementPresentDynamicXpath(xpathStatusFirstLevelApprover)) {
				passed("Successfully Validated First Level Approver  status As In Approved");
			} else {
				failed(driver, "Failed To validate First Level Approver  status As In Approved");
			}
			String xpathStatusFirstLevelMultiApprover = "// div[contains(text(),'"
					+ data.get("1stLevelMultiApproverUserName")
					+ "')]//parent::div//parent::div/div[1]//div[contains(text(),'Approved')]";
			if (isElementPresentDynamicXpath(xpathStatusFirstLevelApprover)) {
				passed("Successfully Validated First Level Approver  status As In Approved");
			} else {
				failed(driver, "Failed To validate First Level Approver  status As In Approved");
			}
			String xpathStatusSecondLevelApprover = "// div[contains(text(),'" + data.get("2ndLevelApproverUserName")
					+ "')]//parent::div//parent::div/div[1]//div[contains(text(),'In Progress')]";
			if (isElementPresentDynamicXpath(xpathStatusSecondLevelApprover)) {
				passed("Successfully Validated Second Level Approver  status As In Progress");
			} else {
				failed(driver, "Failed To validate Second  Level Approver  status In Progress");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateTaskAprovalInTasksPage() {
		try {
			waitForElement(btnClaimTask);
			if (isElementPresent(btnClaimTask)) {
				clickOn(btnClaimTask, "ClaimTask button");
			}
			if (isElementPresent(msgClaimSuccess)) {
				passed("Successfully Validated Claim task success message");
			} else {
				// failed(driver, "Failed To Validate Claim task success message");
			}
			clickOn(btnAproveAll, "Aprove all button");
			if (isElementPresent(msgSuccess)) {
				passed("Successfully Validated Approve ALL success message");
			} else {
				// failed(driver, "Failed To Validate Approve ALL success message");
			}
			clickOn(btnSubmit, "Submit button");
			clickOn(btnYes, "ConfirmYes Button");
			Thread.sleep(5000);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateOneLevelRejectionInTasksPage(String period) {
		try {
			waitForElement(btnClaimTask);
			clickOn(btnClaimTask, "ClaimTask button");
			waitForElement(txtAssignedBy);
			if (txtAssignedBy.getText().equalsIgnoreCase(data.get("2ndLevelApprovalOrgName"))) {
				passed("Successfully Validated Assigned By As" + txtAssignedBy.getText());
			} else {
				failed(driver, "Failed To Validate Assigned By Expected" + data.get("OrgName") + "But Actual is"
						+ txtAssignedBy.getText());
			}
			waitForElement(txtAssignedTo);
			if (txtAssignedTo.getText().equalsIgnoreCase(data.get("1stLevelApproverId"))) {// 1stLevelApproverUserName
				passed("Successfully Validated Assigned To As" + txtAssignedTo.getText());
			} else {
				failed(driver, "Failed To Validate Assigned To Expected" + data.get("1stLevelApproverUserName")
						+ "But Actual is" + txtAssignedTo.getText());
			}
			waitForElement(txtDueDate);
			if (txtDueDate.getText().equals(GlobalVariables.dueDate)) {
				passed("Successfully Validated DueDate As" + txtDueDate.getText());
			} else {
				failed(driver, "Failed To Validate Assigned To Expected" + GlobalVariables.dueDate + "But Actual is"
						+ txtDueDate.getText());
			}
			waitForElement(txtPeriod);
			if (txtPeriod.getText().replaceAll("\\s", "").equalsIgnoreCase(period.replaceAll("\\s", ""))) {
				passed("Successfully Validated Period As" + txtPeriod.getText());
			} else {
				failed(driver, "Failed To Validate Period Expected" + period + "But Actual is" + txtPeriod.getText());
			}
			waitForElement(txtTopicName);
			if (txtTopicName.getText().equals(data.get("TopicName"))) {
				passed("Successfully Validated Topic Name  As" + txtTopicName.getText());
			} else {
				failed(driver, "Failed To Validate Topic Name Expected As" + data.get("TopicName") + "But Actual is"
						+ txtTopicName.getText());
			}
			String metricName = data.get("MetricName");
			String xpathMeticName = "// div[@col-id='metricName']//div[@class='contentDiv']/span[contains(text(),'"
					+ metricName + "')]";
			if (isElementPresentDynamicXpath(xpathMeticName)) {
				passed("Successfully Validated Metric Name in Ag Grid of Tasks Page As" + metricName);
			} else {
				failed(driver, "Failed To validate Metric Name in Ag Grid of Tasks Page  ");
			}
			String xpathMetricValue_demo = "// span[contains(text(),'" + metricName
					+ "')]//ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='displayData']//span[text()='"
					+ GlobalVariables.inputValue + "']";
			String xpathMetricValue = "// span[contains(text(),'" + metricName
					+ "')]//ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']//span[text()='"
					+ GlobalVariables.inputValue + "']";
			if (isElementPresentDynamicXpath(xpathMetricValue)) {
				passed("Successfully Validated Metric Value in Ag Grid of Tasks Page As " + GlobalVariables.inputValue);
			} else {
				failed(driver, "Failed To validate Metric Value in Ag Grid of Tasks Page  ");
			}
			String xpathStatusFirstLevelApprover = "// div[contains(text(),'" + data.get("1stLevelApproverUserName")
					+ "')]//parent::div//parent::div/div[1]//div[contains(text(),'In Progress')]";
			if (isElementPresentDynamicXpath(xpathStatusFirstLevelApprover)) {
				passed("Successfully Validated First Level Approver  status As In Progress");
			} else {
				failed(driver, "Failed To validate First Level Approver  status As In Progress");
			}
			String xpathStatusSecondLevelApprover = "// div[contains(text(),'" + data.get("2ndLevelApproverUserName")
					+ "')]//parent::div//parent::div/div[2]//div[contains(text(),'Pending')]";
			if (isElementPresentDynamicXpath(xpathStatusSecondLevelApprover)) {
				passed("Successfully Validated Second Level Approver  status As Pending");
			} else {
				failed(driver, "Failed To validate Second  Level Approver  status As Pending");
			}
			Robot robot = new Robot();
			for (int i = 0; i < 2; i++) {
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_SUBTRACT);
				robot.keyRelease(KeyEvent.VK_SUBTRACT);
				robot.keyRelease(KeyEvent.VK_CONTROL);
			}
			AddRejectCommentsInTasksPage();
			jsClick(btnReviseAll, "ReviseAll");
			waitForElement(btnSubmit);
			clickOn(btnSubmit, "Submit button");
			clickOn(btnYes, "ConfirmYes Button");
			Thread.sleep(5000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ADD);
			robot.keyPress(KeyEvent.VK_ADD);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//p[contains(text(),'Compare with task')]//following-sibling::div//*[@data-testid='ArrowDropDownIcon']//preceding-sibling::div ")
	private WebElement drpCompareWithTask;

	public void validateDetailsInApprovalTaskForCompareWithoutCompareTask(String currentData, String previousData,
			String currentFY, String previousFY) {
		try {
			String metricName = data.get("MetricName");
			String xpathMeticName = "// div[@col-id='metricName']//div[@class='contentDiv']/span[contains(text(),'"
					+ metricName + "')]";
			if (isElementPresentDynamicXpath(xpathMeticName)) {
				passed("Successfully Validated Metric Name in Ag Grid of Tasks Page As" + metricName);
			} else {
				failed(driver, "Failed To validate Metric Name in Ag Grid of Tasks Page  ");
			}
			String xpathMetricPreviousValue = "// span[contains(text(),'" + metricName
					+ "')]//ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='previousData']//span[text()='"
					+ previousData + "']";
			if (isElementPresentDynamicXpath(xpathMetricPreviousValue)) {
				passed("Successfully Validated Metric Value for previous FY in Ag Grid of Tasks Page As "
						+ previousData);
			} else {
				failed(driver, "Failed To validate Metric Value  for previous FY in Ag Grid of Tasks Page  ");
			}
			String xpathMetricCurrentValue = "//span[contains(text(),'" + metricName
					+ "')]//ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']//span[text()='"
					+ currentData + "']";
			if (isElementPresentDynamicXpath(xpathMetricCurrentValue)) {
				passed("Successfully Validated Metric Value for Current FY in Ag Grid of Tasks Page As " + currentData);
			} else {
				failed(driver, "Failed To validate Metric Value  for Current FY in Ag Grid of Tasks Page  ");
			}
			String xpathMetricPreviousValueColHeader = "//div[contains(text(),'Previous Data')]//following-sibling::div[contains(text(),'"
					+ previousFY + "')]";
			if (isElementPresentDynamicXpath(xpathMetricPreviousValueColHeader)) {
				passed("Successfully Validated Metric Value Header for previous  FY  in Ag Grid of Tasks Page As "
						+ previousFY);
			} else {
				failed(driver, "Failed To validate Metric Value Header for Current  FY  in Ag Grid of Tasks Page  ");
			}
			String xpathMetricCurrentValueColHeader = "//div[contains(text(),'Current Data')]//following-sibling::div[contains(text(),'"
					+ currentFY + "')]";
			if (isElementPresentDynamicXpath(xpathMetricCurrentValueColHeader)) {
				passed("Successfully Validated Metric Value Header for Current  FY  in Ag Grid of Tasks Page As "
						+ currentFY);
			} else {
				failed(driver, "Failed To validate Metric Value Header for Current  FY  in Ag Grid of Tasks Page  ");
			}
			waitForElement(drpCompareWithTask);
			clickOn(drpCompareWithTask, "Compare  with task ");
			String lstValue = "None";
			String xpathListItems = "//ul/li[contains(text(),'" + lstValue + "')]";
			if (isElementPresentDynamicXpath(xpathListItems)) {
				passed("Successfully validate comparision with task dropdown value  AS  " + lstValue);
			} else {
				failed(driver, "Failed To validate  comparision with task dropdown value  AS  " + lstValue);
			}
			clickOnElementWithDynamicXpath(xpathListItems, " None value ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//span[contains(text(),'Previous Task Data')]")
	private WebElement txtHeaderPreviousTaskData;//
	@FindBy(xpath = "//span[contains(text(),'Current Task Data')]")
	private WebElement txtHeaderCurrentTaskData;

	public void validateDetailsInApprovalTaskForCompareWithCompareTask(String currentData, String previousData) {
		try {
			String metricName = data.get("MetricName");
			String xpathMeticName = "// div[@col-id='metricName']//div[@class='contentDiv']/span[contains(text(),'"
					+ metricName + "')]";
			if (isElementPresentDynamicXpath(xpathMeticName)) {
				passed("Successfully Validated Metric Name in Ag Grid of Tasks Page As" + metricName);
			} else {
				failed(driver, "Failed To validate Metric Name in Ag Grid of Tasks Page  ");
			}
			waitForElement(drpCompareWithTask);
			clickOn(drpCompareWithTask, "Compare  with task ");
			String xpathListItems = "//ul/li[contains(text(),'DATA_REQUEST_REVIEW')]";
			if (isElementPresentDynamicXpath(xpathListItems)) {
				passed("Successfully validate comparision with task dropdown value  AS  "
						+ driver.findElement(By.xpath(xpathListItems)));
			} else {
				failed(driver, "Failed To validate  comparision with task dropdown value ");
			}
			clickOnElementWithDynamicXpath(xpathListItems, "Data request review task list");
			waitForElement(txtHeaderCurrentTaskData);
			if (isElementPresent(txtHeaderCurrentTaskData)) {
				passed("Successfully validated Current task data header After selecting data request review option from dropdown");
			} else {
				failed(driver,
						"Failed To validate Current task data header After selecting data request review option from dropdown");
			}
			if (isElementPresent(txtHeaderPreviousTaskData)) {
				passed("Successfully validated previous task data header After selecting data request review option from dropdown");
			} else {
				failed(driver,
						"Failed To validate previous task data header After selecting data request review option from dropdown");
			}
			// metricValue_1
			String xpathMetricCurrentValue = "// span[contains(text(),'" + metricName
					+ "')]//ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue_1']//span[text()='"
					+ currentData + "']";
			if (isElementPresentDynamicXpath(xpathMetricCurrentValue)) {
				passed("Successfully Validated Metric Value for Current FY in Ag Grid of Tasks Page As " + currentData);
			} else {
				failed(driver, "Failed To validate Metric Value  for Current FY in Ag Grid of Tasks Page  ");
			}
			String xpathMetricPreviousValue = "// span[contains(text(),'" + metricName
					+ "')]//ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='previousTaskData']//span[text()='"
					+ previousData + "']";
			if (isElementPresentDynamicXpath(xpathMetricPreviousValue)) {
				passed("Successfully Validated Metric Value for previous FY in Ag Grid of Tasks Page As "
						+ previousData);
			} else {
				failed(driver, "Failed To validate Metric Value  for previous FY in Ag Grid of Tasks Page  ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void rejectTaskWithCommentsInTasksPage() {
		try {
			waitForElement(btnClaimTask);
			clickOn(btnClaimTask, "ClaimTask button");
			sleep(5000);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			// AddRejectCommentsInTasksPage();
			waitForElement(btnReviseAll);
			clickOn(btnReviseAll, "ReviseAll");
			waitForElement(btnSubmit);
			clickOn(btnSubmit, "Submit button");
			clickOn(btnYes, "ConfirmYes Button");
			sleep(3000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ADD);
			robot.keyPress(KeyEvent.VK_ADD);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(5000);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//div[@col-id='comment']//img[@alt='edit']")
	private WebElement btnEditComments;
	@FindBy(xpath = "//textarea[@aria-label='Input Editor']")
	private WebElement txtAreaAddComments;

	public void AddRejectCommentsInTasksPage() {
		try {
			waitForElement(btnEditComments);
			clickOn(btnEditComments, "Edit comments");
			waitForElement(txtAreaAddComments);
			enterText(txtAreaAddComments, "Text Area comments", Constants.DRRejectComments);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void clickOnDataReviewTaskInTasksPage() {
		try {
			String xpathDataRequestApproval = "(//*[contains(@aria-label,'" + data.get("2ndLevelApproverPortCoId")
					+ "')]//parent::div//preceding-sibling::div[contains(text(),'Data Request Review')])[1]";
			clickOnElementWithDynamicXpath(xpathDataRequestApproval, "Data Request Review Task");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public CatalogPage selectDataRequestReviewTask() {
		try {
			String xpathExpandDataRequest = "//span/span[text()='" + data.get("TopicName")
					+ "']//parent::span//preceding-sibling::span[@ref='eContracted']";
			WebElement weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Data requestLink");
			}
//			String xpathReviewRequested = "//button[text()='Review Requested']";
//			WaitForElementWithDynamicXpath(xpathReviewRequested);
//			clickOnElementWithDynamicXpath(xpathReviewRequested, "Review Request");
//			Thread.sleep(5000);----new Change
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
		return new CatalogPage(driver, data);
	}

	public void ValidateRejectDetailsAtPortco(String period) {
		try {
			waitForElement(txtAssignedBy);
			if (txtAssignedBy.getText().equalsIgnoreCase(data.get("EntOrgName"))) {
				passed("Successfully Validated Assigned By As" + txtAssignedBy.getText());
			} else {
				failed(driver, "Failed To Validate Assigned By Expected" + data.get("EntOrgName") + "But Actual is"
						+ txtAssignedBy.getText());
			}
			waitForElement(txtAssignedTo);
			if (txtAssignedTo.getText().equalsIgnoreCase(data.get("2ndLevelApprovalEnterpriseOrgName"))) {
				passed("Successfully Validated Assigned To As" + txtAssignedTo.getText());
			} else {
				failed(driver, "Failed To Validate Assigned To Expected" + data.get("2ndLevelApprovalEnterpriseOrgName")
						+ "But Actual is" + txtAssignedTo.getText());
			}
			waitForElement(txtDueDate);
			if (txtDueDate.getText().equals(GlobalVariables.dueDate)) {
				passed("Successfully Validated DueDate As" + txtDueDate.getText());
			} else {
				failed(driver, "Failed To Validate Assigned To Expected" + GlobalVariables.dueDate + "But Actual is"
						+ txtDueDate.getText());
			}
			waitForElement(txtPeriod);
			if (txtPeriod.getText().replaceAll("\\s", "").equalsIgnoreCase(period.replaceAll("\\s", ""))) {
				passed("Successfully Validated Period As" + txtPeriod.getText());
			} else {
				failed(driver, "Failed To Validate Period Expected" + period + "But Actual is" + txtPeriod.getText());
			}
			waitForElement(txtTopicName);
			if (txtTopicName.getText().equals(data.get("TopicName"))) {
				passed("Successfully Validated Topic Name  As" + txtTopicName.getText());
			} else {
				failed(driver, "Failed To Validate Topic Name Expected As" + data.get("TopicName") + "But Actual is"
						+ txtTopicName.getText());
			}
			String metricName = data.get("MetricName");
			String xpathMeticName = "// div[@col-id='metricName']//div[@class='contentDiv']/span[contains(text(),'"
					+ metricName + "')]";
			if (isElementPresentDynamicXpath(xpathMeticName)) {
				passed("Successfully Validated Metric Name in Ag Grid of Tasks Page As" + metricName);
			} else {
				failed(driver, "Failed To validate Metric Name in Ag Grid of Tasks Page  ");
			}
			String xpathMetricValue_demo = "// span[contains(text(),'" + metricName
					+ "')]//ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='displayData']//span[text()='"
					+ GlobalVariables.inputValue + "']";
			String xpathMetricValue = "// span[contains(text(),'" + metricName
					+ "')]//ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']//span[text()='"
					+ GlobalVariables.inputValue + "']";
			if (isElementPresentDynamicXpath(xpathMetricValue)) {
				passed("Successfully Validated Metric Value in Ag Grid of Tasks Page As " + GlobalVariables.inputValue);
			} else {
				failed(driver, "Failed To validate Metric Value in Ag Grid of Tasks Page  ");
			}
			String xpathComments = "//div[@col-id='comment']//following::div[@class='contentDiv']/span[contains(text(),'"
					+ Constants.DRRejectComments + "')]";
			WebElement comments = driver.findElement(By.xpath(xpathComments));
			if (isElementPresentDynamicXpath(xpathComments)) {
				passed("Successfully Validated Comments in Ag Grid of Tasks Page As " + comments.getText());
			} else {
				failed(driver, "Failed To validate Comments in Ag Grid of Tasks Page  ");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateRevisedDetailsAtInvestCoDataAprovalTaskForSecondLevelApprover(String period) {
		try {
			waitForElement(btnClaimTask);
			if (isElementPresent(btnClaimTask)) {
				clickOn(btnClaimTask, "ClaimTask button");
			}
			waitForElement(txtAssignedBy);
			if (txtAssignedBy.getText().equalsIgnoreCase(data.get("2ndLevelApprovalOrgName"))) {
				passed("Successfully Validated Assigned By As" + txtAssignedBy.getText());
			} else {
				failed(driver, "Failed To Validate Assigned By Expected" + data.get("2ndLevelApprovalOrgName")
						+ "But Actual is" + txtAssignedBy.getText());
			}
			waitForElement(txtAssignedTo);
			if (txtAssignedTo.getText().equalsIgnoreCase(data.get("2ndLevelApproverId"))) {
				passed("Successfully Validated Assigned To As" + txtAssignedTo.getText());
			} else {
				failed(driver, "Failed To Validate Assigned To Expected" + data.get("2ndLevelApproverId")
						+ "But Actual is" + txtAssignedTo.getText());
			}
			waitForElement(txtDueDate);
			if (txtDueDate.getText().equals(GlobalVariables.dueDate)) {
				passed("Successfully Validated DueDate As" + txtDueDate.getText());
			} else {
				failed(driver, "Failed To Validate Assigned To Expected" + GlobalVariables.dueDate + "But Actual is"
						+ txtDueDate.getText());
			}
			waitForElement(txtPeriod);
			if (txtPeriod.getText().replaceAll("\\s", "").equalsIgnoreCase(period.replaceAll("\\s", ""))) {
				passed("Successfully Validated Period As" + txtAssignedTo.getText());
			} else {
				failed(driver, "Failed To Validate Period Expected" + period + "But Actual is" + txtPeriod.getText());
			}
			waitForElement(txtTopicName);
			if (txtTopicName.getText().equals(data.get("TopicName"))) {
				passed("Successfully Validated Topic Name  As" + txtTopicName.getText());
			} else {
				failed(driver, "Failed To Validate Topic Name Expected As" + data.get("TopicName") + "But Actual is"
						+ txtTopicName.getText());
			}
			System.out.println("Hello");
			String metricName = data.get("MetricName");
			String xpathMeticName = "// div[@col-id='metricName']//div[@class='contentDiv']/span[contains(text(),'"
					+ metricName + "')]";
			if (isElementPresentDynamicXpath(xpathMeticName)) {
				passed("Successfully Validated Metric Name in Ag Grid of Tasks Page As" + metricName);
			} else {
				failed(driver, "Failed To validate Metric Name in Ag Grid of Tasks Page  ");
			}
			String xpathMetricValue_demo = "// span[contains(text(),'" + metricName
					+ "')]//ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='displayData']//span[text()='"
					+ GlobalVariables.inputValue + "']";
			String xpathMetricValue = "//span[contains(text(),'" + data.get("MetricName")
					+ "')]//ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']//span";
			WebElement metricValue = driver.findElement(By.xpath(xpathMetricValue));
			if (metricValue.getText().equals(GlobalVariables.inputValue)) {
				passed("Successfully Validated Metric Value in Ag Grid of Tasks Page As " + GlobalVariables.inputValue);
			} else {
				failed(driver, "Failed To validate Metric Value in Ag Grid of Tasks Page  ");
			}
			String xpathStatusFirstLevelApprover = "// div[contains(text(),'" + data.get("1stLevelApproverUserName")
					+ "')]//parent::div//parent::div/div[1]//div[contains(text(),'Approved')]";
			if (isElementPresentDynamicXpath(xpathStatusFirstLevelApprover)) {
				passed("Successfully Validated First Level Approver  status As In Approved");
			} else {
				failed(driver, "Failed To validate First Level Approver  status As In Approved");
			}
			String xpathStatusSecondLevelApprover = "// div[contains(text(),'" + data.get("2ndLevelApproverUserName")
					+ "')]//parent::div//parent::div/div[1]//div[contains(text(),'In Progress')]";
			if (isElementPresentDynamicXpath(xpathStatusSecondLevelApprover)) {
				passed("Successfully Validated Second Level Approver  status As In Progress");
			} else {
				failed(driver, "Failed To validate Second  Level Approver  status In Progress");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateRevisedDetailsAtInvestCoDataAprovalTask(String period) {
		try {
			waitForElement(btnClaimTask);
			if (isElementPresent(btnClaimTask)) {
				clickOn(btnClaimTask, "ClaimTask button");
			}
			waitForElement(txtAssignedBy);
			if (txtAssignedBy.getText().equalsIgnoreCase(data.get("2ndLevelApprovalOrgName"))) {
				passed("Successfully Validated Assigned By As" + txtAssignedBy.getText());
			} else {
				failed(driver, "Failed To Validate Assigned By Expected" + data.get("2ndLevelApprovalOrgName")
						+ "But Actual is" + txtAssignedBy.getText());
			}
			waitForElement(txtAssignedTo);
			if (txtAssignedTo.getText().equalsIgnoreCase(data.get("1stLevelApproverId"))) {
				passed("Successfully Validated Assigned To As" + txtAssignedTo.getText());
			} else {
				failed(driver, "Failed To Validate Assigned To Expected" + data.get("1stLevelApproverId")
						+ "But Actual is" + txtAssignedTo.getText());
			}
			waitForElement(txtDueDate);
			if (txtDueDate.getText().equals(GlobalVariables.dueDate)) {
				passed("Successfully Validated DueDate As" + txtDueDate.getText());
			} else {
				failed(driver, "Failed To Validate Assigned To Expected" + GlobalVariables.dueDate + "But Actual is"
						+ txtDueDate.getText());
			}
			waitForElement(txtPeriod);
			if (txtPeriod.getText().replaceAll("\\s", "").equalsIgnoreCase(period.replaceAll("\\s", ""))) {
				passed("Successfully Validated Period As" + txtAssignedTo.getText());
			} else {
				failed(driver, "Failed To Validate Period Expected" + period + "But Actual is" + txtPeriod.getText());
			}
			waitForElement(txtTopicName);
			if (txtTopicName.getText().equals(data.get("TopicName"))) {
				passed("Successfully Validated Topic Name  As" + txtTopicName.getText());
			} else {
				failed(driver, "Failed To Validate Topic Name Expected As" + data.get("TopicName") + "But Actual is"
						+ txtTopicName.getText());
			}
			System.out.println("Hello");
			String metricName = data.get("MetricName");
			String xpathMeticName = "// div[@col-id='metricName']//div[@class='contentDiv']/span[contains(text(),'"
					+ metricName + "')]";
			if (isElementPresentDynamicXpath(xpathMeticName)) {
				passed("Successfully Validated Metric Name in Ag Grid of Tasks Page As" + metricName);
			} else {
				failed(driver, "Failed To validate Metric Name in Ag Grid of Tasks Page  ");
			}
			String xpathMetricValue = "// span[contains(text(),'" + metricName
					+ "')]//ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='metricValue']//span[text()='"
					+ GlobalVariables.inputValue + "']";
			String xpathMetricValue_demo = "// span[contains(text(),'" + metricName
					+ "')]//ancestor::div[@col-id='metricName']//following-sibling::div[@col-id='displayData']//span[text()='"
					+ GlobalVariables.inputValue + "']";
			WebElement metricValue = driver.findElement(By.xpath(xpathMetricValue));
			if (metricValue.getText().equals(GlobalVariables.inputValue)) {
				passed("Successfully Validated Metric Value in Ag Grid of Tasks Page As " + GlobalVariables.inputValue);
			} else {
				failed(driver, "Failed To validate Metric Value in Ag Grid of Tasks Page  ");
			}
			String xpathStatusFirstLevelApprover = "// div[contains(text(),'" + data.get("1stLevelApproverUserName")
					+ "')]//parent::div//parent::div/div[1]//div[contains(text(),'In Progress')]";
			if (isElementPresentDynamicXpath(xpathStatusFirstLevelApprover)) {
				passed("Successfully Validated First Level Approver  status As In Progress");
			} else {
				failed(driver, "Failed To validate First Level Approver  status As In Progress");
			}
			String xpathStatusSecondLevelApprover = "// div[contains(text(),'" + data.get("2ndLevelApproverUserName")
					+ "')]//parent::div//parent::div/div[2]//div[contains(text(),'Pending')]";
			if (isElementPresentDynamicXpath(xpathStatusSecondLevelApprover)) {
				passed("Successfully Validated Second Level Approver  status As Pending");
			} else {
				failed(driver, "Failed To validate Second  Level Approver  status As Pending");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//div/*[@class='task-grid-padding']")
	private WebElement btnGridView;
	@FindBy(xpath = "//button[text()='Start']")
	private WebElement btnStart;

	public void clickOnGridViewInTasksPage() {
		waitForElement(btnGridView);
		clickOn(btnGridView, "Grid View Button");
	}

	public TasksPage clickOnStartTaskInTasksPage() {
//		GlobalVariables.dataRequestName = "TestAutDRrand_uqu915"; // delete
		String xpathDataRequest = "//p[text()='Request Task']//preceding-sibling::div[text()='"
				+ GlobalVariables.dataRequestName + "']";
		clickOnElementWithDynamicXpath(xpathDataRequest, "Data Request Task");
		clickOnElement(btnStart, "Start button");
		return new TasksPage(driver, data);
	}
	public TasksPage clickOnStartTaskInTasksPage(String DRName) {
		// delete //p[@aria-label='001001 Sapporo -
		// Mitsukoshi_Perf1']//preceding::P[text()='Data Request
		// Task']//preceding-sibling::div
		String xpathDataRequest = "//p[@aria-label='" + data.get("switchOrgName")
				+ "']//preceding::p[text()='Data Request Task']//preceding-sibling::div[@aria-label='" + DRName + "']";
		clickOnElementWithDynamicXpath(xpathDataRequest, "Data Request Task");
		clickOnElement(btnStart, "Start button");
		return new TasksPage(driver, data);
	}

	public void clickOnMetricName(String metricName) {
		try {
			((JavascriptExecutor) driver)
					.executeScript("document.querySelector('div[ref=eBodyViewport]').scrollTop=-2000");
			WebElement weMetricValue = driver.findElement(By.xpath("//span[text()='" + metricName
					+ "']//ancestor::div[@col-id='metricName']//following-sibling::div"));
			waitForElement(weMetricValue);
			clickOn(weMetricValue, "MetricValueFor" + "");
			waitForElement(weMetricValue);
		} catch (Exception e) {
			System.out.println("Exception here *************************>");
			clickOnMetricName(metricName);
		}
	}

	public void clickOnDataForIntegerInputType() {
		try {
			WebElement weMetricData = driver.findElement(
					By.xpath("//p[text()='Provide the value below']//parent::div//following::div[@class='ql-editor']"));
			waitForElement(weMetricData);
			jsClick(weMetricData, "Metric Data");
			sleep(1000);
		} catch (Exception e) {
			System.out.println("No Element Preset in Data Input");
		}
	}
	
	public void clickOnDataForTextInputType() {
		try {
			WebElement weMetricData = driver
					.findElement(By.xpath("//p[text()='Provide the response below']//parent::div//following::div[@class='ql-editor']"));
			waitForElement(weMetricData);
			jsClick(weMetricData, "Metric Data");
			sleep(1000);
		} catch (Exception e) {
			System.out.println("No Element Preset in Data Input");
		}
	}

	public void clickOnDataLinkForTableInputType() {
		try {
			WebElement weMetricData = driver.findElement(By.xpath(
					"//p[text()='Provide a detailed response below']//parent::div//parent::div//following-sibling::div//span[text()='Click here to view values']"));
			waitForElement(weMetricData);
			jsClick(weMetricData, "Metric Data");
			sleep(1000);
		} catch (Exception e) {
			System.out.println("Exception comming At Table Input Data Link");
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

	@FindBy(xpath = "//button[text()='Publish & Next']")
	private WebElement btnpublish_Next;
	@FindBy(xpath = "//button[text()='Confirm']")
	private WebElement btnPublishConfirm;
	@FindBy(xpath = "//button[text()='Done']")
	private WebElement btnPublishDone;

	public void publishCatalogInTaskPage() {
		clickOn(btnPublish, "Publish Button");
		waitForElement(btnpublish_Next);
		clickOn(btnpublish_Next, "btn publish_Next");
		waitForElement(btnPublishConfirm);
		clickOn(btnPublishConfirm, "btn publish Confirm");
		waitForElement(btnPublishDone);
		clickOn(btnPublishDone, "btn publish Done");
		sleep(1000);
	}

	public void clickOnDataNotAvailablecheckBoxTocheck() {
		try {
			WebElement chkBoxValue = driver
					.findElement(By.xpath("//span[text()='Data not available']//following-sibling::span/span"));
			String classExpand = chkBoxValue.getAttribute("class");
			if (!classExpand.contains("Mui-checked")) {
				clickOn(chkBoxValue, "Data Not avaialble Check box ");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//span[text()='Data not available']//following-sibling::span/span")
	private WebElement chkDNA;

	public void clickOnDataNotAvailablecheckBoxToUncheck() {
		try {
			if (isElementPresent(chkDNA)) {
				WebElement chkBoxValue = driver
						.findElement(By.xpath("//span[text()='Data not available']//following-sibling::span/span"));
				String classExpand = chkBoxValue.getAttribute("class");
				if (classExpand.contains("Mui-checked")) {
					clickOn(chkBoxValue, "Data Not avaialble Check box ");
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void clickOnDataNotAvailablecheckBoxTocheckForRelatedMetric() {
		try {
			WebElement chkBoxValue = driver.findElement(By.xpath(
					"//p[text()='Related Metrics']//following::span[text()='Data not available']//parent::div//following-sibling::span/span"));
			Actions act = new Actions(driver);
			waitForElement(chkBoxValue);
			String classExpand = chkBoxValue.getAttribute("class");
			if (!classExpand.contains("Mui-checked")) {
				clickOn(chkBoxValue, "Data Not avaialble Check box Child Metric");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void clearInt_NumInputTypeTextBox() {
		try {
			// p[text()='Enter Data']//parent::div//input[@placeholder='Enter Integer']
			WaitForElementWithDynamicXpath("//input[@placeholder='Please enter data here']");
			// p[text()='Enter Data']//parent::div//input[@placeholder='Enter Integer']
			WebElement MetricValueData = driver.findElement(By.xpath("//input[@placeholder='Please enter data here']"));
			MetricValueData.click();
			MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			MetricValueData.sendKeys(Keys.BACK_SPACE);
			MetricValueData.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterDataForIntegerInputType(String metricValue) {
		try {
			WebElement MetricValueData = driver.findElement(By.xpath("//input[@placeholder='Enter Integer']"));
			waitForElement(MetricValueData);
			MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			sleep(200);
			MetricValueData.sendKeys(Keys.BACK_SPACE);
			sleep(200);
			enterText(MetricValueData, "Metric Value Data", metricValue);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void enterDataForNumberInputType(String metricValue) {
		try {
			WebElement MetricValueData = driver.findElement(By.xpath("//input[@placeholder='Enter Number']"));
			waitForElement(MetricValueData);
			MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			sleep(200);
			MetricValueData.sendKeys(Keys.BACK_SPACE);
			sleep(200);
			enterText(MetricValueData, "Metric Value Data", metricValue);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void enterDataForTextInputType(String metricValue) {
		try {
			WebElement MetricValueData = driver.findElement(By.xpath("//input[@placeholder='Please enter data here']"));
			waitForElement(MetricValueData);
			MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			sleep(200);
			MetricValueData.sendKeys(Keys.BACK_SPACE);
			sleep(200);
			enterText(MetricValueData, "Metric Value Data", metricValue);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void enterDataForDescInputType(String metricValue) {
		try {
			WebElement MetricValueData = driver.findElement(
					By.xpath("//p[text()='Data']//parent::div//parent::div//following-sibling::div//br//parent::p"));
			System.out.println(waitForElement(MetricValueData));
			if (metricValue.equals("NA")) {
				enterText(MetricValueData, "Metric Value Data", "");
			} else {
				MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				MetricValueData.sendKeys(Keys.BACK_SPACE);
				sleep(1000);
				MetricValueData.sendKeys(metricValue);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void enterDataSingleSelectType(String metricValue) {
		try {
			WebElement MetricValueData = driver.findElement(
					By.xpath("//p[text()='Select an option from the list below']//parent::div//parent::div//following-sibling::div//span[text()='"
							+ metricValue + "']//parent::div//span//input"));
			waitForElement(MetricValueData);
			clickOn(MetricValueData, metricValue);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void enterDataForSingleSelectInputTypeRelatedMetrics(String metricValue) {
		try {
			WebElement MetricValueData = driver.findElement(
					By.xpath("//p[text()='Related Metrics']//following::p[text()='Select an option from the list below']//following::div//span[text()='"
							+ metricValue + "']//preceding::input[1]"));
			waitForElement(MetricValueData);
			clickOn(MetricValueData, metricValue);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void enterDataForMultiInputType(String metricValue) {
		try {
			String[] arrMultiSelect = null;
			if (metricValue.contains(",")) {
				arrMultiSelect = metricValue.split(",");
				sleep(2000);
				for (int i = 0; i < arrMultiSelect.length; i++) {
					WaitForElementWithDynamicXpath(
							"//p[text()='Select multiple options from the list below']//parent::div//parent::div//following-sibling::div//span[text()='"
									+ arrMultiSelect[i] + "']//parent::div//span//input");
					WebElement MetricValueData = driver.findElement(By
							.xpath("//p[text()='Select multiple options from the list below']//parent::div//parent::div//following-sibling::div//span[text()='"
									+ arrMultiSelect[i] + "']//parent::div//span//input"));
					WebElement chkBoxValue = driver.findElement(By
							.xpath("//p[text()='Select multiple options from the list below']//parent::div//parent::div//following-sibling::div//span[text()='"
									+ arrMultiSelect[i] + "']//parent::div//span//input//following-sibling::*"));
					if (chkBoxValue.getAttribute("data-testid").contains("CheckBoxOutlineBlankIcon")) {
						clickOn(MetricValueData, metricValue);
					}
				}
			} else {
				WebElement MetricValueData = driver.findElement(
						By.xpath("//p[text()='Select multiple options from the list below']//parent::div//parent::div//following-sibling::div//span[text()='"+ metricValue + "']//parent::div//span//input"));
				WebElement chkBoxValue = driver.findElement(
						By.xpath("//p[text()='Select multiple options from the list below']//parent::div//parent::div//following-sibling::div//span[text()='"
								+ metricValue + "']//parent::div//span//input//following-sibling::*"));
				if (chkBoxValue.getAttribute("data-testid").contains("CheckBoxOutlineBlankIcon")) {
					clickOn(MetricValueData, metricValue);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
							"//p[text()='Related Metrics']//following::p[text()='Select multiple options from the list below']//following::div//span[text()='"
									+ arrMultiSelect[i] + "']//preceding::input[1]");
					WebElement MetricValueData = driver.findElement(By.xpath(
							"//p[text()='Related Metrics']//following::p[text()='Select multiple options from the list below']//following::div//span[text()='"
									+ arrMultiSelect[i] + "']//preceding::input[1]"));
					WebElement chkBoxValue = driver.findElement(By.xpath(
							"//p[text()='Related Metrics']//following::p[text()='Select multiple options from the list below']//following::div//span[text()='"
									+ arrMultiSelect[i] + "']//preceding::input[1]//following-sibling::*"));
					if (chkBoxValue.getAttribute("data-testid").contains("CheckBoxOutlineBlankIcon")) {
						clickOn(MetricValueData, metricValue);
					}
				}
			} else {
				WaitForElementWithDynamicXpath(
						"//p[text()='Related Metrics']//following::p[text()='Select multiple options from the list below']//following::div//span[text()='"
								+ metricValue + "']//preceding::input[1]");
				WebElement MetricValueData = driver.findElement(By.xpath(
						"//p[text()='Related Metrics']//following::p[text()='Select multiple options from the list below']//following::div//span[text()='"
								+ metricValue + "']//preceding::input[1]"));
				WebElement chkBoxValue = driver.findElement(By.xpath(
						"//p[text()='Related Metrics']//following::p[text()='Select multiple options from the list below']//following::div//span[text()='"
								+ metricValue + "']//preceding::input[1]//following-sibling::*"));
				if (chkBoxValue.getAttribute("data-testid").contains("CheckBoxOutlineBlankIcon")) {
					clickOn(MetricValueData, metricValue);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void enterDataForBooleanInputType(String metricValue) {
		try {
			WebElement MetricValueData = driver.findElement(
					By.xpath("//p[text()='Select an option from the list below']//parent::div//parent::div//following-sibling::div//span[text()='"
							+ metricValue + "']//parent::div//span//input"));
			MetricValueData.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void enterDataForBooleanInputTypeRelatedMetrics(String metricValue, String relatedMetricName) {
		try {
			WebElement MetricValueData = driver.findElement(By.xpath("//span[text()='" + relatedMetricName
					+ "']//parent::div//parent::div//parent::div//parent::div//following-sibling::div//following::div//span[text()='"
					+ metricValue + "']//preceding::input[1]"));
			waitForElement(MetricValueData);
			clickOn(MetricValueData, metricValue);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FindBy(xpath = "//button[text()='Next']")
	private WebElement btnNext;
	@FindBy(xpath = " //button[text()='Previous']")
	private WebElement btnPrev;
	@FindBy(xpath = "//div[text()='You have reached the last Metric. Click “Prev” to go back or “X” to save and close.']")
	private WebElement txtLastMetricToastMessage;
	@FindBy(xpath = "//span[text()='Publish']")
	private WebElement btnPublish;
	@FindBy(xpath = "//span[text()='Validation errors detected. Please complete all required fields for successful publishing.']")
	private WebElement msgUnableToPublish;
	@FindBy(xpath = "//span[@class='count']//following-sibling::span[text()='Error']")
	private WebElement btnErrorPublish;
	@FindBy(xpath = "//span[@class='count']//following-sibling::span[text()='Answered']")
	private WebElement btnAnsweredPublish;
	@FindBy(xpath = "//span[text()='All']")
	private WebElement btnAllMetric;
	@FindBy(xpath = "//span[text()='Error']")
	private WebElement btnErrorMetric;

	public void saveMetricDetailsinTaskPage() {
		if (isElementPresent(txtLastMetricToastMessage)) {
			// ---- need to modify
		} else {
			clickOn(btnNext, "Next Button Metric");
			sleep(4000);
			clickOn(btnPrev, "Prev button");
		}
	}

	public void validateTopicNameinPublishBlocker(String topicName) {
		String xpathTopicName = "//span[@ref='eValue'][text()='" + topicName + "']";
		WaitForElementWithDynamicXpath(xpathTopicName);
		if (isElementPresentDynamicXpath(xpathTopicName)) {
			passed("Successfully Validated Topic name in Publish Blocker popup as " + topicName);
		} else {
			failed(driver, "Failed to validate topic Name in Publish Blocker popup  Expected as " + topicName);
		}
	}

	public void validateMetricNameinPublishBlocker(String metricName) {
		String xpathMetricName = "//span[text()='" + metricName + "']";
		WaitForElementWithDynamicXpath(xpathMetricName);
		if (isElementPresentDynamicXpath(xpathMetricName)) {
			passed("Successfully Validated Metric name in Publish Error  Blocker popup as " + metricName);
		} else {
			failed(driver, "Failed to validate Metric Name in Publish Blocker  Error  Expected as " + metricName);
		}
	}
	public void validateValidationMethodPublishBlockerErrorDropDown() {
	try {
		String metricValidationMethods =data.get("ErrorDropDownValidations");
		if(!metricValidationMethods.isEmpty()) {
		String[] metricValidationMethod = metricValidationMethods.split(",");
	for (int i = 0; i < metricValidationMethod.length; i++) {
				clickOn(btnErrorMetric, "Error Metric LHP Button");
			 WebElement weDrpValidationMethod = driver.findElement(By.xpath("//li//span[contains(text(),'"+metricValidationMethod[i]+"')]"));
			 clickOn(weDrpValidationMethod, "Clicked on Validation in Error Drop");
				validateTopicNameinPublishBlocker(data.get("TopicName"));
				expandTopicInTasksPage(data.get("TopicName"));
				validateMetricNameinPublishBlocker(data.get("MetricName"));
	 }
		}else {
			
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	}

	WebElement weExpand;
	public void expandTopicInTasksPage(String TopicName) {
		try {
			String xpathExpandDataRequest = "//*[text()='" + TopicName
					+ "']//parent::span//parent::span[@ref='eValue']//preceding-sibling::span[@ref='eContracted']";
			WaitForElementWithDynamicXpath(xpathExpandDataRequest);
			weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			sleep(500);
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Topic");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void contractTopicInCatalog(String TopicName) {
		try {
			String xpathExpandDataRequest = "//*[contains(text(),'" + TopicName
					+ "')]//parent::span//parent::span[@ref='eValue']//preceding-sibling::span[@ref='eExpanded']";
			WaitForElementWithDynamicXpath(xpathExpandDataRequest);
			weExpand = driver.findElement(By.xpath(xpathExpandDataRequest));
			sleep(500);
			String classExpand = weExpand.getAttribute("class");
			if (!classExpand.contains("hidden")) {
				clickOnElementWithDynamicXpath(xpathExpandDataRequest, "Expand Topic");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateIntInputTypeMetricPublishBlocker_DRFlow() {
		try {
			expandTopicInTasksPage(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			} else {
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
					clickOnDataNotAvailablecheckBoxToUncheck();
				}
				sleep(1000);
				clickOnDataForIntegerInputType();
//				clearInt_NumInputTypeTextBox();
				enterDataForIntegerInputType(data.get("RHPMetricValue"));
			}
			saveMetricDetailsinTaskPage();
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
			clickOn(btnPublish, "Publish Button");
			waitForElement(msgUnableToPublish);
			if (isElementPresent(msgUnableToPublish)) {
				passed("Successfully Validated Publish blocker");
			} else {
				failed(driver, "Failed To Validate Publish Blocker");
			}
			validateValidationMethodPublishBlockerErrorDropDown();
			clickOn(btnAllMetric, "Btn All metrics");
//			contractTopicInCatalog(data.get("TopicName"));
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateNumInputTypeMetricPublishBlocker_DRFlow() {
		try {
			expandTopicInTasksPage(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			if (Boolean.parseBoolean(data.get("IsDataRequiredMessage"))) {
				clickOnDataNotAvailablecheckBoxToUncheck();
				clickOnDataForIntegerInputType();
				WaitForElementWithDynamicXpath("//*[text()='" + Constants.RequiredErrorMessageWithDNA + "']");
				if ((isElementPresentDynamicXpath("//*[text()='" + Constants.RequiredErrorMessageWithDNA + "']"))) {
					passed("Successfully Validated Data Required Error Message As"
							+ Constants.RequiredErrorMessageWithDNA);
				} else {
					failed(driver, "Failed To Validate Data Required  Error message");
				}
			}
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			} else {
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
					clickOnDataNotAvailablecheckBoxToUncheck();
				}
				sleep(1000);
				clickOnDataForIntegerInputType();
//				clearInt_NumInputTypeTextBox();
				enterDataForNumberInputType(data.get("RHPMetricValue"));
			}
			saveMetricDetailsinTaskPage();
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
			clickOn(btnPublish, "Publish Button");
			waitForElement(msgUnableToPublish);
			if (isElementPresent(msgUnableToPublish)) {
				passed("Successfully Validated Publish blocker");
			} else {
				failed(driver, "Failed To Validate Publish Blocker");
			}
			validateValidationMethodPublishBlockerErrorDropDown();
			clickOn(btnAllMetric, "Btn All metrics");
//			contractTopicInCatalog(data.get("TopicName"));
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void clearInputTypeTextBox() {
		try {
			WaitForElementWithDynamicXpath("//input[@placeholder='Please enter data here']");
			WebElement MetricValueData = driver.findElement(By.xpath("//input[@placeholder='Please enter data here']"));
			MetricValueData.click();
			MetricValueData.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			MetricValueData.sendKeys(Keys.BACK_SPACE);
			MetricValueData.clear();
		} catch (Exception e) {
			clearInputTypeTextBox();
		}
	}

	@FindBy(xpath = "//button[text()='Add']")
	private WebElement btnAddInTable;
	@FindBy(xpath = "//div/div/*[@fill='none']")
	private WebElement btnDeleteInTable;
	@FindBy(xpath = "//button[text()='Cancel']//following-sibling::button[text()='Save']")
	private WebElement btnSaveInTable;

	public void clearDataInTable() {
		try {
			Thread.sleep(2000);
			List<WebElement> lstDeleteButtons = driver
					.findElements(By.xpath("//div[@class='datagrid']//div/*[@fill='none']"));
			System.out.println(lstDeleteButtons.size());
			if (lstDeleteButtons.size() > 0) {
				for (int i = 0; i < lstDeleteButtons.size(); i++) {
					waitForElement(lstDeleteButtons.get(i));
					clickOnElement(lstDeleteButtons.get(i), "Table data delete ");
				}
			}
			waitForElement(btnSaveInTable);
			clickOn(btnSaveInTable, "Save Button");
		} catch (Exception e) {
			System.out.println("Exception comming At Table Delete");
		}
	}

	public void ValidateTextInputTypeMetricPublishBlocker_DRFlow() {
		try {
			expandTopicInTasksPage(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			} else {
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
					clickOnDataNotAvailablecheckBoxToUncheck();
				}
				sleep(1000);
				clickOnDataForTextInputType();
				enterDataForTextInputType(data.get("RHPMetricValue"));
			}
			saveMetricDetailsinTaskPage();
			if (isNumber(data.get("RHPMetricValue"))) {
				clickOnDataForTextInputType();
				clearInputTypeTextBox();
				enterDataForTextInputType(data.get("RHPMetricValue"));
				clickOn(btnNext, "Button Next Metrics");
				sleep(4000);
				clickOn(btnPrev, "Button Previous Metrics");
				sleep(1000);
				WaitForElementWithDynamicXpath("//*[contains(text(),'" + Constants.inputTypeRequiredErrorMessageText + "')]");
				if (isElementPresentDynamicXpath("//*[contains(text(),'" + Constants.inputTypeRequiredErrorMessageText + "')]")) {
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
				clickOnDataForTextInputType();
				clearInputTypeTextBox();
				WaitForElementWithDynamicXpath("//*[text()='" + Constants.RequiredErrorMessageWithDNA + "']");
				if ((isElementPresentDynamicXpath("//*[text()='" + Constants.RequiredErrorMessageWithDNA + "']"))) {
					passed("Successfully Validated Data Required Error Message As"
							+ Constants.RequiredErrorMessageWithDNA);
				} else {
					failed(driver, "Failed To Validate Data Required  Error message");
				}
			}
			clickOn(btnPublish, "Publish Button");
			waitForElement(msgUnableToPublish);
			if (isElementPresent(msgUnableToPublish)) {
				passed("Successfully Validated Publish blocker");
			} else {
				failed(driver, "Failed To Validate Publish Blocker");
			}
			validateValidationMethodPublishBlockerErrorDropDown();
			clickOn(btnAllMetric, "Btn All metrics");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//span[text()='Reset']")
	private WebElement btnReset;
	@FindBy(xpath = "//p[text()='Related Metrics']//following::span[text()='Reset']")
	private WebElement btnChildMetricReset;
	@FindBy(xpath = "//span[text()='View More ...']")
	private WebElement btnViewMoreRelatedMetric;

	public void clickOnBooleanRelatedResetButton(String childMetricName) {
		WebElement wechildReset = driver.findElement(By.xpath("//span[text()='" + childMetricName
				+ "']//parent::div//parent::div//parent::div//parent::div//following-sibling::div//span[text()='Reset']"));
		waitForElement(wechildReset);
		clickOn(wechildReset, "Button Boolean Child metric Reset");
	}

	public void clickOnBooleanRelatedMetricViewMoreButton(String childMetricName) {
		try {
			WebElement wechildViewMore = driver.findElement(By.xpath("//span[text()='" + childMetricName
					+ "']//parent::div//parent::diV//parent::div//parent::div//following-sibling::div//span[text()='View More ...']"));
			waitForElement(wechildViewMore);
			clickOn(wechildViewMore, "Button Boolean View More Child Metric");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ValidateSingleSelectInputTypeMetricPublishBlocker_DRFlow() {
		try {
			expandTopicInTasksPage(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			} else {
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
					waitForElement(btnReset);
					clickOn(btnReset, "btnReset");
					saveMetricDetailsinTaskPage();
				}
				enterDataSingleSelectType(data.get("RHPMetricValue"));
			}
			saveMetricDetailsinTaskPage();
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
						waitForElement(btnReset);
						clickOn(btnReset, "btnReset");
						saveMetricDetailsinTaskPage();
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
					enterDataSingleSelectType(data.get("RHPMetricValue"));
					saveMetricDetailsinTaskPage();
				}
			} else {
				if (Boolean.parseBoolean(data.get("RelatedMetricDataNotAvailable"))) {
					clickOnDataNotAvailablecheckBoxTocheckForRelatedMetric();
				} else {
					waitForElement(btnChildMetricReset);
					clickOn(btnChildMetricReset, "btnReset child Metric");
					enterDataForSingleSelectInputTypeRelatedMetrics(data.get("RelatedMetricRHPValue"));
				}
				saveMetricDetailsinTaskPage();
				if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						clickOn(btnViewMoreRelatedMetric, "btnViewMoreRelatedMetric");
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
						clickOn(btnViewMoreRelatedMetric, "btnViewMoreRelatedMetric");
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
			clickOn(btnPublish, "Publish Button");
			waitForElement(msgUnableToPublish);
			if (isElementPresent(msgUnableToPublish)) {
				passed("Successfully Validated Publish blocker");
			} else {
				failed(driver, "Failed To Validate Publish Blocker");
			}
			validateValidationMethodPublishBlockerErrorDropDown();
			clickOn(btnAllMetric, "Btn All metrics");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateMultiSelectInputTypeMetricPublishBlocker_DRFlow() {
		try {
			expandTopicInTasksPage(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			} else {
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
					waitForElement(btnReset);
					clickOn(btnReset, "btnReset");
				} else {
					clickOnDataNotAvailablecheckBoxToUncheck();
					waitForElement(btnReset);
					clickOn(btnReset, "btnReset");
				}
				saveMetricDetailsinTaskPage();
				enterDataForMultiInputType(data.get("RHPMetricValue"));
			}
			saveMetricDetailsinTaskPage();
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
					if (Boolean.parseBoolean(data.get("RelatedMetricDataNotAvailable"))) {
						clickOnDataNotAvailablecheckBoxTocheckForRelatedMetric();
					} else {
						waitForElement(btnChildMetricReset);
						clickOn(btnChildMetricReset, "btnReset child Metric");
						saveMetricDetailsinTaskPage();
						enterDataForMultiInputTypeRelatedMetrics(data.get("RelatedMetricRHPValue"));
					}
					saveMetricDetailsinTaskPage();
					if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
						if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
							clickOn(btnViewMoreRelatedMetric, "btnViewMoreRelatedMetric");
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
							clickOn(btnViewMoreRelatedMetric, "btnViewMoreRelatedMetric");
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
			clickOn(btnPublish, "Publish Button");
			waitForElement(msgUnableToPublish);
			if (isElementPresent(msgUnableToPublish)) {
				passed("Successfully Validated Publish blocker");
			} else {
				failed(driver, "Failed To Validate Publish Blocker");
			}
			validateValidationMethodPublishBlockerErrorDropDown();
			clickOn(btnAnsweredPublish, "Answered Publish Button");
			sleep(500);
			clickOn(btnErrorPublish, "Error Publish Button");
			validateTopicNameinPublishBlocker(data.get("TopicName"));
			expandTopicInTasksPage(data.get("TopicName"));
			if (Boolean.parseBoolean(data.get("IsRelatedMetric"))) {
				// RelatedMetricName
				validateMetricNameinPublishBlocker(data.get("MetricName"));
			} else {
				validateMetricNameinPublishBlocker(data.get("MetricName"));
			}
			clickOn(btnAllMetric, "Btn All metrics");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateTableInputTypeMetricPublishBlocker_DRFlow() {
		try {
			expandTopicInTasksPage(data.get("TopicName"));
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
					if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
						clickOnDataNotAvailablecheckBoxTocheck();
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
			clickOn(btnPublish, "Publish Button");
			waitForElement(msgUnableToPublish);
			if (isElementPresent(msgUnableToPublish)) {
				passed("Successfully Validated Publish blocker");
			} else {
				failed(driver, "Failed To Validate Publish Blocker");
			}
			validateValidationMethodPublishBlockerErrorDropDown();
			clickOn(btnAllMetric, "Btn All metrics");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateDescInputTypeMetricPublishBlocker_DRFlow() {
		try {
			expandTopicInTasksPage(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			} else {
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
					clickOnDataNotAvailablecheckBoxTocheck();
					clickOnDataNotAvailablecheckBoxToUncheck();
				}
				sleep(1000);
				clickOnDataForIntegerInputType();
				enterDataForDescInputType(data.get("RHPMetricValue"));
			}
			saveMetricDetailsinTaskPage();
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
			clickOn(btnPublish, "Publish Button");
			waitForElement(msgUnableToPublish);
			if (isElementPresent(msgUnableToPublish)) {
				passed("Successfully Validated Publish blocker");
			} else {
				failed(driver, "Failed To Validate Publish Blocker");
			}
			validateValidationMethodPublishBlockerErrorDropDown();
			clickOn(btnAllMetric, "Btn All metrics");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateBooleanInputTypeMetricPublishBlocker_DRFlow() {
		try {
			expandTopicInTasksPage(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			if (Boolean.parseBoolean(data.get("DataNotAvailable"))) {
				clickOnDataNotAvailablecheckBoxTocheck();
			} else {
				if (Boolean.parseBoolean(data.get("IsDNAPresent"))) {
				}
				clickOnDataNotAvailablecheckBoxToUncheck();
				waitForElement(btnReset);
				clickOn(btnReset, "btnReset");
				saveMetricDetailsinTaskPage();
				enterDataForBooleanInputType(data.get("RHPMetricValue"));
			}
			saveMetricDetailsinTaskPage();
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
				if (Boolean.parseBoolean(data.get("RelatedMetricDataNotAvailable"))) {
					clickOnDataNotAvailablecheckBoxTocheckForRelatedMetric();
				} else {
					clickOnBooleanRelatedResetButton(data.get("RelatedMetricName"));
					saveMetricDetailsinTaskPage();
					enterDataForBooleanInputTypeRelatedMetrics(data.get("RelatedMetricRHPValue"),
							data.get("RelatedMetricName"));
				}
				if (Boolean.parseBoolean(data.get("IsStandardMessage"))) {
					if (Boolean.parseBoolean(data.get("IsEvidenceErrorMessage"))) {
						clickOnBooleanRelatedMetricViewMoreButton(data.get("RelatedMetricName"));
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
						clickOnBooleanRelatedMetricViewMoreButton(data.get("RelatedMetricName"));
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
			clickOn(btnPublish, "Publish Button");
			waitForElement(msgUnableToPublish);
			if (isElementPresent(msgUnableToPublish)) {
				passed("Successfully Validated Publish blocker");
			} else {
				failed(driver, "Failed To Validate Publish Blocker");
			}
			validateValidationMethodPublishBlockerErrorDropDown();
			clickOn(btnAllMetric, "Btn All metrics");
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

	@FindBy(xpath = "//*[text()='Explanation']//parent::div//following-sibling::div//p//parent::div")
	private WebElement txtExplanation;
	@FindBy(xpath = "//*[text()='Explanation']//parent::div//following-sibling::div//p")
	private WebElement txtareaExplanationText;

	public void enterExplanationText() {
		try {
			sleep(1000);
			waitForElement(txtExplanation);
			clickOn(txtExplanation, "Explanation Text");
			sleep(500);
			if (txtareaExplanationText.getText().isEmpty()) {
			} else {
				txtareaExplanationText.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				txtareaExplanationText.sendKeys(Keys.BACK_SPACE);
			}
			sleep(3000);
			txtareaExplanationText.sendKeys(data.get("ExplanationText"));
			sleep(500);
		} catch (Exception e) {
			System.out.println("Explanation text error");
			enterExplanationText();
		}
	}
	public void enterExplanationTextRandom() {
		try {
			waitForElement(txtExplanation);
			scrollToViewElement(txtExplanation);
			clickOn(txtExplanation, "Explanation Text");
			sleep(500);
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
	@FindBy(xpath = "//button[text()='Answer']")
	private WebElement btnAnswer;
	@FindBy(xpath = "//button[text()='Audit Logs']")
	private WebElement btnAuditLogInMetricDetails;
	@FindBy(xpath = "//button[text()='Refresh']")
	private WebElement btnRefresh;
	@FindBy(xpath = "(//div[@role='rowgroup']/div/div[@col-id='value'and @role='gridcell'])[1]")
	private WebElement txtLatestAuditLogInMetricDetailsValue;

	public void validateAuditLogForEnteredValueInMetricDetailsRHP() {
		try {
			waitForElement(btnAuditLogInMetricDetails);
			clickOn(btnAuditLogInMetricDetails, "Audit button metric details");
			waitForElement(btnRefresh);
			List<WebElement> noOfRecords = driver
					.findElements(By.xpath("//div[@role='rowgroup']/div/div[@col-id='value'and @role='gridcell']"));
			int intialRecorsCount = noOfRecords.size();
			int afterRefreshRecorsCount;
			List<WebElement> noOfRecordsAfterRefresh;
			do {
				clickOn(btnRefresh, "Refresh button");
				noOfRecordsAfterRefresh = driver
						.findElements(By.xpath("//div[@role='rowgroup']/div/div[@col-id='value'and @role='gridcell']"));
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
			waitForElement(btnAnswer);
			clickOnElement(btnAnswer, "Answer Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	int intialRecorsCount ;
	int afterRefreshRecorsCount;
	public void validateAuditLogIntitialValueInMetricDetailsRHP() {
		try {
			waitForElement(btnAuditLogInMetricDetails);
			clickOn(btnAuditLogInMetricDetails, "Audit button metric details");
			waitForElement(btnRefresh);
			List<WebElement> noOfRecords = driver
					.findElements(By.xpath("//div[@role='rowgroup']/div/div[@col-id='value'and @role='gridcell']"));
			intialRecorsCount = noOfRecords.size();
			waitForElement(btnAnswer);
			clickOnElement(btnAnswer, "Answer Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	public void validateAuditLogAfterEnteringDataValueInMetricDetailsRHP() {
		try {
			waitForElement(btnAuditLogInMetricDetails);
			clickOn(btnAuditLogInMetricDetails, "Audit button metric details");
			waitForElement(btnRefresh);
			List<WebElement> noOfRecordsAfterRefresh;
			do {
				clickOn(btnRefresh, "Refresh button");
				noOfRecordsAfterRefresh = driver
						.findElements(By.xpath("//div[@role='rowgroup']/div/div[@col-id='value'and @role='gridcell']"));
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
			waitForElement(btnAnswer);
			clickOnElement(btnAnswer, "Answer Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	public void clickOnAnswerButtonMetricDetails() {
		try {
			waitForElement(btnAnswer);
			clickOnElement(btnAnswer, "Answer Button");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@FindBy(xpath = "//*[@data-testid='AddIcon']")
	private WebElement btnAddEvidenceInMetricDetailsRHP;
	@FindBy(xpath = "//div[text()='File has deleted succssfully']")
	private WebElement txtDeleteEvidenceToastMessage;
	@FindBy(xpath = "//div/*[@data-testid='FileDownloadOutlinedIcon']//following-sibling::*")
	private WebElement btnDeleteEvidence;

	public void deleteUploadedEvidenceInMetricDetailsRHP() {
		try {
			sleep(1000);
			// waitForElement(btnDeleteEvidence);
			if (isElementPresent(btnDeleteEvidence)) {
				List<WebElement> lstDeleteButtons = driver.findElements(
						By.xpath("//div/*[@data-testid='FileDownloadOutlinedIcon']//following-sibling::*"));
				if (lstDeleteButtons.size() > 0) {
					for (WebElement deleteEvidence : lstDeleteButtons) {
						clickOnElement(deleteEvidence, "deleteEvidence");
						if (isElementPresent(txtDeleteEvidenceToastMessage)) {
							passed("Succesffully validated Delete Evidence toast Message");
						} else {
							failed(driver, "failed to validate Delete Evidence toast Message");
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Expection At delete Evidence---->");
		}
	}

	@FindBy(xpath = "//span[text()='Upload evidence or proof for the answer (if applicable)']//following-sibling::button[text()='Upload']")
	private WebElement btnEvidenceInMetricDetailsRHP;
	@FindBy(xpath = "//button[text()='Browse Files']")
	private WebElement btnBrowseFiles;

	public void uploadEvidenceInMetricDetailsRHP() {
		try {
			waitForElement(btnEvidenceInMetricDetailsRHP);
			clickOnElement(btnEvidenceInMetricDetailsRHP, "Evidence button");
			sleep(500);
			uploadEvidencerhp();
			validateUploadedEvidenceInMetricDetaisRHP();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//button[text()='Upload']//parent::div//following-sibling::div/button[text()='Close']")
	private WebElement btnDoneUploadFile;
	@FindBy(xpath = "//span[text()='Evidence']//parent::*//following-sibling::div//button[text()='Upload']")
	private WebElement btnUpload;
	@FindBy(xpath = "//span[text()='Evidence']//parent::*//following-sibling::div//button[text()='Close']")
	private WebElement btnCloseEvidenceUpload;

	public void uploadEvidencerhp() {
		try {
			waitForElement(btnBrowseFiles);
			clickOnElement(btnBrowseFiles, "Browse Files");
			String path = System.getProperty("user.dir");
			System.out.println(path);
			String filePath = path + "\\src\\test\\resources\\DataRequestFiles\\" + data.get("EvidenceFileName");
			ProcessBuilder pb = new ProcessBuilder(path + "\\src\\test\\resources\\DataRequestFiles\\fileUpload.exe",
					filePath);
			pb.start();
			waitForElement(btnUpload);
			clickOn(btnUpload, "btnUpload");
			waitForElement(btnCloseEvidenceUpload);
			clickOn(btnCloseEvidenceUpload, "btnCloseEvidenceUpload");
			sleep(1000);
		} catch (Exception e) {
			System.out.println("Expection At Uploading---->" + e.getMessage());
		}
	}

	@FindBy(xpath = "//div/*[@data-testid='FileDownloadOutlinedIcon']//parent::div//parent::div//div[@class='evidence-file-name']//span")
	private WebElement txtUploadedFileName;

	public void validateUploadedEvidenceInMetricDetaisRHP() {
		waitForElement(txtUploadedFileName);
		sleep(1000);
		if (txtUploadedFileName.getText().equals(data.get("EvidenceFileName"))) {
			passed("Successfully Validated Uploaded Evidence as" + txtUploadedFileName.getText());
		} else {
			failed(driver, "Failed To validate Uploaded Evidence");
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
	@FindBy(xpath = "//*[contains(text(),'Metric detail saved successfully.')]")
	private WebElement txtTableToastMessage;
	@FindBy(xpath = "//h2[text()='Metric Data']")
	private WebElement lblMetricDataTable;
	@FindBy(xpath = "//p[text()='FavCar']//following-sibling::div//input")
	private WebElement txtFavCarMultiSelect;
	@FindBy(xpath = "(//p[text()='Name']//parent::div//parent::div//parent::div//div/*)[1]")
	private WebElement btnDeleteMetricValue;
	@FindBy(xpath = "//*[@fill='none']//following::input[@name='0Boolean']//following-sibling::*[@data-testid='ArrowDropDownIcon']")
	private WebElement btnTableDrpDown;
	@FindBy(xpath = "//input[@id='1Name']")
	private WebElement txtNameTable;
	@FindBy(xpath = "//*[text()='Metric Data']/*[@data-testid='CloseIcon']")
	private WebElement btnTableCloseIcon;
	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement btnCancelTable;

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
				clickOn(btnAddInTable, "Add button In Table");
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
				clickOn(btnCancelTable, "Cancel Button Table Metric Value");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	public void validateAuditLogForEnteredValueInMetricDetailsRHPTable_InitialCount() {
		try {
			if (!data.get("MetricName").equals("TableNonFixedAndUniqueKey")) {
				waitForElement(btnAuditLogInMetricDetails);
				clickOn(btnAuditLogInMetricDetails, "Audit button In RHP");
				waitForElement(btnRefresh);
				List<WebElement> noOfRecords = driver
						.findElements(By.xpath("//div[@role='rowgroup']/div/div[@col-id='value'and @role='gridcell']"));
				intialRecorsCount = noOfRecords.size();
				waitForElement(btnAnswer);
				clickOnElement(btnAnswer, "AuditLog Expand Button");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	public void validateAuditLogForEnteredValueInMetricDetailsRHPTable_AfterUpdate() {
		try {
			if (!data.get("MetricName").equals("TableNonFixedAndUniqueKey")) {
				waitForElement(btnAuditLogInMetricDetails);
				clickOn(btnAuditLogInMetricDetails, "Audit button In RHP");
				waitForElement(btnRefresh);
				List<WebElement> noOfRecordsAfterRefresh;
				do {
					clickOn(btnRefresh, "Refresh button");
					noOfRecordsAfterRefresh = driver.findElements(
							By.xpath("//div[@role='rowgroup']/div/div[@col-id='value'and @role='gridcell']"));
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
				waitForElement(btnAnswer);
				clickOnElement(btnAnswer, "AuditLog Expand Button");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//h2/*[@data-testid='CloseIcon' and @aria-label='Close']")
	private WebElement btnSectionCloseTableMetric;

	public void ValidateDataInputValuesinMetricsvalue() {
		try {
			clickOnDataLinkForTableInputType();
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
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//p[text()='Related Metrics']//following::*[@class='errorWithEvidence']")
	private WebElement txtErrorMessageMandatoryRelated;
	@FindBy(xpath = "//p[text()='Data']//following::*[@class='errorWithEvidence']")
	private WebElement txtErrorMessageMandatoryNonRelated;

	public void ValidateDescDataTypeMetricDataEntryAndEvidenceUpload() {
		try {
			expandTopicInTasksPage(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			validateAuditLogIntitialValueInMetricDetailsRHP();
			clickOnDataForIntegerInputType();
			enterDataForDescInputType(data.get("RHPMetricValue"));
			enterExplanationTextRandom();
			saveMetricDetailsinTaskPage();
			validateAuditLogAfterEnteringDataValueInMetricDetailsRHP();
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateNumDataTypeMetricDataEntryAndEvidenceUpload() {
		try {
			expandTopicInTasksPage(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			validateAuditLogIntitialValueInMetricDetailsRHP();
			clickOnDataForIntegerInputType();
			enterDataForNumberInputType(data.get("RHPMetricValue"));
			enterExplanationTextRandom();
			saveMetricDetailsinTaskPage();
			validateAuditLogAfterEnteringDataValueInMetricDetailsRHP();
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateIntDataTypeMetricDataEntryAndEvidenceUpload() {
		try {
			expandTopicInTasksPage(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			validateAuditLogIntitialValueInMetricDetailsRHP();
			clickOnDataForIntegerInputType();
			enterDataForIntegerInputType(data.get("RHPMetricValue"));
			enterExplanationTextRandom();
			saveMetricDetailsinTaskPage();
			validateAuditLogAfterEnteringDataValueInMetricDetailsRHP();
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateTextDataTypeMetricDataEntryAndEvidenceUpload() {
		try {
			expandTopicInTasksPage(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			validateAuditLogIntitialValueInMetricDetailsRHP();
			clickOnDataForIntegerInputType();
			enterDataForTextInputType(data.get("RHPMetricValue"));
			enterExplanationTextRandom();
			saveMetricDetailsinTaskPage();
			validateAuditLogAfterEnteringDataValueInMetricDetailsRHP();
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateSingleSelctDataTypeMetricDataEntryAndEvidenceUpload() {
		try {
			expandTopicInTasksPage(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOn(btnReset, "Button Reset");
			saveMetricDetailsinTaskPage();
			validateAuditLogIntitialValueInMetricDetailsRHP();
			enterDataSingleSelectType(data.get("RHPMetricValue"));
			enterExplanationTextRandom();
			saveMetricDetailsinTaskPage();
			validateAuditLogAfterEnteringDataValueInMetricDetailsRHP();
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateTableDataTypeMetricDataEntryAndEvidenceUpload() {
		try {
			expandTopicInTasksPage(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			validateAuditLogForEnteredValueInMetricDetailsRHPTable_InitialCount();
			clickOnDataLinkForTableInputType();
			enterDataForTableInputType(data.get("RHPMetricValue"));
			enterExplanationTextRandom();
			saveMetricDetailsinTaskPage();
			validateAuditLogForEnteredValueInMetricDetailsRHPTable_AfterUpdate();
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
			ValidateDataInputValuesinMetricsvalue();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateBooleanTypeMetricDataEntryAndEvidenceUpload() {
		try {
			expandTopicInTasksPage(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			waitForElement(btnReset);
			clickOn(btnReset, "Button Reset");
			saveMetricDetailsinTaskPage();
			validateAuditLogIntitialValueInMetricDetailsRHP();
			enterDataForBooleanInputType(data.get("RHPMetricValue"));
			enterExplanationTextRandom();
			saveMetricDetailsinTaskPage();
			validateAuditLogAfterEnteringDataValueInMetricDetailsRHP();
			deleteUploadedEvidenceInMetricDetailsRHP();
			uploadEvidenceInMetricDetailsRHP();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateMultiSelctDataTypeMetricDataEntryAndEvidenceUpload() {
		try {
			expandTopicInTasksPage(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			waitForElement(btnReset);
			clickOn(btnReset, "Button Reset");
			saveMetricDetailsinTaskPage();
			validateAuditLogIntitialValueInMetricDetailsRHP();
			enterDataForMultiInputType(data.get("RHPMetricValue"));
			if (!Boolean.parseBoolean(data.get("IsRelatedMetric"))) {
				if (Boolean.parseBoolean(data.get("IsCondOrNonCondValidation"))) {
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
				saveMetricDetailsinTaskPage();
				validateAuditLogAfterEnteringDataValueInMetricDetailsRHP();
				deleteUploadedEvidenceInMetricDetailsRHP();
				uploadEvidenceInMetricDetailsRHP();
			} else {
				clickOn(btnChildMetricReset, "btnChildMetricReset");
				saveMetricDetailsinTaskPage();
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
	public void verifyTasksCreationinProtco() {
//		GlobalVariables.dataRequestName = "TestAutDRrand_hrc046";
		String xpathDataRequest1 = "//p[text()='Request Task']//preceding-sibling::div[text()='"
				+ GlobalVariables.dataRequestName + "']";
		WebElement xpathDataRequest = driver.findElement(By.xpath(xpathDataRequest1));
		if(isElementPresent(xpathDataRequest)) {
			passed("Successfully validated Tasks creation in Protco as " + GlobalVariables.dataRequestName);
		}else {
			failed(driver, "Failed to validate Tasks creation in Protco as " + GlobalVariables.dataRequestName );
		}
	}
	public void verifyTasksandAddingDataInMetrics() {
		try {
			String[] organizations = data.get("OrgName").split(",");
			for (int i = 0; i < organizations.length; i++) {
				clickOnGridViewInTasksPage();
			clickOnStartTaskInTasksPageTiffanyFlow(organizations[i]);
			validateAddMetricValuesTiffanyFlow();
			publishCatalogInTaskPage();
			waitForElement(weTasksMenu);
			clickOn(weTasksMenu, " Tasks Menu");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void validateAddMetricValuesTiffanyFlow() {
		Data data;
		ArrayList<String> datasets;
		data = new Data(Constants.DRTiffanyFlow);
		datasets = data.getDataSets();
		data.setColIndex();
		List<String> logInDetails = new ArrayList();
		for (String dataset : datasets) {
			data.setIndex_Multiple(dataset);
	switch (data.get("InputType")) {
	case "Integer":
		ValidateIntDataTypeMetricDataEntryAndEvidenceUploadTiffanyFlow();
		break;
	case "Number":
		ValidateNumDataTypeMetricDataEntryAndEvidenceUploadTiffenyFlow();
		break;
	case "Text":
		ValidateTextDataTypeMetricDataEntryAndEvidenceUploadTiffanyFlow();
		break;
	case "SingleSelect":
		ValidateSingleSelctDataTypeMetricDataEntryAndEvidenceUploadTiffanyFlow();
		break;
	case "MultiSelect":
		ValidateMultiSelctDataTypeMetricDataEntryAndEvidenceUploadTiffanyFlow();
		break;
	case "Table":
		ValidateTableDataTypeMetricDataEntryAndEvidenceUploadTiffanuFlow();
		break;
	case "Description":
		ValidateDescDataTypeMetricDataEntryAndEvidenceUploadTiffanyFlow();
		break;
	case "Boolean":
		ValidateBooleanTypeMetricDataEntryAndEvidenceUploadTiffanyFlow();
		break;
	default:
	}
		}
	}
	public void enterExplanationTextBasedonCondition(String conditionData) {
		if(Boolean.parseBoolean(conditionData)) {
			enterExplanationText();
		}
	}
	public void ValidateIntDataTypeMetricDataEntryAndEvidenceUploadTiffanyFlow() {
		try {
			expandTopicInTasksPage(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForIntegerInputType();
			enterDataForIntegerInputType(data.get("RHPMetricValue"));
			enterExplanationTextBasedonCondition(data.get("IsExplanation Mandatory"));
			if(Boolean.parseBoolean(data.get("IsEvidence Mandatory"))) {
			uploadEvidenceInMetricDetailsRHP();
			}
			saveMetricDetailsinTaskPage();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	public void ValidateNumDataTypeMetricDataEntryAndEvidenceUploadTiffenyFlow() {
		try {
			expandTopicInTasksPage(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForIntegerInputType();
			enterDataForNumberInputType(data.get("RHPMetricValue"));
			enterExplanationTextBasedonCondition(data.get("IsExplanation Mandatory"));
			if(Boolean.parseBoolean(data.get("IsEvidence Mandatory"))) {
			uploadEvidenceInMetricDetailsRHP();
			}
			saveMetricDetailsinTaskPage();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
		public void ValidateTextDataTypeMetricDataEntryAndEvidenceUploadTiffanyFlow() {
		try {
			expandTopicInTasksPage(data.get("TopicName"));
			clickOnMetricName(data.get("MetricName"));
			clickOnDataForIntegerInputType();
			enterDataForTextInputType(data.get("RHPMetricValue"));
			enterExplanationTextBasedonCondition(data.get("IsExplanation Mandatory"));
			if(Boolean.parseBoolean(data.get("IsEvidence Mandatory"))) {
			uploadEvidenceInMetricDetailsRHP();
			}
			saveMetricDetailsinTaskPage();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
		public void ValidateSingleSelctDataTypeMetricDataEntryAndEvidenceUploadTiffanyFlow() {
			try {
				sleep(100);
				expandTopicInTasksPage(data.get("TopicName"));
				clickOnMetricName(data.get("MetricName"));
				clickOn(btnReset, "Button Reset");
				saveMetricDetailsinTaskPage();
				enterDataSingleSelectType(data.get("RHPMetricValue"));
				enterExplanationTextBasedonCondition(data.get("IsExplanation Mandatory"));
				if(Boolean.parseBoolean(data.get("IsEvidence Mandatory"))) {
				uploadEvidenceInMetricDetailsRHP();
				}
				saveMetricDetailsinTaskPage();
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
		}
		public void ValidateMultiSelctDataTypeMetricDataEntryAndEvidenceUploadTiffanyFlow() {
			try {
				expandTopicInTasksPage(data.get("TopicName"));
				clickOnMetricName(data.get("MetricName"));
				waitForElement(btnReset);
			      clickOn(btnReset, "Button Reset");
			      saveMetricDetailsinTaskPage();
				enterDataForMultiInputType(data.get("RHPMetricValue"));
				enterExplanationTextBasedonCondition(data.get("IsExplanation Mandatory"));
				if(Boolean.parseBoolean(data.get("IsEvidence Mandatory"))) {
				uploadEvidenceInMetricDetailsRHP();
				}
				saveMetricDetailsinTaskPage();
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
		}
		public void ValidateTableDataTypeMetricDataEntryAndEvidenceUploadTiffanuFlow() {
			try {
				expandTopicInTasksPage(data.get("TopicName"));
				clickOnMetricName(data.get("MetricName"));
				clickOnDataLinkForTableInputType();
				enterDataForTableInputType(data.get("RHPMetricValue"));
				enterExplanationTextBasedonCondition(data.get("IsExplanation Mandatory"));
				if(Boolean.parseBoolean(data.get("IsEvidence Mandatory"))) {
				uploadEvidenceInMetricDetailsRHP();
				}
				saveMetricDetailsinTaskPage();
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
		}
		public void ValidateDescDataTypeMetricDataEntryAndEvidenceUploadTiffanyFlow() {
			try {
				expandTopicInTasksPage(data.get("TopicName"));
				clickOnMetricName(data.get("MetricName"));
				clickOnDataForIntegerInputType();
				enterDataForDescInputType(data.get("RHPMetricValue"));
				enterExplanationTextBasedonCondition(data.get("IsExplanation Mandatory"));
				if(Boolean.parseBoolean(data.get("IsEvidence Mandatory"))) {
				uploadEvidenceInMetricDetailsRHP();
				}
				saveMetricDetailsinTaskPage();
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
		}
		public void ValidateBooleanTypeMetricDataEntryAndEvidenceUploadTiffanyFlow() {
			try {
				expandTopicInTasksPage(data.get("TopicName"));
				clickOnMetricName(data.get("MetricName"));
				enterDataForBooleanInputType(data.get("RHPMetricValue"));
				enterExplanationTextBasedonCondition(data.get("IsExplanation Mandatory"));
				if(Boolean.parseBoolean(data.get("IsEvidence Mandatory"))) {
				uploadEvidenceInMetricDetailsRHP();
				}
				saveMetricDetailsinTaskPage();
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
		}
		public void ValidateOneLevelAprovalInTasksPageTiffanyFlow() {
			try {
				String[] organizations = data.get("OrgName").split(",");
				for (int i = 0; i < organizations.length; i++) {
//					GlobalVariables.dataRequestName = "TestAutDRrand_bpr137"; // delete
					clickOnGridViewInTasksPage();
					String xpathDataRequest = "//p[@aria-label='"+organizations[i]+"']//parent::div//parent::div//div[@aria-label='"+ GlobalVariables.dataRequestName + "']";
					clickOnElementWithDynamicXpath(xpathDataRequest, "Data Request Task");
				clickOn(btnClaimTask, "ClaimTask button");
				if (isElementPresent(msgClaimSuccess)) {
					passed("Successfully Validated Claim task success message");
				} else {
					failed(driver, "Failed To Validate  Claim task success message");
				}
				clickOn(btnAproveAll, "Aprove all button");
				if (isElementPresent(msgSuccess)) {
					passed("Successfully Validated Approve ALL success message");
				} else {
					failed(driver, "Failed To Validate  Approve ALL success message");
				}
				clickOn(btnSubmit, "Submit button");
				clickOn(btnYes, "ConfirmYes Button");
				Thread.sleep(5000);
				waitForElement(weTasksMenu);
				clickOn(weTasksMenu, " Tasks Menu");
				}
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
		}
	public TasksPage clickOnStartTaskInTasksPageTiffanyFlow(String orgName) {
//		GlobalVariables.dataRequestName = "TestAutDRrand_vpk189"; // delete
		String xpathDataRequest = "//p[@aria-label='"+orgName+"']//parent::div//parent::div//div[@aria-label='"+ GlobalVariables.dataRequestName + "']";
		clickOnElementWithDynamicXpath(xpathDataRequest, "Data Request Task");
		clickOnElement(btnStart, "Start button");
		return new TasksPage(driver, data);
	}
@FindBy(xpath = "//button[@aria-label='Profile']")
	private WebElement btnProfile;
	@FindBy(xpath = "//div[text()='Switch Organization']")
	private WebElement btnSwitchOrgslabel;
	// div[@aria-labelledby='scroll-dialog-title']//div[text()='Atlanta']//parent::div
	@FindBy(xpath = "//button[text()='Switch']")
	private WebElement btnSwitchOrgs;
	// div[text()='Chevy Chase'] - After switching on profile we can verify switched


	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(lblTasksPage);
			if (isElementPresent(lblTasksPage)) {
				passed("User Successfully Navigated To Tasks Page");
			} else {
				failed(driver, "User Failed To navigate To Tasks Page ");
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
}
