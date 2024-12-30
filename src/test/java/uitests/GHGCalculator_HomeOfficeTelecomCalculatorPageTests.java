package uitests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.GHGCalculatorsPage;
import pages.HomePage;
import pages.MenuBarPage;
import utilities.Common;
import utilities.Constants;
import utilities.Data;
import utilities.GlobalKeys;

public class GHGCalculator_HomeOfficeTelecomCalculatorPageTests extends Common {
	private TestBase TestBase;
	private HomePage HomePage;
	private MenuBarPage MenuBarPage;
	private pages.SignInPage SignInPage;
	private GHGCalculatorsPage gHGCalculatorsPage;
	private pages.calculators.HomeOfficeTelecommutingCalculatorPage HomeOfficeTelecommutingCalculatorPage;
	public Data data;
	public ArrayList<String> datasets;

	@BeforeMethod
	public void testStart(Method method) {
		String name = method.getName();
		String testdataName = GlobalKeys.configData.get("TestsType");
		String testdataTypeName = GlobalKeys.configData.get("TestDataType");
		if (testdataTypeName.equalsIgnoreCase(Constants.SigngleSetData)) {
			if (testdataName.equalsIgnoreCase(Constants.UITests)) {
				data = new Data(Constants.UIExcelDataFile);
			} else if (testdataName.equalsIgnoreCase(Constants.APITests)) {
				data = new Data(Constants.APIExcelDataFile);
			}
			datasets = data.getDataSets(name);
		}
	}

	

	@Test
	public void TC017_ValidateAddActivityforHomeOfficeTelecommuting_InScope3_7Calculator() {
		String strName = new Exception().getStackTrace()[0].getMethodName();
		data = new Data(Constants.HomeOfficeTelecommuting);
		datasets = data.getDataSets();
		data.setColIndex();
		List<String> logInDetails = new ArrayList();
		for (String dataset : datasets) {
			data.setIndex_Multiple(dataset);
			datasetStart(strName + dataset);
			if (!logInDetails.contains(data.get("UserName"))) {
				logInDetails.add(data.get("UserName"));
				SignInPage = TestBase.setUp(data);
				HomePage = SignInPage.SignInToPulsEsGApp();
				MenuBarPage = HomePage.returnMenuPage();
				MenuBarPage.clickOnHamburgerMenu();
				gHGCalculatorsPage = MenuBarPage.clickOnGHGCalculatorsMenu();
				gHGCalculatorsPage.clickOnGHGEmissionsSetup();
				gHGCalculatorsPage.clickOnCarbonManagementNavigationMenu();
				HomeOfficeTelecommutingCalculatorPage = gHGCalculatorsPage.clickOnScope3_7_Home_Office_Telecommuting();
				gHGCalculatorsPage.calculateGHGEmissionBeforeActivity();
			}
			if (data.get("Edit").equals("YES")) {
				gHGCalculatorsPage.calculateGHGEmissionBeforeActivity();
				gHGCalculatorsPage.clickOnAddedActivity();
				gHGCalculatorsPage.clickOnEditButtonInActivityDetails_1();
				HomeOfficeTelecommutingCalculatorPage.EditActivityScope3_7Emissions();
			} else {
				if(data.get("gwp year").equals("AR6")) {
					gHGCalculatorsPage.clickOnGHGEmissionsSetup();
					gHGCalculatorsPage.clickOnCarbonManagementNavigationMenu();
					gHGCalculatorsPage.clickOnScope3_7_Home_Office_Telecommuting();
					gHGCalculatorsPage.calculateGHGEmissionBeforeActivity();
					gHGCalculatorsPage.clickOnAddActivity();
					gHGCalculatorsPage.verifyAddLabelRHP();
					HomeOfficeTelecommutingCalculatorPage.EditActivityScope3_7Emissions();
				}
				else {		
					gHGCalculatorsPage.clickOnAddActivity();
					gHGCalculatorsPage.verifyAddLabelRHP();
					HomeOfficeTelecommutingCalculatorPage.EditActivityScope3_7Emissions();
				}
			}
					gHGCalculatorsPage.clickOnAddedActivity();
					HomeOfficeTelecommutingCalculatorPage.validateTOTALCO2EforHomeOffice();
					HomeOfficeTelecommutingCalculatorPage.ValidateActivityDetailsInViewActivityScope3_7();
					gHGCalculatorsPage.VerifyEvidence();
					gHGCalculatorsPage.ValidateEvidenceDetails();
					gHGCalculatorsPage.validateAuditLogForAllCalc();
					gHGCalculatorsPage.clickOnCloseInActivityDetails();
					gHGCalculatorsPage.clickOnGenerateButtonAfterActivity();
					datasetEnd();
				}
		MenuBarPage.logOut();
		TestBase.tearDown();
	}

}
