//package utilities;
//
//public class Constants {
//
//	// Catalog
//	public static String catalogName = "TestAutCatalog_";
//	public static String catalogType = "Metric Catalog";
//	public static String catalogDesc = "TestAutCatalogDesc";
//	public static String UITests = "ui";
//	public static String APITests = "api";
//	public static final String Path_TestData = System.getProperty("user.dir")
//			+ "\\src\\test\\resources\\TestData\\MultipleTestData.xlsx";
//	// API
//	public static String accessTokenPostURL = "/kc/realms/PulsESG/protocol/openid-connect/token";
//	public static String accessTokenUserName = "rapidsos-org-admin-user1";
//	public static String accessTokenPassword = "rapidsos-org-admin-user1";
//	public static String SigngleSetData = "SingeSet";
//	public static String MultiSetData = "MultiSet";
//	public static String overlapPeriodYear2022 = "Year2022";
//	public static String datePrdstrt = "2022/01/01";
//	public static String datePrdend = "2022/12/31";
//	public static String leapPrdstrt = "2020/01/01";
//	public static String leapPrdend = "2020/12/31";
//	public static String leapDayStart = "2020/01/01";
//	public static String leapDayEnd = "2020/12/31";
//	public static String UIExcelDataFile = "TestData";
//	public static String APIExcelDataFile = "APITestData";
//	// Calc Sheet Names
//	public static String BusinessTravelDistBasedCalc = "BusinessTravelDistBasedCalc"; // BusinessTravelDistBasedCalc
//	public static String BusinessTravelSpendBasedCalc = "BusinessTravelSpendBasedCalc";
//	public static String CalculatorStationaryCombustion = "CalculatorStationaryCombustion";
//	public static String CalculatorMobileCombution = "CalculatorMobileCombution";
//	public static String RefrigerantsandFugitives = "RefrigerantsandFugitives";
//	public static String DownStreamLeasedAsset = "DownStreamLeasedAsset";
//	public static String DonwstreamWeight_DistanceMethod = "DonwstreamWeight-DistanceMethod";
//	public static String Franchises = "Franchises";
//	public static String FuelandEnergyRelatedActivit = "FuelandEnergyRelatedActivit";
//	public static String HomeOfficeTelecommuting = "HomeOfficeTelecommuting";
//	public static String HotelStay = "HotelStay";
//	public static String PurchasedElectricityRegres = "PurchasedElectricityRegres";
//	public static String PurchasedEnergy = "Purchased Energy";
//	public static String Investments = "Investments";
//	public static String InvestmentsAverageDataMethod = "InvestmentsAverageDataMethod";
//	public static String InvestmentsDebtProjectSpecific = "InvestmentsDebtProjectSpecific";
//	public static String InvestmentsDebtProjectAverage = "InvestmentsDebtProjectAverage";
//	public static String UpstreamLeasedAsset = "UpstreamLeasedAsset";
//	public static String UpstreamWeight_DistanceMethod = "UpstreamWeight-DistanceMethod";
//	public static String UseOfSoldProducts = "UseOfSoldProducts";
//	public static String WasteGeneratedInOpsCalculator = "WasteGeneratedInOpsCalculator";
//	public static String CapitalGoodsAvreageBased = "CapitalGoodsAvreageBased";
//	public static String CapitalGoodsSpendBased = "CapitalGoodsSpendBased";
//	public static String EmployeeCommutingCalculator = "EmployeeCommutingCalculator";
//	public static String PurchasedGoodsAndServicesAverag = "PurchasedGoodsAndServicesAverag";
//	public static String PurchaseGoodsServiSpendBased = "PurchaseGoodsServiSpendBased";
//	public static String EndOfLifeTreatmentofSoldProduct = "EndOfLifeTreatmentofSoldProduct";
//	public static String ProcessingOfSoldProducts = "ProcessingOfSoldProducts";
//	// CEF
//	public static String customEmissionFactor = "TestAutCEF";
//	public static String CEFName = "AutTestCEF";
//	public static String catalogDRPublishBlocker = "DRCatalogPublishBlocker";
//	public static String CatalogDRMetricAllInputTypesEntry = "DRMetricAllInputTypesEntry";
//	public static String standardErrorMessageMandatoryEvidence = "Evidence required for this metric";
//	public static String standardErrorMessageExplanationMandatory = "Explanation Mandatory";
//	public static String inputTypeRequiredErrorMessage = "Please enter a value or select “Data not available for this required metric.”";
//	public static String customErrorMessageMandatoryEvidence = "Required evidence for chosen / selected value. Please attach evidences for this metric.";
//	public static String customErrorMessageExplanationMandatory = "Please provide the explanation for the chosen / selected Value";
//	public static String RequiredErrorMessageWithoutDNA = "Please enter a value for this required metric.";
//	public static String inputTypeRequiredErrorMessageText = "Expression validation is based on : [a-z]";
//	public static String RequiredErrorMessageWithDNA = "Please enter a value or select “Data not available for this required metric.”";
//	public static String catalogAggregations = "CatalogAggregations";
//	public static String DRatalogAggregations = "DRCatalogAggregations";
//	
//	public static String textMetricTableValue = "Test1";
//	public static String drpGenderBooleanValue = "male";
//	public static String drpDownIsActiveValue = "true";
//	public static String drpMultiSelectFavCar = "Audi";
//	public static String texttxtAge = "28";
//	public static String tableUniqueKeyErrorMessage = "Duplicate record detected. Please enter Name column(s) as unique values across all records.";
//	public static String tableMetricSavedToastMessage = "Metric detail saved successfully.";
//
//	//DS
//	public static String dsWorkDayUsername = "MTIyYzNkOTMtNTk0Mi00NzkwLWE4OTEtMTJiMDA2ZWM1MjVl";
//	public static String dsWorkDayPassword = "jpa3sfxi7m1280mvodt9gqs4pi8evuykyx3iq4yhs6kwa87k2bo40vv7defkge06jb1svidj2k97xrdbacl98rrx1koh3j535cb";
//	public static String dsWorkDayRefreshtoken = "e4m64dmmflxoqfqfhzbuzefwqj7erjihh1o3nxlv0wehirrcgs4ppkq9wh96u4gkv8r31ciczetc3qfgtvbtzwyr2km9740gnxs";
//	public static String dsWorkDayTokenendpointURL = "https://wd2-impl-services1.workday.com/ccx/oauth2/pulsesg_dpt1/token/";
//	public static String dsWorkDayURL = "https://wd2-impl-services1.workday.com/api/wql/v1/pulsesg_dpt1/data";
//
//    // Catalog
//    public static String catalogName = "TestAutCatalog_";
//    public static String catalogType = "Metric Catalog";
//    public static String catalogDesc = "TestAutCatalogDesc";
//    public static String UITests = "ui";
//    public static String APITests = "api";
//    public static final String Path_TestData = System.getProperty("user.dir")
//	    + "\\src\\test\\resources\\TestData\\MultipleTestData.xlsx";
//
//    public static String DRRejectComments = "Rejected due to Invalid Data  Please Add Data Again";
//    // API
//    public static String accessTokenPostURL = "/kc/realms/PulsESG/protocol/openid-connect/token";
//    public static String accessTokenUserName = "rapidsos-org-admin-user1";
//    public static String accessTokenPassword = "rapidsos-org-admin-user1";
//    public static String SigngleSetData = "SingeSet";
//    public static String MultiSetData = "MultiSet";
//    public static String overlapPeriodYear2022 = "Year2022";
//    public static String datePrdstrt = "2022/01/01";
//    public static String datePrdend = "2022/12/31";
//    public static String leapPrdstrt = "2020/01/01";
//    public static String leapPrdend = "2020/12/31";
//    public static String leapDayStart = "2020/01/01";
//    public static String leapDayEnd = "2020/12/31";
//    public static String UIExcelDataFile = "TestData";
//    public static String APIExcelDataFile = "APITestData";
//    // Calc Sheet Names
//    public static String BusinessTravelDistBasedCalc = "BusinessTravelDistBasedCalc"; // BusinessTravelDistBasedCalc
//    public static String BusinessTravelSpendBasedCalc = "BusinessTravelSpendBasedCalc";
//    public static String CalculatorStationaryCombustion = "CalculatorStationaryCombustion";
//    public static String CalculatorMobileCombution = "CalculatorMobileCombution";
//    public static String RefrigerantsandFugitives = "RefrigerantsandFugitives";
//    public static String DownStreamLeasedAsset = "DownStreamLeasedAsset";
//    public static String DonwstreamWeight_DistanceMethod = "DonwstreamWeight-DistanceMethod";
//    public static String Franchises = "Franchises";
//    public static String FuelandEnergyRelatedActivit = "FuelandEnergyRelatedActivit";
//    public static String HomeOfficeTelecommuting = "HomeOfficeTelecommuting";
//    public static String HotelStay = "HotelStay";
//    public static String PurchasedElectricityRegres = "PurchasedElectricityRegres";
//    public static String PurchasedEnergy = "Purchased Energy";
//    public static String Investments = "Investments";
//    public static String InvestmentsAverageDataMethod = "InvestmentsAverageDataMethod";
//    public static String InvestmentsDebtProjectSpecific = "InvestmentsDebtProjectSpecific";
//    public static String InvestmentsDebtProjectAverage = "InvestmentsDebtProjectAverage";
//    public static String UpstreamLeasedAsset = "UpstreamLeasedAsset";
//    public static String UpstreamWeight_DistanceMethod = "UpstreamWeight-DistanceMethod";
//    public static String UseOfSoldProducts = "UseOfSoldProducts";
//    public static String WasteGeneratedInOpsCalculator = "WasteGeneratedInOpsCalculator";
//    public static String CapitalGoodsAvreageBased = "CapitalGoodsAvreageBased";
//    public static String CapitalGoodsSpendBased = "CapitalGoodsSpendBased";
//    public static String EmployeeCommutingCalculator = "EmployeeCommutingCalculator";
//    public static String PurchasedGoodsAndServicesAverag = "PurchasedGoodsAndServicesAverag";
//    public static String PurchaseGoodsServiSpendBased = "PurchaseGoodsServiSpendBased";
//    public static String EndOfLifeTreatmentofSoldProduct = "EndOfLifeTreatmentofSoldProduct";
//    public static String ProcessingOfSoldProducts = "ProcessingOfSoldProducts";
//    // CEF
//    public static String customEmissionFactor = "TestAutCEF";
//    public static String CEFName = "AutTestCEF";
//    public static String catalogDRPublishBlocker = "DRCatalogPublishBlocker";
//    public static String CatalogDRMetricAllInputTypesEntry = "DRMetricAllInputTypesEntry";
//    public static String standardErrorMessageMandatoryEvidence = "Evidence required for this metric";
//    public static String standardErrorMessageExplanationMandatory = "Explanation Mandatory";
//    public static String inputTypeRequiredErrorMessage = "Please enter a value or select “Data not available for this required metric.”";
//    public static String customErrorMessageMandatoryEvidence = "Required evidence for chosen / selected value. Please attach evidences for this metric.";
//    public static String customErrorMessageExplanationMandatory = "Please provide the explanation for the chosen / selected Value";
//    public static String RequiredErrorMessageWithoutDNA = "Please enter a value for this required metric.";
//    public static String inputTypeRequiredErrorMessageText = "Expression validation is based on : [a-z]";
//    public static String RequiredErrorMessageWithDNA = "Please enter a value or select “Data not available for this required metric.”";
//    public static String catalogAggregations = "CatalogAggregations";
//    public static String DRatalogAggregations = "DRCatalogAggregations";
//
//    public static String textMetricTableValue = "Test1";
//    public static String drpGenderBooleanValue = "male";
//    public static String drpDownIsActiveValue = "true";
//    public static String drpMultiSelectFavCar = "Audi";
//    public static String texttxtAge = "28";
//    public static String tableUniqueKeyErrorMessage = "Duplicate record detected. Please enter Name column(s) as unique values across all records.";
//    public static String tableMetricSavedToastMessage = "Metric detail saved successfully.";
//
//}
