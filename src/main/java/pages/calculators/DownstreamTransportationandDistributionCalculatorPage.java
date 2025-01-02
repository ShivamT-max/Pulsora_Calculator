package pages.calculators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import pages.GHGCalculatorsPage;
import uielements.CalculatorElements;
import utilities.Constants;
import utilities.Data;
import utilities.GlobalVariables;

public class DownstreamTransportationandDistributionCalculatorPage extends CalculatorElements {
	public DownstreamTransportationandDistributionCalculatorPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	Actions mouseActions = new Actions(driver);
	String sourceOfEmissionFactor;

	@FindBy(xpath = "//ul//li[text()='Sabancci Tag1']//*[@data-testid='CheckBoxOutlineBlankIcon']")
	private WebElement drptags;
	@FindBy(xpath = "//input[@id='tag_id']//parent::div//*[@data-testid='ArrowDropDownIcon']")
	private WebElement dptag;
	@FindBy(xpath = "(//input[@placeholder='Sales Region']//parent::div//following::button)[2]")
	private WebElement drpsalesregion;

	public void EditActivityScope3_9Emissions() {
		try {
			Actions act = new Actions(driver);
			verifyIfElementPresent(lblAddActivityFuelEnrgy, "lblAddActivityFuelEnrgy", "lblAddActivityFuelEnrgy");
			Thread.sleep(2000);
			// clickOn(txtFacilityNameScope3_4, "txtFacilityNameScope3_4");
			clickOn(txtFacilityNameScope3_4Qa, "txtFacilityNameScope3_4");
			sleep(20);
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Facility Name") + "']"));
			clickOn(we, data.get("Facility Name"));
			sleep(2000);
			// mouseActions.doubleClick(txtDescrScope3_4).perform();
			// enterText(txtDescrScope3_4, "Description Scope3.4", data.get("Description"));
			clickOn(drpsalesregion, "clicked on sales region");
			WebElement sales = driver.findElement(By.xpath("//li[contains(text(),'Sr 1')]"));
			clickOn(sales,"sales region");
			clickOn(invoceNoupSt, "Invoice No.");
			mouseActions.doubleClick(invoceNoupSt).perform();
			enterText(invoceNoupSt, "Invoice No.", data.get("Invoice No."));
			clickOn(txtInvoiceDate, "Invoice Date");
			enterText(txtInvoiceDate, "Invoice Date", data.get("Invoice Date"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			// ----
			mouseActions.doubleClick(txtServiceProvider).perform();
			enterText(txtServiceProvider, "Service Provider", data.get("Service Provider"));
			mouseActions.doubleClick(txtServiceType).perform();
			enterText(txtServiceType, "Service Type", data.get("Service Type"));
			clickOn(drpModeOfFrght, "Mode of freight DropDown");
			WebElement weModeType = driver.findElement(By.xpath("//li[text()='" + data.get("Mode of Freight") + "']"));
			clickOn(weModeType, data.get("Mode of Freight"));
			clickOn(drpVehcle, "Vehicle Type DropDown");
			WebElement weVehicleTypeEnter = driver.findElement(By.xpath("//li[text()='" + data.get("Vehicle") + "']"));
			// enterText(weVehicleTypeEnter, "Vehicle Type", Vehicle.get(i));
			clickOn(weVehicleTypeEnter, data.get("Vehicle"));
			clickOn(drpType, "Type DropDown");
			WebElement weType = driver.findElement(By.xpath("//li[text()='" + data.get("Type") + "']"));
			// clickOn(weFuelName, data.get("Fuel Name"));
			jsClick(weType, data.get("Type"));
			sleep(5000);
			clickOn(drpFuel_Size, "Fuel/Size Drop Down");
			WebElement weFuel_Size = driver.findElement(By.xpath("//li[text()='" + data.get("Fuel / Size") + "']"));
			clickOn(weFuel_Size, data.get("Fuel / Size"));
			mouseActions.doubleClick(txtWeight).perform();
			enterText(txtWeight, "Weight", data.get("Weight"));
			clickOn(drpWeightUnit, "Fuel/Size Drop Down");
			WebElement weWeigthUnit = driver.findElement(By.xpath("//li[text()='" + data.get("Weight Unit") + "']"));
			clickOn(weWeigthUnit, data.get("Weight Unit"));
			mouseActions.doubleClick(txtDistance).perform();
			enterText(txtDistance, "Distance", data.get("Distance"));
			clickOn(drpDisatnceUnit, "Distance Drop Down");
			WebElement weDistanceUnit = driver
					.findElement(By.xpath("//li[text()='" + data.get("Distance Unit") + "']"));
			clickOn(weDistanceUnit, data.get("Distance Unit"));
			waitForElement(btnSave);
			act.moveToElement(btnSave).doubleClick().perform();
			verifyAddActivityUpdatedToastMessage();
			try {
				if (btnClose.isDisplayed()) {
					clickOn(btnClose, "");
				}
			} catch (Exception e) {
				System.out.println("Activity details RHP Closed");
			}
			System.out.println("-------Validate Emission Factor and tCO2e details---------");
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void ValidateActivityDetailsInViewActivityScope3_9() {
		try {
			Thread.sleep(2000);
			System.out.println("------validate details----------");
			String[] activityDetailFieldNames = { "Facility Name", "Invoice No.", "Invoice Date", "Start Date",
					"End Date", "Service Provider", "Service Type", "Mode of Freight", "Custom Emission Factor",
					"Vehicle", "Type", "Fuel / Size", "Activity Amount", "Weight", "Weight Unit", "Distance",
					"Distance Unit", "Source", "Emission Factor" };
			Thread.sleep(5000);
			verifyIfElementPresent(lblActivityDetails, "lblViewActivityFulEngy",
					"upstream transportation details page");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				WebElement weActivityField = driver.findElement(By.xpath(
						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::p//following-sibling::p"));
				if (weActivityField.getText().trim().equals(data.get(activityDetailFieldNames[j]).trim())) {
					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As "
							+ weActivityField.getText());
				} else {
					failed(driver,
							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
									+ data.get(activityDetailFieldNames[j]) + " But Actual is "
									+ weActivityField.getText());
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void ValidateActivityDetailsInViewActivityScope3_9CEF() {
		try {
			Thread.sleep(2000);
			System.out.println("------validate details----------");
			String[] activityDetailFieldNames = { "Facility Name", "Description", "Invoice No.", "Invoice Date",
					"Start Date", "End Date", "Service Provider", "Service Type", "Mode of Freight", "Vehicle", "Type",
					"Fuel / Size", "Activity Amount", "Weight", "Weight Unit", "Distance", "Distance Unit" };
			Thread.sleep(5000);
			verifyIfElementPresent(lblActivityDetails, "lblViewActivityFulEngy",
					"upstream transportation details page");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				WebElement weActivityField = driver.findElement(By.xpath(
						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::p//following-sibling::p"));
				if (weActivityField.getText().trim().equals(data.get(activityDetailFieldNames[j]).trim())) {
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

	String nameOfCEF;

	public void addCEFForDownStreamTrans() {
		try {
			sleep(2000);
			clickOn(btnAddCEF, "Custom Emission Factor");
			verifyIfElementPresent(lblAddCEF, "CEF", "CEF");
			nameOfCEF = Constants.customEmissionFactor + generateRandomNumber(3);
			enterText(txtNameCEFForFuel, "name of cusytom emission factor", nameOfCEF);
			clickOn(drpCalculationMethod, "Calculation Method");
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Calculation Method") + "']"));
			clickOn(we, "calculation method" + data.get("Calculation Method"));
			sourceOfEmissionFactor = "SRCEF" + generateRandomNumber(3);
			enterText(txtSourceOfEmissionFactorFuel, "Source of Emission Factor", sourceOfEmissionFactor);
			clickOn(drpModeOfFreight, "Mode of freight");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Mode of Freight") + "']"));
			clickOn(we, data.get("Mode of Freight"));
			clickOn(drpWeightUnitDown, "Weight Unit");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Weight Unit") + "']"));
			clickOn(we, data.get("Weight Unit"));
			clickOn(drpDistanceUnitDown, "Distance Unit");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Distance Unit") + "']"));
			clickOn(we, data.get("Distance Unit"));
//			 clickOn(drpCurrencyDownstream, "Currency");
//			 we = driver.findElement(By.xpath("//li[text()='" + data.get("Currency") + "']"));
//			 clickOn(we, data.get("Currency"));
			enterText(txtco2e, "CO2e", data.get("CO2e"));
			sleep(2000);
			clickOn(drpUnitOfCustomEFNum, "unit of custom EF numerator");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Numerator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Numerator)"));
			clickOn(btnSaveParemeterInput, "Save Custom Emission factor");
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void addCEFForDownStreamTransEdit() {
		try {
			Actions mouseAction = new Actions(driver);
			sleep(1000);
			clickOn(btnAddCEF, "Custom Emission Factor");
			verifyIfElementPresent(lblAddCEF, "CEF", "CEF");
			nameOfCEF = Constants.customEmissionFactor + generateRandomNumber(3);
			enterText(txtNameCEFForFuel, "name of cusytom emission factor", nameOfCEF);
			clickOn(drpCalculationMethod, "Calculation Method");
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Calculation Method") + "']"));
			clickOn(we, "calculation method" + data.get("Calculation Method"));
			sourceOfEmissionFactor = "SRCEF" + generateRandomNumber(3);
			enterText(txtSourceOfEmissionFactorFuel, "Source of Emission Factor", sourceOfEmissionFactor);
			clickOn(drpModeOfFreight, "Mode of freight");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Mode of Freight") + "']"));
			clickOn(we, data.get("Mode of Freight"));
			clickOn(drpWeightUnitDown, "Weight Unit");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Weight Unit") + "']"));
			clickOn(we, data.get("Weight Unit"));
			clickOn(drpDistanceUnitDown, "Distance Unit");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Distance Unit") + "']"));
			clickOn(we, data.get("Distance Unit"));
//			 clickOn(drpCurrencyDownstream, "Currency");
//			 we = driver.findElement(By.xpath("//li[text()='" + data.get("Currency") + "']"));
//			 clickOn(we, data.get("Currency"));
			mouseAction.doubleClick(txtco2e).perform();
			enterText(txtco2e, "CO2e", data.get("CO2eEdit"));
			sleep(2000);
			clickOn(drpUnitOfCustomEFNum, "unit of custom EF numerator");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Numerator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Numerator)"));
			clickOn(btnSaveParemeterInput, "Save Custom Emission factor");
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void clickOnCEFInCEFGridMultiple() {
		sleep(3000);
		WebElement weActivity = driver
				.findElement(By.xpath("//div[text()='" + nameOfCEF + "']//parent::div[@role='row']"));
		clickOn(weActivity, "Activity of Facility" + nameOfCEF);
	}

	public void clickWeigth_DistanceBasedDownStream() {
		waitForElement(btnweightDistanceBased);
		clickOn(btnweightDistanceBased, "weight-distance based method");
		sleep(2000);
	}

	public void EditActivityScope3_9EmissionsCEf() {
		try {
			verifyIfElementPresent(lblAddActivityFuelEnrgy, "lblAddActivityFuelEnrgy", "lblAddActivityFuelEnrgy");
			Thread.sleep(2000);
			// clickOn(txtFacilityNameScope3_4, "txtFacilityNameScope3_4");
			clickOn(txtFacilityNameScope3_4Qa, "txtFacilityNameScope3_4");
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Facility Name") + "']"));
			clickOn(we, data.get("Facility Name"));
			sleep(2000);
			mouseActions.doubleClick(txtDescrScope3_4).perform();
			enterText(txtDescrScope3_4, "Description Scope3.4", data.get("Description"));
			clickOn(invoceNoupSt, "Invoice No.");
			mouseActions.doubleClick(invoceNoupSt).perform();
			enterText(invoceNoupSt, "Invoice No.", data.get("Invoice No."));
			clickOn(txtInvoiceDate, "Invoice Date");
			enterText(txtInvoiceDate, "Invoice Date", data.get("Invoice Date"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			// ----
			validateCEF();

			mouseActions.doubleClick(txtServiceProvider).perform();
			enterText(txtServiceProvider, "Service Provider", data.get("Service Provider"));
			mouseActions.doubleClick(txtServiceType).perform();
			enterText(txtServiceType, "Service Type", data.get("Service Type"));
//		    clickOn(drpModeOfFrght, "Mode of freight DropDown");
//		    WebElement weModeType = driver.findElement(By.xpath("//li[text()='" + data.get("Mode of Freight") + "']"));
//		    clickOn(weModeType, data.get("Mode of Freight"));
			WebElement drpvehicleDown = driver.findElement(By.xpath("//input[@id='vehicle_name']"));
			if (isElementPresent(drpvehicleDown)) {
				failed(driver, "Vehicle element is enable");
			} else {
				passed("Vehicle element is disable");
			}
			mouseActions.doubleClick(txtWeight).perform();
			enterText(txtWeight, "Weight", data.get("Weight"));
			clickOn(drpWeightUnit, "Fuel/Size Drop Down");
			WebElement weWeigthUnit = driver.findElement(By.xpath("//li[text()='" + data.get("Weight Unit") + "']"));
			clickOn(weWeigthUnit, data.get("Weight Unit"));
			mouseActions.doubleClick(txtDistance).perform();
			enterText(txtDistance, "Distance", data.get("Distance"));
			clickOn(drpDisatnceUnit, "Distance Drop Down");
			WebElement weDistanceUnit = driver
					.findElement(By.xpath("//li[text()='" + data.get("Distance Unit") + "']"));
			clickOn(weDistanceUnit, data.get("Distance Unit"));
			sleep(5000);
			clickOn(btnSave, "Save Button upstream transportation ");
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void validateCEF() {
		sleep(1000); // id="emissionName"
		jsClick(chbxCustomEmssionFactorFuel, "yes");
		verifyIfElementPresent(drpCustomEmissionDownStream, "--", "--");
		clickOn(drpCustomEmissionDownStream, "");
		WebElement namecef = driver.findElement(By.xpath("//li[text()='" + nameOfCEF + "']"));
		JavascriptExecutor jsExc = (JavascriptExecutor) driver;
		jsExc.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow');", namecef);
		String drpCEF = "//li[text()='" + nameOfCEF + "']";
		clickOnElementWithDynamicXpath(drpCEF, nameOfCEF);
		WebElement qw = driver.findElement(By.xpath("//input[@id='emissionName']"));
		String CustomEmissionFactorNameActivity = qw.getAttribute("value");
		System.out.println(CustomEmissionFactorNameActivity);
		if (CustomEmissionFactorNameActivity.equals(nameOfCEF)) {
			passed("Successfully Validated Custom Emisiion Factor in Activities" + CustomEmissionFactorNameActivity);
		} else {
			failed(driver, " Failed to Validated Custom Emisiion Factor in Activities Expected is " + nameOfCEF
					+ "But Actual is" + CustomEmissionFactorNameActivity);
		}
	}

	public void ValidateCEFScope3_9() {
		try {
			sleep(4000);
			WebElement cstomEmission = driver.findElement(
					By.xpath("//span[contains(text(),'Custom Emission')]//parent::p//following-sibling::p"));
			WebElement cstomEmissionSource = driver
					.findElement(By.xpath("//span[contains(text(),'Source')]//parent::p//following-sibling::p"));
			System.out.println("custom emission factor" + cstomEmission.getText());
			System.out.println("custom Source" + cstomEmissionSource.getText());
			if (cstomEmission.getText().equals(nameOfCEF)) {
				passed("successfully validated CEF in RHP as actual is" + cstomEmission.getText() + " "
						+ "and Expected is" + " " + nameOfCEF);
			} else {
				fail("failed to validate CEF in RHP as actual is" + " " + cstomEmission.getText() + " "
						+ "and Expected is" + " " + nameOfCEF);
			}
			if (cstomEmissionSource.getText().equals(sourceOfEmissionFactor)) {
				passed("successfully validated Source in RHP as actual is" + cstomEmissionSource.getText() + " "
						+ "and Expected is" + " " + sourceOfEmissionFactor);
			} else {
				fail("failed to validate Source in RHP as actual is" + " " + cstomEmissionSource.getText() + " "
						+ "and Expected is" + " " + sourceOfEmissionFactor);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void ValidateCEFDetailsInViewCEF() {
		try {
			sleep(1000);
			WebElement weNameOfCEF = driver.findElement(By.xpath(
					"//span[contains(text(),'Name of Custom Emission Factor')]//parent::div//following-sibling::div/div"));
			WebElement weSourceOfCEF = driver.findElement(
					By.xpath("//span[contains(text(),'Source')]//parent::div//following-sibling::div/div"));
			WebElement weUnitCustmEFNum = driver.findElement(By.xpath(
					"//span[contains(text(),'Unit of Custom EF (Numerator)')]//parent::div//following-sibling::div/div"));
			sleep(500);
			if (weNameOfCEF.getText().equals(nameOfCEF)) {
				passed("successfully validated name of custom emission factor as expected" + nameOfCEF + "and actual is"
						+ weNameOfCEF.getText());
			} else {
				fail("failed to validated name of custom emission factor as expected" + nameOfCEF + "and actual is"
						+ weNameOfCEF.getText());
			}
			if (weSourceOfCEF.getText().equals(sourceOfEmissionFactor)) {
				passed("successfully validated source of custom emission factor");
			} else {
				fail("failed to validate source of custom emission factor");
			}
			if (weUnitCustmEFNum.getText().equals(data.get("Unit of Custom EF (Numerator)"))) {
				passed("successfully validated name of unit of custom EF  as expected"
						+ data.get("Unit of Custom EF (Numerator)") + "and actual is" + weUnitCustmEFNum.getText());
			} else {
				fail("failed to validated name of custom emission factor as expected"
						+ data.get("Unit of Custom EF (Numerator)") + "and actual is" + weUnitCustmEFNum.getText());
			}
			String[] activityDetailFieldNames = { "Calculation Method", "Mode of Freight", "Weight Unit",
					"Distance Unit", "CO2e" };
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

	public void ValidateRHPCEFValueswithActivitiesRHP() {
		WebElement valuetCO2e = driver
				.findElement(By.xpath("(//span[contains(text(),'tCO2e')])[2]//parent::p//following-sibling::p"));
		Double CO2 = Double.parseDouble(data.get("CO2e")) * Double.parseDouble(data.get("Activity Amount"));
		String tCO2Activity = CO2.toString();
		String tCO2ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCO2Activity);
		if (tCO2ActivityRHP.equals(valuetCO2e.getText())) {
			passed("Succesfully validated tCO2 value as expected" + " " + tCO2ActivityRHP + " And Actual is "
					+ valuetCO2e.getText());
		} else {
			fail("Failed validated tCO2 value as expected" + " " + tCO2ActivityRHP + " But Actual is "
					+ valuetCO2e.getText());
		}
		WebElement emission_Factor = driver.findElement(
				By.xpath("((//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p))[2]"));
		String[] splitEF = emission_Factor.getText().split(" ");
		if (data.get("CO2e").equals(splitEF[0])) {
			passed("Succesfully validated emission factor value" + " " + data.get("CO2e") + " And Actual is "
					+ emission_Factor.getText());
		} else {
			fail("Failed validated emission factor value" + " " + data.get("CO2e") + " But Actual is "
					+ emission_Factor.getText());
		}
	}
	@FindBy(xpath = "//*[text()='Scope 3.9 - Downstream Transportation and Distribution']")
	private WebElement lblGHGCalculatorDWST;
	
	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(lblGHGCalculatorDWST);
			if (isElementPresent(lblGHGCalculatorDWST)) {
				passed("User Successfully Navigated To GHG_Calculator Page "+data.get("CalcName"));
			} else {
				failed(driver, "Failed To Navigate To GHG_Calculator Page "+data.get("CalcName"));
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}


	public void verifyAddActivityUpdatedToastMessage() {
		try {
			sleep(100);
			By toast = By.xpath("//*[contains(text(),'Activity added successfully')]");
			By toastEdit = By.xpath("//div[contains(text(),'Activity updated successfully')]");
			if (data.get("Edit").equals("YES")) {
				if (isElementPresent(toastEdit)) {
					passed("Successfully displayed toast message - Activity updated successfully");
					sleep(2000);
				} else {
					failed(driver, "Failed to display updated toast message");
				}
			} else {
				if (isElementPresent(toast)) {
					passed("Successfully displayed toast message - Activity added successfully");
					sleep(2000);
				} else {
					failed(driver, "Failed to display added toast message");
				}
			}

			/*
			 * if (isElementPresent(toastMessagePopUp)) { jsClick(toastMessagePopUp,
			 * "AddedSucessfully"); } else { failed(driver,
			 * "Failed to display toast message"); }
			 */
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	public void valiadteTOTALCO2EforDownstream() {
		try {
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails",
					"Downstream Transportation and Distribution");
			WebElement emissionFactor3 = driver.findElement(
					By.xpath("(//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p)[2]"));
			String[] splitEF3 = emissionFactor3.getText().replaceAll(",", "").split(" ");
			String d = data.get("Activity Amount").replaceAll((","), (""));
			Double result3 = Double.parseDouble(splitEF3[0]) * Double.parseDouble(d) / 1000;
			String res3 = result3.toString();
			String CalCo2e3 = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(res3);
			WebElement valuetCO2e3 = driver
					.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
			GlobalVariables.exceptedtco2e = valuetCO2e3.getText().trim();
			if (CalCo2e3.trim().equals(valuetCO2e3.getText().trim())) {
				passed("Successfully validated tCO2e value for : " + " Downstream Transportation and Distribution "
						+ valuetCO2e3.getText());
			} else {
				failed(driver, "Failed to valiadte tCO2e value for : " + " Downstream Transportation and Distribution "
						+ valuetCO2e3.getText() + " but actual should be " + CalCo2e3);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());

		}
	}
}
