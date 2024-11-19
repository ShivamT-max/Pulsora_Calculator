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

public class UpstreamTransportationandDistributionCalculatorPage extends CalculatorElements {
	public UpstreamTransportationandDistributionCalculatorPage(WebDriver driver2, Data data) {
		super(driver2, data);
		// TODO Auto-generated constructor stub
	}

	Actions mouseActions = new Actions(driver);
	String sourceOfEmissionFactor;

	public void EditActivityScope3_4Emissions() {
		try {
			verifyIfElementPresent(lblAddActivityFuelEnrgy, "lblAddActivityFuelEnrgy", "lblAddActivityFuelEnrgy");
			Thread.sleep(5000);
			// clickOn(txtFacilityNameScope3_4, "txtFacilityNameScope3_4");
			clickOn(txtFacilityNameScope3_4Qa, "txtFacilityNameScope3_4");
			sleep(2000);
			WebElement we = driver.findElement(By.xpath("//ul//li[text()='" + data.get("Facility Name") + "']"));
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
			mouseActions.doubleClick(txtServicePrvodr).perform();
			enterText(txtServicePrvodr, "Service Provider", data.get("Service Provider"));
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
			sleep(2000);

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
			sleep(5000);
			clickOn(btnSave, "Save Button upstream transportation ");
			verifyAddActivityUpdatedToastMessage();
			System.out.println("-------Validate details---------");
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}
	
	@FindBy(xpath = "//button[text()='Close']")
	WebElement toastMessagePopUp;

	public void verifyAddActivityUpdatedToastMessage() {
		try {
			sleep(100);
			By toast = By.xpath("//*[contains(text(),'Activity added successfully')]");
			By toastEdit = By.xpath("//div[contains(text(),'Activity updated successfully')]");
			if(data.get("Edit").equals("YES")) {
				if (isElementPresent(toastEdit)) {
					passed("Successfully displayed toast message - Activity updated successfully");
					sleep(2000);
				} else {
					failed(driver, "Failed to display updated toast message");
				}			}else {
				if (isElementPresent(toast)) {
					passed("Successfully displayed toast message - Activity added successfully");
					sleep(2000);
				} else {
					failed(driver, "Failed to display added toast message");
				}
			}
			
			if (isElementPresent(toastMessagePopUp)) {
				jsClick(toastMessagePopUp, "AddedSucessfully");
			} else {
				failed(driver, "Failed to display toast message");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public void addCEFForUpStreamTrans() {
		try {
			sleep(2000);
			clickOn(btnAddCEF, "Custom Emission Factor");
			verifyIfElementPresent(lblAddCEF, "CEF", "CEF");
			nameOfCEF = Constants.customEmissionFactor + generateRandomNumber(3);
			enterText(txtNameCEFForFuel, "name of cusytom emission factor", nameOfCEF);
			clickOn(drpCalculationMethod, "Calculation Method");
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Calculation Method") + "']"));
			clickOn(we, "calculation method" + data.get("Calculation Method"));
			sourceOfEmissionFactor = "SRCEF" + "_" + generateRandomNumber(3);
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
			sleep(1000);
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void addCEFForUpStreamTransEdit() {
		try {
			Actions mouseAction = new Actions(driver);
			sleep(2000);
			verifyIfElementPresent(lblEditCEF, "Edit CEF", "Edit CEF");
			nameOfCEF = Constants.customEmissionFactor + generateRandomNumber(3);
			mouseAction.doubleClick(txtNameCEFForFuel).perform();
			enterText(txtNameCEFForFuel, "name of cusytom emission factor", nameOfCEF);
			clickOn(drpCalculationMethod, "Calculation Method");
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Calculation Method") + "']"));
			clickOn(we, "calculation method" + data.get("Calculation Method"));
			sourceOfEmissionFactor = "SRCEF" + "_" + generateRandomNumber(3);
			mouseAction.doubleClick(txtSourceOfEmissionFactorFuel).perform();
			enterText(txtSourceOfEmissionFactorFuel, "Source of Emission Factor", sourceOfEmissionFactor);
			clickOn(drpModeOfFreight, "Mode of freight");
			WebElement we1 = driver.findElement(By.xpath("//li[text()='" + data.get("Mode of Freight1") + "']"));
			clickOn(we1, data.get("Mode of Freight1"));
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
			enterText(txtco2e, "CO2e", data.get("co2Edit"));
			sleep(1000);
			clickOn(drpUnitOfCustomEFNum, "unit of custom EF numerator");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Numerator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Numerator)"));
			clickOn(btnSaveParemeterInput, "Save Custom Emission factor");
			sleep(1000);
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void ValidateCEFDetailsInViewCEF() {
		try {
			WebElement weNameOfCEF = driver.findElement(By.xpath(
					"//span[contains(text(),'Name of Custom Emission Factor')]//parent::div//following-sibling::div"));
			WebElement weSourceOfCEF = driver
					.findElement(By.xpath("//span[contains(text(),'Source')]//parent::div//following-sibling::div"));
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
			String[] activityDetailFieldNames = { "Calculation Method", "Mode of Freight", "Weight Unit",
					"Distance Unit", "CO2e", "Unit of Custom EF (Numerator)" };
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

	public void clickOnCEFInCEFGridMultiple() {
		sleep(3000);
		WebElement weActivity = driver.findElement(By.xpath("//*[text()='" + nameOfCEF + "']//parent::div"));
		clickOn(weActivity, "Activity of Facility" + nameOfCEF);
	}

	public void clickWeigth_DistanceBasedDownStream() {
		waitForElement(btnWeightDistanceUpstream);
		clickOn(btnWeightDistanceUpstream, "weight-distance based method");
		sleep(2000);
	}

	String nameOfCEF;

	public void EditActivityScope3_4EmissionsCEf() {
		try {
			verifyIfElementPresent(lblAddActivityFuelEnrgy, "lblAddActivityFuelEnrgy", "lblAddActivityFuelEnrgy");
			Thread.sleep(2000);
			// clickOn(txtFacilityNameScope3_4, "txtFacilityNameScope3_4");
			clickOn(txtFacilityNameScope3_4Qa, "txtFacilityNameScope3_4");
			sleep(2000);
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
//			String drpCEF = "//li[text()='" + nameOfCEF + "']";
//			clickOnElementWithDynamicXpath(drpCEF, nameOfCEF);

			mouseActions.doubleClick(txtServicePrvodr).perform();
			enterText(txtServicePrvodr, "Service Provider", data.get("Service Provider"));
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
			System.out.println("-------Validate details---------");
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void ValidateActivityDetailsInViewAddActivityScope3_4() {
		try {
			Thread.sleep(1000);
			System.out.println("------validate details----------");
			String[] activityDetailFieldNames = { "Facility Name", "Invoice No.", "Invoice Date",
					"Start Date", "End Date", "Custom Emission Factor", "Service Provider", "Service Type",
					"Mode of Freight", "Vehicle", "Type", "Fuel / Size", "Activity Amount", "Weight", "Weight Unit",
					"Distance", "Distance Unit", "Source"};
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

	public void ValidateActivityDetailsInViewActivityScope3_4() {
		try {
			Thread.sleep(1000);
			System.out.println("------validate details----------");
			String[] activityDetailFieldNames = { "Facility Name", "Invoice No.", "Invoice Date",
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

	public void validateCEF() {
		sleep(1000);
		jsClick(chbxCustomEmssionFactorFuel, "yes");
		verifyIfElementPresent(drpCustomEmiFactorActivityBussiness, "--", "--");
		clickOn(drpCustomEmiFactorActivityBussiness, "-");
		WebElement namecef = driver.findElement(By.xpath("//li[text()='" + nameOfCEF + "']"));
		JavascriptExecutor jsExc = (JavascriptExecutor) driver;
		jsExc.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow');", namecef);
		sleep(1000);
		clickOn(namecef, "nameOfCEFDrpOptions");
		WebElement dropDwonValues = driver.findElement(
				By.xpath("//span[text()='Custom Emission Factor']//parent::div/following-sibling::div/div/div/input"));
//		for (int i = 0; i < dropDwonValues.size(); i++) {
//			if (dropDwonValues.get(i).getText().contains(nameOfCEF)) {
//				passed("Successfully validated custom emission factor name in avtivities of scope 2");
//				break;
//			} else {
//				failed(driver, "failed to validated custom emission factor name in avtivities of scope 2");
//			}
//		}

		String ewq = dropDwonValues.getAttribute("value");
		if (nameOfCEF.equals(ewq)) {
			passed("Successfully validated custom emission factor name in avtivities of scope 2");
		} else {
			failed(driver, "failed to validated custom emission factor name in avtivities of scope 2");
		}
	}

	public void ValidateCEFScope3_4() {
		try {
			sleep(1000);
			WebElement cstomEmission = driver.findElement(
					By.xpath("//span[contains(text(),'Custom Emission')]//parent::p//following-sibling::p"));
			WebElement cstomEmissionSource = driver
					.findElement(By.xpath("//span[contains(text(),'Source')]//parent::p//following-sibling::p"));
			System.out.println(cstomEmission.getText());
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

	public void ValidateRHPCEFValueswithActivitiesRHP() {
		WebElement valuetCO2 = driver
				.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
		Double CO2 = Double.parseDouble(data.get("CO2e")) * Double.parseDouble(data.get("Activity Amount"));
		String tCO2Activity = CO2.toString();
		String tCO2ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCO2Activity);
		if (tCO2ActivityRHP.equals(valuetCO2.getText())) {
			passed("Succesfully validated tCO2 value" + tCO2ActivityRHP + " And Actual is " + valuetCO2.getText());
		} else {
			fail("Failed validated tCO2 value" + " " + tCO2ActivityRHP + " But Actual is " + valuetCO2.getText());
		}
		WebElement EFActivity = driver
				.findElement(By.xpath("//span[text()='Emission Factor']//parent::p//following-sibling::p"));
		String s2 = EFActivity.getText().trim();
		String[] EFValueRHP = s2.split(" ");
		if (EFValueRHP[0].equals(data.get("CO2e"))) {
			passed("Succesfully validated Emission factor Expected value" + " " + data.get("CO2e") + " And Actual is "
					+ EFValueRHP[0]);
		} else {
			fail("Failed validated Emission Factor Expected value" + " " + data.get("CO2e") + " But Actual is "
					+ EFValueRHP[0]);
		}
	}

	public void ValidateRHPCEFValueswithActivitiesRHPEdit() {
		WebElement valuetCO2 = driver
				.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
		Double CO2 = Double.parseDouble(data.get("co2Edit")) * Double.parseDouble(data.get("Activity Amount"));
		String tCO2Activity = CO2.toString();
		String tCO2ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCO2Activity);
		if (tCO2ActivityRHP.equals(valuetCO2.getText())) {
			passed("Succesfully validated tCO2 value" + tCO2ActivityRHP + " And Actual is " + valuetCO2.getText());
		} else {
			fail("Failed validated tCO2 value" + " " + tCO2ActivityRHP + " But Actual is " + valuetCO2.getText());
		}
		WebElement EFActivity = driver
				.findElement(By.xpath("//span[text()='Emission Factor']//parent::p//following-sibling::p"));
		String s2 = EFActivity.getText().trim();
		String[] EFValueRHP = s2.split(" ");
		if (EFValueRHP[0].equals(data.get("co2Edit"))) {
			passed("Succesfully validated Emission factor Expected value" + " " + data.get("co2Edit")
					+ " And Actual is " + EFValueRHP[0]);
		} else {
			fail("Failed validated Emission Factor Expected value" + " " + data.get("co2Edit") + " But Actual is "
					+ EFValueRHP[0]);
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

	public void validateTotalCO2EforUpstream() {
		try {
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails",
					"Upstream Transportation and Distribution Energy related Activities");
			WebElement emissionfactor = driver.findElement(
					By.xpath("(//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p)[2]"));
			String[] splitEF4 = emissionfactor.getText().split(" ");
			if (emissionfactor.getText().equals(data.get("Emission Factor"))) {
				passed("Successfully validated EmissionFactor as expected is==>" + data.get("Emission Factor")+" and actual is==>"+emissionfactor.getText());
			} else {
				failed(driver, "Failed to validate EmissionFactor as expected is==>" + data.get("Emission Factor") + " but Actual is==>"
						+ emissionfactor.getText());
			}
			WebElement ee = driver
					.findElement(By.xpath("//span[contains(text(),'Conversion')]//parent::p//following-sibling::p"));
			String hh = ee.getText().replaceAll(",", "");
			String[] yy = hh.split("=");
			String[] t=yy[1].split(" ");
			Double d = Double.parseDouble(t[1]);
			WebElement emissionFactor1 = driver.findElement(
					By.xpath("(//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p)[2]"));
			String[] splitEF1 = emissionFactor1.getText().split(" ");
			Double result1 = (Double.parseDouble(splitEF1[0]) * Double.parseDouble(data.get("Activity Amount"))*d )/ 1000;
			String res1 = result1.toString();
			String CalCo2e1 = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(res1);
			WebElement valuetCO2e1 = driver
					.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
			GlobalVariables.exceptedtco2e=valuetCO2e1.getText().trim();
			if (CalCo2e1.trim().equals(valuetCO2e1.getText().trim())) {
				passed("Successfully validated tCO2e value for : " + " Upstream Transportation and Distribution "
						+ valuetCO2e1.getText());
			} else {
				failed(driver, "Failed to valiadte tCO2e value for : " + " Upstream Transportation and Distribution "
						+ valuetCO2e1.getText()+" But Actual as "+CalCo2e1);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
}
