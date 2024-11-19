package uitests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.TestBase;
import pages.AdminPage;
import pages.Admin_AssigneeSetupConfiguration;
import pages.CatalogPage;
import pages.DataRequestPage;
import pages.EventsPage;
import pages.GoalsPage;
import pages.HomePage;
import pages.MenuBarPage;
import utilities.Common;
import utilities.Constants;
import utilities.Data;
import utilities.GlobalKeys;
import utilities.GlobalVariables;

@Listeners({ utilities.TestListener.class })
public class DataRequestPageTests extends Common {
	public static int count = 1;
	private TestBase TestBase;
	private HomePage HomePage;
	private MenuBarPage MenuBarPage;
	private GoalsPage GoalsPage;
	private CatalogPage CatalogsPage;
	private DataRequestPage DataRequestPage;
	private pages.SignInPage SignInPage;
	private pages.TasksPage TasksPage;
	private AdminPage adminPage;
	private EventsPage eventsPage;
	private Admin_AssigneeSetupConfiguration adminassigneeSetupConfiguration;
	public Data data;
	public ArrayList<String> datasets;

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
	public void TC001_ValidateCreateDataRequestFlowsInPulsESG() {
		String strName = new Exception().getStackTrace()[0].getMethodName();
		data.setColIndex(strName);
		for (String dataset : datasets) {
			data.setIndex(dataset);
			datasetStart(dataset);
			System.out.println("Assertions");
			SignInPage = TestBase.setUp(data);
			HomePage = SignInPage.SignInToPulsEsGApp();
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			CatalogsPage = MenuBarPage.clickOnCatalogsMenu();
			CatalogsPage.clickOnCatalogInCatalogPage(data.get("CatalogName"));
			CatalogsPage.configPageCatalogSortOrder(data.get("topic"));
			MenuBarPage.clickOnHamburgerMenu();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			String flow = data.get("ApprovalWorkFlowLevels").trim();
			switch (flow) {
			case "No Level Approval Flow":
				System.out.println("");
				DataRequestPage.validationNoLevelApprovalFlow();
				break;
			case "One Level Approval Flow":
				System.out.println("");
				DataRequestPage.validationOneLevelApprovalFlow();
				break;
			case "Two Level Approval Flow":
				System.out.println("");
				DataRequestPage.validationTwoLevelApprovalFlow();
				break;
			case "Multi Level Approval Flow with Multiple approvers":
				System.out.println("");
				DataRequestPage.validationMultipleLevelApprovalFlowWithMultipleAprovers();
				break;
			case "All with Two level Approval with Rejection":
				System.out.println("");
				DataRequestPage.validatDRforNoAndOneLevelApprovalAndTwoLevelRejectionFlow();
				break;
			case "All Approval Flow":
				DataRequestPage.validatDRforNoAndOneAndTwoLevelApprovaFlows();
				break;
			default:
				warn("No Flow is slected");
			}
			TestBase.tearDown();
			datasetEnd();
		}
	}

//	@Test
//	public void TC002_ValidateDataRequestCatalogPublishBlocker() {
//		String strName = new Exception().getStackTrace()[0].getMethodName();
//		data = new Data(Constants.catalogDRPublishBlocker);
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
//				DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
//				DataRequestPage.createDataRequestInDataRequestPageWithAllTopics();
//				DataRequestPage.validateCreatedDataRequestInDataRequestPage();
//				SignInPage = MenuBarPage.logOut();
//				HomePage = SignInPage.signInToPulsEsGApp(data.get("PortCoUserName"), data.get("PortCoPassword"));
//				HomePage = SignInPage.SignInToPulsEsGApp();
//				MenuBarPage = HomePage.returnMenuPage();
//				MenuBarPage.clickOnHamburgerMenu();
//				DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
//				DataRequestPage.createDRandSigninWithProtcoandClickingonTasksGrid();
//				SignInPage = MenuBarPage.logOut();
//				HomePage = SignInPage.SignInToPulsEsGApp(data.get("PortCoUserName"), data.get("PortCoPassword"), true);
//				MenuBarPage = HomePage.returnMenuPage();
//				MenuBarPage.clickOnHamburgerMenu();
//				TasksPage = MenuBarPage.clickOnTasksMenu();
//				TasksPage.clickOnGridViewInTasksPage();
//				TasksPage = TasksPage.clickOnStartTaskInTasksPage();
//			}
//			System.out.println(data.get("TopicName"));
//			TasksPage.ValidatePublishBlocker(data.get("TopicName"));
//			datasetEnd();
//		}
//		TestBase.tearDown();
//	}

	
	@Test
	public void TC002_ValidateDataRequestCatalogPublishBlocker() {
		String strName = new Exception().getStackTrace()[0].getMethodName();
		data = new Data(Constants.catalogDRPublishBlocker);
		datasets = data.getDataSets();
		data.setColIndex();
		List<String> logInDetails = new ArrayList();
		for (String dataset : datasets) {
			data.setIndex_Multiple(dataset);
			datasetStart(strName + dataset);
			if (!logInDetails.contains(data.get("UserName"))) {
				logInDetails.add(data.get("UserName"));
				SignInPage = TestBase.setUp(data);
//				HomePage = SignInPage.SignInToPulsEsGApp();
//				MenuBarPage = HomePage.returnMenuPage();
//				MenuBarPage.clickOnHamburgerMenu();
//				DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
//				DataRequestPage.createDRandSigninWithProtcoandClickingonTasksGrid();
//				SignInPage = MenuBarPage.logOut();
				HomePage = SignInPage.SignInToPulsEsGApp(data.get("PortCoUserName"), data.get("PortCoPassword"), true);
				MenuBarPage = HomePage.returnMenuPage();
				MenuBarPage.clickOnHamburgerMenu();
				TasksPage = MenuBarPage.clickOnTasksMenu();
				TasksPage.clickOnGridViewInTasksPage();
				TasksPage = TasksPage.clickOnStartTaskInTasksPage();
			}
			System.out.println(data.get("TopicName"));
			TasksPage.ValidatePublishBlocker(data.get("TopicName"));
			datasetEnd();
		}
		TestBase.tearDown();
	}
	
	
	//@Test
//	public void TC004_ValidateAllMetricInputDataTypesInCatalogForDRNoApprovalFlowSimplifiedView() {
//		String strName = new Exception().getStackTrace()[0].getMethodName();
//		data = new Data(Constants.DRMetricAllInputTypesEntrySimpl);
//		datasets = data.getDataSets();
//		data.setColIndex();
//		List<String> logInDetails = new ArrayList();
//		for (String dataset : datasets) {
//			data.setIndex_Multiple(dataset);
//			datasetStart(strName + dataset);
//			if (!logInDetails.contains(data.get("UserName"))) {
//				logInDetails.add(data.get("UserName"));
//				GlobalVariables.userName = data.get("UserName");
//				GlobalVariables.password = data.get("Password");
//				System.out.println(data.get("CatalogName"));
//				GlobalVariables.catalogName = data.get("CatalogName");
//				GlobalVariables.orgName = data.get("OrgName");
//				SignInPage = TestBase.setUp(data);
//				HomePage = SignInPage.SignInToPulsEsGApp();
//				MenuBarPage = HomePage.returnMenuPage();
//				MenuBarPage.clickOnHamburgerMenu();
//				DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
//				DataRequestPage.createDRandSigninWithProtcoandClickingonTasksGrid();
//				SignInPage = MenuBarPage.logOut();
//				HomePage = SignInPage.SignInToPulsEsGApp(data.get("PortCoUserName"), data.get("PortCoPassword"), true);
//				MenuBarPage = HomePage.returnMenuPage();
//				MenuBarPage.clickOnHamburgerMenu();
//				TasksPage = MenuBarPage.clickOnTasksMenu();
//				TasksPage.clickOnGridViewInTasksPage();
//				TasksPage = TasksPage.clickOnStartTaskInTasksPage();
//			}
//			TasksPage.ValidateCatalogMetricInputTypeEntries(data.get("InputType"));
//			TasksPage.clickOnAnswerButtonMetricDetails();
//		}
//		TasksPage.publishCatalogInTaskPage();
//		SignInPage = MenuBarPage.logOut();
//		HomePage = SignInPage.signInToPulsEsGApp(GlobalVariables.userName, GlobalVariables.password);
//		MenuBarPage = HomePage.returnMenuPage();
//		MenuBarPage.clickOnHamburgerMenu();
//		DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
//		DataRequestPage.validateApprovalDRFlowStatusForCompleteApproval("1", "1");
//		DataRequestPage.NavigateToDRWorkFlowMonitor();
//		DataRequestPage.validateWorkFlowMonitorStatusForDRCompleteApproval("1", "100", "0");
//		DataRequestPage.ValidateDataRequestNameLinkNavigationToValidPage(GlobalVariables.orgName,
//				GlobalVariables.catalogName);
//		DataRequestPage.ValidateDataInputValuesinMetricsvalue();
//		SignInPage = MenuBarPage.logOut();
//		datasetEnd();
//		TestBase.tearDown();
//	}
	
	//@Test
//	public void TC003_ValidateAllMetricInputDataTypesInCatalogForDRNoApprovalFlow() {
//		String strName = new Exception().getStackTrace()[0].getMethodName();
//		data = new Data(Constants.CatalogDRMetricAllInputTypesEntry);
//		datasets = data.getDataSets();
//		data.setColIndex();
//		List<String> logInDetails = new ArrayList();
//		for (String dataset : datasets) {
//			data.setIndex_Multiple(dataset);
//			datasetStart(strName + dataset);
//			if (!logInDetails.contains(data.get("UserName"))) {
//				logInDetails.add(data.get("UserName"));
//				GlobalVariables.userName = data.get("UserName");
//				GlobalVariables.password = data.get("Password");
//				System.out.println(data.get("CatalogName"));
//				GlobalVariables.catalogName = data.get("CatalogName");
//				GlobalVariables.orgName = data.get("OrgName");
//				SignInPage = TestBase.setUp(data);
//				HomePage = SignInPage.SignInToPulsEsGApp();
//				MenuBarPage = HomePage.returnMenuPage();
//				MenuBarPage.clickOnHamburgerMenu();
//				DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
//				DataRequestPage.createDRandSigninWithProtcoandClickingonTasksGridWithSummaryHistory();
//				SignInPage = MenuBarPage.logOut();
//				HomePage = SignInPage.signInToPulsEsGApp(data.get("PortCoUserName"), data.get("PortCoPassword"));
//				MenuBarPage = HomePage.returnMenuPage();
//				MenuBarPage.clickOnHamburgerMenu();
//				TasksPage = MenuBarPage.clickOnTasksMenu();
//				TasksPage.clickOnGridViewInTasksPage();
//				CatalogsPage = TasksPage.clickOnCatalogNameInTasksPage();
//			}
//			CatalogsPage.ValidateCatalogMetricInputTypeEntriesUpdateNextForth(data.get("InputType"));
//			datasetEnd();
//		}
//		CatalogsPage.publishDRInCatalogPage();
//		SignInPage = MenuBarPage.logOut();
//		HomePage = SignInPage.signInToPulsEsGApp(GlobalVariables.userName, GlobalVariables.password);
//		MenuBarPage = HomePage.returnMenuPage();
//		MenuBarPage.clickOnHamburgerMenu();
//		DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
//		DataRequestPage.validateApprovalDRFlowStatusForCompleteApproval("1", "1");
//		DataRequestPage.NavigateToDRWorkFlowMonitor();
//		DataRequestPage.validateWorkFlowMonitorStatusForDRCompleteApproval("1", "100", "0");
//		DataRequestPage.ValidateDataRequestNameLinkNavigationToValidPage(GlobalVariables.orgName,
//				GlobalVariables.catalogName);
//		DataRequestPage.ValidateDataInputValuesinMetricsvalue();
//		SignInPage = MenuBarPage.logOut();
//		datasetEnd();
//		TestBase.tearDown();
//	}
	
	@Test
	public void TC005_ValidateCatalogPeriodAggregationsWithDRFlow() {
		String strName = new Exception().getStackTrace()[0].getMethodName();
		data = new Data(Constants.DRatalogAggregations);
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
				DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			}
			DataRequestPage.ValidateDataRequestPeriodAggregations();
			CatalogsPage = MenuBarPage.clickOnCatalogsMenu();
			CatalogsPage.clickOnCatalogInCatalogPage(data.get("CatalogName"));
			CatalogsPage.selectOrganizationInCatalogDetailsPage(data.get("OrgName"));
			CatalogsPage.ValidatePeriodEnteredValuesAtCatalogInvestCo();
			CatalogsPage.ValidatePeriodAggregations();
			datasetEnd();
		}
		TestBase.tearDown();
	}

	@Test
	public void TC002_ValidateDataRequestsandTasksChips() throws InterruptedException {
		String strName = new Exception().getStackTrace()[0].getMethodName();
		data = new Data(Constants.DRChipsValidation);
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
				DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
				DataRequestPage.ValidateCreateDataRequestandValidateChips();
				MenuBarPage.clickOnDataRequestMenu();
				SignInPage = MenuBarPage.logOut();
				HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverPortCoId"),
						data.get("1stLevelApproverPortCoPassword"), true);
				MenuBarPage = HomePage.returnMenuPage();
				MenuBarPage.clickOnHamburgerMenu();
				TasksPage = MenuBarPage.clickOnTasksMenu();
				TasksPage.clickOnGridViewInTasksPage();
				TasksPage.verifyTasksCreationinProtco();
				MenuBarPage = HomePage.returnMenuPage();
				CatalogsPage = MenuBarPage.clickOnCatalogsMenu();
				CatalogsPage.searchAndSelectCatalogCard(data.get("CatalogName"));
				CatalogsPage.selectPeriod_And_TasksInCatalogFilters(data.get("Period"),GlobalVariables.dataRequestName);
				CatalogsPage.validateChips_ErrorsdrpCatalogRegression();
				CatalogsPage.ValidateRequiredDataforcatalogClearData();
				CatalogsPage.validateCatalogStatusForDRCompleteApproval("0", "0", "4", "4", "0");
				CatalogsPage.ValidateDataNotAvailableDataCatalogChips();
				CatalogsPage.validateCatalogStatusForDRCompleteApproval("0", "4", "4", "0", "0");
			}
			CatalogsPage.ValidateEnterDRFromPortCoenterData();
		}
		CatalogsPage.validateCatalogStatusForDRCompleteApproval("4", "0", "4", "0", "4");
		CatalogsPage.validateMetricDropDownValidations();
		CatalogsPage.ValidateEnterDataPositiveValuesCatalogChips();
		CatalogsPage.validateCatalogStatusForDRCompleteApproval("4", "0", "4", "0", "0");
		CatalogsPage.publishDRInCatalogPage();
		Thread.sleep(1000);
		SignInPage = MenuBarPage.logOut();
		HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverId"), data.get("1stLevelApproverPassword"),
				true);
		MenuBarPage = HomePage.returnMenuPage();
		MenuBarPage.clickOnHamburgerMenu();
		TasksPage = MenuBarPage.clickOnTasksMenu();
		TasksPage.clickOnGridViewInTasksPage();
		TasksPage.ClickOnTasksChipsDataRequestApprovalTaskInTasksPage(data.get("OrgName"));
		SignInPage = MenuBarPage.logOut();
		HomePage = SignInPage.SignInToPulsEsGApp();
		MenuBarPage = HomePage.returnMenuPage();
		MenuBarPage.clickOnHamburgerMenu();
		DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
		DataRequestPage.NavigateToDRWorkFlowMonitor();
		DataRequestPage.validateWorkFlowMonitorStatusForDRCompleteApproval("1", "100", "0");
		DataRequestPage.ValidateDRChipsinWorkflowmonitor(data.get("1stLevelApprovalOrgName"),
				data.get("1stLevelApprovalEnterpriseName"));
//	 	    CatalogsPage.validateWorkFlowMonitorStatusForDRCompleteApproval("4","0","4","0","0");
		SignInPage = MenuBarPage.logOut();
		TestBase.tearDown();
	}

	@Test
	public void TC006_ValidateDataRequestApprovalcompareTasksDetailsAndEventLogs() {
		String strName = new Exception().getStackTrace()[0].getMethodName();
		data.setColIndex(strName);
		for (String dataset : datasets) {
			data.setIndex(dataset);
			datasetStart(dataset);
			String path = System.getProperty("user.dir");
			String filePath = path + "\\src\\test\\resources\\DataRequestFiles\\EventsFile";
			SignInPage = TestBase.setUpWithFileDownload(data, filePath);
			HomePage = SignInPage.SignInToPulsEsGApp();
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			DataRequestPage.ValidateDataRequestApprovalcompareTasksDetails();
			TestBase.tearDown();
			datasetEnd();
		}
	}
//	@Test
//	public void TC002ValidateTiffanyDRflowWithSwitchOrgUser() {
//		String strName = new Exception().getStackTrace()[0].getMethodName();
//		data = new Data(Constants.TiffanySitchOrgDRFlow);
//		datasets = data.getDataSets();
//		data.setColIndex();
//		List<String> logInDetails = new ArrayList();
//		for (String dataset : datasets) {
//			data.setIndex_Multiple(dataset);
//			datasetStart(strName + dataset);
//			if (!logInDetails.contains(data.get("UserName"))) {
//				logInDetails.add(data.get("UserName"));
//				String path = System.getProperty("user.dir");
//				String filePath = path + "\\src\\test\\resources\\DataRequestFiles\\TiffanyCatalogData";
//				SignInPage = TestBase.setUpWithFileDownload(data, filePath);
//				HomePage = SignInPage.SignInToPulsEsGApp();
//			}
//			List<String> switchOrGName = new ArrayList();
//			if (!switchOrGName.contains(data.get("switchOrgName"))) {
//				HomePage.selectSwitchOrgInHomePage();
//				switchOrGName.add(data.get("switchOrgName"));
//				MenuBarPage = HomePage.returnMenuPage();
//				MenuBarPage.clickOnHamburgerMenu();
//				CatalogsPage = MenuBarPage.clickOnCatalogsMenu();
//				CatalogsPage.clickOnCatalogInCatalogPage(data.get("CatalogName"));
//				CatalogsPage.uploadMetricDataInCatalog();
//				MenuBarPage.clickOnHamburgerMenu();
//				datasetEnd();
//			}
//		}
//		MenuBarPage.logOut();
//		TestBase.tearDown();
//	}
	

	//@Test
//	public void TC007_ValidateDataRequestTiffanyApprovalWorkFlow() {
//	String strName = new Exception().getStackTrace()[0].getMethodName();
//	data = new Data(Constants.DRTiffanyFlow);
//	datasets = data.getDataSets();
//	data.setColIndex();
//	List<String> logInDetails = new ArrayList();
//	for (String dataset : datasets) {
//		data.setIndex_Multiple(dataset);
//		datasetStart(strName + dataset);
//		if (!logInDetails.contains(data.get("UserName"))) {
//			logInDetails.add(data.get("UserName"));
//			SignInPage = TestBase.setUp(data);
//			HomePage = SignInPage.SignInToPulsEsGApp();
//			MenuBarPage = HomePage.returnMenuPage();
//			MenuBarPage.clickOnHamburgerMenu();
//			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
//			DataRequestPage.validateTiffanyDRFlow();
//			SignInPage = MenuBarPage.logOut();
//			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverPortCoId"),
//					data.get("1stLevelApproverPortCoPassword"), true);
//			MenuBarPage = HomePage.returnMenuPage();
//			MenuBarPage.clickOnHamburgerMenu();
////			TasksPage = MenuBarPage.clickOnTasksMenu();
////			TasksPage.clickOnGridViewInTasksPage();
////			TasksPage = TasksPage.clickOnStartTaskInTasksPage();
//		}
//	}
//	}

	//@Test
	public void TC007_ValidateDataRequestTiffanyApprovalWorkFlow() {
	String strName = new Exception().getStackTrace()[0].getMethodName();
	data = new Data(Constants.DRTiffanyFlow);
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
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			DataRequestPage.validateTiffanyDRFlow();
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("DataProviderID"),
					data.get("DataProviderPassword"), true);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.verifyTasksandAddingDataInMetrics();
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp(data.get("1stLevelApproverId"),
					data.get("1stLevelApproverPassword"), true);
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			TasksPage = MenuBarPage.clickOnTasksMenu();
			TasksPage.ValidateOneLevelAprovalInTasksPageTiffanyFlow();
			SignInPage = MenuBarPage.logOut();
			HomePage = SignInPage.SignInToPulsEsGApp();
			MenuBarPage = HomePage.returnMenuPage();
			MenuBarPage.clickOnHamburgerMenu();
			DataRequestPage = MenuBarPage.clickOnDataRequestMenu();
			DataRequestPage.NavigateToDRWorkFlowMonitor();
			DataRequestPage.validateWorkFlowMonitorStatusForDRCompleteApproval("1", "100", "0");
			DataRequestPage.ValidateDRNameLinkNavigationToValidPageTiffanyFlow();
			SignInPage = MenuBarPage.logOut();
		}
	}
	TestBase.tearDown();
	datasetEnd();
	}

	
}
