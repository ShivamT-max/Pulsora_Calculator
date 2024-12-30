package pages.calculators;

import java.io.File;
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

public class FuelandEnergyRelatedActivitiesCalculatorPage extends CalculatorElements {
	public FuelandEnergyRelatedActivitiesCalculatorPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	String nameOfCEF;
	String sourceOfEmissionFactor;

	public void addCEFForFuelandEnergyRelatedActivities() {
		try {
			sleep(2000);
			clickOn(btnAddCEF, "Custom Emission Factor");
			verifyIfElementPresent(lblAddCEF, "CEF", "CEF");
			nameOfCEF = Constants.customEmissionFactor + generateRandomNumber(3);
			enterText(txtNameCEFForFuel, "name of cusytom emission factor", nameOfCEF);
			clickOn(drpEnergyTypeFuel, "energy category");
			sleep(1000);
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Energy Type") + "']"));
			clickOn(we, "energy category" + data.get("Energy Type"));
			sourceOfEmissionFactor = "SRCEF" + generateRandomNumber(3);
			enterText(txtSourceOfEmissionFactorFuel, "Source of Emission Factor", sourceOfEmissionFactor);
			clickOn(drpUnitCEFFuel, "Unit of Custom EF");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Denominator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Denominator)"));
			enterText(txtco2e, "CO2e", data.get("CO2e"));
			sleep(2000);
			clickOn(drpUnitEmmsinFuel, "Unit of Custom EF ");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Numerator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Numerator)"));
			clickOn(btnSaveParemeterInput, "Save Custom Emission factor");
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void addCEFForFuelandEnergyRelatedActivitiesEdit() {
		try {
			Actions mouseHandling = new Actions(driver);
			sleep(2000);
			verifyIfElementPresent(lblEditCEF, "CEF", "CEF");
			nameOfCEF = Constants.customEmissionFactor + generateRandomNumber(3);
			mouseHandling.doubleClick(txtNameCEFForFuel).perform();
			enterText(txtNameCEFForFuel, "name of cusytom emission factor", nameOfCEF);
			clickOn(drpEnergyTypeFuel, "energy category");
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Energy Type1") + "']"));
			clickOn(we, "energy category" + data.get("Energy Type"));
			sourceOfEmissionFactor = "SRCEF" + generateRandomNumber(3);
			mouseHandling.doubleClick(txtSourceOfEmissionFactorFuel).perform();
			enterText(txtSourceOfEmissionFactorFuel, "Source of Emission Factor", sourceOfEmissionFactor);
			clickOn(drpUnitCEFFuel, "Unit of Custom EF");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Denominator)1") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Denominator)"));
			enterText(txtco2e, "CO2e", data.get("CO2e1"));
			sleep(2000);
			clickOn(drpUnitEmmsinFuel, "Unit of Custom EF ");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Numerator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Numerator)"));
			clickOn(btnSaveParemeterInput, "Save Custom Emission factor");
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void clickOnCEFInCEFGridMultiple() {
		sleep(3000);
		WebElement weActivity = driver.findElement(By.xpath("//*[text()='" + nameOfCEF + "']//parent::div"));
		clickOn(weActivity, "Activity of Facility" + nameOfCEF);
	}

	public void clickFuelAndEnergyEnergy() {
		clickOn(btnavergaeDataMethod, "avergare data method");
	}

	@FindBy(xpath = "//ul//li[text()='Sabancci Tag1']//*[@data-testid='CheckBoxOutlineBlankIcon']")
	private WebElement drptags;
	@FindBy(xpath = "//input[@id='tag_id']//parent::div//*[@data-testid='ArrowDropDownIcon']")
	private WebElement dptag;

	public void EditActivityScope3_3Emissions() {
		Actions act = new Actions(driver);
		try {
			verifyIfElementPresent(lblAddActivityFuelEnrgy, "lblAddActivityFuelEnrgy", "lblAddActivityFuelEnrgy");
			Thread.sleep(2000);
			clickOn(txtFacilityNameScope3_3, "txtFacilityNameScope3_3");
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Facility Name") + "']"));
			clickOn(we, data.get("Facility Name"));
			sleep(200);
			clickOn(txtStartDate, "Start Date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			clickOn(drpEnerfgyType, "Energy type DropDown");
			WebElement weEnergyType = driver.findElement(By.xpath("//li[text()='" + data.get("Energy Type") + "']"));
			clickOn(weEnergyType, data.get("Energy Type"));
			if (data.get("Energy Type").equals("Electricity") || data.get("Energy Type").equals("Heat and Steam")) {
				clickOn(txtScope3_3FuelEnrgyUnit, "Unit");
				weUnit = driver.findElement(By.xpath("//li[text()='" + data.get("Units") + "']"));
				clickOn(weUnit, data.get("Units"));
				sleep(2000);
				act.doubleClick(txtQuantityConsumedFuelEngy).perform();
				enterText(txtQuantityConsumedFuelEngy, "Quantity of fuel Consumed", data.get("Quantity Consumed"));
				if (data.get("Env").equals("eu.prod") || data.get("Env").equals("eu.uat")
						|| (data.get("Env").equals("qa") && data.get("UserName").equals("tiffanyAut_Ent"))) {
					System.out.println("No tag present");
				} else {
					if (!data.get("Edit").equals("YES")) {
						clickOn(dptag, "Tags");
						WebElement selecttag = driver
								.findElement(By.xpath("(//ul[@aria-labelledby='tag_id-label']//li)[1]"));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", selecttag);
						act.moveToElement(selecttag).click().perform(); // clickOn(selecttag, "Tags");
						act.sendKeys(Keys.ESCAPE).perform();
					}
				}
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
				System.out.println("-------Validate details---------");
			}
			else {
				clickOn(drpFuelType, "Fuel Type DropDown");
				WebElement weFuelType = driver.findElement(By.xpath("//li[text()='" + data.get("Fuel Type") + "']"));
				clickOn(weFuelType, data.get("Fuel Type"));
				sleep(2000);
				clickOn(drpFuelName, "Fuel Name DropDown");
				WebElement weFuelName = driver.findElement(By.xpath("//li[text()='" + data.get("Fuel Name") + "']"));
				jsClick(weFuelName, data.get("Fuel Name"));
				System.out.println(data.get("Fuel Name"));
				clickOn(txtScope3_3FuelEnrgyUnit, "Units");
				weUnit = driver.findElement(By.xpath("//li[text()='" + data.get("Units") + "']"));
				clickOn(weUnit, data.get("Units"));
				sleep(2000);
				act.doubleClick(txtQuantityConsumedFuelEngy).perform();
				enterText(txtQuantityConsumedFuelEngy, "Quantity of fuel Consumed", data.get("Quantity Consumed"));
				sleep(2000);
				if (data.get("Env").equals("eu.prod") || data.get("Env").equals("eu.uat")
						|| (data.get("Env").equals("qa") && data.get("UserName").equals("tiffanyAut_Ent"))) {
					System.out.println("No tag present");
				} else {
					if (!data.get("Edit").equals("YES")) {
						try {
							if (dptag.isDisplayed()) {
								clickOn(dptag, "Tags");
								WebElement selecttag = driver
										.findElement(By.xpath("(//ul[@aria-labelledby='tag_id-label']//li)[1]"));
								((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
										selecttag);
								act.moveToElement(selecttag).click().perform(); //
								act.sendKeys(Keys.ESCAPE).perform();
							} else {
							}

						} catch (Exception e) {
							failed(driver, "Exception caught " + e.getMessage());
						}
					}
				}
					waitForElement(btnSave);
					act.moveToElement(btnSave).doubleClick().perform();
					verifyAddActivityUpdatedToastMessage();
					System.out.println("-------Validate details---------");
				}
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
			By toastEdit = By.xpath("//*[contains(text(),'Activity updated successfully')]");
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

	public void EditActivityScope3_3EmissionsCEF() {
		try {
			sleep(2000);
			verifyIfElementPresent(lblAddActivityFuelEnrgy, "lblAddActivityFuelEnrgy", "lblAddActivityFuelEnrgy");
			Thread.sleep(5000);
			clickOn(txtFacilityNameScope3_3, "txtFacilityNameScope3_3");
			sleep(2000);
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Facility Name") + "']"));
			clickOn(we, data.get("Facility Name"));
			sleep(2000);
			Actions mouseActions = new Actions(driver);
			// mouseActions.doubleClick(txtDescrScope3_3).doubleClick().perform();
			mouseActions.doubleClick(txtDescrScope3_3Qa).doubleClick().perform();
			// enterText(txtDescrScope3_3, "Description Scope3", data.get("description"));
			enterText(txtDescrScope3_3Qa, "Description Scope3", data.get("Description"));
			clickOn(txtStartDate, "Start Date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			validateCEF();
			WebElement drpEnergyType = driver.findElement(By.xpath("//input[@id='type_name']"));
			if (isElementPresent(drpEnergyType)) {
				failed(driver, "Energy type element is enable");
			} else {
				passed("Energy type element is disable");
			}
			clickOn(txtScope3_3FuelEnrgyUnit, "Units");
			weUnit = driver.findElement(By.xpath("//li[text()='" + data.get("Units") + "']"));
			clickOn(weUnit, data.get("Units"));
			sleep(2000);
			mouseActions.doubleClick(txtQuantityConsumedFuelEngy).perform();
			enterText(txtQuantityConsumedFuelEngy, "Quantity of fuel Consumed", data.get("Quantity Consumed"));
			sleep(1);
			if (!data.get("Edit").equals("YES")) {
				try {
				if (dptag.isDisplayed()) {
					clickOn(dptag, "Tags");
					WebElement selecttag = driver
							.findElement(By.xpath("(//ul[@aria-labelledby='tag_id-label']//li)[1]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", selecttag);
					mouseActions.moveToElement(selecttag).click().perform(); //
				} else {
				}
			}
				catch (Exception e) {
					failed(driver, "Exception caught " + e.getMessage());
				}
			}
			clickOn(btnSave, "Save Button");
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void EditActivityScope3_3EmissionsCEFEdit() {
		try {
			sleep(2000);
			verifyIfElementPresent(lblEditActivityFuelEnrgy, "lblEditActivityFuelEnrgy", "lblEditActivityFuelEnrgy");
			clickOn(txtFacilityNameScope3_3, "txtFacilityNameScope3_3");
			sleep(2000);
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Facility Name") + "']"));
			clickOn(we, data.get("Facility Name"));
			sleep(2000);
			Actions mouseActions = new Actions(driver);
			// mouseActions.doubleClick(txtDescrScope3_3).doubleClick().perform();
			mouseActions.doubleClick(txtDescrScope3_3Qa).doubleClick().perform();
			// enterText(txtDescrScope3_3, "Description Scope3", data.get("description"));
			enterText(txtDescrScope3_3Qa, "Description Scope3", data.get("Description"));
			clickOn(txtStartDate, "Start Date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			validateCEF();
			String drpCEF = "//li[text()='" + nameOfCEF + "']";
			clickOnElementWithDynamicXpath(drpCEF, nameOfCEF);
			WebElement drpEnergyType = driver.findElement(By.xpath("//input[@id='type_name']"));
			if (isElementPresent(drpEnergyType)) {
				failed(driver, "Energy type element is enable");
			} else {
				passed("Energy type element is disable");
			}
			clickOn(txtScope3_3FuelEnrgyUnit, "Units");
			weUnit = driver.findElement(By.xpath("//li[text()='" + data.get("Units1") + "']"));
			clickOn(weUnit, data.get("Units1"));
			sleep(2000);
			mouseActions.doubleClick(txtQuantityConsumedFuelEngy).perform();
			enterText(txtQuantityConsumedFuelEngy, "Quantity of fuel Consumed", data.get("Quantity Consumed"));
			sleep(5000);
			clickOn(btnSave, "Save Button");
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
		sleep(2000);
		String drpCEF = "//li[text()='" + nameOfCEF + "']";
		clickOnElementWithDynamicXpath(drpCEF, nameOfCEF);
		sleep(1000);
		WebElement ter = driver.findElement(By.xpath("//input[@id='emissionName']"));
		String ewq = ter.getAttribute("value");
		if (nameOfCEF.equals(ewq)) {
			passed("Successfully validated custom emission factor name in avtivities for Fuel & Energy related");
		} else {
			failed(driver, "failed to validated custom emission factor name in avtivities for Fuel & Energy related");
		}
	}

	public void ValidateActivityDetailsInViewAddActivityScope3_3() {
		try {
			Thread.sleep(2000);
			WebElement valuetCO2e = driver
					.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::div//following-sibling::div"));
			WebElement emsfatot = driver.findElement(
					By.xpath("//span[contains(text(),'Emission Factor')]//parent::div//following-sibling::div"));
			Double CO2 = Double.parseDouble(emsfatot.getText()) * Double.parseDouble(data.get("Quantity Consumed"))
					/ 1000;
			String tCO2Activity = CO2.toString();
			String tCO2ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCO2Activity);
			if (tCO2ActivityRHP.equals(valuetCO2e.getText())) {
				passed("Succesfully validated tCO2 value" + tCO2ActivityRHP + " And Actual is " + valuetCO2e.getText());
			} else {
				fail("Failed validated tCO2 value" + " " + tCO2ActivityRHP + " But Actual is " + valuetCO2e.getText());
			}
			String[] activityDetailFieldNames = { "Facility Name", "Description", "Start Date", "End Date",
					"Energy Type", "Fuel Type", "Fuel Name", "Quantity Consumed", "Units", "Custom Emission", "Source",
					"tCO2e", "Emission Factor" };
			Thread.sleep(5000);
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Fuel & Energy related Activities");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				WebElement weActivityField = driver.findElement(By.xpath(
						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
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
			failed(driver, "Exception caught " + e.getMessage());
		}

	}

	public void validateTotalCO2EforFuel() {
		try {
			String calTco2e7 = "";
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Fuel & Energy related Activities");
			WebElement emissionfactor = driver.findElement(
					By.xpath("//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p/div"));
			String[] splitEF4 = emissionfactor.getText().split(" ");
			if (emissionfactor.getText().equals(data.get("Emission Factor"))) {
				passed("Successfully validated EmissionFactor as Actual is==>" + emissionfactor.getText()
						+ " and expected is==>" + data.get("Emission Factor"));
			} else {
				failed(driver, "Failed to validate EmissionFactor as Actual is==>" + emissionfactor.getText()
						+ " and expected is==>" + data.get("Emission Factor"));
			}
			WebElement ee = driver
					.findElement(By.xpath("//span[contains(text(),'Conversion')]//parent::p//following-sibling::p"));
			String hh = ee.getText().replaceAll(",", "");
			String[] yy = hh.split("=");
			String[] t = yy[1].split(" ");
			Double d = Double.parseDouble(t[1]);
			WebElement emission_Factor = driver.findElement(
					By.xpath("//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p"));
			String[] splitEF = emission_Factor.getText().split(" ");
			String amt = data.get("Quantity Consumed").replaceAll(",", "");
			if (splitEF[1].trim().equals(Constants.conToTonnfrlb)) {
				Double result7 = (Double.parseDouble(splitEF[0]) * Double.parseDouble(amt) * d) / 2204.623;
				calTco2e7 = result7.toString();
			} else if (splitEF[1].trim().equals(Constants.conToTonnfrKg)) {
				Double result7 = (Double.parseDouble(splitEF[0]) * Double.parseDouble(amt) * d) / 1000;
				calTco2e7 = result7.toString();
			} else if (splitEF[1].trim().equals(Constants.conToTonnfrg)) {
				Double result7 = (Double.parseDouble(splitEF[0]) * Double.parseDouble(amt) * d) / 1000000;
				calTco2e7 = result7.toString();
			} else if (splitEF[1].trim().equals(Constants.conToTonnfrTonn)) {
				Double result7 = Double.parseDouble(splitEF[0]) * Double.parseDouble(amt) * d;
				calTco2e7 = result7.toString();
			}

			String CalCo2e = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(calTco2e7);
			System.out.println("Calculated tCO2e value should be  :" + CalCo2e);
			WebElement valuetCO2e = driver
					.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
			GlobalVariables.exceptedtco2e = valuetCO2e.getText().trim().replaceAll(",./", "");
			if (CalCo2e.trim().equals(valuetCO2e.getText().trim())) {
				passed("Successfully validated tCO2e value for : " + " Fuel and Energy Related Activities "
						+ valuetCO2e.getText());
			} else {
				failed(driver, "Failed to valiadte tCO2e value for : " + " Fuel and Energy Related Activities "
						+ valuetCO2e.getText() + " But Actual is " + CalCo2e);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}

	}

	public void ValidateActivityDetailsInViewActivityScope3_3() {
		try {
			Thread.sleep(2000);
			String[] activityDetailFieldNames = { "Facility Name", "Energy Type", "Fuel Type", "Fuel Name",
					"Quantity Consumed", "Units", "Source" };
			Thread.sleep(2000);
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Fuel & Energy related Activities");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				WebElement weActivityField = driver.findElement(By.xpath(
						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::p//following-sibling::p"));
				if (weActivityField.getText().trim().equals(data.get(activityDetailFieldNames[j]).trim())) {
					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
							+ weActivityField.getText());
				} else {
					failed(driver,
							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
									+ data.get(activityDetailFieldNames[j]) + " But Actual is "
									+ weActivityField.getText());
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateActivityDetailsInViewActivityScope3_3Edit() {
		try {
			Thread.sleep(2000);
			WebElement unitFuelAndEnergy = driver
					.findElement(By.xpath("//span[text()='Units']//parent::p//following-sibling::p"));
			if (unitFuelAndEnergy.getText().equals(data.get("Units1"))) {
				passed("Successfully Validated " + data.get("Units1") + " In Activity Details As"
						+ unitFuelAndEnergy.getText());
			} else {
				failed(driver, "Failed To validate " + "Units1" + " In Activity Details Expected As "
						+ data.get("Units1") + "But Actual is" + unitFuelAndEnergy.getText());
			}
			WebElement EnergyType = driver
					.findElement(By.xpath("//span[text()='Energy Type']//parent::p//following-sibling::p"));
			if (EnergyType.getText().equals(data.get("Energy Type1"))) {
				passed("Successfully Validated " + data.get("Energy Type1") + " In Activity Details As"
						+ EnergyType.getText());
			} else {
				failed(driver, "Failed To validate " + "Energy Type" + " In Activity Details Expected As "
						+ data.get("Energy Type1") + "But Actual is" + EnergyType.getText());
			}
			String[] activityDetailFieldNames = { "Facility Name", "Fuel Type", "Fuel Name",
					"Quantity Consumed" };
			Thread.sleep(5000);
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Fuel & Energy related Activities");
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
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateCEFInScope3_3() {
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
				passed("successfully validated Source in RHP as actual is" + " " + cstomEmissionSource.getText() + " "
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
			sleep(2000);
			WebElement weNameOfCEF = driver.findElement(
					By.xpath("//span[contains(text(),'Custom Emission')]//parent::div//following-sibling::div"));
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
				passed("successfully validated source of custom emission factor" + sourceOfEmissionFactor
						+ "and actual is" + weSourceOfCEF.getText());
			} else {
				fail("failed to validate source of custom emission factor" + sourceOfEmissionFactor + "and actual is"
						+ weSourceOfCEF.getText());
			}
			String[] activityDetailFieldNames = { "Energy Type", "Unit of Custom EF (Denominator)", "CO2e",
					"Unit of Custom EF (Numerator)" };
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
				.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
		Double CO2 = Double.parseDouble(data.get("CO2e")) * Double.parseDouble(data.get("Quantity Consumed")) / 1000;
		String tCO2Activity = CO2.toString();
		String tCO2ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCO2Activity);
		if (tCO2ActivityRHP.equals(valuetCO2e.getText())) {
			passed("Succesfully validated tCO2 value" + tCO2ActivityRHP + " And Actual is " + valuetCO2e.getText());
		} else {
			fail("Failed validated tCO2 value" + " " + tCO2ActivityRHP + " But Actual is " + valuetCO2e.getText());
		}
		WebElement emission_Factor = driver
				.findElement(By.xpath("//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p"));
		String[] splitEF = emission_Factor.getText().split(" ");
		if (data.get("CO2e").equals(splitEF[0])) {
			passed("Succesfully validated emission factor value" + " " + data.get("CO2e") + " And Actual is "
					+ emission_Factor.getText());
		} else {
			fail("Failed validated emission factor value" + " " + data.get("CO2e") + " But Actual is "
					+ emission_Factor.getText());
		}
	}

	public void ValidateRHPCEFValueswithActivitiesRHPEdit() {
		WebElement valuetCO2e = driver
				.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
		Double CO2 = Double.parseDouble(data.get("CO2e1")) * Double.parseDouble(data.get("Quantity Consumed")) / 1000;
		String tCO2Activity = CO2.toString();
		String tCO2ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCO2Activity);
		if (tCO2ActivityRHP.equals(valuetCO2e.getText())) {
			passed("Succesfully validated tCO2 value" + tCO2ActivityRHP + " And Actual is " + valuetCO2e.getText());
		} else {
			fail("Failed validated tCO2 value" + " " + tCO2ActivityRHP + " But Actual is " + valuetCO2e.getText());
		}
		WebElement emission_Factor = driver
				.findElement(By.xpath("//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p"));
		String[] splitEF = emission_Factor.getText().split(" ");
		if (data.get("CO2e1").equals(splitEF[0])) {
			passed("Succesfully validated emission factor value" + " " + data.get("CO2e1") + " And Actual is "
					+ emission_Factor.getText());
		} else {
			fail("Failed validated emission factor value" + " " + data.get("CO2e1") + " But Actual is "
					+ emission_Factor.getText());
		}
	}

	@FindBy(xpath = "//*[text()='Scope 3.3 -  Fuel and Energy Related Activities']")
	private WebElement lblGHGCalculatorFUERACT;
	
	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(lblGHGCalculatorFUERACT);
			if (isElementPresent(lblGHGCalculatorFUERACT)) {
				passed("User Successfully Navigated To GHG_Calculator Page "+data.get("CalcName"));
			} else {
				failed(driver, "Failed To Navigate To GHG_Calculator Page "+data.get("CalcName"));
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}


}
