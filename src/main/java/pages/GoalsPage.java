package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import base.TestBase;
import utilities.Data;
import utilities.GlobalVariables;

public class GoalsPage extends TestBase {
	protected GoalsPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	public MenuBarPage returnMenuPage() {
		return new MenuBarPage(driver, data);
	}

	@FindBy(xpath = "//input[@type='search']")
	private WebElement txtSearch;

	public void verifyGoalsMenus() {
		if (isElementPresent(btnEdit)) {
			passed("Successfully validated Edit Button in Goals Page");
			clickOn(btnEdit, "edit button");
			takeScreenshot(driver);
		} else {
			failed(driver, "Failed To validate The Edit Button in Goals Page");
		}
		if (isElementPresent(btnDelete)) {
			passed("Successfully validated delete Button in Goals Page");
		} else {
			failed(driver, "Failed To validate The Delete Button in Goals Page");
		}
		if (isElementPresent(btnSearch)) {
			passed("Successfully validated Search Button in Goals Page");
			clickOn(btnSearch, "Search button");
			takeScreenshot(driver);
			enterText(txtSearch, "Seacrch button", "Hello World");
		} else {
			failed(driver, "Failed To validate The Search Button in Goals Page");
		}
	}

	@FindBy(xpath = "//li[text()='Goals']/preceding-sibling::li[text()='Home']")
	private WebElement lnkHome;
	@FindBy(xpath = "//article//div[text()='Home']")
	private WebElement weHomeHeader;

	public void NavigateToHomeFromBreadCrumLink() {
		try {
			clickOn(lnkHome, "Home");
			if (verifyElementAndHighlightIfExists(weHomeHeader, "Home", "Home Page")) {
				passed("Successfully navigated to Home page");
			} else {
				failed(driver, "Unable to navigate to Home Page");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(lblGoalsPage);
			if (isElementPresent(lblGoalsPage)) {
				passed("User Successfully Navigated To Goals Page");
			} else {
				failed(driver, "failed To Navigate To Goals Page");
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//div/*[contains(text(),'Goals')]")
	private WebElement lblGoalsPage;
	@FindBy(xpath = "//*[@data-testid='EditOutlinedIcon']")
	private WebElement btnEdit;
	@FindBy(xpath = "//*[@data-testid='DeleteOutlineOutlinedIcon']")
	private WebElement btnDelete;
	// *[text()='You want to delete the
	// goal?']/parent::div/following-sibling::div/button[text()='Yes']
	@FindBy(xpath = "//button[text()='Delete']")
	private WebElement btnDeleteGoalConfirm;
	@FindBy(xpath = "//*[@data-testid='SearchOutlinedIcon']")
	private WebElement btnSearch;
	@FindBy(xpath = "//input[@placeholder='Search Goal']")
	private WebElement txtSrchGoal;
	@FindBy(xpath = "//span[contains(text(),'Goals')]/following-sibling::span")
	private WebElement weGoalsCount;
	@FindBy(xpath = "//div[text()='No rows to show.']")
	private WebElement weNoRowsToShow;
	// @FindBy(xpath = "//span[text()='Period
	// :']//input/following-sibling::*[@data-testid='ArrowDropDownIcon']")
	@FindBy(xpath = "//span[contains(text(),'Period:')]//input/preceding-sibling::div")
	private WebElement drpPeriod;
	@FindBy(xpath = "//*[@data-testid='CloseIcon']")
	private WebElement btnSrchClose;
	@FindBy(xpath = "//button[text()='Goals Archived']")
	private WebElement btnGoalsArchived;
	@FindBy(xpath = "//button[text()='Goals']")
	private WebElement btnGoals;

	public void verifyGoalsPageAndArchivePageWhenThereAreNoGoals() {
		WebElement weHeader;
		// 1.1Goals Count - 0
		String strGoalCount = "", strGoalArchivedCount = "";
		strGoalCount = weGoalsCount.getText();
		Assert.assertEquals(0, Integer.parseInt(strGoalCount));
		String[] weHeaders = { "Goal Name", "Base Date", "Base Value", "Target Date", "Target Value", "Goal Progress",
				"Topic", "Metric", "Data", "Audit" };
		// Headers
		for (int i = 0; i < weHeaders.length; i++) {
			weHeader = driver.findElement(By.xpath("//div[@role='columnheader']//span[text()='" + weHeaders[i] + "']"));
			verifyElementAndHighlightIfExists(weHeader, weHeaders[i].toString(), "Goals");
		}
		// No Rows To Show
		verifyElementAndHighlightIfExists(weNoRowsToShow, "No Rows To Show", "Goals Page");
		// verify elements
		List<WebElement> lstElements = new ArrayList<>();
		lstElements.add(btnGoals);
		lstElements.add(btnGoalsArchived);
		lstElements.add(btnEdit);
		lstElements.add(btnDelete);
		lstElements.add(btnSearch);
		lstElements.add(drpPeriod);
		for (int i = 0; i < lstElements.size(); i++) {
			if (!lstElements.get(i).equals("btnSearch")) {
				verifyElementAndHighlightIfExists(lstElements.get(i), lstElements.get(i).toString(), "Goals");
			} else {
				verifyElementAndHighlightIfExists(btnSearch, lstElements.get(i).toString(), "Goals Page");
				clickOn(btnSearch, "Search Icon/button");
				verifyElementAndHighlightIfExists(txtSrchGoal, "txtSrchGoal", "Goals Page");
				verifyElementAndHighlightIfExists(btnSrchClose, "btnSrchClose", "Goals Page");
				clickOn(btnSrchClose, "Search Close Icon/button");
				verifyElementAndHighlightIfExists(btnSearch, lstElements.get(i).toString(), "Goals Page");
			}
		}
		// 2 )
		clickOn(btnGoalsArchived, "Goals Archived button");
		// 1.1Goals Count - 0
		strGoalArchivedCount = weGoalsCount.getText();
		Assert.assertEquals(0, Integer.parseInt(strGoalArchivedCount));
		String[] weGoalsArchivedHeaders = { "Goal Name", "Base Date", "Base Value", "Target Date", "Target Value",
				"Goal Progress", "Topic", "Metric", "Data", "Actions" };
		// Headers
		for (int i = 0; i < weGoalsArchivedHeaders.length; i++) {
			weHeader = driver.findElement(
					By.xpath("//div[@role='columnheader']//span[text()='" + weGoalsArchivedHeaders[i] + "']"));
			verifyElementAndHighlightIfExists(weHeader, weGoalsArchivedHeaders[i], "Goals Archived");
		}
		// No Rows To Show
		verifyElementAndHighlightIfExists(weNoRowsToShow, "No Rows To Show", "Goals Archived Page");
		// verify elements
		List<WebElement> lstArchivedElements = new ArrayList<>();
		lstArchivedElements.add(btnGoals);
		lstArchivedElements.add(btnGoalsArchived);
		lstArchivedElements.add(btnEdit);
		lstArchivedElements.add(btnDelete);
		lstArchivedElements.add(drpPeriod);
		for (int i = 0; i < lstArchivedElements.size(); i++) {
			verifyElementAndHighlightIfExists(lstArchivedElements.get(i), lstArchivedElements.get(i).toString(),
					"Goals Archived");
			passed(lstArchivedElements.get(i) + " element is present");
		}
	}

	public void verifyGoalsPageAndArchivePageWhenThereAreGoals() {
		try {
			WebElement weHeader;
			// 1.1Goals Count - 0
			String strGoalCount = "", strGoalArchivedCount = "";
			String periodName = data.get("Period");
			clickOn(drpPeriod, "Period Dropdown");
			WebElement wePeriodName = driver
					.findElement(By.xpath("//ul[@role='listbox']/li[text()='" + periodName + "']"));
			clickOn(wePeriodName, periodName);
			Thread.sleep(5000);
			strGoalCount = weGoalsCount.getText();
			List<WebElement> rows = driver.findElements(By.xpath("//div[@role='rowgroup']/div"));
			if (rows.size() == (Integer.parseInt(strGoalCount) + 1)) {
				passed("Goals Rows count are matching with Goals count");
				// Assert.assertEquals(rows.size(), Integer.parseInt(strGoalCount)+1);
			} else {
				failed(driver, "Goals Rows count are not matching with Goals count, actual count=> "
						+ (Integer.parseInt(strGoalCount) + 1) + ", Expected count => " + strGoalCount);
			}
			String[] weHeaders = { "Goal Name", "Base Date", "Base Value", "Target Date", "Target Value",
					"Goal Progress", "Topic", "Metric", "Data", "Audit" };
			// Headers
			for (int i = 0; i < weHeaders.length; i++) {
				weHeader = driver
						.findElement(By.xpath("//div[@role='columnheader']//span[text()='" + weHeaders[i] + "']"));
				// if (isElementPresent(weHeader)) {
				// ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px
				// solid red'", weHeader);
				// passed(weHeader + "Element is displayed in Goals page");
				// } else {
				// failed(driver, weHeader + "Element is not displayed in Goals Archived page");
				// }
				verifyElementAndHighlightIfExists(weHeader, weHeaders[i].toString(), "Goals");
			}
			// verify elements
			List<WebElement> lstElements = new ArrayList<>();
			lstElements.add(btnGoals);
			lstElements.add(btnGoalsArchived);
			lstElements.add(btnEdit);
			lstElements.add(btnDelete);
			lstElements.add(btnSearch);
			lstElements.add(drpPeriod);
			for (int i = 0; i < lstElements.size(); i++) {
				if (!lstElements.get(i).equals("btnSearch")) {
					verifyElementAndHighlightIfExists(lstElements.get(i), lstElements.get(i).toString(), "Goals");
				} else {
					verifyElementAndHighlightIfExists(btnSearch, lstElements.get(i).toString(), "Goals Page");
					clickOn(btnSearch, "Search Icon/button");
					verifyElementAndHighlightIfExists(txtSrchGoal, "txtSrchGoal", "Goals Page");
					verifyElementAndHighlightIfExists(btnSrchClose, "btnSrchClose", "Goals Page");
					clickOn(btnSrchClose, "Search Close Icon/button");
					verifyElementAndHighlightIfExists(btnSearch, lstElements.get(i).toString(), "Goals Page");
				}
			}
			// 2 )
			clickOn(btnGoalsArchived, "Goals Archived button");
			Thread.sleep(5000);
			// 1.1Goals Count - 0
			strGoalArchivedCount = weGoalsCount.getText();
			List<WebElement> rowsGoalsArchived = driver.findElements(By.xpath("//div[@role='rowgroup']/div"));
			if (rowsGoalsArchived.size() == (Integer.parseInt(strGoalArchivedCount) + 1)) {
				passed("Goals Rows count are matching with Goals count");
				// Assert.assertNotEquals(0, Integer.parseInt(strGoalArchivedCount));
			} else {
				failed(driver, "Goals Rows count are not matching with Goals count, actual count=> "
						+ (Integer.parseInt(strGoalArchivedCount) + 1) + ", Expected count => " + strGoalCount);
			}
			String[] weGoalsArchivedHeaders = { "Goal Name", "Base Date", "Base Value", "Target Date", "Target Value",
					"Goal Progress", "Topic", "Metric", "Data", "Actions" };
			// Headers
			for (int i = 0; i < weGoalsArchivedHeaders.length; i++) {
				weHeader = driver.findElement(
						By.xpath("//div[@role='columnheader']//span[text()='" + weGoalsArchivedHeaders[i] + "']"));
				verifyElementAndHighlightIfExists(weHeader, weGoalsArchivedHeaders[i], "Goals Archived");
			}
			// verify elements
			List<WebElement> lstArchivedElements = new ArrayList<>();
			lstArchivedElements.add(btnGoals);
			lstArchivedElements.add(btnGoalsArchived);
			lstArchivedElements.add(btnEdit);
			lstArchivedElements.add(btnDelete);
			lstArchivedElements.add(drpPeriod);
			for (int i = 0; i < lstArchivedElements.size(); i++) {
				verifyElementAndHighlightIfExists(lstArchivedElements.get(i), lstArchivedElements.get(i).toString(),
						"Goals Archived");
				passed(lstArchivedElements.get(i) + " element is present");
			}
		} catch (Exception e) {
			failed(driver, "Exception occured at verifyGoalsPageAndArchivePageWhenThereAreGoals" + e.getMessage());
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blnFnd;
	}

	public void verifySortOrderOfGoalsPage() {
		try {
			// Ascending order
			String[] headerNames = { "Goal Name", "Base Date", "Base Value", "Target Date", "Target Value",
					"Goal Progress", "Topic", "Metric" };
			String[] colId = { "goalName", "baseUnixDate", "baseDataValue", "targetUnixDate", "targetDataValue",
					"progress", "topicName", "metricName" };
			for (int i = 0; i < headerNames.length; i++) {
				LinkedList<String> obtainedList = new LinkedList<>();
				List<WebElement> elementList = new LinkedList<>();
				WebElement weCol = driver.findElement(By.xpath("//span[text()='" + headerNames[i] + "']"));
				clickOn(weCol, headerNames[i] + " Coulmn");
				sleep(3000);
				WebElement weAscIcon = driver.findElement(By.xpath("//span[text()='" + headerNames[i]
						+ "']/following-sibling::span[@ref='eSortAsc' and contains(@class,'ag-sort-ascending-icon') and not(contains(@class,'ag-hidden'))]"));
				verifyIfElementPresent(weAscIcon, "Ascending Order", "Goals Page");
				String cellVal = "";
				elementList = driver
						.findElements(By.xpath("//div[@col-id='" + colId[i] + "' and not(@role='columnheader')]"));
				int rowCnt = elementList.size() + 1;
				for (int r = 2; r <= rowCnt; r++) {
					if (headerNames[i] == "Goal Name") {
						cellVal = driver.findElement(By.xpath("//*[@aria-rowindex='" + r + "']/div[@col-id='" + colId[i]
								+ "']//span[@ref='eCellValue']")).getText();
					} else {
						cellVal = driver
								.findElement(
										By.xpath("//*[@aria-rowindex='" + r + "']/div[@col-id='" + colId[i] + "']"))
								.getText();
					}
					obtainedList.add(cellVal);
				}
				ArrayList<String> sortedList = new ArrayList<>();
				for (String s : obtainedList) {
					sortedList.add(s);
				}
				Collections.sort(sortedList);
				System.out.println(sortedList.equals(obtainedList));
				Assert.assertTrue(sortedList.equals(obtainedList));
				passed(headerNames[i] + " is in ascending order");
			}
			// Descending order
			for (int i = 0; i < headerNames.length; i++) {
				LinkedList<String> obtainedList = new LinkedList<>();
				List<WebElement> elementList = new LinkedList<>();
				WebElement weCol = driver.findElement(By.xpath("//span[text()='" + headerNames[i] + "']"));
				jsClick(weCol, headerNames[i] + " Coulmn");
				jsClick(weCol, headerNames[i] + " Coulmn");
				WebElement weDescIcon = driver.findElement(By.xpath("//span[text()='" + headerNames[i]
						+ "']/following-sibling::span[@ref='eSortDesc' and contains(@class,'ag-sort-descending-icon') and not(contains(@class,'ag-hidden'))]"));
				verifyIfElementPresent(weDescIcon, "Descending Order", "Goals Page");
				String cellVal = "";
				elementList = driver
						.findElements(By.xpath("//div[@col-id='" + colId[i] + "' and not(@role='columnheader')]"));
				int rowCnt = elementList.size() + 1;
				for (int r = 2; r <= rowCnt; r++) {
					if (headerNames[i] == "Goal Name") {
						cellVal = driver.findElement(By.xpath("//*[@aria-rowindex='" + r + "']/div[@col-id='" + colId[i]
								+ "']//span[@ref='eCellValue']")).getText();
					} else {
						cellVal = driver
								.findElement(
										By.xpath("//*[@aria-rowindex='" + r + "']/div[@col-id='" + colId[i] + "']"))
								.getText();
					}
					obtainedList.add(cellVal);
				}
				ArrayList<String> sortedList = new ArrayList<>();
				for (String s : obtainedList) {
					sortedList.add(s);
				}
				Collections.sort(sortedList, Collections.reverseOrder());
				System.out.println(sortedList.equals(obtainedList));
				Assert.assertTrue(sortedList.equals(obtainedList));
				passed(headerNames[i] + " is in Descending order");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//div[contains(@class,'MuiDrawer-pape')]/div//*[local-name() = 'svg']")
	private WebElement weHamburgerMenu;

	public void closeSidePannel() {
		clickOn(weHamburgerMenu, "clicked on hamberger menu");
	}

	public int getGoalsCount() {
		String strGoalCount = "";
		try {
			strGoalCount = weGoalsCount.getText();
		} catch (Exception e) {
			failed(driver, "Exception occured at getGoalsCount method" + e.getMessage());
		}
		return Integer.parseInt(strGoalCount);
	}

	public void getAndVerifyGoalsProgressForNoMetricData(String goalName) {
		String strProgressVal = "", strProgressPerct = "";
		try {
			Thread.sleep(20000);
			WebElement weGoal = driver.findElement(By.xpath("//span[text()='" + goalName + "']"));
			verifyElementAndHighlightIfExists(weGoal, "Goal Name => " + goalName, "Goals Page");
			WebElement weProgress = driver.findElement(By.xpath("//span[text()='" + goalName
					+ "']/parent::div/parent::div[@col-id='goalName']/following-sibling::div[@col-id='progress']//span"));
			verifyElementAndHighlightIfExists(weProgress, "Progress %", "Goals Page");
			strProgressVal = weProgress.getText();
			String color = weProgress.getCssValue("color");
			String[] strProgressValArr = strProgressVal.split(":");
			strProgressPerct = strProgressValArr[1].trim();
			if (strProgressPerct.equals("0%")) {
				passed("Goal Progress in Goals page for " + goalName + " Goal is 0%");
			} else {
				failed(driver, "Goal Progress in Goals page for " + goalName + " Goal is not 0%");
			}
			if (strProgressPerct.contains("-")) {
				if (color.equals("rgba(255, 0, 0, 1)")) {
					passed("Goal Progress % is in Red color when Percentage is negative " + goalName + " Goal");
				} else {
					failed(driver, "Goal Progress % is not in Red color when Percentage is negative " + goalName
							+ " Goal and current color is " + color);
				}
			} else {
				if (color.equals("rgba(26, 199, 26, 1)")) {
					passed("Goal Progress % is in Green color when Percentage is negative " + goalName + " Goal");
				} else {
					failed(driver, "Goal Progress % is not in Green color when Percentage is negative " + goalName
							+ " Goal and current color is " + color);
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception occured at getGoalsCount method" + e.getMessage());
		}
	}

	public void getAndVerifyGoalsProgressForProvidedMetricData(String goalName, int metricData) {
		String strProgressVal = "", strProgressPerct = "";
		try {
			Thread.sleep(5000);
			WebElement weGoal = driver.findElement(By.xpath("//span[text()='" + goalName + "']"));
			verifyElementAndHighlightIfExists(weGoal, "Goal Name => " + goalName, "Goals Page");
			WebElement weProgress = driver.findElement(By.xpath("//span[text()='" + goalName
					+ "']/parent::div/parent::div[@col-id='goalName']/following-sibling::div[@col-id='progress']//span"));
			verifyElementAndHighlightIfExists(weProgress, "Progress % ", "Goals Page");
			strProgressVal = weProgress.getText();
			String color = weProgress.getCssValue("color");
			String[] strProgressValArr = strProgressVal.split(":");
			strProgressPerct = strProgressValArr[1].trim();
			double CurrentValue = metricData;
			double baseValue = Integer.parseInt(data.get("BaseValue"));
			double TargetValue = Integer.parseInt(data.get("TargetValue"));
			double eq1 = CurrentValue - baseValue;
			double eq2 = TargetValue - baseValue;
			double res = eq1 / eq2;
			double resPerct = res * 100;
			int progressPerct = (int) resPerct;
			System.out.println(progressPerct);
			if (strProgressPerct.equals(Integer.toString(progressPerct) + "%")) {
				passed("Goal Progress in Goals page for " + goalName + " Goal is " + Integer.toString(progressPerct)
						+ "%");
			} else {
				failed(driver, "Goal Progress in Goals page for " + goalName + " Goal is not "
						+ Integer.toString(progressPerct) + "%");
			}
			if (strProgressPerct.contains("-")) {
				if (color.equals("rgba(255, 0, 0, 1)")) {
					passed("Goal Progress % is in Red color when Progress Percentage value is Negative " + goalName
							+ " Goal");
				} else {
					failed(driver, "Goal Progress % is not in Red color when Progress Percentage value is Negative "
							+ goalName + " Goal and current color is " + color);
				}
			} else {
				if (color.equals("rgba(26, 199, 26, 1)")) {
					passed("Goal Progress % is in Green color when Progress Percentage value is Positive " + goalName
							+ " Goal");
				} else {
					failed(driver, "Goal Progress % is not in Green color when Progress Percentage value is Positive  "
							+ goalName + " Goal and current color is " + color);
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception occured at getGoalsCount method" + e.getMessage());
		}
	}

	public void getAndVerifyGoalsProgressForProvidedSingleSelectMetricData(String goalName, String progressPerct) {
		String strProgressVal = "", strProgressPerct = "";
		try {
			boolean blnFnd = true;
			WebElement weGoal, weProgress;
			String color = null;
			// retry 5 times for 20 secs to get progress % to be loaded
			for (int i = 0; i < 4; i++) {
				Thread.sleep(5000);
				weGoal = driver.findElement(By.xpath("//span[text()='" + goalName + "']"));
				verifyElementAndHighlightIfExists(weGoal, "Goal Name => " + goalName, "Goals Page");
				weProgress = driver.findElement(By.xpath("//span[text()='" + goalName
						+ "']/parent::div/parent::div[@col-id='goalName']/following-sibling::div[@col-id='progress']//span"));
				verifyElementAndHighlightIfExists(weProgress, "Progress % ", "Goals Page");
				strProgressVal = weProgress.getText();
				color = weProgress.getCssValue("color");
				String[] strProgressValArr = strProgressVal.split(":");
				strProgressPerct = strProgressValArr[1].trim();
				System.out.println("strProgressPerct" + strProgressPerct);
				System.out.println("progressPerct" + progressPerct);
				if (strProgressPerct.trim().equals(progressPerct.trim() + "%")) {
					passed("Goal Progress in Goals page for " + goalName + " Goal is " + progressPerct + "%");
					break;
				} else {
					blnFnd = false;
				}
			}
			if (blnFnd == false) {
				failed(driver, "Goal Progress in Goals page for " + goalName + " Goal is not " + progressPerct + "%"
						+ ", Actual value is " + strProgressPerct);
			}
			if (strProgressPerct.contains("-")) {
				if (color.equals("rgba(255, 0, 0, 1)")) {
					passed("Goal Progress % is in Red color when Progress Percentage value is Negative " + goalName
							+ " Goal");
				} else {
					failed(driver, "Goal Progress % is not in Red color when Progress Percentage value is Negative "
							+ goalName + " Goal and current color is " + color);
				}
			} else {
				if (color.equals("rgba(26, 199, 26, 1)")) {
					passed("Goal Progress % is in Green color when Progress Percentage value is Positive " + goalName
							+ " Goal");
				} else {
					failed(driver, "Goal Progress % is not in Green color when Progress Percentage value is Positive  "
							+ goalName + " Goal and current color is " + color);
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception occured at getGoalsCount method" + e.getMessage());
		}
	}

	public CatalogPage returnCatalogPage() {
		return new CatalogPage(driver, data);
	}

	public void selectPeriodFromGoalsDetailsPage(String periodName) {
		try {
			Thread.sleep(3000);
			clickOn(drpPeriod, "Period Dropdown");
			WebElement wePeriodName = driver
					.findElement(By.xpath("//ul[@role='listbox']/li[text()='" + periodName + "']"));
			clickOn(wePeriodName, periodName);
			passed("Selected " + periodName + " period");
		} catch (Exception e) {
			failed(driver, "Exception Caught As" + e.getMessage());
		}
	}

	@FindBy(xpath = "//*[@aria-label='Search']")
	private WebElement btnSrchIcon;
	@FindBy(xpath = "//input[@placeholder='Search Goal']")
	private WebElement txtSearchGoal;

	public boolean searchGoalsInGoalsPage(String goalName) {
		boolean blnSrched = false;
		clickOn(btnSrchIcon, "Search Icon");
		enterText(txtSearchGoal, "Search Goal", goalName);
		List<WebElement> lstRows = driver
				.findElements(By.xpath("//div[@role='rowgroup']//div[@aria-label='Press SPACE to select this row.']"));
		if (lstRows.size() > 0) {
			WebElement weGoalRow = driver.findElement(
					By.xpath("//div[@role='rowgroup']//div[@col-id='goalName']//span[text()='" + goalName + "']"));
			blnSrched = verifyElementAndHighlightIfExists(weGoalRow, goalName, "Goals Search");
			passed("Searched Goal => " + goalName);
		} else {
			failed(driver, "No Results found for Goal => " + goalName);
		}
		return blnSrched;
	}

	public boolean searchNonExistGoalsInGoalsPage(String goalName) {
		boolean blnSrched = false;
		try {
			clickOn(btnSrchIcon, "Search Icon");
			enterText(txtSearchGoal, "Search Goal", goalName);
			List<WebElement> lstRows = driver.findElements(
					By.xpath("//div[@role='rowgroup']//div[@aria-label='Press SPACE to select this row.']"));
			if (lstRows.size() != 0) {
				WebElement weGoalRow = driver.findElement(
						By.xpath("//div[@role='rowgroup']//div[@col-id='goalName']//span[text()='" + goalName + "']"));
				verifyElementAndHighlightIfExists(weGoalRow, goalName, "Goals Search");
				failed(driver, "Searched Goal => " + goalName);
			} else {
				passed("Searched Goal => " + goalName);
			}
		} catch (Exception e) {
			blnSrched = true;
		}
		return blnSrched;
	}

	public void verifyGoalsDetails() {
		String cellVal = "";
		String[] colId = { "baseUnixDate", "baseDataValue", "targetUnixDate", "targetDataValue", "progress",
				"topicName", "metricName" };
		for (int i = 0; i < colId.length; i++) {
			cellVal = driver.findElement(By.xpath(
					"//div[@role='rowgroup']//div[@col-id='goalName']//span[text()='Abcd']/ancestor::div[@col-id='goalName']/following-sibling::div[@col-id='"
							+ colId[i] + "']"))
					.getText();
		}
//		
//		
//		
//		
//		String[] weHeaders = {"Goal Name","Base Date","Base Value","Target Date","Target Value","Goal Progress","Topic","Metric","Data","Audit"};
//WebElement weHeader;
//		//Headers
//		for(int i =0;i<weHeaders.length;i++) {
//			weHeader = driver.findElement(By.xpath("//div[@role='columnheader']//span[text()='"+weHeaders[i]+"']"));
//			//			if (isElementPresent(weHeader)) {
//			//				((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", weHeader);
//			//				passed(weHeader + "Element is displayed in Goals page");
//			//			} else {
//			//				failed(driver, weHeader + "Element is not displayed in Goals Archived page");
//			//			}
//			verifyElementAndHighlightIfExists(weHeader,weHeaders[i].toString(),"Goals");
//		}
	}

	public boolean verifyGoalInArchivedPage(String goalName) {
		boolean blnFnd = false;
		try {
			clickOn(btnGoalsArchived, "Goals Archived button");
			sleep(3000);
			WebElement weGoal = driver.findElement(By.xpath("//div[text()='" + goalName + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weGoal);
			blnFnd = verifyIfElementPresent(weGoal, "Topic ==> " + goalName, "Goal In Archived");
		} catch (Exception e) {
			blnFnd = false;
		}
		return blnFnd;
	}

	public void verifyDeleteGoals(String goalName) {
		boolean blnSrched = true;
		try {
			WebElement weGoalRow = driver.findElement(
					By.xpath("//div[@role='rowgroup']//div[@col-id='goalName']//span[text()='" + goalName + "']"));
			verifyElementAndHighlightIfExists(weGoalRow, goalName, "Goals");
			passed("Searched Goal => " + goalName);
			clickOn(weGoalRow, "Goal " + goalName);
			WebElement goalsPopupUpClose = driver.findElement(
					By.xpath("//div[contains(@class,'paperAnchorRight')]//*[local-name()='svg' and @fill='none']"));
			if (isElementPresent(goalsPopupUpClose)) {
				clickOn(goalsPopupUpClose, "goalsPopupUpClose");
			}
			clickOn(btnEdit, "Edit button");
			WebElement goalsCheckBox = driver
					.findElement(By.xpath("//div[@role='rowgroup']//div[@col-id='goalName']//span[text()='" + goalName
							+ "']/preceding-sibling::div//input[@type='checkbox']"));
			clickOn(goalsCheckBox, "Goal Checkbox");
			clickOn(btnDelete, "Delete button");
			clickOn(btnDeleteGoalConfirm, "Delete Yes Confirm");
			driver.navigate().refresh();
			selectPeriodFromGoalsDetailsPage(data.get("Period"));
			searchNonExistGoalsInGoalsPage(GlobalVariables.goalName);
			if (verifyGoalInArchivedPage(GlobalVariables.goalName)) {
				passed("Deleted Goal exisit in Archived section");
			} else {
				failed(driver, "Deleted Goal not exisit in Archived section");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//span[text()='Audit Log']")
	private WebElement lblAuditHeader;
	@FindBy(xpath = "//*[@data-testid='CloseIcon']")
	private WebElement btnCloseAuditPopUp;

	public void verifyAuditLogs(String goalName) {
		try {
			searchGoalsInGoalsPage(goalName);
			WebElement weAduit = driver.findElement(By.xpath("//span[text()='" + goalName
					+ "']/parent::div/parent::div/following-sibling::div[@col-id='Audit']/div//*[local-name()='svg']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", weAduit);
			verifyElementAndHighlightIfExists(weAduit, "Audit", "Goals Details");
			clickOn(weAduit, "Audit for Goal => " + goalName);
			verifyElementAndHighlightIfExists(lblAuditHeader, "Audit Header", "Goals Audit");
			clickOn(btnCloseAuditPopUp, "Close Audit ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
