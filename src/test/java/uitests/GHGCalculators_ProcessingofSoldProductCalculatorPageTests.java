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
import pages.calculators.ProcessingofSoldProductsCalculatorPage;
import pages.calculators.UseOfSoldProductGHGCalculatorPage;
import utilities.Common;
import utilities.Constants;
import utilities.Data;
import utilities.GlobalKeys;

public class GHGCalculators_ProcessingofSoldProductCalculatorPageTests extends Common {
	private TestBase testBase;
	private HomePage HomePage;
	private MenuBarPage MenuBarPage;
	private GoalsPage GoalsPage;
	private CatalogPage CatalogsPage;
	private pages.SignInPage SignInPage;
	private GHGCalculatorsPage gHGCalculatorsPage;
	private pages.CalculatorPage CalculatorPage;
	private pages.calculators.ProcessingofSoldProductsCalculatorPage processingofSoldProductsCalculatorPage;
	public Data data;
	public ArrayList<String> datasets;
	private UseOfSoldProductGHGCalculatorPage useOfSoldProductGHGCalculatorPage;

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
	public void TC021_ValidateAddActivityfor3_10PrcessingofSoldProdutcs() {
		try {
			GHGCalculatorsPage.printNewTestCaseMessage("POSP Calculator (ADD && EDIT) ");
			String strName = new Exception().getStackTrace()[0].getMethodName();
			data = new Data(Constants.ProcessingOfSoldProducts);
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
				String[] ActivityDetails = Constants.activityDetailFieldsPOSP;
				String CalcName = "POSP";
				String ActivityAmount = data.get("Mass of Sold Product");
				gHGCalculatorsPage.Add$Edit$OverLapActivitiesFor_Calculators(ActivityDetails,CalcName,ActivityAmount);
		}
			gHGCalculatorsPage.clickOnCarbonManagementNavigationMenu();

			datasetEnd();
		} catch (Exception e) {
			gHGCalculatorsPage.clickOnCarbonManagementNavigationMenu();
			e.printStackTrace();
		}
	}
	
	// --------------------------CEF--------------------------------------------

		//@Test
//		public void TC021_ValidateAddCustomEmissionFactorProcessingofSoldProduct() {
//			try {
//				GHGCalculatorsPage.printNewTestCaseMessage("POSP ADDCEF");
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
//					gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailFieldNamesPOSP);
//					String Amount = data.get("Mass of Sold Product");
//	                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.activityDetailFieldsPOSP,Amount);
//				}
//				datasetEnd();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		@Test
//		public void TC021_ValidateEditCustomEmissionFactorProcessingofSoldProduct() {
//			try {
//				GHGCalculatorsPage.printNewTestCaseMessage("POSP EditCEF");
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
//					gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailFieldNamesPOSP);
//					String Amount = data.get("Mass of Sold Product");
//	                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.activityDetailFieldsPOSP,Amount);
//				}
//				datasetEnd();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		
		
		
//		@Test
//		public void TC021_ValidateEditActivityfor3_10PrcessingofSoldProdutcs() {
//			try {
//				String strName = new Exception().getStackTrace()[0].getMethodName();
//				data = new Data(Constants.ProcessingOfSoldProducts);
//				datasets = data.getDataSets();
//				data.setColIndex();
//				List<String> logInDetails = new ArrayList();
//				for (String dataset : datasets) {
//					data.setIndex_Multiple(dataset);
//					datasetStart(strName + dataset);
//					if (!logInDetails.contains(data.get("UserName"))) {
//						logInDetails.add(data.get("UserName"));
//						SignInPage = testBase.setUp(data);
//						HomePage = SignInPage.SignInToPulsEsGApp();
//						MenuBarPage = HomePage.returnMenuPage();
//						MenuBarPage.clickOnHamburgerMenu();
//						gHGCalculatorsPage = MenuBarPage.clickOnGHGCalculatorsMenu();
//						processingofSoldProductsCalculatorPage = gHGCalculatorsPage.clickOnScope3_10ProcessingOfSoldProduct();
//						//gHGCalculatorsPage.SelectPeriod();
//					}
//					gHGCalculatorsPage.clickOnActivityInActivitiesGridMultiple();
//					gHGCalculatorsPage.clickOnEditButtonInActivityDetails_1();
//					processingofSoldProductsCalculatorPage.EditActivityScope3_10PrcessingofSoldProdutcs();
//					gHGCalculatorsPage.verifyEditActivityUpdatedToastMessage();
//					processingofSoldProductsCalculatorPage
//							.ValidateActivityDetailsInViewActivityScope3_10PrcessingofSoldProdutcs();
//					//gHGCalculatorsPage.clickOnCloseInActivityDetails();
//					gHGCalculatorsPage.clickOnCloseInActivityDetailsSoldProduct();
//					gHGCalculatorsPage.clickOnGenerateButtonAlternate();
//				}
//				MenuBarPage.logOut();
//				testBase.tearDown();
//				datasetEnd();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//	@Test
//	public void TC021_ValidateAddActivityOverlapforPrcessingofSoldProdutcs(){
//		try {
//			GHGCalculatorsPage.printNewTestCaseMessage("POSP Calculator (OVERLAP) ");
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.ProcessingOfSoldProducts);
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
////				processingofSoldProductsCalculatorPage = gHGCalculatorsPage.clickOnScope3_10ProcessingOfSoldProduct();
////				gHGCalculatorsPage.SelectPeriod2023();
////				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				}
//				gHGCalculatorsPage = testBase.navigateToGHGCalculatorPage(data);
//				processingofSoldProductsCalculatorPage = gHGCalculatorsPage.clickOnScope3_10ProcessingOfSoldProduct();
//				gHGCalculatorsPage.SelectPeriod2022();
//				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				gHGCalculatorsPage.clickOnAddActivityButton();
//				processingofSoldProductsCalculatorPage.EditActivityScope3_10PrcessingofSoldProdutcs();
//				gHGCalculatorsPage.extractTco2Value();
//				//gHGCalculatorsPage.clickOnCloseInActivityDetails();
//				gHGCalculatorsPage.clickOnCloseInActivityDetailsSoldProduct();
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
