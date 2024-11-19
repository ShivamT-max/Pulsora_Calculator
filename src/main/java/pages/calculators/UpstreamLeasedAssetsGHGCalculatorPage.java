package pages.calculators;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pages.GHGCalculatorsPage;
import utilities.Constants;
import utilities.Data;

public class UpstreamLeasedAssetsGHGCalculatorPage extends GHGCalculatorsPage {
	public UpstreamLeasedAssetsGHGCalculatorPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	public void EditActivityScope3_8UpstreamLeasedAssets() {
		try {
			ReusabilityForFacilityNameAndStartDateAndEndDateForCalculators();
			entertTextInTextFieldForCalculators_ActivityDetails("Leased Asset");
			if(!Boolean.parseBoolean(data.get("Activity CEF"))) {
				SelectDropdownOptionsForCalculatorActivityFields("Energy Category");
				SelectDropdownOptionsForCalculatorActivityFields("Type");
			}
			ReusabilityForAmount$Units$Tags$Notes$SaveForCalculators("Activity Amount", "Units", "False", "Description");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	public void validateTCO2EValueForUpstreamLeasedAsset(String Amount,String[] units,String[] conversions) {
		 try {
			 sleep(1);
			Double d;
			ArrayList<String> masses = new ArrayList<String>();
			ArrayList<String> volumes = new ArrayList<String>();
			for(int i =0;i<Constants.unitNamesVolume.length;i++) {
				volumes.add(Constants.unitNamesVolume[i]);
			}
			for(int i =0;i<Constants.unitNamesMass.length;i++) {
				masses.add(Constants.unitNamesMass[i]);
			}
			HashMap<String,String> conversionUnitValues = new HashMap();
			 for(int i = 0; i<units.length; i++) {
				 conversionUnitValues.put(units[i],conversions[i]);
			 }
			WebElement cefValueRHP = driver
					.findElement(By.xpath("//span[contains(text(),'CO2e')]//parent::p//following-sibling::p/div"));
			WebElement EFActivity = driver.findElement(
					By.xpath("(//span[contains(text(),'Emission Factor')]//parent::p//following-sibling::p)[2]"));
			String s2 = EFActivity.getText().trim().replaceAll(",", "");
			String[] EFValueRHP = s2.split(" ");
			String EFValueActual = EFValueRHP[0];
			if(conversionUnitValues.containsKey(data.get("Units"))) {
				if(EFActivity.getText().replaceAll(",", "").contains("/kWh")) {
					System.out.println(conversionUnitValues.get(data.get("Units")));
						if(data.get("Units").contains("(kWh)")) {
							d =Double.parseDouble(EFValueActual)
									   * Double.parseDouble(Amount) 
									   /// Double.parseDouble(conversionUnitValues.get(data.get("Units"))) * 0.003412 
									   / 1000;
						}else {
					        d =Double.parseDouble(EFValueActual) 
							   * Double.parseDouble(Amount) 
							   / Double.parseDouble(conversionUnitValues.get(data.get("Units")))* 0.003412 
							   / 1000;
						}
			    }else if(EFActivity.getText().replaceAll(",", "").contains("/kg")) {
			    	 d = Double.parseDouble(EFValueActual) 
							   * Double.parseDouble(Amount) 
							   // / Double.parseDouble(conversionUnitValues.get(data.get("Unit"))) * 0.003412
							   / 1000;
			    }else {
			    	d = (Double.parseDouble(EFValueActual) / Double.parseDouble(conversionUnitValues.get(data.get("Units"))))
							   * Double.parseDouble(Amount)  
							   / 1000;
			    }
				String expTCO2eULA1 = d.toString();
				ValCO2eRHP = approximateDecimalValueWithBigDecimal(expTCO2eULA1);
				if(ValCO2eRHP.equals(cefValueRHP.getText().replaceAll(",", ""))) {
					passed("Successfully validated, Tco2e expected value is "+ValCO2eRHP+" and the "
							+ "Actual as "+cefValueRHP.getText().replaceAll(",", "")+"");
				}else {
					Double doubleDiff= Double.parseDouble(ValCO2eRHP) - Double.parseDouble(cefValueRHP.getText().replaceAll(",", ""));
					info(""+doubleDiff+"");
					if(doubleDiff<1 && doubleDiff>-0.4) {    
						passed("successfully validated, Tco2e expected value is "+ValCO2eRHP+" and the "
										+ "Actual as "+cefValueRHP.getText().replaceAll(",", "")+"");
					}else {
						failed(driver,
								"failed to validate, Tco2e expected value is "+ValCO2eRHP+" and the "
										+ "Actual as "+cefValueRHP.getText().replaceAll(",", "")+"");
					}	
				 }
				}
			
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
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
}

