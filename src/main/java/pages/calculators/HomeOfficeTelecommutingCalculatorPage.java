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

public class HomeOfficeTelecommutingCalculatorPage extends CalculatorElements {
	public HomeOfficeTelecommutingCalculatorPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

//	String customEmissionAfctorHome = "TestAut"+generateRandomNumber(3);
	Actions mouseActions = new Actions(driver);

	public void EditActivityScope3_7Emissions() {
		try {
			verifyIfElementPresent(lblViewActivityHomeoffice, "Home Office/Telecommuting", "Home Office/Telecommuting");
			Thread.sleep(3000);
			clickOn(txtFacilityNameScope3_4, "txtFacilityNameScope3_4");
			sleep(2000);
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Facility / Location") + "']"));
			clickOn(we, data.get("Facility / Location"));
			sleep(2000);
			mouseActions.doubleClick(txtDscrptnHmeOffce).perform();
			enterText(txtDscrptnHmeOffce, "Description Scope3.4", data.get("Description"));
			clickOn(drptypeHmeOffce, "type of home office");
//			mouseActions.doubleClick(drptypeHmeOffce).perform();
			sleep(1000);
			WebElement web = driver.findElement(By.xpath("//li[text()='" + data.get("Type Of Home Office") + "']"));
			clickOn(web, data.get("Type Of Home Office"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			mouseActions.doubleClick(txtNoOfWrknDaysypeHmeOffce).perform();
			sleep(1000);
			enterText(txtNoOfWrknDaysypeHmeOffce, "No. of Working days", data.get("Number of Working Days"));
			System.out.println(data.get("Number of Working Days"));
			mouseActions.doubleClick(txtNoOfWrknhHorsHmeOffce).perform();
			sleep(1000);
			enterText(txtNoOfWrknhHorsHmeOffce, "No. of Working Hours per day",
					data.get("Number of Working Hours Per Day"));
			mouseActions.doubleClick(txtNoOfEmplysHmeOffce).perform();
			sleep(1000);
			enterText(txtNoOfEmplysHmeOffce, "No. of Working Hours per day", data.get("Number of Employees"));
			mouseActions.doubleClick(txtWrkngRegimeHmeOffce).perform();
			sleep(1000);
			enterText(txtWrkngRegimeHmeOffce, "No. of Working Hours per day",
					data.get("Working Regime of Employees in %"));
			mouseActions.doubleClick(txtWFHHmeOffce).perform();
			sleep(1000);
			enterText(txtWFHHmeOffce, "No. of Working Hours per day", data.get("% of Employees Working From Home"));
			clickOn(drpUnitsHmeOffce, "drp units");
			WebElement webUnits = driver.findElement(By.xpath("//li[text()='" + data.get("Units") + "']"));
			clickOn(webUnits, data.get("Units"));
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
			failed(driver, "Exception csaught," + e.getMessage());
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

	public void ValidateActivityDetailsInViewActivityScope3_7() {
		try {
			String[] activityDetailFieldNames = { "Facility Name", "Description", "Start Date", "End Date",
					"Custom Emission Factor", "Type Of Home Office", "Number of Working Days",
					"Number of Working Hours Per Day", "Number of Employees", "Working Regime of Employees in %",
					"% of Employees Working From Home", "Energy Consumed (kWh)", "Emission Factor", "Source" };
			Thread.sleep(5000);
			verifyIfElementPresent(lblActivityDetails, "lblViewActivityFulEngy", "home office telecommuting");
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

	// ----cef
	public static String customEmissionAfctorHome;
	String srcCEf;

	public void addCEFForHomeOfficeTelecommuting() {
		try {
			sleep(1000);
			JavascriptExecutor jsExc = (JavascriptExecutor) driver;
			clickOn(btnAddCEF, "Custom Emission Factor");
			verifyIfElementPresent(lblAddCEF, "CEF", "CEF");
			customEmissionAfctorHome = Constants.customEmissionFactor + generateRandomNumber(3);
			enterText(txtNameOfCustomEFBussiness, "name of custom emission factor", customEmissionAfctorHome);
			 clickOn(drpTypeOfHomeOffce, "Type of home office");
			mouseActions.click(drpTypeOfHomeOffce).perform();
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Type of Home Office") + "']"));
			waitForElement(we);
			clickOn(we, "type of home office");
			srcCEf = "TestAut" + generateRandomNumber(3);
			enterText(txtSourcs3_15, "source of custom emission factor", srcCEf);
			enterText(txtEnergyConsumption, "energy consumption", data.get("Energy Consumption (kWh) per hour"));
			clickOn(drpUnitsOfCustomHmeOffce, "unit of custom emission factor");
			mouseActions.click(drpUnitsOfCustomHmeOffce).perform();
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Denominator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Denominator)"));
			enterText(txtco2e, "co2e", data.get("CO2e"));
			mouseActions.click(drpUnitsOfCustomNumHmeOffce).perform();
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Numerator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Numerator)"));
			clickOn(btnSaveParemeterInput, "Save Custom Emission factor");
			sleep(1000);
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void addCEFForHomeOfficeTelecommutingEdit() {
		try {
			Actions mouseActions = new Actions(driver);
			sleep(1000);
			// clickOn(btnAddCEF, "Custom Emission Factor");
			verifyIfElementPresent(lblEditCEF, "Edit CEF", "Edit CEF");
			customEmissionAfctorHome = Constants.customEmissionFactor + generateRandomNumber(3);
			mouseActions.doubleClick(txtNameOfCustomEFBussiness).perform();
			enterText(txtNameOfCustomEFBussiness, "name of custom emission factor", customEmissionAfctorHome);
			 clickOn(drpTypeOfHomeOffce, "Type of home office");
			mouseActions.click(drpTypeOfHomeOffce).perform();
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Type of Home Office") + "']"));
			clickOn(we, data.get("Type of Home Office"));
			srcCEf = "TestAut" + generateRandomNumber(3);
			mouseActions.doubleClick(txtSourcs3_15).perform();
			enterText(txtSourcs3_15, "source of custom emission factor", srcCEf);
			enterText(txtEnergyConsumption, "energy consumption", data.get("Energy Consumption (kWh) per hour"));
			// clickOn(drpUnitsOfCustomHmeOffce, "unit if custom emission factor");
			mouseActions.click(drpUnitsOfCustomHmeOffce).perform();
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Denominator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Denominator)"));
			enterText(txtco2e, "co2e", data.get("CO2eEdit"));
			mouseActions.click(drpUnitsOfCustomNumHmeOffce).perform();
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
					"//span[contains(text(),'Name of Custom Emission Factor')]//parent::div//following-sibling::div/div"));
			WebElement weSourceOfCEF = driver.findElement(
					By.xpath("//span[contains(text(),'Source')]//parent::div//following-sibling::div/div"));
			if (weNameOfCEF.getText().equals(customEmissionAfctorHome)) {
				passed("successfully validated name of custom emission factor as expected" + customEmissionAfctorHome
						+ "and actual is" + weNameOfCEF.getText());
			} else {
				fail("failed to validated name of custom emission factor as expected" + customEmissionAfctorHome
						+ "and actual is" + weNameOfCEF.getText());
			}
			if (weSourceOfCEF.getText().equals(srcCEf)) {
				passed("successfully validated source of custom emission factor" + srcCEf + "and actual is"
						+ weNameOfCEF.getText());
			} else {
				fail("failed to validate source of custom emission factor" + srcCEf + "and actual is"
						+ weNameOfCEF.getText());
			}
			WebElement unitOfCustmEFNum = driver.findElement(By.xpath(
					"(//span[contains(text(),'Unit of Custom EF')]//parent::div//following-sibling::div/div)[2]"));
			if (unitOfCustmEFNum.getText().equals(data.get("Unit of Custom EF (Numerator)"))) {
				passed("successfully validated unit of custom efNUm " + data.get("Unit of Custom EF (Numerator)")
						+ "and actual is" + unitOfCustmEFNum.getText());
			} else {
				fail("failed to validate unit of custom efNUm " + data.get("Unit of Custom EF (Numerator)")
						+ "and actual is" + unitOfCustmEFNum.getText());
			}
			String[] activityDetailFieldNames = { "Type of home office", "Energy Consumption (kWh) per hour",
					"Unit of Custom EF (Denominator)" };
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

	public void ValidateActivityDetailsInViewActivityScope3_7Cef() {
		try {
			String[] activityDetailFieldNames = { "Facility Name", "Description", "Start Date", "End Date",
					"Type Of Home Office", "Number of Working Days", "Number of Working Hours Per Day",
					"Number of Employees", "Working Regime of Employees in %", "% of Employees Working From Home",
					"Energy Consumed (kWh)" };
			Thread.sleep(1000);
			verifyIfElementPresent(lblActivityDetails, "lblViewActivityFulEngy", "home office telecommuting");
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
				.findElement(By.xpath("//div[text()='" + srcCEf + "']//parent::div[@role='row']"));
		clickOn(weActivity, "Activity of Facility" + srcCEf);
	}

	public void clickAvergaeDataMethod() {
		waitForElement(btnInvestmentsAveargeMethod);
		clickOn(btnInvestmentsAveargeMethod, "avergae method");
	}

	public void EditActivityScope3_7EmissionsCEF() {
		try {
			verifyIfElementPresent(lblViewActivityHomeoffice, "Home Office/Telecommuting", "Home Office/Telecommuting");
			Thread.sleep(1000);
			clickOn(txtFacilityNameScope3_4, "txtFacilityNameScope3_4");
			sleep(2000);
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Facility / Location") + "']"));
			clickOn(we, data.get("Facility / Location"));
			sleep(2000);
//			mouseActions.doubleClick(txtContryHmeOffce).perform();			
//			enterText(txtContryHmeOffce, "Country", country.get(i));
			mouseActions.doubleClick(txtDscrptnHmeOffce).perform();
			enterText(txtDscrptnHmeOffce, "Description Scope3.4", data.get("Description"));
			clickOn(drptypeHmeOffce, "type of home office");
			// enterText(drptypeHmeOffce, "drptypeHmeOffce", data.get("Type Of Home
			// Office"));
			sleep(1000);
			WebElement web = driver.findElement(By.xpath("//li[text()='" + data.get("Type Of Home Office") + "']"));
			clickOn(web, data.get("Type Of Home Office"));
			validateCEF();
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			mouseActions.doubleClick(txtNoOfWrknDaysypeHmeOffce).perform();
			sleep(1000);
			enterText(txtNoOfWrknDaysypeHmeOffce, "No. of Working days", data.get("Number of Working Days"));
			System.out.println(data.get("Number of Working Days"));
			mouseActions.doubleClick(txtNoOfWrknhHorsHmeOffce).perform();
			sleep(1000);
			enterText(txtNoOfWrknhHorsHmeOffce, "No. of Working Hours per day",
					data.get("Number of Working Hours Per Day"));
			mouseActions.doubleClick(txtNoOfEmplysHmeOffce).perform();
			sleep(1000);
			enterText(txtNoOfEmplysHmeOffce, "No. of Working Hours per day", data.get("Number of Employees"));
			mouseActions.doubleClick(txtWrkngRegimeHmeOffce).perform();
			sleep(1000);
			enterText(txtWrkngRegimeHmeOffce, "No. of Working Hours per day",
					data.get("Working Regime of Employees in %"));
			mouseActions.doubleClick(txtWFHHmeOffce).perform();
			sleep(1000);
			enterText(txtWFHHmeOffce, "No. of Working Hours per day", data.get("% of Employees Working From Home"));
			clickOn(drpUnitsHmeOffce, "drp units");
			WebElement webUnits = driver.findElement(By.xpath("//li[text()='" + data.get("Units") + "']"));
			clickOn(webUnits, data.get("Units"));
			clickOn(btnSave, "Saved");
			sleep(1000);
		} catch (Exception e) {
			failed(driver, "Exception csaught," + e.getMessage());
		}
	}

	public void validateCEF() {
		sleep(1000);
		jsClick(chbxCustomEmssionFactorFuel, "yes");
		verifyIfElementPresent(drpcutmEFHome, "--", "--");
		clickOn(drpcutmEFHome, "-");
		WebElement namecef = driver.findElement(By.xpath("//li[text()='" + customEmissionAfctorHome + "']"));
		JavascriptExecutor jsExc = (JavascriptExecutor) driver;
		jsExc.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow');", namecef);
		sleep(2000);
		String drpCEF = "//li[text()='" + customEmissionAfctorHome + "']";
		clickOnElementWithDynamicXpath(drpCEF, customEmissionAfctorHome);		
		WebElement qw = driver.findElement(By.xpath("//input[@id='Custom Emission']"));
		String CustomEmissionFactorNameActivity = qw.getAttribute("value");
		System.out.println(CustomEmissionFactorNameActivity);
		if (customEmissionAfctorHome.equals(CustomEmissionFactorNameActivity)) {
			passed("Successfully Validated Custom Emisiion Factor in Activities"
					+ CustomEmissionFactorNameActivity);
		} else {
			failed(driver, " Failed to Validated Custom Emisiion Factor in Activities Expected is "
					+ customEmissionAfctorHome + "But Actual is" + CustomEmissionFactorNameActivity);
		}
	}

	public void ValidateCEFInScope3_7() {
		try {
			sleep(2000);
			WebElement cstomEmission = driver.findElement(
					By.xpath("//span[contains(text(),'Custom Emission')]//parent::p//following-sibling::p"));
			WebElement cstomEmissionSource = driver
					.findElement(By.xpath("//span[contains(text(),'Source')]//parent::p//following-sibling::p"));
			System.out.println(cstomEmission.getText());
			if (cstomEmission.getText().equals(customEmissionAfctorHome)) {
				passed("successfully validated CEF in RHP as actual is" + cstomEmission.getText() + " "
						+ "and Expected is" + " " + customEmissionAfctorHome);
			} else {
				fail("failed to validate CEF in RHP as actual is" + " " + cstomEmission.getText() + " "
						+ "and Expected is" + " " + customEmissionAfctorHome);
			}
			if (cstomEmissionSource.getText().equals(srcCEf)) {
				passed("successfully validated Source in RHP as actual is" + cstomEmissionSource.getText() + " "
						+ "and Expected is" + " " + srcCEf);
			} else {
				fail("failed to validate Source in RHP as actual is" + " " + cstomEmissionSource.getText() + " "
						+ "and Expected is" + " " + srcCEf);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void ValidateRHPCEFValueswithActivitiesRHP() {
		WebElement valuetCO2 = driver
				.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
		WebElement energyConsumed = driver.findElement(
				By.xpath("//span[contains(text(),'Energy Consumed (kWh)')]//parent::p//following-sibling::p"));
		Double CO2 = Double.parseDouble(data.get("CO2e")) * Double.parseDouble(energyConsumed.getText());
		String tCO2Activity = CO2.toString();
		String tCO2ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCO2Activity);
		if (tCO2ActivityRHP.equals(valuetCO2.getText())) {
			passed("Succesfully validated tCO2 value" + tCO2ActivityRHP + " And Actual is " + valuetCO2.getText());
		} else {
			fail("Failed validated tCO2 value" + " " + tCO2ActivityRHP + " But Actual is " + valuetCO2.getText());
		}
		WebElement EFActivity = driver.findElement(
				By.xpath("//span[text()='Emission Factor']//parent::p//parent::div"));
		String s2 = EFActivity.getText().trim();
		String[] EFActivity2 = s2.split("\n");
		String[] EFValueRHP1 = EFActivity2[1].split(" ");
		String EFValueRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(EFValueRHP1[0]);
		String tco2eCEFRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(data.get("CO2e"));
//		String s2 = EFActivity.getText().trim();
//		String[] EFValueRHP = s2.split(" ");
		if (EFValueRHP.equals(tco2eCEFRHP)) {
			passed("Succesfully validated Emission factor Expected value" + " " + tco2eCEFRHP + " And Actual is "
					+ EFValueRHP);
		} else {
			fail("Failed validated Emission Factor Expected value" + " " + tco2eCEFRHP + " But Actual is "
					+ EFValueRHP);
		}
	}

	public void ValidateRHPCEFValueswithActivitiesRHPEdit() {
		WebElement valuetCO2 = driver
				.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
		WebElement energyConsumed = driver.findElement(
				By.xpath("//span[contains(text(),'Energy Consumed (kWh)')]//parent::p//following-sibling::p"));
		Double CO2 = Double.parseDouble(data.get("CO2eEdit")) * Double.parseDouble(energyConsumed.getText());
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


	public void validateTOTALCO2EforHomeOffice() {
		try {
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Home Office/Telecommuting");
			WebElement ee = driver
					.findElement(By.xpath("//span[text()='Unit Conversion']//parent::p//following-sibling::p"));
			String hh = ee.getText().replaceAll(",", "");
			String[] yy = hh.split("=");
			String[] t = yy[1].split(" ");
			Double d = Double.parseDouble(t[1]);
			WebElement emissionFactor4 = driver.findElement(
					By.xpath("(//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p)[2]"));
			String[] splitEF4 = emissionFactor4.getText().replaceAll(",", "").split(" ");
			Double result4 = (Double.parseDouble(splitEF4[0]) * Double.parseDouble(data.get("Energy Consumed (kWh)"))
					* d) / 1000;
			String res4 = result4.toString();
			String CalCo2e4 = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(res4);
			WebElement valtco2e = driver
					.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
			GlobalVariables.exceptedtco2e = valtco2e.getText().trim();
			if (CalCo2e4.trim().equals(GlobalVariables.exceptedtco2e)) {
				passed("Successfully validated tCO2e value for : " + " Home Office/Telecommuting "
						+ GlobalVariables.exceptedtco2e);
			} else {
				failed(driver, "Failed to valiadte tCO2e value for : " + " Home Office/Telecommuting "
						+ GlobalVariables.exceptedtco2e + " But Actual should be " + CalCo2e4);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());

		}
	}
	
	@FindBy(xpath = "//*[text()='Scope 3.7 - Home Office/Telecommuting']")
	private WebElement lblGHGCalculatorHOMOFF;
	
	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(lblGHGCalculatorHOMOFF);
			if (isElementPresent(lblGHGCalculatorHOMOFF)) {
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
