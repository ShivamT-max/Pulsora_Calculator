package pages.calculators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import pages.GHGCalculatorsPage;
import uielements.CalculatorElements;
import utilities.Constants;
import utilities.Data;
import utilities.GlobalVariables;

public class WasteGeneratedInOperationsCalculatorPage extends CalculatorElements {
	public WasteGeneratedInOperationsCalculatorPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	String sourceOfEmissionFactor;
	@FindBy(xpath = "//ul//li[text()='Sabancci Tag1']//*[@data-testid='CheckBoxOutlineBlankIcon']")
	private WebElement drptags;
	@FindBy(xpath = "//input[@id='tag_id']//parent::div//*[@data-testid='ArrowDropDownIcon']")
	private WebElement dptag;

	public void addActivityDetailsForWasteGeneratedInOperationsInActivityDetailsPannel() {
		try {
			Actions act = new Actions(driver);
			waitForElement(drpFacilityName);
			clickOn(drpFacilityName, "txtFacilityName");
//			enterText(drpFacilityName, "Facility Name", data.get("Facility Name"));
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Facility Name") + "']"));
			clickOn(we, data.get("Facility Name"));
			sleep(2000);
			// clickOn(txtDescription, " Description");
			// enterText(txtDescription, "Description", data.get("Description"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			clickOn(drpWasteCategory, "WasteCategory");
			WebElement wef = driver.findElement(By.xpath("//li[text()='" + data.get("Waste Category") + "']"));
			clickOn(wef, data.get("Waste Category"));
			sleep(2000);
			clickOn(drpWasteType, "Waste Type");
			WebElement eee = driver.findElement(By.xpath("//li[text()='" + data.get("Waste Type") + "']"));
			clickOn(eee, data.get("Waste Type"));
			sleep(3000);
			clickOn(drpWasteDisposalMethod, "WasteDisposalMethod");
			WebElement rrr = driver.findElement(By.xpath("//li[text()='" + data.get("Waste Disposal Method") + "']"));
			clickOn(rrr, data.get("Waste Disposal Method"));
			sleep(2000);
			clickOn(drpUnits, "Units");
			WebElement wee = driver.findElement(By.xpath("//li[text()='" + data.get("Units") + "']"));
			clickOn(wee, data.get("Units"));
			sleep(2000);
			enterText(txtMassOfWasteProduced, "MassOfWasteProduced", data.get("Mass of Waste Produced"));
			try {
			if (!data.get("Edit").equals("YES")) {
				clickOn(dptag, "Tags");
				WebElement selecttag = driver.findElement(By.xpath("(//ul[@aria-labelledby='tag_id-label']//li)[1]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", selecttag);
				act.moveToElement(selecttag).click().perform(); // clickOn(selecttag, "Tags");
			}
			}
			catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
			sleep(2);
			clickOn(btnSave, "Save Button");
			sleep(1000);
			verifyAddActivityUpdatedToastMessage();
			sleep(2000);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
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

	String nameOfCEF;


	public void addCEFForWasteGeneratedinOperationsEdit() {
		try {
			sleep(3000);
			clickOn(btnAddCEF, "Custom Emission Factor");
			verifyIfElementPresent(lblAddCEF, "CEF", "CEF");
			nameOfCEF = Constants.customEmissionFactor + generateRandomNumber(3);
			enterText(txtNameCEFForFuel, "name of cusytom emission factor", nameOfCEF);
			clickOn(drpWasteCategoryCEF, "energy category");
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Waste Category") + "']"));
			clickOn(we, "Waste category" + data.get("Waste Category"));
			sourceOfEmissionFactor = "SRCEF" + generateRandomNumber(3);
			enterText(txtSourceOfEmissionFactorFuel, "Source of Emission Factor", sourceOfEmissionFactor);
			clickOn(drpWasteTypeCEF, "Waste Type");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Waste Type") + "']"));
			clickOn(we, data.get("Waste Type"));
			clickOn(drpWasteDisposalMethodCEF, "waste disposal method");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Waste Disposal Method") + "']"));
			clickOn(we, data.get("Waste Disposal Method"));
			enterText(txtco2e, "CO2e", data.get("CO2eEdit"));
			sleep(2000);
			clickOn(drpUnitOfCustomEFDenominator, "Unit of Custom EF Denominator");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Denominator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Denominator)"));
			clickOn(drpUnitofCustomEFNumeratorCEF, "Unit of Custom EF Numerator");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Numerator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Numerator)"));
			clickOn(btnSaveParemeterInput, "Save Custom Emission factor");
			sleep(2000);
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void clickOnCEFInCEFGridMultiple() {
		sleep(3000);
		WebElement weActivity = driver.findElement(By.xpath("//*[text()='" + nameOfCEF + "']//parent::div"));
		clickOn(weActivity, "Activity of Facility" + nameOfCEF);
	}

	public void clickWasteTypeSpecificMethod() {
		waitForElement(btnWasteTypeSpecificMethod);
		clickOn(btnWasteTypeSpecificMethod, "Waste specific method");
	}

	public void EditActivityScope3_5EmissionsCEf() {
		try {
			sleep(3000);
			Actions act=new Actions(driver);
			waitForElement(drpFacilityName);
			clickOn(drpFacilityName, "txtFacilityName");
			// enterText(drpFacilityName, "Facility Name", data.get("Facility Name"));
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Facility Name") + "']"));
			clickOn(we, data.get("Facility Name"));
			sleep(2000);
			// clickOn(txtDescription, " Description");
			// enterText(txtDescription, "Description", data.get("Description"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			validateCEF();
			/*
			 * if (isElementPresent(drpWasteCategory)) { failed(driver,
			 * "Waste category element is enable"); } else {
			 * passed("Waste category element is disable"); } clickOn(drpWasteCategory,
			 * "WasteCategory"); we = driver.findElement(By.xpath("//li[text()='" +
			 * data.get("Waste Category") + "']")); clickOn(we, data.get("Waste Category"));
			 * sleep(2000); clickOn(drpWasteType, "Waste Type"); we =
			 * driver.findElement(By.xpath("//li[text()='" + data.get("Waste Type") +
			 * "']")); clickOn(we, data.get("Waste Type")); sleep(2000);
			 * clickOn(drpWasteDisposalMethod, "WasteDisposalMethod"); we =
			 * driver.findElement(By.xpath("//li[text()='" +
			 * data.get("Waste Disposal Method") + "']")); clickOn(we,
			 * data.get("WasteDisposalMethod")); sleep(2000); clickOn(drpUnits, "Units"); we
			 * = driver.findElement(By.xpath("//li[text()='" + data.get("Units") + "']"));
			 * clickOn(we, data.get("Units")); sleep(2000);
			 */
			enterText(txtMassOfWasteProduced, "MassOfWasteProduced", data.get("Mass of Waste Produced"));
			try {
				if (!data.get("Edit").equals("YES")) {
					clickOn(dptag, "Tags");
					WebElement selecttag = driver.findElement(By.xpath("(//ul[@aria-labelledby='tag_id-label']//li)[1]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", selecttag);
					act.moveToElement(selecttag).click().perform(); // clickOn(selecttag, "Tags");
				}
				}
				catch (Exception e) {
					failed(driver, "Exception caught " + e.getMessage());
				}
			clickOn(btnSave, "Save Button");
			sleep(10000);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateCEFScope3_5() {
		try {
			sleep(4000);
			WebElement cstomEmission = driver.findElement(
					By.xpath("//span[contains(text(),'Custom Emission Factor')]//parent::p//following-sibling::p"));
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
				passed("successfully validated Soucre in RHP as actual is" + cstomEmissionSource.getText() + " "
						+ "and Expected is" + " " + sourceOfEmissionFactor);
			} else {
				fail("failed to validate Source in RHP as actual is" + " " + cstomEmissionSource.getText() + " "
						+ "and Expected is" + " " + sourceOfEmissionFactor);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void validateCEF() {
		sleep(1000);
		jsClick(chbxCustomEmssionFactorFuel, "yes");
		verifyIfElementPresent(drpCustomEmiFactorActivityFuel, "--", "--");
		clickOn(drpCustomEmiFactorActivityFuel, "-");
		WebElement namecef = driver.findElement(By.xpath("//li[text()='" + nameOfCEF + "']"));
		JavascriptExecutor jsExc = (JavascriptExecutor) driver;
		jsExc.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow');", namecef);
		sleep(1000);
		String drpCEF = "//li[text()='" + nameOfCEF + "']";
		clickOnElementWithDynamicXpath(drpCEF, nameOfCEF);
		sleep(1000);
		WebElement ter = driver.findElement(By.xpath("//input[@id='emission']"));
		String ewq = ter.getAttribute("value");
		if (nameOfCEF.equals(ewq)) {
			passed("Successfully validated custom emission factor name in avtivities for waste generated");
		} else {
			failed(driver, "failed to validated custom emission factor name in avtivities for waste generated");
		}

	}

	public void validateAddActivityDetailsInViewActivityForWasteGeneratedOprtionsCalculator() {
		try {
			takeScreenshot(driver);
			String[] activityDetailFieldNames = { "Facility Name", "Start Date", "End Date", "Custom Emission Factor",
					"Waste Category", "Waste Type", "Waste Disposal Method", "Mass of Waste Produced", "Units",
					"Emission Factor", "Source" };
			verifyIfElementPresent(lblViewActivity, "lblActivityDetails", "lblActivityDetails");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				WebElement weActivityField = driver.findElement(By.xpath(
						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::p//following-sibling::p"));
				if (weActivityField.getText().trim().equals(data.get(activityDetailFieldNames[j]))) {
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
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateActivityDetailsInViewActivityForWasteGeneratedOprtionsCalculator() {
		try {
			takeScreenshot(driver);
			String[] activityDetailFieldNames = { "Facility Name", "Waste Category", "Waste Type",
					"Waste Disposal Method", "Mass of Waste Produced", "Units" };
			verifyIfElementPresent(lblViewActivity, "lblActivityDetails", "lblActivityDetails");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				WebElement weActivityField = driver.findElement(By.xpath(
						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::p//following-sibling::p"));
				if (weActivityField.getText().trim().equals(data.get(activityDetailFieldNames[j]))) {
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
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void extractTco2Value() {
		sleep(2000);
		WebElement tco2Filed = driver
				.findElement(By.xpath("//span[text()='tCO2e']//parent::div//following-sibling::div"));
		String prevoiusTc02 = tco2Filed.getText();
		GlobalVariables.tco2e = Double.parseDouble(prevoiusTc02);
		System.out.println(GlobalVariables.tco2e);
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
			String[] activityDetailFieldNames = { "Waste Category", "Waste Disposal Method", "Waste Type",
					"Unit of Custom EF (Denominator)", "Unit of Custom EF (Numerator)", "CO2e" };
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
			WebElement weNameOfCEF = driver.findElement(By.xpath(
					"//span[contains(text(),'Name of Custom Emission Factor')]//parent::div//following-sibling::div"));
			WebElement weSourceOfCEF = driver
					.findElement(By.xpath("//span[contains(text(),'Source')]//parent::div//following-sibling::div"));
			WebElement co2evalue = driver
					.findElement(By.xpath("//span[contains(text(),'CO2e')]//parent::div//following-sibling::div"));
			if (co2evalue.getText().equals(data.get("CO2eEdit"))) {
				passed("successfully validated co2e value as expected" + data.get("CO2eEdit") + "and actual is"
						+ co2evalue.getText());
			} else {
				fail("failed to validated co2e value as expected" + data.get("CO2eEdit") + "and actual is"
						+ co2evalue.getText());
			}
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
			String[] activityDetailFieldNames = { "Waste Category", "Waste Disposal Method", "Waste Type",
					"Unit of Custom EF (Denominator)", "Unit of Custom EF (Numerator)" };
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
		WebElement valuetCO2 = driver
				.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
		Double CO2 = Double.parseDouble(data.get("CO2e")) * Double.parseDouble(data.get("Mass of Waste Produced"))
				/ 1000;
		String tCO2Activity = CO2.toString();
		String tCO2ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCO2Activity);
		if (tCO2ActivityRHP.equals(valuetCO2.getText())) {
			passed("Succesfully validated tCO2 value" + tCO2ActivityRHP + " And Actual is " + valuetCO2.getText());
		} else {
			fail("Failed validated tCO2 value" + " " + tCO2ActivityRHP + " But Actual is " + valuetCO2.getText());
		}
		WebElement EFActivity = driver.findElement(
				By.xpath("(//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p)[2]"));
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
		Double CO2 = Double.parseDouble(data.get("CO2eEdit")) * Double.parseDouble(data.get("Mass of Waste Produced"))
				/ 1000;
		String tCO2Activity = CO2.toString();
		String tCO2ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCO2Activity);
		if (tCO2ActivityRHP.equals(valuetCO2.getText())) {
			passed("Succesfully validated tCO2 value" + tCO2ActivityRHP + " And Actual is " + valuetCO2.getText());
		} else {
			fail("Failed validated tCO2 value" + " " + tCO2ActivityRHP + " But Actual is " + valuetCO2.getText());
		}
		WebElement EFActivity = driver.findElement(
				By.xpath("(//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p)[2]"));
		String s2 = EFActivity.getText().trim();
		String[] EFValueRHP = s2.split(" ");
		if (EFValueRHP[0].equals(data.get("CO2eEdit"))) {
			passed("Succesfully validated Emission factor Expected value" + " " + data.get("CO2eEdit")
					+ " And Actual is " + EFValueRHP[0]);
		} else {
			fail("Failed validated Emission Factor Expected value" + " " + data.get("CO2eEdit") + " But Actual is "
					+ EFValueRHP[0]);
		}
	}

	public void validateCreatedTransactionBillType() {

	}

	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(lblWasteGeneratedInOps);
			if (isElementPresent(lblWasteGeneratedInOps)) {
				passed("User Successfully Navigated To WasteGeneratedInOps Page");
			} else {
				failed(driver, "Failed To Navigate To WasteGeneratedInOps Page");
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateTOTALCO2EforWastegenerated() {
		try {
			String res2="";
			String	calTco2e7="";
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Waste Generated in Operations");
			WebElement emissionfactor = driver.findElement(
					By.xpath("(//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p)[2]"));
			String[] splitEF4 = emissionfactor.getText().split(" ");
			if (splitEF4[0].trim().equals(data.get("Emission Factor"))) {
				passed("Successfully validated EmissionFactor as " + data.get("Emission Factor"));
			} else {
				failed(driver, "Failed to validate EmissionFactor as " + data.get("Emission Factor") + " but Actual is "
						+ splitEF4[0].trim());
			}
			WebElement ee = driver.findElement(By
					.xpath("//p[contains(text(),'Conversion')]//parent::div//parent::div//following-sibling::div/div"));
			String hh = ee.getText().replaceAll(",", "");
			String[] yy = hh.split("=");
			String[] t = yy[1].split(" ");
			Double d = Double.parseDouble(t[1]);
			String s = data.get("Mass of Waste Produced").replaceAll(",", "");
			if (splitEF4[1].trim().equals(Constants.conToTonnfrlb)) {
				Double result7 = (Double.parseDouble(splitEF4[0]) * Double.parseDouble(s) * d) / 2204.623;
				calTco2e7 = result7.toString();
			} else if (splitEF4[1].trim().equals(Constants.conToTonnfrKg)) {
				Double result7 = (Double.parseDouble(splitEF4[0]) * Double.parseDouble(s) * d) / 1000;
				calTco2e7 = result7.toString();
			} else if (splitEF4[1].trim().equals(Constants.conToTonnfrg)) {
				Double result7 = (Double.parseDouble(splitEF4[0]) * Double.parseDouble(s) * d) / 1000000;
				calTco2e7 = result7.toString();
			}
			else if(splitEF4[1].trim().equals(Constants.conToTonnfrTonn)) {
				Double result7 = Double.parseDouble(splitEF4[0]) * Double.parseDouble(s) * d;
				calTco2e7 = result7.toString();
			}
			WebElement emissionFactor2 = driver.findElement(
					By.xpath("(//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p)[2]"));
			String[] splitEF2 = emissionFactor2.getText().split(" ");
			String CalCo2e2 = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(calTco2e7);
			WebElement valuetCO2e2 = driver
					.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
			GlobalVariables.exceptedtco2e = valuetCO2e2.getText().trim();
			if (CalCo2e2.trim().equals(valuetCO2e2.getText().trim())) {
				passed("Successfully validated tCO2e value for : " + " Waste Generated in Operations "
						+ valuetCO2e2.getText().replaceAll(",", "") + "as actual " + CalCo2e2);
			} else {
				failed(driver, "Failed to valiadte tCO2e value for : " + " Waste Generated in Operations "
						+ valuetCO2e2.getText().replaceAll(",", "") + " But Actual is " + CalCo2e2);
			}
		} catch (Exception e) {

			failed(driver, "Exception caught " + e.getMessage());

		}
	}

	public void selectOrganizationFromViewOrghierarchy(String organizationName) {
		try {
			verifyIfElementPresent(verifyOrgViewScreen, "View Org Screen", "directEmissionPage");
			clickOnfilterOrgLabels(data.get("navigationType"));
			WebElement txtsearchingForOrganization = driver.findElement(By.xpath("//input[@placeholder='Search']"));
			verifyIfElementPresent(txtsearchingForOrganization, "Search tab for Organization", "GHGCalculator");
			clickOn(txtsearchingForOrganization, "Search tab for Organization");
			enterText(txtsearchingForOrganization, "Search tab for Organization", organizationName);
			WebElement optOrganizationName = driver.findElement(By.xpath("//div[text()='" + organizationName + "']"));
			waitForElement(optOrganizationName);
			clickOn(optOrganizationName, "Clicked on " + organizationName);
			sleep(3000);
		} catch (Exception e) {
			failed(driver, "Exceptio caught " + e.getMessage());
		}
	}

	public void clickOnfilterOrgLabels(String navigationType) {
		try {
			WebElement orgHierarchyTyep = driver
					.findElement(By.xpath("//span[text()='" + navigationType + "']/parent::div"));
			if (orgHierarchyTyep.getAttribute("class").contains("false")) {
				WebElement orgorgHierarchyTyep1 = driver
						.findElement(By.xpath("//span[text()='" + navigationType + "']"));
				clickOn(orgorgHierarchyTyep1, "Clicked on " + navigationType);
			}
		} catch (Exception e) {
			failed(driver, "Exceptio caught " + e.getMessage());
		}
	}

	public void clickOnactivityinGridMultiple() {
		try {
			Actions action = new Actions(driver);
			sleep(1000);
			WebElement orgFacility = driver.findElement(By
					.xpath("(//*[@col-id='facility_name']//span[@aria-label='" + data.get("Facility Name") + "'])[1]"));
			waitForElement(orgFacility);
			verifyElementAndHighlightIfExists(orgFacility, "Facility Name", "IndirectEmissionsCalculatorPage");
			action.moveToElement(orgFacility).click().perform();
			sleep(2000);
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public boolean verifyElementAndHighlightIfExists(WebElement element, String elemName, String pageName) {
		boolean blnFnd = false;
		try {
			if (isElementPresent(element)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
				passed(elemName + "Element is displayed in " + pageName + " page");
				blnFnd = true;
			} else {
				failed(driver, elemName + "Element is not displayed in " + pageName + " page");
			}
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
		return blnFnd;
	}

	public void ValidateActivityDetailsInViewActivityAddedBillsScope3WasteCategory() {
		try {
			System.out.println("----------- Emissions Details are--------------");
			String[] activityDetailFieldNames = { "Facility Name", "Start Date", "End Date", "Waste Category",
					"Waste Type", "Waste Disposal Method", "Mass of Waste Produced", "Units", "tCO2", "tCH4", "tN2O",
					"tCO2e", "Emission Factor", "Source" };
			Thread.sleep(5000);
			verifyIfElementPresent(lblActivityDetails, "lblViewActivityFulEngy", "direct emission");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				WebElement weActivityField = driver.findElement(By.xpath(
						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::p//following-sibling::p//div"));
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
}