package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import base.TestBase;
import utilities.Constants;
import utilities.Data;

public class AdminPage extends TestBase {

	protected AdminPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	@FindBy(xpath = "//article//*[text()='Admin']")
	private WebElement lblAdmin;
	@FindBy(xpath = "//article/*[contains(text(),'Admin')]")
	private WebElement lblCatalogs;
	@FindBy(xpath = "//button/*[@aria-label='Period Config']")
	private WebElement btnPeriodConfig;
	@FindBy(xpath = "//*[@aria-label='Facilities']")
	private WebElement btnFacilities;
	@FindBy(xpath = "//*[@aria-label='Assignee Setup']/div")
	private WebElement btnAssigneeSetupConfiguration;
	@FindBy(xpath = "//*[@aria-label='Events']")
	private WebElement btnEvents;
	@FindBy(xpath = "//div[@aria-label='Organizations']")
	private WebElement btnOrganisations;
	public Admin_PeriodConfigPage clickOnPeriodConfigButton() {
		clickOn(btnPeriodConfig, "Period Config button");
		return new Admin_PeriodConfigPage(driver, data);
	}

	public Admin_FacilitiesPage clickOnFacilities() {
		clickOn(btnFacilities, "Facilities button");
		return new Admin_FacilitiesPage(driver, data);
	}

	public EventsPage clickOnEvents() {
		clickOn(btnEvents, "Events button");
		return new EventsPage(driver, data);
	}

	public void clickOnOrganisations() {
		try {
			clickOnElement(btnOrganisations, "Organisations button");
		} catch (Exception e) {
			failed(driver, "Failed To Navigate To Organisation Page");
		}

	}

	@FindBy(xpath = "//span[text()='Organizations']/div/div")
	private WebElement drpOrganizationAdmin;
	@FindBy(xpath = "//button[text()='Create Period']")
	private WebElement btnCreatePeriod;
	@FindBy(xpath = "//input[@id='periodId']")
	private WebElement inputPeriodId;
	@FindBy(xpath = "//input[@id='periodDesc']")
	private WebElement inputPeriodDesc;
	@FindBy(xpath = "//input[@id='periodStartDate']")
	private WebElement inputPeriodStartDate;
	@FindBy(xpath = "//input[@id='periodEndDate']")
	private WebElement inputPeriodSEndDate;
	@FindBy(xpath = "//div[@id='activeFlag']")
	private WebElement drpActiveFlag;
	@FindBy(xpath = "//input[@id='sortedOrder']")
	private WebElement inputSortOrder;
	@FindBy(xpath = "//button[text()='Create']")
	private WebElement btnCreate;
	String periodID;
	@FindBy(xpath = "//li[@aria-label='Organizations']/div")
	private WebElement btnOrganizations;
	
	public void selectOrgInAdminPeriod() {
		clickOn(drpOrganizationAdmin, "clicked on organization drop in admin screen");
		WebElement orgName = driver.findElement(By.xpath("//ul/li[text()='"+data.get("Organization")+"']"));
		clickOn(orgName, "select org name ent.org.aut002");
	}
	public void validatePeriodConfig() {
		createPeriodAdmin();
		WebElement tostMessage = driver
				.findElement(By.xpath("//div[contains(text(),'successfully')]"));
		if (isElementPresent(tostMessage)) {
			passed("Sucessfully created period for ent.org.aut002");
		} else {
			fail("failed to create period for ent.org.aut002");
		}
//		clickOn(btnCreatePeriod, "clicked on cretae period");
		//validateDuplicatePeriod();
	}

	@FindBy (xpath = "//div[@id='periodType']")
	public WebElement drpPerioType;
	public void createPeriodAdmin() {
		try {
			clickOn(btnCreatePeriod, "clicked on cretae period");
//			periodID = generateRandomString(6);
			clickOn(drpPerioType, "Period Type");
			WebElement listPeriodType = driver.findElement(By.xpath("//ul//li[text()='"+data.get("Period Type")+"']"));
			clickOn(listPeriodType, data.get("Period Type"));
			periodID = data.get("PeriodID");
			enterText(inputPeriodId, "enter period ID ", periodID);			
			enterText(inputPeriodDesc, "enter period Description ", data.get("Period Descr"));
			enterText(inputPeriodStartDate, "enter period StartDate ", data.get("Start Date"));
			enterText(inputPeriodSEndDate, "enter period EndDate ", data.get("End Date"));
			clickOn(drpActiveFlag, "clicked on active flag");
			WebElement activeFlag = driver.findElement(By.xpath("//ul/li[text()='true']"));
			clickOn(activeFlag, "Active Flag");
			String sortOrder = "0";
			//String sortOrder = generateRandomNumber(2);
			enterText(inputSortOrder, "enter sort order ", sortOrder);
			System.out.println("sort order for period" + " " + periodID + " " + sortOrder);
			clickOn(btnCreate, "created period");
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}
	public Admin_AssigneeSetupConfiguration clickOnAssigneeSetupConfigButton() {
		sleep(2000);
		waitForElement(btnOrganizations);
		clickOn(btnOrganizations, "Organizations button");
		waitForElement(btnAssigneeSetupConfiguration);
		clickOn(btnAssigneeSetupConfiguration, "Assignee Setup Configuration button");
		return new Admin_AssigneeSetupConfiguration(driver, data);
	}

	public void validateDuplicatePeriod() {
		try {
			enterText(inputPeriodId, "enter period ID ", periodID);
			String periodDescp = generateRandomString(6);
			enterText(inputPeriodDesc, "enter period Description ", periodDescp);
			enterText(inputPeriodStartDate, "enter period StartDate ", "202301");
			enterText(inputPeriodSEndDate, "enter period EndDate ", "202312");
			clickOn(drpActiveFlag, "clicked on active flag");
			WebElement activeFlag = driver.findElement(By.xpath("//ul/li[text()='true']"));
			clickOn(activeFlag, "Active Flag");
			String sortOrder = generateRandomNumber(2);
			enterText(inputSortOrder, "enter sort order ", sortOrder);
			System.out.println("sort order for period" + " " + periodID + " " + sortOrder);
			clickOn(btnCreate, "created period");
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}
	public void clickOnOrganizationMenuInAdminPage() {
		try {
		clickOn(btnOrganization, "clicked on organization");
		}catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}
	
	public void clickOnUsersMenuInAdminPage() {
		try {
		clickOn(btnUsers, "clicked on Users");
		}catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}
	@FindBy(xpath = "//div[@aria-label='Organizations']")
	private WebElement btnOrganization;
	@FindBy(xpath = "//div[@aria-label='Users']")
	private WebElement btnUsers;
	@FindBy(xpath = "//div/*[@id='Name']")
	private WebElement txtName;
	@FindBy(xpath = "//div/*[@id='logoUrl']")
	private WebElement txtLogoURL;
	@FindBy(xpath = "//div[text()='Sector']//parent::div//parent::div//following-sibling::div//div[text()='Select']")
	private WebElement drpSector;
	@FindBy(xpath = "//div[text()='Primary Industry']//parent::div//parent::div//following-sibling::div//div[text()='Select']")
	private WebElement drpPrimaryIndustry;
	@FindBy(xpath = "//div[text()='Secondary Industry']//parent::div//parent::div//following-sibling::div//div[text()='Select']")
	private WebElement drpSecondaryIndustry;
	@FindBy(xpath = "//input[@id='country']")
	private WebElement drpCountry;
	@FindBy(xpath = "//input[@id='city']")
	private WebElement drpCity;
	@FindBy(xpath = "//div[text()='Status']//parent::div//parent::div//following-sibling::div//div[text()='Select']")
	private WebElement drpStatusFlag;
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement btnSaveOrg;
	@FindBy(xpath = "//*[@data-testid='AddOutlinedIcon']")
	private WebElement btnAddOrg;
	public void vaildateAddingOrganization() {
		clickOn(btnAddOrg, "btnAddOrg");
		enterText(txtName, "Name of the Organization", data.get("Org Name"));
		enterText(txtLogoURL, "Logo Url", data.get("Logo URL"));
		clickOn(drpSector, " Clicked on drpSector");
//		enterText(drpSector, "City", data.get("Sector"));
		WebElement weSector = driver.findElement(By.xpath("//li[text()='"+data.get("Sector")+"']"));
		System.out.println(data.get("Sector"));
		clickOn(weSector, data.get("Sector"));
		clickOn(drpPrimaryIndustry, " Clicked on drpPrimaryIndustry");
		WebElement wePrimarySector = driver.findElement(By.xpath("//li[text()='"+data.get("Primary Industry")+"']"));
		clickOn(wePrimarySector, data.get("Primary Industry"));
		clickOn(drpSecondaryIndustry, " Clicked on drpSecondaryIndustry");
		WebElement weSecondarySector = driver.findElement(By.xpath("//ul//li[text()='"+data.get("Secondary Industry")+"']"));
		clickOn(weSecondarySector, data.get("Secondary Industry"));
		clickOn(drpCountry, " Clicked on drpCountry");
		List<WebElement> ve = driver.findElements(By.xpath("//ul[@id=\"country-listbox\"]//li"));
		ArrayList listOfCoutry = new ArrayList<>();
		for(int i=0;i<ve.size();i++) {
			listOfCoutry.add(ve.get(i).getText());
			System.out.println(listOfCoutry.get(i));
		}
		
		
		enterText(drpCountry, "Country", data.get("Country"));
		//drpCountry.sendKeys(Keys.ENTER);
		sleep(1000);
		WebElement weSCountry = driver.findElement(By.xpath("//*[text()='"+data.get("Country")+"']"));
		clickOn(weSCountry, data.get("Country"));
		drpCountry.sendKeys(Keys.TAB);
		sleep(2000);
//		Actions act = new Actions(driver);
//		act.sendKeys(Keys.TAB).build().perform();
//		act.sendKeys(Keys.RETURN).build().perform();
		clickOn(drpCity, " Clicked on drp City");
		enterText(drpCity, "City", data.get("City"));
		//drpCity.sendKeys(Keys.ENTER);
		sleep(1000);
		WebElement weCity = driver.findElement(By.xpath("//*[text()='"+data.get("City")+"']"));
		clickOn(weCity, data.get("City"));
		clickOn(drpStatusFlag, " Clicked on drp Status");
		WebElement weStatus = driver.findElement(By.xpath("//li[text()='Active']"));
		clickOn(weStatus, "Clicked on Status Flag");
		System.out.println("Hello");
		clickOn(btnSaveOrg, "Clicked on button save Org");
		System.out.println("divjslf");
		
	}
	
	@FindBy(xpath = "//div/*[@id='firstName']")
	private WebElement txtFirstName;
	@FindBy(xpath = "//div/*[@id='lastName']")
	private WebElement txtLastNameName;
	@FindBy(xpath = "//div/*[@id='emailId']")
	private WebElement txtEmailId;
	@FindBy(xpath = "//div/*[@id='username']")
	private WebElement txtUserName;
	@FindBy(xpath = "//div[@id='organization']")
	private WebElement btnOrgs;
	@FindBy(xpath = "//h5[text()='Organization']//parent::div//following-sibling::div//input")
	private WebElement txtOrgName;
	@FindBy(xpath = "//div[text()='Select Organization']")
	private WebElement btndefaultOrgs1;
	@FindBy(xpath = "//h5[text()='Default Organization']//parent::div//following-sibling::div//input")
	private WebElement btndefaultOrgs;
	@FindBy(xpath = "//h5[text()='User Role']//parent::div//following-sibling::div//input")
	private WebElement btnUserRole;
	
	
	public void addOrgs() {
		try {
			Data data;
			ArrayList<String> datasets;
			data = new Data(Constants.AddUsers);
			datasets = data.getDataSets();
			data.setColIndex();
			for (String dataset : datasets) {
				data.setIndex_Multiple(dataset);
		     	enterText(txtOrgName, "Enter Org Names", data.get("OrgNames"));
		     	WebElement weorgs = driver.findElement(By.xpath("//h5[text()='Organization']//parent::div//following-sibling::div//div[text()='"+data.get("OrgNames")+"']"));
				Actions mouse = new Actions(driver);
				mouse.moveToElement(weorgs).click().perform();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FindBy(xpath = "//div[@aria-label='Users']")
	private WebElement btnUser;
	@FindBy(xpath = "//*[@aria-label='Add']")
	private WebElement btnAddUser;
	@FindBy(xpath = "//h2[text()='Add User']")
	private WebElement lblverifyAddUser;
	@FindBy(xpath = "//input[@id='firstName']")
	private WebElement inputFirstName;
	@FindBy(xpath = "//input[@id='lastName']")
	private WebElement inputLastName;
	@FindBy(xpath = "//input[@id='username']")
	private WebElement inputUserName;
	@FindBy(xpath = "//h5[text()='User Role']//following::div[@id='role']")
	private WebElement drpUserRole;
	@FindBy(xpath = "//h5[text()='Organization']//following::div[@id='organization']")
	private WebElement drpOrganization;
	@FindBy(xpath = "//div[text()='Select Organization']")
	private WebElement drpDefaultOrganization;
	@FindBy(xpath = "//input[@id='emailId']")
	private WebElement inputEmail;
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement btnSaveAdmin;
	@FindBy(xpath = "//i[@aria-label='Toggle Organization']")
	private WebElement btnToggleOrg;
	@FindBy(xpath = "//p[text()='ALL']")
	private WebElement btnAll;
	public void defaultOrgs() {
		try {
			Data data;
			ArrayList<String> datasets;
			data = new Data(Constants.AddUsers);
			datasets = data.getDataSets();
			data.setColIndex();
			for (String dataset : datasets) {
				data.setIndex_Multiple(dataset);
		     	enterText(txtOrgName, "Enter Org Names", data.get("OrgNames"));
		     	WebElement weorgs = driver.findElement(By.xpath("//li[text()='"+data.get("DefaultOrgNames")+"']"));
				clickOn(weorgs, data.get("DefaultOrgNames"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	ArrayList<String> orgsNames = new ArrayList<>();
	public void vaildateAddingOrganizationintoUsers() {	
		sleep(2000);
		clickOn(btnAddOrg, "btnAddOrg");
		enterText(txtFirstName, "First Name", data.get("First Name"));
		
		enterText(txtLastNameName, "Last Name", data.get("Last Name"));
		
		enterText(txtEmailId, "Email", data.get("EmailId"));
		
		enterText(txtUserName, "User Name", data.get("User Name"));
		clickOn(btnOrgs, "btnOrgs");
	}
		public void vaildateAddingOrganizationintoUsers1() {	
		clickOn(btndefaultOrgs1, "btndefaultOrgs");
     	enterText(btndefaultOrgs, "Enter Org Names", data.get("DefaultOrgNames"));
     	WebElement weorgs = driver.findElement(By.xpath(data.get("DefaultOrgNames")));
		clickOn(weorgs, data.get("DefaultOrgNames"));
//		defaultOrgs();
	    clickOn(btnUserRole, "btnUserRole");
    	enterText(btnUserRole, "Enter Org Names", data.get("OrgNames"));
     	WebElement weUserRole = driver.findElement(By.xpath(data.get("UserRole")));
		clickOn(weorgs, data.get("UserRole"));
//		clickOn(btnSaveOrg, "Clicked on button save Org");
	}
		public Admin_AssigneeSetupConfiguration clickOnAssigneeSetupConfigButtonTiffany() {
	        sleep(2000);
	        waitForElement(btnOrganizations);
	        clickOn(btnOrganizations, "Organizations button");
	        waitForElement(btnAssigneeSetupConfiguration);
	        clickOn(btnAssigneeSetupConfiguration, "Assignee Setup Configuration button");
	        return new Admin_AssigneeSetupConfiguration(driver, data);
	    }
		
	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(lblAdmin);
			if (isElementPresent(lblAdmin)) {
				passed("User Successfully Navigated To Admin Page");
			} else {
				failed(driver, "Failed To Navigate To Admin Page");
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

}
