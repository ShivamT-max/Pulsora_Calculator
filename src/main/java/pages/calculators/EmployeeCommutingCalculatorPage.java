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
import utilities.GlobalVariables;

public class EmployeeCommutingCalculatorPage extends CalculatorElements {
	public EmployeeCommutingCalculatorPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	public void addActivityDetailsForEmployeeCommutingCalculatorInActivityDetailsPannel() {
		try {
			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
			clickOn(drpFacilityName, "txtFacilityName");
			// enterText(drpFacilityName, "Facility Name", facilityName.get(i));
			WebElement weFacilityName = driver
					.findElement(By.xpath("//li[text()='" + data.get("Facility Name") + "']"));
			clickOn(weFacilityName, data.get("Facility Name"));
			sleep(2000);
//			clickOn(txtCommuteDescription, " Commute Description");
//			enterText(txtCommuteDescription, "Commute Description", data.get(" Commute Description"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			clickOn(txtGroupEmployee, " Group of Emp");
			enterText(txtGroupEmployee, "Group of Emp", data.get("Employee / Group of Employees"));
			clickOn(txtNoOfEmpCommuted, " NoOfEmpCommuted");
			enterText(txtNoOfEmpCommuted, "numberOfEmployeeCommuted", data.get(" Number of Employees Commuted"));
			clickOn(drpModeOfCommuteEmployye, "Mode Of Commute");
			WebElement weModeofCommute = driver
					.findElement(By.xpath("//li[text()='" + data.get("Mode of Commute") + "']"));
			clickOn(weModeofCommute, data.get("Mode of Commute"));
			sleep(2000);
			Actions act = new Actions(driver);
			act.doubleClick(txtVehicleTypeEmployee).perform();
			clickOn(drpVehicleTypeEmployee, "VehicalType");
			sleep(1000);
			WebElement weVehicleType = driver.findElement(By.xpath("//li[text()='" + data.get("Vehicle Type") + "']"));
			sleep(2000);
			clickOn(weVehicleType, data.get("Vehicle Type"));
			clickOn(txtAvgCommuteDays, " Average Commute Days");
			enterText(txtAvgCommuteDays, "Average Commute Days", data.get(" Average Commute Days"));
			clickOn(txtAvgDailyCommuteDistance, " Average Daily Commute Distance ");
			enterText(txtAvgDailyCommuteDistance, "Average Daily Commute Distance ",
					data.get("Average Daily Commute Distance"));
			clickOn(drpBusinessTravlUnits, "Units");
			WebElement weUnits = driver.findElement(By.xpath("//li[text()='" + data.get("Units") + "']"));
			clickOn(weUnits, data.get("Units"));
			clickOn(btnSave, "Save Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateActivityDetailsInViewActivityForEmployeeCommutingCalculator() {
		try {
			//sleep(2);
			String[] activityDetailFieldNames = {"Facility Name",
					"Employee / Group of Employees", " Number of Employees Commuted", "Custom Emission Factor",
					"Activity Amount", "Mode of Commute", "Vehicle Type", " Average Commute Days",
					"Average Daily Commute Distance", "Units",
					"Emission Factor", "Source" };
			// "Start Date", "End Date",
			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				WebElement weActivityField = driver.findElement(By.xpath(
						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::p//following-sibling::p//div"));
				String scopeDes3_6txt = weActivityField.getText().trim();
				String[] weHotelStayActivity = scopeDes3_6txt.split("\n");
				System.out.println(scopeDes3_6txt+scopeDes3_6txt.length()+"  "+data.get(activityDetailFieldNames[j]).length());
				if (scopeDes3_6txt.equals(data.get(activityDetailFieldNames[j]))) {
					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
							+ scopeDes3_6txt);
				} else {
					failed(driver,
							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
									+ data.get(activityDetailFieldNames[j]) + "But Actual is" + scopeDes3_6txt);
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	// ----------------------------------CEF----------------------

	public void ClickOnEmployeeCommutingButton() {
		clickOn(btnEmployeeCommuting, "btnEmployeeCommuting");
		sleep(3000);
	}

	String CustomEmissionFactorName;

	public void Add_CustomEmissionFactorEmployeeCommutingScope3_7() {
		try {
			CustomEmissionFactorName = Constants.CEFName + generateRandomNumber(4);
			verifyIfElementPresent(lblAddCutsomEmissionFactor, "lblAddCutsomEmissionFactor",
					"lblAddCutsomEmissionFactor");
			enterText(txtCutsomEFName, "entered Name of Custom EF", CustomEmissionFactorName);
			clickOn(drpCalculationMethodEC, "Calculation Method Drop Doen");
			WebElement weVehicleType = driver
					.findElement(By.xpath("//li[text()='" + data.get("Calculation Method") + "']"));
			clickOn(weVehicleType, data.get("Calculation Method"));
			enterText(txtSourceCEF, "entered Name of Custom EF", data.get("Source of Emission Factor"));
			clickOn(drpUnitCEFDenominator, "Click on Unit of Custom EF (Denominator) Drp");
			WebElement weUnitCEFDenominator = driver
					.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Denominator)") + "']"));
			clickOn(weUnitCEFDenominator, data.get("Unit of Custom EF (Denominator)"));
			enterText(txtCO2, "entered txtCO2F", data.get("CO2"));
			enterText(txtCH4, "entered txtCH4", data.get("CH4"));
			enterText(txtN2O, "entered txtN2O", data.get("N2O"));

			clickOn(drpUnitCEFNumerator, "Click on Unit of Custom EF (Numerator) Drp");
			WebElement weUnitCEFNumerator = driver
					.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Numerator)") + "']"));
			clickOn(weUnitCEFNumerator, data.get("Unit of Custom EF (Numerator)"));
			enterText(txtModeofCommute, "entered Mode of Commute", data.get("Mode of Commute"));
			System.out.println(data.get("Vehicle Type/Commute Category"));
			enterText(txtVehicleType, "entered Vehicle Type/Commute Category",
					data.get("Vehicle Type/Commute Category"));

			enterText(txtNotesCEF, "entered txtNotesCEF", data.get(" Notes"));
			clickOn(btnSaveParemeterInput, "Save Button");
			CO2eFactor();
			sleep(2000);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	Actions act = new Actions(driver);

	public void Edit_CustomEmissionFactorEmployeeCommutingScope3_7() {
		try {
			verifyIfElementPresent(lblEditCutsomEmissionFactor, "lblEditCutsomEmissionFactor",
					"lblEditCutsomEmissionFactor");
			act.doubleClick(txtCutsomEFName).perform();
			enterText(txtCutsomEFName, "entered Name of Custom EF", CustomEmissionFactorName);
			clickOn(drpCalculationMethodEC, "Calculation Method Drop Doen");
			WebElement weVehicleType = driver
					.findElement(By.xpath("//li[text()='" + data.get("Calculation Method") + "']"));
			clickOn(weVehicleType, data.get("Calculation Method"));
			act.doubleClick(txtSourceCEF).perform();
			enterText(txtSourceCEF, "entered Name of Custom EF", data.get("Source of Emission Factor"));
			clickOn(drpUnitCEFDenominator, "Click on Unit of Custom EF (Denominator) Drp");
			WebElement weUnitCEFDenominator = driver
					.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Denominator)") + "']"));
			clickOn(weUnitCEFDenominator, data.get("Unit of Custom EF (Denominator)"));
			clickOn(drpUnitCEFNumerator, "Click on Unit of Custom EF (Numerator) Drp");
			WebElement weUnitCEFNumerator = driver
					.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Numerator)") + "']"));
			clickOn(weUnitCEFNumerator, data.get("Unit of Custom EF (Numerator)"));
			act.doubleClick(txtCO2).perform();
			enterText(txtCO2, "entered txtCO2F", data.get("CO2 Edit"));
			act.doubleClick(txtCH4).perform();
			enterText(txtCH4, "entered txtCH4", data.get("CH4 Edit"));
			act.doubleClick(txtN2O).perform();
			enterText(txtN2O, "entered txtN2O", data.get("N2O Edit"));
			act.doubleClick(txtNotesCEF).perform();
			enterText(txtNotesCEF, "entered txtNotesCEF", data.get(" Notes"));
			clickOn(btnSaveParemeterInput, "Save Button");
			CO2eFactorEC();
			sleep(2000);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void clickOnCEFinCEFGrid() {
		sleep(3000);
		WebElement weActivity = driver
				.findElement(By.xpath("//div[text()='" + CustomEmissionFactorName + "']//parent::div"));
		clickOn(weActivity, "Activity of Facility" + CustomEmissionFactorName);
	}

	public void VerifyCEF_ActivityInEmployeeCommuting() {
		try {
			jsClick(btnCustomBasedCEF, "Check Box Using CEF");
			sleep(1000);
			clickOn(drpCustomEmiFactorActivityMobile, "Click on of Custom EF ");
//			enterText(txtCEFEmission, "entered Name of Custom EF", CustomEmissionFactorName);
			sleep(1000);
			// verifyIfElementPresent(lblViewActivity, "lblViewActivity",
			// "lblViewActivity");
			WebElement weCEFActivity = driver.findElement(By.xpath("//li[text()='" + CustomEmissionFactorName + "']"));
			clickOn(weCEFActivity, CustomEmissionFactorName);
			String CustomEmissionFactorNameActivity = txtCEFEmission.getAttribute("value");
			System.out.println(CustomEmissionFactorNameActivity);
			if (CustomEmissionFactorName.equals(CustomEmissionFactorNameActivity)) {
				passed("Successfully Validated Custom Emisiion Factor in Activities"
						+ CustomEmissionFactorNameActivity);
			} else {
				failed(driver, " Failed to Validated Custom Emisiion Factor in Activities Expected is "
						+ CustomEmissionFactorName + "But Actual is" + CustomEmissionFactorNameActivity);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void AddActivityForCEFEmployeeCommuting() {
		try {
			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
			clickOn(drpFacilityName, "txtFacilityName");
			// enterText(drpFacilityName, "Facility Name", facilityName.get(i));
			WebElement weFacilityName = driver
					.findElement(By.xpath("//li[text()='" + data.get("Facility Name") + "']"));
			clickOn(weFacilityName, data.get("Facility Name"));
			sleep(2000);
			clickOn(txtCommuteDescription, " Commute Description");
			act.doubleClick(txtCommuteDescription).perform();
			enterText(txtCommuteDescription, "Commute Description", data.get(" Commute Description"));
			clickOn(txtStartDate, "Start date");
			act.doubleClick(txtStartDate).perform();
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			act.doubleClick(txtEndDate).perform();
			enterText(txtEndDate, "End Date", data.get("End Date"));
			clickOn(txtGroupEmployee, " Group of Emp");
			enterText(txtGroupEmployee, "Group of Emp", data.get("Employee / Group of Employees"));
			clickOn(txtNoOfEmpCommuted, " NoOfEmpCommuted");
			enterText(txtNoOfEmpCommuted, "numberOfEmployeeCommuted", data.get(" Number of Employees Commuted"));
			VerifyCEF_ActivityInEmployeeCommuting();
//			 clickOn(drpModeOfCommuteEmployye, "Mode Of Commute");
//			 WebElement weModeofCommute = driver
//					.findElement(By.xpath("//li[text()='" + data.get("Mode of Commute") + "']"));
//			clickOn(weModeofCommute, data.get("Mode of Commute"));
//			clickOn(drpVehicleTypeEmployee, "VehicalType");
//		sleep(1000);
//			WebElement weVehicleType = driver.findElement(By.xpath("//li[text()='" + data.get("Vehicle Type") + "']"));
//			sleep(2000);
//			clickOn(weVehicleType, data.get("Vehicle Type"));
			clickOn(txtAvgCommuteDays, " Average Commute Days");
		enterText(txtAvgCommuteDays, "Average Commute Days", data.get(" Average Commute Days"));
			clickOn(txtAvgDailyCommuteDistance, " Average Daily Commute Distance ");
			enterText(txtAvgDailyCommuteDistance, "Average Daily Commute Distance ",
					data.get("Average Daily Commute Distance"));
//			clickOn(drpBusinessTravlUnits, "Units");
//			WebElement weUnits = driver.findElement(By.xpath("//li[text()='" + data.get("Units") + "']"));
//			clickOn(weUnits, data.get("Units"));
			clickOn(btnSave, "Save Button");
			sleep(2000);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void VerifyCustomEmissionFactorActivitiesDetailsRHPGHGCalculator() {
		try {
			sleep(2000);
			WebElement weCEFNameRHP = driver.findElement(
					By.xpath("//span[text()='Custom Emission Factor']//parent::p//following-sibling::p//div"));
//			String cefName = weCEFNameRHP.getText().trim();
//			String[] weCEFNameHotelStay = cefName.split("\n");
			if (weCEFNameRHP.getText().equals(CustomEmissionFactorName)) {
				passed("Successfully Validated Custom Emisiion Factor in Activity Details RHP"
						+ weCEFNameRHP.getText());
			} else {
				failed(driver, " Failed to Validated Custom Emisiion Factor in Activity Details RHP Expected is "
						+ CustomEmissionFactorName + "But Actual is" + weCEFNameRHP.getText());
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateActivityDetailsInViewActivityForEmployeeCommutingCalculatorCEF() {
		try {
			String[] activityDetailFieldNames = { " Commute Description", "Employee / Group of Employees",
					" Number of Employees Commuted", "Activity Amount", "Mode of Commute", "Vehicle Type",
					" Average Commute Days", "Average Daily Commute Distance", "Units", "Source" };
			// "Start Date", "End Date",
			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				WebElement weActivityField = driver.findElement(By.xpath(
						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::p//following-sibling::p//div"));
				String scopeDes3_6txt = weActivityField.getText().trim();
				String[] weHotelStayActivity = scopeDes3_6txt.split("\n");
				if (scopeDes3_6txt.equals(data.get(activityDetailFieldNames[j]))) {
					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
							+ scopeDes3_6txt);
				} else {
					failed(driver,
							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
									+ data.get(activityDetailFieldNames[j]) + "But Actual is" + scopeDes3_6txt);
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateAddCEFDetailsInEmployeeCommutingCEF() {
		try {
			System.out.println("-------Emission Details Are-------");
			sleep(1000);
			String[] activityDetailFieldNames = { "Calculation Method", "Source of Emission Factor",
					"Unit of Custom EF (Denominator)", "CO2", "CH4", "N2O", "Biofuel CO2",
					"Unit of Custom EF (Numerator)", "Mode of Commute", "Vehicle Type/Commute Category", " Notes" };
			// Biofuel CO2 "Name of Custom EF",
//    	    verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				sleep(1000);
				WebElement weActivityField = driver.findElement(By.xpath(
						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
				sleep(500);
				if (weActivityField.getText().trim().equals(data.get(activityDetailFieldNames[j]))) {
					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
							+ weActivityField.getText());
				} else {
					System.out.println(data.get(activityDetailFieldNames[j]));
					System.out.println(weActivityField.getText());
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

	public void ValidateEditCEFDetailsInEmployeeCommutingCEF() {
		try {
			System.out.println("-------Emission Details Are-------");
			if (weEmissionDetails_CO2.getText().trim().equals(data.get("CO2 Edit"))) {
				passed("Successfuuly Validated CO2 Emission Factor value on Activity RHP"
						+ weEmissionDetails_CO2.getText().trim());
			} else {
				fail("Failed to  Validated CO2 Emission Factor value on Activity RHP Expected is "
						+ data.get("CO2 Edit") + "But Actual is " + weEmissionDetails_CO2.getText().trim());
			}
			if (weEmissionDetails_N2O.getText().trim().equals(data.get("N2O Edit"))) {
				passed("Successfuuly Validated N2O Emission Factor value on Activity RHP"
						+ weEmissionDetails_N2O.getText().trim());
			} else {
				fail("Failed to  Validated N20 Emission Factor value on Activity RHP Expected is "
						+ data.get("N2O Edit") + "But Actual is " + weEmissionDetails_N2O.getText().trim());
			}
			if (weEmissionDetails_CH4.getText().trim().equals(data.get("CH4 Edit"))) {
				passed("Successfuuly Validated CH4 Emission Factor value on Activity RHP"
						+ weEmissionDetails_CH4.getText().trim());
			} else {
				fail("Failed to  Validated CH4 Emission Factor value on Activity RHP Expected is "
						+ data.get("CH4 Edit") + "But Actual is " + weEmissionDetails_CH4.getText().trim());
			}
			sleep(1000);
			String[] activityDetailFieldNames = { "Calculation Method", "Source of Emission Factor",
					"Unit of Custom EF (Denominator)", "Biofuel CO2", "Unit of Custom EF (Numerator)", " Notes" };
			// Biofuel CO2 "Name of Custom EF",
//    	    verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				sleep(1000);
				WebElement weActivityField = driver.findElement(By.xpath(
						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
				sleep(500);
				if (weActivityField.getText().trim().equals(data.get(activityDetailFieldNames[j]))) {
					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
							+ weActivityField.getText());
				} else {
					System.out.println(data.get(activityDetailFieldNames[j]));
					System.out.println(weActivityField.getText());
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

	Double valueCo2e;
	String valCO2e;

	public void CO2eFactor() {
		valueCo2e = Double.parseDouble(data.get("CO2")) + Double.parseDouble(data.get("CH4")) * 25
				+ Double.parseDouble(data.get("N2O")) * 298;
		System.out.println("calculated value" + valueCo2e);
		valCO2e = valueCo2e.toString();
	}

	public void ValidateRHPCEFValueswithActivitiesRHPEmployeeCommuting() {
		WebElement valuetCO2EC = driver
				.findElement(By.xpath("//span[text()='tCO2']//parent::p//following-sibling::p//div"));
		String scopeDes3_6txt = valuetCO2EC.getText().trim();
		String[] valuetCO2 = scopeDes3_6txt.split("\n");
		Double CO2 = Double.parseDouble(data.get("CO2"))
				* Double.parseDouble(data.get("Average Daily Commute Distance"))
				* Double.parseDouble(data.get(" Average Commute Days"))
				* Double.parseDouble(data.get(" Number of Employees Commuted")) / 1000;
		String tCO2Activity = CO2.toString();
		String tCO2ActivityRHP = approximateDecimalValueWithBigDecimal(tCO2Activity);
		if (tCO2ActivityRHP.equals(approximateDecimalValueWithBigDecimal(valuetCO2EC.getText()))) {
			passed("Succesfully validated tCO2 value" + tCO2ActivityRHP + " And Actual is "
					+ approximateDecimalValueWithBigDecimal(valuetCO2EC.getText()));
		} else {
			failed(driver, "Failed validated tCO2 value" + " " + tCO2ActivityRHP + " But Actual is "
					+ approximateDecimalValueWithBigDecimal(valuetCO2EC.getText()));
		}
		WebElement valueCH4EC = driver
				.findElement(By.xpath("//span[text()='tCH4']//parent::p//following-sibling::p//div"));
		String scopetCH4 = valueCH4EC.getText().trim();
		String[] valueCH4 = scopetCH4.split("\n");
		Double CH4 = Double.parseDouble(data.get("CH4"))
				* Double.parseDouble(data.get("Average Daily Commute Distance"))
				* Double.parseDouble(data.get(" Average Commute Days"))
				* Double.parseDouble(data.get(" Number of Employees Commuted")) / 1000;
		String CH4Activity = CH4.toString();
		String CH4ActivityRHP = approximateDecimalValueWithBigDecimal(CH4Activity);
//    	  String valueCH4EComm = approximateDecimalValueWithBigDecimal(valueCH4[1]);
		String valueCH4EComm = approximateDecimalValueWithBigDecimal(valueCH4EC.getText());
		if (CH4ActivityRHP.equals(valueCH4EComm)) {
			passed("Succesfully validated CH4 value" + CH4ActivityRHP + " And Actual is " + valueCH4EComm);
		} else {
			failed(driver, "Failed validated CH4 value" + " " + CH4ActivityRHP + " But Actual is " + valueCH4EComm);
		}
		WebElement valueN2OEC = driver
				.findElement(By.xpath("//span[text()='tN2O']//parent::p//following-sibling::p//div"));
		String scopetN2O = valueN2OEC.getText().trim();
		String[] valueN2O = scopetN2O.split("\n");
		Double N2O = Double.parseDouble(data.get("N2O"))
				* Double.parseDouble(data.get("Average Daily Commute Distance"))
				* Double.parseDouble(data.get(" Average Commute Days"))
				* Double.parseDouble(data.get(" Number of Employees Commuted")) / 1000;
		String N2OActivity = N2O.toString();
		String N2OActivityRHP = approximateDecimalValueWithBigDecimal(N2OActivity);
		String valueN20EComm = approximateDecimalValueWithBigDecimal(valueN2OEC.getText());
		if (N2OActivityRHP.equals(valueN20EComm)) {
			passed("Succesfully validated N2O value" + N2OActivityRHP + " And Actual is " + valueN20EComm);
		} else {
			failed(driver, "Failed validated N2O value" + " " + N2OActivityRHP + " But Actual is " + valueN20EComm);
		}
		WebElement CO2eActivityEC = driver
				.findElement(By.xpath("//span[text()='tCO2e']//parent::p//following-sibling::p//div"));
		String scopetCO2e = CO2eActivityEC.getText().trim();
		String[] CO2eActivity = scopetCO2e.split("\n");
		Double ValCO2e = valueCo2e * Double.parseDouble(data.get("Average Daily Commute Distance"))
				* Double.parseDouble(data.get(" Average Commute Days"))
				* Double.parseDouble(data.get(" Number of Employees Commuted")) / 1000;
		String ValCO2eRHP = ValCO2e.toString();
		String CO2eActivityEc = approximateDecimalValueWithBigDecimal(CO2eActivityEC.getText());
		String valuetCO2e = approximateDecimalValueWithBigDecimal(ValCO2eRHP);
		if (CO2eActivityEc.equals(valuetCO2e)) {
			passed("Succesfully validated tCO2e value" + valuetCO2e + " And Actual is " + CO2eActivityEc);
		} else {
			failed(driver, "Failed validated tCO2e value" + " " + valuetCO2e + " But Actual is " + CO2eActivityEc);
		}
		WebElement EFActivityEC = driver
				.findElement(By.xpath("//span[text()='Emission Factor']//parent::p//following-sibling::p//div"));
//		String scopetEF = EFActivityEC.getText().trim();
//		String[] CO2eActivity1 = scopetEF.split("\n");
//        String[] EFValueRHP = CO2eActivity1[1].split(" ");
//        String valuetEF = approximateDecimalValueWithBigDecimal(EFActivityEC.getText());
		String valuetCO2eEF = approximateDecimalValueWithBigDecimal(valCO2e);
		if (EFActivityEC.getText().equals(valuetCO2eEF)) {
			passed("Succesfully validated Emission factor value" + valuetCO2eEF + " And Actual is "
					+ EFActivityEC.getText());
		} else {
			failed(driver, "Failed validated Emission factor  value" + " " + valuetCO2eEF + " But Actual is "
					+ EFActivityEC.getText());
		}
	}

	public void CO2eFactorEC() {
		valueCo2e = Double.parseDouble(data.get("CO2 Edit")) + Double.parseDouble(data.get("CH4 Edit")) * 25
				+ Double.parseDouble(data.get("N2O Edit")) * 298;
		System.out.println("calculated value" + valueCo2e);
		valCO2e = valueCo2e.toString();
	}

	public void ValidateEditRHPCEFValueswithActivitiesEditRHPEmployeeCommuting() {
		WebElement valuetCO2EC = driver
				.findElement(By.xpath("//span[text()='tCO2']//parent::p//following-sibling::p//div"));
		String scopeDes3_6txt = valuetCO2EC.getText().trim();
		String[] valuetCO2 = scopeDes3_6txt.split("\n");
		Double CO2 = Double.parseDouble(data.get("CO2 Edit"))
				* Double.parseDouble(data.get("Average Daily Commute Distance"))
				* Double.parseDouble(data.get(" Average Commute Days"))
				* Double.parseDouble(data.get(" Number of Employees Commuted")) / 1000;
		String tCO2Activity = CO2.toString();
		String tCO2ActivityRHP = approximateDecimalValueWithBigDecimal(tCO2Activity);
		if (tCO2ActivityRHP.equals(approximateDecimalValueWithBigDecimal(scopeDes3_6txt))) {
			passed("Succesfully validated tCO2 value" + tCO2ActivityRHP + " And Actual is "
					+ approximateDecimalValueWithBigDecimal(scopeDes3_6txt));
		} else {
			failed(driver, "Failed validated tCO2 value" + " " + tCO2ActivityRHP + " But Actual is "
					+ approximateDecimalValueWithBigDecimal(scopeDes3_6txt));
		}
		WebElement valueCH4EC = driver
				.findElement(By.xpath("//span[text()='tCH4']//parent::p//following-sibling::p//div"));
		String scopetCH4 = valueCH4EC.getText().trim();
		String[] valueCH4 = scopetCH4.split("\n");
		Double CH4 = Double.parseDouble(data.get("CH4 Edit"))
				* Double.parseDouble(data.get("Average Daily Commute Distance"))
				* Double.parseDouble(data.get(" Average Commute Days"))
				* Double.parseDouble(data.get(" Number of Employees Commuted")) / 1000;
		String CH4Activity = CH4.toString();
		String CH4ActivityRHP = approximateDecimalValueWithBigDecimal(CH4Activity);
		String valueCH4EComm = approximateDecimalValueWithBigDecimal(scopetCH4);
		if (CH4ActivityRHP.equals(valueCH4EComm)) {
			passed("Succesfully validated CH4 value" + CH4ActivityRHP + " And Actual is " + valueCH4EComm);
		} else {
			failed(driver, "Failed validated CH4 value" + " " + CH4ActivityRHP + " But Actual is " + valueCH4EComm);
		}
		WebElement valueN2OEC = driver
				.findElement(By.xpath("//span[text()='tN2O']//parent::p//following-sibling::p//div"));
		String scopetN2O = valueN2OEC.getText().trim();
		String[] valueN2O = scopetN2O.split("\n");
		Double N2O = Double.parseDouble(data.get("N2O Edit"))
				* Double.parseDouble(data.get("Average Daily Commute Distance"))
				* Double.parseDouble(data.get(" Average Commute Days"))
				* Double.parseDouble(data.get(" Number of Employees Commuted")) / 1000;
		String N2OActivity = N2O.toString();
		String N2OActivityRHP = approximateDecimalValueWithBigDecimal(N2OActivity);
		String valueN20EComm = approximateDecimalValueWithBigDecimal(scopetN2O);
		if (N2OActivityRHP.equals(valueN20EComm)) {
			passed("Succesfully validated N2O value" + N2OActivityRHP + " And Actual is " + valueN20EComm);
		} else {
			failed(driver, "Failed validated N2O value" + " " + N2OActivityRHP + " But Actual is " + valueN20EComm);
		}
		WebElement CO2eActivityEC = driver
				.findElement(By.xpath("//span[text()='tCO2e']//parent::p//following-sibling::p//div"));
		String scopetCO2e = CO2eActivityEC.getText().trim();
		String[] CO2eActivity = scopetCO2e.split("\n");
		Double ValCO2e = valueCo2e * Double.parseDouble(data.get("Average Daily Commute Distance"))
				* Double.parseDouble(data.get(" Average Commute Days"))
				* Double.parseDouble(data.get(" Number of Employees Commuted")) / 1000;
		String ValCO2eRHP = ValCO2e.toString();
		String CO2eActivityEc = approximateDecimalValueWithBigDecimal(scopetCO2e);
		String valuetCO2e = approximateDecimalValueWithBigDecimal(ValCO2eRHP);
		if (CO2eActivityEc.equals(valuetCO2e)) {
			passed("Succesfully validated tCO2e value" + valuetCO2e + " And Actual is " + CO2eActivityEc);
		} else {
			failed(driver, "Failed validated tCO2e value" + " " + valuetCO2e + " But Actual is " + CO2eActivityEc);
		}
		WebElement EFActivityEC = driver
				.findElement(By.xpath("//span[text()='Emission Factor']//parent::p//following-sibling::p//div"));
		String scopetEF = EFActivityEC.getText().trim();
		String[] CO2eActivity1 = scopetEF.split("\n");
		String[] EFValueRHP = CO2eActivity1[1].split(" ");
		String valuetEF = approximateDecimalValueWithBigDecimal(scopetEF);
		String valuetCO2eEF = approximateDecimalValueWithBigDecimal(valCO2e);
		if (valuetEF.equals(valCO2e)) {
			passed("Succesfully validated Emission factor value" + valuetCO2eEF + " And Actual is " + valuetEF);
		} else {
			failed(driver,
					"Failed validated Emission factor  value" + " " + valuetCO2eEF + " But Actual is " + valuetEF);
		}
	}

	public void verifyCO2eFactor() {
		WebElement cefValueRHP = driver
				.findElement(By.xpath("//span[contains(text(),'CO2e')]//parent::div//following-sibling::div//div"));
		String newPlainValue = approximateDecimalValueWithBigDecimal(cefValueRHP.getText());
		if (newPlainValue.equals(valCO2e)) {
			passed("Succesfully validated tCO2e value" + newPlainValue + " And Actual is " + valCO2e);
		} else {
			fail("Failed validated tCO2e value" + " " + newPlainValue + " But Actual is " + valCO2e);
		}
		WebElement weNameOfCEF = driver.findElement(
				By.xpath("//span[contains(text(),'Name of Custom EF')]//parent::div//following-sibling::div"));
		if (weNameOfCEF.getText().equals(CustomEmissionFactorName)) {
			passed("successfully validated name of custom emission factor");
		} else {
			fail("failed to validate name of custom emission factor");
		}
	}

	public String approximateDecimalValueWithBigDecimal(String value) {
		BigDecimal myBigDecimal = new BigDecimal(value);
		BigDecimal newValue = myBigDecimal.setScale(4, RoundingMode.DOWN).stripTrailingZeros();
		System.out.println(newValue);
		return newValue.toPlainString();
	}
	
	public void validateTOTALCO2EforEmployeeCommuting() {

		try {
			sleep(1);
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope 3.7 - Employee Commuting");
			if (Constants.selectedGWP.contains("2007 IPCC Fourth Assessment (AR4, 100-Year GWP)")) {
				WebElement arco2e = driver
						.findElement(By.xpath("(//p[text()=' Emission Factors']/../../following-sibling::div//p/span[text()='CO2']/../following-sibling::p)[1]"));
				String[] splitEF1 = arco2e.getText().split(" ");
				Double multi1 = Double.parseDouble(splitEF1[0]) * Constants.GWParCO2;
				WebElement arch4 = driver
						.findElement(By.xpath("(//span[contains(text(),'CH4')]//parent::p//following-sibling::p)[2]"));
				String[] splitEF2 = arch4.getText().split(" ");
				Double multi2 = Double.parseDouble(splitEF2[0]) * Constants.GWPar4CH4;
				WebElement arn2o = driver
						.findElement(By.xpath("(//span[contains(text(),'N2O')]//parent::p//following-sibling::p)[2]"));
				String[] splitEF3 = arn2o.getText().split(" ");
				Double multi3 = Double.parseDouble(splitEF3[0]) * Constants.GWPar4N2O;
				Double res7 = multi1 + multi2 + multi3;
				String str1 = res7.toString();
				String CalEF7 = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(str1);
				Double result7 = Double.parseDouble(CalEF7) * Double.parseDouble(data.get("Activity Amount")) / 1000;
				String calTco2e7 = result7.toString();
				WebElement valuetCO2e7 = driver
						.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
				GlobalVariables.exceptedtco2e=valuetCO2e7.getText().trim();

				if (calTco2e7.trim().equals(valuetCO2e7.getText().trim())) {
					passed("Successfully validated tCO2e value for : " + " Scope 2 - Indirect Emissions Actual "
							+ valuetCO2e7.getText() + " Expected tco2e " + calTco2e7);
				} else {
					failed(driver, "Failed to valiadte tCO2e value for : " + " Scope 2 - Indirect Emissions Actual"
							+ valuetCO2e7.getText() + " Expected tco2e " + calTco2e7);
				}
			} else if (Constants.selectedGWP.contains("2014 IPCC Fifth Assessment (AR5, 100-Year GWP)")) {
				WebElement arco2e = driver
						.findElement(By.xpath("(//span[contains(text(),'CO2')]//parent::p//following-sibling::p)[7]"));
				String[] splitEF1 = arco2e.getText().split(" ");
				Double multi1 = Double.parseDouble(splitEF1[0]) * Constants.GWParCO2;
				WebElement arch5 = driver
						.findElement(By.xpath("(//span[contains(text(),'CH4')]//parent::p//following-sibling::p)[3]"));
				String[] splitEF2 = arch5.getText().split(" ");
				Double multi2 = Double.parseDouble(splitEF2[0]) * Constants.GWPar5CH4;
				WebElement arn2o5 = driver
						.findElement(By.xpath("(//span[contains(text(),'N2O')]//parent::p//following-sibling::p)[3]"));
				String[] splitEF3 = arn2o5.getText().split(" ");
				Double multi3 = Double.parseDouble(splitEF3[0]) * Constants.GWPar5N2O;
				Double res8 = multi1 + multi2 + multi3;
				String str2 = res8.toString();
				String CalEF8 = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(str2);
				String[] splitEF8 = CalEF8.split(" ");
				Double result8 = Double.parseDouble(splitEF8[0]) * Double.parseDouble(data.get("Amount of Energy"))
						/ 1000;
				String calTco2e8 = result8.toString();
				WebElement valuetCO2e8 = driver
						.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
				if (calTco2e8.trim().equals(valuetCO2e8.getText().trim())) {
					passed("Successfully validated tCO2e value for : " + " Scope 2 - Indirect Emissions "
							+ valuetCO2e8.getText());
				} else {
					failed(driver, "Failed to valiadte tCO2e value for : " + " Scope 2 - Indirect Emissions "
							+ valuetCO2e8.getText());
				}
			} else if (Constants.selectedGWP.contains("2023 IPCC Sixth Assessment (AR6, 100-Year GWP)")) {
				WebElement arco2e = driver
						.findElement(By.xpath("(//span[contains(text(),'CO2')]//parent::p//following-sibling::p)[7]"));
				String[] splitEF1 = arco2e.getText().split(" ");
				Double multi1 = Double.parseDouble(splitEF1[0]) * Constants.GWParCO2;
				WebElement arch6 = driver
						.findElement(By.xpath("(//span[contains(text(),'CH4')]//parent::p//following-sibling::p)[3]"));
				String[] splitEF2 = arch6.getText().split(" ");
				Double multi2 = Double.parseDouble(splitEF2[0]) * Constants.GWPar6CH4;
				WebElement arn2o6 = driver
						.findElement(By.xpath("(//span[contains(text(),'N2O')]//parent::p//following-sibling::p)[3]"));
				String[] splitEF3 = arn2o6.getText().split(" ");
				Double multi3 = Double.parseDouble(splitEF3[0]) * Constants.GWPar6N2O;
				Double res8 = multi1 + multi2 + multi3;
				String str2 = res8.toString();
				String CalEF8 = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(str2);
				String[] splitEF8 = CalEF8.split(" ");
				Double result8 = Double.parseDouble(splitEF8[0]) * Double.parseDouble(data.get("Amount of Energy"))
						/ 1000;
				String calTco2e8 = result8.toString();
				WebElement valuetCO2e8 = driver
						.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
				GlobalVariables.exceptedtco2e=valuetCO2e8.getText().trim();
				if (calTco2e8.trim().equals(valuetCO2e8.getText().trim())) {
					passed("Successfully validated tCO2e value for : " + " Scope 3.7 - Employee Commuting "
							+ valuetCO2e8.getText());
				} else {
					failed(driver, "Failed to valiadte tCO2e value for : " + " Scope 3.7 - Employee Commuting "
							+ valuetCO2e8.getText());
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	
	}

	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(lblEmployeeCommuting);
			if (isElementPresent(lblEmployeeCommuting)) {
				passed("User Successfully Navigated To EmployeeCommuting Page");
			} else {
				failed(driver, "Failed To Navigate To EmployeeCommuting Page");
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
}
