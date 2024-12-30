package pages.calculators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.GHGCalculatorsPage;
import utilities.Data;

public class PurchasedGoodsAndServicesGHGCalculatorPage extends GHGCalculatorsPage {
	public PurchasedGoodsAndServicesGHGCalculatorPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	public void EditActivityScope3EmissionsScope3_1_AverageBased() {
		try {
			ReusabilityForFacilityNameAndStartDateAndEndDateForCalculators();
			if(!Boolean.parseBoolean(data.get("Activity CEF"))) {
				SelectDropdownOptionsForCalculatorActivityFields("Purchased Goods Category");
				SelectDropdownOptionsForCalculatorActivityFields("Purchased Good");
				SelectDropdownOptionsForCalculatorActivityFields("Production Process Involved");
				sleep(1000);
			}
			ReusabilityForAmount$Units$Tags$Notes$SaveForCalculators("Quantity of Goods Purchased", "Units", "Tags", "Notes");
		} catch (Exception e) {
			clickOnCancelButtonBeforeAdding();
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void Add_CustomEmissionFactor_Purchased_Godds_Services_Average_Data_Scope3_1(String subCalc) {
		try {
			SelectDropdownOptionsForCalculatorActivityFields("Calculation Method");
			if(subCalc.equals("Average Data Method")){
				SelectDropdownOptionsForCalculatorActivityFields("Purchased Goods Category");
				SelectDropdownOptionsForCalculatorActivityFields("Purchased Good");
				SelectDropdownOptionsForCalculatorActivityFields("Production Process Involved");
			}else if(subCalc.equals("Spend Based Method")){
			    entertTextInTextFieldForCalculators_ActivityDetails("Purchased Goods Category");
			    String s = "(//span[normalize-space()='Purchased Good']/ancestor::div//input[contains(@placeholder,'Purchased Good')])[2]";
			    WebElement wePurchasedGood = driver.findElement(By.xpath(s));
			    clearUntillTextFieldIsGettingCleared(wePurchasedGood);
			    enterText(wePurchasedGood, subCalc, data.get("Purchased Good"));
				SelectDropdownOptionsForCalculatorActivityFields("Unit/Currency");
			}
			commonFieldsForAddingCUSTOMEMISSIONFACTOR_ParameterInput("Source of Emission Factor","true");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	public void clickOnScope3_1PurchasedGoods_Ser_SpendBased() {
		clickOn(btnScope3_1SpendBased, "Spend Based Scope3_1 Button");
	}

	public void EdittActivityScope3_1SpendBasedEmissions() {
		try {
			ReusabilityForFacilityNameAndStartDateAndEndDateForCalculators();
			if(!Boolean.parseBoolean(data.get("Activity CEF"))) {
				SelectDropdownOptionsForCalculatorActivityFields("Purchased Goods or Services");
			}
			ReusabilityForAmount$Units$Tags$Notes$SaveForCalculators("Amount Spent","Unit/Currency","Tags","Notes");
		} catch (Exception e) {
			clickOnCancelButtonBeforeAdding();
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@Override
	protected void VerifyNavigationToValidPage() {
		try {
//			waitForElement(lblGHGCalculator);
//			if (isElementPresent(lblGHGCalculator)) {
//				passed("User Successfully Navigated To GHG_Calculator Page");
//			} else {
//				failed(driver, "Failed To Navigate To GHG_Calculator Page");
//			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
}