package uielements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.TestBase;
import utilities.Data;
import utilities.GlobalVariables;

public class Facility extends TestBase{

	protected Facility(WebDriver driver2, Data data) {
		super(driver2, data);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//div[contains(@class,'MuiDrawer-pape')]/div//*[local-name() = 'svg']")
	private WebElement hambmenu;
	@FindBy(xpath = "//input[@type='search']")
	private WebElement searchtxt;
	@FindBy(xpath = "//*[@data-testid='SearchOutlinedIcon']")
	private WebElement searchfacility;
	@FindBy(xpath = "//div[contains(text(),'Scope 2 | Indirect Emissions')]")
	private WebElement weScope2emission;
	@FindBy(xpath = "(//div/span[contains(text(),'Advanced Setup')])[3]")
	private WebElement BtnadvancedSetup;
	@FindBy(xpath = "(//input[@id='energy_Data']//parent::div//button)[2]")
	private WebElement facilityEnergyCategorydrpdwn;
	@FindBy(xpath = "(//input[@id='location_emission']//parent::div//button)[2]")
	private WebElement EmissionFactorType;
	@FindBy(xpath = "(//input[@placeholder='Select Emission Factor']//parent::div//button)[2]")
	private WebElement emissionfactor;
	@FindBy(xpath = "(//input[@id='market_emission']//parent::div//button)[2]")
	private WebElement marketbasedemf;
	@FindBy(xpath = "(//input[@id='market_emissionType']//parent::div//button)[2]")
	private WebElement mbemissionfactor;
	@FindBy(xpath = "//input[@id='units']//parent::div//button[@aria-label='Open']")
	private WebElement defaultUnit;
	@FindBy(xpath = "//button[contains(text(),'Add')]")
	private WebElement btnaddcef;
	@FindBy(xpath = "(//button[contains(text(),'Close')])[2]")
	private WebElement btnclose;
	
	public void clickOnFacilityinFacilityGridMultiple() {
		try {
			sleep(3000);
			WebElement facilitygrid = driver
					.findElement(By.xpath("//*[text()='" + data.get("Facility Name") + "']//parent::div"));
			clickOn(facilitygrid, "Activity of Facility");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement weScope2emission = driver
					.findElement(By.xpath("//div[contains(text(),'Scope 2 | Indirect Emissions')]"));
			js.executeScript("arguments[0].scrollIntoView();", weScope2emission);
			isElementPresent(weScope2emission);
			if (weScope2emission.getText().contains("Scope 2 | Indirect Emissions")) {
				clickOn(weScope2emission, "click on Scope 2 | Indirect Emissions");
			} else {
				failed(driver, "Failed to click on Scope2 Indirect Emissions");
			}
			clickOn(BtnadvancedSetup, "Clicked on Scope2 IndirectEmissions AdvancedSetUp");
			sleep(2000);
			clickOn(hambmenu,"click on hamburger menu");
			sleep(2000); 
			clickOn(facilityEnergyCategorydrpdwn, "Clicked on EnergyType in Facilities");
			WebElement Energytyp = driver
					.findElement(By.xpath("//li[text()='" + data.get("energyCategoryFacility") + "']"));
			clickOn(Energytyp, data.get("energyCategoryFacility"));
			clickOn(EmissionFactorType, "clicked on emission factor type");
			WebElement eftype = driver.findElement(By.xpath("//ul//li[text()='Custom']"));
			eftype.click();
			clickOn(emissionfactor, "clicked on emission factor");
			WebElement efname = driver.findElement(By.xpath("//ul//li[text()='" + GlobalVariables.nameOfCEF + "']"));
			clickOn(efname, "CustomEmissionFactorName");
			clickOn(marketbasedemf, " market based emission factor");
			WebElement mbemftype = driver.findElement(By.xpath("//ul//li[text()='Custom']"));
			clickOn(mbemftype, "marketbasedcustomemission");
			clickOn(mbemissionfactor, "clicked on market based emission factor");
			WebElement mbefname = driver.findElement(By.xpath("//ul//li[text()='" + GlobalVariables.nameOfCEF + "']"));
			clickOn(mbefname, "CustomEmissionFactorName");
			clickOn(defaultUnit, "select default unit");
			WebElement defunit = driver.findElement(By.xpath("//ul//li[contains(text(),'kilowatt-hour (kWh)')]"));
			clickOn(defunit, "defaultUnit ");
			clickElement(btnaddcef, "Indirect Emissions AdvancedSetup added ");
			WebElement toastmessage = driver
					.findElement(By.xpath("//*[contains(text(),'Advanced setup updated successfully')]"));
			if (isElementPresent(toastmessage)) {
				passed("Successfully updated advanced setup");
			} else {
				failed(driver, "failed to update advanced setup");
			}
			clickOn(btnclose, "closed");
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());	
		}
	}
	
	
	
	@FindBy(xpath = "(//div[text()='Facilities'])[1]")
	private WebElement lblFacility;
	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(lblFacility);
			if (isElementPresent(lblFacility)) {
				passed("User Successfully Navigated To facility Page");
			} else {
				failed(driver, "Failed to Navigated To facility Page");
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	
		
		
	}

}
