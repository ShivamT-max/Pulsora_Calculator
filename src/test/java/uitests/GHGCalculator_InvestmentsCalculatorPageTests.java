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
import pages.calculators.InvestmentsGHGCalculatorsPage;
import utilities.Common;
import utilities.Constants;
import utilities.Data;
import utilities.GlobalKeys;

public class GHGCalculator_InvestmentsCalculatorPageTests extends Common {
	private TestBase testBase;
	private HomePage HomePage;
	private MenuBarPage MenuBarPage;
	private GoalsPage GoalsPage;
	private CatalogPage CatalogsPage;
	private pages.SignInPage SignInPage;
	private GHGCalculatorsPage gHGCalculatorsPage;
	private pages.CalculatorPage CalculatorPage;
	private pages.calculators.InvestmentsGHGCalculatorsPage investmentsGHGCalculatorsPage;
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
	public void TC026_ValidateAddActivityfor3_15InvestmentsSpecificMethod() {
		try {
			GHGCalculatorsPage.printNewTestCaseMessage("Investments Specific Method Calculator (ADD && EDIT) ");
			String strName = new Exception().getStackTrace()[0].getMethodName();
			data = new Data(Constants.Investments);
			datasets = data.getDataSets();
			data.setColIndex();
			List<String> userName = CarbonManagementPageTests.logInDetails;
			for (String dataset : datasets) {
				data.setIndex_Multiple(dataset);
				datasetStart(strName + dataset);
				if (!userName.contains(data.get("UserName"))) {
					userName.add(data.get("UserName"));
					SignInPage = TestBase.setUp(data);
					gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
					gHGCalculatorsPage.stepsToNavigateGHGCalculators();
				}
				gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
				String[] ActivityDetails = Constants.activityDetailsInvestmentsSpecific;
				String CalcName = "InvestmentsSpecificMethod";
				String ActivityAmount = "";
				gHGCalculatorsPage.Add$Edit$OverLapActivitiesFor_Calculators(ActivityDetails,CalcName,ActivityAmount);
			}
			datasetEnd();
		} catch (Exception e) {
			investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_11InvestmentsAverageDataMethod();
			e.printStackTrace();
		}
	}
	
	

	@Test
	public void TC027_ValidateAddActivityfor3_15InvestmentsAveraggeDataMethod() {
		try {
			GHGCalculatorsPage.printNewTestCaseMessage("Investments Average Data Method Calculator (ADD && EDIT) ");
			String strName = new Exception().getStackTrace()[0].getMethodName();
			data = new Data(Constants.InvestmentsAverageDataMethod);
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
					investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_15Investments();
				}
				gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
				investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_11InvestmentsAverageDataMethod();
				gHGCalculatorsPage.clickOnAddandEditActivitiesForCalculators();
				investmentsGHGCalculatorsPage.EditActivityScope3_15InvestmentsAverageDataMethod();
				gHGCalculatorsPage.validateToastMessageForActivityCalculators();
				gHGCalculatorsPage.validateActivityDetailsandEmissionDetails(Constants.actDtailsInvestmentsAvgDataMethod
						                                              , "InvestmentsAveraggeDataMethod", "");
			}
			datasetEnd();
		} catch (Exception e) {
			investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_11InvestmentsDept_ProjectFinancing();
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void TC028_ValidateAddActivityfor3_15InvestmentsDeptProjectFinancingSpecificMethod() {
		try {
			GHGCalculatorsPage.printNewTestCaseMessage("Investments Dept Project Financing Specific Method Calculator (ADD && EDIT) ");
			String strName = new Exception().getStackTrace()[0].getMethodName();
			data = new Data(Constants.InvestmentsDebtProjectSpecific);
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
					investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_15Investments();
				}
				gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
				investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_11InvestmentsDept_ProjectFinancing();
				gHGCalculatorsPage.clickOnAddandEditActivitiesForCalculators();
				investmentsGHGCalculatorsPage.EditActivityScope3_15InvestmentsDept_ProjectFinancingSpecificMethod();
				gHGCalculatorsPage.validateToastMessageForActivityCalculators();
				gHGCalculatorsPage.validateActivityDetailsandEmissionDetails(Constants.actDetailsDebtSpecificMethod, 
						                                                        "InvestmentsDeptProjectFinancingSpecificMethod", "");
			}
			datasetEnd();
		} catch (Exception e) {
			investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_11InvestmentsDept_ProjectFinancing();
			investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_11InvestmentsAverageDataMethod();
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void TC029_ValidateAddActivityfor3_15InvestmentsDebtProjectFinancingAverageDataMethod() {
		try {
			GHGCalculatorsPage.printNewTestCaseMessage("Investments Dept Project Financing Average Method Calculator (ADD && EDIT) ");
			String strName = new Exception().getStackTrace()[0].getMethodName();
			data = new Data(Constants.InvestmentsDebtProjectAverage);
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
					investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_15Investments();
				}
				gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
				investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_11InvestmentsDept_ProjectFinancing();
				investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_11InvestmentsAverageDataMethod();
				gHGCalculatorsPage.clickOnAddandEditActivitiesForCalculators();
				investmentsGHGCalculatorsPage.EditActivityScope3_15InvestmentsDebtProjectFinancingAverageDataMethod();
				gHGCalculatorsPage.validateToastMessageForActivityCalculators();
				gHGCalculatorsPage.validateActivityDetailsandEmissionDetails(Constants.activityDetailsDebtAvgMethod, "InvestmentsDebtProjectFinancingAverageDataMethod", "");
			}
			datasetEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	// --------------------------------CEF----------------------------------------------------

//@Test
//	public void TC027_ValidateAddCustomEmissionEquityInvestmentsAverage() {
//		try {
//			GHGCalculatorsPage.printNewTestCaseMessage("EquityInvestmentsAverage ADDCEF");
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
//				gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailFieInvestments);
//                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF( Constants.actDtailsInvestmentsAvgDataMethod,"");
//				datasetEnd();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	public void TC027_ValidateEditCustomEmissionEquityInvestmentsAverage() {
//		try {
//			GHGCalculatorsPage.printNewTestCaseMessage("Equity Investments Average EditCEF");
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
//				gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailFieInvestments);
//                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF( Constants.actDtailsInvestmentsAvgDataMethod,"");
//				datasetEnd();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	//-------------------------CEF---------------------------- 
//		@Test
//		public void TC029_ValidateAddCustomEmissionDebtProjectFinancingInvestmentsAverage() {
//			try {
//				GHGCalculatorsPage.printNewTestCaseMessage("DebtProjectFinancingInvestmentsAverage ADDCEF");
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
//					gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailFieInvestments);
//	                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.activityDetailsDebtAvgMethod,"");
//					datasetEnd();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		@Test
//		public void TC029_ValidateEditCustomEmissionDebtProjectFinancingInvestmentsAverage() {
//			try {
//				GHGCalculatorsPage.printNewTestCaseMessage("Debt Project Financing Investments Average EditCEF");
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
//					gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(Constants.cefDetailFieInvestments);
//	                gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(Constants.activityDetailsDebtAvgMethod,"");
//					datasetEnd();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//	
//		
//		
//		
		
	

//	// ---------------------Overlap------------------------------------
//	@Test
//	public void TC028_ValidateAddActivityOverlapfor3_15InvestmentsDeptProjectFinancingSpecificMethod() {
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.InvestmentsDebtProjectSpecific);
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
//					investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_15Investments();
////				investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_11InvestmentsDept_ProjectFinancing();
////				gHGCalculatorsPage.SelectPeriod2023();
////				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				}
//				gHGCalculatorsPage = testBase.navigateToGHGCalculatorPage(data);
//				investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_11InvestmentsDept_ProjectFinancing();
//				gHGCalculatorsPage.SelectPeriod2022();
//				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				gHGCalculatorsPage.clickOnAddActivityButton();
//				investmentsGHGCalculatorsPage.EditActivityScope3_15InvestmentsDept_ProjectFinancingSpecificMethod();
//				gHGCalculatorsPage.extractTco2ValueInvestments();
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
//	// ''''''''''''''''''''''Debt and Project Financing Average Data
//	// Method'''''''''''''''''''''''''
//	@Test
//	public void TC029_ValidateEditActivityfor3_15InvestmentsDebtProjectFinancingAverageDataMethod() {
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.InvestmentsDebtProjectAverage);
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
//					investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_15Investments();
//					investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_11InvestmentsDept_ProjectFinancing();
//					investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_11InvestmentsAverageDataMethod();
//					//gHGCalculatorsPage.SelectPeriod();
//				}
//				//gHGCalculatorsPage.selectPeriodToAll();
//				gHGCalculatorsPage.clickOnActivityInActivitiesGridScope3_15InvestmentSpecificMethodDebtandFinanc();
//				gHGCalculatorsPage.clickOnEditButtonInActivityDetails_1();
//				investmentsGHGCalculatorsPage.EditActivityScope3_15InvestmentsDebtProjectFinancingAverageDataMethod();
//				gHGCalculatorsPage.verifyEditActivityUpdatedToastMessage();
//				investmentsGHGCalculatorsPage
//						.ValidateActivityDetailsInViewActivityScope3_15InvestmentsDebtProjectFinancingAverageDataMethod();
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
////--------------------Overelap-----------------------------------
//
//	@Test
//	public void TC029_ValidateAddActivityOverlapfor3_15InvestmentsDebtProjectFinancingAverageDataMethod() {
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.InvestmentsDebtProjectAverage);
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
//					investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_15Investments();
////				investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_11InvestmentsDept_ProjectFinancing();
////				investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_11InvestmentsAverageDataMethod();
////				gHGCalculatorsPage.SelectPeriod2023();
////				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				}
//				gHGCalculatorsPage = testBase.navigateToGHGCalculatorPage(data);
//				investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_11InvestmentsDept_ProjectFinancing();
//				investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_11InvestmentsAverageDataMethod();
//				gHGCalculatorsPage.SelectPeriod2022();
//				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				gHGCalculatorsPage.clickOnAddActivityButton();
//				investmentsGHGCalculatorsPage.EditActivityScope3_15InvestmentsDebtProjectFinancingAverageDataMethod();
//				gHGCalculatorsPage.extractTco2ValueInvestments();
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
//	
//	
//	@Test
//	public void TC026_ValidateEditActivityfor3_15InvestmentsSpecificMethod() {
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.Investments);
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
//					investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_15Investments();
//				}
//				gHGCalculatorsPage.clickOnActivityInActivitiesGridScope3_15InvestmentSpecificMethod();
//				gHGCalculatorsPage.clickOnEditButtonInActivityDetails_1();
//				investmentsGHGCalculatorsPage.EditActivityScope3_15InvestmentsSpecificMethod();
//				gHGCalculatorsPage.verifyEditActivityUpdatedToastMessage();
//				investmentsGHGCalculatorsPage
//						.ValidateActivityDetailsInViewActivityScope3_15Investments_InvestmentSpecificMethod();
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
//	// ----------------------------Overlap--------------------------
//	@Test
//	public void TC026_ValidateAddActivityOverlapfor3_15InvestmentsSpecificMethod() {
//		try {
//			GHGCalculatorsPage.printNewTestCaseMessage("Investments Specific Method Calculator (OVERLAP) ");
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.Investments);
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
////				investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_15Investments();
////				gHGCalculatorsPage.SelectPeriod2023();
////				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				}
//				gHGCalculatorsPage = testBase.navigateToGHGCalculatorPage(data);
//				investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_15Investments();
//				gHGCalculatorsPage.SelectPeriod2022();
//				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				gHGCalculatorsPage.clickOnAddActivityButton();
//				investmentsGHGCalculatorsPage.EditActivityScope3_15InvestmentsSpecificMethod();
//				gHGCalculatorsPage.extractTco2ValueInvestments();
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
//	// '''''''''''''''''''''''Equity investments Average data
//	// Method'''''''''''''''''''''''''''''''
//	@Test
//	public void TC027_ValidateEditActivityfor3_15InvestmentsAveraggeDataMethod() {
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.InvestmentsAverageDataMethod);
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
//					investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_15Investments();
//					investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_11InvestmentsAverageDataMethod();
////			gHGCalculatorsPage.SelectPeriod();
//				}
//				//gHGCalculatorsPage.selectPeriodToAll();
//				gHGCalculatorsPage.clickOnActivityInActivitiesGridScope3_15InvestmentSpecificMethod();
//				gHGCalculatorsPage.clickOnEditButtonInActivityDetails_1();
//				investmentsGHGCalculatorsPage.EditActivityScope3_15InvestmentsAverageDataMethod();
//				gHGCalculatorsPage.verifyEditActivityUpdatedToastMessage();
//				investmentsGHGCalculatorsPage
//						.ValidateActivityDetailsInViewActivityScope3_15Investments_InvestmentAverageDataMethod();
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
//	// -------------------------Overlap----------------------------------
//
//	@Test
//	public void TC027_ValidateAddActivityOverlapfor_3_15InvestmentsAveraggeDataMethod(){
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			data = new Data(Constants.InvestmentsAverageDataMethod);
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
//					investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_15Investments();
////				investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_11InvestmentsAverageDataMethod();
////				gHGCalculatorsPage.SelectPeriod2023();
////				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				}
//				gHGCalculatorsPage = testBase.navigateToGHGCalculatorPage(data);
//				investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_11InvestmentsAverageDataMethod();
//				gHGCalculatorsPage.SelectPeriod2022();
//				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				gHGCalculatorsPage.clickOnAddActivityButton();
//				investmentsGHGCalculatorsPage.EditActivityScope3_15InvestmentsAverageDataMethod();
//				gHGCalculatorsPage.extractTco2ValueInvestments();
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
//	// ''''''''''''''''''''''Debt and Project Financing Specific
//		// Method'''''''''''''''''''''''''
//		@Test
//		public void TC028_ValidateEditActivityfor3_15InvestmentsDeptProjectFinancingSpecificMethod() {
//			try {
//				String strName = new Exception().getStackTrace()[0].getMethodName();
//				data = new Data(Constants.InvestmentsDebtProjectSpecific);
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
//						investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_15Investments();
//						investmentsGHGCalculatorsPage = gHGCalculatorsPage.clickOnScope3_11InvestmentsDept_ProjectFinancing();
//						//gHGCalculatorsPage.SelectPeriod();
//					}
//					//gHGCalculatorsPage.selectPeriodToAll();
//					gHGCalculatorsPage.clickOnActivityInActivitiesGridScope3_15InvestmentSpecificMethodDebtandFinanc();
//					gHGCalculatorsPage.clickOnEditButtonInActivityDetails_1();
//					investmentsGHGCalculatorsPage.EditActivityScope3_15InvestmentsDept_ProjectFinancingSpecificMethod();
//					gHGCalculatorsPage.verifyEditActivityUpdatedToastMessage();
//					investmentsGHGCalculatorsPage
//							.ValidateActivityDetailsInViewActivityScope3_15InvestmentsDept_ProjectFinancingSpecificMethod();
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
//
//	
//	
	
	
	
	
}
