package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.TestBase;
import utilities.Data;

public class FacilitiesPage extends TestBase {
	protected FacilitiesPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	@FindBy(xpath = "//*[contains(text(),'Facilities')]")
	private WebElement lblFacilities;
	@FindBy(xpath = "//*[contains(text(),'Facilities')]//following::article[@aria-label='Add Facility']")
	private WebElement btnAddFacility;
	@FindBy(xpath = "(//div[contains(text(),'Add New Facility')]//parent::div)[1]")
	private WebElement lblAddFacility;
	@FindBy(xpath = "//button[text()='Confirm']")
	private WebElement btnConfirm;
	@FindBy(xpath = "//input[@id='facilityName']")
	private WebElement txtFacilityName;
	@FindBy(xpath = "//input[@id='Country']")
	private WebElement txtCountry;
	// *[contains(text(),'Emission Factor
	// Datasets')]//following::*[@data-testid='ExpandMoreIcon']/parent::div//preceding-sibling::div//div[@aria-haspopup='listbox']
	@FindBy(xpath = "//*[contains(text(),'Emission Factor Datasets')]//following::div[@aria-haspopup='listbox']//parent::div[1]")
	private WebElement drpYear;
	@FindBy(xpath = "// input[@id='Country']//following-sibling::div/button[@aria-label='Clear']/*[@data-testid='CloseIcon']")
	private WebElement btnClearCountry;
	@FindBy(xpath = "//button[contains(text(),'Cancel')]")
	private WebElement btnCancel;

	public void clickOnCreateFacility() {
		clickOn(btnAddFacility, "Create New Facility");
		verifyIfElementPresent(lblAddFacility, "lblAddFacility", "lblAddFacility");
		enterText(txtFacilityName, "FacilityName", "Sample Facility"); // --Optional
	}

	public void selectCountry() {
		try {
			waitForElement(txtCountry);
			clickOn(txtCountry, "CountryDropDown");
			txtCountry.sendKeys(data.get("CountryName"));
			sleep(2000);
			String lstCountryXpath = "//li[text()='" + data.get("CountryName") + "']";
			WaitForElementWithDynamicXpath(lstCountryXpath);
			clickOnElementWithDynamicXpath(lstCountryXpath, "CountryName");
			sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOnConfirmButton() {
		if (isElementPresent(btnConfirm)) {
			jsClick(btnConfirm, "confirm  button");
		}
	}

	public void clickOnCancelButton() {
		waitForElement(btnCancel);
		jsClick(btnCancel, "Cancel  button");
	}

	public void clearCountry() {
		scrollTo(txtCountry);
		if (!(txtCountry.getAttribute("value").isEmpty())) {
			txtCountry.click();
			txtCountry.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			txtCountry.sendKeys(Keys.BACK_SPACE);
		} else {
			System.out.println("//----------->do nothing");
		}
	}

	public void selectPeriod() {
		try {
			waitForElement(drpYear);
			scrollTo(drpYear);
			waitForElement(drpYear);
			clickOn(drpYear, "Year DropDown");
			String xpathYear = "//ul/li[text()='" + data.get("EFDataSetsYear") + "']";
			WaitForElementWithDynamicXpath(xpathYear);
			clickOnElementWithDynamicXpath(xpathYear, " EF DataSets Year");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateEmissionFactorDataSetsInFacilities() {
		try {
			String[] excelEFDataSetsColNames = { "S1_DirectEmissions_SC", "S1_DirectEmissions_MC",
					"S2_IndirectEmissions_ElectricityLB", "S2_IndirectEmissions_ElectricityMB",
					"S2_IndirectEmissions_HeatSteamandCooling", "S3.1_PurchasedGoods_AverageDataMethod",
					"S3.1_PurchasedGoods_SpendBasedMethod", "S3.2_CapitalGoods_AverageDataBased",
					"S3.2_CapitalGoods_SpendBased", "S3.3_FuelAndEnergy_Fuels",
					"S3.3_FuelAndEnergy_TransmissionandDistributionLosses", "S3.4_UTD_Weight.Distance Based",
					"S3.4_UTD_SpendBased", "S3.5_WasteGeneratedInOps", "S_3.6_BusinessTravel_DistanceBased",
					"S3.6_BusinessTravel_SpendBased", "S3.6_HotelStay", "S_3.7_EmployeeCommuting_DistanceBased",
					"S3.7_EmployeeCommuting_SpendBased", "S3.7_HomeOffice", "s3.8_ula_fuels_ds",
					"s3.8_ula_electricity_ds", "s3.8_ula_heat_ds", "S3.9_dtd_WeightDistanceBased",
					"S3.9_dtd_SpendBased", "S3.10_Processingofsoldproducts", "S3.11_USP_Fuels", "S3.11_USP_Electricity",
					"S3.12_EOL", "s3.13_dla_fuels_ds", "s3.13_dla_electricity_ds", "s3.13_dla_heat_ds",
					"s3.14_franchises_fuels_ds", "s3.14_franchises_electricity_ds", "s3.14_franchises_heat_ds" };
			String[] calcScopes = { "Scope 1 | Direct Emissions", "Scope 2 | Indirect Emissions",
					"Scope 3 | Category 1 | Purchased Goods and Services", "Scope 3 | Category 2 | Capital Goods",
					"Scope 3 | Category 3 | Fuel and Energy Related Activities",
					"Scope 3 | Category 4 | Upstream Transportation and Distribution",
					"Scope 3 | Category 5 | Waste Generated in Operations", "Scope 3 | Category 6 | Business Travel",
					"Scope 3 | Category 6 | Hotel Stay", "Scope 3 | Category 7 | Employee Commuting",
					"Scope 3 | Category 7 | Home Office/Telecommuting", "Scope 3 | Category 8 | Upstream Leased Assets",
					"Scope 3 | Category 9 | Downstream Transportation and Distribution",
					"Scope 3 | Category 10 | Processing of Sold Products",
					"Scope 3 | Category 11 | Use of Sold Products",
					"Scope 3 | Category 12 | End-of-Life Treatment of Sold Products",
					"Scope 3 | Category 13 | Downstream Leased Assets", "Scope 3 | Category 14 | Franchises" };
			List<String[]> lstArrayScopeCalcNames = new <String[]>ArrayList();
			String[] scope1CalcNames = { "Stationary Combustion", "Mobile Combustion" };
			String[] scope2CalcNames = { "Electricity - Location Based", "Electricity - Market Based",
					"Heat, Steam and Cooling" };
			String[] scope3_1CalcNames = { "Average Data Method", "Spend Based Method" };
			String[] scope3_2CalcNames = { "Average Data Method", "Spend Based Method" };
			String[] scope3_3CalcNames = { "Average Data Method - Fuels",
					"Average Data Method - Transmission and Distribution Losses" };
			String[] scope3_4CalcNames = { "Weight.Distance Based Method", "Spend Based Method" };
			String[] scope3_5CalcNames = { "Waste Generated in Operations" };
			String[] scope3_6BTCalcNames = { "Distance Based Method", "Spend Based Method" };
			String[] scope3_6HotelStayCalcNames = { "Activity Based Method" };
			String[] scope3_7EmployeeCommutingCalcNames = { "Distance Based Method", "Spend Based Method" };
			String[] scope3_7HomeOfficeCalcNames = { "Home Office/Telecommuting" };
			String[] scope3_8CalcNames = { "Asset Specific Method - Fuels", "Asset Specific Method - Electricity",
					"Asset Specific Method - Heat, Steam and Cooling" };
			String[] scope3_9CalcNames = { "Weight.Distance Based Method", "Spend Based Method" };
			String[] scope3_10CalcNames = { "Average Data Method" };
			String[] scope3_11CalcNames = { "Average Data Method - Fuels", "Average Data Method - Electricity" };
			String[] scope3_12CalcNames = { "Waste-type Specific Method" };
			String[] scope3_13CalcNames = { "Asset Specific Method - Fuels", "Asset Specific Method - Electricity",
					"Asset Specific Method - Heat, Steam and Cooling" };
			String[] scope3_14CalcNames = { "Franchisee Specific Method - Fuels",
					"Franchisee Specific Method - Electricity",
					"Franchisee Specific Method - Heat, Steam and Cooling" };
			lstArrayScopeCalcNames.add(scope1CalcNames);
			lstArrayScopeCalcNames.add(scope2CalcNames);
			lstArrayScopeCalcNames.add(scope3_1CalcNames);
			lstArrayScopeCalcNames.add(scope3_2CalcNames);
			lstArrayScopeCalcNames.add(scope3_3CalcNames);
			lstArrayScopeCalcNames.add(scope3_4CalcNames);
			lstArrayScopeCalcNames.add(scope3_5CalcNames);
			lstArrayScopeCalcNames.add(scope3_6BTCalcNames);
			lstArrayScopeCalcNames.add(scope3_6HotelStayCalcNames);
			lstArrayScopeCalcNames.add(scope3_7EmployeeCommutingCalcNames);
			lstArrayScopeCalcNames.add(scope3_7HomeOfficeCalcNames);
			lstArrayScopeCalcNames.add(scope3_8CalcNames);
			lstArrayScopeCalcNames.add(scope3_9CalcNames);
			lstArrayScopeCalcNames.add(scope3_10CalcNames);
			lstArrayScopeCalcNames.add(scope3_11CalcNames);
			lstArrayScopeCalcNames.add(scope3_12CalcNames);
			lstArrayScopeCalcNames.add(scope3_13CalcNames);
			lstArrayScopeCalcNames.add(scope3_14CalcNames);
			List<String> uiDataSetsNames = new ArrayList();
			for (int i = 0; i < lstArrayScopeCalcNames.size(); i++) {
				String[] arrScopes = lstArrayScopeCalcNames.get(i);
				System.out.println(calcScopes[i]);
				for (int k = 0; k < arrScopes.length; k++) {
					String xpathCalDataSetName = "//*[text()='" + calcScopes[i]
							+ "']//following-sibling::div//span[@class!='notranslate']//self::span[text()='"
							+ arrScopes[k] + "']//following::input[1]";
					WebElement weCalcDataSetName = driver.findElement(By.xpath(xpathCalDataSetName));
					String calcDataSetName = weCalcDataSetName.getAttribute("value");
					uiDataSetsNames.add(calcDataSetName);
					// System.out.println(calcDataSetName);
				}
			}
			for (int k = 0; k < excelEFDataSetsColNames.length; k++) {
				if (data.get(excelEFDataSetsColNames[k]).trim().replaceAll("\\s", "")
						.equals(uiDataSetsNames.get(k).replaceAll("\\s", ""))) {
					passed("Successfully Validated Data Set name as" + uiDataSetsNames.get(k) + "For "
							+ excelEFDataSetsColNames[k] + "Calculator");
					// System.out.println("First" +
					// data.get(excelEFDataSetsColNames[k]).trim().replaceAll("\\s", ""));
					// System.out.println("Second" + uiDataSetsNames.get(k).replaceAll("\\s", ""));
				} else {
					failed(driver,
							"failed To validate Data set name Expected as " + data.get(excelEFDataSetsColNames[k])
									+ "But Actual is" + uiDataSetsNames.get(k) + "For" + excelEFDataSetsColNames[k]
									+ "Calculator");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(lblFacilities);
			if (isElementPresent(lblFacilities)) {
				passed("User Successfully Navigated To Facilities Page");
			} else {
				failed(driver, "Failed To Navigate To Facilities Page");
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
}
