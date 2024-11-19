package pages.calculators;

import java.awt.Desktop.Action;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class IndirectEmissionsCalculatorPage extends CalculatorElements {
	public IndirectEmissionsCalculatorPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	String sourceOfEmissionFactor;

	public void clickOnIndirectemissionCalculator() {
		clickOn(btnIndirectEmisssion, "Scope 2 Indirect Emission");
	}

	

	public void verifyEditLabelRHP() {
		verifyIfElementPresent(lblEditActivityIndirect, "lblEditActivity", "Edit activity indirect emission scope 2");
	}

	@FindBy(xpath = "//ul//li[text()='Sabancci Tag1']//*[@data-testid='CheckBoxOutlineBlankIcon']")
	private WebElement drptags;
	@FindBy(xpath = "//input[@id='tag_id']//parent::div//*[@data-testid='ArrowDropDownIcon']")
	private WebElement dptag;

	public void EditActivityInDirectEmissions() {
		try {
			Actions act = new Actions(driver);
			Thread.sleep(3000);
			clickOn(txtFacilityNameScope3_1qa, "txtFacilityName");
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Facility Name") + "']"));
			clickOn(we, data.get("Facility Name"));
			sleep(2000);
			clickOn(drpEnergyCategoryIndirect, "energy category");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Energy Category") + "']"));
			clickOn(we, data.get("Energy Category"));
			clickOn(txtInvoiceNoIndirect, "invoice Number");
			enterText(txtInvoiceNoIndirect, "invoice number", data.get("Invoice No."));
			clickOn(txtInvoiceDate, "invoice date");
			enterText(txtInvoiceDate, "Invoice Date", data.get("Invoice Date"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			clickOn(drpAmountEnergy, "Amount of enrgy");
			enterText(drpAmountEnergy, "Amount of enrgy", data.get("Amount of Energy"));
			sleep(2000);
			clickOn(drpUnitsIndirect, "Unit");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Units") + "']"));
			clickOn(we, data.get("Units"));
			if (!data.get("Edit").equals("YES")) {
				sleep(1000);
				clickOn(dptag, "Tags");
				WebElement selecttag = driver.findElement(By.xpath("//ul[@aria-labelledby='tag_id-label']//li[text()='SpecifictoTiffanyHQ23']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", selecttag);
				act.moveToElement(selecttag).click().perform();
				act.sendKeys(Keys.ESCAPE).perform();
				//selecttag.sendKeys(Keys.ESCAPE);
				//clickOn(selecttag, "Tags");
			}			  
			sleep(1000);
			clickOn(btnSave, "Save Button");
			verifyAddActivityUpdatedToastMessage();
			// sleep(2000);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	
	public void verifyAddActivityUpdatedToastMessage() {
		try {
			sleep(100);

			By toast = By.xpath("//*[contains(text(),'Activity added successfully')]");
			By toastEdit = By.xpath("//*contains(text(),'Activity updated successfully')]");
			if(data.get("Edit").equals("YES")) {
				if (isElementPresent(toastEdit)) {
					passed("Successfully displayed toast message - Activity updated successfully");
					sleep(2000);
				} else {
					failed(driver, "Failed to display updated toast message");
				}
			}else {
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

	@FindBy(xpath = "//span[text()='Period']//parent::div//child::div//*[@data-testid='ArrowDropDownIcon']")
	private WebElement drpPeriodDwnLatest;

	@FindBy(xpath = "//button[text()='Close']")
	WebElement toastMessagePopUp;

	public void EditActivityInDirectEmissionsCEF() {
		try {
			Thread.sleep(1000);
			clickOn(txtFacilityNameScope3_1qa, "txtFacilityName");
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Facility Name") + "']"));
			clickOn(we, data.get("Facility Name"));
			sleep(2000);
			clickOn(drpEnergyCategoryIndirect, "energy category");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Energy Category") + "']"));
			clickOn(we, data.get("Energy Category"));
			clickOn(txtInvoiceNoIndirect, "invoice Number");
			enterText(txtInvoiceNoIndirect, "invoice number", data.get("Invoice No."));
			clickOn(txtInvoiceDate, "invoice date");
			enterText(txtInvoiceDate, "Invoice Date", data.get("Invoice Date"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			validateCEF();
			clickOn(drpAmountEnergy, "Amount of enrgy");
			enterText(drpAmountEnergy, "Amount of enrgy", data.get("Amount of Energy"));
			sleep(2000);
			clickOn(drpUnitsIndirect, "Unit");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Units") + "']"));
			clickOn(we, data.get("Unit"));
			sleep(2000);
			clickOn(btnSave, "Save Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void EditActivityInDirectEmissionsCEF1() {
		try {
			Thread.sleep(5000);
			clickOn(txtFacilityNameScope3_1qa, "txtFacilityName");
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Facility Name") + "']"));
			clickOn(we, data.get("Facility Name"));
			sleep(2000);
			clickOn(drpEnergyCategoryIndirect, "energy category");
//			    WebElement energyCat = driver.findElement(By.xpath("//span[text()='Energy Category']/parent::div/following-sibling::div/div/div"));
//			    if(energyCat.getText().equals(data.get("Energy Category"))) {
//			    	we = driver.findElement(By.xpath("//li[text()='" + data.get("Energy Category1") + "']"));
//				    clickOn(we, data.get("Energy Category1"));
//			    }else {
//			    	we = driver.findElement(By.xpath("//li[text()='" + data.get("Energy Category") + "']"));
//				    clickOn(we, data.get("Energy Category"));
//			    }
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Energy Category1") + "']"));
			clickOn(we, data.get("Energy Category1"));
			clickOn(txtInvoiceNoIndirect, "invoice Number");
			enterText(txtInvoiceNoIndirect, "invoice number", data.get("Invoice No."));
			clickOn(txtInvoiceDate, "invoice date");
			enterText(txtInvoiceDate, "Invoice Date", data.get("Invoice Date"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			validateCEF();
			String custom_LocationBased = "//div//ul/li[contains(text(),'" + GlobalVariables.nameOfCEF + "')]";
			clickOnElementWithDynamicXpath(custom_LocationBased, "selected cef");
			clickOn(drpAmountEnergy, "Amount of enrgy");
			enterText(drpAmountEnergy, "Amount of enrgy", data.get("Amount of Energy"));
			sleep(2000);
			clickOn(drpUnitsIndirect, "Unit");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Units") + "']"));
			clickOn(we, data.get("Unit"));
			sleep(2000);
			clickOn(btnSave, "Save Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	/*
	 * public void clickOnEmissionFactors() { try {
	 * 
	 * Actions sda = new Actions(driver);
	 * sda.moveToElement(customchevronicon).click().perform(); //
	 * clickOn(customchevronicon, "click on energy dropdown"); WebElement energytype
	 * = driver .findElement(By.xpath("//ul[@role='listbox']//li[text()='" +
	 * data.get("cusemissiontype") + "']")); clickOn(energytype,
	 * data.get("cusemissiontype")); } catch (Exception e) { failed(driver,
	 * "Exception caught" + e.getMessage()); } }
	 */

//	GlobalVariables.nameOfCEF;
	String rndNotes;

	public void addCEFForIndirectEmission() {
		try {
			clickOn(btnAddCEF, "Custom Emission Factor");
			verifyIfElementPresent(lblAddCEF, "CEF", "CEF");

			GlobalVariables.nameOfCEF = Constants.customEmissionFactor + generateRandomNumber(3);
			enterText(txtNameOfCustomEF, "name of custom emission factor", GlobalVariables.nameOfCEF);

			GlobalVariables.nameOfCEF = Constants.customEmissionFactor + generateRandomNumber(3);
			enterText(txtNameOfCustomEF, "name of custom emission factor", GlobalVariables.nameOfCEF);

			GlobalVariables.nameOfCEF = Constants.customEmissionFactor + generateRandomNumber(3);
			enterText(txtNameOfCustomEF, "name of custom emission factor", GlobalVariables.nameOfCEF);
			GlobalVariables.nameOfCEF = Constants.customEmissionFactor + generateRandomNumber(3);
			enterText(txtNameOfCustomEF, "name of custom emission factor", GlobalVariables.nameOfCEF);
			clickOn(drpEnergyType, "energy category");
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Energy Category") + "']"));
			clickOn(we, "energy category" + data.get("Energy Category"));
			sourceOfEmissionFactor = "SRCEF" + generateRandomNumber(3);
			enterText(txtSourceCEF, "Source of Emission Factor", sourceOfEmissionFactor);
			clickOn(drpUnitCEFDenominator, "Unit of Custom EF (Denominator)");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Denominator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Denominator)"));
			enterText(txtCO2, "CO2", data.get("CO2"));
			enterText(txtCH4, "CH4", data.get("CH4"));
			enterText(txtN2O, "N2O", data.get("N2O"));
			clickOn(drpUnitCEFNumerator, "Unit of Custom EF (Numerator)");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Numerator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Numerator)"));
			WebElement inputNotes = driver.findElement(By.xpath("//*[@id='notes']"));
			rndNotes = generateRandomString(4) + generateRandomNumber(3);
			enterText(inputNotes, "Notes", rndNotes);
			clickOn(btnSaveParemeterInput, "Save Custom Emission factor");
			sleep(2000);
			CO2eFactor();
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void validateAgGrid() {
		/*
		 * int count = 0; String cefanme = ""; //List<String> nameOfAddedCEF = new
		 * ArrayList<>(); Map<Integer,String> map=new HashMap<Integer,String>();
		 * List<WebElement> agGridList = driver.findElements(By.
		 * xpath("//div[@ref='eContainer' and @role='rowgroup']/div/div[@col-id='NameOfCustomEmission']//span"
		 * )); System.out.println("Total No. of CEF are -->"+agGridList.size()); for(int
		 * i=0;i<agGridList.size();i++) {
		 * System.out.println(agGridList.get(i).getText()); String temp =
		 * agGridList.get(i).getText(); map.put(i,temp); } Iterator <Integer> it =
		 * map.keySet().iterator(); while(it.hasNext()) { int key=(int)it.next();
		 * if(key==0) {
		 * System.out.println("CEF Name -->>"+map.get(key)+" "+"positon:-->"+key); count
		 * = key; cefanme = map.get(key); break; } } if(count==0) {
		 * passed("Successfully validated postion of Newly Added CEF "
		 * +cefanme+" is present at"+" "+(count+1)); }
		 */

		WebElement rowIndex = driver.findElement(
				By.xpath("//span[text()='" + GlobalVariables.nameOfCEF + "']//ancestor::div[@role='row']"));

		String indexValue = rowIndex.getAttribute("row-index");
		int intRowValue = Integer.valueOf(indexValue);
		if (intRowValue == 0) {
			passed("Newly added CEF at" + " " + intRowValue + "th Position");
		} else {
			failed(driver, "Failed to displayed Newly added CEF");
		}
		List<String> nameOfAddedCEF = new ArrayList<>();

		List<WebElement> agGridDetails = driver.findElements(By.xpath("//*[text()='" + GlobalVariables.nameOfCEF

				+ "']//ancestor::div[@col-id='NameOfCustomEmission' and @role='gridcell']//following-sibling::div//span"));
		for (int i = 0; i < agGridDetails.size(); i++) {
			nameOfAddedCEF.add(agGridDetails.get(i).getText());
			System.out.println(agGridDetails.get(i).getText());
		}
		if (nameOfAddedCEF.get(0).equals(data.get("Energy Category"))) {
			passed("Successfully displayed Energy category");
		} else {
			failed(driver, "Failed to displayed Energy category");
		}
		if (nameOfAddedCEF.get(1).equals(sourceOfEmissionFactor)) {
			passed("Successfully displayed Source of emission");
		} else {
			failed(driver, "Failed to displayed Source of emission");
		}
		if (nameOfAddedCEF.get(2).equals(data.get("Unit of Custom EF (Denominator)"))) {
			passed("Successfully displayed Unit of Custom EF (Denominator)");
		} else {
			failed(driver, "Failed to displayed Unit of Custom EF (Denominator)");
		}
		if (nameOfAddedCEF.get(3).equals(data.get("CO2"))) {
			passed("Successfully displayed CO2");
		} else {
			failed(driver, "Failed to displayed CO2");
		}
		if (nameOfAddedCEF.get(4).equals(data.get("CH4"))) {
			passed("Successfully displayed CH4");
		} else {
			failed(driver, "Failed to displayed CH4");
		}
		if (nameOfAddedCEF.get(5).equals(data.get("N2O"))) {
			passed("Successfully displayed N2O");
		} else {
			failed(driver, "Failed to displayed N2O");
		}
		if (nameOfAddedCEF.get(6).equals(co2efactorCalculatedValue)) {
			passed("Successfully displayed CO2e as Actual is" + " " + agGridDetails.get(6) + " " + "and Expected is"
					+ " " + co2efactorCalculatedValue);
		} else {
			failed(driver, "Failed to displayed CO2e as Actual is" + " " + agGridDetails.get(6) + " "
					+ "and Expected is" + " " + co2efactorCalculatedValue);
		}
		if (nameOfAddedCEF.get(7).equals(data.get("Unit of Custom EF (Numerator)"))) {
			passed("Successfully displayed Unit of Custom EF (Numerator)");
		} else {
			failed(driver, "Failed to displayed Unit of Custom EF (Numerator)");
		}
		if (nameOfAddedCEF.get(8).equals(rndNotes)) {
			passed("Successfully displayed Notes");
		} else {
			failed(driver, "Failed to displayed Notes");
		}
	}

	String co2 = generateRandomNumber(2); /// co2 ch4 n2o
	String ch4 = generateRandomNumber(2);
	String n2o = generateRandomNumber(2);

	public void editCEFForIndirectEmission() {
		try {
			Actions mouseHandling = new Actions(driver);
			sleep(2000);
			verifyIfElementPresent(lblEditCEF, "Edit CEF", "");
			GlobalVariables.nameOfCEF = Constants.customEmissionFactor + generateRandomNumber(3);
			mouseHandling.moveToElement(txtNameOfCustomEF).doubleClick(txtNameOfCustomEF).perform();
			enterText(txtNameOfCustomEF, "name of cusytom emission factor", GlobalVariables.nameOfCEF);
			clickOn(drpEnergyType, "energy category");
//			 WebElement energyCat = driver.findElement(By.xpath("//input[@id='energy_category_name']"));
//			 if(energyCat.getAttribute("value").equals(data.get("Energy Category"))) {
//				 WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Energy Category1") + "']"));
//				 clickOn(we, "energy category"+data.get("Energy Category1"));
//			 }else {
//			 WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Energy Category") + "']"));
//			 clickOn(we, "energy category"+data.get("Energy Category"));
//			 }
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Energy Category1") + "']"));
			clickOn(we, "energy category" + data.get("Energy Category1"));
			sourceOfEmissionFactor = "SRCEF" + generateRandomNumber(3);
			mouseHandling.moveToElement(txtSourceCEF).doubleClick(txtSourceCEF).perform();
			enterText(txtSourceCEF, "Source of Emission Factor", sourceOfEmissionFactor);
			clickOn(drpUnitCEFDenominator, "Unit of Custom EF (Denominator)");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Denominator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Denominator)"));
			enterText(txtCO2, "CO2", co2);
			enterText(txtCH4, "CH4", ch4);
			enterText(txtN2O, "N2O", n2o);
			clickOn(drpUnitCEFNumerator, "Unit of Custom EF (Numerator)");
			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Numerator)") + "']"));
			clickOn(we, data.get("Unit of Custom EF (Numerator)"));
			clickOn(btnSaveParemeterInput, "Save Custom Emission factor");
			CO2eFactorEdit();
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void clickOnCEFInCEFGridMultiple() {
		sleep(3000);

		WebElement weActivity = driver
				.findElement(By.xpath("//*[text()='" + GlobalVariables.nameOfCEF + "']//parent::div"));

		clickOn(weActivity, "Activity of Facility" + GlobalVariables.nameOfCEF);
	}

	Double valueCo2e;
	String co2efactorCalculatedValue;
	String co2eFactor;

	public void CO2eFactor() {
		valueCo2e = Double.parseDouble(data.get("CO2")) + Double.parseDouble(data.get("CH4")) * 25
				+ Double.parseDouble(data.get("N2O")) * 298;
		System.out.println("calculated value" + valueCo2e);
		co2efactorCalculatedValue = valueCo2e.toString();
		sleep(1000);
		co2eFactor = co2efactorCalculatedValue + " " + data.get("Unit of Custom EF(Num)") + " " + "CO2e" + "/"
				+ data.get("Unit of Custom EF(Den)");
	}

	Double valueCo2eedit;
	String m;
	String co2eFactoredit;

	public void CO2eFactorEdit() {
		valueCo2eedit = Double.parseDouble(co2) + Double.parseDouble(ch4) * 25 + Double.parseDouble(n2o) * 298;
		System.out.println("calculated value" + valueCo2e);
		m = valueCo2eedit.toString();
		co2eFactoredit = m + " " + data.get("Unit of Custom EF(Num)") + " " + "CO2e" + "/"
				+ data.get("Unit of Custom EF(Den)");
	}

	public void verifyCO2eFactor() {
		WebElement cefValueRHP = driver
				.findElement(By.xpath("//span[contains(text(),'CO2e Factor')]//parent::div//following-sibling::div"));
		String newPlainValue = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(cefValueRHP.getText());
		if (newPlainValue.equals(co2efactorCalculatedValue)) {
			passed("Succesfully validated tCO2e value" + newPlainValue + "--" + co2efactorCalculatedValue);
		} else {
			fail("Failed validated tCO2e value" + " " + newPlainValue + "--" + co2efactorCalculatedValue);
		}
		WebElement weNameOfCEF = driver.findElement(
				By.xpath("//span[contains(text(),'Name of Custom EF')]//parent::div//following-sibling::div"));
		WebElement weSourceOfCEF = driver.findElement(
				By.xpath("//span[contains(text(),'Source of Emission Factor')]//parent::div//following-sibling::div"));
		if (weNameOfCEF.getText().equals(GlobalVariables.nameOfCEF)) {
			passed("successfully validated name of custom emission factor");
		} else {
			fail("failed to validate name of custom emission factor");
		}
		if (weSourceOfCEF.getText().equals(sourceOfEmissionFactor)) {
			passed("successfully validated source of custom emission factor");
		} else {
			fail("failed to validate source of custom emission factor");
		}
	}

	public void verifyCO2eFactor1() {
		WebElement cefValueRHP = driver
				.findElement(By.xpath("//span[contains(text(),'CO2e Factor')]//parent::div//following-sibling::div"));
		String newPlainValue = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(cefValueRHP.getText());
		if (newPlainValue.equals(m)) {
			passed("Succesfully validated tCO2e value" + newPlainValue + "--" + m);
		} else {
			fail("Failed validated tCO2e value" + " " + newPlainValue + "--" + m);
		}
		WebElement weNameOfCEF = driver.findElement(
				By.xpath("//span[contains(text(),'Name of Custom EF')]//parent::div//following-sibling::div"));
		WebElement weSourceOfCEF = driver.findElement(
				By.xpath("//span[contains(text(),'Source of Emission Factor')]//parent::div//following-sibling::div"));
		if (weNameOfCEF.getText().equals(GlobalVariables.nameOfCEF)) {
			passed("successfully validated name of custom emission factor");
		} else {
			fail("failed to validate name of custom emission factor");
		}
		if (weSourceOfCEF.getText().equals(sourceOfEmissionFactor)) {
			passed("successfully validated source of custom emission factor");
		} else {
			fail("failed to validate source of custom emission factor");
		}
	}

	public void clickPurchasedEnergy() {
		clickOn(btnPurchasedEnergy, "purchased energy");
	}

	public void validateCEF() {
		try {
			jsClick(chboxCstmLocatinBased, "yes");
			verifyIfElementPresent(drpCustomEmiFactorLocationScope2, "ww", "ww");
			clickOn(drpCustomEmiFactorLocationScope2, "drop down");
			WebElement namecef = driver.findElement(By.xpath("//ul/li[text()='" + GlobalVariables.nameOfCEF + "']"));
			JavascriptExecutor jsExc = (JavascriptExecutor) driver;
			jsExc.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow');",
					namecef);
			sleep(1000);
			String drpCEF = "//li[text()='" + GlobalVariables.nameOfCEF + "']";
			clickOnElementWithDynamicXpath(drpCEF, GlobalVariables.nameOfCEF);
			sleep(1000);
			WebElement ter = driver
					.findElement(By.xpath("//*[text()='Custom/Location Based Emission Factor']//following::div[text()='"
							+ GlobalVariables.nameOfCEF + "']"));
			// String ewq = ter.getAttribute("value");
			if (isElementPresent(ter)) {
				passed("Successfully validated custom emission factor for Location Based name in avtivities for Scope 2");
			} else {
				failed(driver,
						"failed to validated custom emission factor for Location Based name in avtivities for Scope 2");
			}
			sleep(1000);
			// market based
			jsClick(chboxCstmMarketBased, "yes");
			verifyIfElementPresent(drpCustomEmiFactorMarketScope2, "ww", "ww");
			clickOn(drpCustomEmiFactorMarketScope2, "drop down");
			WebElement namecef1 = driver.findElement(By.xpath("//ul/li[text()='" + GlobalVariables.nameOfCEF + "']"));
			JavascriptExecutor jsExc1 = (JavascriptExecutor) driver;
			jsExc1.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow');",
					namecef1);
			sleep(1000);
			String drpCEF1 = "//li[text()='" + GlobalVariables.nameOfCEF + "']";
			clickOnElementWithDynamicXpath(drpCEF, GlobalVariables.nameOfCEF);
			sleep(1000);
			WebElement ter1 = driver
					.findElement(By.xpath("//*[text()='Custom/Location Based Emission Factor']//following::div[text()='"
							+ GlobalVariables.nameOfCEF + "']"));
			// String ewq = ter.getAttribute("value");
			if (isElementPresent(ter1)) {
				passed("Successfully validated custom emission factor for Market Based name in avtivities for Scope 2");
			} else {
				failed(driver,
						"failed to validated custom emission factorfor Market Based  name in avtivities for Scope 2");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void ValidateActivityDetailsInViewActivityAddScope2() {
		try {
			String[] activityDetailFieldNames = { "Facility Name", "Energy Category", "Start Date", "End Date",
					"Invoice No.", "Invoice Date", "Amount of Energy", "Units", "Source", "CO2", "CH4", "N2O" };
			Thread.sleep(5000);
			verifyIfElementPresent(lblActivityDetails, "lblViewActivityFulEngy", "Indirect emission");
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

	@FindBy(xpath = "//button[contains(text(),'Close')]")
	private WebElement btclose;

	public void closevalidationRHP() {
		clickOn(btclose, "click on close");
	}

	String valueunitConversion;
	String valueAmount;
	String valEFUnit;
	String valUnit_FuelAmount;
	String valEF;

	public void ValidateActivityDetailsInViewActivityAddScope2UnitConversion() {
		try {
			System.out.println(
					"Unit Used in The Activity to validate Emission Factors  -----------> " + data.get("Units"));
			WebElement weAmountofEnergy = driver
					.findElement(By.xpath("//span[text()='Amount of Energy']//parent::p//following-sibling::p"));
			String valueAmount1 = weAmountofEnergy.getText().trim();
			valueAmount = valueAmount1.replaceAll(",", "");
			String metricValidationMethod = data.get("Validation Method");
			String[] calculationMethod = metricValidationMethod.split(",");
			// ,"Market Based" Location Based
			for (int k = 0; k < calculationMethod.length; k++) {
				if (!isElementPresent(valunitConversion)) {
					WebElement weLocationBasedUnitConversion = driver.findElement(
							By.xpath("//span[text()='" + calculationMethod[k] + "']//parent::p//following-sibling::p"));
					String[] split = weLocationBasedUnitConversion.getText().trim().split("=");
					String trim = split[1].trim();
					String[] split2 = trim.trim().split(" ");
					valueunitConversion = split2[0];
				} else {
					WebElement weLocationBasedUnitConversion = driver
							.findElement(By.xpath("//span[text()='Unit Conversion']//parent::p//following-sibling::p"));
					String[] split = weLocationBasedUnitConversion.getText().trim().split("=");
					String trim = split[1].trim();
					String[] split2 = trim.trim().split(" ");
					valueunitConversion = split2[0];
				}
				String[] emissionFactors = { "CO2", "CH4", "N2O" };
				for (int i = 0; i < emissionFactors.length; i++) {
					WebElement weActivityField;
					if (calculationMethod[k].equals("Location Based")) {
						weActivityField = driver.findElement(By.xpath(
								"//p[text()='Emission Factors - Location Based']//parent::div//parent::div//following-sibling::div//span[text()='"
										+ emissionFactors[i] + "']//parent::p//following-sibling::p"));
						String[] valEF1 = weActivityField.getText().trim().split(" ");
						valEF = valEF1[0];
						valEFUnit = valEF1[1];
						if (valEFUnit.contains("lb/MWh")) {
							calculateUnitConversion(valueAmount, valueunitConversion, valEF, Constants.unitConvlbtoton);
						} else if (valEFUnit.equals("kg/kWh")) {
							calculateUnitConversion(valueAmount, valueunitConversion, valEF, "1000");
						} else if (valEFUnit.equals("g/kWh")) {
							calculateUnitConversion(valueAmount, valueunitConversion, valEF, "1000000");
						}
					} else {
						weActivityField = driver.findElement(By.xpath(
								"//p[text()='Emission Factors - Market Based']//parent::div//parent::div//following-sibling::div//span[text()='"
										+ emissionFactors[i] + "']//parent::p//following-sibling::p"));
						String[] valEF1 = weActivityField.getText().trim().split(" ");
						valEF = valEF1[0];
						valEFUnit = valEF1[1];
						if (valEFUnit.contains("lb/MWh")) {
							calculateUnitConversion(valueAmount, valueunitConversion, valEF, Constants.unitConvlbtoton);
						} else if (valEFUnit.contains("kg/kWh")) {
							calculateUnitConversion(valueAmount, valueunitConversion, valEF, "1000");
						} else if (valEFUnit.equals("g/kWh")) {
							calculateUnitConversion(valueAmount, valueunitConversion, valEF, "1000000");
						}
					}
					String[] emissionDetails = { "tCO2", "tCH4", "tN2O" };
					for (int j = 0; j < emissionDetails.length; j++) {
						if (emissionDetails[j].contains(emissionFactors[i])) {
							WebElement weActivityFieldRHP;
							if (calculationMethod[k].equals("Location Based")) {
								weActivityFieldRHP = driver.findElement(By.xpath(
										"//p[text()='Emission Details - Location Based']//parent::div//parent::div//following-sibling::div//span[text()='"
												+ emissionDetails[j] + "']//parent::p//following-sibling::p"));
							} else {
								weActivityFieldRHP = driver.findElement(By.xpath(
										"//p[text()='Emission Details - Market Based']//parent::div//parent::div//following-sibling::div//span[text()='"
												+ emissionDetails[j] + "']//parent::p//following-sibling::p"));
							}
							if (weActivityFieldRHP.getText().trim().equals(valUnit_FuelAmount)) {
								passed("Successfully Validated " + calculationMethod[k] + " " + emissionDetails[j]
										+ " unit Conversion Value In Activity Details As"
										+ weActivityFieldRHP.getText());
							} else {
								failed(driver,
										"Failed To validate " + calculationMethod[k] + "" + emissionDetails[j]
												+ " unit Conversion Value In Activity Details Expected As "
												+ valUnit_FuelAmount + "But Actual is" + weActivityFieldRHP.getText());
							}
						}
					}
				}
				validateEmissionFcator(calculationMethod[k]);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void calculateUnitConversion(String valueAmount, String valueunitConversion, String valEF,
			String unitConvUnittoUnit) {
		if (valEFUnit.contains("lb/MWh")) {
			Double unitconverted = Double.parseDouble(valueAmount) * Double.parseDouble(valueunitConversion)
					* Double.parseDouble(valEF) * Double.parseDouble(unitConvUnittoUnit);
			String valUnit_FuelAmount1 = unitconverted.toString();
			valUnit_FuelAmount = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(valUnit_FuelAmount1);
		} else {
			Double unitconverted = Double.parseDouble(valueAmount) * Double.parseDouble(valueunitConversion)
					* Double.parseDouble(valEF) / Double.parseDouble(unitConvUnittoUnit);
			String valUnit_FuelAmount1 = unitconverted.toString();
			valUnit_FuelAmount = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(valUnit_FuelAmount1);
		}
	}

	String emissionFactorCalc;
	String weEmissionFactorRHP;
	String weCO2;
	String weN2O;
	String weCH4;

	public void validateEmissionFcator(String valiidationmMethod) {
		if (valiidationmMethod.equals("Location Based")) {
			WebElement weCO21 = driver.findElement(By.xpath(
					"//p[text()='Emission Factors - Location Based']//parent::div//parent::div//following-sibling::div//span[text()='CO2']//parent::p//following-sibling::p"));
			String[] weCO2_2 = weCO21.getText().trim().split(" ");
			weCO2 = weCO2_2[0].toString();
			WebElement weCH4_1 = driver.findElement(By.xpath(
					"//p[text()='Emission Factors - Location Based']//parent::div//parent::div//following-sibling::div//span[text()='CH4']//parent::p//following-sibling::p"));
			String[] weCH4_2 = weCH4_1.getText().trim().split(" ");
			weCH4 = weCH4_2[0].toString();
			WebElement weN2_1 = driver.findElement(By.xpath(
					"//p[text()='Emission Factors - Location Based']//parent::div//parent::div//following-sibling::div//span[text()='N2O']//parent::p//following-sibling::p"));
			String[] weN2_2 = weN2_1.getText().trim().split(" ");
			weN2O = weN2_2[0].toString();
			WebElement weEmissionFactor1 = driver.findElement(By.xpath("	"));
			String[] weEmissionFactor2 = weEmissionFactor1.getText().trim().split(" ");
			weEmissionFactorRHP = weEmissionFactor2[0].toString();
		} else if (valiidationmMethod.equals("Market Based")) {
			WebElement weCO21 = driver.findElement(By.xpath(
					"//p[text()='Emission Factors - Market Based']//parent::div//parent::div//following-sibling::div//span[text()='CO2']//parent::p//following-sibling::p"));
			String[] weCO2_2 = weCO21.getText().trim().split(" ");
			weCO2 = weCO2_2[0].toString();
			WebElement weCH4_1 = driver.findElement(By.xpath(
					"//p[text()='Emission Factors - Market Based']//parent::div//parent::div//following-sibling::div//span[text()='CH4']//parent::p//following-sibling::p"));
			String[] weCH4_2 = weCH4_1.getText().trim().split(" ");
			weCH4 = weCH4_2[0].toString();
			WebElement weN2_1 = driver.findElement(By.xpath(
					"//p[text()='Emission Factors - Market Based']//parent::div//parent::div//following-sibling::div//span[text()='N2O']//parent::p//following-sibling::p"));
			String[] weN2_2 = weN2_1.getText().trim().split(" ");
			weN2O = weN2_2[0].toString();
			WebElement weEmissionFactor1 = driver.findElement(By.xpath(
					"//p[text()='Emission Details - Market Based']//parent::div//parent::div//following-sibling::div//span[text()='Emission Factor']//parent::p//following-sibling::p"));
			String[] weEmissionFactor2 = weEmissionFactor1.getText().trim().split(" ");
			weEmissionFactorRHP = weEmissionFactor2[0].toString();
		}
		Double emissionFactorCalc1 = Double.parseDouble(weCO2) * 1 + Double.parseDouble(weCH4) * 25
				+ Double.parseDouble(weN2O) * 298;
		String emissionFactorCalc2 = emissionFactorCalc1.toString();
		emissionFactorCalc = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(emissionFactorCalc2);
		if (emissionFactorCalc.equals(weEmissionFactorRHP)) {
			passed("Successfully Validated Emission Factor " + valiidationmMethod
					+ " unit Conversion Value In Activity Details As" + emissionFactorCalc);
		} else {
			failed(driver,
					"Failed To validate Emission Factor " + valiidationmMethod
							+ " unit Conversion Value In Activity Details Expected As " + emissionFactorCalc
							+ "But Actual is" + weEmissionFactorRHP);
		}
		valiadteCO2eRHP(valiidationmMethod);
		calculateCO2edaily(valiidationmMethod);
	}

	String valueCO2e;
	String weCO2eRHP;

	public void valiadteCO2eRHP(String valiidationmMethod) {
		if (valiidationmMethod.equals("Location Based")) {
			if (valEFUnit.contains("lb/MWh")) {
				Double valueCO2e_1 = Double.parseDouble(emissionFactorCalc) * Double.parseDouble(valueunitConversion)
						* Double.parseDouble(valueAmount) * Double.parseDouble(Constants.unitConvlbtoton);
				String valueCO2e2 = valueCO2e_1.toString();
				valueCO2e = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(valueCO2e2);
			} else if (valEFUnit.contains("kg/kWh")) {
				Double valueCO2e_1 = Double.parseDouble(emissionFactorCalc) * Double.parseDouble(valueAmount)
						* Double.parseDouble(valueunitConversion) / 1000;
				String valueCO2e2 = valueCO2e_1.toString();
				valueCO2e = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(valueCO2e2);
			} else if (valEFUnit.equals("g/kWh")) {
				calculateUnitConversion(valueAmount, valueunitConversion, valEF, "1");
				Double valueCO2e_1 = Double.parseDouble(emissionFactorCalc) * Double.parseDouble(valueAmount)
						* Double.parseDouble(valueunitConversion) / 1000000;
				String valueCO2e2 = valueCO2e_1.toString();
				valueCO2e = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(valueCO2e2);
			}
			WebElement weCO2eRHP1 = driver.findElement(By.xpath(
					"//p[text()='Emission Details - Location Based']//parent::div//parent::div//following-sibling::div//span[text()='tCO2e']//parent::p//following-sibling::p"));
			weCO2eRHP = weCO2eRHP1.getText().trim();
		} else if (valiidationmMethod.equals("Market Based")) {
			if (valEFUnit.contains("lb/MWh")) {
				Double valueCO2e_1 = Double.parseDouble(emissionFactorCalc) * Double.parseDouble(valueunitConversion)
						* Double.parseDouble(valueAmount) * Double.parseDouble(Constants.unitConvlbtoton);
				String valueCO2e2 = valueCO2e_1.toString();
				valueCO2e = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(valueCO2e2);
			} else if (valEFUnit.contains("kg/kWh")) {
				Double valueCO2e_1 = Double.parseDouble(emissionFactorCalc) * Double.parseDouble(valueAmount)
						* Double.parseDouble(valueunitConversion) / 1000;
				String valueCO2e2 = valueCO2e_1.toString();
				valueCO2e = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(valueCO2e2);
			} else if (valEFUnit.equals("g/kWh")) {
				Double valueCO2e_1 = Double.parseDouble(emissionFactorCalc) * Double.parseDouble(valueAmount)
						* Double.parseDouble(valueunitConversion) / 1000000;
				String valueCO2e2 = valueCO2e_1.toString();
				valueCO2e = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(valueCO2e2);
			}
			WebElement weCO2eRHP1 = driver.findElement(By.xpath(
					"//p[text()='Emission Details - Market Based']//parent::div//parent::div//following-sibling::div//span[text()='tCO2e']//parent::p//following-sibling::p"));
			weCO2eRHP = weCO2eRHP1.getText().trim();
		}
		if (valueCO2e.equals(weCO2eRHP)) {
			passed("Succesfully validated " + valiidationmMethod + " Co2e in Activity details as " + weCO2eRHP);
		} else {
			failed(driver, "Failed to validate" + valiidationmMethod + " Co2e in Activity details Expected " + valueCO2e
					+ "But Actual  is  " + weCO2eRHP);
		}
	}

	Double calculatedovrlap;
	String weCO2e_daily;

	public void calculateCO2edaily(String valiidationmMethod) {
		try {
			String daystart = data.get("Start Date");
			String dayEnd = data.get("End Date");
			if (daystart.contains(Constants.leapDayEnd) && dayEnd.contains(Constants.leapDayEnd)) {
				calculatedovrlap = leapOvrlapDays(daystart, dayEnd, Constants.datePrdstrt, Constants.datePrdend);
			} else {
				calculatedovrlap = overlappDaysCount(daystart, dayEnd, Constants.datePrdstrt, Constants.datePrdend);
			}
			Double differenceDays = calculateDaysDiff(daystart, dayEnd);
			System.out.println(calculatedovrlap);
			Double frstval = Double.parseDouble(valueCO2e) / differenceDays;
			String CO2edailyvalue1 = frstval.toString();
			String CO2edailyvalue = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(CO2edailyvalue1);
			if (valiidationmMethod.equals("Location Based")) {
				WebElement weCO2edailyRHP1 = driver.findElement(By.xpath(
						"//p[text()='Emission Details - Location Based']//parent::div//parent::div//following-sibling::div//span[text()='tCO2e (Daily)']//parent::p//following-sibling::p"));
				weCO2e_daily = weCO2edailyRHP1.getText().trim();
			} else if (valiidationmMethod.equals("Market Based")) {
				WebElement weCO2edailyRHP1 = driver.findElement(By.xpath(
						"//p[text()='Emission Details - Market Based']//parent::div//parent::div//following-sibling::div//span[text()='tCO2e (Daily)']//parent::p//following-sibling::p"));
				weCO2e_daily = weCO2edailyRHP1.getText().trim();
			}
			if (weCO2e_daily.equals(CO2edailyvalue)) {
				passed("Successfully validated CO2e Daily " + valiidationmMethod + " Emission values as "
						+ CO2edailyvalue);
			} else {
				failed(driver, " Failed to validate CO2e Daily " + valiidationmMethod + " Emission values Expected as "
						+ CO2edailyvalue + " But Actual is " + weCO2e_daily);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void ValidateCEFDetailsInViewCEF() {
		try {
			String[] activityDetailFieldNames = { "Energy Category", "Unit of Custom EF (Denominator)",
					"Unit of Custom EF (Numerator)", "CO2", "CH4", "N2O" };
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

	public void ValidateActivityDetailsInViewActivityScope2() {
		try {
			String[] activityDetailFieldNames = { "Facility Name", "Energy Category", "Start Date", "End Date",
					"Invoice No.", "Amount of Energy", "Units" };
			Thread.sleep(3000);
			verifyIfElementPresent(lblActivityDetails, "lblViewActivityFulEngy", "Indirect emission");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				WebElement weActivityField = driver.findElement(By.xpath(
						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::p//following-sibling::p"));
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

	public void ValidateCEFInScope2() {
		try {
			sleep(2000);
			WebElement cstomEmission = driver.findElement(By.xpath(
					"//span[contains(text(),'Custom/Location Based Emission Factor')]//parent::p//following-sibling::p"));
			WebElement cstomEmissionSource = driver
					.findElement(By.xpath("//span[contains(text(),'Source')]//parent::p//following-sibling::p"));
			System.out.println(cstomEmission.getText());
			if (cstomEmission.getText().equals(GlobalVariables.nameOfCEF)) {
				passed("successfully validated CEF in RHP as actual is" + cstomEmission.getText() + " "
						+ "and Expected is" + " " + GlobalVariables.nameOfCEF);
			} else {
				fail("failed to validate CEF in RHP as actual is" + " " + cstomEmission.getText() + " "
						+ "and Expected is" + " " + GlobalVariables.nameOfCEF);
			}
			if (cstomEmissionSource.getText().equals(sourceOfEmissionFactor)) {
				passed("successfully validated CEF in RHP as actual is" + cstomEmissionSource.getText() + " "
						+ "and Expected is" + " " + sourceOfEmissionFactor);
			} else {
				fail("failed to validate CEF in RHP as actual is" + " " + cstomEmissionSource.getText() + " "
						+ "and Expected is" + " " + sourceOfEmissionFactor);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void ValidateRHPCEFValueswithActivitiesRHP() {
		WebElement valuetCO2 = driver
				.findElement(By.xpath("(//span[contains(text(),'tCO2')]//parent::p//following-sibling::p)[1]"));
		Double CO2 = Double.parseDouble(data.get("CO2")) * Double.parseDouble(data.get("Amount of Energy")) / 1000;
		String tCO2Activity = CO2.toString();
		String tCO2ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCO2Activity);
		if (tCO2ActivityRHP.equals(valuetCO2.getText())) {
			passed("Succesfully validated tCO2 value" + tCO2ActivityRHP + " And Actual is " + valuetCO2.getText());
		} else {
			fail("Failed validated tCO2 value" + " " + tCO2ActivityRHP + " But Actual is " + valuetCO2.getText());
		}
		WebElement valueCH4 = driver
				.findElement(By.xpath("(//span[contains(text(),'tCH4')]//parent::p//following-sibling::p)[1]"));
		Double CH4 = Double.parseDouble(data.get("CH4")) * Double.parseDouble(data.get("Amount of Energy")) / 1000;
		String CH4Activity = CH4.toString();
		String CH4ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(CH4Activity);
		if (CH4ActivityRHP.equals(valueCH4.getText())) {
			passed("Succesfully validated CH4 value" + CH4ActivityRHP + " And Actual is " + valueCH4.getText());
		} else {
			fail("Failed validated CH4 value" + " " + CH4ActivityRHP + " But Actual is " + valueCH4.getText());
		}
		WebElement valueN2O = driver
				.findElement(By.xpath("(//span[contains(text(),'tN2O')]//parent::p//following-sibling::p)[1]"));
		Double N2O = Double.parseDouble(data.get("N2O")) * Double.parseDouble(data.get("Amount of Energy")) / 1000;
		String N2OActivity = N2O.toString();
		String N2OActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(N2OActivity);
		if (N2OActivityRHP.equals(valueN2O.getText())) {
			passed("Succesfully validated N2O value" + N2OActivityRHP + " And Actual is " + valueN2O.getText());
		} else {
			fail("Failed validated N2O value" + " " + N2OActivityRHP + " But Actual is " + valueN2O.getText());
		}
		WebElement CO2eActivity = driver
				.findElement(By.xpath("(//span[contains(text(),'CO2e')]//parent::p//following-sibling::p)[1]"));
		Double ValCO2e = valueCo2e * Double.parseDouble(data.get("Amount of Energy")) / 1000;
		String ValCO2eRHP = ValCO2e.toString();
		if (CO2eActivity.getText().equals(ValCO2eRHP)) {
			passed("Succesfully validated tCO2e value" + ValCO2eRHP + " And Actual is " + CO2eActivity.getText());
		} else {
			fail("Failed validated tCO2e value" + " " + ValCO2eRHP + " But Actual is " + CO2eActivity.getText());
		}
		WebElement EFActivity = driver.findElement(
				By.xpath("(//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p)[3]"));
		String s2 = EFActivity.getText().trim();
		// String[] EFValueRHP = s2.split(" ");
		if (s2.equals(co2eFactor)) {
			passed("Succesfully validated Emission Factor Expected value" + " " + co2eFactor + " And Actual is " + s2);
		} else {
			fail("Failed validated Emission Factor Expected value" + " " + co2eFactor + " But Actual is " + s2);
		}
	}

	public void ValidateRHPCEFValueswithActivitiesRHP1() {
		WebElement valuetCO2 = driver.findElement(By.xpath("//span[text()='tCO2']//parent::p//following-sibling::p"));
		Double CO2 = Double.parseDouble(co2) * Double.parseDouble(data.get("Amount of Energy")) / 1000;
		String tCO2Activity = CO2.toString();
		String tCO2ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCO2Activity);
		if (tCO2ActivityRHP.equals(valuetCO2.getText())) {
			passed("Succesfully validated tCO2 value" + tCO2ActivityRHP + " And Actual is " + valuetCO2.getText());
		} else {
			fail("Failed validated tCO2 value" + " " + tCO2ActivityRHP + " But Actual is " + valuetCO2.getText());
		}
		WebElement valueCH4 = driver.findElement(By.xpath("//span[text()='tCH4']//parent::p//following-sibling::p"));
		Double CH4 = Double.parseDouble(ch4) * Double.parseDouble(data.get("Amount of Energy")) / 1000;
		String CH4Activity = CH4.toString();
		String CH4ActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(CH4Activity);
		if (CH4ActivityRHP.equals(valueCH4.getText())) {
			passed("Succesfully validated CH4 value" + CH4ActivityRHP + " And Actual is " + valueCH4.getText());
		} else {
			fail("Failed validated CH4 value" + " " + CH4ActivityRHP + " But Actual is " + valueCH4.getText());
		}
		WebElement valueN2O = driver.findElement(By.xpath("//span[text()='tN2O']//parent::p//following-sibling::p"));
		Double N2O = Double.parseDouble(n2o) * Double.parseDouble(data.get("Amount of Energy")) / 1000;
		String N2OActivity = N2O.toString();
		String N2OActivityRHP = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(N2OActivity);
		if (N2OActivityRHP.equals(valueN2O.getText())) {
			passed("Succesfully validated N2O value" + N2OActivityRHP + " And Actual is " + valueN2O.getText());
		} else {
			fail("Failed validated N2O value" + " " + N2OActivityRHP + " But Actual is " + valueN2O.getText());
		}
		WebElement CO2eActivity = driver
				.findElement(By.xpath("//span[text()='tCO2e']//parent::p//following-sibling::p"));
		Double ValCO2e = valueCo2eedit * Double.parseDouble(data.get("Amount of Energy")) / 1000;
		String ValCO2eRHP = ValCO2e.toString();
		if (CO2eActivity.getText().equals(ValCO2eRHP)) {
			passed("Succesfully validated tCO2e value" + ValCO2eRHP + " And Actual is " + CO2eActivity.getText());
		} else {
			fail("Failed validated tCO2e value" + " " + ValCO2eRHP + " But Actual is " + CO2eActivity.getText());
		}
		WebElement EFActivity = driver
				.findElement(By.xpath("//span[text()='Emission Factor']//parent::p//following-sibling::p"));
		String s2 = EFActivity.getText().trim();
		// String[] EFValueRHP = s2.split(" ");
		if (s2.equals(co2eFactoredit)) {
			passed("Succesfully validated tCO2e Expected value" + " " + co2eFactor + " And Actual is " + s2);
		} else {
			fail("Failed validated tCO2e Expected value" + " " + co2eFactor + " But Actual is " + s2);
		}
	}

	public static Double leapOvrlapDays(String daystart, String dayEnd, String leapPrdstrt, String leapPrdend)
			throws ParseException {
		Double leapoverlapdays = 0.0;
		Date dateActStrt;
		Date dateperiodstrt;
		Date dateActEnd;
		Date dateperiodend;
		SimpleDateFormat dates = new SimpleDateFormat("yyyy/MM/dd");
		dateActStrt = dates.parse(daystart);
		dateActEnd = dates.parse(dayEnd);
		dateperiodstrt = dates.parse(leapPrdstrt);
		dateperiodend = dates.parse(leapPrdend);
		if (dateActStrt.compareTo(dateperiodstrt) == 0 && dateActEnd.compareTo(dateperiodend) == 0) {
			leapoverlapdays = 366.0;
		}
		return leapoverlapdays;
	}

	public static Double calculateDaysDiff(String daystart, String dayEnd) {
		Date date1;
		Date date2;
		SimpleDateFormat dates = new SimpleDateFormat("yyyy/MM/dd");
		Double differenceDates = 0.0;
		try {
			date1 = dates.parse(daystart);
			date2 = dates.parse(dayEnd);
			Double difference = (double) Math.abs(date1.getTime() - date2.getTime());
			differenceDates = (difference / (24 * 60 * 60 * 1000)) + 1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return differenceDates;
	}

	public static Double overlappDaysCount(String daystart, String dayEnd, String datePrdstrt, String datePrdend)
			throws Exception {
		Double overlapdays = 0.0;
		Date dateActStrt;
		Date dateperiodstrt;
		Date dateActEnd;
		Date dateperiodend;
		SimpleDateFormat dates = new SimpleDateFormat("yyyy/MM/dd");
		dateActStrt = dates.parse(daystart);
		dateActEnd = dates.parse(dayEnd);
		dateperiodstrt = dates.parse(datePrdstrt);
		dateperiodend = dates.parse(datePrdend);
		if (dateActStrt.compareTo(dateperiodstrt) > 0 && dateActEnd.compareTo(dateperiodend) < 0) {
			overlapdays = calculateDaysDiff(daystart, dayEnd);
		} else if (dateActStrt.compareTo(dateperiodstrt) <= 0 && dateActEnd.compareTo(dateperiodend) < 0
				&& dateActEnd.compareTo(dateperiodstrt) > 0) {
			overlapdays = calculateDaysDiff(datePrdstrt, dayEnd);
		} else if (dateActStrt.compareTo(dateperiodstrt) > 0 && dateActEnd.compareTo(dateperiodend) >= 0
				&& dateActStrt.compareTo(dateperiodend) < 0) {
			overlapdays = calculateDaysDiff(daystart, datePrdend);
		} else if (dateActStrt.compareTo(dateperiodstrt) < 0 && dateActEnd.compareTo(dateperiodend) <= 0
				&& dateActEnd.compareTo(dateperiodstrt) >= 0) {
			overlapdays = calculateDaysDiff(datePrdstrt, dayEnd);
		} else if (dateActStrt.compareTo(dateperiodstrt) < 0 && dateActEnd.compareTo(dateperiodend) < 0
				&& dateActEnd.compareTo(dateperiodstrt) <= 0) {
			overlapdays = 0.0;
		} else if (dateActStrt.compareTo(dateperiodstrt) > 0 && dateActEnd.compareTo(dateperiodend) > 0
				&& dateActStrt.compareTo(dateperiodend) >= 0) {
			overlapdays = 0.0;
		} else if (dateActStrt.compareTo(dateperiodstrt) == 0 && dateActEnd.compareTo(dateperiodend) == 0) {
			overlapdays = 365.0;
		} else if (dateActStrt.compareTo(dateperiodstrt) < 0 && dateActEnd.compareTo(dateperiodend) > 0) {
			overlapdays = 365.0;
		}
		return overlapdays;
	}

	public void selectOrganizationFromViewOrghierarchy(String organizationName) {
		try {
			verifyIfElementPresent(verifyOrgViewScreen, "View Org Screen", "IndirectEmissionPage");
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

	public void clickOnActivityInActivitiesGridMultipleForTransactions() {
		try {
			WebElement gridActivityForTransactions = driver
					.findElement(By.xpath("//*[text()='" + Constants.InvoiceNo + "']"));
			clickOn(gridActivityForTransactions, "Activity of Facility" + Constants.InvoiceNo);
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void validateCreatedTransactionBillType() {
		ValidateActivityDetailsInViewActivityScope2();
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
//	@FindBy(xpath = "//div[contains(text(),'Fuels')]//parent::div//*[@fill='none']")
//	private WebElement customchevronicon;
//
//	public void clickOnEmissionFactors() {
//		try {
//			sleep(4000);
//			WebElement clickOnEmissionFactors = driver.findElement(By.xpath("//ul/li[@aria-label='Emission Factors']"));
//			clickOn(clickOnEmissionFactors, "clicked");
//			sleep(2000);
//			Actions sda = new Actions(driver);
//			sda.moveToElement(customchevronicon).click().perform();
//			// clickOn(customchevronicon, "click on energy dropdown");
//			WebElement energytype = driver
//					.findElement(By.xpath("//ul[@role='listbox']//li[text()='" + data.get("cusemissiontype") + "']"));
//			clickOn(energytype, data.get("cusemissiontype"));
//		} catch (Exception e) {
//			failed(driver, "Exception caught" + e.getMessage());
//		}
//	}

	@FindBy(xpath = "//input[@type='search']")
	private WebElement searchtxt;
	@FindBy(xpath = "//*[@data-testid='SearchOutlinedIcon']")
	private WebElement searchfacility;
	@FindBy(xpath = "//div[contains(text(),'Scope 2 | Indirect Emissions')]")
	private WebElement weScope2emission;
	@FindBy(xpath = "(//div/span[contains(text(),'Advanced Setup')])[3]")
	private WebElement BtnadvancedSetup;
	@FindBy(xpath = "(//input[@id='energy_Data']//parent::div//button)[2]")
	private WebElement facilityEnergyCategorydrpdwn;
	@FindBy(xpath = "(//input[@id='location_emission']//parent::div//button)[2]")
	private WebElement EmissionFactorType;
	@FindBy(xpath = "(//input[@placeholder='Select Emission Factor']//parent::div//button)[2]")
	private WebElement emissionfactor;
	@FindBy(xpath = "(//input[@id='market_emission']//parent::div//button)[2]")
	private WebElement marketbasedemf;
	@FindBy(xpath = "(//input[@id='market_emissionType']//parent::div//button)[2]")
	private WebElement mbemissionfactor;
	@FindBy(xpath = "//input[@id='units']//parent::div//button[@aria-label='Open']")
	private WebElement defaultUnit;
	@FindBy(xpath = "//button[contains(text(),'Add')]")
	private WebElement btnaddcef;
	@FindBy(xpath = "(//button[contains(text(),'Close')])[2]")
	private WebElement btnclose;

	/*
	 * public void clickOnFacilities() { try { sleep(2000); WebElement facility =
	 * driver.findElement(By.xpath("//ul//li[@aria-label='Facilities']"));
	 * clickOn(facility, "clicked"); sleep(2000); enterText(searchtxt,
	 * "facilityname", data.get("Facility Name")); clickOn(searchfacility,
	 * "clicked"); sleep(2000); } catch (Exception e) { failed(driver,
	 * "Exception caught" + e.getMessage()); } }
	 */

	@FindBy(xpath = "//div[contains(@class,'MuiDrawer-pape')]/div//*[local-name() = 'svg']")
	private WebElement hambmenu;

	/*
	 * public void clickOnFacilityinFacilityGridMultiple() { try { sleep(3000);
	 * WebElement facilitygrid = driver .findElement(By.xpath("//*[text()='" +
	 * data.get("Facility Name") + "']//parent::div")); clickOn(facilitygrid,
	 * "Activity of Facility"); JavascriptExecutor js = (JavascriptExecutor) driver;
	 * WebElement weScope2emission = driver .findElement(By.
	 * xpath("//div[contains(text(),'Scope 2 | Indirect Emissions')]"));
	 * js.executeScript("arguments[0].scrollIntoView();", weScope2emission);
	 * isElementPresent(weScope2emission); if
	 * (weScope2emission.getText().contains("Scope 2 | Indirect Emissions")) {
	 * clickOn(weScope2emission, "click on Scope 2 | Indirect Emissions"); } else {
	 * failed(driver, "Failed to click on Scope2 Indirect Emissions"); }
	 * clickOn(BtnadvancedSetup,
	 * "Clicked on Scope2 IndirectEmissions AdvancedSetUp"); sleep(2000);
	 * 
	 * clickOn(hambmenu, "click on hamburger menu"); sleep(2000);
	 * 
	 * clickOn(facilityEnergyCategorydrpdwn, "Clicked on EnergyType in Facilities");
	 * WebElement Energytyp = driver .findElement(By.xpath("//li[text()='" +
	 * data.get("energyCategoryFacility") + "']")); clickOn(Energytyp,
	 * data.get("energyCategoryFacility")); clickOn(EmissionFactorType,
	 * "clicked on emission factor type"); WebElement eftype =
	 * driver.findElement(By.xpath("//ul//li[text()='Custom']")); eftype.click();
	 * clickOn(emissionfactor, "clicked on emission factor"); WebElement efname =
	 * driver.findElement(By.xpath("//ul//li[text()='" + GlobalVariables.nameOfCEF +
	 * "']")); clickOn(efname, "CustomEmissionFactorName"); clickOn(marketbasedemf,
	 * " market based emission factor"); WebElement mbemftype =
	 * driver.findElement(By.xpath("//ul//li[text()='Custom']")); clickOn(mbemftype,
	 * "marketbasedcustomemission"); clickOn(mbemissionfactor,
	 * "clicked on market based emission factor");
	 * 
	 * WebElement mbefname = driver.findElement(By.xpath("//ul//li[text()='" +
	 * GlobalVariables.nameOfCEF + "']")); clickOn(mbefname,
	 * "CustomEmissionFactorName"); clickOn(defaultUnit, "select default unit");
	 * WebElement defunit = driver.findElement(By.
	 * xpath("//ul//li[contains(text(),'kilowatt-hour (kWh)')]")); clickOn(defunit,
	 * "defaultUnit "); clickElement(btnaddcef,
	 * "Indirect Emissions AdvancedSetup added "); WebElement toastmessage = driver
	 * .findElement(By.
	 * xpath("//*[contains(text(),'Advanced setup updated successfully')]")); if
	 * (isElementPresent(toastmessage)) {
	 * passed("Successfully updated advanced setup"); } else { failed(driver,
	 * "failed to update advanced setup"); } clickOn(btnclose, "closed"); } catch
	 * (Exception e) { failed(driver, "Exception caught" + e.getMessage()); } }
	 */

	public void clickOnGHGcalculator() {
		try {
			WebElement ghgcalc = driver.findElement(By.xpath("//ul//li[@aria-label='GHG Calculators']/div"));
			clickOn(ghgcalc, "GHG Calculators");
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}
	/*
	 * @FindBy(xpath =
	 * "//div[contains(text(),'Fuels')]//parent::div//*[@fill='none']") private
	 * WebElement customchevronicon; ======= >>>>>>>
	 * 9aad036d0e1748d98732738c950061304b6fd971
	 * 
	 * public void clickOnViewOrgIndirectEmission() { try { WebElement vorg =
	 * driver.findElement(By.xpath("//article[@aria-label='View Organization Data']"
	 * )); clickOn(vorg, "clicked"); } catch (Exception e) { failed(driver,
	 * "Exception caught" + e.getMessage()); } } /*
	 * 
	 * @FindBy(xpath =
	 * "//div[contains(text(),'Fuels')]//parent::div//*[@fill='none']") private
	 * WebElement customchevronicon;
	 * 
	 * public IndirectEmissionsCalculatorPage clickOnEmissionFactors() {
	 */
	/*
	 * try { sleep(4000); WebElement clickOnEmissionFactors =
	 * driver.findElement(By.xpath("(//ul[@role='tree']//li/div)[4]"));
	 * clickOn(clickOnEmissionFactors, "clicked"); sleep(2000); Actions sda = new
	 * Actions(driver); sda.moveToElement(customchevronicon).click().perform(); //
	 * clickOn(customchevronicon, "click on energy dropdown"); WebElement energytype
	 * = driver .findElement(By.xpath("//ul[@role='listbox']//li[text()='" +
	 * data.get("cusemissiontype") + "']")); clickOn(energytype,
	 * data.get("cusemissiontype")); } catch (Exception e) { failed(driver,
	 * "Exception caught" + e.getMessage()); } return new
	 * IndirectEmissionsCalculatorPage(driver, data); // returning new object //
	 * IndirectEmissionsCalculatorPage
	 */

	public void clickOnactivityinGridMultiple() {
		try {
			WebElement orgFacility = driver
					.findElement(By.xpath("(//span[contains(text(),'" + data.get("Facility Name") + "')])[1]"));
			clickOn(orgFacility, data.get("Facility Name"));
			sleep(2000);
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void validateTOTALCO2EforIndirectemission() {
		try {
			String calTco2e7 = "";
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Indirect Emissions");
			if (Constants.selectedGWP.contains("2007 IPCC Fourth Assessment (AR4, 100-Year GWP)")) {
				WebElement arco2e = driver
						.findElement(By.xpath("(//span[contains(text(),'CO2')]//parent::p//following-sibling::p)[7]"));
				String[] splitEF1 = arco2e.getText().split(" ");
				Double multi1 = Double.parseDouble(splitEF1[0]) * Constants.GWParCO2;
				WebElement arch4 = driver
						.findElement(By.xpath("(//span[contains(text(),'CH4')]//parent::p//following-sibling::p)[3]"));
				String[] splitEF2 = arch4.getText().split(" ");
				Double multi2 = Double.parseDouble(splitEF2[0]) * Constants.GWPar4CH4;
				WebElement arn2o = driver
						.findElement(By.xpath("(//span[contains(text(),'N2O')]//parent::p//following-sibling::p)[3]"));
				String[] splitEF3 = arn2o.getText().split(" ");
				Double multi3 = Double.parseDouble(splitEF3[0]) * Constants.GWPar4N2O;
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
				WebElement ee = driver.findElement(
						By.xpath("//span[contains(text(),'Conversion')]//parent::p//following-sibling::p"));
				String hh = ee.getText();
				String[] yy = hh.split("=");
				String[] t = yy[1].split(" ");
				Double d = Double.parseDouble(t[1]);
				if (splitEF4[1].trim().equals(Constants.conToTonnfrlb)) {
					Double result7 = (Double.parseDouble(CalEF7) * Double.parseDouble(data.get("Amount of Energy")) * d)
							/ 2204.623;
					calTco2e7 = result7.toString();
				} else if (splitEF4[1].trim().equals(Constants.conToTonnfrKg)) {
					Double result7 = (Double.parseDouble(CalEF7) * Double.parseDouble(data.get("Amount of Energy")) * d)
							/ 1000;
					calTco2e7 = result7.toString();
				} else if (splitEF4[1].trim().equals(Constants.conToTonnfrg)) {
					Double result7 = (Double.parseDouble(CalEF7) * Double.parseDouble(data.get("Amount of Energy")) * d)
							/ 1000000;
					calTco2e7 = result7.toString();
				}
				System.out.println("Calculated tCO2e value should be  :" + calTco2e7);
				WebElement valuetCO2e7 = driver
						.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
				if (calTco2e7.trim().equals(valuetCO2e7.getText().trim())) {
					passed("Successfully validated tCO2e Location Based value for Scope 2 - Indirect Emissions Actual "
							+ valuetCO2e7.getText() + " Expected tco2e " + calTco2e7);
				} else {
					failed(driver,
							"Failed to validate tCO2e Location Based  value for Scope 2 - Indirect Emissions Actual "
									+ valuetCO2e7.getText() + " Expected tco2e " + calTco2e7);
				}
				sleep(3);
				WebElement tco2edaily = driver.findElement(By.xpath(
						"//p[contains(text(),'Emission Details - Location Based')]//parent::div/parent::div//following-sibling::div//span[text()='tCO2e (Daily)']//parent::p//following-sibling::p"));
				if (tco2edaily.getText().trim().equals(data.get("tCO2e(Daily)"))) {
					passed("Successfully validated tCO2e(Daily) for location based as " + data.get("tCO2e(Daily)"));
				} else {
					failed(driver, "Failed to validate tCO2e(Daily) value for Location Based Actual as "
							+ data.get("tCO2e(Daily)") + " Expected tCO2e(Daily) " + tco2edaily.getText());
				}
				WebElement tco2edaily1 = driver.findElement(By.xpath(
						"//p[contains(text(),'Emission Details - Market Based')]//parent::div/parent::div//following-sibling::div//span[text()='tCO2e (Daily)']//parent::p//following-sibling::p"));
				if (tco2edaily1.getText().trim().equals(data.get("tCO2e(Daily)(M)"))) {
					passed("Successfully validated tCO2e(Daily) for Market Based as " + data.get("tCO2e(Daily)(M)"));
				} else {
					failed(driver, "Failed to validate tCO2e(Daily) value for Market Based Actual as "
							+ data.get("tCO2e(Daily)(M)") + " Expected tCO2e(Daily) " + tco2edaily.getText());
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
				System.out.println("Calculated tCO2e value should be  :" + calTco2e8);
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
				WebElement emissionfactor = driver.findElement(
						By.xpath("(//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p)[2]"));
				String[] splitEF4 = emissionfactor.getText().split(" ");
				if (splitEF4[0].trim().equals(CalEF8)) {
					passed("Successfully validated EmissionFactor for loaction based as " + CalEF8);
				} else {
					failed(driver, "Failed to validate EmissionFactor for loaction based as " + CalEF8
							+ " but Actual is " + splitEF4[0].trim());
				}
				WebElement ee = driver.findElement(
						By.xpath("//span[contains(text(),'Conversion')]//parent::p//following-sibling::p"));
				String hh = ee.getText();
				String[] yy = hh.split("=");
				String[] t = yy[1].split(" ");
				Double d = Double.parseDouble(t[1]);
				Double result8 = (Double.parseDouble(splitEF8[0]) * Double.parseDouble(data.get("Amount of Energy"))
						* d) / 1000;
				String calTco2e8 = result8.toString();
				System.out.println("Calculated tCO2e value should be  :" + calTco2e8);
				WebElement valuetCO2e8 = driver
						.findElement(By.xpath("//span[contains(text(),'tCO2e')]//parent::p//following-sibling::p"));
				if (calTco2e8.trim().equals(valuetCO2e8.getText().trim())) {
					passed("Successfully validated tCO2e Location Based value for Scope 2 - Indirect Emissions "
							+ valuetCO2e8.getText());
				} else {
					failed(driver, "Failed to valiadte tCO2e Location Based value for Scope 2 - Indirect Emissions "
							+ valuetCO2e8.getText());
				}
				sleep(3);
				//---tco2e daily----
				String daystart = data.get("Start Date");
				String dayEnd = data.get("End Date");
				Double differenceDays = calculateDaysDiff(daystart, dayEnd);
				Double tco2eLocation = result8/differenceDays;
				
				WebElement tco2edailyLocation = driver.findElement(By.xpath(
						"(//p[contains(text(),'Emission Details - Location Based')]//parent::div/parent::div//following-sibling::div//span[text()='tCO2e (Daily)']//parent::p//following-sibling::p/div)[1]"));
				if (tco2edailyLocation.getText().trim().equals(tco2eLocation.toString())) {
					passed("Successfully validated tCO2e(Daily) for location based as " + data.get("tCO2e(Daily)"));
				} else {
					failed(driver, "Failed to validate tCO2e(Daily) value for location based as actual==>"
							+ tco2edailyLocation.getText() + "and Expected tCO2e(Daily)==>" + tco2eLocation);
				}
				WebElement tco2edailyMarket = driver.findElement(By.xpath(
						"(//p[contains(text(),'Emission Details - Location Based')]//parent::div/parent::div//following-sibling::div//span[text()='tCO2e (Daily)']//parent::p//following-sibling::p/div)[2]"));
				if (tco2edailyMarket.getText().trim().equals(data.get("tCO2e(Daily)(M)"))) {
					passed("Successfully validated tCO2e(Daily) for Market Based as actual==>" + tco2edailyMarket+" and expected is==>"+tco2eLocation);
				} else {
					failed(driver, "Failed to validate tCO2e(Daily) value for Market Based Actual as "
							+ tco2edailyMarket.getText() + " Expected tCO2e(Daily) " + tco2eLocation);
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void ValidateEmissionDetailsforLocationBased() {
		try {
			sleep(2000);
			Double sp = 0.0;
			WebElement emissionfactor = driver.findElement(
					By.xpath("(//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p)[2]"));
			String[] splitEF4 = emissionfactor.getText().split(" ");
			WebElement ee = driver
					.findElement(By.xpath("//span[contains(text(),'Conversion')]//parent::p//following-sibling::p"));
			String hh = ee.getText();
			String[] yy = hh.split("=");
			String[] t = yy[1].split(" ");
			Double d = Double.parseDouble(t[1]);
			int flag = 0;
			int i, k, l = 0, m = 0;
			String[] tva = { "tCO2", "tCH4", "tN2O", "tCO2", "tCH4", "tN2O" };
			{
				for (int j = 0; j < tva.length; j++) {
					if (flag <= 2) {
						WebElement tval = driver.findElement(By.xpath(
								"//p[text()='Emission Details - Location Based']//parent::div//parent::div//following-sibling::div/div//span[text()='"
										+ tva[j] + "']//parent::p/following-sibling::p"));
						Double dv = Double.parseDouble(tval.getText().trim());
						String[] Emiloc = { "CO2", "CH4", "N2O" };
						for (i = l; i <= Emiloc.length; i++) {
							WebElement val = driver.findElement(By.xpath(
									"//p[text()='Emission Factors - Location Based']//parent::div//parent::div//following-sibling::div/div//span[text()='"
											+ Emiloc[i] + "']//parent::p/following-sibling::p"));
							String[] splitval = val.getText().split(" ");
							if (splitEF4[1].trim().equals(Constants.conToTonnfrlb)) {
								sp = (Double.parseDouble(splitval[0]) * Double.parseDouble(data.get("Amount of Energy"))
										* d) / 2204.623;
							} else if (splitEF4[1].trim().equals(Constants.conToTonnfrKg)) {
								sp = (Double.parseDouble(splitval[0]) * Double.parseDouble(data.get("Amount of Energy"))
										* d) / 1000;
							} else if (splitEF4[1].trim().equals(Constants.conToTonnfrg)) {
								sp = (Double.parseDouble(splitval[0]) * Double.parseDouble(data.get("Amount of Energy"))
										* d) / 1000000;
							}
							String Loaction = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(sp.toString());
							if (tval.getText().equals(Loaction)) {
								passed("Successfully validated emission details location based for " + Emiloc[i]
										+ " as actual is==>" + dv +" and expected is==>"+Loaction);
							} else {
								failed(driver, "Failed to validate emission details location based for " + Emiloc[i]
										+ " as actual is==>" + dv + " but expected is==>" + Loaction);
							}
							i = i + 3;
						}
						l = i - 3;
						flag = flag + 1;
						;
					} else if (flag > 2) {
						WebElement tval = driver.findElement(By.xpath(
								"//p[text()='Emission Details - Market Based']//parent::div//parent::div//following-sibling::div/div//span[text()='"
										+ tva[j] + "']//parent::p/following-sibling::p"));
						Double dv = Double.parseDouble(tval.getText().trim());
						String[] Emiloc = { "CO2", "CH4", "N2O" };
						for (k = m; k < Emiloc.length; k++) {

							WebElement val = driver.findElement(By.xpath(
									"//p[text()='Emission Factors - Market Based']//parent::div//parent::div//following-sibling::div/div//span[text()='"
											+ Emiloc[k] + "']//parent::p/following-sibling::p"));
							String[] splitval = val.getText().split(" ");
							if (splitEF4[1].trim().equals(Constants.conToTonnfrlb)) {
								sp = (Double.parseDouble(splitval[0]) * Double.parseDouble(data.get("Amount of Energy"))
										* d) / 2204.623;
							} else if (splitEF4[1].trim().equals(Constants.conToTonnfrKg)) {
								sp = (Double.parseDouble(splitval[0]) * Double.parseDouble(data.get("Amount of Energy"))
										* d) / 1000;
							} else if (splitEF4[1].trim().equals(Constants.conToTonnfrg)) {
								sp = (Double.parseDouble(splitval[0]) * Double.parseDouble(data.get("Amount of Energy"))
										* d) / 1000000;
							}
							String Market = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(sp.toString());
							String emissionValues = dv.toString();
							if (tval.getText().equals(Market)) {
								passed("Successfully validated emission details market based for " + Emiloc[k] + " as actual is==>"+dv+
									"and expected is==>"+ Market);
							} else {
								failed(driver, "Failed to validate emission details market based for " + Emiloc[k]
										+ " as actual is==>" + dv + "but expected is==>" + Market);
							}
							k = k + 3;
						}
						flag = flag + 1;

						m = k - 3;
						;
					}
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void ValidateActivityDetailsInViewActivityAddedBillsScope2forAllCategories() {
		try {
			sleep(2000);

			Actions action = new Actions(driver);
			// List<WebElement> record =
			// driver.findElements(
			// By.xpath("//span[text()='Invoice No.']//ancestor::div//span[text()='" +
			// Constants.inv + "']"));
			//
			for (int i = 1; i <= 4; i++) {
				WebElement gh = driver.findElement(By.xpath("(//*[@col-id='facility_name']//span[@aria-label='"
						+ data.get("Facility Name") + "'])[" + i + "]"));
				waitForElement(gh);
				verifyElementAndHighlightIfExists(gh, "Facility Name", "IndirectEmissionsCalculatorPage");
				action.moveToElement(gh).click().perform();
				sleep(2000);
				WebElement energytype = driver
						.findElement(By.xpath("//span[text()='Energy Category']//parent::p//following-sibling::p"));
				if (energytype.getText().trim().equals("District Heating")) {
					System.out.println("----------- Emissions Details are--------------");
					String[] activityDetailFieldNames = { "Facility Name", "Energy Category", "Start Date", "End Date",
							"Invoice No.", "Meter", "Invoice Date", "Amount of Energy", "Calorific Value",
							"Calorific Unit", "Units", "Cost", "Currency", "tCO2", "tCH4", "tN2O", "tCO2e",
							"Emission Factor", "Source" };
					Thread.sleep(2000);
					verifyIfElementPresent(lblActivityDetails, "lblViewActivityFulEngy", "Indirect emission");
					for (int j = 0; j < activityDetailFieldNames.length; j++) {
						WebElement weActivityField = driver.findElement(By.xpath("//span[text()='"
								+ activityDetailFieldNames[j] + "']//parent::p//following-sibling::p//div"));
						if (weActivityField.getText().trim().contains(data.get(activityDetailFieldNames[j]).trim())) {
							passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
									+ weActivityField.getText());
						} else {
							failed(driver, "Failed To validate " + activityDetailFieldNames[j]
									+ " In Activity Details Expected As " + data.get(activityDetailFieldNames[j])
									+ "But Actual is" + weActivityField.getText());
						}

					}
					clickOnElement(btclose, "activity details close");
				} else if (energytype.getText().trim().equals("Cooling")) {
					System.out.println("----------- Emissions Details are--------------");
					String[] activityDetailFieldNames = { "Facility Name", "Energy Category", "Start Date", "End Date",
							"Invoice No.", "Invoice Date", "Amount of Energy", "Units", "Cost", "Currency", "tCO2",
							"tCH4", "tN2O", "tCO2e", "Emission Factor", "Source" };
					Thread.sleep(5000);
					verifyIfElementPresent(lblActivityDetails, "lblViewActivityFulEngy", "Indirect emission");
					for (int j = 0; j < activityDetailFieldNames.length; j++) {
						WebElement weActivityField = driver.findElement(By.xpath("//span[text()='"
								+ activityDetailFieldNames[j] + "']//parent::p//following-sibling::p//div"));
						if (weActivityField.getText().trim().contains(data.get(activityDetailFieldNames[j]).trim())) {
							passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
									+ weActivityField.getText());
						} else {
							failed(driver, "Failed To validate " + activityDetailFieldNames[j]
									+ " In Activity Details Expected As " + data.get(activityDetailFieldNames[j])
									+ "But Actual is" + weActivityField.getText());
						}
					}
					clickOnElement(btclose, "activity details close");
				} else if (energytype.getText().trim().equals("Steam")) {
					System.out.println("----------- Emissions Details are--------------");
					String[] activityDetailFieldNames = { "Facility Name", "Energy Category", "Start Date", "End Date",
							"Invoice No.", "Invoice Date", "Amount of Energy", "Units", "Cost", "Currency", "tCO2",
							"tCH4", "tN2O", "tCO2e", "Emission Factor", "Source" };
					Thread.sleep(5000);
					verifyIfElementPresent(lblActivityDetails, "lblViewActivityFulEngy", "Indirect emission");
					for (int j = 0; j < activityDetailFieldNames.length; j++) {
						WebElement weActivityField = driver.findElement(By.xpath("//span[text()='"
								+ activityDetailFieldNames[j] + "']//parent::p//following-sibling::p//div"));
						if (weActivityField.getText().trim().contains(data.get(activityDetailFieldNames[j]).trim())) {
							passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
									+ weActivityField.getText());
						} else {
							failed(driver, "Failed To validate " + activityDetailFieldNames[j]
									+ " In Activity Details Expected As " + data.get(activityDetailFieldNames[j])
									+ "But Actual is" + weActivityField.getText());
						}
					}
					clickOnElement(btclose, "activity details close");
				} else if (energytype.getText().trim().equals("Electricity")) {
					System.out.println("----------- Emissions Details are--------------");
					String[] activityDetailFieldNames = { "Facility Name", "Energy Category", "Start Date", "End Date",
							"Invoice No.", "Invoice Date", "Amount of Energy", "Calorific Value", "Calorific Unit",
							"Meter", "Units", "Cost", "Currency", "tCO2", "tCH4", "tN2O", "tCO2e", "Emission Factor",
							"Source" };
					Thread.sleep(5000);
					verifyIfElementPresent(lblActivityDetails, "lblViewActivityFulEngy", "Indirect emission");
					for (int j = 0; j < activityDetailFieldNames.length; j++) {
						WebElement weActivityField = driver.findElement(By.xpath("//span[text()='"
								+ activityDetailFieldNames[j] + "']//parent::p//following-sibling::p//div"));
						if (weActivityField.getText().trim().contains(data.get(activityDetailFieldNames[j]).trim())) {
							passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
									+ weActivityField.getText());
						} else {
							failed(driver, "Failed To validate " + activityDetailFieldNames[j]
									+ " In Activity Details Expected As " + data.get(activityDetailFieldNames[j])
									+ "But Actual is" + weActivityField.getText());
						}
					}
					clickOnElement(btclose, "activity details close");
				}
			}
		} catch (InterruptedException e) {
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

	public void ValidateActivityDetailsInViewActivityAddedBillsScope2meters() {
		try {
			Actions action = new Actions(driver);
			WebElement gh = driver.findElement(By.xpath(
					"(//div[@col-id='facility_name']//span[@aria-label='" + data.get("Facility Name") + "'])[1]"));
			waitForElement(gh);
			verifyElementAndHighlightIfExists(gh, "Facility Name", "IndirectEmissionsCalculatorPage");
			action.moveToElement(gh).click().perform();
			sleep(2000);
			System.out.println("----------- Emissions Details are--------------");
			String[] activityDetailFieldNames = { "Facility Name", "Energy Category", "Start Date", "End Date",
					"Invoice No.", "Invoice Date", "Amount of Energy", "Calorific Value", "Calorific Unit", "Meter",
					"Units", "Cost", "Currency", "tCO2", "tCH4", "tN2O", "tCO2e", "Emission Factor", "Source" };
			Thread.sleep(5000);
			verifyIfElementPresent(lblActivityDetails, "lblViewActivityFulEngy", "Indirect emission");
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
		} catch (InterruptedException e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
	}

	public void verifyTCo2eCV() {
		try {
			sleep(7);
			WebElement amount = driver
					.findElement(By.xpath("//span[text()='Amount of Energy']//parent::p//following-sibling::p//div"));
			String s = amount.getText().trim().replace(",", "");
			double d = Double.valueOf(s);
			System.out.println(d);
			WebElement unitOfAmountOfEnergy = driver
					.findElement(By.xpath("//span[text()='Unit Conversion']//parent::p//following-sibling::p/div"));
			String[] valueOfunitOfAmountOfEnergy = unitOfAmountOfEnergy.getText().split("=");
			String[] sdf = valueOfunitOfAmountOfEnergy[1].split(" ");
			String u = sdf[1];
			double t = Double.parseDouble(u);
			double i = (d * t) / 1000;
			System.out.println(i);
			WebElement cv = driver
					.findElement(By.xpath("//span[text()='Calorific Value']//parent::p//following-sibling::p/div"));
			String j = cv.getText();
			if (Double.parseDouble(j) == i) {
				passed("Successfully Validated Calorific value In Activity Details As " + j);
			} else {
				failed(driver, "Failed to validate Activity Details As " + i + "But Actual is " + j);
			}
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
	}

	public void ValidateEmissionDetailsforMarketBased() {
		try {
			double sum = 0;
			int j, l = 0;
			String fitco2em = "";
			String s[] = { "CO2", "CH4", "N2O" };
			if (Constants.selectedGWP.contains("2007 IPCC Fourth Assessment (AR4, 100-Year GWP)")) {
				for (int i = 0; i < s.length; i++) {
					WebElement val = driver.findElement(By.xpath(
							"//p[text()='Emission Factors - Market Based']//parent::div/parent::div//following-sibling::div//div//span[text()='"
									+ s[i] + "']//parent::p//following-sibling::p"));
					String sp[] = val.getText().trim().split(" ");
					String w[] = { Constants.GWParCO2+"", Constants.GWPar4CH4+"", Constants.GWPar4N2O+"" };
					for (j = l; j <= w.length; j++) {
						Double d = Double.parseDouble(sp[0]) * Double.parseDouble(w[j]);
						sum = sum + d;
						j = j + 3;
					}
					l = j - 3;
				}
			} else if (Constants.selectedGWP.contains("2023 IPCC Sixth Assessment (AR6, 100-Year GWP)")) {
				for (int i = 0; i < s.length; i++) {
					WebElement val = driver.findElement(By.xpath(
							"//p[text()='Emission Factors - Market Based']//parent::div/parent::div//following-sibling::div//div//span[text()='"
									+ s[i] + "']//parent::p//following-sibling::p"));
					String sp[] = val.getText().trim().split(" ");
					String w[] = { Constants.GWParCO2+"", Constants.GWPar6CH4+"", Constants.GWPar6N2O+"" };
					for (j = l; j <= w.length; j++) {
						Double d = Double.parseDouble(sp[0]) * Double.parseDouble(w[j]);
						sum = sum + d;
						j = j + 3;
					}
					l = j - 3;
				}
			}
			WebElement efm = driver.findElement(By.xpath(
					"//p[contains(text(),'Emission Details - Market Based')]//parent::div/parent::div//following-sibling::div//span[text()='Emission Factor']//parent::p//following-sibling::p"));
			String u[] = efm.getText().trim().split(" ");
			String ap = Double.toString(sum);
			String Calco2em = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(ap);
			if (u[0].equals(Calco2em)) {
				passed("Successfully validated EmissionFactor for Market Based Emissions as " + u[0]);
			} else {
				failed(driver, "Failed to validate EmissionFactor for Market Based Emissions as " + u[0]
						+ " but Actual is " + sum);
			}

			WebElement ee = driver
					.findElement(By.xpath("//span[contains(text(),'Conversion')]//parent::p//following-sibling::p"));
			String hh = ee.getText();
			String[] yy = hh.split("=");
			String[] t = yy[1].split(" ");
			Double d = Double.parseDouble(t[1]);
			if (u[1].trim().equals(Constants.conToTonnfrlb)) {
				Double fitco2e = (sum * Double.parseDouble(data.get("Amount of Energy")) * d) / 2204.623;
				fitco2em = fitco2e.toString();
			} else if (u[1].trim().equals(Constants.conToTonnfrKg)) {
				Double fitco2e = (sum * Double.parseDouble(data.get("Amount of Energy")) * d) / 1000;
				fitco2em = fitco2e.toString();
			} else if (u[1].trim().equals(Constants.conToTonnfrg)) {
				Double fitco2e = (sum * Double.parseDouble(data.get("Amount of Energy")) * d) / 1000000;
				fitco2em = fitco2e.toString();
			}
			System.out.println("Calculated tCO2e value should be  :" + fitco2em);
			WebElement valuetCO2e8 = driver.findElement(By.xpath(
					"//p[contains(text(),'Emission Details - Market Based')]//parent::div/parent::div//following-sibling::div//span[text()='tCO2e']//parent::p//following-sibling::p"));
			if (fitco2em.trim().equals(valuetCO2e8.getText().trim())) {
				passed("Successfully validated tCO2e value for Market Based Emissions as  " + valuetCO2e8.getText()
						+ " Expected " + fitco2em);
			} else {
				failed(driver, "Failed to validate tCO2e value for Market Based Emissions as  " + valuetCO2e8.getText()
						+ " but actual is " + fitco2em);
			}
			WebElement sourcem = driver.findElement(By.xpath(
					"//p[contains(text(),'Emission Factors - Market Based')]//parent::div/parent::div//following-sibling::div//span[text()='Source']//parent::p//following-sibling::p"));
			if (sourcem.getText().trim().equals(data.get("Source(Market)"))) {
				passed("Successfully validated Source for market based emissions as " + data.get("Source(Market)"));
			} else {
				failed(driver, "Failed to validate Source for market based emissions as " + data.get("Source(Market)")
						+ " but the actal is " + sourcem.getText().trim());
			}
			String[] activityDetailFieldNames = { "CO2", "CH4", "N2O" };
			for (int p = 0; p < activityDetailFieldNames.length; p++) {
				WebElement weActivityField = driver.findElement(By.xpath("(//span[text()='"
						+ activityDetailFieldNames[p] + "']//parent::p//following-sibling::p//div)[2]"));
				if (weActivityField.getText().trim().contains(data.get(activityDetailFieldNames[p] + "(M)").trim())) {
					passed("Successfully Validated " + activityDetailFieldNames[p] + "(M)" + " In Activity Details As"
							+ weActivityField.getText());
				} else {
					failed(driver,
							"Failed To validate " + activityDetailFieldNames[p] + "(M)"
									+ " In Activity Details Expected As " + data.get(activityDetailFieldNames[p])
									+ "(M)" + "But Actual is" + weActivityField.getText());
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	public void clickOnPurchasedEnergy() {
		try {
			waitForElement(btnPurchasedEnergy);
			clickOn(btnPurchasedEnergy, "Purchased Energy");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
