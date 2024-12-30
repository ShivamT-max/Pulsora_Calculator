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
import utilities.Common;
import utilities.Constants;
import utilities.Data;
import utilities.GlobalKeys;

public class GHGCalculatorCapitalGoodsAveragedataPageTests extends Common {
	private TestBase testBase;
	private HomePage homePage;
	private MenuBarPage MenuBarPage;
	private GoalsPage GoalsPage;
	private CatalogPage CatalogsPage;
	private pages.SignInPage SignInPage;
	private GHGCalculatorsPage gHGCalculatorsPage;
	private pages.CalculatorPage CalculatorPage;
	private pages.calculators.CapitalGoodsGHGCalculatorPage capitalGoodsGHGCalculatorPage;
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
	public void TC007_ValidateAddActivityforCapitalGoodsandSerAveragedataInScope3_2Calculator() {
		try {
			GHGCalculatorsPage.printNewTestCaseMessage("Capital Goods Average Calculator (ADD && EDIT) ");
			String strName = new Exception().getStackTrace()[0].getMethodName();
			data = new Data(Constants.CapitalGoodsAvreageBased);
			datasets = data.getDataSets();
			data.setColIndex();
			for (String dataset : datasets) {
				data.setIndex_Multiple(dataset);
				datasetStart(strName + dataset);
				List<String> userName = CarbonManagementPageTests.logInDetails;
				if (!userName.contains(data.get("UserName"))) {
					userName.add(data.get("UserName"));
					SignInPage = TestBase.setUp(data);
					gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
					gHGCalculatorsPage.stepsToNavigateGHGCalculators();
				}
				gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
				String[] ActivityDetails = Constants.activityDetailsCapitalGoodsAvg;
				String CalcName = "Average Data Method";
				String ActivityAmount = data.get("Quantity of Goods Purchased");
				gHGCalculatorsPage.Add$Edit$OverLapActivitiesFor_Calculators(ActivityDetails,CalcName,ActivityAmount);
			}
			datasetEnd();
		} catch (Exception e) {
			capitalGoodsGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_2SpendBased();
			e.printStackTrace();
		}
	}
	
	@Test
	public void TC008_ValidateAddActivityforCapitalGoodsandSerInScope3_2SpendBasedCalculator() {
		try {
			GHGCalculatorsPage.printNewTestCaseMessage("Capital Goods Spend Calculator (ADD && EDIT) ");
			String strName = new Exception().getStackTrace()[0].getMethodName();
			data = new Data(Constants.CapitalGoodsSpendBased);
			datasets = data.getDataSets();
			data.setColIndex();
			for (String dataset : datasets) {
				data.setIndex_Multiple(dataset);
				datasetStart(strName + dataset);
				List<String> userName = CarbonManagementPageTests.logInDetails;
				if (!userName.contains(data.get("UserName"))) {
					userName.add(data.get("UserName"));
					SignInPage = TestBase.setUp(data);
					gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
			    	gHGCalculatorsPage.stepsToNavigateGHGCalculators();
					//capitalGoodsGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_2CapitalGoods();
				} 
				gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
				String[] ActivityDetails = Constants.activityDetailsCapitalGoodsSpend;
				String CalcName = "spend";
				String ActivityAmount = data.get("Amount Spent");
				gHGCalculatorsPage.Add$Edit$OverLapActivitiesFor_Calculators(ActivityDetails,CalcName,ActivityAmount);
			}
			datasetEnd();
		} catch (Exception e) {
			gHGCalculatorsPage.clickOnCarbonManagementNavigationMenu();
			e.printStackTrace();
		}
	}
	
	
	// ---------------------------CEF-------------------------------------------------
//@Test
//		public void TC007_ValidateAddCustomEmissionFactorInActivityCapitalGoodsAverageScope3_2() {
//			try {
//				GHGCalculatorsPage.printNewTestCaseMessage("Capital Goods Average ADDCEF");
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
//					gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailsCapitlAvg);
//					String Amount = data.get("Quantity of Goods Purchased");
//	                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.activityDetailsCapitalGoodsAvg,Amount);
//					datasetEnd();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		@Test
//		public void TC007_ValidateEditCustomEmissionFactorInActivityCapitalGoodsAverageScope3_2() {
//			try {
//				GHGCalculatorsPage.printNewTestCaseMessage("Capital Goods Average EditCEF");
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
//					gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailsCapitlAvg);
//					String Amount = data.get("Quantity of Goods Purchased");
//	                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.activityDetailsCapitalGoodsAvg,Amount);
//					datasetEnd();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		@Test
//		public void TC008_ValidateAddCustomEmissionFactorInActivityCapitalGoodsspendScope3_2() {
//			try {
//				GHGCalculatorsPage.printNewTestCaseMessage("Capital Goods Spend ADDCEF");
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
//					gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailsCapitlAvg);
//					String Amount = data.get("Amount Spent");
//	                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.activityDetailsCapitalGoodsSpend,Amount);
//				    datasetEnd();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		@Test
//		public void TC008_ValidateEditCustomEmissionFactorInActivityCapitalGoodsspendScope3_2() {
//			try {
//				GHGCalculatorsPage.printNewTestCaseMessage("Capital Goods Spend EditCEF");
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
//					gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailsCapitlAvg);
//					String Amount = data.get("Amount Spent");
//	                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.activityDetailsCapitalGoodsSpend,Amount);
//				    datasetEnd();
//				datasetEnd();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		
//		
//		
		
		
		
	
	
//	@Test
//	public void TC007_ValidateEditActivityforCapitalGoodsandSerAveragedataInScope3_2Calculator() {
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.CapitalGoodsAvreageBased);
//			datasets = data.getDataSets();
//			data.setColIndex();
//			List<String> logInDetails = new ArrayList();
//			for (String dataset : datasets) {
//				data.setIndex_Multiple(dataset);
//				datasetStart(strName + dataset);
//				if (!logInDetails.contains(data.get("UserName"))) {
//					logInDetails.add(data.get("UserName"));
//					SignInPage = testBase.setUp(data);
//					homePage = SignInPage.SignInToPulsEsGApp();
//					MenuBarPage = homePage.returnMenuPage();
//					MenuBarPage.clickOnHamburgerMenu();
//					gHGCalculatorsPage = MenuBarPage.clickOnGHGCalculatorsMenu();
//					capitalGoodsGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_2CapitalGoods();
//					// gHGCalculatorsPage.SelectPeriod();
//				}
//				gHGCalculatorsPage.clickOnActivityInActivitiesGridMultiple();
//				gHGCalculatorsPage.clickOnEditButtonInActivityDetails_1();
//				capitalGoodsGHGCalculatorPage.EditActivityScopeCapitalGoods3_2EmissionsAverageBased();
//				gHGCalculatorsPage.verifyEditActivityUpdatedToastMessage();
//				capitalGoodsGHGCalculatorPage.ValidateActivityDetailsInViewActivityScope3_2AverageBased();
//				gHGCalculatorsPage.clickOnCloseInActivityDetails();
//				gHGCalculatorsPage.clickOnGenerateButtonAlternate();
//			}
//			MenuBarPage.logOut();
//			testBase.tearDown();
//			datasetEnd();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	// ............Overlap.............
//	@Test
//	public void TC007_ValidateAddActvityOverlapCapitalGoodsInScope3_2Calculator() {
//		try {
//			GHGCalculatorsPage.printNewTestCaseMessage("Capital Goods Average Calculator (OVERLAP) ");
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.CapitalGoodsAvreageBased);
//			datasets = data.getDataSets();
//			data.setColIndex();
//			for (String dataset : datasets) {
//				data.setIndex_Multiple(dataset);
//				datasetStart(strName + dataset);
//				if (!CarbonManagementPageTests.logInDetails.contains(data.get("UserName"))) {
//					CarbonManagementPageTests.logInDetails.add(data.get("UserName"));
//					SignInPage = testBase.setUp(data);
//					homePage = SignInPage.SignInToPulsEsGApp();
//					MenuBarPage = homePage.returnMenuPage();
//					MenuBarPage.clickOnHamburgerMenu();
//					gHGCalculatorsPage = MenuBarPage.clickOnGHGCalculatorsMenu();
////				capitalGoodsGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_2CapitalGoods();
////				gHGCalculatorsPage.SelectPeriod2023();
////				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				}
//				gHGCalculatorsPage = testBase.navigateToGHGCalculatorPage(data);
//				capitalGoodsGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_2CapitalGoods();
//				gHGCalculatorsPage.SelectPeriod2022();
//				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				gHGCalculatorsPage.clickOnAddActivityButton();
//				capitalGoodsGHGCalculatorPage.EditActivityScopeCapitalGoods3_2EmissionsAverageBased();
//				gHGCalculatorsPage.extractTco2Value();
//				gHGCalculatorsPage.clickOnCloseInActivityDetails();
//				gHGCalculatorsPage.collectGHGEmissionAfter();
//			}
//			gHGCalculatorsPage.clickOnGenerateButton();
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
//
//	
//	// ------------------------Spend Based------------------------
//	@Test
//	public void TC008_ValidateEditActivityforCapitalGoodsandSerInScope3_2SpendBasedCalculator() {
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.CapitalGoodsSpendBased);
//			datasets = data.getDataSets();
//			data.setColIndex();
//			List<String> logInDetails = new ArrayList();
//			for (String dataset : datasets) {
//				data.setIndex_Multiple(dataset);
//				datasetStart(strName + dataset);
//				if (!logInDetails.contains(data.get("UserName"))) {
//					logInDetails.add(data.get("UserName"));
//					SignInPage = testBase.setUp(data);
//					homePage = SignInPage.SignInToPulsEsGApp();
//					MenuBarPage = homePage.returnMenuPage();
//					MenuBarPage.clickOnHamburgerMenu();
//					gHGCalculatorsPage = MenuBarPage.clickOnGHGCalculatorsMenu();
//					capitalGoodsGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_2CapitalGoods();
//					capitalGoodsGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_2SpendBased();
//					// gHGCalculatorsPage.SelectPeriod();
//				}
//				gHGCalculatorsPage.clickOnActivityInActivitiesGridMultiple();
//				gHGCalculatorsPage.clickOnEditButtonInActivityDetails_1();
//				capitalGoodsGHGCalculatorPage.EditActivityCapitalGoodsScope3_2SpendBasedEmissions();
//				gHGCalculatorsPage.verifyEditActivityUpdatedToastMessage();
//				capitalGoodsGHGCalculatorPage.ValidateActivityDetailsInViewActivityScope3_2SpendBased();
//				gHGCalculatorsPage.clickOnCloseInActivityDetails();
//				gHGCalculatorsPage.clickOnGenerateButtonAlternate();
//			}
//			MenuBarPage.logOut();
//			testBase.tearDown();
//			datasetEnd();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	
////--------------------Overlap-----------------------------
//
//	@Test
//	public void TC008_ValidateAddActvityOverlapCapitalGoodsInScope3_2SpenBasedCalculator() {
//		try {
//			GHGCalculatorsPage.printNewTestCaseMessage("Capital Goods Spends Calculator (OVERLAP) ");
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.CapitalGoodsSpendBased);
//			datasets = data.getDataSets();
//			data.setColIndex();
//			List<String> logInDetails = new ArrayList();
//			for (String dataset : datasets) {
//				data.setIndex_Multiple(dataset);
//				datasetStart(strName + dataset);
//				if (!CarbonManagementPageTests.logInDetails.contains(data.get("UserName"))) {
//					CarbonManagementPageTests.logInDetails.add(data.get("UserName"));
//					SignInPage = testBase.setUp(data);
//					homePage = SignInPage.SignInToPulsEsGApp();
//					MenuBarPage = homePage.returnMenuPage();
//					MenuBarPage.clickOnHamburgerMenu();
//					gHGCalculatorsPage = MenuBarPage.clickOnGHGCalculatorsMenu();
//					capitalGoodsGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_2CapitalGoods();
////				capitalGoodsGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_2SpendBased();
////				gHGCalculatorsPage.SelectPeriod2023();
////				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				}
//				gHGCalculatorsPage = testBase.navigateToGHGCalculatorPage(data);
//				capitalGoodsGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_2SpendBased();
//				gHGCalculatorsPage.SelectPeriod2022();
//				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				gHGCalculatorsPage.clickOnAddActivityButton();
//				capitalGoodsGHGCalculatorPage.EditActivityCapitalGoodsScope3_2SpendBasedEmissions();
//				gHGCalculatorsPage.extractTco2Value();
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
//	// -----------------------------CEF------------------------------------------

	
}
