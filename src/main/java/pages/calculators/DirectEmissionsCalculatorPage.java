package pages.calculators;

import java.io.File;
import java.io.IOException;
import java.math.*;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import base.TestBase;
import groovyjarjarantlr4.v4.runtime.tree.xpath.XPath;
import pages.GHGCalculatorsPage;
import uielements.CalculatorElements;
import utilities.Constants;
import utilities.Data;
import utilities.ExcelWriter;
import utilities.GlobalKeys;
import utilities.GlobalVariables;

public class DirectEmissionsCalculatorPage extends GHGCalculatorsPage {
	private GHGCalculatorsPage gHGCalculatorsPage;

	public DirectEmissionsCalculatorPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}
	public void clickOnGHGCalculatorIcon()
	{
		clickOn(btnGHGCalc,"btnGHGCalculators");
	}

	public void ClickOnStationaryCombutionScope1() {
		clickOn(btnStationaryCombution, "btnStationaryCombution");
	}
	public void EditActivityForStationaryCombutionInDirectEmissions() {
		try {
			ReusabilityForFacilityNameAndStartDateAndEndDateForCalculators();
			entertTextInTextFieldForCalculators_ActivityDetails("Invoice No.");
			entertTextInTextFieldForCalculators_ActivityDetails("Invoice Date");
			SelectDropdownOptionsForCalculatorActivityFields("Fuel Type");
			ReusabilityForAmount$Units$Tags$Notes$SaveForCalculators("Fuel Amount","Unit","Tags","Notes");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void addAdvanceConfigurationForDirectEmissionsScope1() {
		try {
			GHGCalculatorsPage ghgCalculatorsPage = new GHGCalculatorsPage(driver, data);
			ghgCalculatorsPage.clickOnFacilities();
			clickOnGridInFacilitiesMenu();
			clickOnFacilityinFacilityGridMultipleDirect();
			clickOnGHGCalculatorIcon();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectDropdownOptionsForDirectEmissionsScope1(WebElement we,String eleName) {
		try {
			clickOn(we, eleName + "DropDown");
			String strOptions = "//li[text()='" + data.get(eleName) + "']";
			WebElement weUnitCEFDenominator = driver.findElement(By.xpath(strOptions));
			clickOn(weUnitCEFDenominator, data.get(eleName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void validateTCO2andTCH4andTN2O_ForStationaryCombustionInScope1() {         //Naveen
		try {
			String ApplicationEnvironment = GlobalKeys.configData.get("ApplicationEnvironment").toLowerCase();
			sleep(2);
			Double tEmissions;
			String convertToUnitType;
			if(data.get("unitCategory").equals("mass")) {
				 convertToUnitType = "//div[contains(text(),'Mass')]";        
			}else if(data.get("unitCategory").equals("energy")){
				convertToUnitType = "//div[contains(text(),'Energy')]";
			}
			else {
				convertToUnitType = "//div[contains(text(),'Volume')]";
			}
			String fuelTypeConvert = "//span[text()='Calorific Factor/Heat Content Value']/parent::p/../p/div";
			String[] emissions = {"tCO2","tCH4","tN2O"};
			Double[] rhpValues = {gHGCalculatorsPage.CO2Value,gHGCalculatorsPage.CH4ValueEXP,gHGCalculatorsPage.N2OValueEXP};
			for(int i=0; i<emissions.length; i++){
				if(data.get("Fuel Type").equals("Natural Gas")) {
					WebElement volume = driver.findElement(By.xpath(convertToUnitType));
					WebElement fuelTypeValue = driver.findElement(By.xpath(fuelTypeConvert));
					String splitVolume = volume.getText().split("= ")[1].split(" ")[0].replaceAll(",","");
					if(ApplicationEnvironment.equals("uat.eu")) {   //&& data.get("DataSet").equals("IEA DataSet")
						if(emissions[i].equals("tCO2")) {
							 tEmissions = Double.parseDouble(data.get("Fuel Amount")) 
										        * rhpValues[i]                                   //kg
												* Double.parseDouble(splitVolume)
												/ 1000;
						}else {
							 tEmissions = Double.parseDouble(data.get("Fuel Amount")) 
												* rhpValues[i] * 0.001                         //g - convert to kg
												* Double.parseDouble(splitVolume)
												/ 1000;
						}
					}else {
						String splitFuelValue = fuelTypeValue.getText().split("\\| ")[1].split(" ")[0];
						if(emissions[i].equals("tCO2")) {
							 tEmissions = Double.parseDouble(data.get("Fuel Amount")) 
										        * rhpValues[i]                                   //kg
												* Double.parseDouble(splitVolume)
												* Double.parseDouble(splitFuelValue.trim())
												/ 1000;
						}else {
							 tEmissions = Double.parseDouble(data.get("Fuel Amount")) 
												* rhpValues[i] * 0.001                         //g - convert to kg
												* Double.parseDouble(splitVolume)
												* Double.parseDouble(splitFuelValue.trim())
												/ 1000;
						}
					}
							      
				}else {
					WebElement energy = driver.findElement(By.xpath(convertToUnitType));
					String splitEnergy = energy.getText().split("= ")[1].split(" ")[0];
					if(emissions[i].equals("tCO2")) {
						 tEmissions = Double.parseDouble(data.get("Fuel Amount")) 
									        * rhpValues[i]                                   //kg
											* Double.parseDouble(splitEnergy)
											/ 1000;
					}else {
						 tEmissions = Double.parseDouble(data.get("Fuel Amount")) 
											* rhpValues[i] * 0.001                         //g - convert to kg
											* Double.parseDouble(splitEnergy)
											/ 1000;
					}
				}
				String expectedEmissions1 = tEmissions.toString().replaceAll(",", "");
				String expectedEmissions = approximateDecimalValueWithBigDecimal(expectedEmissions1);
				WebElement actualEmissions = driver.findElement(By
						.xpath("//span[text()='"+emissions[i]+"']/parent::p/following-sibling::p/div"));
				String actualEmissionFactor=actualEmissions.getText().replaceAll(",", "");
				if(expectedEmissions.equals(actualEmissionFactor)) {
					passed("Successfully validated, The activity of "+emissions[i]+" is expected is "+expectedEmissions+" "
							+ " and the actual as "+actualEmissionFactor+"");
				}else {
					Double doubleDiff= Double.parseDouble(expectedEmissions.trim()) - Double.parseDouble(actualEmissionFactor.trim());
					if(doubleDiff<1 && doubleDiff>-1) {    
						passed("Succesfully validated activityDetail" + expectedEmissions + " And Actual is " +actualEmissionFactor.trim());
					}else {
					    failed(driver,
							    "Failed validated activityDetail" + " " + expectedEmissions + " But Actual is " +actualEmissionFactor.trim());
					}
				}
			}
	} catch (Exception e) {
		failed(driver, "Exception caught " + e.getMessage());
	}
	}
	
	
	public void validateTCO2andTCH4andTN2O_ForMobileCombustionInScope1() {         //Naveen1
		try {
			String[] emissions = {"tCO2","tCH4","tN2O"};
			Double[] rhpValues = {GHGCalculatorsPage.CO2Value,GHGCalculatorsPage.CH4ValueEXP,GHGCalculatorsPage.N2OValueEXP};
			for(int i=0; i<emissions.length; i++){	
				WebElement volumeConv = driver.findElement(By.xpath("//div[contains(text(),'Volume')]"));
				String splitVolumeCov = volumeConv.getText().split("= ")[1].split(" ")[0].replaceAll(",", "");
				Double tEmissions = Double.parseDouble(data.get("Activity Amount")) 
				                    * rhpValues[i]                                  
						            * Double.parseDouble(splitVolumeCov)
						            / 1000;
				String expectedEmissions1 = tEmissions.toString().replaceAll(",", "");
				String expectedEmissions = approximateDecimalValueWithBigDecimal(expectedEmissions1);
				WebElement actualEmissions = driver.findElement(By
						.xpath("//span[text()='"+emissions[i]+"']/parent::p/following-sibling::p/div"));
				String actualEmissionValue=actualEmissions.getText().replaceAll(",", "");
				if(expectedEmissions.equals(actualEmissionValue)) {
					passed("Successfully validated, The activity of "+emissions[i]+" is expected is "+expectedEmissions+" "
							+ " and the actual as "+actualEmissionValue+"");
				}else {
					Double doubleDiff= Double.parseDouble(expectedEmissions) - Double.parseDouble(actualEmissionValue);
					info(""+doubleDiff+"");
					if(doubleDiff<1 && doubleDiff>-1) {
						passed("successfully validated, The activity of "+emissions[i]+" is expected is "+expectedEmissions+" "
								+ " and the actual as "+actualEmissionValue+"");
					}else {
						failed(driver,
								"failed to validate, The activity of "+emissions[i]+" is expected is "+expectedEmissions+" "
								+ " and the actual as "+actualEmissionValue+"");
					}
				}
			}
	} catch (Exception e) {
		failed(driver, "Exception caught " + e.getMessage());
	}
	}
	
	
	
	public void validateTCO2andTCH4andTN2O_ForMobileCombustionInScope1FuelEfficiency() {         //Naveen1
		try {
			Double conversionOfCO2=null;
			Double conversionValue = null;
			String[] emissions = {"tCO2","tCH4","tN2O"};
			Double[] rhpValues = {GHGCalculatorsPage.CO2Value,GHGCalculatorsPage.CH4ValueEXP,GHGCalculatorsPage.N2OValueEXP};
			for(int i=0; i<emissions.length; i++){
				if(emissions[i].equals("tCO2")) {
					if(data.get("Co2NumeratorUnit").equals("scf")) {
						 conversionOfCO2 = 113.26;
					}else {
						 conversionOfCO2 = 1.0;
					}
				}else {
					 if(data.get("Nemerator").equals("kg") && data.get("Denominator").equals("gal")) {
						 conversionValue = 1.0;
					 }else if(data.get("Nemerator").equals("g") && data.get("Denominator").equals("mi")) {
						 conversionValue = Double.parseDouble(data.get("Fuel Efficiency")) * 0.001;
					 }else if(data.get("Nemerator").equals("g") && data.get("Denominator").equals("gal")) {
						 conversionValue = 0.001;
					 }else {
						 conversionValue = 1.0;
					 }
				}
				Double fuelEfficiency;
				if(data.get("Unit").equals("gal")) {
					fuelEfficiency = 1.0;
				}else {
					fuelEfficiency = Double.parseDouble(data.get("Fuel Efficiency"));
				}
				String UnitConversion = "//div[contains(text(),'Unit Conversion | ')]";
	 			WebElement weUnitConversion = driver.findElement(By.xpath(UnitConversion));
	 			String strUnitConversion = weUnitConversion.getText().split("= ")[1].split(" ")[0];
				Double tEmissions = Double.parseDouble(data.get("Activity Amount")) 
						            * rhpValues[i] * conversionOfCO2 * conversionValue 
						            *Double.parseDouble(strUnitConversion)
						            / 1000 / fuelEfficiency;
				String expectedEmissions1 = tEmissions.toString();
				String expectedEmissions = approximateDecimalValueWithBigDecimal(expectedEmissions1);
				WebElement actualEmissions = driver.findElement(By
						.xpath("//span[text()='"+emissions[i]+"']/parent::p/following-sibling::p/div"));
				if(expectedEmissions.equals(actualEmissions.getText())) {
					passed("Successfully validated, The activity of "+emissions[i]+" is expected is "+expectedEmissions+" "
							+ " and the actual as "+actualEmissions.getText()+"");
				}else {
					Double doubleDiff= Double.parseDouble(expectedEmissions) - Double.parseDouble(actualEmissions.getText());
					info(""+doubleDiff+"");
					if(doubleDiff<1 && doubleDiff>-0.4) {
						passed("successfully validated, The activity of "+emissions[i]+" is expected is "+expectedEmissions+" "
								+ " and the actual as "+actualEmissions.getText()+"");
					}else {
						failed(driver,
								"failed to validate, The activity of "+emissions[i]+" is expected is "+expectedEmissions+" "
								+ " and the actual as "+actualEmissions.getText()+"");
					}
				}
			}
	} catch (Exception e) {
		failed(driver, "Exception caught " + e.getMessage());
	}
	}
	 
	
	
	 public static String emmissionFactorValueExp;
	public void calculateEmissionFactorForStationaryCombustionScope1_MMBTU() {            //Naveen
		try {
			sleep(102);
			WebElement emissionFactor = driver.findElement(By
			        .xpath("//span[text()='Emission Factor']/parent::p/following-sibling::p/div"));
			String[] emissionFactor1 = emissionFactor.getText().split(" ");
			String emissionFactorValueRHP = emissionFactor1[0].replaceAll(",", "");
			Double co2;
			Double ch4;
			Double n2o;
			if(data.get("gwp year").contains("AR4")) {
				 co2 = Constants.GWParCO2 * GHGCalculatorsPage.CO2Value;
				 ch4 = Constants.GWPar4CH4 * GHGCalculatorsPage.CH4ValueEXP * 0.001;                 
				 n2o = Constants.GWPar4N2O  * GHGCalculatorsPage.N2OValueEXP * 0.001;
			}else if(data.get("gwp year").contains("AR5")) {
				 co2 = Constants.GWParCO2 * GHGCalculatorsPage.CO2Value;
				 ch4 = Constants.GWPar5CH4 * GHGCalculatorsPage.CH4ValueEXP * 0.001;                 
				 n2o = Constants.GWPar5CH4  * GHGCalculatorsPage.N2OValueEXP * 0.001;
			}else {
				 co2 = Constants.GWParCO2 *GHGCalculatorsPage.CO2Value;
				 ch4 = Constants.GWPar6CH4 * GHGCalculatorsPage.CH4ValueEXP * 0.001;                 
				 n2o = Constants.GWPar6CH4 * GHGCalculatorsPage.N2OValueEXP * 0.001;
			}
			Double valOfEmission = co2 + ch4 + n2o;                          
			 emmissionFactorValueExp = valOfEmission.toString().replaceAll(",","");
			String emmissionFactorValueExpected = approximateDecimalValueWithBigDecimal(emmissionFactorValueExp);
			if(emissionFactorValueRHP.equals(emmissionFactorValueExpected)) {
				passed("Succesfully validated Emission Factor value" + emmissionFactorValueExpected + ""
						+ " And Actual is " + emissionFactorValueRHP);
			} else {
					Double doubleDiff= Double.parseDouble(emmissionFactorValueExpected) -Double.parseDouble(emissionFactorValueRHP);
					if(doubleDiff<1 && doubleDiff>-1) {    
						passed("Succesfully validated activityDetail" + emmissionFactorValueExpected + " And Actual is " + emissionFactorValueRHP.trim());
					}else {
					    failed(driver,
							    "Failed validated activityDetail" + " " + emmissionFactorValueExpected + " But Actual is " + emissionFactorValueRHP.trim());
					}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	
	public void validateActivityDetailsandEmissionDetailsStationaryCombustion(String calcName,String Amount) {
		
			getCO2_CH4_N20Value();
			if(calcName.equals("SC")) {
				calculateEmissionFactorForStationaryCombustionScope1_MMBTU();
				validateTCO2andTCH4andTN2O_ForStationaryCombustionInScope1();
				//calculateTCO2eValueUsing_EmissionFactorValueStationaryCombustion(Amount);
				validateTCO2EValueForUpstreamLeasedAsset();
			}else{
				calculateEmissionFactorUsingFormulaForMobileCombustion();
				validateTCO2andTCH4andTN2O_ForMobileCombustionInScope1();
				calculateTCO2EValueForMobileCombustionInScope1();
				//calculateTCO2eValueUsing_EmissionFactorValue(Amount);
				//validateGlobalWarmingPotentialValuesRelatedToAR();
			}
			validateGlobalWarmingPotentialValuesRelatedToAR();
		
	}
	
	 public void calculateTCO2eValueUsing_EmissionFactorValueStationaryCombustion() {
		 
	 }

	
	
	// public static  String ValCO2eRHP;
//	  @FindBy(xpath = "//span[text()='Calorific Factor/Heat Content Value']/parent::p/following-sibling::p/div")
//	  private WebElement calorificConversionValueValue;
     /* public void calculateTCO2eValueUsing_EmissionFactorValueStationaryCombustion(String amount) {
		try {
			sleep(2);
			String ApplicationEnvironment = GlobalKeys.configData.get("ApplicationEnvironment").toLowerCase();
			String[] MassUnits = {"long ton (LT)","short ton (ton)","kilogram (kg)","gram (g)",
					              "metric ton (t)","pound (lb)","Gigagram (Gg)"};
			List<String> addUnnits = new ArrayList();
			for(int i = 0;i<MassUnits.length; i++) {
				addUnnits.add(MassUnits[i]);	
			}
			Double ValCO2e;
			String convertToBaseUnitCategory;
			if(data.get("unitCategory").equals("volume")) {
				convertToBaseUnitCategory = "//div[contains(text(),'Volume')]";        
			}else if(addUnnits.contains(data.get("Unit"))) {
				convertToBaseUnitCategory = "//div[contains(text(),'Mass')]";
			}else {
				convertToBaseUnitCategory = "//div[contains(text(),'Energy')]";
			}
			if(data.get("Fuel Type").equals("Natural Gas")) {
				sleep(1);
				String fuelTypeConvert = "//div[contains(text(),'Volume')]";
				WebElement fuelTypeValue = driver.findElement(By.xpath(fuelTypeConvert));
				String splitVolume = fuelTypeValue.getText().split("\\| ")[1].split(" ")[0].replaceAll(",", "");
					if(ApplicationEnvironment.equals("uat.eu")){
						ValCO2e = Double.parseDouble(emmissionFactorValueExp)
		                           * Double.parseDouble(amount) * Double.parseDouble(splitVolume.replaceAll(",", ""))
		                           / 1000;
					}else {
						WebElement splitUnitConversion = driver.findElement(By.xpath(convertToBaseUnitCategory));
						String valueUnitConversion = splitUnitConversion.getText().split("= ")[1].split(" ")[0].replaceAll(",", ""); 
					      ValCO2e = Double.parseDouble(emmissionFactorValueExp)
		                           * Double.parseDouble(amount) * Double.parseDouble(valueUnitConversion) * Double.parseDouble(splitVolume.replace(",", ""))
		                           / 1000;
					}
			}else {
				WebElement splitUnitConversion = driver.findElement(By.xpath(convertToBaseUnitCategory));
				String valueUnitConversion = splitUnitConversion.getText().split("= ")[1].split(" ")[0].replaceAll(",", ""); 
			          ValCO2e = Double.parseDouble(emmissionFactorValueExp)
					                       * Double.parseDouble(amount) * Double.parseDouble(valueUnitConversion)
					                       / 1000;
			}
			String ValCO2eRHP1 = ValCO2e.toString().replaceAll(",", "");
			ValCO2eRHP = approximateDecimalValueWithBigDecimal(ValCO2eRHP1);
			//RhpActual
			WebElement CO2eActivity = driver
					.findElement(By.xpath("//span[contains(text(),'CO2e')]/parent::p/following-sibling::p/div"));
			String ActualCO2eActivity=CO2eActivity.getText().replaceAll(",","");
			if (ActualCO2eActivity.equals(ValCO2eRHP)) {
				passed("Succesfully validated tCO2e value" + ValCO2eRHP + " And Actual is " + ActualCO2eActivity);
			} else {
				Double doubleDiff= Double.parseDouble(ValCO2eRHP) - Double.parseDouble(ActualCO2eActivity);
				//info(""+doubleDiff+"");
				if(doubleDiff<1 && doubleDiff>-0.4) {
					passed("Succesfully validated tCO2e value" + ValCO2eRHP + " And Actual is " + ActualCO2eActivity);
				}else {
				    failed(driver,
						    "Failed validated tCO2e value" + " " + ValCO2eRHP + " But Actual is " + ActualCO2eActivity);
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
		
		
	}*/
	

	public void clickOnCloseInActivityDetails() {
		clickOn(btnCloselatest, "Close Button");
	}
	// ..................... Custome Emission factor........................

	public void clickOnCEFinCEFGrid() {
		sleep(2000);
		System.out.println(CustomEmissionFactorName);
		WebElement weActivity = driver
				.findElement(By.xpath("//*[text()='" + CustomEmissionFactorName + "']//parent::div"));
		clickOn(weActivity, "Activity of Facility" + CustomEmissionFactorName);
	}


	public void Add_CustomEmissionFactor_Scope1_InDirectEmissions() {
		try {
			CustomEmissionFactorName = Constants.CEFName + generateRandomNumber(4);
			clearUntillTextFieldIsGettingCleared(txtCutsomEFName);
			enterText(txtCutsomEFName, "entered Name of Custom EF", CustomEmissionFactorName);
			if(data.get("Activity Type").equals("Mobile Combustion - Fuel Use")) { 
				selectDropdownOptionsForDirectEmissionsScope1(drpActivityType, "Activity Type");
			}
			if(data.get("cusemissiontype").equals("Vehicles")){
			     WebElement we = driver.findElement(By.xpath("//input[@id='fuel_type']"));
			     clearUntillTextFieldIsGettingCleared(we);
			     enterText(we, data.get("Fuel Type"), data.get("Fuel Type"));
			}
			clearUntillTextFieldIsGettingCleared(txtSourceCEF);
			enterText(txtSourceCEF, "entered Name of Custom EF", data.get("Source of Emission Factor"));
			selectDropdownOptionsForDirectEmissionsScope1(drpUnitCEFDenominator, "Unit of Custom EF (Denominator)");
			clearUntillTextFieldIsGettingCleared(txtCO2);
			enterText(txtCO2, "entered txtCO2F", data.get("CO2"));
			clearUntillTextFieldIsGettingCleared(txtCH4);
			enterText(txtCH4, "entered txtCH4", data.get("CH4"));
			clearUntillTextFieldIsGettingCleared(txtN2O);
			enterText(txtN2O, "entered txtN2O", data.get("N2O"));
			selectDropdownOptionsForDirectEmissionsScope1(drpUnitCEFNumerator, "Unit of Custom EF (Numerator)");
			
//			/* ------------------------------  Naveen -------------------------------*/
			if(data.get("TestCase Type").equals("Sabanci") || data.get("TestCase Type").contains("Dataset")){
				clickOn(txtCalorificFactor, "CaloricFactor TextField");
				clearUntillTextFieldIsGettingCleared(txtCalorificFactor);
				enterText(txtCalorificFactor, "CaloricFactor TextField", data.get("Calorific Factor"));
				selectDropdownOptionsForDirectEmissionsScope1(drpCalorificFactorNumerator, "Unit of Calorific Factor (Numerator)");
				selectDropdownOptionsForDirectEmissionsScope1(drpCalorificFactorDenominator, "Unit of Calorific Factor (Denominator)");
			}
			enterText(txtNotesCEF, "entered txtNotesCEF", data.get("Notes"));
			clickOn(btnSaveParemeterInput, "Save Button");
			CO2eFactor();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	
	
	public static Double valueCo2e;
	public static String valCO2e;

	public void CO2eFactor() {
		valueCo2e = Double.parseDouble(data.get("CO2")) + Double.parseDouble(data.get("CH4")) * 25
				+ Double.parseDouble(data.get("N2O")) * 298;
		System.out.println("calculated value" + valueCo2e);
		valCO2e = valueCo2e.toString();
	}

	public void CO2eFactorEdit() {
		valueCo2e = Double.parseDouble(data.get("CO2 Edit")) + Double.parseDouble(data.get("CH4 Edit")) * 25
				+ Double.parseDouble(data.get("N2O Edit")) * 298;
		System.out.println("calculated value" + valueCo2e);
		valCO2e = valueCo2e.toString();
	}

	public void clickOnMobileCombutionScope1GHGCalculator() {
		clickOn(btnMobileCombution, "Mobile Combution Scope1");
	}

	

	

	public static String approximateDecimalValueWithBigDecimal(String value) {
		BigDecimal myBigDecimal = new BigDecimal(value);
		BigDecimal newValue = myBigDecimal.setScale(4, RoundingMode.DOWN).stripTrailingZeros();
		//System.out.println(newValue);
		return newValue.toPlainString();
	}
	
	


	public void AddActivityForCEFStationaryCombutionInDirectEmissions() {
		try {
			ReusabilityForFacilityNameAndStartDateAndEndDateForCalculators();
			entertTextInTextFieldForCalculators_ActivityDetails("Invoice No.");
			String strInvoiceDate = "//input[@placeholder='yyyy/mm/dd']";
			WebElement weInvoiceDate = driver.findElement(By.xpath(strInvoiceDate));
			clearUntillTextFieldIsGettingCleared(weInvoiceDate);
			enterText(weInvoiceDate, "Invoice Date", data.get("Invoice Date"));
			if (isElementPresent(txtFuelType)) {
				clickOn(txtFuelType, "Fuel Type");
				if(data.get("TestCase Type").equals("IEA Dataset")) {
					String[] fuelType = {data.get("Fuel Name")};
					validateUnitsForFuelTypesForStationaryCombustion_IEADataSets(fuelType);
				}else {
				WebElement txtFuelTypeOptions = driver.findElement(By
						               .xpath("//li[text()='"+data.get("Fuel Name")+"']"));
				clickOn(txtFuelTypeOptions, data.get("Fuel Name"));
				}
				
			} else {
				passed("Successfully Validated that Fuel type is Disabled");
			}
			String strFuelAmount = "//input[@placeholder='Amount of Fuel']";
			WebElement weFuelAmount = driver.findElement(By.xpath(strFuelAmount));
			clearUntillTextFieldIsGettingCleared(weFuelAmount);
			enterText(weFuelAmount, "Fuel Amount", data.get("Fuel Amount"));
			SelectDropdownOptionsForCalculatorActivityFields("Unit");
			entertTextInTextFieldForCalculators_ActivityDetails("Notes");
			if(data.get("Facility Name").equals("Sabanci Holdings_QA Facility")){
			clickOn(sabanciTags,"Tags");
		    WebElement tagNames=driver.findElement(By.xpath("//li[text()='"+data.get("Tags")+"']"));
		    clickOn(tagNames,data.get("Tags"));
			}
			clickOn(btnSave, "Save Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void VerifyCustomEmissionFactorActivitiesDetailsRHPGHGCalculator() {
		try {
			WebElement weCEFNameRHP = driver
					.findElement(By.xpath("//span[text()='Custom Emission Factor']//parent::p//following-sibling::p"));
			System.out.println(weCEFNameRHP.getText());
			System.out.println(CustomEmissionFactorName);
			if (weCEFNameRHP.getText().trim().equals(CustomEmissionFactorName)) {
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

	
	// ........Mobile Combution.................

	public void EditActivityMobileCombutionInDirectEmissions() {
		try {
			ReusabilityForFacilityNameAndStartDateAndEndDateForCalculators();
			entertTextInTextFieldForCalculators_ActivityDetails("Invoice No.");
			String strInvoiceDate = "//input[@placeholder='yyyy/mm/dd']";
			WebElement weInvoiceDate = driver.findElement(By.xpath(strInvoiceDate));
			clearUntillTextFieldIsGettingCleared(weInvoiceDate);
			enterText(weInvoiceDate, "Invoice Date", data.get("Invoice Date"));
			SelectDropdownOptionsForCalculatorActivityFields("Vehicle Name");
			ReusabilityForAmount$Units$Tags$Notes$SaveForCalculators("Activity Amount","Unit","Tags","Notes");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	
	public void EditActivityForStationary_MobileCombutionAndFugitivesInDirectEmissions() {
		try {
			ReusabilityForFacilityNameAndStartDateAndEndDateForCalculators();
			entertTextInTextFieldForCalculators_ActivityDetails("Invoice No.");
			//invoice date
			String invoiceDate = "//span[text()='Invoice Date']/parent::div/following-sibling::div//input[@placeholder='yyyy/mm/dd']";
			WebElement weInvoiceDate = driver.findElement(By.xpath(invoiceDate));
			clearUntillTextFieldIsGettingCleared(weInvoiceDate);
			enterText(weInvoiceDate, "Invoice date", data.get("Invoice Date"));
			if(data.get("subCalcName").equals("Stationary Combustion")) {
				SelectDropdownOptionsForCalculatorActivityFields("Fuel Type");
				ReusabilityForAmount$Units$Tags$Notes$SaveForCalculators("Fuel Amount","Unit","Tags","Notes");
			}else if(data.get("subCalcName").equals("Mobile Combustion")) {
				SelectDropdownOptionsForCalculatorActivityFields("Vehicle Name");
				ReusabilityForAmount$Units$Tags$Notes$SaveForCalculators("Activity Amount","Unit","Tags","Notes");
			}else{
				entertTextInTextFieldForCalculators_ActivityDetails("Type of equipment");
				SelectDropdownOptionsForCalculatorActivityFields("Refrigerant used");
				entertTextInTextFieldForCalculators_ActivityDetails("Quantity of refrigerant purchase/use/leak");
				SelectDropdownOptionsForCalculatorActivityFields("Unit(s)");
				if(data.get("Sales based approach").equals("Yes")) {
					EditActivityRefrigerantsandFugitivesInDirectEmissions_SalesBasedApproach();	
				}
				clickOn(btnSave, "Save Button");
			    
			}
			sleep(2000);
		} catch (Exception e) {
			clickOnCancelButtonBeforeAdding();
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	
	
	
	
	public void calculateTCO2EValueForMobileCombustionInScope1() {
		try {
			WebElement CO2eActivity = driver
					.findElement(By.xpath("//span[contains(text(),'CO2e')]/parent::p/following-sibling::p/div"));
			WebElement volumeConv = driver.findElement(By.xpath("//div[contains(text(),'Volume')]"));
			String splitVolumeCov = volumeConv.getText().split("= ")[1].split(" ")[0].replaceAll(",", "");
			Double d = Double.parseDouble(emmissionFactorValueExp) 
					   * Double.parseDouble(data.get("Activity Amount")) 
					  // * Double.parseDouble(splitVolumeCov) 
					   /1000;
			String stringD = d.toString().replaceAll(",", "");
			 ValCO2eRHP = approximateDecimalValueWithBigDecimal(stringD);
			 String actualtco2eValue =CO2eActivity.getText().replaceAll(",", "");
			if (actualtco2eValue.equals(ValCO2eRHP)) {
				passed("Succesfully validated tCO2e value" + ValCO2eRHP + " And Actual is " + actualtco2eValue);
			} else {
				Double doubleDiff= Double.parseDouble(ValCO2eRHP) - Double.parseDouble(actualtco2eValue);
				info(""+doubleDiff+"");
				if(doubleDiff<1 && doubleDiff>-1) {
					passed("Succesfully validated tCO2e value" + ValCO2eRHP + " And Actual is " + actualtco2eValue);
				}else {
				    failed(driver,
						    "Failed validated tCO2e value" + " " + ValCO2eRHP + " But Actual is " +actualtco2eValue);
				}
			}
		} catch (NumberFormatException e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	
	public void calculateEmissionFactorUsingFormulaForMobileCombustion() {                                            //naveen
		try {
			WebElement emissionFactor = driver.findElement(By
			        .xpath("//span[text()='Emission Factor']/parent::p/following-sibling::p/div"));
			String[] emissionFactor1 = emissionFactor.getText().replaceAll(",", "").split(" ");
			String emissionFactorValueRHP = emissionFactor1[0];
			GHGCalculatorsPage.getCO2_CH4_N20Value();
			Double co2;
			Double ch4;
			Double n2o;
			if(data.get("gwp year").contains("AR4")) {
			 co2 = Constants.GWParCO2 * GHGCalculatorsPage.CO2Value;
			 ch4 = Constants.GWPar4CH4 * GHGCalculatorsPage.CH4ValueEXP;                 
			 n2o = Constants.GWPar4N2O * GHGCalculatorsPage.N2OValueEXP;
			}else if(data.get("gwp year").contains("AR5")){
				 co2 = Constants.GWParCO2 * GHGCalculatorsPage.CO2Value;
				 ch4 = Constants.GWPar5CH4 * GHGCalculatorsPage.CH4ValueEXP;                 
				 n2o = Constants.GWPar5N2O * GHGCalculatorsPage.N2OValueEXP;
			}else {
				 co2 = Constants.GWParCO2 * GHGCalculatorsPage.CO2Value;
				 ch4 = Constants.GWPar6CH4 * GHGCalculatorsPage.CH4ValueEXP;                 
				 n2o = Constants.GWPar6N2O * GHGCalculatorsPage.N2OValueEXP;
			}
			Double valOfEmission = co2 + ch4 + n2o; 
			String UnitConversion = "//div[contains(text(),'Unit Conversion | ')]";
			WebElement weUnitConversion = driver.findElement(By.xpath(UnitConversion));
			valOfEmission = valOfEmission * Double.parseDouble(weUnitConversion.getText().split("= ")[1].split(" ")[0].replaceAll(",", ""));
			 emmissionFactorValueExp = valOfEmission.toString().replaceAll(",", "");
			String emmissionFactorValueExpected = approximateDecimalValueWithBigDecimal(emmissionFactorValueExp);
			if(emissionFactorValueRHP.equals(emmissionFactorValueExpected)) {
				passed("Succesfully validated Emission Factor value" + emmissionFactorValueExpected + ""
						+ " And Actual is " + emissionFactorValueRHP);
			} else {
				Double doubleDiff= Double.parseDouble(emmissionFactorValueExpected) - Double.parseDouble(emissionFactorValueRHP);
				info(""+doubleDiff+"");
				if(doubleDiff<1 && doubleDiff>-1) {
					passed("Succesfully validated Emission Factor value" + emmissionFactorValueExpected + ""
							+ " And Actual is " + emissionFactorValueRHP);
				}else {
					failed(driver,
							"Failed validated Emission Factor value" + " " + emmissionFactorValueExpected + ""
									+ " But Actual is " + emissionFactorValueRHP);
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	
	// ..................... Custome Emission factor........................

	Actions act = new Actions(driver);

	
	@FindBy(xpath = "//span[text()='Custom Emission Factor']//parent::div//following-sibling::div")
	private WebElement weCEFNameRHP;
	public void VerifyCustomActivitiesDetailsRHPGHGCalculator() {
		try {
			sleep(1001);
			if(isElementPresent(weCEFNameRHP)) {
		    weCEFNameRHP = driver
					.findElement(By.xpath("//span[text()='Custom Emission Factor']//parent::div//following-sibling::div"));
			}else {
				 weCEFNameRHP = driver
							.findElement(By.xpath("//span[text()='Custom Emission Factor']//parent::p//following-sibling::p"));
			}
			System.out.println(CustomEmissionFactorName);
			System.out.println(weCEFNameRHP.getText());
			if (weCEFNameRHP.getText().trim().equals(CustomEmissionFactorName)) {
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

	

	
	

	// ..........Refrierants and fugitives.................
	public void AddActivityRefrigerantsandFugitivesInDirectEmissions() {
		try {
			Thread.sleep(3000);
			clickOn(btnActivities_scope1, "btnActivities");
			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
			clickOn(txtFacility_Name, "Click on Facility name");
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Facility Name") + "']"));
			clickOn(we, data.get("Facility Name"));
			clickOn(txtInput_Voice, "invoice Number");
			enterText(txtInput_Voice, "invoice number", data.get("Invoice No."));
			enterText(txtInvoiceDate, "Invoice Date", data.get("Invoice Date"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			sleep(2000);
			enterText(txtEquipmentRefrigerantType, "txt Equipment Refrigerant Type",
					data.get("Type of air conditioning and refrigeration equipment"));
			clickOn(drpRefrigerantUsed, "drp Refrigerant Used Scope1");
			sleep(2000);
			WebElement weRefreigerantUsed = driver
					.findElement(By.xpath("//li[text()='" + data.get("Refrigerant used") + "']"));
			clickOn(weRefreigerantUsed, data.get("Refrigerant used"));
			enterText(txtDecreaseinInventory, "txt Decrease in Inventory",
					data.get("Refrigerant inventory (in storage, not equipment) at the beginning of the year"));
			enterText(txtPurchase_Acquisition, "Refrigerant purchased from producers/distributors",
					data.get("Refrigerant inventory (in storage, not equipment) at the end of the year"));
			enterText(drpRefrigePurchased, "Refrigerant purchased from producers/distributors",
					data.get("Refrigerant purchased from producers/distributors"));
			enterText(txtRefrigerantreturned, "Refrigerant returned by equipment users",
					data.get("Refrigerant returned by equipment users"));
			enterText(txtRefrigerantreturnedOffSite, "Refrigerant returned after off-site recycling or reclamation",
					data.get("Refrigerant returned after off-site recycling or reclamation"));
			enterText(txtRefrigerantcharged, "Refrigerant charged into equipment",
					data.get("Refrigerant charged into equipment*"));
			enterText(txtRefrigerantdelivered, "Refrigerant delivered to equipment users in containers",
					data.get("Refrigerant delivered to equipment users in containers"));
			enterText(txtRefrigerantreturnedtorefrigerant, "Refrigerant returned to refrigerant producers",
					data.get("Refrigerant returned to refrigerant producers"));
			enterText(txtRefrigerantsentoffsiteRecycling, "Refrigerant sent off-site for recycling or reclamation",
					data.get("Refrigerant sent off-site for recycling or reclamation"));
			enterText(txtRefrigerantsentoffsitedestruction, "Refrigerant sent off-site for destruction",
					data.get("Refrigerant sent off-site for destruction"));
			clickOn(btnSave, "Save Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//input[@id='source_data_unit']")
	private WebElement unit_Refrigirants;
	
	public void EditActivityRefrigerantsandFugitivesInDirectEmissions() {
		try {
			ReusabilityForFacilityNameAndStartDateAndEndDateForCalculators();
			entertTextInTextFieldForCalculators_ActivityDetails("Invoice No.");
			entertTextInTextFieldForCalculators_ActivityDetails("Invoice Date");
			entertTextInTextFieldForCalculators_ActivityDetails("Type of equipment");
			SelectDropdownOptionsForCalculatorActivityFields("Refrigerant used");
			entertTextInTextFieldForCalculators_ActivityDetails("Quantity of refrigerant leakage / Usage");
			SelectDropdownOptionsForCalculatorActivityFields("Unit(s)");
			if(data.get("Sales based approach").equals("Yes")) {
				EditActivityRefrigerantsandFugitivesInDirectEmissions_SalesBasedApproach();	
			}
			clickOn(btnSave, "Save Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	
		public void EditActivityRefrigerantsandFugitivesInDirectEmissions_SalesBasedApproach() {
			try {
				sleep(2000);
				Actions action = new Actions(driver);
				WebElement radioButtonYes = driver.findElement(By
						.xpath("(//input[@name='Are you using Sales based approach?'])[1]"));
				if(!radioButtonYes.isSelected()) {
					action.doubleClick(radioButtonYes).build().perform();
					passed("clicked on ---> Are you using Sales based approach? Radio Button");
				}
				action.doubleClick(txtDecreaseinInventory).build().perform();
				enterText(txtDecreaseinInventory, "Refrigerant inventory at the beginning of the year",
						data.get("Refrigerant inventory (in storage, not equipment) at the beginning of the year"));
				
				action.doubleClick(txtPurchase_Acquisition).build().perform();
				enterText(txtPurchase_Acquisition, "Refrigerant inventory at the end of the year",
						data.get("Refrigerant inventory (in storage, not equipment) at the end of the year"));
				
				action.doubleClick(drpRefrigePurchased).build().perform();
				enterText(drpRefrigePurchased, "Refrigerant purchased from producers/distributors",
						data.get("Refrigerant purchased from producers/distributors"));
				
				action.doubleClick(txtRefrigerantreturned).build().perform();
				enterText(txtRefrigerantreturned, "Refrigerant returned by equipment users",
						data.get("Refrigerant returned by equipment users"));
				
				action.doubleClick(txtRefrigerantreturnedOffSite).build().perform();
				enterText(txtRefrigerantreturnedOffSite, "Refrigerant returned after off-site recycling or reclamation",
						data.get("Refrigerant returned after off-site recycling or reclamation"));
				
				action.doubleClick(txtRefrigerantcharged).build().perform();
				enterText(txtRefrigerantcharged, "Refrigerant charged into equipment",
						data.get("Refrigerant charged into equipment*"));
				
				action.doubleClick(txtRefrigerantdelivered).build().perform();
				enterText(txtRefrigerantdelivered, "Refrigerant delivered to equipment users in containers",
						data.get("Refrigerant delivered to equipment users in containers"));
				
				action.doubleClick(txtRefrigerantreturnedtorefrigerant).build().perform();
				enterText(txtRefrigerantreturnedtorefrigerant, "Refrigerant returned to refrigerant producers",
						data.get("Refrigerant returned to refrigerant producers"));
				
				action.doubleClick(txtRefrigerantsentoffsiteRecycling).build().perform();
				enterText(txtRefrigerantsentoffsiteRecycling, "Refrigerant sent off-site for recycling or reclamation",
						data.get("Refrigerant sent off-site for recycling or reclamation"));
				
				action.doubleClick(txtRefrigerantsentoffsitedestruction).build().perform();
				enterText(txtRefrigerantsentoffsitedestruction, "Refrigerant sent off-site for destruction",
						data.get("Refrigerant sent off-site for destruction"));
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
		}
	
	

	public void ValidateActivityRefrigerantsandFugitivesDetailsInViewActivity_WithoutSalesBasedApproch() {
		try {
			System.out.println("-------Emission Details Are-------");
			String[] activityDetailFieldNames = { "Facility Name", "Invoice No.", "Refrigerant used","Start Date", "End Date",
					                             "Invoice Date","Emission Factor","Refrigerant used","Type of equipment",};
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				WebElement weActivityField = driver.findElement(By.xpath(
						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::p//following-sibling::p/div"));
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
	
	public void ValidateActivityRefrigerantsandFugitivesDetailsInViewActivitySalesBasedApproch() {
		try {
			System.out.println("-------Emission Details Are-------");
			String[] activityDetailFieldNames = { "Facility Name", "Invoice No.", "Refrigerant used","Start Date", "End Date",
					                             "Invoice Date","Emission Factor","Refrigerant used","Type of equipment",
			 "Refrigerant inventory (in storage, not equipment) at the beginning of the year",
			  "Refrigerant inventory (in storage, not equipment) at the end of the year",
			  "Refrigerant purchased from producers/distributors",
			  "Refrigerant returned by equipment users",
			  "Refrigerant returned after off-site recycling or reclamation",
			  
			  //"Refrigerant charged into equipment*",
			  "Refrigerant delivered to equipment users in containers",
			  "Refrigerant returned to refrigerant producers",
			  "Refrigerant sent off-site for recycling or reclamation",
			  "Refrigerant sent off-site for destruction"};
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				WebElement weActivityField = driver.findElement(By.xpath(
						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::p//following-sibling::p/div"));
				if (weActivityField.getText().trim().equals(data.get(activityDetailFieldNames[j]).trim())) {
					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As "
							+ weActivityField.getText());
				} else {
					failed(driver,
							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
									+ data.get(activityDetailFieldNames[j]) + "But Actual is "
									+ weActivityField.getText());
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	public void ValidateActivityRefrigerantsandFugitivesDetailsInViewActivity() {
		try {
			if(data.get("Sales based approach").equals("Yes")) {
				ValidateActivityRefrigerantsandFugitivesDetailsInViewActivitySalesBasedApproch();
			}else {
				ValidateActivityRefrigerantsandFugitivesDetailsInViewActivity_WithoutSalesBasedApproch();
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
//Emission data sets new code

	public void addActivityWithValidationOfUnitsAndFuelType() {
		try {
			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
			clickOn(txtFacilityName, "txtFacilityName");
			enterText(txtFacilityName, "Facility Name", data.get("Facility Name"));
			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Facility Name") + "']"));
			clickOn(we, data.get("Facility Name"));
			sleep(500);
			clickOn(txtInputVoice, "invoice Number");
			enterText(txtInputVoice, "invoice number", data.get("Invoice No."));
			clickOn(txtInvoiceDate, "invoice date");
			enterText(txtInvoiceDate, "Invoice Date", data.get("Invoice Date"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			clickOn(txtFuelType, "txtFuelType");
			if(data.get("DataSet").equals("IEA DataSet") && data.get("Activity").equals("Add")) {
				validateUnitsForFuelTypesForStationaryCombustion_IEADataSets(Constants.fuelsIEA);
			}else {
				we = driver.findElement(By.xpath("//li[text()='" + data.get("Fuel Type") + "']"));
				clickOn(we, data.get("Fuel Type"));
			}
			if(!data.get("DataSet").equals("IEA DataSet")) {
			    validateUnitDropDownOptions();
			}
			jsClick(txtUnitName, "Unit Type");// changed
			WebElement weUnits = driver.findElement(By.xpath("//li[text()='" + data.get("Unit") + "']"));
			clickOn(weUnits, "Sucessfully Validated Unit in Unit DropDown As" + "-->>" + data.get("Unit"));
			clickOn(txtFuelAmount, "Fuel Amount");
			enterText(txtFuelAmount, "Fuel Amount", data.get("Fuel Amount"));
			jsClick(btnSave, "Save Button");
			sleep(1000);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateUnitDropDownOptions() {
		try {
			if (Boolean.parseBoolean(data.get("Energy"))) {
				validateUnitDropDown(Constants.unitTypeEnergy);
			}
			if (Boolean.parseBoolean(data.get("Mass"))) {
				validateUnitDropDown(Constants.unitTypesMass);
			}
			if (Boolean.parseBoolean(data.get("Volume"))) {
				validateUnitDropDown(Constants.unitTypesVolume);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateUnitDropDown(String[] units) {
		for (int i = 0; i < units.length; i++) {
			jsClick(txtUnitName, "Unit Type");
			WebElement we = driver.findElement(By.xpath("//li[text()='" + units[i] + "']"));
			clickOn(we, "Sucessfully Validated Unit in Unit DropDown As" + "-->>" + units[i]);
		}
	}

	public void ValidateUserInputActivityDetailsInStationaryCombutionViewActivity() {
		try {
			sleep(3000);
			System.out.println("-------Emission Details Are-------");
			String[] activityDetailFieldNames = { "Facility Name", "Invoice No.", "Start Date", "End Date", "Fuel Type",
					"Fuel Amount", "Unit", "Custom Emission Factor" };
			// "Invoice Date", "Start Date", "Unit",
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				WebElement weActivityField = driver.findElement(By.xpath(
						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::p//following-sibling::p"));
				Thread.sleep(500);
				String uiEmissionDetails = null;
				if (activityDetailFieldNames[j].equals("Fuel Amount")) {
					if (weActivityField.getText().contains(",")) {
						uiEmissionDetails = weActivityField.getText().replaceAll(",", "");
					} else {
						uiEmissionDetails = weActivityField.getText();
					}
				} else {
					uiEmissionDetails = weActivityField.getText();
				}
				if (uiEmissionDetails.trim().equals(data.get(activityDetailFieldNames[j]).trim())) {
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

	public void validateGWPValuesInctivityDetails() {
		try {
			String[] GWPHeaders = { "Assessment", "CO2", "CH4", "N2O" };
			for (int i = 0; i < GWPHeaders.length; i++) {
				sleep(500);
				String xpathGWPValues = "//p[text()='Global Warming Potential Values']//following::span[text()='"
						+ GWPHeaders[i] + "']//parent::p//following-sibling::p//div";
				WebElement weGwpvalues = driver.findElement(By.xpath(xpathGWPValues));
				System.out.println(weGwpvalues.getText());
				System.out.println(Constants.arrAR4Values[0]);
				if (data.get("GWPSetUpValue").equalsIgnoreCase("AR4")) {
					if (weGwpvalues.getText().replaceAll("\\s", "")
							.equals(Constants.arrAR4Values[i].replaceAll("\\s", ""))) {
						passed("Successfully validate AR4 Data set Value of " + GWPHeaders[i] + "As"
								+ weGwpvalues.getText());
					} else {
						failed(driver, "Failed To validate AR4 Data set Value of " + GWPHeaders[i] + "Expected as"
								+ Constants.arrAR4Values[i] + "But Actual is " + weGwpvalues.getText());
					}
				} else if (data.get("GWPSetUpValue").equalsIgnoreCase("AR5")) {
					if (weGwpvalues.getText().replaceAll("\\s", "")
							.equals(Constants.arrAR5Values[i].replaceAll("\\s", ""))) {
						passed("Successfully validate AR5 Data set Value of " + GWPHeaders[i] + "As"
								+ weGwpvalues.getText());
					} else {
						failed(driver, "Failed To validate AR5 Data set Value of " + GWPHeaders[i] + "Expected as"
								+ Constants.arrAR5Values[i] + "But Actual is " + weGwpvalues.getText());
					}
				}
			}
			sleep(3000);
			String EFInEmissionDetails = null;
			Double emissionFactorCalc = null;
			if (Boolean.parseBoolean(data.get("EFBiofuelExist"))) {
				EFInEmissionDetails = GHGCalculatorsPage
						.approximateDecimalValueWithBigDecimal(data.get("EFBiofuel CO2"));
			} else {
				if (data.get("GWPSetUpValue").equalsIgnoreCase("AR4")) {
					emissionFactorCalc = Double.parseDouble(data.get("EFCO2"))
							* Integer.parseInt(Constants.arrAR4Values[1])
							+ Double.parseDouble(data.get("EFCH4")) * Integer.parseInt(Constants.arrAR4Values[2])
							+ Double.parseDouble(data.get("EFN2O")) * Integer.parseInt(Constants.arrAR4Values[3]);
				} else if (data.get("GWPSetUpValue").equalsIgnoreCase("AR5")) {
					emissionFactorCalc = Double.parseDouble(data.get("EFCO2"))
							* Integer.parseInt(Constants.arrAR5Values[1])
							+ Double.parseDouble(data.get("EFCH4")) * Integer.parseInt(Constants.arrAR5Values[2])
							+ Double.parseDouble(data.get("EFN2O")) * Integer.parseInt(Constants.arrAR5Values[3]);
				}
				EFInEmissionDetails = GHGCalculatorsPage
						.approximateDecimalValueWithBigDecimal(emissionFactorCalc.toString());
			}
			GlobalVariables.calculatedEFValue = EFInEmissionDetails;
			String xpathEmissionFactorInEFDetails = "//p[contains(text(),'Emissions Details')]//parent::div//parent::div//following-sibling::div//span[text()='Emission Factor']//parent::p//following-sibling::p";
			WebElement weEFDetails = driver.findElement(By.xpath(xpathEmissionFactorInEFDetails));
			String[] uiEFdetail = weEFDetails.getText().split(" ");
			String uiEFValue = uiEFdetail[0];
			String numeratorUnit = uiEFdetail[1];
			String denomUnit = uiEFdetail[2].split("/")[1];
			if (EFInEmissionDetails.equals(uiEFValue)) {
				passed("Successfully validated Emission Factor in Emission details as " + EFInEmissionDetails);
			} else {
				failed(driver, "Failed to validate  Emission Factor in Emission details  Expected as "
						+ EFInEmissionDetails + "But Actual is " + uiEFValue);
			}
			if (numeratorUnit.equals(data.get("NumeratorUnit"))) {
				passed("Successfully Validated Numerator Unit of Emission Factor in Emission details as"
						+ numeratorUnit);
			} else {
				failed(driver, "Failed  to validate  Numerator Unit of Emission Factor in Emission details Expected as"
						+ data.get("NumeratorUnit") + "But Actual is" + numeratorUnit);
			}
			if (denomUnit.equals(data.get("DenomUnit"))) {
				passed("Successfully Validated denominator Unit of Emission Factor in Emission details as" + denomUnit);
			} else {
				failed(driver,
						"Failed  to validate  denominator Unit of Emission Factor in Emission details Expected as"
								+ data.get("DenomUnit") + "But Actual is" + denomUnit);
			}
			System.out.println(uiEFdetail[2]);
			if (uiEFdetail[2].split("/")[0].equals("CO2e")) {
				passed("Successfully Validated CO2e  Emission Factor in Emission details as" + uiEFdetail[2]);
			} else {
				failed(driver, "Failed  to validate  CO2e  Emission Factor in Emission details");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateGWPValuesInctivityDetails_EPA() {
		try {
			String[] GWPHeaders = { "Assessment", "CO2", "CH4", "N2O" };
			for (int i = 0; i < GWPHeaders.length; i++) {
				sleep(500);
				String xpathGWPValues = "//p[text()='Global Warming Potential Values']//following::span[text()='"
						+ GWPHeaders[i] + "']//parent::p//following-sibling::p//div";
				WebElement weGwpvalues = driver.findElement(By.xpath(xpathGWPValues));
				System.out.println(weGwpvalues.getText());
				System.out.println(Constants.arrAR4Values[0]);
				if (data.get("GWPSetUpValue").equalsIgnoreCase("AR4")) {
					if (weGwpvalues.getText().replaceAll("\\s", "")
							.equals(Constants.arrAR4Values[i].replaceAll("\\s", ""))) {
						passed("Successfully validate AR4 Data set Value of " + GWPHeaders[i] + "As"
								+ weGwpvalues.getText());
					} else {
						failed(driver, "Failed To validate AR4 Data set Value of " + GWPHeaders[i] + "Expected as"
								+ Constants.arrAR4Values[i] + "But Actual is " + weGwpvalues.getText());
					}
				} else if (data.get("GWPSetUpValue").equalsIgnoreCase("AR5")) {
					if (weGwpvalues.getText().replaceAll("\\s", "")
							.equals(Constants.arrAR5Values[i].replaceAll("\\s", ""))) {
						passed("Successfully validate AR5 Data set Value of " + GWPHeaders[i] + "As"
								+ weGwpvalues.getText());
					} else {
						failed(driver, "Failed To validate AR5 Data set Value of " + GWPHeaders[i] + "Expected as"
								+ Constants.arrAR5Values[i] + "But Actual is " + weGwpvalues.getText());
					}
				}
			}
			sleep(3000);
			String EFInEmissionDetails = null;
			String EFInEmissionDetailsBioFuel = null;
			Double emissionFactorCalcBioFuel = null;
			Double emissionFactorCalc = null;
			if (Boolean.parseBoolean(data.get("EFBiofuelExist"))) {
				if (data.get("GWPSetUpValue").equalsIgnoreCase("AR4")) {
					emissionFactorCalcBioFuel = Double.parseDouble(data.get("EFCO2"))
							* Integer.parseInt(Constants.arrAR4Values[1])
							+ Double.parseDouble(data.get("EFCH4")) / 1000.0
									* Integer.parseInt(Constants.arrAR4Values[2])
							+ Double.parseDouble(data.get("EFN2O")) / 1000.0
									* Integer.parseInt(Constants.arrAR4Values[3])
							+ Double.parseDouble(data.get("EFBiofuel CO2"));
				} else if (data.get("GWPSetUpValue").equalsIgnoreCase("AR5")) {
					emissionFactorCalcBioFuel = Double.parseDouble(data.get("EFCO2"))
							* Integer.parseInt(Constants.arrAR5Values[1])
							+ Double.parseDouble(data.get("EFCH4")) / 1000.0
									* Integer.parseInt(Constants.arrAR5Values[2])
							+ Double.parseDouble(data.get("EFN2O")) / 1000.0
									* Integer.parseInt(Constants.arrAR5Values[3])
							+ Double.parseDouble(data.get("EFBiofuel CO2"));
				}
				EFInEmissionDetailsBioFuel = GHGCalculatorsPage
						.approximateDecimalValueWithBigDecimal(emissionFactorCalcBioFuel.toString());
				GlobalVariables.calculatedEFValueBioFuel = EFInEmissionDetailsBioFuel;
			}
			if (data.get("GWPSetUpValue").equalsIgnoreCase("AR4")) {
				emissionFactorCalc = Double.parseDouble(data.get("EFCO2")) * Integer.parseInt(Constants.arrAR4Values[1])
						+ Double.parseDouble(data.get("EFCH4")) / 1000.0 * Integer.parseInt(Constants.arrAR4Values[2])
						+ Double.parseDouble(data.get("EFN2O")) / 1000.0 * Integer.parseInt(Constants.arrAR4Values[3]);
			} else if (data.get("GWPSetUpValue").equalsIgnoreCase("AR5")) {
				emissionFactorCalc = Double.parseDouble(data.get("EFCO2")) * Integer.parseInt(Constants.arrAR5Values[1])
						+ Double.parseDouble(data.get("EFCH4")) / 1000.0 * Integer.parseInt(Constants.arrAR5Values[2])
						+ Double.parseDouble(data.get("EFN2O")) / 1000.0 * Integer.parseInt(Constants.arrAR5Values[3]);
			}
			EFInEmissionDetails = GHGCalculatorsPage
					.approximateDecimalValueWithBigDecimal(emissionFactorCalc.toString());
			GlobalVariables.calculatedEFValue = EFInEmissionDetails;
			String xpathEmissionFactorInEFDetails = "//p[contains(text(),'Emissions Details')]//parent::div//parent::div//following-sibling::div//span[text()='Emission Factor']//parent::p//following-sibling::p";
			WebElement weEFDetails = driver.findElement(By.xpath(xpathEmissionFactorInEFDetails));
			String[] uiEFdetail = weEFDetails.getText().split(" ");
			String uiEFValue = uiEFdetail[0];
			String numeratorUnit = uiEFdetail[1];
			String denomUnit = uiEFdetail[2].split("/")[1];
			if (Boolean.parseBoolean(data.get("EFBiofuelExist"))) {
				if (EFInEmissionDetailsBioFuel.equals(uiEFValue)) {
					passed("Successfully validated Emission Factor in Emission details as "
							+ EFInEmissionDetailsBioFuel);
				} else {
					failed(driver, "Failed to validate  Emission Factor in Emission details  Expected as "
							+ EFInEmissionDetailsBioFuel + "But Actual is " + uiEFValue);
				}
			} else if (EFInEmissionDetails.equals(uiEFValue)) {
				passed("Successfully validated Emission Factor in Emission details as " + EFInEmissionDetails);
			} else {
				failed(driver, "Failed to validate  Emission Factor in Emission details  Expected as "
						+ EFInEmissionDetails + "But Actual is " + uiEFValue);
			}
			if (numeratorUnit.equals(data.get("NumeratorUnit"))) {
				passed("Successfully Validated Numerator Unit of Emission Factor in Emission details as"
						+ numeratorUnit);
			} else {
				failed(driver, "Failed  to validate  Numerator Unit of Emission Factor in Emission details Expected as"
						+ data.get("NumeratorUnit") + "But Actual is" + numeratorUnit);
			}
			if (denomUnit.equals(data.get("DenomUnit"))) {
				passed("Successfully Validated denominator Unit of Emission Factor in Emission details as" + denomUnit);
			} else {
				failed(driver,
						"Failed  to validate  denominator Unit of Emission Factor in Emission details Expected as"
								+ data.get("DenomUnit") + "But Actual is" + denomUnit);
			}
			System.out.println(uiEFdetail[2]);
			if (uiEFdetail[2].split("/")[0].equals("CO2e")) {
				passed("Successfully Validated CO2e  Emission Factor in Emission details as" + uiEFdetail[2]);
			} else {
				failed(driver, "Failed  to validate  CO2e  Emission Factor in Emission details");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateEmissionFactorsInActivityDetails() {
		try {
			String[] EFHeaders = { "CO2", "CH4", "N2O", "Biofuel CO2" };
			for (int i = 0; i < EFHeaders.length; i++) {
				String xpathEFDetails = "(//p[contains(text(),'Emission Factors')]//parent::div//parent::div//following-sibling::div//span[text()='"
						+ EFHeaders[i] + "'])[1]//parent::*//following-sibling::*";
				WebElement weEFDetails = driver.findElement(By.xpath(xpathEFDetails));
				String[] uiEFdetail = weEFDetails.getText().split(" ");
				String uiValue = uiEFdetail[0];
				String[] uiUnit = uiEFdetail[1].split("/");
				String numeratorUnit = uiUnit[0];
				String denomUnit = uiUnit[1];
				System.out.println(12);
				if (data.get("EF" + EFHeaders[i]).replaceAll("\\s", "").equals(uiValue.replaceAll("\\s", ""))) {
					passed("Successfully EF value for" + EFHeaders[i] + "As" + uiValue);
				} else {
					failed(driver, "Failed To validate  EF value for" + EFHeaders[i] + "Expected as"
							+ data.get("EF" + EFHeaders[i]) + "Actual is " + uiValue);
				}
				if (data.get("DenomUnit").replaceAll("\\s", "").equals(denomUnit.replaceAll("\\s", ""))) {
					passed("Successfully validated DenomUnit for " + EFHeaders[i] + "As" + denomUnit);
				} else {
					failed(driver, "failed validated DenomUnit for " + EFHeaders[i] + "Expected As"
							+ data.get("DenomUnit") + "But Actual is" + denomUnit);
				}
				if (data.get("NumeratorUnit").replaceAll("\\s", "").equals(numeratorUnit.replaceAll("\\s", ""))) {
					passed("Successfully validated numerator Unit for " + EFHeaders[i] + "As" + numeratorUnit);
				} else {
					failed(driver, "failed validated numerator Unit for " + EFHeaders[i] + "Expected As"
							+ data.get("NumeratorUnit") + "But Actual is" + numeratorUnit);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateEmissionFactorsInActivityDetails_EPA() {
		try {
			String[] EFHeaders = { "CO2", "CH4", "N2O", "Biofuel CO2" };
			for (int i = 0; i < EFHeaders.length; i++) {
				String xpathEFDetails = "(//p[contains(text(),'Emission Factors')]//parent::div//parent::div//following-sibling::div//span[text()='"
						+ EFHeaders[i] + "'])[1]//parent::*//following-sibling::*";
				WebElement weEFDetails = driver.findElement(By.xpath(xpathEFDetails));
				String[] uiEFdetail = weEFDetails.getText().split(" ");
				String uiValue = uiEFdetail[0];
				String[] uiUnit = uiEFdetail[1].split("/");
				String numeratorUnit = uiUnit[0];
				String denomUnit = uiUnit[1];
				System.out.println(12);
				if (data.get("EF" + EFHeaders[i]).replaceAll("\\s", "").equals(uiValue.replaceAll("\\s", ""))) {
					passed("Successfully EF value for" + EFHeaders[i] + " As " + uiValue);
				} else {
					failed(driver, "Failed To validate  EF value for" + EFHeaders[i] + "Expected as "
							+ data.get("EF" + EFHeaders[i]) + "Actual is " + uiValue);
				}
				if (data.get("DenomUnit").replaceAll("\\s", "").equals(denomUnit.replaceAll("\\s", ""))) {
					passed("Successfully validated DenomUnit for " + EFHeaders[i] + " As " + denomUnit);
				} else {
					failed(driver, "failed validated DenomUnit for " + EFHeaders[i] + "Expected As "
							+ data.get("DenomUnit") + "But Actual is " + denomUnit);
				}
				if (EFHeaders[i].equals("CO2")) {
					if (data.get("NumeratorUnit_CO2").replaceAll("\\s", "")
							.equals(numeratorUnit.replaceAll("\\s", ""))) {
						passed("Successfully validated numerator Unit for " + EFHeaders[i] + " As " + numeratorUnit);
					} else {
						failed(driver, "failed validated numerator Unit for " + EFHeaders[i] + "Expected As "
								+ data.get("NumeratorUnit_CO2") + "But Actual is " + numeratorUnit);
					}
				} else if (EFHeaders[i].equals("CH4")) {
					if (data.get("NumeratorUnit_CH4").replaceAll("\\s", "")
							.equals(numeratorUnit.replaceAll("\\s", ""))) {
						passed("Successfully validated numerator Unit for " + EFHeaders[i] + " As " + numeratorUnit);
					} else {
						failed(driver, "failed validated numerator Unit for " + EFHeaders[i] + "Expected As "
								+ data.get("NumeratorUnit_CH4") + "But Actual is " + numeratorUnit);
					}
				} else if (EFHeaders[i].equals("N2O")) {
					if (data.get("NumeratorUnit_N2O").replaceAll("\\s", "")
							.equals(numeratorUnit.replaceAll("\\s", ""))) {
						passed("Successfully validated numerator Unit for " + EFHeaders[i] + " As " + numeratorUnit);
					} else {
						failed(driver, "failed validated numerator Unit for " + EFHeaders[i] + "Expected As "
								+ data.get("NumeratorUnit_N2O") + "But Actual is " + numeratorUnit);
					}
				} else if (EFHeaders[i].equals("Biofuel CO2")) {
					if (data.get("NumeratorUnit_BiofuelCO2").replaceAll("\\s", "")
							.equals(numeratorUnit.replaceAll("\\s", ""))) {
						passed("Successfully validated numerator Unit for " + EFHeaders[i] + " As " + numeratorUnit);
					} else {
						failed(driver, "failed validated numerator Unit for " + EFHeaders[i] + "Expected As "
								+ data.get("NumeratorUnit_BiofuelCO2") + "But Actual is " + numeratorUnit);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateEmisionDetailsInActivityDetailsRHP() {
		try {
			if (Boolean.parseBoolean(data.get("EFBiofuelExist"))) {
				String[] emissionDetailFieldNames = { "tCO2", "tCH4", "tN2O", "tCO2e", "tCO2e (Daily)" };
				for (int j = 0; j < emissionDetailFieldNames.length; j++) {
					WebElement weActivityField = driver.findElement(By.xpath(
							"//span[text()='" + emissionDetailFieldNames[j] + "']//parent::p//following-sibling::p"));
					sleep(500);
					if (weActivityField.getText().trim().equals("0")) {
						passed("Successfully Validated " + emissionDetailFieldNames[j] + " In Emisstiom Details As"
								+ weActivityField.getText());
					} else {
						System.out.println(data.get(emissionDetailFieldNames[j]));
						System.out.println(weActivityField.getText());
						failed(driver,
								"Failed To validate " + emissionDetailFieldNames[j]
										+ " In Emission  Details Expected As " + data.get(emissionDetailFieldNames[j])
										+ "But Actual is" + weActivityField.getText());
					}
				}
				// GlobalVariables.calculatedEFValue
				Double biofueltCO2 = Double.parseDouble(data.get("Fuel Amount"))
						* Double.parseDouble(GlobalVariables.coversionValue)
						* Double.parseDouble(data.get("EFBiofuel CO2")) / 1000.0;
				String bioFueltCo2String1 = biofueltCO2.toString();
				String bioFueltCo2Stringapproximate = GHGCalculatorsPage
						.approximateDecimalValueWithBigDecimal(bioFueltCo2String1);
				String bioFueltCo2String = bioFueltCo2Stringapproximate.toString();
				WebElement weBioFueltCO2 = driver
						.findElement(By.xpath("//span[text()='Biofuel tCO2']//parent::p//following-sibling::p"));
				if (weBioFueltCO2.getText().trim().equals(bioFueltCo2String)) {
					passed("Successfully Validated BiofueltCO2 as" + bioFueltCo2String);
				} else {
					failed(driver, "Failed to validate BiofueltCO2 Expected as" + bioFueltCo2String + "But actual is"
							+ weBioFueltCO2.getText());
				}
				Double noOfDays = calculateDaysDiff(data.get("Start Date"), data.get("End Date"));
				Double biofueltCO2_Daily = biofueltCO2 / noOfDays;
				String biofueltCO2_DailyString = biofueltCO2_Daily.toString();
				String biofueltCO2_DailyStringApprox = GHGCalculatorsPage
						.approximateDecimalValueWithBigDecimal(biofueltCO2_DailyString);
				WebElement weBioFueltCO2_Daily = driver.findElement(
						By.xpath("//span[text()='Biofuel tCO2 (Daily)']//parent::p//following-sibling::p"));
				if (weBioFueltCO2_Daily.getText().trim().equals(biofueltCO2_DailyStringApprox)) {
					passed("Successfully Validated Bio Fuel tCO2 daily as" + biofueltCO2_DailyStringApprox);
				} else {
					failed(driver, "Failed to Validate Bio Fuel tCO2 daily Expected as" + biofueltCO2_DailyStringApprox
							+ "ButActual is" + weBioFueltCO2_Daily.getText());
				}
			} else {
				String[] emissionDetailFieldsBioFules = { "Biofuel tCO2", "Biofuel tCO2 (Daily)" };
				for (int j = 0; j < emissionDetailFieldsBioFules.length; j++) {
					WebElement weActivityField = driver.findElement(By.xpath("//span[text()='"
							+ emissionDetailFieldsBioFules[j] + "']//parent::p//following-sibling::p"));
					sleep(500);
					if (weActivityField.getText().trim().equals("0")) {
						passed("Successfully Validated " + emissionDetailFieldsBioFules[j] + " In Emisstiom Details As"
								+ weActivityField.getText());
					} else {
						System.out.println(data.get(emissionDetailFieldsBioFules[j]));
						System.out.println(weActivityField.getText());
						failed(driver, "Failed To validate " + emissionDetailFieldsBioFules[j]
								+ " In Emission  Details Expected As " + data.get(emissionDetailFieldsBioFules[j])
								+ "But Actual is" + weActivityField.getText());
					}
				}
				String calcEmissionApproxValue = null;
				String[] emissionDetailFieldsNonBioFules = { "tCO2", "tCH4", "tN2O" };
				String[] emissionFactorDataHeaders = { "EFCO2", "EFCH4", "EFN2O" };
				for (int i = 0; i < emissionFactorDataHeaders.length; i++) {
					WebElement weEmissionValue = driver.findElement(By.xpath("//span[text()='"
							+ emissionDetailFieldsNonBioFules[i] + "']//parent::p//following-sibling::p"));
					// --------HHV is True and UnitType is Energy------------------------
					if (Boolean.parseBoolean(data.get("HHV")) && data.get("UnitType").equals("Energy")) {
						Double calcEmissionValue1 = Double.parseDouble(data.get("Fuel Amount"))
								* Double.parseDouble(data.get("ConversionValue"))
								* Double.parseDouble(data.get(emissionFactorDataHeaders[i])) / 1000.0;
						// converting to ton    
						Double calcEmissionValue = calcEmissionValue1
								/ Double.parseDouble(data.get("HHV Unit Conversion Value"));
						calcEmissionApproxValue = GHGCalculatorsPage
								.approximateDecimalValueWithBigDecimal(calcEmissionValue.toString());
						// --------Density is True and UnitType is Mass------------------------
					} else if (Boolean.parseBoolean(data.get("Density")) && data.get("UnitType").equals("Mass")) {
						Double calcEmissionValue1 = Double.parseDouble(data.get("Fuel Amount"))
								* Double.parseDouble(data.get("ConversionValue")) * 1000.0
								* Double.parseDouble(data.get(emissionFactorDataHeaders[i])) / 1000.0;
						// Converting to Sm3
						Double calcEmissionValue = calcEmissionValue1
								/ Double.parseDouble(data.get("Density Unit Conversion Value to Sm3"));
						calcEmissionApproxValue = GHGCalculatorsPage
								.approximateDecimalValueWithBigDecimal(calcEmissionValue.toString());
						// --------Density is True and UnitType is Energy------------------------
					} else if (Boolean.parseBoolean(data.get("Density")) && data.get("UnitType").equals("Energy")) {
						// need to convert from ton to kg
						Double calcEmissionValue1 = Double.parseDouble(data.get("Fuel Amount"))
								* Double.parseDouble(data.get("ConversionValue")) * 1000000.0
								* Double.parseDouble(data.get(emissionFactorDataHeaders[i])) / 1000.0;
						// converting to ton
						Double calcEmissiontonConversion = calcEmissionValue1
								/ Double.parseDouble(data.get("Density Unit Conversion Value to ton"));
						// converting to Sm3
						Double calcEmissionValue = calcEmissiontonConversion
								/ Double.parseDouble(data.get("Density Unit Conversion Value to Sm3"));
						calcEmissionApproxValue = GHGCalculatorsPage
								.approximateDecimalValueWithBigDecimal(calcEmissionValue.toString());
						// --------Both HHV and Density are False------------------------
					} else {
						Double calcEmissionValue = Double.parseDouble(data.get("Fuel Amount"))
								* Double.parseDouble(data.get("ConversionValue"))
								* Double.parseDouble(data.get(emissionFactorDataHeaders[i])) / 1000.0;
						calcEmissionApproxValue = GHGCalculatorsPage
								.approximateDecimalValueWithBigDecimal(calcEmissionValue.toString());
					}
					if (weEmissionValue.getText().trim().equals(calcEmissionApproxValue)) {
						passed("Successfully Validated  " + emissionDetailFieldsNonBioFules[i]
								+ "In Emission Details as" + calcEmissionApproxValue);
					} else {
						failed(driver,
								"Failed To Validate " + emissionDetailFieldsNonBioFules
										+ "In Emission details Expected as" + calcEmissionApproxValue + "But Actual is"
										+ weEmissionValue.getText());
					}
				}
				sleep(3000);
				String tCO2eString = null;
				Double tCO2e = null;
				// --------HHV is True and UnitType is Energy------------------------
				if (Boolean.parseBoolean(data.get("HHV")) && data.get("UnitType").equals("Energy")) {
					Double tCO2e1 = Double.parseDouble(data.get("Fuel Amount"))
							* Double.parseDouble(GlobalVariables.coversionValue)
							* Double.parseDouble(GlobalVariables.calculatedEFValue) / 1000.0;
					tCO2e = tCO2e1 / Double.parseDouble(data.get("HHV Unit Conversion Value"));
					tCO2eString = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCO2e.toString());
					// --------Density is True and UnitType is Mass------------------------
				} else if (Boolean.parseBoolean(data.get("Density")) && data.get("UnitType").equals("Mass")) {
					Double tCO2e1 = Double.parseDouble(data.get("Fuel Amount"))
							* Double.parseDouble(GlobalVariables.coversionValue) * 1000.0
							* Double.parseDouble(GlobalVariables.calculatedEFValue) / 1000.0;
					tCO2e = tCO2e1 / Double.parseDouble(data.get("Density Unit Conversion Value to Sm3"));
					tCO2eString = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCO2e.toString());
					// --------Density is True and UnitType is Energy------------------------
				} else if (Boolean.parseBoolean(data.get("Density")) && data.get("UnitType").equals("Energy")) {
					Double tCO2e1 = Double.parseDouble(data.get("Fuel Amount"))
							* Double.parseDouble(GlobalVariables.coversionValue) * 1000000.0
							* Double.parseDouble(GlobalVariables.calculatedEFValue) / 1000.0;
					// conversion to ton
					Double tCO2etonconversion = tCO2e1
							/ Double.parseDouble(data.get("Density Unit Conversion Value to ton"));
					// conversion to Sm3
					tCO2e = tCO2etonconversion / Double.parseDouble(data.get("Density Unit Conversion Value to Sm3"));
					tCO2eString = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCO2e.toString());
					// --------Both HHV and Density are False------------------------
				} else {
					tCO2e = Double.parseDouble(data.get("Fuel Amount"))
							* Double.parseDouble(GlobalVariables.coversionValue)
							* Double.parseDouble(GlobalVariables.calculatedEFValue) / 1000.0;
					tCO2eString = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCO2e.toString());
				}
				WebElement wetCO2e = driver
						.findElement(By.xpath("//span[text()='tCO2e']//parent::p//following-sibling::p"));
				if (wetCO2e.getText().trim().equals(tCO2eString)) {
					passed("Successfully Validated tCO2e as" + tCO2eString);
				} else {
					failed(driver,
							"Failed to validate tCO2e Expected as" + tCO2eString + "But actual is" + wetCO2e.getText());
				}
				Double noOfDays = calculateDaysDiff(data.get("Start Date"), data.get("End Date"));
				Double tCO2e_Daily = tCO2e / noOfDays;
				String tCO2e_DailyString = tCO2e_Daily.toString();
				String tCO2e_DailyStringApprox = GHGCalculatorsPage
						.approximateDecimalValueWithBigDecimal(tCO2e_DailyString);
				WebElement wetCO2e_Daily = driver
						.findElement(By.xpath("//span[text()='tCO2e (Daily)']//parent::p//following-sibling::p"));
				if (wetCO2e_Daily.getText().trim().equals(tCO2e_DailyStringApprox)) {
					passed("Successfully Validated tCO2e daily as" + tCO2e_DailyStringApprox);
				} else {
					failed(driver, "Failed to Validate tCO2e daily Expected as" + tCO2e_DailyStringApprox
							+ "ButActual is" + wetCO2e_Daily.getText());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateEmisionDetailsInActivityDetailsRHP_EPA() {
		try {
			if (Boolean.parseBoolean(data.get("EFBiofuelExist"))) {
				String[] emissionDetailFieldNames = { "tCO2" };
				for (int j = 0; j < emissionDetailFieldNames.length; j++) {
					WebElement weActivityField = driver.findElement(By.xpath(
							"//span[text()='" + emissionDetailFieldNames[j] + "']//parent::p//following-sibling::p"));
					sleep(500);
					if (weActivityField.getText().trim().equals("0")) {
						passed("Successfully Validated " + emissionDetailFieldNames[j] + " In Emisstiom Details As"
								+ weActivityField.getText());
					} else {
						System.out.println(data.get(emissionDetailFieldNames[j]));
						System.out.println(weActivityField.getText());
						failed(driver,
								"Failed To validate " + emissionDetailFieldNames[j]
										+ " In Emission  Details Expected As " + data.get(emissionDetailFieldNames[j])
										+ "But Actual is" + weActivityField.getText());
						System.out.println(123);
					}
				}
				Double biofueltCO2 = Double.parseDouble(data.get("Fuel Amount"))
						* Double.parseDouble(GlobalVariables.coversionValue)
						* Double.parseDouble(data.get("EFBiofuel CO2")) / 1000.0;
				String bioFueltCo2String1 = biofueltCO2.toString();
				String bioFueltCo2Stringapproximate = GHGCalculatorsPage
						.approximateDecimalValueWithBigDecimal(bioFueltCo2String1);
				String bioFueltCo2String = bioFueltCo2Stringapproximate.toString();
				WebElement weBioFueltCO2 = driver
						.findElement(By.xpath("//span[text()='Biofuel tCO2']//parent::p//following-sibling::p"));
				if (weBioFueltCO2.getText().trim().equals(bioFueltCo2String)) {
					passed("Successfully Validated BiofueltCO2 as" + bioFueltCo2String);
				} else {
					failed(driver, "Failed to validate BiofueltCO2 Expected as" + bioFueltCo2String + "But actual is"
							+ weBioFueltCO2.getText());
				}
				Double noOfDays = calculateDaysDiff(data.get("Start Date"), data.get("End Date"));
				Double biofueltCO2_Daily = biofueltCO2 / noOfDays;
				String biofueltCO2_DailyString = biofueltCO2_Daily.toString();
				String biofueltCO2_DailyStringApprox = GHGCalculatorsPage
						.approximateDecimalValueWithBigDecimal(biofueltCO2_DailyString);
				WebElement weBioFueltCO2_Daily = driver.findElement(
						By.xpath("//span[text()='Biofuel tCO2 (Daily)']//parent::p//following-sibling::p"));
				if (weBioFueltCO2_Daily.getText().trim().equals(biofueltCO2_DailyStringApprox)) {
					passed("Successfully Validated Bio Fuel tCO2 daily as" + biofueltCO2_DailyStringApprox);
				} else {
					failed(driver, "Failed to Validate Bio Fuel tCO2 daily Expected as" + biofueltCO2_DailyStringApprox
							+ "ButActual is" + weBioFueltCO2_Daily.getText());
				}
			}
			if (!Boolean.parseBoolean(data.get("EFBiofuelExist"))) {
				String[] emissionDetailFieldsBioFules = { "Biofuel tCO2", "Biofuel tCO2 (Daily)" };
				for (int j = 0; j < emissionDetailFieldsBioFules.length; j++) {
					WebElement weActivityField = driver.findElement(By.xpath("//span[text()='"
							+ emissionDetailFieldsBioFules[j] + "']//parent::p//following-sibling::p"));
					sleep(500);
					if (weActivityField.getText().trim().equals("0")) {
						passed("Successfully Validated " + emissionDetailFieldsBioFules[j] + " In Emisstiom Details As"
								+ weActivityField.getText());
					} else {
						System.out.println(data.get(emissionDetailFieldsBioFules[j]));
						System.out.println(weActivityField.getText());
						failed(driver, "Failed To validate " + emissionDetailFieldsBioFules[j]
								+ " In Emission  Details Expected As " + data.get(emissionDetailFieldsBioFules[j])
								+ "But Actual is" + weActivityField.getText());
					}
				}
			}
			String calcEmissionApproxValue = null;
			Double calcEmissionValue1;
			String[] emissionDetailFieldsNonBioFules = { "tCO2", "tCH4", "tN2O" };
			String[] emissionFactorDataHeaders = { "EFCO2", "EFCH4", "EFN2O" };
			for (int i = 0; i < emissionFactorDataHeaders.length; i++) {
				WebElement weEmissionValue = driver.findElement(By.xpath("//span[text()='"
						+ emissionDetailFieldsNonBioFules[i] + "']//parent::p//following-sibling::p"));
				// --------HHV is True and UnitType is Mass------------------------
				if (Boolean.parseBoolean(data.get("HHV")) && data.get("UnitType").equals("Mass")) {
					if (emissionDetailFieldsNonBioFules[i].equals("tCO2")) {
						calcEmissionValue1 = Double.parseDouble(data.get("Fuel Amount"))
								* Double.parseDouble(data.get("ConversionValue"))
								* Double.parseDouble(data.get(emissionFactorDataHeaders[i])) / 1000.0;
					} else {
						calcEmissionValue1 = Double.parseDouble(data.get("Fuel Amount"))
								* Double.parseDouble(data.get("ConversionValue"))
								* Double.parseDouble(data.get(emissionFactorDataHeaders[i])) / 1000.0 / 1000.0;
					}
					// converting to ton
					Double calcEmissionValue = calcEmissionValue1
							* Double.parseDouble(data.get("HHV Unit Conversion Value"));
					calcEmissionApproxValue = GHGCalculatorsPage
							.approximateDecimalValueWithBigDecimal(calcEmissionValue.toString());
					// --------Density is True and UnitType is Volume------------------------
				} else if (Boolean.parseBoolean(data.get("Density")) && data.get("UnitType").equals("Volume")) {
					if (emissionDetailFieldsNonBioFules[i].equals("tCO2")) {
						calcEmissionValue1 = Double.parseDouble(data.get("Fuel Amount"))
								* Double.parseDouble(data.get("ConversionValue"))
								* Double.parseDouble(data.get(emissionFactorDataHeaders[i])) / 1000.0;
					} else {
						calcEmissionValue1 = Double.parseDouble(data.get("Fuel Amount"))
								* Double.parseDouble(data.get("ConversionValue"))
								* Double.parseDouble(data.get(emissionFactorDataHeaders[i])) / 1000.0 / 1000.0;
					}
					Double calcEmissionValue = calcEmissionValue1
							* Double.parseDouble(data.get("Density Unit Conversion Value to Scf"));
					calcEmissionApproxValue = GHGCalculatorsPage
							.approximateDecimalValueWithBigDecimal(calcEmissionValue.toString());
					// --------Density is True and UnitType is Energy------------------------
				} else {
					Double calcEmissionValue;
					if (emissionDetailFieldsNonBioFules[i].equals("tCO2")) {
						calcEmissionValue = Double.parseDouble(data.get("Fuel Amount"))
								* Double.parseDouble(data.get("ConversionValue"))
								* Double.parseDouble(data.get(emissionFactorDataHeaders[i])) / 1000.0;
					} else {
						calcEmissionValue = Double.parseDouble(data.get("Fuel Amount"))
								* Double.parseDouble(data.get("ConversionValue"))
								* Double.parseDouble(data.get(emissionFactorDataHeaders[i])) / 1000.0 / 1000.0;
					}
					calcEmissionApproxValue = GHGCalculatorsPage
							.approximateDecimalValueWithBigDecimal(calcEmissionValue.toString());
				}
				if (weEmissionValue.getText().trim().equals(calcEmissionApproxValue)) {
					passed("Successfully Validated  " + emissionDetailFieldsNonBioFules[i] + "In Emission Details as"
							+ calcEmissionApproxValue);
				} else {
					failed(driver,
							"Failed To Validate " + emissionDetailFieldsNonBioFules + "In Emission details Expected as"
									+ calcEmissionApproxValue + "But Actual is" + weEmissionValue.getText());
				}
			}
			sleep(3000);
			String tCO2eString = null;
			Double tCO2e = null;
			// --------HHV is True and UnitType is Mass------------------------
			if (Boolean.parseBoolean(data.get("HHV")) && data.get("UnitType").equals("Mass")) {
				Double tCO2e1 = Double.parseDouble(data.get("Fuel Amount"))
						* Double.parseDouble(GlobalVariables.coversionValue)
						* Double.parseDouble(GlobalVariables.calculatedEFValue) / 1000.0;
				tCO2e = tCO2e1 * Double.parseDouble(data.get("HHV Unit Conversion Value"));
				tCO2eString = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCO2e.toString());
				// --------Density is True and UnitType is Volume------------------------
			} else if (Boolean.parseBoolean(data.get("Density")) && data.get("UnitType").equals("Volume")) {
				Double tCO2e1 = Double.parseDouble(data.get("Fuel Amount"))
						* Double.parseDouble(GlobalVariables.coversionValue)
						* Double.parseDouble(GlobalVariables.calculatedEFValue) / 1000.0;
				tCO2e = tCO2e1 * Double.parseDouble(data.get("Density Unit Conversion Value to Scf"));
				tCO2eString = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCO2e.toString());
				// --------Both HHV and Density are False------------------------
			} else {
				tCO2e = Double.parseDouble(data.get("Fuel Amount")) * Double.parseDouble(GlobalVariables.coversionValue)
						* Double.parseDouble(GlobalVariables.calculatedEFValue) / 1000.0;
			}
			tCO2eString = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(tCO2e.toString());
			WebElement wetCO2e = driver
					.findElement(By.xpath("//span[text()='tCO2e']//parent::p//following-sibling::p"));
			if (wetCO2e.getText().trim().equals(tCO2eString)) {
				passed("Successfully Validated tCO2e as" + tCO2eString);
			} else {
				failed(driver,
						"Failed to validate tCO2e Expected as" + tCO2eString + "But actual is" + wetCO2e.getText());
			}
			Double noOfDays = calculateDaysDiff(data.get("Start Date"), data.get("End Date"));
			Double tCO2e_Daily = tCO2e / noOfDays;
			String tCO2e_DailyString = tCO2e_Daily.toString();
			String tCO2e_DailyStringApprox = GHGCalculatorsPage
					.approximateDecimalValueWithBigDecimal(tCO2e_DailyString);
			WebElement wetCO2e_Daily = driver
					.findElement(By.xpath("//span[text()='tCO2e (Daily)']//parent::p//following-sibling::p"));
			if (wetCO2e_Daily.getText().trim().equals(tCO2e_DailyStringApprox)) {
				passed("Successfully Validated tCO2e daily as" + tCO2e_DailyStringApprox);
			} else {
				failed(driver, "Failed to Validate tCO2e daily Expected as" + tCO2e_DailyStringApprox + "ButActual is"
						+ wetCO2e_Daily.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateConversionFactorInActivityDetails() {
		try {
			if (Boolean.parseBoolean("MultipleUnitsExist")) {
				// Next step
			} else {
				// --------------modified------------
				WebElement weUnitConvHeader = driver.findElement(
						By.xpath("//p[text()='Conversion Factors']/../..//following-sibling::div//p/span[text()='"
								+ data.get("UnitType") + "']"));  //Mass
				WebElement weUnitConvValue = driver.findElement(
						By.xpath("//p[text()='Conversion Factors']/../..//following-sibling::div//p/span[text()='"
								+ data.get("UnitType") + "']//parent::p//following-sibling::p")); //mass value
				if (isElementPresent(weUnitConvHeader)) {
					passed("Successfully validated Unit Type in Conversion Factors as " + weUnitConvHeader.getText());
				} else {
					failed(driver, "Failed To validated Unit Type in Conversion Factors Expected" + data.get("UnitType")
							+ "But Actual is" + weUnitConvHeader.getText());
				}
				String[] unitConvValue = weUnitConvValue.getText().split("\\|"); //splitting mass and value
				if (unitConvValue[0].trim().equals(data.get("UnitType"))) //trim the value and take text and compare to unittype
				{ 
					passed("Successfully validated Unit Type in unit conversion value of Conversion Factors as "
							+ data.get("UnitType"));
				} else {
					failed(driver, "Failed To validated Unit Type in unit conversion value of  Expected"
							+ data.get("UnitType") + "But Actual is" + unitConvValue[0].trim());
				}
				String[] convValue = unitConvValue[1].split("="); //splitting the value(example 1Lb=0.4535923kg)
				String lhs_ConvalueRaw = convValue[0]; //1 Lb assigning as 0 index
				String Rhs_ConvalueRaw = convValue[1]; //0.4535923 kg as 1 index
				String lhs_Convalue = convValue[0].split(" ")[1];// splitting value and unit( 1 and Lb)
				String Rhs_Convalue = convValue[1].split(" ")[1]; //splitting value and unit(0.4535923 and kg)
				GlobalVariables.coversionValue = Rhs_Convalue;// (assigning this value globally 0.4535923 )
				String selectUnit = data.get("SelectUnit");
				if (lhs_ConvalueRaw.replaceAll("\\s", "").equals(("1" + selectUnit).replaceAll("\\s", ""))) {
					passed("Successfully Validated Selected Unit in Unit conversions as" + selectUnit);
				} else {
					failed(driver, "Failed to validate Selected Unit in Unit conversions  Expected as " + selectUnit
							+ "But actual is " + lhs_Convalue);
				}
				// --------HHV is True and UnitType is Energy------------------------
				if (Boolean.parseBoolean(data.get("HHV")) && data.get("UnitType").equals("Energy")) {
					if (Rhs_ConvalueRaw.replaceAll("\\s", "")
							.equals((data.get("ConversionValue") + " " + data.get("Selecetd Unit Denominator"))
									.replaceAll("\\s", ""))) {
						passed("Successfully Validated ConversionValue in Unit conversions as"
								+ data.get("ConversionValue"));
					} else {
						failed(driver,
								"Failed to validate ConversionValue i in Unit conversions  Expected as "
										+ (data.get("ConversionValue") + " " + data.get("Selecetd Unit Denominator")
												+ "But actual is " + Rhs_ConvalueRaw));
					}
					// --------Density is True and UnitType is Mass------------------------
				} else if (Boolean.parseBoolean(data.get("Density")) && data.get("UnitType").equals("Mass")) {
					if (Rhs_ConvalueRaw.replaceAll("\\s", "")
							.equals((data.get("ConversionValue") + " " + data.get("Selecetd Unit Denominator"))
									.replaceAll("\\s", ""))) {
						passed("Successfully Validated ConversionValue in Unit conversions as"
								+ data.get("ConversionValue"));
					} else {
						failed(driver,
								"Failed to validate ConversionValue i in Unit conversions  Expected as "
										+ (data.get("ConversionValue") + " " + data.get("Selecetd Unit Denominator")
												+ "But actual is " + Rhs_ConvalueRaw));
					}
					// --------Density is True and UnitType is Energy------------------------
				} else if (Boolean.parseBoolean(data.get("Density")) && data.get("UnitType").equals("Energy")) {
					if (Rhs_ConvalueRaw.replaceAll("\\s", "")
							.equals((data.get("ConversionValue") + " " + data.get("Selecetd Unit Denominator"))
									.replaceAll("\\s", ""))) {
						passed("Successfully Validated ConversionValue in Unit conversions as"
								+ data.get("ConversionValue"));
					} else {
						failed(driver,
								"Failed to validate ConversionValue i in Unit conversions  Expected as "
										+ (data.get("ConversionValue") + " " + data.get("Selecetd Unit Denominator")
												+ "But actual is " + Rhs_ConvalueRaw));
					}
					// --------Both HHV and Density are False------------------------
				} else {
					if (Rhs_ConvalueRaw.replaceAll("\\s", "").equals(
							(data.get("ConversionValue") + " " + data.get("DenomUnit")).replaceAll("\\s", ""))) {
						passed("Successfully Validated ConversionValue in Unit conversions as"
								+ data.get("ConversionValue"));
					} else {
						failed(driver,
								"Failed to validate ConversionValue i in Unit conversions  Expected as "
										+ (data.get("ConversionValue") + " " + data.get("DenomUnit") + "But actual is "
												+ Rhs_ConvalueRaw));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateConversionFactorInActivityDetails_EPA() {
		try {
			if (Boolean.parseBoolean("MultipleUnitsExist")) {
				// Next step
			} else {
				// --------------modified------------
				WebElement weUnitConvHeader = driver.findElement(
						By.xpath("//p[text()='Conversion Factors']/../..//following-sibling::div//p/span[text()='"
								+ data.get("UnitType") + "']"));
				WebElement weUnitConvValue = driver.findElement(
						By.xpath("//p[text()='Conversion Factors']/../..//following-sibling::div//p/span[text()='"
								+ data.get("UnitType") + "']//parent::p//following-sibling::p"));
				if (isElementPresent(weUnitConvHeader)) {
					passed("Successfully validated Unit Type in Conversion Factors as " + weUnitConvHeader.getText());
				} else {
					failed(driver, "Failed To validated Unit Type in Conversion Factors Expected" + data.get("UnitType")
							+ "But Actual is" + weUnitConvHeader.getText());
				}
				String[] unitConvValue = weUnitConvValue.getText().split("\\|");
				if (unitConvValue[0].trim().equals(data.get("UnitType"))) {
					passed("Successfully validated Unit Type in unit conversion value of Conversion Factors as "
							+ data.get("UnitType"));
				} else {
					failed(driver, "Failed To validated Unit Type in unit conversion value of  Expected"
							+ data.get("UnitType") + "But Actual is" + unitConvValue[0].trim());
				}
				String[] convValue = unitConvValue[1].split("=");
				String lhs_ConvalueRaw = convValue[0];
				String Rhs_ConvalueRaw = convValue[1];
				String lhs_Convalue = convValue[0].split(" ")[1];
				String Rhs_Convalue = convValue[1].split(" ")[1];
				GlobalVariables.coversionValue = Rhs_Convalue;
				String selectUnit = data.get("SelectUnit");
				if (lhs_ConvalueRaw.replaceAll("\\s", "").equals(("1" + selectUnit).replaceAll("\\s", ""))) {
					passed("Successfully Validated Selected Unit in Unit conversions as" + selectUnit);
				} else {
					failed(driver, "Failed to validate Selected Unit in Unit conversions  Expected as " + selectUnit
							+ "But actual is " + lhs_Convalue);
				}
				// --------HHV is True and UnitType is Mass------------------------
				if (Boolean.parseBoolean(data.get("HHV")) && data.get("UnitType").equals("Mass")) {
					if (Rhs_ConvalueRaw.replaceAll("\\s", "")
							.equals((data.get("ConversionValue") + " " + data.get("Selecetd Unit Denominator"))
									.replaceAll("\\s", ""))) {
						passed("Successfully Validated ConversionValue in Unit conversions as"
								+ data.get("ConversionValue"));
					} else {
						failed(driver,
								"Failed to validate ConversionValue i in Unit conversions  Expected as "
										+ (data.get("ConversionValue") + " " + data.get("Selecetd Unit Denominator")
												+ "But actual is " + Rhs_ConvalueRaw));
					}
					// --------Density is True and UnitType is Volume------------------------
				} else if (Boolean.parseBoolean(data.get("Density")) && data.get("UnitType").equals("Volume")) {
					if (Rhs_ConvalueRaw.replaceAll("\\s", "")
							.equals((data.get("ConversionValue") + " " + data.get("Selecetd Unit Denominator"))
									.replaceAll("\\s", ""))) {
						passed("Successfully Validated ConversionValue in Unit conversions as"
								+ data.get("ConversionValue"));
					} else {
						failed(driver,
								"Failed to validate ConversionValue i in Unit conversions  Expected as "
										+ (data.get("ConversionValue") + " " + data.get("Selecetd Unit Denominator")
												+ "But actual is " + Rhs_ConvalueRaw));
					}
					// --------Density is True and UnitType is Energy------------------------
				} else if (Boolean.parseBoolean(data.get("Density")) && data.get("UnitType").equals("Energy")) {
					if (Rhs_ConvalueRaw.replaceAll("\\s", "")
							.equals((data.get("ConversionValue") + " " + data.get("Selecetd Unit Denominator"))
									.replaceAll("\\s", ""))) {
						passed("Successfully Validated ConversionValue in Unit conversions as"
								+ data.get("ConversionValue"));
					} else {
						failed(driver,
								"Failed to validate ConversionValue i in Unit conversions  Expected as "
										+ (data.get("ConversionValue") + " " + data.get("Selecetd Unit Denominator")
												+ "But actual is " + Rhs_ConvalueRaw));
					}
					// --------Both HHV and Density are False------------------------
				} else {
					if (Rhs_ConvalueRaw.replaceAll("\\s", "").equals(
							(data.get("ConversionValue") + " " + data.get("DenomUnit")).replaceAll("\\s", ""))) {
						passed("Successfully Validated ConversionValue in Unit conversions as"
								+ data.get("ConversionValue"));
					} else {
						failed(driver,
								"Failed to validate ConversionValue i in Unit conversions  Expected as "
										+ (data.get("ConversionValue") + " " + data.get("DenomUnit") + "But actual is "
												+ Rhs_ConvalueRaw));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//data.get("Fuel Amount")
	public String calculateUnitConversion(String valueAmount, String valueunitConversion, String valEF,
			String unitConvUnittoUnit) {
		String valUnit_FuelAmount;
		Double unitconverted = Double.parseDouble(valueAmount) * Double.parseDouble(valueunitConversion)
				* Double.parseDouble(valEF) / Double.parseDouble(unitConvUnittoUnit);
		String valUnit_FuelAmount1 = unitconverted.toString();
		valUnit_FuelAmount = GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(valUnit_FuelAmount1);
		return valUnit_FuelAmount;
	}
	
	
	
//	public void ValidateActivityDetailsInStationaryCombutionForTransactions() {
//        try {
//            sleep(1000);
//            System.out.println("-------Emission Details Are-------");
//            String[] activityDetailFieldNames = { "Facility Name","Start Date", "End Date", "Fuel Type",
//                    "Fuel Amount", "Unit", "Cost", "Currency", "tCO2","tCH4","tN2O","tCO2e","Emission Factor"};
//            verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
//            
//            for (int j = 0; j < activityDetailFieldNames.length; j++) {
//                WebElement weActivityField = driver.findElement(By.xpath(
//                        "//span[text()='" + activityDetailFieldNames[j] + "']//parent::p//following-sibling::p"));
//                if (weActivityField.getText().trim().equals(data.get(activityDetailFieldNames[j]).trim())) {
//                    passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//                            + weActivityField.getText());
//                } else {
//                    System.out.println(data.get(activityDetailFieldNames[j]));
//                    System.out.println(weActivityField.getText());
//                    failed(driver,
//                            "Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//                                    + data.get(activityDetailFieldNames[j]) + "But Actual is"
//                                    + weActivityField.getText());
//                }
//            }
//            validateTransactionGridParameters();
//        } catch (Exception e) {
//            failed(driver, "Exception caught " + e.getMessage());
//        }
//    }
	
//	public void validateTransactionGridParameters() {
//        String[] activityDetailFieldNames = { "Invoice No."," Notes" };
//        for (int j = 0; j < activityDetailFieldNames.length; j++) {
//            WebElement weActivityField = driver.findElement(By.xpath(
//                    "//span[text()='" + activityDetailFieldNames[j] + "']//parent::p//following-sibling::p"));
//            if (weActivityField.getText().trim().equals(TransactionsPage.InvoiceNo)) {
//                passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//                        + weActivityField.getText());
//            } else {
//                System.out.println(data.get(activityDetailFieldNames[j]));
//                System.out.println(weActivityField.getText());
//                failed(driver,
//                        "Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//                                + data.get(activityDetailFieldNames[j]) + "But Actual is"
//                                + weActivityField.getText());
//            }
//        }
//    }


//	public void ValidateActivityDetailsInMobileCombutionForTransactions() {
//        try {
//            sleep(1000);
//            System.out.println("-------Emission Details Are-------");
//            String[] activityDetailFieldNames = { "Facility Name","Vehicle Name","Number Of Vehicle","Start Date", "End Date",
//                    "Amount", "Unit", "Cost", "Currency", "tCO2","tCH4","tN2O","tCO2e","Emission Factor"};
//            verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
//            for (int j = 0; j < activityDetailFieldNames.length; j++) {
//                WebElement weActivityField = driver.findElement(By.xpath(
//                        "//span[contains(text(),'" + activityDetailFieldNames[j] + "')]//parent::p//following-sibling::p"));
//                if (weActivityField.getText().trim().equals(data.get(activityDetailFieldNames[j]).trim())) {
//                    passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//                            + weActivityField.getText());
//                } else {
//                    System.out.println(data.get(activityDetailFieldNames[j]));
//                    System.out.println(weActivityField.getText());
//                    failed(driver,
//                            "Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//                                    + data.get(activityDetailFieldNames[j]) + "But Actual is"
//                                    + weActivityField.getText());
//                }
//            }
//            WebElement weActivityField = driver.findElement(By.xpath(
//                    "//span[text()=' Notes']//parent::p//following-sibling::p"));
//            if(weActivityField.getText().trim().equals(TransactionsPage.InvoiceNo)){
//                passed("Successfully Validated Notes as Expected as "+weActivityField.getText()+" and actual is "+TransactionsPage.InvoiceNo);                        
//            }else {
//                failed(driver,"Failed to validate Notes as Expected as "+weActivityField.getText()+" and actual is "+TransactionsPage.InvoiceNo);
//            }
//        } catch (Exception e) {
//            failed(driver, "Exception caught " + e.getMessage());
//        }
//    }

	
	
	
	@FindBy(xpath = "//div[text()='Add fuel details']")
	private WebElement lblAddFuelsDetails;
	@FindBy(xpath = "//input[@id='fuels']/following-sibling::div/button[@aria-label='Open']")
	private WebElement drpFuelNameForFuelDetails;
	@FindBy(xpath = "//input[@id='emissionType']")
	private WebElement drpEmissionFactorType;
	@FindBy(xpath = "//input[@id='customEmission']")
	private WebElement drpCustomEmission;
	@FindBy(xpath = "//input[@id='dataUnit']")
	private WebElement drpDefaultUnit;
	@FindBy(xpath = "//button[text()='Add']")
	private WebElement btnAdd;
	@FindBy(xpath = "(//button[text()='Close'])[2]")
	private WebElement btnCloseForAddFacilityDetails;
	@FindBy(xpath = "//input[@id='vehicle']")
	private WebElement drpVehicleDetails;
	@FindBy(xpath = "(//span[text()='Period']/following-sibling::div//*[local-name()='svg'])[2]") ////span[text()='Period']//parent::div//child::div/div[@role='button']
	private WebElement drpPeriodDwn;
	
	public void SelectPeriod2023() {                                                                               //naveen
		try {
			sleep(1);
			Actions action = new Actions(driver);
			String ApplicationEnvironment = GlobalKeys.configData.get("ApplicationEnvironment").toLowerCase();
			waitForElement(drpPeriodDwn);
			action.click(drpPeriodDwn).build().perform();
			WebElement perioddrp;
			if(ApplicationEnvironment.equals("preprod")) {
			 perioddrp = driver 
					.findElement(By.xpath("//li[text()='"+Constants.overlapPeriodYear2022+"']"));
			}else {
				perioddrp = driver 
						.findElement(By.xpath("//li[text()='"+Constants.overlapPeriodYear2022+"']"));
			}
			clickOn(perioddrp, "clicked ");
			sleep(2000);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	public void clickOnGridInFacilitiesMenu()
	{
		try {
			sleep(1000);
			WebElement facilitygrid = driver
					.findElement(By.xpath("//*[@value='" + data.get("Facility Name") + "']"));
			clickOn(facilitygrid, "Activity of Facility");
			WebElement FacilityNameEyeicon=driver.findElement(By.xpath("//div[@col-id='actions']//*[@fill='none']"));
			clickOn(FacilityNameEyeicon, "Facility Name EyeIcon");
			activityForEmissionSourcesAndDatasetsForDirectEmissionsScope1();
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}
	
	@FindBy(xpath = "//div[text()='Add Vehicle Details']")
	private WebElement lblAddVehicleDetailse;
	@FindBy(xpath = "//input[@value='Standard']")
	private WebElement drpEmissionFactorTypeValue;
	@FindBy(xpath = "//button[@aria-label='Delete']")
	private WebElement emission_Grid_DeleteButton;
	@FindBy(xpath = "//button[@id='closeIcon']")
	private WebElement closeIcon_EmissionSources;
	public void clickOnFacilityinFacilityGridMultipleDirect()
	{
		try {
			sleep(1000);   
			WebElement lnkAdvanceSetup = driver.findElement(By.xpath("//span[text()='"+data.get("Advance Setup")+"']/parent::div/parent::div"
				         		+ "/following-sibling::div/span"));
		    waitForElement(lnkAdvanceSetup);
		    clickOn(lnkAdvanceSetup, "Advance SetUp");
		    sleep(1000);
		    //SelectPeriod2022();
		    addFuelAndVehicleDetailsInAdvanceConfiguration();
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}
	
	public void addFuelAndVehicleDetailsInAdvanceConfiguration() {
		try {
			Actions action = new Actions(driver);
			waitForElement(drpEmissionFactorType);
			WebElement drpCustomEmissionOptions;
			if(data.get("Vehicle Name").equals("CAR")) {
				action.click(drpVehicleDetails).perform();
				WebElement drpVehicleDetailsOptions = driver.findElement(By.xpath("//li[text()='CAR']"));
				clickOn(drpVehicleDetailsOptions, "CAR");
			}else {
				jsClick(drpFuelNameForFuelDetails, "Fuel Name For Fuel Details");
				WebElement DrpFuelOptions = driver.findElement(By.xpath("//li[text()='"+data.get("Fuel Name")+"']"));
				clickOn(DrpFuelOptions, data.get("Fuel Name"));
			}
			action.click(drpEmissionFactorType).perform();
			WebElement drpEmissionFactorTypeOptions = driver.findElement(By.xpath("//li[text()='"+data.get("Emission Factor Type")+"']"));
			clickOn(drpEmissionFactorTypeOptions, data.get("Emission Factor Type"));
			action.click(drpCustomEmission).perform();
			drpCustomEmissionOptions = driver.findElement(By.xpath("//li[text()='"+CustomEmissionFactorName+"']"));   
			clickOn(drpCustomEmissionOptions, ""+CustomEmissionFactorName+"");
			action.moveToElement(btnAdd).click().perform();
			WebElement toastmessage = driver.findElement(By.xpath("//*[contains(text(),'Advanced setup added successfully')]"));
			if (isElementPresent(toastmessage)) {
				passed("Successfully updated advanced setup");
			} else {
				failed(driver, "failed to update advanced setup");
			}
			action.moveToElement(btnCloseForAddFacilityDetails).click().perform();
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}
	
	@FindBy(xpath = "//div[text()='Emission Sources & Datasets']")
	private WebElement lblEmissionSourcesAndDatasets;
	@FindBy(xpath = "//div[contains(text(),'Scope 1')]/parent::div/following-sibling::div//*[local-name()='svg']")
	private WebElement expandMoreIconScope1;
	public void activityForEmissionSourcesAndDatasetsForDirectEmissionsScope1(){
		try {
			waitForElement(lblEmissionSourcesAndDatasets);
			verifyIfElementPresent(lblEmissionSourcesAndDatasets, "Emission Sources And Datasets", "GHGCalculators");
			waitForElement(expandMoreIconScope1);
			clickOn(expandMoreIconScope1, "Expand More Icon For DirectEmission");
			WebElement lnkAdvanceSetup = driver.findElement(By
					           .xpath("//span[text()='"+data.get("Advance Setup")+"']/parent::div/parent::div/following-sibling::div/span"));
			waitForElement(lnkAdvanceSetup);
			clickOn(lnkAdvanceSetup, "Advance SetUp");
			sleep(1000);
			//SelectPeriod2022();
			Actions action = new Actions(driver);
			if(!data.get("Fuel Name").equals("Fuel_S")) {
	            if(isElementPresent(emission_Grid_DeleteButton)) {
	            	String s;
	            	if(data.get("Vehicle Name").equals("CAR")) {
	            		 s = data.get("Vehicle Name");
	            	}else {
	            		 s = data.get("Fuel Name");
	            	}
	            	String fuel_Vehicle = "//span[text()='"+s+"']/parent::div/parent::div/parent::div/following-sibling::div//button[@aria-label='Delete']";
	            	WaitForElementWithDynamicXpath(fuel_Vehicle);
	            	WebElement emission_Grid_DeleteButton = driver.findElement(By.xpath(fuel_Vehicle));
	            	actionsClick(emission_Grid_DeleteButton, "Emission Grid Delete Button");
	            }
	            actionsClick(closeIcon_EmissionSources, "close x icon");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
		
	}
	
	
	
	
	
	@FindBy(xpath = "//button[text()='Add Transaction']")
	private WebElement btnAddTransaction;
	public void clickOnAddTransactionButton() {
		try {
			verifyIfElementPresent(btnAddTransaction, "Add Transaction", "DirectEmissionCalculator");
			clickOn(btnAddTransaction, "Add Transaction");
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}
	
	public static String InvoiceNo;

	public void clickOnActivityInActivitiesGridMultipleForTransactions() {
		try {
			WebElement gridActivityForTransactions = driver
					                   .findElement(By.xpath("//*[text()='"+InvoiceNo+"']"));
			clickOn(gridActivityForTransactions, "Activity of Facility" + InvoiceNo );
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}
	
	
	

	
	
	

	
	
	//Naveen
	
	     @FindBy(xpath = "//input[@id='refrigerant_ref_name']")
	     private WebElement drpRefrigerantUsed;
	     public void EditRefrigirentsUsedForAllRefrigirantsInFugitives() {
	    	 try {
				 clickOn(drpRefrigerantUsed, "DropDown Refrigirant Used");
				 WebElement optsRefruigirantUsed = driver.findElement(By
						             .xpath("//ul[@id='refrigerant_ref_name-listbox']//li[text()='"+data.get("Refrigerant used")+"']"));
				 clickOn(optsRefruigirantUsed, data.get("Refrigerant used"));
				 clickOn(btnSave, "Save Button");
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
	     }
	
	     
	
	     //public static String actualEmissionFactor1;
	     public static String differenceOfTCO2e;
	     public static String emissionFactorStatus;
	     public void validateEmissionFactorAndTCO2eForFugitivesForAllRefrigirants() throws IOException {
	    	try {
	    		differenceOfTCO2e = null;
	    		emissionFactorStatus=null;
	    		passed("Refrigirant Name ---> " + data.get("Refrigerant used"));
				String emissionFactor = "//*[text()='Emission Factor']/parent::p/following-sibling::p/div";
				System.out.println(WaitForElementWithDynamicXpath(emissionFactor));
				System.out.println(data.get("S.No"));
				WebElement actualEmissionFactor = driver.findElement(By.xpath(emissionFactor));
				String actualEmissionFactor1 = actualEmissionFactor.getText().split(" ")[0];
				if(actualEmissionFactor1.trim().equals(data.get("EmissionFactor AR5").trim())) {
					passed("Successfully validated, Emission factor Expected is "+data.get("EmissionFactor AR6")+" "
							+ "and the Actual is "+actualEmissionFactor1+"");
				}else {
					emissionFactorStatus="Failed";
					failed(driver,"failed to validate, Emission factor Expected is "+data.get("EmissionFactor AR6")+" "
							+ "and the Actual is "+actualEmissionFactor1+"");
				}
				
				Double dTCO2e = (Double.parseDouble(actualEmissionFactor1) 
						                 * Double.parseDouble(data.get("Quantity of refrigerant leakage / Usage")))
						                 / 1000;
				String expectedTCO2eValue = dTCO2e.toString();
				WebElement actualTCO2eValue = driver.findElement(By
						          .xpath("//span[text()='tCO2e']/parent::p/following-sibling::p/div"));
				
				if(actualTCO2eValue.getText().equals(expectedTCO2eValue)) {
					passed("Successfully validated, TCO2eValue Expected is "+expectedTCO2eValue+" "
							+ "and the Actual is "+actualTCO2eValue.getText()+"");
				}else {
					Double difference = Double.parseDouble(actualTCO2eValue.getText()) - Double.parseDouble(expectedTCO2eValue);
					 differenceOfTCO2e = difference.toString();
					if(differenceOfTCO2e.equals("0.0")) {
						passed("Successfully validated, TCO2eValue Expected is "+expectedTCO2eValue+" "
								+ "and the Actual is "+actualTCO2eValue.getText()+"");
					}else {
					failed(driver,"failed to validate, TCO2eValue Expected is "+expectedTCO2eValue+" "
							+ "and the Actual is "+actualTCO2eValue.getText()+""
									+ " and the Difference is "+differenceOfTCO2e+"");
					}
				}
				
				
				String filepath = System.getProperty("user.dir");
				System.out.println(filepath);
				String path = filepath + "\\src\\test\\resources\\Fugitives\\RefrigerantsValidation.xlsx";
				ExcelWriter xlWriter = new ExcelWriter(path);
				xlWriter.setCellData("Refrigerants", 0, 0, "S.No");
				xlWriter.setCellData("Refrigerants", 0, 1, "Refrigerant used");
				xlWriter.setCellData("Refrigerants", 0, 2, "Expected Emission Factor");
				xlWriter.setCellData("Refrigerants", 0, 3, "Actual Emission Factor");
				xlWriter.setCellData("Refrigerants", 0, 4, "EmissionFactor Status");
				xlWriter.setCellData("Refrigerants", 0, 5, "Expected TCO2e Value");
				xlWriter.setCellData("Refrigerants", 0, 6, "Actual TCO2e Value");
				xlWriter.setCellData("Refrigerants", 0, 7, "Difference Value of TCO2e");
				
				int rowCount = xlWriter.getRowCount("Refrigerants");
				System.out.println(rowCount);
				xlWriter.setCellData("Refrigerants", rowCount+1, 0, data.get("S.No"));
				xlWriter.setCellData("Refrigerants", rowCount+1, 1, data.get("Refrigerant used"));
				xlWriter.setCellData("Refrigerants", rowCount+1, 2, data.get("EmissionFactor AR5"));
				xlWriter.setCellData("Refrigerants", rowCount+1, 3, actualEmissionFactor1);
				xlWriter.setCellData("Refrigerants", rowCount+1, 4, emissionFactorStatus);
				xlWriter.setCellData("Refrigerants", rowCount+1, 5, expectedTCO2eValue);
				xlWriter.setCellData("Refrigerants", rowCount+1, 6, actualTCO2eValue.getText()); 
				xlWriter.setCellData("Refrigerants", rowCount+1, 7, differenceOfTCO2e);
			} catch (NumberFormatException e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
	    	
	    	
	     }
	
	     
	
	     public void clearDirForFugitivesRefrigerants() {
	    	    try {
					File file;
					File dir;
					String filepath = System.getProperty("user.dir");
					System.out.println(filepath);
					String path1 = filepath + "\\src\\test\\resources\\Fugitives";
					dir = new File(path1);
					FileUtils.cleanDirectory(dir);
				} catch (IOException e) {
					failed(driver, "Exception caught " + e.getMessage());
				}
	     }
	
	
	     
	     
	     @FindBy(xpath = "//button[text()='Update']")
	     private WebElement btnUpdate;
	     public void editFuelAndVehicleDetailsForSebanciInAdvanceSetup() {
	    	 try {
	    		 Actions action = new Actions(driver);
	    		 String btnFuelGridEdit ="//span[text()='"+data.get("Fuel Name")+"']/ancestor::div[@role='gridcell']"
	    		 		+ "/following-sibling::div//button[@aria-label='Edit']";
	    		 boolean fuelName = WaitForElementWithDynamicXpath(btnFuelGridEdit);
	    		 if( fuelName == true) {
	    			 clickOnElementWithDynamicXpath(btnFuelGridEdit, btnFuelGridEdit);
	    			 AddFuel$VehicleDetailsForScope1AdvanceSetUp();
	    		 }else {
	    			 action.click(drpFuelNameForFuelDetails).build().perform();
	    			 String fuelNameOpts = "//li[text()='"+data.get("Fuel Name")+"']";
	    			 clickOnElementWithDynamicXpath(fuelNameOpts, data.get("Fuel Name"));
	    			 AddFuel$VehicleDetailsForScope1AdvanceSetUp();
	    		 }
					action.moveToElement(btnCloseForAddFacilityDetails).click().perform();
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
	         
	     }
	     
	     public void AddFuel$VehicleDetailsForScope1AdvanceSetUp() {
	    	    Actions action = new Actions(driver);
			    action.click(drpEmissionFactorType).perform();
				WebElement drpEmissionFactorTypeOptions = driver.findElement(By
						.xpath("//li[text()='"+data.get("Emission Factor Type")+"']"));
				clickOn(drpEmissionFactorTypeOptions, data.get("Emission Factor Type"));
				action.click(drpCustomEmission).perform();				
				WebElement drpCustomEmissionOptions = driver.findElement(By
							                 .xpath("//li[text()='"+CustomEmissionFactorName+"']"));   
					clickOn(drpCustomEmissionOptions, ""+CustomEmissionFactorName+"");
				if(data.get("Vehicle Name").equals("myVehicleMobile")) {
					WebElement weDefaultUnit = driver.findElement(By.xpath("(//input[@id='dataUnit'])[2]"));
					clickOn(weDefaultUnit, "Default Unit");
				}else {
					clickOn(drpDefaultUnit, "Default Unit");
				}
				WebElement optDefaultUnit = driver.findElement(By
						          .xpath("//li[text()='"+data.get("Default Unit")+"']"));
				action.click(optDefaultUnit).build().perform();
				action.moveToElement(btnUpdate).click().perform();
				WebElement toastmessage = driver
						.findElement(By.xpath("//*[contains(text(),'Advanced setup updated successfully')]"));
				if (isElementPresent(toastmessage)) {
					passed("Successfully updated advanced setup");
				} else {
					failed(driver, "failed to update advanced setup");
				}
	     }
	     
	     public void clickOnSabanciOrganization() {
	    	 try {
	    		 sleep(2);
				Actions action = new Actions(driver);
				 String sabanciOrg = "//div[text()='"+data.get("OrgName")+"']";             //Sabanci Holdings_QA
				 System.out.println("Sabanci org is diplayed -->"+WaitForElementWithDynamicXpath(sabanciOrg));
				 WebElement weSabanciOrgName = driver.findElement(By.xpath(sabanciOrg));
				 action.doubleClick(weSabanciOrgName).build().perform();
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
	     }
	     
	
	     @FindBy(xpath = "//button[@aria-label='Previous']//following-sibling::button[@aria-label='Edit']")
	 	 private WebElement btnEditActivityqa;
	     public void validateCalorificValuesForTypeOfFuelsInScope1(String unitCategory) {
	    	 try {
	    		 waitForElement(btnEditActivityqa);
				 clickOn(btnEditActivityqa, "Edit Button");
				 waitForElement(txtUnitName);
				 scrollTo(txtUnitName);
				 clickOn(txtUnitName, "Unit Dropdown");
				 String strOptsUnit = "//li[text()='"+unitCategory+"']";
				 WaitForElementWithDynamicXpath(strOptsUnit);
				 WebElement optUnit = driver.findElement(By.xpath(strOptsUnit));
				 scrollTo(optUnit);
				 clickOn(optUnit, unitCategory);
				 clickOn(btnSave, "Save Button");
				 calculateAndValidateCalorificValueUsingFormula(unitCategory);
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
	     }
	
	
	
	     public void validateCalorificValuesAndUnitConversionMessagesForScope1(String unitCategory) {
	    	 try {
	    		 sleep(1);
				switch (unitCategory) {
				case "energy":
					String[] unitsForEnergy = Constants.unitNamesEnergy;
					for(int i = 0;i<unitsForEnergy.length; i++) {
					validateCalorificValuesForTypeOfFuelsInScope1(unitsForEnergy[i]);
					}
					
					break;
				case "mass":
					String[] unitsForMass = Constants.unitNamesMass;
					for(int i = 0;i<unitsForMass.length; i++) {
						validateCalorificValuesForTypeOfFuelsInScope1(unitsForMass[i]);
					}
					break;
				case "volume" : 
					String[] unitsForVolume = Constants.unitNamesVolume;
					for(int i = 0;i<unitsForVolume.length; i++) {
						validateCalorificValuesForTypeOfFuelsInScope1(unitsForVolume[i]);
					}
					break;
				}
				clickOnGHGCalculatorIcon();
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
	    		 
	     }
	
	
	
	     public void calculateAndValidateCalorificValueUsingFormula(String unitCategory) {
	    	 try {
	    		    sleep(1);
					String strConvertUnitKWh = "//span[text()='Energy']/parent::p/parent::div//following-sibling::div/p/div[contains(text(),'kWh')]";
					String strConvertUnitTJ = "//span[text()='Energy']/parent::p/parent::div//following-sibling::div/p/div[contains(text(),'TJ')]";
					String strConvertUnitLT = "//span[text()='Mass']/parent::p/parent::div//following-sibling::div//div[contains(text(),'kg')]";
					String strConvertUnitVol = "//span[text()='Volume']/parent::p/following-sibling::p/div";
					Double formula;
					WaitForElementWithDynamicXpath("//span[text()='Calorific Value']");
				 if(data.get("unitCategory").equals("energy")) {
					 if(unitCategory.equals("Terajoule (TJ)")) {
				    	 formula = Double.parseDouble(data.get("Fuel Amount"));
				     }else {
				     System.out.println(WaitForElementWithDynamicXpath(strConvertUnitKWh));
					 WebElement weConvertUnitKWh = driver.findElement(By.xpath(strConvertUnitKWh));
					 WebElement weConvertUnitTJ = driver.findElement(By.xpath(strConvertUnitTJ));
					 String splitKWh = weConvertUnitKWh.getText().split("= ")[1].split(" ")[0];
					 String splitTJ = weConvertUnitTJ.getText().split("= ")[1].split(" ")[0];
				        formula = Double.parseDouble(data.get("Fuel Amount")) 
						          * Double.parseDouble(splitKWh)
						          * Double.parseDouble(splitTJ);
				     } 
				 }else if(data.get("unitCategory").equals("mass")) {
					 System.out.println(WaitForElementWithDynamicXpath(strConvertUnitLT));
					 WebElement weConvertUnitLT = driver.findElement(By.xpath(strConvertUnitLT));
					 String splitLT = weConvertUnitLT.getText().split("= ")[1].split(" ")[0];
					 formula = Double.parseDouble(data.get("Fuel Amount")) 
					          * Double.parseDouble(data.get("Calorific Factor")) 
					          * Double.parseDouble(splitLT) 
					          * 0.0000036;                // KWH to TJ ---> 0.0000036
				 }else {
					 WebElement weConvertUnitVol;
					 if(data.get("Calculator Name").equals("Mobile Combustion")) {
						 sleep(3000);
						  weConvertUnitVol = driver
								       .findElement(By.xpath("//p[text()='Conversion Factors']/parent::div/parent::div"
								       		+ "/following-sibling::div//p/div[contains(text(),'Volume')]"));
						  String  splitVol = weConvertUnitVol.getText().split("= ")[1].split(" ")[0];
							 formula = Double.parseDouble(data.get("Activity Amount")) 
							          * Double.parseDouble(data.get("Calorific Factor")) 
							          * Double.parseDouble(splitVol) 
							          * 0.001;
					 }else{
						  System.out.println(WaitForElementWithDynamicXpath(strConvertUnitVol));
						  weConvertUnitVol = driver.findElement(By.xpath(strConvertUnitVol));
						  String  splitVol = weConvertUnitVol.getText().split("= ")[1].split(" ")[0];
							 formula = Double.parseDouble(data.get("Fuel Amount")) 
							          * Double.parseDouble(data.get("Calorific Factor")) 
							          * Double.parseDouble(splitVol)
							          * 0.0000036;
					 }
				 }
				 String expectedCalorific = formula.toString();
				 String expectedCalorificValue = approximateDecimalValueWithBigDecimal(expectedCalorific);
				 WebElement actualCalorificValue = driver.findElement(By
						       .xpath("//span[text()='Calorific Value']/parent::p/following-sibling::p/div"));
				 if(actualCalorificValue.getText().equals(expectedCalorificValue)) {
					 passed("Successfully validated, The Expected Caloric value is "+expectedCalorificValue+" "
					 		+ "and the Actual as "+actualCalorificValue.getText()+"");
				 }else {
					 Double doubleDiff= Double.parseDouble(expectedCalorificValue) - Double.parseDouble(actualCalorificValue.getText());
						info(""+doubleDiff+"");
						if(doubleDiff<1 && doubleDiff>-0.4) {
							passed("Successfully validated, The Expected Caloric value is "+expectedCalorificValue+" "
							 		+ "and the Actual as "+actualCalorificValue.getText()+"");
						}else {
							 failed(driver,"failed to validate, The Expected Caloric value is "+expectedCalorificValue+" "
								 		+ "and the Actual as "+actualCalorificValue.getText()+"");
						}
				 }
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
	    	 
	     }
	
	     
	     //IEA Dataset
	
	     public void validateUnitsForFuelTypesForStationaryCombustion_IEADataSets(String[] fuelsIEA) {
	    	 try {
	    		 sleep(2);
				 for(int i = 0; i<fuelsIEA.length; i++) {
					 WebElement validFuels = driver.findElement(By
							                       .xpath("//li[text()='"+fuelsIEA[i]+"']"));
					 System.out.println(validFuels.getText());
					 if(validFuels.getText().equals(fuelsIEA[i])) {
						 passed("Successfully validated, The Fuel Type in dropdown - > "+validFuels.getText()+"");
					 }else {
						 failed(driver,
								 "failed to validate, FuelType expected is "+fuelsIEA[i]+" and "
								 		+ "Actual as "+validFuels.getText()+"");
					 }
					 clickOn(validFuels, validFuels.getText());
					 clickOn(txtUnitName, "UnitType");
					 String[] unitNames;
					 if(data.get("unitCategory").equals("volume")) {
						 unitNames = Constants.unitNamesVolume;
					 }else if(data.get("unitCategory").equals("energy")){
						 unitNames = Constants.unitNamesEnergy;
					 }else {
						 unitNames = Constants.unitNamesMass;
					 }
					 for(int j=0; j<unitNames.length; j++) {
						 WebElement validFuelsUnitType = driver.findElement(By
			                       .xpath("//li[text()='"+unitNames[j]+"']"));
						 if(validFuelsUnitType.getText().equals(unitNames[j])) {
							 passed("Successfully validated, "+data.get("unitCategory")+" Unit Type in dropdown - > "
							 		+ ""+validFuelsUnitType.getText()+"");
						 }else {
							 failed(driver,
									 "failed to validated, "+data.get("unitCategory")+" UnitType expected is "+unitNames[j]+" and "
									 		+ "Actual as "+validFuelsUnitType.getText()+"");
						 }
					 }
					// clickOn(txtFuelType, "txtFuelType");
				 }
				
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
	    	 
	     }
	
	
	     @FindBy(xpath = "//input[@placeholder='Search...']")
	     public WebElement searchForEmissionFactor;
	     public void Add_EditActivitiesForEmissionFactorGridForChangingFuelTypeAndUnits() {
	    	 try {
				if(data.get("unitCategory").equals("energy")) {
					Add_CustomEmissionFactor_Scope1_InDirectEmissions();
				 }else {
					 clickOn(searchForEmissionFactor, "Search Field of Emission Factor");
					 enterText(searchForEmissionFactor, "", CustomEmissionFactorName);
					 sleep(2000);
					 String CEFGrid = "//*[text()='" + CustomEmissionFactorName + "']//parent::div";
					 if(isElementPresentDynamicXpath(CEFGrid)) {
						 clickOnElementWithDynamicXpath(CEFGrid, CustomEmissionFactorName);
						 sleep(500);
						 clickOn(btnEditActivityqa, "btnActivities");
						 clickOn(drpUnitCEFDenominator, "Click on Unit of Custom EF (Denominator) Drp");
						 WebElement weUnitCEFDenominator = driver
								.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Denominator)") + "']"));
						clickOn(weUnitCEFDenominator, data.get("Unit of Custom EF (Denominator)"));
						clickOn(drpCalorificFactorDenominator, "Calorifactor Denominator");
						WebElement calDenomiUnits = driver
							       .findElement(By.xpath("//li[text()='"+data.get("Unit of Calorific Factor (Denominator)")+"']"));
						clickOn(calDenomiUnits, data.get("Unit of Calorific Factor (Denominator)"));
						enterText(txtNotesCEF, "entered txtNotesCEF", data.get("Notes"));
						clickOn(btnSaveParemeterInput, "Save Button");
					 }else {
						 Add_CustomEmissionFactor_Scope1_InDirectEmissions();
					 }
				 }
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
	    	
	    	 
	     }
	     
	
	     public void Add_EditActivityForStationaryCombustionScope1() {
	    	 try {
	    		 sleep(1);
				String[] fuelType = {data.get("Fuel Name")};
				 if(data.get("unitCategory").equals("energy")) {
					 AddActivityForCEFStationaryCombutionInDirectEmissions();
					 }else {
						 clickOn(searchForEmissionFactor, "Search Field of FacilityName");
						 enterText(searchForEmissionFactor, "", data.get("Facility Name"));
						 sleep(2000);
						 String CEFGrid = "//*[text()='" + data.get("Facility Name") + "']//parent::div";
						 if(isElementPresentDynamicXpath(CEFGrid)) {
							 clickOnElementWithDynamicXpath(CEFGrid, data.get("Facility Name"));
							 sleep(500);
							 clickOn(btnEditActivityqa, "btnActivities");
							 sleep(300);
							 clickOn(txtStartDate, "Start date");
							 enterText(txtStartDate, "Start Date", "2022/01/02");
							 clickOn(txtFuelType, "fuel type");
							 validateUnitsForFuelTypesForStationaryCombustion_IEADataSets(fuelType);
							 WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit") + "']"));
							 clickOn(we, data.get("Unit"));
							 //enterText(txtNotesCEF, "entered txtNotesCEF", data.get("Notes"));
							 clickOn(btnSaveParemeterInput, "Save Button");
						 }else {
							 AddActivityForCEFStationaryCombutionInDirectEmissions();
						 }
					 }
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
	     }
	     
	     
	     
	     @FindBy(xpath = "//div[contains(text(),'Default Avg Fuel Efficiency')]")
	     public WebElement fuelEfficiencyFromRHP;
	     public void calculateEmissionFactorUsingFormulaForMobileCombustionForFuelEfficiency() {                                            //naveen
	 		try {
	 			sleep(1);
	 			WebElement emissionFactor = driver.findElement(By
	 			        .xpath("//span[text()='Emission Factor']/parent::p/following-sibling::p/div"));
	 			String[] emissionFactor1 = emissionFactor.getText().split(" ");
	 			String emissionFactorValueRHP = emissionFactor1[0];
	 			GHGCalculatorsPage.getCO2_CH4_N20Value();
	 			formulaForCalculatingEmissionFactorUsingDenominatorUnitInMobileCombustion();
	 			Double strfuelEfficiencyFromRHP = Double.parseDouble(fuelEfficiencyFromRHP.getText().split("\\|")[2]);
	 			Double valOfEmission =( co2 + ch4 + n2o ); 
	 			if(data.get("unitCategory").equals("mass")) {
	 				 valOfEmission =( co2 + ch4 + n2o ) / strfuelEfficiencyFromRHP; 
	 			}
	 			String UnitConversion = "//div[contains(text(),'Unit Conversion | ')]";
	 			WebElement weUnitConversion = driver.findElement(By.xpath(UnitConversion));
	 			valOfEmission =valOfEmission * Double.parseDouble(weUnitConversion.getText().split("= ")[1].split(" ")[0]);
	 			emmissionFactorValueExp = valOfEmission.toString();
	 			String emmissionFactorValueExpected = approximateDecimalValueWithBigDecimal(emmissionFactorValueExp);
	 			if(emissionFactorValueRHP.equals(emmissionFactorValueExpected)) {
	 				passed("Succesfully validated Emission Factor value" + emmissionFactorValueExpected + ""
	 						+ " And Actual is " + emissionFactorValueRHP);
	 			} else {
	 				Double doubleDiff= Double.parseDouble(emmissionFactorValueExpected) - Double.parseDouble(emissionFactorValueRHP);
	 				info(""+doubleDiff+"");
	 				if(doubleDiff<1 && doubleDiff>-0.4) {
	 					passed("Succesfully validated Emission Factor value" + emmissionFactorValueExpected + ""
	 							+ " And Actual is " + emissionFactorValueRHP);
	 				}else {
	 					failed(driver,
	 							"Failed validated Emission Factor value" + " " + emmissionFactorValueExpected + ""
	 									+ " But Actual is " + emissionFactorValueRHP);
	 				}
	 			}
	 		} catch (Exception e) {
	 			failed(driver, "Exception caught " + e.getMessage());
	 		}
	 	}
	     
	     public static Double co2;
 		 public static Double ch4;
 		 public static Double n2o;
	     public void formulaForCalculatingEmissionFactorUsingDenominatorUnitInMobileCombustion() {
	 		 try {
				Double conversionValue = null;
				 Double conversionOfCO2 = null;
				 if(data.get("Nemerator").equals("kg") && data.get("Denominator").equals("gal")) {
					 conversionValue = 1.0;
				 }else if(data.get("Nemerator").equals("g") && data.get("Denominator").equals("mi")) {
					 conversionValue = Double.parseDouble(data.get("Fuel Efficiency")) * 0.001;
				 }else if(data.get("Nemerator").equals("g") && data.get("Denominator").equals("gal")) {
					 conversionValue = 0.001;
				 }else {
					 conversionValue = 1.0;
				 }if(data.get("Nemerator").equals("kg") && data.get("Co2DenominatorUnit").equals("scf")) {
					  conversionOfCO2 = 113.26;
				 }else {
					conversionOfCO2 = 1.0;
				 }
				 if(data.get("gwp year").contains("AR4")) {
					 co2 = Constants.GWParCO2 * GHGCalculatorsPage.CO2Value * conversionOfCO2;
					 ch4 = Constants.GWPar4CH4 * GHGCalculatorsPage.CH4ValueEXP * conversionValue;                 
					 n2o = Constants.GWPar4N2O * GHGCalculatorsPage.N2OValueEXP * conversionValue;
				 }else if(data.get("gwp year").contains("AR5")){
						 co2 = Constants.GWParCO2 * GHGCalculatorsPage.CO2Value * conversionOfCO2;
						 ch4 = Constants.GWPar5CH4 * GHGCalculatorsPage.CH4ValueEXP * conversionValue;                 
						 n2o = Constants.GWPar5N2O * GHGCalculatorsPage.N2OValueEXP * conversionValue;
				 }else {
						 co2 = Constants.GWParCO2 * GHGCalculatorsPage.CO2Value * conversionOfCO2;
						 ch4 = Constants.GWPar6CH4 * GHGCalculatorsPage.CH4ValueEXP * conversionValue;                 
						 n2o = Constants.GWPar6N2O * GHGCalculatorsPage.N2OValueEXP * conversionValue;
				}
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
	     }
	     
	     
	     public void validateStandardisedUnitsOfNumeratorsAndDenominatorsForMobileCombustionFE() {
	    	 try {
				String[] actualUnits =  {GHGCalculatorsPage.CO2Value.toString(),GHGCalculatorsPage.CH4ValueEXP.toString(),
						                 GHGCalculatorsPage.N2OValueEXP.toString(), 
						                 GHGCalculatorsPage.expCO2NumereatorUnit,GHGCalculatorsPage.expCO2DenoMinatorUnit,
						                 GHGCalculatorsPage.expCH4NumereatorUnit,GHGCalculatorsPage.expCH4DenoMinatorUnit,
						                 GHGCalculatorsPage.expN2ONumereatorUnit,GHGCalculatorsPage.expN2ODenoMinatorUnit};
				 String[] expUnits    = {"CO2","CH4","N2O","Co2NumeratorUnit","Co2DenominatorUnit","CH4NumeratorUnit","CH4DenominatorUnit",
						                 "N2ONumeratorUnit","N20DenominatorUnit"};
				 for(int i=0; i<actualUnits.length; i++) {
					 if(actualUnits[i].equals(data.get(expUnits[i]))) {
						 passed("Successfully validated, The "+expUnits[i]+" Expected is "+data.get(expUnits[i])+" "
						 		+ "but the actual as "+actualUnits[i]+"");
					 }else {
						 failed(driver,
								 "failed to validate, The "+expUnits[i]+" Expected is "+data.get(expUnits[i])+" "
							 		+ "but the actual as "+actualUnits[i]+"");
					 }
				 }
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
	     }

	     public void SelectOrganizationForTiffany() {
	    	 try {
				String strOrgName = "//div[text()='Tiffany']";
				 WaitForElementWithDynamicXpath(strOrgName);
				 WebElement weOrgName = driver.findElement(By.xpath(strOrgName));
				 actionsClick(weOrgName, "Tiffany");
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
	     }
	     
	     
	     //FE - FUEL EFFICICENCY
	     public ArrayList<String> datasets;
	     public void editandValidateForAllUnitsForStationaryCombustionFE() {
	    	String strName = new Exception().getStackTrace()[0].getMethodName();
	 		data = new Data(Constants.MCStandardFuelEfficiency);
	 		datasets = data.getDataSets();
	 		data.setColIndex();
	 		List<String> vehicleName = new ArrayList();
	 		for (String dataset : datasets) {
	 			data.setIndex_Multiple(dataset);
	 			datasetStart(strName + dataset);
	 			if (!vehicleName.contains(data.get("Vehicle Name"))) {
	 				vehicleName.add(data.get("Vehicle Name"));
	 				clickOnAddActivityButton();
	 				EditActivityMobileCombutionInDirectEmissions();
	 				verifyAddActivityUpdatedToastMessage();
	 			}else {
	 				clickOnEditButtonInActivityDetails();
	 				clickOn(txtUnitName, "Unit");
	 				String strUnit = "//li[text()='" + data.get("Unit") + "']";
	 				WaitForElementWithDynamicXpath(strName);
	 				WebElement we = driver.findElement(By.xpath(strUnit));
	 				clickOn(we, data.get("Unit"));
	 				clickOn(btnSave, "Save Button");
	 				verifyEditActivityUpdatedToastMessage();
	 			}
	 			validateRHPActivityDetailsForAllCalculators(Constants.actMC,"Mobile Combustion");
 				calculateEmissionFactorUsingFormulaForMobileCombustionForFuelEfficiency();
 				validateStandardisedUnitsOfNumeratorsAndDenominatorsForMobileCombustionFE();
 				calculateTCO2eValueUsing_EmissionFactorValue(data.get("Activity Amount"));
 				validateTCO2andTCH4andTN2O_ForMobileCombustionInScope1FuelEfficiency();
 				clickOnCloseInActivityDetails();
	 		}
	     }
	     
	
	@Override
	protected void VerifyNavigationToValidPage() {
		try {
//			waitForElement(lblGHGCalculator);
//			if (isElementPresent(lblGHGCalculator)) {
//				passed("User Successfully Navigated To GHG_Calculator Page");
//			} else {
//				failed(driver, "Failed To Navigate To GHG_Calculator Page");
//			}
//			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
}
