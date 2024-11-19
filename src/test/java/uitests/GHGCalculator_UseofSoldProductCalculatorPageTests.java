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
import pages.calculators.UseOfSoldProductGHGCalculatorPage;
import utilities.Common;
import utilities.Constants;
import utilities.Data;
import utilities.GlobalKeys;

public class GHGCalculator_UseofSoldProductCalculatorPageTests extends Common {
	private TestBase testBase;
	private HomePage HomePage;
	private MenuBarPage MenuBarPage;
	private GoalsPage GoalsPage;
	private CatalogPage CatalogsPage;
	private pages.SignInPage SignInPage;
	private GHGCalculatorsPage gHGCalculatorsPage;
	private pages.CalculatorPage CalculatorPage;
	private pages.calculators.UseOfSoldProductGHGCalculatorPage useOfSoldProductGHGCalculatorPage;
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
	public void TC022_ValidateAddActivityfor3_11UseofSoldProdutcs() {
		try {
			GHGCalculatorsPage.printNewTestCaseMessage("UOSP Calculator (ADD && EDIT) ");
			String strName = new Exception().getStackTrace()[0].getMethodName();
			data = new Data(Constants.UseOfSoldProducts);
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
				useOfSoldProductGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_11UseOfSoldProduct();
				gHGCalculatorsPage.clickOnParameneterInputGHGCalculator();
				useOfSoldProductGHGCalculatorPage.getElectricityComsuptionConfigurationValueInProductSpecifications();
				useOfSoldProductGHGCalculatorPage.clickOnActivityBasedMethodInUseOfSoldProducts();
				gHGCalculatorsPage.clickOnAddandEditActivitiesForCalculators();
				useOfSoldProductGHGCalculatorPage.EditActivityScope3_11UseofSoldProdutcs();
				String[] ActivityDetails = Constants.activityDetailFieldsUOSP;
				String CalcName = "UOSP";
				String ActivityAmount = data.get("Number of Units Sold");
				Double ActivityAmountdouble=Double.parseDouble(ActivityAmount)*Double.parseDouble(UseOfSoldProductGHGCalculatorPage.lifeTimeUOSP);
				String ActivityAmount1=ActivityAmountdouble.toString();
				gHGCalculatorsPage.validateActivityDetailsandEmissionDetails(ActivityDetails,CalcName,ActivityAmount1);
				gHGCalculatorsPage.clickOnCarbonManagementNavigationMenu();
			}
			datasetEnd();
		} catch (Exception e) {
			gHGCalculatorsPage.clickOnCarbonManagementNavigationMenu();
			e.printStackTrace();
		}
	}
	
	

	// ----------------------------CEF------------------------------------------
//@Test
//	public void TC022_ValidateAddCustomEmissionFactorUseofSoldProduct() {
//		try {
//			GHGCalculatorsPage.printNewTestCaseMessage("UOSP ADDCEF");
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
//					SignInPage = TestBase.setUp(data);
//					gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
//					gHGCalculatorsPage.stepsToNavigateGHGCalculators();
//				}
//				gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
//				gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailFieldNamesUOSP);
//				String Amount = data.get("Number of Units Sold");
//                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.activityDetailFieldsUOSP,Amount);
//			}
//			datasetEnd();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	public void TC022_ValidateEditCustomEmissionFactorUseofSoldProduct() {
//		try {
//			GHGCalculatorsPage.printNewTestCaseMessage("UOSP EditCEF");
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
//				gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailFieldNamesUOSP);
//				String Amount = data.get("Number of Units Sold");
//                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.activityDetailFieldsUOSP,Amount);
//			}
//			datasetEnd();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
	
	

//	@Test
//	public void TC022_ValidateEditActivityfor3_11UseofSoldProdutcs() {
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.UseOfSoldProducts);
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
//					useOfSoldProductGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_11UseOfSoldProduct();
//				}
//				gHGCalculatorsPage.clickOnActivityInActivitiesGridMultiple();
//				gHGCalculatorsPage.clickOnEditButtonInActivityDetails_1();
//				useOfSoldProductGHGCalculatorPage.EditActivityScope3_11UseofSoldProdutcs();
//				gHGCalculatorsPage.verifyEditActivityUpdatedToastMessage();
//				useOfSoldProductGHGCalculatorPage.ValidateActivityDetailsInViewActivityScope3_11UseofSoldProdutcs();
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
//	// '''''''''Overlap'''''''''''''''
//	@Test
//	public void TC022_ValidateAddActivityOverlapforUseofSoldProdutcs() {
//		try {
//			GHGCalculatorsPage.printNewTestCaseMessage("UOSP Calculator (OVERLAP) ");
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.UseOfSoldProducts);
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
////				useOfSoldProductGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_11UseOfSoldProduct();
////				gHGCalculatorsPage.SelectPeriod2023();
////				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				}
//				gHGCalculatorsPage = testBase.navigateToGHGCalculatorPage(data);
//				useOfSoldProductGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_11UseOfSoldProduct();
//				gHGCalculatorsPage.SelectPeriod2022();
//				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				gHGCalculatorsPage.clickOnAddActivityButton();
//				useOfSoldProductGHGCalculatorPage.EditActivityScope3_11UseofSoldProdutcs();
//				gHGCalculatorsPage.extractTco2Value_1_P();
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
