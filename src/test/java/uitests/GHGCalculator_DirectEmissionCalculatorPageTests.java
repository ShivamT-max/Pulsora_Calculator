package uitests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.GHGCalculatorsPage;
import pages.HomePage;
import pages.MenuBarPage;
import pages.calculators.DirectEmissionsCalculatorPage;
import utilities.Common;
import utilities.Constants;
import utilities.Data;
import utilities.GlobalKeys;

public class GHGCalculator_DirectEmissionCalculatorPageTests extends Common {
	private HomePage HomePage;
	private MenuBarPage MenuBarPage;
	private pages.SignInPage SignInPage;
	private GHGCalculatorsPage gHGCalculatorsPage;
	private pages.calculators.DirectEmissionsCalculatorPage directEmissionsCalculatorPage;
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

	public GHGCalculatorsPage navigateToGHGCalculatorsPage() {
		return new GHGCalculatorsPage(driver, data);
	}

	// ------------------ Add Activity---------------------//

	@Test
	public void TC001_ValidateAddActivityForStationaryCombustionInScope1Calculator() {
		try {
			GHGCalculatorsPage
					.printNewTestCaseMessage("Stationary Combustion Scope1 Calculator (ADD && EDIT && Overlap) ");
			String strName = new Exception().getStackTrace()[0].getMethodName();
			data = new Data(Constants.CalculatorStationaryCombustion);
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
				String[] ActivityDetails = Constants.actSC;
				String CalcName = "SC";
				String ActivityAmount = data.get("Fuel Amount");
				gHGCalculatorsPage.Add$Edit$OverLapActivitiesFor_Calculators(ActivityDetails, CalcName, ActivityAmount);
			}
			datasetEnd();
		} catch (Exception e) {
			directEmissionsCalculatorPage.clickOnMobileCombutionScope1GHGCalculator();
			e.printStackTrace();
		}
	}

	@Test
	public void TC002_ValidateAddActivityForMobileCombustionInScope1Calculator() {
		try {
			GHGCalculatorsPage.printNewTestCaseMessage("Mobile Combustion Scope1 Calculator (ADD && EDIT) ");
			String strName = new Exception().getStackTrace()[0].getMethodName();
			data = new Data(Constants.CalculatorMobileCombution);
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
				String[] ActivityDetails = Constants.actMC;
				String CalcName = "MC";
				String ActivityAmount = data.get("Activity Amount");
				gHGCalculatorsPage.Add$Edit$OverLapActivitiesFor_Calculators(ActivityDetails, CalcName, ActivityAmount);
			}
			datasetEnd();
		} catch (Exception e) {
			directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnRefrigerantsandFugitivesScope1QAGHGCalculator();
			e.printStackTrace();
		}
	}

	@Test
	public void TC003_ValidateAddActivityForRefrigerantsandFugitivesInScope1Calculator() {
		try {
			GHGCalculatorsPage.printNewTestCaseMessage("Fugitives Scope1 Calculator (ADD && EDIT) ");
			String strName = new Exception().getStackTrace()[0].getMethodName();
			System.out.println(strName);
			data = new Data(Constants.RefrigerantsandFugitives);
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
				String[] ActivityDetails = Constants.actFugitive;
				String CalcName = "Refrigerants";
				String ActivityAmount = data.get("Quantity of refrigerant purchase/use/leak");
				gHGCalculatorsPage.Add$Edit$OverLapActivitiesFor_Calculators(ActivityDetails, CalcName, ActivityAmount);
			}
			datasetEnd();
		} catch (Exception e) {
			gHGCalculatorsPage.clickOnCarbonManagementNavigationMenu();
			e.printStackTrace();
		}
	}

	// ------------------------------------------- ADD CEF
	// ----------------------------------------------//
//	String strName;
//	    public void ValidateCustomEmissionFactorInActivityForDirectEmissionsScope1(String[] cefDetails,String[] actDetails) {
//			try {
//				data = new Data("TestData");
//				datasets = data.getDataSets(strName);
//				data.setColIndex(strName);
//				for (String dataset : datasets) {
//					data.setIndex(dataset);
//					datasetStart(dataset);
//					if (!CarbonManagementPageTests.logInDetails.contains(data.get("UserName"))) {
//						CarbonManagementPageTests.logInDetails.add(data.get("UserName"));
//						SignInPage = TestBase.setUp(data);
//						gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
//						gHGCalculatorsPage.stepsToNavigateGHGCalculators();
//					}
//					gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
//					gHGCalculatorsPage.addAndValidateParameters_CustomEmissionFactor(cefDetails);
//					directEmissionsCalculatorPage = new DirectEmissionsCalculatorPage(TestBase.driver, data);
//					directEmissionsCalculatorPage.addAdvanceConfigurationForDirectEmissionsScope1();
//					gHGCalculatorsPage.clickOnScope1DirectEmissions();
//					String ActivityAmount;
//					if(data.get("subCalcName").equals("Stationary Combustion")) {
//						ActivityAmount =  data.get("Fuel Amount");
//					}else {
//						ActivityAmount =  data.get("Activity Amount");
//					}
//					gHGCalculatorsPage.AddandValidateActivityForCalculators_CEF(actDetails,ActivityAmount);
//					datasetEnd();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		
//	    }
//	
//		@Test
//		public void TC001_ValidateAddCustomEmissionFactorInActivityStationaryCombutionScope1() {
//			 strName = new Exception().getStackTrace()[0].getMethodName();
//			GHGCalculatorsPage.printNewTestCaseMessage("Stationary Combution Scope1 ADDCEF");
//			String[] cefDetails = Constants.cefDetailsSC;
//			String[] actDetails = Constants.actSC;
//			ValidateCustomEmissionFactorInActivityForDirectEmissionsScope1(cefDetails, actDetails);
//		}
//		
//		
//		@Test
//		public void TC002_ValidateAddCustomEmissionFactorInActivityMobileCombutionScope1() {
//			strName = new Exception().getStackTrace()[0].getMethodName();
//			GHGCalculatorsPage.printNewTestCaseMessage("Mobile Combution Scope1 ADDCEF");
//			String[] cefDetails = Constants.cefDetailsMC;
//			String[] actDetails = Constants.actMC;
//			ValidateCustomEmissionFactorInActivityForDirectEmissionsScope1(cefDetails, actDetails);
//		}
//
//		//-------------------------------------------  EDIT CEF ----------------------------------------------//
//
//		@Test
//		public void TC001_ValidateEditCustomEmissionFactorInActivityStationaryCombutionScope1() {
//		     	strName = new Exception().getStackTrace()[0].getMethodName();
//				GHGCalculatorsPage.printNewTestCaseMessage("Stationary Combution Scope1 EDITCEF");
//				String[] cefDetails = Constants.cefDetailsSC;
//				String[] actDetails = Constants.actSC;
//				ValidateCustomEmissionFactorInActivityForDirectEmissionsScope1(cefDetails, actDetails);
//		}
//	
//		@Test
//		public void TC002_ValidateEditCustomEmissionFactorInActivityMobileCombutionScope1() {
//			    strName = new Exception().getStackTrace()[0].getMethodName();
//				GHGCalculatorsPage.printNewTestCaseMessage("Mobile Combution Scope1 EDITCEFCEF");
//				String[] cefDetails = Constants.cefDetailsMC;
//				String[] actDetails = Constants.actMC;
//				ValidateCustomEmissionFactorInActivityForDirectEmissionsScope1(cefDetails, actDetails);
//		}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
////	@Test
////	public void TC001_ValidateEditActivityForStationaryCombustionInScope1Calculator() {
////		String strName = new Exception().getStackTrace()[0].getMethodName();
////		data = new Data(Constants.CalculatorStationaryCombustion);
////		datasets = data.getDataSets();
////		data.setColIndex();
////		List<String> logInDetails = new ArrayList();
////		for (String dataset : datasets) {
////			data.setIndex_Multiple(dataset);
////			datasetStart(strName + dataset);
////			System.out.println(strName);
////			if (!logInDetails.contains(data.get("UserName"))) {
////				logInDetails.add(data.get("UserName"));
////				SignInPage = TestBase.setUp(data);
////				HomePage = SignInPage.SignInToPulsEsGApp();
////				MenuBarPage = HomePage.returnMenuPage();
////				MenuBarPage.clickOnHamburgerMenu();
////				gHGCalculatorsPage = MenuBarPage.clickOnGHGCalculatorsMenu();
////				directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnScope1DirectEmissions();
////				gHGCalculatorsPage.clickOnStationaryCombustion();
////			}
////			gHGCalculatorsPage.clickOnActivityInActivitiesGridMultiple();
////			gHGCalculatorsPage.clickOnEditButtonInActivityDetails();
////			directEmissionsCalculatorPage.EditActivityForStationaryCombutionInDirectEmissions();
////			gHGCalculatorsPage.verifyEditActivityToastMessage();
////			GHGCalculatorsPage.getCO2_CH4_N20Value();
////			directEmissionsCalculatorPage.ValidateActivityDetailsInStationaryCombutionViewActivity();
////			directEmissionsCalculatorPage.validateTCO2andTCH4andTN2O_ForStationaryCombustionInScope1();
////			gHGCalculatorsPage.validateGlobalWarmingPotentialValuesRelatedToAR();
////			gHGCalculatorsPage.clickOnCloseInActivityDetails();
////			gHGCalculatorsPage.clickOnGenerateButtonAlternate();
////		}
////		MenuBarPage.logOut();
////		TestBase.tearDown();
////		datasetEnd();
////	}
////
////	// .................Overlap..................
////	@Test
////	public void TC001_ValidateAddActvityOverlapStationaryCombutionInScope1Calculator() {
////		try {
////			GHGCalculatorsPage.printNewTestCaseMessage("Stationary Combustion Scope1 Calculator (OVERLAP) ");
////			String strName = new Exception().getStackTrace()[0].getMethodName();
////			System.out.println(strName);
////			data = new Data(Constants.CalculatorStationaryCombustion);
////			datasets = data.getDataSets();
////			data.setColIndex();
////			List<String> logInDetails = new ArrayList();
////			for (String dataset : datasets) {
////				data.setIndex_Multiple(dataset);
////				datasetStart(strName + dataset);
////				if (!CarbonManagementPageTests.logInDetails.contains(data.get("UserName"))) {
////					CarbonManagementPageTests.logInDetails.add(data.get("UserName"));
////					SignInPage = TestBase.setUp(data);
////					HomePage = SignInPage.SignInToPulsEsGApp();
////					MenuBarPage = HomePage.returnMenuPage();
////					MenuBarPage.clickOnHamburgerMenu();
////					gHGCalculatorsPage = MenuBarPage.clickOnGHGCalculatorsMenu();	
////				} 
////				gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
////				directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnScope1DirectEmissions();
////				gHGCalculatorsPage.SelectPeriod2022();
////				gHGCalculatorsPage.calculateGHGEmissionBefore();
////				gHGCalculatorsPage.clickOnAddActivityButton();
////				directEmissionsCalculatorPage.EditActivityForStationaryCombutionInDirectEmissions();
////				gHGCalculatorsPage.extractTco2Value_1_P();
////				gHGCalculatorsPage.clickOnCloseInActivityDetails();
////				gHGCalculatorsPage.collectGHGEmissionAfter();
////				gHGCalculatorsPage.clickOnGenerateButton();
////			}
////			if (!CarbonManagementPageTests.logInDetails.contains(data.get("UserName"))) {
////				CarbonManagementPageTests.logInDetails.add(data.get("UserName"));
////				MenuBarPage.logOut();
////				TestBase.tearDown();
////			}
////			datasetEnd();
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////	}
////
////	
////
////	// -----------------Mobile Combution---------------------
////	@Test
////	public void TC002_ValidateEditActivityForMobileCombustionInScope1Calculator() {
////		try {
////			String strName = new Exception().getStackTrace()[0].getMethodName();
////			System.out.println(strName);
////			data = new Data(Constants.CalculatorMobileCombution);
////			datasets = data.getDataSets();
////			data.setColIndex();
////			List<String> logInDetails = new ArrayList();
////			for (String dataset : datasets) {
////				data.setIndex_Multiple(dataset);
////				datasetStart(strName + dataset);
////				if (!logInDetails.contains(data.get("UserName"))) {
////					logInDetails.add(data.get("UserName"));
////					SignInPage = TestBase.setUp(data);
////					HomePage = SignInPage.SignInToPulsEsGApp();
////					MenuBarPage = HomePage.returnMenuPage();
////					MenuBarPage.clickOnHamburgerMenu();
////					gHGCalculatorsPage = MenuBarPage.clickOnGHGCalculatorsMenu();
////					directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnScope1DirectEmissions();
////					directEmissionsCalculatorPage.clickOnMobileCombutionScope1GHGCalculator();
////					
////				}
////				gHGCalculatorsPage.clickOnActivityInActivitiesGridMultiple();
////				gHGCalculatorsPage.clickOnEditButtonInActivityDetailsMC();
////				directEmissionsCalculatorPage.EditActivityMobileCombutionInDirectEmissions();
////				gHGCalculatorsPage.verifyEditActivityToastMessage();
////				directEmissionsCalculatorPage.ValidateActivityMobileCumbutionDetailsInViewActivity();
////				//gHGCalculatorsPage.clickOnEditButtonInActivityDetailsMC();
////				gHGCalculatorsPage.clickOnCloseInActivityDetails();
////				gHGCalculatorsPage.clickOnGenerateButtonAlternate();
////			}
////			MenuBarPage.logOut();
////			TestBase.tearDown();
////			datasetEnd();
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////	}
////
////	
////
////	// ------------------------Overlap --------------------------------
////	@Test
////	public void TC002_ValidateAddActvityOverlapMobileCombutionInScope1Calculator() {
////		try {
////			GHGCalculatorsPage.printNewTestCaseMessage("Mobile Combustion Scope1 Calculator (OVERLAP) ");
////			String strName = new Exception().getStackTrace()[0].getMethodName();
////			System.out.println(strName);
////			data = new Data(Constants.CalculatorMobileCombution);
////			datasets = data.getDataSets();
////			data.setColIndex();
////			List<String> logInDetails = new ArrayList();
////			for (String dataset : datasets) {
////				data.setIndex_Multiple(dataset);
////				datasetStart(strName + dataset);
////				if (!CarbonManagementPageTests.logInDetails.contains(data.get("UserName"))) {
////					CarbonManagementPageTests.logInDetails.add(data.get("UserName"));
////					SignInPage = TestBase.setUp(data);
////					HomePage = SignInPage.SignInToPulsEsGApp();
////					MenuBarPage = HomePage.returnMenuPage();
////					MenuBarPage.clickOnHamburgerMenu();
////					gHGCalculatorsPage = MenuBarPage.clickOnGHGCalculatorsMenu();
////					directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnScope1DirectEmissions();
//////				DirectEmissionsCalculatorPage.clickOnMobileCombutionScope1GHGCalculator();
//////				gHGCalculatorsPage.calculateGHGEmissionBefore();
////				}
////				directEmissionsCalculatorPage = gHGCalculatorsPage.navigateToDirectEmissionPage(data);
////				directEmissionsCalculatorPage.clickOnMobileCombutionScope1GHGCalculator();
////				gHGCalculatorsPage.calculateGHGEmissionBefore();
////				gHGCalculatorsPage.clickOnAddActivityButton();
////				directEmissionsCalculatorPage.EditActivityMobileCombutionInDirectEmissions();
////				gHGCalculatorsPage.extractTco2Value_1_P();
////				//gHGCalculatorsPage.clickOnEditButtonInActivityDetailsMC();
////				gHGCalculatorsPage.clickOnCloseInActivityDetailsInScope2();
////				gHGCalculatorsPage.collectGHGEmissionAfter();
////			}
////			gHGCalculatorsPage.clickOnGenerateButton();
////			if (!CarbonManagementPageTests.logInDetails.contains(data.get("UserName"))) {
////				CarbonManagementPageTests.logInDetails.add(data.get("UserName"));
////				MenuBarPage.logOut();
////				TestBase.tearDown();
////			}
////			datasetEnd();
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////	}
////
////	
////
////	// -------------Refrigerants and Fugitives---------------------------
////	@Test
////	public void TC003_ValidateEditActivityForRefrigerantsandFugitivesInScope1Calculator(){
////		try {
////			String strName = new Exception().getStackTrace()[0].getMethodName();
////			System.out.println(strName);
////			data = new Data(Constants.RefrigerantsandFugitives);
////			datasets = data.getDataSets();
////			data.setColIndex();
////			List<String> logInDetails = new ArrayList();
////			for (String dataset : datasets) {
////				data.setIndex_Multiple(dataset);
////				datasetStart(strName + dataset);
////				if (!logInDetails.contains(data.get("UserName"))) {
////					logInDetails.add(data.get("UserName"));
////					SignInPage = TestBase.setUp(data);
////					HomePage = SignInPage.SignInToPulsEsGApp();
////					MenuBarPage = HomePage.returnMenuPage();
////					MenuBarPage.clickOnHamburgerMenu();
////					gHGCalculatorsPage = MenuBarPage.clickOnGHGCalculatorsMenu();
////					directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnScope1DirectEmissions();
////					directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnRefrigerantsandFugitivesScope1QAGHGCalculator();		
////					}
////				gHGCalculatorsPage.clickOnActivityInActivitiesGridMultiple();
////				gHGCalculatorsPage.clickOnEditButtonInActivityDetails();
////				directEmissionsCalculatorPage.EditActivityRefrigerantsandFugitivesInDirectEmissions();
////				gHGCalculatorsPage.verifyEditActivityToastMessage();
////				directEmissionsCalculatorPage.ValidateActivityRefrigerantsandFugitivesDetailsInViewActivity();
////				gHGCalculatorsPage.clickOnCloseInActivityDetails();
////				gHGCalculatorsPage.clickOnGenerateButtonAlternate();
////			}
////			MenuBarPage.logOut();
////			TestBase.tearDown();
////			datasetEnd();
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////	}
//
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	/* validate 
//	 * 139
//	 * Refrigerants 
//	 * test case
//	 */
//	
//	
//	
//	@Test
//	public void TC003_ValidateAddActivityForRefrigerantsandFugitivesInScope1Calculator_RefrigirantUsed() {
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			System.out.println(strName);
//			data = new Data(Constants.RefrigerantsandFugitives_RefrigerantUsed);
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
//					//gHGCalculatorsPage.clickOnGHGEmissionsSetup();
//					directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnScope1DirectEmissions();
//					directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnRefrigerantsandFugitivesScope1QAGHGCalculator();
//					gHGCalculatorsPage.clickOnActivityInActivitiesGridMultipleForAGGridEdit();
//					directEmissionsCalculatorPage.clearDirForFugitivesRefrigerants();
//				}
//				gHGCalculatorsPage.clickOnEditButtonInActivityDetails();
//				directEmissionsCalculatorPage.EditRefrigirentsUsedForAllRefrigirantsInFugitives();
//				directEmissionsCalculatorPage.validateEmissionFactorAndTCO2eForFugitivesForAllRefrigirants();
//			}
//			MenuBarPage.logOut();
//			TestBase.tearDown();
//			datasetEnd();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//
////-------------------------Overlap -------------------  
//	@Test
//	public void TC003_ValidateAddActvityOverlapRefregerantsInScope1Calculator() {
//		try {
//			GHGCalculatorsPage.printNewTestCaseMessage("Fugitives Scope1 Calculator (OVERLAP) ");
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			System.out.println(strName);
//			data = new Data(Constants.RefrigerantsandFugitives);
//			datasets = data.getDataSets();
//			data.setColIndex();
//			List<String> logInDetails = new ArrayList();
//			for (String dataset : datasets) {
//				data.setIndex_Multiple(dataset);
//				datasetStart(strName + dataset);
//				if (!CarbonManagementPageTests.logInDetails.contains(data.get("UserName"))) {
//					CarbonManagementPageTests.logInDetails.add(data.get("UserName"));
//					SignInPage = TestBase.setUp(data);
//					HomePage = SignInPage.SignInToPulsEsGApp();
//					MenuBarPage = HomePage.returnMenuPage();
//					MenuBarPage.clickOnHamburgerMenu();
//					gHGCalculatorsPage = MenuBarPage.clickOnGHGCalculatorsMenu();
//					MenuBarPage.clickOnHamburgerMenu();
//					directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnScope1DirectEmissions();
//				}
//				gHGCalculatorsPage = TestBase.navigateToGHGCalculatorPage(data);
//				directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnRefrigerantsandFugitivesScope1QAGHGCalculator();
//				gHGCalculatorsPage.SelectPeriod2022();
//				gHGCalculatorsPage.calculateGHGEmissionBefore();
//				gHGCalculatorsPage.clickOnAddActivityButton();
//				directEmissionsCalculatorPage.EditActivityRefrigerantsandFugitivesInDirectEmissions();
//				gHGCalculatorsPage.extractTco2Value_1();
//				gHGCalculatorsPage.clickOnCloseInActivityDetails();
//				gHGCalculatorsPage.collectGHGEmissionAfter();
//				gHGCalculatorsPage.clickOnGenerateButton();
//			}
//			gHGCalculatorsPage.clickOnCarbonManagementNavigationMenu();
//			if (!CarbonManagementPageTests.logInDetails.contains(data.get("UserName"))) {
//				CarbonManagementPageTests.logInDetails.add(data.get("UserName"));
//				MenuBarPage.logOut();
//				TestBase.tearDown();
//			}
//			datasetEnd();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	// Data Sets Validations with Unit Conversions.
//	
//	// Deefra data Set
//	@Test
//	public void TC001_ValidateAddActivityForDataSetsInScope1Calculator() {
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			System.out.println(strName);
//			data = new Data(Constants.CalculatorStationaryCombustionUnit);
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
//					// gHGCalculatorsPage.validateSelectGWPValuesInGHGEmissionCalculatorSetUpPage();
//					gHGCalculatorsPage.clickOnGHGCalculatorsMenu();
//					directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnScope1DirectEmissions();
//					gHGCalculatorsPage.clickOnStationaryCombustion();
//				}
//				gHGCalculatorsPage.selectPeriodToAll();
//				gHGCalculatorsPage.clickOnAddActivityButton();
//				directEmissionsCalculatorPage.addActivityWithValidationOfUnitsAndFuelType();
//				directEmissionsCalculatorPage.ValidateUserInputActivityDetailsInStationaryCombutionViewActivity();
//				directEmissionsCalculatorPage.validateConversionFactorInActivityDetails();
//				directEmissionsCalculatorPage.validateEmissionFactorsInActivityDetails();
//				directEmissionsCalculatorPage.validateGWPValuesInctivityDetails();
//				directEmissionsCalculatorPage.validateEmisionDetailsInActivityDetailsRHP();
//				directEmissionsCalculatorPage.clickOnCloseInActivityDetails();
//			}
//			TestBase.tearDown();
//			datasetEnd();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	// EPA data Set
//	@Test
//	public void TC001_ValidateAddActivityForDataSetsInScope1Calculatore_WithEPADataSet(){
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			System.out.println(strName);
//			data = new Data(Constants.CalculatorStationaryCombustionUnit_EPA);
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
//					// gHGCalculatorsPage.validateSelectGWPValuesInGHGEmissionCalculatorSetUpPage();
//					gHGCalculatorsPage.clickOnGHGCalculatorsMenu();
//					directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnScope1DirectEmissions();
//					gHGCalculatorsPage.clickOnStationaryCombustion();
//				}
//				gHGCalculatorsPage.selectPeriodToAll();
//				gHGCalculatorsPage.clickOnAddActivityButton();
//				directEmissionsCalculatorPage.addActivityWithValidationOfUnitsAndFuelType();
//				directEmissionsCalculatorPage.ValidateUserInputActivityDetailsInStationaryCombutionViewActivity();
//				directEmissionsCalculatorPage.validateConversionFactorInActivityDetails_EPA();
//				directEmissionsCalculatorPage.validateEmissionFactorsInActivityDetails_EPA();
//				directEmissionsCalculatorPage.validateGWPValuesInctivityDetails_EPA();
//				directEmissionsCalculatorPage.validateEmisionDetailsInActivityDetailsRHP_EPA();
//				directEmissionsCalculatorPage.clickOnCloseInActivityDetails();
//			}
//			TestBase.tearDown();
//			datasetEnd();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	
//	
//	// IEA data Set
//	@Test
//	public void TC001_ValidateAddActivityForDataSetsInScope1Calculatore_WithIEADataSet() {
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			System.out.println(strName);
//			data = new Data(Constants.CalculatorStationaryCombustionUnit_IEA);
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
//					directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnScope1DirectEmissions();
//					gHGCalculatorsPage.clickOnStationaryCombustion();
//				}
//				if(data.get("Activity").equals("Add")) {
//				gHGCalculatorsPage.clickOnAddActivityButton();
//				}else {
//					gHGCalculatorsPage.clickOnActivityInActivitiesGridMultipleForAGGridEdit();
//					gHGCalculatorsPage.clickOnEditButtonInActivityDetails();
//				}
//				directEmissionsCalculatorPage.addActivityWithValidationOfUnitsAndFuelType();
//				//DirectEmissionsCalculatorPage.validateConversionFactorInActivityDetails_EPA(); //doubt
//				GHGCalculatorsPage.getCO2_CH4_N20Value();
//				gHGCalculatorsPage.validateRHPActivityDetailsForAllCalculators(Constants.actSC,"Stationary Combustion");
//				directEmissionsCalculatorPage.validateTCO2andTCH4andTN2O_ForStationaryCombustionInScope1();
//				gHGCalculatorsPage.validateGlobalWarmingPotentialValuesRelatedToAR();
//				directEmissionsCalculatorPage.calculateTCO2eValueUsing_EmissionFactorValueStationaryCombustion(data.get("Fuel Amount"));
//				directEmissionsCalculatorPage.clickOnCloseInActivityDetails();
//			}
//			TestBase.tearDown();
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
//	
//	
//	
//	
//	@Test
//	public void TC001_ValidateSebanciFuelTypesForStationaryCombustionInScope1Calculator(){
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			System.out.println(strName);
//			data = new Data(Constants.StationaryCombustionCalorifcVal);
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
//					MenuBarPage.clickOnHamburgerMenu();
//				}
//				directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnScope1DirectEmissions();
//				directEmissionsCalculatorPage.clickOnEmissionFactorsForDirectEmission();
//				gHGCalculatorsPage.clickOnbuttonParameterInputGHGCalculatorScope1();
//				directEmissionsCalculatorPage.Add_EditActivitiesForEmissionFactorGridForChangingFuelTypeAndUnits();  //AddCEF
//				gHGCalculatorsPage.verifyCEFAddToastMessage();
//				gHGCalculatorsPage.clickOnCloseInActivityDetails();
//				gHGCalculatorsPage.clickOnFacilities();
//				directEmissionsCalculatorPage.clickOnSabanciOrganization();
//				directEmissionsCalculatorPage.clickOnGridInFacilitiesMenu();
//				directEmissionsCalculatorPage.editFuelAndVehicleDetailsForSebanciInAdvanceSetup();
//				directEmissionsCalculatorPage.clickOnGHGCalculatorIcon();
//				directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnScope1DirectEmissions();
//				directEmissionsCalculatorPage.ClickOnStationaryCombutionScope1();
//				directEmissionsCalculatorPage.clickOnSabanciOrganization();
//
//				gHGCalculatorsPage.clickOnAddActivityButton();
//				directEmissionsCalculatorPage.Add_EditActivityForStationaryCombustionScope1();
//				if(data.get("TestCase Type").equals("Sabanci")) {
//				directEmissionsCalculatorPage.validateCalorificValuesAndUnitConversionMessagesForScope1(data.get("unitCategory"));
//				}else {
//					GHGCalculatorsPage.getCO2_CH4_N20Value();
//					gHGCalculatorsPage.validateRHPActivityDetailsForAllCalculators(Constants.actSC,"Stationary Combustion");
//					directEmissionsCalculatorPage.validateTCO2andTCH4andTN2O_ForStationaryCombustionInScope1();
//					gHGCalculatorsPage.validateGlobalWarmingPotentialValuesRelatedToAR();
//					directEmissionsCalculatorPage.calculateTCO2eValueUsing_EmissionFactorValueStationaryCombustion(data.get("Fuel Amount"));
//					directEmissionsCalculatorPage.clickOnCloseInActivityDetails();
//				}
//			}
//
//			MenuBarPage.logOut();
//			TestBase.tearDown();
//			datasetEnd();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	
//	@Test
//	public void TC001_ValidateSebanciFuelTypesForMobileCombustionInScope1Calculator(){
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			System.out.println(strName);
//			data = new Data(Constants.MobileCombustionCalorifcVal);
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
//				}
//				MenuBarPage.clickOnHamburgerMenu();
//				directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnScope1DirectEmissions();
//				directEmissionsCalculatorPage.clickOnEmissionFactorsForDirectEmission();
//				gHGCalculatorsPage.clickOnbuttonParameterInputGHGCalculatorScope1();
//				directEmissionsCalculatorPage.Add_CustomEmissionFactor_Scope1_InDirectEmissions();
//				gHGCalculatorsPage.verifyCEFAddToastMessage();
//				gHGCalculatorsPage.clickOnCloseInActivityDetails();
//				gHGCalculatorsPage.clickOnFacilities();
//				directEmissionsCalculatorPage.clickOnSabanciOrganization();
//				directEmissionsCalculatorPage.clickOnGridInFacilitiesMenu();
//				directEmissionsCalculatorPage.editFuelAndVehicleDetailsForSebanciInAdvanceSetup();
//				directEmissionsCalculatorPage.clickOnGHGCalculatorIcon();
//				directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnScope1DirectEmissions();
//				directEmissionsCalculatorPage.clickOnMobileCombutionScope1GHGCalculator();
//				directEmissionsCalculatorPage.clickOnSabanciOrganization();
//				gHGCalculatorsPage.clickOnAddActivityButton();
//				directEmissionsCalculatorPage.EditActivityMobileCombutionInDirectEmissions();
//				gHGCalculatorsPage.validateRHPActivityDetailsForAllCalculators(Constants.actMC,"Mobile Combustion");
//				directEmissionsCalculatorPage.validateCalorificValuesAndUnitConversionMessagesForScope1(data.get("unitCategory"));
//			}
//			MenuBarPage.logOut();
//			TestBase.tearDown();
//			datasetEnd();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	  /**
//	 * 
//	 * ALL Datasets 
//	 * ADD CEF 
//	 * 
//	   **/
//	
//	@Test
//	public void TC003_ValidateEditCEF_CFForStationaryCombustionForAllDataset() {
//		try {
//			String strName = new Exception().getStackTrace()[0].getMethodName();
//			System.out.println(strName);
//			data = new Data(Constants.datasets);
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
//					MenuBarPage.clickOnHamburgerMenu();
//				}
//				directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnScope1DirectEmissions();
//				directEmissionsCalculatorPage.clickOnEmissionFactorsForDirectEmission();
//				gHGCalculatorsPage.clickOnbuttonParameterInputGHGCalculatorScope1();
//				directEmissionsCalculatorPage.Add_EditActivitiesForEmissionFactorGridForChangingFuelTypeAndUnits();  //AddCEF
//				gHGCalculatorsPage.verifyCEFAddToastMessage();
//				gHGCalculatorsPage.validateRHPActivityDetailsForAllCalculators(Constants.cefDetailsMC,"Mobile Combustion");
//				gHGCalculatorsPage.clickOnCloseInActivityDetails();
//				gHGCalculatorsPage.clickOnFacilities();
//
//				directEmissionsCalculatorPage.clickOnGridInFacilitiesMenu();
//				directEmissionsCalculatorPage.editFuelAndVehicleDetailsForSebanciInAdvanceSetup();
//				directEmissionsCalculatorPage.clickOnGHGCalculatorIcon();
//				directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnScope1DirectEmissions();
//				directEmissionsCalculatorPage.ClickOnStationaryCombutionScope1();
//				gHGCalculatorsPage.SelectPeriod2022();
//				if(data.get("unitCategory").equals("energy")) {
//					 gHGCalculatorsPage.clickOnAddActivityButton();
//				}
//				directEmissionsCalculatorPage.Add_EditActivityForStationaryCombustionScope1();
//				GHGCalculatorsPage.getCO2_CH4_N20Value();
//				gHGCalculatorsPage.validateRHPActivityDetailsForAllCalculators(Constants.actSC,"Stationary Combustion");
//				gHGCalculatorsPage.calculateEmissionFactorUsingFormula();
//				directEmissionsCalculatorPage.validateTCO2andTCH4andTN2O_ForStationaryCombustionInScope1();
//				gHGCalculatorsPage.validateGlobalWarmingPotentialValuesRelatedToAR();
//				directEmissionsCalculatorPage.calculateTCO2eValueUsing_EmissionFactorValueStationaryCombustion(data.get("Fuel Amount"));
//				directEmissionsCalculatorPage.validateCalorificValuesAndUnitConversionMessagesForScope1(data.get("unitCategory"));
//			    directEmissionsCalculatorPage.clickOnCloseInActivityDetails();
//			}
//			MenuBarPage.logOut();
//			TestBase.tearDown();
//			datasetEnd();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	/*Mobile Combustion 
//	 * 
//	 * Fuel Efficiency tes tcase
//	 * 
//	 * */
//	
//	@Test
//	public void validateMobileCombustionForStandardDataSetEPADefraFuelEfficiency() {
//		String strName = new Exception().getStackTrace()[0].getMethodName();
//		System.out.println(strName);
//		data = new Data(Constants.MCStandardFuelEfficiency);
//		datasets = data.getDataSets();
//		data.setColIndex();
//		List<String> logInDetails = new ArrayList();
//		for (String dataset : datasets) {
//			data.setIndex_Multiple(dataset);
//			datasetStart(strName + dataset);
//			if (!logInDetails.contains(data.get("UserName"))) {
//				logInDetails.add(data.get("UserName"));
//				SignInPage = TestBase.setUp(data);
//				HomePage = SignInPage.SignInToPulsEsGApp();
//				MenuBarPage = HomePage.returnMenuPage();
//				MenuBarPage.clickOnHamburgerMenu();
//				gHGCalculatorsPage = MenuBarPage.clickOnGHGCalculatorsMenu();
//				//MenuBarPage.clickOnHamburgerMenu();
//				gHGCalculatorsPage.clickOnCarbonManagementNavigationMenu();
//				directEmissionsCalculatorPage = gHGCalculatorsPage.clickOnScope1DirectEmissions();
//				directEmissionsCalculatorPage.clickOnMobileCombutionScope1GHGCalculator(); 
//				gHGCalculatorsPage.SelectOrganizationForTiffany();
//				gHGCalculatorsPage.SelectPeriod2024();
//				directEmissionsCalculatorPage.editandValidateForAllUnitsForStationaryCombustionFE();
//			}
//		}
//		MenuBarPage.logOut();
//		TestBase.tearDown();
//		datasetEnd();
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//
//	

}
