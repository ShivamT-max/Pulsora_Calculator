package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.TestBase;
import utilities.Data;

public class CalculatorPage extends TestBase {
	protected CalculatorPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	@FindBy(xpath = "//article//*[contains(text(),'GHG Calculators')]")
	private WebElement lblGHGCalculators;
	@FindBy(xpath = "//div[text()='Indirect Emissions']")
	private WebElement btnGHGScope2;
	@FindBy(xpath = "//span[text()='Activities']/parent::div//article")
	private WebElement btnAddNewTranscation;
	@FindBy(xpath = "//input[@id='InvoiceNo']")
	private WebElement txtInputVoice;
	@FindBy(xpath = "(//input[@placeholder='yyyy/mm/dd'])[1]")
	private WebElement txtInvoiceDate;
	@FindBy(xpath = "(//input[@placeholder='yyyy/mm/dd'])[2]")
	private WebElement txtStartDate;
	@FindBy(xpath = "(//input[@placeholder='yyyy/mm/dd'])[3]")
	private WebElement txtEndDate;
	@FindBy(xpath = "//input[@id='amount_of_energy']")
	private WebElement txtAmountOfEnergy;
	@FindBy(xpath = "//button[text()='Generate']")
	private WebElement btnGenerate;
	@FindBy(xpath = "(//button[text()='Save'])[2]")
	private WebElement btnSaveTranscation;
	@FindBy(xpath = "//button[@aria-label='Open']")
	private WebElement drpFacilityName;
	@FindBy(xpath = "//span[contains(text(),'Location Based Emissions')]")
	private WebElement weLocationBasedEmissions;
	@FindBy(xpath = "//span[contains(text(),'Market Based Emissions')]")
	private WebElement weMarketBasedLocation;
	@FindBy(xpath = "//*[@data-testid='CloseOutlinedIcon']")
	private WebElement btnClose;
	@FindBy(xpath = "(//span[text()='Units']/parent::div//parent::div//div)[4]")
	private WebElement txtUnit;
	@FindBy(xpath = "//li[text()='kilowatt-hour (kWh)']")
	private WebElement weKiloWattPerHour;
	@FindBy(xpath = "(//span[text()='Energy Category']//parent::div//parent::div//following-sibling::div)[2]")
	private WebElement drpEnergyCategory;
	@FindBy(xpath = "//li[text()='Electricity']")
	private WebElement weElectricity;
	@FindBy(xpath = "//li[text()='Heat, Steam and Cooling']")
	private WebElement weHeatSteamCooling;
	@FindBy(xpath = "//span[text()='Period']//parent::div//following-sibling::div//span")
	private WebElement drpPeriod;
	// Facilities
	@FindBy(xpath = "//li[@aria-label='Facilities']/div")
	private WebElement btnFacilities;
	
	public FacilitiesPage clickOnFacilitiesInCarbonMangementPage() {
		try {
			waitForElement(btnFacilities);
			clickOn(btnFacilities, "Facilities");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new FacilitiesPage(driver, data);
	}

	public void selectFacility() throws AWTException {
		clickOn(drpFacilityName, "facility name");
		sleep(5000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		// This will press the down key on your numpad.
		robot.keyRelease(KeyEvent.VK_DOWN);
		// This will release the down key on your numpad.
		robot.keyPress(KeyEvent.VK_ENTER);
		// This will press the down key on your numpad.
		robot.keyRelease(KeyEvent.VK_ENTER);
		// This will release the down key on your numpad.
	}

	public void selectEnergyCategory(String energyType) {
		clickOn(drpEnergyCategory, "EnergyCategory");
		String xpath = "//li[text()='" + energyType + "']";
		WebElement energy = driver.findElement(By.xpath(xpath));
		clickOn(energy, energyType);
	}

	public void selectUnit(String unit) {
		clickOn(txtUnit, "unit");
		String xpath = "//li[text()='" + unit + "']";
		WebElement unitOfEnergy = driver.findElement(By.xpath(xpath));
		clickOn(unitOfEnergy, "unit");
	}

	public void selectPeriod(String period) {
		clickOn(drpPeriod, "period");
		String xpath = "//li[text()='" + period + "']";
		WebElement Period = driver.findElement(By.xpath(xpath));
		clickOn(Period, "period");
		// li[text()='FY 2022']
	}

	public void addNewTranscation_FilterAndGenerate() {
		try {
			clickOn(btnGHGScope2, "indirect emissions");
			clickOn(btnAddNewTranscation, "add new transaction");
			selectFacility();
			selectEnergyCategory("Electricity");
			enterText(txtInputVoice, "invoice number", data.get("InvoiceNumber"));
			clickOn(txtInvoiceDate, "invoice date");
			enterText(txtInvoiceDate, "Invoice Date", data.get("InvoiceDate"));
			enterText(txtStartDate, "Start Date", data.get("startDate"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("endDate"));
			enterText(txtAmountOfEnergy, "amount of energy", data.get("AmountOfEletricity"));
			selectUnit("kilowatt-hour (kWh)");
			clickOn(btnSaveTranscation, "save");
			clickOn(btnClose, "close");
			selectPeriod("FY 2022");
			clickOn(btnGenerate, "generate");
			sleep(3000);
			passed(weLocationBasedEmissions.getText());
			passed(weMarketBasedLocation.getText());
		} catch (Exception e) {
			failed(driver, "Exception caught in method addNewTranscation " + e.getMessage());
		}
	}

	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(lblGHGCalculators);
			if (isElementPresent(lblGHGCalculators)) {
				passed("User Successfully Navigated To GHGCalculatorsPage Page");
			} else {
				failed(driver, "Failed To Navigate To GHGCalculatorsPage Page");
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
}