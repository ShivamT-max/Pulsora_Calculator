package pages.calculators;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import pages.GHGCalculatorsPage;
import uielements.CalculatorElements;
import utilities.Constants;
import utilities.Data;

public class CapitalGoodsGHGCalculatorPage extends GHGCalculatorsPage {
	public CapitalGoodsGHGCalculatorPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	public void EditActivityScopeCapitalGoods3_2EmissionsAverageBased() {
		try {
			ReusabilityForFacilityNameAndStartDateAndEndDateForCalculators();
			if(!Boolean.parseBoolean(data.get("Activity CEF"))) {
				SelectDropdownOptionsForCalculatorActivityFields("Capital Goods Category");
				SelectDropdownOptionsForCalculatorActivityFields("Capital Good");
				SelectDropdownOptionsForCalculatorActivityFields("Production Process Involved");
			}
		    ReusabilityForAmount$Units$Tags$Notes$SaveForCalculators("Quantity of Goods Purchased", "Units", "False", "Description");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	public void Add_CustomEmissionFactor_Capital_Goods_Aversge_Spend_Data_Scope3_1() {
		try {
			SelectDropdownOptionsForCalculatorActivityFields("Calculation Method");
			entertTextInTextFieldForCalculators_ActivityDetails("Capital Goods Category");
			String strTextField = "(//span[normalize-space()='Capital Good']/ancestor::div//input[contains(@placeholder,'Capital Good')])[2]";
			WebElement capitalGood = driver.findElement(By.xpath(strTextField));
			clearUntillTextFieldIsGettingCleared(capitalGood);
			enterText(capitalGood, "Capital Good", data.get("Capital Good"));
			entertTextInTextFieldForCalculators_ActivityDetails("Production Process Involved");
			commonFieldsForAddingCUSTOMEMISSIONFACTOR_ParameterInput("Source of Emission Factor","true");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	public void EditActivityCapitalGoodsScope3_2SpendBasedEmissions() {
		try {
			ReusabilityForFacilityNameAndStartDateAndEndDateForCalculators();
			if(!Boolean.parseBoolean(data.get("Activity CEF"))) {
				sleep(2000);
	
				String noOptions="//input[@placeholder='Capital Goods Category']/parent::div/parent::div/parent::div/following-sibling::div//div[text()='No options']";
			    SelectDropdownOptionsForCalculatorActivityFields("Capital Goods Category");
//			    if(isElementPresentDynamicXpath(noOptions)) {
//					String CancelButtonInSpend="//button[text()='Cancel']";
//					clickOnElementWithDynamicXpath(CancelButtonInSpend, "CancelButtonInSpend");
//					clickOnAddActivity();
//					ReusabilityForFacilityNameAndStartDateAndEndDateForCalculators();
//					SelectDropdownOptionsForCalculatorActivityFields("Capital Goods Category");
//				}
				String strTextFieldCap = "(//span[normalize-space()='Capital Good']/ancestor::div//input[contains(@placeholder,'Capital Good')])[2]";
				WebElement weCapital = driver.findElement(By.xpath(strTextFieldCap));
				clearUntillTextFieldIsGettingCleared(weCapital);
				enterText(weCapital, "capital goods", data.get("Capital Good"));
				
				entertTextInTextFieldForCalculators_ActivityDetails("Production Process Involved");
			}
			ReusabilityForAmount$Units$Tags$Notes$SaveForCalculators("Amount Spent","Unit/Currency","False","Description");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(lblGHGCalculator);
			if (isElementPresent(lblGHGCalculator)) {
				passed("User Successfully Navigated To GHG_Calculator Page");
			} else {
				failed(driver, "Failed To Navigate To GHG_Calculator Page");
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
}
