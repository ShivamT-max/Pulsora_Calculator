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

public class GHGCalculator_UpstreamLeasedAssetsCalculatorPageTests extends Common {
	private TestBase testBase;
	private HomePage HomePage;
	private MenuBarPage MenuBarPage;
	private GoalsPage GoalsPage;
	private CatalogPage CatalogsPage;
	private pages.SignInPage SignInPage;
	private GHGCalculatorsPage gHGCalculatorsPage;
	private pages.CalculatorPage CalculatorPage;
	private pages.calculators.UpstreamLeasedAssetsGHGCalculatorPage upstreamLeasedAssetsGHGCalculatorPage;
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
	public void TC018_ValidateAddActivityfor3_8UpstreamLeasedAssets() {
		try {
			GHGCalculatorsPage.printNewTestCaseMessage("ULA Calculator (ADD && EDIT) ");
			String strName = new Exception().getStackTrace()[0].getMethodName();
			data = new Data(Constants.UpstreamLeasedAsset);
			datasets = data.getDataSets();
			data.setColIndex();
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
				String[] ActivityDetails = Constants.activityDetailFieldsULA;
				String CalcName = "ULA";
				String ActivityAmount = "";
				gHGCalculatorsPage.Add$Edit$OverLapActivitiesFor_Calculators(ActivityDetails,CalcName,ActivityAmount);
			}
			datasetEnd();
		} catch (Exception e) {
			gHGCalculatorsPage.clickOnCarbonManagementNavigationMenu();
			e.printStackTrace();
		}
	}
	
	

	// --------------------------------CEF---------------------------------------------------------------

//@Test
//	public void TC018_ValidateAddCustomEmissionFactorUpstreamLeasedAssets() {
//		try {
//			GHGCalculatorsPage.printNewTestCaseMessage("ULA ADDCEF");
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data("TestData");
//			datasets = data.getDataSets(strName);
//			data.setColIndex(strName);
//			for (String dataset : datasets) {
//				data.setIndex(dataset);
//				datasetStart(dataset);
//				List<String> userName = CarbonManagementPageTests.logInDetails;
//				if (!userName.contains(data.get("UserName"))) {
//					userName.add(data.get("UserName"));
//					System.out.println(data.get("gwp year"));
//					SignInPage = TestBase.setUp(data);
//					gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
//					gHGCalculatorsPage.stepsToNavigateGHGCalculators();
//				}
//				gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
//				gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailFieldNamesULA);
//                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.activityDetailFieldsULA,data.get("Activity Amount"));
//			}
//			datasetEnd();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	public void TC018_ValidateEditCustomEmissionFactorUpstreamLeasedAssets() {
//		try {
//			GHGCalculatorsPage.printNewTestCaseMessage("ULA EditCEF");
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data.setColIndex(strName);
//			for (String dataset : datasets) {
//				data.setIndex(dataset);
//				datasetStart(dataset);
//				List<String> userName = CarbonManagementPageTests.logInDetails;
//				if (!userName.contains(data.get("UserName"))) {
//					userName.add(data.get("UserName"));
//					SignInPage = TestBase.setUp(data);
//					gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
//					gHGCalculatorsPage.stepsToNavigateGHGCalculators();
//				}
//				gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
//				gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailFieldNamesULA);
//                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.activityDetailFieldsULA,data.get("Activity Amount"));
//			}
//			datasetEnd();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	
//	
	
	
//	
//	@Test
//	public void TC018_ValidateEditActivityfor3_8UpstreamLeasedAssets() {
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.UpstreamLeasedAsset);
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
//					upstreamLeasedAssetsGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_8UpstreamLeasedAsset();
//				}
//				//gHGCalculatorsPage.SelectPeriod();
//				gHGCalculatorsPage.clickOnActivityInActivitiesGridMultiple();
//				gHGCalculatorsPage.clickOnEditButtonInActivityDetails_1();
//				upstreamLeasedAssetsGHGCalculatorPage.EditActivityScope3_8UpstreamLeasedAssets();
//				gHGCalculatorsPage.verifyEditActivityUpdatedToastMessage();
//				upstreamLeasedAssetsGHGCalculatorPage.ValidateActivityDetailsInViewActivityScope3_8UpstreamLeasedAssets();
//				gHGCalculatorsPage.clickOnCloseInActivityDetails();
////			gHGCalculatorsPage.clickOnGenerateButtonAlternate();
//				datasetEnd();
//			}
//			MenuBarPage.logOut();
//			testBase.tearDown();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	// -----------------------Overlap-------------------------------------
//	@Test
//	public void TC018_ValidateAddActivityOverlapfor38UpstreamLeasedAssets(){
//		try {
//			GHGCalculatorsPage.printNewTestCaseMessage("ULA Calculator (OVERLAP) ");
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.UpstreamLeasedAsset);
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
////				upstreamLeasedAssetsGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_8UpstreamLeasedAsset();
////				gHGCalculatorsPage.SelectPeriod2023();
////				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				}
//				gHGCalculatorsPage = testBase.navigateToGHGCalculatorPage(data);
//				upstreamLeasedAssetsGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_8UpstreamLeasedAsset();
//				gHGCalculatorsPage.SelectPeriod2022();
//				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				gHGCalculatorsPage.clickOnAddActivityscope1();
//				upstreamLeasedAssetsGHGCalculatorPage.EditActivityScope3_8UpstreamLeasedAssets();
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
