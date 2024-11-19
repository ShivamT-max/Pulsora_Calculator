package uitests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CalculatorPage;
import pages.CatalogPage;
import pages.FacilitiesPage;
import pages.GHGCalculatorsPage;
import pages.HomePage;
import pages.MenuBarPage;
import pages.calculators.HotelStayGHGCalculatorPage;
import utilities.Common;
import utilities.Constants;
import utilities.Data;
import utilities.GlobalKeys;

public class CarbonManagementPageTests extends Common {
	public static int count = 1;
	private TestBase TestBase;
	private HomePage HomePage;
	private MenuBarPage MenuBarPage;
	private CatalogPage CatalogsPage;
	private pages.SignInPage SignInPage;
	private CalculatorPage calculatorPage;
	private GHGCalculatorsPage gHGCalculatorsPage;
	private GHGCalculator_DirectEmissionCalculatorPageTests ghg_DECalculatorScope1;
	private GHGCalculatorPurchasedGoodServicesCalculatorPageTests ghg_PurchasedGoods;
	private GHGCalculatorCapitalGoodsAveragedataPageTests ghg_CapitalGoods;
	private GHGCalculator_HotelStayCalculatorPageTests ghg_HotelStay;
	private GHGCalculator_UpstreamLeasedAssetsCalculatorPageTests ghg_ULA;
	private GHGCalculator_DownstreamLeasedAssetsCalculatorPageTests ghg_DLA;
	private GHGCalculators_ProcessingofSoldProductCalculatorPageTests ghg_POSP;
	private GHGCalculator_UseofSoldProductCalculatorPageTests ghg_UOSP;
	private GHGCalculators_EndOfLifeTreatmentCalculatorPageTests ghg_EOLT;
	private GHGCalculator_InvestmentsCalculatorPageTests ghg_Investments;
	
	private FacilitiesPage facilitiesPage;
	public Data data;
	public ArrayList<String> datasets;
	
	
    public static List<String> logInDetails = new ArrayList();
	
	public static List<String> loginDetailsAR6 = new ArrayList();

	@BeforeMethod
	public void testStart(Method method) {
		String name = method.getName();
		String testdataName = GlobalKeys.configData.get("TestsType");
		String testdataTypeName = GlobalKeys.configData.get("TestDataType");
		if (testdataTypeName.equalsIgnoreCase(Constants.SigngleSetData)) {
			if (testdataName.equalsIgnoreCase(Constants.UITests)) {
				data = new Data(Constants.UIExcelDataFile);
			} else if (testdataName.equalsIgnoreCase(Constants.APITests)) {
				data = new Data(Constants.APIExcelDataFile);
				data = new Data("TestData");
			} else if (testdataName.equalsIgnoreCase(Constants.APITests)) {
				data = new Data("APITestData");
			}
			datasets = data.getDataSets(name);
		}
	}
	
	
	
	

	@Test
	public void TC001_ValidateEmissionFactorDataSetsInFacilities() {
		String strName = new Exception().getStackTrace()[0].getMethodName();
		data = new Data(Constants.calcFacilityEFDataSets);
		datasets = data.getDataSets();
		data.setColIndex();
		List<String> logInDetails = new ArrayList();
		for (String dataset : datasets) {
			data.setIndex_Multiple(dataset);
			datasetStart(strName + dataset);
			if (!logInDetails.contains(data.get("UserName"))) {
				logInDetails.add(data.get("UserName"));
				SignInPage = TestBase.setUp(data);
				HomePage = SignInPage.SignInToPulsEsGApp();
				MenuBarPage = HomePage.returnMenuPage();
				MenuBarPage.clickOnHamburgerMenu();
				calculatorPage = MenuBarPage.clickOnCalculatorsMenu();
				facilitiesPage = calculatorPage.clickOnFacilitiesInCarbonMangementPage();
				facilitiesPage.clickOnCreateFacility();
			}
			facilitiesPage.selectCountry();
			facilitiesPage.selectPeriod();
			facilitiesPage.validateEmissionFactorDataSetsInFacilities();
			facilitiesPage.clickOnCancelButton();
			facilitiesPage.clickOnCreateFacility();
			datasetEnd();
		}
		TestBase.tearDown();
	}
	
	
	
	public void clickOnCarbonManagementNavigationMenu() {
		try {
			Actions action = new Actions(TestBase.driver);
			String btnGHGCalc1 = "//li[@aria-label='GHG Calculators']";
			WebElement btnGHGCalc = TestBase.driver.findElement(By.xpath(btnGHGCalc1));
			action.click(btnGHGCalc).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public GHGCalculator_DirectEmissionCalculatorPageTests navigateToDirectEmissionCalculatorPage() {
		return new GHGCalculator_DirectEmissionCalculatorPageTests();		
	}
	public GHGCalculatorPurchasedGoodServicesCalculatorPageTests navigatePurchasedGoodsCalculator() {
		return new GHGCalculatorPurchasedGoodServicesCalculatorPageTests();	
	}
	public GHGCalculatorCapitalGoodsAveragedataPageTests navigateCapitalGoodsCalculator() {
		return new GHGCalculatorCapitalGoodsAveragedataPageTests();	
	}
	
	public GHGCalculator_HotelStayCalculatorPageTests navigateHotelStayCalculator() {
		return new GHGCalculator_HotelStayCalculatorPageTests();	
	}
	
	public GHGCalculator_UpstreamLeasedAssetsCalculatorPageTests navigateUpstreamLeasedAssetCalculator() {
		return new GHGCalculator_UpstreamLeasedAssetsCalculatorPageTests();	
	}
	
	public GHGCalculator_DownstreamLeasedAssetsCalculatorPageTests navigateDownstreamLeasedAssetCalculator() {
		return new GHGCalculator_DownstreamLeasedAssetsCalculatorPageTests();	
	}
	public GHGCalculators_ProcessingofSoldProductCalculatorPageTests navigateProcessingOfSoldProductsCalculator() {
		return new GHGCalculators_ProcessingofSoldProductCalculatorPageTests();	
	}
	public GHGCalculator_UseofSoldProductCalculatorPageTests navigateUseOfSoldProductsCalculator() {
		return new GHGCalculator_UseofSoldProductCalculatorPageTests();	
	}
	public GHGCalculators_EndOfLifeTreatmentCalculatorPageTests navigateEndOfLifeTreatmentCalculator() {
		return new GHGCalculators_EndOfLifeTreatmentCalculatorPageTests();	
	}
	public GHGCalculator_InvestmentsCalculatorPageTests navigateInvestmentsCalculator() {
		return new GHGCalculator_InvestmentsCalculatorPageTests();	
	}
	
	public GHGCalculatorsPage navigateToGHGCalculatorsPage(Data data) {
		return new GHGCalculatorsPage(TestBase.driver, data);
	}
	
	
	
//	          @Test
//			public void TC002_ValidateSanityTestCasesForCarbonManagementCalculators() throws Exception {
//		    	Constants.funType = "AR4";
//				TC002_ValidateAdd_EditTestCasesForCarbonManagementCalculators();         //ADD and EDIT
//////				clickOnCarbonManagementNavigationMenu();
//////				Constants.funType = "CEF";
//////				TC003_ValidateADDCEFTestCasesForCarbonManagementCalculators();           //ADDCEF
//////				TestBase.tearDown();
////				Constants.funType = "AR6";
////				TC002_ValidateAdd_EditTestCasesForCarbonManagementCalculators();         //ADD AR6
//				TestBase.tearDown();
//			}
//	          
//	         
//	      
//	          //@Test
//	        public void TC003_ValidateRegressionTestCasesForCarbonManagementCalculators() throws Exception {
//		    	Constants.funType = "AR4";
//				TC002_ValidateAdd_EditTestCasesForCarbonManagementCalculators();       //ADD and EDIT
//				clickOnCarbonManagementNavigationMenu();
//				Constants.funType = "CEF";
//				TC003_ValidateEDITCEFTestCasesForCarbonManagementCalculators();        //EditECF
//				TestBase.tearDown();
//				Constants.funType = "AR6";
//				TC002_ValidateAdd_EditTestCasesForCarbonManagementCalculators();       //Add AR6
//				TC002_ValidateOverlapTestCasesForCarbonManagementCalculators();        //Overlap
//				TestBase.tearDown();
//			}
//		
//		
//		public void TC002_ValidateAdd_EditTestCasesForCarbonManagementCalculators() throws Exception {
////				ghg_DECalculatorScope1 = navigateToDirectEmissionCalculatorPage();
////				ghg_DECalculatorScope1.TC001_ValidateAddActivityForStationaryCombustionInScope1Calculator();
////				ghg_DECalculatorScope1.TC002_ValidateAddActivityForMobileCombustionInScope1Calculator();
////				ghg_DECalculatorScope1.TC003_ValidateAddActivityForRefrigerantsandFugitivesInScope1Calculator();
////				ghg_PurchasedGoods = navigatePurchasedGoodsCalculator();
////				ghg_PurchasedGoods.TC005_ValidateAddActivityforPurchasedGoodsandSerAveragedataInScope3_1Calculator();
////				ghg_PurchasedGoods.TC006_ValidateAddActivityforPurchasedGoodsandSerInScope3_1SpendBasedCalculator();		
////				ghg_CapitalGoods = navigateCapitalGoodsCalculator();
////				ghg_CapitalGoods.TC007_ValidateAddActivityforCapitalGoodsandSerAveragedataInScope3_2Calculator();
////				ghg_CapitalGoods.TC008_ValidateAddActivityforCapitalGoodsandSerInScope3_2SpendBasedCalculator();				
////				ghg_HotelStay = navigateHotelStayCalculator();
////				ghg_HotelStay.TC015_ValidateAddActivityforHotelStay3_6GHGCalculator();				
////				ghg_ULA = navigateUpstreamLeasedAssetCalculator();
////				ghg_ULA.TC018_ValidateAddActivityfor3_8UpstreamLeasedAssets();		
////				ghg_DLA = navigateDownstreamLeasedAssetCalculator();
////				ghg_DLA.TC024_ValidateAddActivityfor3_13DownstreamLeasedAssets();		
////				ghg_POSP = navigateProcessingOfSoldProductsCalculator();
////				ghg_POSP.TC021_ValidateAddActivityfor3_10PrcessingofSoldProdutcs();	
////				ghg_UOSP = navigateUseOfSoldProductsCalculator();
////				ghg_UOSP.TC022_ValidateAddActivityfor3_11UseofSoldProdutcs();				
////				ghg_EOLT = navigateEndOfLifeTreatmentCalculator();
////				ghg_EOLT.TC023_ValidateAddActivityforEndOfLifeTreatemtForSoldProductInScope3_12Calculator();			
//				ghg_Investments = navigateInvestmentsCalculator();
//				ghg_Investments.TC026_ValidateAddActivityfor3_15InvestmentsSpecificMethod();
//				ghg_Investments.TC027_ValidateAddActivityfor3_15InvestmentsAveraggeDataMethod();
//				ghg_Investments.TC028_ValidateAddActivityfor3_15InvestmentsDeptProjectFinancingSpecificMethod();
//		        ghg_Investments.TC029_ValidateAddActivityfor3_15InvestmentsDebtProjectFinancingAverageDataMethod();
//		}
//		
//		
//		
//		public void TC002_ValidateOverlapTestCasesForCarbonManagementCalculators() throws Exception {
//			ghg_DECalculatorScope1 = navigateToDirectEmissionCalculatorPage();
//			ghg_DECalculatorScope1.TC001_ValidateAddActvityOverlapStationaryCombutionInScope1Calculator();
//			ghg_DECalculatorScope1.TC002_ValidateAddActvityOverlapMobileCombutionInScope1Calculator();
//			ghg_DECalculatorScope1.TC003_ValidateAddActvityOverlapRefregerantsInScope1Calculator();	 
////			ghg_PurchasedGoods = navigatePurchasedGoodsCalculator();
////			ghg_PurchasedGoods.TC005_ValidateAddActvityOverlapPurchasedGoodsandServicesInScope3_1Calculator();
////			ghg_PurchasedGoods.TC006_ValidateAddActvityOverlapPurchasedGoodsandServicesSpenBasedInScope3_1Calculator();
////			ghg_CapitalGoods = navigateCapitalGoodsCalculator();
////			ghg_CapitalGoods.TC007_ValidateAddActvityOverlapCapitalGoodsInScope3_2Calculator();
////			ghg_CapitalGoods.TC008_ValidateAddActvityOverlapCapitalGoodsInScope3_2SpenBasedCalculator();
////			ghg_HotelStay = navigateHotelStayCalculator();
////			ghg_HotelStay.TC015_ValidateAddActivityOverlapforHotelStay3_6GHGCalculator();
//			ghg_ULA = navigateUpstreamLeasedAssetCalculator();
//			ghg_ULA.TC018_ValidateAddActivityOverlapfor38UpstreamLeasedAssets();
//			ghg_DLA = navigateDownstreamLeasedAssetCalculator();
//			ghg_DLA.TC024_ValidateAddActivityOverlapfor3_8DownLeasedAssets();
//			ghg_POSP = navigateProcessingOfSoldProductsCalculator();
//			ghg_POSP.TC021_ValidateAddActivityOverlapforPrcessingofSoldProdutcs();	
//			ghg_UOSP = navigateUseOfSoldProductsCalculator();
//			ghg_UOSP.TC022_ValidateAddActivityOverlapforUseofSoldProdutcs();
//			ghg_EOLT = navigateEndOfLifeTreatmentCalculator();
//			ghg_EOLT.TC023_ValidateAddActvityOverlapEndofLifeTreatmentofSoldProductsInScope3_12Calculator();
//			ghg_Investments = navigateInvestmentsCalculator();
//			ghg_Investments.TC026_ValidateAddActivityOverlapfor3_15InvestmentsSpecificMethod();
//			ghg_Investments.TC027_ValidateAddActivityOverlapfor_3_15InvestmentsAveraggeDataMethod();
//			ghg_Investments.TC028_ValidateAddActivityOverlapfor3_15InvestmentsDeptProjectFinancingSpecificMethod();
//	        ghg_Investments.TC029_ValidateAddActivityOverlapfor3_15InvestmentsDebtProjectFinancingAverageDataMethod();
//	    }
//	
//		
//		public void TC003_ValidateADDCEFTestCasesForCarbonManagementCalculators() {
//			ghg_DECalculatorScope1 = navigateToDirectEmissionCalculatorPage();
//			ghg_DECalculatorScope1.TC001_ValidateAddCustomEmissionFactorInActivityStationaryCombutionScope1();
//			ghg_DECalculatorScope1.TC002_ValidateAddCustomEmissionFactorInActivityMobileCombutionScope1();
//			ghg_PurchasedGoods = navigatePurchasedGoodsCalculator();
//			ghg_PurchasedGoods.TC005_ValidateAddCustomEmissionFactorInActivityPurchasedGoodsServicesAverageScope3_1();
//			ghg_PurchasedGoods.TC006_ValidateAddCustomEmissionFactorInActivityPurchasedGoodsServicesSpendScope3_1();
//			ghg_CapitalGoods = navigateCapitalGoodsCalculator();
//			ghg_CapitalGoods.TC007_ValidateAddCustomEmissionFactorInActivityCapitalGoodsAverageScope3_2();
//			ghg_CapitalGoods.TC008_ValidateAddCustomEmissionFactorInActivityCapitalGoodsspendScope3_2();
//			ghg_HotelStay = navigateHotelStayCalculator();
//			ghg_HotelStay.TC015_ValidateAddCustomEmissionFactorForHotelStsayScope3_6();
//			ghg_ULA = navigateUpstreamLeasedAssetCalculator();
//			ghg_ULA.TC018_ValidateAddCustomEmissionFactorUpstreamLeasedAssets();
//			ghg_DLA = navigateDownstreamLeasedAssetCalculator();
//			ghg_DLA.TC024_ValidateAddCustomEmissionFactorDownstreamLeasedAssets();
//			ghg_POSP = navigateProcessingOfSoldProductsCalculator();
//			ghg_POSP.TC021_ValidateAddCustomEmissionFactorProcessingofSoldProduct();
//			ghg_UOSP = navigateUseOfSoldProductsCalculator();
//			ghg_UOSP.TC022_ValidateAddCustomEmissionFactorUseofSoldProduct();
//			ghg_EOLT = navigateEndOfLifeTreatmentCalculator();
//			ghg_EOLT.TC023_ValidateAddCustomEmissionFactorForEndofLifeTreatment3_12();
//			ghg_Investments = navigateInvestmentsCalculator();
//			ghg_Investments.TC027_ValidateAddCustomEmissionEquityInvestmentsAverage();
//			ghg_Investments.TC029_ValidateAddCustomEmissionDebtProjectFinancingInvestmentsAverage();
//		}
//		
//		
//		public void TC003_ValidateEDITCEFTestCasesForCarbonManagementCalculators() {
//			ghg_DECalculatorScope1 = navigateToDirectEmissionCalculatorPage();
//			ghg_DECalculatorScope1.TC001_ValidateEditCustomEmissionFactorInActivityStationaryCombutionScope1();
//			ghg_DECalculatorScope1.TC002_ValidateEditCustomEmissionFactorInActivityMobileCombutionScope1();
//			ghg_PurchasedGoods = navigatePurchasedGoodsCalculator();
//			ghg_PurchasedGoods.TC005_ValidateEditCustomEmissionFactorInActivityPurchasedGoodsServicesAverageScope3_1();
//			ghg_PurchasedGoods.TC006_ValidateEditCustomEmissionFactorInActivityPurchasedGoodsServicesSpendScope3_1();
//			ghg_CapitalGoods = navigateCapitalGoodsCalculator();
//			ghg_CapitalGoods.TC007_ValidateEditCustomEmissionFactorInActivityCapitalGoodsAverageScope3_2();
//			ghg_CapitalGoods.TC008_ValidateEditCustomEmissionFactorInActivityCapitalGoodsspendScope3_2();
//			ghg_HotelStay = navigateHotelStayCalculator();
//			ghg_HotelStay.TC015_ValidateEditCustomEmissionFactorForHotelStsayScope3_6();
//			ghg_ULA = navigateUpstreamLeasedAssetCalculator();
//			ghg_ULA.TC018_ValidateEditCustomEmissionFactorUpstreamLeasedAssets();
//			ghg_DLA = navigateDownstreamLeasedAssetCalculator();
//			ghg_DLA.TC024_ValidateEditCustomEmissionFactorDownstreamLeasedAssets();
//			ghg_POSP = navigateProcessingOfSoldProductsCalculator();
//			ghg_POSP.TC021_ValidateEditCustomEmissionFactorProcessingofSoldProduct();
//			ghg_UOSP = navigateUseOfSoldProductsCalculator();
//			ghg_UOSP.TC022_ValidateEditCustomEmissionFactorUseofSoldProduct();
//			ghg_EOLT = navigateEndOfLifeTreatmentCalculator();
//			ghg_EOLT.TC023_ValidateEditCustomEmissionFactorForEndofLifeTreatment3_12();
//			ghg_Investments = navigateInvestmentsCalculator();
//			ghg_Investments.TC027_ValidateEditCustomEmissionEquityInvestmentsAverage();
//			ghg_Investments.TC029_ValidateEditCustomEmissionDebtProjectFinancingInvestmentsAverage();
//		}
//		
		
		
	
	
	//@Test
	//public void TC001_collectDataFrom_I_Tooltips() {
//		String strName = new Exception().getStackTrace()[0].getMethodName();
//		data.setColIndex(strName);
//		for (String dataset : datasets) {
//			data.setIndex(dataset);
//			datasetStart(dataset);
//			SignInPage = TestBase.setUpVerifyFile(data);
//			HomePage = SignInPage.SignInToPulsEsGApp();
//			MenuBarPage = HomePage.returnMenuPage();
//			MenuBarPage.clickOnHamburgerMenu();
//			gHGCalculatorsPage = MenuBarPage.clickOnGHGCalculatorsMenu();
//			gHGCalculatorsPage.verify_i_ToolTipsIncalcPages();
//			datasetEnd();
//			TestBase.tearDown();
//		}
//	}
}
