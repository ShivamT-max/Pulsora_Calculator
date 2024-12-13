package pages.calculators;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

public class UseOfSoldProductGHGCalculatorPage extends GHGCalculatorsPage {
	public UseOfSoldProductGHGCalculatorPage(WebDriver driver2, Data data) {
		super(driver2, data);
		// TODO Auto-generated constructor stub
	}
	
	

	public void EditActivityScope3_11UseofSoldProdutcs() {
		try {
			ReusabilityForFacilityNameAndStartDateAndEndDateForCalculators();
			entertTextInTextFieldForCalculators_ActivityDetails("Description");
			if(!Boolean.parseBoolean(data.get("Activity CEF"))) {
				//SelectDropdownOptionsForCalculatorActivityFields("Sold Product");
				selectDropDownOptionsForCalculatorActivityFieldsForUOSP();
				
			}
			entertTextInTextFieldForCalculators_ActivityDetails("Number of Units Sold");
			clickOn(btnSaveHotelStay, "Save Button Processing of Sold Products");
		} catch (Exception e) {
			clickOnCancelButtonBeforeAdding();
			failed(driver, "Exception caught " + e.getMessage());
		}
	}


	public void Add_CustomEmissionFactorUseofSoldProduct() {
		try {
			CustomEmissionFactorName = Constants.CEFName + generateRandomNumber(4);
			verifyIfElementPresent(lblAddCutsomEmissionFactor, "lblAddCutsomEmissionFactor",
					"lblAddCutsomEmissionFactor");
			clearUntillTextFieldIsGettingCleared(txtCutsomEFName_1);
			enterText(txtCutsomEFName_1, "entered Name of Custom EF", CustomEmissionFactorName);
			clearUntillTextFieldIsGettingCleared(txtSoldProductUSP);
			enterText(txtSoldProductUSP, "entered Sold Product Custom EF", data.get("Sold Product"));
			clearUntillTextFieldIsGettingCleared(txtProductLifeTime);
			enterText(txtProductLifeTime, "entered Product Life Time Custom EF", data.get("Product Life Time (Years)"));
			clearUntillTextFieldIsGettingCleared(txtC02e);
			enterText(txtC02e, "entered CO2e value", data.get("CO2e (Yearly)"));
			clickOn(drpUnitsCO2e, data.get("Units of CO2e"));
			String weCO2eUnit = "//li[text()='" + data.get("Units of CO2e") + "']";
			clickOnElementWithDynamicXpath(weCO2eUnit, data.get("Units of CO2e"));
			clearUntillTextFieldIsGettingCleared(txtSourceCEFUOSL);
			enterText(txtSourceCEFUOSL, "entered txt Source", data.get("Source"));
			clickOn(btnSaveParemeterInput, "Save Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}


	
	public static String expectedElectricityComsuption;
	public static String lifeTimeUOSP;
	public void getElectricityComsuptionConfigurationValueInProductSpecifications() {
		try {
			String electricityCom = "(//div[text()='"+data.get("Sold Product")+"']/following-sibling::div/following-sibling::div)[1]";
			System.out.println(WaitForElementWithDynamicXpath(electricityCom));
			WebElement weElectricityCom = driver.findElement(By.xpath(electricityCom));
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", weElectricityCom);
			expectedElectricityComsuption = weElectricityCom.getText();
			WebElement weElectricitylifetime=driver.findElement(By.xpath("//div[text()='AC Electricity']/following-sibling::div[@col-id='product_life_time']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", weElectricitylifetime);
			lifeTimeUOSP=weElectricitylifetime.getText();
		
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	
	public void clickOnActivityBasedMethodInUseOfSoldProducts() {
		try {
			WebElement activityBased = driver.findElement(By
					         .xpath("//button[text()='Activity Based Method']"));
			clickOn(activityBased, "Activity Based Method");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void validateEmissionFactorUsingElectricityComsuptionForUseOfSold() {
		try {
			sleep(1);
			WebElement actualEmissionFactor1 = driver.findElement(By
					           .xpath("//span[text()='Emission Factor']/parent::p/following-sibling::p/div"));
			WebElement electricityConvert = driver.findElement(By
					       .xpath("//span[text()='Electricity:']/parent::p/following-sibling::p/div"));
			String electricityConSplit =electricityConvert.getText().split("\\-")[1].split(" ")[0];  
			Double expectedEmission = Double.parseDouble(electricityConSplit) 
					                              * Double.parseDouble(expectedElectricityComsuption);//  /1000; //* 0.454;	   
			String expectedEmissionFactor1 = expectedEmission.toString();
			String expectedEmissionFactor = approximateDecimalValueWithBigDecimal(expectedEmissionFactor1);
			String actualEmissionFactor = actualEmissionFactor1.getText().split(" ")[0];
			if(actualEmissionFactor.equals(expectedEmissionFactor)) {
				passed("Successfully validated, Emission Factor expected value is "+expectedEmissionFactor+" "
						+ "and the actual as "+actualEmissionFactor+"");
			}else {
				failed(driver,
						"failed to validate, Emission Factor expected value is "+expectedEmissionFactor+" "
						+ "and the actual as "+actualEmissionFactor+"");
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
