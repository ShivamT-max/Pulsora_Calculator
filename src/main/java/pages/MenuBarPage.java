package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.TestBase;
import utilities.Data;

public class MenuBarPage extends TestBase {
	protected MenuBarPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	@FindBy(xpath = "//*[contains(@class,'SideBar_logoContentainer')]")
	private WebElement weHamburgerMenu;                                 //*[@stroke-linecap='round']//parent::*[contains(@class,'SideBar')]
	@FindBy(xpath = "//div[contains(@class,'MuiPaper-elevation')]/ul//div[contains(text(),'Goals')]")
	private WebElement weGoalsMenu;
	@FindBy(xpath = "//div[contains(@class,'MuiPaper-elevation')]/ul//div[contains(text(),'Admin')]")
	private WebElement weAdminMenu;
	@FindBy(xpath = "//div[contains(@class,'MuiPaper-elevation')]/ul//div[contains(text(),'Catalogs')]")
	private WebElement weCatalogsMenu;
	@FindBy(xpath = "//div[contains(@class,'MuiPaper-elevation')]/ul//div[contains(text(),'Requests')]")
	private WebElement weDataRequestMenu;
	@FindBy(xpath = "//div[contains(@class,'MuiPaper-elevation')]/ul//div[contains(text(),'Data Destinations')]")
	private WebElement weDataDestinationsMenu;
	@FindBy(xpath = "//div[contains(@class,'MuiPaper-elevation')]/ul//div[contains(text(),'Data Sources')]")
	private WebElement weDataSourcesMenu;
	@FindBy(xpath = "//div[contains(@class,'MuiPaper-elevation')]/ul//div[contains(text(),'Analytics')]")
	private WebElement weAnalyticsMenu;
	@FindBy(xpath = "//div[contains(@class,'MuiPaper-elevation')]/ul//div[contains(text(),'Carbon Management')]")
	private WebElement weGHGCalculatorsMenu;
	@FindBy(xpath = "//div[contains(@class,'MuiPaper-elevation')]/ul//div[contains(text(),'Tasks')]")
	private WebElement weTasksMenu;
	@FindBy(xpath = "//button[@aria-label='Profile']")
	private WebElement btnProfile;
	@FindBy(xpath = "//div[contains(text(),'Logout')]")
	private WebElement btnLogOut;
	@FindBy(xpath = "//ul[@role='tree']/parent::div/preceding-sibling::div//*[local-name()='svg']")
	private WebElement btnCarbonMenu;

	public GoalsPage clickOnGoalsMenu() {
		waitForElement(weGoalsMenu);
		clickOn(weGoalsMenu, "Goals Menu");
		return new GoalsPage(driver, data);
	}

	public CatalogPage clickOnCatalogsMenu() {
		waitForElement(weCatalogsMenu);
		clickOn(weCatalogsMenu, "Cataloge Menu");
		return new CatalogPage(driver, data);
	}

	public AdminPage clickOnAdminMenu() {
		waitForElement(weAdminMenu);
		clickOn(weAdminMenu, "Admin Menu");
		return new AdminPage(driver, data);
	}

	public void clickOnHamburgerMenu() {
		clickOn(weHamburgerMenu, "HambugerMenu");
	}
	public void clickOnCarbonMenuIconForDirectEmission() {
		try {
			sleep(500);
			jsClick(btnCarbonMenu, "CarbonMenu");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DataSourcePage clickOnDataSourcesMenu() {
		waitForElement(weDataSourcesMenu);
		clickOn(weDataSourcesMenu, "Data Request Menu");
		return new DataSourcePage(driver, data);
	}

	public DataRequestPage clickOnDataRequestMenu() {
		waitForElement(weDataRequestMenu);
		clickOn(weDataRequestMenu, "Data Request Menu");
		return new DataRequestPage(driver, data);
	}
	
	public DataDestinationPage clickOnDataDestinationMenu() {
		waitForElement(weDataDestinationsMenu);
		clickOn(weDataDestinationsMenu, "Data destinations Menu");
		return new DataDestinationPage(driver, data);
	}

	public AnalyticsPage clickOnAnalyticsMenu() {
		waitForElement(weAnalyticsMenu);
		clickOn(weAnalyticsMenu, "Analytics Menu");
		return new AnalyticsPage(driver, data);
	}

	@FindBy(xpath = "//li[@aria-label='GHG Calculators']")
	private WebElement ghgcalc;
	public GHGCalculatorsPage clickOnGHGCalculatorsMenu() {
		waitForElement(weGHGCalculatorsMenu);
		clickOn(weGHGCalculatorsMenu, "GHG calculators Menu");
		return new GHGCalculatorsPage(driver, data);
	}

	public void clickOnGHGCalcFromTranscationPage() {
		waitForElement(weGHGCalculatorsMenu);
		clickOn(ghgcalc, "GHG Calc");
	}
	
//	public IndirectEmissionsCalculatorPage clickOnEmissionFactors() {
//		sleep(4000);
//		WebElement clickOnEmissionFactors = driver.findElement(By.xpath("(//ul[@role='tree']//li/div)[4]"));
//		clickOn(clickOnEmissionFactors, "clicked");
//		sleep(2000);		
//		return new IndirectEmissionsCalculatorPage(driver, data);
//	}
	
	public CalculatorPage clickOnCalculatorsMenu() {
		waitForElement(weGHGCalculatorsMenu);
		clickOn(weGHGCalculatorsMenu, "GHG calculators Menu");
		return new CalculatorPage(driver, data);
	}

	public TasksPage clickOnTasksMenu() {
		waitForElement(weTasksMenu);
		clickOn(weTasksMenu, " Tasks Menu");
		return new TasksPage(driver, data);
	}

	public SignInPage logOut() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnProfile);
		jsClick(btnProfile, "Profile Button");
		takeScreenshot(driver);
		clickOn(btnLogOut, "LogOut Button");
		return new SignInPage(driver, data);
	}

	@Override
	protected void VerifyNavigationToValidPage() {
		try {
//			waitForElement(weHamburgerMenu);
//			if (isElementPresent(weHamburgerMenu)) {
//				passed("User Successfully Navigated To MenuBarPage");
//			} else {
//				failed(driver, "User Failed To navigate To MenuBarPage ");
//			}
//			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
}
