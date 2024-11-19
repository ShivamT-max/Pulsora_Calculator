package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import base.TestBase;
import utilities.Data;
import utilities.GlobalVariables;

public class Admin_AssigneeSetupConfiguration extends TestBase {
	public MenuBarPage returnMenuPage() {
		return new MenuBarPage(driver, data);
	}

	public DataRequestPage returnDataRequestPage() {
		return new DataRequestPage(driver, data);
	}

	protected Admin_AssigneeSetupConfiguration(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	@FindBy(xpath = "//button[text()='Create Period']")
	private WebElement btnCreatePeriod;
	@FindBy(xpath = "//input[@id='periodId']")
	private WebElement txtPeriodId;
	@FindBy(xpath = "//input[@id='periodDesc']")
	private WebElement txtPeriodDesc;
	@FindBy(xpath = "//input[@id='periodStartDate']")
	private WebElement txtPeriodStartDate;
	@FindBy(xpath = "//input[@id='periodEndDate']")
	private WebElement txtPeriodEndDate;
	@FindBy(xpath = "//input[@id='rollUpId']")
	private WebElement txtParentPeriodId;
	@FindBy(xpath = "//input[@id='sortedOrder']")
	private WebElement txtSortOrder;
	@FindBy(xpath = "//*[@id='activeFlag']")
	private WebElement drpActiveFlag;
	@FindBy(xpath = "//button[text()='Create' ]")
	private WebElement btnCreate;
	@FindBy(xpath = "//button[text()='Cancel' ]")
	private WebElement btnCancel;
	@FindBy(xpath = "//span[text()='Organizations']//div[@aria-haspopup='listbox']")
	private WebElement btnSelectOrg;

	public void createPeriodInPeriodConfig() {
		try {
			clickOn(btnCreatePeriod, "Create Period Button");
			enterText(txtPeriodId, "Period Id", data.get("PeriodId"));
			enterText(txtPeriodDesc, "Period Description", data.get("PeriodDescription"));
			enterText(txtPeriodStartDate, "Period Start Date", data.get("PeriodStartDate"));
			enterText(txtPeriodEndDate, "Period End Date", data.get("PeriodEndDate"));
			enterText(txtParentPeriodId, "Parent Period Id", data.get("ParentPeriodId"));
			clickOn(drpActiveFlag, "Active Flag Dropdown");
			String activeFlag = data.get("ActiveFlag");
			clickOnElementWithDynamicXpath("//ul/li[text()='" + activeFlag + "']", "Select ActiveFlag");
			enterText(txtSortOrder, "Sort Order", data.get("SortOrder"));
			clickOn(btnCreate, "Create Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(btnAssigneeSetupConfiguration);
			if (isElementPresent(btnAssigneeSetupConfiguration)) {
				passed("User Successfully Navigated To Assignee Setup Configuration Page");
			} else {
				failed(driver, "Failed To Navigate To Assignee Setup Configuration Page");
			}
			// takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//*[text()='Assignee Setup/Configuration Assignee']")
	private WebElement btnAssigneeSetupConfiguration;
	@FindBy(xpath = "//button[text()='Assignee Setup/Configuration Assignee ']")
	private WebElement btnAssigneeSetupConfigurationAssignee;
	@FindBy(xpath = "//button[text()='Workflow Approval Setup']")
	private WebElement btnWorkflowApprovalSetup;
	@FindBy(xpath = "//*[@data-testid='AddOutlinedIcon']")
	private WebElement btnAddWorkflowApprovalSetup;
	@FindBy(xpath = "//h5[text()='Create Workflow Approval Setup']")
	private WebElement lblCreateWorkflowApprovalSetup;
	// @FindBy(xpath="//*[@data-testid='AddOutlinedIcon']")
	@FindBy(xpath = "//button[text()='Add']")
	private WebElement btnCreateWorkflowApprovalSetupIcon;
//	@FindBy(xpath = "//h6[text()='Data Request Name']//parent::div//div[@aria-haspopup='listbox']")
	@FindBy(xpath = "//h6[text()='Data Request Name']//following-sibling::div//div[@role='button']")
	private WebElement drpDataRequestName;
	@FindBy(xpath = "//h6[text()='Organization Name']//parent::div//div[@aria-haspopup='listbox']")
	private WebElement drpOrganizationName;
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement btnSave;
	@FindBy(xpath = "//*[@data-testid='CloseIcon']")
	private WebElement btnCloseCreateWorkflowApprovalSetup;

	public void verifyCreateWorkflowApprovalSetupForNoLevel() {
		try {
			Thread.sleep(5000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnWorkflowApprovalSetup);
			jsClick(btnWorkflowApprovalSetup, "Workflow Approval Setup");
			jsClick(btnCreateWorkflowApprovalSetupIcon, "Create Workflow Approval Setup Icon");
			verifyIfElementPresent(lblCreateWorkflowApprovalSetup, "Create Workflow Approval Setup",
					"Create Workflow Approval Setup");
			clickOn(drpDataRequestName, "Data Request Name dropdown");
			WebElement lstDRName = driver
					.findElement(By.xpath("//ul/li[text()='" + GlobalVariables.dataRequestName + "']"));
			clickOn(lstDRName, "selected => " + lstDRName.getText() + " DataRequest");
			clickOn(drpOrganizationName, "Organization Name dropdown");
			WebElement lstOrgName = driver
					.findElement(By.xpath("//ul/li[text()='" + data.get("NoLevelApprovalOrgName") + "']"));
			clickOn(lstOrgName, "selected => " + lstOrgName.getText() + " Organization");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();
			clickOn(btnSave, "Save");
			Thread.sleep(5000);
			// clickOn(btnCancel, "Save");
		} catch (Exception e) {
			failed(driver, "Exception occured at verifyCreateWorkflowApprovalSetupForNoLevel => " + e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//h5[text()='List Of Approvals']")
	private WebElement lblListOfApprovals;
	@FindBy(xpath = "(//label[contains(text(),'Approval Level') ])[1]//following-sibling::div")
	private WebElement drpApprovalLevelOne;
	@FindBy(xpath = "(//label[contains(text(),'Approval Level') ])[2]//following-sibling::div")
	private WebElement drpApprovalLevelTwo;
	@FindBy(xpath = "//button[text()='Add Approval Level']")
	private WebElement drpAddApprovalLevel;
	@FindBy(xpath = "//button[text()='Add Approval Level']")
	private WebElement btnAddApprovalLevel;

	public void verifyCreateWorkflowApprovalSetupForOneLevel() {
		try {
			Thread.sleep(5000);
			System.out.println(data.get("1stLevelApproverUserName"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnWorkflowApprovalSetup);
			jsClick(btnWorkflowApprovalSetup, "Workflow Approval Setup");
			clickOn(btnCreateWorkflowApprovalSetupIcon, "Create Workflow Approval Setup Icon");
			verifyIfElementPresent(lblCreateWorkflowApprovalSetup, "Create Workflow Approval Setup",
					"Create Workflow Approval Setup");
			clickOn(drpDataRequestName, "Data Request Name dropdown");
			WaitForElementWithDynamicXpath("//ul/li[text()='" + GlobalVariables.dataRequestName + "']");
			WebElement lstDRName = driver
					.findElement(By.xpath("//ul/li[text()='" + GlobalVariables.dataRequestName + "']"));
			clickOn(lstDRName, "selected => " + lstDRName.getText() + " DataRequest");
			clickOn(drpOrganizationName, "Organization Name dropdown");
			WebElement lstOrgName = driver
					.findElement(By.xpath("//ul/li[text()='" + data.get("1stLevelApprovalOrgName") + "']"));
			clickOn(lstOrgName, "selected => " + lstOrgName.getText() + " Organization");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();
			clickOn(btnSave, "Save");
			Thread.sleep(10000);
			String xpath = "//span[text()='" + GlobalVariables.dataRequestName + "']"
					+ "//ancestor::div[@col-id='dataRequestName']/following-sibling::div[@col-id='orgName']//span[text()='"
					+ data.get("1stLevelApprovalOrgName") + "']"
					+ "/ancestor::div[@col-id='orgName']/following-sibling::div//span[@aria-label='List Of Approvals']/div";
			try {
				WebElement btnListOfapprovals = driver.findElement(By.xpath(xpath));
				clickOn(btnListOfapprovals, "List Of Approvals");
			} catch (Exception e) {
				failed(driver, "Failed at => " + xpath);
			}
			verifyIfElementPresent(lblListOfApprovals, "List Of Approvals", "List Of Approvals");
			System.out.println(waitForElement(btnAddApprovalLevel));
			System.out.println("Add approval Level" + btnAddApprovalLevel.isDisplayed());
			if (btnAddApprovalLevel.isDisplayed()) {
				Thread.sleep(5000);
				// Actions builder = new Actions(driver);
				// builder.moveToElement(btnAddApprovalLevel).click().build().perform();
				System.out.println("" + btnAddApprovalLevel.isDisplayed());
				clickOn(btnAddApprovalLevel, "Add Approval Level");
			}
			if (!drpApprovalLevelOne.isDisplayed()) {
				clickOn(btnAddApprovalLevel, "Add Approval Level");
			}
			clickOn(drpApprovalLevelOne, "Approval Level One dropdown");
			lstOrgName = driver.findElement(By.xpath("//ul/li[text()='" + data.get("1stLevelApproverUserName") + "']"));
			clickOn(lstOrgName, "selected => " + lstOrgName.getText() + " Organization");
			Actions builder = new Actions(driver);
			builder.moveToElement(lblListOfApprovals).click().build().perform();
			clickOn(lblListOfApprovals, "List Of Approvals");
			Thread.sleep(1000);
			action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();
			clickOn(btnSave, "Save");
			// clickOn(btnCancel, "Save");
			Thread.sleep(5000);
		} catch (Exception e) {
			failed(driver, "Exception occured at verifyCreateWorkflowApprovalSetupForOneLevel => " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void verifyCreateWorkflowApprovalSetupForTwoLevel() {
		try {
			Thread.sleep(5000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnWorkflowApprovalSetup);
			jsClick(btnWorkflowApprovalSetup, "Workflow Approval Setup");
			jsClick(btnCreateWorkflowApprovalSetupIcon, "Create Workflow Approval Setup Icon");
			verifyIfElementPresent(lblCreateWorkflowApprovalSetup, "Create Workflow Approval Setup",
					"Create Workflow Approval Setup");
			clickOn(drpDataRequestName, "Data Request Name dropdown");
			WebElement lstDRName = driver
					.findElement(By.xpath("//ul/li[text()='" + GlobalVariables.dataRequestName + "']"));
			clickOn(lstDRName, "selected => " + lstDRName.getText() + " DataRequest");
			clickOn(drpOrganizationName, "Organization Name dropdown");
			WebElement lstOrgName = driver
					.findElement(By.xpath("//ul/li[text()='" + data.get("2ndLevelApprovalOrgName") + "']"));
			clickOn(lstOrgName, "selected => " + lstOrgName.getText() + " Organization");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();
			clickOn(btnSave, "Save");
			Thread.sleep(10000);
			String xpath = "//span[text()='" + GlobalVariables.dataRequestName + "']"
					+ "//ancestor::div[@col-id='dataRequestName']/following-sibling::div[@col-id='orgName']//span[text()='"
					+ data.get("2ndLevelApprovalOrgName") + "']"
					+ "/ancestor::div[@col-id='orgName']/following-sibling::div//span[@aria-label='List Of Approvals']/div";
			try {
				System.out.println(WaitForElementWithDynamicXpath(xpath));
				WebElement btnListOfapprovals = driver.findElement(By.xpath(xpath));
				clickOn(btnListOfapprovals, "List Of Approvals");
			} catch (Exception e) {
				failed(driver, "Failed at => " + xpath);
			}
			verifyIfElementPresent(lblListOfApprovals, "List Of Approvals", "List Of Approvals");
			System.out.println(waitForElement(btnAddApprovalLevel));
			System.out.println("Add approval Level" + btnAddApprovalLevel.isDisplayed());
			if (btnAddApprovalLevel.isDisplayed()) {
				Thread.sleep(5000);
				// Actions builder = new Actions(driver);
				// builder.moveToElement(btnAddApprovalLevel).click().build().perform();
				System.out.println("" + btnAddApprovalLevel.isDisplayed());
				clickOn(btnAddApprovalLevel, "Add Approval Level");
			}
			if (!drpApprovalLevelOne.isDisplayed()) {
				clickOn(btnAddApprovalLevel, "Add Approval Level");
			}
			clickOn(drpApprovalLevelOne, "Approval Level One dropdown");
			lstOrgName = driver.findElement(By.xpath("//ul/li[text()='" + data.get("1stLevelApproverUserName") + "']"));
			clickOn(lstOrgName, "selected => " + lstOrgName.getText() + " Organization");
			waitForElement(lstOrgName);
			Actions builder = new Actions(driver);
			builder.moveToElement(lblListOfApprovals).click().build().perform();
			clickOn(lblListOfApprovals, "List Of Approvals");
			waitForElement(drpAddApprovalLevel);
			clickOn(drpAddApprovalLevel, "Add Approval Level");
			waitForElement(drpApprovalLevelTwo);
			clickOn(drpApprovalLevelTwo, "Approval Level Two dropdown");
			lstOrgName = driver.findElement(By.xpath("//ul/li[text()='" + data.get("2ndLevelApproverUserName") + "']"));
			clickOn(lstOrgName, "selected => " + lstOrgName.getText() + " Organization");
			builder.moveToElement(lblListOfApprovals).click().build().perform();
			clickOn(lblListOfApprovals, "List Of Approvals");
			action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();
			// clickOn(btnCancel, "Save");
			clickOn(btnSave, "Save");
			Thread.sleep(5000);
		} catch (Exception e) {
			failed(driver, "Exception occured at verifyCreateWorkflowApprovalSetupForTwoLevel => " + e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyCreateWorkflowApprovalSetupForMultiLevelWithMultipleApprovers() {
		try {
			Thread.sleep(5000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnWorkflowApprovalSetup);
			jsClick(btnWorkflowApprovalSetup, "Workflow Approval Setup");
			jsClick(btnCreateWorkflowApprovalSetupIcon, "Create Workflow Approval Setup Icon");
			verifyIfElementPresent(lblCreateWorkflowApprovalSetup, "Create Workflow Approval Setup",
					"Create Workflow Approval Setup");
			clickOn(drpDataRequestName, "Data Request Name dropdown");
			WebElement lstDRName = driver
					.findElement(By.xpath("//ul/li[text()='" + GlobalVariables.dataRequestName + "']"));
			clickOn(lstDRName, "selected => " + lstDRName.getText() + " DataRequest");
			clickOn(drpOrganizationName, "Organization Name dropdown");
			WebElement lstOrgName = driver
					.findElement(By.xpath("//ul/li[text()='" + data.get("NoLevelApprovalOrgName") + "']"));
			clickOn(lstOrgName, "selected => " + lstOrgName.getText() + " Organization");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();
			clickOn(btnSave, "Save");
			Thread.sleep(10000);
			String xpath = "//span[text()='" + GlobalVariables.dataRequestName + "']"
					+ "//ancestor::div[@col-id='dataRequestName']/following-sibling::div[@col-id='orgName']//span[text()='"
					+ data.get("2ndLevelApprovalOrgName") + "']"
					+ "/ancestor::div[@col-id='orgName']/following-sibling::div//span[@aria-label='List Of Approvals']/div";
			try {
				System.out.println(WaitForElementWithDynamicXpath(xpath));
				WebElement btnListOfapprovals = driver.findElement(By.xpath(xpath));
				clickOn(btnListOfapprovals, "List Of Approvals");
			} catch (Exception e) {
				failed(driver, "Failed at => " + xpath);
			}
			verifyIfElementPresent(lblListOfApprovals, "List Of Approvals", "List Of Approvals");
			System.out.println(waitForElement(btnAddApprovalLevel));
			System.out.println("Add approval Level" + btnAddApprovalLevel.isDisplayed());
			if (btnAddApprovalLevel.isDisplayed()) {
				System.out.println("" + btnAddApprovalLevel.isDisplayed());
				clickOn(btnAddApprovalLevel, "Add Approval Level");
			}
			if (!drpApprovalLevelOne.isDisplayed()) {
				clickOn(btnAddApprovalLevel, "Add Approval Level");
			}
			clickOn(drpApprovalLevelOne, "Approval Level One dropdown");
			lstOrgName = driver.findElement(By.xpath("//ul/li[text()='" + data.get("1stLevelApproverUserName") + "']"));
			clickOn(lstOrgName, "selected => " + lstOrgName.getText() + " Organization");
			lstOrgName = driver
					.findElement(By.xpath("//ul/li[text()='" + data.get("1stLevelMultiApproverUserName") + "']"));
			clickElement(lstOrgName, "selected => " + lstOrgName.getText() + " Organization");
			action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();
			clickOn(btnAddApprovalLevel, "Add Approval Level");
			clickOn(drpApprovalLevelTwo, "Approval Level Two dropdown");
			lstOrgName = driver.findElement(By.xpath("//ul/li[text()='" + data.get("2ndLevelApproverUserName") + "']"));
			clickElement(lstOrgName, "selected => " + lstOrgName.getText() + " Organization");
			action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();
			clickOn(btnSave, "Save");
			Thread.sleep(5000);
		} catch (Exception e) {
			failed(driver, "Exception occured at verifyCreateWorkflowApprovalSetupForTwoLevel => " + e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void verifyCreateWorkflowApprovalSetupForOneLevelTiffany() {
		try {
			Thread.sleep(5000);
			System.out.println(data.get("1stLevelApproverUserName"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnWorkflowApprovalSetup);
			jsClick(btnWorkflowApprovalSetup, "Workflow Approval Setup");
			clickOn(btnCreateWorkflowApprovalSetupIcon, "Create Workflow Approval Setup Icon");
			verifyIfElementPresent(lblCreateWorkflowApprovalSetup, "Create Workflow Approval Setup",
					"Create Workflow Approval Setup");
			WebElement drname= driver.findElement(By.xpath("//h6[text()='Data Request Name']//following-sibling::div//div[@role='button']"));
			clickOn(drname, "Data Request Name dropdown");
			WaitForElementWithDynamicXpath("//ul/li[text()='" + GlobalVariables.dataRequestName + "']");
			WebElement lstDRName = driver
					.findElement(By.xpath("//ul/li[text()='" + GlobalVariables.dataRequestName + "']"));
			clickOn(lstDRName, "selected => " + lstDRName.getText() + " DataRequest");
			selectOrgFromHierarchyInAssigneeSetup(data.get("OrgName"));			
			clickOn(btnSave, "Save");
			Thread.sleep(10000);
			/*
			 * String xpath = "//span[text()='" + GlobalVariables.dataRequestName + "']" +
			 * "//ancestor::div[@col-id='dataRequestName']/following-sibling::div[@col-id='orgName']//span[text()='"
			 * + data.get("1stLevelApprovalOrgName") + "']" +
			 * "/ancestor::div[@col-id='orgName']/following-sibling::div//span[@aria-label='List Of Approvals']/div"
			 * ;
			 */
			String xpath = "//span[text()='"+GlobalVariables.dataRequestName+"']//ancestor::div[@col-id='dataRequestName']/following-sibling::div[@col-id='orgName']//span/parent::div/parent::div/parent::div//following-sibling::div//span[@aria-label='List Of Approvals']/div";
			try {
				WebElement btnListOfapprovals = driver.findElement(By.xpath(xpath));
				clickOn(btnListOfapprovals, "List Of Approvals");
			} catch (Exception e) {
				failed(driver, "Failed at => " + xpath);
			}
			verifyIfElementPresent(lblListOfApprovals, "List Of Approvals", "List Of Approvals");
			System.out.println(waitForElement(btnAddApprovalLevel));
			System.out.println("Add approval Level" + btnAddApprovalLevel.isDisplayed());
			
			clickOn(btnaddOrganization, "CLicked on Add org for list of approvals");
			clickOnfilterOrgLabels(data.get("Org Hierarchy Type"));			
			enterText(btnSearchOrg, "Search Org", data.get("defaultOrg"));
			WebElement orgNameAssigneSetup = driver
					.findElement(By.xpath("//li[@role='treeitem']//div[text()='" + data.get("defaultOrg") + "']"));
			clickOn(orgNameAssigneSetup, "Select org " + data.get("defaultOrg"));
			WebElement orgGridRHPApprover = driver.findElement(By.xpath("//div[@role='row']/*[text()='"+data.get("defaultOrg")+"']"));
			clickOn(orgGridRHPApprover, "clicked on "+data.get("defaultOrg"));
			clickOn(btnSelectOrganizations, "Selected Organizations "+data.get("defaultOrg"));
			//clickOn(btnSelectOrganizations, "Selected Organizations");
			clickOn(drpSelectRoles, "clickedon select role");
			WebElement listselectRole = driver.findElement(By.xpath("//span[text()='"+data.get("UserRole")+"']//parent::div//parent::li//input"));
			clickOn(listselectRole, "Clicked on User Role "+data.get("UserRole"));
			Actions action = new Actions(driver);
			action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();
			clickOn(drpSelectUser, "clicked on user role");
			String[] approverFirstName = data.get("Approval Name").split(" ");
			WebElement approverName = driver.findElement(By.xpath("//li//span[text()='"+approverFirstName[0]+"']"));
			clickOn(approverName, "clicked on approval Name"+approverFirstName[0]);
			action.sendKeys(Keys.ESCAPE).build().perform();
			clickOn(btnSave, "Save");
			Thread.sleep(5000);
		} catch (Exception e) {
			failed(driver, "Exception occured at verifyCreateWorkflowApprovalSetupForOneLevel => " + e.getMessage());
			e.printStackTrace();
		}
	}
	public void clickOnfilterOrgLabels(String filterOrg) {
		try {
			WebElement orgHierarchyTyep = driver.findElement(By.xpath("//span[text()='"+filterOrg+"']/parent::div"));
			if(orgHierarchyTyep.getAttribute("class").contains("false")) {
				WebElement orgorgHierarchyTyep1 = driver.findElement(By.xpath("//span[text()='"+filterOrg+"']"));
				clickOn(orgorgHierarchyTyep1, "Clicked on "+filterOrg);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@FindBy(xpath = "//div[text()='Add Organization']")
	private WebElement btnaddOrganization;
	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement btnSearchOrg;
	@FindBy(xpath = "//button[text()='Select']")
	private WebElement btnSelectOrganizations;
	@FindBy(xpath = "//span[text()='Select Roles']")
	private WebElement drpSelectRoles;
	@FindBy(xpath = "//span[text()='Select Users']")
	private WebElement drpSelectUser;
	public void selectOrgFromHierarchyInAssigneeSetup(String orgNames) {
		try {
			String[] organizations = orgNames.split(",");			
			for (int i = 0; i < organizations.length; i++) {
				clickOn(btnaddOrganization, "clicked on Add Organization button");
				enterText(btnSearchOrg, "Search Org", organizations[i]);
				WebElement orgNameAssigneSetup = driver
						.findElement(By.xpath("//li[@role='treeitem']//div[text()='" + organizations[i] + "']"));
				clickOn(orgNameAssigneSetup, "Select org " + organizations[i]);
				WebElement orgRHPAssigneSetupCheckBox = driver
						.findElement(By.xpath("//span[text()='"+organizations[i]+"']//parent::div/div//input"));
				clickOn(orgRHPAssigneSetupCheckBox, "Selected org checkBox" + organizations[i]);
				clickOn(btnSelectOrganizations, "Selected Organizations");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
