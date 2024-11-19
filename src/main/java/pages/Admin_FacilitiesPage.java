package pages;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import base.TestBase;
import utilities.Data;

public class Admin_FacilitiesPage extends TestBase {
	protected Admin_FacilitiesPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	Date date = new Date();
	Random rnd = new Random();
	@FindBy(xpath = "//span[contains(text(),'Facilities')]//parent::div//article")
	private WebElement btnCreateFacility;
	@FindBy(xpath = "//input[@id='facilityName']")
	private WebElement txtFacilityName;
	@FindBy(xpath = "(//div[contains(text(),'Add New Facility')]//parent::div)[1]")
	private WebElement lblAddFacility;
	@FindBy(xpath = "//input[@id='facilityCode']")
	private WebElement txtFacilityCode;
	@FindBy(xpath = "//input[@id='Country']")
	private WebElement txtCountry;
	@FindBy(xpath = "//input[@placeholder='State/Province']")
	private WebElement dropCountry;
	@FindBy(xpath = "//button[contains(text(),'Save')]")
	private WebElement btnSave;
	@FindBy(xpath = "//button[contains(text(),'Cancel')]")
	private WebElement btnCancel;
	@FindBy(xpath = "//button[contains(text(),'Close')]")
	private WebElement btnClose;
	@FindBy(xpath = "//span[text()='Facility Name']//parent::div//following-sibling::div")
	private WebElement facilityNameDetails;
	@FindBy(xpath = "//span[text()='Facility Code']//parent::div//following-sibling::div")
	private WebElement facilityCodeDetails;
	@FindBy(xpath = "//span[text()='Country']//parent::div//following-sibling::div")
	private WebElement facilityCOuntryDetails;
	@FindBy(xpath = "//p[text()='Emission Factors Dataset']")
	private WebElement emissionFcatorDataSetPannel;
	@FindBy(xpath = "//div[contains(@class,'MuiDrawer-pape')]/div//*[local-name() = 'svg']")
	private WebElement weHamburgerMenu;
	@FindBy(xpath = "//div[text()='Facility Details']")
	private WebElement lblfcailityRHP;
	String createdFaclity;
	WebElement firstRow;

	public void validate_Add_Admin_FacilitiesInPulsESG() {
		try {
			clickOn(btnCreateFacility, "Create New Facility");
			verifyIfElementPresent(lblAddFacility, "lblAddFacility", "lblAddFacility");
			String randomNumber1 = date.toString().replaceAll(" ", "").replaceAll("[a-z]", "");
			/*-------generating random date for facility name---------*/
			String somValue = randomNumber1.replaceAll("[A-Z]", "");
			String[] spliting = somValue.split(":");
			String ran = "ABC";
			String randomFacilityName = ran.concat(spliting[2]);
			enterText(txtFacilityName, "FacilityName", randomFacilityName);
			/*-----------generating random no for facility code--------*/
			int rndNo = rnd.nextInt(1000);
			String rndFacilityCode = Integer.toString(rndNo);
			enterText(txtFacilityCode, "FacilityCode", rndFacilityCode);
			sleep(5000);
			enterText(txtCountry, "CountryName", data.get("CountryName"));
			WebElement weCountry = driver.findElement(By.xpath("//li[text()='" + data.get("CountryName") + "']"));
			clickOn(weCountry, "CountryName");
			sleep(2000);
			clickOn(btnSave, "Save New Facility");
			/*-------VERIFY TOAST MESSAGE-------*/
			By toast = By.xpath("//div[text()='Facility added successfully']");
			if (isElementPresent(toast)) {
				passed("Successfully displayed toast message - Facility added successfully");
			} else {
				failed(driver, "Failed to display toast message");
			}
			clickOn(btnClose, "Close Activity Details Button");
			/*-------VERIFYING VIEWING PANNEL---------*/
			createdFaclity = "//div[text()='" + randomFacilityName + "']";
			clickOnElementWithDynamicXpath(createdFaclity, "clicked created facility");
			if (verifyIfElementPresent(lblfcailityRHP, "lblfcailityRHP", "lblfcailityRHP")) {
				passed("facility detail page displayed");
			} else {
				failed(driver, "facility details page not displayed");
			}
			/*------------FACILITY DETAIL PANNEL-----------*/
			if (facilityNameDetails.getText().equals(randomFacilityName)) {
				passed("facility name inputed correctly");
			} else {
				failed(driver, "facility name inputed incorrectly");
			}
			if (facilityCodeDetails.getText().equals(rndFacilityCode)) {
				passed("facility code inputed correctly");
			} else {
				failed(driver, "facility code inputed incorrectly");
			}
			if (facilityCOuntryDetails.getText().equals(data.get("CountryName"))) {
				passed("Country details inputed correctly");
			} else {
				failed(driver, "Country detail inputed incorrectly");
			}
			clickOn(btnClose, "closed RHP");
			/*----TOP MOST ROW----*/
			firstRow = driver.findElement(By.xpath(createdFaclity));
			String row_No = firstRow.getAttribute("aria-colindex");
			try {
				if (Integer.parseInt(row_No) == 0) {
					passed("created facility is top most as it at " + (Integer.parseInt(row_No) + 1));
				} else {
					failed(driver, "created facility is not at top most beacuse it is at" + row_No);
				}
			} catch (NumberFormatException e) {
				failed(driver, "Exception caught" + e.getMessage());
			}
			/*--------FACILITY ADDED, AVAILBLE WITHOUT REFRESH------------*/
			if (firstRow.isDisplayed()) {
				passed("facility added available without refresh");
			} else {
				failed(driver, "Facility not added");
			}
			/*----------------------*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validate_Edit_Save_Cancel_Admin_FacilitiesInPulsESG() {
		try {
			clickOn(btnCreateFacility, "Create New Facility");
			verifyIfElementPresent(lblAddFacility, "lblAddFacility", "lblAddFacility");
			String randomNumber1 = date.toString().replaceAll(" ", "").replaceAll("[a-z]", "");
			/*-------generating random date for facility name---------*/
			String somValue = randomNumber1.replaceAll("[A-Z]", "");
			String[] spliting = somValue.split(":");
			String ran = "ABC";
			String randomFacilityName = ran.concat(spliting[2]);
			enterText(txtFacilityName, "FacilityName", randomFacilityName);
			/*-----------generating random no for facility code--------*/
			int rndNo = rnd.nextInt(1000);
			String rndFacilityCode = Integer.toString(rndNo);
			enterText(txtFacilityCode, "FacilityCode", rndFacilityCode);
			sleep(5000);
			/*------selecting country--------*/
			enterText(txtCountry, "CountryName", data.get("CountryName"));
			WebElement weCountry = driver.findElement(By.xpath("//li[text()='" + data.get("CountryName") + "']"));
			clickOn(weCountry, "CountryName");
			sleep(2000);
			/*------ADD-CANCEL BUTTON------*/
			clickOn(btnCancel, "cancel add acitvity");
			String qas = "//div[text()='" + randomFacilityName + "']";
			By frstRecord = By.xpath(qas);
			if (!isElementPresent(frstRecord)) {
				passed("Fcaility not get created after clicking cancel button");
			} else {
				failed(driver, "Fcaility got created after clicking cancel button");
			}
			/*--------EDIT, SAVE--------------*/
			List<WebElement> ew = driver.findElements(By.xpath("//div[@ref='eContainer']//div[@role='row']"));
			for (int i = 0; i < ew.size(); i++) {
				if (i == 0) {
					ew.get(i).click();
					break;
				}
			}
			WebElement btnedit = driver.findElement(By.xpath("//*[@type='submit']"));
			clickOn(btnedit, "edit button");
			int rndGnrt = rnd.nextInt(2000);
			String rndgnrtFacilityCode = Integer.toString(rndGnrt);
			Actions action = new Actions(driver);
			action.moveToElement(txtFacilityCode).doubleClick().perform();
			enterText(txtFacilityCode, "FacilityCode", rndgnrtFacilityCode);
			sleep(3000);
			clickOn(btnSave, "save after editing");
			By verifyTostMssg = By.xpath("//div[text()='Facility updated successfully']");
			if (isElementPresent(verifyTostMssg)) {
				passed("saved successfully after editing");
			} else {
				failed(driver, "failed to update facilty");
			}
			if (facilityCodeDetails.getText().equals(rndgnrtFacilityCode)) {
				passed("facility code inputed correctly");
			} else {
				failed(driver, "facility code inputed incorrectly");
			}
			/*--------------EDIT, CANCEL------------*/
			// clickOn(firstRow, "clicked created activity");
			clickOn(btnedit, "edit button");
			action.moveToElement(txtFacilityCode).doubleClick().perform();
			int rndGnrt1 = rnd.nextInt(2000);
			String rndgnrtFacilityCode1 = Integer.toString(rndGnrt1);
			enterText(txtFacilityCode, "FacilityCode", rndgnrtFacilityCode1);
			sleep(3000);
			clickOn(btnCancel, "cancel edit acitvity");
			By re = By.xpath("//div[text()='Edit Facility']");
			if (!isElementPresent(re)) {
				passed("edit facility pannel closed");
			} else {
				failed(driver, "edit facility pannel not closed");
			}
			clickOn(btnClose, "close button");
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(btnCreateFacility);
			if (isElementPresent(btnCreateFacility)) {
				passed("User Successfully Navigated To Admin_Facilities Page");
			} else {
				failed(driver, "Failed To Navigate To Admin_Facilities Page");
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
}
