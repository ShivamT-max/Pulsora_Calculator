package pages.calculators;


import org.openqa.selenium.WebDriver;
import pages.GHGCalculatorsPage;
import utilities.Data;

public class HotelStayGHGCalculatorPage extends GHGCalculatorsPage {
	public HotelStayGHGCalculatorPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	public void EditActivityScope3_6HotelStayEmissions() {
		try {sleep(1);
			ReusabilityForFacilityNameAndStartDateAndEndDateForCalculators();
			SelectDropdownOptionsForCalculatorActivityFields("Country of Hotel Stay");
			entertTextInTextFieldForCalculators_ActivityDetails("Number of Rooms Occupied");
			ReusabilityForAmount$Units$Tags$Notes$SaveForCalculators("Number of Nights Stayed", "Units", "False", "Notes");
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