package pages.calculators;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import pages.GHGCalculatorsPage;
import uielements.CalculatorElements;
import utilities.Constants;
import utilities.Data;

public class EndOfLifeTreatmentGHGCalculatorPage extends GHGCalculatorsPage {
	public EndOfLifeTreatmentGHGCalculatorPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	public void EditActivityEndOfLifeTreatmentScope3_12Emissions() {
		try {
			ReusabilityForFacilityNameAndStartDateAndEndDateForCalculators();
			if(!Boolean.parseBoolean(data.get("Activity CEF"))) {
				SelectDropdownOptionsForCalculatorActivityFields("Product Waste Category");
				SelectDropdownOptionsForCalculatorActivityFields("Product Waste Type");
				SelectDropdownOptionsForCalculatorActivityFields("Waste Disposal Method");
			}
			ReusabilityForAmount$Units$Tags$Notes$SaveForCalculators("Mass of Waste after Consumer Use", "Unit", "False", "Description");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}



	public void Add_CustomEmissionFactorEndofLifeTreatmentScope3_12() {
		try {
			SelectDropdownOptionsForCalculatorActivityFields("Product Waste Category");
			SelectDropdownOptionsForCalculatorActivityFields("Product Waste Type");
			SelectDropdownOptionsForCalculatorActivityFields("Waste Disposal Method");
			commonFieldsForAddingCUSTOMEMISSIONFACTOR_ParameterInput("Source of Emission Factor", "true");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	

	

	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(lblGHGCalculator);
			if (isElementPresent(lblGHGCalculator)) {
				passed("User Successfully Navigated To EOLT Page");
			} else {
				failed(driver, "Failed To Navigate To EOLT Page");
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
}
