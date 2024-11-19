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

public class GHGCalculatorPurchasedGoodServicesCalculatorPageTests extends Common {
	private TestBase testBase;
	private HomePage HomePage;
	private MenuBarPage MenuBarPage;
	private GoalsPage GoalsPage;
	private CatalogPage CatalogsPage;
	private pages.SignInPage SignInPage;
	private GHGCalculatorsPage gHGCalculatorsPage;
	private pages.CalculatorPage CalculatorPage;
	private pages.calculators.PurchasedGoodsAndServicesGHGCalculatorPage purchasedGoodsAndServicesGHGCalculatorPage;
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
	public void TC005_ValidateAddActivityforPurchasedGoodsandSerAveragedataInScope3_1Calculator() {
		try {
			GHGCalculatorsPage.printNewTestCaseMessage("Purchased Goods Average Calculator (ADD && EDIT) ");
			String strName = new Exception().getStackTrace()[0].getMethodName();
			data = new Data(Constants.PurchasedGoodsAndServicesAverag);
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
				String[] ActivityDetails = Constants.actPurchAvg;
				String CalcName = "PurchasedGoodsAverage";
				String ActivityAmount = data.get("Quantity of Goods Purchased");
				gHGCalculatorsPage.Add$Edit$OverLapActivitiesFor_Calculators(ActivityDetails,CalcName,ActivityAmount);
			}
			datasetEnd();
		} catch (Exception e) {
			purchasedGoodsAndServicesGHGCalculatorPage.clickOnScope3_1PurchasedGoods_Ser_SpendBased();
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void TC006_ValidateAddActivityforPurchasedGoodsandSerInScope3_1SpendBasedCalculator() {
		try {
			GHGCalculatorsPage.printNewTestCaseMessage("Purchased Goods Spend Calculator (ADD && EDIT) ");
			String strName = new Exception().getStackTrace()[0].getMethodName();
			data = new Data(Constants.PurchaseGoodsServiSpendBased);
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
				String[] ActivityDetails = Constants.actDetPurchGoodsSpend;
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
	
	// --------------------------------Custom Emission
		// Factor---------------------------

//@Test
//		public void TC005_ValidateAddCustomEmissionFactorInActivityPurchasedGoodsServicesAverageScope3_1() {
//			try {
//				GHGCalculatorsPage.printNewTestCaseMessage("Purchased Goods Average ADDCEF");
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
//						 gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
//						 gHGCalculatorsPage.stepsToNavigateGHGCalculators();
//					}
//					gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
//					gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailsPurchAverage);
//					String Amount = data.get("Quantity of Goods Purchased");
//					gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.actPurchAvg,Amount);
//					datasetEnd();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		@Test
//		public void TC005_ValidateEditCustomEmissionFactorInActivityPurchasedGoodsServicesAverageScope3_1() {
//			try {
//				GHGCalculatorsPage.printNewTestCaseMessage("Purchased Goods Average EditCEF");
//				String strName = new Exception().getStackTrace()[0].getMethodName();
//				data.setColIndex(strName);
//				for (String dataset : datasets) {
//					data.setIndex(dataset);
//					datasetStart(dataset);
//					List<String> userName = CarbonManagementPageTests.logInDetails;
//					if (!userName.contains(data.get("UserName"))) {
//						userName.add(data.get("UserName"));
//						SignInPage = TestBase.setUp(data);
//						 gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
//						 gHGCalculatorsPage.stepsToNavigateGHGCalculators();
//					}
//					gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
//					gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailsPurchAverage);
//					String Amount = data.get("Quantity of Goods Purchased");
//					gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.actPurchAvg,Amount);
//					datasetEnd();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		
//		// -----------------------------------CEF-----------------------------------
//		@Test
//		public void TC006_ValidateAddCustomEmissionFactorInActivityPurchasedGoodsServicesSpendScope3_1() {
//			try {
//				GHGCalculatorsPage.printNewTestCaseMessage("Purchased Goods Spend ADDCEF");
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
//					gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailsPurchSpend);
//					String Amount = data.get("Quantity of Goods Purchased");
//	                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.actDetPurchGoodsSpend,Amount);
//	                datasetEnd();
//			 }
//			
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//
//		@Test
//		public void TC006_ValidateEditCustomEmissionFactorInActivityPurchasedGoodsServicesSpendScope3_1() {
//			try {
//				GHGCalculatorsPage.printNewTestCaseMessage("Purchased Goods Spend EditCEF");
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
//					gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailsPurchSpend);
//					String Amount = data.get("Quantity of Goods Purchased");
//	                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.actDetPurchGoodsSpend,Amount);
//	                datasetEnd();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	
//		
//		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
//	@Test
//	public void TC005_ValidateEditActivityforPurchasedGoodsandSerAveragedataInScope3_1Calculator() {
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.PurchasedGoodsAndServicesAverag);
//			datasets = data.getDataSets();
//			data.setColIndex();
//			List<String> logInDetails = new ArrayList();
//			for (String dataset : datasets) {
//				data.setIndex_Multiple(dataset);
//				datasetStart(strName + dataset);
//				if (!logInDetails.contains(data.get("UserName"))) {
//					logInDetails.add(data.get("UserName"));
//					SignInPage = TestBase.setUp(data);
//					HomePage = SignInPage.SignInToPulsEsGApp();
//					MenuBarPage = HomePage.returnMenuPage();
//					MenuBarPage.clickOnHamburgerMenu();
//					gHGCalculatorsPage = MenuBarPage.clickOnGHGCalculatorsMenu();
//					purchasedGoodsAndServicesGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_1PurchasedGoods_Ser();
//					
//				}
//				gHGCalculatorsPage.clickOnActivityInActivitiesGridMultiple();
//				gHGCalculatorsPage.clickOnEditButtonInActivityDetails_1();
//				purchasedGoodsAndServicesGHGCalculatorPage.EditActivityScope3EmissionsScope3_1_AverageBased();
//				gHGCalculatorsPage.verifyEditActivityUpdatedToastMessage();
//				purchasedGoodsAndServicesGHGCalculatorPage.ValidateActivityDetailsInViewActivityScope3_1AverageBased();
//				gHGCalculatorsPage.calculateTCO2eValueUsing_EmissionFactorValue(data.get("Quantity of Goods Purchased"));
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
//	// ....................Overlap......................
//	@Test
//	public void TC005_ValidateAddActvityOverlapPurchasedGoodsandServicesInScope3_1Calculator() throws Exception {
//		try {
//			GHGCalculatorsPage.printNewTestCaseMessage("Purchased Goods Average Calculator (OVERLAP) ");
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.PurchasedGoodsAndServicesAverag);
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
////				purchasedGoodsAndServicesGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_1PurchasedGoods_Ser();
////				gHGCalculatorsPage.SelectPeriod2023();
////				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				}
//				gHGCalculatorsPage = testBase.navigateToGHGCalculatorPage(data);
//				purchasedGoodsAndServicesGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_1PurchasedGoods_Ser();
//				gHGCalculatorsPage.SelectPeriod2022();
//				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				gHGCalculatorsPage.clickOnAddActivityButton();
//				purchasedGoodsAndServicesGHGCalculatorPage.EditActivityScope3EmissionsScope3_1_AverageBased();
//				gHGCalculatorsPage.extractTco2Value_1();
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
//	// -------------------------Spend Base---------------------------------
//	@Test
//	public void TC006_ValidateEditActivityforPurchasedGoodsandSerInScope3_1SpendBasedCalculator() {
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.PurchaseGoodsServiSpendBased);
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
//					purchasedGoodsAndServicesGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_1PurchasedGoods_Ser();
//					purchasedGoodsAndServicesGHGCalculatorPage.clickOnScope3_1PurchasedGoods_Ser_SpendBased();
//					// gHGCalculatorsPage.SelectPeriod();
//				}
//				gHGCalculatorsPage.clickOnActivityInActivitiesGridMultiple();
//				gHGCalculatorsPage.clickOnEditButtonInActivityDetails_1();
//				purchasedGoodsAndServicesGHGCalculatorPage.EdittActivityScope3_1SpendBasedEmissions();
//				gHGCalculatorsPage.verifyEditActivityUpdatedToastMessage();
//				purchasedGoodsAndServicesGHGCalculatorPage.ValidateActivityDetailsInViewActivityScope3_1SpendBased();
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
//	// ----------------------Overlap----------------------
//
//	@Test
//	public void TC006_ValidateAddActvityOverlapPurchasedGoodsandServicesSpenBasedInScope3_1Calculator() {
//		try {
//			GHGCalculatorsPage.printNewTestCaseMessage("Purchased Goods Spend Calculator (OVERLAP) ");
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.PurchaseGoodsServiSpendBased);
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
//					purchasedGoodsAndServicesGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_1PurchasedGoods_Ser();
////				purchasedGoodsAndServicesGHGCalculatorPage.clickOnScope3_1PurchasedGoods_Ser_SpendBased();
////				gHGCalculatorsPage.SelectPeriod2023();
////				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				}
//				gHGCalculatorsPage = testBase.navigateToGHGCalculatorPage(data);
//				purchasedGoodsAndServicesGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_1PurchasedGoods_SerSpend();
//				gHGCalculatorsPage.SelectPeriod2022();
//				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				gHGCalculatorsPage.clickOnAddActivityButton();
//				purchasedGoodsAndServicesGHGCalculatorPage.EdittActivityScope3_1SpendBasedEmissions();
//				gHGCalculatorsPage.extractTco2Value_1();
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
