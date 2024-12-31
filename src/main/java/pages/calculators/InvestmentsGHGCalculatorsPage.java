package pages.calculators;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import pages.GHGCalculatorsPage;
import uielements.CalculatorElements;
import utilities.Constants;
import utilities.Data;

public class InvestmentsGHGCalculatorsPage extends GHGCalculatorsPage {
	public InvestmentsGHGCalculatorsPage(WebDriver driver2, Data data) {
		super(driver2, data);
		// TODO Auto-generated constructor stub
	}

	
	@FindBy(xpath = "//input[@id='scope3_co2e']/following-sibling::div//*[local-name()='svg']")
	private WebElement totalEmissionCalc;
	public void EditActivityScope3_15InvestmentsSpecificMethod() {
		try {
			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
			Actions act = new Actions(driver);
			act.doubleClick(txtInvesteeCompanyScope3_15).perform();
			enterText(txtInvesteeCompanyScope3_15, "Investee Copmany text", data.get("Investee Company"));
			clickOn(drpInvesteeCompany, data.get("Investee Company Sector"));
			WebElement weinvesteeCompanyProject = driver
					.findElement(By.xpath("//li[text()='" + data.get("Investee Company Sector") + "']"));
			clickOn(weinvesteeCompanyProject, data.get("Investee Company Sector"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			act.doubleClick(txtreporting_company_share3_15).perform();
			enterText(txtreporting_company_share3_15, "txtreporting_company_share3_15", data.get("Reporting company's share of equity (%)"));
			act.doubleClick(txtScope1tCO2eEmissions3_15).perform();
			enterText(txtScope1tCO2eEmissions3_15, "Scope 1 - (tCO2e) Emissions of investee company 3_15",
					data.get("Scope 1 - (tCO2e) Emissions of investee company"));
			act.doubleClick(txtScope2tCO2eEmissions3_15).perform();
			enterText(txtScope2tCO2eEmissions3_15, "Scope 2 - (tCO2e) Emissions of investee company 3_15",
					data.get("Scope 2 - (tCO2e) Emissions of investee company"));
			act.doubleClick(txtScope3tCO2eEmissions3_15).perform();
			enterText(txtScope3tCO2eEmissions3_15, "Scope 3 - (tCO2e) Emissions of investee company 3_15",
					data.get("Scope 3 - (tCO2e) Emissions of investee company"));
			
//			act.doubleClick(txtTotaltCO2eEmissions3_15).perform();
//			enterText(txtTotaltCO2eEmissions3_15, "Total (tCO2e) Emissions of investee company 3_15",
//					data.get("Total GHG Emissions - (tCO2e) of investee company"));
			act.doubleClick(totalEmissionCalc).build().perform();
			act.doubleClick(txtSourcs3_15).perform();
			sleep(2000);
			enterText(txtSourcs3_15, "Sourcs 3_15", data.get("Source of Emissions Data"));
			clickOn(btnSaveHotelStay, "Save Button Investments");
		} catch (Exception e) {
			clickOnCancelButtonBeforeAdding();
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	
		
		//public static String emissionsTotal;
		public void validateAllScopesTC02eValuesForInvestments() {              	//Naveen           
			try {
				sleep(2);
				Double total=0d;
				String[] tco2eValues = {"Scope 1 - (tCO2e) Emissions of investee company",
						                "Scope 2 - (tCO2e) Emissions of investee company",
						                "Scope 3 - (tCO2e) Emissions of investee company"};
				String[] actualEmission = {"Scope 1 Emissions (tCO2e)","Scope 2 Emissions (tCO2e)","3 Emissions (tCO2e)"};
				for(int i = 0 ; i<tco2eValues.length; i++) {
					Double allScopesEmissionValues;
					if(data.get("Value of Debt Investment").equals("10")) {
						allScopesEmissionValues =( Double.parseDouble(data.get("Value of Debt Investment")) 
								                   / Double.parseDouble(data.get("Total Project Cost")))
								                   * Double.parseDouble(data.get(tco2eValues[i]));
					}else {
					 allScopesEmissionValues = (Double.parseDouble(data.get(tco2eValues[i])) 
							                         * Double.parseDouble(data.get("Reporting company's share of equity (%)"))) / 100;
					}
					 total = total + allScopesEmissionValues;
					 ValCO2eRHP = total.toString();
					String Scopes = allScopesEmissionValues.toString();
					WebElement we = driver.findElement(By
							    .xpath("//span[contains(text(),'"+actualEmission[i]+"')]/parent::div/following-sibling::div"));
					if(we.getText().trim().equals(Scopes)) {
						passed("Successfully validated, "+actualEmission[i]+" Expected as "+Scopes+" "
								+ "and the actual as "+we.getText()+"");
					}else {
						Double doubleDiff= Double.parseDouble(Scopes) - Double.parseDouble(we.getText());
						if(doubleDiff<1 && doubleDiff>-0.4) {    
							passed("Succesfully validated, "+actualEmission[i]+" value" + Scopes + " And Actual is " 
						                                            + we.getText());
						}else {
							failed(driver,
									"failed to validate, "+actualEmission[i]+" Expected as "+Scopes+" "
									+ "and the actual as "+we.getText()+"");
						}	
					}
					
				}
				WebElement totalForEmissions = driver.findElement(By
						          .xpath("//span[text()='Total GHG Emissions (tCO2e)']/parent::div/following-sibling::div"));
				if(ValCO2eRHP.contains(totalForEmissions.getText())) {
					passed("Successfully validated, Total GHG Emissions (tCO2e) Expected as "+ValCO2eRHP+" "
							+ "and the actual as "+totalForEmissions.getText()+"");
				}else {
					Double doubleDiff= Double.parseDouble(ValCO2eRHP) - Double.parseDouble(totalForEmissions.getText());
					if(doubleDiff<1 && doubleDiff>-0.4) {    
						passed("Succesfully validated Total GHG Emissions (tCO2e) value" + ValCO2eRHP + " And Actual is " 
					                                            + totalForEmissions.getText());
					}else {
						failed(driver,
								"failed to validate, Total GHG Emissions (tCO2e) Expected as "+ValCO2eRHP+" "
								+ "and the actual as "+totalForEmissions.getText()+"");
					}	
				}
				
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
		}

	public void AddNewActivityScope3_15InvestmentsSpecificMethod() {
		try {
			sleep(2000);
			clickOn(btnbtnActivities, "Craeting Activities Transaction");
			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
			enterText(txtInvesteeCompanyScope3_15, "Investee Copmany text", data.get("Investee Company"));
			clickOn(drpInvesteeCompany, data.get("Investee Company Sector"));
			WebElement weinvesteeCompanyProject = driver
					.findElement(By.xpath("//li[text()='" + data.get("Investee Company Sector") + "']"));
			clickOn(weinvesteeCompanyProject, data.get("Investee Company Sector"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			enterText(txtreporting_company_share3_15, "txtreporting_company_share3_15", data.get("Reporting company"));
			enterText(txtScope1tCO2eEmissions3_15, "Scope 1 - (tCO2e) Emissions of investee company 3_15",
					data.get("Scope 1 - (tCO2e) Emissions of investee company"));
			enterText(txtScope2tCO2eEmissions3_15, "Scope 2 - (tCO2e) Emissions of investee company 3_15",
					data.get("Scope 2 - (tCO2e) Emissions of investee company"));
			enterText(txtScope3tCO2eEmissions3_15, "Scope 3 - (tCO2e) Emissions of investee company 3_15",
					data.get("Scope 3 - (tCO2e) Emissions of investee company"));
			enterText(txtTotaltCO2eEmissions3_15, "Total (tCO2e) Emissions of investee company 3_15",
					data.get("Total GHG Emissions - (tCO2e) of investee company"));
			enterText(txtSourcs3_15, "Sourcs 3_15", data.get("Source of Emissions"));
			clickOn(btnSaveHotelStay, "Save Button Hotel stay");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateActivityDetailsInViewActivityScope3_15Investments_InvestmentSpecificMethod() {
		try {
			sleep(1);
			System.out.println("-------Emission Details Are-------");
			String[] activityDetailFieldNames = { "Investee Company", "Investee Company Sector", "Reporting company",
					"Scope 1 - (tCO2e) Emissions of investee company",
					"Scope 2 - (tCO2e) Emissions of investee company",
					"Scope 3 - (tCO2e) Emissions of investee company",
					"Total GHG Emissions - (tCO2e) of investee company", "Source of Emissions","Start Date", "End Date" };
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				WebElement weActivityField = driver.findElement(By.xpath("//span[contains(text(),'"
						+ activityDetailFieldNames[j] + "')]//parent::div//following-sibling::div"));
				String ActivityField =weActivityField.getText();
				if(GHGCalculatorsPage.DoubleOrNot(ActivityField) == true) {
					 ActivityField = weActivityField.getText().split("\\.")[0].toString();
				}
				if (ActivityField.trim().equals(data.get(activityDetailFieldNames[j]).trim())) {
					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As "
							+ ActivityField);
				} else {
					failed(driver,
							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
									+ data.get(activityDetailFieldNames[j]) + "But Actual is"
									+ ActivityField);
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	// '''''''''''''''''''''''Investments Equity investments Average data
	// Method'''''''''''''''''''''''''''''''

	public void EditActivityScope3_15InvestmentsAverageDataMethod() {
		try {
			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
			Actions act = new Actions(driver);
			act.doubleClick(txtInvesteeCompanyScope3_15).perform();
			clearUntillTextFieldIsGettingCleared(txtInvesteeCompanyScope3_15);
			enterText(txtInvesteeCompanyScope3_15, "Investee Copmany text", data.get("Investee Company"));
			clickOn(drpInvesteeCompany, data.get("Investee Company Sector"));
			String weinvesteeCompanyProject = "//li[text()='" + data.get("Investee Company Sector") + "']";
			clickOnElementWithDynamicXpath(weinvesteeCompanyProject, data.get("Investee Company Sector"));
			clickOn(drpCEFAverageInvestments, "Custom emission factor");
			String weCEFAverageInvestments;
		    if(Boolean.parseBoolean(data.get("Activity CEF"))) {
		    	 weCEFAverageInvestments = "//li[text()='" + CustomEmissionFactorName + "']";
		    }else {
			 weCEFAverageInvestments = "//li[text()='" + data.get("Custom Emission Factor") + "']";
		    }
			clickOnElementWithDynamicXpath(weCEFAverageInvestments, data.get("Custom Emission Factor") + CustomEmissionFactorName);
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			act.doubleClick(txtRevenueOfInvesteeAverage).perform();
			enterText(txtRevenueOfInvesteeAverage, "txt RevenueOf Investee Average data Method",
					data.get("Investee Company's Revenue"));
			sleep(2000);
			act.doubleClick(txtReportingCompanyShareInvesteeAverage).perform();
			// Reporting company's share of equity (%)
			enterText(txtReportingCompanyShareInvesteeAverage, "txt Reporting CompanyShare Average Data method",
					data.get("Reporting company's share of equity (%)"));
			
			act.doubleClick(txtInvesteeCompanyShareInvesteeAverage).perform();
			// Investee company's revenue (%) from selected sector
			enterText(txtInvesteeCompanyShareInvesteeAverage, "txt Reporting CompanyShare Average Data method",
					data.get("Investee company's revenue (%) from selected sector"));
			sleep(1000);
			clickOn(btnSaveHotelStay, "Save Button Investments");
//    		jsClick(btnSaveHotelStay, "Save Button Investments");
		} catch (Exception e) {
			clickOnCancelButtonBeforeAdding();
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void AddNewActivityScope3_15InvestmentsAverageDataMethod() {
		try {
			clickOn(btnbtnActivities, "Craeting Activities Transaction");
			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
			enterText(txtInvesteeCompanyScope3_15, "Investee Copmany text", data.get("Investee Company"));
			clickOn(drpInvesteeCompany, data.get("Investee Company Sector"));
			WebElement weinvesteeCompanyProject = driver
					.findElement(By.xpath("//li[text()='" + data.get("Investee Company Sector") + "']"));
			clickOn(weinvesteeCompanyProject, data.get("Investee Company Sector"));
			clickOn(drpCEFAverageInvestments, data.get("Custom Emission Factor"));
			sleep(1000);
			WebElement weCEFAverageInvestments = driver
					.findElement(By.xpath("//li[text()='" + data.get("Custom Emission Factor") + "']"));
			sleep(1000);
			clickOn(weCEFAverageInvestments, data.get("Custom Emission Factor"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			enterText(txtRevenueOfInvesteeAverage, "txt RevenueOf Investee Average data Method",
					data.get("Investee Company's Revenue"));
			// Reporting company's share of equity (%)
			enterText(txtReportingCompanyShareInvesteeAverage, "txt Reporting CompanyShare Average Data method",
					data.get("Reporting company"));
			// Investee company's revenue (%) from selected sector
			enterText(txtInvesteeCompanyShareInvesteeAverage, "txt Reporting CompanyShare Average Data method",
					data.get("Investee company Revenue"));
			clickOn(btnSaveHotelStay, "Save Button Investments");
//    			jsClick(btnSaveHotelStay, "Save Button Investments");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	// xpaths
	public void ValidateActivityDetailsInViewActivityScope3_15Investments_InvestmentAverageDataMethod() {
		try {
			System.out.println("-------Emission Details Are-------");
			String[] activityDetailFieldNames = { "Investee Company", "Investee Company Sector",
					"Custom Emission Factor", "Investee Company's Revenue", "Units",
					"Investee company's revenue (%) from selected sector", "Reporting company",
					"Total GHG Emissions (tCO2e)", "Emission Factor","Start Date", "End Date" };
			// "Start Date", "End Date","Source",
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				WebElement weActivityField;
				if (activityDetailFieldNames[j].equals("Investee Company's Revenue")) {
					 weActivityField = driver.findElement(By.xpath(
							"(//span[contains(text(),'Investee Company')]//parent::div//following-sibling::div)[3]"));
				} else if (activityDetailFieldNames[j].equals("Emission Factor")) {
					 weActivityField = driver.findElement(By.xpath("(//span[contains(text(),'"
							+ activityDetailFieldNames[j] + "')]//parent::div//following-sibling::div)[2]"));
				} else if (activityDetailFieldNames[j].equals("Investee company's revenue (%) from selected sector")) {
					 weActivityField = driver.findElement(By
							.xpath("//span[contains(text(),'Investee company')]//parent::div//following-sibling::div"));
				} else {
					 weActivityField = driver.findElement(By.xpath("//span[contains(text(),'"
							+ activityDetailFieldNames[j] + "')]//parent::div//following-sibling::div"));
				}
				String strActivityField;
				if(activityDetailFieldNames[j].equals("Reporting company")) {
					 strActivityField = weActivityField.getText().split(".")[0];
				}else {
					strActivityField = weActivityField.getText();
				}
				if (strActivityField.trim().equals(data.get(activityDetailFieldNames[j]).trim())) {
						passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
								+ strActivityField);
					} else {
						failed(driver,
								"Failed To validate " + activityDetailFieldNames[j]
										+ " In Activity Details Expected As " + data.get(activityDetailFieldNames[j])
										+ "But Actual is" + strActivityField);
					}
				}
			
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

//	public void clickOnScope3_11InvestmentsDept_ProjectFinancing() {
//		clickOn(btnInvestmentsDebt_Project, "Click On Investment Debt and Project Financing Button GHG Calculator");
//	}
//
//	public void clickOnScope3_11InvestmentsAverageDataMethod() {
//		clickOn(btnInvestmentsAveargeMethod, "Click On Investment Average method Button GHG Calculator");
//	}

	// ----------------------------------------CEF------------------------------------
	public void clickOnScope3_11btnEquityInvestments() {
		clickOn(btnEquityInvestments, "Click On Investment btnEquityInvestments Button GHG Calculator");
	}

	public void clickOnCEFinCEFGrid() {
		sleep(3000);
		WebElement weActivity = driver
				.findElement(By.xpath("//div[text()='" + CustomEmissionFactorName + "']//parent::div[@role='row']"));
		clickOn(weActivity, "Activity of Facility" + CustomEmissionFactorName);
	}

	

	public void Add_CustomEmissionFactorInestments() {
		try {
			SelectDropdownOptionsForCalculatorActivityFields("Sector");
			commonFieldsForAddingCUSTOMEMISSIONFACTOR_ParameterInput("Source of Emission Factor", "true");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	Actions act = new Actions(driver);

	public void Edit_CustomEmissionFactorInestments() {
		try {
//					CustomEmissionFactorName = Constants.CEFName + generateRandomNumber(4);
			verifyIfElementPresent(lblEditCutsomEmissionFactorInvestee, "lblEditCutsomEmissionFactorInvestee",
					"lblEditCutsomEmissionFactorInvestee");
			act.doubleClick(txtCutsomEFName_1).perform();
			enterText(txtCutsomEFName_1, "entered Name of Custom EF", CustomEmissionFactorName);
			clickOn(drpSectorInvestment, data.get("Sector"));
			sleep(1000);
			WebElement weSector = driver.findElement(By.xpath("//li[text()='" + data.get("Sector") + "']"));
			clickOn(weSector, data.get("Sector"));		
			act.doubleClick(txtC02e).perform();
			enterText(txtC02e, "entered CO2e value", data.get("CO2e Edit"));
			clickOn(drpUnitCEFNumerator, "Click on Unit of Custom EF (Denominator) Drp");
			WebElement weUnitCEFNumerator = driver
					.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Numerator)") + "']"));
			clickOn(weUnitCEFNumerator, data.get("Unit of Custom EF (Numerator)"));
			act.doubleClick(txtSource).perform();
			enterText(txtSource, "entered Name of Custom EF", data.get("Source of Emission Factor"));
			//clickOn(drpUnitCEFDenominator, "Click on Unit of Custom EF (Denominator) Drp");
			WebElement CEFDenominator = driver.findElement(By.xpath("//input[@id='currency_code']"));
			clickOn(CEFDenominator, "CEF Denominator");
			enterText(CEFDenominator, "CEF Denominator", data.get("Unit of Custom EF (Denominator)"));
			WebElement weUnitCEFDenominator = driver
					.findElement(By.xpath("//li[text()='" + data.get("Unit of Custom EF (Denominator)") + "']"));
			clickOn(weUnitCEFDenominator, data.get("Unit of Custom EF (Denominator)"));
			clickOn(btnSaveParemeterInput, "Save Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateAddCEFDetailsInScope1CEF() {
		try {
			System.out.println("-------Emission Details Are-------");
			String[] activityDetailFieldNames = { "Sector", "Source of Emission Factor",
					"Unit of Custom EF (Denominator)", "CO2e", "Unit of Custom EF (Numerator)" };
			// Biofuel CO2
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				WebElement weActivityField = driver.findElement(By.xpath(
						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
				Thread.sleep(500);
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

	public void ValidateEditCEFDetailsInScope1CEF() {
		try {
			System.out.println("-------Emission Details Are-------");
			if (weEmissionDetails_CO2e.getText().trim().equals(data.get("CO2e Edit"))) {
				passed("Successfuuly Validated CO2e Emission Factor value on Activity RHP"
						+ weEmissionDetails_CO2e.getText().trim());
			} else {
				fail("Failed to  Validated CO2e Emission Factor value on Activity RHP Expected is "
						+ data.get("CO2e Edit") + "But Actual is " + weEmissionDetails_CO2e.getText().trim());
			}
			String[] activityDetailFieldNames = { "Sector", "Source of Emission Factor",
					"Unit of Custom EF (Denominator)", "Unit of Custom EF (Numerator)" };
			// Biofuel CO2
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				WebElement weActivityField = driver.findElement(By.xpath(
						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
				Thread.sleep(500);
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

	public void VerifyCEF_ActivityInInvestments() {
		try {
			clickOn(drpCustomEmiFactorActivity, "Click on of Custom EF ");
			enterText(txtvalActivityCEF1, "entered Name of Custom EF", CustomEmissionFactorName);
			sleep(500);
			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
			WebElement weCEFActivity = driver.findElement(By.xpath("//li[text()='" + CustomEmissionFactorName + "']"));
			clickOn(weCEFActivity, CustomEmissionFactorName);
			String CustomEmissionFactorNameActivity = txtvalActivityCEF1.getAttribute("value");
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

	public void Add_Activity_CustomEmissionFactorInvestmentsAveragedata() {
		try {
			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
			act.doubleClick(txtInvesteeCompanyScope3_15).perform();
			enterText(txtInvesteeCompanyScope3_15, "Investee Copmany text", data.get("Investee Company"));
			clickOn(drpInvesteeCompany, data.get("Investee Company Sector"));
			WebElement weinvesteeCompanyProject = driver
					.findElement(By.xpath("//li[text()='" + data.get("Investee Company Sector") + "']"));
			clickOn(weinvesteeCompanyProject, data.get("Investee Company Sector"));
			VerifyCEF_ActivityInInvestments();
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			act.doubleClick(txtRevenueOfInvesteeAverage).perform();
			enterText(txtRevenueOfInvesteeAverage, "txt RevenueOf Investee Average data Method",
					data.get("Investee Company's Revenue"));
			// Reporting company's share of equity (%)
			act.doubleClick(txtReportingCompanyShareInvesteeAverage).perform();
			enterText(txtReportingCompanyShareInvesteeAverage, "txt Reporting CompanyShare Average Data method",
					data.get("Reporting company"));
			// Investee company's revenue (%) from selected sector
			act.doubleClick(txtInvesteeCompanyShareInvesteeAverage).perform();
			enterText(txtInvesteeCompanyShareInvesteeAverage, "txt Reporting CompanyShare Average Data method",
					data.get("Investee company Revenue"));
			clickOn(btnSaveHotelStay, "Save Button Investments");
//		    			jsClick(btnSaveHotelStay, "Save Button Investments");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void VerifyCustomEmissionFactorActivitiesDetailsRHPGHGCalculator() {
		try {
			sleep(1000);
			WebElement weCEFNameRHP = driver.findElement(
					By.xpath("//span[text()='Custom Emission Factor']//parent::div//following-sibling::div"));
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

	public void VerifyCustomActivitiesDetailsRHPGHGCalculator() {
		try {
			WebElement CO2eActivity = driver.findElement(By.xpath(
					"//span[contains(text(),'Total GHG Emissions (tCO2e)')]//parent::div//following-sibling::div"));
			Double ValCO2e = Double.parseDouble(data.get("CO2e"))
					* Double.parseDouble(data.get("Investee Company's Revenue"))
					* Double.parseDouble(data.get("Investee company Revenue")) / 100
					* Double.parseDouble(data.get("Reporting company")) / 100 / 1000;
			String ValCO2eRHP1 = ValCO2e.toString();
			String ValCO2eRHP = approximateDecimalValueWithBigDecimal(ValCO2eRHP1);
			if (CO2eActivity.getText().equals(ValCO2eRHP)) {
				passed("Succesfully validated tCO2e value" + ValCO2eRHP + " And Actual is " + CO2eActivity.getText());
			} else {
				failed(driver,
						"Failed validated tCO2e value" + " " + ValCO2eRHP + " But Actual is " + CO2eActivity.getText());
			}
			WebElement EFActivity = driver.findElement(
					By.xpath("(//span[contains(text(),'Emission Factor')]//parent::div//following-sibling::div)[2]"));
			String s2 = EFActivity.getText().trim();
			String[] EFValueRHP = s2.split(" ");
			String tco2eCEFRHP = approximateDecimalValueWithBigDecimal(data.get("CO2e"));
			if (EFValueRHP[0].equals(tco2eCEFRHP)) {
				passed("Succesfully validated Emission factor value" + tco2eCEFRHP + " And Actual is " + EFValueRHP[0]);
			} else {
				failed(driver, "Failed validated Emission factor  value" + " " + tco2eCEFRHP + " But Actual is "
						+ EFValueRHP[0]);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void VerifyEditCustomActivitiesDetailsRHPGHGCalculator() {
		try {
			WebElement CO2eActivity = driver.findElement(By.xpath(
					"//span[contains(text(),'Total GHG Emissions (tCO2e)')]//parent::div//following-sibling::div"));
			Double ValCO2e = Double.parseDouble(data.get("CO2e Edit"))
					* Double.parseDouble(data.get("Investee Company's Revenue"))
					* Double.parseDouble(data.get("Investee company Revenue")) / 100
					* Double.parseDouble(data.get("Reporting company")) / 100 / 1000;
			String ValCO2eRHP1 = ValCO2e.toString();
			String ValCO2eRHP = approximateDecimalValueWithBigDecimal(ValCO2eRHP1);
			if (CO2eActivity.getText().equals(ValCO2eRHP)) {
				passed("Succesfully validated tCO2e value" + ValCO2eRHP + " And Actual is " + CO2eActivity.getText());
			} else {
				failed(driver,
						"Failed validated tCO2e value" + " " + ValCO2eRHP + " But Actual is " + CO2eActivity.getText());
			}
			WebElement EFActivity = driver.findElement(
					By.xpath("(//span[contains(text(),'Emission Factor')]//parent::div//following-sibling::div)[2]"));
			String s2 = EFActivity.getText().trim();
			String[] EFValueRHP = s2.split(" ");
			String tco2eCEFRHP = approximateDecimalValueWithBigDecimal(data.get("CO2e Edit"));
			if (EFValueRHP[0].equals(tco2eCEFRHP)) {
				passed("Succesfully validated Emission factor value" + tco2eCEFRHP + " And Actual is " + EFValueRHP[0]);
			} else {
				failed(driver, "Failed validated Emission factor  value" + " " + tco2eCEFRHP + " But Actual is "
						+ EFValueRHP[0]);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

//	public String approximateDecimalValueWithBigDecimal(String value) {
//		BigDecimal myBigDecimal = new BigDecimal(value);
//		BigDecimal newValue = myBigDecimal.setScale(4, RoundingMode.DOWN).stripTrailingZeros();
//		System.out.println(newValue);
//		return newValue.toPlainString();
//	}

	public void ValidateActivityDetailsInViewActivityScope3_15Investments_InvestmentAverageDataMethodCEF() {
		try {
			String s1;
			System.out.println("-------Emission Details Are-------");
			String[] activityDetailFieldNames = { "Investee Company", "Investee Company Sector",
					"Investee Company's Revenue", "Units", "Investee company's revenue (%) from selected sector",
					"Reporting company", "Emission Factor","Start Date", "End Date" };
			//,"Source",
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				if (activityDetailFieldNames[j].equals("Investee Company's Revenue")) {
					WebElement weActivityField = driver.findElement(By.xpath(
							"(//span[contains(text(),'Investee Company')]//parent::div//following-sibling::div)[3]"));
				} else if (activityDetailFieldNames[j].equals("Emission Factor")) {
					WebElement weActivityField = driver.findElement(By.xpath("(//span[contains(text(),'"
							+ activityDetailFieldNames[j] + "')]//parent::div//following-sibling::div)[2]"));
				} else if (activityDetailFieldNames[j].equals("Investee company's revenue (%) from selected sector")) {
					WebElement weActivityField = driver.findElement(By
							.xpath("//span[contains(text(),'Investee company')]//parent::div//following-sibling::div"));
				} else {
					WebElement weActivityField = driver.findElement(By.xpath("//span[contains(text(),'"
							+ activityDetailFieldNames[j] + "')]//parent::div//following-sibling::div"));
					if(activityDetailFieldNames[j].equals("Reporting company")) {
						 String s2=weActivityField.getText().trim().replaceAll(",", "");
						s1 =  GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(s2);
					} else {
						 s1=weActivityField.getText();
					}
					if (s1.trim().equals(data.get(activityDetailFieldNames[j]).trim())) {
						passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
								+ s1);
					} else {
						failed(driver,
								"Failed To validate " + activityDetailFieldNames[j]
										+ " In Activity Details Expected As " + data.get(activityDetailFieldNames[j])
										+ "But Actual is" + s1);
					}
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	// span[contains(text(),'Investee
	// company')]//parent::div//following-sibling::div
	// ''''''''''''''''''''''Debt and Project Financing Specific
	// Method'''''''''''''''''''''''''

	public void EditActivityScope3_15InvestmentsDept_ProjectFinancingSpecificMethod() {
		try {
			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
			Actions act = new Actions(driver);
			act.doubleClick(txtInvesteeCompanyScope3_15).perform();
			enterText(txtInvesteeCompanyScope3_15, "Investee Copmany text", data.get("Investee Company/Project"));
			clickOn(drpInvesteeCompanyDebtFinance, data.get("Investee Company/Project Sector"));
			WebElement weinvesteeCompanyProject = driver
					.findElement(By.xpath("//li[text()='" + data.get("Investee Company/Project Sector") + "']"));
			clickOn(weinvesteeCompanyProject, data.get("Investee Company/Project Sector"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			act.doubleClick(txtValueofDebtInvest).perform();
			enterText(txtValueofDebtInvest, "txtValueofDebtInvest", data.get("Value of Debt Investment"));
			act.doubleClick(txtTotalProjectCost).perform();
			enterText(txtTotalProjectCost, "txtValueofDebtInvest", data.get("Total Project Cost"));
			clickOn(drpUnitDebtInvest, "Unit of Investments Debt");
			WebElement weUnits = driver.findElement(By.xpath("//li[text()='" + data.get("Units") + "']"));
			clickOn(weUnits, data.get("Units"));
			act.doubleClick(txtScope1tCO2eEmissions3_15).perform();
			enterText(txtScope1tCO2eEmissions3_15, "Scope 1 - (tCO2e) Emissions of investee company 3_15",
					data.get("Scope 1 - (tCO2e) Emissions of investee company"));
			act.doubleClick(txtScope2tCO2eEmissions3_15).perform();
			enterText(txtScope2tCO2eEmissions3_15, "Scope 2 - (tCO2e) Emissions of investee company 3_15",
					data.get("Scope 2 - (tCO2e) Emissions of investee company"));
			act.doubleClick(txtScope3tCO2eEmissions3_15).perform();
			enterText(txtScope3tCO2eEmissions3_15, "Scope 3 - (tCO2e) Emissions of investee company 3_15",
					data.get("Scope 3 - (tCO2e) Emissions of investee company"));
			//act.doubleClick(txtTotaltCO2eEmissions3_15).perform();
			WebElement we = driver.findElement(By.xpath("//input[@id='scope3_co2e']/following-sibling::div//*[local-name()='svg']"));
			act.moveToElement(we).click().build().perform();
//			enterText(txtTotaltCO2eEmissions3_15, "Total (tCO2e) Emissions of investee company 3_15",
//					data.get("Total GHG Emissions - (tCO2e) of investee company"));
			act.doubleClick(txtSourceDebtInvest).perform();
			enterText(txtSourceDebtInvest, "Sourcs 3_15", data.get("Source of Emissions Data"));
			clickOn(btnSaveHotelStay, "Save Button Hotel stay");
		} catch (Exception e) {
			clickOnCancelButtonBeforeAdding();
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void AddNewActivityScope3_15InvestmentsDept_ProjectFinancingSpecificMethod() {
		try {
			clickOn(btnbtnActivities, "Craeting Activities Transaction");
			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
			enterText(txtInvesteeCompanyScope3_15, "Investee Copmany text", data.get("Investee Company/Project"));
			clickOn(drpInvesteeCompanyDebtFinance, data.get("Investee Company/Project Sector"));
			WebElement weinvesteeCompanyProject = driver
					.findElement(By.xpath("//li[text()='" + data.get("Investee Company/Project Sector") + "']"));
			clickOn(weinvesteeCompanyProject, data.get("Investee Company/Project Sector"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			enterText(txtValueofDebtInvest, "txtValueofDebtInvest", data.get("Value of Debt Investment"));
			sleep(1000);
			enterText(txtTotalProjectCost, "txtValueofDebtInvest", data.get("Total Project Cost"));
			clickOn(drpUnitDebtInvest, "Unit of Investments Debt");
			WebElement weUnits = driver.findElement(By.xpath("//li[text()='" + data.get("Units") + "']"));
			clickOn(weUnits, data.get("Units"));
			enterText(txtScope1tCO2eEmissions3_15, "Scope 1 - (tCO2e) Emissions of investee company 3_15",
					data.get("Scope 1 - (tCO2e) Emissions of investee company"));
			enterText(txtScope2tCO2eEmissions3_15, "Scope 2 - (tCO2e) Emissions of investee company 3_15",
					data.get("Scope 2 - (tCO2e) Emissions of investee company"));
			enterText(txtScope3tCO2eEmissions3_15, "Scope 3 - (tCO2e) Emissions of investee company 3_15",
					data.get("Scope 3 - (tCO2e) Emissions of investee company"));
			enterText(txtTotaltCO2eEmissions3_15, "Total (tCO2e) Emissions of investee company 3_15",
					data.get("Total GHG Emissions - (tCO2e) of investee company"));
			enterText(txtSourceDebtInvest, "Sourcs 3_15", data.get("Source of Emissions"));
			sleep(2000);
			clickOn(btnSaveHotelStay, "Save Button Hotel stay");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateActivityDetailsInViewActivityScope3_15InvestmentsDept_ProjectFinancingSpecificMethod() {
		try {
			System.out.println("-------Emission Details Are-------");
			String[] activityDetailFieldNames = { "Investee Company/Project", "Investee Company/Project Sector",
					"Value of Debt Investment", "Total Project Cost", "Units",
					"Scope 1 - (tCO2e) Emissions of investee company",
					"Scope 2 - (tCO2e) Emissions of investee company",
					"Scope 3 - (tCO2e) Emissions of investee company",
					"Total GHG Emissions - (tCO2e) of investee company",
					"Share of total project costs (percent)", "Source","Start Date", "End Date" };
			// "Start Date", "End Date","Scope 1 Emissions (tCO2e)",
			//"Scope 2 Emissions (tCO2e)", "Scope 3 Emissions (tCO2e)", "Total GHG Emissions (tCO2e)",
			// "Source of Emissions", ---- demo
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				WebElement weActivityField = driver.findElement(By.xpath("//span[contains(text(),'"
						+ activityDetailFieldNames[j] + "')]//parent::div//following-sibling::div"));
				String ActivityField;
				if(activityDetailFieldNames[j].contains("(tCO2e) ") 
						|| activityDetailFieldNames[j].equals("Value of Debt Investment")
						|| activityDetailFieldNames[j].equals("Total Project Cost")) {
					String Field = weActivityField.getText().replaceAll(",", "");
					String[] Field1 = Field.split("\\.");
					ActivityField = Field1[0];
				}else {
					 ActivityField = weActivityField.getText();
				}
				if (ActivityField.trim().equals(data.get(activityDetailFieldNames[j]).trim())) {
					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
							+ ActivityField);
				}else {
					failed(driver,
							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
									+ data.get(activityDetailFieldNames[j]) + "But Actual is"
									+ ActivityField);
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	// ''''''''''''''''''''''Debt and Project Financing Average Data
	// Method'''''''''''''''''''''''''

	public void EditActivityScope3_15InvestmentsDebtProjectFinancingAverageDataMethod() {
		try {
			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
			Actions act = new Actions(driver);
			act.doubleClick(txtInvesteeCompanyScope3_15).perform();
			tctProjectPhase.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			tctProjectPhase.sendKeys(Keys.BACK_SPACE);
			enterText(tctProjectPhase, "txt Project Phase Debt Average", data.get("Project Phase"));
			enterText(txtInvesteeCompanyScope3_15, "Investee Copmany text", data.get("Investee Company/Project"));
			enterText(txtInvesteeCompanyScope3_15, "Investee Copmany text", data.get("Investee Company/Project"));
			clickOn(drpInvesteeCompanyDebtFinance, data.get("Investee Company/Project Sector"));
			String weinvesteeCompanyProject = "//li[text()='" + data.get("Investee Company/Project Sector") + "']";
			clickOnElementWithDynamicXpath(weinvesteeCompanyProject, data.get("Investee Company/Project Sector"));
			//CEF Name
			clickOn(drpCEFDebtAverage, data.get("Custom Emission Factor") + CustomEmissionFactorName);//CustomEmissionFactorName
			String strCEF;
			if(Boolean.parseBoolean(data.get("Activity CEF"))) {
				 strCEF = CustomEmissionFactorName;
			}else {
				 strCEF = data.get("Custom Emission Factor");
			}
			enterText(txtvalActivityCEF1, "CEF  text", strCEF);
			String weCEFAverageInvestments = "//li[text()='" + strCEF + "']";
			clickOnElementWithDynamicXpath(weCEFAverageInvestments, strCEF);
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			act.doubleClick(tctProjectPhase).perform();
			act.doubleClick(txtValueofDebt).perform();
			enterText(txtValueofDebt, "txt Value of Debt Average Data method", data.get("Value of Debt Investment"));
			act.doubleClick(txtProjectCost).perform();
			enterText(txtProjectCost, "txt Project Cost Debt Average Data method", data.get("Total Project Cost"));
			clickOn(btnSaveHotelStay, "Save Button Investments");
		} catch (Exception e) {
			clickOnCancelButtonBeforeAdding();
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void AddNewActivityScope3_15InvestmentsDebtProjectFinancingAverageDataMethod() {
		try {
			sleep(1000);
			clickOn(btnbtnActivities, "Craeting Activities Transaction");
			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
			enterText(txtInvesteeCompanyScope3_15, "Investee Copmany text", data.get("Investee Company/Project"));
			clickOn(drpInvesteeCompanyDebtFinance, data.get("Investee Company/Project Sector"));
			sleep(1000);
			WebElement weinvesteeCompanyProject = driver
					.findElement(By.xpath("//li[text()='" + data.get("Investee Company/Project Sector") + "']"));
			sleep(1000);
			clickOn(weinvesteeCompanyProject, data.get("Investee Company/Project Sector"));
			clickOn(drpCEFDebtAverage, data.get("Custom Emission Factor"));
			sleep(1000);
			WebElement weCEFAverageInvestments = driver
					.findElement(By.xpath("//li[text()='" + data.get("Custom Emission Factor") + "']"));
			sleep(500);
			clickOn(weCEFAverageInvestments, data.get("Custom Emission Factor"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			enterText(tctProjectPhase, "txt Project Phase Debt Average", data.get("Project Phase"));
			enterText(txtValueofDebt, "txt Value of Debt Average Data method", data.get("Value of Debt Investment"));
			enterText(txtProjectCost, "txt Project Cost Debt Average Data method", data.get("Total Project Cost"));
			clickOn(btnSaveHotelStay, "Save Button Investments");
//        	  		jsClick(btnSaveHotelStay, "Save Button Investments");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateActivityDetailsInViewActivityScope3_15InvestmentsDebtProjectFinancingAverageDataMethod() {
		try {
			System.out.println("-------Emission Details Are-------");
			String[] activityDetailFieldNames = { "Investee Company/Project", "Investee Company/Project Sector",
					"Project Phase", "Custom Emission Factor", "Value of Debt Investment", "Units",
					"Total Project Cost", "Share of total project costs (percent)", "Total GHG Emissions (tCO2e)",
					"Source", "Emission Factor","Start Date", "End Date"};
			// "Start Date", "End Date",
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				WebElement weActivityField;
				if (activityDetailFieldNames[j].equals("Emission Factor")) {
					 weActivityField = driver.findElement(By.xpath("(//span[contains(text(),'"
							+ activityDetailFieldNames[j] + "')]//parent::div//following-sibling::div)[2]"));
				} else {
					 weActivityField = driver.findElement(By.xpath("//span[contains(text(),'"
							+ activityDetailFieldNames[j] + "')]//parent::div//following-sibling::div"));
				}
				String ActivityField;
				if(activityDetailFieldNames[j].contains("Value of Debt Investment") 
						                      || activityDetailFieldNames[j].contains("Total Project Cost")){
					String Field = weActivityField.getText().replaceAll(",", "");
					String[] Field1 = Field.split("\\.");
					ActivityField = Field1[0];
				}else {
					 ActivityField = weActivityField.getText();
				}
					if (ActivityField.trim().equals(data.get(activityDetailFieldNames[j]).trim())) {
						passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
								+ ActivityField);
					} else {
						failed(driver,
								"Failed To validate " + activityDetailFieldNames[j]
										+ " In Activity Details Expected As " + data.get(activityDetailFieldNames[j])
										+ "But Actual is" + ActivityField);
					}
				}
			
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	// --------------------------CEF------------------------------------------------

	public void VerifyCEF_ActivityInInvestmentsDebtProjectFinancing() {
		try {
			clickOn(drpCustomEF, "Click on of Custom EF ");
			enterText(txtvalActivityCEF1, "entered Name of Custom EF", CustomEmissionFactorName);
			sleep(500);
			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
			WebElement weCEFActivity = driver.findElement(By.xpath("//li[text()='" + CustomEmissionFactorName + "']"));
			clickOn(weCEFActivity, CustomEmissionFactorName);
			String CustomEmissionFactorNameActivity = txtvalActivityCEF1.getAttribute("value");
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

	public void Add_Activity_CustomEmissionFactorDebtProjectFinancingInvestmentsAveragedata() {
		try {
			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
			enterText(tctProjectPhase, "txt Project Phase Debt Average", data.get("Project Phase"));
			enterText(txtInvesteeCompanyScope3_15, "Investee Copmany text", data.get("Investee Company/Project"));
			clickOn(drpInvesteeCompanyDebtFinance, data.get("Investee Company/Project Sector"));
			WebElement weinvesteeCompanyProject = driver
					.findElement(By.xpath("//li[text()='" + data.get("Investee Company/Project Sector") + "']"));
			clickOn(weinvesteeCompanyProject, data.get("Investee Company/Project Sector"));
			VerifyCEF_ActivityInInvestmentsDebtProjectFinancing();
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			enterText(txtValueofDebt, "txt Value of Debt Average Data method", data.get("Value of Debt Investment"));
			enterText(txtProjectCost, "txt Project Cost Debt Average Data method", data.get("Total Project Cost"));
			clickOn(btnSave, "Save Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void ValidateActivityDetailsInViewActivityScope3_15InvestmentsDebtProjectFinancingAverageDataMethodCEF() {
		try {
			String s1;
			System.out.println("-------Emission Details Are-------");
			String[] activityDetailFieldNames = { "Investee Company/Project", "Investee Company/Project Sector",
					"Project Phase", "Value of Debt Investment", "Units", "Total Project Cost", "Source",
					"Start Date", "End Date" };
			// "Share of total project costs (percent)"
			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
			for (int j = 0; j < activityDetailFieldNames.length; j++) {
				if (activityDetailFieldNames[j].equals("Emission Factor")) {
					WebElement weActivityField = driver.findElement(By.xpath("(//span[contains(text(),'"
							+ activityDetailFieldNames[j] + "')]//parent::div//following-sibling::div)[2]"));
				} else {
					WebElement weActivityField = driver.findElement(By.xpath("//span[contains(text(),'"
							+ activityDetailFieldNames[j] + "')]//parent::div//following-sibling::div"));
					if(activityDetailFieldNames[j].equals("Total Project Cost")) {
						 String s2=weActivityField.getText().trim().replaceAll(",", "");
						s1 =  GHGCalculatorsPage.approximateDecimalValueWithBigDecimal(s2);
					} else {
						 s1=weActivityField.getText();
					}
					if (s1.trim().equals(data.get(activityDetailFieldNames[j]).trim())) {
						passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
								+ s1);
					} else {
						failed(driver,
								"Failed To validate " + activityDetailFieldNames[j]
										+ " In Activity Details Expected As " + data.get(activityDetailFieldNames[j])
										+ "But Actual is" + s1);
					}
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void VerifyCustomActivitiesDetailsRHPGHGCalculatorDebtProjectAverageData() {
		try {
			WebElement CO2eActivity = driver.findElement(By.xpath(
					"//span[contains(text(),'Total GHG Emissions (tCO2e)')]//parent::div//following-sibling::div"));
			Double ValCO2e = Double.parseDouble(data.get("CO2e"))
					* Double.parseDouble(data.get("Value of Debt Investment")) / 1000;
			String ValCO2eRHP1 = ValCO2e.toString();
			String ValCO2eRHP = approximateDecimalValueWithBigDecimal(ValCO2eRHP1);
			if (CO2eActivity.getText().equals(ValCO2eRHP)) {
				passed("Succesfully validated tCO2e value" + ValCO2eRHP + " And Actual is " + CO2eActivity.getText());
			} else {
				failed(driver,
						"Failed validated tCO2e value" + " " + ValCO2eRHP + " But Actual is " + CO2eActivity.getText());
			}
			WebElement EFActivity = driver.findElement(
					By.xpath("(//span[contains(text(),'Emission Factor')]//parent::div//following-sibling::div)[2]"));
			String s2 = EFActivity.getText().trim();
			String[] EFValueRHP = s2.split(" ");
			String tco2eCEFRHP = approximateDecimalValueWithBigDecimal(data.get("CO2e"));
			if (EFValueRHP[0].equals(tco2eCEFRHP)) {
				passed("Succesfully validated Emission factor value" + tco2eCEFRHP + " And Actual is " + EFValueRHP[0]);
			} else {
				failed(driver, "Failed validated Emission factor  value" + " " + tco2eCEFRHP + " But Actual is "
						+ EFValueRHP[0]);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void VerifyEditCustomActivitiesDetailsRHPGHGCalculatorDebtProjectAverageData() {
		try {
			WebElement CO2eActivity = driver.findElement(By.xpath(
					"//span[contains(text(),'Total GHG Emissions (tCO2e)')]//parent::div//following-sibling::div"));
			Double ValCO2e = Double.parseDouble(data.get("CO2e Edit"))
					* Double.parseDouble(data.get("Value of Debt Investment")) / 1000;
			String ValCO2eRHP1 = ValCO2e.toString();
			String ValCO2eRHP = approximateDecimalValueWithBigDecimal(ValCO2eRHP1);
			if (CO2eActivity.getText().equals(ValCO2eRHP)) {
				passed("Succesfully validated tCO2e value" + ValCO2eRHP + " And Actual is " + CO2eActivity.getText());
			} else {
				failed(driver,
						"Failed validated tCO2e value" + " " + ValCO2eRHP + " But Actual is " + CO2eActivity.getText());
			}
			WebElement EFActivity = driver.findElement(
					By.xpath("(//span[contains(text(),'Emission Factor')]//parent::div//following-sibling::div)[2]"));
			String s2 = EFActivity.getText().trim();
			String[] EFValueRHP = s2.split(" ");
			String tco2eCEFRHP = approximateDecimalValueWithBigDecimal(data.get("CO2e Edit"));
			if (EFValueRHP[0].equals(tco2eCEFRHP)) {
				passed("Succesfully validated Emission factor value" + tco2eCEFRHP + " And Actual is " + EFValueRHP[0]);
			} else {
				failed(driver, "Failed validated Emission factor  value" + " " + tco2eCEFRHP + " But Actual is "
						+ EFValueRHP[0]);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	
	public void TCO2eValidationForAverageDataMethodsForInvestments(	String calcName) {
		try {
			Double tco2e;
			if(calcName.equals("InvestmentsAveraggeDataMethod")) {
			 tco2e = Double.parseDouble(data.get("Investee company's revenue (%) from selected sector")) /100
					       *Double.parseDouble(data.get("Reporting company's share of equity (%)")) / 100
					       *Double.parseDouble(data.get("Investee Company's Revenue"))
					       / 1000;
			}else {
				 tco2e = Double.parseDouble(data.get("Value of Debt Investment"))
						 *Double.parseDouble(data.get("Total Project Cost"))/1000;
			}
			ValCO2eRHP = approximateDecimalValueWithBigDecimalWitgDecimal(tco2e.toString(),10);
			String totalGHGEmissionstCO2e = "//span[text()='Total GHG Emissions (tCO2e)']/parent::div/following-sibling::div";
			WebElement weTotalGHGEmissionstCO2e = driver.findElement(By.xpath(totalGHGEmissionstCO2e));
			if(ValCO2eRHP.trim().equals(weTotalGHGEmissionstCO2e.getText().replaceAll(",",""))) {
				passed("Succesfully validated tCO2e value " + ValCO2eRHP + " And Actual is " + weTotalGHGEmissionstCO2e.getText().replaceAll(",",""));
			} else {
				Double doubleDiff= Double.parseDouble(ValCO2eRHP) - Double.parseDouble(weTotalGHGEmissionstCO2e.getText().replaceAll(",",""));
				if(doubleDiff<1 && doubleDiff>-1) {    
					passed("Succesfully validated tCO2e value" + ValCO2eRHP + " And Actual is " + weTotalGHGEmissionstCO2e.getText().replaceAll(",",""));
				}else {
				    failed(driver,
						    "Failed validated tCO2e value" + " " + ValCO2eRHP + " But Actual is " + weTotalGHGEmissionstCO2e.getText().replaceAll(",",""));
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
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
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
}
