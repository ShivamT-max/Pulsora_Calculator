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
import pages.calculators.EndOfLifeTreatmentGHGCalculatorPage;
import utilities.Common;
import utilities.Constants;
import utilities.Data;
import utilities.GlobalKeys;

public class GHGCalculators_EndOfLifeTreatmentCalculatorPageTests extends Common {
	private TestBase testBase;
	private HomePage HomePage;
	private MenuBarPage MenuBarPage;
	private GoalsPage GoalsPage;
	private CatalogPage CatalogsPage;
	private pages.SignInPage SignInPage;
	private GHGCalculatorsPage gHGCalculatorsPage;
	private pages.CalculatorPage CalculatorPage;
	private pages.calculators.EndOfLifeTreatmentGHGCalculatorPage endOfLifeTreatmentGHGCalculatorPage;
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
	public void TC023_ValidateAddActivityforEndOfLifeTreatemtForSoldProductInScope3_12Calculator() {
		try {
			GHGCalculatorsPage.printNewTestCaseMessage("EOLT Calculator (ADD && EDIT) ");
			String strName = new Exception().getStackTrace()[0].getMethodName();
			data = new Data(Constants.EndOfLifeTreatmentofSoldProduct);
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
				String[] ActivityDetails = Constants.activityDetailFieldsEOLT;
				String CalcName = "EOLT";
				String ActivityAmount = data.get("Mass of Waste After Consumer Use");
				gHGCalculatorsPage.Add$Edit$OverLapActivitiesFor_Calculators(ActivityDetails,CalcName,ActivityAmount);
			}
			datasetEnd();
		} catch (Exception e) {
			gHGCalculatorsPage.clickOnCarbonManagementNavigationMenu();
			e.printStackTrace();
		}
	}
	
	
	// -------------------------CEF------------------------------

//@Test
//		public void TC023_ValidateAddCustomEmissionFactorForEndofLifeTreatment3_12() {
//			try {
//				GHGCalculatorsPage.printNewTestCaseMessage("EOLT ADDCEF");
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
//					gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailFieldNamesEOLT);
//					String Amount = data.get("Mass Of Waste After Consumer Use");
//	                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.activityDetailFieldsEOLT,Amount);
//				}
//				datasetEnd();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		@Test
//		public void TC023_ValidateEditCustomEmissionFactorForEndofLifeTreatment3_12() {
//			try {
//				GHGCalculatorsPage.printNewTestCaseMessage("EOLT EditCEF");
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
//					gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailFieldNamesEOLT);
//					String Amount = data.get("Mass Of Waste after Consumer Use");
//	                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.activityDetailFieldsEOLT,Amount);
//				}
//				datasetEnd();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
		
	
//	@Test
//	public void TC023_ValidateEditActivityforEndOfLifeTreatemtForSoldProductInScope3_12Calculator() {
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.EndOfLifeTreatmentofSoldProduct);
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
//					endOfLifeTreatmentGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_12EndOfLifeTreat();
//					//gHGCalculatorsPage.SelectPeriod();
//				}
//				gHGCalculatorsPage.clickOnActivityInActivitiesGridMultiple();
//				gHGCalculatorsPage.clickOnEditButtonInActivityDetails_1();
//				endOfLifeTreatmentGHGCalculatorPage.EditActivityEndOfLifeTreatmentScope3_12Emissions();
//				gHGCalculatorsPage.verifyEditActivityUpdatedToastMessage();
//				endOfLifeTreatmentGHGCalculatorPage.ValidateActivityDetailsInViewActivityScope3_12();
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
//	// ----------------------------------Overlap----------------------------------
//	@Test
//	public void TC023_ValidateAddActvityOverlapEndofLifeTreatmentofSoldProductsInScope3_12Calculator() {
//		try {
//			GHGCalculatorsPage.printNewTestCaseMessage("EOLT Calculator (OVERLAP) ");
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.EndOfLifeTreatmentofSoldProduct);
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
//				}
//				gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
//				endOfLifeTreatmentGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_12EndOfLifeTreat();
//				gHGCalculatorsPage.SelectPeriod2022();
//				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				gHGCalculatorsPage.clickOnAddActivityButton();
//				endOfLifeTreatmentGHGCalculatorPage.EditActivityEndOfLifeTreatmentScope3_12Emissions();
//				gHGCalculatorsPage.extractTco2ValueEndOfLifeTreatment();
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
//	
}
