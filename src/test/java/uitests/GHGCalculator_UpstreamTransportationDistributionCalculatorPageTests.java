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
import pages.calculators.FuelandEnergyRelatedActivitiesCalculatorPage;
import utilities.Common;
import utilities.Constants;
import utilities.Data;
import utilities.GlobalKeys;

public class GHGCalculator_UpstreamTransportationDistributionCalculatorPageTests extends Common {
	private TestBase TestBase;
	private HomePage HomePage;
	private MenuBarPage MenuBarPage;
	private pages.SignInPage SignInPage;
	private GHGCalculatorsPage gHGCalculatorsPage;
	private pages.calculators.UpstreamTransportationandDistributionCalculatorPage UpstreamTransportationandDistributionCalculatorPage;
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
	public void TC010_ValidateAddActivityforUpstreamTransportationandDistribution_InScope3_4Calculator() {
		String strName = new Exception().getStackTrace()[0].getMethodName();
		data = new Data(Constants.UpstreamWeight_DistanceMethod);
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
				UpstreamTransportationandDistributionCalculatorPage = gHGCalculatorsPage
						.clickOnScope3_4_Upstream_Transportation_and_Distribution();
			}
			gHGCalculatorsPage.calculateGHGEmissionBefore();
			if(data.get("Edit").equals("YES")) {
				gHGCalculatorsPage.clickOnActivityInActivitiesGridMultipleTiffany();
				gHGCalculatorsPage.clickOnEditButtonInActivityDetails_1();
			}else {
				gHGCalculatorsPage.clickOnAddActivity();
				gHGCalculatorsPage.verifyAddLabelRHP();
			}
		//	gHGCalculatorsPage.clickOnAddActivity();
			UpstreamTransportationandDistributionCalculatorPage.EditActivityScope3_4Emissions();
			//gHGCalculatorsPage.verifyAddActivityUpdatedToastMessage();
			gHGCalculatorsPage.selectPeriodToAll();
			gHGCalculatorsPage.clickOnAddedActivity();
			UpstreamTransportationandDistributionCalculatorPage.validateTotalCO2EforUpstream();
			UpstreamTransportationandDistributionCalculatorPage.ValidateActivityDetailsInViewAddActivityScope3_4();
			gHGCalculatorsPage.extractTco2Value();
			gHGCalculatorsPage.VerifyEvidence();
			gHGCalculatorsPage.ValidateEvidenceDetails();
			gHGCalculatorsPage.validateAuditLogForAllCalc();
			gHGCalculatorsPage.clickOnCloseInActivityDetails();
			gHGCalculatorsPage.clickOnGenerateButton();
			gHGCalculatorsPage.clickOnGenerateButtonAlternate();
			datasetEnd();
		}
//		 MenuBarPage.logOut();
		TestBase.tearDown();
	}

	


}
