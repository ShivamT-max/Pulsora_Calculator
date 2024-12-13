package pages.calculators;
import org.openqa.selenium.WebDriver;
import pages.GHGCalculatorsPage;
import utilities.Data;

public class ProcessingofSoldProductsCalculatorPage extends GHGCalculatorsPage {
	public ProcessingofSoldProductsCalculatorPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	public void EditActivityScope3_10PrcessingofSoldProdutcs() {
		try {
			ReusabilityForFacilityNameAndStartDateAndEndDateForCalculators();
			if(!Boolean.parseBoolean(data.get("Activity CEF"))) {
				SelectDropdownOptionsForCalculatorActivityFields("Sold Product");
				SelectDropdownOptionsForCalculatorActivityFields("Process Type");
			}
			ReusabilityForAmount$Units$Tags$Notes$SaveForCalculators("Mass of Sold Product", "Units", "False", "Description");
		} catch (Exception e) {
			clickOnCancelButtonBeforeAdding();
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void Add_CustomEmissionFactorProcessingofSoldProduct() {
		try {
			entertTextInTextFieldForCalculators_ActivityDetails("Sold Product");
			entertTextInTextFieldForCalculators_ActivityDetails("Process Type");
			commonFieldsForAddingCUSTOMEMISSIONFACTOR_ParameterInput("Source of Emission Factor", "true");
		} catch (Exception e) {
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
