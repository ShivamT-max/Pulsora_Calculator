package utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Constants {
	// Catalog
	public static String DRChipsValidation = "DRChipsValidation";
	public static String catalogAllInputTypes = "CatalogAllInpuTypes";
	public static String catalogValidationsconfig = "CatalogMultipleInputValidations";
	public static String createCatalogwithValidations = "createCatalog";
	// Catalog
	public static String catalogName = "TestAutCatalog_";
	public static String catalogType = "Metric Catalog";
	public static String catalogDesc = "TestAutCatalogDesc";
	public static String UITests = "ui";
	public static String APITests = "api";
	public static final String Path_TestData = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\TestData\\MultipleTestData.xlsx";
	public static String DRRejectComments = "Rejected due to Invalid Data  Please Add Data Again";
	// API
	public static String accessTokenPostURL = "/kc/realms/PulsESG/protocol/openid-connect/token";
	public static String accessTokenUserName = "rapidsos-org-admin-user1";
	public static String accessTokenPassword = "rapidsos-org-admin-user1";
	public static String SigngleSetData = "SingeSet";
	public static String MultiSetData = "MultiSet";
	public static String overlapPeriodYear2022 = "Year 2024";
	public static String overlapPeriodYear2022prod = "FY 2022";  //naveen
	public static String overlapPeriodYear2023preprod = "Year 2022";    //naveen
	public static String overlapPeriodYear2023prod = "FY23";       //naveen
	public static String overlapPeriodYearAll = "All";
	public static String overlapPeriodYear2024 = "FY 2024";
	public static String datePrdstrt = "2022/01/01";
	public static String datePrdend = "2022/12/31";
	public static String leapPrdstrt = "2020/01/01";
	public static String leapPrdend = "2020/12/31";
	public static String leapDayStart = "2020/01/01";
	public static String leapDayEnd = "2020/12/31";
	public static String UIExcelDataFile = "TestData";
	public static String APIExcelDataFile = "APITestData";
	// Calc Sheet Names
	
	public static String conToTonnfrg = "g";
	public static double confromgramtoKg = .001;
	public static String conToTonnfrlb = "lb";
	public static String conToTonnfrKg = "kg";
	public static String conToTonnfrTonn = "t";
	public static double GWParCO2 = 1;
	public static double GWPar5CH4 = 28;
	public static double GWPar5N2O = 265;
	public static double GWPar4CH4 = 25;
	public static double GWPar4N2O = 298;
	public static double GWPar6CH4 = 27.9;
	public static double GWPar6N2O = 273;
	public static String navigationType = "Business";
	public static String BusinessTravelDistBasedCalc = "BusinessTravelDistBasedCalc"; // BusinessTravelDistBasedCalc
	public static String BusinessTravelSpendBasedCalc = "BusinessTravelSpendBasedCalc";
	public static String CalculatorStationaryCombustion = "CalculatorStationaryCombustion";
	public static String CalculatorMobileCombution = "CalculatorMobileCombution";
	public static String RefrigerantsandFugitives = "RefrigerantsandFugitives";
	public static String DownStreamLeasedAsset = "DownStreamLeasedAsset";
	public static String DonwstreamWeight_DistanceMethod = "DonwstreamWeight-DistanceMethod";
	public static String Franchises = "Franchises";
	public static String FuelandEnergyRelatedActivit = "FuelandSample";
	public static String HomeOfficeTelecommuting = "HomeOfficeTelecommuting";
	public static String HotelStay = "HotelStay";
	public static String PurchasedElectricityRegres = "PurchasedElectricityRegres";
	public static String PurchasedEnergy = "Purchased Energy";
	public static String Investments = "Investments";
	public static String InvestmentsAverageDataMethod = "InvestmentsAverageDataMethod";
	public static String InvestmentsDebtProjectSpecific = "InvestmentsDebtProjectSpecific";
	public static String InvestmentsDebtProjectAverage = "InvestmentsDebtProjectAverage";
	public static String UpstreamLeasedAsset = "UpstreamLeasedAsset";
	public static String UpstreamWeight_DistanceMethod = "UpstreamWeight-DistanceMethod";
	public static String UseOfSoldProducts = "UseOfSoldProducts";
	public static String WasteGeneratedInOpsCalculator = "WasteGeneratedInOps (2)";
	public static String CapitalGoodsAvreageBased = "CapitalGoodsAvreageBased";
	public static String CapitalGoodsSpendBased = "CapitalGoodsSpendBased";
	public static String EmployeeCommutingCalculator = "EmployeeCommutingCalculator";
	public static String PurchasedGoodsAndServicesAverag = "PurchasedGoodsAndServicesAverag";
	public static String PurchaseGoodsServiSpendBased = "PurchaseGoodsServiSpendBased";
	public static String EndOfLifeTreatmentofSoldProduct = "EndOfLifeTreatmentofSoldProduct";
	public static String ProcessingOfSoldProducts = "ProcessingOfSoldProducts";
	public static String PurchasedEnergyUnitConversion = "UnitConversionScope2";
	public static String calcFacilityEFDataSets = "FacilityEFDataSets";
	// CEF
	public static String customEmissionFactor = "CEF_INDE_";
	public static String CEFName = "AutTestCEF";
	
	public static String catalogDRPublishBlocker = "DRCatalogPublishBlocker";
	public static String CatalogDRMetricAllInputTypesEntry = "DRMetricAllInputTypesEntry";
	public static String DRMetricAllInputTypesEntrySimpl = "DRMetricAllInputTypesEntrySimpl";
	public static String standardErrorMessageMandatoryEvidence = "Evidence required for this metric";
	public static String standardErrorMessageExplanationMandatory = "Explanation Mandatory";
	public static String inputTypeRequiredErrorMessage = "Please enter a value or select “Data not available for this required metric.”";
	public static String customErrorMessageMandatoryEvidence = "Required evidence for chosen / selected value. Please attach evidences for this metric.";
	public static String customErrorMessageExplanationMandatory = "Please provide the explanation for the chosen / selected Value";
	public static String RequiredErrorMessageWithoutDNA = "Please enter a value for this required metric.";
	public static String inputTypeRequiredErrorMessageText = "Expression validation is based on : [a-z] ";
	public static String RequiredErrorMessageWithDNA = "Please enter a value or select “Data not available for this required metric.”";
	public static String catalogAggregations = "CatalogAggregations";
	public static String DRatalogAggregations = "DRCatalogAggregations";
	public static String textMetricTableValue = "Test1";
	public static String drpGenderBooleanValue = "male";
	public static String drpDownIsActiveValue = "true";
	public static String drpMultiSelectFavCar = "Audi";
	public static String texttxtAge = "28";
	public static String tableUniqueKeyErrorMessage = "Duplicate record detected. Please enter Name column(s) as unique values across all records.";
	public static String tableMetricSavedToastMessage = "Metric detail saved successfully";
	// DS
	public static String dsWorkDayUsername = "MTIyYzNkOTMtNTk0Mi00NzkwLWE4OTEtMTJiMDA2ZWM1MjVl";
	public static String dsWorkDayPassword = "jpa3sfxi7m1280mvodt9gqs4pi8evuykyx3iq4yhs6kwa87k2bo40vv7defkge06jb1svidj2k97xrdbacl98rrx1koh3j535cb";
	public static String dsWorkDayRefreshtoken = "e4m64dmmflxoqfqfhzbuzefwqj7erjihh1o3nxlv0wehirrcgs4ppkq9wh96u4gkv8r31ciczetc3qfgtvbtzwyr2km9740gnxs";
	public static String dsWorkDayTokenendpointURL = "https://wd2-impl-services1.workday.com/ccx/oauth2/pulsesg_dpt1/token/";
	public static String dsWorkDayURL = "https://wd2-impl-services1.workday.com/api/wql/v1/pulsesg_dpt1/data";
	public static String unitConvlbtoton = "0.000453592";
	public static String WQLQueryDS = "SELECT createdMoment, lastUpdated, supplierInvoiceDocument{invoiceNumber}, referenceID1, supplierInvoiceSpendType, spendCategoryAsWorktag, documentDate, cf_Year_Month, invoiceAccountingDate, supplierforAbstractSpendLine,location{primaryAddress_Full,"
			+ " primaryAddress_Line1, primaryAddress_Line2, primaryAddress_City, primaryAddress_StateProvince, country, primaryAddress_PostalCode} as location, ship_ToAddress_Full, ship_ToAddress{ addressLine31, addressLine1, city, country, postalCode} as shipToAddress, quantity as kWh, unitCost, invoiceLineUnitCost,"
			+ " supplierforAbstractSpendLine{defaultAddress} as Supplier, costCenter, invoiceLineDetailDescription, supplierInvoiceStatus,  cf_InvoiceAmount, grossAmount,    cf_InvoiceDueDate, cf_SupplierInvoiceLineSupplierBillToAddress FROM supplierInvoiceLines "
			+ "(dataSourceFilter = supplierInvoiceLinesForHeaderCompanyFilter, company = 980834d277264d308b75094eebff1f3e) WHERE ON cf_SupplierContingentWorker isContingentWorker != true WHERE ((createdMoment >= '2023-01-01T00:00:00Z') OR (lastUpdated >= '2023-01-01')) AND spendCategoryAsWorktag in (6b80fb8fc21745328a145e5fa228d414, 8f201b28765410d3d3da87ea1042191c) "
			+ "AND (quantity > 0) ORDER BY cf_Year_Month ASC";
	public static String[] unitTypeEnergy = { "kilowatt-hour (kWh)", "megawatt-hour (MWh)", "gigawatt-hour (GWh)",
			"therm (thm)", "British Thermal Unit (BTU)", "Metric Million British Thermal Unit (MMBTU)",
			"megajoule (MJ)", "gigajoule (GJ)", "kilojoule (KJ)", "dekatherm (dth)" };
	public static String[] unitTypesMass = { "kilogram (kg)", "gram (g)", "metric ton (t)", "short ton (ton)",
			"pound (lb)", "long ton (LT)" };
	public static String[] unitTypesVolume = { "gal (US)", "barrel (bbl)", "hundred cubic feet (ccf)",
			"cubic yard (yd3)", "kilolitre (kl)", "Normal cubic meter (Nm3)", "Thousand Cubit Feet (MCF)",
			"cubic centimeter (cm3)", "litre (l)", "Standard cubic meter (Sm3)", "standard cubic foot (scf)",
			"Million Cubic Feet (MMCF)" };
	public static String CalculatorStationaryCombustionUnit = "SCDatasetUnitValidations";
	public static String CalculatorStationaryCombustionUnit_EPA = "SCDatasetUnitValidationsEPA";
	public static String CalculatorStationaryCombustionUnit_IEA = "SCDatasetUnitValidationsIEA";
	public static String[] arrAR4Values = { "2007 IPCC Fourth Assessment (AR4)", "1", "25", "298" };
	public static String[] arrAR5Values = { "2014 IPCC Fifth Assessment (AR5)", "1", "28", "265" };
	public static String periodAll = "All";
	// Catalog Config
	public static String customMetricInputTypes = "CustomMetricInputTypes";
//	public static String createCatalogTopicAndMetrics = "CreateCatalogTopicAndMetrics";//undo comment 
//	public static String createCatalogTopicAndMetrics = "CreateCatalogTopicAndMetric_2";
	public static String createCatalogTopicAndMetrics = "CreateCatalogTopicAndMetricRege";
	public static String createCustomCatalogTopicAndMetrics = "CreateCatalogTopicAndMetric_2";
	public static String createCatalogTopicAndMetrics_DR = "CreateCatalogTopicAndMetric_DR";
	public static String MetricValidationDRFlow = "MetricValidationDRFlow";
	public static String createCatalogTopicAndMetricsValidations = "CatalogValidationsRegression";
	public static String[] standardValidationMethods = { "Explanation Mandatory", "Mandatory Evidence",
			"Related Metric", "Formula" };
	public static String[] textValidationMethods = { "Explanation Mandatory", "Mandatory Evidence", "Expression",
			"Formula" };
	public static String[] numericValidationMethods = { "Explanation Mandatory", "Mandatory Evidence", "Total",
			"Expression", "Threshold", "Threshold % of Previous Value", "Threshold % of Value for Previous Period",
			"Formula" };
	public static String DNA_ExplanationMethod = "Explanation mandatory (Data not available selected)";
	public static String[] allInputTypes = { "Boolean", "Text", "MultiSelect", "SingleSelect", "Number", "Integer",
			"Description", "Table", "Explanation mandatory (Data not available selected)" };
	public static String[] tableInputTypes = { "Boolean", "Text", "MultiSelect", "SingleSelect", "Number", "Integer",
			"Description" };
	public static String OptionNone = "None";
	public static String[] booleanAggregationRules = { "Any of", "All of" };// None Default
	public static String[] numericAggregationRules = { "Average", "Last", "Sum", "Median" };// None Default
	public static String[] metricTypeOptions = { "Reporting practice", "Ethics and integrity", "Stakeholder engagement",
			"Organizational profile", "Governance", "N/A", "Accounting Metric" };
	public static String catalogConfigVali = "CatalogConifigValidation";
	public static String TiffanySitchOrgDRFlow = "TiffanySitchOrgDRFlow";
	public static String AdminPeriod = "AdminPeriod";
	public static String AddOrg = "AddOrganizations";
	public static String  AddUsers = "Orgs";
	public static String[] chinaCountry = {};// None Default

	public static String DRTiffanyFlow = "TiffanyDRWorkFlow";
	public static String TiffanyDRTransactionBill = "TransactionBill";
	public static String InvoiceNo = "INVOICE";
	public static String InstructionCatalog = "a Principal Software Development Engineer in Test, I bring over a decade of experience in automating Web UI, API, and Mobile tests using tools like Selenium WebDriver, Appium, Postman, RestAssured, and more. I began my journey as a manual QA engineer and quickly developed a passion for test automation. Having served as the lead test automation expert in various projects, I’m confident in my ability to help you excel in test automation.";

	
	
	
	public static String selectedGWP = "2007 IPCC Fourth Assessment (AR4, 100-Year GWP)";           //AR4
//	public static String selectedGWP = "2014 IPCC Fifth Assessment (AR5, 100-Year GWP)";           //AR5
	//public static String selectedGWP = "2023 IPCC Sixth Assessment (AR6, 100-Year GWP)";            //AR6
	//
	//
	public static String RefrigerantsandFugitives_RefrigerantUsed = "RefrigerantUsed";
	public static String StationaryCombustionCalorifcVal = "StationaryCombustionCalorifcVal"; 
	public static String MobileCombustionCalorifcVal = "MobileCombustionCalorifcVal";
	public static String datasets = "datasetsValidationStationary";
	public static String MCStandardFuelEfficiency = "MCStandardFuelEfficiency";
	
	public static String[] converionOfUnitsToMMBTU = {"1055055.85262","252164.432603688","293.071038661345","0.29307107044088","0.00029307107044",
			                            "10","1000000","1","1055.05598654592","1.05505585296876","0.00105505585296","1"};  
	
	
	
	//fuel types
	public static String[] fuelsIEA = {"Aviation Gasoline","Bitumen","Coal tar","Coke oven coke","Coking coal",
            "Fuel oil","Gas/diesel oil excl bio","Kerosene","Kerosene type jet fuel excl bio",
            "Liquefied petroleum gases",
            "Lubricants","Motor gasoline excl bio","Naphtha","Natural Gas","Natural gas liquids",
            "Non-specified oil products","Other bituminous coal","Petroleum Coke","Refinery gas","White spirit"};
	
	
	
	/* 
		 * Types of units 
		 * Mass
		 * Volume
		 * Energy
	*/
	 public static String[] unitNamesMass = {"long ton (LT)","short ton (ton)","kilogram (kg)","gram (g)",
                                             "metric ton (t)","pound (lb)","Gigagram (Gg)"
                                            };
	 
	 public static String[] unitNamesVolume = {"Standard cubic meter (Sm3)","standard cubic foot (scf)","cubic yard (yd3)",
			                                   "kilolitre (kl)","Normal cubic meter (Nm3)","Thousand Cubit Feet (MCF)",
			                                   "Million Cubic Feet (MMCF)","gal (US)","barrel (bbl)","hundred cubic feet (ccf)",
			                                   "cubic centimeter (cm3)","litre (l)"
			                                  };
	
	 public static String[] unitNamesEnergy = {"kilojoule (KJ)","kilocalories (kcal)",                                        //Energy
		                                        "kilowatt-hour (kWh)", "megawatt-hour (MWh)", "gigawatt-hour (GWh)",
			                                    "therm (thm)", "British Thermal Unit (BTU)", "Metric Million British Thermal Unit (MMBTU)",
			                                    "megajoule (MJ)", "gigajoule (GJ)", "Terajoule (TJ)", "dekatherm (dth)" };
	
	
	
	
	
	public static String funType;
	
	
	                    // -----------------------   Add Activity Related -------------------------------------//
	
		public static String[] actSC                   = { "Facility Name","Start Date", "End Date","Fuel Type", "Fuel Amount", "Unit"
	                                                     };
	    public static String[] actMC                   = { "Facility Name", "Invoice No.","Start Date","End Date","Vehicle Name",
	                                                                   "Activity Amount", "Unit", //"Biogenic tCO2" 
	                                                                  };
	public static String[] actFugitive                = { "Facility Name", "Invoice No.", "Refrigerant used","Start Date", "End Date",
	                                                      "Invoice Date","Refrigerant used","Type of equipment"
	                                                    };
	public static String [] actPurchAvg       = { "Facility Name","Start Date","End Date","Purchased Goods Category",
		                                                               "Purchased Good","Production Process Involved",
		                                                               "Quantity of Goods Purchased","Units"
		                                                              };
		public static String[] actDetPurchGoodsSpend  = { "Facility Name",  "Purchased Goods or Services",
		                                                               "Unit/Currency", //"Source",
		                                                               //"Amount Spent" 
		                                                             };
	public static String[] activityDetailsCapitalGoodsAvg         = { "Facility Name", "Description", "Capital Goods Category",
														              "Capital Good", "Production Process Involved", "Units",
														              "Quantity of Goods Purchased",// "Source" 
	          													     };
	public static String[] activityDetailsCapitalGoodsSpend       ={ "Facility Name", "Description", "Capital Goods Category",
														            "Capital Good", "Production Process Involved", "Unit/Currency",
														             //"Source"
														           };
	public static String[] activityDetailFieldsHotelStay          = { "Facility Name", "Number of Rooms Occupied",
																      "Units", "Country of Hotel Stay",// "Source",
																      "Number of Nights Stayed" 
																    };
	public static String[] activityDetailFieldsULA                = { "Facility Name", "Description", "Leased Asset", "Energy Category",
																   "Type", "Activity Amount","Units",   //, "tCO2e"
																   "Biofuel tCO2",// "Source"
																   };
		public static String[] activityDetailFieldsDLA             = { "Facility Name", "Description", "Energy Category", "Type",
																	   "Activity Amount","Units",
																	   "Biofuel tCO2", //"Source" 
																	 };
		public static String[] activityDetailFieldsUOSP            = { "Facility Name", "Description","Start Date", "End Date", "Sold Product",
															            "Number of Units Sold",
															          }; 
		public static String[] activityDetailFieldsEOLT            = { "Facility Name", "Description", "Product Waste Category",
																	   "Product Waste Type", "Waste Disposal Method", "Unit",
																	   //"Mass Of Waste after Consumer Use",// "Source"
																	 };
		public static String[] activityDetailFieldsPOSP            = { "Facility Name", "Description", "Sold Product", "Process Type",
															           "Mass of Sold Product", "Units", //"Source"
															          };
		
		public static String[] activityDetailsInvestmentsSpecific = { "Investee Company", "Investee Company Sector", "Reporting company's share of equity (%)",
																	  "Scope 1 - (tCO2e) Emissions of investee company",
																	  "Scope 2 - (tCO2e) Emissions of investee company",
																	  "Scope 3 - (tCO2e) Emissions of investee company",
																	  "Total GHG Emissions - (tCO2e) of investee company", "Source of Emissions Data",
																	  "Start Date", "End Date" };
		
		public static String[] actDtailsInvestmentsAvgDataMethod = { "Investee Company", "Investee Company Sector",
																	 "Investee Company's Revenue",
																	"Investee company's revenue (%) from selected sector", 
																	"Reporting company's share of equity (%)","Start Date", "End Date"};
																	//"Total GHG Emissions (tCO2e)", 
		
		public static String[] actDetailsDebtSpecificMethod = { "Investee Company/Project", "Investee Company/Project Sector",
															"Value of Debt Investment", "Total Project Cost", "Units",
															"Scope 1 - (tCO2e) Emissions of investee company",
															"Scope 2 - (tCO2e) Emissions of investee company",
															"Scope 3 - (tCO2e) Emissions of investee company",
															"Total GHG Emissions - (tCO2e) of investee company",
															"Share of total project costs (percent)", "Source of Emissions Data","Start Date", "End Date" };
		
		public static String[] activityDetailsDebtAvgMethod = { "Investee Company/Project", "Investee Company/Project Sector",
																"Project Phase","Value of Debt Investment",
																"Total Project Cost", "Share of total project costs (percent)",// "Total GHG Emissions (tCO2e)",
															"Start Date", "End Date"};
		
		
		
		
		// ----------------------------------------  Add CEF Related ---------------------------------------------//
		
		public static String[] cefDetailsSC             = { "Source of Emission Factor",
				                                          "Unit of Custom EF (Denominator)", "CO2", "CH4", "N2O", "Unit of Custom EF (Numerator)" };
		public static String[] cefDetailsMC             = { "Source of Emission Factor",
				                                            "Unit of Custom EF (Denominator)", "CO2", "CH4", "N2O", "Unit of Custom EF (Numerator)" };;
		public static String[] cefDetailsPurchAverage   = { "Calculation Method", "Purchased Goods Category", "Purchased Good",
															"Unit of Custom EF (Denominator)", "CO2e", "Source of Emission Factor",
															"Unit of Custom EF (Numerator)", "Notes" };
		public static String[] cefDetailsPurchSpend     = { "Calculation Method", "Purchased Goods Category", "Purchased Good",
															"Source of Emission Factor", "CO2e", "Unit/Currency", 
															"Unit of Custom EF (Numerator)", "Notes" };
		public static String[] cefDetailsCapitlAvg      = { "Calculation Method", "Capital Goods Category",
															"Production Process Involved", "Capital Good", "Unit of Custom EF (Denominator)", "CO2e",
															"Source of Emission Factor", "Unit of Custom EF (Numerator)", "Notes" };
		public static String[] cefDetailsHotelStay      = { "Unit of Custom EF (Denominator)", "Source of Emission Factor",
															"CO2e", "Unit of Custom EF (Numerator)", "Notes" };
		public static String[] cefDetailFieldNamesDLA   = { "Energy Category", "Source of Emission Factor",
				                                            "Unit of Custom EF (Denominator)", "CO2", "CH4", "N2O", 
				                                            "Unit of Custom EF (Numerator)", "Notes" };
		public static String[] cefDetailFieldNamesULA   = { "Energy Category", "Source of Emission Factor",
															"Unit of Custom EF (Denominator)", "CO2", "CH4", "N2O", 
															"Unit of Custom EF (Numerator)", "Notes" };
		public static String[] cefDetailFieldNamesUOSP = { "Sold Product", "Product Life Time (Years)", "CO2e (Yearly)",
														   "Units of CO2e", "Source" };
		public static String[] cefDetailFieldNamesPOSP = { "Sold Product", "Process Type", "Source of Emission Factor", "CO2e",
														   "Unit of Custom EF (Denominator)", "Unit of Custom EF (Numerator)", "Notes" };
		public static String[] cefDetailFieldNamesEOLT = { "Product Waste Category", "Product Waste Type",
														   "Waste Disposal Method", "Unit of Custom EF (Denominator)", "CO2e", "Source of Emission Factor",
														   "Unit of Custom EF (Numerator)", "Notes" };
		public static String[] cefDetailFieInvestments = { "Sector", "Source of Emission Factor","Unit of Custom EF (Denominator)", 
															"CO2e", "Unit of Custom EF (Numerator)" };
		
		public static List<String> arrayCardPresent = new ArrayList<String>();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		 public static String DBURL = "jdbc:postgresql://pulsesg-qa-pg.cluster-ro-cfe4acbez05a.us-east-1.rds.amazonaws.com/pulsesg-data";
		    public static String DBUserName = "pulsesgdata";
		    public static String DBPassword = "xb44YjZR";
		    public static String dbDriverName = "org.postgresql.Driver";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
