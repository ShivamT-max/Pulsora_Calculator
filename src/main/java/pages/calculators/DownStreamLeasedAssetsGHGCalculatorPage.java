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

public class DownStreamLeasedAssetsGHGCalculatorPage extends GHGCalculatorsPage {
	public DownStreamLeasedAssetsGHGCalculatorPage(WebDriver driver2, Data data) {
		super(driver2, data);
		// TODO Auto-generated constructor stub
	}

	public void EditActivityScope3_13DownstreamLeasedAssets() {
		try {
			ReusabilityForFacilityNameAndStartDateAndEndDateForCalculators();
			entertTextInTextFieldForCalculators_ActivityDetails("Leased Asset");
			if(!Boolean.parseBoolean(data.get("Activity CEF"))) {
				SelectDropdownOptionsForCalculatorActivityFields("Energy Category");
				SelectDropdownOptionsForCalculatorActivityFields("Type");
			}
			ReusabilityForAmount$Units$Tags$Notes$SaveForCalculators("Activity Amount", "Units", "False", "Description");
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
