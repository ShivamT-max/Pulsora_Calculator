package pages;

//import java.math.BigDecimal;
import java.math.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle.Control;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;
import pages.calculators.BusinessTravelCalculatorPage;
import pages.calculators.CapitalGoodsGHGCalculatorPage;
import pages.calculators.DirectEmissionsCalculatorPage;
import pages.calculators.DownStreamLeasedAssetsGHGCalculatorPage;
import pages.calculators.DownstreamTransportationandDistributionCalculatorPage;
import pages.calculators.EmployeeCommutingCalculatorPage;
import pages.calculators.EndOfLifeTreatmentGHGCalculatorPage;
import pages.calculators.FranchisesCalculatorPage;
import pages.calculators.FuelandEnergyRelatedActivitiesCalculatorPage;
import pages.calculators.HomeOfficeTelecommutingCalculatorPage;
import pages.calculators.HotelStayGHGCalculatorPage;
import pages.calculators.IndirectEmissionsCalculatorPage;
import pages.calculators.InvestmentsGHGCalculatorsPage;
import pages.calculators.ProcessingofSoldProductsCalculatorPage;
import pages.calculators.PurchasedGoodsAndServicesGHGCalculatorPage;
import pages.calculators.UpstreamLeasedAssetsGHGCalculatorPage;
import pages.calculators.UpstreamTransportationandDistributionCalculatorPage;
import pages.calculators.UseOfSoldProductGHGCalculatorPage;
import pages.calculators.WasteGeneratedInOperationsCalculatorPage;
import uielements.CEFPage;
import uielements.CalculatorElements;
import utilities.Constants;
import utilities.Data;
import utilities.ExcelWriter;
import utilities.GlobalKeys;
import utilities.GlobalVariables;
import utilities.MultiDataExcelReader;

public class GHGCalculatorsPage extends CalculatorElements {
	
	private HomePage homePage;
	private MenuBarPage menuBarPage;
	private InvestmentsGHGCalculatorsPage investmentsGHGCalculatorsPage;
	private PurchasedGoodsAndServicesGHGCalculatorPage PGS;
	private CapitalGoodsGHGCalculatorPage CGS;
	private HotelStayGHGCalculatorPage hotelStay;
	private ProcessingofSoldProductsCalculatorPage POSP;
	private UseOfSoldProductGHGCalculatorPage UOSP;
	private EndOfLifeTreatmentGHGCalculatorPage EOLT;
	private InvestmentsGHGCalculatorsPage Investments;
	private DownStreamLeasedAssetsGHGCalculatorPage DLA;
	private UpstreamLeasedAssetsGHGCalculatorPage ULA;
	private DirectEmissionsCalculatorPage directEmissionsCalculatorPage;
	
	
	public GHGCalculatorsPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}
	Actions action = new Actions(driver);
	@FindBy(xpath = "//div[text()='Tiffany']")
	private WebElement lblFacility;
	
	public void selectFacilityFromOrgViewScreen(String facilityName) {
		try {
			WebElement viewOrgScreen = driver.findElement(By.xpath("//article[@aria-label='View Organization Data']"));
			if (viewOrgScreen.isEnabled()) {
				clickOnfilterOrgLabels(data.get("navigationType"));
				waitForElement(lblFacility);
				clickOn(lblFacility, " ");
				//		WebElement lblFacilityName = driver.findElement(By.xpath("//div[text()='"+data.get("Facility Name")+"']"));
				//		waitForElement(lblFacilityName);
				// clickOn(lblFacility, "clicked on facilitiy name: " + lblFacility.getText());
				sleep(4000);
				WebElement facilityFromOrgViewScreen = driver
						.findElement(By.xpath("//ul[@role='group']//div//li//div[text()='" + facilityName + "']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
						facilityFromOrgViewScreen);
				waitForElement(facilityFromOrgViewScreen);
				clickOn(facilityFromOrgViewScreen, "selected facilitiy from view prg screen: " + facilityName);
			}else {
				System.out.println("There is no view organization Data ");
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clickOnfilterOrgLabels(String navigationType) {
		try {
			WebElement orgHierarchyTyep = driver
					.findElement(By.xpath("//span[text()='" + Constants.navigationType + "']/parent::div"));
			if (orgHierarchyTyep.getAttribute("class").contains("false")) {
				WebElement orgorgHierarchyTyep1 = driver
						.findElement(By.xpath("//span[text()='" + Constants.navigationType + "']"));
				clickOn(orgorgHierarchyTyep1, "Clicked on " + Constants.navigationType);
			}
		} catch (Exception e) {
			failed(driver, "Exceptio caught " + e.getMessage());
		}
	}
	
	public void clickOnActivityInActivitiesGridMultipleTiffany() {
		try {
			sleep(3000);
			
			
			WebElement weActivity = driver
					.findElement(By.xpath("(//div[@role='gridcell']//following::div[@col-id='facility_name']//span[text()='"
							+ data.get("Facility Name") + "'])"));
			clickOn(weActivity, "Activity of Facility" + data.get("Facility Name"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void verifyAddLabelRHP() {
		verifyIfElementPresent(lblAddActivityIndirect, "lblAddActivity", "add activity ");
	}
	
	public void clickOnAddedActivity() {
		try {
			sleep(4000);
			try {
				WebElement viewOrgScreen = driver.findElement(By.xpath("//article[@aria-label='View Organization Data']"));
				WebElement weActivity = driver
						.findElement(By.xpath("(//*[text()='" + data.get("Facility Name") + "']//parent::div)[3]"));
				waitForElement(weActivity);
				clickOn(weActivity, "Activity of Facility" + data.get("Facility Name"));
			}catch (Exception e) {
				
				if(data.get("Calc Name").equals("Home Office")) {
					WebElement weActivity = driver
							.findElement(By.xpath("//*[text()='" + data.get("Facility / Location") + "']//parent::div"));
					waitForElement(weActivity);
					clickOn(weActivity, "Activity of Facility" + data.get("Facility Name"));
				}else {
					WebElement weActivity = driver
							.findElement(By.xpath("//*[text()='" + data.get("Facility Name") + "']//parent::div"));
					waitForElement(weActivity);
					clickOn(weActivity, "Activity of Facility" + data.get("Facility Name"));
				}
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FindBy(xpath = "//button[contains(text(),'Browse Files')]")
	private WebElement browfil;

	public void VerifyEvidence() {
		try {sleep(1000);
			WebElement evid = driver.findElement(By.xpath(
					"//*[text()='Evidence']//parent::div//following-sibling::div//*[@data-testid='ExpandMoreIcon']"));
			clickOnElement(evid, "Evidence");
			sleep(2000);
			WebElement plicon = driver.findElement(By.xpath("//*[@data-testid='AddOutlinedIcon']"));
			clickOnElement(plicon, "Plus icon Evidence");
			uploadEvidenceDynamicallyInCatalogMetricRHP();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	@FindBy(xpath = "//button[text()='Browse Files']")
	private WebElement btnBrowseFiles;
	@FindBy(xpath = "(//button[text()='Upload'])[2]")
	private WebElement btnUpload;
	@FindBy(xpath = "//button[text()='Upload']//parent::div//following-sibling::div/button[text()='Close']")
	private WebElement btnDoneUploadFile;
	@FindBy(xpath = "(//button[contains(text(),'Close')])[2]")
	private WebElement btnclose;
	String sharedEvidenceFileName;
	String date1;
	String res;

	public void uploadEvidenceDynamicallyInCatalogMetricRHP() {
		try {
			Actions act = new Actions(driver);
			waitForElement(btnBrowseFiles);
			System.out.println(waitForElement(btnBrowseFiles));
			act.moveToElement(btnBrowseFiles).doubleClick().build().perform();
			//jsClick(btnBrowseFiles, "Browse Files");
			// clickOnElement(btnBrowseFiles, "Browse Files");
			String path = System.getProperty("user.dir");
			sharedEvidenceFileName = "evidence.txt";
			String filePath = path + "\\src\\test\\resources\\Calculator\\" + sharedEvidenceFileName + "";
//			File f;
//			f = new File(filePath);
//			if (!f.exists()) {
//				f.createNewFile();// fileUpload.exe
//			}
			ProcessBuilder pb = new ProcessBuilder(path + "\\src\\test\\resources\\DataRequestFiles\\fileUpload.exe",
					filePath);
			pb.start();
			System.out.println("Verify the Evidence");
			sleep(6000);
			WebElement weFile = driver
					.findElement(By.xpath("//span[contains(text(),'" + sharedEvidenceFileName + "')]"));
			verifyIfElementPresent(weFile, "Evidence :" + sharedEvidenceFileName, "FuelAndEnergy");
			WebElement wefilesize = driver
					.findElement(By.xpath("//input[@id='file_note_0']//parent::div//following::span"));
			String str = wefilesize.getText().trim();
			if (str.equals("0.32KB") || str.equals("0.06KB")) {
				passed("Successfully validated the evidencefile size as " + wefilesize.getText());
			} else {
				failed(driver, "Failed To validate the evidencefile size as " + wefilesize.getText());
			}
			waitForElement(btnUpload);
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
			Date date = new Date(); // get current date time with Date()
			date1 = dateFormat.format(date);// Now format the date
			String[] splitdate = date1.split(" ");
			res = splitdate[1];
			passed("Date and time of uploading the Evidence File " + date1);
			System.out.println("Date and time of uploading the Evidence File " + date1); // the formatted date-time
																							// string.
			clickOn(btnUpload, "btnUpload");
			sleep(2000);
			waitForElement(btnclose);
			clickOn(btnclose, "Close");
			// waitForElement(btnDoneUploadFile);
			// clickOn(btnDoneUploadFile, "btnDoneUploadFile");
			sleep(3000);
		} catch (Exception e) {
			System.out.println("Expection At Uploading---->" + e.getMessage());
		}
	}
	
	public void ValidateEvidenceDetails() {
	try {
			sleep(2000);
			WebElement wedtetime = driver.findElement(
					By.xpath("//th[contains(text(),'Date')]//parent::tr//parent::thead//following::tr//th"));
			String DateTime = wedtetime.getText();
			String[] splitdate1 = date1.split(" ");
			String res1 = splitdate1[1];
			int returnVal = res.compareTo(res1);
			if (returnVal > 0 || returnVal == 0) {
				passed("Successfully Uploaded the EvidenceFile as :" + sharedEvidenceFileName);
			} else {
				failed(driver, "Failed to upload the Evidence File as :" + sharedEvidenceFileName);
			}

			/*
			 * if(DateTime.trim().equals("date1")) {
			 * passed("Successfully displayed DateAndTime of Uploading the file "+date1); }
			 * else { failed(driver,
			 * "Failed to display DateAndTime of Uploading the file as"
			 * +DateTime+" but Actual is "+date1); }
			 */
			WebElement weUser = driver.findElement(By.xpath(
					"//th[contains(text(),'User/App')]//parent::tr//parent::thead//following::tr//th[@value='createdBy']"));
			if (weUser.getText().trim().equals(data.get("UserName"))) {
				passed("Successfully validated the User/App as " + weUser.getText());
			} else {
				failed(driver,
						"Failed to validate User/App as" + data.get("UserName") + " but Actual is " + weUser.getText());
			}
			WebElement weEvi = driver.findElement(
					By.xpath("//th[contains(text(),'Evidence')]//parent::tr//parent::thead//following::tr//th[3]"));
			if (weEvi.getText().trim().equals(sharedEvidenceFileName)) {
				passed("Successfully validated the EvidenceFile Uploaded as :" + sharedEvidenceFileName);
			} else {
				failed(driver, "Failed to validate the EvidenceFile Uploaded as :" + weEvi.getText() + " but Actual is "
						+ sharedEvidenceFileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			failed(driver, "Exception caught " + e.getMessage());

		}
	}
	
	@FindBy(xpath = "//span[text()='Period']//parent::div/div/*[@data-testid='ArrowDropDownIcon']")
    private WebElement drpPeriodDwn1;
    String[] splittedValue1;
    @FindBy(xpath = "//ul[@aria-label='icon expansion']//li")
    private WebElement lblDropPeriod1;
    @FindBy(xpath = "//article[@aria-label='View Organization Data']")
    private WebElement viewOrgScreen;
    @FindBy(xpath = "(//ul[@aria-label='icon expansion'])[2]//li")
    private List<WebElement> drpActvityType;
    @FindBy(xpath = "//ul[@aria-label='icon expansion']//li")
    private List<WebElement> drpActvityType1;
	
	public void clickOnGenerateButtonAlternate1() {
		try {
			clickOn(drpPeriodDwn, "period drop down");
			
			WebElement perioddrp = driver 
					.findElement(By.xpath("//ul//*[text()='"+Constants.overlapPeriodYear2022+"']"));
			scrollTo(perioddrp);
			actionsClick(perioddrp, "clicked "+data.get("Period Year")+"");
			sleep(1000);
			WebElement btnGenerateRefresh = driver.findElement(By.xpath(
					"//span[contains(text(),'i')]//parent::div//div//following::span//*[local-name()='svg']"));
			clickOn(btnGenerateRefresh, "generate button");
			WebElement totalco2Value = driver.findElement(By.xpath("//span[contains(text(),'Total(tCO2e)')]"));
			String afteregenrtvalue = totalco2Value.getText();
			String[] CO2valueafter = afteregenrtvalue.split("-");
			sleep(3000);
			System.out.println("generated emission are" + ":-" + afteregenrtvalue);
			if (GlobalVariables.initialTotaltCo2e.toString().equals(afteregenrtvalue.toString())) {
				System.out.println("Generate button not working");
				failed(driver, "failed to validate generate button functionality" + CO2valueafter[1] + " previous value "
						+ GlobalVariables.initialTotaltCo2e);
			} else {
				passed("successfully validated generate button functionality" + CO2valueafter[1]);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	@FindBy(xpath = "//p[text()='Global Warming Potential Values']")
    private WebElement taggwp;
    public void validateGlobalWarmingPotentialvalues() {
        try {
            verifyIfElementPresent(taggwp, "Global Warming Potential Values", "");
            sleep(20);
            int n = 0;
            String gwp[] = { "Assessment", "CO2", "CH4", "N2O"};
            for (int i = 0; i < gwp.length; i++) {
                WebElement gwpvalue = driver.findElement(By.xpath(
                        "//p[text()='Global Warming Potential Values']//parent::div//parent::div//following-sibling::div//span[text()='"
                                + gwp[i] + "']//parent::p//following-sibling::p"));
                sleep(2);
                if (Constants.selectedGWP.equals("2007 IPCC Fourth Assessment (AR4, 100-Year GWP)")) {
                    String gwp4[] = { "2007 IPCC Fourth Assessment (AR4)", "1.0000", "25.0000", "298.0000" };
                    for (int j = n; j < gwp4.length; j++) {
                        if (gwpvalue.getText().trim().equals(gwp4[j])) {
                            passed("Successfully Validated " + gwp4[j] + " In Activity Details As"
                                    + gwpvalue.getText().trim());
                            n = j + 1;
                            break;
                        } else {
                            failed(driver, "Failed To validate " + gwp[i] + " In Activity Details Expected As "
                                    + gwp4[j] + "But Actual is" + gwpvalue.getText().trim());
                            n = j + 1;
                            break;
                        }
                    }
                }
                else if (Constants.selectedGWP.equals("2023 IPCC Sixth Assessment (AR6, 100-Year GWP)")) {
                    String gwp6[] = { "2007 IPCC Fourth Assessment (AR6)", "1", "27.9", "273" };
                    for (int j = n; j < gwp6.length; j++) {
                        if (gwpvalue.getText().trim().equals(gwp6[j])) {
                            passed("Successfully Validated " + gwp6[j] + " In Activity Details As"
                                    + gwpvalue.getText().trim());
                            n = j + 1;
                            break;
                        } else {
                            failed(driver, "Failed To validate " + gwp6[j] + " In Activity Details Expected As "
                                    + gwp6[j] + "But Actual is" + gwpvalue.getText().trim());
                            n = j + 1;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void ReusabilityForFacilityNameAndStartDateAndEndDateForCalculators() {
		
			sleep(1000);
			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
			String txtFacilityName = "//input[contains(@id,'acility')]";
			clickOnElementWithDynamicXpath(txtFacilityName, "txtFacilityName");
			WebElement weFacilityName = driver.findElement(By.xpath(txtFacilityName));
			clearUntillTextFieldIsGettingCleared(weFacilityName);
			//enterText(weFacilityName, "Facility Name", data.get("Facility Name"));
			String strFacilityXpath = "//li[text()='"+data.get("Facility Name")+"']";
			WebElement FacilityXpath=driver.findElement(By.xpath(strFacilityXpath));
			scrollTo(FacilityXpath);
			sleep(3000);
		    clickOnElementWithDynamicXpath(strFacilityXpath, data.get("Facility Name"));
//			WebElement description=driver.findElement(By.xpath("//input[@placeholder='Description']"));
//			enterText(description, "des", data.get("Description"));
			clickOn(txtStartDate, "Start date");
			enterText(txtStartDate, "Start Date", data.get("Start Date"));
			clickOn(txtEndDate, "end date");
			enterText(txtEndDate, "End Date", data.get("End Date"));
			if(Boolean.parseBoolean(data.get("Activity CEF")) && !data.get("CardName").equals("Direct Emissions")) {
				if(data.get("CardName").contains("Upstream")) {
					SelectDropdownOptionsForCalculatorActivityFields("Energy Category");
				}
				VerifyCEFReflected_ActivityForCalculators();
			}
		
	}
	
	
	public void clearUntillTextFieldIsGettingCleared(WebElement we) {
		try {
			for(int i=1;i<=50;i++) {
				String strTextField1 = we.getAttribute("value");
				if(!strTextField1.isEmpty()) {
					we.sendKeys(Keys.chord(Keys.CONTROL,"a"));
					we.sendKeys(Keys.BACK_SPACE);
				}else {
					break;
				}
			}
		} catch (Exception e) {
			failed(driver,"Exception caught "+e.getMessage());
		}
	}
	
	public void VerifyCEFReflected_ActivityForCalculators() {
		try {
			Actions action = new Actions(driver);
			if(!btnCustomBasedCEF.isSelected()) {
				action.doubleClick(btnCustomBasedCEF).build().perform();
				passed("clicked on webelement - Check Box Using CEF");
			}
			if( data.get("CardName").contains("Downstream")) {
				SelectDropdownOptionsForCalculatorActivityFields("Energy Category");
			}
			if(data.get("CardName").equals("Processing of Sold Products")) {
				drpCustomEmiFactorActivityMobile = driver.findElement(By.xpath("//input[@id='Emission']"));
			}
			clickOn(drpCustomEmiFactorActivityMobile, "Click on of Custom EF ");
			enterText(drpCustomEmiFactorActivityMobile, "entered Name of Custom EF", CustomEmissionFactorName);
			sleep(500);
			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
			WebElement weCEFActivity = driver.findElement(By.xpath("//li[text()='" + CustomEmissionFactorName + "']"));
			clickOn(weCEFActivity, CustomEmissionFactorName);
			String CustomEmissionFactorNameActivity = drpCustomEmiFactorActivityMobile.getAttribute("value");
			if (CustomEmissionFactorName.equals(CustomEmissionFactorNameActivity)) {
				passed("Successfully Validated Custom Emisiion Factor in Activities"
						+ CustomEmissionFactorNameActivity);
			} else {
				failed(driver, " Failed to Validated Custom Emisiion Factor in Activities Expected is "
						+ CustomEmissionFactorName + "But Actual is " + CustomEmissionFactorNameActivity);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	
	public void ReusabilityForAmount$Units$Tags$Notes$SaveForCalculators(String Amount,
			                                          String Unit,String Tags,String Notes) {
			
			if(data.get("subCalcName").equals("Stationary Combustion")) {
				WebElement fuelAmountSC = driver.findElement(By.xpath("//input[@id='fuelAmount']"));
				clearUntillTextFieldIsGettingCleared(fuelAmountSC);
				enterText(fuelAmountSC, "Fuel Amount", data.get("Fuel Amount"));
			}
			else {
				entertTextInTextFieldForCalculators_ActivityDetails(Amount);
			}
			if(data.get("CardName").equals("Capital Goods") && Boolean.parseBoolean(data.get("Activity CEF")) 
					||data.get("CardName").contains("End-of-Life Treatment") && Boolean.parseBoolean(data.get("Activity CEF"))) {
				System.out.println("Unit is Disabled");
			}else {
				SelectDropdownOptionsForCalculatorActivityFields(Unit);
			}
//			if(!Tags.equals("False")) {
//				if(!ApplicationEnvironment.contains(".eu")) {
//					SelectDropdownOptionsForCalculatorActivityFields(Tags); 
//				}
//			}
			entertTextInTextFieldForCalculators_ActivityDetails(Notes);
			clickOn(btnSave, "Save Button");
	}
	
	
	
	
	public void entertTextInTextFieldForCalculators_ActivityDetails(String textField) {
		try {
			sleep(2);
			String strTextField = "//span[normalize-space()='"+textField+"']/ancestor::div//input[contains(@placeholder,'"+textField+"')]";
			//clickOnElementWithDynamicXpath(strTextField, textField);
			WebElement weTextField = driver.findElement(By.xpath(strTextField));
			clearUntillTextFieldIsGettingCleared(weTextField);
			enterText(weTextField, textField, data.get(textField));
		} catch (Exception e) {
			failed(driver,"Exception caught "+e.getMessage());
		}
	}
	
	
	
	
	
	public void SelectDropdownOptionsForCalculatorActivityFields(String fieldName) {
			sleep(2000);
			//System.out.println(data.get("Quantity of refrigerant purchase/use/leak"));
			String strExpandIcon = "//span[normalize-space()='"+fieldName+"']/ancestor::div/following-sibling::div/div//div//input[@placeholder='"+fieldName+"']";
			WaitForElementWithDynamicXpath(strExpandIcon);
			clickOnElementWithDynamicXpath(strExpandIcon, fieldName);
			sleep(4000);
			String strOptions = "//li[normalize-space()='"+data.get(fieldName)+"']";
			WebElement weOptions = driver.findElement(By.xpath(strOptions));
			clickOn(weOptions, data.get(fieldName));
	}
	
	
	@FindBy(xpath="//input[@id='product_name']")
	public WebElement soldProduct;
	public void selectDropDownOptionsForCalculatorActivityFieldsForUOSP() {
		try {
			clickOn(soldProduct, "soldProduct");
			String strExpandIconOfsoldProduct="//li[text()='"+data.get("Sold Product")+"']";
			clickOnElementWithDynamicXpath(strExpandIconOfsoldProduct,data.get("Sold Product"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<String> arrayOfActivityDetails;
    public void clickFirstElementInADropdownIfanyExceptionHappenedForActivityFields(String fieldName) {
        String strFE = "//ul[@role='listbox']//li";
        if(isElementPresentDynamicXpath(strFE) == true) {
            WebElement FEleDrp = driver.findElement(By.xpath("//ul[@role='listbox']//li"));
            clickOn(FEleDrp, fieldName);
            arrayOfActivityDetails.add(fieldName);
        }else {
            String strFE1 = "//div[text()='No options']";
            WebElement FEleDrp1 = driver.findElement(By.xpath(strFE1));
            if(FEleDrp1.getText().equals("No options")) {
                failed(driver,"No Options -------> "+ fieldName + "in "+ data.get("CardName")+"_"+data.get("subCalcName"));
            }
        }
    }
	
	
	// ------------------------------------------ cef -----------------------------------------------------------//
	
	  public static String CustomEmissionFactorName;
	    public void commonFieldsForAddingCUSTOMEMISSIONFACTOR_ParameterInput(String source,String CO2e) {
	    	try {
	    		CustomEmissionFactorName = Constants.CEFName + generateRandomNumber(4);
	    		clearUntillTextFieldIsGettingCleared(txtCutsomEFName_1);
				enterText(txtCutsomEFName_1, "entered Name of Custom EF", CustomEmissionFactorName);
				entertTextInTextFieldForCalculators_ActivityDetails(source);
				if(CO2e.equals("true")) {
					entertTextInTextFieldForCalculators_ActivityDetails("CO2e");
				}
				SelectDropdownOptionsForCalculatorActivityFields("Unit of Custom EF (Numerator)");
				if(data.get("subCalcName").equals("Spend Based Method") && data.get("CardName").equals("Purchased Goods and Services") ) {
					System.out.println("Unit of Custom EF (Denominator) is not there");
				}else {
					SelectDropdownOptionsForCalculatorActivityFields("Unit of Custom EF (Denominator)");
				}
				if( !data.get("CardName").equals("Investments")) {
					String strNotes = "//span[normalize-space()='Notes']/ancestor::div/following-sibling::div//*[contains(@placeholder,'Notes')]";
					WebElement weNotes = driver.findElement(By.xpath(strNotes));
					clearUntillTextFieldIsGettingCleared(weNotes);
					enterText(weNotes, "Notes", data.get("Notes"));
				}
				clickOn(btnSave, "Save Button");
			} catch (Exception e) {
				failed(driver,"Exception caught "+e.getMessage());
			}
	    }
	
	public void addAndValidateParameters_CustomEmissionFactor(String[] actDetails) {
		try { 
			if(!Constants.arrayCardPresent.contains(data.get("CardName"))) {
				Constants.arrayCardPresent.add(data.get("CardName"));
				clickOnCarbonManagementNavigationMenu();
				String strCalcCard = "//div[text()='"+data.get("CardName")+"']";
				clickOnElementWithDynamicXpath(strCalcCard, data.get("CardName"));
			}
			if(data.get("CardName").equals("Direct Emissions")) {
				clickOnEmissionFactorsForDirectEmission();
				clickOnbuttonParameterInputGHGCalculatorScope1();
			}else {
				clickOnParameneterInputGHGCalculator();
				clickOnbuttonParameneterInputGHGCalculator();
			}
			AddCustomEmissionFactorsForCalculators(data.get("CardName"),data.get("subCalcName"));
			if(data.get("Activity").equals("Add")) {
				verifyCEFAddToastMessage();
			}else {
				verifyCEFEditToastMessage();
			}
			validateRHPActivityDetailsForAllCalculators(actDetails, data.get("CardName"));
			if(data.get("CardName").equals("Upstream Leased Assets") || data.get("CardName").equals("Downstream Leased Assets")) {
				validateTCO2EvalueForCustomEmissionFactorRHP();
				verifyCO2eFactorForCustomEmissionFactorRHP();
			}
			clickOnCloseInActivityDetails();
		} catch (Exception e) {
			failed(driver,"Exception caught "+e.getMessage());
		}
	}
	
	
	public void AddCustomEmissionFactorsForCalculators(String calcCardName,String subCalc) {
		try {
			switch (calcCardName) {
			case "Direct Emissions":
				directEmissionsCalculatorPage = new DirectEmissionsCalculatorPage(driver, data);
				directEmissionsCalculatorPage.Add_CustomEmissionFactor_Scope1_InDirectEmissions();
				break;
			case "Purchased Goods and Services":
				PGS = new PurchasedGoodsAndServicesGHGCalculatorPage(driver, data);
				if(subCalc.equals("Average Data Method")) {
					PGS.Add_CustomEmissionFactor_Purchased_Godds_Services_Average_Data_Scope3_1("Average Data Method");
				}else {
					PGS.Add_CustomEmissionFactor_Purchased_Godds_Services_Average_Data_Scope3_1("Spend Based Method");
				}
				break;
			case "Capital Goods":
				CGS = new CapitalGoodsGHGCalculatorPage(driver, data);
				CGS.Add_CustomEmissionFactor_Capital_Goods_Aversge_Spend_Data_Scope3_1();
				break;
			case "Hotel Stay":
				commonFieldsForAddingCUSTOMEMISSIONFACTOR_ParameterInput("Source of Emission Factor","true");
				break;
			case "Downstream Leased Assets":
			case "Upstream Leased Assets":
				addCustomEmissionFactorForULA_DLA_ParameterInput();
				break;
			case "Processing of Sold Products":
				POSP = new ProcessingofSoldProductsCalculatorPage(driver, data);
				POSP.Add_CustomEmissionFactorProcessingofSoldProduct();
				break;
			case "Use of Sold Products":
				UOSP = new UseOfSoldProductGHGCalculatorPage(driver, data);
				UOSP.Add_CustomEmissionFactorUseofSoldProduct();
				break;
			case "End-of-Life Treatment of Sold Products":
				EOLT = new EndOfLifeTreatmentGHGCalculatorPage(driver, data);
				EOLT.Add_CustomEmissionFactorEndofLifeTreatmentScope3_12();
				break;
			case "Investments":
				Investments = new InvestmentsGHGCalculatorsPage(driver, data);
				Investments.Add_CustomEmissionFactorInestments();
				break;
			}
		} catch (Exception e) {
			failed(driver,"Exception caught "+e.getMessage());
		}
	}
	
	
	public void validateTCO2EvalueForCustomEmissionFactorRHP() {
		Double co2;
		Double ch4;
		Double n2o;
		sleep(1);
		
		System.out.println(data.get("gwp year"));
		if(data.get("gwp year").contains("AR4")) {
			 co2 = Constants.GWParCO2 * Double.parseDouble(data.get("CO2"));
			 ch4 = Constants.GWPar4CH4 * Double.parseDouble(data.get("CH4"));                 
			 n2o = Constants.GWPar4N2O  * Double.parseDouble(data.get("N2O"));
		}else if(data.get("gwp year").contains("AR5")){
			 co2 = Constants.GWParCO2 * Double.parseDouble(data.get("CO2"));;
			 ch4 = Constants.GWPar5CH4 * Double.parseDouble(data.get("CH4"));                 
			 n2o = Constants.GWPar5N2O  * Double.parseDouble(data.get("N2O"));
		}else {
			 co2 = Constants.GWParCO2 * Double.parseDouble(data.get("CO2"));;
			 ch4 = Constants.GWPar6CH4 * Double.parseDouble(data.get("CH4"));                 
			 n2o = Constants.GWPar6N2O  * Double.parseDouble(data.get("N2O"));
		}
		Double emmissionFactorValueExp1 = co2 + ch4 + n2o;	
		emmissionFactorValueExp = emmissionFactorValueExp1.toString();
	}
	
	public void AddandValidateActivityForCalculators_CEF(String[] actDetails,String Amount) {
		 try {
			 String strSubCalc = "//button[text()='"+data.get("subCalcName")+"']";
			 clickOnElementWithDynamicXpath(strSubCalc, data.get("subCalcName"));
			 if(data.get("CardName").equals("Investments")) {
				 String investmentsSubCalc = "//button[text()='Average Data Method']";
				 clickOnElementWithDynamicXpath(investmentsSubCalc, "Average Data Method");
			 }
			 if(Boolean.parseBoolean(data.get("OrgVisibility"))) {
			       SelectOrganizationForTiffany();
			 }
			SelectPeriod2022();
			if(data.get("Activity").equals("Add")) {
				clickOnAddActivityButton();
			}else if(data.get("Activity").equals("Edit")) {
				clickOnActivityInActivitiesGridMultipleForAGGridEdit();
				clickOnEditButtonInActivityDetails();
			}
			AddActivityForCalculatorsUsingCustomEmissionFactors(data.get("CardName"),data.get("subCalcName"));
			if(data.get("Activity").equals("Add")) {
				verifyAddActivityUpdatedToastMessage();
			}else {
				verifyEditActivityToastMessage();
			}
			validateRHPActivityDetailsForAllCalculators(actDetails, data.get("CardName"));               //RHP ActDetails
			validateEmissionDetailsForAddCEF_EditCEF(data.get("CardName"), Amount);                      //Enission Details
			clickOnCloseInActivityDetails();
		} catch (Exception e) {
			failed(driver,"Exception caught "+e.getMessage());
		}
	}
	
	    public void AddActivityForCalculatorsUsingCustomEmissionFactors(String calcCardName,String subCalc) {
	    	try {
				switch (calcCardName) {
				case "Direct Emissions":
					directEmissionsCalculatorPage = new DirectEmissionsCalculatorPage(driver, data);
					if(subCalc.equals("Stationary Combustion")) {
						directEmissionsCalculatorPage.AddActivityForCEFStationaryCombutionInDirectEmissions();
					}else {
						directEmissionsCalculatorPage.EditActivityMobileCombutionInDirectEmissions();
					}
					break;
				case "Purchased Goods and Services":
					PGS = new PurchasedGoodsAndServicesGHGCalculatorPage(driver, data);
					if(subCalc.equals("Average Data Method")) {
						PGS.EditActivityScope3EmissionsScope3_1_AverageBased();
					}else {
						PGS.EdittActivityScope3_1SpendBasedEmissions();
					}
					break;
				case "Capital Goods":
					CGS = new CapitalGoodsGHGCalculatorPage(driver, data);
					if(subCalc.equals("Average Data Method")) {
						CGS.EditActivityScopeCapitalGoods3_2EmissionsAverageBased();
					}else {
						CGS.EditActivityCapitalGoodsScope3_2SpendBasedEmissions();
					}
					break;
				case "Hotel Stay":
					hotelStay = new HotelStayGHGCalculatorPage(driver, data);
					hotelStay.EditActivityScope3_6HotelStayEmissions();
					break;
				case "Downstream Leased Assets":
					DLA = new DownStreamLeasedAssetsGHGCalculatorPage(driver, data);
					DLA.EditActivityScope3_13DownstreamLeasedAssets();
					break;
				case "Upstream Leased Assets":
					ULA = new UpstreamLeasedAssetsGHGCalculatorPage(driver, data);
					ULA.EditActivityScope3_8UpstreamLeasedAssets();
					break;
				case "Processing of Sold Products":
					POSP = new ProcessingofSoldProductsCalculatorPage(driver, data);
					POSP.EditActivityScope3_10PrcessingofSoldProdutcs();
					break;
				case "Use of Sold Products":
					UOSP = new UseOfSoldProductGHGCalculatorPage(driver, data);
					UOSP.EditActivityScope3_11UseofSoldProdutcs();
					break;
				case "End-of-Life Treatment of Sold Products":
					EOLT = new EndOfLifeTreatmentGHGCalculatorPage(driver, data);
					EOLT.EditActivityEndOfLifeTreatmentScope3_12Emissions();
					break;
				case "Investments":
					Investments = new InvestmentsGHGCalculatorsPage(driver, data);
					if(subCalc.equals("Equity Investments")) {
						Investments.EditActivityScope3_15InvestmentsAverageDataMethod();
					}else if(subCalc.equals("Debt & Project Financing")) {
						Investments.EditActivityScope3_15InvestmentsDebtProjectFinancingAverageDataMethod();
					}
					break;
             }
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
	   }
	    
	    public void validateEmissionDetailsForAddCEF_EditCEF(String calcName,String Amount) {
	    	try {
				if(calcName.equals("Upstream Leased Assets") || calcName.equals("Downstream Leased Assets") 
						|| calcName.equals("Direct Emissions")) {
					VerifyCustomEmissionFactorActivitiesDetailsRHPGHGCalculator();
					validateTCO2_TCH4_TN2OvaluesIfWeHaveValues_CEF(Amount);
					calculateTCO2eValueUsing_EmissionFactorValue(Amount);
				}else if(calcName.equals("Investments")){
					VerifyCustomEmissionFactorActivitiesDetailsRHPGHGCalculator();
				}else {
					VerifyCustomEmissionFactorActivitiesDetailsRHPGHGCalculator();
					validateEmissionDtailsForCalculatorsHavingCo2eField_TCO2E_CEF(Amount);
				}
			} catch (Exception e) {	
				failed(driver, "Exception caught " + e.getMessage());
			}	
	    }
	    
	    public void VerifyCustomEmissionFactorActivitiesDetailsRHPGHGCalculator() {
			try {
				String strCEF;
				if(data.get("CardName").equals("Investments")) {
					 strCEF = "//span[text()='Custom Emission Factor']//parent::div//following-sibling::div";
				}else if(data.get("CardName").equals("Capital Goods") || data.get("CardName").equals("Processing of Sold Products")) {
					strCEF = "//span[text()='Custom EF']//parent::p//following-sibling::p";
				}
				else {
					 strCEF = "//span[contains(text(),'Custom Emission Factor')]//parent::p//following-sibling::p";
				}
				WebElement weCEFNameRHP = driver.findElement(By.xpath(strCEF));
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
	    
	    
	    public void validateTCO2_TCH4_TN2OvaluesIfWeHaveValues_CEF(String ActivityAmount) {
	    	try {
				String[] emissionDetails = {"CO2","CH4","N2O"};
				for(int i=0;i<emissionDetails.length;i++) {
					Double formulae = Double.parseDouble(data.get(emissionDetails[i])) 
							                                    * Double.parseDouble(ActivityAmount) /1000;
					String emissionExpectedValues = formulae.toString();
					WebElement weActualEmissionValues = driver.findElement(By
							          .xpath("//span[text()='t"+emissionDetails[i]+"']/parent::p/following-sibling::p/div"));
					String actualEmissionValues = weActualEmissionValues.getText().replaceAll(",", "");
					if(emissionExpectedValues.equals(actualEmissionValues)) {
						passed("Successfully validated, t"+emissionDetails[i]+" Expected as "+emissionExpectedValues+" " 
					                              + "and the Actual as " + actualEmissionValues);
					}else {
						Double doubleDiff= Double.parseDouble(emissionExpectedValues)- Double.parseDouble(actualEmissionValues);
						if(doubleDiff<1 && doubleDiff>-0.4) {    
							passed("Successfully validated, t"+emissionDetails[i]+" Expected as "+emissionExpectedValues+" " 
				                      + "and the Actual as " + actualEmissionValues);
						}else {
							failed(driver,"failed to validate, t"+emissionDetails[i]+" Expected as "+emissionExpectedValues+" " 
				                    + "and the Actual as " + actualEmissionValues);
						}
					}
				}
			} catch (NumberFormatException e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
	    	
	    }
	    public void verifyCO2eFactor() {
			try {
				WebElement cefValueRHP = driver
						.findElement(By.xpath("//span[contains(text(),'CO2e')]//parent::div//following-sibling::div"));
				String newPlainValue = approximateDecimalValueWithBigDecimal(cefValueRHP.getText());
				if (newPlainValue.equals(emmissionFactorValueExp)) {
					passed("Succesfully validated tCO2e value" + newPlainValue + " And Actual is " + emmissionFactorValueExp);
				} else {
					fail("Failed validated tCO2e value" + " " + newPlainValue + " But Actual is " + emmissionFactorValueExp);
				}
				WebElement weNameOfCEF = driver.findElement(
						By.xpath("//span[contains(text(),'Name of Custom EF')]//parent::div//following-sibling::div"));
				if (weNameOfCEF.getText().equals(CustomEmissionFactorName)) {
					passed("successfully validated name of custom emission factor");
				} else {
					fail("failed to validate name of custom emission factor");
				}
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
		}
	    
	    
	    public void verifyCO2eFactorForCustomEmissionFactorRHP() {
	    	try {
	    		sleep(1);
				WebElement cefValueRHP = driver
						.findElement(By.xpath("//span[contains(text(),'CO2e')]//parent::div//following-sibling::div"));
				System.out.println(cefValueRHP.getText());
				String newPlainValue = approximateDecimalValueWithBigDecimal(cefValueRHP.getText().replaceAll(",", ""));
				if (newPlainValue.equals(emmissionFactorValueExp)) {
					passed("Succesfully validated tCO2e value" + newPlainValue + " And Actual is " + emmissionFactorValueExp);
				} else {
					Double doubleDiff= Double.parseDouble(newPlainValue)- Double.parseDouble(emmissionFactorValueExp);
					if(doubleDiff<1 && doubleDiff>-0.4) {    
						passed("Succesfully validated activityDetail" + newPlainValue + " And Actual is " + 
								emmissionFactorValueExp);
					}else {
					    failed(driver,
							    "Failed validated activityDetail" + " " + newPlainValue + " But Actual is " 
					                                              + emmissionFactorValueExp);
					}
				}
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
	    }
	    
	    public void validateEmissionDtailsForCalculatorsHavingCo2eField_TCO2E_CEF(String Amount) {
			try {
				sleep(2);
				WebElement CO2eActivity = driver
						.findElement(By.xpath("//span[contains(text(),'CO2e')]/parent::p/following-sibling::p/div"));
				String strCO2E;
				if(data.get("CardName").equals("Use of Sold Products")) {
					 strCO2E = data.get("CO2e (Yearly)");
				}else {
					 strCO2E = data.get("CO2e");
				}
				Double ValCO2e = Double.parseDouble(strCO2E)
						                         * Double.parseDouble(Amount) / 1000;
				String ValCO2eRHP1 = ValCO2e.toString();
				String ValCO2eRHP = approximateDecimalValueWithBigDecimal(ValCO2eRHP1);
				if (CO2eActivity.getText().equals(ValCO2eRHP)) {
					passed("Succesfully validated tCO2e value" + ValCO2eRHP + " And Actual is " + CO2eActivity.getText());
				} else {
					Double doubleDiff= Double.parseDouble(ValCO2eRHP) - Double.parseDouble(CO2eActivity.getText());
					if(doubleDiff<1 && doubleDiff>-0.4) {
						passed("Succesfully validated tCO2e value" + ValCO2eRHP + " And Actual is " + CO2eActivity.getText());
					}else {
					    failed(driver,
							    "Failed validated tCO2e value" + " " + ValCO2eRHP + " But Actual is " + CO2eActivity.getText());
					}
				}
				WebElement EFActivity = driver.findElement(By.xpath
					("//p[text()='Emission Details']/ancestor::div//span[text()='Emission Factor']/parent::p/following-sibling::p/div"));
				String s2 = EFActivity.getText().trim();
				String[] EFValueRHP = s2.split(" ");
				String tco2eCEFRHP = approximateDecimalValueWithBigDecimal(strCO2E);
				if (EFValueRHP[0].equals(tco2eCEFRHP)) {
					passed("Succesfully validated Emission factor value" + EFValueRHP[0] + " And Actual is " + tco2eCEFRHP);
				} else {
					Double doubleDiff= Double.parseDouble(EFValueRHP[0]) - Double.parseDouble(tco2eCEFRHP);
					if(doubleDiff<1 && doubleDiff>-0.4) {
						passed("Succesfully validated Emission factor value" + EFValueRHP[0] + " And Actual is " + tco2eCEFRHP);
					}else {
					    failed(driver,
							    "Failed validated Emission factor value" + " " + EFValueRHP[0] + " But Actual is " + tco2eCEFRHP);
					}
				}
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
		}
	    
	    
	    
	    
	  public void addCustomEmissionFactorForULA_DLA_ParameterInput() {
		  try {
			  SelectDropdownOptionsForCalculatorActivityFields("Energy Category");
			  entertTextInTextFieldForCalculators_ActivityDetails("CO2");
			  entertTextInTextFieldForCalculators_ActivityDetails("CH4");
			  entertTextInTextFieldForCalculators_ActivityDetails("N2O");
//			  if(!data.get("CardName").equals("Downstream Leased Assets")) {
//				  entertTextInTextFieldForCalculators_ActivityDetails("Biofuel CO2");
//			  }
			  commonFieldsForAddingCUSTOMEMISSIONFACTOR_ParameterInput("Source of Emission Factor","false");
		} catch (Exception e) {
			failed(driver,"Exception caught "+e.getMessage());
		}
	  }
	
	   
	//--------------------------------------- cef end --------------------------------------------------------//
	
	
	public UpstreamLeasedAssetsGHGCalculatorPage navigateToUpStreamLeasedAssert() {
		return new UpstreamLeasedAssetsGHGCalculatorPage(driver, data);
		
	}
	
	public InvestmentsGHGCalculatorsPage navigateInvestmentsPage() {
		return new InvestmentsGHGCalculatorsPage(driver, data);
		
	}
	
	public DirectEmissionsCalculatorPage navigateToDirectEmissionPage(Data data) {
		return new DirectEmissionsCalculatorPage(driver, data);
		
	}
	
	public BusinessTravelCalculatorPage clickOnDistancebasedMethodInBusinessTravelCalculator() {
		clickOn(btnDistanceBasedMethod, "DistanceBasedMethodCalculator");
		sleep(3000);
		return new BusinessTravelCalculatorPage(driver, data);
	}

	public BusinessTravelCalculatorPage clickOnSpendbasedMethodInBusinessTravelCalculator() {
		clickOn(btnSpendBasedMethod, "SpendBasedMethodCalculator");
		return new BusinessTravelCalculatorPage(driver, data);
	}

	public BusinessTravelCalculatorPage clickOnBusinessTravelCalculator() {
		clickOn(weBusinessTravelCalculator, "BusinessTravelCalculator");
		return new BusinessTravelCalculatorPage(driver, data);
	}

	public EmployeeCommutingCalculatorPage clickOnEmployeeCommutingCalculator() {
		clickOn(weEmployeeCommutingCalculator, "EmployeeCommutingCalculator");
		return new EmployeeCommutingCalculatorPage(driver, data);
	}

	public WasteGeneratedInOperationsCalculatorPage clickOnWasteGeneratedInOperationsCalculator() {
		clickOn(weWasteGeneratedInOperations, "WasteGeneratedInOperations");
		return new WasteGeneratedInOperationsCalculatorPage(driver, data);
	}
	
	Date date = new Date();
	Random rnd = new Random();
	@FindBy(xpath = "//div[@class='activity-item']//article/*")
	public WebElement btnActivitiesLanguage;
	@FindBy(xpath = "//*[text()='Stationary Combustion']")
	public WebElement btnStationary;
	@FindBy(xpath = "//button[@aria-label='Close']")
	public WebElement btnCloseProcessingofsoldProduct;
	@FindBy (xpath = "//li[@aria-label='Transactions']")
	public WebElement btnTransactions;
	@FindBy(xpath = "//li[@aria-label='Emission Factors']")
	public WebElement btnParameterScope1;
	@FindBy(xpath = "//article[@aria-label='Add Custom Emission Factors']")
	public WebElement btnAddCstEmsonFctrScope1;
//	@FindBy(xpath = "//ul[@aria-label='icon expansion']//li[@role='treeitem']")
//	private List<WebElement> drpActvityType;
	@FindBy(xpath = "//button[text()='Close']")
	public WebElement bttnCloseAdd;
	@FindBy(xpath = "//div[@name='center']")
	public WebElement agGridForCalculators;
	
	@FindBy(xpath = "//div[text()='Waste Generated in Operations']")
	public WebElement weWasteGeneratedInOperations;
	@FindBy(xpath = "//article[@aria-label='Add Activity']")
	public WebElement btnAddActivities3_1;
	@FindBy(xpath = "//button[@aria-label='Previous']//following-sibling::button[@aria-label='Edit']")
	public WebElement btnEditActivityqa;
	@FindBy(xpath = "//div[text()='Edit Activity']//parent::div//parent::div//div//button[text()='Cancel']")
	public WebElement btnCancelActivity;
	@FindBy(xpath = "(//span[text()='Next']//parent::button//parent::span//following-sibling::button)[2]")
	public WebElement btnEditActivityMobileComb;
	
	/* -----------------------------------------------  Common --------------------------------------------------------*/
	
//	public TransactionsPage clickOnTransactionsSubMenu() {
//		try {
//			waitForElement(btnTransactions);
//			clickOn(btnTransactions, "Clicked on Transactions Sub Menu");
//			return new TransactionsPage(driver, data);
//		} catch (Exception e) {
//			failed(driver,"Exception caught "+e.getMessage());
//		}
//		return null;
//	}
	
	public void clickOnCloseInActivityDetails() {

		try {
			jsClick(btnCloselatest, "Close Button");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	public void clickOnCloseInActivityDetailsSoldProduct() {
		clickOn(btnCloseProcessingofsoldProduct, "Close Button");
	}

	public static String approximateDecimalValueWithBigDecimal(String value) {
		BigDecimal myBigDecimal = new BigDecimal(value);
		BigDecimal newValue = myBigDecimal.setScale(4, RoundingMode.DOWN).stripTrailingZeros();
		System.out.println(newValue);
		return newValue.toPlainString();
	}
	
	
	public static String approximateDecimalValueWithBigDecimalWitgDecimal(String value,int DecimalCount) {
		BigDecimal myBigDecimal = new BigDecimal(value);
		BigDecimal newValue = myBigDecimal.setScale(DecimalCount, RoundingMode.DOWN).stripTrailingZeros();
		System.out.println(newValue);
		return newValue.toPlainString();
	}
	
	public static String approximateDecimalValueWithBigDecimal_Up(String value) {
		BigDecimal myBigDecimal = new BigDecimal(value);
		BigDecimal newValue = myBigDecimal.setScale(2, RoundingMode.UP).stripTrailingZeros();
		System.out.println(newValue);
		return newValue.toPlainString();
	}

	
	
	@FindBy(xpath = "//span[text()='Period']//parent::div//child::div//*[@data-testid='ArrowDropDownIcon']")
    private WebElement drpPeriodDwnLatest;
    public void selectPeriodToAll() {
        try {
            clickOn(drpPeriodDwnLatest, "period drop down");
            String periodDesc = "//ul[@role='tree']//li//div//div[text()='All']";
            sleep(2000);
            clickOnElementWithDynamicXpath(periodDesc, "period selected");
            sleep(2000);
        } catch (Exception e) {
            failed(driver, "Exception caught " + e.getMessage());
        }
    }
	
	String customEmissionName;

	public void clickOnCloseInActivityDetailsInScope2() {
		clickOn(btnCloselatest, "Close Button");
		//clickOnGenerateButtonAlternateScoep2();
	}

	public void clickOnGenerateButtonAlternateScoep2() {
		sleep(3000);
		clickOn(drpPeriodDwn, "period drop down");
		String periodDesc = "//ul[@role='listbox']//li[text()='" + Constants.overlapPeriodYear2022 + "']";
		clickOnElementWithDynamicXpath(periodDesc, "period selected");
		clickOn(btnGenerate, "generate button");
		sleep(1000);
		WebElement totalco2Value = driver
				.findElement(By.xpath("//span[contains(text(),'Location Based Emissions(tCO2e)')]"));
		// span[contains(text(),'Total(tCO2e)')]
		// span[contains(text(),'Location Based Emissions(tCO2e)')]
		String afteregenrtvalue = totalco2Value.getText();
		String[] CO2valueafter = afteregenrtvalue.split("-");
		System.out.println("generated emission are" + ":-" + afteregenrtvalue);
		if (isNumeric(CO2valueafter[1])) {
			passed("successfully validated generate button functionality" + CO2valueafter[1]);
		} else {
			failed(driver, "failed to validate generate button functionality" + CO2valueafter[1]);
		}
	}

	public void clickOnGenerateButtonAlternate() {
		try {
		    SelectPeriod2022();
		    String strGenerate = "//*[@aria-label='Select refresh to calculate emissions totals. Any activities spread across multiple periods will be prorated by the number of days.']";
		    WebElement btnGenerate = driver.findElement(By.xpath(strGenerate));
			actionsClick(btnGenerate, "generate button");
			validateGenerateButtonToastMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FindBy(xpath="//div[text()='Emissions Value Refreshed Successfully']")
	public WebElement generatButtonToastMessage;
	public void validateGenerateButtonToastMessage(){
		try {
			if(isElementPresent(generatButtonToastMessage)){
				passed("Successfully Validated,toast Message as"+generatButtonToastMessage.getText());
			}else {
				failed(driver,"Failed to Validate Generate Button Toast Message");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public void clickOnParameneterInputGHGCalculator() {
		sleep(1000);
		clickOn(btnParameterInput, "clicked on parameter input");
	}

	public void clickOnParameterInputGHGCalculatorScope1() {
		sleep(2000);
		clickOn(btnParameterScope1, "clicked on parameter input");
	}

	public void clickOnbuttonParameneterInputGHGCalculator() {
		try {
			Actions action = new Actions(driver);
			if(data.get("Activity").equals("Add")) {
			      clickOn(btnAddCstmEmsonFctr, "clicked on Add parameter Button");
			}else if(data.get("Activity").equals("Edit")) {
				String strCefName = "//*[text()='" + CustomEmissionFactorName + "']//parent::div";
				WebElement weCEFGRID = driver.findElement(By.xpath(strCefName));
				action.doubleClick(weCEFGRID).build().perform();
				passed("clicked on Element Name as --> " + "Parameter AG GRID");
				clickOnEditButtonInActivityDetails();
			}
		} catch (Exception e) {
			failed(driver,"Exception caught "+e.getMessage());
		}
	}

	public void clickOnbuttonParameterInputGHGCalculatorScope1() {
		jsClick(btnAddCstEmsonFctrScope1, "clicked on Add parameter Button");
	}

	public void clickOnEditParameneterInputGHGCalculator() {
		clickOn(btnEditCstmEmsonFctr, "clicked on Edit parameter Button");
	}
	
	/* -----------------------------------------------  Common close--------------------------------------------------------*/
	
	/* -----------------------------------------------  Calculators --------------------------------------------------------*/
	
	
	
	public void clickOnActivityInActivitiesGridMultipleForAGGridEdit() {
		try {
			String name;
			waitForElementForCalculators(agGridForCalculators, "AG grid");
			//extractEmissionValueBeforeGenerate();
			if(data.get("CardName").equals("Investments")) {
				if(data.get("Investment subName").contains("Debt")) {
					 name = data.get("Investee Company/Project");
				}else {
					 name = data.get("Investee Company");
				}
			}else {
				  name = data.get("Facility Name");
			}
			for(int i=0;i<10;i++) {
				String agGridName;
				String strAGRow = "//*[text()='"+name+"']//ancestor::div[@role='row']";
				WebElement weAGRow = driver.findElement(By.xpath(strAGRow));
				if(!weAGRow.getAttribute("class").contains("ag-row-selected")) {
					if(data.get("CardName").contains("End-of-Life Treatment of Sold Products")) {
						agGridName="//*[text()='"+name+"']";
					}else {
						agGridName="//*[text()='"+name+"' and @aria-label='"+name+"']";
					}
					WaitForElementWithDynamicXpath(agGridName);
					WebElement we = driver.findElement(By.xpath(agGridName));
					we.click();
				}else {	
					if(weAGRow.getAttribute("class").contains("ag-row-selected")) {
						passed("Clicked on, Activity of Facility" + "AG GRID");
						break;
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Exception caught while Clicking AG Grid");
		}
	}

	public void clickOnActivityInActivitiesGridMultipleUseofSold() {
		WebElement weActivity = driver.findElement(By.xpath("//*[text()='" + data.get("Facility") + "']//parent::div"));
		clickOn(weActivity, "Activity of Facility" + data.get("Facility"));
	}

	public void clickOnActivityInActivitiesGridMultipleHomeOffice() {
		WebElement weActivity = driver
				.findElement(By.xpath("//*[text()='" + data.get("Facility / Location") + "']//parent::div"));
		clickOn(weActivity, "Activity of Facility" + data.get("Facility / Location"));
	}
	
	/* -----------------------------------------------  Direct Emissions  --------------------------------------------------------*/
	
	public DirectEmissionsCalculatorPage clickOnScope1DirectEmissions() {
		clickOn(weDirectEmissions, "Scope1DirectEmissions");
		return new DirectEmissionsCalculatorPage(driver, data);
	}

	public void clickOnStationaryCombustion() {
		clickElement(btnStationary, "StationaryCombustion");

	}

	@FindBy(xpath = "//ul/li[@aria-label='GHG Calculators']/div/*")
	public WebElement weGHGCalculators;

	public void clickOnGHGCalculatorsMenu() {
		System.out.println(waitForElement(weGHGCalculators));
		weGHGCalculators.click();
		// clickOnElement(weGHGEmissionSetUp, "GHGEmission setUp Menu");
	}
	
	public DirectEmissionsCalculatorPage clickOnRefrigerantsandFugitivesScope1GHGCalculator() {
		clickOn(btnRefrigerantsScope1, "Refrigerants Button Scope1");
		return new DirectEmissionsCalculatorPage(driver, data);
	}

	public DirectEmissionsCalculatorPage clickOnRefrigerantsandFugitivesScope1QAGHGCalculator() {
		waitForElement(btnRefrigerantsScope1);
		clickOn(btnRefrigerantsScope1, "Refrigerants Button Scope1");
		return new DirectEmissionsCalculatorPage(driver, data);
	}
	
	
	/* -----------------------------------------------  Direct Emissions close --------------------------------------------------------*/
	
	/* ----------------------------------------  Purchhased Goods and Services --------------------------------------------------------*/
	
	@FindBy(xpath = "//div[text()='Purchased Goods and Services']")
	public WebElement weScope3Purchased_Good_Ser;

	public PurchasedGoodsAndServicesGHGCalculatorPage clickOnScope3_1PurchasedGoods_Ser() {
		clickOn(weScope3Purchased_Good_Ser, "Scope1DirectEmissions");
		return new PurchasedGoodsAndServicesGHGCalculatorPage(driver, data);
	}
	
	public PurchasedGoodsAndServicesGHGCalculatorPage clickOnScope3_1PurchasedGoods_SerSpend() {
		clickOn(btnScope3_1SpendBased, "purchasd spend");
		return new PurchasedGoodsAndServicesGHGCalculatorPage(driver, data);
	}
	@FindBy(xpath = "//article[@aria-label='View Organization Data']")
	public WebElement viewOrgData;
	
	public static String CO2valueBefore;
	public void extractEmissionValueBeforeGenerate() {
		try {
			String totalco2ValueBeforeGenerate = "//span[contains(text(),'Total(tCO2e)')]";
			System.out.println(WaitForElementWithDynamicXpath("Emission Value : " + totalco2ValueBeforeGenerate));
			WebElement totalco2Value = driver.findElement(By.xpath(totalco2ValueBeforeGenerate));
			String afteregenrtvalue = totalco2Value.getText();
			String[] CO2valueafter = afteregenrtvalue.split("- ");
			 CO2valueBefore = CO2valueafter[1];
			System.out.println("generated emission are" + ":-" + CO2valueBefore);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void clickOnAddActivityButton() {
		try {
			waitForElementForCalculators(agGridForCalculators, "Activity AG Grid");
			sleep(1000);
			//extractEmissionValueBeforeGenerate();
			clickOn(btnActivities_scope1, "btnActivities");
		} catch (Exception e) {
			System.err.println("While Selecting Add Plus Button");
		}
	}

	

	public void clickOnAddActivityButtonPurch() {
		try {
			//extractEmissionValueBeforeGenerate();
			waitForElement(btnAddActivities3_1);
			clickOn(btnAddActivities3_1, "btnActivities");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void clickOnAddActivityButtonScope1() {
		try {
			sleep(2000);
			Actions action = new Actions(driver);
		sleep(4000);
		extractEmissionValueBeforeGenerate();
		waitForElement(btnActivities_scope1);
		jsClick(btnActivities_scope1, "btnActivities_scope1");
			sleep(3000);
			if(isElementPresentDynamicXpath("//article[@aria-label='View Organization Data']")) {
				WebElement iconViewOrgData = driver.findElement(By.xpath("//article[@aria-label='View Organization Data']"));
				jsClick(iconViewOrgData, "View Organization Data");
			}
			sleep(4000);
			waitForElement(btnActivities_scope1);
			jsClick(btnActivities_scope1, "btnActivities_scope1");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
		
	}
	//button[text()='Mobile Combustion']
	
	@FindBy(xpath = "//button[text()='Mobile Combustion']")
	public WebElement mobilecombustionCalc;
	public DirectEmissionsCalculatorPage clickOnMobileCombustion() {
		try {
			jsClick(mobilecombustionCalc, "Mobile Combustion");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
		return new DirectEmissionsCalculatorPage(driver, data);
	}

	public void clickOnAddActivityscope1() {
		try {
			extractEmissionValueBeforeGenerate();
			waitForElement(btnActivities_scope1);
			jsClick(btnActivities_scope1, "btnActivityScope1");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void clickOnActivityInActivitiesGrid() {
		try {
			WebElement weActivity = driver
					.findElement(By.xpath("//div[text()='" + data.get("FacilityName") + "']//parent::div[@role='row']"));
			clickOn(weActivity, "Activity of Facility" + data.get("FacilityName"));
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	public void extractTco2ValueEmployeeCommuity() {
		try {
			WebElement tco2Filed = driver.findElement(By.xpath("//span[text()='tCO2e']//parent::div//parent::div"));
			String combinedTco2e = tco2Filed.getText();
			System.out.println(combinedTco2e);
			String[] splittedTco2e = combinedTco2e.split("U+000A");
			String previousTco2e = splittedTco2e[1];
			Double.parseDouble(previousTco2e);
			System.out.println(GlobalVariables.tco2e);
		} catch (NumberFormatException e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	
	@FindBy(xpath = "//div[text()='Fuel and Energy Related Activities']")
	public WebElement weScope3_3FuelEnrgRelatAct;
	@FindBy(xpath = "//div[text()='Upstream Transportation and Distribution']")
	public WebElement weScope3_4UpStrmTrnprtnDstrbtn;
	@FindBy(xpath = "//div[text()='Downstream Transportation and Distribution']")
	public WebElement weScope3_9DownStrmTrnprtnDstrbtn;
	@FindBy(xpath = "//div[text()='Franchises']")
	public WebElement weScope3_14Franchises;
	@FindBy(xpath = "//div[text()='Home Office / Telecommuting']")
	public WebElement weScope3_7HomeOfficeTelecommuting;

	public FuelandEnergyRelatedActivitiesCalculatorPage clickOnScope3_3_Fuel_and_Energy_Related_Activities() {
		clickOn(weScope3_3FuelEnrgRelatAct, "Scope3.3Fuel&EnergyRelatedActivities");
		sleep(2000);
		return new FuelandEnergyRelatedActivitiesCalculatorPage(driver, data);
	}
//
	public UpstreamTransportationandDistributionCalculatorPage clickOnScope3_4_Upstream_Transportation_and_Distribution() {
		jsClick(weScope3_4UpStrmTrnprtnDstrbtn, "Scope3.4Upstream Transportation and Distribution");
		return new UpstreamTransportationandDistributionCalculatorPage(driver, data);
	}

	public HomeOfficeTelecommutingCalculatorPage clickOnScope3_7_Home_Office_Telecommuting() {
		jsClick(weScope3_7HomeOfficeTelecommuting, "Scope3.7 Home Office Telecommuting");
		sleep(2000);
		return new HomeOfficeTelecommutingCalculatorPage(driver, data);
	}

	public DownstreamTransportationandDistributionCalculatorPage clickOnScope3_9_Downstream_Transportation_and_Distribution() {
		jsClick(weScope3_9DownStrmTrnprtnDstrbtn, "Scope3.9 Downstream Transportation and Distribution");
		sleep(2000);
		return new DownstreamTransportationandDistributionCalculatorPage(driver, data);
	}
//
	public FranchisesCalculatorPage clickOnScope3_14_Franchises() {
		jsClick(weScope3_14Franchises, "Scope3.14 Franchises");
		sleep(2000);
		return new FranchisesCalculatorPage(driver, data);
	}
	
	public void verifyEditActivityUpdatedToastMessage() {
		try {
			By toast = By.xpath("//div[text()='Activity updated successfully']");
			if (isElementPresent(toast)) {
				passed("Successfully displayed toast message - Activity updated successfully");
			} else {
				failed(driver, "Failed to display toast message");
			}
		} catch (Exception e) {
			failed(driver, "Failed to display toast message");
		}
	}

	@FindBy(xpath = "//span[text()='Activity updated successfully']//parent::div/following-sibling::div//button")
	WebElement toastMessagePopUp;

	
	@FindBy(xpath = "//*[text()='Activity added successfully']")
	WebElement toast;
	
	public void verifyAddActivityUpdatedToastMessage() {
		try {
			//WebDriverWait wait=new WebDriverWait(driver,20000);
			highlightTheElement(toast);
			if (isElementPresent(toast)) {
				passed("Successfully displayed toast message - Activity added successfully");
			} else {
					failed(driver, "Failed to display toast message");
				}
			
			waitForElementForCalculators(lblActivityDetails_P,"RHP Navigation Buttons");
		} catch (Exception e) {
			failed(driver, "Failed to display toast message");
		}

	}
	@FindBy(xpath="//*[text()='Activity updated successfully']")
	public WebElement toastMessageEdit;
	public void verifyEditActivityToastMessage() {
		try {
//			WebDriverWait wait=new WebDriverWait(driver,20000);
			highlightTheElement(toastMessageEdit);
			if (isElementPresent(toastMessageEdit)) {
				passed("Successfully displayed toast message - Activity updated successfully");
			} else {
					failed(driver, "Failed to display toast message");
				}
			waitForElementForCalculators(lblActivityDetails_P,"RHP Navigation Buttons");
		} catch (Exception e) {
			failed(driver, "Failed to display toast message");
		}

	}

	public void verifyCEFAddToastMessage() {
		try {
			By toast = By.xpath("//div[text()='Custom Emission Factor added successfully']");
			if (isElementPresent(toast)) {
				passed("Successfully displayed toast message - Custom Emission Factor added successfully");
			} else {
				if (isElementPresent(toast)) {
					passed("Successfully displayed toast message - Custom Emission Factor added successfully");
				} else {
					failed(driver, "Failed to display toast message");
				}
			}
			waitForElementForCalculators(lblActivityDetails_P,"RHP Navigation Buttons");
		} catch (Exception e) {
			failed(driver, "Failed to display toast message");
		}
	}

	@FindBy(xpath = "//span[contains(text(),'Name of Custom')]/parent::*/following-sibling::*")
	public WebElement CEFName;
	public void verifyCEFEditToastMessage() {
		try {
			By toast = By.xpath("//div[text()='Custom Emission Factor updated successfully']");
			if (isElementPresent(toast)) {
				passed("Successfully displayed toast message - Custom Emission updated successfully");
			} else {
				if (isElementPresent(toast)) {
					passed("Successfully displayed toast message - Custom Emission updated successfully");
				}else {
					failed(driver, "Failed to display toast message");
				}
			}
			waitForElementForCalculators(lblActivityDetails_P,"RHP Navigation Buttons");
		} catch (Exception e) {
			failed(driver, "Failed to display toast message");
		}
	}
	
	@FindBy(xpath = "//div[text()='Capital Goods']")
	public WebElement weScope3Capital_Good_Ser;

	public CapitalGoodsGHGCalculatorPage clickOnScope3_2CapitalGoods() {
		clickOn(weScope3Capital_Good_Ser, "Scope1DirectEmissions");
		return new CapitalGoodsGHGCalculatorPage(driver, data);
	}
	
	
	public void clickOnCancelButtonInActivityDetails() {
		waitForElement(btnCancelActivity);
		clickOn(btnCancelActivity, "btnCancelActivity");
	}
	@FindBy(xpath="//button[text()='Cancel']")
	public WebElement cancelButtonBeforeAdding;
	public void clickOnCancelButtonBeforeAdding() {
		waitForElement(cancelButtonBeforeAdding);
		clickOn(cancelButtonBeforeAdding, "cancelButtonBeforeAdding");
		
	}

	public void clickOnEditButtonInActivityDetails() {
		try {
			clickOn(btnEditActivityqa, "btnActivities");
		} catch (Exception e) {
			System.err.println("Exception caught while clicking Edit Button in RHP");
		}
	}

	public void clickOnEditButtonInActivityDetailsMC() {
		waitForElement(btnEditActivityMobileComb);
		clickOn(btnEditActivityMobileComb, "btnActivities");
	}

	public CapitalGoodsGHGCalculatorPage clickOnScope3_2SpendBased() {
		clickOn(btnScope3_2SpendBased, "Spend Based Scope3_2 Button");
		return new CapitalGoodsGHGCalculatorPage(driver, data);
	}

	
	@FindBy(xpath = "//div[text()='End-of-Life Treatment of Sold Products']")
	public WebElement weScope3_12EndOfLife;

	public EndOfLifeTreatmentGHGCalculatorPage clickOnScope3_12EndOfLifeTreat() {
		clickOn(weScope3_12EndOfLife, "Scope3_12End Of Life Treatment Sold Praducts");
		return new EndOfLifeTreatmentGHGCalculatorPage(driver, data);
	}
	
	public void extractTco2ValueEndOfLifeTreatment() {
		WebElement tco2Filed = driver.findElement(By.xpath("//span[text()='tCO2e']//parent::p//following-sibling::p"));
		String prevoiusTc02 = tco2Filed.getText();
		GlobalVariables.tco2e = Double.parseDouble(prevoiusTc02);
		System.out.println(GlobalVariables.tco2e);
	}
	
	@FindBy(xpath = "//div[text()='Hotel Stay']")
	public WebElement weScope3_HotelStay;

	public HotelStayGHGCalculatorPage clickOnScope3_6HotelStay() {
		clickOn(weScope3_HotelStay, "Click On Hotel Stay GHG Calculator");
		return new HotelStayGHGCalculatorPage(driver, data);
	}
	
	@FindBy(xpath = "//div[text()='Upstream Leased Assets']")
	public WebElement weScope3_8UpstreamLeasedAsset;

	public UpstreamLeasedAssetsGHGCalculatorPage clickOnScope3_8UpstreamLeasedAsset() {
		clickOn(weScope3_8UpstreamLeasedAsset, "Click On Upstream Leased Asset GHG Calculator");
		return new UpstreamLeasedAssetsGHGCalculatorPage(driver, data);
	}

	@FindBy(xpath = "(//button[text()='Cancel'])[2]")
	public WebElement btncancelLeasedAssets;

	public void clickOnCancelLeasedAssetInActivityDetails() {
		clickOn(btncancelLeasedAssets, "Cancel Button");
	}
	@FindBy(xpath = "//div[text()='Downstream Leased Assets']")
	public WebElement weScope3_8DownstreamLeasedAsset;

	public DownStreamLeasedAssetsGHGCalculatorPage clickOnScope3_13DownstreamLeasedAsset() {
		clickOn(weScope3_8DownstreamLeasedAsset, "Click On DownStream Leased Asset GHG Calculator");
		return new DownStreamLeasedAssetsGHGCalculatorPage(driver, data);
	}

	@FindBy(xpath = "//span[text()='Next']//parent::button//following-sibling::button[@type='submit']")
	public WebElement btnEditActivitypurchased;

	public void clickOnEditButtonInActivityDetails_1() {
		waitForElement(btnEditActivitypurchased);
		clickOn(btnEditActivitypurchased, "btnEditActivitypurchased");
	}
	
	public ProcessingofSoldProductsCalculatorPage clickOnScope3_10ProcessingOfSoldProduct() {
		clickOn(weScope3_10ProcessingSoldProduct, "Click On Processing of sold Products GHG Calculator");
		return new ProcessingofSoldProductsCalculatorPage(driver, data);
	}
	
	@FindBy(xpath = "//*[text()='Use of Sold Products']")
	public WebElement weScope3_1UseSoldProduct;

	public UseOfSoldProductGHGCalculatorPage clickOnScope3_11UseOfSoldProduct() {
		clickOn(weScope3_1UseSoldProduct, "Click On Use of sold Products GHG Calculator");
		return new UseOfSoldProductGHGCalculatorPage(driver, data);
	}
	
	@FindBy(xpath = "//div[text()='Investments']")
	public WebElement weScope3_15Investments;

	public InvestmentsGHGCalculatorsPage clickOnScope3_15Investments() {
		clickOn(weScope3_15Investments, "Click On Investments GHG Calculator");
		return new InvestmentsGHGCalculatorsPage(driver, data);
	}
	
	public void clickOnActivityInActivitiesGridScope3_15InvestmentSpecificMethod() {
		WebElement weActivityScope3_15 = driver
				.findElement(By.xpath("//*[text()='" + data.get("Investee Company") + "']//parent::div"));
		clickOn(weActivityScope3_15, "Activity Investee Company" + data.get("Investee Company"));
	}

	public void clickOnActivityInActivitiesGridScope3_15InvestmentSpecificMethodDebtandFinanc() {
		WebElement weActivityScope3_15 = driver
				.findElement(By.xpath("//*[text()='" + data.get("Investee Company/Project") + "']//parent::div"));
		clickOn(weActivityScope3_15, "Activity Investee Company" + data.get("Investee Company/Project"));
	}
	
	@FindBy(xpath = "//button[contains(text(),'Average Data Method')]")
	public WebElement btnInvestmentsAveargeMethod;

	public InvestmentsGHGCalculatorsPage clickOnScope3_11InvestmentsAverageDataMethod() {
		clickOn(btnInvestmentsAveargeMethod, "Click On Investment Average method Button GHG Calculator");
		return new InvestmentsGHGCalculatorsPage(driver, data);
	}

	@FindBy(xpath = "//button[contains(text(),'Investment Specific Method')]")
	public WebElement btnInvestmentsspecificMethod;

	public void clickOnScope3_11InvestmentsspecificDataMethod() {
		sleep(3000);
		clickOn(btnInvestmentsspecificMethod, "Click On Investment specific method Button GHG Calculator");
	}
	
	@FindBy(xpath = "//button[contains(text(),'Debt & Project Financing')]")
	public WebElement btnInvestmentsDebt_Project;

	public InvestmentsGHGCalculatorsPage clickOnScope3_11InvestmentsDept_ProjectFinancing() {
		clickOn(btnInvestmentsDebt_Project, "Click On Investment Debt and Project Financing Button GHG Calculator");
		return new InvestmentsGHGCalculatorsPage(driver, data);
	}
	
	public void SelectPeriod() {
		clickOn(drpPeriodDwn, "period drop down");
		verifyIfElementPresent(lblDropPeriod, "lblDropPeriod", "lblDropPeriod");
		sleep(1000);
		WebElement perioddrp = driver
				.findElement(By.xpath("//ul/li[text()='" + (Constants.overlapPeriodYear2022) + "']"));
		clickOn(perioddrp, "clicked ");
		sleep(2000);
	}
	public void SelectPeriod2022() {                                                                               //naveen
		try {
			sleep(2001);
			WebElement drpPeriodDwn=driver.findElement(By.xpath("//span[text()='Period']//following-sibling::div"));
			jsClick(drpPeriodDwn, "period drop down");
			WebElement perioddrp = driver 
						.findElement(By.xpath("//ul//*[text()='"+data.get("Period Year")+"']"));
			scrollTo(perioddrp);
			actionsClick(perioddrp, "clicked "+data.get("Period Year")+"");
		} catch (Exception e) {
			System.err.println("Exception caught While Selecting Period Year ---> "+ data.get("Period Year"));
		}
	}
	
	public void SelectPeriod2024() {                                                                               //naveen
		try {
			clickOn(drpPeriodDwn, "period drop down");
			WebElement perioddrp = driver 
					.findElement(By.xpath("//li//div[text()='"+Constants.overlapPeriodYear2024+"']"));;
			actionsClick(perioddrp, "clicked "+Constants.overlapPeriodYear2022+"");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	

	@FindBy(xpath = "(//button[contains(text(),'Cancel')])[2]")
	public WebElement btnCancelScope1stat;

	public void clickOnbtnCancelScope1GHGCalculator() {
		clickOn(btnCancelScope1stat, "Cancel Button Stationary");
	}

	@FindBy(xpath = "//span[text()='Period']//parent::div//child::div//*[local-name()='svg']") ////span[text()='Period']//parent::div//child::div/div[@role='button']
	public WebElement drpPeriodDwn;
	String[] splittedValue;
	@FindBy(xpath = "//div[@id='menu-']//child::ul")
	public WebElement lblDropPeriod;

	public void calculateGHGEmissionBefore() {
		try {
			sleep(2000);
			SelectPeriod2022();
			WebElement btnGenerateRefresh = driver.findElement(By.xpath("//span[contains(text(),'i')]//parent::div//div//following::span//*[local-name()='svg']"));
			sleep(2000);
			clickOn(btnGenerateRefresh, "generate button");
			sleep(2000);                             
			WebElement totalco2Value = driver.findElement(By.xpath("//span[contains(text(),'Total(tCO2e)')]"));
			String beforeGertValue = totalco2Value.getText();
			splittedValue = beforeGertValue.split("-");
			GlobalVariables.initialTotaltCo2e = Double.parseDouble(splittedValue[1].replaceAll(",", ""));
			System.out.println(GlobalVariables.initialTotaltCo2e);
			System.out.println(Arrays.toString(splittedValue));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	public void extractTco2Value() {
		try {
			sleep(2000);
			WebElement tco2Filed = driver.findElement(By.xpath("//span[text()='tCO2e']//parent::p//following-sibling::p/div"));
			//WebElement tco2Filed = driver.findElement(By.xpath("//span[text()='tCO2e']//parent::p//following-sibling::p"));
//		WebElement tco2Filed = driver
//				.findElement(By.xpath("//span[text()='tCO2e']//parent::p//following-sibling::p"));

			sleep(1000);
			String prevoiusTc02 = tco2Filed.getText().replaceAll(",", "");
			GlobalVariables.tco2e = Double.parseDouble(prevoiusTc02);
			System.out.println(GlobalVariables.tco2e);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void extractTco2Value2() {
		sleep(2000);
		WebElement tco2Filed = driver
				.findElement(By.xpath("//span[text()='tCO2e']//parent::div//following-sibling::div"));
		String prevoiusTc02 = tco2Filed.getText();
		GlobalVariables.tco2e = Double.parseDouble(prevoiusTc02);
		System.out.println(GlobalVariables.tco2e);
	}

	public void extractTco2ValueULADLA() {
		sleep(2000);
		WebElement tco2Filed = driver.findElement(By.xpath("//span[text()='tCO2e']//parent::p//following-sibling::p"));
		sleep(1000);
		String prevoiusTc02 = tco2Filed.getText();
		GlobalVariables.tco2e = Double.parseDouble(prevoiusTc02);
		System.out.println(GlobalVariables.tco2e);
	}

	public void extractTco2Value_1() {
		try {
			sleep(1000);
			WebElement tco2Filed = driver
					.findElement(By.xpath("//span[text()='tCO2e']//parent::p//following-sibling::p/div"));
			String prevoiusTc02 = tco2Filed.getText();
			GlobalVariables.tco2e = Double.parseDouble(prevoiusTc02);
			System.out.println(GlobalVariables.tco2e);
		} catch (NumberFormatException e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	
	public void extractTco2Value_1_P() {
		try {
			String tco2Filed1 ="//span[text()='tCO2e']//parent::p//following-sibling::p/div";
			System.out.println(WaitForElementWithDynamicXpath(tco2Filed1));
			WebElement tco2Filed = driver.findElement(By.xpath(tco2Filed1));
			String prevoiusTc02 = tco2Filed.getText().replaceAll(",", "");
			GlobalVariables.tco2e = Double.parseDouble(prevoiusTc02);
			System.out.println(GlobalVariables.tco2e);
		} catch (NumberFormatException e) {
			failed(driver, "Exception caught " + e.getMessage());
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

	Double calculatedovrlap;

	public void collectGHGEmissionAfter() {
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
			Double frstval = (GlobalVariables.tco2e / differenceDays);
			Double resultTco2 = frstval * calculatedovrlap;
			GlobalVariables.listOverlaptco2.add(resultTco2);
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void calculateGHGEmissionBeforeIndirect() {
		try {
			SelectPeriod2022();
			sleep(1000);
			WebElement btnGenerateRefresh = driver.findElement(By.xpath("//span[@aria-label='Select refresh to calculate emissions totals. Any activities spread across multiple periods will be prorated by the number of days']"));
			sleep(2000);
			clickOn(btnGenerateRefresh, "generate button");
			sleep(2000);
			WebElement totalco2Value = driver
					.findElement(By.xpath("//span[contains(text(),'Location Based Emissions(tCO2e)')]"));
			String beforeGertValue = totalco2Value.getText();
			System.out.println(beforeGertValue);
			splittedValue = beforeGertValue.split("-");
			GlobalVariables.initialTotaltCo2e = Double.parseDouble(splittedValue[1].replaceAll(",", ""));
			System.out.println(GlobalVariables.initialTotaltCo2e);
			System.out.println(Arrays.toString(splittedValue));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickOnGenerateButton() {
		try {
			SelectPeriod2022();
			clickOn(btnGenerate, "generate button");
			WebElement totalco2Value = driver.findElement(By.xpath("//span[contains(text(),'Total(tCO2e)')]"));
			String afteregenrtvalue = totalco2Value.getText();
			String[] CO2valueafter = afteregenrtvalue.split("-");
			GlobalVariables.updatedTotaltCo2e = Double.parseDouble(CO2valueafter[1].replaceAll(",", ""));
			Double rounf_offActual = Math.round(GlobalVariables.updatedTotaltCo2e * 10000.0) / 10000.0;
			Double sum_of_totalTco2e = 0.0;
			Double roundOffCalculated = 0.0;
			for (int m = 0; m < GlobalVariables.listOverlaptco2.size(); m++) {
				sum_of_totalTco2e = sum_of_totalTco2e + GlobalVariables.listOverlaptco2.get(m);
				roundOffCalculated = Math.round((sum_of_totalTco2e + GlobalVariables.initialTotaltCo2e) * 10000.0)
						/ 10000.0;
			}
			if ((roundOffCalculated - rounf_offActual) == 0.0) {
				passed("activity passes overlap sceanrio as calculatd is" + " " + roundOffCalculated + " " + "and actual is"
						+ " " + rounf_offActual);
			} else {
				fail("activity failed overlap sceanrio as calculatd is" + " " + roundOffCalculated + " " + "and actual is"
						+ " " + rounf_offActual);
				System.out.println(
						"Failed beacuse of values are not matched and diffrenece between calculated and actual value are"
								+ ":" + (roundOffCalculated - rounf_offActual));
			}
		} catch (NumberFormatException e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public void clickOnParameterInput() {
		waitForElement(btnParameterInput);
		clickOn(btnParameterInput, "parameter input scope 2");
	}

	public void clickOnGenerateButtonIndirect() {
		sleep(3000);
		clickOn(drpPeriodDwn, "period drop down");
		for (int j = 0; j < drpActvityType.size(); j++) {
			if (drpActvityType.get(j).getText().equals(Constants.overlapPeriodYear2022)) {
				clickOn(drpActvityType.get(j), "selecting the period");
				sleep(1000);
				break;
			}
		}
		clickOn(btnGenerate, "generate button");
		sleep(3000);

		WebElement totalco2Value = driver
				.findElement(By.xpath("//span[contains(text(),'Location Based Emissions(tCO2e)')]"));
		String afteregenrtvalue = totalco2Value.getText();
		String[] CO2valueafter = afteregenrtvalue.split("-");
		GlobalVariables.updatedTotaltCo2e = Double.parseDouble(CO2valueafter[1]);
		Double rounf_offActual = Math.round(GlobalVariables.updatedTotaltCo2e * 10000.0) / 10000.0;
		Double sum_of_totalTco2e = 0.0;
		Double roundOffCalculated = 0.0;
		for (int m = 0; m < GlobalVariables.listOverlaptco2.size(); m++) {
			sum_of_totalTco2e = sum_of_totalTco2e + GlobalVariables.listOverlaptco2.get(m);
			roundOffCalculated = Math.round((sum_of_totalTco2e + GlobalVariables.initialTotaltCo2e) * 10000.0)
					/ 10000.0;
		}
		if ((roundOffCalculated - rounf_offActual) == 0.0) {
			passed("activity passes overlap sceanrio as calculatd is" + " " + roundOffCalculated + " " + "and actual is"
					+ " " + rounf_offActual);
		} else {
			fail("activity failed overlap sceanrio as calculatd is" + " " + roundOffCalculated + " " + "and actual is"
					+ " " + rounf_offActual);
			System.out.println(
					"Failed beacuse of values are not matched and diffrenece between calculated and actual value are"
							+ ":" + (roundOffCalculated - rounf_offActual));
		}

	}

	public IndirectEmissionsCalculatorPage clickOnIndirectemissionCalculator() {
		clickOn(btnIndirectEmisssion, "Scope 2 Indirect Emission");
		return new IndirectEmissionsCalculatorPage(driver, data);
	}
	
	public void clickOnMobileCombutionScope1GHGCalculator() {
		try {
			sleep(2000);
			clickOn(btnMobileCombution, "Mobile Combution Scope1");
			sleep(3000);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//article[@aria-label='Add Activity']")
	public WebElement btnActivities;
	public void clickOnAddActivity() {
		sleep(5000);
		clickOn(btnActivities, "btnActivities");
	}

	public void clickOnAddActivity_ForDifferantLangauge() {
		sleep(5000);
		clickOn(btnActivitiesLanguage, "btnActivities");
	}
	
	public CEFPage clickOnEmissionFactors() {
		sleep(4000);
		WebElement clickOnEmissionFactors = driver.findElement(By.xpath("(//ul[@role='tree']//li/div)[4]"));
		clickOn(clickOnEmissionFactors, "clicked");
	sleep(2000);		
		return new CEFPage(driver, data);
	}


	public void clickOnGenerateBut() {
		sleep(3000);
		clickOn(drpPeriodDwn, "period drop down");
		for (int j = 0; j < drpActvityType.size(); j++) {
			if (drpActvityType.get(j).getText().equals(Constants.overlapPeriodYear2022)) {
				clickOn(drpActvityType.get(j), "selecting the period");
				sleep(1000);
				break;
			}
		}
		clickOn(btnGenerate, "generate button");
		sleep(3000);
	}




	@FindBy(xpath = "//article[@aria-label='View Organization Data']")
	public WebElement viewOrg;

	public void ClickOnViewOrg() {
		isElementPresent(viewOrg);
		clickOn(viewOrg, "clicked on View Organisation Data");
	}

	
	
	public void clickOnFacilities() {
		try {
			sleep(1000);
			WebElement facility = driver.findElement(By.xpath("//ul//li[@aria-label='Facilities']"));
			clickOn(facility, "clicked");
			sleep(5000);
			SelectPeriod2022();
			enterText(searchtxt, "facilityname", data.get("Facility Name"));
			clickOn(searchfacility, "clicked");
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	//---------------------------------------------------------Transaction menu ------------------------------------------------>
	
	
	public void clickOnTransaction() {
		try {
			sleep(2000);
			WebElement transactionMenu = driver.findElement(By.xpath("//ul//li[@aria-label='Transactions']"));
			clickOn(transactionMenu, "Transaction Menu in GHGCalculaztor");
			searchForOrganizationInAllOrganizations();
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
		
	}
	
	public void searchForOrganizationInAllOrganizations() {
		try {
			WebElement txtsearchingForOrganization = driver.findElement(By.xpath("//input[@placeholder='Search']"));
			verifyIfElementPresent(txtsearchingForOrganization, "Search tab for Organization", "GHGCalculator");
			clickOn(txtsearchingForOrganization, "Search tab for Organization");
			enterText(txtsearchingForOrganization, "Search tab for Organization", data.get(""));
			WebElement optOrganizationName = driver.findElement(By.xpath("//div[text()='"+data.get("OrganizationName")+"']"));
			waitForElement(optOrganizationName);
			clickOn(optOrganizationName, data.get("OrganizationName"));
			sleep(3000);
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}
	
	public DirectEmissionsCalculatorPage clickOnButtonTransactionsTypes() {
		try {
			WebElement btnTransactionType = driver.findElement(By
					              .xpath("//div[text()='"+data.get("Transaction Type")+"']/parent::div/preceding-sibling::div"));
			verifyIfElementPresent(btnTransactionType, "Transaction Type", "GHGCalculator");
			clickOn(btnTransactionType, data.get("Transaction Type"));
			sleep(5000);
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
		return new DirectEmissionsCalculatorPage(driver, data);
	}
	
	@FindBy(xpath = "//div[@aria-label='Without label']")
	public WebElement drpLanguage;
	@FindBy(xpath = "//button[text()='Yes']")
	public WebElement btnYes;
	public void selectLanguage(String language) {
		try {
			waitForElement(drpLanguage);
			clickOn(drpLanguage, "drpLanguage");
			WebElement welanguageName = driver.findElement(By.xpath("//li[text()='"+language+"']"));
			clickOn(welanguageName, language);
			if(isElementPresent(btnYes)) {
				clickOn(btnYes, language);
				sleep(1000);
			}
			WebElement weSelectedlanguage = driver.findElement(By.xpath("//div[text()='"+language+"']"));
			if(isElementPresent(weSelectedlanguage)) {
				passed("Successfully validated language  in application as _----->  " +   language);
			}else {
				failed(driver, "Failed to  validate language in application as _----->  " +   language);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public void clickOnCalculator(int indexCalc) {
		sleep(1000);
		WebElement weCalcName = driver.findElement(By.xpath("(//div[@class='cardContent'])["+indexCalc+"]"));
		sleep(500);
		verifyElementAndHighlightIfExists(weCalcName, "weCalcName", "weCalcName");
		clickOn(weCalcName, "Clicked on the Calculator   ->");
	}
	@FindBy(xpath = "//div[text()='Transaction added successfully'")
	public WebElement toastMessageForAddingTransaction;
    public void verifyAddTransactionSuccessfulToastMessage() {
    	try {
			if(toastMessageForAddingTransaction.getText().trim().equals("Transaction added successfully"))    {
				passed("Successfully displayed toast message - Transaction added successfully");
			}
			else {
				failed(driver, "Failed to display toast message");
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
    }
    
    
    
    public static String approximateDecimalValueWithBigDecimalZero(String value) {
			BigDecimal myBigDecimal = new BigDecimal(value);
			BigDecimal newValue = myBigDecimal.setScale(0, RoundingMode.DOWN).stripTrailingZeros();
			System.out.println(newValue);
			return newValue.toPlainString();
		
	}
	
	
	
	
	
	
	public static String ValCO2eRHP;
	public void calculateTCO2eValueUsing_EmissionFactorValue(String amount) {                   //naveen
		
		try {
			sleep(1);
			Double ValCO2e;
			WebElement CO2eActivity = driver
					.findElement(By.xpath("//span[contains(text(),'CO2e')]/parent::p/following-sibling::p/div"));
			WebElement emissionFactor = driver.findElement(By
					            .xpath("//span[text()='Emission Factor']/parent::p/following-sibling::p/div"));
			String[] emissionFactor1 = emissionFactor.getText().split(" ");
			String emissionFactorValueRHP = emissionFactor1[0].replaceAll(",", "");
			//Formula
			ValCO2e = Double.parseDouble(emissionFactorValueRHP)
					                       * Double.parseDouble(amount) / 1000;
			String ValCO2eRHP1 = ValCO2e.toString();
			ValCO2eRHP = approximateDecimalValueWithBigDecimal(ValCO2eRHP1);
			String CO2eActivityStr = CO2eActivity.getText().replaceAll(",", "");
			String CO2eActivityStr3digit = approximateDecimalValueWithBigDecimal(CO2eActivityStr.replaceAll(",", ""));
			if (CO2eActivityStr3digit.equals(ValCO2eRHP)) {
				passed("Succesfully validated tCO2e value " + ValCO2eRHP + " And Actual is " + CO2eActivityStr3digit);
			} else {
				Double doubleDiff= Double.parseDouble(ValCO2eRHP) - Double.parseDouble(CO2eActivityStr3digit);
				Double doubleDiff1= Double.parseDouble(CO2eActivityStr3digit) - Double.parseDouble(ValCO2eRHP);
				if(doubleDiff<1 || doubleDiff>-1 || doubleDiff1<1 && doubleDiff<-1) {    
					passed("Succesfully validated tCO2e value" + ValCO2eRHP + " And Actual is " + CO2eActivityStr3digit);
				}else {
				    failed(driver,
						    "Failed validated tCO2e value" + " " + ValCO2eRHP + " But Actual is " + CO2eActivityStr3digit);
				}
			}
		} catch (NumberFormatException e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
		
		
	}
	
	
	
	
	@FindBy(xpath = "(//span[text()='CO2']/parent::*/following-sibling::*/div)[1]")
	private static WebElement CO2inEmissionFactor;
	@FindBy(xpath = "(//span[text()='CH4']/parent::p/following-sibling::p/div)[1]")
	private static WebElement CH4inEmissionFactor;
	@FindBy(xpath = "(//span[text()='N2O']/parent::p/following-sibling::p/div)[1]")
	private static WebElement N2OinEmissionFactor;
	public static Double CH4ValueEXP;
	public static Double N2OValueEXP;
	public static Double CO2Value;
	public static String expCO2NumereatorUnit;
	public static String expCO2DenoMinatorUnit;
	public static String expCH4NumereatorUnit;
	public static String expCH4DenoMinatorUnit;
	public static String expN2ONumereatorUnit;
	public static String expN2ODenoMinatorUnit;
	public static void getCO2_CH4_N20Value() {                                                  //naveen
		try {
            sleep(2);
			     CO2Value		   = Double.parseDouble(CO2inEmissionFactor.getText().split(" ")[0].replaceAll(",", ""));
			String[] ch4		   = CH4inEmissionFactor.getText().replaceAll(",", "").split(" ");
			String[] n2o		   = N2OinEmissionFactor.getText().replaceAll(",", "").split(" ");
			 expCO2NumereatorUnit  = CO2inEmissionFactor.getText().replaceAll(",", "").split(" ")[1].split("/")[0];
			 expCO2DenoMinatorUnit = CO2inEmissionFactor.getText().replaceAll(",", "").split(" ")[1].split("/")[1].split(" ")[0];
			 expCH4NumereatorUnit  = CH4inEmissionFactor.getText().replaceAll(",", "").split(" ")[1].split("/")[0];
			 expCH4DenoMinatorUnit = CH4inEmissionFactor.getText().replaceAll(",", "").split(" ")[1].split("/")[1].split(" ")[0];
			 expN2ONumereatorUnit  = N2OinEmissionFactor.getText().replaceAll(",", "").split(" ")[1].split("/")[0];
			 expN2ODenoMinatorUnit = N2OinEmissionFactor.getText().replaceAll(",", "").split(" ")[1].split("/")[1].split(" ")[0];
			//CH4
			 String p = ch4[0];
			Double CH4Value = Double.parseDouble(ch4[0]);            
			String CH4Value1 = CH4Value.toString();
			String CH4Value2 = approximateDecimalValueWithBigDecimal(CH4Value1);
			 CH4ValueEXP = Double.parseDouble(CH4Value2);
			 //N2O
			 String s = n2o[0];
			Double N2OValue = Double.parseDouble(n2o[0]);
			String N2OValue1 = N2OValue.toString();
			String N2OValue2 = N2OValue1;                         //approximateDecimalValueWithBigDecimal(N2OValue1);
			 N2OValueEXP = Double.parseDouble(N2OValue2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String emmissionFactorValueExp;
	public void calculateEmissionFactorUsingFormula() {                                            //naveen
		try {
			WebElement emissionFactor = driver.findElement(By
			        .xpath("//span[text()='Emission Factor']/parent::p/following-sibling::p/div"));
			String[] emissionFactor1 = emissionFactor.getText().split(" ");
			String emissionFactorValueRHP = emissionFactor1[0];
			getCO2_CH4_N20Value();
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
				 n2o = Constants.GWPar5N2O* GHGCalculatorsPage.N2OValueEXP;
			}else {
				 co2 = Constants.GWParCO2 * GHGCalculatorsPage.CO2Value;
				 ch4 = Constants.GWPar6CH4 * GHGCalculatorsPage.CH4ValueEXP;                 
				 n2o = Constants.GWPar6N2O * GHGCalculatorsPage.N2OValueEXP;
			}
			Double valOfEmission = co2 + ch4 + n2o;                           
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
	
	
	
	
	@FindBy(xpath = "//ul//li[contains(@aria-label,'GHG Settings')]")
    public WebElement ghgemissionsetup;
    @FindBy(xpath = "//h1[contains(text(),'GHG Settings')]")
    public WebElement emissionsetup;
    @FindBy(xpath = "//ui//span[text()='Carbon Management']")
    public WebElement carbonManagementbtn;
    @FindBy(xpath = "//button[text()='Save']")
    public WebElement buttonSave;
	
	public void clickOnGHGEmissionsSetup1() { // new code
        try {
            Actions act = new Actions(driver);
            clickOnElement(ghgemissionsetup, " GHG Emissions SetUp ");
            verifyIfElementPresent(emissionsetup, "GHG Emissions Calculators SetUp ",
                    " GHG Emissions Calculators Setup ");
            sleep(2000);
            WebElement gwpAR4 = driver.findElement(By
                    .xpath("(//h4[text()='Global Warming Potential']//parent::div//following-sibling::div//span)[3]"));
            scrollTo(gwpAR4);
            WebElement gwpAR5 = driver.findElement(By
                    .xpath("(//h4[text()='Global Warming Potential']//parent::div//following-sibling::div//span)[6]"));
            if (gwpAR4.getText().trim().equals(Constants.selectedGWP)) {
                sleep(2000);
                WebElement btnGwp = driver.findElement(By.xpath(
                        "(//input[@type='radio']//parent::span//*//*[@data-testid='RadioButtonCheckedIcon'])[4]"));
                act.moveToElement(btnGwp).click().perform();
                clickOn(buttonSave, "Save");
            } else if (gwpAR5.getText().trim().equals(Constants.selectedGWP)) {
                sleep(2000);
                WebElement btnGwp = driver.findElement(By.xpath(
                        "(//input[@type='radio']//parent::span//*//*[@data-testid='RadioButtonCheckedIcon'])[5]"));
                act.moveToElement(btnGwp).click().perform();
                clickOn(buttonSave, "Save");
            }
            clickOnElement(carbonManagementbtn, " Carbon Management ");
            sleep(3000);
        } catch (Exception e) {
            failed(driver, "Exception caught" + e.getMessage());
        }
    }
	
	@FindBy(xpath = "//button[text()='GHG Inventory']")
    public WebElement lblGHGInventory;
	@FindBy(xpath = "//*[text()='Confirm']")
	public WebElement btnConfirm;
	@FindBy(xpath = "//*[text()='Global Warming Potential']")
	public WebElement headerGWp;
	
	public void clickOnGHGEmissionsSetup() {                                               // new code Priyanka
        try {
        	sleep(1);
            Actions act = new Actions(driver);
            clickOnElement(ghgemissionsetup, " GHG Settings");
            verifyIfElementPresent(emissionsetup, "GHG Emissions Calculators SetUp ",
                    " GHG Emissions Calculators Setup ");
            sleep(3000);
            scrollTo(headerGWp);
            verifyIfElementPresent(lblGHGInventory, "GHG Inventory", "GHG Settings");
            WebElement periodGHG = driver.findElement(By.xpath("//div[@class='ghg-inventory-period']/div/div/div"));
            waitForElement(periodGHG);
            //act.moveToElement(periodGHG).click();
            clickOn(periodGHG, "period drop down");
            WebElement selectPeriod = driver.findElement(By.xpath("//li[text()='"+data.get("Period Year")+"']"));
            waitForElement(selectPeriod);//*[text()='Global Warming Potential']/../../following-sibling::div//div[@aria-labelledby='demo-radio-buttons-group-label']/div/label/span/following-sibling::span/span
            clickOn(selectPeriod, "Selected period is ==>"+ data.get("Period Year"));
            List<WebElement> GHGInveRadioButton = driver.findElements(By.xpath("//*[text()='Global Warming Potential']/../../following-sibling::div//div[@aria-labelledby='demo-radio-buttons-group-label']/div/label/span/input/.."));
            List<WebElement> GWPValue = driver.findElements(By.xpath("//*[text()='Global Warming Potential']/../../following-sibling::div//div[@aria-labelledby='demo-radio-buttons-group-label']/div/label/span/following-sibling::span/span"));
          //h4[text()='Global Warming Potential']/../../following-sibling::div//div[@aria-labelledby='demo-radio-buttons-group-label']/div/label/span/span[contains(@class,'y typo-sub-header')]
            String ValueOfGWP="";
            for (int i = 0; i < GHGInveRadioButton.size(); i++) {
                System.out.print(GWPValue.get(i).getText());
                 ValueOfGWP=GWPValue.get(i).getText();
                String checked = GHGInveRadioButton.get(i).getAttribute("class");
                if (checked.contains("Mui-checked")) {
                    System.out.println("Checked by default");
                    break;
                } else {
                    System.out.println("Not Checked by default");
                }
            }
            
            if(ValueOfGWP.contains(data.get("gwp year"))) {
            	System.out.println(data.get("gwp year")+"Checked by Auto");
            	
            	}else {
            	WebElement gwpAR = driver.findElement(By
                      .xpath("//span[contains(text(),'"+data.get("gwp year")+"')]//parent::span/parent::label/span/input"));
            	act.click(gwpAR).build().perform();
            	   clickOn(btnConfirm, "Confirm Button");
            	   for (int i = 0; i < GHGInveRadioButton.size(); i++) {
                       System.out.print(GWPValue.get(i).getText());
                        ValueOfGWP=GWPValue.get(i).getText();
                       String checked = GHGInveRadioButton.get(i).getAttribute("class");
                       if (checked.contains("Mui-checked")) {
                           System.out.println("Checked by Auto");
                           break;
                       } else {
                           System.out.println("Not Checked by Auto");
                       }
                   }
            	
            
//            if(data.get("gwp year").equals("AR4")) {
//                System.out.println(data.get("gwp year"));
//                 WebElement gwpAR4 = driver.findElement(By
//                         .xpath("//span[contains(text(),'"+data.get("gwp year")+"')]//parent::span/parent::label/span/input"));
//                 act.click(gwpAR4).build().perform();
//               
//                 
//                 
//                 
//                 
//                 clickOn(btnConfirm, "");
//                 for (int i = 0; i < GHGInveRadioButton.size(); i++) {
//                     System.out.print(GWPValue.get(i).getText());
//                     String checked = GHGInveRadioButton.get(i).getAttribute("class");
//                     if (checked.contains("Mui-checked")) {
//                         System.out.println("Checked by Auto");
//                         break;
//                     } else {
//                         System.out.println("Not Checked by default");
//                     }
//                 }
//
//            }else if(data.get("gwp year").equals("AR5")) {
//                System.out.println(data.get("gwp year"));
//                WebElement gwpAR5 = driver.findElement(By
//                        .xpath("//span[contains(text(),'"+data.get("gwp year")+"')]//parent::span/parent::label/span/input"));
//                sleep(1000);
//                act.click(gwpAR5).build().perform();
//               
//            }else {
//                System.out.println(data.get("gwp year"));
//                WebElement gwpAR6 = driver.findElement(By
//                           .xpath("//span[contains(text(),'"+data.get("gwp year")+"')]//parent::span/parent::label/span/input"));
//                   sleep(1000);
//                   act.doubleClick(gwpAR6).build().perform();
                   
            }
            takeScreenshot(driver);
           // clickOn(buttonSave, "Save");    
            
            sleep(3000);
        } catch (Exception e) {
            failed(driver, "Exception caught" + e.getMessage());
        }
    }
	
	
	
	public void clickOnGHGEmissionsSetupLatest() {
		sleep(1);
        Actions act = new Actions(driver);
        clickOnElement(ghgemissionsetup, " GHG Settings");
        verifyIfElementPresent(emissionsetup, "GHG Emissions Calculators SetUp ",
                " GHG Emissions Calculators Setup ");
        sleep(3000);
        scrollTo(headerGWp);
        verifyIfElementPresent(lblGHGInventory, "GHG Inventory", "GHG Settings");
        WebElement periodGHG = driver.findElement(By.xpath("//div[@class='ghg-inventory-period']/div/div/div"));
        waitForElement(periodGHG);
        //act.moveToElement(periodGHG).click();
        clickOn(periodGHG, "period drop down");
        WebElement selectPeriod = driver.findElement(By.xpath("//li[text()='"+data.get("Period Year")+"']"));
        waitForElement(selectPeriod);
        clickOn(selectPeriod, "Selected period is ==>"+ data.get("Period Year"));
        List<WebElement> GHGInveRadioButton = driver.findElements(By.xpath("//h4[text()='Global Warming Potential']/../../following-sibling::div//div[@aria-labelledby='demo-radio-buttons-group-label']/div/label/span/input/.."));
        List<WebElement> GWPValue = driver.findElements(By.xpath("//h4[text()='Global Warming Potential']/../../following-sibling::div//div[@aria-labelledby='demo-radio-buttons-group-label']/div/label/span/span[contains(@class,'y typo-sub-header')]"));
        for (int i = 0; i < GHGInveRadioButton.size(); i++) {
            System.out.print(GWPValue.get(i).getText());
            String checked = GHGInveRadioButton.get(i).getAttribute("class");
            if (checked.contains("Mui-checked")) {
                System.out.println("Checked by default");
                break;
            } else {
                System.out.println("Not Checked by default");
            }
        }
		
	}
	
	public void validateGlobalWarmingPotentialValuesRelatedToAR() {
		try {
			String[] gwpValues = {"Assessment","CO2","CH4","N2O"};
			for(int i = 0; i<gwpValues.length; i++) {
				WebElement weGwpValues = driver.findElement(By
						.xpath("//p[text()='Global Warming Potential Values']/parent::div/parent::div/following-sibling::div"
								+ "//span[text()='"+gwpValues[i]+"']/parent::p/following-sibling::p/div"));
				String gwpValuesAR;
				if(data.get("gwp year").equals("AR4")) {
					String[] gwpValuesAR4 = {"2007 IPCC Fourth Assessment (AR4)",String.valueOf(Constants.GWParCO2),String.valueOf(Constants.GWPar4CH4)
							                 ,String.valueOf(Constants.GWPar4N2O)};  //2014 IPCC Fifth Assessment (AR5)
					 gwpValuesAR = gwpValuesAR4[i];
				}else if(data.get("gwp year").equals("AR5")) {
					String[] gwpValuesAR5 = {"2014 IPCC Fifth Assessment (AR5)",String.valueOf(Constants.GWParCO2),String.valueOf(Constants.GWPar5CH4)
							                 ,String.valueOf(Constants.GWPar5N2O)};
					 gwpValuesAR = gwpValuesAR5[i];
				}else {
					String[] gwpValuesAR6 = {"2023 IPCC Sixth Assessment (AR6)",String.valueOf(Constants.GWParCO2),String.valueOf(Constants.GWPar6CH4)
							                 ,String.valueOf(Constants.GWPar6N2O)};
					 gwpValuesAR = gwpValuesAR6[i];
				}
				if(weGwpValues.getText().equals(gwpValuesAR)) {
					passed("Successfully validated, "+data.get("gwp year")+" Expected value is "+gwpValuesAR+""
							+ " and the Actual value as "+weGwpValues.getText()+"");
				}else {
					Double doubleDiff= Double.parseDouble(weGwpValues.getText())- Double.parseDouble(gwpValuesAR.trim());
					//info(""+doubleDiff+"");
					if(doubleDiff<1 && doubleDiff>-0.4) {    
						passed("Succesfully validated activityDetail" + weGwpValues.getText() + " And Actual is " + gwpValuesAR.trim());
					}else {
					    failed(driver,
							    "Failed validated activityDetail" + " " + weGwpValues.getText() + " But Actual is " + gwpValuesAR.trim());
					}
					
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}
	
	
	@FindBy(xpath="//p[text()='Audit Log']")
	public WebElement audit;
	@FindBy(xpath="(//div[text()='tCO2e']//following-sibling::div[1])[1]")
	public WebElement tco2eValueinAuditLog;
	@FindBy(xpath="//p[text()='Activity Details']")
	public WebElement panelHeaderOfActDetails;
	@FindBy(xpath="//div[text()='tCO2e']/preceding-sibling::div/span/span/span[contains(@class,'closed')]")
	public WebElement auditOptionsExpand;
	//((//div[@id='panel1d-content'])[3]//p//div[@role='grid']/div/following-sibling::div/div//span[@role='presentation'])[2]
	//div[text()='tCO2e']/preceding-sibling::div/span/span/span[contains(@class,'closed')]

	private UpstreamLeasedAssetsGHGCalculatorPage upstreamLeasedAssetsGHGCalculatorPage;
	public void validateAuditLogForAllCalc(String exceptedtco2e){
		try {
			clickOn(audit,"Audit log for calc");
			sleep(1000);
			if(waitForElement(auditOptionsExpand) == true) {
				clickOn(auditOptionsExpand, "Audit Expand");
			}else {
				 clickOnCloseInActivityDetails();
				 clickOnActivityInActivitiesGridMultipleForAGGridEdit();
				 clickOn(audit,"Audit log for calc");
				 clickOn(auditOptionsExpand, "Audit Expand");
			}
			sleep(100);
			if(exceptedtco2e.equals(tco2eValueinAuditLog.getText())){
				passed("Successfully validated ,tco2e value in auditlog as "+tco2eValueinAuditLog.getText()+" ");
			}else{
				Double doubleDiff= Double.parseDouble(exceptedtco2e) - Double.parseDouble(tco2eValueinAuditLog.getText());
				//info(""+doubleDiff+"");
				if(doubleDiff<1 || doubleDiff>-1) {    
					passed("Succesfully validated tCO2e value" + exceptedtco2e + " And Actual is " + tco2eValueinAuditLog.getText());
				}else {
					failed(driver, "Failed to validate tco2e in auditlog --> excepted is "+exceptedtco2e+" "
							+ "and the actual as "+tco2eValueinAuditLog.getText()+"");
				}	
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
		
	}
	
	public void validateAuditLogForAllCalc(){
        try {
            clickOn(audit, "Audit log for calc");
            sleep(1000);
            if (waitForElement(auditOptionsExpand) == true) {
                clickOn(auditOptionsExpand, "Audit Expand");
            } else {
                clickOnCloseInActivityDetails();
                clickOnActivityInActivitiesGridMultipleTiffany();
                clickOn(audit, "Audit log for calc");
                clickOn(auditOptionsExpand, "Audit Expand");
            }
            sleep(500);
            String s = GlobalVariables.exceptedtco2e.replaceAll(",.", "");
            String c = tco2eValueinAuditLog.getText().trim().replaceAll(",.", "");
            if (s.equals(c)) {
                passed("Successfully validated ,tco2e value in auditlog as " + tco2eValueinAuditLog.getText() + " ");
            } else {
                Double doubleDiff = Double.parseDouble(s) - Double.parseDouble(c);
                // info(""+doubleDiff+"");
                if (doubleDiff < 1 && doubleDiff > -0.4) {
                    passed("Succesfully validated tCO2e value" + GlobalVariables.exceptedtco2e + " And Actual is "
                            + tco2eValueinAuditLog.getText());
                } else {
                    failed(driver,
                            "Failed to validate tco2e in auditlog --> excepted is " + GlobalVariables.exceptedtco2e
                                    + " " + "and the actual as " + tco2eValueinAuditLog.getText() + "");
                }
            }
        } catch (Exception e) {
            failed(driver, "Exception caught" + e.getMessage());
        }
    }
	
	
	public void clickOnCarbonManagementNavigationMenu() {
		try {
			sleep(500);
			Actions action = new Actions(driver);
			String btnGHGCalc1 = "//li[@aria-label='GHG Calculators']//*[local-name()='svg']";
			WebElement btnGHGCalc = driver.findElement(By.xpath(btnGHGCalc1));
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", btnGHGCalc);
			action.doubleClick(btnGHGCalc).build().perform();
			WebElement weGHGLabel = driver.findElement(By.xpath("//h5[text()='GHG Calculators']"));
			if(weGHGLabel.getText().equals("GHG Calculators")) {
				for(int i=0;i<10;i++) {System.out.println("user Navigated to GHG Calculators");}
			}
		} catch (Exception e) {
			//clickOnCarbonManagementNavigationMenu();
		}
	}
	
	public static void printNewTestCaseMessage(String TestCaseName) {                                                                                                              
		System.out.println("                                                                                                                ");
		System.out.println("                                                                                                                ");
		System.out.println("                                                                                                                ");
		System.out.println("*****************************************          "+TestCaseName+"          ********************************** ");
		System.out.println("                                                                                                                ");
		System.out.println("                                                                                                                ");
		System.out.println("                                                                                                                ");                                                                                                                                    
	}

	
	public static Boolean DoubleOrNot(String value) {
		try {
			Double d = Double.parseDouble(value);
			return true;
		} catch (Exception e) {
			return false;
		}  
}
	@FindBy(xpath = "//div[@name='center']")
	public WebElement AGGRID;
	@FindBy(xpath = "//input[@placeholder='Search']")
	public WebElement txtSearchOrgName;
	
	
	 public void SelectOrganizationForTiffany() {
    	 try {
    		 sleep(1);
    		 waitForElementForCalculators(AGGRID, "Ag Grid");
    		 String strOrgName = "//div[text()='"+data.get("OrgName")+"']";            
			 System.out.println("ORG Hierarchy visibility --> "+ WaitForElementWithDynamicXpath(strOrgName));
			 WebElement weOrgName = driver.findElement(By.xpath(strOrgName));
			 weOrgName.click();
    	 } catch (Exception e) {
			  System.err.println("Failed to select Organization in Heirarchy");
		}
     }
	 
	 
	
	 
	 
	 public void stepsToNavigateGHGCalculators() {
		 try {
			 SignInPage signInPage = new SignInPage(driver, data);
			 homePage = signInPage.SignInToPulsEsGApp();
			 menuBarPage = homePage.returnMenuPage();
			 menuBarPage.clickOnHamburgerMenu();
			 menuBarPage.clickOnGHGCalculatorsMenu();
			 clickOnGHGEmissionsSetup();
			 clickOnElement(carbonManagementbtn, " Carbon Management ");
		 } catch (Exception e) {
			 failed(driver, "Exception caught" + e.getMessage());
		 }
	 }
	 
	 public void Add$Edit$OverLapActivitiesFor_Calculators(String[] actDetails,String calcName,String Amount) {
		 try {
			 if(!Constants.arrayCardPresent.contains(data.get("CardName"))) {
				 Constants.arrayCardPresent.add(data.get("CardName"));
				 clickOnCarbonManagementNavigationMenu();
				 String calcCardName = "//*[text()='"+data.get("CardName")+"']";
				 clickOnElementWithDynamicXpath(calcCardName, data.get("CardName"));
			 }
			 String strcalcName = "//button[text()='"+data.get("subCalcName")+"']";
			 clickOnElementWithDynamicXpath(strcalcName, data.get("subCalcName"));
			 clickOnAddandEditActivitiesForCalculators();
			 AddActivitiesForCalculators();
			 validateActivityDetailsandEmissionDetails(actDetails, calcName, Amount);
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	 }
	 
	 
	 public void AddActivitiesForCalculators() {
		 try {
			switch (data.get("CardName")) {
			case "Direct Emissions":
				directEmissionsCalculatorPage = new DirectEmissionsCalculatorPage(driver, data);
				directEmissionsCalculatorPage.EditActivityForStationary_MobileCombutionAndFugitivesInDirectEmissions();
				break;
			case "Purchased Goods and Services":
				PGS = new PurchasedGoodsAndServicesGHGCalculatorPage(driver, data);
				if(data.get("subCalcName").equals("Average Data Method")) {
					PGS.EditActivityScope3EmissionsScope3_1_AverageBased();
				}else {
					PGS.EdittActivityScope3_1SpendBasedEmissions();
				}
				break;
			case "Capital Goods":
				CGS = new CapitalGoodsGHGCalculatorPage(driver, data);
				if(data.get("subCalcName").equals("Average Data Method")) {
					CGS.EditActivityScopeCapitalGoods3_2EmissionsAverageBased();
				}else {
					CGS.EditActivityCapitalGoodsScope3_2SpendBasedEmissions();
				}
				break;
			case "Hotel Stay":
				hotelStay = new HotelStayGHGCalculatorPage(driver, data);
				hotelStay.EditActivityScope3_6HotelStayEmissions();
				break;
			case "Upstream Leased Assets":
				ULA = new UpstreamLeasedAssetsGHGCalculatorPage(driver, data);
				ULA.EditActivityScope3_8UpstreamLeasedAssets();
				break;
			case "Downstream Leased Assets":
				DLA = new DownStreamLeasedAssetsGHGCalculatorPage(driver, data);
				DLA.EditActivityScope3_13DownstreamLeasedAssets();
				break;
			case "Processing of Sold Products":
				POSP = new ProcessingofSoldProductsCalculatorPage(driver, data);
				POSP.EditActivityScope3_10PrcessingofSoldProdutcs();
				break;
			case "Use of Sold Products":
				UOSP = new UseOfSoldProductGHGCalculatorPage(driver, data);
				UOSP.EditActivityScope3_11UseofSoldProdutcs();
				break;
			case "End-of-Life Treatment of Sold Products":
				EOLT = new EndOfLifeTreatmentGHGCalculatorPage(driver, data);
				EOLT.EditActivityEndOfLifeTreatmentScope3_12Emissions();
				break;
			case "Investments":
				Investments = new InvestmentsGHGCalculatorsPage(driver, data);
				if(data.get("Investment subName").equals("Equity Investments")){
					if(data.get("subCalcName").equals("Investment Specific Method")) {
						Investments.EditActivityScope3_15InvestmentsSpecificMethod();
					}else if(data.get("subCalcName").equals("Average Data Method")) {
						Investments.EditActivityScope3_15InvestmentsAverageDataMethod();
					}
				}else {
					 if(data.get("subCalcName").equals("Investment Specific Method")) {
						Investments.EditActivityScope3_15InvestmentsDept_ProjectFinancingSpecificMethod();
					}else if(data.get("subCalcName").equals("Average Data Method")) {
						Investments.EditActivityScope3_15InvestmentsDebtProjectFinancingAverageDataMethod();
					}
				}
			break;
			}
			sleep(1000);
//			VerifyEvidence();
//			ValidateEvidenceDetails();
//			expandActivityDetailsInRHP();
			
		} 
		 catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
		 
	 }
	 
	 public void clickOnAddandEditActivitiesForCalculators() {
		 try {
			 SelectPeriod2022();
			 if(Boolean.parseBoolean(data.get("OrgVisibility"))) {
				 SelectOrganizationForTiffany();
			 }
			 
			 
			 GHGCalculatorsPage.printNewTestCaseMessage(data.get("Activity"));
			if(data.get("Activity").equals("Add") || data.get("Activity").equals("Overlap")) {
				 if(data.get("Activity").equals("Overlap")) {
					 calculateGHGEmissionBefore();
				 }
				 clickOnAddActivityButton();
			 }else {
				 clickOnActivityInActivitiesGridMultipleForAGGridEdit();
				 clickOnEditButtonInActivityDetails();
			 }
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	 }
	 
	 public void validateToastMessageForActivityCalculators() {
		 try {sleep(1);
		     
			if(data.get("Activity").equals("Add")) {
				verifyAddActivityUpdatedToastMessage();
			 }else {
				verifyEditActivityToastMessage();
			 }
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	 }
	 
	 
	 
	 public void validateActivityDetailsandEmissionDetails(String[] actDetails,String calcName,String Amount) {
		 try {
			 
			 validateToastMessageForActivityCalculators();
			 sleep(1);
			 if(!data.get("Activity").equals("Overlap")) {
				 validateRHPActivityDetailsForAllCalculators(actDetails,calcName);
				 switch (calcName) {
				 case "SC": case "MC":
					 directEmissionsCalculatorPage = new DirectEmissionsCalculatorPage(driver, data);
					 directEmissionsCalculatorPage.validateActivityDetailsandEmissionDetailsStationaryCombustion(calcName, Amount);
					 break;
				 case "DLA": case "ULA":
					 upstreamLeasedAssetsGHGCalculatorPage = navigateToUpStreamLeasedAssert();
					 upstreamLeasedAssetsGHGCalculatorPage.validateTCO2EValueForUpstreamLeasedAsset(data.get("Activity Amount")
							 ,Constants.unitNamesEnergy, Constants.converionOfUnitsToMMBTU);
					 break;
				 case "InvestmentsSpecificMethod": case "InvestmentsDeptProjectFinancingSpecificMethod":
					 investmentsGHGCalculatorsPage = new InvestmentsGHGCalculatorsPage(driver, data);
					 investmentsGHGCalculatorsPage.validateAllScopesTC02eValuesForInvestments();
					 break;
				 case "InvestmentsAveraggeDataMethod": 
					 investmentsGHGCalculatorsPage = new InvestmentsGHGCalculatorsPage(driver, data);
					 investmentsGHGCalculatorsPage.TCO2eValidationForAverageDataMethodsForInvestments(calcName);
					 break;
				 case "InvestmentsDebtProjectFinancingAverageDataMethod":
					 break;
				 default:
					 calculateTCO2eValueUsing_EmissionFactorValue(Amount);
					 break;
				 }
				 clickOnCloseInActivityDetails();
				 clickOnGenerateButtonAlternate();
				 if(data.get("CardName").equals("Purchased Goods and Services")|| data.get("CardName").equals("Investments")||data.get("subCalcName").equals("Fugitives")){
					System.out.println();
				 }else {
					 clickOnActivityInActivitiesGridMultipleForAGGridEdit();
					 validateAuditLogForAllCalc(ValCO2eRHP);
					 clickOnCloseInActivityDetails();
				 }
				 }
			 else {
				extractTco2Value_1_P();
				clickOnCloseInActivityDetails();
				collectGHGEmissionAfter();
				clickOnGenerateButton();
			 }
			
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	 }
	 
	 public void validateRHPActivityDetailsForAllCalculators(String[] activityDetails,String calcName){
         
             sleep(1);
             String strActivityDetailes = null;
             String expectedActivity = null;
             for(int i=0;i<activityDetails.length;i++){
                 String activityFields;
                 if(calcName.contains("Investments")) {
                      activityFields="//span[text()=\""+activityDetails[i]+"\"]/parent::div/following-sibling::div";
                 }else if(data.get("CardName").contains("Capital") && Boolean.parseBoolean(data.get("Activity CEF")) 
                      ||data.get("CardName").contains("Use of Sold Products") && Boolean.parseBoolean(data.get("Activity CEF"))) {
                      activityFields="//span[normalize-space()='"+activityDetails[i]+"']/parent::*/following-sibling::*";
                 }else {
                      activityFields="//span[normalize-space()='"+activityDetails[i]+"']/parent::*/following-sibling::*/*";
                 }
                 
                     WebElement weActivityDetails = driver.findElement(By.xpath(activityFields));
                      strActivityDetailes = weActivityDetails.getText().replaceAll(",", "");
                      expectedActivity = data.get(activityDetails[i]).trim().replaceAll(",", "");
                     if(DoubleOrNot(strActivityDetailes) == true) {
                         strActivityDetailes = strActivityDetailes.split("\\.")[0].toString();
                     }
                     if(strActivityDetailes.trim().equals(expectedActivity.trim())){
                         passed("Successfully validated, the "+activityDetails[i]+" as  --> "+weActivityDetails.getText());
                     }else {
                         if(DoubleOrNot(strActivityDetailes) == true) {
                             Double doubleDiff= Double.parseDouble(strActivityDetailes.trim()) - Double.parseDouble(expectedActivity.trim());
                             if(doubleDiff<1 && doubleDiff>-1) {    
                                 passed("Succesfully validated activityDetail is "+activityDetails[i]+", Expected is " + strActivityDetailes 
                                         + " And Actual is " + expectedActivity.trim());
                             }else {
                                 failed(driver,"Failed validated, activityDetail is "+activityDetails[i]
                                         +", Expected is " + strActivityDetailes 
                                         + " But Actual is " + expectedActivity.trim());
                             }
                         }else {
                                 failed(driver, "Failed to validate the activityDetail "+activityDetails[i]
                                         +" Expected as "+expectedActivity.trim()+" "
                                         + "and the actual as "+strActivityDetailes+"");
                         }
                     }
             }
         
     }
	 
	 @FindBy(xpath = "//div[contains(text(),'Fuels')]//parent::div//*[@fill='none']")
		private WebElement customchevronicon;
		
		public void clickOnEmissionFactorsForDirectEmission() {
			try {
				sleep(1);
				Actions sda = new Actions(driver);
				String strtabFacilityIcon = "//ul//li[contains(@aria-label,'Emission Factors')]";
				WaitForElementWithDynamicXpath(strtabFacilityIcon);
				WebElement tabFacilityIcon = driver.findElement(By.xpath(strtabFacilityIcon));
				sda.moveToElement(tabFacilityIcon).click().build().perform();
				waitForElementForCalculators(fullAGGridNameOFCEF,"AG Grid");
				sda.moveToElement(customchevronicon).click().perform();
				WebElement energytype = driver
						.findElement(By.xpath("//ul[@role='listbox']//li[text()='" + data.get("cusemissiontype") + "']"));
				clickOn(energytype, data.get("cusemissiontype"));
			} catch (Exception e) {
				failed(driver, "Exception caught" + e.getMessage());
			}
			
		}
	 
	 
	 
	 

	 @Override
		protected void VerifyNavigationToValidPage() {
			try {
//				waitForElement(lblGHGCalculator);
//				if (isElementPresent(lblGHGCalculator)) {
//					passed("User Successfully Navigated To GHG_Calculator Page");
//				} else {
//					failed(driver, "Failed To Navigate To GHG_Calculator Page");
//				}
//				takeScreenshot(driver);
			} catch (Exception e) {
				failed(driver, "Exception caught " + e.getMessage());
			}
		}
	
	/* ---------------------------------------  --------------------------------------------------s*/
	
	
	/* -----------------------------------------------  Calculators --------------------------------------------------------*/
	
	
//	@FindBy(xpath = "//h6[contains(text(),'GHG Calculators')]")
//	public WebElement lblGHGCalculator;
//	@FindBy(xpath = "//div[contains(text(),'SCOPE 2')]")
//	public WebElement btnIndirectEmisssion;
//	@FindBy(xpath = "//span[contains(text(),'Activities')]//parent::div//article")
//	public WebElement btnbtnActivities;
//	@FindBy(xpath = "//button[@title='Open']")
//	public WebElement drpFacName;
//	@FindBy(xpath = "//*[@id='FacilityName']")
//	public WebElement txtFacName;
//	@FindBy(xpath = "(//span[text()='Energy Category']//parent::div//parent::div//following-sibling::div)[2]")
//	public WebElement dpdEnergyCategory;
//	@FindBy(xpath = "//div/ul/li[contains(text(),'Electricity')]")
//	public WebElement drpEclectricity;
//	@FindBy(xpath = "(//span[text()='Invoice No.'])[1]")
//	public WebElement lblInvoiceNo;
//	@FindBy(xpath = "//input[@id='InvoiceNo']")
//	public WebElement txtInvoiceNo;
//	@FindBy(xpath = "(//span[text()='Invoice No.'])[1]")
//	public WebElement selDropFacilityName;
//	@FindBy(xpath = "//input[@id='amount_of_energy']")
//	public WebElement txtAmountOfEnergy;
//	@FindBy(xpath = "(//span[text()='Units']/parent::div//parent::div//div)[4]")
//	public WebElement droUnits;
//	@FindBy(xpath = "(//button[text()='Save'])")
//	public WebElement btnSave;
//	@FindBy(xpath = "//div[@class='datePicker']//div//div")
//	public WebElement dpdPeriod;
//	@FindBy(xpath = "//*[@data-testid='CloseOutlinedIcon']")
//	public WebElement btnClose;
	
	
	
	
//	public void validateCalculatorScope2InPulsESG() {
//		try {
//			clickOn(btnIndirectEmisssion, "Indirect scope 2");
//			clickOn(btnbtnActivities, "Craeting Activities Transaction");
//			clickOn(drpFacName, "Facility Name");
//			Actions act = new Actions(driver);
//			act.moveToElement(selDropFacilityName).click().build().perform();
//			clickOn(dpdEnergyCategory, "Opening Energy Category");
//			clickOn(drpEclectricity, "Selecting Electricity Energy Category");
//			clickOn(lblInvoiceNo, "Label invoice No");
//			enterText(txtInvoiceNo, "InvoiceNo", data.get("InvoiceNo"));
//			enterText(txtInvoiceDate, "InvoiceDate", data.get("InvoiceDate"));
//			enterText(txtStartDate, "StartDate", data.get("StartDate"));
//			enterText(txtEndDate, "EndDate", data.get("EndDate"));
//			enterText(txtAmountOfEnergy, "AmountOfEnergy", data.get("AmountOfEnergy"));
//			clickOn(droUnits, "Select Units");
//			String xpathlisUnit = "//ul//li[contains(text(),'" + data.get("UnitsXpaths") + "')]";
//			clickOnElementWithDynamicXpath(xpathlisUnit, "UnitsXpaths");
//			clickOn(btnSave, "Click on Save Button");
//			clickOn(btnClose, "Close Button");
//			clickOn(dpdPeriod, "Scope 2 Period Drop Down ");
//			String xpathdropPeriod = "//li[contains(text(),'" + data.get("SelectPeriod") + "')]";
//			clickOnElementWithDynamicXpath(xpathdropPeriod, "SelectPeriod");
//			clickOn(btnGenerate, "Generate the Calculation");
//			WebElement locBasedEle = driver
//					.findElement(By.xpath("//span[contains(text(),'Location Based Emissions(tCO2e)')]"));
//			String localBaseText = locBasedEle.getText();
//			String[] localBaseValue = localBaseText.split("-");
//			try {
//				if (isNumber(localBaseValue[1].trim())) {
//					passed(localBaseValue[1] + " LocalBased CO2e Values are generated and updated Succesfully");
//				} else {
//					failed(driver, " Failed to generate the LocalBased values");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			WebElement marBasedEle = driver
//					.findElement(By.xpath("//span[contains(text(),'Market Based Emissions(tCO2e)')]"));
//			String marketBaseText = marBasedEle.getText();
//			String[] marketBaseValue = marketBaseText.split("-");
//			try {
//				if (isNumber(marketBaseValue[1].trim())) {
//					passed(marketBaseValue[1] + " MarketBase CO2e Values are generated and updated Succesfully");
//				} else {
//					failed(driver, " Failed to generate the MarketBase values");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	@FindBy(xpath = "//article//*[contains(text(),'GHG Calculators')]")
//	public WebElement lblGHGCalculators;
//	@FindBy(xpath = "//div[text()='Indirect Emissions']")
//	public WebElement btnGHGScope2;
//	@FindBy(xpath = "//span[text()='Activities']/parent::div//article")
//	public WebElement btnAddNewTranscation;
//	@FindBy(xpath = "//input[@id='invoiceNo']")
//	public WebElement txtInputVoice;
//	@FindBy(xpath = "//button[contains(text(),'Generate')]")
//	public WebElement btnGenerate;
//	@FindBy(xpath = "(//button[text()='Save'])[2]")
//	public WebElement btnSaveTranscation;
//	@FindBy(xpath = "//span[text()='Facility Name']//parent::div//following-sibling::div//input")
//	public WebElement drpFacilityName;
//	@FindBy(xpath = "//span[contains(text(),'Location Based Emissions')]")
//	public WebElement weLocationBasedEmissions;
//	@FindBy(xpath = "//span[contains(text(),'Market Based Emissions')]")
//	public WebElement weMarketBasedLocation;
//	@FindBy(xpath = "(//span[text()='Units']/parent::div//parent::div//div)[4]")
//	public WebElement txtUnit;
//	@FindBy(xpath = "//li[text()='kilowatt-hour (kWh)']")
//	public WebElement weKiloWattPerHour;
//	@FindBy(xpath = "(//span[text()='Energy Category']//parent::div//parent::div//following-sibling::div)[2]")
//	public WebElement drpEnergyCategory;
//	@FindBy(xpath = "//li[text()='Electricity']")
//	public WebElement weElectricity;
//	@FindBy(xpath = "//li[text()='Heat, Steam and Cooling']")
//	public WebElement weHeatSteamCooling;
//	@FindBy(xpath = "//span[text()='Period']//parent::div//following-sibling::div//span")
//	public WebElement drpPeriod;
//	@FindBy(xpath = "//input[@id='facilityName']")
//	public WebElement txtFacilityName;
//	@FindBy(xpath = "//input[@id='fuelName']")
//	public WebElement txtFuelType;
//	@FindBy(xpath = "//input[@id='unitType']")
//	public WebElement txtUnitType;
//	@FindBy(xpath = "//input[@id='unitName']")
//	public WebElement txtUnitName;
//	@FindBy(xpath = "//span[text()='Invoice Date']//parent::div//following-sibling::div//input")
//	public WebElement txtInvoiceDate;
//	@FindBy(xpath = "//span[text()='Expense Date']//parent::div//following-sibling::div//input")
//	public WebElement txtExpenseDate;
//	@FindBy(xpath = "//span[text()='Start Date']//parent::div//following-sibling::div//input")
//	public WebElement txtStartDate;
//	@FindBy(xpath = "//span[text()='End Date']//parent::div//following-sibling::div//input")
//	public WebElement txtEndDate;

//	public void selectFacility() throws AWTException {
//		clickOn(drpFacilityName, "facility name");
//		sleep(5000);
//		Robot robot = new Robot();
//		robot.keyPress(KeyEvent.VK_DOWN);
//		// This will press the down key on your numpad.
//		robot.keyRelease(KeyEvent.VK_DOWN);
//		// This will release the down key on your numpad.
//		robot.keyPress(KeyEvent.VK_ENTER);
//		// This will press the down key on your numpad.
//		robot.keyRelease(KeyEvent.VK_ENTER);
//		// This will release the down key on your numpad.
//	}

//	public void selectEnergyCategory(String energyType) {
//		clickOn(drpEnergyCategory, "EnergyCategory");
//		String xpath = "//li[text()='" + energyType + "']";
//		WebElement energy = driver.findElement(By.xpath(xpath));
//		clickOn(energy, energyType);
//	}
//
//	public void selectUnit(String unit) {
//		clickOn(txtUnit, "unit");
//		String xpath = "//li[text()='" + unit + "']";
//		WebElement unitOfEnergy = driver.findElement(By.xpath(xpath));
//		clickOn(unitOfEnergy, "unit");
//	}
//
//	public void selectPeriod(String period) {
//		clickOn(drpPeriod, "period");
//		String xpath = "//li[text()='" + period + "']";
//		WebElement Period = driver.findElement(By.xpath(xpath));
//		clickOn(Period, "period");
//		// li[text()='FY 2022']
//	}

//	public void addNewTranscation_FilterAndGenerate() {
//		try {
//			clickOn(btnGHGScope2, "indirect emissions");
//			clickOn(btnAddNewTranscation, "add new transaction");
//			selectFacility();
//			selectEnergyCategory("Electricity");
//			enterText(txtInputVoice, "invoice number", data.get("InvoiceNumber"));
//			clickOn(txtInvoiceDate, "invoice date");
//			enterText(txtInvoiceDate, "Invoice Date", data.get("InvoiceDate"));
//			enterText(txtStartDate, "Start Date", data.get("startDate"));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", data.get("endDate"));
//			enterText(txtAmountOfEnergy, "amount of energy", data.get("AmountOfEletricity"));
//			selectUnit("kilowatt-hour (kWh)");
//			clickOn(btnSaveTranscation, "save");
//			clickOn(btnClose, "close");
//			selectPeriod("FY 2022");
//			clickOn(btnGenerate, "generate");
//			sleep(3000);
//			passed(weLocationBasedEmissions.getText());
//			passed(weMarketBasedLocation.getText());
//		} catch (Exception e) {
//			failed(driver, "Exception caught in method addNewTranscation " + e.getMessage());
//		}
//	}
	// new
	// code-----------------------------------------------------------------------------------------------

//	@FindBy(xpath = "//*[text()='Add Activity']")
//	public WebElement lblAddActivity;
//	@FindBy(xpath = "//div[text()='Direct Emissions']")
//	public WebElement weDirectEmissions;
//	@FindBy(xpath = "//*[contains(text(),'Activity Details')]")
//	protected WebElement lblViewActivity;
////	@FindBy(xpath = "//h6[text()='Activity Details']")
////	public WebElement lblViewActivity;
//	@FindBy(xpath = "//span[text()='Fuel Amount']//parent::div//following-sibling::div//input")
//	public WebElement txtFuelAmount;
//	// Activity details
//	@FindBy(xpath = "//span[text()='Facility Name']//parent::div//following-sibling::div")
//	public WebElement weFacilityName;
//	@FindBy(xpath = "//span[text()='Invoice No.']//parent::div//following-sibling::div")
//	public WebElement weInVoiceNo;
//	@FindBy(xpath = "//span[text()='Invoice Date']//parent::div//following-sibling::div")
//	public WebElement weInVoiceDate;
//	@FindBy(xpath = "//span[text()='Start Date']//parent::div//following-sibling::div")
//	public WebElement weStartDate;
//	@FindBy(xpath = "//span[text()='End Date']//parent::div//following-sibling::div")
//	public WebElement weEndDate;
//	@FindBy(xpath = "//span[text()='Custom Emission Factor']//parent::div//following-sibling::div")
//	public WebElement weCustomEmissionFactor;
//	@FindBy(xpath = "//span[text()='Fuel Type']//parent::div//following-sibling::div")
//	public WebElement weFuelType;
//	@FindBy(xpath = "//span[text()='Fuel Amount']//parent::div//following-sibling::div")
//	public WebElement weFuelAmount;
//	@FindBy(xpath = "//span[text()='Unit Type']//parent::div//following-sibling::div")
//	public WebElement weUnitType;
//	@FindBy(xpath = "//span[text()='Unit']//parent::div//following-sibling::div")
//	public WebElement weUnit;
//	// emission details
//	@FindBy(xpath = "//span[text()='tCO2']//parent::div//following-sibling::div")
//	public WebElement weEmissionDetails_tCO2;
//	@FindBy(xpath = "//span[text()='tCH4']//parent::div//following-sibling::div")
//	public WebElement weEmissionDetails_tCH4;
//	@FindBy(xpath = "//span[text()='tN2O']//parent::div//following-sibling::div")
//	public WebElement weEmissionDetails_tN2O;
//	@FindBy(xpath = "//span[text()='tCO2e']//parent::div//following-sibling::div")
//	public WebElement weEmissionDetails_tCO2e;
//	@FindBy(xpath = "//span[text()='Biofuel tCO2']//parent::div//following-sibling::div")
//	public WebElement weEmissionDetails_BiofueltCO2;
//	@FindBy(xpath = "//span[text()='Biofuel CO2']//parent::div//following-sibling::div")
//	public WebElement weEmissionDetails_BiofuelCO2;
//	@FindBy(xpath = "//span[text()='tCO2e (Daily)']//parent::div//following-sibling::div")
//	public WebElement weEmissionDetails_tCO2e_Daily;
//	@FindBy(xpath = "//span[text()='Biofuel tCO2 (Daily)']//parent::div//following-sibling::div")
//	public WebElement weEmissionDetails_BiofueltCO2Daily;
//	@FindBy(xpath = "//span[text()='Emission Factor']//parent::div//following-sibling::div")
//	public WebElement weEmissionDetails_EmissionFactor;
//	@FindBy(xpath = "//article[@aria-label='Add Activity']")
//	public WebElement btnActivities;
//	@FindBy(xpath = "//button[text()='Close']")
//	public WebElement btnCloselatest;

	

	

//	public void AddActivityInDirectEmissions() {
//		try {
//			Thread.sleep(12000);
//			clickOn(btnActivities, "btnActivities");
//			verifyIfElementPresent(lblAddActivity, "lblAddActivity", "lblAddActivity");
//			Thread.sleep(5000);
//			clickOn(txtFacilityName, "txtFacilityName");
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("FacilityName") + "']"));
//			clickOn(we, data.get("FacilityName"));
//			sleep(2000);
//			clickOn(txtInputVoice, "invoice Number");
//			enterText(txtInputVoice, "invoice number", data.get("InvoiceNumber"));
//			clickOn(txtInvoiceDate, "invoice date");
//			enterText(txtInvoiceDate, "Invoice Date", data.get("InvoiceDate"));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", data.get("StartDate"));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", data.get("EndDate"));
//			clickOn(txtFuelType, "txtFacilityName");
//			we = driver.findElement(By.xpath("//li[text()='" + data.get("FuelType") + "']"));
//			clickOn(we, data.get("FuelType"));
//			sleep(2000);
//			clickOn(txtFuelAmount, "Fuel Amount");
//			enterText(txtFuelAmount, "Fuel Amount", data.get("FuelAmount"));
//			clickOn(txtUnitType, "Unit Type");
//			we = driver.findElement(By.xpath("//li[text()='" + data.get("UnitType") + "']"));
//			clickOn(we, data.get("UnitType"));
//			sleep(2000);
//			clickOn(txtUnitName, "Unit");
//			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit") + "']"));
//			clickOn(we, data.get("Unit"));
//			sleep(2000);
//			clickOn(btnSave, "Save Button");
//			// verification of toast after clicking save
//			By toastActivityScope1 = By.xpath("//div[text()='Activity added successfully']");
//			if (isElementPresent(toastActivityScope1)) {
//				passed("Successfully displayed toast message - Activity added successfully");
//			} else {
//				failed(driver, "Failed to display toast message");
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

//	public void AddActivityForStationaryCombutionInDirectEmissions(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("CalculatorStationaryCombustion",
//					"Facility Name");
//			List<String> invoiceNumb = MultiDataExcelReader.getTestData("CalculatorStationaryCombustion",
//					"Invoice No.");
//			List<String> invoiceDate = MultiDataExcelReader.getTestData("CalculatorStationaryCombustion",
//					"Invoice Date");
//			List<String> startDate = MultiDataExcelReader.getTestData("CalculatorStationaryCombustion", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("CalculatorStationaryCombustion", "End Date");
//			List<String> fuelType = MultiDataExcelReader.getTestData("CalculatorStationaryCombustion", "Fuel Type");
//			List<String> fuelAmount = MultiDataExcelReader.getTestData("CalculatorStationaryCombustion", "Fuel Amount");
//			List<String> unitType = MultiDataExcelReader.getTestData("CalculatorStationaryCombustion", "Unit Type");
//			List<String> units = MultiDataExcelReader.getTestData("CalculatorStationaryCombustion", "Unit");
//			Thread.sleep(5000);
//			clickOn(btnActivities, "btnActivities");
//			verifyIfElementPresent(lblAddActivity, "lblAddActivity", "lblAddActivity");
//			clickOn(txtFacilityName, "txtFacilityName");
//			enterText(txtFacilityName, "Facility Name", facilityName.get(i));
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			sleep(500);
//			clickOn(txtInputVoice, "invoice Number");
//			enterText(txtInputVoice, "invoice number", invoiceNumb.get(i));
//			clickOn(txtInvoiceDate, "invoice date");
//			enterText(txtInvoiceDate, "Invoice Date", invoiceDate.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			clickOn(txtFuelType, "txtFuelType");
//			we = driver.findElement(By.xpath("//li[text()='" + fuelType.get(i) + "']"));
//			clickOn(we, fuelType.get(i));
//			sleep(500);
//			clickOn(txtFuelAmount, "Fuel Amount");
//			enterText(txtFuelAmount, "Fuel Amount", fuelAmount.get(i));
//			clickOn(txtUnitType, "Unit Type");
//			we = driver.findElement(By.xpath("//li[text()='" + unitType.get(i) + "']"));
//			clickOn(we, unitType.get(i));
//			clickOn(txtUnitName, "Unit");
//			we = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			clickOn(we, units.get(i));
//			clickOn(btnSave, "Save Button");
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void EditActivityForStationaryCombutionInDirectEmissions(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("CalculatorStationaryCombustion",
//					"Facility Name");
//			List<String> invoiceNumb = MultiDataExcelReader.getTestData("CalculatorStationaryCombustion",
//					"Invoice No.");
//			List<String> invoiceDate = MultiDataExcelReader.getTestData("CalculatorStationaryCombustion",
//					"Invoice Date");
//			List<String> startDate = MultiDataExcelReader.getTestData("CalculatorStationaryCombustion", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("CalculatorStationaryCombustion", "End Date");
//			List<String> fuelType = MultiDataExcelReader.getTestData("CalculatorStationaryCombustion", "Fuel Type");
//			List<String> fuelAmount = MultiDataExcelReader.getTestData("CalculatorStationaryCombustion", "Fuel Amount");
//			List<String> unitType = MultiDataExcelReader.getTestData("CalculatorStationaryCombustion", "Unit Type");
//			List<String> units = MultiDataExcelReader.getTestData("CalculatorStationaryCombustion", "Unit");
//			Thread.sleep(3000);
//			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
//			clickOn(txtFacilityName, "txtFacilityName");
//			enterText(txtFacilityName, "Facility Name", facilityName.get(i));
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			sleep(500);
//			clickOn(txtInputVoice, "invoice Number");
//			enterText(txtInputVoice, "invoice number", invoiceNumb.get(i));
//			clickOn(txtInvoiceDate, "invoice date");
//			enterText(txtInvoiceDate, "Invoice Date", invoiceDate.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			clickOn(txtFuelType, "txtFuelType");
//			we = driver.findElement(By.xpath("//li[text()='" + fuelType.get(i) + "']"));
//			clickOn(we, fuelType.get(i));
//			sleep(500);
//			clickOn(txtFuelAmount, "Fuel Amount");
//			enterText(txtFuelAmount, "Fuel Amount", fuelAmount.get(i));
//			clickOn(txtUnitType, "Unit Type");
//			we = driver.findElement(By.xpath("//li[text()='" + unitType.get(i) + "']"));
//			clickOn(we, unitType.get(i));
//			clickOn(txtUnitName, "Unit");
//			we = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			clickOn(we, units.get(i));
//			clickOn(btnSave, "Save Button");
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void ValidateActivityDetailsInViewActivity() {
//		try {
//			Thread.sleep(5000);
//			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "Calculators");
//			waitForElement(weFacilityName);
//			if (weFacilityName.getText().trim().equals(data.get("FacilityName").trim())) {
//				passed("Successfully Validated FacilityName In Activity Details As" + weFacilityName.getText());
//			} else {
//				failed(driver, "Failed To validate Facility Name In Activity Details Expected As "
//						+ data.get("FacilityName") + "But Actual is" + weFacilityName.getText());
//			}
//			if (weInVoiceNo.getText().trim().equals(data.get("InvoiceNumber").trim())) {
//				passed("Successfully Validated InvoiceNumber In Activity Details As" + weInVoiceNo.getText());
//			} else {
//				failed(driver, "Failed To validate InvoiceNumber In Activity Details Expected As "
//						+ data.get("InvoiceNumber") + "But Actual is" + weInVoiceNo.getText());
//			}
//			if (weInVoiceDate.getText().trim().equals(data.get("InvoiceDate").trim())) {
//				passed("Successfully Validated InvoiceDate In Activity Details As" + weFacilityName.getText());
//			} else {
//				failed(driver, "Failed To validate InvoiceDate In Activity Details Expected As "
//						+ data.get("InvoiceDate") + "But Actual is" + weFacilityName.getText());
//			}
//			if (weStartDate.getText().trim().equals(data.get("StartDate").trim())) {
//				passed("Successfully Validated StartDate In Activity Details As" + weStartDate.getText());
//			} else {
//				failed(driver, "Failed To validate StartDate In Activity Details Expected As " + data.get("StartDate")
//						+ "But Actual is" + weStartDate.getText());
//			}
//			if (weEndDate.getText().trim().equals(data.get("EndDate").trim())) {
//				passed("Successfully Validated EndDate In Activity Details As" + weEndDate.getText());
//			} else {
//				failed(driver, "Failed To validate EndDate In Activity Details Expected As " + data.get("EndDate")
//						+ "But Actual is" + weEndDate.getText());
//			}
//			if (weCustomEmissionFactor.getText().trim().equals(data.get("CustomEmissionFactor").trim())) {
//				passed("Successfully Validated CustomEmissionFactor In Activity Details As"
//						+ weCustomEmissionFactor.getText());
//			} else {
//				failed(driver, "Failed To validate CustomEmissionFactor In Activity Details Expected As "
//						+ data.get("CustomEmissionFactor") + "But Actual is" + weCustomEmissionFactor.getText());
//			}
//			if (weFuelType.getText().trim().equals(data.get("FuelType").trim())) {
//				passed("Successfully Validated FuelType In Activity Details As" + weFuelType.getText());
//			} else {
//				failed(driver, "Failed To validate FuelType In Activity Details Expected As " + data.get("FuelType")
//						+ "But Actual is" + weFuelType.getText());
//			}
//			if (weFuelAmount.getText().trim().equals(data.get("FuelAmount").trim())) {
//				passed("Successfully Validated FuelAmount In Activity Details As" + weFuelAmount.getText());
//			} else {
//				failed(driver, "Failed To validate FuelAmount In Activity Details Expected As " + data.get("FuelAmount")
//						+ "But Actual is" + weFuelAmount.getText());
//			}
//			if (weUnitType.getText().trim().equals(data.get("UnitType").trim())) {
//				passed("Successfully Validated UnitType In Activity Details As" + weUnitType.getText());
//			} else {
//				failed(driver, "Failed To validate UnitType In Activity Details Expected As " + data.get("UnitType")
//						+ "But Actual is" + weUnitType.getText());
//			}
//			if (weUnit.getText().trim().equals(data.get("Unit").trim())) {
//				passed("Successfully Validated Unit In Activity Details As" + weUnit.getText());
//			} else {
//				failed(driver, "Failed To validate Unit In Activity Details Expected As " + data.get("Unit")
//						+ "But Actual is" + weUnit.getText());
//			}
//			// Emission Details
//			if (weEmissionDetails_tCO2.getText().trim().equals(data.get("tCO2").trim())) {
//				passed("Successfully Validated tCo2 In Emission Details As" + weEmissionDetails_tCO2.getText());
//			} else {
//				failed(driver, "Failed To validate tCo2 In Emission Details Expected As " + data.get("tCO2")
//						+ "But Actual is" + weEmissionDetails_tCO2.getText());
//			}
//			if (weEmissionDetails_tCH4.getText().trim().equals(data.get("tCH4").trim())) {
//				passed("Successfully Validated tCH4 In Emission Details As" + weEmissionDetails_tCH4.getText());
//			} else {
//				failed(driver, "Failed To validate tCH4 In Emission Details Expected As " + data.get("tCH4")
//						+ "But Actual is" + weEmissionDetails_tCH4.getText());
//			}
//			if (weEmissionDetails_tN2O.getText().trim().equals(data.get("tN2O").trim())) {
//				passed("Successfully Validated tN2O In Emission Details As" + weEmissionDetails_tN2O.getText());
//			} else {
//				failed(driver, "Failed To validate tN2O In Emission Details Expected As " + data.get("tN2O")
//						+ "But Actual is" + weEmissionDetails_tN2O.getText());
//			}
//			if (weEmissionDetails_tCO2e.getText().trim().equals(data.get("tCO2e").trim())) {
//				passed("Successfully Validated tCo2e In Emission Details As" + weEmissionDetails_tCO2e.getText());
//			} else {
//				failed(driver, "Failed To validate tCo2e In Emission Details Expected As " + data.get("tCO2e")
//						+ "But Actual is" + weEmissionDetails_tCO2e.getText());
//			}
////			System.out.println(weEmissionDetails_BiofueltCO2.getText());
////			System.out.println(data.get("BiofuelCO2"));
////			
////			if(weEmissionDetails_BiofueltCO2.getText().trim().equals(data.get("BiofuelCO2").trim())) {
////				passed("Successfully Validated BiofuelCO2e In Emission Details As"+weEmissionDetails_BiofueltCO2.getText());
////			}else {
////				failed(driver,"Failed To validate BiofuelCO2e In Emission Details Expected As "+data.get("BiofuelCO2")+"But Actual is"+weEmissionDetails_BiofueltCO2.getText());
////			}
////			
//			if (weEmissionDetails_BiofueltCO2Daily.getText().trim().equals(data.get("BiofuelCO2eDaily").trim())) {
//				passed("Successfully Validated BiofuelCO2eDaily In Emission Details As"
//						+ weEmissionDetails_BiofueltCO2Daily.getText());
//			} else {
//				failed(driver,
//						"Failed To validate BiofuelCO2eDaily In Emission Details Expected As "
//								+ data.get("BiofuelCO2eDaily") + "But Actual is"
//								+ weEmissionDetails_BiofueltCO2Daily.getText());
//			}
//			System.out.println(weEmissionDetails_EmissionFactor.getText());
//			if (weEmissionDetails_EmissionFactor.getText().trim().contains(data.get("EmissionFactor").trim())) {
//				passed("Successfully Validated EmissionFactor In Emission Details As"
//						+ weEmissionDetails_EmissionFactor.getText());
//			} else {
//				failed(driver, "Failed To validate EmissionFactor In Emission Details Expected As "
//						+ data.get("EmissionFactor") + "But Actual is" + weEmissionDetails_EmissionFactor.getText());
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	@FindBy(xpath = "//button[contains(text(),'Mobile Combustion')]")
//	public WebElement btnMobileCombution;
//	@FindBy(xpath = "//input[@id='activityType']")
//	public WebElement txtActivityTypeScope1;
//	@FindBy(xpath = "//input[@id='vehicleTypeName']")
//	public WebElement txtVehicleTypeScope1;
//	@FindBy(xpath = "//input[@id='activityAmount']")
//	public WebElement txtActivityAmountScope1;
//	@FindBy(xpath = "//input[@id='fuelSourceName']")
//	public WebElement txtFuelTypeMobileCombuScope1;
//
//	public void AddActivityMobileCombutionInDirectEmissions() {
//		try {
////	    clickOn(btnForwordScope1, "Forword Button Scope1");
//			clickOn(btnMobileCombution, "Mobile Combution Scope1");
//			Thread.sleep(12000);
//			clickOn(btnActivities, "btnActivities");
//			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
//			Thread.sleep(5000);
//			clickOn(txtFacilityName, "txtFacilityName");
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("FacilityName") + "']"));
//			clickOn(we, data.get("FacilityName"));
//			sleep(2000);
//			clickOn(txtInputVoice, "invoice Number");
//			enterText(txtInputVoice, "invoice number", data.get("InvoiceNumber"));
//			clickOn(txtInvoiceDate, "invoice date");
//			enterText(txtInvoiceDate, "Invoice Date", data.get("InvoiceDate"));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", data.get("StartDate"));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", data.get("EndDate"));
//			clickOn(txtActivityTypeScope1, "txtActivity Type Scope1");
//			we = driver.findElement(By.xpath("//li[text()='" + data.get("ActivityType") + "']"));
//			clickOn(we, data.get("ActivityType"));
//			Thread.sleep(5000);
//			clickOn(txtFuelTypeMobileCombuScope1, "Fuel Type");
//			we = driver.findElement(By.xpath("//li[text()='" + data.get("FuelType") + "']"));
//			clickOn(we, data.get("FuelType"));
//			Thread.sleep(5000);
//			clickOn(txtVehicleTypeScope1, "txtVaehicle Type");
//			we = driver.findElement(By.xpath("//li[text()='" + data.get("VehicleType") + "']"));
//			clickOn(we, data.get("VehicleType"));
//			Thread.sleep(5000);
//			clickOn(txtActivityAmountScope1, "Activity Amount Scope1");
//			enterText(txtActivityAmountScope1, "Fuel Amount", data.get("ActivityAmount"));
//			sleep(2000);
//			clickOn(txtUnitName, "Unit");
//			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit") + "']"));
//			clickOn(we, data.get("Unit"));
//			sleep(2000);
//			clickOn(btnSave, "Save Button");
//			// verification of toast after clicking save
//			By toastActivityScope1 = By.xpath("//div[text()='Activity added successfully']");
//			if (isElementPresent(toastActivityScope1)) {
//				passed("Successfully displayed toast message - Activity added successfully");
//			} else {
//				failed(driver, "Failed to display toast message");
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void AddActivityMobileCombutionInDirectEmissions(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("CalculatorMobileCombution", "Facility Name");
//			List<String> invoiceNumb = MultiDataExcelReader.getTestData("CalculatorMobileCombution", "Invoice No.");
//			List<String> invoiceDate = MultiDataExcelReader.getTestData("CalculatorMobileCombution", "Invoice Date");
//			List<String> startDate = MultiDataExcelReader.getTestData("CalculatorMobileCombution", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("CalculatorMobileCombution", "End Date");
//			List<String> fuelType = MultiDataExcelReader.getTestData("CalculatorMobileCombution", "Fuel Type");
//			List<String> activityType = MultiDataExcelReader.getTestData("CalculatorMobileCombution", "Activity Type");
//			List<String> vehicleType = MultiDataExcelReader.getTestData("CalculatorMobileCombution", "Vehicle Type");
//			List<String> activityAmount = MultiDataExcelReader.getTestData("CalculatorMobileCombution",
//					"Activity Amount");
//			List<String> units = MultiDataExcelReader.getTestData("CalculatorMobileCombution", "Unit");
//			Thread.sleep(5000);
//			clickOn(btnActivities, "btnActivities");
//			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
//			waitForElement(drpFacilityNameScope3_6);
//			clickOn(drpFacilityNameScope3_6, facilityName.get(i));
//			WebElement weFacility = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(weFacility, facilityName.get(i));
//			clickOn(txtInputVoice, "invoice Number");
//			enterText(txtInputVoice, "invoice number", invoiceNumb.get(i));
//			clickOn(txtInvoiceDate, "invoice date");
//			enterText(txtInvoiceDate, "Invoice Date", invoiceDate.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			clickOn(txtActivityTypeScope1, "txtActivity Type Scope1");
//			WebElement weActivityType = driver.findElement(By.xpath("//li[text()='" + activityType.get(i) + "']"));
//			clickOn(weActivityType, activityType.get(i));
//			Thread.sleep(1000);
//			clickOn(txtFuelTypeMobileCombuScope1, "Fuel Type");
//			Thread.sleep(1000);
//			WebElement weFuelType = driver.findElement(By.xpath("//li[text()='" + fuelType.get(i) + "']"));
//			clickOn(weFuelType, fuelType.get(i));
//			clickOn(txtVehicleTypeScope1, "txtVaehicle Type");
//			Thread.sleep(1000);
//			WebElement weVehicleType = driver.findElement(By.xpath("//li[text()='" + vehicleType.get(i) + "']"));
//			clickOn(weVehicleType, vehicleType.get(i));
//			clickOn(txtActivityAmountScope1, "Activity Amount Scope1");
//			enterText(txtActivityAmountScope1, "Fuel Amount", activityAmount.get(i));
//			clickOn(txtUnitName, "Unit");
//			enterText(txtUnitName, "txtUnitName", units.get(i));
//			WebElement weUnit = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			clickOn(weUnit, units.get(i));
//			clickOn(btnSave, "Save Button");
//			sleep(2000);
//			waitForElement(weEmissionDetails_tCO2e);
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

//	@FindBy(xpath = "//span[text()='Activity Type']//parent::div//following-sibling::div")
//	public WebElement weActivityTypeScope1;
//	@FindBy(xpath = "//span[text()='Custom Emission']//parent::div//following-sibling::div")
//	public WebElement weCustomEmissMobileComScope1;
//	@FindBy(xpath = "//span[text()='Vehicle Type']//parent::div//following-sibling::div")
//	public WebElement weVehicleTypeScope1;
//	@FindBy(xpath = "//span[text()='Activity Amount']//parent::div//following-sibling::div")
//	public WebElement weActivityAmountScope1;

//	public void ValidateActivityMobileCumbutionDetailsInViewActivity() {
//		try {
//			Thread.sleep(5000);
//			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "Calculators");
//			waitForElement(weFacilityName);
//			if (weFacilityName.getText().trim().equals(data.get("FacilityName").trim())) {
//				passed("Successfully Validated FacilityName In Activity Details As" + weFacilityName.getText());
//			} else {
//				failed(driver, "Failed To validate Facility Name In Activity Details Expected As "
//						+ data.get("FacilityName") + "But Actual is" + weFacilityName.getText());
//			}
//			if (weInVoiceNo.getText().trim().equals(data.get("InvoiceNumber").trim())) {
//				passed("Successfully Validated InvoiceNumber In Activity Details As" + weInVoiceNo.getText());
//			} else {
//				failed(driver, "Failed To validate InvoiceNumber In Activity Details Expected As "
//						+ data.get("InvoiceNumber") + "But Actual is" + weInVoiceNo.getText());
//			}
//			if (weInVoiceDate.getText().trim().equals(data.get("InvoiceDate").trim())) {
//				passed("Successfully Validated InvoiceDate In Activity Details As" + weFacilityName.getText());
//			} else {
//				failed(driver, "Failed To validate InvoiceDate In Activity Details Expected As "
//						+ data.get("InvoiceDate") + "But Actual is" + weFacilityName.getText());
//			}
//			if (weStartDate.getText().trim().equals(data.get("StartDate").trim())) {
//				passed("Successfully Validated StartDate In Activity Details As" + weStartDate.getText());
//			} else {
//				failed(driver, "Failed To validate StartDate In Activity Details Expected As " + data.get("StartDate")
//						+ "But Actual is" + weStartDate.getText());
//			}
//			if (weEndDate.getText().trim().equals(data.get("EndDate").trim())) {
//				passed("Successfully Validated EndDate In Activity Details As" + weEndDate.getText());
//			} else {
//				failed(driver, "Failed To validate EndDate In Activity Details Expected As " + data.get("EndDate")
//						+ "But Actual is" + weEndDate.getText());
//			}
//			if (weActivityTypeScope1.getText().trim().equals(data.get("ActivityType").trim())) {
//				passed("Successfully Validated EndDate In Activity Details As" + weActivityTypeScope1.getText());
//			} else {
//				failed(driver, "Failed To validate EndDate In Activity Details Expected As " + data.get("ActivityType")
//						+ "But Actual is" + weActivityTypeScope1.getText());
//			}
//			if (weCustomEmissMobileComScope1.getText().trim().equals(data.get("CustomEmissionFactor").trim())) {
//				passed("Successfully Validated CustomEmissionFactor In Activity Details As"
//						+ weCustomEmissMobileComScope1.getText());
//			} else {
//				failed(driver, "Failed To validate CustomEmissionFactor In Activity Details Expected As "
//						+ data.get("CustomEmissionFactor") + "But Actual is" + weCustomEmissMobileComScope1.getText());
//			}
//			if (weFuelType.getText().trim().equals(data.get("FuelType").trim())) {
//				passed("Successfully Validated FuelType In Activity Details As" + weFuelType.getText());
//			} else {
//				failed(driver, "Failed To validate FuelType In Activity Details Expected As " + data.get("FuelType")
//						+ "But Actual is" + weFuelType.getText());
//			}
//			if (weVehicleTypeScope1.getText().trim().equals(data.get("VehicleType").trim())) {
//				passed("Successfully Validated FuelType In Activity Details As" + weVehicleTypeScope1.getText());
//			} else {
//				failed(driver, "Failed To validate FuelType In Activity Details Expected As " + data.get("VehicleType")
//						+ "But Actual is" + weVehicleTypeScope1.getText());
//			}
//			if (weActivityAmountScope1.getText().trim().equals(data.get("ActivityAmount").trim())) {
//				passed("Successfully Validated FuelAmount In Activity Details As" + weActivityAmountScope1.getText());
//			} else {
//				failed(driver, "Failed To validate FuelAmount In Activity Details Expected As "
//						+ data.get("ActivityAmount") + "But Actual is" + weActivityAmountScope1.getText());
//			}
//			if (weUnit.getText().trim().equals(data.get("Unit").trim())) {
//				passed("Successfully Validated Unit In Activity Details As" + weUnit.getText());
//			} else {
//				failed(driver, "Failed To validate Unit In Activity Details Expected As " + data.get("Unit")
//						+ "But Actual is" + weUnit.getText());
//			}
//			// Emission Details
//			if (weEmissionDetails_tCO2.getText().trim().equals(data.get("tCO2").trim())) {
//				passed("Successfully Validated tCo2 In Emission Details As" + weEmissionDetails_tCO2.getText());
//			} else {
//				failed(driver, "Failed To validate tCo2 In Emission Details Expected As " + data.get("tCO2")
//						+ "But Actual is" + weEmissionDetails_tCO2.getText());
//			}
//			if (weEmissionDetails_tCH4.getText().trim().equals(data.get("tCH4").trim())) {
//				passed("Successfully Validated tCH4 In Emission Details As" + weEmissionDetails_tCH4.getText());
//			} else {
//				failed(driver, "Failed To validate tCH4 In Emission Details Expected As " + data.get("tCH4")
//						+ "But Actual is" + weEmissionDetails_tCH4.getText());
//			}
//			if (weEmissionDetails_tN2O.getText().trim().equals(data.get("tN2O").trim())) {
//				passed("Successfully Validated tN2O In Emission Details As" + weEmissionDetails_tN2O.getText());
//			} else {
//				failed(driver, "Failed To validate tN2O In Emission Details Expected As " + data.get("tN2O")
//						+ "But Actual is" + weEmissionDetails_tN2O.getText());
//			}
//			if (weEmissionDetails_tCO2e.getText().trim().equals(data.get("tCO2e").trim())) {
//				passed("Successfully Validated tCo2e In Emission Details As" + weEmissionDetails_tCO2e.getText());
//			} else {
//				failed(driver, "Failed To validate tCo2e In Emission Details Expected As " + data.get("tCO2e")
//						+ "But Actual is" + weEmissionDetails_tCO2e.getText());
//			}
//			System.out.println("Actual BioFuel Value " + weEmissionDetails_BiofueltCO2.getText());
//			System.out.println("Expected BioFuel Value " + data.get("BiofuelCO2"));
//			if (weEmissionDetails_BiofueltCO2.getText().trim().equals(data.get("BiofuelCO2").trim())) {
//				passed("Successfully Validated BiofuelCO2e In Emission Details As"
//						+ weEmissionDetails_BiofueltCO2.getText());
//			} else {
//				failed(driver, "Failed To validate BiofuelCO2e In Emission Details Expected As "
//						+ data.get("BiofuelCO2") + "But Actual is" + weEmissionDetails_BiofueltCO2.getText());
//			}
////			if(weEmissionDetails_BiofueltCO2Daily.getText().trim().equals(data.get("BiofuelCO2eDaily").trim())) {
////				passed("Successfully Validated BiofuelCO2eDaily In Emission Details As"+weEmissionDetails_BiofueltCO2Daily.getText());
////			}else {
////				failed(driver,"Failed To validate BiofuelCO2eDaily In Emission Details Expected As "+data.get("BiofuelCO2eDaily")+"But Actual is"+weEmissionDetails_BiofueltCO2Daily.getText());
////			}
//			System.out.println(weEmissionDetails_EmissionFactor.getText());
//			if (weEmissionDetails_EmissionFactor.getText().trim().contains(data.get("EmissionFactor").trim())) {
//				passed("Successfully Validated EmissionFactor In Emission Details As"
//						+ weEmissionDetails_EmissionFactor.getText());
//			} else {
//				failed(driver, "Failed To validate EmissionFactor In Emission Details Expected As "
//						+ data.get("EmissionFactor") + "But Actual is" + weEmissionDetails_EmissionFactor.getText());
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

	

//	@FindBy(xpath = "//input[@id='facility_name']")
//	public WebElement txtFacility_Name;
//	@FindBy(xpath = "//input[@id='type_of_equipment']")
//	public WebElement txtEquipmentRefrigerantType;
//	@FindBy(xpath = "//input[@id='refrigerant_ref_name']")
//	public WebElement drpRefrigerantUsed;
//	@FindBy(xpath = "//input[@id='start_inventory']")
//	public WebElement txtDecreaseinInventory;
//	@FindBy(xpath = "//input[@id='purchased_from_producer']")
//	public WebElement drpRefrigePurchased;
//	@FindBy(xpath = "//input[@id='end_inventory']")
//	public WebElement txtPurchase_Acquisition;
//	@FindBy(xpath = "//input[@id='equipment_user_return']")
//	public WebElement txtRefrigerantreturned;
//	@FindBy(xpath = "//input[@id='recycle_reclamation_return']")
//	public WebElement txtRefrigerantreturnedOffSite;
//	@FindBy(xpath = "//input[@id='charged_into_equipment']")
//	public WebElement txtRefrigerantcharged;
//	@FindBy(xpath = "//input[@id='delivered_to_equipment']")
//	public WebElement txtRefrigerantdelivered;
//	@FindBy(xpath = "//input[@id='returned_to_producer']")
//	public WebElement txtRefrigerantreturnedtorefrigerant;
//	@FindBy(xpath = "//input[@id='recycle_reclamation_sent_offsite']")
//	public WebElement txtRefrigerantsentoffsiteRecycling;
//	@FindBy(xpath = "//input[@id='destruction_sent_offsite']")
//	public WebElement txtRefrigerantsentoffsitedestruction;
//	@FindBy(xpath = "//input[@id='invoice_no']")
//	public WebElement txtInput_Voice;

//	public void AddActivityRefrigerantsandFugitivesInDirectEmissions(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("RefrigerantsandFugitives", "Facility Name");
//			List<String> invoiceNumb = MultiDataExcelReader.getTestData("RefrigerantsandFugitives", "Invoice No.");
//			List<String> invoiceDate = MultiDataExcelReader.getTestData("RefrigerantsandFugitives", "Invoice Date");
//			List<String> startDate = MultiDataExcelReader.getTestData("RefrigerantsandFugitives", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("RefrigerantsandFugitives", "End Date");
//			List<String> EquipmentRefrigerantType = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Type of air conditioning and refrigeration equipment");
//			List<String> refrigerantUsed = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant used");
//			List<String> refrigerantinventory = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant inventory (in storage, not equipment) at the beginning of the year");
//			List<String> refrigerantinventoryend = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant inventory (in storage, not equipment) at the end of the year");
//			List<String> refrigerantPurchased = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant purchased from producers/distributors");
//			List<String> refrigerantReturnedEquip = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant returned by equipment users");
//			List<String> refrigerantReturnedOffsite = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant returned after off-site recycling or reclamation");
//			List<String> refrigerantCharged = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant charged into equipment*");
//			List<String> refrigerantDeliverEquip = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant delivered to equipment users in containers");
//			List<String> refrigeranReturned = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant returned to refrigerant producers");
//			List<String> refrigerantSentRecycling = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant sent off-site for recycling or reclamation");
//			List<String> refrigerantSentDistruction = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant sent off-site for destruction");
//			Thread.sleep(5000);
//			clickOn(btnActivities, "btnActivities");
//			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
//			clickOn(txtFacility_Name, "Click on Facility name");
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			clickOn(txtInput_Voice, "invoice Number");
//			enterText(txtInput_Voice, "invoice number", invoiceNumb.get(i));
//			clickOn(txtInvoiceDate, "invoice date");
//			enterText(txtInvoiceDate, "Invoice Date", invoiceDate.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			enterText(txtEquipmentRefrigerantType, "txt Equipment Refrigerant Type", EquipmentRefrigerantType.get(i));
//			clickOn(drpRefrigerantUsed, "drp Refrigerant Used Scope1");
//			WebElement weRefreigerantUsed = driver
//					.findElement(By.xpath("//li[text()='" + refrigerantUsed.get(i) + "']"));
//			clickOn(weRefreigerantUsed, refrigerantUsed.get(i));
//			enterText(txtDecreaseinInventory, "txt Decrease in Inventory", refrigerantinventory.get(i));
//			enterText(txtPurchase_Acquisition, "Refrigerant purchased from producers/distributors",
//					refrigerantinventoryend.get(i));
//			enterText(drpRefrigePurchased, "Refrigerant purchased from producers/distributors",
//					refrigerantPurchased.get(i));
//			enterText(txtRefrigerantreturned, "Refrigerant returned by equipment users",
//					refrigerantReturnedEquip.get(i));
//			enterText(txtRefrigerantreturnedOffSite, "Refrigerant returned after off-site recycling or reclamation",
//					refrigerantReturnedOffsite.get(i));
//			enterText(txtRefrigerantcharged, "Refrigerant charged into equipment", refrigerantCharged.get(i));
//			enterText(txtRefrigerantdelivered, "Refrigerant delivered to equipment users in containers",
//					refrigerantDeliverEquip.get(i));
//			enterText(txtRefrigerantreturnedtorefrigerant, "Refrigerant returned to refrigerant producers",
//					refrigeranReturned.get(i));
//			enterText(txtRefrigerantsentoffsiteRecycling, "Refrigerant sent off-site for recycling or reclamation",
//					refrigerantSentRecycling.get(i));
//			enterText(txtRefrigerantsentoffsitedestruction, "Refrigerant sent off-site for destruction",
//					refrigerantSentDistruction.get(i));
//			clickOn(btnSave, "Save Button");
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void EditActivityRefrigerantsandFugitivesInDirectEmissions(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("RefrigerantsandFugitives", "Facility Name");
//			List<String> invoiceNumb = MultiDataExcelReader.getTestData("RefrigerantsandFugitives", "Invoice No.");
//			List<String> invoiceDate = MultiDataExcelReader.getTestData("RefrigerantsandFugitives", "Invoice Date");
//			List<String> startDate = MultiDataExcelReader.getTestData("RefrigerantsandFugitives", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("RefrigerantsandFugitives", "End Date");
//			List<String> EquipmentRefrigerantType = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Type of air conditioning and refrigeration equipment");
//			List<String> refrigerantUsed = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant used");
//			List<String> refrigerantinventory = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant inventory (in storage, not equipment) at the beginning of the year");
//			List<String> refrigerantinventoryend = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant inventory (in storage, not equipment) at the end of the year");
//			List<String> refrigerantPurchased = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant purchased from producers/distributors");
//			List<String> refrigerantReturnedEquip = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant returned by equipment users");
//			List<String> refrigerantReturnedOffsite = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant returned after off-site recycling or reclamation");
//			List<String> refrigerantCharged = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant charged into equipment*");
//			List<String> refrigerantDeliverEquip = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant delivered to equipment users in containers");
//			List<String> refrigeranReturned = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant returned to refrigerant producers");
//			List<String> refrigerantSentRecycling = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant sent off-site for recycling or reclamation");
//			List<String> refrigerantSentDistruction = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//					"Refrigerant sent off-site for destruction");
//			Thread.sleep(5000);
//			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
//			clickOn(txtFacility_Name, "Click on Facility name");
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			clickOn(txtInput_Voice, "invoice Number");
//			Actions act = new Actions(driver);
//			act.doubleClick(txtInput_Voice).perform();
//			enterText(txtInput_Voice, "invoice number", invoiceNumb.get(i));
//			clickOn(txtInvoiceDate, "invoice date");
//			enterText(txtInvoiceDate, "Invoice Date", invoiceDate.get(i));
//			clickOn(txtStartDate, "Start date");
//			act.doubleClick(txtStartDate).perform();
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			act.doubleClick(txtEndDate).perform();
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			act.doubleClick(txtEquipmentRefrigerantType).perform();
//			enterText(txtEquipmentRefrigerantType, "txt Equipment Refrigerant Type", EquipmentRefrigerantType.get(i));
//			clickOn(drpRefrigerantUsed, "drp Refrigerant Used Scope1");
//			WebElement weRefreigerantUsed = driver
//					.findElement(By.xpath("//li[text()='" + refrigerantUsed.get(i) + "']"));
//			clickOn(weRefreigerantUsed, refrigerantUsed.get(i));
//			act.doubleClick(txtDecreaseinInventory).perform();
//			enterText(txtDecreaseinInventory, "txt Decrease in Inventory", refrigerantinventory.get(i));
//			act.doubleClick(txtPurchase_Acquisition).perform();
//			enterText(txtPurchase_Acquisition, "Refrigerant purchased from producers/distributors",
//					refrigerantinventoryend.get(i));
//			act.doubleClick(drpRefrigePurchased).perform();
//			enterText(drpRefrigePurchased, "Refrigerant purchased from producers/distributors",
//					refrigerantPurchased.get(i));
//			act.doubleClick(txtRefrigerantreturned).perform();
//			enterText(txtRefrigerantreturned, "Refrigerant returned by equipment users",
//					refrigerantReturnedEquip.get(i));
//			act.doubleClick(txtRefrigerantreturnedOffSite).perform();
//			enterText(txtRefrigerantreturnedOffSite, "Refrigerant returned after off-site recycling or reclamation",
//					refrigerantReturnedOffsite.get(i));
//			act.doubleClick(txtRefrigerantcharged).perform();
//			enterText(txtRefrigerantcharged, "Refrigerant charged into equipment", refrigerantCharged.get(i));
//			act.doubleClick(txtRefrigerantdelivered).perform();
//			enterText(txtRefrigerantdelivered, "Refrigerant delivered to equipment users in containers",
//					refrigerantDeliverEquip.get(i));
//			act.doubleClick(txtRefrigerantreturnedtorefrigerant).perform();
//			enterText(txtRefrigerantreturnedtorefrigerant, "Refrigerant returned to refrigerant producers",
//					refrigeranReturned.get(i));
//			act.doubleClick(txtRefrigerantsentoffsiteRecycling).perform();
//			enterText(txtRefrigerantsentoffsiteRecycling, "Refrigerant sent off-site for recycling or reclamation",
//					refrigerantSentRecycling.get(i));
//			act.doubleClick(txtRefrigerantsentoffsitedestruction).perform();
//			enterText(txtRefrigerantsentoffsitedestruction, "Refrigerant sent off-site for destruction",
//					refrigerantSentDistruction.get(i));
//			clickOn(btnSave, "Save Button");
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void ValidateActivityRefrigerantsandFugitivesDetailsInViewActivity(int i) {
//		try {
//			Thread.sleep(5000);
//			System.out.println("-------Emission Details Are-------");
//			String[] activityDetailFieldNames = { "Facility Name", "Invoice No.", "Invoice Date", "Start Date",
//					"End Date", "Type of air conditioning and refrigeration equipment", "Refrigerant used",
//					"Refrigerant inventory (in storage, not equipment) at the beginning of the year",
//					"Refrigerant purchased from producers/distributors", "Refrigerant returned by equipment users",
//					"Refrigerant returned after off-site recycling or reclamation",
//					"Refrigerant charged into equipment*", "Refrigerant delivered to equipment users in containers",
//					"Refrigerant returned to refrigerant producers",
//					"Refrigerant sent off-site for recycling or reclamation",
//					"Refrigerant sent off-site for destruction", "tCO2", "tCO2e (Daily)", "Emission Factor", "Source" };
//			// Refrigerant inventory (in storage, not equipment) at the end of the year
//			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath(
//						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("RefrigerantsandFugitives",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
	/* for regression ---------- */

//	@FindBy(xpath = "//div[contains(text(),'SCOPE 1')]")
//	public WebElement btnIndirectEmisssionScope1;
//	@FindBy(xpath = "//button[text()='Parameter Input']")
//	public WebElement btnParameterInput;
//	@FindBy(xpath = "//li[@aria-label='Emission Factors']")
//	public WebElement btnParameterScope1;
////	// span[contains(text(),'Custom Emission Factors')]//parent::div//article
////	@FindBy(xpath = "//span[text()='Custom Emission Factors']/following-sibling::article//*[local-name()='svg']")
////	public WebElement btnAddCstmEmsonFctr;
//	@FindBy(xpath = "//article[@aria-label='Add Custom Emission Factors']")
//	public WebElement btnAddCstEmsonFctrScope1;
////	@FindBy(xpath = "//input[@id='Efname']")
////	public WebElement inputNameCstmFctr;
////	@FindBy(xpath = "//*[contains(text(),'Activity Type')]//parent::div//parent::div//following-sibling::div//input")
////	public WebElement inputActivityType;
//	@FindBy(xpath = "//ul[@aria-label='icon expansion']//li[@role='treeitem']")
//	private List<WebElement> drpActvityType;
//	@FindBy(xpath = "//input[@id='SourceOfEmission']")
//	public WebElement srcOfEmisn;
//	// h5[text()='Add Custom Emission Factor']//following::input[@id='CO2']
//	// input[@id='CO2']
//	// h5[text()='Add Custom Emission
//	// Factor']//following::span[text()='CO2']//parent::div//parent::div/following-sibling::div/div/input
//	@FindBy(xpath = "//input[@id='CO2']")
//	public WebElement inputCO2;
//	@FindBy(xpath = "//input[@id='CH4']")
//	public WebElement inputCH4;
//	@FindBy(xpath = "//input[@id='N2O']")
//	public WebElement inputN2O;
//	@FindBy(xpath = "//input[@id='BiofuelCO2']")
//	public WebElement inputBioFule;
//	@FindBy(xpath = "//span[contains(text(),'(Denominator)')]//parent::div//parent::div//following-sibling::div//input")
//	public WebElement inputunitCustmEFDen;
//	@FindBy(xpath = "//ul[@role='listbox']//li")
//	private List<WebElement> drpunitCustmEFDen;
//	@FindBy(xpath = "//button[contains(text(),'Save')]")
//	public WebElement btnsave;
//	@FindBy(xpath = "//span[contains(text(),'(Numerator)')]//parent::div//parent::div//following-sibling::div//input")
//	public WebElement inputunitCstnEFNum;
//	@FindBy(xpath = "//ul[@role='listbox']//li")
//	private List<WebElement> drpunitCustmEFNum;
//	/* custom emission factor details */
//	@FindBy(xpath = "//h5[text()='View Custom Emission Factor']")
//	public WebElement lblViewCustom;
//	@FindBy(xpath = "//span[text()='Name of Custom EF']//parent::div//following-sibling::div")
//	public WebElement nameCust;
//	@FindBy(xpath = "//span[text()='Activity Type']//parent::div//following-sibling::div")
//	public WebElement actType;
//	@FindBy(xpath = "//span[text()='Source of Emission Factor']//parent::div//following-sibling::div")
//	public WebElement srcEmsnFctr;
//	@FindBy(xpath = "//span[text()='Unit of Custom EF (Denominator)']//parent::div//following-sibling::div")
//	public WebElement custEFDen;
//	@FindBy(xpath = "//span[text()='CO2']//parent::div//following-sibling::div")
//	public WebElement co2value;
//	@FindBy(xpath = "//span[text()='CH4']//parent::div//following-sibling::div")
//	public WebElement ch4value;
//	@FindBy(xpath = "//span[text()='N2O']//parent::div//following-sibling::div")
//	public WebElement n2ovalue;
//	@FindBy(xpath = "//span[text()='Unit of Custom EF (Numerator)']//parent::div//following-sibling::div")
//	public WebElement custEFNum;
	
	

//	public void Validate_Add_New_Custom_Emission_Factor() {
//		sleep(8000);
//		clickOn(btnIndirectEmisssionScope1, "clicked on scope 1");
//		clickOn(btnParameterInput, "clicked on parameter input");
//		sleep(2000);
//		Actions act = new Actions(driver);
//		act.moveToElement(btnAddCstmEmsonFctr).click().perform();
//		// clickOn(btnAddCstmEmsonFctr, "add new custom emission factor");
//		SimpleDateFormat dsf = new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");
//		Date date = new Date();
//		customEmissionName = dsf.format(date);
//		enterText(inputNameCstmFctr, "entered Name of Custom EF", customEmissionName);
//		clickOn(inputActivityType, "opening on Activity Type drop down");
//		for (int i = 0; i < drpActvityType.size(); i++) {
//			if (drpActvityType.get(i).getText().equals(data.get("FuelType"))) {
//				clickOn(drpActvityType.get(i), "selecting the activity type");
//			}
//		}
//		enterText(srcOfEmisn, "entered source of emission factor", data.get("sourceOfEmison"));
//		clickOn(inputunitCustmEFDen, "opening unit of cutom Ef denominator drop down");
//		sleep(1000);
//		for (int i = 0; i < drpunitCustmEFDen.size(); i++) {
//			if (drpunitCustmEFDen.get(i).getText().equals(data.get("CustomEf(Den)"))) {
//				clickOn(drpunitCustmEFDen.get(i), "selecting emission factor denominator ");
//				break;
//			}
//		}
//		jsClick(inputCO2, "clicked on co2");
//		enterText(inputCO2, "entered co2 vlaue", data.get("co2"));
//		enterText(inputCH4, "entered CH4 value", data.get("ch4"));
//		enterText(inputN2O, "entered N2O value", data.get("N20"));
//		// enterText(inputBioFule, "bio fuel value", "0");
//		clickOn(inputunitCstnEFNum, "opening unit of cutom Ef Num drop down");
//		for (int i = 0; i < drpunitCustmEFNum.size(); i++) {
//			if (drpunitCustmEFNum.get(i).getText().equals(data.get("CustomEf(Num)"))) {
//				clickOn(drpunitCustmEFNum.get(i), "selecting emission factor Numerator");
//			}
//		}
//		clickOn(btnsave, "clicked on CEF save button");
//		By toast = By.xpath("//div[text()='Custom Emission Factor added successfully']");
//		if (isElementPresent(toast)) {
//			passed("Successfully displayed toast message - Custom Emission added successfully");
//		} else {
//			failed(driver, "Failed to display toast message");
//		}
//	}
//
//	public void Verify_Add_New_Custom_Emission_Factor() {
//		try {
//			sleep(5000);
//			verifyIfElementPresent(lblViewCustom, "label view custom emisin factor", "calculator");
//			waitForElement(nameCust);
//			if (nameCust.getText().trim().equals(customEmissionName)) {
//				passed("Sucessfully validated");
//			} else {
//				failed(driver, "Failed To validate Expected As " + data.get("nameOfCustomEF") + "But Actual is"
//						+ nameCust.getText());
//			}
//			if (actType.getText().trim().equals(data.get("FuelType"))) {
//				passed("Succesfully validated");
//			} else {
//				failed(driver,
//						"Failed To validate Expected As " + data.get("FuelType") + "But Actual is" + actType.getText());
//			}
//			if (srcEmsnFctr.getText().trim().equals(data.get("sourceOfEmison"))) {
//				passed("Succesfully validated");
//			} else {
//				failed(driver, "Failed To validate Expected As " + data.get("sourceOfEmison") + "But Actual is"
//						+ srcEmsnFctr.getText());
//			}
//			if (custEFDen.getText().trim().equals(data.get("CustomEf(Den)"))) {
//				passed("Succesfully validated");
//			} else {
//				failed(driver, "Failed To validate Expected As " + data.get("CustomEf(Den)") + "But Actual is"
//						+ custEFDen.getText());
//			}
//			if (co2value.getText().trim().equals(data.get("co2"))) {
//				passed("Succesfully validated");
//			} else {
//				failed(driver,
//						"Failed To validate Expected As " + data.get("co2") + "But Actual is" + co2value.getText());
//			}
//			if (ch4value.getText().trim().equals(data.get("ch4"))) {
//				passed("Succesfully validated");
//			} else {
//				failed(driver,
//						"Failed To validate Expected As " + data.get("ch4") + "But Actual is" + ch4value.getText());
//			}
//			if (n2ovalue.getText().trim().equals(data.get("N20"))) {
//				passed("Succesfully validated");
//			} else {
//				failed(driver,
//						"Failed To validate Expected As " + data.get("N20") + "But Actual is" + n2ovalue.getText());
//			}
//			if (custEFNum.getText().trim().equals(data.get("CustomEf(Num)"))) {
//				passed("Succesfully validated");
//			} else {
//				failed(driver, "Failed To validate Expected As " + data.get("CustomEf(Num)") + "But Actual is"
//						+ custEFNum.getText());
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught" + e.getMessage());
//		}
//	}

//	public void Verify_Position_New_Custom_Emission_Factor() {
//		clickOn(bttnCloseAdd, "clicked on close button");
//		sleep(3000);
//		String postXapth = "(//div[@role='rowgroup'])[2]//div[@row-id='" + data.get("indexValue") + "']//div";
//		List<WebElement> fe = driver.findElements(By.xpath(postXapth));
//		for (int i = 0; i < fe.size(); i++) {
//			if (i == 0) {
//				if (fe.get(i).getText().equals(customEmissionName)) {
//					passed("successfully validated the position of first row");
//					break;
//				} else {
//					failed(driver, "failed to find the position of first row");
//				}
//				break;
//			}
//		}
//	}
//	// ......Sidda....Regression
//
//	// div[@ref='eContainer']//div[@role='row']
//	@FindBy(xpath = "(//div[@role='rowgroup'])[2]//div[@row-id='0']")
//	public WebElement btnFirstRowCstmEmiFact;
//	@FindBy(xpath = "//*[text()='Next']//parent::button//following-sibling::button[@type='submit']")
//	public WebElement btnEditCustomEmissScope1;
//	@FindBy(xpath = "//button[contains(text(),'Cancel')]")
//	public WebElement btnCancel;

//	public void Validate_CancelButtonByEditingCustomEmissionFactorDetails() {
//		clickOn(btnParameterInput, "clicked on parameter input");
//		clickOn(btnFirstRowCstmEmiFact, "Clicking on Custom emission factor First row");
//		verifyIfElementPresent(lblViewCustom, "label view custom emisin factor", "calculator");
//		clickOn(btnEditCustomEmissScope1, "Clicking Custom emission factor Edit Button");
//		enterText(inputNameCstmFctr, "entered Name of Custom EF", data.get("nameOfCustomEF"));
//		clickOn(inputActivityType, "opening on Activity Type drop down");
//		for (int i = 0; i < drpActvityType.size(); i++) {
//			if (drpActvityType.get(i).getText().equals(data.get("ActivityType"))) {
//				clickOn(drpActvityType.get(i), "selecting the activity type");
//			}
//		}
//		enterText(srcOfEmisn, "entered source of emission factor", data.get("sourceOfEmison"));
//		clickOn(inputunitCustmEFDen, "opening unit of cutom Ef denominator drop down");
//		sleep(1000);
//		for (int i = 0; i < drpunitCustmEFDen.size(); i++) {
//			if (drpunitCustmEFDen.get(i).getText().equals(data.get("UnitCustomEf_Den"))) {
//				clickOn(drpunitCustmEFDen.get(i), "selecting emission factor denominator ");
//				break;
//			}
//		}
//		jsClick(inputCO2, "clicked on co2");
//		enterText(inputCO2, "entered co2 vlaue", data.get("co2"));
//		enterText(inputCH4, "entered CH4 value", data.get("ch4"));
//		enterText(inputN2O, "entered N2O value", data.get("N20"));
//		// enterText(inputBioFule, "bio fuel value", "0");
//		clickOn(inputunitCstnEFNum, "opening unit of cutom Ef Num drop down");
//		for (int i = 0; i < drpunitCustmEFNum.size(); i++) {
//			if (drpunitCustmEFNum.get(i).getText().equals(data.get("UnitCustomEf_Num"))) {
//				clickOn(drpunitCustmEFNum.get(i), "selecting emission factor Numerator");
//			}
//		}
//		clickOn(btnCancel, "clicked on CEF Cancel button");
//	}
//
//	@FindBy(xpath = "//span[text()='Name of Custom EF']//parent::div//following-sibling::div")
//	public WebElement weNameCustomEmirow1;
//	@FindBy(xpath = "//span[text()='Source of Emission Factor']//parent::div//following-sibling::div")
//	public WebElement weSourceCustomEmirow1;
//	@FindBy(xpath = "//span[text()='CO2']//parent::div//following-sibling::div")
//	public WebElement weCO2CustomEmirow1;
//	@FindBy(xpath = "//span[text()='CH4']//parent::div//following-sibling::div")
//	public WebElement weCH4CustomEmirow1;
//	@FindBy(xpath = "//span[text()='N2O']//parent::div//following-sibling::div")
//	public WebElement weN2OCustomEmirow1;
//	@FindBy(xpath = "//span[text()='Activity Type']//parent::div//following-sibling::div")
//	public WebElement weActicityCustomEmirow1;
//	@FindBy(xpath = "//span[text()='Unit of Custom EF (Denominator)']//parent::div//following-sibling::div")
//	public WebElement weUnitDenoCustomEmirow1;
//	@FindBy(xpath = "//span[text()='Unit of Custom EF (Numerator)']//parent::div//following-sibling::div")
//	public WebElement weUnitNumaCustomEmirow1;
//
//	public void verification_CustomEmissionFactorCancelButtonInScope1GHGcalculator() {
//		verifyIfElementPresent(lblViewCustom, "lblViewCustom", "lblViewCustom");
//		waitForElement(weNameCustomEmirow1);
//		if (weNameCustomEmirow1.getText().trim().equals(data.get("nameOfCustomEF").trim())) {
//			failed(driver, "Values are updated when we clicked on Canecl button Custom Emission Details "
//					+ data.get("nameOfCustomEF") + " Updated Value is " + weNameCustomEmirow1.getText());
//		} else {
//			passed("Successfully validated -- Values are not updated when we clicked on Canecl button Custom Emission Details "
//					+ weNameCustomEmirow1.getText());
//		}
//		if (weSourceCustomEmirow1.getText().trim().equals(data.get("sourceOfEmison").trim())) {
//			failed(driver, "Values are updated when we clicked on Canecl button Custom Emission Details "
//					+ data.get("sourceOfEmison") + " Updated Value is " + weSourceCustomEmirow1.getText());
//		} else {
//			passed("Successfully validated -- Values are not updated when we clicked on Canecl button Custom Emission Details "
//					+ weSourceCustomEmirow1.getText());
//		}
//		if (weCO2CustomEmirow1.getText().trim().equals(data.get("co2").trim())) {
//			failed(driver, "Values are updated when we clicked on Canecl button Custom Emission Details "
//					+ data.get("co2") + " Updated Value is " + weCO2CustomEmirow1.getText());
//		} else {
//			passed("Successfully validated -- Values are not updated when we clicked on Canecl button Custom Emission Details "
//					+ weCO2CustomEmirow1.getText());
//		}
//		if (weCH4CustomEmirow1.getText().trim().equals(data.get("ch4").trim())) {
//			failed(driver, "Values are updated when we clicked on Canecl button Custom Emission Details "
//					+ data.get("ch4") + " Updated Value is " + weCH4CustomEmirow1.getText());
//		} else {
//			passed("Successfully validated -- Values are not updated when we clicked on Canecl button Custom Emission Details "
//					+ weCH4CustomEmirow1.getText());
//		}
//		if (weN2OCustomEmirow1.getText().trim().equals(data.get("N20").trim())) {
//			failed(driver, "Values are updated when we clicked on Canecl button Custom Emission Details "
//					+ data.get("N20") + " Updated Value is " + weN2OCustomEmirow1.getText());
//		} else {
//			passed("Successfully validated -- Values are not updated when we clicked on Canecl button Custom Emission Details "
//					+ weN2OCustomEmirow1.getText());
//		}
//		if (weActicityCustomEmirow1.getText().trim().equals(data.get("ActivityType").trim())) {
//			failed(driver, "Values are updated when we clicked on Canecl button Custom Emission Details "
//					+ data.get("ActivityType") + " Updated Value is " + weActicityCustomEmirow1.getText());
//		} else {
//			passed("Successfully validated -- Values are not updated when we clicked on Canecl button Custom Emission Details "
//					+ weActicityCustomEmirow1.getText());
//		}
//		if (weUnitDenoCustomEmirow1.getText().trim().equals(data.get("UnitCustomEf_Den").trim())) {
//			failed(driver, "Values are updated when we clicked on Canecl button Custom Emission Details "
//					+ data.get("UnitCustomEf_Den") + " Updated Value is " + weUnitDenoCustomEmirow1.getText());
//		} else {
//			passed("Successfully validated -- Values are not updated when we clicked on Canecl button Custom Emission Details "
//					+ weUnitDenoCustomEmirow1.getText());
//		}
//		if (weUnitNumaCustomEmirow1.getText().trim().equals(data.get("UnitCustomEf_Num").trim())) {
//			failed(driver, "Values are updated when we clicked on Canecl button Custom Emission Details "
//					+ data.get("UnitCustomEf_Num") + " Updated Value is " + weUnitNumaCustomEmirow1.getText());
//		} else {
//			passed("Successfully validated -- Values are not updated when we clicked on Canecl button Custom Emission Details "
//					+ weUnitNumaCustomEmirow1.getText());
//		}
//	}
	// ............sidda....Regression....TC

//	@FindBy(xpath = "//div[@role='gridcell' and contains(text(),'ACD_BC')]") // excel
//	public WebElement weRowviewACD_BC;
//	// h6[contains(text(),'Activity
//	// Details')]//parent::div//div//div//following-sibling::div
//	@FindBy(xpath = "//*[text()='Next']//parent::button//parent::div//following-sibling::div") // todo
//	public WebElement btnEdit;

//	public void ValidateEditActivityDetailsInViewActivity() {
//		try {
////				String xpathweRowviewgrid = "//div[@role='gridcell' and contains(text(),'" + data.get("RowEdit") + "')]";
////
////				clickOnElementWithDynamicXpath(xpathweRowviewgrid, "Row to Edit");
//			clickOn(weRowviewACD_BC, "Click on row to view Details");
//			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "Calculators");
//			String ACD_BCFacilName = weFacilityName.getText().trim();
//			System.out.println("ACD_BC Facility values - " + ACD_BCFacilName);
//			String ACD_BCInvoiceNo = weInVoiceNo.getText().trim(); // remove all syso to do
//			System.out.println("ACD_BC Facility values - " + ACD_BCInvoiceNo);
//			String ACD_BCInvoiceDate = weInVoiceDate.getText().trim();
//			System.out.println("ACD_BC Facility values - " + ACD_BCInvoiceDate);
//			String ACD_BCStartDate = weStartDate.getText().trim();
//			System.out.println("ACD_BC Facility values - " + ACD_BCStartDate);
//			String ACD_BCEndDate = weEndDate.getText().trim();
//			System.out.println("ACD_BC Facility values - " + ACD_BCEndDate);
//			String ACD_BCEFuelType = weFuelType.getText().trim();
//			System.out.println("ACD_BC Facility values - " + ACD_BCEFuelType);
//			String ACD_BCFuelAmount = weFuelAmount.getText().trim();
//			System.out.println("ACD_BC Facility values - " + ACD_BCFuelAmount);
//			String ACD_BCUnitType = weUnitType.getText().trim();
//			System.out.println("ACD_BC Facility values - " + ACD_BCUnitType);
//			String ACD_BCUnit = weUnit.getText().trim();
//			System.out.println("ACD_BC Facility values - " + ACD_BCUnit);
//			Thread.sleep(5000);
//			clickOn(btnEdit, "Edit Activity scope 1 Button");
//			clickOn(txtFacilityName, "txtFacilityName");
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("FacilityName") + "']"));
//			clickOn(we, data.get("FacilityName"));
//			sleep(2000);
//			clickOn(txtInputVoice, "invoice Number");
//			enterText(txtInputVoice, "invoice number", data.get("InvoiceNumber"));
//			clickOn(txtInvoiceDate, "invoice date");
//			enterText(txtInvoiceDate, "Invoice Date", data.get("InvoiceDate"));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", data.get("StartDate"));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", data.get("EndDate"));
//			clickOn(txtFuelType, "txtFuelType");
//			we = driver.findElement(By.xpath("//li[text()='" + data.get("FuelType") + "']"));
//			clickOn(we, data.get("FuelType"));
//			sleep(2000);
//			clickOn(txtFuelAmount, "Fuel Amount");
//			enterText(txtFuelAmount, "Fuel Amount", data.get("FuelAmount"));
//			clickOn(txtUnitType, "Unit Type");
//			we = driver.findElement(By.xpath("//li[text()='" + data.get("UnitType") + "']"));
//			clickOn(we, data.get("UnitType"));
//			sleep(2000);
//			clickOn(txtUnitName, "Unit");
//			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit") + "']"));
//			clickOn(we, data.get("Unit"));
//			sleep(2000);
//			clickOn(btnSave, "Save Button");
//			sleep(4000);
//			String ABC_CAFacilName = weFacilityName.getText().trim();
//			System.out.println("ABC_CA Facility values - " + ABC_CAFacilName);
//			String ABC_CAInvoiceNo = weInVoiceNo.getText().trim();
//			System.out.println("ABC_CA Facility values - " + ABC_CAInvoiceNo);
//			String ABC_CAInvoiceDate = weInVoiceDate.getText().trim();
//			System.out.println("ABC_CA Facility values - " + ABC_CAInvoiceDate);
//			String ABC_CAStartDate = weStartDate.getText().trim();
//			System.out.println("ABC_CA Facility values - " + ABC_CAStartDate);
//			String ABC_CAEndDate = weEndDate.getText().trim();
//			System.out.println("ABC_CA Facility values - " + ABC_CAEndDate);
//			String ABC_CAEFuelType = weFuelType.getText().trim();
//			System.out.println("ABC_CA Facility values - " + ABC_CAEFuelType);
//			String ABC_CAFuelAmount = weFuelAmount.getText().trim();
//			System.out.println("ABC_CA Facility values - " + ABC_CAFuelAmount);
//			String ABC_CAUnitType = weUnitType.getText().trim();
//			System.out.println("ABC_CA Facility values - " + ABC_CAUnitType);
//			String ABC_CAUnit = weUnit.getText().trim();
//			System.out.println("ABC_CA Facility values - " + ABC_CAUnit);
//			if (ABC_CAFacilName.equals(ACD_BCFacilName)) {
//				failed(driver, "Values are not updated when we edited Activity Details " + ACD_BCFacilName
//						+ " Updated Activity Details are " + ABC_CAFacilName);
//			} else {
//				passed("Successfully validated -- Values are updated when we Edited  Activity Details - Updated Activity Details are-> "
//						+ ABC_CAFacilName);
//			}
//			if (ABC_CAInvoiceNo.equals(ACD_BCInvoiceNo)) {
//				failed(driver, "Values are not updated when we edited Activity Details " + ACD_BCInvoiceNo
//						+ " Updated Activity Details are " + ABC_CAInvoiceNo);
//			} else {
//				passed("Successfully validated -- Values are updated when we Edited  Activity Details - Updated Activity Details are-> "
//						+ ABC_CAInvoiceNo);
//			}
//			if (ABC_CAInvoiceDate.equals(ACD_BCInvoiceDate)) {
//				failed(driver, "Values are not updated when we edited Activity Details " + ACD_BCInvoiceDate
//						+ " Updated Activity Details are " + ABC_CAInvoiceDate);
//			} else {
//				passed("Successfully validated -- Values are updated when we Edited  Activity Details - Updated Activity Details are-> "
//						+ ABC_CAInvoiceDate);
//			}
//			if (ABC_CAStartDate.equals(ACD_BCStartDate)) {
//				failed(driver, "Values are not updated when we edited Activity Details " + ACD_BCStartDate
//						+ " Updated Activity Details are " + ABC_CAStartDate);
//			} else {
//				passed("Successfully validated -- Values are updated when we Edited  Activity Details - Updated Activity Details are-> "
//						+ ABC_CAStartDate);
//			}
//			if (ABC_CAEndDate.equals(ACD_BCEndDate)) {
//				failed(driver, "Values are not updated when we edited Activity Details " + ACD_BCEndDate
//						+ " Updated Activity Details are " + ABC_CAEndDate);
//			} else {
//				passed("Successfully validated -- Values are updated when we Edited  Activity Details - Updated Activity Details are-> "
//						+ ABC_CAEndDate);
//			}
//			if (ABC_CAEFuelType.equals(ACD_BCEFuelType)) {
//				failed(driver, "Values are not updated when we edited Activity Details " + ACD_BCEFuelType
//						+ " Updated Activity Details are " + ABC_CAEFuelType);
//			} else {
//				passed("Successfully validated -- Values are updated when we Edited  Activity Details - Updated Activity Details are-> "
//						+ ABC_CAEFuelType);
//			}
//			if (ABC_CAFuelAmount.equals(ACD_BCFuelAmount)) {
//				failed(driver, "Values are not updated when we edited Activity Details " + ACD_BCFuelAmount
//						+ " Updated Activity Details are " + ABC_CAFuelAmount);
//			} else {
//				passed("Successfully validated -- Values are updated when we Edited  Activity Details - Updated Activity Details are-> "
//						+ ABC_CAFuelAmount);
//			}
//			if (ABC_CAUnitType.equals(ACD_BCUnitType)) {
//				failed(driver, "Values are not updated when we edited Activity Details " + ACD_BCUnitType
//						+ " Updated Activity Details are " + ABC_CAUnitType);
//			} else {
//				passed("Successfully validated -- Values are updated when we Edited  Activity Details - Updated Activity Details are-> "
//						+ ABC_CAUnitType);
//			}
//			if (ABC_CAUnit.equals(ACD_BCUnit)) {
//				failed(driver, "Values are not updated when we edited Activity Details " + ACD_BCUnit
//						+ " Updated Activity Details are " + ABC_CAUnit);
//			} else {
//				passed("Successfully validated -- Values are updated when we Edited  Activity Details - Updated Activity Details are-> "
//						+ ABC_CAUnit);
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
	// For loops ...to do

//	@FindBy(xpath = "(//div[@role='rowgroup'])[2]//div[@row-id='0']")
//	public WebElement weFirstrow;
//	@FindBy(xpath = "((//div[@role='rowgroup'])[2]//div[@row-id='0']//div)[1]")
//	public WebElement weFirstrowFaciName;
//	@FindBy(xpath = "((//div[@role='rowgroup'])[2]//div[@row-id='0']//div)[2]")
//	public WebElement weFirstrowInvoiceNo;
//	@FindBy(xpath = "((//div[@role='rowgroup'])[2]//div[@row-id='0']//div)[3]")
//	public WebElement weFirstrowInvDate;
//	@FindBy(xpath = "((//div[@role='rowgroup'])[2]//div[@row-id='0']//div)[4]")
//	public WebElement weFirstrowStartDate;
//	@FindBy(xpath = "((//div[@role='rowgroup'])[2]//div[@row-id='0']//div)[5]")
//	public WebElement weFirstrowEndDate;
//	@FindBy(xpath = "((//div[@role='rowgroup'])[2]//div[@row-id='0']//div)[6]")
//	public WebElement weFirstrowFuelType;
//	@FindBy(xpath = "((//div[@role='rowgroup'])[2]//div[@row-id='0']//div)[8]")
//	public WebElement weFirstrowFuelAmount;
//	@FindBy(xpath = "((//div[@role='rowgroup'])[2]//div[@row-id='0']//div)[9]")
//	public WebElement weFirstrowUnitType;
//	@FindBy(xpath = "((//div[@role='rowgroup'])[2]//div[@row-id='0']//div)[10]")
//	public WebElement weFirstrowUnit;

//	public void Verify_Position_New_Scope1Actvity() {
//		String rowpositionverxpath = "(//div[@role='rowgroup'])[2]//div[@row-id='" + data.get("indexValue") + "']//div"; // to
//		// do
//		List<WebElement> fe = driver.findElements(By.xpath(rowpositionverxpath));
//		for (int i = 0; i < fe.size(); i++) {
//			if (i == 0) {
//				if (fe.get(i).getText().equals(data.get("FacilityName"))) {
//					passed("successfully validated the position of first row");
//					break;
//				} else {
//					failed(driver, "failed to find the position of first row");
//				}
//				break;
//			}
//		}
//		String veriFirstrowval = weFirstrow.getText();
//		System.out.println(veriFirstrowval);
////	            String veriFirstrowUnit= weFirstrowUnit.getText();
////	            
////	            System.out.println(veriFirstrowUnit);
//		if (weFirstrowFaciName.getText().equals(data.get("FacilityName").trim())) {
//			passed("Successfully Validated Row Value As" + weFirstrowFaciName.getText());
//		} else {
//			failed(driver, "Failed To validate Row Value, Expected As " + data.get("FacilityName") + "But Actual is"
//					+ weFirstrowFaciName.getText());
//		}
//		if (weFirstrowInvoiceNo.getText().equals(data.get("InvoiceNumber").trim())) {
//			passed("Successfully Validated Row Value As" + weFirstrowInvoiceNo.getText());
//		} else {
//			failed(driver, "Failed To validate Row Value, Expected As " + data.get("InvoiceNumber") + "But Actual is"
//					+ weFirstrowInvoiceNo.getText());
//		}
//		if (weFirstrowInvDate.getText().equals(data.get("InvoiceDate").trim())) {
//			passed("Successfully Validated Row Value As" + weFirstrowInvDate.getText());
//		} else {
//			failed(driver, "Failed To validate Row Value, Expected As " + data.get("InvoiceDate") + "But Actual is"
//					+ weFirstrowInvDate.getText());
//		}
//		if (weFirstrowStartDate.getText().equals(data.get("StartDate").trim())) {
//			passed("Successfully Validated Row Value As" + weFirstrowStartDate.getText());
//		} else {
//			failed(driver, "Failed To validate Row Value, Expected As " + data.get("StartDate") + "But Actual is"
//					+ weFirstrowStartDate.getText());
//		}
//		if (weFirstrowEndDate.getText().equals(data.get("EndDate").trim())) {
//			passed("Successfully Validated Row Value As" + weFirstrowEndDate.getText());
//		} else {
//			failed(driver, "Failed To validate Row Value, Expected As " + data.get("EndDate") + "But Actual is"
//					+ weFirstrowEndDate.getText());
//		}
//		if (weFirstrowFuelType.getText().equals(data.get("FuelType").trim())) {
//			passed("Successfully Validated Row Value As" + weFirstrowFuelType.getText());
//		} else {
//			failed(driver, "Failed To validate Row Value, Expected As " + data.get("FuelType") + "But Actual is"
//					+ weFirstrowFuelType.getText());
//		}
//		if (weFirstrowFuelAmount.getText().equals(data.get("FuelAmount").trim())) {
//			passed("Successfully Validated Row Value As" + weFirstrowFuelAmount.getText());
//		} else {
//			failed(driver, "Failed To validate Row Value, Expected As " + data.get("FuelAmount") + "But Actual is"
//					+ weFirstrowFuelAmount.getText());
//		}
//		if (weFirstrowUnitType.getText().equals(data.get("UnitType").trim())) {
//			passed("Successfully Validated Row Value As" + weFirstrowUnitType.getText());
//		} else {
//			failed(driver, "Failed To validate Row Value, Expected As " + data.get("UnitType") + "But Actual is"
//					+ weFirstrowUnitType.getText());
//		}
////					if(weFirstrowUnit.getText().equals(data.get("Unit").trim())) {
////						passed("Successfully Validated Row Value As"+weFirstrowUnit.getText());
////					}else {
////						failed(driver,"Failed To validate Row Value, Expected As "+data.get("Unit")+"But Actual is"+weFirstrowUnit.getText());
////					}
//	}

//	public void VerifyGridSidePanelDisplay() {
//		try {
//			clickOn(weFirstrow, "Clicked on First row from grid");
//			By ActivityDetialsSidePanel = By.xpath("//h6[text()='Activity Details']");
//			if (isElementPresent(ActivityDetialsSidePanel)) {
//				passed("Successfully displayed Activity details Side Panel");
//			} else {
//				failed(driver, "Failed to display Activity details Side Panel");
//			}
//			clickOn(btnCloselatest, getCurrentDate());
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

//	@FindBy(xpath = "//input[@name='Are you using a Custom Emissions Factor']")
//	public WebElement rdbCustomEmiFactor;
//	@FindBy(xpath = "//input[@id='emissionName']")
//	public WebElement txtEmissionName;
//	@FindBy(xpath = "//span[text()='Source']//parent::div//following-sibling::div")
//	public WebElement weCustomEmissionSource;

//	public void Verify_CustomEmission_FuelTypeInScope1() {
//		try {
//			Thread.sleep(12000);
//			clickOn(btnActivities, "btnActivities");
//			verifyIfElementPresent(lblAddActivity, "lblAddActivity", "lblAddActivity");
//			Thread.sleep(5000);
//			clickOn(txtFacilityName, "txtFacilityName");
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("FacilityName") + "']"));
//			clickOn(we, data.get("FacilityName"));
//			sleep(5000);
//			clickOn(txtInputVoice, "invoice Number");
//			enterText(txtInputVoice, "invoice number", data.get("InvoiceNumber"));
//			clickOn(txtInvoiceDate, "invoice date");
//			enterText(txtInvoiceDate, "Invoice Date", data.get("InvoiceDate"));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", data.get("StartDate"));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", data.get("EndDate"));
//			clickOn(rdbCustomEmiFactor, "Custom Emission Button");
//			clickOn(txtEmissionName, "txtEmissionName");
//			WebElement wetxtEmissionName = driver
//					.findElement(By.xpath("//li[text()='" + data.get("CustomEmissionFactor") + "']"));
//			clickOn(wetxtEmissionName, data.get("CustomEmissionFactor"));
//			sleep(2000);
//			clickOn(txtFuelAmount, "Fuel Amount");
//			enterText(txtFuelAmount, "Fuel Amount", data.get("FuelAmount"));
//			sleep(2000);
//			clickOn(txtUnitName, "Unit");
//			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit") + "']"));
//			clickOn(we, data.get("Unit"));
//			sleep(2000);
//			clickOn(btnSave, "Save Button");
//			sleep(2000);
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

//	public void ValidateActivityDetailsCustomFuelTypeInViewActivity() {
//		try {
//			Thread.sleep(5000);
//			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "Calculators");
//			waitForElement(weFacilityName);
//			if (weFacilityName.getText().trim().equals(data.get("FacilityName").trim())) {
//				passed("Successfully Validated FacilityName In Activity Details As" + weFacilityName.getText());
//			} else {
//				failed(driver, "Failed To validate Facility Name In Activity Details Expected As "
//						+ data.get("FacilityName") + "But Actual is" + weFacilityName.getText());
//			}
//			if (weInVoiceNo.getText().trim().equals(data.get("InvoiceNumber").trim())) {
//				passed("Successfully Validated InvoiceNumber In Activity Details As" + weInVoiceNo.getText());
//			} else {
//				failed(driver, "Failed To validate InvoiceNumber In Activity Details Expected As "
//						+ data.get("InvoiceNumber") + "But Actual is" + weInVoiceNo.getText());
//			}
//			if (weInVoiceDate.getText().trim().equals(data.get("InvoiceDate").trim())) {
//				passed("Successfully Validated InvoiceDate In Activity Details As" + weFacilityName.getText());
//			} else {
//				failed(driver, "Failed To validate InvoiceDate In Activity Details Expected As "
//						+ data.get("InvoiceDate") + "But Actual is" + weFacilityName.getText());
//			}
//			if (weStartDate.getText().trim().equals(data.get("StartDate").trim())) {
//				passed("Successfully Validated StartDate In Activity Details As" + weStartDate.getText());
//			} else {
//				failed(driver, "Failed To validate StartDate In Activity Details Expected As " + data.get("StartDate")
//						+ "But Actual is" + weStartDate.getText());
//			}
//			if (weEndDate.getText().trim().equals(data.get("EndDate").trim())) {
//				passed("Successfully Validated EndDate In Activity Details As" + weEndDate.getText());
//			} else {
//				failed(driver, "Failed To validate EndDate In Activity Details Expected As " + data.get("EndDate")
//						+ "But Actual is" + weEndDate.getText());
//			}
//			if (weCustomEmissionFactor.getText().trim().equals(data.get("CustomEmissionFactor").trim())) {
//				passed("Successfully Validated CustomEmissionFactor In Activity Details As"
//						+ weCustomEmissionFactor.getText());
//			} else {
//				failed(driver, "Failed To validate CustomEmissionFactor In Activity Details Expected As "
//						+ data.get("CustomEmissionFactor") + "But Actual is" + weCustomEmissionFactor.getText());
//			}
//			if (weFuelAmount.getText().trim().equals(data.get("FuelAmount").trim())) {
//				passed("Successfully Validated FuelAmount In Activity Details As" + weFuelAmount.getText());
//			} else {
//				failed(driver, "Failed To validate FuelAmount In Activity Details Expected As " + data.get("FuelAmount")
//						+ "But Actual is" + weFuelAmount.getText());
//			}
//			if (weUnit.getText().trim().equals(data.get("Unit").trim())) {
//				passed("Successfully Validated Unit In Activity Details As" + weUnit.getText());
//			} else {
//				failed(driver, "Failed To validate Unit In Activity Details Expected As " + data.get("Unit")
//						+ "But Actual is" + weUnit.getText());
//			}
//			// Emission Details
//			if (weEmissionDetails_tCO2.getText().trim().equals(data.get("tCO2").trim())) {
//				passed("Successfully Validated tCo2 In Emission Details As" + weEmissionDetails_tCO2.getText());
//			} else {
//				failed(driver, "Failed To validate tCo2 In Emission Details Expected As " + data.get("tCO2")
//						+ "But Actual is" + weEmissionDetails_tCO2.getText());
//			}
//			if (weEmissionDetails_tCH4.getText().trim().equals(data.get("tCH4").trim())) {
//				passed("Successfully Validated tCH4 In Emission Details As" + weEmissionDetails_tCH4.getText());
//			} else {
//				failed(driver, "Failed To validate tCH4 In Emission Details Expected As " + data.get("tCH4")
//						+ "But Actual is" + weEmissionDetails_tCH4.getText());
//			}
//			if (weEmissionDetails_tN2O.getText().trim().equals(data.get("tN2O").trim())) {
//				passed("Successfully Validated tN2O In Emission Details As" + weEmissionDetails_tN2O.getText());
//			} else {
//				failed(driver, "Failed To validate tN2O In Emission Details Expected As " + data.get("tN2O")
//						+ "But Actual is" + weEmissionDetails_tN2O.getText());
//			}
//			if (weEmissionDetails_tCO2e.getText().trim().equals(data.get("tCO2e").trim())) {
//				passed("Successfully Validated tCo2e In Emission Details As" + weEmissionDetails_tCO2e.getText());
//			} else {
//				failed(driver, "Failed To validate tCo2e In Emission Details Expected As " + data.get("tCO2e")
//						+ "But Actual is" + weEmissionDetails_tCO2e.getText());
//			}
////				System.out.println(weEmissionDetails_BiofueltCO2.getText());
////				System.out.println(data.get("BiofuelCO2"));
////				
////				if(weEmissionDetails_BiofueltCO2.getText().trim().equals(data.get("BiofuelCO2").trim())) {
////					passed("Successfully Validated BiofuelCO2e In Emission Details As"+weEmissionDetails_BiofueltCO2.getText());
////				}else {
////					failed(driver,"Failed To validate BiofuelCO2e In Emission Details Expected As "+data.get("BiofuelCO2")+"But Actual is"+weEmissionDetails_BiofueltCO2.getText());
////				}
////				
//			if (weEmissionDetails_BiofueltCO2Daily.getText().trim().equals(data.get("BiofuelCO2eDaily").trim())) {
//				passed("Successfully Validated BiofuelCO2eDaily In Emission Details As"
//						+ weEmissionDetails_BiofueltCO2Daily.getText());
//			} else {
//				failed(driver,
//						"Failed To validate BiofuelCO2eDaily In Emission Details Expected As "
//								+ data.get("BiofuelCO2eDaily") + "But Actual is"
//								+ weEmissionDetails_BiofueltCO2Daily.getText());
//			}
//			System.out.println(weEmissionDetails_EmissionFactor.getText());
//			if (weEmissionDetails_EmissionFactor.getText().trim().contains(data.get("EmissionFactor").trim())) {
//				passed("Successfully Validated EmissionFactor In Emission Details As"
//						+ weEmissionDetails_EmissionFactor.getText());
//			} else {
//				failed(driver, "Failed To validate EmissionFactor In Emission Details Expected As "
//						+ data.get("EmissionFactor") + "But Actual is" + weEmissionDetails_EmissionFactor.getText());
//			}
//			if (weCustomEmissionSource.getText().trim().contains(data.get("Source").trim())) {
//				passed("Successfully Validated EmissionFactor In Emission Details As"
//						+ weCustomEmissionSource.getText());
//			} else {
//				failed(driver, "Failed To validate EmissionFactor In Emission Details Expected As " + data.get("Source")
//						+ "But Actual is" + weCustomEmissionSource.getText());
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

//	public void Validate_GenerateValueScope1InGHGCalculator() {
//		try {
//			WebElement totalCO2eEle = driver.findElement(By.xpath("//span[contains(text(),'Total(tCO2e)')]"));
//			WebElement totalStatonaryCombuEle = driver
//					.findElement(By.xpath("//span[contains(text(),'Stationary Combustion Total')]"));
//			sleep(5000);
//			clickOn(dpdPeriod, "Scope 1 Period Drop Down ");
//			String xpathdropPeriod = "//li[contains(text(),'" + data.get("SelectPeriod") + "')]";
//			sleep(2000);
//			clickOnElementWithDynamicXpath(xpathdropPeriod, "SelectPeriod");
//			sleep(2000);
//			clickOn(btnGenerate, "Generate the Calculation");
//			sleep(5000);
//			String totalCO2eValueText = totalCO2eEle.getText();
//			String[] totalCO2eValue = totalCO2eValueText.split("-");
//			try {
//				if (isNumber(totalCO2eValue[1].trim())) {
//					passed(totalCO2eValue[1] + " Total CO2e Values are generated and updated Succesfully");
//				} else {
//					failed(driver, " Failed to generate the Total CO2e");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			String stationaryCombuText = totalStatonaryCombuEle.getText();
//			String[] stationaryCombutionValue = stationaryCombuText.split("-");
//			try {
//				if (isNumber(stationaryCombutionValue[1].trim())) {
//					passed(stationaryCombutionValue[1]
//							+ " Stationary Combution Value Values are generated and updated Succesfully");
//				} else {
//					failed(driver, " Failed to generate the Stationary Combution Value");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	// ................Scope3
	// ...........................................................

	
//
//	@FindBy(xpath = "//input[@id='FacilityName']")
//	public WebElement txtFacilityNameScope3_1;
//	@FindBy(xpath = "//input[@id='description']")
//	public WebElement txtDescrScope3;
//	@FindBy(xpath = "//span[contains(text(),'Purchased Goods Category')]//parent::div/parent::div//input//parent::div")
//	public WebElement drpPurchasedGoodCategory;
//	@FindBy(xpath = "(//span[contains(text(),'Purchased Good')])[2]//parent::div/parent::div//input//parent::div")
//	public WebElement drpPurchasedGood;
//	@FindBy(xpath = "//span[contains(text(),'Production Process Involved')]//parent::div/parent::div//input//parent::div")
//	public WebElement drpProduProcInvolved;
//	@FindBy(xpath = "//input[@id='unit']")
//	public WebElement txtScope3PurchaseGoodUnit;
//	@FindBy(xpath = "//input[@id='amount_of_material']")
//	public WebElement txtQuantityofGoodsPurch;
//	@FindBy(xpath = "//button[text()='Save']")
//	public WebElement btnSaveScope3PurcGood;
//
//	public void AddActivityScope3Emissions() {
//		try {
//			Thread.sleep(5000);
//			clickOn(btnActivities, "btnActivities");
//			verifyIfElementPresent(lblAddActivity, "lblAddActivity", "lblAddActivity");
//			Thread.sleep(5000);
//			clickOn(txtFacilityNameScope3_1, "txtFacilityNameScope3_1");
//			sleep(2000);
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("FacilityName") + "']"));
//			clickOn(we, data.get("FacilityName"));
//			sleep(2000);
//			enterText(txtDescrScope3, "Description Scope3", data.get("Descrption"));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", data.get("StartDate"));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", data.get("EndDate"));
//			clickOn(drpPurchasedGoodCategory, "Purchased Good and Category  DropDown");
//			WebElement wePurchasedGoodCategory = driver
//					.findElement(By.xpath("//li[text()='" + data.get("PurchasedGoodTypeCategory") + "']"));
//			clickOn(wePurchasedGoodCategory, data.get("PurchasedGoodTypeCategory"));
//			clickOn(drpPurchasedGood, "Purchased Good DropDown");
//			WebElement wePurchasedGood = driver
//					.findElement(By.xpath("//li[text()='" + data.get("PurchasedGoodType") + "']"));
//			clickOn(wePurchasedGood, data.get("PurchasedGoodType"));
//			sleep(2000);
//			clickOn(drpProduProcInvolved, "Production Process Involved DropDown");
//			WebElement weProduProInvolved = driver
//					.findElement(By.xpath("//li[text()='" + data.get("ProductionProcess") + "']"));
//			clickOn(weProduProInvolved, data.get("ProductionProcess"));
//			clickOn(txtScope3PurchaseGoodUnit, "Unit");
//			we = driver.findElement(By.xpath("//li[text()='" + data.get("Unit") + "']"));
//			clickOn(we, data.get("Unit"));
//			sleep(2000);
//			enterText(txtQuantityofGoodsPurch, "Quantity of Goods Purchased", data.get("Quantity"));
//			clickOn(btnSaveScope3PurcGood, "Save Button");
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	@FindBy(xpath = "//span[text()='Purchased Goods Category']//parent::div//following-sibling::div")
//	public WebElement wePurchaseGoodsCata;
//	@FindBy(xpath = "//span[text()='Purchased Good']//parent::div//following-sibling::div")
//	public WebElement wePurchaseGoods;
//	@FindBy(xpath = "//span[text()='Production Process Involved']//parent::div//following-sibling::div")
//	public WebElement weProdProcInvol;
//	@FindBy(xpath = "//span[text()='Units']//parent::div//following-sibling::div")
//	public WebElement weUnitScope3;
//	@FindBy(xpath = "//span[text()='Quantity of Goods Purchased']//parent::div//following-sibling::div")
//	public WebElement weQuantityofGoods;
//	@FindBy(xpath = "//span[text()='Source']//parent::div//following-sibling::div")
//	public WebElement weSourceScope3;
//	@FindBy(xpath = "//span[text()='Custom Emission']//parent::div//following-sibling::div")
//	public WebElement weCustomEmissScope3;

//	public void ValidateActivityDetailsInViewActivityScope3() {
//		try {
//			Thread.sleep(5000);
//			verifyIfElementPresent(lblViewActivity, "lblActivityDetails", "Scope3Calculators");
//			waitForElement(weFacilityName);
//			if (weFacilityName.getText().trim().equals(data.get("FacilityName").trim())) {
//				passed("Successfully Validated FacilityName In Activity Details As" + weFacilityName.getText());
//			} else {
//				failed(driver, "Failed To validate Facility Name In Activity Details Expected As "
//						+ data.get("FacilityName") + "But Actual is" + weFacilityName.getText());
//			}
//			if (weDescrptionScope3.getText().trim().equals(data.get("Descrption").trim())) {
//				passed("Successfully Validated Descrption In Scope3 Activity Details As"
//						+ weDescrptionScope3.getText());
//			} else {
//				failed(driver, "Failed To validate Descrption In Scope3 Activity Details Expected As "
//						+ data.get("Descrption") + "But Actual is" + weDescrptionScope3.getText());
//			}
//			if (weStartDate.getText().trim().equals(data.get("StartDate").trim())) {
//				passed("Successfully Validated StartDate In Activity Details As" + weStartDate.getText());
//			} else {
//				failed(driver, "Failed To validate StartDate In Activity Details Expected As " + data.get("StartDate")
//						+ "But Actual is" + weStartDate.getText());
//			}
//			if (weEndDate.getText().trim().equals(data.get("EndDate").trim())) {
//				passed("Successfully Validated EndDate In Activity Details As" + weEndDate.getText());
//			} else {
//				failed(driver, "Failed To validate EndDate In Activity Details Expected As " + data.get("EndDate")
//						+ "But Actual is" + weEndDate.getText());
//			}
//			if (weCustomEmissScope3.getText().trim().equals(data.get("CustomEmissionFactor").trim())) {
//				passed("Successfully Validated CustomEmission In Scope3 Activity Details As"
//						+ weCustomEmissScope3.getText());
//			} else {
//				failed(driver, "Failed To validate CustomEmissionFactor In Scope3 Activity Details Expected As "
//						+ data.get("CustomEmissionFactor") + "But Actual is" + weCustomEmissScope3.getText());
//			}
//			if (wePurchaseGoodsCata.getText().trim().equals(data.get("PurchasedGoodTypeCategory").trim())) {
//				passed("Successfully Validated Purchase Goods Catagery In Scope3 Activity Details As"
//						+ wePurchaseGoodsCata.getText());
//			} else {
//				failed(driver, "Failed To validate Purchase Goods Catagery In Scope3 Activity Details Expected As "
//						+ data.get("PurchasedGoodTypeCategory") + "But Actual is" + wePurchaseGoodsCata.getText());
//			}
//			if (wePurchaseGoods.getText().trim().equals(data.get("PurchasedGoodType").trim())) {
//				passed("Successfully Validated Purchase Goods In Scope3  Activity Details As"
//						+ wePurchaseGoods.getText());
//			} else {
//				failed(driver, "Failed To validate Purchase Goods In Scope3  Activity Details Expected As "
//						+ data.get("PurchasedGoodType") + "But Actual is" + wePurchaseGoods.getText());
//			}
//			if (weProdProcInvol.getText().trim().equals(data.get("ProductionProcess").trim())) {
//				passed("Successfully Validated Production Process Involved In Scope3 Activity Details As"
//						+ weProdProcInvol.getText());
//			} else {
//				failed(driver, "Failed To validate roduction Process Involved In Scope3 Activity Details Expected As "
//						+ data.get("ProductionProcess") + "But Actual is" + weProdProcInvol.getText());
//			}
//			if (weUnitScope3.getText().trim().equals(data.get("Unit").trim())) {
//				passed("Successfully Validated Unit In Scope3 Activity Details As" + weUnitScope3.getText());
//			} else {
//				failed(driver, "Failed To validate Unit In Scope3 Activity Details Expected As " + data.get("Unit")
//						+ "But Actual is" + weUnitScope3.getText());
//			}
//			if (weQuantityofGoods.getText().trim().equals(data.get("Quantity").trim())) {
//				passed("Successfully Validated Quantity of Goods Purchase In Scope3 Activity Details As"
//						+ weQuantityofGoods.getText());
//			} else {
//				failed(driver, "Failed To validate Quantity of Goods Purchase In Scope3 Activity Details Expected As "
//						+ data.get("Quantity") + "But Actual is" + weQuantityofGoods.getText());
//			}
//			// Emission Details
//			if (weEmissionDetails_tCO2e.getText().trim().equals(data.get("tCO2e").trim())) {
//				passed("Successfully Validated tCo2e In Scope3 Emission Details As"
//						+ weEmissionDetails_tCO2e.getText());
//			} else {
//				failed(driver, "Failed To validate tCo2e In Scope3 Emission Details Expected As " + data.get("tCO2e")
//						+ "But Actual is" + weEmissionDetails_tCO2e.getText());
//			}
//			System.out.println(weEmissionDetails_EmissionFactor.getText());
//			if (weEmissionDetails_EmissionFactor.getText().trim().contains(data.get("EmissionFactor").trim())) {
//				passed("Successfully Validated EmissionFactor In Scope3 Emission Details As"
//						+ weEmissionDetails_EmissionFactor.getText());
//			} else {
//				failed(driver, "Failed To validate EmissionFactor In Scope3 Emission Details Expected As "
//						+ data.get("EmissionFactor") + "But Actual is" + weEmissionDetails_EmissionFactor.getText());
//			}
//			if (weSourceScope3.getText().trim().contains(data.get("Source").trim())) {
//				passed("Successfully Validated Source In Scope3 Emission Details As" + weSourceScope3.getText());
//			} else {
//				failed(driver, "Failed To validate Source In Scope3 Emission Details Expected As " + data.get("Source")
//						+ "But Actual is" + weSourceScope3.getText());
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
	/*----SCOPE-2----*/
	/*---------CUSTOM EMISSION FACTOR-----------*/

//	@FindBy(xpath = "//h5[text()='Edit Custom Emission Factor']")
//	public WebElement lblEditCustomEmission;
//	@FindBy(xpath = "//h5[text()='View Custom Emission Factor']")
//	public WebElement lblViewCustomEmission;
//	@FindBy(xpath = "//div[contains(text(),'Ter')]")
//	public WebElement cefgridValue;
//	@FindBy(xpath = "(//h5[contains(text(),'View Custom')]/parent::div/following-sibling::span//button)[3]")
//	public WebElement btnEditcef;
//	/* CEF details pannel */
//	@FindBy(xpath = "//span[text()='CO2e Factor']//parent::div//following-sibling::div")
//	public WebElement factorCO2e;
//	@FindBy(xpath = "//span[text()='Name of Custom EF']//parent::div//following-sibling::div")
//	public WebElement inputNameOfCustmEFverify;
//	@FindBy(xpath = "//span[text()='Source of Emission Factor']//parent::div//following-sibling::div")
//	public WebElement inputSourceEmsnFctrverify;
//	@FindBy(xpath = "//span[text()='CO2']//parent::div//following-sibling::div")
//	public WebElement inputCo2verify;
//	@FindBy(xpath = "//span[text()='CH4']//parent::div//following-sibling::div")
//	public WebElement inputCH4verify;
//	@FindBy(xpath = "//span[text()='N2O']//parent::div//following-sibling::div")
//	public WebElement inputN2Overify;
//	@FindBy(xpath = "//span[text()='CO2e Factor']//parent::div//following-sibling::div")
//	public WebElement co2eFcatorverify;
//	/*---------*/
//	@FindBy(xpath = "//input[@id='NameOfCustomEmission']")
//	public WebElement nameofcustom;
//	@FindBy(xpath = "//input[@id='energy_category_name']")
//	public WebElement inputenergyCategory;
//	@FindBy(xpath = "(//span[contains(text(),'Denominator')])[2]")
//	public WebElement selectenergyCategory;
//	String createdCustomEmissionFactor;
//	WebElement firstCEF;

//	public void Verify_Add_Custom_Emission_Factor_Scope2() {
//		Actions cta = new Actions(driver);
//		waitForElement(btnIndirectEmisssion);
//		clickOn(btnIndirectEmisssion, "Clicked on scope 2");
//		clickOn(btnParameterInput, "clicked on parameter input");
//		cta.moveToElement(btnAddCstmEmsonFctr).click().perform();
//		//clickOn(btnAddCstmEmsonFctr, "add new custom emission factor");
//		//clickOn(cefgridValue, "clicked on cef gird value");
//		//String CO2e_Factor = factorCO2e.getText();
//		//clickOn(btnEditcef, "clicked on edit button");
//		//sleep(3000);
//		//verifyIfElementPresent(lblEditCustomEmission, "viewing label", "calculator scope 2");
//		/* ---- creating rndom number form date class ------ */
//		String randomNumber = date.toString().replaceAll(" ", "").replaceAll("[a-z]", "");
//		String somValue = randomNumber.replaceAll("[A-Z]", "");
//		String[] spliting = somValue.split(":");
//		String req = "ABC";
//		String randomOfCustm = req.concat(spliting[2]);
//		/*-----------*/
//		cta.doubleClick(nameofcustom).perform();
//		enterText(nameofcustom, "entered Name of Custom EF", randomOfCustm);
//		sleep(3000);
//		clickOn(inputenergyCategory, "clicked on energy category");
//		cta.moveToElement(selectenergyCategory).click().perform();
//		sleep(3000);
//		String qwe = "abc";
//		String randomSrcEmsn = qwe.concat(spliting[2]);
//		cta.doubleClick(srcOfEmisn).perform();
//		enterText(srcOfEmisn, "entred value of source emission", randomSrcEmsn);
//		sleep(3000);
//		clickOn(inputunitCustmEFDen, "clicked on unit custom denominator drop down");
//		sleep(1000);
//		WebElement selectdropvalueDen = driver.findElement(By.xpath("//input[@id='CH4']"));
//		cta.moveToElement(selectdropvalueDen).click().perform();
//		int rndvlaue1 = rnd.nextInt(10);
//		String rndvalueco2 = Integer.toString(rndvlaue1);
//		enterText(inputCO2, "entered co2 value", rndvalueco2);
//		int rndvlaue2 = rnd.nextInt(10);
//		String rndvaluech4 = Integer.toString(rndvlaue2);
//		enterText(inputCH4, "entered ch4 value", rndvaluech4);
//		int rndvlaue3 = rnd.nextInt(10);
//		String rndvalueN20 = Integer.toString(rndvlaue3);
//		enterText(inputN2O, "entered n2o value", rndvalueN20);
//		clickOn(inputunitCstnEFNum, "clicked on unit custom numerator drop down");
//		WebElement selectdropvalueNum = driver.findElement(By.xpath("//button[text()='Close']"));
//		cta.moveToElement(selectdropvalueNum).click().perform();
//		clickOn(btnsave, "clicked on save button");
//		/*----------VERIFYING TOAST MESSAGE--------*/
//		By toast = By.xpath("//div[text()='Custom Emission added successfully']");
//		if (isElementPresent(toast)) {
//			passed("Successfully added Custom Emission");
//		} else {
//			failed(driver, "Failed to add Custom Emission");
//		}
//		clickOn(btnClose, "close view emission factor");
//		sleep(2000);
//		/*---------VERIFYING VIEW PANNEL-----------*/
//		createdCustomEmissionFactor = "//div[text()='" + randomOfCustm + "']";
//		clickOnElementWithDynamicXpath(createdCustomEmissionFactor, "clicked created customemission factor");
//		if (verifyIfElementPresent(lblViewCustomEmission, "viewing label", "calculator scope 2")) {
//			passed("custom emission factor detail page displayed");
//		} else {
//			failed(driver, "custom emission factor details page not displayed");
//		}
//		/*---------CUSTOM EMISSION DETAIL PANNEL-----------*/
//		if (inputNameOfCustmEFverify.getText().equals(randomOfCustm)) {
//			passed("name of custom emission EF inputed correctly");
//		} else {
//			failed(driver, "name of custom emission EF inputed incorrectly");
//		}
//		if (inputSourceEmsnFctrverify.getText().equals(randomSrcEmsn)) {
//			passed("source of custom emission  inputed correctly");
//		} else {
//			failed(driver, "soucre of custom emission EF inputed incorrectly");
//		}
//		if (inputCo2verify.getText().equals(rndvalueco2)) {
//			passed("co2 inputed correctly");
//		} else {
//			failed(driver, "co2 inputed incorrectly");
//		}
//		if (inputCH4verify.getText().equals(rndvaluech4)) {
//			passed("ch4 inputed correctly");
//		} else {
//			failed(driver, "ch4 inputed incorrectly");
//		}
//		if (inputN2Overify.getText().equals(rndvalueN20)) {
//			passed("n2o inputed correctly");
//		} else {
//			failed(driver, "n20 inputed incorrectly");
//		}
//		clickOn(btnClose, "close emision factor");
//		/*----TOP MOST ROW----*/
//		firstCEF = driver.findElement(By.xpath(createdCustomEmissionFactor));
//		String row_No = firstCEF.getAttribute("aria-colindex");
//		int rowCount = Integer.parseInt(row_No);
//		try {
//			if (rowCount == 1) {
//				passed("created custom emission factor is top most as it at " + rowCount);
//			} else {
//				failed(driver, "created custom emission factor is not at top most beacuse it is at" + row_No);
//			}
//		} catch (NumberFormatException e) {
//			failed(driver, "Exception caught" + e.getMessage());
//		}
//		/*--------CUSTOM EMISSION FACTOR ADDED, AVAILBLE WITHOUT REFRESH------------*/
//		if (firstCEF.isDisplayed()) {
//			passed("custom emission factor added available without refresh");
//		} else {
//			failed(driver, "custom emission factor not added");
//		}
//		/*-------------------------*/
//	}

//	@FindBy(xpath = "(//div[@role='rowgroup'])[2]//div[contains(@aria-label,'Press SPACE')]//div")
//	private List<WebElement> webTableCef;
//	@FindBy(xpath = "//div[@role='rowgroup'])[2]//div[contains(@aria-label,'Press SPACE')]")
//	private List<WebElement> no_OfRecords;
//	@FindBy(xpath = "//span[text()='Name of Custom EF']")
//	public WebElement colnameOfCust;
//	@FindBy(xpath = "//button[contains(text(),'Cancel')]")
//	public WebElement btnCancel;
	/*-------------------------------*/

//	public void validate_Edit_Save_Cancel_CEF() {
//		try {
//			Actions ces = new Actions(driver);
//			waitForElement(btnIndirectEmisssion);
//			clickOn(btnIndirectEmisssion, "Clicked on scope 2");
//			clickOn(btnParameterInput, "clicked on parameter input");
//			ces.moveToElement(btnAddCstmEmsonFctr).click().perform();
//			String randomNumber = date.toString().replaceAll(" ", "").replaceAll("[a-z]", "");
//			String somValue = randomNumber.replaceAll("[A-Z]", "");
//			String[] spliting = somValue.split(":");
//			String req = "ABC";
//			String randomOfCustm = req.concat(spliting[2]);
//			/*-----------*/
//			ces.doubleClick(nameofcustom).perform();
//			enterText(nameofcustom, "entered Name of Custom EF", randomOfCustm);
//			sleep(3000);
//			clickOn(inputenergyCategory, "clicked on energy category");
//			ces.moveToElement(selectenergyCategory).click().perform();
//			sleep(3000);
//			String qwe = "abc";
//			String randomSrcEmsn = qwe.concat(spliting[2]);
//			ces.doubleClick(srcOfEmisn).perform();
//			enterText(srcOfEmisn, "entred value of source emission", randomSrcEmsn);
//			sleep(3000);
//			clickOn(inputunitCustmEFDen, "clicked on unit custom denominator drop down");
//			sleep(1000);
//			WebElement selectdropvalueDen = driver.findElement(By.xpath("//input[@id='CH4']"));
//			ces.moveToElement(selectdropvalueDen).click().perform();
//			int rndvlaue1 = rnd.nextInt(10);
//			String rndvalueco2 = Integer.toString(rndvlaue1);
//			enterText(inputCO2, "entered co2 value", rndvalueco2);
//			int rndvlaue2 = rnd.nextInt(10);
//			String rndvaluech4 = Integer.toString(rndvlaue2);
//			enterText(inputCH4, "entered ch4 value", rndvaluech4);
//			int rndvlaue3 = rnd.nextInt(10);
//			String rndvalueN20 = Integer.toString(rndvlaue3);
//			enterText(inputN2O, "entered n2o value", rndvalueN20);
//			clickOn(inputunitCstnEFNum, "clicked on unit custom numerator drop down");
//			WebElement selectdropvalueNum = driver.findElement(By.xpath("//button[text()='Close']"));
//			ces.moveToElement(selectdropvalueNum).click().perform();
//			/*------ADD-CANCEL BUTTON------*/
//			clickOn(btnClose, "cancel add custom emission factor");
//			sleep(2000);
//			String qas = "//div[text()='" + randomOfCustm + "']";
//			By frstRecord = By.xpath(qas);
//			if (!isElementPresent(frstRecord)) {
//				passed("custom emission factor not get created after clicking cancel button");
//			} else {
//				failed(driver, "custom emission factor got created after clicking cancel button");
//			}
//			/*--------EDIT, SAVE--------------*/
//			List<WebElement> ew = driver.findElements(By.xpath("//div[@ref='eContainer']//div[@role='row']"));
//			for (int i = 0; i < ew.size(); i++) {
//				if (i == 0) {
//					ew.get(i).click();
//					break;
//				}
//			}
//			WebElement btnedit = driver.findElement(By.xpath("//*[@type='submit']"));
//			clickOn(btnedit, "edit button");
//			Actions des = new Actions(driver);
//			des.moveToElement(inputCO2).doubleClick().perform();
//			int rndGnrt = rnd.nextInt(10);
//			String rndgnrtco2Value = Integer.toString(rndGnrt);
//			enterText(inputCO2, "co2", rndgnrtco2Value);
//			sleep(3000);
//			clickOn(btnSave, "save after editing");
//			By verifyTostMssg = By.xpath("//div[text()='Custom Emission updated successfully']");
//			if (isElementPresent(verifyTostMssg)) {
//				passed("saved successfully after editing");
//			} else {
//				failed(driver, "failed to update custom emission factor");
//			}
//			if (inputCo2verify.getText().equals(rndgnrtco2Value)) {
//				passed("co2 value inputed correctly");
//			} else {
//				failed(driver, "co2 value inputed incorrectly");
//			}
//			/*--------------EDIT, CANCEL------------*/
//			clickOn(btnedit, "edit button");
//			ces.moveToElement(inputCO2).doubleClick().perform();
//			int rndGnrt1 = rnd.nextInt(10);
//			String rndgnrtFacilityCode1 = Integer.toString(rndGnrt1);
//			enterText(inputCO2, "co2 value", rndgnrtFacilityCode1);
//			sleep(3000);
//			clickOn(btnCancel, "cancel edit custom emission factor");
//			By re = By.xpath("//div[text()='Edit Custom Emission Factor']");
//			if (!isElementPresent(re)) {
//				passed("edit custom emission factor pannel closed");
//			} else {
//				failed(driver, "edit custom emission factor pannel not closed");
//			}
//			clickOn(btnClose, "close button");
//		} catch (Exception e) {
//// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	// ------------------yogesh

//	@FindBy(xpath = "//div[text()='Waste Generated in Operations']")
//	public WebElement weWasteGeneratedInOperations;
////	@FindBy(xpath = "//button[text()='Distance Based Method']")
////	public WebElement btnDistanceBasedMethod;
////	@FindBy(xpath = "//button[text()='Spend Based Method']")
////	public WebElement btnSpendBasedMethod;
////	@FindBy(xpath = "//div[text()='Business Travel']")
////	public WebElement weBusinessTravelCalculator;
////	@FindBy(xpath = "//div[text()='Employee Commuting']")
////	public WebElement weEmployeeCommutingCalculator;
////	@FindBy(xpath = "//span[text()='Activities']/following-sibling::article//*[local-name()='svg']")
////	public WebElement btnAddActivities;
////	@FindBy(xpath = "//div[contains(text(),'Activities')]//parent::div//parent::div//div//*[local-name()='svg']")
////	public WebElement btnAddActivities1;
//	@FindBy(xpath = "//article[@aria-label='Add Activity']")
//	public WebElement btnAddActivities3_1;
////	@FindBy(xpath = "//span[text()='Next']//parent::button//following-sibling::button/*[@data-testid='BorderColorOutlinedIcon']") // span[text()='Next']//parent::button//following-sibling::button[@type='submit']
////	public WebElement btnEditActivity;
//	@FindBy(xpath = "//button[@aria-label='Previous']//following-sibling::button[@aria-label='Edit']")
//	public WebElement btnEditActivityqa;
//	@FindBy(xpath = "//div[text()='Edit Activity']//parent::div//parent::div//div//button[text()='Cancel']")
//	public WebElement btnCancelActivity;
//	@FindBy(xpath = "(//span[text()='Next']//parent::button//parent::span//following-sibling::button)[2]")
//	public WebElement btnEditActivityMobileComb;
//	@FindBy(xpath = "//span[text()='Next']//parent::button/following-sibling::button/*[@data-testid='BorderColorOutlinedIcon']") // span[text()='Next']//parent::button//following-sibling::button[@type='submit']
//	public WebElement btnEditActivityFuelEnergy;
//	@FindBy(xpath = "//input[@id='waste_produce']")
//	public WebElement txtMassOfWasteProduced;
//	@FindBy(xpath = "//span[text()='Description']//parent::div//following-sibling::div//textarea") // textarea[@id='description']
//	public WebElement txtDescription;
//	@FindBy(xpath = "//input[@id='Expense No']")
//	public WebElement txtInvoiceNum;
//	@FindBy(xpath = "//span[text()='Waste Category']//parent::div//following-sibling::div//div[@role='button']")
//	public WebElement drpWasteCategory;
//	@FindBy(xpath = "//input[@id='wm_management_type']")
//	public WebElement drpWasteType;
//	@FindBy(xpath = "//input[@id='wm_type']")
//	public WebElement drpWasteDisposalMethod;
//	@FindBy(xpath = "//input[@id='unit']")
//	public WebElement drpUnits;
//	@FindBy(xpath = "//span[text()='Mode of Business Travel']//parent::div//following-sibling::div//input") // LastChange
//	// //input[@id='Mode
//	// of
//	// Transport']
//	// --Xpath
//	public WebElement drpModeOfBusinessTravel;
//	@FindBy(xpath = "//input[@id='AmountofActivity']")
//	public WebElement txtAmountSpent;
//	@FindBy(xpath = "//input[@id='currency']")
//	public WebElement drpCurrency;
//	@FindBy(xpath = "//span[text()='Travel Category']//parent::div//following-sibling::div/div")
//	public WebElement drpTravelCategory;
//	@FindBy(xpath = "//span[text()='Vehicle Type']//parent::div//following-sibling::div//input") // old
//	// //input[@id='VehicleType']
//	// //new
//	// //input[@id='Vehicle
//	// Type']
//	public WebElement drpVehicleType;
//	@FindBy(xpath = "//input[@id='Units']")
//	public WebElement drpBusinessTravlUnits;
//	@FindBy(xpath = "//input[@id='TravelDistance']") // old //input[@id='TravelDistance']new //input[@id=' Average
//	// commute distance (daily)']
//	public WebElement txtDistanceTrvlByEmp;
//	@FindBy(xpath = "//span[text()='Average Daily Commute Distance ']//parent::div//following-sibling::div//input") // old
//	// //input[@id='TravelDistance']new
//	// //input[@id='
//	// Average
//	// commute
//	// //
//	// (daily)']
//	public WebElement txtAvgDailyCommuteDistance;
//	@FindBy(xpath = "//input[@id='TravelDescription']") // //input[@id='TravelDescription']
//	public WebElement txtTravelDescription;
//	@FindBy(xpath = "//span[text()=' Commute Description']//parent::div//following-sibling::div//input") // old-->//input[@id='TravelDescription']
//	// new -->
//	public WebElement txtCommuteDescription;
//	@FindBy(xpath = "//input[@id='NoOfTravel'] ") // Travel Description
//	public WebElement txtNoOfEmpTravel;
//	@FindBy(xpath = "//span[text()=' Number of Employees Commuted']//parent::div//following-sibling::div//input")
//	public WebElement txtNoOfEmpCommuted;
//	// old //input[@id='NoOfTravel'] new //input[@id='No of Employees Traveled']
//	@FindBy(xpath = "//span[text()='Employee / Group of Employees']//parent::div//following-sibling::div//input") // old--->//input[@id='GroupEmployee']
//	// new
//	// -->
//	// //input[@id='Employee/Group
//	// //
//	// employees']
//	public WebElement txtGroupEmployee;
//	//
//	@FindBy(xpath = "//span[text()=' Average Commute Days']//parent::div//following-sibling::div//input")
//	public WebElement txtAvgCommuteDays;
//	@FindBy(xpath = "//span[text()='Mode of Commute']//parent::div//following-sibling::div//input")
//	public WebElement drpModeOfCommute;
//	// Activity details
//	@FindBy(xpath = "//span[text()='Emission Factor']//parent::div//following-sibling::div")
//	public WebElement weEmissionFactor;
//	@FindBy(xpath = "//span[text()='Description']//parent::div//following-sibling::div")
//	public WebElement weDescription;
//	@FindBy(xpath = "//span[text()='Waste Category']//parent::div//following-sibling::div")
//	public WebElement weWasteCategory;
//	@FindBy(xpath = "//span[text()='Waste Type']//parent::div//following-sibling::div")
//	public WebElement weWasteType;
//	@FindBy(xpath = "//span[text()='Waste Disposal Method']//parent::div//following-sibling::div")
//	public WebElement weWasteDisposalMethod;
//	@FindBy(xpath = "//span[text()='Mass of Waste Produced']//parent::div//following-sibling::div")
//	public WebElement weMassOfWasteProduced;
//	@FindBy(xpath = "//span[text()='Units']//parent::div//following-sibling::div")
//	public WebElement weUnits;
//	@FindBy(xpath = "//span[text()='Source']//parent::div//following-sibling::div")
//	public WebElement weSource;
//	@FindBy(xpath = "//span[text()='Travel Description']//parent::div//following-sibling::div")
//	public WebElement weTravelDescription;
//	@FindBy(xpath = "//span[text()='Number of Employee Travelled']//parent::div//following-sibling::div")
//	public WebElement weNoOfEmpTrvl;
//	@FindBy(xpath = "//span[text()='Distance Travelled by Each Employee']//parent::div//following-sibling::div")
//	public WebElement weDistTrvlByEachEmp;
//	@FindBy(xpath = "//span[text()='Mode of Business Travel']//parent::div//following-sibling::div")
//	public WebElement weModeOfBusinessTrvl;
//	@FindBy(xpath = "//span[text()='Vehicle Type']//parent::div//following-sibling::div")
//	public WebElement weVehicalType;
//	@FindBy(xpath = "//span[text()='Activity Amount']//parent::div//following-sibling::div")
//	public WebElement weActivityAmount;
//	@FindBy(xpath = "//span[text()='Biofuel CO2']//parent::div//following-sibling::div")
//	public WebElement weBiofuelCO2;
//	@FindBy(xpath = "//span[text()='Travel Category']//parent::div//following-sibling::div")
//	public WebElement weTravelCategory;
//	@FindBy(xpath = "//span[text()='Amount Spent']//parent::div//following-sibling::div")
//	public WebElement weAmountSpent;
//	@FindBy(xpath = "//span[text()='Currency / Unit']//parent::div//following-sibling::div")
//	public WebElement weCurrencyOrUnit;

	
	

	

//	public void addActivityDetailsForWasteGeneratedInOperationsInActivityDetailsPannel() {
//		try {
//			waitForElement(drpFacilityName);
//			clickOn(drpFacilityName, "txtFacilityName");
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + data.get("FacilityName") + "']"));
//			clickOn(we, data.get("FacilityName"));
//			sleep(2000);
//			clickOn(txtDescription, " Description");
//			enterText(txtDescription, "Description", data.get("Description"));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", data.get("StartDate"));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", data.get("EndDate"));
//			clickOn(drpWasteCategory, "WasteCategory");
//			we = driver.findElement(By.xpath("//li[text()='" + data.get("WasteCategory") + "']"));
//			clickOn(we, data.get("WasteCategory"));
//			sleep(2000);
//			clickOn(drpWasteType, "WasteType");
//			we = driver.findElement(By.xpath("//li[text()='" + data.get("WasteType") + "']"));
//			clickOn(we, data.get("WasteType"));
//			sleep(2000);
//			clickOn(drpWasteDisposalMethod, "WasteDisposalMethod");
//			we = driver.findElement(By.xpath("//li[text()='" + data.get("WasteDisposalMethod") + "']"));
//			clickOn(we, data.get("WasteDisposalMethod"));
//			sleep(2000);
//			clickOn(drpUnits, "Units");
//			we = driver.findElement(By.xpath("//li[text()='" + data.get("Units") + "']"));
//			clickOn(we, data.get("Units"));
//			sleep(2000);
//			enterText(txtMassOfWasteProduced, "MassOfWasteProduced", data.get("MassOfWasteProduced"));
//			clickOn(btnSave, "Save Button");
//			sleep(10000);
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

//	public void ValidateActivityDetailsInViewActivityForWasteGeneratedOprtionsCalculator() {
//		try {
//			waitForElement(weFacilityName);
//			if (weFacilityName.getText().trim().equals(data.get("FacilityName").trim())) {
//				passed("Successfully Validated FacilityName In Activity Details As" + weFacilityName.getText());
//			} else {
//				failed(driver, "Failed To validate Facility Name In Activity Details Expected As "
//						+ data.get("FacilityName") + "But Actual is" + weFacilityName.getText());
//			}
//			if (weStartDate.getText().trim().equals(data.get("StartDate").trim())) {
//				passed("Successfully Validated StartDate In Activity Details As" + weStartDate.getText());
//			} else {
//				failed(driver, "Failed To validate StartDate In Activity Details Expected As " + data.get("StartDate")
//						+ "But Actual is" + weStartDate.getText());
//			}
//			if (weEndDate.getText().trim().equals(data.get("EndDate").trim())) {
//				passed("Successfully Validated EndDate In Activity Details As" + weEndDate.getText());
//			} else {
//				failed(driver, "Failed To validate EndDate In Activity Details Expected As " + data.get("EndDate")
//						+ "But Actual is" + weEndDate.getText());
//			}
//			if (weCustomEmissionFactor.getText().trim().equals(data.get("CustomEmissionFactor").trim())) {
//				passed("Successfully Validated CustomEmissionFactor In Activity Details As"
//						+ weCustomEmissionFactor.getText());
//			} else {
//				failed(driver, "Failed To validate CustomEmissionFactor In Activity Details Expected As "
//						+ data.get("CustomEmissionFactor") + "But Actual is" + weCustomEmissionFactor.getText());
//			}
//			if (weWasteCategory.getText().trim().equals(data.get("WasteCategory").trim())) {
//				passed("Successfully Validated WasteCategory In Activity Details As" + weWasteCategory.getText());
//			} else {
//				failed(driver, "Failed To validate WasteCategory In Activity Details Expected As "
//						+ data.get("WasteCategory") + "But Actual is" + weWasteCategory.getText());
//			}
//			if (weWasteType.getText().trim().equals(data.get("WasteType").trim())) {
//				passed("Successfully Validated WasteType In Activity Details As" + weWasteType.getText());
//			} else {
//				failed(driver, "Failed To validate WasteType In Activity Details Expected As " + data.get("WasteType")
//						+ "But Actual is" + weWasteType.getText());
//			}
//			if (weWasteDisposalMethod.getText().trim().equals(data.get("WasteDisposalMethod").trim())) {
//				passed("Successfully Validated WasteDisposalMethod In Activity Details As"
//						+ weWasteDisposalMethod.getText());
//			} else {
//				failed(driver, "Failed To validate WasteDisposalMethod In Activity Details Expected As "
//						+ data.get("WasteDisposalMethod") + "But Actual is" + weWasteDisposalMethod.getText());
//			}
//			if (weMassOfWasteProduced.getText().trim().equals(data.get("MassOfWasteProduced").trim())) {
//				passed("Successfully Validated MassOfWasteProduced In Activity Details As"
//						+ weMassOfWasteProduced.getText());
//			} else {
//				failed(driver, "Failed To validate MassOfWasteProduced In Activity Details Expected As "
//						+ data.get("MassOfWasteProduced") + "But Actual is" + weMassOfWasteProduced.getText());
//			}
//			if (weUnits.getText().trim().equals(data.get("Units").trim())) {
//				passed("Successfully Validated Units In Activity Details As" + weUnits.getText());
//			} else {
//				failed(driver, "Failed To validate Units In Activity Details Expected As " + data.get("Units")
//						+ "But Actual is" + weUnits.getText());
//			}
//			if (weEmissionDetails_tCO2e.getText().trim().equals(data.get("tCO2e").trim())) {
//				passed("Successfully Validated tCo2e In Emission Details As" + weEmissionDetails_tCO2e.getText());
//			} else {
//				failed(driver, "Failed To validate tCo2e In Emission Details Expected As " + data.get("tCO2e")
//						+ "But Actual is" + weEmissionDetails_tCO2e.getText());
//			}
//			System.out.println(weEmissionDetails_EmissionFactor.getText());
//			if (weEmissionDetails_EmissionFactor.getText().trim().contains(data.get("EmissionFactor").trim())) {
//				passed("Successfully Validated EmissionFactor In Emission Details As"
//						+ weEmissionDetails_EmissionFactor.getText());
//			} else {
//				failed(driver, "Failed To validate EmissionFactor In Emission Details Expected As "
//						+ data.get("EmissionFactor") + "But Actual is" + weEmissionDetails_EmissionFactor.getText());
//			}
//			if (weSource.getText().trim().equals(data.get("Source").trim())) {
//				passed("Successfully Validated Source In Activity Details As" + weSource.getText());
//			} else {
//				failed(driver, "Failed To validate Source In Activity Details Expected As " + data.get("Source")
//						+ "But Actual is" + weSource.getText());
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void addActivityDetailsForWasteGeneratedInOperationsInActivityDetailsPannel_Multiple(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator",
//					"Facility Name");
//			List<String> description = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator", "Description");
//			List<String> startDate = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator", "End Date");
//			List<String> wasteCategory = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator",
//					"Waste Category");
//			List<String> wasteType = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator", "Waste Type");
//			List<String> wasteDisposalMethod = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator",
//					"Waste Disposal Method");
//			List<String> massOfWasteProduced = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator",
//					"Mass of Waste Produced");
//			List<String> units = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator", "Units");
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator",
//					"Custom Emission Factor");
//			List<String> tCO2e = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator", "tCO2e");
//			List<String> emissionFactor = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator",
//					"Emission Factor");
//			List<String> source = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator", "Source");
//			waitForElement(drpFacilityName);
//			clickOn(drpFacilityName, "txtFacilityName");
//			// enterText(drpFacilityName, "Facility Name", facilityName.get(i));
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			sleep(2000);
//			clickOn(txtDescription, " Description");
//			enterText(txtDescription, "Description", description.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			clickOn(drpWasteCategory, "WasteCategory");
//			we = driver.findElement(By.xpath("//li[text()='" + wasteCategory.get(i) + "']"));
//			clickOn(we, wasteCategory.get(i));
//			sleep(2000);
//			clickOn(drpWasteType, "WasteType");
//			we = driver.findElement(By.xpath("//li[text()='" + wasteType.get(i) + "']"));
//			clickOn(we, wasteType.get(i));
//			sleep(2000);
//			clickOn(drpWasteDisposalMethod, "WasteDisposalMethod");
//			we = driver.findElement(By.xpath("//li[text()='" + wasteDisposalMethod.get(i) + "']"));
//			clickOn(we, wasteDisposalMethod.get(i));
//			sleep(2000);
//			clickOn(drpUnits, "Units");
//			we = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			clickOn(we, units.get(i));
//			sleep(2000);
//			enterText(txtMassOfWasteProduced, "MassOfWasteProduced", massOfWasteProduced.get(i));
//			clickOn(btnSave, "Save Button");
//			sleep(10000);
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

//	public void validateActivityDetailsInViewActivityForWasteGeneratedOprtionsCalculator_Multiple(int i) {
//		try {
//			takeScreenshot(driver);
//			String[] activityDetailFieldNames = { "Facility Name", "Description", "Start Date", "End Date",
//					"Custom Emission Factor", "Waste Category", "Waste Type", "Waste Disposal Method",
//					"Mass of Waste Produced", "Units", "tCO2e", "Emission Factor", "Source" };
//			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath(
//						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//			List<String> facilityName = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator",
//					"FacilityName");
//
//			List<String> description = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator", "Description");
//
//			List<String> startDate = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator", "StartDate");
//
//			List<String> endDate = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator", "EndDate");
//
//			List<String> wasteCategory = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator",
//					"WasteCategory");
//
//			List<String> wasteType = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator", "WasteType");
//
//			List<String> wasteDisposalMethod = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator",
//					"WasteDisposalMethod");
//
//			List<String> massOfWasteProduced = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator",
//					"MassOfWasteProduced");
//
//			List<String> units = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator", "Units");
//
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator",
//					"CustomEmissionFactor");
//
//			List<String> tCO2e = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator", "tCO2e");
//
//			List<String> emissionFactor = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator",
//					"EmissionFactor");
//
//			List<String> source = MultiDataExcelReader.getTestData("WasteGeneratedInOpsCalculator", "Source");
//
//			waitForElement(weFacilityName);
//
//			if (weFacilityName.getText().trim().equals(facilityName.get(i).trim())) {
//				passed("Successfully Validated FacilityName In Activity Details As" + weFacilityName.getText());
//			} else {
//				failed(driver, "Failed To validate Facility Name In Activity Details Expected As " + facilityName.get(i)
//						+ "But Actual is" + weFacilityName.getText());
//			}
//			if (weDescription.getText().trim().equals(description.get(i).trim())) {
//				passed("Successfully Validated description In Activity Details As" + weFacilityName.getText());
//			} else {
//				failed(driver, "Failed To validate description In Activity Details Expected As " + description.get(i)
//						+ "But Actual is" + weDescription.getText());
//			}
//
//			if (weStartDate.getText().trim().equals(startDate.get(i).trim())) {
//				passed("Successfully Validated StartDate In Activity Details As" + weStartDate.getText());
//			} else {
//				failed(driver, "Failed To validate StartDate In Activity Details Expected As " + startDate.get(i)
//						+ "But Actual is" + weStartDate.getText());
//			}
//
//			if (weEndDate.getText().trim().equals(endDate.get(i).trim())) {
//				passed("Successfully Validated EndDate In Activity Details As" + weEndDate.getText());
//			} else {
//				failed(driver, "Failed To validate EndDate In Activity Details Expected As " + endDate.get(i)
//						+ "But Actual is" + weEndDate.getText());
//			}
//
//			if (weWasteCategory.getText().trim().equals(wasteCategory.get(i).trim())) {
//				passed("Successfully Validated WasteCategory In Activity Details As" + weWasteCategory.getText());
//			} else {
//				failed(driver, "Failed To validate WasteCategory In Activity Details Expected As "
//						+ wasteCategory.get(i) + "But Actual is" + weWasteCategory.getText());
//			}
//			if (weWasteType.getText().trim().equals(wasteType.get(i).trim())) {
//				passed("Successfully Validated WasteType In Activity Details As" + weWasteType.getText());
//			} else {
//				failed(driver, "Failed To validate WasteType In Activity Details Expected As " + wasteType.get(i)
//						+ "But Actual is" + weWasteType.getText());
//			}
//
//			if (weWasteDisposalMethod.getText().trim().equals(wasteDisposalMethod.get(i).trim())) {
//				passed("Successfully Validated WasteDisposalMethod In Activity Details As"
//						+ weWasteDisposalMethod.getText());
//			} else {
//				failed(driver, "Failed To validate WasteDisposalMethod In Activity Details Expected As "
//						+ wasteDisposalMethod.get(i) + "But Actual is" + weWasteDisposalMethod.getText());
//			}
//
//			if (weMassOfWasteProduced.getText().trim().equals(massOfWasteProduced.get(i).trim())) {
//				passed("Successfully Validated MassOfWasteProduced In Activity Details As"
//						+ weMassOfWasteProduced.getText());
//			} else {
//				failed(driver, "Failed To validate MassOfWasteProduced In Activity Details Expected As "
//						+ massOfWasteProduced.get(i) + "But Actual is" + weMassOfWasteProduced.getText());
//			}
//
//			if (weUnits.getText().trim().equals(units.get(i).trim())) {
//				passed("Successfully Validated Units In Activity Details As" + weUnits.getText());
//			} else {
//				failed(driver, "Failed To validate Units In Activity Details Expected As " + units.get(i)
//						+ "But Actual is" + weUnits.getText());
//			}
//
//			if (weEmissionDetails_tCO2e.getText().trim().equals(tCO2e.get(i).trim())) {
//				passed("Successfully Validated tCo2e In Emission Details As" + weEmissionDetails_tCO2e.getText());
//			} else {
//				failed(driver, "Failed To validate tCo2e In Emission Details Expected As " + tCO2e.get(i)
//						+ "But Actual is" + weEmissionDetails_tCO2e.getText());
//			}
//
//			System.out.println(weEmissionDetails_EmissionFactor.getText());
//			if (weEmissionDetails_EmissionFactor.getText().trim().contains(emissionFactor.get(i).trim())) {
//				passed("Successfully Validated EmissionFactor In Emission Details As"
//						+ weEmissionDetails_EmissionFactor.getText());
//			} else {
//				failed(driver, "Failed To validate EmissionFactor In Emission Details Expected As "
//						+ emissionFactor.get(i) + "But Actual is" + weEmissionDetails_EmissionFactor.getText());
//			}
//
//			if (weSource.getText().trim().equals(source.get(i).trim())) {
//				passed("Successfully Validated Source In Activity Details As" + weSource.getText());
//			} else {
//				failed(driver, "Failed To validate Source In Activity Details Expected As " + source.get(i)
//						+ "But Actual is" + weSource.getText());
//			}

//	public void addActivityDetailsForBusinessTravelDistanceBasedCalculatorInActivityDetailsPannel_Multiple(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("BusinessTravelDistBasedCalc",
//					"Facility Name");
//			List<String> inVoiceNo = MultiDataExcelReader.getTestData("BusinessTravelDistBasedCalc", "Invoice No.");
//			List<String> invoiceDate = MultiDataExcelReader.getTestData("BusinessTravelDistBasedCalc", "Invoice Date");
//			List<String> startDate = MultiDataExcelReader.getTestData("BusinessTravelDistBasedCalc", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("BusinessTravelDistBasedCalc", "End Date");
//			List<String> numberOfEmployeeTraveled = MultiDataExcelReader.getTestData("BusinessTravelDistBasedCalc",
//					"Number of Employee Travelled");
//			List<String> travelDescription = MultiDataExcelReader.getTestData("BusinessTravelDistBasedCalc",
//					"Travel Description");
//			List<String> modeOfBusinessTravel = MultiDataExcelReader.getTestData("BusinessTravelDistBasedCalc",
//					"Mode of Business Travel");
//			List<String> vehicalType = MultiDataExcelReader.getTestData("BusinessTravelDistBasedCalc", "Vehicle Type");
//			List<String> distanceTravelByEachEmp = MultiDataExcelReader.getTestData("BusinessTravelDistBasedCalc",
//					"Distance Travelled by Each Employee");
//			List<String> units = MultiDataExcelReader.getTestData("BusinessTravelDistBasedCalc", "Units");
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("BusinessTravelDistBasedCalc",
//					"Custom Emission Factor");
//			List<String> tCO2 = MultiDataExcelReader.getTestData("BusinessTravelDistBasedCalc", "tCO2");
//			List<String> tCH4 = MultiDataExcelReader.getTestData("BusinessTravelDistBasedCalc", "tCH4");
//			List<String> tN2O = MultiDataExcelReader.getTestData("BusinessTravelDistBasedCalc", "tN2O");
//			List<String> biofuelCO2 = MultiDataExcelReader.getTestData("BusinessTravelDistBasedCalc", "Biofuel CO2");
//			List<String> tCO2e = MultiDataExcelReader.getTestData("BusinessTravelDistBasedCalc", "tCO2e");
//			List<String> emissionFactor = MultiDataExcelReader.getTestData("BusinessTravelDistBasedCalc",
//					"Emission Factor");
//			List<String> source = MultiDataExcelReader.getTestData("BusinessTravelDistBasedCalc", "Source");
//			waitForElement(drpFacilityName);
//			clickOn(drpFacilityName, "txtFacilityName");
//			// enterText(drpFacilityName, "Facility Name", facilityName.get(i));
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			sleep(2000);
//			clickOn(txtInvoiceNum, " InVoiceNo");
//			enterText(txtInvoiceNum, "InvoiceNo", inVoiceNo.get(i));
//			clickOn(txtInvoiceDate, "Invoice date");
//			enterText(txtInvoiceDate, "Invoice Date", invoiceDate.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			clickOn(txtNoOfEmpTravel, " NoOfEmpTravel");
//			sleep(2000);
//			enterText(txtNoOfEmpTravel, "numberOfEmployeeTraveled", numberOfEmployeeTraveled.get(i));
//			clickOn(txtTravelDescription, " Travel Description");
//			enterText(txtTravelDescription, "Travel Description", travelDescription.get(i));
//			clickOn(drpModeOfBusinessTravel, "Mode Of Business Travel");
//			we = driver.findElement(By.xpath("//li[text()='" + modeOfBusinessTravel.get(i) + "']"));
//			clickOn(we, modeOfBusinessTravel.get(i));
//			sleep(2000);
//			clickOn(drpVehicleType, "VehicalType");
//			we = driver.findElement(By.xpath("//li[text()='" + vehicalType.get(i) + "']"));
//			clickOn(we, vehicalType.get(i));
//			sleep(2000);
//			clickOn(drpBusinessTravlUnits, "WasteDisposalMethod");
//			we = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			clickOn(we, units.get(i));
//			sleep(2000);
//			clickOn(txtDistanceTrvlByEmp, "DistanceTravelByEmp");
//			enterText(txtDistanceTrvlByEmp, "DistanceTravelByEmp", distanceTravelByEachEmp.get(i));
//			clickOn(btnSave, "Save Button");
//			sleep(10000);
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

//	public void validateActivityDetailsInViewActivityForBusinessTravelDistanceBasedCalculator_Multiple(int i) {
//		try {
//			takeScreenshot(driver);
//			String[] activityDetailFieldNames = { "Facility Name", "Invoice No.", "Invoice Date", "Start Date",
//					"End Date", "Travel Description", "Number of Employee Travelled", "Custom Emission Factor",
//					"Distance Travelled by Each Employee", "Mode of Business Travel", "Vehicle Type", "Activity Amount",
//					"Units", "tCO2", "tCH4", "tN2O", "Biofuel CO2", "tCO2e", "Emission Factor", "Source" };
//			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath(
//						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("BusinessTravelDistBasedCalc",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//			List<String> facilityName = MultiDataExcelReader.getTestData("BusinessTravelCalculator", "FacilityName");
//
//			List<String> inVoiceNo = MultiDataExcelReader.getTestData("BusinessTravelCalculator", "InVoiceNo");
//
//			List<String> invoiceDate = MultiDataExcelReader.getTestData("BusinessTravelCalculator", "InvoivceDate");
//
//			List<String> startDate = MultiDataExcelReader.getTestData("BusinessTravelCalculator", "StartDate");
//
//			List<String> endDate = MultiDataExcelReader.getTestData("BusinessTravelCalculator", "EndDate");
//
//			List<String> numberOfEmployeeTraveled = MultiDataExcelReader.getTestData("BusinessTravelCalculator",
//					"NumberOfEmployeeTraveled");
//
//			List<String> travelDescription = MultiDataExcelReader.getTestData("BusinessTravelCalculator",
//					"TravelDescription");
//
//			List<String> modeOfBusinessTravel = MultiDataExcelReader.getTestData("BusinessTravelCalculator",
//					"ModeOfBusinessTravel");
//
//			List<String> vehicalType = MultiDataExcelReader.getTestData("BusinessTravelCalculator", "VehicalType");
//			
//			List<String> activityAmount = MultiDataExcelReader.getTestData("BusinessTravelCalculator", "ActivityAmount");
//
//			List<String> distanceTravelByEachEmp = MultiDataExcelReader.getTestData("BusinessTravelCalculator",
//					"DistanceTravelByEachEmp");
//
//			List<String> units = MultiDataExcelReader.getTestData("BusinessTravelCalculator", "Units");
//
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("BusinessTravelCalculator",
//					"CustomEmissionFactor");
//
//			List<String> tCO2 = MultiDataExcelReader.getTestData("BusinessTravelCalculator", "tCO2");
//
//			List<String> tCH4 = MultiDataExcelReader.getTestData("BusinessTravelCalculator", "tCH4");
//
//			List<String> tN2O = MultiDataExcelReader.getTestData("BusinessTravelCalculator", "tN2O");
//
//			List<String> biofuelCO2 = MultiDataExcelReader.getTestData("BusinessTravelCalculator", "BiofuelCO2");
//
//			List<String> tCO2e = MultiDataExcelReader.getTestData("BusinessTravelCalculator", "tCO2e");
//
//			List<String> emissionFactor = MultiDataExcelReader.getTestData("BusinessTravelCalculator",
//					"EmissionFactor");
//
//			List<String> source = MultiDataExcelReader.getTestData("BusinessTravelCalculator", "Source");
//
//			waitForElement(weFacilityName);
//
//			if (weFacilityName.getText().trim().equals(facilityName.get(i).trim())) {
//				passed("Successfully Validated FacilityName In Activity Details As" + weFacilityName.getText());
//			} else {
//				failed(driver, "Failed To validate Facility Name In Activity Details Expected As " + facilityName.get(i)
//						+ "But Actual is" + weFacilityName.getText());
//			}
//			
//			if (weInVoiceNo.getText().trim().equals(inVoiceNo.get(i).trim())) {
//				passed("Successfully Validated InVoiceNo In Activity Details As" + weInVoiceNo.getText());
//			} else {
//				failed(driver, "Failed To validate InVoiceNo In Activity Details Expected As " + inVoiceNo.get(i)
//						+ "But Actual is" + weInVoiceNo.getText());
//			}
//			
//			if (weInVoiceDate.getText().trim().equals(invoiceDate.get(i).trim())) {
//				passed("Successfully Validated InVoiceDate In Activity Details As" + weInVoiceDate.getText());
//			} else {
//				failed(driver, "Failed To validate InVoiceDate In Activity Details Expected As " + invoiceDate.get(i)
//						+ "But Actual is" + weInVoiceDate.getText());
//			}
//			
//			if (weStartDate.getText().trim().equals(startDate.get(i).trim())) {
//				passed("Successfully Validated StartDate In Activity Details As" + weStartDate.getText());
//			} else {
//				failed(driver, "Failed To validate StartDate In Activity Details Expected As " + startDate.get(i)
//						+ "But Actual is" + weStartDate.getText());
//			}
//
//			if (weEndDate.getText().trim().equals(endDate.get(i).trim())) {
//				passed("Successfully Validated EndDate In Activity Details As" + weEndDate.getText());
//			} else {
//				failed(driver, "Failed To validate EndDate In Activity Details Expected As " + endDate.get(i)
//						+ "But Actual is" + weEndDate.getText());
//			}
//
//			if (weTravelDescription.getText().trim().equals(travelDescription.get(i).trim())) {
//				passed("Successfully Validated travel Description In Activity Details As" + weTravelDescription.getText());
//			} else {
//				failed(driver, "Failed To validate travel Description In Activity Details Expected As " +travelDescription .get(i)
//						+ "But Actual is" + weTravelDescription.getText());
//			}
//			
//			if (weNoOfEmpTrvl.getText().trim().equals(numberOfEmployeeTraveled.get(i).trim())) {
//				passed("Successfully Validated  In Activity Details As" + weNoOfEmpTrvl.getText());
//			} else {
//				failed(driver, "Failed To validate EndDate In Activity DetaiweEndDatels Expected As " + numberOfEmployeeTraveled.get(i)
//						+ "But Actual is" + weNoOfEmpTrvl.getText());
//			}
//			
//			if (weCustomEmissionFactor.getText().trim().equals(customEmissionFactor.get(i).trim())) {
//				passed("Successfully Validated CustomEmissionFactor In Activity Details As"
//						+ weCustomEmissionFactor.getText());
//			} else {
//				failed(driver, "Failed To validate CustomEmissionFactor In Activity Details Expected As "
//						+ customEmissionFactor.get(i) + "But Actual is" + weCustomEmissionFactor.getText());
//			}
//			
//			if (weDistTrvlByEachEmp.getText().trim().equals(distanceTravelByEachEmp.get(i).trim())) {
//				passed("Successfully Validated Distance Travel By Each Emp In Activity Details As" + weDistTrvlByEachEmp.getText());
//			} else {
//				failed(driver, "Failed To validate Distance Travel By Each Emp In Activity Details Expected As " + distanceTravelByEachEmp.get(i)
//						+ "But Actual is" + weDistTrvlByEachEmp.getText());
//			}
//			
//			if (weModeOfBusinessTrvl.getText().trim().equals(modeOfBusinessTravel.get(i).trim())) {
//				passed("Successfully Validated ModeOfBusinessTrvl In Activity Details As" + weModeOfBusinessTrvl.getText());
//			} else {
//				failed(driver, "Failed To validate ModeOfBusinessTrvl In Activity Details Expected As " + endDate.get(i)
//						+ "But Actual is" + weModeOfBusinessTrvl.getText());
//			}
//			
//			if (weVehicalType.getText().trim().equals(vehicalType.get(i).trim())) {
//				passed("Successfully Validated Vehical Type In Activity Details As" + weVehicalType.getText());
//			} else {
//				failed(driver, "Failed To validate Vehical Type In Activity Details Expected As " + vehicalType.get(i)
//						+ "But Actual is" + weVehicalType.getText());
//			}
//			
//			if (weActivityAmount.getText().trim().equals(activityAmount.get(i).trim())) {
//				passed("Successfully Validated activityAmount In Activity Details As" + weActivityAmount.getText());
//			} else {
//				failed(driver, "Failed To validate activityAmount In Activity Details Expected As " + activityAmount.get(i)
//						+ "But Actual is" + weActivityAmount.getText());
//			}
//			
//			if (weUnits.getText().trim().equals(units.get(i).trim())) {
//				passed("Successfully Validated Units In Activity Details As" + weUnits.getText());
//			} else {
//				failed(driver, "Failed To validate Units In Activity Details Expected As " + units.get(i)
//						+ "But Actual is" + weUnits.getText());
//			}
//
//			if (weEmissionDetails_tCO2.getText().trim().equals(tCO2.get(i).trim())) {
//				passed("Successfully Validated tCo2 In Emission Details As" + weEmissionDetails_tCO2.getText());
//			} else {
//				failed(driver, "Failed To validate tCo2 In Emission Details Expected As " + tCO2.get(i)
//						+ "But Actual is" + weEmissionDetails_tCO2.getText());
//			}
//
//			if (weEmissionDetails_tCH4.getText().trim().equals(tCH4.get(i).trim())) {
//				passed("Successfully Validated tCH4 In Emission Details As" + weEmissionDetails_tCH4.getText());
//			} else {
//				failed(driver, "Failed To validate tCH4 In Emission Details Expected As " + tCH4.get(i)
//						+ "But Actual is" + weEmissionDetails_tCH4.getText());
//			}
//
//			if (weEmissionDetails_tN2O.getText().trim().equals(tN2O.get(i).trim())) {
//				passed("Successfully Validated tN2O In Emission Details As" + weEmissionDetails_tN2O.getText());
//			} else {
//				failed(driver, "Failed To validate tN2O In Emission Details Expected As " + tN2O.get(i)
//						+ "But Actual is" + weEmissionDetails_tN2O.getText());
//			}
//
//			if (weEmissionDetails_tCO2e.getText().trim().equals(tCO2e.get(i).trim())) {
//				passed("Successfully Validated tCo2e In Emission Details As" + weEmissionDetails_tCO2e.getText());
//			} else {
//				failed(driver, "Failed To validate tCo2e In Emission Details Expected As " + tCO2e.get(i)
//						+ "But Actual is" + weEmissionDetails_tCO2e.getText());
//			}
//
//			System.out.println(weEmissionDetails_EmissionFactor.getText());
//			if (weEmissionDetails_EmissionFactor.getText().trim().contains(emissionFactor.get(i).trim())) {
//				passed("Successfully Validated EmissionFactor In Emission Details As"
//						+ weEmissionDetails_EmissionFactor.getText());
//			} else {
//				failed(driver, "Failed To validate EmissionFactor In Emission Details Expected As "
//						+ emissionFactor.get(i) + "But Actual is" + weEmissionDetails_EmissionFactor.getText());
//			}
//			
//			if(weEmissionDetails_BiofuelCO2.getText().trim().equals(biofuelCO2.get(i).trim())) {
//				passed("Successfully Validated BiofuelCO2e In Emission Details As"+weEmissionDetails_BiofuelCO2.getText());
//			}else {
//				failed(driver,"Failed To validate BiofuelCO2e In Emission Details Expected As "+biofuelCO2.get(i)+"But Actual is"+weEmissionDetails_BiofuelCO2.getText());
//			}
//
//			if (weSource.getText().trim().equals(source.get(i).trim())) {
//				passed("Successfully Validated Source In Activity Details As" + weSource.getText());
//			} else {
//				failed(driver, "Failed To validate Source In Activity Details Expected As " + source.get(i)
//						+ "But Actual is" + weSource.getText());
//			}
//}

//	public void addActivityDetailsForBusinessTravelSpendBasedCalculatorInActivityDetailsPannel_Multiple(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc",
//					"Facility Name");
//			List<String> inVoiceNo = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc", "Invoice No.");
//			List<String> invoiceDate = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc", "Invoice Date");
//			List<String> startDate = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc", "End Date");
//			List<String> travelDescription = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc",
//					"Travel Description");
//			List<String> modeOfBusinessTravel = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc",
//					"Mode of Business Travel");
//			List<String> travelCategory = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc",
//					"Travel Category");
//			List<String> amountSpent = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc", "Amount Spent");
//			List<String> currencyOrUnit = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc",
//					"Currency / Unit");
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc",
//					"Custom Emission Factor");
//			List<String> tCO2e = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc", "tCO2e");
//			List<String> emissionFactor = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc",
//					"Emission Factor");
//			List<String> source = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc", "Source");
//			waitForElement(drpFacilityName);
//			clickOn(drpFacilityName, "txtFacilityName");
//			// enterText(drpFacilityName, "Facility Name", facilityName.get(i));
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			sleep(2000);
//			clickOn(txtInvoiceNum, " InVoiceNo");
//			enterText(txtInvoiceNum, "InvoiceNo", inVoiceNo.get(i));
//			clickOn(txtInvoiceDate, "Invoice date");
//			enterText(txtInvoiceDate, "Invoice Date", invoiceDate.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			clickOn(txtTravelDescription, " Travel Description");
//			enterText(txtTravelDescription, "Travel Description", travelDescription.get(i));
//			clickOn(drpModeOfBusinessTravel, "Mode Of Business Travel");
//			we = driver.findElement(By.xpath("//li[text()='" + modeOfBusinessTravel.get(i) + "']"));
//			clickOn(we, modeOfBusinessTravel.get(i));
//			sleep(2000);
//			clickOn(drpTravelCategory, "Travel Category");
//			we = driver.findElement(By.xpath("//li[text()='" + travelCategory.get(i) + "']"));
//			clickOn(we, travelCategory.get(i));
//			sleep(2000);
//			clickOn(txtAmountSpent, "Amount Spent");
//			enterText(txtAmountSpent, "AmountSpent", amountSpent.get(i));
//			clickOn(drpCurrency, "Mode Of Business Travel");
//			we = driver.findElement(By.xpath("//li[text()='" + currencyOrUnit.get(i) + "']"));
//			clickOn(we, modeOfBusinessTravel.get(i));
//			sleep(2000);
//			clickOn(btnSave, "Save Button");
//			sleep(5000);
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

//	public void validateActivityDetailsInViewActivityForSpendBasedBusinessTravelCalculator_Multiple(int i) {
//		try {
//			takeScreenshot(driver);
//			String[] activityDetailFieldNames = { "Facility Name", "Invoice No.", "Invoice Date", "Start Date",
//					"End Date", "Travel Description", "Custom Emission Factor", "Mode of Business Travel",
//					"Travel Category", "Amount Spent", "Currency / Unit", "tCO2e", "Emission Factor", "Source" };
//			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath(
//						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//			
//			
//			List<String> facilityName = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc",
//					"Facility Name");
//
//			List<String> inVoiceNo = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc", "InVoiceNo");
//
//			List<String> invoiceDate = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc", "InvoivceDate");
//
//			List<String> startDate = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc", "StartDate");
//
//			List<String> endDate = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc", "EndDate");
//
//			List<String> travelDescription = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc",
//					"TravelDescription");
//
//			List<String> modeOfBusinessTravel = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc",
//					"ModeOfBusinessTravel");
//
//			List<String> travelCategory = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc",
//					"TravelCategory");
//
//			List<String> amountSpent = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc", "AmountSpent");
//
//			List<String> currencyOrUnit = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc",
//					"CurrencyOrUnit");
//
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc",
//					"CustomEmissionFactor");
//
//			List<String> tCO2e = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc", "tCO2e");
//
//			List<String> emissionFactor = MultiDataExcelReader.getTestData("BusinessTravelSpendBasedCalc",
//					"EmissionFactor");
//
//			List<String> source = MultiDataExcelReader.getTestData("BusinessTravelCalculator", "Source");
//
//			waitForElement(weFacilityName);
//
//			if (weFacilityName.getText().trim().equals(facilityName.get(i).trim())) {
//				passed("Successfully Validated FacilityName In Activity Details As" + weFacilityName.getText());
//			} else {
//				failed(driver, "Failed To validate Facility Name In Activity Details Expected As " + facilityName.get(i)
//						+ "But Actual is" + weFacilityName.getText());
//			}
//
//			if (weInVoiceNo.getText().trim().equals(inVoiceNo.get(i).trim())) {
//				passed("Successfully Validated InVoiceNo In Activity Details As" + weInVoiceNo.getText());
//			} else {
//				failed(driver, "Failed To validate InVoiceNo In Activity Details Expected As " + inVoiceNo.get(i)
//						+ "But Actual is" + weInVoiceNo.getText());
//			}
//
//			if (weInVoiceDate.getText().trim().equals(invoiceDate.get(i).trim())) {
//				passed("Successfully Validated InVoiceDate In Activity Details As" + weInVoiceDate.getText());
//			} else {
//				failed(driver, "Failed To validate InVoiceDate In Activity Details Expected As " + invoiceDate.get(i)
//						+ "But Actual is" + weInVoiceDate.getText());
//			}
//
//			if (weStartDate.getText().trim().equals(startDate.get(i).trim())) {
//				passed("Successfully Validated StartDate In Activity Details As" + weStartDate.getText());
//			} else {
//				failed(driver, "Failed To validate StartDate In Activity Details Expected As " + startDate.get(i)
//						+ "But Actual is" + weStartDate.getText());
//			}
//
//			if (weEndDate.getText().trim().equals(endDate.get(i).trim())) {
//				passed("Successfully Validated EndDate In Activity Details As" + weEndDate.getText());
//			} else {
//				failed(driver, "Failed To validate EndDate In Activity Details Expected As " + endDate.get(i)
//						+ "But Actual is" + weEndDate.getText());
//			}
//
//			if (weTravelDescription.getText().trim().equals(travelDescription.get(i).trim())) {
//				passed("Successfully Validated travel Description In Activity Details As"
//						+ weTravelDescription.getText());
//			} else {
//				failed(driver, "Failed To validate travel Description In Activity Details Expected As "
//						+ travelDescription.get(i) + "But Actual is" + weTravelDescription.getText());
//			}
//
//			if (weCustomEmissionFactor.getText().trim().equals(customEmissionFactor.get(i).trim())) {
//				passed("Successfully Validated CustomEmissionFactor In Activity Details As"
//						+ weCustomEmissionFactor.getText());
//			} else {
//				failed(driver, "Failed To validate CustomEmissionFactor In Activity Details Expected As "
//						+ customEmissionFactor.get(i) + "But Actual is" + weCustomEmissionFactor.getText());
//			}
//
//			if (weModeOfBusinessTrvl.getText().trim().equals(modeOfBusinessTravel.get(i).trim())) {
//				passed("Successfully Validated ModeOfBusinessTrvl In Activity Details As"
//						+ weModeOfBusinessTrvl.getText());
//			} else {
//				failed(driver, "Failed To validate ModeOfBusinessTrvl In Activity Details Expected As " + endDate.get(i)
//						+ "But Actual is" + weModeOfBusinessTrvl.getText());
//			}
//
//			if (weTravelCategory.getText().trim().equals(travelCategory.get(i).trim())) {
//				passed("Successfully Validated Travel Category In Activity Details As" + weTravelCategory.getText());
//			} else {
//				failed(driver, "Failed To validate Travel Category In Activity Details Expected As "
//						+ travelCategory.get(i) + "But Actual is" + weTravelCategory.getText());
//			}
//
//			if (weAmountSpent.getText().trim().equals(amountSpent.get(i).trim())) {
//				passed("Successfully Validated Amount Spent In Activity Details As" + weAmountSpent.getText());
//			} else {
//				failed(driver, "Failed To validate Amount Spent In Activity Details Expected As " + amountSpent.get(i)
//						+ "But Actual is" + weAmountSpent.getText());
//			}
//
//			if (weCurrencyOrUnit.getText().trim().equals(currencyOrUnit.get(i).trim())) {
//				passed("Successfully Validated Currency/Units In Activity Details As" + weCurrencyOrUnit.getText());
//			} else {
//				failed(driver, "Failed To validate Currency/Units  In Activity Details Expected As "
//						+ currencyOrUnit.get(i) + "But Actual is" + weCurrencyOrUnit.getText());
//			}
//
//			if (weEmissionDetails_tCO2e.getText().trim().equals(tCO2e.get(i).trim())) {
//				passed("Successfully Validated tCo2e In Emission Details As" + weEmissionDetails_tCO2e.getText());
//			} else {
//				failed(driver, "Failed To validate tCo2e In Emission Details Expected As " + tCO2e.get(i)
//						+ "But Actual is" + weEmissionDetails_tCO2e.getText());
//			}
//
//			System.out.println(weEmissionDetails_EmissionFactor.getText());
//			if (weEmissionDetails_EmissionFactor.getText().trim().contains(emissionFactor.get(i).trim())) {
//				passed("Successfully Validated EmissionFactor In Emission Details As"
//						+ weEmissionDetails_EmissionFactor.getText());
//			} else {
//				failed(driver, "Failed To validate EmissionFactor In Emission Details Expected As "
//						+ emissionFactor.get(i) + "But Actual is" + weEmissionDetails_EmissionFactor.getText());
//			}
//
//			if (weSource.getText().trim().equals(source.get(i).trim())) {
//				passed("Successfully Validated Source In Activity Details As" + weSource.getText());
//			} else {
//				failed(driver, "Failed To validate Source In Activity Details Expected As " + source.get(i)
//						+ "But Actual is" + weSource.getText());
//			}

//	public void addActivityDetailsForEmployeeCommutingCalculatorInActivityDetailsPannel_Multiple(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator",
//					"Facility Name");
//			List<String> commuteDescription = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator",
//					"Commute Description");
//			List<String> startDate = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator", "End Date");
//			List<String> empOrGrpOfEmployees = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator",
//					"Employee / Group of Employees");
//			List<String> numberOfEmpCommuted = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator",
//					"Number of Employees Commuted");
//			List<String> modeOfCommute = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator",
//					"Mode of Commute");
//			List<String> vehicalType = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator", "Vehicle Type");
//			List<String> avgCommuteDays = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator",
//					"Average Commute Days");
//			List<String> averageDailyCommuteDistance = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator",
//					"Average Daily Commute Distance");
//			List<String> units = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator", "Units");
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator",
//					"Custom Emission Factor");
//			List<String> tCO2 = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator", "tCO2");
//			List<String> tCH4 = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator", "tCH4");
//			List<String> tN2O = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator", "tN2O");
//			List<String> biofuelCO2 = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator", "Biofuel CO2");
//			List<String> tCO2e = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator", "tCO2e");
//			List<String> emissionFactor = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator",
//					"Emission Factor");
//			List<String> source = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator", "Source");
//			waitForElement(drpFacilityName);
//			clickOn(drpFacilityName, "txtFacilityName");
//			// enterText(drpFacilityName, "Facility Name", facilityName.get(i));
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			sleep(2000);
//			clickOn(txtCommuteDescription, " Commute Description");
//			enterText(txtCommuteDescription, "Commute Description", commuteDescription.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			clickOn(txtGroupEmployee, " Group of Emp");
//			enterText(txtGroupEmployee, "Group of Emp", empOrGrpOfEmployees.get(i));
//			clickOn(txtNoOfEmpCommuted, " NoOfEmpCommuted");
//			enterText(txtNoOfEmpCommuted, "numberOfEmployeeCommuted", numberOfEmpCommuted.get(i));
//			clickOn(drpModeOfCommute, "Mode Of Commute ");
//			we = driver.findElement(By.xpath("//li[text()='Car']"));
//			clickOn(we, modeOfCommute.get(i));
//			sleep(2000);
//			clickOn(drpModeOfCommute, "Mode Of Commute ");
//			we = driver.findElement(By.xpath("//li[text()='" + modeOfCommute.get(i) + "']"));
//			clickOn(we, modeOfCommute.get(i));
//			sleep(2000);
//			clickOn(drpVehicleType, "VehicalType");
//			we = driver.findElement(By.xpath("//li[text()='" + vehicalType.get(i) + "']"));
//			clickOn(we, vehicalType.get(i));
//			sleep(2000);
//			clickOn(txtAvgCommuteDays, " Average Commute Days");
//			enterText(txtAvgCommuteDays, "Average Commute Days", avgCommuteDays.get(i));
//			clickOn(txtAvgDailyCommuteDistance, " Average Daily Commute Distance ");
//			enterText(txtAvgDailyCommuteDistance, "Average Daily Commute Distance ",
//					averageDailyCommuteDistance.get(i));
//			clickOn(drpBusinessTravlUnits, "WasteDisposalMethod");
//			we = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			clickOn(we, units.get(i));
//			clickOn(btnSave, "Save Button");
//			sleep(10000);
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

	

//	public void validateActivityDetailsInViewActivityForEmployeeCommutingCalculator_Multiple(int i) {
//		try {
//			takeScreenshot(driver);
//			String[] activityDetailFieldNames = { "Facility Name", "Commute Description", "Start Date", "End Date",
//					"Employee / Group of Employees", "Number of Employees Commuted", "Custom Emission Factor",
//					"Activity Amount", "Mode of Commute", "Vehicle Type", "Average Commute Days",
//					"Average Daily Commute Distance", "Units", "tCO2", "tCH4", "tN2O", "Biofuel CO2", "tCO2e",
//					"Emission Factor", "Source" };
//			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
////
////			for (int j = 0; j < activityDetailFieldNames.length; j++) {
////
////				WebElement weActivityField = driver.findElement(By.xpath(
////						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
////				List<String> activityFieldName = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator",
////						activityDetailFieldNames[j]);
////
////				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
////					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
////							+ weActivityField.getText());
////				} else {
////					failed(driver,
////							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
////									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
////				}
////
////			}
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath(
//						"//span[contains(text(),'" + activityDetailFieldNames[j] + "')]//parent::div//parent::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("EmployeeCommutingCalculator",
//						activityDetailFieldNames[j]);
//				String strActivityField = weActivityField.getText().trim();
//				String[] arrActivityField = strActivityField.split("\n");
//				System.out.println(arrActivityField[1]);
//				if (arrActivityField[1].equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ arrActivityField[1]);
//				} else {
//					// failed(driver,"Failed To validate "+activityDetailFieldNames[j]+" In Activity
//					// Details Expected As "+activityFieldName.get(i).trim()+"But Actual
//					// is"+arrActivityField[1]);
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

	

//	@FindBy(xpath = "//input[@id='Facility Name']")
//	public WebElement txtFacilityNameScope3_3;
//	@FindBy(xpath = "//textarea[@id='description']") // demo
//	public WebElement txtDescrScope3_3;
//	@FindBy(xpath = "//input[@id='description']") // Qa
//	public WebElement txtDescrScope3_3Qa;
//	@FindBy(xpath = "(//span[contains(text(),'Energy Type')])[1]//parent::div/parent::div//input//parent::div")
//	public WebElement drpEnerfgyType;
//	@FindBy(xpath = "(//span[contains(text(),'Fuel Type')])[1]//parent::div/parent::div//input//parent::div")
//	public WebElement drpFuelType;
//	@FindBy(xpath = "(//span[contains(text(),'Fuel Name')])[1]//parent::div/parent::div//input//parent::div")
//	public WebElement drpFuelName;
//	@FindBy(xpath = "//input[@id='unit_name']")
//	public WebElement txtScope3_3FuelEnrgyUnit;
//	@FindBy(xpath = "//input[@id='amount']")
//	public WebElement txtQuantityConsumedFuelEngy;
//	@FindBy(xpath = "(//button[text()='Save'])[2]")
//	public WebElement btnSaveScope3_3PurcGood;
//	@FindBy(xpath = "//div[text()='Edit Activity']")
//	public WebElement lblAddActivityFuelEnrgy;
//	Actions mouseActions = new Actions(driver);
	/*
	 * public void EditActivityScope3_3Emissions() { try {
	 * 
	 * // clickOn(btnActivities, "btnActivities");
	 * verifyIfElementPresent(lblAddActivityFuelEnrgy, "lblAddActivityFuelEnrgy",
	 * "lblAddActivityFuelEnrgy"); Thread.sleep(5000);
	 * clickOn(txtFacilityNameScope3_3, "txtFacilityNameScope3_3"); sleep(2000);
	 * WebElement we = driver.findElement(By.xpath("//li[text()='" +
	 * facilityName.get(i) + "']")); clickOn(we, facilityName.get(i)); sleep(2000);
	 * // mouseActions.doubleClick(txtDescrScope3_3).doubleClick().perform();
	 * Actions mouseActions = new Actions(driver);
	 * mouseActions.doubleClick(txtDescrScope3_3Qa).doubleClick().perform(); //
	 * enterText(txtDescrScope3_3, "Description Scope3", description.get(i));
	 * enterText(txtDescrScope3_3Qa, "Description Scope3", description.get(i));
	 * clickOn(txtStartDate, "Start date"); enterText(txtStartDate, "Start Date",
	 * startDate.get(i)); clickOn(txtEndDate, "end date"); enterText(txtEndDate,
	 * "End Date", endDate.get(i)); clickOn(drpEnerfgyType, "Energy type DropDown");
	 * WebElement weEnergyType = driver.findElement(By.xpath("//li[text()='" +
	 * energy_Type.get(i) + "']")); clickOn(weEnergyType, energy_Type.get(i)); if
	 * (energy_Type.get(i).equals("Electricity") ||
	 * energy_Type.get(i).equals("Heat and Steam")) {
	 * clickOn(txtScope3_3FuelEnrgyUnit, "Unit"); weUnit =
	 * driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
	 * clickOn(weUnit, units.get(i)); sleep(2000);
	 * mouseActions.doubleClick(txtQuantityConsumedFuelEngy).perform();
	 * enterText(txtQuantityConsumedFuelEngy, "Quantity of fuel Consumed",
	 * quantity_consumed.get(i)); sleep(5000); clickOn(btnSaveScope3_3PurcGood,
	 * "Save Button"); System.out.println("-------Validate details---------"); }
	 * else { clickOn(drpFuelType, "Fuel Type DropDown"); WebElement weFuelTypeEnter
	 * = driver.findElement(By.xpath("//input[@id='fuel_type_name']"));
	 * enterText(weFuelTypeEnter, "Fuel Type", fuel_Type.get(i)); WebElement
	 * weFuelType = driver.findElement(By.xpath("//li[text()='" + fuel_Type.get(i) +
	 * "']")); clickOn(weFuelType, fuel_Type.get(i)); sleep(2000);
	 * clickOn(drpFuelName, "Fuel Name DropDown"); WebElement weFuelName =
	 * driver.findElement(By.xpath("//li[text()='" + fuel_Name.get(i) + "']")); //
	 * clickOn(weFuelName, data.get("Fuel Name")); jsClick(weFuelName,
	 * fuel_Name.get(i)); System.out.println(fuel_Name.get(i));
	 * clickOn(txtScope3_3FuelEnrgyUnit, "Unit"); weUnit =
	 * driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
	 * clickOn(weUnit, units.get(i)); sleep(2000);
	 * mouseActions.doubleClick(txtQuantityConsumedFuelEngy).perform();
	 * enterText(txtQuantityConsumedFuelEngy, "Quantity of fuel Consumed",
	 * quantity_consumed.get(i)); sleep(5000); clickOn(btnSaveScope3_3PurcGood,
	 * "Save Button"); System.out.println("-------Validate details---------"); } }
	 * catch (Exception e) { failed(driver, "Exception caught" + e.getMessage()); }
	 * }
	 */
//	@FindBy(xpath = "//span[text()='Description']//parent::div//following-sibling::div")
//	public WebElement weDescrptionScope3;
//	@FindBy(xpath = "//span[text()='Energy Type']//parent::div//following-sibling::div")
//	public WebElement weEnergyTypeverify;
//	@FindBy(xpath = "//span[text()='Fuel Type']//parent::div//following-sibling::div")
//	public WebElement weFuelTypeverify;
//	@FindBy(xpath = "//span[text()='Fuel Name']//parent::div//following-sibling::div")
//	public WebElement weFuelNameverify;
//	@FindBy(xpath = "//span[text()='Quantity Consumed']//parent::div//following-sibling::div")
//	public WebElement weQuantityConsumedverify;
//	@FindBy(xpath = "//div[text()='Activity Details']")
//	public WebElement lblViewActivityFulEngy;
//
//	public void ValidateActivityDetailsInViewActivityScope3_3(int i) {
//		try {
//			String[] activityDetailFieldNames = { "Facility Name", "Description", "Start Date", "End Date",
//					"Custom Emission", "Energy Type", "Fuel Type", "Fuel Name", "Quantity Consumed", "Units",
//					"Custom Emission", "tCO2e", "Emission Factor", "Source" };
//			Thread.sleep(5000);
//			verifyIfElementPresent(lblViewActivityFulEngy, "lblViewActivityFulEngy", "lblViewActivityFulEngy");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath(
//						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("FuelandEnergyRelatedActivit",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	@FindBy(xpath = "//input[@id='FacilityName']") // demo
//	public WebElement txtFacilityNameScope3_4;
//	@FindBy(xpath = "//input[@id='Facility Name']") // Qa
//	public WebElement txtFacilityNameScope3_4Qa;
//	@FindBy(xpath = "//input[@id='description']")
//	public WebElement txtDescrScope3_4;
//	@FindBy(xpath = "//span[text()='Invoice No.']//parent::div//following-sibling::div//input")
//	public WebElement invoceNoupSt;
//	@FindBy(xpath = "//input[@id='service_provider']")
//	public WebElement txtServicePrvodr;
//	@FindBy(xpath = "//input[@id='service_type']")
//	public WebElement txtServiceType;
//	@FindBy(xpath = "//input[@id='mode_of_transport']")
//	public WebElement drpModeOfFrght;
//	@FindBy(xpath = "//input[@id='vehicle_name']")
//	public WebElement drpVehcle;
//	@FindBy(xpath = "(//span[contains(text(),'Type')])[2]//parent::div/parent::div/parent::div/div[2]//input")
//	public WebElement drpType;
//	@FindBy(xpath = "//span[contains(text(),'Fuel / Size')]//parent::div/parent::div/parent::div/div[2]//input")
//	public WebElement drpFuel_Size;
//	@FindBy(xpath = "//span[contains(text(),'Fuel / Size')]//parent::div/parent::div/parent::div/div[3]//input")
//	public WebElement drpFuel_Size3_9;
//	@FindBy(xpath = "//input[@id='weight']")
//	public WebElement txtWeight;
//	@FindBy(xpath = "//input[@id='distance']")
//	public WebElement txtDistance;
//	@FindBy(xpath = "//span[contains(text(),'Weight Unit')]//parent::div/parent::div/parent::div/div[2]//input")
//	public WebElement drpWeightUnit;
//	@FindBy(xpath = "//span[contains(text(),'Weight Unit')]//parent::div/parent::div/parent::div/div[3]//input")
//	public WebElement drpWeightUnit3_9;
//	@FindBy(xpath = "//span[contains(text(),'Distance Unit')]//parent::div/parent::div/parent::div/div[2]//input")
//	public WebElement drpDisatnceUnit;

//	public void EditActivityScope3_4Emissions(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod",
//					"Facility Name");
//			List<String> description = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Description");
//			List<String> InvoiceNumn = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Invoice No.");
//			List<String> Invoicedate = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod",
//					"Invoice Date");
//			List<String> startDate = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "End Date");
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod",
//					"Custom Emission");
//			List<String> Service_Provide = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod",
//					"Service Provider");
//			List<String> Service_Type = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod",
//					"Service Type");
//			List<String> Mode_of_Freight = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod",
//					"Mode of Freight");
//			List<String> Vehicle = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Vehicle");
//			List<String> Type = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Type");
//			List<String> Fuel_Size = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Fuel / Size");
//			List<String> Activity_Amount = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod",
//					"Activity Amount");
//			List<String> Weight = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Weight");
//			List<String> Weight_Unit = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Weight Unit");
//			List<String> Distance = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Distance");
//			List<String> Distance_Unit = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod",
//					"Distance Unit");
//			List<String> tCO2e = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "tCO2e");
//			List<String> emissionFactor = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod",
//					"Emission Factor");
//			List<String> source = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Source");
//			// clickOn(btnActivities, "btnActivities");
//			verifyIfElementPresent(lblAddActivityFuelEnrgy, "lblAddActivityFuelEnrgy", "lblAddActivityFuelEnrgy");
//			Thread.sleep(5000);
//			// clickOn(txtFacilityNameScope3_4, "txtFacilityNameScope3_4");
//			clickOn(txtFacilityNameScope3_4Qa, "txtFacilityNameScope3_4");
//			sleep(2000);
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			sleep(2000);
//			mouseActions.doubleClick(txtDescrScope3_4).perform();
//			enterText(txtDescrScope3_4, "Description Scope3.4", description.get(i));
//			clickOn(invoceNoupSt, "Invoice No.");
//			mouseActions.doubleClick(invoceNoupSt).perform();
//			enterText(invoceNoupSt, "Invoice No.", InvoiceNumn.get(i));
//			clickOn(txtInvoiceDate, "Invoice Date");
//			enterText(txtInvoiceDate, "Invoice Date", Invoicedate.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			// ----
//			mouseActions.doubleClick(txtServicePrvodr).perform();
//			enterText(txtServicePrvodr, "Service Provider", Service_Provide.get(i));
//			mouseActions.doubleClick(txtServiceType).perform();
//			enterText(txtServiceType, "Service Type", Service_Type.get(i));
//			clickOn(drpModeOfFrght, "Mode of freight DropDown");
//			WebElement weModeType = driver.findElement(By.xpath("//li[text()='" + Mode_of_Freight.get(i) + "']"));
//			clickOn(weModeType, Mode_of_Freight.get(i));
//			clickOn(drpVehcle, "Vehicle Type DropDown");
//			WebElement weVehicleTypeEnter = driver.findElement(By.xpath("//li[text()='" + Vehicle.get(i) + "']"));
//			// enterText(weVehicleTypeEnter, "Vehicle Type", Vehicle.get(i));
//			clickOn(weVehicleTypeEnter, Vehicle.get(i));
//			clickOn(drpType, "Type DropDown");
//			WebElement weType = driver.findElement(By.xpath("//li[text()='" + Type.get(i) + "']"));
//			// clickOn(weFuelName, data.get("Fuel Name"));
//			jsClick(weType, Type.get(i));
//			sleep(5000);
//			clickOn(drpFuel_Size, "Fuel/Size Drop Down");
//			WebElement weFuel_Size = driver.findElement(By.xpath("//li[text()='" + Fuel_Size.get(i) + "']"));
//			clickOn(weFuel_Size, Fuel_Size.get(i));
//			mouseActions.doubleClick(txtWeight).perform();
//			enterText(txtWeight, "Weight", Weight.get(i));
//			clickOn(drpWeightUnit, "Fuel/Size Drop Down");
//			WebElement weWeigthUnit = driver.findElement(By.xpath("//li[text()='" + Weight_Unit.get(i) + "']"));
//			clickOn(weWeigthUnit, Weight_Unit.get(i));
//			mouseActions.doubleClick(txtDistance).perform();
//			enterText(txtDistance, "Distance", Distance.get(i));
//			clickOn(drpDisatnceUnit, "Distance Drop Down");
//			WebElement weDistanceUnit = driver.findElement(By.xpath("//li[text()='" + Distance_Unit.get(i) + "']"));
//			clickOn(weDistanceUnit, Distance_Unit.get(i));
//			sleep(5000);
//			clickOn(btnSaveScope3_3PurcGood, "Save Button");
//			By toast = By.xpath("//div[text()='Activity updated successfully']");
//			if (isElementPresent(toast)) {
//				passed("Successfully displayed toast message - Activity updated successfully");
//			} else {
//				failed(driver, "Failed to display toast message");
//			}
//			System.out.println("-------Validate details---------");
//		} catch (Exception e) {
//			failed(driver, "Exception caught" + e.getMessage());
//		}
//	}
//-----------validation 3.4---------------//

//	@FindBy(xpath = "//span[text()='Service Provider']//parent::div//following-sibling::div")
//	public WebElement weServiceProvider;
//	@FindBy(xpath = "//span[text()='Service Type']//parent::div//following-sibling::div")
//	public WebElement weServiceType;
//	@FindBy(xpath = "//span[text()='Mode of Freight']//parent::div//following-sibling::div")
//	public WebElement weModeOfFreight;
//	@FindBy(xpath = "//span[text()='Vehicle']//parent::div//following-sibling::div")
//	public WebElement weVehicle;
//	@FindBy(xpath = "//span[text()='Type']//parent::div//following-sibling::div")
//	public WebElement weType;
//	@FindBy(xpath = "//span[text()='Fuel / Size']//parent::div//following-sibling::div")
//	public WebElement weFuel_Size;
//	@FindBy(xpath = "//span[text()='Weight']//parent::div//following-sibling::div")
//	public WebElement weWeight;
//	@FindBy(xpath = "//span[text()='Weight Unit']//parent::div//following-sibling::div")
//	public WebElement weWeight_Unit;
//	@FindBy(xpath = "//span[text()='Distance']//parent::div//following-sibling::div")
//	public WebElement weDistance;
//	@FindBy(xpath = "//span[text()='Distance Unit']//parent::div//following-sibling::div")
//	public WebElement weDistance_Unit;
//	@FindBy(xpath = "//span[text()='Activity Amount']//parent::div//following-sibling::div")
//	public WebElement weActivity_Amount;
//
//	public void ValidateActivityDetailsInViewActivityScope3_4(int i) {
//		try {
//			String[] activityDetailFieldNames = { "Facility Name", "Description", "Invoice No.", "Invoice Date",
//					"Start Date", "End Date", "Custom Emission", "Service Provider", "Service Type", "Mode of Freight",
//					"Vehicle", "Type", "Fuel / Size", "Activity Amount", "Weight", "Weight Unit", "Distance",
//					"Distance Unit", "tCO2e", "Emission Factor", "Source" };
//			Thread.sleep(5000);
//			verifyIfElementPresent(lblViewActivityFulEngy, "lblViewActivityFulEngy", "lblViewActivityFulEngy");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath(
//						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught" + e.getMessage());
//		}
//	}
//
//	public void ValidateActivityDetailsInViewActivityScope3_7(int i) {
//		try {
//			String[] activityDetailFieldNames = { "Facility / Location", "Description", "Start Date", "End Date",
//					"Custom Emission Factor", "Country", "Type Of Home Office", "Number Of Working Days",
//					"Number Of Working Hours Per Day", "Number Of Employee", "Working Regime %", "% Working From Home",
//					"Energy Consumed (kWh)", "tCO2e", "Emission Factor", "Source" };
//			Thread.sleep(5000);
//			verifyIfElementPresent(lblViewActivityFulEngy, "lblViewActivityFulEngy", "lblViewActivityFulEngy");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath(
//						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught" + e.getMessage());
//		}
//	}
//
//	@FindBy(xpath = "//div[text()='Add Activity']")
//	public WebElement lblAddActvtyUpstrem;

//	public void AddActivityOnlyScope3_4Emissions(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod",
//					"Facility Name");
//			List<String> description = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Description");
//			List<String> InvoiceNumn = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Invoice No.");
//			List<String> Invoicedate = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod",
//					"Invoice Date");
//			List<String> startDate = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "End Date");
//			// List<String> numOfDays =
//			// MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Total No.
//			// Of Days");
//			// List<String> overlappingDays =
//			// MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Overlap
//			// Days");
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod",
//					"Custom Emission");
//			List<String> Service_Provide = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod",
//					"Service Provider");
//			List<String> Service_Type = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod",
//					"Service Type");
//			List<String> Mode_of_Freight = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod",
//					"Mode of Freight");
//			List<String> Vehicle = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Vehicle");
//			List<String> Type = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Type");
//			List<String> Fuel_Size = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Fuel / Size");
//			List<String> Activity_Amount = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod",
//					"Activity Amount");
//			List<String> Weight = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Weight");
//			List<String> Weight_Unit = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Weight Unit");
//			List<String> Distance = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Distance");
//			List<String> Distance_Unit = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod",
//					"Distance Unit");
//			List<String> tCO2e = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "tCO2e");
//			List<String> emissionFactor = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod",
//					"Emission Factor");
//			List<String> source = MultiDataExcelReader.getTestData("UpstreamWeight-DistanceMethod", "Source");
//			clickOn(btnbtnActivities, "add activity for upstream transportation");
//			verifyIfElementPresent(lblAddActvtyUpstrem, "lblAddActvtyUpstrem", "lblAddActvtyUpstrem");
//			Thread.sleep(5000);
//			// clickOn(txtFacilityNameScope3_4, "txtFacilityNameScope3_4");
//			clickOn(txtFacilityNameScope3_4Qa, "txtFacilityNameScope3_4");
//			sleep(2000);
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			sleep(2000);
//			mouseActions.doubleClick(txtDescrScope3_4).perform();
//			// ------Description
//			String randomNumber = date.toString().replaceAll(" ", "").replaceAll("[a-z]", "");
//			String somValue = randomNumber.replaceAll("[A-Z]", "");
//			String[] spliting = somValue.split(":");
//			String req = "AutTestDesc";
//			String randomString = req.concat(spliting[2]);
//			// ---------
//			enterText(txtDescrScope3_4, "Description Scope3.4", randomString);
//			clickOn(invoceNoupSt, "Invoice No.");
//			mouseActions.doubleClick(invoceNoupSt).perform();
//			enterText(invoceNoupSt, "Invoice No.", InvoiceNumn.get(i));
//			clickOn(txtInvoiceDate, "Invoice Date");
//			enterText(txtInvoiceDate, "Invoice Date", Invoicedate.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			// ----
//			mouseActions.doubleClick(txtServicePrvodr).perform();
//			enterText(txtServicePrvodr, "Service Provider", Service_Provide.get(i));
//			mouseActions.doubleClick(txtServiceType).perform();
//			enterText(txtServiceType, "Service Type", Service_Type.get(i));
//			clickOn(drpModeOfFrght, "Mode of freight DropDown");
//			WebElement weModeType = driver.findElement(By.xpath("//li[text()='" + Mode_of_Freight.get(i) + "']"));
//			clickOn(weModeType, Mode_of_Freight.get(i));
//			clickOn(drpVehcle, "Vehicle Type DropDown");
//			WebElement weVehicleTypeEnter = driver.findElement(By.xpath("//li[text()='" + Vehicle.get(i) + "']"));
//			// enterText(weVehicleTypeEnter, "Vehicle Type", Vehicle.get(i));
//			clickOn(weVehicleTypeEnter, Vehicle.get(i));
//			clickOn(drpType, "Type DropDown");
//			WebElement weType = driver.findElement(By.xpath("//li[text()='" + Type.get(i) + "']"));
//			// clickOn(weFuelName, data.get("Fuel Name"));
//			jsClick(weType, Type.get(i));
//			sleep(5000);
//			clickOn(drpFuel_Size, "Fuel/Size Drop Down");
//			WebElement weFuel_Size = driver.findElement(By.xpath("//li[text()='" + Fuel_Size.get(i) + "']"));
//			clickOn(weFuel_Size, Fuel_Size.get(i));
//			mouseActions.doubleClick(txtWeight).perform();
//			enterText(txtWeight, "Weight", Weight.get(i));
//			clickOn(drpWeightUnit, "Fuel/Size Drop Down");
//			WebElement weWeigthUnit = driver.findElement(By.xpath("//li[text()='" + Weight_Unit.get(i) + "']"));
//			clickOn(weWeigthUnit, Weight_Unit.get(i));
//			mouseActions.doubleClick(txtDistance).perform();
//			enterText(txtDistance, "Distance", Distance.get(i));
//			clickOn(drpDisatnceUnit, "Distance Drop Down");
//			WebElement weDistanceUnit = driver.findElement(By.xpath("//li[text()='" + Distance_Unit.get(i) + "']"));
//			clickOn(weDistanceUnit, Distance_Unit.get(i));
//			sleep(5000);
//			WebElement cancelButton = driver.findElement(By.xpath("(//button[contains(text(),'Cancel')])[2]"));
//			// clickOn(cancelButton, "cancel saving activity");
//			clickOn(btnSaveScope3_3PurcGood, "Save Button");
////	    
////	    By toast =By.xpath("//div[text()='Activity updated successfully']");
////
////		if (isElementPresent(toast)) {
////			
////			passed("Successfully displayed toast message - Activity updated successfully");
////			
////		} else {
////			
////			failed(driver, "Failed to display toast message");
////			
////		}
//		} catch (Exception e) {
//			failed(driver, "Exception caught" + e.getMessage());
//		}
//	}

//	@FindBy(xpath = "//input[@id='Description']")
//	public WebElement txtDscrptnHmeOffce;
//	@FindBy(xpath = "//input[@id='Country']")
//	public WebElement txtContryHmeOffce;
//	@FindBy(xpath = "//input[@id='TypeOfHomeOffice']")
//	public WebElement drptypeHmeOffce;
//	@FindBy(xpath = "//input[@id='NumberOfWorkingDays']")
//	public WebElement txtNoOfWrknDaysypeHmeOffce;
//	@FindBy(xpath = "//input[@id='NumberOfWorkingHoursPerDay']")
//	public WebElement txtNoOfWrknhHorsHmeOffce;
//	@FindBy(xpath = "//input[@id='NumberOfEmployees']")
//	public WebElement txtNoOfEmplysHmeOffce;
//	@FindBy(xpath = "//input[@id='WorkingRegime']")
//	public WebElement txtWrkngRegimeHmeOffce;
//	@FindBy(xpath = "//input[@id='WorkingFromHome']")
//	public WebElement txtWFHHmeOffce;
//	@FindBy(xpath = "//input[@id='source_data_unit_name']")
//	public WebElement drpUnitsHmeOffce;
//
//	public void EditActivityScope3_7Emissions(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting",
//					"Facility / Location");
//			// List<String> country =
//			// MultiDataExcelReader.getTestData("HomeOfficeTelecommuting", "Country");
//			List<String> description = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting", "Description");
//			List<String> type_Of_Home_Office = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting",
//					"Type Of Home Office");
//			List<String> startDate = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting", "End Date");
//			List<String> number_of_Working_Days = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting",
//					"Number Of Working Days");
//			List<String> number_of_Working_Hours_Per_Day = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting",
//					"Number Of Working Hours Per Day");
//			List<String> number_of_Employees = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting",
//					"Number Of Employee");
//			List<String> working_Regime_of_Employees_in = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting",
//					"Working Regime %");
//			List<String> employees_Working_From_Home = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting",
//					"% Working From Home");
//			List<String> units = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting", "Units");
//			List<String> Energy_units = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting",
//					"Energy Consumed (kWh)");
//			List<String> tCO2e = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting", "tCO2e");
//			List<String> emission_Factor = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting",
//					"Emission Factor");
//			List<String> source = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting", "Source");
//			verifyIfElementPresent(lblAddActivityFuelEnrgy, "Home Office/Telecommuting", "Home Office/Telecommuting");
//			Thread.sleep(5000);
//			clickOn(txtFacilityNameScope3_4, "txtFacilityNameScope3_4");
//			sleep(2000);
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			sleep(2000);
////		mouseActions.doubleClick(txtContryHmeOffce).perform();
////		
////		enterText(txtContryHmeOffce, "Country", country.get(i));
//			mouseActions.doubleClick(txtDscrptnHmeOffce).perform();
//			enterText(txtDscrptnHmeOffce, "Description Scope3.4", description.get(i));
//			clickOn(drptypeHmeOffce, "type of home office");
//			sleep(1000);
//			WebElement web = driver.findElement(By.xpath("//li[text()='" + type_Of_Home_Office.get(i) + "']"));
//			clickOn(web, type_Of_Home_Office.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			mouseActions.doubleClick(txtNoOfWrknDaysypeHmeOffce).perform();
//			enterText(txtNoOfWrknDaysypeHmeOffce, "No. of Working days", number_of_Working_Days.get(i));
//			mouseActions.doubleClick(txtNoOfWrknhHorsHmeOffce).perform();
//			enterText(txtNoOfWrknhHorsHmeOffce, "No. of Working Hours per day", number_of_Working_Hours_Per_Day.get(i));
//			mouseActions.doubleClick(txtNoOfEmplysHmeOffce).perform();
//			enterText(txtNoOfEmplysHmeOffce, "No. of Working Hours per day", number_of_Employees.get(i));
//			mouseActions.doubleClick(txtWrkngRegimeHmeOffce).perform();
//			enterText(txtWrkngRegimeHmeOffce, "No. of Working Hours per day", working_Regime_of_Employees_in.get(i));
//			mouseActions.doubleClick(txtWFHHmeOffce).perform();
//			enterText(txtWFHHmeOffce, "No. of Working Hours per day", employees_Working_From_Home.get(i));
//			clickOn(drpUnitsHmeOffce, "drp units");
//			WebElement webUnits = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			clickOn(webUnits, units.get(i));
//			clickOn(btnSaveTranscation, "Saved");
//			sleep(2000);
//			By toast = By.xpath("//div[text()='Activity updated successfully']");
//			if (isElementPresent(toast)) {
//				passed("Successfully displayed toast message - Activity updated successfully");
//			} else {
//				failed(driver, "Failed to display toast message");
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception csaught," + e.getMessage());
//		}
//	}
//
//	public void EditActivityScope3_9Emissions(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Facility Name");
//			List<String> description = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Description");
//			List<String> InvoiceNumn = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Invoice No.");
//			List<String> Invoicedate = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Invoice Date");
//			List<String> startDate = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod", "End Date");
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Custom Emission");
//			List<String> Service_Provide = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Service Provider");
//			List<String> Service_Type = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Service Type");
//			List<String> Mode_of_Freight = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Mode of Freight");
//			List<String> Vehicle = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod", "Vehicle");
//			List<String> Type = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod", "Type");
//			List<String> Fuel_Size = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod", "Fuel / Size");
//			List<String> Activity_Amount = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Activity Amount");
//			List<String> Weight = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod", "Weight");
//			List<String> Weight_Unit = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Weight Unit");
//			List<String> Distance = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod", "Distance");
//			List<String> Distance_Unit = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Distance Unit");
//			List<String> tCO2e = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod", "tCO2e");
//			List<String> emissionFactor = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Emission Factor");
//			List<String> source = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod", "Source");
//			// clickOn(btnActivities, "btnActivities");
//			verifyIfElementPresent(lblAddActivityFuelEnrgy, "lblAddActivityFuelEnrgy", "lblAddActivityFuelEnrgy");
//			Thread.sleep(5000);
//			// clickOn(txtFacilityNameScope3_4, "txtFacilityNameScope3_4");
//			clickOn(txtFacilityNameScope3_4Qa, "txtFacilityNameScope3_4");
//			sleep(2000);
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			sleep(2000);
//			mouseActions.doubleClick(txtDescrScope3_4).perform();
//			enterText(txtDescrScope3_4, "Description Scope3.4", description.get(i));
//			clickOn(invoceNoupSt, "Invoice No.");
//			mouseActions.doubleClick(invoceNoupSt).perform();
//			enterText(invoceNoupSt, "Invoice No.", InvoiceNumn.get(i));
//			clickOn(txtInvoiceDate, "Invoice Date");
//			enterText(txtInvoiceDate, "Invoice Date", Invoicedate.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			// ----
//			mouseActions.doubleClick(txtServicePrvodr).perform();
//			enterText(txtServicePrvodr, "Service Provider", Service_Provide.get(i));
//			mouseActions.doubleClick(txtServiceType).perform();
//			enterText(txtServiceType, "Service Type", Service_Type.get(i));
//			clickOn(drpModeOfFrght, "Mode of freight DropDown");
//			WebElement weModeType = driver.findElement(By.xpath("//li[text()='" + Mode_of_Freight.get(i) + "']"));
//			clickOn(weModeType, Mode_of_Freight.get(i));
//			clickOn(drpVehcle, "Vehicle Type DropDown");
//			WebElement weVehicleTypeEnter = driver.findElement(By.xpath("//li[text()='" + Vehicle.get(i) + "']"));
//			// enterText(weVehicleTypeEnter, "Vehicle Type", Vehicle.get(i));
//			clickOn(weVehicleTypeEnter, Vehicle.get(i));
//			clickOn(drpType, "Type DropDown");
//			WebElement weType = driver.findElement(By.xpath("//li[text()='" + Type.get(i) + "']"));
//			// clickOn(weFuelName, data.get("Fuel Name"));
//			jsClick(weType, Type.get(i));
//			sleep(5000);
//			clickOn(drpFuel_Size3_9, "Fuel/Size Drop Down");
//			WebElement weFuel_Size = driver.findElement(By.xpath("//li[text()='" + Fuel_Size.get(i) + "']"));
//			clickOn(weFuel_Size, Fuel_Size.get(i));
//			mouseActions.doubleClick(txtWeight).perform();
//			enterText(txtWeight, "Weight", Weight.get(i));
//			clickOn(drpWeightUnit3_9, "Weight unit Drop Down");
//			WebElement weWeigthUnit = driver.findElement(By.xpath("//li[text()='" + Weight_Unit.get(i) + "']"));
//			clickOn(weWeigthUnit, Weight_Unit.get(i));
//			mouseActions.doubleClick(txtDistance).perform();
//			enterText(txtDistance, "Distance", Distance.get(i));
//			clickOn(drpDisatnceUnit, "Distance Drop Down");
//			WebElement weDistanceUnit = driver.findElement(By.xpath("//li[text()='" + Distance_Unit.get(i) + "']"));
//			clickOn(weDistanceUnit, Distance_Unit.get(i));
//			sleep(5000);
//			clickOn(btnSaveScope3_3PurcGood, "Save Button");
//			By toast = By.xpath("//div[text()='Activity updated successfully']");
//			if (isElementPresent(toast)) {
//				passed("Successfully displayed toast message - Activity updated successfully");
//			} else {
//				failed(driver, "Failed to display toast message");
//			}
//			System.out.println("-------Validate details---------");
//		} catch (Exception e) {
//			failed(driver, "Exception caught" + e.getMessage());
//		}
//	}
//
//	public void AddActivityScope3_9Emissions(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Facility Name");
//			List<String> description = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Description");
//			List<String> InvoiceNumn = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Invoice No.");
//			List<String> Invoicedate = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Invoice Date");
//			List<String> startDate = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod", "End Date");
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Custom Emission");
//			List<String> Service_Provide = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Service Provider");
//			List<String> Service_Type = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Service Type");
//			List<String> Mode_of_Freight = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Mode of Freight");
//			List<String> Vehicle = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod", "Vehicle");
//			List<String> Type = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod", "Type");
//			List<String> Fuel_Size = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod", "Fuel / Size");
//			List<String> Activity_Amount = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Activity Amount");
//			List<String> Weight = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod", "Weight");
//			List<String> Weight_Unit = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Weight Unit");
//			List<String> Distance = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod", "Distance");
//			List<String> Distance_Unit = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Distance Unit");
//			List<String> tCO2e = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod", "tCO2e");
//			List<String> emissionFactor = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//					"Emission Factor");
//			List<String> source = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod", "Source");
//			clickOn(btnActivities, "btnActivities");
//			verifyIfElementPresent(lblAddActvtyUpstrem, "lblAddActvtyUpstrem", "lblAddActvtyUpstrem");
//			Thread.sleep(5000);
//			// clickOn(txtFacilityNameScope3_4, "txtFacilityNameScope3_4");
//			clickOn(txtFacilityNameScope3_4Qa, "txtFacilityNameScope3_4");
//			sleep(2000);
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			sleep(2000);
//			mouseActions.doubleClick(txtDescrScope3_4).perform();
//			enterText(txtDescrScope3_4, "Description Scope3.4", description.get(i));
//			clickOn(invoceNoupSt, "Invoice No.");
//			mouseActions.doubleClick(invoceNoupSt).perform();
//			enterText(invoceNoupSt, "Invoice No.", InvoiceNumn.get(i));
//			clickOn(txtInvoiceDate, "Invoice Date");
//			enterText(txtInvoiceDate, "Invoice Date", Invoicedate.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			// ----
//			mouseActions.doubleClick(txtServicePrvodr).perform();
//			enterText(txtServicePrvodr, "Service Provider", Service_Provide.get(i));
//			mouseActions.doubleClick(txtServiceType).perform();
//			enterText(txtServiceType, "Service Type", Service_Type.get(i));
//			clickOn(drpModeOfFrght, "Mode of freight DropDown");
//			WebElement weModeType = driver.findElement(By.xpath("//li[text()='" + Mode_of_Freight.get(i) + "']"));
//			clickOn(weModeType, Mode_of_Freight.get(i));
//			clickOn(drpVehcle, "Vehicle Type DropDown");
//			WebElement weVehicleTypeEnter = driver.findElement(By.xpath("//li[text()='" + Vehicle.get(i) + "']"));
//			// enterText(weVehicleTypeEnter, "Vehicle Type", Vehicle.get(i));
//			clickOn(weVehicleTypeEnter, Vehicle.get(i));
//			clickOn(drpType, "Type DropDown");
//			WebElement weType = driver.findElement(By.xpath("//li[text()='" + Type.get(i) + "']"));
//			// clickOn(weFuelName, data.get("Fuel Name"));
//			jsClick(weType, Type.get(i));
//			sleep(5000);
//			clickOn(drpFuel_Size3_9, "Fuel/Size Drop Down");
//			WebElement weFuel_Size = driver.findElement(By.xpath("//li[text()='" + Fuel_Size.get(i) + "']"));
//			clickOn(weFuel_Size, Fuel_Size.get(i));
//			mouseActions.doubleClick(txtWeight).perform();
//			enterText(txtWeight, "Weight", Weight.get(i));
//			clickOn(drpWeightUnit3_9, "Weight unit Drop Down");
//			WebElement weWeigthUnit = driver.findElement(By.xpath("//li[text()='" + Weight_Unit.get(i) + "']"));
//			clickOn(weWeigthUnit, Weight_Unit.get(i));
//			mouseActions.doubleClick(txtDistance).perform();
//			enterText(txtDistance, "Distance", Distance.get(i));
//			clickOn(drpDisatnceUnit, "Distance Drop Down");
//			WebElement weDistanceUnit = driver.findElement(By.xpath("//li[text()='" + Distance_Unit.get(i) + "']"));
//			clickOn(weDistanceUnit, Distance_Unit.get(i));
//			sleep(5000);
//			clickOn(btnSaveScope3_3PurcGood, "Save Button");
//			By toast = By.xpath("//div[text()='Activity added successfully']");
//			if (isElementPresent(toast)) {
//				passed("Successfully displayed toast message - Activity updated successfully");
//			} else {
//				failed(driver, "Failed to display toast message");
//			}
//			System.out.println("-------Validate details---------");
//		} catch (Exception e) {
//			failed(driver, "Exception caught" + e.getMessage());
//		}
//	}
//
////--	
//	public void ValidateActivityDetailsInViewActivityScope3_9(int i) {
//		try {
//			String[] activityDetailFieldNames = { "Facility Name", "Description", "Invoice No.", "Invoice Date",
//					"Start Date", "End Date", "Custom Emission", "Service Provider", "Service Type", "Mode of Freight",
//					"Vehicle", "Type", "Fuel / Size", "Activity Amount", "Weight", "Weight Unit", "Distance",
//					"Distance Unit", "tCO2e", "Emission Factor", "Source" };
//			Thread.sleep(5000);
//			verifyIfElementPresent(lblViewActivityFulEngy, "lblViewActivityFulEngy", "lblViewActivityFulEngy");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath(
//						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("DonwstreamWeight-DistanceMethod",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught" + e.getMessage());
//		}
//	}
//
////--	
//	@FindBy(xpath = "//input[@id='facility_name']")
//	public WebElement txtFacilityFrenchiseDmo;
//	@FindBy(xpath = "//input[@id='franchisee_name']")
//	public WebElement txtFrenchiseNameFrenchiseDmo;
//	@FindBy(xpath = "//input[@id='leased_asset']")
//	public WebElement txtLeasedAssetFrenchiseDmo;
//	@FindBy(xpath = "//input[@id='energy_category_name']")
//	public WebElement drpEnergyCatgryFrenchiseDmo;
//	@FindBy(xpath = "//input[@id='type_name']")
//	public WebElement drpTypeFrenchiseDmo;
//	@FindBy(xpath = "//input[@id='activity_amount']")
//	public WebElement txtActcivityAmntFrenchiseDmo;
//
//	public void EditActivityScope3_14Emissions(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("Franchises", "Facility Name");
//			List<String> description = MultiDataExcelReader.getTestData("Franchises", "Description");
//			List<String> startDate = MultiDataExcelReader.getTestData("Franchises", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("Franchises", "End Date");
//			// List<String> customEmissionFactor =
//			// MultiDataExcelReader.getTestData("Franchises", "Custom Emission");
//			List<String> Frenchise_Type = MultiDataExcelReader.getTestData("Franchises", "Type");
//			List<String> Franchisee_Name = MultiDataExcelReader.getTestData("Franchises", "Franchisee Name");
//			List<String> Activity_Amount_Frenchise = MultiDataExcelReader.getTestData("Franchises", "Activity Amount");
//			List<String> Leased_Asset = MultiDataExcelReader.getTestData("Franchises", "Leased Asset");
//			List<String> unit_Frenchisee = MultiDataExcelReader.getTestData("Franchises", "Units");
//			List<String> Energy_Category = MultiDataExcelReader.getTestData("Franchises", "Energy Category");
////		List<String> tCO2e = MultiDataExcelReader.getTestData("Franchises", "tCO2e");
////
////		List<String> emissionFactor = MultiDataExcelReader.getTestData("Franchises", "Emission Factor");
////		
////		List<String> source = MultiDataExcelReader.getTestData("Franchises", "Source");
////		
//			// clickOn(btnActivities, "btnActivities");
//			verifyIfElementPresent(lblAddActivityFuelEnrgy, "lblAddActivityFuelEnrgy", "lblAddActivityFuelEnrgy");
//			Thread.sleep(5000);
//			// clickOn(txtFacilityNameScope3_4, "txtFacilityNameScope3_4");
//			clickOn(txtFacilityFrenchiseDmo, "txtFacilityNameScope3_14");
//			sleep(2000);
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			sleep(2000);
//			clickOn(txtFrenchiseNameFrenchiseDmo, "frenhcise drop down");
//			WebElement wefrenchiseName = driver.findElement(By.xpath("//li[text()='" + Franchisee_Name.get(i) + "']"));
//			clickOn(wefrenchiseName, Franchisee_Name.get(i));
//			mouseActions.doubleClick(txtDescrScope3_3).perform();
//			enterText(txtDescrScope3_3, "frenchisee Description Scope3.4", description.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			// ----
//			mouseActions.doubleClick(txtLeasedAssetFrenchiseDmo).perform();
//			enterText(txtLeasedAssetFrenchiseDmo, "frenchisee Leased_Asset Scope3.14", Leased_Asset.get(i));
//			clickOn(drpEnergyCatgryFrenchiseDmo, "frenchisee Energy category drop down");
//			WebElement weEnrgyCat = driver.findElement(By.xpath("//li[text()='" + Energy_Category.get(i) + "']"));
//			clickOn(weEnrgyCat, Energy_Category.get(i));
//			clickOn(drpTypeFrenchiseDmo, "Frenchise Type DropDown");
//			WebElement weTypeEnter = driver.findElement(By.xpath("//li[text()='" + Frenchise_Type.get(i) + "']"));
//			// enterText(weVehicleTypeEnter, "Vehicle Type", Vehicle.get(i));
//			clickOn(weTypeEnter, Frenchise_Type.get(i));
//			mouseActions.doubleClick(txtActcivityAmntFrenchiseDmo).perform();
//			enterText(txtActcivityAmntFrenchiseDmo, "frenchisee Leased_Asset Scope3.14",
//					Activity_Amount_Frenchise.get(i));
//			clickOn(drpUnitsHmeOffce, "Frenchise unit drop down");
//			WebElement weUnitFrenchise = driver.findElement(By.xpath("//li[text()='" + unit_Frenchisee.get(i) + "']"));
//			clickOn(weUnitFrenchise, unit_Frenchisee.get(i));
//			sleep(3000);
//			clickOn(btnSaveScope3_3PurcGood, "Save Button");
//			By toast = By.xpath("//div[text()='Activity updated successfully']");
//			if (isElementPresent(toast)) {
//				passed("Successfully displayed toast message - Activity updated successfully");
//			} else {
//				failed(driver, "Failed to display toast message");
//			}
//			System.out.println("-------Validate details---------");
//		} catch (Exception e) {
//			failed(driver, "Exception caught" + e.getMessage());
//		}
//	}
//
//	public void addActivityScope3_3Emissions(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("FuelandEnergyRelatedActivit",
//					"Facility Name");
//			List<String> description = MultiDataExcelReader.getTestData("FuelandEnergyRelatedActivit", "Description");
//			List<String> startDate = MultiDataExcelReader.getTestData("FuelandEnergyRelatedActivit", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("FuelandEnergyRelatedActivit", "End Date");
//			List<String> energy_Type = MultiDataExcelReader.getTestData("FuelandEnergyRelatedActivit", "Energy Type");
//			List<String> fuel_Type = MultiDataExcelReader.getTestData("FuelandEnergyRelatedActivit", "Fuel Type");
//			List<String> fuel_Name = MultiDataExcelReader.getTestData("FuelandEnergyRelatedActivit", "Fuel Name");
//			List<String> quantity_consumed = MultiDataExcelReader.getTestData("FuelandEnergyRelatedActivit",
//					"Quantity Consumed");
//			List<String> units = MultiDataExcelReader.getTestData("FuelandEnergyRelatedActivit", "Units");
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("FuelandEnergyRelatedActivit",
//					"Custom Emission");
//			List<String> tCO2e = MultiDataExcelReader.getTestData("FuelandEnergyRelatedActivit", "tCO2e");
//			List<String> emissionFactor = MultiDataExcelReader.getTestData("FuelandEnergyRelatedActivit",
//					"Emission Factor");
//			List<String> source = MultiDataExcelReader.getTestData("FuelandEnergyRelatedActivit", "Source");
//			clickOn(btnActivities, "btnActivities");
//			verifyIfElementPresent(lblAddActvtyUpstrem, "lblAddActvtyUpstrem", "lblAddActvtyUpstrem");
//			Thread.sleep(5000);
//			clickOn(txtFacilityNameScope3_3, "txtFacilityNameScope3_3");
//			sleep(2000);
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			sleep(2000);
//			// mouseActions.doubleClick(txtDescrScope3_3).doubleClick().perform();
//			mouseActions.doubleClick(txtDescrScope3_3Qa).doubleClick().perform();
//			// enterText(txtDescrScope3_3, "Description Scope3", description.get(i));
//			enterText(txtDescrScope3_3Qa, "Description Scope3", description.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			clickOn(drpEnerfgyType, "Energy type DropDown");
//			WebElement weEnergyType = driver.findElement(By.xpath("//li[text()='" + energy_Type.get(i) + "']"));
//			clickOn(weEnergyType, energy_Type.get(i));
//			if (energy_Type.get(i).equals("Electricity") || energy_Type.get(i).equals("Heat and Steam")) {
//				clickOn(txtScope3_3FuelEnrgyUnit, "Unit");
//				weUnit = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//				clickOn(weUnit, units.get(i));
//				sleep(2000);
//				mouseActions.doubleClick(txtQuantityConsumedFuelEngy).perform();
//				enterText(txtQuantityConsumedFuelEngy, "Quantity of fuel Consumed", quantity_consumed.get(i));
//				sleep(5000);
//				clickOn(btnSaveScope3_3PurcGood, "Save Button");
//				System.out.println("-------Validate details---------");
//			} else {
//				clickOn(drpFuelType, "Fuel Type DropDown");
//				WebElement weFuelTypeEnter = driver.findElement(By.xpath("//input[@id='fuel_type_name']"));
//				enterText(weFuelTypeEnter, "Fuel Type", fuel_Type.get(i));
//				WebElement weFuelType = driver.findElement(By.xpath("//li[text()='" + fuel_Type.get(i) + "']"));
//				clickOn(weFuelType, fuel_Type.get(i));
//				sleep(2000);
//				clickOn(drpFuelName, "Fuel Name DropDown");
//				WebElement weFuelName = driver.findElement(By.xpath("//li[text()='" + fuel_Name.get(i) + "']"));
//				jsClick(weFuelName, fuel_Name.get(i));
//				System.out.println(fuel_Name.get(i));
//				clickOn(txtScope3_3FuelEnrgyUnit, "Unit");
//				weUnit = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//				clickOn(weUnit, units.get(i));
//				sleep(2000);
//				mouseActions.doubleClick(txtQuantityConsumedFuelEngy).perform();
//				enterText(txtQuantityConsumedFuelEngy, "Quantity of fuel Consumed", quantity_consumed.get(i));
//				sleep(5000);
//				clickOn(btnSaveScope3_3PurcGood, "Save Button");
//				System.out.println("-------Validate details---------");
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught" + e.getMessage());
//		}
//	}
//--Naveen

	

//	@FindBy(xpath = "//input[@id='Facility Name']")
//	public WebElement txtFacilityNameScope3_1qa;
//	@FindBy(xpath = "//*[text()='Activity Details']")
//	public WebElement lblActivityDetails;

//	public void AddActivityScope3EmissionsScope3_1_AverageBased(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("PurchasedGoodsAndServicesAverag",
//					"Facility Name");
//			List<String> description = MultiDataExcelReader.getTestData("PurchasedGoodsAndServicesAverag",
//					"Description");
//			List<String> startDate = MultiDataExcelReader.getTestData("PurchasedGoodsAndServicesAverag", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("PurchasedGoodsAndServicesAverag", "End Date");
//			List<String> pruchaseGoodTypeCategory = MultiDataExcelReader.getTestData("PurchasedGoodsAndServicesAverag",
//					"Purchased Goods Category");
//			List<String> purchaseGoodType = MultiDataExcelReader.getTestData("PurchasedGoodsAndServicesAverag",
//					"Purchased Good");
//			List<String> productionProcess = MultiDataExcelReader.getTestData("PurchasedGoodsAndServicesAverag",
//					"Production Process Involved");
//			List<String> units = MultiDataExcelReader.getTestData("PurchasedGoodsAndServicesAverag", "Units");
//			List<String> quantity = MultiDataExcelReader.getTestData("PurchasedGoodsAndServicesAverag",
//					"Quantity of Goods Purchased");
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("PurchasedGoodsAndServicesAverag",
//					"Custom Emission");
//			Actions act = new Actions(driver);
//			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "lblActivityDetails");
//			clickOn(txtFacilityNameScope3_1, "txtFacilityNameScope3_1");
////			clickOn(txtFacilityNameScope3_1qa, "txtFacilityNameScope3_1qa");
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			clickOn(txtDescrScope3, "Description Scope3");
//			sleep(2000);
//			act.doubleClick(txtDescrScope3).perform();
//			enterText(txtDescrScope3, "Description Scope3", description.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			clickOn(drpPurchasedGoodCategory, "Purchased Good and Category  DropDown");
//			WebElement wePurchasedGoodCategory = driver
//					.findElement(By.xpath("//li[text()='" + pruchaseGoodTypeCategory.get(i) + "']"));
//			clickOn(wePurchasedGoodCategory, pruchaseGoodTypeCategory.get(i));
//			clickOn(drpPurchasedGood, "Purchased Good DropDown");
//			sleep(2000);
//			WebElement wePurchasedGood = driver.findElement(By.xpath("//li[text()='" + purchaseGoodType.get(i) + "']"));
//			clickOn(wePurchasedGood, purchaseGoodType.get(i));
//			clickOn(drpProduProcInvolved, "Production Process Involved DropDown");
//			if (pruchaseGoodTypeCategory.get(i).equals("Plastic")
//					&& productionProcess.get(i).equals("Open-loop source")) {
//				List<WebElement> weProduProInvolved = driver
//						.findElements(By.xpath("//li[text()='" + productionProcess.get(i) + "']"));
//				clickOn(weProduProInvolved.get(1), "Open source");
//				sleep(2000);
////			} else if (pruchaseGoodTypeCategory.get(i).equals("Plastic") && purchaseGoodType.get(i).equals("Plastics: PP (incl. forming)") && productionProcess.get(i).equals("Open-loop source")){
////				List<WebElement> weProduProInvolved = driver.findElements(By.xpath("//li[text()='" + productionProcess.get(i) + "']"));
////
////				clickOn(weProduProInvolved.get(0), "Open source");
////				sleep(2000);
////				
////				sleep(2000);
//			} else {
//				WebElement weProduProInvolved = driver
//						.findElement(By.xpath("//li[text()='" + productionProcess.get(i) + "']"));
//				clickOn(weProduProInvolved, productionProcess.get(i));
//			}
//			clickOn(txtScope3PurchaseGoodUnit, "Unit");
//			we = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			clickOn(we, units.get(i));
//			clickOn(txtQuantityofGoodsPurch, "txtQuantityofGoodsPurch");
////			act.doubleClick(txtQuantityofGoodsPurch);
//			enterText(txtQuantityofGoodsPurch, "Quantity of Goods Purchased", quantity.get(i));
//			clickOn(btnSaveScope3PurcGood, "Save Button");
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

	

//	public void ValidateActivityDetailsInViewActivityScope3_1AverageBased(int i) {
//		try {
//			System.out.println("-------Emission Details Are-------");
//			String[] activityDetailFieldNames = { "Facility Name", "Description", "Start Date", "End Date",
//					"Purchased Goods Category", "Purchased Good", "Production Process Involved", "Custom Emission",
//					"Quantity of Goods Purchased", "Units", "tCO2e", "Emission Factor", "Source" };
//			Thread.sleep(1000);
//			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath(
//						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("PurchasedGoodsAndServicesAverag",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	@FindBy(xpath = "//button[contains(text(),'Spend Based Method')]")
//	public WebElement btnScope3_1SpendBased;
//	@FindBy(xpath = "//button[contains(text(),'Average Data Method')]")
//	public WebElement btnScope3_1AverageBased;
//	@FindBy(xpath = "//span[contains(text(),'Purchased Goods or Services')]//parent::div/parent::div//input//parent::div")
//	public WebElement drpPurchasedGoods_orServices;
//	@FindBy(xpath = "//input[@id='amount_spend']")
//	public WebElement txtAmountSpentScope3spend;
//	@FindBy(xpath = "//span[contains(text(),'Currency')]//parent::div/parent::div//input//parent::div")
//	public WebElement drpCurrencySpendBased;
//	@FindBy(xpath = "//input[@id='pg_sb_activity_type_name']")
//	public WebElement txtPurchaseGood_Ser;

//	public void AddActivityScope3_1SpendBasedEmissions(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("PurchaseGoodsServiSpendBased",
//					"Facility Name");
//			List<String> description = MultiDataExcelReader.getTestData("PurchaseGoodsServiSpendBased", "Description");
//			List<String> startDate = MultiDataExcelReader.getTestData("PurchaseGoodsServiSpendBased", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("PurchaseGoodsServiSpendBased", "End Date");
//			List<String> pruchaseGoodTypeCategory = MultiDataExcelReader.getTestData("PurchaseGoodsServiSpendBased",
//					"Purchased Goods or Services");
//			List<String> currency = MultiDataExcelReader.getTestData("PurchaseGoodsServiSpendBased", "Currency");
//			List<String> amount = MultiDataExcelReader.getTestData("PurchaseGoodsServiSpendBased", "Amount Spent");
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("PurchaseGoodsServiSpendBased",
//					"Custom Emission");
//			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "lblActivityDetails");
//			Thread.sleep(1000);
//			// qa ---UAT
////			clickOn(txtFacilityNameScope3_1qa, "txtFacilityNameScope3_1qa");
//			// demo
//			clickOn(txtFacilityNameScope3_1, "txtFacilityNameScope3_1");
//			sleep(2000);
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			Actions act = new Actions(driver);
//			act.doubleClick(txtDescrScope3).perform();
//			enterText(txtDescrScope3, "Description Scope3", description.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			clickOn(drpPurchasedGoods_orServices, "Purchased Good or Services  DropDown");
//			enterText(txtPurchaseGood_Ser, "Purchased Godod And services", pruchaseGoodTypeCategory.get(i));
//			sleep(1000);
//			WebElement wePurchasedGoodCategory = driver
//					.findElement(By.xpath("//li[text()='" + pruchaseGoodTypeCategory.get(i) + "']"));
//			clickOn(wePurchasedGoodCategory, pruchaseGoodTypeCategory.get(i));
//			sleep(2000);
//			enterText(txtAmountSpentScope3spend, "Amount Spent", amount.get(i));
//			clickOn(drpCurrencySpendBased, "Currency");
//			we = driver.findElement(By.xpath("//li[text()='" + currency.get(i) + "']"));
//			clickOn(we, currency.get(i));
//			clickOn(btnSaveScope3PurcGood, "Save Button");
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

//	public void ValidateActivityDetailsInViewActivityScope3_1SpendBased(int i) {
//		try {
//			System.out.println("-------Emission Details Are-------");
//			String[] activityDetailFieldNames = { "Facility Name", "Description", "Start Date", "End Date",
//					"Purchased Goods or Services", "Currency", "Custom Emission", "Amount Spent", "tCO2e",
//					"Emission Factor", "Source" };
//			Thread.sleep(1000);
//			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath(
//						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("PurchaseGoodsServiSpendBased",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

	
//	@FindBy(xpath = "//input[@id='facilityName']")
//	public WebElement txtFacilityNameScope3_2;
//	@FindBy(xpath = "//span[contains(text(),'Capital Goods Category')]//parent::div/parent::div//input//parent::div")
//	public WebElement drpCapitalGoodCategory;
//	@FindBy(xpath = "(//span[contains(text(),'Capital Good')])[2]//parent::div/parent::div//input//parent::div//button[@aria-label='Open']")
//	public WebElement drpCapitalGood;
//	@FindBy(xpath = "//input[@id='quantity_purchase']")
//	public WebElement txtQuantityofGoodsCapital;
//	@FindBy(xpath = "//input[@id='activity_type_name']")
//	public WebElement txtCapitalGoods_3_2;
//	@FindBy(xpath = "//input[@id='material_used_name']")
//	public WebElement txtCapitalGood_3_2;
//	@FindBy(xpath = "//input[@id='usage_type_name']")
//	public WebElement txtProdProInv3_2;
//	@FindBy(xpath = "(//button[text()='Save'])[2]")
//	public WebElement btnSaveScope3CapitalGood;
//
//	public void clickOnActivityInActivitiesGrid_CapitalGoodsAverageBased(int i) {
//		sleep(1000);
//		List<String> facilityName = MultiDataExcelReader.getTestData("CapitalGoodsAvreageBased", "Facility Name");
//		WebElement weActivity = driver
//				.findElement(By.xpath("//div[text()='" + facilityName.get(i) + "']//parent::div[@role='row']"));
//		clickOn(weActivity, "Activity of Facility" + facilityName.get(i));
//	}
//
//	public void AddActivityScope3_2EmissionsAverageBased(int i) {
//		List<String> facilityName = MultiDataExcelReader.getTestData("CapitalGoodsAvreageBased", "Facility Name");
//		List<String> description = MultiDataExcelReader.getTestData("CapitalGoodsAvreageBased", "Description");
//		List<String> startDate = MultiDataExcelReader.getTestData("CapitalGoodsAvreageBased", "Start Date");
//		List<String> endDate = MultiDataExcelReader.getTestData("CapitalGoodsAvreageBased", "End Date");
//		List<String> capitalGoodCategory = MultiDataExcelReader.getTestData("CapitalGoodsAvreageBased",
//				"Capital Goods Category");
//		List<String> capitalGoodType = MultiDataExcelReader.getTestData("CapitalGoodsAvreageBased", "Capital Good");
//		List<String> productionProcess = MultiDataExcelReader.getTestData("CapitalGoodsAvreageBased",
//				"Production Process Involved");
//		List<String> units = MultiDataExcelReader.getTestData("CapitalGoodsAvreageBased", "Units");
//		List<String> quantity = MultiDataExcelReader.getTestData("CapitalGoodsAvreageBased",
//				"Quantity of Goods Purchased");
//		List<String> customEmissionFactor = MultiDataExcelReader.getTestData("CapitalGoodsAvreageBased", "Custom EF");
//		List<String> tCO2e = MultiDataExcelReader.getTestData("CapitalGoodsAvreageBased", "tCO2e");
//		List<String> emissionFactor = MultiDataExcelReader.getTestData("CapitalGoodsAvreageBased", "Emission Factor");
//		List<String> source = MultiDataExcelReader.getTestData("CapitalGoodsAvreageBased", "Source");
//		try {
////		clickOn(btnActivities, "btnActivities");
//			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
//			sleep(4000);
//			clickOn(txtFacilityNameScope3_2, "txtFacilityNameScope3_1");
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			Actions act = new Actions(driver);
//			act.doubleClick(txtDescrScope3).perform();
//			enterText(txtDescrScope3, "Description Scope3", description.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			clickOn(drpCapitalGoodCategory, "Capital Good and Category  DropDown");
//			enterText(txtCapitalGoods_3_2, "txt Capital goods or ser ", capitalGoodCategory.get(i));
//			WebElement wePurchasedGoodCategory = driver
//					.findElement(By.xpath("//li[text()='" + capitalGoodCategory.get(i) + "']"));
//			clickOn(wePurchasedGoodCategory, capitalGoodCategory.get(i));
//			clickOn(drpCapitalGood, "Capital Good DropDown");
//			enterText(txtCapitalGood_3_2, "txt Capital good ", capitalGoodType.get(i));
//			WebElement wePurchasedGood = driver.findElement(By.xpath("//li[text()='" + capitalGoodType.get(i) + "']"));
//			clickOn(wePurchasedGood, capitalGoodType.get(i));
//			clickOn(drpProduProcInvolved, "Production Process Involved DropDown");
////        enterText(txtProdProInv3_2, "txtProdProInv3_2 ", productionProcess.get(i));
//			if (capitalGoodCategory.get(i).equals("Plastic") && productionProcess.get(i).equals("Open-loop source")) {
//				List<WebElement> weProduProInvolved = driver
//						.findElements(By.xpath("//li[text()='" + productionProcess.get(i) + "']"));
//				clickOn(weProduProInvolved.get(1), "Open source");
//				sleep(2000);
////		} else if (pruchaseGoodTypeCategory.get(i).equals("Plastic") && purchaseGoodType.get(i).equals("Plastics: PP (incl. forming)") && productionProcess.get(i).equals("Open-loop source")){
////			List<WebElement> weProduProInvolved = driver.findElements(By.xpath("//li[text()='" + productionProcess.get(i) + "']"));
////
////			clickOn(weProduProInvolved.get(0), "Open source");
////			sleep(2000);
////			
////			sleep(2000);
//			} else {
//				WebElement weProduProInvolved = driver
//						.findElement(By.xpath("//li[text()='" + productionProcess.get(i) + "']"));
//				clickOn(weProduProInvolved, productionProcess.get(i));
//			}
////		WebElement weProduProInvolved = driver.findElement(By.xpath("//li[text()='" + productionProcess.get(i) + "']"));
////
////		clickOn(weProduProInvolved, productionProcess.get(i));
//			clickOn(txtScope3PurchaseGoodUnit, "Unit");
//			we = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			clickOn(we, units.get(i));
//			act.doubleClick(txtQuantityofGoodsCapital).perform();
//			enterText(txtQuantityofGoodsCapital, "Quantity of Goods Purchased", quantity.get(i));
//			clickOn(btnSaveScope3CapitalGood, "Save Button");
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void ValidateActivityDetailsInViewActivityScope3_2AverageBased(int i) {
//		try {
//			System.out.println("-------Emission Details Are-------");
//			String[] activityDetailFieldNames = { "Facility Name", "Description", "Start Date", "End Date",
//					"Capital Goods Category", "Capital Good", "Production Process Involved", "Custom EF", "Units",
//					"Quantity of Goods Purchased", "tCO2e", "Emission Factor", "Source" };
//			Thread.sleep(1000);
//			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath(
//						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("CapitalGoodsAvreageBased",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

	

//	@FindBy(xpath = "//button[contains(text(),'Spend Based Method')]")
//	public WebElement btnScope3_2SpendBased;
//	@FindBy(xpath = "//span[contains(text(),'Capital Goods Category')]//parent::div/parent::div//input//parent::div")
//	public WebElement drpCapitalGoodsCategorySpend3_2;
//	@FindBy(xpath = "//input[@id='sb_activity_type_name']")
//	public WebElement txtCapitalGoodsCateg_3_2;
//	@FindBy(xpath = "//input[@id='material_used']")
//	public WebElement txtCapitalGoodSpendBased3_2;
//	@FindBy(xpath = "//input[@id='usage_type']")
//	public WebElement txtProductProInvSppendBse3_2;
//	@FindBy(xpath = "//p[text()='Activity Details']")
//	public WebElement lblActivityDetailsSppendBse3_2;
//
//	public void clickOnActivityInActivitiesGrid_SpendBasedCapitalGoods(int i) {
//		sleep(1000);
//		List<String> facilityName = MultiDataExcelReader.getTestData("CapitalGoodsSpendBased", "Facility Name");
//		WebElement weActivity = driver
//				.findElement(By.xpath("//div[text()='" + facilityName.get(i) + "']//parent::div[@role='row']"));
//		clickOn(weActivity, "Activity of Facility" + facilityName.get(i));
//	}
//
//	public void AddActivityScope3_2SpendBasedEmissions(int i) {
//		List<String> facilityName = MultiDataExcelReader.getTestData("CapitalGoodsSpendBased", "Facility Name");
//		List<String> description = MultiDataExcelReader.getTestData("CapitalGoodsSpendBased", "Description");
//		List<String> startDate = MultiDataExcelReader.getTestData("CapitalGoodsSpendBased", "Start Date");
//		List<String> endDate = MultiDataExcelReader.getTestData("CapitalGoodsSpendBased", "End Date");
//		List<String> capitalGoodCategory = MultiDataExcelReader.getTestData("CapitalGoodsSpendBased",
//				"Capital Goods Category");
//		List<String> capitalGoodType = MultiDataExcelReader.getTestData("CapitalGoodsSpendBased", "Capital Good");
//		List<String> productionProcess = MultiDataExcelReader.getTestData("CapitalGoodsSpendBased",
//				"Production Process Involved");
//		List<String> amount = MultiDataExcelReader.getTestData("CapitalGoodsSpendBased", "Amount Spent");
//		List<String> currency = MultiDataExcelReader.getTestData("CapitalGoodsSpendBased", "Unit/Currency");
//		List<String> customEmissionFactor = MultiDataExcelReader.getTestData("CapitalGoodsSpendBased", "Custom EF");
//		List<String> tCO2e = MultiDataExcelReader.getTestData("CapitalGoodsSpendBased", "tCO2e");
//		List<String> emissionFactor = MultiDataExcelReader.getTestData("CapitalGoodsSpendBased", "Emission Factor");
//		List<String> source = MultiDataExcelReader.getTestData("CapitalGoodsSpendBased", "Source");
//		try {
//			verifyIfElementPresent(lblActivityDetailsSppendBse3_2, "lblActivityDetailsSppendBse3_2",
//					"lblActivityDetailsSppendBse3_2");
//			Thread.sleep(2000);
//			clickOn(txtFacilityNameScope3_2, facilityName.get(i));
//			sleep(2000);
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			Actions act = new Actions(driver);
//			act.doubleClick(txtDescrScope3).perform();
//			clickOn(txtDescrScope3, "Description");
//			enterText(txtDescrScope3, "Description Scope3", description.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			clickOn(drpCapitalGoodsCategorySpend3_2, "Capital Goods Category  DropDown");
//			enterText(txtCapitalGoodsCateg_3_2, "txt Capital goods Categery", capitalGoodCategory.get(i));
//			sleep(1000);
//			WebElement wePurchasedGoodCategory = driver
//					.findElement(By.xpath("//li[text()='" + capitalGoodCategory.get(i) + "']"));
//			clickOn(wePurchasedGoodCategory, capitalGoodCategory.get(i));
//			sleep(2000);
//			clickOn(txtCapitalGoodSpendBased3_2, "CapiatalGoodType");
//			enterText(txtCapitalGoodSpendBased3_2, "txt Capital good ", capitalGoodType.get(i));
//			clickOn(txtProductProInvSppendBse3_2, "ProductionProcess");
//			enterText(txtProductProInvSppendBse3_2, "txtProdProInv3_2 ", productionProcess.get(i));
//			act.doubleClick(txtAmountSpentScope3spend).perform();
//			enterText(txtAmountSpentScope3spend, "Amount Spent", amount.get(i));
//			clickOn(drpCurrencySpendBased, "Currency");
//			we = driver.findElement(By.xpath("//li[text()='" + currency.get(i) + "']"));
//			clickOn(we, currency.get(i));
//			clickOn(btnSaveScope3CapitalGood, "Save Button");
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void ValidateActivityDetailsInViewActivityScope3_2SpendBased(int i) {
//		try {
//			System.out.println("-------Emission Details Are-------");
//			String[] activityDetailFieldNames = { "Facility Name", "Description", "Start Date", "End Date",
//					"Capital Goods Category", "Capital Good", "Production Process Involved", "Custom EF",
//					"Unit/Currency", "Amount Spent", "tCO2e", "Emission Factor", "Source" };
//			Thread.sleep(1000);
//			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath(
//						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("CapitalGoodsSpendBased",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

	

//	@FindBy(xpath = "//input[@id='Facility Name']")
//	public WebElement txtFacilityNameScope3_12;
//	@FindBy(xpath = "//*[@id='description']")
//	public WebElement txtDescrScope3_12;
//	@FindBy(xpath = "//span[contains(text(),'Product Waste Category')]//parent::div/parent::div//div//button[@aria-label='Open']")
//	public WebElement drpProduWsateCate;
//	@FindBy(xpath = "//input[@id='product_category']")
//	public WebElement txtProduWasteCate;
//	@FindBy(xpath = "//span[contains(text(),'Product Waste Type')]//parent::div/parent::div//div//button[@aria-label='Open']")
//	public WebElement drpProduWasteType;
//	@FindBy(xpath = "//input[@id='product_waste_type']")
//	public WebElement txtProduWasteType;
//	@FindBy(xpath = "//span[contains(text(),'Waste Treatment Method')]//parent::div/parent::div//div//button[@aria-label='Open']")
//	public WebElement drpWasteTreatMethod; // demo
//	@FindBy(xpath = "//span[contains(text(),'Waste Disposal Method')]//parent::div/parent::div//div//button[@aria-label='Open']")
//	public WebElement drpWasteTreatMethodqa;
//	@FindBy(xpath = "//input[@id='waste_treatment_method']")
//	public WebElement txtWasteTreatMethod;
//	@FindBy(xpath = "//input[@id='waste_produce']")
//	public WebElement txtMassofWaste;
//
//	public void clickOnActivityInActivitiesGrid_EndOfLifeTreatment3_12(int i) {
//		sleep(1000);
//		List<String> facilityName = MultiDataExcelReader.getTestData("EndOfLifeTreatmentofSoldProduct",
//				"Facility Name");
//		WebElement weActivity = driver
//				.findElement(By.xpath("//div[text()='" + facilityName.get(i) + "']//parent::div[@role='row']"));
//		clickOn(weActivity, "Activity of Facility" + facilityName.get(i));
//	}

	

//	public void AddActivityScope3_12Emissions(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("EndOfLifeTreatmentofSoldProduct",
//					"Facility Name");
//			List<String> description = MultiDataExcelReader.getTestData("EndOfLifeTreatmentofSoldProduct",
//					"Description");
//			List<String> startDate = MultiDataExcelReader.getTestData("EndOfLifeTreatmentofSoldProduct", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("EndOfLifeTreatmentofSoldProduct", "End Date");
//			List<String> productWasteCategory = MultiDataExcelReader.getTestData("EndOfLifeTreatmentofSoldProduct",
//					"Product Waste Category");
//			List<String> productWasteType = MultiDataExcelReader.getTestData("EndOfLifeTreatmentofSoldProduct",
//					"Product Waste Type");
//			// demo
//			// List<String> wasteTreatmentMethod =
//			// MultiDataExcelReader.getTestData("EndOfLifeTreatmentofSoldProduct", "Waste
//			// Treatment Method");
//			// qa___UAT
//			List<String> wasteTreatmentMethod = MultiDataExcelReader.getTestData("EndOfLifeTreatmentofSoldProduct",
//					"Waste Disposal Method");
//			List<String> massOfWaste = MultiDataExcelReader.getTestData("EndOfLifeTreatmentofSoldProduct",
//					"Mass Of Waste After Consumer Use");
//			// demo
////	   List<String> units = MultiDataExcelReader.getTestData("EndOfLifeTreatmentofSoldProduct", "Units");
//			// qa __UAT
//			List<String> units = MultiDataExcelReader.getTestData("EndOfLifeTreatmentofSoldProduct", "Unit");
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("EndOfLifeTreatmentofSoldProduct",
//					"Custom Emission Factors");
//			List<String> tCO2e = MultiDataExcelReader.getTestData("EndOfLifeTreatmentofSoldProduct", "tCO2e");
//			List<String> emissionFactor = MultiDataExcelReader.getTestData("EndOfLifeTreatmentofSoldProduct",
//					"Emission Factor");
//			List<String> source = MultiDataExcelReader.getTestData("EndOfLifeTreatmentofSoldProduct", "Source");
//			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
//			sleep(1000);
//			clickOn(txtFacilityNameScope3_12, "txtFacilityNameScope3_12");
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			Actions act = new Actions(driver);
//			act.doubleClick(txtDescrScope3_12).perform();
//			enterText(txtDescrScope3_12, "Description Scope3_12", description.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			clickOn(drpProduWsateCate, "Product waste category  DropDown");
//			enterText(txtProduWasteCate, "txt CProduct waste Category ", productWasteCategory.get(i));
//			WebElement weProductWasteCategory = driver
//					.findElement(By.xpath("//li[text()='" + productWasteCategory.get(i) + "']"));
//			clickOn(weProductWasteCategory, productWasteCategory.get(i));
//			clickOn(drpProduWasteType, "Product wsate Type Dropdown");
//			enterText(txtProduWasteType, "txt Product Waste Type", productWasteType.get(i));
//			WebElement weProductWasteType = driver
//					.findElement(By.xpath("//li[text()='" + productWasteType.get(i) + "']"));
//			clickOn(weProductWasteType, productWasteType.get(i));
//			clickOn(drpWasteTreatMethodqa, "Waste Treatment Method DropDown");
//			enterText(txtWasteTreatMethod, "txtWasteTreatMethod ", wasteTreatmentMethod.get(i));
//			sleep(1000);
//			WebElement weWasteTreatmentMthod = driver
//					.findElement(By.xpath("//li[text()='" + wasteTreatmentMethod.get(i) + "']"));
//			sleep(3000);
//			jsClick(weWasteTreatmentMthod, wasteTreatmentMethod.get(i));
//			enterText(txtMassofWaste, "Mass of waste afetr consumer use", massOfWaste.get(i));
//			clickOn(txtScope3PurchaseGoodUnit, "Unit");
//			we = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			clickOn(we, units.get(i));
//			sleep(1000);
//			clickOn(btnSaveScope3CapitalGood, "Save Button End of Life");
//			System.out.println();
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void ValidateActivityDetailsInViewActivityScope3_12(int i) {
//		try {
//			System.out.println("-------Emission Details Are-------");
//			// demo
//			// String[] activityDetailFieldNames = {"Facility Name","Description","Start
//			// Date","End Date","Product Waste Category","Product Waste Type","Waste
//			// Treatment Method","Custom Emission Factors","Units","Mass Of Waste After
//			// Consumer Use","tCO2e","Emission Factor","Source"};
//			// qa __UAT
//			String[] activityDetailFieldNames = { "Facility Name", "Description", "Start Date", "End Date",
//					"Product Waste Category", "Product Waste Type", "Waste Disposal Method", "Custom Emission Factors",
//					"Unit", "Mass Of Waste After Consumer Use", "tCO2e", "Emission Factor", "Source" };
//			Thread.sleep(1000);
//			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				// demo UAT.netr
//				WebElement weActivityField = driver.findElement(By.xpath(
//						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
//				// qa__UAT
//				// WebElement weActivityField =
//				// driver.findElement(By.xpath("//span[text()='"+activityDetailFieldNames[j]+"']//parent::p//following-sibling::p"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("EndOfLifeTreatmentofSoldProduct",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

	
//	public void clickOnActivityInActivitiesGridHotelStay3_6(int i) {
//		sleep(1000);
//		List<String> facilityName = MultiDataExcelReader.getTestData("HotelStay", "Facility Name");
//		WebElement weActivity = driver
//				.findElement(By.xpath("//div[text()='" + facilityName.get(i) + "']//parent::div[@role='row']"));
//		clickOn(weActivity, "Activity of Facility" + facilityName.get(i));
//	}

//	@FindBy(xpath = "//input[@id='FacilityName']")
//	public WebElement txtFacilityNameScope3_6;
//	@FindBy(xpath = "//input[@id='GroupEmployee']")
//	public WebElement txtTravelDescrpScope3_6;
//	@FindBy(xpath = "//input[@id='Travel Purpose']")
//	public WebElement txtTravelDescrpScope3_6UAT;
//	@FindBy(xpath = "//span[contains(text(),'Country of Hotel Stay')]//parent::div/parent::div//div//button[@aria-label='Open']")
//	public WebElement drpCountryHotelStay;
//	@FindBy(xpath = "//input[@id='Countries']")
//	public WebElement txtCountryOfHotelstay;
//	@FindBy(xpath = "//input[@id='OccupiedRooms']")
//	public WebElement txtRoomsOccupied;
//	@FindBy(xpath = "//input[@id='Occupied Rooms']")
//	public WebElement txtRoomsOccupiedUAT;
//	@FindBy(xpath = "//input[@id='Numberofnights']")
//	public WebElement txtNightStayed;
//	@FindBy(xpath = "//input[@id='Number of Nights']")
//	public WebElement txtNightStayedUAT;
//	@FindBy(xpath = "//input[@id='Units']")
//	public WebElement txtUnitsHotelStay;
//	@FindBy(xpath = "(//button[text()='Save'])[2]")
//	public WebElement btnSaveHotelStay;
//	@FindBy(xpath = "//span[contains(text(),'Facility Name')]//parent::div/parent::div//div//button[@aria-label='Open']")
//	public WebElement drpFacilityNameScope3_6;
//	@FindBy(xpath = "//div[contains(text(),'Edit Activity')]")
//	public WebElement lblActivityDetailsScope3_6;
//	@FindBy(xpath = "//span[text()='Start Date']")
//	public WebElement westartdateHotelstay;
//
//	public void AddActivityScope3_6HotelStayEmissions(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("HotelStay", "Facility Name");
//			List<String> country = MultiDataExcelReader.getTestData("HotelStay", " Country of Hotel Stay");
//			List<String> descriptionHotelStay = MultiDataExcelReader.getTestData("HotelStay", "Travel Description");
//			List<String> startDate = MultiDataExcelReader.getTestData("HotelStay", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("HotelStay", "End Date");
//			List<String> roomsOccupied = MultiDataExcelReader.getTestData("HotelStay", "Number of rooms occupied");
//			List<String> nightStayed = MultiDataExcelReader.getTestData("HotelStay", " Number of nights stayed");
//			List<String> units = MultiDataExcelReader.getTestData("HotelStay", "Units");
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("HotelStay", "Custom Emission Factor");
//			waitForElement(drpFacilityNameScope3_6);
//			clickOn(drpFacilityNameScope3_6, facilityName.get(i));
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			clickOn(drpCountryHotelStay, "Country of Hotel stay  DropDown");
//			enterText(txtCountryOfHotelstay, "txt CContry of hotel Stay", country.get(i));
//			sleep(1000);
//			if (country.get(i).equals("Korea (the Democratic People's Republic of Korea)")) {
//				Actions act = new Actions(driver);
//				act.moveToElement(westartdateHotelstay).click().perform();
//			} else {
//				WebElement weCountryofHotelStay = driver.findElement(By.xpath("//li[text()='" + country.get(i) + "']"));
//				clickOn(weCountryofHotelStay, country.get(i));
//			}
//			// Demo and QA uat.net
//			Actions act = new Actions(driver);
//			act.doubleClick(txtTravelDescrpScope3_6).perform();
//			enterText(txtTravelDescrpScope3_6, "Travel Description Scope3_6", descriptionHotelStay.get(i));
//			// uat.com
////		act.doubleClick(txtTravelDescrpScope3_6UAT).perform();
////		
////		enterText(txtTravelDescrpScope3_6UAT, "Travel Description Scope3_6", descriptionHotelStay.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
////       demo__qa	uat.net
//			act.doubleClick(txtRoomsOccupied).perform();
//			enterText(txtRoomsOccupied, "Number of rooms occupied", roomsOccupied.get(i));
//			// UAT
////		act.doubleClick(txtRoomsOccupiedUAT).perform();
////		enterText(txtRoomsOccupiedUAT, "Number of rooms occupied",roomsOccupied.get(i) );
////      demo__qa	uat.net
//			act.doubleClick(txtNightStayed).perform();
//			enterText(txtNightStayed, "Number of Nights Stayed", nightStayed.get(i));
////       UAT
////		act.doubleClick(txtNightStayedUAT).perform();
////		enterText(txtNightStayedUAT, "Number of Nights Stayed",nightStayed.get(i) );
//			clickOn(txtUnitsHotelStay, "Unit");
//			we = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			clickOn(we, units.get(i));
//			sleep(1000);
//			clickOn(btnSaveHotelStay, "Save Button Hotel stay");
////		By toast =By.xpath("//div[text()='Activity updated successfully']");
////
////        if (isElementPresent(toast)) {
////            
////            passed("Successfully displayed toast message - Activity updated successfully");
////            
////        } else {
////            
////            failed(driver, "Failed to display toast message");
////            
////        }
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void ValidateActivityDetailsInViewActivityScope3_6HotelStay(int i) {
//		try {
//			System.out.println("-------Emission Details Are-------");
//			String[] activityDetailFieldNames = { "Facility Name", "Travel Description", "Start Date", "End Date",
//					"Number of rooms occupied", "Custom Emission Factor", "Units", " Country of Hotel Stay",
//					"Emission Factor", "Source", " Number of nights stayed" };
//			Thread.sleep(1000);
//			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(
//						By.xpath("//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//parent::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("HotelStay",
//						activityDetailFieldNames[j]);
//				String scopeDes3_6txt = weActivityField.getText().trim();
//				String[] weHotelStayActivity = scopeDes3_6txt.split("\n");
//				if (weHotelStayActivity[1].equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weHotelStayActivity[1]);
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weHotelStayActivity[1]);
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
// .................Upstream Leased Assets.....

	

//	@FindBy(xpath = "//span[contains(text(),'Energy Category')]//parent::div/parent::div//div//button[@aria-label='Open']")
//	public WebElement drpEnergyCategoryLeasedassests;
//	@FindBy(xpath = "//span[contains(text(),'Type')]//parent::div/parent::div//div//button[@aria-label='Open']")
//	public WebElement drpTypeLeasedAsset;
//	@FindBy(xpath = "//input[@id='leased_asset']")
//	public WebElement txtLeasedAsset;
//	@FindBy(xpath = "//input[@id='activity_amount']")
//	public WebElement txtActivityAmountLeasedAsset;
//	@FindBy(xpath = "//input[@id='source_data_unit_name']")
//	public WebElement txtunitLeasedAsset;
//
//	public void AddActivityScope3_8UpstreamLeasedAssets(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("UpstreamLeasedAsset", "Facility Name");
//			List<String> descriLeasedAsset = MultiDataExcelReader.getTestData("UpstreamLeasedAsset", "Description");
//			List<String> startDate = MultiDataExcelReader.getTestData("UpstreamLeasedAsset", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("UpstreamLeasedAsset", "End Date");
//			List<String> leasedAsset = MultiDataExcelReader.getTestData("UpstreamLeasedAsset", "Leased Asset");
//			List<String> energyCatleasedAsset = MultiDataExcelReader.getTestData("UpstreamLeasedAsset",
//					"Energy Category");
//			List<String> typeleasedAsset = MultiDataExcelReader.getTestData("UpstreamLeasedAsset", "Type");
//			List<String> units = MultiDataExcelReader.getTestData("UpstreamLeasedAsset", "Units");
//			List<String> activityAmontLeasedAsset = MultiDataExcelReader.getTestData("UpstreamLeasedAsset",
//					"Activity Amount");
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("UpstreamLeasedAsset",
//					"Custom Emission Factor");
//			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
//			waitForElement(drpFacilityNameScope3_6);
//			clickOn(drpFacilityNameScope3_6, facilityName.get(i));
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			Actions act = new Actions(driver);
//			act.doubleClick(txtDescrScope3_12).perform();
//			enterText(txtDescrScope3_12, "Upstream leased Description Scope3_8", descriLeasedAsset.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			act.doubleClick(txtLeasedAsset).perform();
//			enterText(txtLeasedAsset, "Leased Asset Input", leasedAsset.get(i));
//			clickOn(drpEnergyCategoryLeasedassests, energyCatleasedAsset.get(i));
//			WebElement weenergLeasedasset = driver
//					.findElement(By.xpath("//li[text()='" + energyCatleasedAsset.get(i) + "']"));
//			clickOn(weenergLeasedasset, energyCatleasedAsset.get(i));
//			clickOn(drpTypeLeasedAsset, typeleasedAsset.get(i));
//			WebElement weTypeLeasedAsset = driver
//					.findElement(By.xpath("//li[text()='" + typeleasedAsset.get(i) + "']"));
//			clickOn(weTypeLeasedAsset, typeleasedAsset.get(i));
//			act.doubleClick(txtActivityAmountLeasedAsset).perform();
//			enterText(txtActivityAmountLeasedAsset, "Activity type Leased Asset", activityAmontLeasedAsset.get(i));
//			clickOn(txtunitLeasedAsset, "Unit");
//			we = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			clickOn(we, units.get(i));
//			sleep(1000);
//			clickOn(btnSaveHotelStay, "Save Button Hotel stay");
//			By toast = By.xpath("//div[text()='Activity updated successfully']");
//			if (isElementPresent(toast)) {
//				passed("Successfully displayed toast message - Activity updated successfully");
//			} else {
//				failed(driver, "Failed to display toast message");
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void AddNewActivityScope3_8UpstreamLeasedAssets(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("UpstreamLeasedAsset", "Facility Name");
//			List<String> descriLeasedAsset = MultiDataExcelReader.getTestData("UpstreamLeasedAsset", "Description");
//			List<String> startDate = MultiDataExcelReader.getTestData("UpstreamLeasedAsset", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("UpstreamLeasedAsset", "End Date");
//			List<String> leasedAsset = MultiDataExcelReader.getTestData("UpstreamLeasedAsset", "Leased Asset");
//			List<String> energyCatleasedAsset = MultiDataExcelReader.getTestData("UpstreamLeasedAsset",
//					"Energy Category");
//			List<String> typeleasedAsset = MultiDataExcelReader.getTestData("UpstreamLeasedAsset", "Type");
//			List<String> units = MultiDataExcelReader.getTestData("UpstreamLeasedAsset", "Units");
//			List<String> activityAmontLeasedAsset = MultiDataExcelReader.getTestData("UpstreamLeasedAsset",
//					"Activity Amount");
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("UpstreamLeasedAsset",
//					"Custom Emission Factor");
//			Actions act = new Actions(driver);
//			Thread.sleep(3000);
//			clickOn(btnActivities, "btnActivities");
//			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
//			waitForElement(drpFacilityNameScope3_6);
//			clickOn(drpFacilityNameScope3_6, facilityName.get(i));
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			act.doubleClick(txtDescrScope3_12).perform();
//			enterText(txtDescrScope3_12, "Upstream leased Description Scope3_8", descriLeasedAsset.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			act.doubleClick(txtLeasedAsset).perform();
//			enterText(txtLeasedAsset, "Leased Asset Input", leasedAsset.get(i));
//			clickOn(drpEnergyCategoryLeasedassests, energyCatleasedAsset.get(i));
//			WebElement weenergLeasedasset = driver
//					.findElement(By.xpath("//li[text()='" + energyCatleasedAsset.get(i) + "']"));
//			clickOn(weenergLeasedasset, energyCatleasedAsset.get(i));
//			clickOn(drpTypeLeasedAsset, typeleasedAsset.get(i));
//			WebElement weTypeLeasedAsset = driver
//					.findElement(By.xpath("//li[text()='" + typeleasedAsset.get(i) + "']"));
//			clickOn(weTypeLeasedAsset, typeleasedAsset.get(i));
//			act.doubleClick(txtActivityAmountLeasedAsset).perform();
//			enterText(txtActivityAmountLeasedAsset, "Activity type Leased Asset", activityAmontLeasedAsset.get(i));
//			clickOn(txtunitLeasedAsset, "Unit");
//			we = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			clickOn(we, units.get(i));
//			sleep(1000);
//			clickOn(btnSaveHotelStay, "Save Button Hotel stay");
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void ValidateActivityDetailsInViewActivityScope3_8UpstreamLeasedAssets(int i) {
//		try {
//			sleep(3000);
//			System.out.println("-------Emission Details Are-------");
//			String[] activityDetailFieldNames = { "Facility Name", "Description", "Start Date", "End Date",
//					"Leased Asset", "Energy Category", "Type", "Activity Amount", "Custom Emission Factor", "Units",
//					"tCO2", "tCH4", "tN2O", "tCO2e", "Biofuel tCO2", "Emission Factor", "Source" };
//			Thread.sleep(1000);
//			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath(
//						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("UpstreamLeasedAsset",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

	

//	public void AddActivityScope3_13DownstreamLeasedAssets(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("DownStreamLeasedAsset", "Facility Name");
//			List<String> descriLeasedAsset = MultiDataExcelReader.getTestData("DownStreamLeasedAsset", "Description");
//			List<String> startDate = MultiDataExcelReader.getTestData("DownStreamLeasedAsset", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("DownStreamLeasedAsset", "End Date");
//			List<String> leasedAsset = MultiDataExcelReader.getTestData("DownStreamLeasedAsset", "Leased Asset");
//			List<String> energyCatleasedAsset = MultiDataExcelReader.getTestData("DownStreamLeasedAsset",
//					"Energy Category");
//			List<String> typeleasedAsset = MultiDataExcelReader.getTestData("DownStreamLeasedAsset", "Type");
//			List<String> units = MultiDataExcelReader.getTestData("DownStreamLeasedAsset", "Units");
//			List<String> activityAmontLeasedAsset = MultiDataExcelReader.getTestData("DownStreamLeasedAsset",
//					"Activity Amount");
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("DownStreamLeasedAsset",
//					"Custom Emission Factor");
//			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
//			waitForElement(drpFacilityNameScope3_6);
//			clickOn(drpFacilityNameScope3_6, facilityName.get(i));
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			Actions act = new Actions(driver);
//			act.doubleClick(txtDescrScope3_12).perform();
//			enterText(txtDescrScope3_12, "Upstream leased Description Scope3_8", descriLeasedAsset.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			act.doubleClick(txtLeasedAsset).perform();
//			enterText(txtLeasedAsset, "Leased Asset Input", leasedAsset.get(i));
//			clickOn(drpEnergyCategoryLeasedassests, energyCatleasedAsset.get(i));
//			WebElement weenergLeasedasset = driver
//					.findElement(By.xpath("//li[text()='" + energyCatleasedAsset.get(i) + "']"));
//			clickOn(weenergLeasedasset, energyCatleasedAsset.get(i));
//			clickOn(drpTypeLeasedAsset, typeleasedAsset.get(i));
//			WebElement weTypeLeasedAsset = driver
//					.findElement(By.xpath("//li[text()='" + typeleasedAsset.get(i) + "']"));
//			clickOn(weTypeLeasedAsset, typeleasedAsset.get(i));
//			act.doubleClick(txtActivityAmountLeasedAsset).perform();
//			enterText(txtActivityAmountLeasedAsset, "Activity type Leased Asset", activityAmontLeasedAsset.get(i));
//			clickOn(txtunitLeasedAsset, "Unit");
//			we = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			clickOn(we, units.get(i));
//			sleep(1000);
//			clickOn(btnSaveHotelStay, "Save Button Hotel stay");
//			By toast = By.xpath("//div[text()='Activity updated successfully']");
//			if (isElementPresent(toast)) {
//				passed("Successfully displayed toast message - Activity updated successfully");
//			} else {
//				failed(driver, "Failed to display toast message");
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void AddNewActivityScope3_13DownstreamLeasedAssets(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("DownStreamLeasedAsset", "Facility Name");
//			List<String> descriLeasedAsset = MultiDataExcelReader.getTestData("DownStreamLeasedAsset", "Description");
//			List<String> startDate = MultiDataExcelReader.getTestData("DownStreamLeasedAsset", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("DownStreamLeasedAsset", "End Date");
//			List<String> leasedAsset = MultiDataExcelReader.getTestData("DownStreamLeasedAsset", "Leased Asset");
//			List<String> energyCatleasedAsset = MultiDataExcelReader.getTestData("DownStreamLeasedAsset",
//					"Energy Category");
//			List<String> typeleasedAsset = MultiDataExcelReader.getTestData("DownStreamLeasedAsset", "Type");
//			List<String> units = MultiDataExcelReader.getTestData("DownStreamLeasedAsset", "Units");
//			List<String> activityAmontLeasedAsset = MultiDataExcelReader.getTestData("DownStreamLeasedAsset",
//					"Activity Amount");
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("DownStreamLeasedAsset",
//					"Custom Emission Factor");
//			Thread.sleep(3000);
//			clickOn(btnActivities, "btnActivities");
//			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
//			waitForElement(drpFacilityNameScope3_6);
//			clickOn(drpFacilityNameScope3_6, facilityName.get(i));
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			enterText(txtDescrScope3_12, "Upstream leased Description Scope3_8", descriLeasedAsset.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			enterText(txtLeasedAsset, "Leased Asset Input", leasedAsset.get(i));
//			clickOn(drpEnergyCategoryLeasedassests, energyCatleasedAsset.get(i));
//			WebElement weenergLeasedasset = driver
//					.findElement(By.xpath("//li[text()='" + energyCatleasedAsset.get(i) + "']"));
//			clickOn(weenergLeasedasset, energyCatleasedAsset.get(i));
//			clickOn(drpTypeLeasedAsset, typeleasedAsset.get(i));
//			WebElement weTypeLeasedAsset = driver
//					.findElement(By.xpath("//li[text()='" + typeleasedAsset.get(i) + "']"));
//			clickOn(weTypeLeasedAsset, typeleasedAsset.get(i));
//			enterText(txtActivityAmountLeasedAsset, "Activity type Leased Asset", activityAmontLeasedAsset.get(i));
//			clickOn(txtunitLeasedAsset, "Unit");
//			we = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			clickOn(we, units.get(i));
//			sleep(1000);
//			clickOn(btnSaveHotelStay, "Save Button Hotel stay");
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void ValidateActivityDetailsInViewActivityScope3_13DownStreamLeasedAssets(int i) {
//		try {
//			sleep(3000);
//			System.out.println("-------Emission Details Are-------");
//			String[] activityDetailFieldNames = { "Facility Name", "Description", "Start Date", "End Date",
//					"Leased Asset", "Energy Category", "Type", "Activity Amount", "Custom Emission Factor", "Units",
//					"tCO2", "tCH4", "tN2O", "tCO2e", "Biofuel tCO2", "Emission Factor", "Source" };
//			Thread.sleep(1000);
//			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath(
//						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("UpstreamLeasedAsset",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	@FindBy(xpath = "//span[contains(text(),'Sold Product')]//parent::div/parent::div//div//button[@aria-label='Open']")
//	public WebElement drpSoldPrducts;
//	@FindBy(xpath = "//span[contains(text(),'Process Type')]//parent::div/parent::div//div//button[@aria-label='Open']")
//	public WebElement drpProcessTypeSoldPrducts;
//	@FindBy(xpath = "//input[@id='Amount']")
//	public WebElement txtMassOfSoldProduct;
//	@FindBy(xpath = "//div[text()='Processing of Sold Products']")
//	public WebElement weScope3_10ProcessingSoldProduct;
//
	
//
//	public void AddActivityScope3_10PrcessingofSoldProdutcs(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("ProcessingOfSoldProducts", "Facility Name");
//			List<String> descriPrcessingofSoldProdutcs = MultiDataExcelReader.getTestData("ProcessingOfSoldProducts",
//					"Description");
//			List<String> startDate = MultiDataExcelReader.getTestData("ProcessingOfSoldProducts", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("ProcessingOfSoldProducts", "End Date");
//			List<String> soldProduct = MultiDataExcelReader.getTestData("ProcessingOfSoldProducts", "Sold Product");
//			List<String> processTypeSoldProd = MultiDataExcelReader.getTestData("ProcessingOfSoldProducts",
//					"Process Type");
//			List<String> units = MultiDataExcelReader.getTestData("ProcessingOfSoldProducts", "Units");
//			List<String> activityAmontsoldProduct = MultiDataExcelReader.getTestData("ProcessingOfSoldProducts",
//					"Activity Amount");
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("ProcessingOfSoldProducts",
//					"Custom EF");
//			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
//			waitForElement(drpFacilityNameScope3_6);
//			clickOn(drpFacilityNameScope3_6, facilityName.get(i));
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			Actions act = new Actions(driver);
//			act.doubleClick(txtDescrScope3_12).perform();
//			enterText(txtDescrScope3_12, "Upstream leased Description Scope3_8", descriPrcessingofSoldProdutcs.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			clickOn(drpSoldPrducts, soldProduct.get(i));
//			WebElement weSoldProducts = driver.findElement(By.xpath("//li[text()='" + soldProduct.get(i) + "']"));
//			clickOn(weSoldProducts, soldProduct.get(i));
//			clickOn(drpProcessTypeSoldPrducts, processTypeSoldProd.get(i));
//			WebElement weProcessTypeSoldProducts = driver
//					.findElement(By.xpath("//li[text()='" + processTypeSoldProd.get(i) + "']"));
//			clickOn(weProcessTypeSoldProducts, processTypeSoldProd.get(i));
//			act.doubleClick(txtMassOfSoldProduct).perform();
//			enterText(txtMassOfSoldProduct, "Mass of sold product", activityAmontsoldProduct.get(i));
//			clickOn(txtUnitsHotelStay, "Unit of Sold Product");
//			we = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			clickOn(we, units.get(i));
//			sleep(1000);
//			clickOn(btnSaveHotelStay, "Save Button Processing of Sold Products");
//			By toast = By.xpath("//div[text()='Activity updated successfully']");
//			if (isElementPresent(toast)) {
//				passed("Successfully displayed toast message - Activity updated successfully");
//			} else {
//				failed(driver, "Failed to display toast message");
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void AddNewActivityScope3_10PrcessingofSoldProdutcs(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("ProcessingOfSoldProducts", "Facility Name");
//			List<String> descriPrcessingofSoldProdutcs = MultiDataExcelReader.getTestData("ProcessingOfSoldProducts",
//					"Description");
//			List<String> startDate = MultiDataExcelReader.getTestData("ProcessingOfSoldProducts", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("ProcessingOfSoldProducts", "End Date");
//			List<String> soldProduct = MultiDataExcelReader.getTestData("ProcessingOfSoldProducts", "Sold Product");
//			List<String> processTypeSoldProd = MultiDataExcelReader.getTestData("ProcessingOfSoldProducts",
//					"Process Type");
//			List<String> units = MultiDataExcelReader.getTestData("ProcessingOfSoldProducts", "Units");
//			List<String> activityAmontsoldProduct = MultiDataExcelReader.getTestData("ProcessingOfSoldProducts",
//					"Activity Amount");
//			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("ProcessingOfSoldProducts",
//					"Custom EF");
//			Thread.sleep(3000);
//			clickOn(btnActivities, "btnActivities");
//			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
//			waitForElement(drpFacilityNameScope3_6);
//			clickOn(drpFacilityNameScope3_6, facilityName.get(i));
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			enterText(txtDescrScope3_12, "Upstream leased Description Scope3_8", descriPrcessingofSoldProdutcs.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			clickOn(drpSoldPrducts, soldProduct.get(i));
//			WebElement weSoldProducts = driver.findElement(By.xpath("//li[text()='" + soldProduct.get(i) + "']"));
//			clickOn(weSoldProducts, soldProduct.get(i));
//			clickOn(drpProcessTypeSoldPrducts, processTypeSoldProd.get(i));
//			WebElement weProcessTypeSoldProducts = driver
//					.findElement(By.xpath("//li[text()='" + processTypeSoldProd.get(i) + "']"));
//			clickOn(weProcessTypeSoldProducts, processTypeSoldProd.get(i));
//			enterText(txtMassOfSoldProduct, "Mass of sold product", activityAmontsoldProduct.get(i));
//			clickOn(txtUnitsHotelStay, "Unit of Sold Product");
//			we = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			clickOn(we, units.get(i));
//			sleep(1000);
//			clickOn(btnSaveHotelStay, "Save Button Processing of Sold Products");
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void ValidateActivityDetailsInViewActivityScope3_10PrcessingofSoldProdutcs(int i) {
//		try {
//			sleep(3000);
//			System.out.println("-------Emission Details Are-------");
//			String[] activityDetailFieldNames = { "Facility Name", "Description", "Start Date", "End Date",
//					"Sold Product", "Process Type", "Activity Amount", "Custom EF", "Units", "tCO2e", "Emission Factor",
//					"Source" };
//			Thread.sleep(1000);
//			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(
//						By.xpath("//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//parent::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("ProcessingOfSoldProducts",
//						activityDetailFieldNames[j]);
//				String scopeDes3_6txt = weActivityField.getText().trim();
//				String[] weProcessOfSoldProduct = scopeDes3_6txt.split("\n");
//				System.out.println(weProcessOfSoldProduct[1]);
//				if (weProcessOfSoldProduct[1].equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weProcessOfSoldProduct[1]);
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weProcessOfSoldProduct[1]);
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

	

//	@FindBy(xpath = "//input[@id='unit_sold']")
//	public WebElement txtNo_ofUnitsSold;
//	@FindBy(xpath = "//span[contains(text(),'Facility')]//parent::div/parent::div//div//button[@aria-label='Open']")
//	public WebElement drpFacilityNameScope3_11;
//
//	public void AddActivityScope3_11UseofSoldProdutcs(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("UseOfSoldProducts", "Facility Name");
//			List<String> descriPrcessingofSoldProdutcs = MultiDataExcelReader.getTestData("UseOfSoldProducts",
//					"Description");
//			List<String> startDate = MultiDataExcelReader.getTestData("UseOfSoldProducts", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("UseOfSoldProducts", "End Date");
//			List<String> soldProduct = MultiDataExcelReader.getTestData("UseOfSoldProducts", "Sold Product");
//			List<String> no_OfUnitsSold = MultiDataExcelReader.getTestData("UseOfSoldProducts", "Number of Units Sold");
////		List<String> customEmissionFactor = MultiDataExcelReader.getTestData("UseOfSoldProducts", "Custom EF");
//			clickOn(btnbtnActivities, "Craeting Activities Transaction");
//			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
//			waitForElement(drpFacilityNameScope3_11);
//			clickOn(drpFacilityNameScope3_11, facilityName.get(i));
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			Actions act = new Actions(driver);
//			act.doubleClick(txtDescrScope3_12).perform();
//			enterText(txtDescrScope3_12, "Upstream leased Description Scope3_8", descriPrcessingofSoldProdutcs.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			clickOn(drpSoldPrducts, soldProduct.get(i));
//			WebElement weSoldProducts = driver.findElement(By.xpath("//li[text()='" + soldProduct.get(i) + "']"));
//			clickOn(weSoldProducts, soldProduct.get(i));
//			act.doubleClick(txtNo_ofUnitsSold).perform();
//			enterText(txtNo_ofUnitsSold, "No. Of Units Sold", no_OfUnitsSold.get(i));
//			sleep(1000);
//			clickOn(btnSaveHotelStay, "Save Button Processing of Sold Products");
//			By toast = By.xpath("//div[text()='Activity updated successfully']");
//			if (isElementPresent(toast)) {
//				passed("Successfully displayed toast message - Activity updated successfully");
//			} else {
//				failed(driver, "Failed to display toast message");
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void AddNewActivityScope3_11UseofSoldProdutcs(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("UseOfSoldProducts", "Facility");
//			List<String> descriPrcessingofSoldProdutcs = MultiDataExcelReader.getTestData("UseOfSoldProducts",
//					"Description");
//			List<String> startDate = MultiDataExcelReader.getTestData("UseOfSoldProducts", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("UseOfSoldProducts", "End Date");
//			List<String> soldProduct = MultiDataExcelReader.getTestData("UseOfSoldProducts", "Sold Product");
//			List<String> no_OfUnitsSold = MultiDataExcelReader.getTestData("UseOfSoldProducts", "Number of Units Sold");
////			List<String> customEmissionFactor = MultiDataExcelReader.getTestData("UseOfSoldProducts", "Custom EF");
//			clickOn(btnbtnActivities, "Craeting Activities Transaction");
//			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
//			waitForElement(drpFacilityNameScope3_11);
//			clickOn(drpFacilityNameScope3_11, facilityName.get(i));
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			enterText(txtDescrScope3_12, "Upstream leased Description Scope3_8", descriPrcessingofSoldProdutcs.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			clickOn(drpSoldPrducts, soldProduct.get(i));
//			WebElement weSoldProducts = driver.findElement(By.xpath("//li[text()='" + soldProduct.get(i) + "']"));
//			clickOn(weSoldProducts, soldProduct.get(i));
//			enterText(txtNo_ofUnitsSold, "No. Of Units Sold", no_OfUnitsSold.get(i));
//			sleep(1000);
//			clickOn(btnSaveHotelStay, "Save Button Processing of Sold Products");
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void ValidateActivityDetailsInViewActivityScope3_11UseofSoldProdutcs(int i) {
//		try {
//			sleep(3000);
//			System.out.println("-------Emission Details Are-------");
//			String[] activityDetailFieldNames = { "Facility Name", "Description", "Start Date", "End Date",
//					"Sold Product", "Number of Units Sold", "Custom EF", "tCO2e", "Emission Factor", "Source" };
//			Thread.sleep(1000);
//			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(
//						By.xpath("//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//parent::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("UseOfSoldProducts",
//						activityDetailFieldNames[j]);
//				String scopeDes3_6txt = weActivityField.getText().trim();
//				String[] weProcessOfSoldProduct = scopeDes3_6txt.split("\n");
//				System.out.println(weProcessOfSoldProduct[1]);
//				if (weProcessOfSoldProduct[1].equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weProcessOfSoldProduct[1]);
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weProcessOfSoldProduct[1]);
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

	

//	@FindBy(xpath = "//input[@id='investee_company']")
//	public WebElement txtInvesteeCompanyScope3_15;
//	@FindBy(xpath = "//span[contains(text(),'Investee Company/Project Sector')]//parent::div/parent::div//div//button[@aria-label='Open']")
//	public WebElement drpInvesteeCompany;
//	@FindBy(xpath = "//input[@id='reporting_company_share']")
//	public WebElement txtreporting_company_share3_15;
//	@FindBy(xpath = "//input[@id='scope1_co2e']")
//	public WebElement txtScope1tCO2eEmissions3_15;
//	@FindBy(xpath = "//input[@id='scope2_co2e']")
//	public WebElement txtScope2tCO2eEmissions3_15;
//	@FindBy(xpath = "//input[@id='scope3_co2e']")
//	public WebElement txtScope3tCO2eEmissions3_15;
//	@FindBy(xpath = "//input[@id='ghg']")
//	public WebElement txtTotaltCO2eEmissions3_15;
//	@FindBy(xpath = "//input[@id='ghg']")
//	public WebElement txtSourcs3_15;

	

//	public void AddActivityScope3_15InvestmentsSpecificMethod(int i) {
//		try {
//			List<String> investeeCompany = MultiDataExcelReader.getTestData("Investments", "Investee Company");
//			List<String> investeeCompanyProjectSector = MultiDataExcelReader.getTestData("Investments",
//					"Investee Company Sector");
//			List<String> startDate = MultiDataExcelReader.getTestData("Investments", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("Investments", "End Date");
//			List<String> reportingCompanyShare = MultiDataExcelReader.getTestData("Investments", "Reporting company");
//			List<String> scope1EmissionofInvestee = MultiDataExcelReader.getTestData("Investments",
//					"Scope 1 - (tCO2e) Emissions of investee company");
//			List<String> scope2EmissionofInvestee = MultiDataExcelReader.getTestData("Investments",
//					"Scope 2 - (tCO2e) Emissions of investee company");
//			List<String> scope3EmissionofInvestee = MultiDataExcelReader.getTestData("Investments",
//					"Scope 3 - (tCO2e) Emissions of investee company");
//			List<String> totalCO2eEmissionofInvestee = MultiDataExcelReader.getTestData("Investments",
//					"Total GHG Emissions - (tCO2e) of investee company");
//			List<String> source = MultiDataExcelReader.getTestData("Investments", "Source of Emissions");
//			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
//			Actions act = new Actions(driver);
//			act.doubleClick(txtInvesteeCompanyScope3_15).perform();
//			enterText(txtInvesteeCompanyScope3_15, "Investee Copmany text", investeeCompany.get(i));
//			clickOn(drpInvesteeCompany, investeeCompanyProjectSector.get(i));
//			WebElement weinvesteeCompanyProject = driver
//					.findElement(By.xpath("//li[text()='" + investeeCompanyProjectSector.get(i) + "']"));
//			clickOn(weinvesteeCompanyProject, investeeCompanyProjectSector.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			act.doubleClick(txtreporting_company_share3_15).perform();
//			enterText(txtreporting_company_share3_15, "txtreporting_company_share3_15", reportingCompanyShare.get(i));
//			act.doubleClick(txtScope1tCO2eEmissions3_15).perform();
//			enterText(txtScope1tCO2eEmissions3_15, "Scope 1 - (tCO2e) Emissions of investee company 3_15",
//					scope1EmissionofInvestee.get(i));
//			act.doubleClick(txtScope2tCO2eEmissions3_15).perform();
//			enterText(txtScope2tCO2eEmissions3_15, "Scope 2 - (tCO2e) Emissions of investee company 3_15",
//					scope2EmissionofInvestee.get(i));
//			act.doubleClick(txtScope3tCO2eEmissions3_15).perform();
//			enterText(txtScope3tCO2eEmissions3_15, "Scope 3 - (tCO2e) Emissions of investee company 3_15",
//					scope3EmissionofInvestee.get(i));
//			act.doubleClick(txtTotaltCO2eEmissions3_15).perform();
//			enterText(txtTotaltCO2eEmissions3_15, "Total (tCO2e) Emissions of investee company 3_15",
//					totalCO2eEmissionofInvestee.get(i));
//			act.doubleClick(txtSourcs3_15).perform();
//			enterText(txtSourcs3_15, "Sourcs 3_15", source.get(i));
//			clickOn(btnSaveHotelStay, "Save Button Hotel stay");
//			By toast = By.xpath("//div[text()='Activity updated successfully']");
//			if (isElementPresent(toast)) {
//				passed("Successfully displayed toast message - Activity updated successfully");
//			} else {
//				failed(driver, "Failed to display toast message");
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

	public void extractTco2ValueInvestments() {
		sleep(3000);
		WebElement tco2Filed = driver.findElement(
				By.xpath("//span[text()='Total GHG Emissions (tCO2e)']//parent::div//following-sibling::div"));
		String prevoiusTc02 = tco2Filed.getText();
		GlobalVariables.tco2e = Double.parseDouble(prevoiusTc02);
		System.out.println(GlobalVariables.tco2e);
	}

	public void extract_Tco2Value() {
		sleep(2000);
		WebElement tco2Filed = driver.findElement(By.xpath("//span[text()='tCO2e']//parent::div//parent::div"));
		String scopeDes3_6txt = tco2Filed.getText().trim();
		String[] weHotelStayActivity = scopeDes3_6txt.split("\n");
		String prevoiusTc02 = weHotelStayActivity[1];
		GlobalVariables.tco2e = Double.parseDouble(prevoiusTc02);
		System.out.println(GlobalVariables.tco2e);
	}

//	public void AddNewActivityScope3_15InvestmentsSpecificMethod(int i) {
//		try {
//			List<String> investeeCompany = MultiDataExcelReader.getTestData("Investments", "Investee Company");
//			List<String> investeeCompanyProjectSector = MultiDataExcelReader.getTestData("Investments",
//					"Investee Company Sector");
//			List<String> startDate = MultiDataExcelReader.getTestData("Investments", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("Investments", "End Date");
//			List<String> reportingCompanyShare = MultiDataExcelReader.getTestData("Investments", "Reporting company");
//			List<String> scope1EmissionofInvestee = MultiDataExcelReader.getTestData("Investments",
//					"Scope 1 - (tCO2e) Emissions of investee company");
//			List<String> scope2EmissionofInvestee = MultiDataExcelReader.getTestData("Investments",
//					"Scope 2 - (tCO2e) Emissions of investee company");
//			List<String> scope3EmissionofInvestee = MultiDataExcelReader.getTestData("Investments",
//					"Scope 3 - (tCO2e) Emissions of investee company");
//			List<String> totalCO2eEmissionofInvestee = MultiDataExcelReader.getTestData("Investments",
//					"Total GHG Emissions - (tCO2e) of investee company");
//			List<String> source = MultiDataExcelReader.getTestData("Investments", "Source of Emissions");
//			clickOn(btnbtnActivities, "Craeting Activities Transaction");
//			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
//			enterText(txtInvesteeCompanyScope3_15, "Investee Copmany text", investeeCompany.get(i));
//			clickOn(drpInvesteeCompany, investeeCompanyProjectSector.get(i));
//			WebElement weinvesteeCompanyProject = driver
//					.findElement(By.xpath("//li[text()='" + investeeCompanyProjectSector.get(i) + "']"));
//			clickOn(weinvesteeCompanyProject, investeeCompanyProjectSector.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			enterText(txtreporting_company_share3_15, "txtreporting_company_share3_15", reportingCompanyShare.get(i));
//			enterText(txtScope1tCO2eEmissions3_15, "Scope 1 - (tCO2e) Emissions of investee company 3_15",
//					scope1EmissionofInvestee.get(i));
//			enterText(txtScope2tCO2eEmissions3_15, "Scope 2 - (tCO2e) Emissions of investee company 3_15",
//					scope2EmissionofInvestee.get(i));
//			enterText(txtScope3tCO2eEmissions3_15, "Scope 3 - (tCO2e) Emissions of investee company 3_15",
//					scope3EmissionofInvestee.get(i));
//			enterText(txtTotaltCO2eEmissions3_15, "Total (tCO2e) Emissions of investee company 3_15",
//					totalCO2eEmissionofInvestee.get(i));
//			enterText(txtSourcs3_15, "Sourcs 3_15", source.get(i));
//			clickOn(btnSaveHotelStay, "Save Button Hotel stay");
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void ValidateActivityDetailsInViewActivityScope3_15Investments_InvestmentSpecificMethod(int i) {
//		try {
//			sleep(3000);
//			System.out.println("-------Emission Details Are-------");
//			String[] activityDetailFieldNames = { "Investee Company", "Investee Company Sector", "Start Date",
//					"End Date", "Reporting company", "Scope 1 - (tCO2e) Emissions of investee company",
//					"Scope 2 - (tCO2e) Emissions of investee company",
//					"Scope 3 - (tCO2e) Emissions of investee company",
//					"Total GHG Emissions - (tCO2e) of investee company", "Source of Emissions",
//					"Scope 1 Emissions (tCO2e)", "Scope 2 Emissions (tCO2e)", "Scope3 Emissions (tCO2e)",
//					"Total GHG Emissions (tCO2e)" };
//			Thread.sleep(1000);
//			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath("//span[contains(text(),'"
//						+ activityDetailFieldNames[j] + "')]//parent::div//following-sibling::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("Investments",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

	
	

//	@FindBy(xpath = "//span[contains(text(),'Investee Company/Project Sector')]//parent::div/parent::div//div//button[@aria-label='Open']")
//	public WebElement drpInvesteeCompanyDebtFinance;

//	@FindBy(xpath = "//span[contains(text(),'equity-investment.company-sector')]//parent::div/parent::div//div//button[@aria-label='Open']")
//	public WebElement drpInvesteeCompanyssectorAverage;
//	@FindBy(xpath = "//span[contains(text(),'Custom Emission Factor')]//parent::div/parent::div//div//button[@aria-label='Open']")
//	public WebElement drpCEFAverageInvestments;
//	@FindBy(xpath = "//input[@id='revenue_of_investee']")
//	public WebElement txtRevenueOfInvesteeAverage;
//	@FindBy(xpath = "//input[@id='investee_company_share']")
//	public WebElement txtInvesteeCompanyShareInvesteeAverage;
//	@FindBy(xpath = "//input[@id='reporting_company_share']")
//	public WebElement txtReportingCompanyShareInvesteeAverage;
//
//	public void AddActivityScope3_15InvestmentsAverageDataMethod(int i) {
//		try {
//			List<String> investeeCompany = MultiDataExcelReader.getTestData("InvestmentsAverageDataMethod",
//					"Investee Company");
//			List<String> investeeCompanyProjectSector = MultiDataExcelReader.getTestData("InvestmentsAverageDataMethod",
//					"Investee Company Sector");
//			List<String> customEmissionFactorAverage = MultiDataExcelReader.getTestData("InvestmentsAverageDataMethod",
//					"Custom Emission Factor");
//			List<String> startDate = MultiDataExcelReader.getTestData("InvestmentsAverageDataMethod", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("InvestmentsAverageDataMethod", "End Date");
//			List<String> investeeCpmanyRevenue = MultiDataExcelReader.getTestData("InvestmentsAverageDataMethod",
//					"Investee Company's Revenue");
//			List<String> investeeCompanyshare = MultiDataExcelReader.getTestData("InvestmentsAverageDataMethod",
//					"Investee company's revenue (%) from selected sector");
//			List<String> reportingCompanyshare = MultiDataExcelReader.getTestData("InvestmentsAverageDataMethod",
//					"Reporting company's share of equity (%)");
//			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
//			Actions act = new Actions(driver);
//			act.doubleClick(txtInvesteeCompanyScope3_15).perform();
//			enterText(txtInvesteeCompanyScope3_15, "Investee Copmany text", investeeCompany.get(i));
//			clickOn(drpInvesteeCompanyssectorAverage, investeeCompanyProjectSector.get(i));
//			sleep(1000);
//			WebElement weinvesteeCompanyProject = driver
//					.findElement(By.xpath("//li[text()='" + investeeCompanyProjectSector.get(i) + "']"));
//			sleep(1000);
//			clickOn(weinvesteeCompanyProject, investeeCompanyProjectSector.get(i));
//			clickOn(drpCEFAverageInvestments, customEmissionFactorAverage.get(i));
//			sleep(1000);
//			WebElement weCEFAverageInvestments = driver
//					.findElement(By.xpath("//li[text()='" + customEmissionFactorAverage.get(i) + "']"));
//			sleep(1000);
//			clickOn(weCEFAverageInvestments, customEmissionFactorAverage.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			act.doubleClick(txtRevenueOfInvesteeAverage).perform();
//			enterText(txtRevenueOfInvesteeAverage, "txt RevenueOf Investee Average data Method",
//					investeeCpmanyRevenue.get(i));
//			act.doubleClick(txtReportingCompanyShareInvesteeAverage).perform();
//			enterText(txtReportingCompanyShareInvesteeAverage, "txt Reporting CompanyShare Average Data method",
//					reportingCompanyshare.get(i));
//			act.doubleClick(txtInvesteeCompanyShareInvesteeAverage).perform();
//			enterText(txtInvesteeCompanyShareInvesteeAverage, "txt Reporting CompanyShare Average Data method",
//					investeeCompanyshare.get(i));
//			clickOn(btnSaveHotelStay, "Save Button Investments");
////		jsClick(btnSaveHotelStay, "Save Button Investments");
//			By toast = By.xpath("//div[text()='Activity updated successfully']");
//			if (isElementPresent(toast)) {
//				passed("Successfully displayed toast message - Activity updated successfully");
//			} else {
//				failed(driver, "Failed to display toast message");
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

//	public void AddNewActivityScope3_15InvestmentsAverageDataMethod(int i) {
//		try {
//			List<String> investeeCompany = MultiDataExcelReader.getTestData("InvestmentsAverageDataMethod",
//					"Investee Company");
//			List<String> investeeCompanyProjectSector = MultiDataExcelReader.getTestData("InvestmentsAverageDataMethod",
//					"Investee Company Sector");
//			List<String> customEmissionFactorAverage = MultiDataExcelReader.getTestData("InvestmentsAverageDataMethod",
//					"Custom Emission Factor");
//			List<String> startDate = MultiDataExcelReader.getTestData("InvestmentsAverageDataMethod", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("InvestmentsAverageDataMethod", "End Date");
//			List<String> investeeCpmanyRevenue = MultiDataExcelReader.getTestData("InvestmentsAverageDataMethod",
//					"Investee Company's Revenue");
//			List<String> investeeCompanyshare = MultiDataExcelReader.getTestData("InvestmentsAverageDataMethod",
//					"Investee company's revenue (%) from selected sector");
//			List<String> reportingCompanyshare = MultiDataExcelReader.getTestData("InvestmentsAverageDataMethod",
//					"Reporting company's share of equity (%)");
//			clickOn(btnbtnActivities, "Craeting Activities Transaction");
//			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
//			enterText(txtInvesteeCompanyScope3_15, "Investee Copmany text", investeeCompany.get(i));
//			clickOn(drpInvesteeCompanyssectorAverage, investeeCompanyProjectSector.get(i));
//			WebElement weinvesteeCompanyProject = driver
//					.findElement(By.xpath("//li[text()='" + investeeCompanyProjectSector.get(i) + "']"));
//			clickOn(weinvesteeCompanyProject, investeeCompanyProjectSector.get(i));
//			clickOn(drpCEFAverageInvestments, customEmissionFactorAverage.get(i));
//			sleep(1000);
//			WebElement weCEFAverageInvestments = driver
//					.findElement(By.xpath("//li[text()='" + customEmissionFactorAverage.get(i) + "']"));
//			sleep(1000);
//			clickOn(weCEFAverageInvestments, customEmissionFactorAverage.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			enterText(txtRevenueOfInvesteeAverage, "txt RevenueOf Investee Average data Method",
//					investeeCpmanyRevenue.get(i));
//			enterText(txtReportingCompanyShareInvesteeAverage, "txt Reporting CompanyShare Average Data method",
//					reportingCompanyshare.get(i));
//			enterText(txtInvesteeCompanyShareInvesteeAverage, "txt Reporting CompanyShare Average Data method",
//					investeeCompanyshare.get(i));
//			clickOn(btnSaveHotelStay, "Save Button Investments");
////			jsClick(btnSaveHotelStay, "Save Button Investments");
//			By toast = By.xpath("//div[text()='Activity updated successfully']");
//			if (isElementPresent(toast)) {
//				passed("Successfully displayed toast message - Activity updated successfully");
//			} else {
//				failed(driver, "Failed to display toast message");
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//// xpaths 
//
//	public void ValidateActivityDetailsInViewActivityScope3_15Investments_InvestmentAverageDataMethod(int i) {
//		try {
//			sleep(3000);
//			System.out.println("-------Emission Details Are-------");
//			String[] activityDetailFieldNames = { "Investee Company", "Investee Company Sector",
//					"Custom Emission Factor", "Start Date", "End Date", "Investee Company's Revenue", "Units",
//					"Investee company's revenue (%) from selected sector", "Reporting company's share of equity (%)",
//					"Total GHG Emissions (tCO2e)", "Source", "Emission Factor" };
//			Thread.sleep(1000);
//			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath("//span[contains(text(),'"
//						+ activityDetailFieldNames[j] + "')]//parent::div//following-sibling::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("InvestmentsAverageDataMethod",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

	

//	@FindBy(xpath = "//input[@id='value_of_debt']")
//	public WebElement txtValueofDebtInvest;
//	@FindBy(xpath = "//input[@id='total_project_cost']")
//	public WebElement txtTotalProjectCost;
//	@FindBy(xpath = "//input[@id='currency_code']")
//	public WebElement drpUnitDebtInvest;
//	@FindBy(xpath = "//input[@id='source']")
//	public WebElement txtSourceDebtInvest;
//
//	public void AddActivityScope3_15InvestmentsDept_ProjectFinancingSpecificMethod(int i) {
//		try {
//			List<String> investeeCompany = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific",
//					"Investee Company/Project");
//			List<String> investeeCompanyProjectSector = MultiDataExcelReader
//					.getTestData("InvestmentsDebtProjectSpecific", "Investee Company/Project Sector");
//			List<String> startDate = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific", "End Date");
//			List<String> valofDebtInvest = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific",
//					"Value of Debt Investment");
//			List<String> totalProjectCost = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific",
//					"Total Project Cost");
//			List<String> units = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific", "Units");
//			List<String> scope1EmissionofInvestee = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific",
//					"Scope 1 - (tCO2e) Emissions of investee company");
//			List<String> scope2EmissionofInvestee = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific",
//					"Scope 2 - (tCO2e) Emissions of investee company");
//			List<String> scope3EmissionofInvestee = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific",
//					"Scope 3 - (tCO2e) Emissions of investee company");
//			List<String> totalCO2eEmissionofInvestee = MultiDataExcelReader
//					.getTestData("InvestmentsDebtProjectSpecific", "Total GHG Emissions - (tCO2e) of investee company");
//			List<String> source = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific",
//					"Source of Emissions");
//			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
//			Actions act = new Actions(driver);
//			act.doubleClick(txtInvesteeCompanyScope3_15).perform();
//			enterText(txtInvesteeCompanyScope3_15, "Investee Copmany text", investeeCompany.get(i));
//			clickOn(drpInvesteeCompany, investeeCompanyProjectSector.get(i));
//			WebElement weinvesteeCompanyProject = driver
//					.findElement(By.xpath("//li[text()='" + investeeCompanyProjectSector.get(i) + "']"));
//			clickOn(weinvesteeCompanyProject, investeeCompanyProjectSector.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			act.doubleClick(txtValueofDebtInvest).perform();
//			enterText(txtValueofDebtInvest, "txtValueofDebtInvest", valofDebtInvest.get(i));
//			sleep(1000);
//			act.doubleClick(txtTotalProjectCost).perform();
//			enterText(txtTotalProjectCost, "txtValueofDebtInvest", totalProjectCost.get(i));
//			clickOn(drpUnitDebtInvest, "Unit of Investments Debt");
//			WebElement weUnits = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			clickOn(weUnits, units.get(i));
//			act.doubleClick(txtScope1tCO2eEmissions3_15).perform();
//			enterText(txtScope1tCO2eEmissions3_15, "Scope 1 - (tCO2e) Emissions of investee company 3_15",
//					scope1EmissionofInvestee.get(i));
//			act.doubleClick(txtScope2tCO2eEmissions3_15).perform();
//			enterText(txtScope2tCO2eEmissions3_15, "Scope 2 - (tCO2e) Emissions of investee company 3_15",
//					scope2EmissionofInvestee.get(i));
//			act.doubleClick(txtScope3tCO2eEmissions3_15).perform();
//			enterText(txtScope3tCO2eEmissions3_15, "Scope 3 - (tCO2e) Emissions of investee company 3_15",
//					scope3EmissionofInvestee.get(i));
//			act.doubleClick(txtTotaltCO2eEmissions3_15).perform();
//			enterText(txtTotaltCO2eEmissions3_15, "Total (tCO2e) Emissions of investee company 3_15",
//					totalCO2eEmissionofInvestee.get(i));
//			act.doubleClick(txtSourceDebtInvest).perform();
//			enterText(txtSourceDebtInvest, "Sourcs 3_15", source.get(i));
//			sleep(2000);
//			clickOn(btnSaveHotelStay, "Save Button Hotel stay");
//			By toast = By.xpath("//div[text()='Activity updated successfully']");
//			if (isElementPresent(toast)) {
//				passed("Successfully displayed toast message - Activity updated successfully");
//			} else {
//				failed(driver, "Failed to display toast message");
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

	

//	public void AddNewActivityScope3_15InvestmentsDept_ProjectFinancingSpecificMethod(int i) {
//		try {
//			List<String> investeeCompany = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific",
//					"Investee Company/Project");
//			List<String> investeeCompanyProjectSector = MultiDataExcelReader
//					.getTestData("InvestmentsDebtProjectSpecific", "Investee Company/Project Sector");
//			List<String> startDate = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific", "End Date");
//			List<String> valofDebtInvest = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific",
//					"Value of Debt Investment");
//			List<String> totalProjectCost = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific",
//					"Total Project Cost");
//			List<String> units = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific", "Units");
//			List<String> scope1EmissionofInvestee = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific",
//					"Scope 1 - (tCO2e) Emissions of investee company");
//			List<String> scope2EmissionofInvestee = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific",
//					"Scope 2 - (tCO2e) Emissions of investee company");
//			List<String> scope3EmissionofInvestee = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific",
//					"Scope 3 - (tCO2e) Emissions of investee company");
//			List<String> totalCO2eEmissionofInvestee = MultiDataExcelReader
//					.getTestData("InvestmentsDebtProjectSpecific", "Total GHG Emissions - (tCO2e) of investee company");
//			List<String> source = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific",
//					"Source of Emissions");
//			clickOn(btnbtnActivities, "Craeting Activities Transaction");
//			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
//			enterText(txtInvesteeCompanyScope3_15, "Investee Copmany text", investeeCompany.get(i));
//			clickOn(drpInvesteeCompanyDebtFinance, investeeCompanyProjectSector.get(i));
//			WebElement weinvesteeCompanyProject = driver
//					.findElement(By.xpath("//li[text()='" + investeeCompanyProjectSector.get(i) + "']"));
//			clickOn(weinvesteeCompanyProject, investeeCompanyProjectSector.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			enterText(txtValueofDebtInvest, "txtValueofDebtInvest", valofDebtInvest.get(i));
//			sleep(1000);
//			enterText(txtTotalProjectCost, "txtValueofDebtInvest", totalProjectCost.get(i));
//			clickOn(drpUnitDebtInvest, "Unit of Investments Debt");
//			WebElement weUnits = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			clickOn(weUnits, units.get(i));
//			enterText(txtScope1tCO2eEmissions3_15, "Scope 1 - (tCO2e) Emissions of investee company 3_15",
//					scope1EmissionofInvestee.get(i));
//			enterText(txtScope2tCO2eEmissions3_15, "Scope 2 - (tCO2e) Emissions of investee company 3_15",
//					scope2EmissionofInvestee.get(i));
//			enterText(txtScope3tCO2eEmissions3_15, "Scope 3 - (tCO2e) Emissions of investee company 3_15",
//					scope3EmissionofInvestee.get(i));
//			enterText(txtTotaltCO2eEmissions3_15, "Total (tCO2e) Emissions of investee company 3_15",
//					totalCO2eEmissionofInvestee.get(i));
//			enterText(txtSourceDebtInvest, "Sourcs 3_15", source.get(i));
//			sleep(2000);
//			clickOn(btnSaveHotelStay, "Save Button Hotel stay");
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void ValidateActivityDetailsInViewActivityScope3_15InvestmentsDept_ProjectFinancingSpecificMethod(int i) {
//		try {
//			sleep(3000);
//			System.out.println("-------Emission Details Are-------");
//			String[] activityDetailFieldNames = { "Investee Company/Project", "Investee Company/Project Sector",
//					"Start Date", "End Date", "Value of Debt Investment", "Total Project Cost", "Units",
//					"Scope 1 - (tCO2e) Emissions of investee company",
//					"Scope 2 - (tCO2e) Emissions of investee company",
//					"Scope 3 - (tCO2e) Emissions of investee company",
//					"Total GHG Emissions - (tCO2e) of investee company", "Scope 1 Emissions (tCO2e)",
//					"Scope 2 Emissions (tCO2e)", "Scope 3 Emissions (tCO2e)", "Total GHG Emissions (tCO2e)",
//					"Share of total project costs (percent)", "Source" };
//			// "Source of Emissions", ---- demo
//			Thread.sleep(1000);
//			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath("//span[contains(text(),'"
//						+ activityDetailFieldNames[j] + "')]//parent::div//following-sibling::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("InvestmentsDebtProjectSpecific",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}

//	@FindBy(xpath = "//span[contains(text(),'Custom EF')]//parent::div/parent::div//div//button[@aria-label='Open']")
//	public WebElement drpCEFDebtAverage;
//	@FindBy(xpath = "//input[@id='project']")
//	public WebElement tctProjectPhase;
//	@FindBy(xpath = "//input[@id='value_of_debt']")
//	public WebElement txtValueofDebt;
//	@FindBy(xpath = "//input[@id='total_project_cost']")
//	public WebElement txtProjectCost;
//
//	public void AddActivityScope3_15InvestmentsDebtProjectFinancingAverageDataMethod(int i) {
//		try {
//			List<String> investeeCompany = MultiDataExcelReader.getTestData("InvestmentsDebtProjectAverage",
//					"Investee Company/Project");
//			List<String> investeeCompanyProjectSector = MultiDataExcelReader
//					.getTestData("InvestmentsDebtProjectAverage", "Investee Company/Project Sector");
//			List<String> customEmissionFactorAverage = MultiDataExcelReader.getTestData("InvestmentsDebtProjectAverage",
//					"Custom Emission Factor");
//			List<String> startDate = MultiDataExcelReader.getTestData("InvestmentsDebtProjectAverage", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("InvestmentsDebtProjectAverage", "End Date");
//			List<String> projectPhase = MultiDataExcelReader.getTestData("InvestmentsDebtProjectAverage",
//					"Project Phase");
//			List<String> valueOfDebtInvewstment = MultiDataExcelReader.getTestData("InvestmentsDebtProjectAverage",
//					"Value of Debt Investment");
//			List<String> totalProjectCost = MultiDataExcelReader.getTestData("InvestmentsDebtProjectAverage",
//					"Total Project Cost");
//			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
//			Actions act = new Actions(driver);
//			act.doubleClick(txtInvesteeCompanyScope3_15).perform();
//			enterText(txtInvesteeCompanyScope3_15, "Investee Copmany text", investeeCompany.get(i));
//			clickOn(drpInvesteeCompany, investeeCompanyProjectSector.get(i));
//			sleep(1000);
//			WebElement weinvesteeCompanyProject = driver
//					.findElement(By.xpath("//li[text()='" + investeeCompanyProjectSector.get(i) + "']"));
//			sleep(1000);
//			clickOn(weinvesteeCompanyProject, investeeCompanyProjectSector.get(i));
//			clickOn(drpCEFDebtAverage, customEmissionFactorAverage.get(i));
//			sleep(1000);
//			WebElement weCEFAverageInvestments = driver
//					.findElement(By.xpath("//li[text()='" + customEmissionFactorAverage.get(i) + "']"));
//			sleep(500);
//			clickOn(weCEFAverageInvestments, customEmissionFactorAverage.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			act.doubleClick(tctProjectPhase).perform();
//			enterText(tctProjectPhase, "txt Project Phase Debt Average", projectPhase.get(i));
//			act.doubleClick(txtValueofDebt).perform();
//			enterText(txtValueofDebt, "txt Value of Debt Average Data method", valueOfDebtInvewstment.get(i));
//			act.doubleClick(txtProjectCost).perform();
//			enterText(txtProjectCost, "txt Project Cost Debt Average Data method", totalProjectCost.get(i));
//			clickOn(btnSaveHotelStay, "Save Button Investments");
////		jsClick(btnSaveHotelStay, "Save Button Investments");
//			By toast = By.xpath("//div[text()='Activity updated successfully']");
//			if (isElementPresent(toast)) {
//				passed("Successfully displayed toast message - Activity updated successfully");
//			} else {
//				failed(driver, "Failed to display toast message");
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void AddNewActivityScope3_15InvestmentsDebtProjectFinancingAverageDataMethod(int i) {
//		try {
//			List<String> investeeCompany = MultiDataExcelReader.getTestData("InvestmentsDebtProjectAverage",
//					"Investee Company/Project");
//			List<String> investeeCompanyProjectSector = MultiDataExcelReader
//					.getTestData("InvestmentsDebtProjectAverage", "Investee Company/Project Sector");
//			List<String> customEmissionFactorAverage = MultiDataExcelReader.getTestData("InvestmentsDebtProjectAverage",
//					"Custom Emission Factor");
//			List<String> startDate = MultiDataExcelReader.getTestData("InvestmentsDebtProjectAverage", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("InvestmentsDebtProjectAverage", "End Date");
//			List<String> projectPhase = MultiDataExcelReader.getTestData("InvestmentsDebtProjectAverage",
//					"Project Phase");
//			List<String> valueOfDebtInvewstment = MultiDataExcelReader.getTestData("InvestmentsDebtProjectAverage",
//					"Value of Debt Investment");
//			List<String> totalProjectCost = MultiDataExcelReader.getTestData("InvestmentsDebtProjectAverage",
//					"Total Project Cost");
//			clickOn(btnbtnActivities, "Craeting Activities Transaction");
//			verifyIfElementPresent(lblActivityDetails, "lblAddActivity", "lblAddActivity");
//			enterText(txtInvesteeCompanyScope3_15, "Investee Copmany text", investeeCompany.get(i));
//			clickOn(drpInvesteeCompanyDebtFinance, investeeCompanyProjectSector.get(i));
//			sleep(1000);
//			WebElement weinvesteeCompanyProject = driver
//					.findElement(By.xpath("//li[text()='" + investeeCompanyProjectSector.get(i) + "']"));
//			sleep(1000);
//			clickOn(weinvesteeCompanyProject, investeeCompanyProjectSector.get(i));
//			clickOn(drpCEFDebtAverage, customEmissionFactorAverage.get(i));
//			sleep(1000);
//			WebElement weCEFAverageInvestments = driver
//					.findElement(By.xpath("//li[text()='" + customEmissionFactorAverage.get(i) + "']"));
//			sleep(500);
//			clickOn(weCEFAverageInvestments, customEmissionFactorAverage.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			enterText(tctProjectPhase, "txt Project Phase Debt Average", projectPhase.get(i));
//			enterText(txtValueofDebt, "txt Value of Debt Average Data method", valueOfDebtInvewstment.get(i));
//			enterText(txtProjectCost, "txt Project Cost Debt Average Data method", totalProjectCost.get(i));
//			clickOn(btnSaveHotelStay, "Save Button Investments");
////			jsClick(btnSaveHotelStay, "Save Button Investments");
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void ValidateActivityDetailsInViewActivityScope3_15InvestmentsDebtProjectFinancingAverageDataMethod(int i) {
//		try {
//			sleep(3000);
//			System.out.println("-------Emission Details Are-------");
//			String[] activityDetailFieldNames = { "Investee Company/Project", "Investee Company/Project Sector",
//					"Project Phase", "Custom Emission Factor", "Start Date", "End Date", "Value of Debt Investment",
//					"Units", "Total Project Cost", "Share of total project costs (percent)",
//					"Total GHG Emissions (tCO2e)", "Source", "Emission Factor" };
//			Thread.sleep(1000);
//			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath(
//						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("InvestmentsDebtProjectAverage",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void ValidateActivityDetailsInStationaryCombutionViewActivity(int i) {
//		try {
//			Thread.sleep(3000);
//			System.out.println("-------Emission Details Are-------");
//			String[] activityDetailFieldNames = { "Facility Name", "Invoice No.", "Invoice Date", "Start Date",
//					"End Date", "Fuel Type", "Fuel Amount", "Custom Emission Factor", "Unit Type", "Unit", "tCO2",
//					"tCH4", "tN2O", "tCO2e", "Biofuel tCO2", "Biofuel tCO2 (Daily)", "Emission Factor" };
//			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath(
//						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
//				Thread.sleep(1000);
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("CalculatorStationaryCombustion",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void EditActivityMobileCombutionInDirectEmissions(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("CalculatorMobileCombution", "Facility Name");
//			List<String> invoiceNumb = MultiDataExcelReader.getTestData("CalculatorMobileCombution", "Invoice No.");
//			List<String> invoiceDate = MultiDataExcelReader.getTestData("CalculatorMobileCombution", "Invoice Date");
//			List<String> startDate = MultiDataExcelReader.getTestData("CalculatorMobileCombution", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("CalculatorMobileCombution", "End Date");
//			List<String> fuelType = MultiDataExcelReader.getTestData("CalculatorMobileCombution", "Fuel Type");
//			List<String> activityType = MultiDataExcelReader.getTestData("CalculatorMobileCombution", "Activity Type");
//			List<String> vehicleType = MultiDataExcelReader.getTestData("CalculatorMobileCombution", "Vehicle Type");
//			List<String> activityAmount = MultiDataExcelReader.getTestData("CalculatorMobileCombution",
//					"Activity Amount");
//			List<String> units = MultiDataExcelReader.getTestData("CalculatorMobileCombution", "Unit");
//			verifyIfElementPresent(lblViewActivity, "lblViewActivity", "lblViewActivity");
//			clickOn(txtFacilityName, "txtFacilityName");
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			clickOn(txtInputVoice, "invoice Number");
//			enterText(txtInputVoice, "invoice number", invoiceNumb.get(i));
//			clickOn(txtInvoiceDate, "invoice date");
//			enterText(txtInvoiceDate, "Invoice Date", invoiceDate.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			clickOn(txtActivityTypeScope1, "txtActivity Type Scope1");
//			we = driver.findElement(By.xpath("//li[text()='" + activityType.get(i) + "']"));
//			Thread.sleep(1000);
//			clickOn(we, activityType.get(i));
//			clickOn(txtFuelTypeMobileCombuScope1, "Fuel Type");
//			we = driver.findElement(By.xpath("//li[text()='" + fuelType.get(i) + "']"));
//			Thread.sleep(1000);
//			clickOn(we, fuelType.get(i));
//			clickOn(txtVehicleTypeScope1, "txtVaehicle Type");
//			we = driver.findElement(By.xpath("//li[text()='" + vehicleType.get(i) + "']"));
//			Thread.sleep(1000);
//			clickOn(we, vehicleType.get(i));
//			clickOn(txtActivityAmountScope1, "Activity Amount Scope1");
//			enterText(txtActivityAmountScope1, "Fuel Amount", activityAmount.get(i));
//			clickOn(txtUnitName, "Unit");
//			we = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			sleep(500);
//			clickOn(we, units.get(i));
//			clickOn(btnSave, "Save Button");
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}
//
//	public void ValidateActivityMobileCumbutionDetailsInViewActivity(int i) {
//		try {
//			Thread.sleep(5000);
//			System.out.println("-------Emission Details Are-------");
//			String[] activityDetailFieldNames = { "Facility Name", "Invoice No.", "Invoice Date", "Start Date",
//					"End Date", "Fuel Type", "Activity Type", "Vehicle Type", "Custom Emission", "Activity Amount",
//					"Unit", "tCO2", "tCH4", "tN2O", "tCO2e", "Biofuel tCO2", "Emission Factor" };
//			verifyIfElementPresent(lblActivityDetails, "lblActivityDetails", "Scope3Calculators");
//			for (int j = 0; j < activityDetailFieldNames.length; j++) {
//				WebElement weActivityField = driver.findElement(By.xpath(
//						"//span[text()='" + activityDetailFieldNames[j] + "']//parent::div//following-sibling::div"));
//				List<String> activityFieldName = MultiDataExcelReader.getTestData("CalculatorMobileCombution",
//						activityDetailFieldNames[j]);
//				if (weActivityField.getText().trim().equals(activityFieldName.get(i).trim())) {
//					passed("Successfully Validated " + activityDetailFieldNames[j] + " In Activity Details As"
//							+ weActivityField.getText());
//				} else {
//					failed(driver,
//							"Failed To validate " + activityDetailFieldNames[j] + " In Activity Details Expected As "
//									+ activityFieldName.get(i) + "But Actual is" + weActivityField.getText());
//				}
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception caught " + e.getMessage());
//		}
//	}


//	@FindBy(xpath = "//input[@placeholder='Description']")
//	public WebElement txtDescrption;
//	@FindBy(xpath = "//input[@id='Type Of Home Office']")
//	public WebElement drpTypeOfhomeOffice;
//	@FindBy(xpath = "//input[@id='Number of working days']")
//	public WebElement txtNumberOfWorkingDays;
//	@FindBy(xpath = "//input[@id='Number of working hours per day']")
//	public WebElement txtNumberOfWorkingHoursPerDay;
//	@FindBy(xpath = "//input[@id='Number of employees']")
//	public WebElement txtNumberOfEmployees;
//	@FindBy(xpath = "//input[@id='Working regime']")
//	public WebElement txtWorkingRegime;
//	@FindBy(xpath = "//input[@id='Working from home']")
//	public WebElement txtEmployeeWorkingFromHome;
//	@FindBy(xpath = "//input[@id='Source Units']")
//	public WebElement drpUnitsHomeOffice;
//
//	public void AddActivityScope3_7Emissions(int i) {
//		try {
//			List<String> facilityName = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting",
//					"Facility / Location");
//			// List<String> country =
//			// MultiDataExcelReader.getTestData("HomeOfficeTelecommuting", "Country");
//			List<String> description = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting", "Description");
//			List<String> type_Of_Home_Office = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting",
//					"Type Of Home Office");
//			List<String> startDate = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting", "Start Date");
//			List<String> endDate = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting", "End Date");
//			List<String> number_of_Working_Days = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting",
//					"Number Of Working Days");
//			List<String> number_of_Working_Hours_Per_Day = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting",
//					"Number Of Working Hours Per Day");
//			List<String> number_of_Employees = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting",
//					"Number Of Employee");
//			List<String> working_Regime_of_Employees_in = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting",
//					"Working Regime %");
//			List<String> employees_Working_From_Home = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting",
//					"% Working From Home");
//			List<String> units = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting", "Units");
//			List<String> Energy_units = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting",
//					"Energy Consumed (kWh)");
//			List<String> tCO2e = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting", "tCO2e");
//			List<String> emission_Factor = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting",
//					"Emission Factor");
//			List<String> source = MultiDataExcelReader.getTestData("HomeOfficeTelecommuting", "Source");
//			clickOn(btnActivities, "btnActivities");
//			verifyIfElementPresent(lblAddActvtyUpstrem, "lblAddActvtyUpstrem", "lblAddActvtyUpstrem");
//			Thread.sleep(5000);
//			clickOn(txtFacilityNameScope3_4Qa, "txtFacilityNameScope3_4");
//			sleep(2000);
//			WebElement we = driver.findElement(By.xpath("//li[text()='" + facilityName.get(i) + "']"));
//			clickOn(we, facilityName.get(i));
//			sleep(2000);
//			// mouseActions.doubleClick(txtContryHmeOffce).perform();
////			enterText(txtContryHmeOffce, "Country", country.get(i));
//			mouseActions.doubleClick(txtDescrption).perform();
//			enterText(txtDescrption, "Description Scope3.4", description.get(i));
//			clickOn(drpTypeOfhomeOffice, "type of home office");
//			sleep(1000);
//			WebElement web = driver.findElement(By.xpath("//li[text()='" + type_Of_Home_Office.get(i) + "']"));
//			clickOn(web, type_Of_Home_Office.get(i));
//			clickOn(txtStartDate, "Start date");
//			enterText(txtStartDate, "Start Date", startDate.get(i));
//			clickOn(txtEndDate, "end date");
//			enterText(txtEndDate, "End Date", endDate.get(i));
//			mouseActions.doubleClick(txtNumberOfWorkingDays).perform();
//			enterText(txtNumberOfWorkingDays, "No. of Working days", number_of_Working_Days.get(i));
//			mouseActions.doubleClick(txtNumberOfWorkingHoursPerDay).perform();
//			enterText(txtNumberOfWorkingHoursPerDay, "No. of Working Hours per day",
//					number_of_Working_Hours_Per_Day.get(i));
//			mouseActions.doubleClick(txtNumberOfEmployees).perform();
//			enterText(txtNumberOfEmployees, "No. of Working Hours per day", number_of_Employees.get(i));
//			mouseActions.doubleClick(txtWorkingRegime).perform();
//			enterText(txtWorkingRegime, "Wroking Regime", working_Regime_of_Employees_in.get(i));
//			mouseActions.doubleClick(txtEmployeeWorkingFromHome).perform(); //
//			enterText(txtEmployeeWorkingFromHome, "No. of Working Hours per day", employees_Working_From_Home.get(i));
//			clickOn(drpUnitsHomeOffice, "drp units");
//			WebElement webUnits = driver.findElement(By.xpath("//li[text()='" + units.get(i) + "']"));
//			clickOn(webUnits, units.get(i));
//			clickOn(btnSaveTranscation, "Saved");
//			sleep(2000);
//			By toast = By.xpath("//div[text()='Activity added successfully']");
//			if (isElementPresent(toast)) {
//				passed("Successfully displayed toast message - Activity updated successfully");
//			} else {
//				failed(driver, "Failed to display toast message");
//			}
//		} catch (Exception e) {
//			failed(driver, "Exception csaught," + e.getMessage());
//		}
//	}

	

//	public void addActivityDetailsForScoep2(int i) {
//		List<String> facilityName = MultiDataExcelReader.getTestData("IndirectEmissions", "Facility Name");
//		List<String> energy_Category = MultiDataExcelReader.getTestData("IndirectEmissions", "Energy Category");
//		List<String> invoice_No = MultiDataExcelReader.getTestData("IndirectEmissions", "Invoice No.");
//		List<String> invoice_Date = MultiDataExcelReader.getTestData("IndirectEmissions", "Invoice Date");
//		List<String> start_date = MultiDataExcelReader.getTestData("IndirectEmissions", "Start Date");
//		List<String> end_Date = MultiDataExcelReader.getTestData("IndirectEmissions", "End Date");
//		List<String> amount_Energy = MultiDataExcelReader.getTestData("IndirectEmissions", "Amount of Energy");
//		List<String> unitsScope2 = MultiDataExcelReader.getTestData("IndirectEmissions", "Units");
//		clickOn(btnbtnActivities, "Craeting Activities Transaction");
//		verifyIfElementPresent(lblAddActvtyUpstrem, "Add actvity - ", " Scope 2");
//		clickOn(drpFacName, "Facility Name");
//		Actions act = new Actions(driver);
//		act.moveToElement(selDropFacilityName).click().build().perform();
//		clickOn(dpdEnergyCategory, "Opening Energy Category");
//		clickOn(drpEclectricity, "Selecting Electricity Energy Category");
//		clickOn(lblInvoiceNo, "Label invoice No");
//		enterText(txtInvoiceNo, "InvoiceNo", data.get("InvoiceNo"));
//		enterText(txtInvoiceDate, "InvoiceDate", data.get("InvoiceDate"));
//		enterText(txtStartDate, "StartDate", data.get("StartDate"));
//		enterText(txtEndDate, "EndDate", data.get("EndDate"));
//		enterText(txtAmountOfEnergy, "AmountOfEnergy", data.get("AmountOfEnergy"));
//		clickOn(droUnits, "Select Units");
//		String xpathlisUnit = "//ul//li[contains(text(),'" + data.get("UnitsXpaths") + "')]";
//		clickOnElementWithDynamicXpath(xpathlisUnit, "UnitsXpaths");
//		clickOn(btnSave, "Click on Save Button");
//		/*-----Toast Message-----*/
//		By toast = By.xpath("//div[text()='Activity added successfully']");
//		if (isElementPresent(toast)) {
//			passed("Successfully displayed toast message - Activity added successfully");
//		} else {
//			failed(driver, "Failed to display toast message");
//		}
//	}
	
	
	

	
//	public IndirectEmissionsCalculatorPage clickOnEmissionFactors() {
//		try {
//			sleep(4000);
//			WebElement clickOnEmissionFactors = driver.findElement(By.xpath("(//ul[@role='tree']//li/div)[4]"));
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
//		return new IndirectEmissionsCalculatorPage(driver, data); // returning new object
//																	// IndirectEmissionsCalculatorPage
//		}

	
//	public void verify_i_ToolTipsIncalcPages() {
//		try {
//			sleep(1000);
//			String languages = data.get("Language");
//			if(languages.contains(",")) {
//				String[] splittedlanguages = languages.split(",");
//				for (int i = 0; i < splittedlanguages.length; i++) {
//					selectLanguage(splittedlanguages[i]);
//					clickOnCalculator(8);
//				    clickOnAddActivity_ForDifferantLangauge();
//					String filepath = System.getProperty("user.dir");
//					System.out.println(filepath);
//					String path = filepath + "\\src\\test\\resources\\Details\\WasteGenerateDetails.xlsx";
//					ExcelWriter xlWriter = new ExcelWriter(path);
//					xlWriter.setCellData("WasteGenerate_Details", 0, i, splittedlanguages[i]);
//					List<WebElement> weibuttons = driver.findElements(By.xpath("//*[@data-testid='InfoOutlinedIcon']//parent::button"));
//					for (int j = 0; j < weibuttons.size(); j++) {
//						int s1 = j+1 ;
//						WebElement welblName = driver.findElement(By.xpath("(//*[@data-testid='InfoOutlinedIcon']//parent::button//parent::div//span)["+s1+"]"));
//						String lblName = welblName.getText();
//						sleep(200);
//						Actions act = new Actions(driver);
//						act.moveToElement(weibuttons.get(j)).perform();
//						WebElement we234 = driver.findElement(By.xpath("//div[@role='tooltip']"));		
////						String iToolTips = weibuttons.get(j).getAttribute("aria-label");
//						String iToolTips = we234.getText();
//						int rowCount = xlWriter.getRowCount("WasteGenerate_Details");
//						xlWriter.setCellData("WasteGenerate_Details", j+1, i, iToolTips);
//						System.out.println(lblName  +" --- >  " + iToolTips);
//						weibuttons.get(j).sendKeys(Keys.ESCAPE);
//						sleep(200);
//				}
//				}
//			}else {
//				selectLanguage(data.get("Language"));
//				sleep(1000);
//				clickOnCalculator(8);
//			    clickOnAddActivity_ForDifferantLangauge();
//				sleep(1000);
//				List<WebElement> weibuttons = driver.findElements(By.xpath("//*[@data-testid='InfoOutlinedIcon']//parent::button"));
//				for (int i = 0; i < weibuttons.size(); i++) {
//					int s1 = i+1 ;
//					WebElement welblName = driver.findElement(By.xpath("(//*[@data-testid='InfoOutlinedIcon']//parent::button//parent::div//span)["+s1+"]"));
//					String lblName = welblName.getText();
//					sleep(200);
//					String iToolTips = weibuttons.get(i).getAttribute("aria-label");
//					System.out.println(lblName  +" --- >  " + iToolTips);
//			}
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	@FindBy(xpath="//p[text()='Activity Details']/parent::div/following-sibling::div")
	public WebElement expandActivityDetails;
	public void expandActivityDetailsInRHP() {
		try {
			clickOn(expandActivityDetails, "expandActivityDetails");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void validateTCO2EValueForUpstreamLeasedAsset() {    //latestPriyanka
		try {sleep(1);
			WebElement emissionFactor = driver
					.findElement(By.xpath("//span[text()='Emission Factor']/parent::p/following-sibling::p/div"));
			String actualEmissionFactor = emissionFactor.getText().split(" ")[0].replaceAll(",", "");

			Double FormulaTCO2e = Double.parseDouble(actualEmissionFactor)
					* Double.parseDouble(data.get("Fuel Amount")) * Double.parseDouble(data.get("Conversion Value"))
					/ 1000;
			String TCO2e = FormulaTCO2e.toString();
			ValCO2eRHP = approximateDecimalValueWithBigDecimal(TCO2e);

			WebElement actualTCO2e = driver
					.findElement(By.xpath("//span[text()='tCO2e']/parent::p/following-sibling::p/div"));
			String actualTCO2efromUi = actualTCO2e.getText().replaceAll(",", "");

			if (actualTCO2efromUi.equals(ValCO2eRHP)) {
				passed("Successfully validated the TCO2e, actual as " + actualTCO2efromUi + " but expected as "
						+ ValCO2eRHP + "");
			} else {
				Double doubleDiff = Double.parseDouble(ValCO2eRHP) - Double.parseDouble(actualTCO2efromUi);
				info("" + doubleDiff + "");
				if (doubleDiff < 1 && doubleDiff > -0.4) {
					passed("successfully validated, Tco2e expected value is " + ValCO2eRHP + " and the " + "Actual as "
							+ actualTCO2efromUi + "");
				} else {
					failed(driver, "failed to validate, Tco2e expected value is " + ValCO2eRHP + " and the "
							+ "Actual as " + actualTCO2efromUi + "");
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
}
