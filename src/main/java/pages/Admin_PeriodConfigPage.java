package pages;



import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.TestBase;
import utilities.Data;
import utilities.MultiDataExcelReader;

public class Admin_PeriodConfigPage extends TestBase {

	protected Admin_PeriodConfigPage(WebDriver driver2, Data data) {
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

	public void CreatePeriodConfigForMultipleSetsOfData(int i) {

		try {
			List<String> OrgName = MultiDataExcelReader.getTestData("PeriodSetUp", "OrgName");

			List<String> PeriodId = MultiDataExcelReader.getTestData("PeriodSetUp", "PeriodId");

			List<String> PeriodDesc = MultiDataExcelReader.getTestData("PeriodSetUp", "PeriodDesc");

			List<String> StartDate = MultiDataExcelReader.getTestData("PeriodSetUp", "StartDate");

			List<String> EndDate = MultiDataExcelReader.getTestData("PeriodSetUp", "EndDate");

			List<String> ActiveFlag = MultiDataExcelReader.getTestData("PeriodSetUp", "ActiveFlag");

			List<String> SortOrder = MultiDataExcelReader.getTestData("PeriodSetUp", "SortOrder");

			List<String> logInIdDataList = MultiDataExcelReader.getTestData("PeriodSetUp", "LoginId");

			String logInId = logInIdDataList.get(i);

			for (int j = i-1; j <logInIdDataList.size(); j++) {
	
				if (logInId.equals(logInIdDataList.get(j))) {
					
					waitForElement(btnSelectOrg);

					clickOn(btnSelectOrg, "Select Org Button");
					
					Thread.sleep(500);
					
					String xpathOrg = "//li[text()='" + OrgName.get(j) + "']";
			
					clickOnElementWithDynamicXpath(xpathOrg, "Organizatiopn Name");
					
					clickOn(btnCreatePeriod, "Create Period Button");

					enterText(txtPeriodId, "Period Id", PeriodId.get(j));

					enterText(txtPeriodDesc, "Period Description", PeriodDesc.get(j));

					enterText(txtPeriodStartDate, "Period Start Date", StartDate.get(j));

					enterText(txtPeriodEndDate, "Period End Date", EndDate.get(j));

					clickOn(drpActiveFlag, "Active Flag Dropdown");

					String activeFlag = ActiveFlag.get(j);
					
					String xpathFlag="//ul/li[text()='" + activeFlag + "']";
					
					waitForElement(driver.findElement(By.xpath(xpathFlag)));

					clickOnElementWithDynamicXpath(xpathFlag, "Select ActiveFlag");

					enterText(txtSortOrder, "Sort Order", SortOrder.get(j));

					clickOn(btnCreate, "Create Button");

				}

			}

		} catch (Exception e) {

			failed(driver, "Exception caught " + e.getMessage());
		}

	}
	public void CreatePeriodConfigForMultipleSetsOfData1() {

		try {
			
		List<String> logInIdDataList = MultiDataExcelReader.getTestData("PeriodSetUp", "LoginId");

		List<String> Password = MultiDataExcelReader.getTestData("PeriodSetUp", "Password");

		List<String> OrgName = MultiDataExcelReader.getTestData("PeriodSetUp", "OrgName");

		List<String> PeriodId = MultiDataExcelReader.getTestData("PeriodSetUp", "PeriodId");

		List<String> PeriodDesc = MultiDataExcelReader.getTestData("PeriodSetUp", "PeriodDesc");

		List<String> StartDate = MultiDataExcelReader.getTestData("PeriodSetUp", "StartDate");

		List<String> EndDate = MultiDataExcelReader.getTestData("PeriodSetUp", "EndDate");

		List<String> ActiveFlag = MultiDataExcelReader.getTestData("PeriodSetUp", "ActiveFlag");

		List<String> SortOrder = MultiDataExcelReader.getTestData("PeriodSetUp", "SortOrder");
		
		
			for (int i = 0; i < logInIdDataList.size(); i++) {

					clickOn(btnSelectOrg, "Select Org Button");
					
					String	xpath= "//li[text()='Select']/following-sibling::li[text()='"+OrgName.get(i)+"']";
					
					clickOnElementWithDynamicXpath(xpath, "Organizatiopn Name");

					clickOn(btnCreatePeriod, "Create Period Button");

					enterText(txtPeriodId, "Period Id", PeriodId.get(i));

					enterText(txtPeriodDesc, "Period Description", PeriodDesc.get(i));

					enterText(txtPeriodStartDate, "Period Start Date", StartDate.get(i));

					enterText(txtPeriodEndDate, "Period End Date", EndDate.get(i));

					clickOn(drpActiveFlag, "Active Flag Dropdown");

					String activeFlag = ActiveFlag.get(i);

					clickOnElementWithDynamicXpath("//ul/li[text()='" + activeFlag + "']", "Select ActiveFlag");

					enterText(txtSortOrder, "Sort Order", SortOrder.get(i));

					clickOn(btnCreate, "Create Button");

				}
			
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}

	}
	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(btnCreatePeriod);

			if (isElementPresent(btnCreatePeriod)) {

				passed("User Successfully Navigated To Period Config Page");
			} else {
				failed(driver, "Failed To Navigate To Period Config Page");

			}

			takeScreenshot(driver);

		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}

	}

}
