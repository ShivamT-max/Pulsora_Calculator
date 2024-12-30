package uielements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.TestBase;
import utilities.Data;

public abstract class CalculatorElements extends TestBase {
	protected CalculatorElements(WebDriver driver2, Data data) {
		super(driver2, data);
	}
	
	
	// -----------Common--------------------------------------Start--------------------------------

	@FindBy(xpath = "//span[text()='Name of Custom EF']/parent::div")
	public WebElement fullAGGridNameOFCEF;
	//------------Transactions------------
	@FindBy(xpath = "//div[text()='Transactions']")
	public WebElement lblTransactions;
	@FindBy(xpath = "//div[text()='Filter By Organization']/parent::div//div[contains(@class,'TreeView_container__K73xj')]")
	public WebElement verifyOrgViewScreen;
	@FindBy(xpath = "//div[@ref='centerContainer' and @role='presentation']")
	public WebElement verifyBillGrid;
	@FindBy(xpath = "(//div[text()='Fuel Bills'])[2]")
	public WebElement lblFuelBills;
	@FindBy(xpath = "//*[text()='Add Transaction']")
	public WebElement btbAddTransaction;
    @FindBy(xpath = "(//div[text()='Vehicle Fleet'])[2]")
    public WebElement lblVehicleFleet;
    @FindBy(xpath = "//span[text()='Invoice No']//parent::div//following-sibling::div//input")
    public WebElement txtInvoiceNoForAddTransactionScope1;
    @FindBy(xpath = "//span[text()='Fuel Type']/parent::div/following-sibling::div//*[local-name()='svg']")
    public WebElement drpFuelTypeForAddTransactionScope1;
    @FindBy(xpath = "//span[text()='Meter Number']/parent::div/following-sibling::div//*[local-name()='svg']")
    public WebElement drpMeterNumber;
    @FindBy(xpath = "//span[text()='Meter Number']/parent::div/following-sibling::div//*[local-name()='svg']")
    public WebElement drpUnitsForAddTransactionScope1;
    @FindBy(xpath = "//span[text()='Currency']/parent::div/following-sibling::div//*[local-name()='svg']")
    public WebElement drpCurrencyForAddTransactionScope1;
    @FindBy(xpath = "//span[text()='Notes']//parent::div//following-sibling::div//textarea")
    public WebElement txtNotesForAddTransaction;
    @FindBy(xpath = "//span[contains(text(),'Amount')]//parent::div//following-sibling::div//input")
    public WebElement txtAmount;
    @FindBy(xpath = "//span[text()='Cost']//parent::div//following-sibling::div//input")
    public WebElement txtCost;
    @FindBy(xpath = "//div[text()='Transaction added successfully'")
    public WebElement toastMessageForAddingTransaction;
	@FindBy(xpath = "//*[contains(text(),'Amount')]/parent::div/following-sibling::div/div/div/input")
	public WebElement btnAddAmountOfEnergy;
	//span[text()='Units']/parent::div/following-sibling::div//*[local-name()='svg']
	@FindBy(xpath = "//span[text()='Units']/parent::div/following-sibling::div")
	public WebElement drpUnitsTiffany;
	@FindBy(xpath = "//input[@name='cost']")
	public WebElement btnCost;
	@FindBy(xpath = "//span[text()='Currency']/parent::div/following-sibling::div")
	public WebElement drpcurrencytype;
	@FindBy(xpath = "")
	public WebDriver btncurrency;
	@FindBy(xpath = "//*[@placeholder='Notes']")
	public WebElement txtNotes;
	@FindBy(xpath = "//input[@name='invoice_no']")
    public WebElement inputInvoiceTiffany;
	@FindBy(xpath = "//span[text()='Waste Category']/parent::div/following-sibling::div/div/input")
	public WebElement drpWastwCategory;
	@FindBy(xpath = "//span[text()='Waste Type']/parent::div/following-sibling::div/div/input")
	public WebElement drpWastwtype;
	@FindBy(xpath = "//span[text()='Waste Disposal Method']/parent::div/following-sibling::div/div/input")
	public WebElement drpWastdisposalMethod;
	@FindBy(xpath = "//span[text()='Mass of Waste Produced']/parent::div/following-sibling::div//input[@name='waste_produce']")
	public WebElement txtMassOfwasteProduced;
	@FindBy(xpath = "//span[text()='Vehicle']/parent::div/following-sibling::div")
	public WebElement drpVehicleForTransactionScope1;
    @FindBy(xpath = "//span[text()='Number of Vehicles']//parent::div//following-sibling::div//input")
    public WebElement txtNumberOfVehicles;
	//--------------
	@FindBy(xpath = "//button[text()='Save']")
	public WebElement btnSave;
	@FindBy(xpath = "//span[text()='Activities']/following-sibling::article//*[local-name()='svg']")
	public WebElement btnAddActivities;
	@FindBy(xpath = "//span[text()='Next']//parent::button//following::div/*[@data-testid='BorderColorOutlinedIcon']") // span[text()='Next']//parent::button//following-sibling::button[@type='submit']
	public WebElement btnEditActivity;
	@FindBy(xpath = "//*[text()='Activity Details']")
	public WebElement lblViewActivity;
	@FindBy(xpath = "(//span[text()='Facility Name']//parent::div//following-sibling::div//input//following::button)[2]")
	public WebElement drpFacilityName;
	@FindBy(xpath = "//span[text()='Start Date']//parent::div//following-sibling::div//input")
	public WebElement txtStartDate;
	
	@FindBy(xpath = "//span[text()='End Date']//parent::div//following-sibling::div//input")
	public WebElement txtEndDate;
	@FindBy(xpath = "//span[text()='Description']//parent::div//following-sibling::div//input") // textarea[@id='description']
	public WebElement txtDescription;
	// --------------Common--------------------------------------End--------------------------------
	// -------------------------------Start StatComb--------------------------
	@FindBy(xpath = "//li[contains(text(),'GHG Calculators')]")
	public WebElement lblGHGCalculator;
	@FindBy(xpath = "//*[contains(text(),'SCOPE 2')]")
	public WebElement btnIndirectEmisssion;
	@FindBy(xpath = "//span[contains(text(),'Activities')]//parent::div//article")
	public WebElement btnbtnActivities;
	
	
	
	
	
	
	@FindBy(xpath = "//button[@title='Open']")
	public WebElement drpFacName;
	@FindBy(xpath = "//*[@id='FacilityName']")
	public WebElement txtFacName;
	@FindBy(xpath = "//div/ul/li[contains(text(),'Electricity')]")
	public WebElement drpEclectricity;
	@FindBy(xpath = "//input[@id='InvoiceNo']")
	public WebElement txtInvoiceNo;
	// .............Parameter Input................
	@FindBy(xpath = "//h5[contains(text(),'Custom Emission Factor')]")
	public WebElement lblAddCutsomEmissionFactor;
	@FindBy(xpath = "//h5[contains(text(),'Edit Custom Emission Factor')]")
	public WebElement lblEditCutsomEmissionFactor;
	@FindBy(xpath = "//button[text()='Parameter Input']")
	public WebElement btnParameterInput;
	@FindBy(xpath = "//span[text()='Custom Emission Factors']/following-sibling::article//*[local-name()='svg']")
	public WebElement btnAddCstmEmsonFctr;
	@FindBy(xpath = "(//div[@aria-colindex='1'][@role='gridcell'])[1]")
	public WebElement btnEditCstmEmsonFctr;
	@FindBy(xpath = "//input[@id='Efname']")
	public WebElement txtCutsomEFName;
	@FindBy(xpath = "//span[contains(text(),'Activity Type')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpActivityType;
	@FindBy(xpath = "//input[@id='SourceOfEmission']")
	public WebElement txtSourceCEF;
	@FindBy(xpath = "//span[contains(text(),'Unit of Custom EF (Denominator)')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpUnitCEFDenominator;
	@FindBy(xpath = "//*[@id='Mode of Transport']")
	public WebElement txtModeofCommute;
	@FindBy(xpath = "//span[text()='Vehicle Type/Commute Category']/parent::div/following-sibling::div/div/*[@id='Vehicle Type']")
	public WebElement txtVehicleType;
	@FindBy(xpath = "//input[@id='CO2']")
	public WebElement txtCO2;
	@FindBy(xpath = "//input[@id='CH4']")
	public WebElement txtCH4;
	@FindBy(xpath = "//input[@id='N2O']")
	public WebElement txtN2O;
	@FindBy(xpath = "//input[@id='BiofuelCO2']")
	public WebElement txtBiofuelCO2;
	@FindBy(xpath = "//span[contains(text(),'Unit of Custom EF (Numerator)')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpUnitCEFNumerator;
	@FindBy(xpath = "//button[text()='Save']")
	public WebElement btnSaveParemeterInput;
	@FindBy(xpath="//li[@aria-label='GHG Calculators']")
	public WebElement btnGHGCalc;
	@FindBy(xpath = "//button[contains(text(),'Stationary Combustion')]")
	public WebElement btnStationaryCombution;
	@FindBy(xpath = "//span[contains(text(),'Are you using a Custom Emission Factor?')]//following::input[1]")
	public WebElement btnCustomBasedCEF;
	@FindBy(xpath = "//span[contains(text(),'Custom Emission Factor')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpCustomEmiFactorActivity;
	@FindBy(xpath = "//input[@id='emissionName']")
	public WebElement txtvalActivityCEF;
	@FindBy(xpath = "//input[@id='emissionName']")
	public WebElement drpvalActivityCEF;
	@FindBy(xpath = "//*[@id='notes']")
	public WebElement txtNotesCEF;
	@FindBy(xpath = "//input[@id='calorific_factor']")
	public WebElement txtCalorificFactor;
	@FindBy(xpath = "//input[@id='calorific_num']")
	public WebElement drpCalorificFactorNumerator;
	@FindBy(xpath = "//input[@id='calorific_deno']")
	public WebElement drpCalorificFactorDenominator;
	@FindBy(xpath="//input[@id='tag_id']")
    public WebElement sabanciTags;
	
	//input[@id='calorific_factor']
//	@FindBy(xpath = "//p[text()='Conversion Factors']/../..//following-sibling::div//p/span")
//	public WebElement weUnitConvHeader;
//	@FindBy(xpath = "//p[text()='Conversion Factors']/../..//following-sibling::div//p/div")
//	public WebElement weUnitConvValue;
	// -------------------------------end StatComb--------------------------
	@FindBy(xpath = "//input[@id='amount_of_energy']")
	public WebElement txtAmountOfEnergy;
	@FindBy(xpath = "(//span[text()='Units']/parent::div//parent::div//div)[4]")
	public WebElement droUnits;
	@FindBy(xpath = "//div[@class='datePicker']//div//div")
	public WebElement dpdPeriod;
	@FindBy(xpath = "//*[@data-testid='CloseOutlinedIcon']")
	public WebElement btnClose;
	@FindBy(xpath = "//article//*[contains(text(),'GHG Calculators')]")
	public WebElement lblGHGCalculators;
	@FindBy(xpath = "//div[text()='Indirect Emissions']")
	public WebElement btnGHGScope2;
	@FindBy(xpath = "//span[text()='Activities']/parent::div//article")
	public WebElement btnAddNewTranscation;
	@FindBy(xpath = "//input[@id='invoiceNo']")
	public WebElement txtInputVoice;
	@FindBy(xpath = "//span[@aria-label='Select facilities below and press refresh to calculate applicable emissions']")
	public WebElement btnGenerate;
	@FindBy(xpath = "//span[contains(text(),'Location Based Emissions')]")
	public WebElement weLocationBasedEmissions;
	@FindBy(xpath = "//span[contains(text(),'Market Based Emissions')]")
	public WebElement weMarketBasedLocation;
	
	
	
	
	
	
	@FindBy(xpath = "//li[text()='kilowatt-hour (kWh)']")
	public WebElement weKiloWattPerHour;
	@FindBy(xpath = "//li[text()='Electricity']")
	public WebElement weElectricity;
	@FindBy(xpath = "//li[text()='Heat, Steam and Cooling']")
	public WebElement weHeatSteamCooling;
	@FindBy(xpath = "//span[text()='Period']//parent::div//following-sibling::div//span")
	public WebElement drpPeriod;
	@FindBy(xpath = "//input[contains(@id,'acility')]")
	public WebElement txtFacilityName;
	@FindBy(xpath = "//input[@id='fuelName']")
	public WebElement txtFuelType;
	@FindBy(xpath = "//input[@id='unitType']")
	public WebElement txtUnitType;
	@FindBy(xpath = "//input[@id='unitName']//parent::div//button[@aria-label='Open']")
	public WebElement txtUnitName;
	@FindBy(xpath = "//span[text()='Prev']/parent::button/parent::span")
	public WebElement lblActivityDetails_P;
	// ---new
	// code-----------------------------------------------------------------------------------------------
	@FindBy(xpath = "//*[text()='Add Activity']")
	public WebElement lblAddActivity;
	@FindBy(xpath = "//div[text()='Direct Emissions']")
	public WebElement weDirectEmissions;;
	@FindBy(xpath = "//span[text()='Fuel Amount']//parent::div//following-sibling::div//input")
	public WebElement txtFuelAmount;
	// Activity details
	@FindBy(xpath = "//span[text()='Facility Name']//parent::div//following-sibling::div")
	public WebElement weFacilityName;
	@FindBy(xpath = "//span[text()='Invoice No.']//parent::div//following-sibling::div")
	public WebElement weInVoiceNo;
	@FindBy(xpath = "//span[text()='Invoice Date']//parent::div//following-sibling::div")
	public WebElement weInVoiceDate;
	@FindBy(xpath = "//span[text()='Start Date']//parent::div//following-sibling::div")
	public WebElement weStartDate;
	@FindBy(xpath = "//span[text()='End Date']//parent::div//following-sibling::div")
	public WebElement weEndDate;
	@FindBy(xpath = "//span[text()='Custom Emission Factor']//parent::div//following-sibling::div")
	public WebElement weCustomEmissionFactor;
	@FindBy(xpath = "//span[text()='Fuel Type']//parent::div//following-sibling::div")
	public WebElement weFuelType;
	@FindBy(xpath = "//span[text()='Fuel Amount']//parent::div//following-sibling::div")
	public WebElement weFuelAmount;
	@FindBy(xpath = "//span[text()='Unit Type']//parent::div//following-sibling::div")
	public WebElement weUnitType;
	@FindBy(xpath = "//span[text()='Unit']//parent::div//following-sibling::div")
	public WebElement weUnit;
	// emission details
	@FindBy(xpath = "//span[text()='tCO2']//parent::div//following-sibling::div")
	public WebElement weEmissionDetails_tCO2;
	@FindBy(xpath = "//span[text()='tCH4']//parent::div//following-sibling::div")
	public WebElement weEmissionDetails_tCH4;
	@FindBy(xpath = "//span[text()='tN2O']//parent::div//following-sibling::div")
	public WebElement weEmissionDetails_tN2O;
	@FindBy(xpath = "//span[text()='tCO2e']//parent::div//following-sibling::div")
	public WebElement weEmissionDetails_tCO2e;
	@FindBy(xpath = "//span[text()='Biofuel tCO2']//parent::div//following-sibling::div")
	public WebElement weEmissionDetails_BiofueltCO2;
	@FindBy(xpath = "//span[text()='Biofuel CO2']//parent::div//following-sibling::div")
	public WebElement weEmissionDetails_BiofuelCO2;
	@FindBy(xpath = "//span[text()='tCO2e (Daily)']//parent::div//following-sibling::div")
	public WebElement weEmissionDetails_tCO2e_Daily;
	@FindBy(xpath = "//span[text()='Biofuel tCO2 (Daily)']//parent::div//following-sibling::div")
	public WebElement weEmissionDetails_BiofueltCO2Daily;
	@FindBy(xpath = "//span[text()='Emission Factor']//parent::div//following-sibling::div")
	public WebElement weEmissionDetails_EmissionFactor;
	@FindBy(xpath = "//span[text()='Activities']/following-sibling::article//*[local-name()='svg']")
	public WebElement btnActivities;
	@FindBy(xpath = "//article[@aria-label='Add Activity']")
	public WebElement btnActivities_scope1;
	@FindBy(xpath="//article[@aria-label='View Organization Data']")
	public WebElement btnActivityScope1ForViewOrgData;
	@FindBy(xpath = "//button[text()='Close']")
	public WebElement btnCloselatest;
	@FindBy(xpath = "(//input[@id='Facility Name']//parent::div//button)[2]")
	public WebElement txtFacilityNameScope3_1qa;
	@FindBy(xpath = "//*[text()='Activity Details']")
	public WebElement lblActivityDetails;
	@FindBy(xpath = "//*[text()='View Custom Emission Factors']")
	public WebElement lblCEFDetails;
	// ------------------Fuel&Energy-------------------------//
	@FindBy(xpath = "//input[@id='Facility Name']")
	public WebElement txtFacilityNameScope3_3;
	@FindBy(xpath = "//textarea[@id='description']") // demo
	public WebElement txtDescrScope3_3;
	@FindBy(xpath = "//input[@id='description']") // Qa
	public WebElement txtDescrScope3_3Qa;
	@FindBy(xpath = "//input[@id='type_name']/..//button[@aria-label='Open']")
	public WebElement drpEnerfgyType;
	@FindBy(xpath = "//input[@id='fuel_type_name']/..//button[@aria-label='Open']")
	public WebElement drpFuelType;
	@FindBy(xpath = "//input[@id='fuel_name']/..//button[@aria-label='Open']")
	public WebElement drpFuelName;
	@FindBy(xpath = "//input[@id='unit_name']")
	public WebElement txtScope3_3FuelEnrgyUnit;
	@FindBy(xpath = "//input[@id='amount']")
	public WebElement txtQuantityConsumedFuelEngy;
	@FindBy(xpath = "//button[contains(text(),'Average Data Method')]")
	public WebElement btnavergaeDataMethod;
	
	
	
	
	
	
	
	@FindBy(xpath = "//h5[text()='Edit Activity']")
	public WebElement lblEditActivityFuelEnrgy;
	@FindBy(xpath = "//p[text()='Activity Details']")
	public WebElement lblAddActivityFuelEnrgy;
	@FindBy(xpath = "//span[contains(text(),'Are you using a Custom Emission Factor?')]/parent::div/following-sibling::div/label/span/span")
	public WebElement chbxCustomEmssionFactorFuel;
	@FindBy(xpath = "((//span[contains(text(),'Custom Emission Factor')])[2]//parent::div//following-sibling::div[@role='combobox']//button)[2]")
	public WebElement drpCustomEmiFactorActivityFuel;
	// -------CEF-FuelAndEnergy----------//
	@FindBy(xpath = "//input[@id='name']")
	public WebElement txtNameCEFForFuel;
	@FindBy(xpath = "//span[contains(text(),'Energy Type')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpEnergyTypeFuel;
	@FindBy(xpath = "//input[@id='source']")
	public WebElement txtSourceOfEmissionFactorFuel;
	@FindBy(xpath = "//span[contains(text(),'Unit of Custom EF (Denominator)')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpUnitCEFFuel;
	@FindBy(xpath = "//input[@id='co2e']")
	public WebElement txtco2e;
	@FindBy(xpath = "//span[contains(text(),'Unit of Custom EF (Numerator)')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpUnitEmmsinFuel;
	// -----------upstreamTrans--------------//
	@FindBy(xpath = "//input[@id='Facility Name']") // demo
	public WebElement txtFacilityNameScope3_4;
	@FindBy(xpath = "//input[@id='Facility Name']//following-sibling::div/button[2]") // Qa
	public WebElement txtFacilityNameScope3_4Qa;
	@FindBy(xpath = "//input[@id='description']")
	public WebElement txtDescrScope3_4;
	@FindBy(xpath = "//span[text()='Invoice No.']//parent::div//following-sibling::div//input")
	public WebElement invoceNoupSt;
	@FindBy(xpath = "//input[@id='service_provider']")
	public WebElement txtServiceProvider;
	@FindBy(xpath = "//input[@id='service_type']")
	public WebElement txtServiceType;
	@FindBy(xpath = "(//input[@id='mode_of_transport']//following-sibling::div/button)[2]")
	public WebElement drpModeOfFrght;
	@FindBy(xpath = "//input[@id='vehicle_name']")
	public WebElement drpVehcle;
	@FindBy(xpath = "//*[@id='type_name']")
	public WebElement drpType;
	// span[contains(text(),'Fuel /
	// Size')]//parent::div/parent::div/parent::div/div[2]//input
	@FindBy(xpath = "(//input[@id='fuel_name']//parent::div//following::button)[2]")
	public WebElement drpFuel_Size;
	@FindBy(xpath = "//span[contains(text(),'Fuel / Size')]//parent::div/parent::div/parent::div/div[3]//input")
	public WebElement drpFuel_Size3_9;
	@FindBy(xpath = "//input[@id='weight']")
	public WebElement txtWeight;
	@FindBy(xpath = "//input[@id='distance']")
	public WebElement txtDistance;
	@FindBy(xpath = "//input[@id='weight_unit_name']")
	public WebElement drpWeightUnit;
	@FindBy(xpath = "//span[contains(text(),'Weight Unit')]//parent::div/parent::div/parent::div/div[3]//input")
	public WebElement drpWeightUnit3_9;
	@FindBy(xpath = "//input[@id='distance_unit_name']")
	public WebElement drpDisatnceUnit;
	// ----CEF---//
	@FindBy(xpath = "//button[text()='Weight/Distance-Based Method']")
	public WebElement btnWeightDistanceUpstream;
	// ------DownStream-transportation----//
	@FindBy(xpath = "//span[contains(text(),'Calculation Method')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpCalculationMethod;
	@FindBy(xpath = "//span[contains(text(),'Mode of Freight')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpModeOfFreight;
	@FindBy(xpath = "//input[@id='weight_unit_name']")
	public WebElement drpWeightUnitDown;
	@FindBy(xpath = "//input[@id='distance_unit_name']")
	public WebElement drpDistanceUnitDown;
	@FindBy(xpath = "//span[contains(text(),'Currency')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpCurrencyDownstream;
	@FindBy(xpath = "//span[contains(text(),'Unit of Custom EF (Numerator)')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpUnitOfCustomEFNum;
	@FindBy(xpath = "//button[text()='Weight/Distance Based']")
	public WebElement btnweightDistanceBased;
	@FindBy(xpath = "//span[contains(text(),'Custom Emission')]//parent::div//parent::div/following-sibling::div/div/following-sibling::div/div/div/input")
	public WebElement drpCustomEmissionDownStream;
	// ----------------HomeOfficeTelevommuting---------------//
	@FindBy(xpath = "//input[@placeholder='Description']")
	public WebElement txtDscrptnHmeOffce;
	@FindBy(xpath = "//input[@id='Country']")
	public WebElement txtContryHmeOffce;
	@FindBy(xpath = "//input[@id='Type Of Home Office']/../div/button/*[@data-testid='ArrowDropDownIcon']")
	public WebElement drptypeHmeOffce;
	@FindBy(xpath = "//input[@id='Number of working days']")
	public WebElement txtNoOfWrknDaysypeHmeOffce;
	@FindBy(xpath = "//input[@id='Number of working hours per day']")
	public WebElement txtNoOfWrknhHorsHmeOffce;
	@FindBy(xpath = "//input[@id='Number of employees']")
	public WebElement txtNoOfEmplysHmeOffce;
	@FindBy(xpath = "//input[@id='Working regime']")
	public WebElement txtWrkngRegimeHmeOffce;
	@FindBy(xpath = "//input[@id='Working from home']")
	public WebElement txtWFHHmeOffce;
	@FindBy(xpath = "//input[@id='Source Units']")
	public WebElement drpUnitsHmeOffce;
	@FindBy(xpath = "//*[text()='Activity Details']")
	public WebElement lblViewActivityHomeoffice;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// ---cef
	@FindBy(xpath = "//input[@id='type_of_home_office']//parent::div/div/button")
	public WebElement drpTypeOfHomeOffce;
	@FindBy(xpath = "//input[@id='energy_consumption']")
	public WebElement txtenergyConsumption;
	@FindBy(xpath = "//span[contains(text(),'Unit of Custom EF (Denominator)')]//parent::div//parent::div/following-sibling::div")
	public WebElement drpUnitCEFHomeOffice;
	@FindBy(xpath = "//span[contains(text(),'Unit of Custom EF (Numerator)')]//parent::div//parent::div/following-sibling::div")
	public WebElement drpUnitCEFNumeratorHmeOffce;
	@FindBy(xpath = "//span[contains(text(),'Custom Emission Factor')]//parent::div//parent::div/following-sibling::div/div/div/div/button")
	public WebElement drpcutomEFHome;
	@FindBy(xpath = "//input[@ id='energy_consumption']")
	public WebElement txtEnergyConsumption;
	@FindBy(xpath = "//input[@placeholder='Unit of Custom EF (Denominator)']//parent::div//following::button")
	public WebElement drpUnitsOfCustomHmeOffce;
	@FindBy(xpath = "(//input[@placeholder=' Unit of Custom EF (Numerator)']//parent::div//following::button)[1]")
	public WebElement drpUnitsOfCustomNumHmeOffce;
	@FindBy(xpath = "//span[contains(text(),'Custom Emission Factor')]//parent::div//parent::div/following-sibling::div/div/div/div/button[2]")
	public WebElement drpcutmEFHome;
	// --------------Franchises------------//
	@FindBy(xpath = "//input[@id='facility_name']")
	public WebElement txtFacilityFrenchiseDmo;
	@FindBy(xpath = "(//input[@id='description'])[1]")
	public WebElement txtDescriptionFranchise;
	@FindBy(xpath = "//input[@id='franchisee_name']")
	public WebElement txtFrenchiseNameFrenchiseDmo;
	@FindBy(xpath = "//input[@placeholder='Leased Asset']")
	public WebElement txtLeasedAssetFrenchiseDmo;
	@FindBy(xpath = "//input[@id='energy_category_name']")
	public WebElement drpEnergyCatgryFrenchiseDmo;
	@FindBy(xpath = "//input[@id='type_name']")
	public WebElement drpTypeFrenchiseDmo;
	@FindBy(xpath = "//input[@id='activity_amount']")
	public WebElement txtActcivityAmntFrenchiseDmo;
	@FindBy(xpath = "//input[@id='source_data_unit_name']")
	public WebElement drpUnitsFranchise;
	// --cef
	@FindBy(xpath = "//button[contains(text(),'Franchisee Specific Method')]")
	public WebElement assetSpecificMethod;
	@FindBy(xpath = "//input[@id='energy_category']")
	public WebElement drpEnergyCategoryFranchise;
	@FindBy(xpath = "//input[@id='sourceDataUnitName']")
	public WebElement drpunitOfCEFFranchise;
	@FindBy(xpath = "//input[@id='co2EFUnitName']")
	public WebElement drpunitOfCEFNumFranchise;
	// ---------------indirectEmission----------//
	@FindBy(xpath = "//input[@id='Invoice Number']")
	public WebElement txtInvoiceNoIndirect;
	@FindBy(xpath = "//input[@id='amount_of_energy']")
	public WebElement drpAmountEnergy;
	@FindBy(xpath = "(//span[text()='Units']//parent::div//following-sibling::div//div)[1]")
	public WebElement drpUnitsIndirect;
	@FindBy(xpath = "//input[@id='energy_category']//parent::div//button")
	public WebElement drpEnergyCategoryIndirect;
	@FindBy(xpath = "//h5[text()='Edit Activity']")
	public WebElement lblEditActivityIndirect;
	@FindBy(xpath = "//h5[text()='Add Activity']")
	public WebElement lblAddActivityIndirect;

	@FindBy(xpath = "//*[text()='Activity Details']")
	public WebElement lblTransactTiffany;

	/*--CEF-Indirect-Emission--*/
	@FindBy(xpath = "//article[@aria-label='Add Custom Emission Factors']")
	public WebElement btnAddCEF;
	@FindBy(xpath = "//*[text()='Add Custom Emission Factors']")
	public WebElement lblAddCEF;
	@FindBy(xpath = "//button[contains(text(),'Purchased Energy')]")
	public WebElement btnPurchasedEnergy;
	// 00000
	@FindBy(xpath = "//input[@id='NameOfCustomEmission']")
	public WebElement txtNameOfCustomEF;
	@FindBy(xpath = "//span[contains(text(),'Energy Category')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpEnergyType;
	@FindBy(xpath = "//span[text()='CO2']/parent::p/following-sibling::p/div")
	public WebElement weEmissionDetails_CO2;
	@FindBy(xpath = "//span[text()='CH4']/parent::p/following-sibling::p/div")
	public WebElement weEmissionDetails_CH4;
	@FindBy(xpath = "//span[text()='N2O']/parent::p/following-sibling::p/div")
	public WebElement weEmissionDetails_N2O;
	@FindBy(xpath = "//span[text()='CO2e']//parent::div//following-sibling::div")
	public WebElement weEmissionDetails_CO2e;
	@FindBy(xpath = "//span[text()='CO2e (Yearly)']//parent::div//following-sibling::div")
	public WebElement weEmissionDetails_CO2eYearly;
	@FindBy(xpath = "//span[text()='CO2e']//parent::p//following-sibling::p")
	public WebElement weEmissionDetails_CO2eELT;
	@FindBy(xpath = "//span[contains(text(),'Custom/Location Based Emission Factor')]//parent::div//parent::div/following-sibling::div/div/div")
	public WebElement drpCustomEmiFactorLocationScope2;
	@FindBy(xpath = "//span[contains(text(),'Custom/Market Based Emission Factor')]//parent::div//parent::div/following-sibling::div/div/div")
	public WebElement drpCustomEmiFactorMarketScope2;
	@FindBy(xpath = "//span[contains(text(),'Are you using a Custom Location Based Emission Factor?')]/parent::div/following-sibling::div/label/span/span")
	public WebElement chboxCstmLocatinBased;
	@FindBy(xpath = "//span[contains(text(),'Are you using a Custom Market Based Emission Factor?')]/parent::div/following-sibling::div/label/span/span")
	public WebElement chboxCstmMarketBased;
	@FindBy(xpath = "//span[text()='Unit Conversion']//parent::p//following-sibling::p")
	public WebElement valunitConversion;
	
	
	
	
	
	
	
	
	
	@FindBy (xpath = "//span[text()='Activity updated successfully']/parent::div/following-sibling::div/div")
	public WebElement btnPopUpCloseScope2;
	
	
	// --------------WasteGeneratedInOperations------------------Start---------------------------------
	@FindBy(xpath = "//*[contains(text(),'Waste Generated in Operations')]")
	public WebElement lblWasteGeneratedInOps;
	// span[text()='Waste
	// Category']//parent::div//following-sibling::div//div[@role='button']
	// //previous xpath
	@FindBy(xpath = "(//span[text()='Waste Category']//parent::div//following-sibling::div/div/div//following::button)[2]")
	public WebElement drpWasteCategory;
	@FindBy(xpath = "//input[@id='wm_management_type']")
	public WebElement drpWasteType;
	@FindBy(xpath = "(//*[@data-testid='ArrowDropDownIcon']//parent::button)[4]")
	public WebElement drpWasteDisposalMethod;
	@FindBy(xpath = "//input[@id='unit']")
	public WebElement drpUnits;
	@FindBy(xpath = "//input[@id='waste_produce']")
	public WebElement txtMassOfWasteProduced;
	// ----CEF-----//
	@FindBy(xpath = "//button[contains(text(),'Waste Type Specific Method')]")
	public WebElement btnWasteTypeSpecificMethod;
	@FindBy(xpath = "//span[contains(text(),'Waste Category')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpWasteCategoryCEF;
	@FindBy(xpath = "//span[contains(text(),'Waste Type')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpWasteTypeCEF;
	@FindBy(xpath = "//span[contains(text(),'Waste Disposal Method')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpWasteDisposalMethodCEF;
	@FindBy(xpath = "//span[contains(text(),'Unit of Custom EF (Denominator)')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpUnitOfCustomEFDenominator;
	@FindBy(xpath = "(//span[contains(text(),'Unit of Custom EF (Numerator)')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']//*)[2]")
	public WebElement drpUnitofCustomEFNumeratorCEF;
	// ------------------WasteGeneratedInOperations---------------End-----------------------------------------------------------------------------
	// -----------------------BusinessTravel----------------------Start----------------------------------------------------
	@FindBy(xpath = "//*[contains(text(),'Business Travel')]")
	public WebElement lblBusinessTravel;
	@FindBy(xpath = "//button[text()='Distance Based Method']")
	public WebElement btnDistanceBasedMethod;
	@FindBy(xpath = "//button[text()='Spend Based Method']")
	public WebElement btnSpendBasedMethod;
	@FindBy(xpath = "//*[text()='Business Travel']")
	public WebElement weBusinessTravelCalculator;
	@FindBy(xpath = "//input[@id='Expense No']")
	public WebElement txtInvoiceNum;
	@FindBy(xpath = "//span[text()='Invoice Date']//parent::div//following-sibling::div//input")
	public WebElement txtInvoiceDate;
	@FindBy(xpath = "//span[text()='Expense Date']//parent::div//following-sibling::div//input")
	public WebElement txtExpenseDate;
	@FindBy(xpath = "//span[text()='Mode of Business Travel']//parent::div//following-sibling::div//input") // LastChange
	public WebElement drpModeOfBusinessTravel;
	@FindBy(xpath = "//input[@id='AmountofActivity']")
	public WebElement txtAmountSpent;
	@FindBy(xpath = "//input[@id='currency']")
	public WebElement drpCurrency;
	@FindBy(xpath = "//span[text()='Travel Category']//parent::div//following-sibling::div//input")
	public WebElement drpTravelCategory;
	@FindBy(xpath = "//span[text()='Vehicle Type']//parent::div//following-sibling::div//input")
	public WebElement drpVehicleType;
	@FindBy(xpath = "//input[@id='Units']")
	public WebElement drpBusinessTravlUnits;
	@FindBy(xpath = "//span[text()='Distance Travelled by Each Employee']//parent::div//following-sibling::div//input")
	public WebElement txtDistanceTrvlByEmp;
	@FindBy(xpath = "//input[@id='No of Employees Traveled']")
	public WebElement txtNoOfEmpTravel;
	@FindBy(xpath = "//span[text()='Travel Description']//parent::div//following-sibling::div//input")
	public WebElement txtTravelDescription;
	// ---CEF---//
	@FindBy(xpath = "//input[@id='Efname']")
	public WebElement txtNameOfCustomEFBussiness;
	@FindBy(xpath = "//span[contains(text(),'Calculation Method')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpCalculationMethodBussiness;
	@FindBy(xpath = "//input[@id='SourceOfEmission']")
	public WebElement txtSourceOfEmisson;
	@FindBy(xpath = "//span[contains(text(),'Unit of Custom EF (Denominator)')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpUnitOfCEFDenoBussines;
	@FindBy(xpath = "//input[@id='CO2']")
	public WebElement txtCOBussiness;
	@FindBy(xpath = "//input[@id='CH4']")
	public WebElement txtCH4BUssiness;
	@FindBy(xpath = "//input[@id='N2O']")
	public WebElement txtN2OBussiness;
	@FindBy(xpath = "//input[@id='BiofuelCO2']")
	public WebElement txtBioFuelBussiness;
	@FindBy(xpath = "//input[@id='CO2e']")
	public WebElement txtCO2eBussiness;
	@FindBy(xpath = "//input[@id='Emission']//parent::div//following::button")
	public WebElement drpCustomEmiFactorActivityBussiness;
	@FindBy(xpath = "//*[text()='Edit Custom Emission Factors']")
	public WebElement lblEditCEF;
	// -----------------------BusinessTravel----------------------end----------------------------------------------------
	// -----------------------BusinessTravel----------------------end----------------------------------------------------
	// -----------EmployeeCommuting-----------------------------------Start---------------------------
	@FindBy(xpath = "//*[contains(text(),'Employee Commuting')]")
	public WebElement lblEmployeeCommuting;
	@FindBy(xpath = "//*[text()='Employee Commuting']")
	public WebElement weEmployeeCommutingCalculator;
	@FindBy(xpath = "//span[text()='Average Daily Commute Distance ']//parent::div//following-sibling::div//input")
	public WebElement txtAvgDailyCommuteDistance;
	@FindBy(xpath = "//span[text()=' Commute Description']//parent::div//following-sibling::div//input")
	public WebElement txtCommuteDescription;
	@FindBy(xpath = "//span[text()=' Number of Employees Commuted']//parent::div//following-sibling::div//input")
	public WebElement txtNoOfEmpCommuted;
	@FindBy(xpath = "//span[text()='Employee / Group of Employees']//parent::div//following-sibling::div//input")
	public WebElement txtGroupEmployee;
	@FindBy(xpath = "//span[text()=' Average Commute Days']//parent::div//following-sibling::div//input")
	public WebElement txtAvgCommuteDays;
	@FindBy(xpath = "//span[text()='Mode of Commute']//parent::div//following-sibling::div//input")
	public WebElement drpModeOfCommute;
	@FindBy(xpath = "//span[text()='Mode of Commute']//parent::div//following-sibling::div//button[@aria-label='Open']")
	public WebElement drpModeOfCommuteEmployye;
	@FindBy(xpath = "//span[text()='Vehicle Type']//parent::div//following-sibling::div//button[@aria-label='Open']")
	public WebElement drpVehicleTypeEmployee;
	@FindBy(xpath = "//input[@id='Vehicle Type']")
	public WebElement txtVehicleTypeEmployee;
	// --------------------------CEF---------------------------------
	@FindBy(xpath = "//input[@id='Emission']")
	public WebElement txtCEFEmission;
	@FindBy(xpath = "//button[contains(text(),'Distance Based Method')]")
	public WebElement btnEmployeeCommuting;
	@FindBy(xpath = "//span[text()='Calculation Method']//parent::div//following-sibling::div//button[@aria-label='Open']")
	public WebElement drpCalculationMethodEC;
	// -----------EmployeeCommuting-----------------------------------End---------------------------
	// -------------------------Sidda Reddy------------------------------------
	@FindBy(xpath = "(//button[text()='Save'])[2]")
	public WebElement btnSaveTranscation;
	
	
	
	
	
	@FindBy(xpath = "//input[@type='search']")
	public WebElement searchtxt;
	@FindBy(xpath = "//*[@data-testid='SearchOutlinedIcon']")
	public WebElement searchfacility;
	// ----------------------------stationary combution
	// End------------------------------------
	// -----------------------------Mobile combution
	// start----------------------------------
	@FindBy(xpath = "//*[@data-testid='KeyboardArrowRightIcon']")
	public WebElement btnForwordScope1;
	@FindBy(xpath = "//button[contains(text(),'Mobile Combustion')]")
	public WebElement btnMobileCombution;
	@FindBy(xpath = "//input[@id='activityType']")
	public WebElement txtActivityTypeScope1;
	@FindBy(xpath = "//input[@id='vehicleTypeName']")
	public WebElement txtVehicleTypeScope1;
	@FindBy(xpath="//input[@id='vehicleTypeName']//parent::div//parent::div//button[@aria-label='Open']")
	public WebElement txtVehivleTypeScope1Icon;
	@FindBy(xpath = "//input[@id='activityAmount']")
	public WebElement txtActivityAmountScope1;
	@FindBy(xpath = "//input[@id='fuelSourceName']")
	public WebElement txtFuelTypeMobileCombuScope1;
	@FindBy(xpath = "//span[contains(text(),'Facility Name')]//parent::div/parent::div//div//button[@aria-label='Open']")
	public WebElement drpFacilityNameScope3_6;
	// ................Parameter Input.......................
	@FindBy(xpath = "//*[contains(@placeholder,'Custom E')]")
	public WebElement drpCustomEmiFactorActivityMobile;
	// -----------------------------Mobile combution
	// End----------------------------------
	// -----------------------Refrigerants and Fugitves
	// Start------------------------------------
	@FindBy(xpath = "//button[contains(text(),'Fugitives')]")
	public WebElement btnRefrigerantsScope1;
	@FindBy(xpath = "//button[contains(text(),'Fugitives')]")
	private WebElement btnRefrigerantsScope1QA;
	@FindBy(xpath = "//input[@id='facility_name']")
	public WebElement txtFacility_Name;
	@FindBy(xpath = "//input[@id='type_of_equipment']")
	public WebElement txtEquipmentRefrigerantType;
	@FindBy(xpath = "//input[@id='refrigerant_ref_name']")
	public WebElement drpRefrigerantUsed;
	@FindBy(xpath = "//input[@id='start_inventory']")
	public WebElement txtDecreaseinInventory;
	@FindBy(xpath = "//input[@id='purchased_from_producer']")
	public WebElement drpRefrigePurchased;
	@FindBy(xpath = "//input[@id='end_inventory']")
	public WebElement txtPurchase_Acquisition;
	@FindBy(xpath = "//input[@id='equipment_user_return']")
	public WebElement txtRefrigerantreturned;
	@FindBy(xpath = "//input[@id='recycle_reclamation_return']")
	public WebElement txtRefrigerantreturnedOffSite;
	@FindBy(xpath = "//input[@id='charged_into_equipment']")
	public WebElement txtRefrigerantcharged;
	@FindBy(xpath = "//input[@id='delivered_to_equipment']")
	public WebElement txtRefrigerantdelivered;
	@FindBy(xpath = "//input[@id='returned_to_producer']")
	public WebElement txtRefrigerantreturnedtorefrigerant;
	@FindBy(xpath = "//input[@id='recycle_reclamation_sent_offsite']")
	public WebElement txtRefrigerantsentoffsiteRecycling;
	@FindBy(xpath = "//input[@id='destruction_sent_offsite']")
	public WebElement txtRefrigerantsentoffsitedestruction;
	@FindBy(xpath = "//input[@id='invoice_no']")
	public WebElement txtInput_Voice;
	
	
	
	
	
	
	
	
	
	
	
	
	// -----------------------Refrigerants and Fugitves
	// End------------------------------------
	// --------------------------Purchased Goods and Services start
	// ---------------------------------------.
	@FindBy(xpath = "//div[text()='Purchased Goods and Services']")
	public WebElement weScope3Purchased_Good_Ser;
	@FindBy(xpath = "//input[@id='FacilityName']")
	public WebElement txtFacilityNameScope3_1;
	@FindBy(xpath = "//input[@id='description']")
	public WebElement txtDescrScope3;
	@FindBy(xpath = "//span[contains(text(),'Purchased Goods Category')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpPurchasedGoodCategory;
	@FindBy(xpath = "//input[@id='material_used_name']")
	public WebElement drpPurchasedGood;
	@FindBy(xpath = "//span[contains(text(),'Production Process Involved')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpProduProcInvolved;
	@FindBy(xpath = "//input[@id='unit']")
	public WebElement txtScope3PurchaseGoodUnit;
	@FindBy(xpath = "//input[@id='amount_of_material']")
	public WebElement txtQuantityofGoodsPurch;
	@FindBy(xpath = "//button[text()='Save']")
	public WebElement btnSaveScope3PurcGood;
	@FindBy(xpath = "//button[contains(text(),'Average Data Method')]")
	public WebElement btnScope3_1AverageBased;
	// ---------------------CEF---------------------------------
	@FindBy(xpath = "//span[contains(text(),'Calculation Method')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpCalcMethod;
	@FindBy(xpath = "//input[@id='source']")
	public WebElement txtSource;
	@FindBy(xpath = "(//span[contains(text(),'Purchased Good')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open'])[2]")
	public WebElement drpPurchasedGoodCEF;
	@FindBy(xpath = "//input[@id='co2e']")
	public WebElement txtC02e;
	@FindBy(xpath = "//span[contains(text(),'Unit of Custom Factor')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpUnitCEF;
	@FindBy(xpath = "//span[contains(text(),'Unit Emission Factor')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpUnitEmissionFactor;
	@FindBy(xpath = "//input[contains(@id,'name')]")
	public WebElement txtCutsomEFName_1;
	@FindBy(xpath = "//input[@id='fuel_type']")
	public WebElement txtFuelTypeCEF;
	// --------------------------Purchased Goods and Services Average End
	// ---------------------------------------.
	// -----------------------------SpendBased
	// Start----------------------------------------------------
	@FindBy(xpath = "//button[contains(text(),'Spend Based Method')]")
	public WebElement btnScope3_1SpendBased;
	@FindBy(xpath = "//span[contains(text(),'Purchased Goods or Services')]//parent::div/parent::div//input//parent::div//button[@aria-label='Open']")
	public WebElement drpPurchasedGoods_orServices;
	@FindBy(xpath = "//input[@id='amount_spend']")
	public WebElement txtAmountSpentScope3spend;
	@FindBy(xpath = "//span[contains(text(),'Currency')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpCurrencySpendBased;
	@FindBy(xpath = "//input[@id='pg_sb_activity_type_name']")
	public WebElement txtPurchaseGood_Ser;
	// ------------------------CEF-----------------------------
	@FindBy(xpath = "//input[@id='pg_activity_type']")
	public WebElement txtPurchasedGoodCategory;
	@FindBy(xpath = "//input[@id='material_used']")
	public WebElement txtPurchasedGood;
	// -----------------------------SpendBased
	// End---------------------------------------------------
	// --------------------------------------Capital Goods
	// Start------------------------------------------
	@FindBy(xpath = "//div[text()='Capital Goods']")
	public WebElement weScope3Capital_Good_Ser;
	@FindBy(xpath = "//input[@id='facilityName']")
	public WebElement txtFacilityNameScope3_2;
	@FindBy(xpath = "//span[contains(text(),'Capital Goods Category')]//parent::div/parent::div//input//parent::div//button[@aria-label='Open']")
	public WebElement drpCapitalGoodCategory;
	@FindBy(xpath = "(//span[contains(text(),'Capital Good')])[2]//parent::div/parent::div//input//parent::div//button[@aria-label='Open']")
	public WebElement drpCapitalGood;
	@FindBy(xpath = "//input[@id='quantity_purchase']")
	public WebElement txtQuantityofGoodsCapital;
	@FindBy(xpath = "//input[@id='activity_type_name']")
	public WebElement txtCapitalGoods_3_2;
	@FindBy(xpath = "//input[@id='material_used_name']")
	public WebElement txtCapitalGood_3_2;
	@FindBy(xpath = "//input[@id='usage_type_name']")
	public WebElement txtProdProInv3_2;
	@FindBy(xpath = "//button[text()='Save']")
	public WebElement btnSaveScope3CapitalGood;
	// -------------------CEF--------------------
	@FindBy(xpath = "//input[@id='usage_type']")
	public WebElement txtProductionProcInvolved;
	@FindBy(xpath = "//input[@id='pg_activity_type']")
	public WebElement txtCapitalGoodsCategoCEF;
	@FindBy(xpath = "//input[@id='material_used']")
	public WebElement txtCapitalGoodCEF;
	// --------------------------------------Capital Goods Average
	// End------------------------------------------
	// -----------------------------Capital Goods Spend Based
	// Start------------------------------------------------
	@FindBy(xpath = "//button[contains(text(),'Spend Based Method')]")
	public WebElement btnScope3_2SpendBased;
	@FindBy(xpath = "//span[contains(text(),'Capital Goods Category')]//parent::div/parent::div//input//following-sibling::div//button[@aria-label='Open']")
	public WebElement drpCapitalGoodsCategorySpend3_2;
	@FindBy(xpath = "//input[@id='sb_activity_type_name']")
	public WebElement txtCapitalGoodsCateg_3_2;
	@FindBy(xpath = "//input[@id='material_used']")
	public WebElement txtCapitalGoodSpendBased3_2;
	@FindBy(xpath = "//input[@id='usage_type']")
	public WebElement txtProductProInvSppendBse3_2;
	@FindBy(xpath = "//p[text()='Activity Details']")
	public WebElement lblActivityDetailsSppendBse3_2;
	// -----------------------------Capital Goods Spend Based
	// End------------------------------------------------
	// ---------------------------------End of Life Treatment Start
	// --------------------------------------------------------
	@FindBy(xpath = "//div[text()='End-of-Life Treatment of Sold Products']")
	public WebElement weScope3_12EndOfLife;
	@FindBy(xpath = "//input[@id='Facility Name']")
	public WebElement txtFacilityNameScope3_12;
	@FindBy(xpath = "//*[@id='description']")
	public WebElement txtDescrScope3_12;
	@FindBy(xpath = "//span[contains(text(),'Product Waste Category')]//parent::div/parent::div//div//button[@aria-label='Open']")
	public WebElement drpProduWsateCate;
	@FindBy(xpath = "//input[@id='product_category']")
	public WebElement txtProduWasteCate;
	@FindBy(xpath = "//span[contains(text(),'Product Waste Type')]//parent::div/parent::div//div//button[@aria-label='Open']")
	public WebElement drpProduWasteType;
	@FindBy(xpath = "//input[@id='product_waste_type']")
	public WebElement txtProduWasteType;
	@FindBy(xpath = "//span[contains(text(),'Waste Treatment Method')]//parent::div/parent::div//div//button[@aria-label='Open']")
	public WebElement drpWasteTreatMethod; // demo
	@FindBy(xpath = "//span[contains(text(),'Waste Disposal Method')]//parent::div/parent::div//div//button[@aria-label='Open']")
	public WebElement drpWasteTreatMethodqa;
	@FindBy(xpath = "//input[@id='waste_treatment_method']")
	public WebElement txtWasteTreatMethod;
	@FindBy(xpath = "//input[@id='waste_produce']")
	public WebElement txtMassofWaste;
	@FindBy(xpath = "//button[contains(text(),' Waste Type Specific Method')]")
	public WebElement btnScope3_12EOLT;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// ---------------------------------End of Life Treatment End
	// --------------------------------------------------------
	// -----------------------------------------Hotel Stay
	// Start------------------------------------
	@FindBy(xpath = "//div[text()='Hotel Stay']")
	public WebElement weScope3_HotelStay;
	@FindBy(xpath = "//input[@id='FacilityName']")
	public WebElement txtFacilityNameScope3_6;
	@FindBy(xpath = "//input[@id='Travel Purpose']")
	public WebElement txtTravelDescrpScope3_6;
	@FindBy(xpath = "//input[@id='Travel Purpose']")
	public WebElement txtTravelDescrpScope3_6UAT;
	@FindBy(xpath = "//span[contains(text(),'Country of Hotel Stay')]//parent::div/parent::div//div//button[@aria-label='Open']")
	public WebElement drpCountryHotelStay;
	@FindBy(xpath = "//input[@id='Countries']")
	public WebElement txtCountryOfHotelstay;
	@FindBy(xpath = "//input[@id='OccupiedRooms']")
	public WebElement txtRoomsOccupied;
	@FindBy(xpath = "//input[@id='Occupied Rooms']")
	public WebElement txtRoomsOccupiedUAT;
	@FindBy(xpath = "//input[@id='Numberofnights']")
	public WebElement txtNightStayed;
	@FindBy(xpath = "//input[@id='Number of Nights']")
	public WebElement txtNightStayedUAT;
	@FindBy(xpath = "//input[@id='Units']")
	public WebElement txtUnitsHotelStay;
	@FindBy(xpath = "//button[text()='Save']")
	public WebElement btnSaveHotelStay;
	@FindBy(xpath = "//div[contains(text(),'Edit Activity')]")
	public WebElement lblActivityDetailsScope3_6;
	@FindBy(xpath = "//span[text()='Start Date']")
	public WebElement westartdateHotelstay;
	// --------------HotelStay---------------------------
	@FindBy(xpath = "//input[@id='CO2e']")
	public WebElement txtC02e_1;
	@FindBy(xpath = "//button[contains(text(),'Activity Based Method')]")
	public WebElement btnScope3_6HotelStay;
	@FindBy(xpath = "//input[@id='Custom Emission']")
	public WebElement txtvalActivityCEF_1;
	// -----------------------------------------Hotel Stay
	// End------------------------------------
	// ----------------------------Upstream Leased Assets
	// Start-----------------------------------------
	@FindBy(xpath = "//div[text()='Upstream Leased Assets']")
	public WebElement weScope3_8UpstreamLeasedAsset;
	@FindBy(xpath = "//span[contains(text(),'Energy Category')]//parent::div/parent::div//div//button[@aria-label='Open']")
	public WebElement drpEnergyCategoryLeasedassests;
	@FindBy(xpath = "//span[contains(text(),'Type')]//parent::div/parent::div//div//button[@aria-label='Open']")
	public WebElement drpTypeLeasedAsset;
	@FindBy(xpath = "//input[@id='leased_asset']")
	public WebElement txtLeasedAsset;
	@FindBy(xpath = "//input[@id='activity_amount']")
	public WebElement txtActivityAmountLeasedAsset;
	@FindBy(xpath = "//input[@id='source_data_unit_name']")
	public WebElement txtunitLeasedAsset;
	@FindBy(xpath = "(//button[text()='Cancel'])[2]")
	public WebElement btncancelLeasedAssets;
	// -----------------------CEF-------------------------
	@FindBy(xpath = "//span[contains(text(),'Energy Category')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']")
	public WebElement drpEnergyCategory;
	@FindBy(xpath = "//button[contains(text(),'Asset Specific Method')]")
	public WebElement btnULADLA;
	@FindBy(xpath = "//input[@id='emission_name']")
	public WebElement txtvalActivityCEF_2;
	// ----------------------------Upstream Leased Assets
	// End-----------------------------------------
	// -----------------------------------DownStream Leased Assets
	// Start----------------------------
	@FindBy(xpath = "//div[text()='Downstream Leased Assets']")
	public WebElement weScope3_8DownstreamLeasedAsset;
	// -----------------------------------DownStream Leased Assets
	// End----------------------------
	// --------------------------Processing of sold Products
	// Start---------------------------------------
	@FindBy(xpath = "//span[contains(text(),'Sold Product')]//parent::div/parent::div//div//button[@aria-label='Open']")
	public WebElement drpSoldPrducts;
	@FindBy(xpath = "//span[contains(text(),'Process Type')]//parent::div/parent::div//div//button[@aria-label='Open']")
	public WebElement drpProcessTypeSoldPrducts;
	@FindBy(xpath = "//input[@id='Amount']")
	public WebElement txtMassOfSoldProduct;
	@FindBy(xpath = "//div[text()='Processing of Sold Products']")
	public WebElement weScope3_10ProcessingSoldProduct;
	// -----------------------CEF-----------------------------------
	@FindBy(xpath = "//input[@id='productName']")
	public WebElement txtSoldProduct;
	@FindBy(xpath = "//input[@id='processType']")
	public WebElement txtProcessType;
	@FindBy(xpath = "//span[contains(text(),'Custom EF')]//parent::div/parent::div//div//button[@aria-label='Open']")
	public WebElement drpCustomEF;
	// --------------------------Processing of sold Products
	// End---------------------------------------
	// --------------------------------------Use of Sold Products
	// Start-----------------------------------------
	@FindBy(xpath = "//div[text()='Use of Sold Products']")
	public WebElement weScope3_1UseSoldProduct;
	@FindBy(xpath = "//input[@id='unit_sold']")
	public WebElement txtNo_ofUnitsSold;
	@FindBy(xpath = "//span[contains(text(),'Facility')]//parent::div/parent::div//div//button[@aria-label='Open']")
	public WebElement drpFacilityNameScope3_11;
	@FindBy(xpath = "//input[@id='facility_name']")
	public WebElement txtfacility;
	// -----------------------------------CEF------------------------
	@FindBy(xpath = "//input[@id='product']")
	public WebElement txtSoldProductUSP;
	@FindBy(xpath = "//input[@id='product_life']")
	public WebElement txtProductLifeTime;
	@FindBy(xpath = "//input[@id='electricity_consumption']")
	public WebElement txtElectricityConsumption;
	@FindBy(xpath = "//span[contains(text(),'Units of CO2e')]//parent::div//parent::div//following-sibling::div//button[@aria-label='Open']")
	public WebElement drpUnitsCO2e;
	@FindBy(xpath = "//input[@id='custom_emission_factor_name']")
	public WebElement txtvalActivityCEF1;
	// --------------------------------------Use of Sold Products
	// End-----------------------------------------
	// ''''''''''''''Equity investments Specific Method''''''''''''''''
	@FindBy(xpath = "//div[text()='Investments']")
	public WebElement weScope3_15Investments;
	@FindBy(xpath = "//input[@id='investee_company']")
	public WebElement txtInvesteeCompanyScope3_15;
	@FindBy(xpath = "//span[contains(text(),'Investee Company Sector')]//parent::div/parent::div//div//button[@aria-label='Open']")
	public WebElement drpInvesteeCompany;
	@FindBy(xpath = "//input[@id='reporting_company_share']")
	public WebElement txtreporting_company_share3_15;
	@FindBy(xpath = "//input[@id='scope1_co2e']")
	public WebElement txtScope1tCO2eEmissions3_15;
	@FindBy(xpath = "//input[@id='scope2_co2e']")
	public WebElement txtScope2tCO2eEmissions3_15;
	@FindBy(xpath = "//input[@id='scope3_co2e']")
	public WebElement txtScope3tCO2eEmissions3_15;
	@FindBy(xpath = "//input[@id='ghg']")
	public WebElement txtTotaltCO2eEmissions3_15;
	@FindBy(xpath = "//input[@id='source']")
	public WebElement txtSourcs3_15;
	// '''''''''''''''''''''''Equity investments Average data
	// Method'''''''''''''''''''''''''''''''
	@FindBy(xpath = "//span[contains(text(),'equity-investment.company-sector')]//parent::div/parent::div//div//button[@aria-label='Open']")
	public WebElement drpInvesteeCompanyssectorAverage;
	@FindBy(xpath = "//span[contains(text(),'Custom Emission Factor')]//parent::div/parent::div//div//button[@aria-label='Open']")
	public WebElement drpCEFAverageInvestments;
	@FindBy(xpath = "//input[@id='revenue_of_investee']")
	public WebElement txtRevenueOfInvesteeAverage;
	@FindBy(xpath = "//input[@id='investee_company_share']")
	public WebElement txtInvesteeCompanyShareInvesteeAverage;
	@FindBy(xpath = "//input[@id='reporting_company_share']")
	public WebElement txtReportingCompanyShareInvesteeAverage;
	@FindBy(xpath = "//button[contains(text(),'Investment Specific Method')]")
	public WebElement btnInvestmentsspecificMethod;
	@FindBy(xpath = "//button[contains(text(),'Average Data Method')]")
	public WebElement btnInvestmentsAveargeMethod;
	// ''''''''''''''''''''''Debt and Project Financing Specific
	// Method'''''''''''''''''''''''''
	@FindBy(xpath = "//input[@id='value_of_debt']")
	public WebElement txtValueofDebtInvest;
	@FindBy(xpath = "//input[@id='total_project_cost']")
	public WebElement txtTotalProjectCost;
	@FindBy(xpath = "//input[@id='currency_code']")
	public WebElement drpUnitDebtInvest;
	@FindBy(xpath = "//input[@id='source']")
	public WebElement txtSourceDebtInvest;
	@FindBy(xpath = "//button[contains(text(),'Debt & Project Financing')]")
	public WebElement btnInvestmentsDebt_Project;
	@FindBy(xpath = "//span[contains(text(),'Investee Company/Project Sector')]//parent::div/parent::div//div//button[@aria-label='Open']")
	public WebElement drpInvesteeCompanyDebtFinance;
	// -------------------------CEF-------------------------------------------------
	@FindBy(xpath = "//span[contains(text(),'Sector')]//parent::div/parent::div//div//button[@aria-label='Open']")
	public WebElement drpSectorInvestment;
	@FindBy(xpath = "//button[contains(text(),'Equity Investments')]")
	public WebElement btnEquityInvestments;
	@FindBy(xpath = "//input[@id='notes']")
	public WebElement txtSourceCEFUOSL;
	@FindBy(xpath = "//h5[contains(text(),'Edit Custom Emission')]")
	public WebElement lblEditCutsomEmissionFactorInvestee;
	// ''''''''''''''''''''''Debt and Project Financing Average Data
	// Method'''''''''''''''''''''''''
	@FindBy(xpath = "//span[contains(text(),'Custom EF')]//parent::div/parent::div//div//button[@aria-label='Open']")
	public WebElement drpCEFDebtAverage;
	@FindBy(xpath = "//input[@id='project']")
	public WebElement tctProjectPhase;
	@FindBy(xpath = "//input[@id='value_of_debt']")
	public WebElement txtValueofDebt;
	@FindBy(xpath = "//input[@id='total_project_cost']")
	public WebElement txtProjectCost;
	// ----------///
	
	
	//--------------------------Transactions ------------------------------------
	@FindBy(xpath = "//li[contains(text(),'Transactions')]")
	public WebElement lblTransactions_Calc;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public String lblTransactionsXPath = "//div[text()='Transactions']";
//	public String verifyOrgViewScreenXPath = "//div[text()='Filter By Organization']/parent::div//div[contains(@class,'TreeView_container__K73xj')]";
//	public String verifyBillGridXPath = "//div[@ref='centerContainer' and @role='presentation']";
//	public String lblFuelBillsXPath = "(//div[text()='Fuel Bills'])[2]";
//	public String btbAddTransactionXPath = "//*[text()='Add Transaction']";
//	public String lblVehicleFleetXPath = "(//div[text()='Vehicle Fleet'])[2]";
//	public String txtInvoiceNoForAddTransactionScope1XPath = "//span[text()='Invoice No']//parent::div//following-sibling::div//input";
//	public String drpFuelTypeForAddTransactionScope1XPath = "//span[text()='Fuel Type']/parent::div/following-sibling::div//*[local-name()='svg']";
//	public String drpMeterNumberXPath = "//span[text()='Meter Number']/parent::div/following-sibling::div//*[local-name()='svg']";
//	public String drpUnitsForAddTransactionScope1XPath = "//span[text()='Meter Number']/parent::div/following-sibling::div//*[local-name()='svg']";
//	public String drpCurrencyForAddTransactionScope1XPath = "//span[text()='Currency']/parent::div/following-sibling::div//*[local-name()='svg']";
//	public String txtNotesForAddTransactionXPath = "//span[text()='Notes']//parent::div//following-sibling::div//textarea";
//	public String txtAmountXPath = "//span[contains(text(),'Amount')]//parent::div//following-sibling::div//input";
//	public String txtCostXPath = "//span[text()='Cost']//parent::div//following-sibling::div//input";
//	public String toastMessageForAddingTransactionXPath = "//div[text()='Transaction added successfully']";
//	public String btnAddAmountOfEnergyXPath = "//*[contains(text(),'Amount')]/parent::div/following-sibling::div/div/div/input";
//	public String drpUnitsTiffanyXPath = "//span[text()='Units']/parent::div/following-sibling::div";
//	public String btnCostXPath = "//input[@name='cost']";
//	public String drpcurrencytypeXPath = "//span[text()='Currency']/parent::div/following-sibling::div";
//	public String btncurrencyXPath = ""; // Please provide the XPath expression
//	public String txtNotesXPath = "//*[@placeholder='Notes']";
//	public String inputInvoiceTiffanyXPath = "//input[@name='invoice_no']";
//	public String drpWastwCategoryXPath = "//span[text()='Waste Category']/parent::div/following-sibling::div/div/input";
//	public String drpWastwtypeXPath = "//span[text()='Waste Type']/parent::div/following-sibling::div/div/input";
//	public String drpWastdisposalMethodXPath = "//span[text()='Waste Disposal Method']/parent::div/following-sibling::div/div/input";
//	public String txtMassOfwasteProducedXPath = "//span[text()='Mass of Waste Produced']/parent::div/following-sibling::div//input[@name='waste_produce']";
//	public String drpVehicleForTransactionScope1XPath = "//span[text()='Vehicle']/parent::div/following-sibling::div";
//	public String txtNumberOfVehiclesXPath = "//span[text()='Number of Vehicles']//parent::div//following-sibling::div//input";
//	public String btnSaveXPath = "//button[text()='Save']";
//	public String btnAddActivitiesXPath = "//span[text()='Activities']/following-sibling::article//*[local-name()='svg']";
//	public String btnEditActivityXPath = "//span[text()='Next']//parent::button//following::div/*[@data-testid='BorderColorOutlinedIcon']";
//	public String lblViewActivityXPath = "//*[text()='Activity Details']";
//	public String drpFacilityNameXPath = "(//span[text()='Facility Name']//parent::div//following-sibling::div//input//following::button)[2]";
//	public String txtStartDateXPath = "//span[text()='Start Date']//parent::div//following-sibling::div//input";
//	public String txtEndDateXPath = "//span[text()='End Date']//parent::div//following-sibling::div//input";
//	public String txtDescriptionXPath = "//span[text()='Description']//parent::div//following-sibling::div//input";
//	public String lblGHGCalculatorXPath = "//li[contains(text(),'GHG Calculators')]";
//	public String btnIndirectEmisssionXPath = "//div[contains(text(),'SCOPE 2')]";
//	public String btnbtnActivitiesXPath = "//span[contains(text(),'Activities')]//parent::div//article";
//	public String drpFacNameXPath = "//button[@title='Open']";
//	public String txtFacNameXPath = "//*[@id='FacilityName']";
//	public String drpEclectricityXPath = "//div/ul/li[contains(text(),'Electricity')]";
//	public String txtInvoiceNoXPath = "//input[@id='InvoiceNo']";
//
//	public String lblAddCutsomEmissionFactorXPath = "//h5[contains(text(),'Add Custom Emission Factor')]";
//	public String lblEditCutsomEmissionFactorXPath = "//h5[contains(text(),'Edit Custom Emission Factor')]";
//	public String btnParameterInputXPath = "//button[text()='Parameter Input']";
//	public String btnAddCstmEmsonFctrXPath = "//span[text()='Custom Emission Factors']/following-sibling::article//*[local-name()='svg']";
//	public String btnEditCstmEmsonFctrXPath = "(//div[@aria-colindex='1'][@role='gridcell'])[1]";
//	public String txtCutsomEFNameXPath = "//input[@id='Efname']";
//	public String drpActivityTypeXPath = "//span[contains(text(),'Activity Type')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String txtSourceCEFXPath = "//input[@id='SourceOfEmission']";
//	public String drpUnitCEFDenominatorXPath = "//span[contains(text(),'Unit of Custom EF (Denominator)')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String txtModeofCommuteXPath = "//*[@id='Mode of Transport']";
//	public String txtVehicleTypeXPath = "//span[text()='Vehicle Type/Commute Category']/parent::div/following-sibling::div/div/*[@id='Vehicle Type']";
//	public String txtCO2XPath = "//input[@id='CO2']";
//	public String txtCH4XPath = "//input[@id='CH4']";
//	public String txtN2OXPath = "//input[@id='N2O']";
//	public String txtBiofuelCO2XPath = "//input[@id='BiofuelCO2']";
//	public String drpUnitCEFNumeratorXPath = "//span[contains(text(),'Unit of Custom EF (Numerator)')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String btnSaveParemeterInputXPath = "//button[text()='Save']";
//	public String btnGHGCalcXPath = "//li[@aria-label='GHG Calculators']";
//	public String btnStationaryCombutionXPath = "//button[contains(text(),'Stationary Combustion')]";
//	public String btnCustomBasedCEFXPath = "//span[contains(text(),'Are you using a Custom Emission Factor?')]//following::input[1]";
//	public String drpCustomEmiFactorActivityXPath = "//span[contains(text(),'Custom Emission Factor')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String txtvalActivityCEFXPath = "//input[@id='emissionName']";
//	public String drpvalActivityCEFXPath = "//input[@id='emissionName']";
//	public String txtNotesCEFXPath = "//*[@id='notes']";
//	public String txtCalorificFactorXPath = "//input[@id='calorific_factor']";
//	public String drpCalorificFactorNumeratorXPath = "//input[@id='calorific_num']";
//	public String drpCalorificFactorDenominatorXPath = "//input[@id='calorific_deno']";
//	public String sabanciTagsXPath = "//input[@id='tag_id']";
//
//	public String txtAmountOfEnergyXPath = "//input[@id='amount_of_energy']";
//	public String droUnitsXPath = "(//span[text()='Units']/parent::div//parent::div//div)[4]";
//	public String dpdPeriodXPath = "//div[@class='datePicker']//div//div";
//	public String btnCloseXPath = "//*[@data-testid='CloseOutlinedIcon']";
//	public String lblGHGCalculatorsXPath = "//article//*[contains(text(),'GHG Calculators')]";
//	public String btnGHGScope2XPath = "//div[text()='Indirect Emissions']";
//	public String btnAddNewTranscationXPath = "//span[text()='Activities']/parent::div//article";
//	public String txtInputVoiceXPath = "//input[@id='invoiceNo']";
//	public String btnGenerateXPath = "//button[text()='Generate']";
//	public String weLocationBasedEmissionsXPath = "//span[contains(text(),'Location Based Emissions')]";
//	public String weMarketBasedLocationXPath = "//span[contains(text(),'Market Based Emissions')]";
//    
//	// Original WebElement declarations with XPath details removed
//	public String weKiloWattPerHourXpath = "//li[text()='kilowatt-hour (kWh)']";
//	public String weElectricityXpath = "//li[text()='Electricity']";
//	public String weHeatSteamCoolingXpath = "//li[text()='Heat, Steam and Cooling']";
//	public String drpPeriodXpath = "//span[text()='Period']//parent::div//following-sibling::div//span";
//	public String txtFacilityNameXpath = "//input[@id='facilityName']";
//	public String txtFuelTypeXpath = "//input[@id='fuelName']";
//	public String txtUnitTypeXpath = "//input[@id='unitType']";
//	public String txtUnitNameXpath = "//input[@id='unitName']//parent::div//button[@aria-label='Open']";
//	public String lblActivityDetails_PXpath = "//p[text()='Activity Details']";
//	// ---new
//	// code-----------------------------------------------------------------------------------------------
//	public String lblAddActivityXpath = "//*[text()='Add Activity']";
//	public String weDirectEmissionsXpath = "//div[text()='Direct Emissions']";
//	public String txtFuelAmountXpath = "//span[text()='Fuel Amount']//parent::div//following-sibling::div//input";
//	// Activity details
//	public String weFacilityNameXpath = "//span[text()='Facility Name']//parent::div//following-sibling::div";
//	public String weInVoiceNoXpath = "//span[text()='Invoice No.']//parent::div//following-sibling::div";
//	public String weInVoiceDateXpath = "//span[text()='Invoice Date']//parent::div//following-sibling::div";
//	public String weStartDateXpath = "//span[text()='Start Date']//parent::div//following-sibling::div";
//	public String weEndDateXpath = "//span[text()='End Date']//parent::div//following-sibling::div";
//	public String weCustomEmissionFactorXpath = "//span[text()='Custom Emission Factor']//parent::div//following-sibling::div";
//	public String weFuelTypeXpath = "//span[text()='Fuel Type']//parent::div//following-sibling::div";
//	public String weFuelAmountXpath = "//span[text()='Fuel Amount']//parent::div//following-sibling::div";
//	public String weUnitTypeXpath = "//span[text()='Unit Type']//parent::div//following-sibling::div";
//	public String weUnitXpath = "//span[text()='Unit']//parent::div//following-sibling::div";
//	// emission details
//	public String weEmissionDetails_tCO2Xpath = "//span[text()='tCO2']//parent::div//following-sibling::div";
//	public String weEmissionDetails_tCH4Xpath = "//span[text()='tCH4']//parent::div//following-sibling::div";
//	public String weEmissionDetails_tN2OXpath = "//span[text()='tN2O']//parent::div//following-sibling::div";
//	public String weEmissionDetails_tCO2eXpath = "//span[text()='tCO2e']//parent::div//following-sibling::div";
//	public String weEmissionDetails_BiofueltCO2Xpath = "//span[text()='Biofuel tCO2']//parent::div//following-sibling::div";
//	public String weEmissionDetails_BiofuelCO2Xpath = "//span[text()='Biofuel CO2']//parent::div//following-sibling::div";
//	public String weEmissionDetails_tCO2e_DailyXpath = "//span[text()='tCO2e (Daily)']//parent::div//following-sibling::div";
//	public String weEmissionDetails_BiofueltCO2DailyXpath = "//span[text()='Biofuel tCO2 (Daily)']//parent::div//following-sibling::div";
//	public String weEmissionDetails_EmissionFactorXpath = "//span[text()='Emission Factor']//parent::div//following-sibling::div";
//	public String btnActivitiesXpath = "//span[text()='Activities']/following-sibling::article//*[local-name()='svg']";
//	public String btnActivities_scope1Xpath = "//article[@aria-label='Add Activity']";
//	public String btnActivityScope1ForViewOrgDataXpath = "//article[@aria-label='View Organization Data']";
//	public String btnCloselatestXpath = "//button[text()='Close']";
//	public String txtFacilityNameScope3_1qaXpath = "(//input[@id='Facility Name']//parent::div//button)[2]";
//	public String lblActivityDetailsXpath = "//*[text()='Activity Details']";
//	public String lblCEFDetailsXpath = "//*[text()='View Custom Emission Factors']";
//	// ------------------Fuel&Energy-------------------------//
//	public String txtFacilityNameScope3_3Xpath = "//input[@id='Facility Name']";
//	public String txtDescrScope3_3Xpath = "//textarea[@id='description']";
//	public String txtDescrScope3_3QaXpath = "//input[@id='description']";
//	public String drpEnerfgyTypeXpath = "((//span[contains(text(),'Energy Type')])[1]//parent::div//following::div//div//input//parent::div//div//button)[2]";
//	public String drpFuelTypeXpath = "((//span[text()='Fuel Type'])[1]//parent::div//following-sibling::div//div//div//div//button)[2]";
//	public String drpFuelNameXpath = "((//span[contains(text(),'Fuel Name')])[1]//parent::div//following-sibling::div[@role='combobox']//div/div/div//button)[2]";
//	public String txtScope3_3FuelEnrgyUnitXpath = "//input[@id='unit_name']";
//	public String txtQuantityConsumedFuelEngyXpath = "//input[@id='amount']";
//	public String btnavergaeDataMethodXpath = "//button[contains(text(),'Average Data Method')]";
//
//	// -------Edit Activity---------
//	public String lblEditActivityFuelEnrgyXpath = "//h5[text()='Edit Activity']";
//	public String lblAddActivityFuelEnrgyXpath = "//p[text()='Activity Details']";
//	public String chbxCustomEmssionFactorFuelXpath = "//span[contains(text(),'Are you using a Custom Emission Factor?')]/parent::div/following-sibling::div/label/span/span";
//	public String drpCustomEmiFactorActivityFuelXpath = "((//span[contains(text(),'Custom Emission Factor')])[2]//parent::div//following-sibling::div[@role='combobox']//button)[2]";
//
//	// -------CEF-FuelAndEnergy----------
//	public String txtNameCEFForFuelXpath = "//input[@id='name']";
//	public String drpEnergyTypeFuelXpath = "//span[contains(text(),'Energy Type')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String txtSourceOfEmissionFactorFuelXpath = "//input[@id='source']";
//	public String drpUnitCEFFuelXpath = "//span[contains(text(),'Unit of Custom EF (Denominator)')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String txtco2eXpath = "//input[@id='co2e']";
//	public String drpUnitEmmsinFuelXpath = "//span[contains(text(),'Unit of Custom EF (Numerator)')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//
//	// -----------upstreamTrans--------------
//	public String txtFacilityNameScope3_4Xpath = "//input[@id='Facility Name']";
//	public String txtFacilityNameScope3_4QaXpath = "//input[@id='Facility Name']//following-sibling::div/button[2]";
//	public String txtDescrScope3_4Xpath = "//input[@id='description']";
//	public String invoceNoupStXpath = "//span[text()='Invoice No.']//parent::div//following-sibling::div//input";
//	public String txtServicePrvodrXpath = "//input[@id='service_provider']";
//	public String txtServiceTypeXpath = "//input[@id='service_type']";
//	public String drpModeOfFrghtXpath = "(//input[@id='mode_of_transport']//following-sibling::div/button)[2]";
//	public String drpVehcleXpath = "//input[@id='vehicle_name']";
//	public String drpTypeXpath = "//*[@id='type_name']";
//	public String drpFuel_SizeXpath = "(//input[@id='fuel_name']//parent::div//following::button)[2]";
//	public String drpFuel_Size3_9Xpath = "//span[contains(text(),'Fuel / Size')]//parent::div/parent::div/parent::div/div[3]//input";
//	public String txtWeightXpath = "//input[@id='weight']";
//	public String txtDistanceXpath = "//input[@id='distance']";
//	public String drpWeightUnitXpath = "//input[@id='weight_unit_name']";
//	public String drpWeightUnit3_9Xpath = "//span[contains(text(),'Weight Unit')]//parent::div/parent::div/parent::div/div[3]//input";
//	public String drpDisatnceUnitXpath = "//input[@id='distance_unit_name']";
//
//	// ----CEF---
//	public String btnWeightDistanceUpstreamXpath = "//button[text()='Weight/Distance-Based Method']";
//
//	// ------DownStream-transportation----
//	public String drpCalculationMethodXpath = "//span[contains(text(),'Calculation Method')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String drpModeOfFreightXpath = "//span[contains(text(),'Mode of Freight')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String drpWeightUnitDownXpath = "//input[@id='weight_unit_name']";
//	public String drpDistanceUnitDownXpath = "//input[@id='distance_unit_name']";
//	public String drpCurrencyDownstreamXpath = "//span[contains(text(),'Currency')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String drpUnitOfCustomEFNumXpath = "//span[contains(text(),'Unit of Custom EF (Numerator)')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String btnweightDistanceBasedXpath = "//button[text()='Weight/Distance Based']";
//	public String drpCustomEmissionDownStreamXpath = "//span[contains(text(),'Custom Emission')]//parent::div//parent::div/following-sibling::div/div/following-sibling::div/div/div/input";
//
//	// ----------------HomeOfficeTelevommuting---------------
//	public String txtDscrptnHmeOffceXpath = "//input[@placeholder='Description']";
//	public String txtContryHmeOffceXpath = "//input[@id='Country']";
//	public String drptypeHmeOffceXpath = "//input[@id='Type Of Home Office']//parent::div//following::button";
//	public String txtNoOfWrknDaysypeHmeOffceXpath = "//input[@id='Number of working days']";
//	public String txtNoOfWrknhHorsHmeOffceXpath = "//input[@id='Number of working hours per day']";
//	public String txtNoOfEmplysHmeOffceXpath = "//input[@id='Number of employees']";
//	public String txtWrkngRegimeHmeOffceXpath = "//input[@id='Working regime']";
//	public String txtWFHHmeOffceXpath = "//input[@id='Working from home']";
//	public String drpUnitsHmeOffceXpath = "//input[@id='Source Units']";
//	public String lblViewActivityHomeofficeXpath = "//*[text()='Activity Details']";
//
//	public String drpcutomEFHome = "//span[contains(text(),'Custom Emission Factor')]//parent::div//parent::div/following-sibling::div/div/div/div/button";
//	public String drpUnitsOfCustomHmeOffce = "//input[@placeholder='Unit of Custom EF (Denominator)']//parent::div//following::button";
//	public String drpUnitsOfCustomNumHmeOffce = "(//input[@placeholder=' Unit of Custom EF (Numerator)']//parent::div//following::button)[1]";
//	public String drpcutmEFHome = "//span[contains(text(),'Custom Emission Factor')]//parent::div//parent::div/following-sibling::div/div/div/div/button[2]";
//
//	// --------------Franchises------------
//	public String txtFacilityFrenchiseDmo = "//input[@id='facility_name']";
//	public String txtDescriptionFranchise = "(//input[@id='description'])[1]";
//	public String txtFrenchiseNameFrenchiseDmo = "//input[@id='franchisee_name']";
//	public String txtLeasedAssetFrenchiseDmo = "//input[@placeholder='Leased Asset']";
//	public String drpEnergyCatgryFrenchiseDmo = "//input[@id='energy_category_name']";
//	public String drpTypeFrenchiseDmo = "//input[@id='type_name']";
//	public String txtActcivityAmntFrenchiseDmo = "//input[@id='activity_amount']";
//	public String drpUnitsFranchise = "//input[@id='source_data_unit_name']";
//	public String assetSpecificMethod = "//button[contains(text(),'Franchisee Specific Method')]";
//	public String drpEnergyCategoryFranchise = "//input[@id='energy_category']";
//	public String drpunitOfCEFFranchise = "//input[@id='sourceDataUnitName']";
//	public String drpunitOfCEFNumFranchise = "//input[@id='co2EFUnitName']";
//
//	// ---------------indirectEmission----------
//	public String txtInvoiceNoIndirect = "//input[@id='Invoice Number']";
//	public String drpAmountEnergy = "//input[@id='amount_of_energy']";
//	public String drpUnitsIndirect = "(//span[text()='Units']//parent::div//following-sibling::div//div)[1]";
//	public String drpEnergyCategoryIndirect = "//input[@id='energy_category']//parent::div//button";
//	public String lblEditActivityIndirect = "//h5[text()='Edit Activity']";
//	public String lblAddActivityIndirect = "//h5[text()='Add Activity']";
//	public String lblTransactTiffany = "//*[text()='Activity Details']";
//
//	// --CEF-Indirect-Emission--
//	public String btnAddCEF = "//article[@aria-label='Add Custom Emission Factors']";
//	public String lblAddCEF = "//*[text()='Add Custom Emission Factors']";
//	public String btnPurchasedEnergy = "//button[contains(text(),'Purchased Energy')]";
//	public String txtNameOfCustomEF = "//input[@id='NameOfCustomEmission']";
//	public String drpEnergyType = "//span[contains(text(),'Energy Category')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String weEmissionDetails_CO2 = "//span[text()='CO2']//parent::div//following-sibling::div/div";
//	public String weEmissionDetails_CH4 = "//span[text()='CH4']//parent::div//following-sibling::div/div";
//	public String weEmissionDetails_N2O = "//span[text()='N2O']//parent::div//following-sibling::div/div";
//	public String weEmissionDetails_CO2e = "//span[text()='CO2e']//parent::div//following-sibling::div";
//	public String weEmissionDetails_CO2eYearly = "//span[text()='CO2e (Yearly)']//parent::div//following-sibling::div";
//	public String weEmissionDetails_CO2eELT = "//span[text()='CO2e']//parent::p//following-sibling::p";
//	public String drpCustomEmiFactorLocationScope2 = "//span[contains(text(),'Custom/Location Based Emission Factor')]//parent::div//parent::div/following-sibling::div/div/div";
//	public String drpCustomEmiFactorMarketScope2 = "//span[contains(text(),'Custom/Market Based Emission Factor')]//parent::div//parent::div/following-sibling::div/div/div";
//	public String chboxCstmLocatinBased = "//span[contains(text(),'Are you using a Custom Location Based Emission Factor?')]/parent::div/following-sibling::div/label/span/span";
//	public String chboxCstmMarketBased = "//span[contains(text(),'Are you using a Custom Market Based Emission Factor?')]/parent::div/following-sibling::div/label/span/span";
//	public String valunitConversion = "//span[text()='Unit Conversion']//parent::p//following-sibling::p";
//	public String btnPopUpCloseScope2 = "//span[text()='Activity updated successfully']/parent::div/following-sibling::div/div";
//
//	// --------------WasteGeneratedInOperations------------------Start---------------------------------
//	public String lblWasteGeneratedInOps = "//*[contains(text(),'Waste Generated in Operations')]";
//	public String drpWasteCategory = "(//span[text()='Waste Category']//parent::div//following-sibling::div/div/div//following::button)[2]";
//	public String drpWasteType = "//input[@id='wm_management_type']";
//	public String drpWasteDisposalMethod = "(//*[@data-testid='ArrowDropDownIcon']//parent::button)[4]";
//	public String drpUnits = "//input[@id='unit']";
//	public String txtMassOfWasteProduced = "//input[@id='waste_produce']";
//	public String btnWasteTypeSpecificMethod = "//button[contains(text(),'Waste Type Specific Method')]";
//	public String drpWasteCategoryCEF = "//span[contains(text(),'Waste Category')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String drpWasteTypeCEF = "//span[contains(text(),'Waste Type')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String drpWasteDisposalMethodCEF = "//span[contains(text(),'Waste Disposal Method')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String drpUnitOfCustomEFDenominator = "//span[contains(text(),'Unit of Custom EF (Denominator)')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String drpUnitofCustomEFNumeratorCEF = "(//span[contains(text(),'Unit of Custom EF (Numerator)')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']//*)[2]";
//	// ------------------WasteGeneratedInOperations---------------End-----------------------------------------------------------------------------
//
//	// -----------------------BusinessTravel----------------------Start----------------------------------------------------
//	public String lblBusinessTravel = "//*[contains(text(),'Business Travel')]";
//	public String btnDistanceBasedMethod = "//button[text()='Distance Based Method']";
//	public String btnSpendBasedMethod = "//button[text()='Spend Based Method']";
//	public String weBusinessTravelCalculator = "//div[text()='Business Travel']";
//	public String txtInvoiceNum = "//input[@id='Expense No']";
//	public String txtInvoiceDate = "//span[text()='Invoice Date']//parent::div//following-sibling::div//input";
//	public String txtExpenseDate = "//span[text()='Expense Date']//parent::div//following-sibling::div//input";
//	public String drpModeOfBusinessTravel = "//span[text()='Mode of Business Travel']//parent::div//following-sibling::div//input";
//	public String txtAmountSpent = "//input[@id='AmountofActivity']";
//	public String drpCurrency = "//input[@id='currency']";
//	public String drpTravelCategory = "//span[text()='Travel Category']//parent::div//following-sibling::div//input";
//	public String drpVehicleType = "//span[text()='Vehicle Type']//parent::div//following-sibling::div//input";
//	public String drpBusinessTravlUnits = "//input[@id='Units']";
//	public String txtDistanceTrvlByEmp = "//span[text()='Distance Travelled by Each Employee']//parent::div//following-sibling::div//input";
//	public String txtNoOfEmpTravel = "//input[@id='No of Employees Traveled']";
//	public String txtTravelDescription = "//span[text()='Travel Description']//parent::div//following-sibling::div//input";
//	public String txtNameOfCustomEFBussiness = "//input[@id='Efname']";
//	public String drpCalculationMethodBussiness = "//span[contains(text(),'Calculation Method')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String txtSourceOfEmisson = "//input[@id='SourceOfEmission']";
//	public String drpUnitOfCEFDenoBussines = "//span[contains(text(),'Unit of Custom EF (Denominator)')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String txtCOBussiness = "//input[@id='CO2']";
//	public String txtCH4BUssiness = "//input[@id='CH4']";
//	public String txtN2OBussiness = "//input[@id='N2O']";
//	public String txtBioFuelBussiness = "//input[@id='BiofuelCO2']";
//	public String txtCO2eBussiness = "//input[@id='CO2e']";
//	public String drpCustomEmiFactorActivityBussiness = "//input[@id='Emission']//parent::div//following::button";
//	public String lblEditCEF = "//*[text()='Edit Custom Emission Factors']";
//	// -----------------------BusinessTravel----------------------end----------------------------------------------------
//
//	// -----------EmployeeCommuting-----------------------------------Start---------------------------
//	public String lblEmployeeCommuting = "//*[contains(text(),'Employee Commuting')]";
//	public String weEmployeeCommutingCalculator = "//div[text()='Employee Commuting']";
//	public String txtAvgDailyCommuteDistance = "//span[text()='Average Daily Commute Distance ']//parent::div//following-sibling::div//input";
//	public String txtCommuteDescription = "//span[text()=' Commute Description']//parent::div//following-sibling::div//input";
//	public String txtNoOfEmpCommuted = "//span[text()=' Number of Employees Commuted']//parent::div//following-sibling::div//input";
//	public String txtGroupEmployee = "//span[text()='Employee / Group of Employees']//parent::div//following-sibling::div//input";
//	public String txtAvgCommuteDays = "//span[text()=' Average Commute Days']//parent::div//following-sibling::div//input";
//	public String drpModeOfCommute = "//span[text()='Mode of Commute']//parent::div//following-sibling::div//input";
//	public String drpModeOfCommuteEmployye = "//span[text()='Mode of Commute']//parent::div//following-sibling::div//button[@aria-label='Open']";
//	public String drpVehicleTypeEmployee = "//span[text()='Vehicle Type']//parent::div//following-sibling::div//button[@aria-label='Open']";
//	public String txtVehicleTypeEmployee = "//input[@id='Vehicle Type']";
//	public String txtCEFEmission = "//input[@id='Emission']";
//	public String btnEmployeeCommuting = "//button[contains(text(),'Distance Based Method')]";
//	public String drpCalculationMethodEC = "//span[text()='Calculation Method']//parent::div//following-sibling::div//button[@aria-label='Open']";
//	// -----------EmployeeCommuting-----------------------------------End---------------------------
//
//	// -------------------------Sidda Reddy------------------------------------
//	public String btnSaveTranscation = "(//button[text()='Save'])[2]";

	
//	public String searchtxt = "//input[@type='search']";
//	public String searchfacility = "//*[@data-testid='SearchOutlinedIcon']";
//
//	// Mobile Combustion Section Start
//	public String btnForwordScope1 = "//*[@data-testid='KeyboardArrowRightIcon']";
//	public String btnMobileCombution = "//button[contains(text(),'Mobile Combustion')]";
//	public String txtActivityTypeScope1 = "//input[@id='activityType']";
//	public String txtVehicleTypeScope1 = "//input[@id='vehicleTypeName']";
//	public String txtVehivleTypeScope1Icon = "//input[@id='vehicleTypeName']//parent::div//parent::div//button[@aria-label='Open']";
//	public String txtActivityAmountScope1 = "//input[@id='activityAmount']";
//	public String txtFuelTypeMobileCombuScope1 = "//input[@id='fuelSourceName']";
//	public String drpFacilityNameScope3_6 = "//span[contains(text(),'Facility Name')]//parent::div/parent::div//div//button[@aria-label='Open']";
//
//	// Parameter Input Section
//	public String drpCustomEmiFactorActivityMobile = "(//input[@id='emissionName']//parent::div//following::button)[2]";
//
//	// Refrigerants and Fugitives Section
//	public String btnRefrigerantsScope1 = "//button[contains(text(),'Fugitives')]";
//	public String btnRefrigerantsScope1QA = "//button[contains(text(),'Fugitives')]";
//	public String txtFacility_Name = "//input[@id='facility_name']";
//	public String txtEquipmentRefrigerantType = "//input[@id='type_of_equipment']";
//	public String drpRefrigerantUsed = "//input[@id='refrigerant_ref_name']";
//	public String txtDecreaseinInventory = "//input[@id='start_inventory']";
//	public String drpRefrigePurchased = "//input[@id='purchased_from_producer']";
//	public String txtPurchase_Acquisition = "//input[@id='end_inventory']";
//	public String txtRefrigerantreturned = "//input[@id='equipment_user_return']";
//	public String txtRefrigerantreturnedOffSite = "//input[@id='recycle_reclamation_return']";
//	public String txtRefrigerantcharged = "//input[@id='charged_into_equipment']";
//	public String txtRefrigerantdelivered = "//input[@id='delivered_to_equipment']";
//	public String txtRefrigerantreturnedtorefrigerant = "//input[@id='returned_to_producer']";
//	public String txtRefrigerantsentoffsiteRecycling = "//input[@id='recycle_reclamation_sent_offsite']";
//	public String txtRefrigerantsentoffsitedestruction = "//input[@id='destruction_sent_offsite']";
//	public String txtInput_Voice = "//input[@id='invoice_no']";


	
//	public String weScope3Purchased_Good_Ser = "//div[text()='Purchased Goods and Services']";
//	public String txtFacilityNameScope3_1 = "//input[@id='FacilityName']";
//	public String txtDescrScope3 = "//input[@id='description']";
//	public String drpPurchasedGoodCategory = "//span[contains(text(),'Purchased Goods Category')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String drpPurchasedGood = "//input[@id='material_used_name']";
//	public String drpProduProcInvolved = "//span[contains(text(),'Production Process Involved')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String txtScope3PurchaseGoodUnit = "//input[@id='unit']";
//	public String txtQuantityofGoodsPurch = "//input[@id='amount_of_material']";
//	public String btnSaveScope3PurcGood = "//button[text()='Save']";
//	public String btnScope3_1AverageBased = "//button[contains(text(),'Average Data Method')]";
//	public String drpCalcMethod = "//span[contains(text(),'Calculation Method')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String txtSource = "//input[@id='source']";
//	public String drpPurchasedGoodCEF = "(//span[contains(text(),'Purchased Good')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open'])[2]";
//	public String txtC02e = "//input[@id='co2e']";
//	public String drpUnitCEF = "//span[contains(text(),'Unit of Custom Factor')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String drpUnitEmissionFactor = "//span[contains(text(),'Unit Emission Factor')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String txtCutsomEFName_1 = "//input[@id='name']";
//	public String txtFuelTypeCEF = "//input[@id='fuel_type']";
//	public String btnScope3_1SpendBased = "//button[contains(text(),'Spend Based Method')]";
//	public String drpPurchasedGoods_orServices = "//span[contains(text(),'Purchased Goods or Services')]//parent::div/parent::div//input//parent::div//button[@aria-label='Open']";
//	public String txtAmountSpentScope3spend = "//input[@id='amount_spend']";
//	public String drpCurrencySpendBased = "//span[contains(text(),'Currency')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String txtPurchaseGood_Ser = "//input[@id='pg_sb_activity_type_name']";
//	public String txtPurchasedGoodCategory = "//input[@id='pg_activity_type']";
//	public String txtPurchasedGood = "//input[@id='material_used']";
//	public String weScope3Capital_Good_Ser = "//div[text()='Capital Goods']";
//	public String txtFacilityNameScope3_2 = "//input[@id='facilityName']";
//	public String drpCapitalGoodCategory = "//span[contains(text(),'Capital Goods Category')]//parent::div/parent::div//input//parent::div//button[@aria-label='Open']";
//	public String drpCapitalGood = "(//span[contains(text(),'Capital Good')])[2]//parent::div/parent::div//input//parent::div//button[@aria-label='Open']";
//	public String txtQuantityofGoodsCapital = "//input[@id='quantity_purchase']";
//	public String txtCapitalGoods_3_2 = "//input[@id='activity_type_name']";
//	public String txtCapitalGood_3_2 = "//input[@id='material_used_name']";
//	public String txtProdProInv3_2 = "//input[@id='usage_type_name']";
//	public String btnSaveScope3CapitalGood = "//button[text()='Save']";
//	public String txtProductionProcInvolved = "//input[@id='usage_type']";
//	public String txtCapitalGoodsCategoCEF = "//input[@id='pg_activity_type']";
//	public String txtCapitalGoodCEF = "//input[@id='material_used']";
//	public String btnScope3_2SpendBased = "//button[contains(text(),'Spend Based Method')]";
//	public String drpCapitalGoodsCategorySpend3_2 = "//span[contains(text(),'Capital Goods Category')]//parent::div/parent::div//input//following-sibling::div//button[@aria-label='Open']";
//	public String txtCapitalGoodsCateg_3_2 = "//input[@id='sb_activity_type_name']";
//	public String txtCapitalGoodSpendBased3_2 = "//input[@id='material_used']";
//	public String txtProductProInvSppendBse3_2 = "//input[@id='usage_type']";
//	public String lblActivityDetailsSppendBse3_2 = "//p[text()='Activity Details']";
//	public String weScope3_12EndOfLife = "//div[text()='End-of-Life Treatment of Sold Products']";
//	public String txtFacilityNameScope3_12 = "//input[@id='Facility Name']";
//	public String txtDescrScope3_12 = "//*[@id='description']";
//	public String drpProduWsateCate = "//span[contains(text(),'Product Waste Category')]//parent::div/parent::div//div//button[@aria-label='Open']";
//	public String txtProduWasteCate = "//input[@id='product_category']";
//	public String drpProduWasteType = "//span[contains(text(),'Product Waste Type')]//parent::div/parent::div//div//button[@aria-label='Open']";
//	public String txtProduWasteType = "//input[@id='product_waste_type']";
//	public String drpWasteTreatMethod = "//span[contains(text(),'Waste Treatment Method')]//parent::div/parent::div//div//button[@aria-label='Open']";
//	public String drpWasteTreatMethodqa = "//span[contains(text(),'Waste Disposal Method')]//parent::div/parent::div//div//button[@aria-label='Open']";
//	public String txtWasteTreatMethod = "//input[@id='waste_treatment_method']";
//	public String txtMassofWaste = "//input[@id='waste_produce']";
//	public String btnScope3_12EOLT = "//button[contains(text(),' Waste Type Specific Method')]";
//
//	 

	
//	public String weScope3_HotelStay = "//div[text()='Hotel Stay']";
//	public String txtFacilityNameScope3_6 = "//input[@id='FacilityName']";
//	public String txtTravelDescrpScope3_6 = "//input[@id='Travel Purpose']";
//	public String txtTravelDescrpScope3_6UAT = "//input[@id='Travel Purpose']";
//	public String drpCountryHotelStay = "//span[contains(text(),'Country of Hotel Stay')]//parent::div/parent::div//div//button[@aria-label='Open']";
//	public String txtCountryOfHotelstay = "//input[@id='Countries']";
//	public String txtRoomsOccupied = "//input[@id='OccupiedRooms']";
//	public String txtRoomsOccupiedUAT = "//input[@id='Occupied Rooms']";
//	public String txtNightStayed = "//input[@id='Numberofnights']";
//	public String txtNightStayedUAT = "//input[@id='Number of Nights']";
//	public String txtUnitsHotelStay = "//input[@id='Units']";
//	public String btnSaveHotelStay = "//button[text()='Save']";
//	public String lblActivityDetailsScope3_6 = "//div[contains(text(),'Edit Activity')]";
//	public String westartdateHotelstay = "//span[text()='Start Date']";
//	public String txtC02e_1 = "//input[@id='CO2e']";
//	public String btnScope3_6HotelStay = "//button[contains(text(),'Activity Based Method')]";
//	public String txtvalActivityCEF_1 = "//input[@id='Custom Emission']";
//	public String weScope3_8UpstreamLeasedAsset = "//div[text()='Upstream Leased Assets']";
//	public String drpEnergyCategoryLeasedassests = "//span[contains(text(),'Energy Category')]//parent::div/parent::div//div//button[@aria-label='Open']";
//	public String drpTypeLeasedAsset = "//span[contains(text(),'Type')]//parent::div/parent::div//div//button[@aria-label='Open']";
//	public String txtLeasedAsset = "//input[@id='leased_asset']";
//	public String txtActivityAmountLeasedAsset = "//input[@id='activity_amount']";
//	public String txtunitLeasedAsset = "//input[@id='source_data_unit_name']";
//	public String btncancelLeasedAssets = "(//button[text()='Cancel'])[2]";
//	public String drpEnergyCategory = "//span[contains(text(),'Energy Category')]//parent::div//parent::div/following-sibling::div//button[@aria-label='Open']";
//	public String btnULADLA = "//button[contains(text(),'Asset Specific Method')]";
//	public String txtvalActivityCEF_2 = "//input[@id='emission_name']";
//	public String weScope3_8DownstreamLeasedAsset = "//div[text()='Downstream Leased Assets']";
//	public String drpSoldPrducts = "//span[contains(text(),'Sold Product')]//parent::div/parent::div//div//button[@aria-label='Open']";
//	public String drpProcessTypeSoldPrducts = "//span[contains(text(),'Process Type')]//parent::div/parent::div//div//button[@aria-label='Open']";
//	public String txtMassOfSoldProduct = "//input[@id='Amount']";
//	public String weScope3_10ProcessingSoldProduct = "//div[text()='Processing of Sold Products']";
//	public String txtSoldProduct = "//input[@id='productName']";
//	public String txtProcessType = "//input[@id='processType']";
//	public String drpCustomEF = "//span[contains(text(),'Custom EF')]//parent::div/parent::div//div//button[@aria-label='Open']";
//	public String weScope3_1UseSoldProduct = "//div[text()='Use of Sold Products']";
//	public String txtNo_ofUnitsSold = "//input[@id='unit_sold']";
//	public String drpFacilityNameScope3_11 = "//span[contains(text(),'Facility')]//parent::div/parent::div//div//button[@aria-label='Open']";
//	public String txtfacility = "//input[@id='facility_name']";
//	public String txtSoldProductUSP = "//input[@id='product']";
//	public String txtProductLifeTime = "//input[@id='product_life']";
//	public String txtElectricityConsumption = "//input[@id='electricity_consumption']";
//	public String drpUnitsCO2e = "//span[contains(text(),'Units of CO2e')]//parent::div//parent::div//following-sibling::div//button[@aria-label='Open']";
//	public String txtvalActivityCEF1 = "//input[@id='custom_emission_factor_name']";
//	public String weScope3_15Investments = "//div[text()='Investments']";
//	public String txtInvesteeCompanyScope3_15 = "//input[@id='investee_company']";
//	public String drpInvesteeCompany = "//span[contains(text(),'Investee Company Sector')]//parent::div/parent::div//div//button[@aria-label='Open']";
//	public String txtreporting_company_share3_15 = "//input[@id='reporting_company_share']";
//	public String txtScope1tCO2eEmissions3_15 = "//input[@id='scope1_co2e']";
//	public String txtScope2tCO2eEmissions3_15 = "//input[@id='scope2_co2e']";
//	public String txtScope3tCO2eEmissions3_15 = "//input[@id='scope3_co2e']";
//	public String txtTotaltCO2eEmissions3_15 = "//input[@id='ghg']";
//	public String txtSourcs3_15 = "//input[@id='source']";
//	public String drpInvesteeCompanyssectorAverage = "//span[contains(text(),'equity-investment.company-sector')]//parent::div/parent::div//div//button[@aria-label='Open']";
//	public String drpCEFAverageInvestments = "//span[contains(text(),'Custom Emission Factor')]//parent::div/parent::div//div//button[@aria-label='Open']";
//	public String txtRevenueOfInvesteeAverage = "//input[@id='revenue_of_investee']";
//	public String txtInvesteeCompanyShareInvesteeAverage = "//input[@id='investee_company_share']";
//	public String txtReportingCompanyShareInvesteeAverage = "//input[@id='reporting_company_share']";
//	public String btnInvestmentsspecificMethod = "//button[contains(text(),'Investment Specific Method')]";
//	public String btnInvestmentsAveargeMethod = "//button[contains(text(),'Average Data Method')]";
//	public String txtValueofDebtInvest = "//input[@id='value_of_debt']";
//	public String txtTotalProjectCost = "//input[@id='total_project_cost']";
//	public String drpUnitDebtInvest = "//input[@id='currency_code']";
//	public String txtSourceDebtInvest = "//input[@id='source']";
//	public String drpInvesteeCompanyDebtFinance = "//span[contains(text(),'Investee Company/Project Sector')]//parent::div/parent::div//div//button[@aria-label='Open']";
//	public String drpSectorInvestment = "//span[contains(text(),'Sector')]//parent::div/parent::div//div//button[@aria-label='Open']";
//	public String btnEquityInvestments = "//button[contains(text(),'Equity Investments')]";
//	public String txtSourceCEFUOSL = "//input[@id='notes']";
//	public String lblEditCutsomEmissionFactorInvestee = "//h5[contains(text(),'Edit Custom Emission')]";
//	public String drpCEFDebtAverage = "//span[contains(text(),'Custom EF')]//parent::div/parent::div//div//button[@aria-label='Open']";
//	public String tctProjectPhase = "//input[@id='project']";
//	public String txtValueofDebt = "//input[@id='value_of_debt']";
//	public String txtProjectCost = "//input[@id='total_project_cost']";
//	public String lblTransactions_Calc = "//li[contains(text(),'Transactions')]";

}
