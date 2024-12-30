package uitests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CalculatorPage;
import pages.CatalogPage;
import pages.GHGCalculatorsPage;
import pages.GoalsPage;
import pages.HomePage;
import pages.MenuBarPage;
import utilities.Common;
import utilities.Constants;
import utilities.Data;
import utilities.GlobalKeys;
import utilities.GlobalVariables;

public class GHGCalculatorEmployeeCommutingCalculatorPageTests extends Common {
	private TestBase TestBase;
	private HomePage HomePage;
	private MenuBarPage MenuBarPage;
	private GoalsPage GoalsPage;
	private CatalogPage CatalogsPage;
	private pages.SignInPage SignInPage;
	private GHGCalculatorsPage gHGCalculatorsPage;
	private CalculatorPage CalculatorPage;
	private pages.calculators.EmployeeCommutingCalculatorPage employeeCommutingCalculatorPage;
	public Data data;
	public ArrayList<String> datasets;

	@BeforeMethod
	public void testStart(Method method) {
		String name = method.getName();
		String testdataName = GlobalKeys.configData.get("TestsType");
		String testdataTypeName = GlobalKeys.configData.get("TestDataType");
		if (testdataTypeName.equalsIgnoreCase(Constants.SigngleSetData)) {
			if (testdataName.equalsIgnoreCase(Constants.UITests)) {
				data = new Data("TestData");
			} else if (testdataName.equalsIgnoreCase(Constants.APITests)) {
				data = new Data("APITestData");
			}
			datasets = data.getDataSets(name);
		}
	}

	@Test
	public void TC016_ValidateAddActivitiesForEmployeeCommutingCalculator() {
		String strName = new Exception().getStackTrace()[0].getMethodName();
		data = new Data(Constants.EmployeeCommutingCalculator);
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
				employeeCommutingCalculatorPage = gHGCalculatorsPage.clickOnEmployeeCommutingCalculator();
				gHGCalculatorsPage.selectFacilityFromOrgViewScreen(data.get("Facility Name"));
				gHGCalculatorsPage.calculateGHGEmissionBeforeActivity();
			}
			if (data.get("Edit").equals("YES")) {
				gHGCalculatorsPage.calculateGHGEmissionBeforeActivity();
				gHGCalculatorsPage.clickOnAddedActivity();
				gHGCalculatorsPage.clickOnEditButtonInActivityDetails_1();
				employeeCommutingCalculatorPage.addActivityDetailsForEmployeeCommutingCalculatorInActivityDetailsPannel();
			} else {
				if (data.get("gwp year").equals("AR6")) {
					gHGCalculatorsPage.clickOnGHGEmissionsSetup();
					gHGCalculatorsPage.clickOnEmployeeCommutingCalculator();
					gHGCalculatorsPage.selectFacilityFromOrgViewScreen(data.get("Facility Name"));
					gHGCalculatorsPage.calculateGHGEmissionBeforeActivity();
					gHGCalculatorsPage.clickOnAddActivity();
					gHGCalculatorsPage.verifyAddLabelRHP();
					employeeCommutingCalculatorPage.addActivityDetailsForEmployeeCommutingCalculatorInActivityDetailsPannel();
				}
					 else {
					gHGCalculatorsPage.clickOnAddActivity();
					gHGCalculatorsPage.verifyAddLabelRHP();
					employeeCommutingCalculatorPage.addActivityDetailsForEmployeeCommutingCalculatorInActivityDetailsPannel();

				}
			}
			gHGCalculatorsPage.clickOnAddedActivity();
			employeeCommutingCalculatorPage.validateTOTALCO2EforEmployeeCommuting();
			employeeCommutingCalculatorPage.validateActivityDetailsInViewActivityForEmployeeCommutingCalculator();
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
