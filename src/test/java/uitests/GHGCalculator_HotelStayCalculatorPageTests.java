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

public class GHGCalculator_HotelStayCalculatorPageTests extends Common {
	private TestBase testBase;
	private HomePage HomePage;
	private MenuBarPage MenuBarPage;
	private GoalsPage GoalsPage;
	private CatalogPage CatalogsPage;
	private pages.SignInPage SignInPage;
	private GHGCalculatorsPage gHGCalculatorsPage;
	private pages.CalculatorPage CalculatorPage;
	private pages.calculators.HotelStayGHGCalculatorPage hotelStayGHGCalculatorPage;
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
	public void TC015_ValidateAddActivityforHotelStay3_6GHGCalculator() {
		try {
			GHGCalculatorsPage.printNewTestCaseMessage("HotelStay Calculator (ADD && EDIT) ");
			String strName = new Exception().getStackTrace()[0].getMethodName();
			data = new Data(Constants.HotelStay);
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
				String[] ActivityDetails = Constants.activityDetailFieldsHotelStay;
				String calcName =  "hotelStay";
				String ActivityAmount = data.get("Number of Nights Stayed");
                gHGCalculatorsPage.Add$Edit$OverLapActivitiesFor_Calculators(ActivityDetails,calcName,ActivityAmount);
			}
			datasetEnd();
		} catch (Exception e) {
			gHGCalculatorsPage.clickOnCarbonManagementNavigationMenu();
			e.printStackTrace();
		}
	}
	
	// -------------------------CEF------------------------------

//@Test
//		public void TC015_ValidateAddCustomEmissionFactorForHotelStsayScope3_6() {
//			try {
//				GHGCalculatorsPage.printNewTestCaseMessage("Hotel Stay ADDCEF");
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
//					gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailsHotelStay);
//					Double A=Double.parseDouble(data.get("Number of Nights Stayed"))*Double.parseDouble(data.get("Number of Rooms Occupied"));
//					String Amount = A.toString();
//	                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.activityDetailFieldsHotelStay,Amount);
//				datasetEnd();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		@Test
//		public void TC015_ValidateEditCustomEmissionFactorForHotelStsayScope3_6() {
//			try {
//				GHGCalculatorsPage.printNewTestCaseMessage("HotelStay EditCEF");
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
//					gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailsHotelStay);
//					String Amount = data.get("Number of Nights Stayed");
//	                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.activityDetailFieldsHotelStay,Amount);
//				datasetEnd();
//				}		} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		
		
//		
//		@Test
//		public void TC015_ValidateEditActivityforHotelStay3_6GHGCalculator() {
//			try {
//				String strName = new Exception().getStackTrace()[0].getMethodName();
//				data = new Data(Constants.HotelStay);
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
//						hotelStayGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_6HotelStay();
//						// gHGCalculatorsPage.SelectPeriod();
//					}
//					gHGCalculatorsPage.clickOnActivityInActivitiesGridMultiple();
//					gHGCalculatorsPage.clickOnEditButtonInActivityDetails_1();
//					hotelStayGHGCalculatorPage.EditActivityScope3_6HotelStayEmissions();
//					gHGCalculatorsPage.verifyEditActivityUpdatedToastMessage();
//					hotelStayGHGCalculatorPage.ValidateActivityDetailsInViewActivityScope3_6HotelStay();
//					gHGCalculatorsPage.clickOnCloseInActivityDetails();
//					gHGCalculatorsPage.clickOnGenerateButtonAlternate();
//				}
//				MenuBarPage.logOut();
//				testBase.tearDown();
//				datasetEnd();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	// '''''''''''''''Overlap'''''''''''''''
//
//	@Test
//	public void TC015_ValidateAddActivityOverlapforHotelStay3_6GHGCalculator() {
//		try {
//			GHGCalculatorsPage.printNewTestCaseMessage("HotelStay Calculator (OVERLAP) ");
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.HotelStay);
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
////				hotelStayGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_6HotelStay();
////				gHGCalculatorsPage.SelectPeriod2023();
////				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				}
//				gHGCalculatorsPage = testBase.navigateToGHGCalculatorPage(data);
//				hotelStayGHGCalculatorPage = gHGCalculatorsPage.clickOnScope3_6HotelStay();
//				gHGCalculatorsPage.SelectPeriod2022();
//				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				gHGCalculatorsPage.clickOnAddActivityButton();
//				hotelStayGHGCalculatorPage.EditActivityScope3_6HotelStayEmissions();
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
