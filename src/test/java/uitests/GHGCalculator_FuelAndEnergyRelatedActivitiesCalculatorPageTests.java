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

public class GHGCalculator_FuelAndEnergyRelatedActivitiesCalculatorPageTests extends Common {
	private TestBase TestBase;
	private HomePage HomePage;
	private MenuBarPage MenuBarPage;
	private pages.SignInPage SignInPage;
	private GHGCalculatorsPage gHGCalculatorsPage;
	private pages.calculators.FuelandEnergyRelatedActivitiesCalculatorPage FuelandEnergyRelatedActivitiesCalculatorPage;
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
	public void TC009_ValidateAddActivityforFuelandEnergyRelatedActivities_InScope3_3Calculator() {
		String strName = new Exception().getStackTrace()[0].getMethodName();
		data = new Data(Constants.FuelandEnergyRelatedActivit);
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
				FuelandEnergyRelatedActivitiesCalculatorPage = gHGCalculatorsPage
						.clickOnScope3_3_Fuel_and_Energy_Related_Activities();
				gHGCalculatorsPage.selectFacilityFromOrgViewScreen(data.get("Facility Name"));
				gHGCalculatorsPage.calculateGHGEmissionBeforeActivity();
			}
			if (data.get("Edit").equals("YES")) {
				gHGCalculatorsPage.calculateGHGEmissionBeforeActivity();
				gHGCalculatorsPage.clickOnAddedActivity();
				gHGCalculatorsPage.clickOnEditButtonInActivityDetails_1();
				FuelandEnergyRelatedActivitiesCalculatorPage.EditActivityScope3_3Emissions();
			} else {
				if (data.get("gwp year").equals("AR6")) {
					gHGCalculatorsPage.clickOnGHGEmissionsSetup();
					gHGCalculatorsPage.clickOnCarbonManagementNavigationMenu();
					gHGCalculatorsPage.clickOnScope3_3_Fuel_and_Energy_Related_Activities();				
					gHGCalculatorsPage.selectFacilityFromOrgViewScreen(data.get("Facility Name"));
					gHGCalculatorsPage.calculateGHGEmissionBeforeActivity();
					gHGCalculatorsPage.clickOnAddActivity();
					gHGCalculatorsPage.verifyAddLabelRHP();
					FuelandEnergyRelatedActivitiesCalculatorPage.EditActivityScope3_3Emissions();
				} else {
					gHGCalculatorsPage.clickOnAddActivity();
					gHGCalculatorsPage.verifyAddLabelRHP();
					FuelandEnergyRelatedActivitiesCalculatorPage.EditActivityScope3_3Emissions();
				}
			}
			gHGCalculatorsPage.clickOnAddedActivity();
			FuelandEnergyRelatedActivitiesCalculatorPage.validateTotalCO2EforFuel();
			FuelandEnergyRelatedActivitiesCalculatorPage.ValidateActivityDetailsInViewActivityScope3_3();
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
