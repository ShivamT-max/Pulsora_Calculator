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
import pages.calculators.WasteGeneratedInOperationsCalculatorPage;
import utilities.Common;
import utilities.Constants;
import utilities.Data;
import utilities.GlobalKeys;

public class GHGCalculator_WasteGeneratedInOperationsCalculatorPageTests extends Common {
	private TestBase TestBase;
	private HomePage HomePage;
	private MenuBarPage MenuBarPage;
	private pages.SignInPage SignInPage;
	private GHGCalculatorsPage gHGCalculatorsPage;
	private WasteGeneratedInOperationsCalculatorPage WasteGeneratedInOperationsCalculatorPage;
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
	public void TC012_ValidateAddActivitiesForWastegeneratedInOperations() {
		String strName = new Exception().getStackTrace()[0].getMethodName();
		data = new Data(Constants.WasteGeneratedInOpsCalculator);
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
				WasteGeneratedInOperationsCalculatorPage = gHGCalculatorsPage
						.clickOnWasteGeneratedInOperationsCalculator();
				gHGCalculatorsPage.selectFacilityFromOrgViewScreen(data.get("Facility Name"));
			}
			gHGCalculatorsPage.calculateGHGEmissionBefore();
			if(data.get("Edit").equals("YES")) {
				gHGCalculatorsPage.clickOnActivityInActivitiesGridMultipleTiffany();
				gHGCalculatorsPage.clickOnEditButtonInActivityDetails_1();
			}else {
				gHGCalculatorsPage.clickOnAddActivity();
				gHGCalculatorsPage.verifyAddLabelRHP();
			}
			WasteGeneratedInOperationsCalculatorPage
			.addActivityDetailsForWasteGeneratedInOperationsInActivityDetailsPannel();			
			gHGCalculatorsPage.selectPeriodToAll();
			gHGCalculatorsPage.clickOnAddedActivity();
			WasteGeneratedInOperationsCalculatorPage.validateTOTALCO2EforWastegenerated();
			WasteGeneratedInOperationsCalculatorPage
					.validateAddActivityDetailsInViewActivityForWasteGeneratedOprtionsCalculator();
	//		gHGCalculatorsPage.validateGlobalWarmingPotentialvalues();
			gHGCalculatorsPage.extractTco2Value();
			gHGCalculatorsPage.collectGHGEmissionAfter();
			gHGCalculatorsPage.VerifyEvidence();
			gHGCalculatorsPage.ValidateEvidenceDetails();
			gHGCalculatorsPage.validateAuditLogForAllCalc();
			gHGCalculatorsPage.clickOnCloseInActivityDetails();
			gHGCalculatorsPage.clickOnGenerateButtonAlternate1();
			datasetEnd();
		}
		TestBase.tearDown();
	}

}
