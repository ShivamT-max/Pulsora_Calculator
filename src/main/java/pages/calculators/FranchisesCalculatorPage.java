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

public class FranchisesCalculatorPage extends CalculatorElements {
	public FranchisesCalculatorPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	Actions mouseActions = new Actions(driver);

	public void EditActivityScope3_14Emissions() {
		try {
			verifyIfElementPresent(lblAddActivityFuelEnrgy, "lblAddActivityFuelEnrgy", "lblAddActivityFuelEnrgy");
			Thread.sleep(3000);
			// clickOn(txtFacilityNameScope3_4, "txtFacilityNameScope3_4");
			clickOn(txtFacilityFrenchiseDmo, "txtFacilityNameScope3_14");
			sleep(2000);
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Facility Name") + "']"));
			clickOn(we, data.get("Facility Name"));
			sleep(2000);
			clickOn(txtFrenchiseNameFrenchiseDmo, "frenhcise drop down");
			WebElement wefrenchiseName = driver
					.findElement(By.xpath("//li[text()='" + data.get("Franchisee Name") + "']"));
			clickOn(wefrenchiseName, data.get("Franchisee Name"));
			mouseActions.doubleClick(txtDescriptionFranchise).perform();
			enterText(txtDescriptionFranchise, "frenchisee Description Scope3.4", data.get("Description"));
			clickOn(txtStartDate, "Start Date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "End Date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			// ----
			// mouseActions.doubleClick(txtLeasedAssetFrenchiseDmo).perform();
			// enterText(txtLeasedAssetFrenchiseDmo, "frenchisee Leased_Asset Scope3.14",
			// data.get("Leased Asset"));
			clickOn(drpEnergyCatgryFrenchiseDmo, "frenchisee Energy category drop down");
			WebElement weEnrgyCat = driver.findElement(By.xpath("//li[text()='" + data.get("Energy Category") + "']"));
			clickOn(weEnrgyCat, data.get("Energy Categorys"));
			clickOn(drpTypeFrenchiseDmo, "Frenchise Type DropDown");
			WebElement weTypeEnter = driver.findElement(By.xpath("//li[text()='" + data.get("Type") + "']"));
			// enterText(weVehicleTypeEnter, "Vehicle Type", Vehicle.get(i));
			clickOn(weTypeEnter, data.get("Type"));
			mouseActions.doubleClick(txtActcivityAmntFrenchiseDmo).perform();
			enterText(txtActcivityAmntFrenchiseDmo, "frenchisee Leased_Asset Scope3.14", data.get("Activity Amount"));
			clickOn(drpUnitsFranchise, "Frenchise unit drop down");
			WebElement weUnitFrenchise = driver.findElement(By.xpath("//li[text()='" + data.get("Units") + "']"));
			clickOn(weUnitFrenchise, data.get("Units"));
			sleep(3000);
			waitForElement(btnSave);
			mouseActions.moveToElement(btnSave).doubleClick().perform();
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

	@FindBy(xpath = "//button[text()='Close']")
	WebElement toastMessagePopUp;

	public void verifyAddActivityUpdatedToastMessage() {
		try {
			By toast = By.xpath("//*[contains(text(),'Activity added successfully')]");
			By toastEdit = By.xpath("//*contains(text(),'Activity updated successfully')]");
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
			if (isElementPresent(toastMessagePopUp)) {
				jsClick(toastMessagePopUp, "AddedSucessfully");
			} else {
				failed(driver, "Failed to display toast message");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	public void ValidateActivityDetailsInViewActivityScope3_14() {
		try {
			Thread.sleep(2000);
			System.out.println("------validate details----------");
			String[] activityDetailFieldNames = { "Facility Name", "Description", "Franchisee Name", "Start Date",
					"End Date", "Custom Emission Factors", "Energy Category", "Type", "Activity Amount", "Units",
					 "Emission Factor", "Source" };
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

	public void ValidateActivityDetailsInViewActivityScope3_14CEF() {
		try {
			Thread.sleep(1000);
			System.out.println("------validate details----------");
			String[] activityDetailFieldNames = { "Facility Name", "Description", "Franchisee Name", "Start Date",
					"End Date", "Energy Category", "Type", "Activity Amount", "Units", "Emission Factor" };
			Thread.sleep(1000);
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

	public void ValidateActivityDetailsInViewActivityScope3_14CEFEdit() {
		try {
			Thread.sleep(1000);
			WebElement emissionFactor = driver
					.findElement(By.xpath("//span[text()='Emission Factor']//parent::p//following-sibling::p"));
			if (emissionFactor.getText().equals(data.get("Emission Factor edit"))) {
				System.out.println(emissionFactor.getText() + " " + data.get("Emission Factor edit"));
				passed("successfully validated emission factor as in RHP in" + emissionFactor.getText()
						+ "and actual is" + data.get("Emission Factor edit"));
			} else {
				failed(driver, "failed to validate emission factor as in RHP in" + emissionFactor.getText()
						+ "and actual is" + data.get("Emission Factor edit"));
			}
			System.out.println(emissionFactor.getText() + " " + data.get("Emission Factor edit"));
			System.out.println("------validate details----------");
			String[] activityDetailFieldNames = { "Facility Name", "Description", "Franchisee Name", "Start Date",
					"End Date", "Energy Category", "Type", "Activity Amount", "Units" };
			// System.out.println(emissionFactor.getText()+" "+data.get("Emission Factor
			// edit"));
			Thread.sleep(1000);
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

	public void clickOnCEFInCEFGridMultiple() {
		sleep(1000);
		WebElement weActivity = driver
				.findElement(By.xpath("//div[text()='" + customEF + "']//parent::div[@role='row']"));
		clickOn(weActivity, "Activity of Facility" + customEF);
	}

	public void clickAssetSpecificMethod() {
		waitForElement(assetSpecificMethod);
		clickOn(assetSpecificMethod, "Franchisee specific method");
	}

	String customEF;
	String souceCEF;

	public void addCEFForFranchisee() {
		try {
			sleep(2000);
			clickOn(btnAddCEF, "Custom Emission Factor");
			customEF = Constants.customEmissionFactor + generateRandomNumber(3);
			enterText(txtNameOfCustomEFBussiness, "Name of Custom EF", customEF);
			clickOn(drpEnergyCategoryFranchise, "Energy category");
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Energy Category") + "']"));
			clickOn(we, data.get("Energy Category"));
			souceCEF = "SRC" + generateRandomNumber(3);
			enterText(txtSourceCEF, "soucre of Emission factor", souceCEF);
			clickOn(drpunitOfCEFFranchise, "unit of cef denominator");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Denominator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Denominator)"));
			enterText(txtCO2, "co2", data.get("CO2"));
			enterText(txtCH4, "ch4", data.get("CH4"));
			enterText(txtN2O, "n2o", data.get("N2O"));
			// enterText(txtBioFuelBussiness, "biofuel", data.get("Biofuel CO2"));
			clickOn(drpunitOfCEFNumFranchise, "unit of CEF Num");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Numerator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Numerator)"));
			clickOn(btnSaveParemeterInput, "Save Custom Emission factor");
			CO2eFactor();
			sleep(2000);
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void addCEFForFranchiseeEdit() {
		try {
			Actions mouseAction = new Actions(driver);
			sleep(1000);
			// clickOn(btnAddCEF, "Custom Emission Factor");
			customEF = Constants.customEmissionFactor + generateRandomNumber(3);
			mouseAction.doubleClick(txtNameOfCustomEFBussiness).perform();
			enterText(txtNameOfCustomEFBussiness, "Name of Custom EF", customEF);
			clickOn(drpEnergyCategoryFranchise, "Energy category");
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Energy Category") + "']"));
			clickOn(we, data.get("Energy Category"));
			souceCEF = "SRC" + generateRandomNumber(3);
			mouseAction.doubleClick(txtSourceCEF).perform();
			enterText(txtSourceCEF, "soucre of Emission factor", souceCEF);
			clickOn(drpunitOfCEFFranchise, "unit of cef denominator");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Denominator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Denominator)"));
			enterText(txtCO2, "co2", data.get("CO2Edit"));
			enterText(txtCH4, "ch4", data.get("CH4Edit"));
			enterText(txtN2O, "n2o", data.get("N2OEdit"));
			// enterText(txtBioFuelBussiness, "biofuel", data.get("Biofuel CO2"));
			clickOn(drpunitOfCEFNumFranchise, "unit of CEF Num");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Numerator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Numerator)"));
			clickOn(btnSaveParemeterInput, "Save Custom Emission factor");
			CO2eFactorEdit();
			sleep(1000);
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void EditActivityScope3_14EmissionsCEF() {
		try {
			verifyIfElementPresent(lblAddActivityFuelEnrgy, "lblAddActivityFuelEnrgy", "lblAddActivityFuelEnrgy");
			Thread.sleep(1000);
			// clickOn(txtFacilityNameScope3_4, "txtFacilityNameScope3_4");
			clickOn(txtFacilityFrenchiseDmo, "txtFacilityNameScope3_14");
			sleep(1000);
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Facility Name") + "']"));
			clickOn(we, data.get("Facility Name"));
			sleep(1000);
			clickOn(txtFrenchiseNameFrenchiseDmo, "frenhcise drop down");
			WebElement wefrenchiseName = driver
					.findElement(By.xpath("//li[text()='" + data.get("Franchisee Name") + "']"));
			clickOn(wefrenchiseName, data.get("Franchisee Name"));
			mouseActions.doubleClick(txtDescriptionFranchise).perform();
			enterText(txtDescriptionFranchise, "frenchisee Description Scope3.4", data.get("Description"));
			clickOn(txtStartDate, "Start Date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "End Date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			// ----
			// mouseActions.doubleClick(txtLeasedAssetFrenchiseDmo).perform();
			// enterText(txtLeasedAssetFrenchiseDmo, "frenchisee Leased_Asset Scope3.14",
			// data.get("Leased Asset"));

			clickOn(drpEnergyCatgryFrenchiseDmo, "frenchisee Energy category drop down");
			sleep(2000);
			WebElement weEnrgyCat = driver.findElement(By.xpath("//li[text()='" + data.get("Energy Category") + "']"));
			clickOn(weEnrgyCat, data.get("Energy Category"));
			validateCEFFranchise();

			mouseActions.doubleClick(txtActcivityAmntFrenchiseDmo).perform();
			enterText(txtActcivityAmntFrenchiseDmo, "frenchisee Leased_Asset Scope3.14", data.get("Activity Amount"));
			clickOn(drpUnitsFranchise, "Frenchise unit drop down");
			WebElement weUnitFrenchise = driver.findElement(By.xpath("//li[text()='" + data.get("Units") + "']"));
			clickOn(weUnitFrenchise, data.get("Units"));
			sleep(1000);
			clickOn(btnSave, "Save Button");
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void validateCEFFranchise() {
		sleep(1000);
		jsClick(chbxCustomEmssionFactorFuel, "yes");
		clickOn(drpEnergyCatgryFrenchiseDmo, "frenchisee Energy category drop down");
		sleep(2000);
		WebElement weEnrgyCat = driver.findElement(By.xpath("//li[text()='" + data.get("Energy Category") + "']"));
		clickOn(weEnrgyCat, data.get("Energy Category"));
		// verifyIfElementPresent(drpCustomEmiFactorActivityFuel, "--", "--");
		sleep(1000);
		clickOn(drpCustomEmiFactorActivityFuel, "CustomEmissionFactorDropdown");
		sleep(2000);
		WebElement namecef = driver.findElement(By.xpath("//li[text()='" + customEF + "']"));
		JavascriptExecutor jsExc = (JavascriptExecutor) driver;
		jsExc.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow');", namecef);
		String drpCEF = "//li[text()='" + customEF + "']";
		clickOnElementWithDynamicXpath(drpCEF, customEF);
		WebElement qw = driver.findElement(By.xpath("//input[@id='emission_name']"));
		String CustomEmissionFactorNameActivity = qw.getAttribute("value");
		System.out.println(CustomEmissionFactorNameActivity);
		if (CustomEmissionFactorNameActivity.equals(customEF)) {
			passed("Successfully Validated Custom Emisiion Factor in Activities" + CustomEmissionFactorNameActivity);
		} else {
			failed(driver, " Failed to Validated Custom Emisiion Factor in Activities Expected is " + customEF
					+ "But Actual is" + CustomEmissionFactorNameActivity);
		}
	}

	public void ValidateCEFInScope3_14() {
		try {
			sleep(1000);
			WebElement cstomEmission = driver.findElement(By
					.xpath("//span[contains(text(),'Custom Emission Factors')]//parent::p//following-sibling::p/div"));
			WebElement cstomEmissionSource = driver
					.findElement(By.xpath("//span[contains(text(),'Source')]//parent::p//following-sibling::p/div"));
			System.out.println(cstomEmission.getText());
			if (cstomEmission.getText().equals(customEF)) {
				passed("successfully validated CEF in RHP as actual is" + cstomEmission.getText() + " "
						+ "and Expected is" + " " + customEF);
			} else {
				fail("failed to validate CEF in RHP as actual is" + " " + cstomEmission.getText() + " "
						+ "and Expected is" + " " + customEF);
			}
			if (cstomEmissionSource.getText().equals(souceCEF)) {
				passed("successfully validated Source in RHP as actual is" + cstomEmissionSource.getText() + " "
						+ "and Expected is" + " " + souceCEF);
			} else {
				fail("failed to validate Source in RHP as actual is" + " " + cstomEmissionSource.getText() + " "
						+ "and Expected is" + " " + souceCEF);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	Double valueCo2e;
	String v;

	public void CO2eFactor() {
		valueCo2e = Double.parseDouble(data.get("CO2")) + Double.parseDouble(data.get("CH4")) * 25
				+ Double.parseDouble(data.get("N2O")) * 298;
		System.out.println("calculated value" + valueCo2e);
		v = valueCo2e.toString();
	}

	String m;

	public void CO2eFactorEdit() {
		valueCo2e = Double.parseDouble(data.get("CO2Edit")) + Double.parseDouble(data.get("CH4Edit")) * 25
				+ Double.parseDouble(data.get("N2OEdit")) * 298;
		System.out.println("calculated value" + valueCo2e);
		m = valueCo2e.toString();
	}

	public void verifyCO2eFactor() {
		WebElement cefValueRHP = driver
				.findElement(By.xpath("//span[contains(text(),'CO2e')]//parent::div//following-sibling::div/div"));
		String newPlainValue = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(cefValueRHP.getText());
		if (newPlainValue.equals(GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(v))) {
			passed("Succesfully validated CO2e value" + newPlainValue + "--" + v);
		} else {
			fail("Failed validated CO2e value" + " " + newPlainValue + "--" + v);
		}
		WebElement weNameOfCEF = driver.findElement(
				By.xpath("//span[contains(text(),'Name of Custom EF')]//parent::div//following-sibling::div/div"));
		WebElement weSourceOfCEF = driver.findElement(By.xpath(
				"//span[contains(text(),'Source of Emission Factor')]//parent::div//following-sibling::div/div"));
		if (weNameOfCEF.getText().equals(customEF)) {
			passed("successfully validated name of custom emission factor");
		} else {
			fail("failed to validate name of custom emission factor");
		}
		if (weSourceOfCEF.getText().equals(souceCEF)) {
			passed("successfully validated source of custom emission factor");
		} else {
			fail("failed to validate source of custom emission factor");
		}
	}

	public void verifyCO2eFactorEdit() {
		WebElement cefValueRHP = driver
				.findElement(By.xpath("//span[contains(text(),'CO2e')]//parent::div//following-sibling::div/div"));
		String newPlainValue = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(cefValueRHP.getText().replaceAll(",", ""));
		if (newPlainValue.equals(GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(m))) {
			passed("Succesfully validated CO2e value" + newPlainValue + "--" + m);
		} else {
			fail("Failed validated CO2e value" + " " + newPlainValue + "--" + m);
		}
		WebElement weNameOfCEF = driver.findElement(
				By.xpath("//span[contains(text(),'Name of Custom EF')]//parent::div//following-sibling::div/div"));
		WebElement weSourceOfCEF = driver.findElement(By.xpath(
				"//span[contains(text(),'Source of Emission Factor')]//parent::div//following-sibling::div/div"));
		if (weNameOfCEF.getText().equals(customEF)) {
			passed("successfully validated name of custom emission factor");
		} else {
			fail("failed to validate name of custom emission factor");
		}
		if (weSourceOfCEF.getText().equals(souceCEF)) {
			passed("successfully validated source of custom emission factor");
		} else {
			fail("failed to validate source of custom emission factor");
		}
	}

	public void ValidateCEFDetailsInViewCEF() {
		try {
			String[] activityDetailFieldNames = { "Energy Category", "Unit of Custom EF (Denominator)", "CO2", "CH4",
					"N2O", "Unit of Custom EF (Numerator)" };
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

	public void ValidateCEFDetailsInViewCEFEdit() {
		try {
			WebElement co2value = driver
					.findElement(By.xpath("//span[text()='CO2']//parent::div//following-sibling::div"));
			WebElement ch4value = driver
					.findElement(By.xpath("//span[text()='CH4']//parent::div//following-sibling::div"));
			WebElement n2ovalue = driver
					.findElement(By.xpath("//span[text()='N2O']//parent::div//following-sibling::div"));
			if (co2value.getText().equals(data.get("CO2Edit"))) {
				passed("Successfully Validated co2 In Activity Details As" + co2value.getText() + "and actual is"
						+ data.get("CO2Edit"));
			} else {
				failed(driver, "Failed to validated co2 In Activity Details As" + co2value.getText() + "and actual is"
						+ data.get("CO2Edit"));
			}
			if (ch4value.getText().equals(data.get("CH4Edit"))) {
				passed("Successfully Validated co2 In Activity Details As" + ch4value.getText() + "and actual is"
						+ data.get("CH4Edit"));
			} else {
				failed(driver, "Failed to validated co2 In Activity Details As" + ch4value.getText() + "and actual is"
						+ data.get("CH4Edit"));
			}
			if (n2ovalue.getText().equals(data.get("N2OEdit"))) {
				passed("Successfully Validated co2 In Activity Details As" + n2ovalue.getText() + "and actual is"
						+ data.get("N2OEdit"));
			} else {
				failed(driver, "Failed to validated co2 In Activity Details As" + n2ovalue.getText() + "and actual is"
						+ data.get("N2OEdit"));
			}
			String[] activityDetailFieldNames = { "Energy Category", "Unit of Custom EF (Denominator)",
					"Unit of Custom EF (Numerator)" };
			Thread.sleep(1000);
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
		WebElement valuetCO2 = driver
				.findElement(By.xpath("(//span[contains(text(),'tCO2')]//parent::p//following-sibling::p)[1]"));
		Double CO2 = Double.parseDouble(data.get("CO2")) * Double.parseDouble(data.get("Activity Amount")) / 1000;
		String tCO2Activity = CO2.toString();
		String tCO2ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCO2Activity);
		if (tCO2ActivityRHP.equals(valuetCO2.getText())) {
			passed("Succesfully validated tCO2 value" + tCO2ActivityRHP + " And Actual is " + valuetCO2.getText());
		} else {
			fail("Failed validated tCO2 value" + " " + tCO2ActivityRHP + " But Actual is " + valuetCO2.getText());
		}
		WebElement valueCH4 = driver
				.findElement(By.xpath("(//span[contains(text(),'tCH4')]//parent::p//following-sibling::p)[1]"));
		Double CH4 = Double.parseDouble(data.get("CH4")) * Double.parseDouble(data.get("Activity Amount")) / 1000;
		String CH4Activity = CH4.toString();
		String CH4ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(CH4Activity);
		if (CH4ActivityRHP.equals(valueCH4.getText())) {
			passed("Succesfully validated CH4 value" + CH4ActivityRHP + " And Actual is " + valueCH4.getText());
		} else {
			fail("Failed validated CH4 value" + " " + CH4ActivityRHP + " But Actual is " + valueCH4.getText());
		}
		WebElement valueN2O = driver
				.findElement(By.xpath("(//span[contains(text(),'tN2O')]//parent::p//following-sibling::p)[1]"));
		Double N2O = Double.parseDouble(data.get("N2O")) * Double.parseDouble(data.get("Activity Amount")) / 1000;
		String N2OActivity = N2O.toString();
		String N2OActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(N2OActivity);
		if (N2OActivityRHP.equals(valueN2O.getText())) {
			passed("Succesfully validated N2O value" + N2OActivityRHP + " And Actual is " + valueN2O.getText());
		} else {
			fail("Failed validated N2O value" + " " + N2OActivityRHP + " But Actual is " + valueN2O.getText());
		}
		WebElement tCO2eActivity = driver
				.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
		Double ValCO2e = valueCo2e * Double.parseDouble(data.get("Activity Amount")) / 1000;
		String ValCO2eRHP = ValCO2e.toString();
		if (tCO2eActivity.getText().equals(GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(ValCO2eRHP))) {
			passed("Succesfully validated tCO2e value" + ValCO2eRHP + " And Actual is " + tCO2eActivity.getText());
		} else {
			fail("Failed validated tCO2e value" + " " + ValCO2eRHP + " But Actual is " + tCO2eActivity.getText());
		}
		WebElement EFActivity = driver.findElement(
				By.xpath("(//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p)[2]"));
		String s2 = EFActivity.getText().trim();
		String[] EFValueRHP = s2.split(" ");
		if (EFValueRHP[0].equals(GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(v))) {
			passed("Succesfully validated Emission Factor Expected value" + " " + EFValueRHP[0] + " And Actual is "
					+ valueCo2e);
		} else {
			fail("Failed validated Emission Factor Expected value" + " " + EFValueRHP[0] + " But Actual is "
					+ valueCo2e);
		}
	}

	public void ValidateRHPCEFValueswithActivitiesRHPEdit() {
		WebElement valuetCO2 = driver
				.findElement(By.xpath("(//span[contains(text(),'tCO2')]//parent::p//following-sibling::p)[1]"));
		Double CO2 = Double.parseDouble(data.get("CO2Edit")) * Double.parseDouble(data.get("Activity Amount")) / 1000;
		String tCO2Activity = CO2.toString();
		String tCO2ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCO2Activity);
		if (tCO2ActivityRHP.equals(valuetCO2.getText())) {
			passed("Succesfully validated tCO2 value" + tCO2ActivityRHP + " And Actual is " + valuetCO2.getText());
		} else {
			fail("Failed validated tCO2 value" + " " + tCO2ActivityRHP + " But Actual is " + valuetCO2.getText());
		}
		WebElement valueCH4 = driver
				.findElement(By.xpath("(//span[contains(text(),'tCH4')]//parent::p//following-sibling::p)[1]"));
		Double CH4 = Double.parseDouble(data.get("CH4Edit")) * Double.parseDouble(data.get("Activity Amount")) / 1000;
		String CH4Activity = CH4.toString();
		String CH4ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(CH4Activity);
		if (CH4ActivityRHP.equals(valueCH4.getText())) {
			passed("Succesfully validated CH4 value" + CH4ActivityRHP + " And Actual is " + valueCH4.getText());
		} else {
			fail("Failed validated CH4 value" + " " + CH4ActivityRHP + " But Actual is " + valueCH4.getText());
		}
		WebElement valueN2O = driver
				.findElement(By.xpath("(//span[contains(text(),'tN2O')]//parent::p//following-sibling::p)[1]"));
		Double N2O = Double.parseDouble(data.get("N2OEdit")) * Double.parseDouble(data.get("Activity Amount")) / 1000;
		String N2OActivity = N2O.toString();
		String N2OActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(N2OActivity);
		if (N2OActivityRHP.equals(valueN2O.getText())) {
			passed("Succesfully validated N2O value" + N2OActivityRHP + " And Actual is " + valueN2O.getText());
		} else {
			fail("Failed validated N2O value" + " " + N2OActivityRHP + " But Actual is " + valueN2O.getText());
		}
		WebElement tCO2eActivity = driver
				.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
		Double ValCO2e = valueCo2e * Double.parseDouble(data.get("Activity Amount")) / 1000;
		String ValCO2eRHP = ValCO2e.toString();
		if (tCO2eActivity.getText().equals(GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(ValCO2eRHP))) {
			passed("Succesfully validated tCO2e value" + ValCO2eRHP + " And Actual is " + tCO2eActivity.getText());
		} else {
			fail("Failed validated tCO2e value" + " " + ValCO2eRHP + " But Actual is " + tCO2eActivity.getText());
		}
		WebElement EFActivity = driver.findElement(
				By.xpath("(//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p)[2]"));
		String s2 = EFActivity.getText().trim();
		String[] EFValueRHP = s2.split(" ");
		if (EFValueRHP[0].equals(GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(m))) {
			passed("Succesfully validated Emission Factor Expected value" + " " + EFValueRHP[0] + " And Actual is "
					+ valueCo2e);
		} else {
			fail("Failed validated Emission Factor Expected value" + " " + EFValueRHP[0] + " But Actual is "
					+ valueCo2e);
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

	public void validateTOTALCO2EforFranchises() {
		try {
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Franchisee");
			/*
			 * WebElement ee = driver .findElement(By.xpath(
			 * "//span[contains(text(),'Conversion']//parent::p//following-sibling::p"));
			 * String hh = ee.getText(); String[] yy = hh.split("="); String[]
			 * t=yy[1].split(" "); Double d = Double.parseDouble(t[1]);
			 */
			WebElement emissionFactor5 = driver.findElement(
					By.xpath("(//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p)[2]"));
			String[] splitEF5 = emissionFactor5.getText().replaceAll(",", "").split(" ");
			Double result5 = (Double.parseDouble(splitEF5[0]) * Double.parseDouble(data.get("Activity Amount"))) / 1000;
			String res5 = result5.toString();
			String CalCo2e5 = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(res5);
			WebElement valuetCO2e5 = driver
					.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
			if (CalCo2e5.trim().equals(valuetCO2e5.getText().trim())) {
				passed("Successfully validated tCO2e value for : " + " Franchisee " + valuetCO2e5.getText());
			} else {
				failed(driver, "Failed to valiadte tCO2e value for : " + " Franchisee " + valuetCO2e5.getText()+"but actual is "+CalCo2e5);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
}
