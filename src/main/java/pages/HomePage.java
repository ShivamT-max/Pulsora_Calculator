package pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.TestBase;
import utilities.Data;

public class HomePage extends TestBase {
	 HomePage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	@FindBy(xpath = "//*[text()='Home']")
	private WebElement lblHomePage;


	@Override
	protected void VerifyNavigationToValidPage() {
		try {
//			waitForElement(lblHomePage);
//			if (isElementPresent(lblHomePage)) {
//				passed("User Successfully Navigated To Home Page");
//			} else {
//				failed(driver, "Failed To Navigated To Home Page");
//			}
//			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
	public MenuBarPage returnMenuPage() {
		return new MenuBarPage(driver, data);
	}


	@FindBy(xpath = "//button[@aria-label='Comment']//button")
	private WebElement btnComments;
	@FindBy(xpath = "//button[@aria-label='Comment']//button")
	private WebElement commentsHeader;
	@FindBy(xpath = "//button[text()='Internal']")
	private WebElement btnInternalTab;
	@FindBy(xpath = "//button[text()='External']")
	private WebElement btnExternalTab;
	@FindBy(xpath = "//span[contains(text(),'omments')]")
	private WebElement weCommentsCOunt;
	@FindBy(xpath = "//*[@data-testid='CloseIcon']")
	private WebElement btnCloseComment;

	public void verifyMessagesLoadedInHomePage() {
		try {
			clickOn(btnComments, "Comments");
			verifyIfElementPresent(commentsHeader, "Comments Header", "Comments");
			String commentsCnt = weCommentsCOunt.getText();
			List<WebElement> lstCOmments = driver.findElements(
					By.xpath("//span[contains(text(),'omments')]/parent::div/parent::div/following-sibling::div"));
			String[] arrCommentsCnt = commentsCnt.split(" ");
			if (Integer.parseInt(arrCommentsCnt[0]) == lstCOmments.size()) {
				passed("Internal Comments count is matching with number of comments " + lstCOmments.size());
			} else {
				failed(driver, "Internal Comments count is not matching with number of comments " + lstCOmments.size());
			}
			clickOn(btnExternalTab, "External Comments");
			Thread.sleep(5000);
			commentsCnt = weCommentsCOunt.getText();
			arrCommentsCnt = commentsCnt.split(" ");
			lstCOmments = driver.findElements(
					By.xpath("//span[contains(text(),'omments')]/parent::div/parent::div/following-sibling::div"));
			if (Integer.parseInt(arrCommentsCnt[0]) == lstCOmments.size()) {
				passed("External Comments count is matching with number of comments " + lstCOmments.size());
			} else {
				failed(driver, "External Comments count is not matching with number of comments " + lstCOmments.size());
			}
			clickOn(btnCloseComment, "Close Comments");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//*[@data-testid='NotificationsNoneIcon']")
	private WebElement btnNotificationIcon;
	@FindBy(xpath = "//div[text()='Notifications']")
	private WebElement weNotificationsHeader;
	@FindBy(xpath = "//div[text()='Notifications']/following-sibling::div[contains(text(),'New')]")
	private WebElement weNotificationsCount;
	@FindBy(xpath = "//div[contains(text(),'Notifications')]/parent::div/following-sibling::div[contains(@class,'MuiBox-root')]")
	private WebElement weNotificationsBox;
	@FindBy(xpath = "//*[@data-testid='CloseIcon']")
	private WebElement btnCloseNotification;

	public void verifyNotificationLoadedInHomePage() {
		try {
			clickOn(btnNotificationIcon, "Notification Icon");
			verifyIfElementPresent(weNotificationsHeader, "Notification Header", "Notification");
			String commentsCnt = weNotificationsCount.getText();
			String[] arrCommentsCnt = commentsCnt.split(" ");
			if (Integer.parseInt(arrCommentsCnt[1]) > 0) {
				List<WebElement> lstNotifications = driver.findElements(By.xpath(
						"//div[contains(text(),'Notifications')]/parent::div/following-sibling::div[contains(@class,'MuiBox-root')]"));
				if (lstNotifications.size() > 0) {
					passed("Notifications are displayed " + lstNotifications.size());
				} else {
					failed(driver, "Notifications are not displayed ");
				}
			} else {
				info("No Notifications are available");
			}
			clickOn(btnCloseComment, "Close Comments");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//button[@aria-label='Profile']")
	private WebElement btnAccount;
	@FindBy(xpath = "//li[@role='menuitem']/div[text()='Profile']")
	private WebElement btnProfileMenu;
	@FindBy(xpath = "//h2[text()='Profile']")
	private WebElement weProfileHeader;

	public void verifyUserProfileDetails() {
		try {
			clickOn(btnAccount, "Account Icon");
			verifyIfElementPresent(btnProfileMenu, "Profile", "Profile");
			clickOn(btnProfileMenu, "Notification Icon");
			verifyIfElementPresent(weProfileHeader, "Profile Header", "Profile");
			verifyUserData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyUserData() {
		try {
			// Work Phone Number
			String[] userDetailsLabel = { "User Name", "Work Email ID", "First Name", "Last Name", "Organization Name",
					"Policy Accepted" };
			String[] excelData = { "UserName", "WorkEmailID", "FirstName", "LastName", "OrganizationName",
					"PolicyAccepted" };
			String sUserData = "";
			for (int i = 0; i < userDetailsLabel.length; i++) {
				WebElement we = driver
						.findElement(By.xpath("//h4[text()='" + userDetailsLabel[i] + "']/following-sibling::h3"));
				sUserData = we.getText().trim();
				if (userDetailsLabel[i] == "Work Phone Number") {
					sUserData = sUserData.toString();
				}
				if (sUserData.equalsIgnoreCase(data.get(excelData[i]).trim())) {
					passed("User data is displayed as expected ==> " + sUserData);
				} else {
					failed(driver, "User data is not displayed as expected ==> " + sUserData);
				}
			}
			System.out.println("test");
			System.out.println("test");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//button[@aria-label='Comment']//button")
	private WebElement btnCommentIconHomePage;
	@FindBy(xpath = "//*[@data-testid='CloseIcon']/parent::button/parent::div[text()='Comments']")
	private WebElement commentsHeaderPopup;
	@FindBy(xpath = "//button[@aria-label='Comment']//button/following-sibling::span")
	private WebElement commentsTotalCount;
	@FindBy(xpath = "//button[text()='Internal']")
	private WebElement btnInternalCommentsTab;
	@FindBy(xpath = "//button[text()='External']")
	private WebElement btnExternalCommentsTab;

	public void verifyNavigationOfInternalCommentsFromHomePage() {
		try {
			Random rnd = new Random();
			String strBeforeCreateCommentsCount;
			clickOn(btnCommentIconHomePage, "Comment Icon");
			Thread.sleep(5000);
			verifyIfElementPresent(commentsHeaderPopup, "Comments Header", "Comments");
			String internalCommentSelectionStatus = btnInternalCommentsTab.getAttribute("aria-selected");
			if (internalCommentSelectionStatus.trim().equals("true")) {
				passed("By-default Internal comment tab is selected ");
			} else {
				failed(driver, "By-default Internal comment tab is not selected ");
			}
			WebElement commentsCount = driver.findElement(By.xpath(
					"//button[text()='Internal']/parent::div/parent::div/parent::div/following::div[1]/div/span"));
			String[] arrCommentsCnt = commentsCount.getText().split(" ");
			if (Integer.parseInt(arrCommentsCnt[0]) > 0) {
				int max = Integer.parseInt(arrCommentsCnt[0]), min = 1, num;
				num = rnd.nextInt((max - min) + 1) + min;
				WebElement weCommentCard = driver.findElement(By.xpath(
						"//button[text()='Internal']/parent::div/parent::div/parent::div/following::div[1]/div/span/parent::div/parent::div/following-sibling::div["
								+ num + "]//div//div[contains(text(),'Metric')]"));
				WebElement catalog = driver.findElement(By.xpath(
						"//button[text()='Internal']/parent::div/parent::div/parent::div/following::div[1]/div/span/parent::div/parent::div/following-sibling::div["
								+ num + "]//div//div[contains(text(),'Metric')]/preceding-sibling::div/div[1]/div[2]"));
				String catalogName = catalog.getAttribute("aria-label");
				jsClick(weCommentCard, "Comments Card");
				waitForPageLoad(driver, 20);
				// ui/li[text()='Catalogs']/following-sibling::li[text()='TestCL_ExternalComments_01']
				WebElement weCatalog = driver.findElement(By.xpath(
						"//ui/li[text()='Catalogs']/following-sibling::li[text()='" + data.get("CatalogName") + "']"));
				if (verifyElementAndHighlightIfExists(weCatalog, "Catalog Name", "Catalog")) {
					passed("Navigated to respective catalog page and catalog name is => " + data.get("CatalogName"));
				} else {
					failed(driver, "Unable to navigate to catalog page and selected comments from Catalog is => "
							+ data.get("CatalogName"));
				}
			} else {
				failed(driver, "Internal Comments count is not matching with number of comments " + arrCommentsCnt[0]);
			}
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
	}

	public void verifyNavigationOfExternalCommentsFromHomePage() {
		try {
			Random rnd = new Random();
			String strBeforeCreateCommentsCount;
			clickOn(btnCommentIconHomePage, "Comment Icon");
			Thread.sleep(5000);
			verifyIfElementPresent(commentsHeaderPopup, "Comments Header", "Comments");
			clickOn(btnExternalCommentsTab, "External Comments");
			Thread.sleep(2000);
			String externalCommentSelectionStatus = btnExternalCommentsTab.getAttribute("aria-selected");
			if (externalCommentSelectionStatus.trim().equals("true")) {
				passed("External comment tab is selected ");
			} else {
				failed(driver, "External comment tab is not selected ");
			}
			WebElement commentsCount = driver.findElement(By.xpath(
					"//button[text()='External']/parent::div/parent::div/parent::div/following::div[1]/div/span"));
			String[] arrCommentsCnt = commentsCount.getText().split(" ");
			if (Integer.parseInt(arrCommentsCnt[0]) > 0) {
				int max = Integer.parseInt(arrCommentsCnt[0]), min = 1, num;
				num = rnd.nextInt((max - min) + 1) + min;
				WebElement weCommentCard = driver.findElement(By.xpath(
						"//button[text()='External']/parent::div/parent::div/parent::div/following::div[1]/div/span/parent::div/parent::div/following-sibling::div["
								+ num + "]//div//div[contains(text(),'Metric')]"));
				WebElement catalog = driver.findElement(By.xpath(
						"//button[text()='External']/parent::div/parent::div/parent::div/following::div[1]/div/span/parent::div/parent::div/following-sibling::div["
								+ num + "]//div//div[contains(text(),'Metric')]/preceding-sibling::div/div[1]/div[2]"));
				String catalogName = catalog.getAttribute("aria-label");
				jsClick(weCommentCard, "Comments Card");
				waitForPageLoad(driver, 20);
				WebElement weCatalog = driver.findElement(By.xpath(
						"//ui/li[text()='Catalogs']/following-sibling::li[text()='" + data.get("CatalogName") + "']"));
				if (verifyElementAndHighlightIfExists(weCatalog, "Catalog Name", "Catalog")) {
					passed("Navigated to respective catalog page and catalog name is => " + data.get("CatalogName"));
				} else {
					failed(driver, "Unable to navigate to catalog page and selected comments from Catalog is => "
							+ data.get("CatalogName"));
				}
			} else {
				warn("There is no internal comments to navigate and number of external comments are: "
						+ arrCommentsCnt[0]);
			}
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
	}

	@FindBy(xpath = "//button[@aria-label='Notification']")
	private WebElement btnNotification;
	@FindBy(xpath = "//div[text()='Notifications']")
	private WebElement txtHeaderNotifications;
	@FindBy(xpath = "//button/*[@data-testid='CloseIcon']")
	private WebElement btnNotifyClose;

	public void clickOnNotificationBellbuttonInHomePage() {
		driver.navigate().refresh();
		driver.navigate().refresh();
		waitForElement(btnNotification);
		clickOn(btnNotification, "Notification button ");
	}

	public void closeNotifications() {
		waitForElement(btnNotifyClose);
		clickOn(btnNotifyClose, "Notifications close");
	}

	public void validateNotificationTextInHomePage(String notificationText) {
		try {
			waitForElement(btnNotification);
			if (isElementPresent(txtHeaderNotifications)) {
				//
			} else {
				clickOn(btnNotification, "Notifications");
			}
			String xpathNotification = "//div[text()='Notifications']//parent::div//following-sibling::div//span[contains(text(),\""
					+ notificationText + "\")]";
			sleep(3000);
			WaitForElementWithDynamicXpath(xpathNotification);
			if (isElementWithDynamicXpath(xpathNotification)) {
				passed("Successfully validated Notification text In notifications As"
						+ driver.findElement(By.xpath(xpathNotification)).getText());
			} else {
				failed(driver, "Failed To validate Notification text In notifications");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean verifyElementAndHighlightIfExists(WebElement element, String elemName, String pageName) {
		boolean blnFnd = false;
		try {
			if (isElementPresent(element)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
				passed(elemName + "Element is displayed in " + pageName + " page");
				blnFnd = true;
			} else {
				failed(driver, elemName + "Element is not displayed in " + pageName + " page");
			}
		} catch (Exception e) {
			failed(driver, "Exception occured ==> " + e.getMessage());
		}
		return blnFnd;
	}

	@FindBy(xpath = "//li/div[text()='Switch Organization']")
	private WebElement btnSwitchOrg;
	@FindBy(xpath = "//button[text()='Switch']")
	private WebElement btnSwitch;

	public void selectSwitchOrgInHomePage() {
		try {
			jsClick(btnAccount, "Account Icon");
			jsClick(btnSwitchOrg, "Switch Org button");
			WebElement weSwitchOrgName = driver
					.findElement(By.xpath("//*[text()='Select any Organization ']//following::div[text()='"
							+ data.get("switchOrgName") + "']"));
			clickOn(weSwitchOrgName, "Switch Org name");
			clickOn(btnSwitch, "Switch button ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static HomePage dataqw(Data data) {
		try {
			//System.out.println(data);
			System.out.println(data.get("UserName"));
		} catch (Exception e) {
			System.out.println("exception");
		}
		return new HomePage(driver, data);
		
	}
	
	
	public void sysr() {
		System.out.println(data.get("UserName"));
		System.out.println(data.get("UserName"));
		System.out.println(data.get("UserName"));
	}
	
	
	
}
