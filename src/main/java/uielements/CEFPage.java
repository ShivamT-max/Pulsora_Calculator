package uielements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import base.TestBase;
import pages.GHGCalculatorsPage;
import utilities.Constants;
import utilities.Data;

public class CEFPage extends TestBase{

	public CEFPage(WebDriver driver2, Data data) {
		super(driver2, data);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//li[@aria-label='GHG Calculators']")
	private WebElement btnGHGCalc;
	
	public GHGCalculatorsPage clickonGHG() {
		waitForElement(btnGHGCalc);
		clickElement(btnGHGCalc, "GHG Calculator");
		return new GHGCalculatorsPage(driver,data);
	}
	
	@FindBy(xpath = "//div[contains(text(),'Fuels')]//parent::div//*[@fill='none']")
	private WebElement customchevronicon;
	
	public void addCustomEmmisonFactor() {
		try {
			Actions sda = new Actions(driver);
			sda.moveToElement(customchevronicon).click().perform();
			// clickOn(customchevronicon, "click on energy dropdown");
			WebElement energytype = driver
					.findElement(By.xpath("//ul[@role='listbox']//li[text()='" + data.get("cusemissiontype") + "']"));
			clickOn(energytype, data.get("cusemissiontype"));
			sleep(2000);
		} catch (Exception e) {
			failed(driver,"Message Caught "+e.getMessage());
		}
	}
	
	@FindBy(xpath = "//article[@aria-label='Add Custom Emission Factors']")
	public WebElement btnAddCEF;
	@FindBy(xpath = "//*[text()='Add Custom Emission Factors']")
	public WebElement lblAddCEF;
	@FindBy(xpath = "//button[contains(text(),'Purchased Energy')]")
	public WebElement btnPurchasedEnergy;
	@FindBy(xpath = "//input[@id='NameOfCustomEmission']")
	public WebElement txtNameOfCustomEF;
	@FindBy(xpath = "//span[contains(text(),'Energy Category')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpEnergyType;
	@FindBy(xpath = "//input[@id='SourceOfEmission']")
	public WebElement txtSourceCEF;
	@FindBy(xpath = "//span[contains(text(),'Unit of Custom EF (Denominator)')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpUnitCEFDenominator;
	@FindBy(xpath = "//input[@id='CO2']")
	public WebElement txtCO2;
	@FindBy(xpath = "//input[@id='CH4']")
	public WebElement txtCH4;
	@FindBy(xpath = "//input[@id='N2O']")
	public WebElement txtN2O;
	@FindBy(xpath = "//input[@id='BiofuelCO2']")
	public WebElement txtBiofuelCO2;
	@FindBy(xpath = "//span[contains(text(),'Unit of Custom EF (Numerator)')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpUnitCEFNumerator;
	@FindBy(xpath = "//button[text()='Save']")
	public WebElement btnSaveParemeterInput;
	
	String sourceOfEmissionFactor;
	String nameOfCEF;
	String rndNotes;
	
	public void addCEFForIndirectEmission() {
		try {			
			clickOn(btnAddCEF, "Custom Emission Factor");
			verifyIfElementPresent(lblAddCEF, "CEF", "CEF");
			nameOfCEF = Constants.customEmissionFactor + generateRandomNumber(3);
			enterText(txtNameOfCustomEF, "name of custom emission factor", nameOfCEF);
			clickOn(drpEnergyType, "energy category");
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Energy Category") + "']"));
			clickOn(we, "energy category" + data.get("Energy Category"));
			sourceOfEmissionFactor = "SRCEF" + generateRandomNumber(3);
			enterText(txtSourceCEF, "Source of Emission Factor", sourceOfEmissionFactor);
			clickOn(drpUnitCEFDenominator, "Unit of Custom EF (Denominator)");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Denominator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Denominator)"));
			enterText(txtCO2, "CO2", data.get("CO2"));
			enterText(txtCH4, "CH4", data.get("CH4"));
			enterText(txtN2O, "N2O", data.get("N2O"));
			clickOn(drpUnitCEFNumerator, "Unit of Custom EF (Numerator)");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Numerator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Numerator)"));
			WebElement inputNotes = driver.findElement(By.xpath("//*[@id='notes']"));
			rndNotes = generateRandomString(4) + generateRandomNumber(3);
			enterText(inputNotes, "Notes", rndNotes);
			clickOn(btnSaveParemeterInput, "Save Custom Emission factor");
			sleep(2000);
			CO2eFactor();
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	Double valueCo2e;
	String co2efactorCalculatedValue;
	String co2eFactor;

	public void CO2eFactor() {
		valueCo2e = Double.parseDouble(data.get("CO2")) + Double.parseDouble(data.get("CH4")) * 25
				+ Double.parseDouble(data.get("N2O")) * 298;
		System.out.println("calculated value" + valueCo2e);
		co2efactorCalculatedValue = valueCo2e.toString();
		sleep(1000);
		co2eFactor = co2efactorCalculatedValue + " " + data.get("Unit of Custom EF(Num)") + " " + "CO2e" + "/"
				+ data.get("Unit of Custom EF(Den)");
	}
	
	public void verifyCO2eFactor() {
		WebElement cefValueRHP = driver
				.findElement(By.xpath("//span[contains(text(),'CO2e Factor')]//parent::div//following-sibling::div"));
		String newPlainValue = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(cefValueRHP.getText());
		if (newPlainValue.equals(co2efactorCalculatedValue)) {
			passed("Succesfully validated tCO2e value" + newPlainValue + "--" + co2efactorCalculatedValue);
		} else {
			fail("Failed validated tCO2e value" + " " + newPlainValue + "--" + co2efactorCalculatedValue);
		}
		WebElement weNameOfCEF = driver.findElement(
				By.xpath("//span[contains(text(),'Name of Custom EF')]//parent::div//following-sibling::div"));
		WebElement weSourceOfCEF = driver.findElement(
				By.xpath("//span[contains(text(),'Source of Emission Factor')]//parent::div//following-sibling::div"));
		if (weNameOfCEF.getText().equals(nameOfCEF)) {
			passed("successfully validated name of custom emission factor");
		} else {
			fail("failed to validate name of custom emission factor");
		}
		if (weSourceOfCEF.getText().equals(sourceOfEmissionFactor)) {
			passed("successfully validated source of custom emission factor");
		} else {
			fail("failed to validate source of custom emission factor");
		}
	}

	@FindBy(xpath = "//*[text()='View Custom Emission Factors']")
	public WebElement lblCEFDetails;
	public void ValidateCEFDetailsInViewCEF() {
		try {
			String[] activityDetailFieldNames = { "Energy Category", "Unit of Custom EF (Denominator)",
					"Unit of Custom EF (Numerator)", "CO2", "CH4", "N2O" };
			Thread.sleep(2000);
			verifyIfElementPresent(lblCEFDetails, "lblCEF", "Indirect emission");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				WebElement weActivityField = driver.findElement(By.xpath(
						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
				if (weActivityField.getText().trim().contains(data.get(activityDetailFieldNames[j]).trim())) {
					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
							+ weActivityField.getText());
				} else {
					failed(driver,
							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
									+ data.get(activityDetailFieldNames[j]) + "But Actual is"
									+ weActivityField.getText());
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}
	
	@FindBy(xpath = "//li[@aria-label='Facilities']")
	private WebElement weFacility;
	
	public Facility clickOnFacilities() {
		try {
			waitForElement(weFacility);
			clickOn(weFacility, "Clicked on Facility");
			return new Facility(driver, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	
	@FindBy(xpath = "//p[text()='Custom Emission Factors']")
	private WebElement weCEFLabel;
	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(weCEFLabel);
			if (isElementPresent(weCEFLabel)) {
				passed("User Successfully Navigated To CEF Page");
			} else {
				failed(driver, "Failed to Navigate To CEF Page");
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	
		
		
	}

}
