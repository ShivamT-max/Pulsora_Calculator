package uitests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CatalogPage;
import pages.GHGCalculatorsPage;
import pages.GoalsPage;
import pages.HomePage;
import pages.MenuBarPage;
import pages.calculators.UpstreamLeasedAssetsGHGCalculatorPage;
import utilities.Common;
import utilities.Constants;
import utilities.Data;
import utilities.GlobalKeys;

public class GHGCalculator_DownstreamLeasedAssetsCalculatorPageTests extends Common {
	private TestBase testBase;
	private HomePage HomePage;
	private MenuBarPage MenuBarPage;
	private GoalsPage GoalsPage;
	private CatalogPage CatalogsPage;
	private pages.SignInPage SignInPage;
	private GHGCalculatorsPage gHGCalculatorsPage;
	private pages.CalculatorPage CalculatorPage;
	private pages.calculators.DownStreamLeasedAssetsGHGCalculatorPage downStreamLeasedAssetsGHGCalculatorPage;
	private UpstreamLeasedAssetsGHGCalculatorPage upstreamLeasedAssetsGHGCalculatorPage;
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
	public void TC024_ValidateAddActivityfor3_13DownstreamLeasedAssets() {
		try {
			GHGCalculatorsPage.printNewTestCaseMessage("DLA Calculator (ADD && EDIT) ");
			String strName = new Exception().getStackTrace()[0].getMethodName();
			data = new Data(Constants.DownStreamLeasedAsset);
			datasets = data.getDataSets();
			data.setColIndex();
			//List<String> logInDetails = new ArrayList();
			for (String dataset : datasets) {
				data.setIndex_Multiple(dataset);
				datasetStart(strName + dataset);
				if (!CarbonManagementPageTests.logInDetails.contains(data.get("UserName"))) {
					CarbonManagementPageTests.logInDetails.add(data.get("UserName"));
					SignInPage = TestBase.setUp(data);
					gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
					gHGCalculatorsPage.stepsToNavigateGHGCalculators();
				}
				gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
				String[] ActivityDetails = Constants.activityDetailFieldsDLA;
				String CalcName = "DLA";
				String ActivityAmount = "";
				gHGCalculatorsPage.Add$Edit$OverLapActivitiesFor_Calculators(ActivityDetails,CalcName,ActivityAmount);
			}
			datasetEnd();
		} catch (Exception e) {
			gHGCalculatorsPage.clickOnCarbonManagementNavigationMenu();
			e.printStackTrace();
		}
	}
	
	// ---------------------CEF-----------------------------------------------
		//@Test
//		public void TC024_ValidateAddCustomEmissionFactorDownstreamLeasedAssets() {
//			try {
//				GHGCalculatorsPage.printNewTestCaseMessage("DLA ADDCEF");
//				String strName = new Exception().getStackTrace()[0].getMethodName();
//				data = new Data("TestData");
//				datasets = data.getDataSets(strName);
//				data.setColIndex(strName);
//				for (String dataset : datasets) {
//					data.setIndex(dataset);
//					datasetStart(dataset);
//					List<String> userName = CarbonManagementPageTests.logInDetails;
//					if (!userName.contains(data.get("UserName"))) {
//						userName.add(data.get("UserName"));
//						SignInPage = TestBase.setUp(data);
//						gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
//						gHGCalculatorsPage.stepsToNavigateGHGCalculators();
//					}
//					gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
//					gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailFieldNamesDLA);
//	                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.activityDetailFieldsDLA,data.get("Activity Amount"));
//				    datasetEnd();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		@Test
//		public void TC024_ValidateEditCustomEmissionFactorDownstreamLeasedAssets() {
//			try {
//				GHGCalculatorsPage.printNewTestCaseMessage("DLA EditCEF");
//				String strName = new Exception().getStackTrace()[0].getMethodName();
//				data.setColIndex(strName);
//				for (String dataset : datasets) {
//					data.setIndex(dataset);
//					datasetStart(dataset);
//					List<String> userName = CarbonManagementPageTests.logInDetails;
//					if (!userName.contains(data.get("UserName"))) {
//						userName.add(data.get("UserName"));
//						SignInPage = TestBase.setUp(data);
//						gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
//						gHGCalculatorsPage.stepsToNavigateGHGCalculators();
//					}
//					gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
//					gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailFieldNamesDLA);
//	                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.activityDetailFieldsDLA,data.get("Activity Amount"));
//				    datasetEnd();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	
	
	
//	@Test
//	public void TC024_ValidateEditActivityfor3_13DownstreamLeasedAssets() {
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.DownStreamLeasedAsset);
//			datasets = data.getDataSets();
//			data.setColIndex();
//			List<String> logInDetails = new ArrayList();
//			for (String dataset : datasets) {
//				data.setIndex_Multiple(dataset);
//				datasetStart(strName + dataset);
//				if (!logInDetails.contains(data.get("UserName"))) {
//					logInDetails.add(data.get("UserName"));
//					SignInPage = testBase.setUp(data);
//					HomePage = SignInPage.SignInToPulsEsGApp();
//					MenuBarPage = HomePage.returnMenuPage();
//					MenuBarPage.clickOnHamburgerMenu();
//					gHGCalculatorsPage = MenuBarPage.clickOnGHGCalculatorsMenu();
//					downStreamLeasedAssetsGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_13DownstreamLeasedAsset();
//				}
//				//gHGCalculatorsPage.selectPeriodToAll();
//				gHGCalculatorsPage.clickOnActivityInActivitiesGridMultiple();
//				gHGCalculatorsPage.clickOnEditButtonInActivityDetails_1();
//				downStreamLeasedAssetsGHGCalculatorPage.EditActivityScope3_13DownstreamLeasedAssets();
//				gHGCalculatorsPage.verifyEditActivityUpdatedToastMessage();
//				downStreamLeasedAssetsGHGCalculatorPage
//						.ValidateActivityDetailsInViewActivityScope3_13DownStreamLeasedAssets();
//				gHGCalculatorsPage.clickOnCloseInActivityDetails();
//				gHGCalculatorsPage.clickOnGenerateButtonAlternate();
//				datasetEnd();
//			}
//			MenuBarPage.logOut();
//			testBase.tearDown();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	// '''''''''''''''Overlap'''''''''''''''''''
//
//	@Test
//	public void TC024_ValidateAddActivityOverlapfor3_8DownLeasedAssets()  {
//		try {
//			GHGCalculatorsPage.printNewTestCaseMessage("DLA Calculator (OVERLAP) ");
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.DownStreamLeasedAsset);
//			datasets = data.getDataSets();
//			data.setColIndex();
//			List<String> logInDetails = new ArrayList();
//			for (String dataset : datasets) {
//				data.setIndex_Multiple(dataset);
//				datasetStart(strName + dataset);
//				if (!CarbonManagementPageTests.logInDetails.contains(data.get("UserName"))) {
//					CarbonManagementPageTests.logInDetails.add(data.get("UserName"));
//					SignInPage = testBase.setUp(data);
//					HomePage = SignInPage.SignInToPulsEsGApp();
//					MenuBarPage = HomePage.returnMenuPage();
//					MenuBarPage.clickOnHamburgerMenu();
//					gHGCalculatorsPage = MenuBarPage.clickOnGHGCalculatorsMenu();
////				downStreamLeasedAssetsGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_13DownstreamLeasedAsset();
////				gHGCalculatorsPage.SelectPeriod2023();
////				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				}
//				gHGCalculatorsPage = testBase.navigateToGHGCalculatorPage(data);
//				downStreamLeasedAssetsGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_13DownstreamLeasedAsset();
//				gHGCalculatorsPage.SelectPeriod2022();
//				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				gHGCalculatorsPage.clickOnAddActivityscope1();
//				downStreamLeasedAssetsGHGCalculatorPage.EditActivityScope3_13DownstreamLeasedAssets();
//				gHGCalculatorsPage.extractTco2ValueULADLA();
//				gHGCalculatorsPage.clickOnCloseInActivityDetails();
//				gHGCalculatorsPage.collectGHGEmissionAfter();
//			}
//			gHGCalculatorsPage.clickOnGenerateButton();
//			gHGCalculatorsPage.clickOnCarbonManagementNavigationMenu();
//			if (!CarbonManagementPageTests.logInDetails.contains(data.get("UserName"))) {
//				CarbonManagementPageTests.logInDetails.add(data.get("UserName"));
//				MenuBarPage.logOut();
//				testBase.tearDown();
//			}
//			datasetEnd();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	
}
