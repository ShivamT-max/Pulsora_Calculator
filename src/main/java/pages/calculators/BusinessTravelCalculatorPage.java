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

public class BusinessTravelCalculatorPage extends CalculatorElements {
	public BusinessTravelCalculatorPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	String sourceOfEmissionFactor1;

	@FindBy(xpath = "//ul//li[text()='Sabancci Tag1']//*[@data-testid='CheckBoxOutlineBlankIcon']")
	private WebElement drptags;
	@FindBy(xpath = "//input[@id='tag_id']//parent::div//*[@data-testid='ArrowDropDownIcon']")
	private WebElement dptag;

	public void addActivityDetailsForBusinessTravelDistanceBasedCalculatorInActivityDetailsPannel() {
		try {
			Actions act = new Actions(driver);
			waitForElement(drpFacilityName);
			clickOn(drpFacilityName, "txtFacilityName");
			// enterText(drpFacilityName, "Facility Name", facilityName.get(i));
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Facility Name") + "']"));
			clickOn(we, data.get("Facility Name"));
			sleep(2000);
			clickOn(txtInvoiceNum, " InVoiceNo");
			enterText(txtInvoiceNum, "InvoiceNo", data.get("Invoice No."));
			clickOn(txtInvoiceDate, "Invoice date");
			enterText(txtInvoiceDate, "Invoice Date", data.get("Invoice Date"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			clickOn(txtNoOfEmpTravel, " NoOfEmpTravel");
			sleep(2000);
			enterText(txtNoOfEmpTravel, "numberOfEmployeeTraveled", data.get("Number of Employee Travelled"));
			// clickOn(txtTravelDescription, " Travel Description");
			// enterText(txtTravelDescription, "Travel Description", data.get("Travel
			// Description"));
			clickOn(drpModeOfBusinessTravel, "Mode Of Business Travel");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Mode of Business Travel") + "']"));
			clickOn(we, data.get("Mode of Business Travel"));
			sleep(2000);
			clickOn(drpVehicleType, "VehicalType");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Vehicle Type") + "']"));
			clickOn(we, data.get("Vehicle Type"));
			sleep(2000);
			clickOn(drpBusinessTravlUnits, "Business TravelUnits");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Units") + "']"));
			clickOn(we, data.get("Units"));
			sleep(2000);
			clickOn(txtDistanceTrvlByEmp, "DistanceTravelByEmp");
			enterText(txtDistanceTrvlByEmp, "DistanceTravelByEmp", data.get("Distance Travelled by Each Employee"));

			if (!data.get("Edit").equals("YES")) {
				clickOn(dptag, "Tags");
				WebElement selecttag = driver.findElement(By.xpath("(//ul[@aria-labelledby='tag_id-label']//li)[1]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", selecttag);
				act.moveToElement(selecttag).click().perform(); // clickOn(selecttag, "Tags");
			}

			clickOn(btnSave, "Save Button");
			sleep(1000);
			verifyAddActivityUpdatedToastMessage();
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

	public void validateaddActivityDetailsInViewActivityForBusinessTravelDistanceBasedCalculator() {
		try {
			// takeScreenshot(driver);
			String[] activityDetailFieldNames = { "Facility Name", "Invoice No.", "Invoice Date", "Start Date",
					"End Date", "Custom Emission Factor", "Distance Travelled by Each Employee",
					"Mode of Business Travel", "Vehicle Type", "Activity Amount", "Units", "CO2", "CH4", "N2O",
					"Biogenic CO2", "Source" };
			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				if (activityDetailFieldNames[j].equals("Biogenic CO2")) {
					WebElement weActivityField = driver.findElement(By.xpath("(//span[text()='"
							+ activityDetailFieldNames[j] + "']//parent::p//following-sibling::p)[2]"));
					if (weActivityField.getText().trim().equals(data.get(activityDetailFieldNames[j]))) {
						passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
								+ weActivityField.getText());
					} else {
						failed(driver,
								"Failed To validate " + activityDetailFieldNames[j]
										+ " In Activity Details Expected As " + data.get(activityDetailFieldNames[j])
										+ "But Actual is" + weActivityField.getText());
					}
				} else {
					WebElement weActivityField = driver.findElement(By.xpath(
							"//span[text()='" + activityDetailFieldNames[j] + "']//parent::p//following-sibling::p"));
					if (weActivityField.getText().trim().equals(data.get(activityDetailFieldNames[j]))) {
						passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
								+ weActivityField.getText());
					} else {
						failed(driver,
								"Failed To validate " + activityDetailFieldNames[j]
										+ " In Activity Details Expected As " + data.get(activityDetailFieldNames[j])
										+ "But Actual is" + weActivityField.getText());
					}
				}

			}
			WebElement NumberofEmployeeTravelled = driver.findElement(
					By.xpath("//span[text()='Number of Employee(s) Travelled']//parent::p//following-sibling::p"));
			String u = NumberofEmployeeTravelled.getText();
			String t = data.get("Number of Employee Travelled");
			if (u.equals(u.trim().equals(t))) {
				passed("Successfully Validated " + t + "In Activity Details As" + u);
			} else {
				failed(driver, "Failed To Validate " + t + "But Actual Is" + u);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateActivityDetailsInViewActivityForBusinessTravelDistanceBasedCalculatorCef() {
		try {
			// takeScreenshot(driver);
			String[] activityDetailFieldNames = { "Facility Name", "Number of Employee(s) Travelled",
					"Distance Travelled by Each Employee", "Mode of Business Travel", "Vehicle Type", "Activity Amount",
					"Units" };
			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
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

	public void addActivityDetailsForBusinessTravelSpendBasedCalculatorInActivityDetailsPannel() {
		try {
			sleep(3000);
			waitForElement(drpFacilityName);
			clickOn(drpFacilityName, "txtFacilityName");
//			enterText(drpFacilityName, "Facility Name", data.get("Facility Name"));
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Facility Name") + "']"));
			clickOn(we, data.get("Facility Name"));
			sleep(2000);
			clickOn(txtInvoiceNum, " InVoiceNo");
			enterText(txtInvoiceNum, "InvoiceNo", data.get("Expense No."));
			sleep(1000);
			clickOn(txtExpenseDate, "Invoice date");
			enterText(txtExpenseDate, "Invoice Date", data.get("Expense Date"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
//			clickOn(txtTravelDescription, " Travel Description");
//			enterText(txtTravelDescription, "Travel Description", data.get("Travel Description"));
			clickOn(drpModeOfBusinessTravel, "Mode Of Business Travel");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Mode of Business Travel") + "']"));
			clickOn(we, data.get("Mode of Business Travel"));
			sleep(2000);
			clickOn(drpTravelCategory, "Travel Category");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Travel Category") + "']"));
			clickOn(we, data.get("Travel Category"));
			sleep(2000);
			clickOn(txtAmountSpent, "Amount Spent");
			enterText(txtAmountSpent, "AmountSpent", data.get("Amount Spent"));
			clickOn(drpCurrency, "Currency");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Currency / Unit") + "']"));
			clickOn(we, data.get("Currency / Unit"));
			sleep(2000);
			clickOn(btnSave, "Save Button");
			sleep(4000);
			if (btnClose.isDisplayed()) {
				clickOn(btnClose, "");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void EditActivityScope3_6EmissionsCEfSpendBase() {
		try {
			waitForElement(drpFacilityName);
			clickOn(drpFacilityName, "txtFacilityName");
//			enterText(drpFacilityName, "Facility Name", data.get("Facility Name"));
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Facility Name") + "']"));
			clickOn(we, data.get("Facility Name"));
			sleep(2000);
			clickOn(txtInvoiceNum, " InVoiceNo");
			enterText(txtInvoiceNum, "InvoiceNo", data.get("Expense No."));
			sleep(1000);
			clickOn(txtExpenseDate, "Invoice date");
			enterText(txtExpenseDate, "Invoice Date", data.get("Expense Date"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			/*
			 * clickOn(txtTravelDescription, " Travel Description");
			 * enterText(txtTravelDescription, "Travel Description",
			 * data.get("Travel Description"));
			 */
			System.out.println(nameOfCEFSpendBase + "-----");
			validateCEFSpendBased();
			// String drpCEF = "//li[text()='" + nameOfCEFSpendBase + "']";
			// clickOnElementWithDynamicXpath(drpCEF, nameOfCEFSpendBase);
			// clickOn(drpModeOfBusinessTravel, "Mode Of Business Travel");
			// we = driver.findElement(By.xpath("//li[text()='" + data.get("Mode of Business
			// Travel") + "']"));
			// clickOn(we, data.get("Mode of Business Travel"));
			// sleep(2000);
			// clickOn(drpTravelCategory, "Travel Category");
			// we = driver.findElement(By.xpath("//li[text()='" + data.get("Travel
			// Category") + "']"));
			// clickOn(we, data.get("Travel Category"));
			// sleep(2000);
			clickOn(txtAmountSpent, "Amount Spent");
			enterText(txtAmountSpent, "AmountSpent", data.get("Amount Spent"));
//    	    clickOn(drpCurrency, "Currency");
//    	    we = driver.findElement(By.xpath("//li[text()='" + data.get("Currency / Unit") + "']"));
//    	    clickOn(we, data.get("Currency / Unit"));
			sleep(2000);
			clickOn(btnSave, "Save Button");
			sleep(3000);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public BusinessTravelCalculatorPage clickOnSpendbasedMethodInBusinessTravelCalculator() {
		clickOn(btnSpendBasedMethod, "SpendBasedMethodCalculator");
		return new BusinessTravelCalculatorPage(driver, data);
	}

	public void validateAddActivityDetailsInViewActivityForSpendBasedBusinessTravelCalculator() {
		try {
			takeScreenshot(driver);
			String[] activityDetailFieldNames = { "Facility Name", "Expense No.", "Expense Date", "Start Date",
					"End Date", "Custom Emission Factor", "Mode of Business Travel", "Travel Category", "Amount Spent",
					"Currency / Unit", "Emission Factor", "Source" };
			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
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

	public void validateActivityDetailsInViewActivityForSpendBasedBusinessTravelCalculatorCEF() {
		try {
			takeScreenshot(driver);
			String[] activityDetailFieldNames = { "Facility Name", "Expense No.", "Expense Date", "Start Date",
					"End Date", "Mode of Business Travel", "Travel Category", "Amount Spent", "Currency / Unit" };
			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
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

	public void EditActivityScope3_6EmissionsCEf() {
		try {
			waitForElement(drpFacilityName);
			clickOn(drpFacilityName, "txtFacilityName");
			// enterText(drpFacilityName, "Facility Name", facilityName.get(i));
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Facility Name") + "']"));
			clickOn(we, data.get("Facility Name"));
			sleep(2000);
			clickOn(txtInvoiceNum, " InVoiceNo");
			enterText(txtInvoiceNum, "InvoiceNo", data.get("Invoice No."));
			clickOn(txtInvoiceDate, "Invoice date");
			enterText(txtInvoiceDate, "Invoice Date", data.get("Invoice Date"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			clickOn(txtNoOfEmpTravel, " NoOfEmpTravel");
			enterText(txtNoOfEmpTravel, "numberOfEmployeeTraveled", data.get("Number of Employee(s) Travelled"));
			// clickOn(txtTravelDescription, " Travel Description");
			// enterText(txtTravelDescription, "Travel Description", data.get("Travel
			// Description"));
			validateCEF();
			// String drpCEF = "//li[text()='" + nameOfCEF + "']";
			// clickOnElementWithDynamicXpath(drpCEF, nameOfCEF);
			sleep(2000);
			// waitForElement(drpModeOfBusinessTravel);
			// clickOn(drpModeOfBusinessTravel, "Mode Of Business Travel");
			// sleep(1000);
			// we = driver.findElement(By.xpath("//li[text()='" + data.get("Mode of Business
			// Travel") + "']"));
			// clickOn(we, data.get("Mode of Business Travel"));
			// sleep(2000);
//			clickOn(drpVehicleType, "VehicalType");
//			we = driver.findElement(By.xpath("//li[text()='" + data.get("Vehicle Type") + "']"));
//			clickOn(we, data.get("Vehicle Type"));
//			sleep(2000);
//			clickOn(drpBusinessTravlUnits, "Business TravelUnits");
//			we = driver.findElement(By.xpath("//li[text()='" + data.get("Units") + "']"));
//			clickOn(we, data.get("Units"));
//			sleep(2000);
			clickOn(txtDistanceTrvlByEmp, "DistanceTravelByEmp");
			enterText(txtDistanceTrvlByEmp, "DistanceTravelByEmp", data.get("Distance Travelled by Each Employee"));
			clickOn(btnSave, "Save Button");
			sleep(10000);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void clickOnCEFInCEFGridMultiple() {
		sleep(3000);
		WebElement weActivity = driver.findElement(By.xpath("//*[text()='" + nameOfCEF + "']//parent::div"));
		clickOn(weActivity, "Activity of Facility" + nameOfCEF);
	}

	public void clickDistanceBasedBusinessTravel() {
		waitForElement(btnDistanceBasedMethod);
		clickOn(btnDistanceBasedMethod, "-");
	}

	String nameOfCEF;
	String sourceOfEmissionFactor;

	@FindBy(xpath = "//*[@id='Mode of Transport']")
	private WebElement modeofbusinessTravel;

	@FindBy(xpath = "//input[@placeholder='Vehicle Type/Commute Category']")
	private WebElement vehicleType;

	public void addCEFForBusinessTravel() {
		try {
			sleep(1000);
			clickOn(btnAddCEF, "Custom Emission Factor");
			verifyIfElementPresent(lblAddCEF, "CEF", "CEF");
			nameOfCEF = Constants.customEmissionFactor + generateRandomNumber(3);
			enterText(txtNameOfCustomEFBussiness, "name of custom emission factor", nameOfCEF);
			clickOn(drpCalculationMethodBussiness, "Calculation Method");
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Calculation Method") + "']"));
			clickOn(we, "Waste category" + data.get("Calculation Method"));
			sourceOfEmissionFactor = "SRCEF" + generateRandomNumber(3);
			enterText(txtSourceOfEmisson, "Source of Emission Factor", sourceOfEmissionFactor);
			clickOn(drpUnitOfCEFDenoBussines, "Unit of Custom EF (Denominator)");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Denominator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Denominator)"));
			enterText(txtCOBussiness, "co", data.get("CO2"));
			enterText(txtCH4BUssiness, "ch4", data.get("CH4"));
			enterText(txtN2OBussiness, "n20", data.get("N2O"));
//			 enterText(txtBioFuelBussiness, "biofuel", data.get("Biofuel CO2"));
//			 enterText(txtCO2eBussiness, "CO2e", data.get("CO2e"));
			clickOn(drpUnitofCustomEFNumeratorCEF, "waste disposal method");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Numerator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Numerator)"));
			sleep(2000);
			clickOn(modeofbusinessTravel, "Mode of Business Travel");
			enterText(modeofbusinessTravel, "Mode of Business Travel", data.get("Mode of Business Travel"));

			clickOn(vehicleType, "Vehicle Type/Commute Category");
			enterText(vehicleType, "Vehicle Type/Commute Category", data.get("Vehicle Type"));

			clickOn(btnSaveParemeterInput, "Save Custom Emission factor");
			CO2eFactor();
			sleep(2000);
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void addCEFForBusinessTravelEdit() {
		try {
			Actions mouseAction = new Actions(driver);
			sleep(1000);
			// clickOn(btnAddCEF, "Custom Emission Factor");
			verifyIfElementPresent(lblEditCEF, "CEF", "CEF");
			nameOfCEF = Constants.customEmissionFactor + generateRandomNumber(3);
			mouseAction.doubleClick(txtNameOfCustomEFBussiness).perform();
			enterText(txtNameOfCustomEFBussiness, "name of cusytom emission factor", nameOfCEF);
			clickOn(drpCalculationMethodBussiness, "Calculation Method");
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Calculation Method") + "']"));
			clickOn(we, "Waste category" + data.get("Calculation Method"));
			sourceOfEmissionFactor = "SRCEF" + generateRandomNumber(3);
			mouseAction.doubleClick(txtSourceOfEmisson).perform();
			enterText(txtSourceOfEmisson, "Source of Emission Factor", sourceOfEmissionFactor);
			clickOn(drpUnitOfCEFDenoBussines, "Unit of Custom EF (Denominator)");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Denominator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Denominator)"));
			enterText(txtCOBussiness, "co", data.get("CO2Edit"));
			enterText(txtCH4BUssiness, "ch4", data.get("CH4Edit"));
			enterText(txtN2OBussiness, "n20", data.get("N2OEdit"));
//			 enterText(txtBioFuelBussiness, "biofuel", data.get("Biofuel CO2"));
//			 enterText(txtCO2eBussiness, "CO2e", data.get("CO2e"));
			clickOn(drpUnitofCustomEFNumeratorCEF, "drop Unit of Custom EF Numerator CEF");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Numerator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Numerator)"));

			clickOn(modeofbusinessTravel, "Mode of Business Travel");
			enterText(modeofbusinessTravel, "Mode of Business Travel", data.get("Mode of Business Travel"));

			clickOn(vehicleType, "Vehicle Type/Commute Category");
			enterText(vehicleType, "Vehicle Type/Commute Category", data.get("Vehicle Type"));

			sleep(2000);
			clickOn(btnSaveParemeterInput, "Save Custom Emission factor");
			CO2eFactorEdit();
			sleep(4000);
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void validateCEF() {
		sleep(1000);
		jsClick(chbxCustomEmssionFactorFuel, "yes");
		verifyIfElementPresent(drpCustomEmiFactorActivityBussiness, "--", "--");
		clickOn(drpCustomEmiFactorActivityBussiness, "-");
		WebElement namecef = driver.findElement(By.xpath("//ul//li[text()='" + nameOfCEF + "']"));
		JavascriptExecutor jsExc = (JavascriptExecutor) driver;
		jsExc.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow');", namecef);
		clickOn(namecef, "nameOfCEFDrpOptions");
		sleep(1000);
		List<WebElement> dropDwonValues = driver.findElements(By.xpath("//input[@id='Emission']"));
		for (int i = 0; i < dropDwonValues.size(); i++) {
			if (dropDwonValues.get(i).getAttribute("value").contains(nameOfCEF)) {
				passed("Successfully validated custom emission factor name in avtivities of scope 2");
				break;
			} else {
				failed(driver, "failed to validated custom emission factor name in avtivities of scope 2");
			}
		}
	}

	String nameOfCEFSpendBase;

	public void addCEFForBusinessTravelSpendBased() {
		try {
			sleep(3000);
			clickOn(btnAddCEF, "Custom Emission Factor");
			verifyIfElementPresent(lblAddCEF, "CEF", "CEF");
			nameOfCEFSpendBase = "TestAut" + generateRandomNumber(3);
			System.out.println(nameOfCEFSpendBase);
			enterText(txtNameOfCustomEFBussiness, "name of cusytom emission factor", nameOfCEFSpendBase);
			clickOn(drpCalculationMethodBussiness, "Calculation Method");
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Calculation Method") + "']"));
			clickOn(we, "Waste category" + data.get("Calculation Method"));
			sourceOfEmissionFactor1 = "SRCEF" + "_" + generateRandomNumber(3);
			enterText(txtSourceOfEmisson, "Source of Emission Factor", sourceOfEmissionFactor1);
			clickOn(drpUnitOfCEFDenoBussines, "Unit of Custom EF (Denominator)");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Denominator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Denominator)"));
//			 enterText(txtCOBussiness, "co", data.get("CO2"));
//			 enterText(txtCH4BUssiness, "ch4", data.get("CH4"));
//			 enterText(txtN2OBussiness, "n20", data.get("N2O"));
//			 enterText(txtBioFuelBussiness, "biofuel", data.get("Biofuel CO2"));
			enterText(txtCO2eBussiness, "CO2e", data.get("CO2e"));
			clickOn(drpUnitofCustomEFNumeratorCEF, "waste disposal method");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Numerator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Numerator)"));
			clickOn(modeofbusinessTravel, "Mode of Business Travel");
			enterText(modeofbusinessTravel, "Mode of Business Travel", data.get("Mode of Business Travel"));
			clickOn(vehicleType, "Vehicle Type/Commute Category");
			enterText(vehicleType, "Vehicle Type/Commute Category", data.get("Vehicle Type"));
			sleep(2000);
			clickOn(btnSaveParemeterInput, "Save Custom Emission factor");
			System.out.println(nameOfCEFSpendBase);
			sleep(2000);
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void validateCEFSpendBased() {
		sleep(1000);
		System.out.println(nameOfCEFSpendBase + "" + "pribted");
		jsClick(chbxCustomEmssionFactorFuel, "yes");
		verifyIfElementPresent(drpCustomEmiFactorActivityBussiness, "--", "--");
		clickOn(drpCustomEmiFactorActivityBussiness, "-");
		sleep(1000);
		WebElement namecefSpendBase = driver.findElement(By.xpath("//ul//li[text()='" + nameOfCEFSpendBase + "']"));
		jsClick(namecefSpendBase, nameOfCEFSpendBase);
		// JavascriptExecutor jsExc = (JavascriptExecutor)driver;
		// jsExc.executeScript("arguments[0].setAttribute('style', 'border:2px solid
		// red; background:yellow');", nameOfCEFSpendBase);
		sleep(2000);
		List<WebElement> dropDwonValues = driver.findElements(By.xpath("//ul/li"));
		for (int i = 0; i < dropDwonValues.size(); i++) {
			if (dropDwonValues.get(i).getText().contains(nameOfCEFSpendBase)) {
				passed("Successfully validated custom emission factor name in avtivities");
				break;
			} else {
				failed(driver, "failed to validated custom emission factor name in avtivities");
			}
		}
	}

	public void ValidateCEFScope3_6() {
		try {
			sleep(1000);
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
				passed("successfully validated source in RHP as actual is" + cstomEmissionSource.getText() + " "
						+ "and Expected is" + " " + sourceOfEmissionFactor);
			} else {
				fail("failed to validate source in RHP as actual is" + " " + cstomEmissionSource.getText() + " "
						+ "and Expected is" + " " + sourceOfEmissionFactor);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void ValidateCEFScope3_6SpendBase() {
		try {
			sleep(1000);
			WebElement cstomEmission = driver.findElement(
					By.xpath("//span[contains(text(),'Custom Emission')]//parent::p//following-sibling::p"));
			WebElement cstomEmissionSource = driver
					.findElement(By.xpath("//span[contains(text(),'Source')]//parent::p//following-sibling::p"));
			System.out.println(cstomEmission.getText());
			if (cstomEmission.getText().equals(nameOfCEFSpendBase)) {
				passed("successfully validated CEF in RHP as actual is" + cstomEmission.getText() + " "
						+ "and Expected is" + " " + nameOfCEFSpendBase);
			} else {
				fail("failed to validate CEF in RHP as actual is" + " " + cstomEmission.getText() + " "
						+ "and Expected is" + " " + nameOfCEFSpendBase);
			}
			if (cstomEmissionSource.getText().equals(sourceOfEmissionFactor1)) {
				passed("successfully validated source in RHP as actual is" + cstomEmissionSource.getText() + " "
						+ "and Expected is" + " " + sourceOfEmissionFactor1);
			} else {
				fail("failed to validate source in RHP as actual is" + " " + cstomEmissionSource.getText() + " "
						+ "and Expected is" + " " + sourceOfEmissionFactor1);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	Double valueCo2e;
	String v;
	String co2eFactor;

	public void CO2eFactor() {
		valueCo2e = Double.parseDouble(data.get("CO2")) + Double.parseDouble(data.get("CH4")) * 25
				+ Double.parseDouble(data.get("N2O")) * 298;
		System.out.println("calculated value" + valueCo2e);
		v = valueCo2e.toString();
		co2eFactor = v + " " + data.get("Unit of Custom EF(Num)") + " " + "CO2e" + "/"
				+ data.get("Unit of Custom EF(Den)");
	}

	String m;

	public void CO2eFactorEdit() {
		valueCo2e = Double.parseDouble(data.get("CO2Edit")) + Double.parseDouble(data.get("CH4Edit")) * 25
				+ Double.parseDouble(data.get("N2OEdit")) * 298;
		System.out.println("calculated value" + valueCo2e);
		m = valueCo2e.toString();
		co2eFactor = m + " " + data.get("Unit of Custom EF(Num)") + " " + "CO2e" + "/"
				+ data.get("Unit of Custom EF(Den)");
	}

	public void verifyCO2eFactor() {
		WebElement co2value = driver.findElement(By.xpath("//span[text()='CO2']//parent::div//following-sibling::div"));
		WebElement ch4value = driver.findElement(By.xpath("//span[text()='CH4']//parent::div//following-sibling::div"));
		WebElement n2ovalue = driver.findElement(By.xpath("//span[text()='N2O']//parent::div//following-sibling::div"));
		if (co2value.getText().equals(data.get("CO2"))) {
			passed("Successfully validated co2 value as expected " + data.get("CO2") + "and actual is"
					+ co2value.getText());
		} else {
			failed(driver, "failed to validate co2 value as expected " + data.get("CO2") + "and actual is"
					+ co2value.getText());
		}
		if (ch4value.getText().equals(data.get("CH4"))) {
			passed("Successfully validated ch4 value as expected " + data.get("CH4") + "and actual is"
					+ ch4value.getText());
		} else {
			failed(driver, "failed to validate ch4 value as expected " + data.get("CH4") + "and actual is"
					+ ch4value.getText());
		}
		if (n2ovalue.getText().equals(data.get("N2O"))) {
			passed("Successfully validated n2o value as expected " + data.get("N2O") + "and actual is"
					+ n2ovalue.getText());
		} else {
			failed(driver, "failed to validate n2o value as expected " + data.get("N2O") + "and actual is"
					+ n2ovalue.getText());
		}
		WebElement cefValueRHP = driver
				.findElement(By.xpath("//span[contains(text(),'CO2e')]//parent::div//following-sibling::div"));
		String newPlainValue = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(cefValueRHP.getText());
		if (newPlainValue.equals(GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(v))) {
			passed("Succesfully validated tCO2e value" + newPlainValue + "--" + v);
		} else {
			fail("Failed validated tCO2e value" + " " + newPlainValue + "--" + v);
		}
		WebElement weNameOfCEF = driver.findElement(
				By.xpath("//span[contains(text(),'Name of Custom EF')]//parent::div//following-sibling::div"));
		WebElement weSourceOfCEF = driver.findElement(
				By.xpath("//span[contains(text(),'Source of Emission Factor')]//parent::div//following-sibling::div"));
		if (weNameOfCEF.getText().equals(nameOfCEF)) {
			passed("successfully validated name of custom emission factor");
		} else {
			fail("failed to validate name of custom emission factor");
		}
		if (weSourceOfCEF.getText().equals(sourceOfEmissionFactor)) {
			passed("successfully validated source of custom emission factor");
		} else {
			fail("failed to validate source of custom emission factor");
		}
		String[] activityDetailFieldNames = { "Calculation Method", "Unit of Custom EF (Denominator)",
				"Unit of Custom EF (Numerator)" };
		sleep(1000);
		verifyIfElementPresent(lblCEFDetails, "lblCEF", "Indirect emission");
		for (int j = 0; j < activityDetailFieldNames.length; j++) {
			WebElement weActivityField = driver.findElement(By.xpath(
					"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
			if (weActivityField.getText().trim().contains(data.get(activityDetailFieldNames[j]).trim())) {
				passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
						+ weActivityField.getText());
			} else {
				failed(driver, "Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
						+ data.get(activityDetailFieldNames[j]) + "But Actual is" + weActivityField.getText());
			}
		}
	}

	public void verifyCO2eFactorEdit() {
		WebElement co2value = driver.findElement(By.xpath("//span[text()='CO2']//parent::div//following-sibling::div"));
		WebElement ch4value = driver.findElement(By.xpath("//span[text()='CH4']//parent::div//following-sibling::div"));
		WebElement n2ovalue = driver.findElement(By.xpath("//span[text()='N2O']//parent::div//following-sibling::div"));
		if (co2value.getText().equals(data.get("CO2Edit"))) {
			passed("Successfully validated co2 value as expected " + data.get("CO2Edit") + "and actual is"
					+ co2value.getText());
		} else {
			failed(driver, "failed to validate co2 value as expected " + data.get("CO2Edit") + "and actual is"
					+ co2value.getText());
		}
		if (ch4value.getText().equals(data.get("CH4Edit"))) {
			passed("Successfully validated ch4 value as expected " + data.get("CH4Edit") + "and actual is"
					+ ch4value.getText());
		} else {
			failed(driver, "failed to validate ch4 value as expected " + data.get("CH4Edit") + "and actual is"
					+ ch4value.getText());
		}
		if (n2ovalue.getText().equals(data.get("N2OEdit"))) {
			passed("Successfully validated n2o value as expected " + data.get("N2OEdit") + "and actual is"
					+ n2ovalue.getText());
		} else {
			failed(driver, "failed to validate n2o value as expected " + data.get("N2OEdit") + "and actual is"
					+ n2ovalue.getText());
		}
		WebElement cefValueRHP = driver
				.findElement(By.xpath("//span[contains(text(),'CO2e')]//parent::div//following-sibling::div"));
		String newPlainValue = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(cefValueRHP.getText());
		if (newPlainValue.equals(GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(m))) {
			passed("Succesfully validated tCO2e value" + newPlainValue + "--" + m);
		} else {
			fail("Failed validated tCO2e value" + " " + newPlainValue + "--" + m);
		}
		WebElement weNameOfCEF = driver.findElement(
				By.xpath("//span[contains(text(),'Name of Custom EF')]//parent::div//following-sibling::div"));
		WebElement weSourceOfCEF = driver.findElement(
				By.xpath("//span[contains(text(),'Source of Emission Factor')]//parent::div//following-sibling::div"));
		if (weNameOfCEF.getText().equals(nameOfCEF)) {
			passed("successfully validated name of custom emission factor");
		} else {
			fail("failed to validate name of custom emission factor");
		}
		if (weSourceOfCEF.getText().equals(sourceOfEmissionFactor)) {
			passed("successfully validated source of custom emission factor");
		} else {
			fail("failed to validate source of custom emission factor");
		}
		String[] activityDetailFieldNames = { "Calculation Method", "Unit of Custom EF (Denominator)",
				"Unit of Custom EF (Numerator)" };
		sleep(1000);
		verifyIfElementPresent(lblCEFDetails, "lblCEF", "Indirect emission");
		for (int j = 0; j < activityDetailFieldNames.length; j++) {
			WebElement weActivityField = driver.findElement(By.xpath(
					"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
			if (weActivityField.getText().trim().contains(data.get(activityDetailFieldNames[j]).trim())) {
				passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
						+ weActivityField.getText());
			} else {
				failed(driver, "Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
						+ data.get(activityDetailFieldNames[j]) + "But Actual is" + weActivityField.getText());
			}
		}
	}

	public void ValidateRHPCEFValueswithActivitiesRHP() {
		// --tco2e
		WebElement valuetCO2 = driver
				.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
		WebElement activityAmount = driver
				.findElement(By.xpath("//span[contains(text(),'Activity Amount')]//parent::p//following-sibling::p"));
		Double CO2 = valueCo2e * Double.parseDouble(activityAmount.getText());
		String tCO2ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(CO2.toString());
		if (tCO2ActivityRHP.equals(GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(valuetCO2.getText()))) {
			passed("Succesfully validated tCO2e value" + tCO2ActivityRHP + " And Actual is " + valuetCO2.getText());
		} else {
			fail("Failed validated tCO2e value" + " " + tCO2ActivityRHP + " But Actual is " + valuetCO2.getText());
		}
		// ----ch4
		WebElement valueCH4 = driver
				.findElement(By.xpath("//span[contains(text(),'tCH4')]//parent::p//following-sibling::p"));
		Double CH4 = Double.parseDouble(data.get("CH4")) * Double.parseDouble(activityAmount.getText());
		String CH4ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(CH4.toString());
		if (CH4ActivityRHP.equals(GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(valueCH4.getText()))) {
			passed("Succesfully validated CH4 value" + CH4ActivityRHP + " And Actual is " + valueCH4.getText());
		} else {
			fail("Failed validated CH4 value" + " " + CH4ActivityRHP + " But Actual is " + valueCH4.getText());
		}
		// ---n2o
		WebElement valueN2O = driver
				.findElement(By.xpath("//span[contains(text(),'tN2O')]//parent::p//following-sibling::p"));
		Double N2O = Double.parseDouble(data.get("N2O")) * Double.parseDouble(activityAmount.getText());
		String N2OActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(N2O.toString());
		if (N2OActivityRHP.equals(GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(valueN2O.getText()))) {
			passed("Succesfully validated N2O value" + N2OActivityRHP + " And Actual is " + valueN2O.getText());
		} else {
			fail("Failed validated N2O value" + " " + N2OActivityRHP + " But Actual is " + valueN2O.getText());
		}
		// --tco2
		WebElement CO2eActivity = driver
				.findElement(By.xpath("//span[text()='tCO2']//parent::p//following-sibling::p"));
		Double ValCO2e = Double.parseDouble(data.get("CO2")) * Double.parseDouble(activityAmount.getText());
		String ValCO2eRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(ValCO2e.toString());
		if (GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(CO2eActivity.getText()).equals(ValCO2eRHP)) {
			passed("Succesfully validated tCO2 value" + ValCO2eRHP + " And Actual is " + CO2eActivity.getText());
		} else {
			fail("Failed validated tCO2 value" + " " + ValCO2eRHP + " But Actual is " + CO2eActivity.getText());
		}
		WebElement EFActivity = driver
				.findElement(By.xpath("//span[text()='Emission Factor']//parent::p//following-sibling::p"));
		String s2 = EFActivity.getText().trim();
		String[] EFValueRHP = s2.split(" ");
		if (GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(EFValueRHP[0])
				.equals(GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(v))) {
			passed("Succesfully validated emission factor Expected value" + " " + v + " And Actual is "
					+ EFValueRHP[0]);
		} else {
			fail("Failed validated emission factor Expected value" + " " + v + " But Actual is " + EFValueRHP[0]);
		}
	}

	public void ValidateRHPCEFValueswithActivitiesRHPEdit() {
		// --tco2e
		WebElement valuetCO2 = driver
				.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
		WebElement activityAmount = driver
				.findElement(By.xpath("//span[contains(text(),'Activity Amount')]//parent::p//following-sibling::p"));
		Double CO2 = valueCo2e * Double.parseDouble(activityAmount.getText());
		String tCO2ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(CO2.toString());
		if (tCO2ActivityRHP.equals(GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(valuetCO2.getText()))) {
			passed("Succesfully validated tCO2e value" + tCO2ActivityRHP + " And Actual is " + valuetCO2.getText());
		} else {
			fail("Failed validated tCO2e value" + " " + tCO2ActivityRHP + " But Actual is " + valuetCO2.getText());
		}
		// ----ch4
		WebElement valueCH4 = driver
				.findElement(By.xpath("//span[contains(text(),'tCH4')]//parent::p//following-sibling::p"));
		Double CH4 = Double.parseDouble(data.get("CH4Edit")) * Double.parseDouble(activityAmount.getText());
		String CH4ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(CH4.toString());
		if (CH4ActivityRHP.equals(GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(valueCH4.getText()))) {
			passed("Succesfully validated CH4 value" + CH4ActivityRHP + " And Actual is " + valueCH4.getText());
		} else {
			fail("Failed validated CH4 value" + " " + CH4ActivityRHP + " But Actual is " + valueCH4.getText());
		}
		// ---n2o
		WebElement valueN2O = driver
				.findElement(By.xpath("//span[contains(text(),'tN2O')]//parent::p//following-sibling::p"));
		Double N2O = Double.parseDouble(data.get("N2OEdit")) * Double.parseDouble(activityAmount.getText());
		String N2OActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(N2O.toString());
		if (N2OActivityRHP.equals(GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(valueN2O.getText()))) {
			passed("Succesfully validated N2O value" + N2OActivityRHP + " And Actual is " + valueN2O.getText());
		} else {
			fail("Failed validated N2O value" + " " + N2OActivityRHP + " But Actual is " + valueN2O.getText());
		}
		// --tco2
		WebElement CO2eActivity = driver
				.findElement(By.xpath("//span[text()='tCO2']//parent::p//following-sibling::p"));
		Double ValCO2e = Double.parseDouble(data.get("CO2Edit")) * Double.parseDouble(activityAmount.getText());
		String ValCO2eRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(ValCO2e.toString());
		if (GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(CO2eActivity.getText()).equals(ValCO2eRHP)) {
			passed("Succesfully validated tCO2 value" + ValCO2eRHP + " And Actual is " + CO2eActivity.getText());
		} else {
			fail("Failed validated tCO2 value" + " " + ValCO2eRHP + " But Actual is " + CO2eActivity.getText());
		}
		WebElement EFActivity = driver
				.findElement(By.xpath("//span[text()='Emission Factor']//parent::p//following-sibling::p"));
		String s2 = EFActivity.getText().trim();
		String[] EFValueRHP = s2.split(" ");
		if (GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(EFValueRHP[0]).equals(m)) {
			passed("Succesfully validated emission factor Expected value" + " " + m + " And Actual is "
					+ EFValueRHP[0]);
		} else {
			fail("Failed validated emission factor Expected value" + " " + m + " But Actual is " + EFValueRHP[0]);
		}
	}

	public void ValidateCEFDetailsInViewCEF() {
		try {
			WebElement weNameOfCEF = driver.findElement(
					By.xpath("//span[contains(text(),'Name of Custom EF')]//parent::div//following-sibling::div/div"));
			WebElement weSourceOfCEF = driver.findElement(
					By.xpath("//span[contains(text(),'Source')]//parent::div//following-sibling::div/div"));
			if (weNameOfCEF.getText().equals(nameOfCEFSpendBase)) {
				passed("successfully validated name of custom emission factor as expected" + nameOfCEFSpendBase
						+ "and actual is" + weNameOfCEF.getText());
			} else {
				fail("failed to validated name of custom emission factor as expected" + nameOfCEFSpendBase
						+ "and actual is" + weNameOfCEF.getText());
			}
			if (weSourceOfCEF.getText().equals(sourceOfEmissionFactor1)) {
				passed("successfully validated source of custom emission factor");
			} else {
				fail("failed to validate source of custom emission factor");
			}
			String[] activityDetailFieldNames = { "Calculation Method", "Unit of Custom EF (Denominator)", "CO2", "CH4",
					"N2O", "Biofuel CO2", "Unit of Custom EF (Numerator)" };
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

	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(lblBusinessTravel);
			if (isElementPresent(lblBusinessTravel)) {
				passed("User Successfully Navigated To Business Travel Calc Page");
			} else {
				failed(driver, "Failed To Navigate To  Business Travel Calc Page");
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateTOTALCO2EforBTSpendBased() {
		try {
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Business Travel");
			WebElement ee = driver
					.findElement(By.xpath("//span[contains(text(),'Conversion')]//parent::p//following-sibling::p"));
			String hh = ee.getText().replaceAll(",", "");
			String[] yy = hh.split("=");
			String[] t = yy[1].split(" ");
			Double d = Double.parseDouble(t[1]);
			WebElement emissionFactor6 = driver.findElement(
					By.xpath("(//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p)[2]"));
			String[] splitEF6 = emissionFactor6.getText().trim().replaceAll(",", "").split(" ");
			Double result6 = (Double.parseDouble(splitEF6[0]) * Double.parseDouble(data.get("Amount Spent")) / d)
					/ 1000;
			String res6 = result6.toString();
			String CalCo2e6 = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(res6);
			WebElement valuetCO2e6 = driver
					.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
			GlobalVariables.exceptedtco2e = valuetCO2e6.getText().trim();

			if (CalCo2e6.trim().equals(valuetCO2e6.getText().trim())) {
				passed("Successfully validated tCO2e value for : " + " Business Travel-Spend Based "
						+ valuetCO2e6.getText());
			} else {
				failed(driver, "Failed to valiadte tCO2e value for : " + " Business Travel-Spend Based "
						+ valuetCO2e6.getText() + "actual is " + CalCo2e6);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());

		}
	}

	public void validateTOTALCO2EforBusinessDistanceBased() {

		try {
			String calTco2e7 = "";
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", " Business Travel-Distance Based ");
			if (Constants.selectedGWP.contains("2007 IPCC Fourth Assessment (AR4, 100-Year GWP)")) {
				double convertedEmissionFactorCO2 = 0;
				double convertedEmissionFactorCH4 = 0;
				double convertedEmissionFactorN2O = 0;
				WebElement arco2e = driver.findElement(By.xpath(
						"//*[text()=' Emission Factors']//parent::div//parent::div//following-sibling::div/div//*[contains(text(),'CO2')]//parent::p//following-sibling::p"));
				String[] splitEF1 = arco2e.getText().split(" ");
				String[] numUnitOfEmissionFactorCO2 = splitEF1[1].split("/");
				if (numUnitOfEmissionFactorCO2[0].equals("kg")) {
					convertedEmissionFactorCO2 = Double.parseDouble(splitEF1[0]);
				} else if (numUnitOfEmissionFactorCO2[0].equals("g")) {
					convertedEmissionFactorCO2 = Double.parseDouble(splitEF1[0]) * Constants.confromgramtoKg;
				}
				Double multi1 = convertedEmissionFactorCO2 * Constants.GWParCO2;
				WebElement arch4 = driver.findElement(By.xpath(
						"//*[contains(text(),' Emission Factors')]//parent::div//parent::div//following-sibling::div//div//following-sibling::div//*[contains(text(),'CH4')]//parent::p//following-sibling::p"));
				String[] splitEF2 = arch4.getText().split(" ");
				String[] numUnitOfEmissionFactorCH4 = splitEF2[1].split("/");
				if (numUnitOfEmissionFactorCH4[0].equals("kg")) {
					convertedEmissionFactorCH4 = Double.parseDouble(splitEF2[0]);
				} else if (numUnitOfEmissionFactorCH4[0].equals("g")) {
					convertedEmissionFactorCH4 = Double.parseDouble(splitEF2[0]) * Constants.confromgramtoKg;
				}
				Double multi2 = convertedEmissionFactorCH4 * Constants.GWPar4CH4;
				WebElement arn2o4 = driver.findElement(By.xpath(
						"(//*[contains(text(),' Emission Factors')]//parent::div//parent::div//following-sibling::div)[2]//div//*[contains(text(),'N2O')]//parent::p//following-sibling::p"));
				String[] splitEF3 = arn2o4.getText().split(" ");
				String[] numUnitOfEmissionFactorN2O = splitEF3[1].split("/");
				if (numUnitOfEmissionFactorN2O[0].equals("kg")) {
					convertedEmissionFactorN2O = Double.parseDouble(splitEF3[0]);
				} else if (numUnitOfEmissionFactorCH4[0].equals("g")) {
					convertedEmissionFactorN2O = Double.parseDouble(splitEF3[0]) * Constants.confromgramtoKg;
				}
				Double multi3 = convertedEmissionFactorN2O * Constants.GWPar4N2O;
				Double res7 = multi1 + multi2 + multi3;
				String str1 = res7.toString();
				String CalEF7 = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(str1);
				WebElement emissionfactor = driver.findElement(
						By.xpath("(//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p)[2]"));
				String[] splitEF4 = emissionfactor.getText().split(" ");
				if (splitEF4[0].trim().equals(CalEF7)) {
					passed("Successfully validated EmissionFactor for loaction based as " + str1);
				} else {
					failed(driver, "Failed to validate EmissionFactor for loaction based as " + str1 + " but Actual is "
							+ splitEF4[0].trim());
				}
				WebElement activityamt = driver.findElement(
						By.xpath("(//span[contains(text(),'Amount')]//parent::p//following-sibling::p)[1]"));
				String am = activityamt.getText().replaceAll(",", "");

				WebElement ee = driver.findElement(
						By.xpath("//span[contains(text(),'Conversion')]//parent::p//following-sibling::p"));
				String hh = ee.getText().replaceAll(",", "");
				String[] yy = hh.split("=");
				String[] t = yy[1].split(" ");
				Double d = Double.parseDouble(t[1]);
				if (splitEF4[1].trim().equals(Constants.conToTonnfrlb)) {
					Double result7 = (Double.parseDouble(CalEF7) * Double.parseDouble(am) * d) / 2204.623;
					calTco2e7 = result7.toString();
				} else if (splitEF4[1].trim().equals(Constants.conToTonnfrKg)) {
					Double result7 = (Double.parseDouble(CalEF7) * Double.parseDouble(am) * d) / 1000;
					calTco2e7 = result7.toString();
				} else if (splitEF4[1].trim().equals(Constants.conToTonnfrg)) {
					Double result7 = (Double.parseDouble(CalEF7) * Double.parseDouble(am) * d) / 1000000;
					calTco2e7 = result7.toString();
				}else if(splitEF4[1].trim().equals(Constants.conToTonnfrTonn)) {
					Double result7 = Double.parseDouble(CalEF7) * Double.parseDouble(am) * d;
					calTco2e7 = result7.toString();
				}
				WebElement valuetCO2e7 = driver
						.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
				GlobalVariables.exceptedtco2e = valuetCO2e7.getText().trim();
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
					passed("Successfully validated tCO2e value for : " + " Business Travel-Distance Based "
							+ valuetCO2e8.getText());
				} else {
					failed(driver, "Failed to valiadte tCO2e value for : " + " Business Travel-Distance Based "
							+ valuetCO2e8.getText());
				}
			} else if (Constants.selectedGWP.contains("2023 IPCC Sixth Assessment (AR6, 100-Year GWP)")) { // (//span[contains(text(),'CO2')]//parent::p//following-sibling::p)[7]
				sleep(100);
				double convertedEmissionFactorCO2 = 0;
				double convertedEmissionFactorCH4 = 0;
				double convertedEmissionFactorN2O = 0;
				WebElement arco2e = driver.findElement(By.xpath(
						"//*[text()=' Emission Factors']//parent::div//parent::div//following-sibling::div/div//*[contains(text(),'CO2')]//parent::p//following-sibling::p"));
				String[] splitEF1 = arco2e.getText().split(" ");
				String[] numUnitOfEmissionFactorCO2 = splitEF1[1].split("/");
				if (numUnitOfEmissionFactorCO2[0].equals("kg")) {
					convertedEmissionFactorCO2 = Double.parseDouble(splitEF1[0]);
				} else if (numUnitOfEmissionFactorCO2[0].equals("g")) {
					convertedEmissionFactorCO2 = Double.parseDouble(splitEF1[0]) * Constants.confromgramtoKg;
				}
				Double multi1 = convertedEmissionFactorCO2 * Constants.GWParCO2;
				WebElement arch6 = driver.findElement(By.xpath(
						"//*[contains(text(),' Emission Factors')]//parent::div//parent::div//following-sibling::div//div//following-sibling::div//*[contains(text(),'CH4')]//parent::p//following-sibling::p"));
				String[] splitEF2 = arch6.getText().split(" ");
				String[] numUnitOfEmissionFactorCH4 = splitEF2[1].split("/");
				if (numUnitOfEmissionFactorCH4[0].equals("kg")) {
					convertedEmissionFactorCH4 = Double.parseDouble(splitEF2[0]);
				} else if (numUnitOfEmissionFactorCH4[0].equals("g")) {
					convertedEmissionFactorCH4 = Double.parseDouble(splitEF2[0]) * Constants.confromgramtoKg;
				}
				Double multi2 = convertedEmissionFactorCH4 * Constants.GWPar6CH4;
				WebElement arn2o6 = driver.findElement(By.xpath(
						"(//*[contains(text(),' Emission Factors')]//parent::div//parent::div//following-sibling::div)[2]//div//*[contains(text(),'N2O')]//parent::p//following-sibling::p"));
				String[] splitEF3 = arn2o6.getText().split(" ");
				String[] numUnitOfEmissionFactorN2O = splitEF3[1].split("/");
				if (numUnitOfEmissionFactorN2O[0].equals("kg")) {
					convertedEmissionFactorN2O = Double.parseDouble(splitEF3[0]);
				} else if (numUnitOfEmissionFactorCH4[0].equals("g")) {
					convertedEmissionFactorN2O = Double.parseDouble(splitEF3[0]) * Constants.confromgramtoKg;
				}
				Double multi3 = convertedEmissionFactorN2O * Constants.GWPar6N2O;
				Double res8 = multi1 + multi2 + multi3;
				String str2 = res8.toString();
				String CalEF8 = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(str2);
				WebElement emissionFactor = driver.findElement(
						By.xpath("(//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p)[2]"));
				String[] emissionFactorValue = emissionFactor.getText().split(" ");
				if (CalEF8.equals(emissionFactorValue[0])) {
					passed("Successfully validated Emission Factor as Expected is==>" + CalEF8 + " and actula is==>"
							+ emissionFactorValue[0]);
				} else {
					failed(driver, "failed to validate Emission factor as Expected is==>" + CalEF8 + " and actual is==>"
							+ emissionFactorValue[0]);
				}
				verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", " Business Travel-Distance Based ");
				WebElement activityamt = driver.findElement(
						By.xpath("(//span[contains(text(),'Amount')]//parent::p//following-sibling::p)[1]"));
				String am = activityamt.getText().replaceAll(",", "");
				WebElement ee = driver.findElement(
						By.xpath("//span[contains(text(),'Conversion')]//parent::p//following-sibling::p"));
				String hh = ee.getText();
				String[] yy = hh.split("=");
				String[] t = yy[1].split(" ");
				Double d = Double.parseDouble(t[1]);
				if (emissionFactorValue[1].trim().equals(Constants.conToTonnfrlb)) {
					Double result7 = (Double.parseDouble(CalEF8) * Double.parseDouble(am) * d) / 2204.623;
					calTco2e7 = result7.toString();
				} else if (emissionFactorValue[1].trim().equals(Constants.conToTonnfrKg)) {
					Double result7 = (Double.parseDouble(CalEF8) * Double.parseDouble(am) * d) / 1000;
					calTco2e7 = result7.toString();
				} else if (emissionFactorValue[1].trim().equals(Constants.conToTonnfrg)) {
					Double result7 = (Double.parseDouble(CalEF8) * Double.parseDouble(am) * d) / 1000000;
					calTco2e7 = result7.toString();
				}
				WebElement valuetCO2e8 = driver
						.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
				if (GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(calTco2e7.trim())
						.equals(valuetCO2e8.getText().trim())) {
					passed("Successfully validated tCO2e value for : " + " Scope 2 - Indirect Emissions Actual "
							+ valuetCO2e8.getText() + " Expected tco2e "
							+ GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(calTco2e7.trim()));
				} else {
					failed(driver,
							"Failed to valiadte tCO2e value for : " + " Scope 2 - Indirect Emissions Actual"
									+ valuetCO2e8.getText() + " Expected tco2e "
									+ GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(calTco2e7.trim()));
				}
				WebElement emissionDetailstCO2 = driver.findElement(By.xpath(
						"//p[contains(text(),'Emission Details')]//parent::div/parent::div//following-sibling::div//span[text()='tCO2']//parent::p//following-sibling::p"));
				String emssnDetailtCO2value = emissionDetailstCO2.getText();
				double calculatedtCO2 = (Double.parseDouble(am) * convertedEmissionFactorCO2 * d) / 1000;
				String tco2 = Double.toString(calculatedtCO2);
				if (GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tco2.trim())
						.equals(GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(emssnDetailtCO2value))) {
					passed("Successfully validated tCO2 as actual is==>" + emssnDetailtCO2value + " and expected is==>"
							+ GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tco2.trim()));
				} else {
					failed(driver,
							"Failed to validate tCO2 as actual is==>" + emssnDetailtCO2value + " and expected is==>"
									+ GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tco2.trim()));
				}

				WebElement emissionDetailstCH4 = driver.findElement(By.xpath(
						"//p[contains(text(),'Emission Details')]//parent::div/parent::div//following-sibling::div//span[text()='tCH4']//parent::p//following-sibling::p"));
				String emssnDetailtCH4value = emissionDetailstCH4.getText();
				double calculatedtCH4 = (Double.parseDouble(am) * convertedEmissionFactorCH4 * d) / 1000;
				String tCH4 = Double.toString(calculatedtCH4);
				if (GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCH4.trim())
						.equals(GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(emssnDetailtCH4value))) {
					passed("Successfully validated CH4 as actual is==>" + emssnDetailtCH4value + " and expected is==>"
							+ GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCH4.trim()));
				} else {
					failed(driver,
							"Failed to validate CH4 as actual is==>" + emssnDetailtCH4value + " and expected is==>"
									+ GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCH4.trim()));
				}

				WebElement emissionDetailstN2O = driver.findElement(By.xpath(
						"//p[contains(text(),'Emission Details')]//parent::div/parent::div//following-sibling::div//span[text()='tN2O']//parent::p//following-sibling::p"));
				String emssnDetailtN2Ovalue = emissionDetailstN2O.getText();
				double calculatedtN2O = (Double.parseDouble(am) * convertedEmissionFactorN2O * d) / 1000;
				String tN2O = Double.toString(calculatedtCH4);
				if (GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tN2O.trim())
						.equals(GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(emssnDetailtN2Ovalue))) {
					passed("Successfully validated N2O as actual is==>" + emssnDetailtN2Ovalue + " and expected is==>"
							+ GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tN2O.trim()));
				} else {
					failed(driver,
							"Failed to validate N2O as actual is==>" + emssnDetailtN2Ovalue + " and expected is==>"
									+ GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tN2O.trim()));
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}

	}
}
