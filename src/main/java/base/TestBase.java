package base;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
//import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.batik.swing.JSVGCanvas.ZoomOutAction;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.GHGCalculatorsPage;
import pages.SignInPage;
import utilities.Common;
import utilities.Data;
import utilities.GlobalKeys;

public abstract class TestBase extends Common {
	public static WebDriver driver;
	public static Data data;
	/* make file as static */
	public static File folder;

	protected abstract void VerifyNavigationToValidPage();

	protected TestBase(WebDriver driver2, Data data) { // constructor
		this.driver = driver2;
		this.data = data;			
		PageFactory.initElements(driver2, this);
		VerifyNavigationToValidPage();
	}

	
	public static SignInPage setUp(Data data) {
		try {
			 String path = System.getProperty("user.dir");
			String browserName = GlobalKeys.configData.get("BrowserName");
			String ApplicationEnvironment = GlobalKeys.configData.get("ApplicationEnvironment").toLowerCase();
			String urlExtension = GlobalKeys.configData.get("URLExtension").toLowerCase();
			if (browserName.equals("chrome")) {
				 System.setProperty("webdriver.chrome.driver",
				            path + "\\src\\test\\resources\\BrowserDrivers\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (browserName.equals("ie")) {
				System.setProperty("webdriver.edge.driver",
			            path + "\\src\\test\\resources\\BrowserDrivers\\msedgedriver.exe");
				driver = new EdgeDriver();
			}
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			String URL = String.format("https://%s.pulsesg.%s/dashboard/en_US", ApplicationEnvironment, urlExtension);
			driver.get(URL);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
		} catch (Exception e) {
			failAssert("Unable to launch application , Exception is : " + e.getMessage());
		}
		return new SignInPage(driver, data);
	}

	public static SignInPage setUpVerifyFile(Data data) {
		try {
			String browserName = GlobalKeys.configData.get("BrowserName");
			String ApplicationEnvironment = GlobalKeys.configData.get("ApplicationEnvironment").toLowerCase();
			String urlExtension = GlobalKeys.configData.get("URLExtension").toLowerCase();
			if (browserName.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				/*
				 * author -- Shivam for verifying file downloading
				 */
				folder = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\DataDestinationFiles");
				folder.mkdir();
				ChromeOptions options = new ChromeOptions();
				Map<String, Object> pref = new HashMap<String, Object>();
				pref.put("profile.default_content_settings.popups", 0);
				pref.put("download.default_directory", folder.getAbsolutePath());
				options.setExperimentalOption("prefs", pref);
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability(ChromeOptions.CAPABILITY, options);
				/*----*/
				driver = new ChromeDriver(options);
			} else if (browserName.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (browserName.equals("ie")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			}
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			String URL = String.format("https://%s.pulsesg.%s/login/en", ApplicationEnvironment, urlExtension);
			driver.get(URL);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (Exception e) {
			failAssert("Unable to launch application , Exception is : " + e.getMessage());
		}
		return new SignInPage(driver, data);
	}

	public static SignInPage setUpWithFileDownload(Data data, String fileDownloadPath) {
		try {
			String browserName = GlobalKeys.configData.get("BrowserName");
			File folder;
			String ApplicationEnvironment = GlobalKeys.configData.get("ApplicationEnvironment").toLowerCase();
			String urlExtension = GlobalKeys.configData.get("URLExtension").toLowerCase();
			if (browserName.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				// folder = new File(System.getProperty("user.dir")+
				// "\\src\\test\\resources\\DataDestinationFiles");
				folder = new File(fileDownloadPath);
				if (!folder.exists()) {
					folder.mkdir();
				}
				ChromeOptions options = new ChromeOptions();
				Map<String, Object> pref = new HashMap<String, Object>();
				pref.put("profile.default_content_settings.popups", 0);
				pref.put("download.default_directory", folder.getAbsolutePath());
				options.setExperimentalOption("prefs", pref);
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability(ChromeOptions.CAPABILITY, options);
				/*----*/
				driver = new ChromeDriver(options);
			} else if (browserName.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (browserName.equals("ie")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			}
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			String URL = String.format("https://%s.pulsesg.%s/login/en", ApplicationEnvironment, urlExtension);
			driver.get(URL);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (Exception e) {
			failAssert("Unable to launch application , Exception is : " + e.getMessage());
		}
		return new SignInPage(driver, data);
	}

	public static void tearDown() {
		try {
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void tearDownFileDownloadVerfiying() {
		/*
		 * custom method -- sD for deleting file and folder
		 */
		try {
			driver.quit();
			for (File file : folder.listFiles()) {
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getDateTimeStampString() {
		DateFormat originalFormat = null;
		Date date1 = null;
		try {
			Calendar calendar = Calendar.getInstance();
			originalFormat = new SimpleDateFormat("yyyymmdd_hh_mm_ss");
			date1 = calendar.getTime();
			originalFormat.format(date1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return originalFormat.format(date1);
	}

	public String getTimeFormatString(String time) {
		String formattedTime = null;
		try {
			DateFormat originalFormat = new SimpleDateFormat("hh:mm aa");
			DateFormat targetlFormat = new SimpleDateFormat("h:mm aa");
			Date format = originalFormat.parse(time);
			formattedTime = targetlFormat.format(format);
			System.out.println(formattedTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formattedTime;
	}

	public String getTodayUploadRecordDate() {
		String strDate1 = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			Date date1 = calendar.getTime();
			strDate1 = dateFormat.format(date1);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return strDate1;
	}

	public String getInVoiceDateFormat(String InVoiceDate) {
		String formattedDate = "";
		try {
			DateFormat originalFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
			DateFormat targetFormat = new SimpleDateFormat("MMMM d, yyyy");
			Date date;
			date = originalFormat.parse(InVoiceDate);
			formattedDate = targetFormat.format(date);
			System.out.println(formattedDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formattedDate;
	}

	/**
	 * Check if the element is present in the page
	 * 
	 * @param element WebElement need to check
	 * @return True/False
	 */
	protected boolean isElementPresent(WebElement element) {
		try {
			if (element.isEnabled()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	public void selectByValue(WebElement we, String strElemName, String strVisibleValue) {
		try {
			// if (waitForDropDown(we)) {
			Select sel = new Select(we);
			sel.selectByValue(strVisibleValue);
			passed("Selected value -" + strVisibleValue + " from dropdown->" + strElemName);
			// }
		} catch (Exception Ex) {
			failed(driver, "Unable to select value from the dropdown " + Ex.getMessage());
		}
	}

	public void selectByVisisbleText(WebElement we, String strElemName, String strVisibleText) {
		try {
			Select sel = new Select(we);
			sel.selectByVisibleText(strVisibleText);
			passed("Selected value -" + strVisibleText + " from dropdown->" + strElemName);
		} catch (Exception Ex) {
			failed(driver, "Unable to select value from the dropdown " + Ex.getMessage());
		}
	}

	/**
	 * Check if the element is present in the page
	 * 
	 * @param Element locator of type By
	 * @return True/False
	 */
	public boolean isElementPresent(By by) {
		try {
			new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(by));
			if (driver.findElement(by).isDisplayed()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * Check if the element is present in the page and report
	 * 
	 * @param element
	 * @param Name    of the Element
	 * @param Name    of the page
	 */
	protected boolean verifyIfElementPresent(WebElement element, String elemName, String pageName) {
		boolean blnSucc = false;
		try {
			waitForIsClickable(element);
			if (isElementPresent(element)) {
				passed(elemName + "Element is displayed in " + pageName + " page");
				blnSucc = true;
			} else {
				failed(driver, elemName + "Element is not displayed in " + pageName + " page");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blnSucc;
	}

	/***
	 * Method to check for an alert for 20 seconds
	 * 
	 * @param : Element Name
	 * @return : Modified By :
	 ***/
	public boolean isAlertPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Method to wait for element to load in the page
	 * 
	 * @param WebElement
	 */
	protected Boolean waitForIsClickable(WebElement we) {
		String str = null;
		try {
			str = we.toString();
			new WebDriverWait(driver, GlobalKeys.elementLoadWaitTime)
					.until(ExpectedConditions.elementToBeClickable(we));
			if (isElementPresent(we)) {
				return true;
			} else {
				failed(driver,
						"Element is not visible after waiting for " + GlobalKeys.elementLoadWaitTime + " Seconds");
				return false;
			}
		} catch (Exception ex) {
			failed(driver, "Element is not visible after waiting for " + GlobalKeys.elementLoadWaitTime + " Seconds, : "
					+ str);
			return false;
		}
	}

	/**
	 * Method to wait for element to load in the page
	 * 
	 * @param WebElement
	 */
	protected Boolean waitForElementPresent(By by) {
		String str = null;
		try {
			str = by.toString();
			new WebDriverWait(driver, GlobalKeys.elementLoadWaitTime)
					.until(ExpectedConditions.visibilityOfElementLocated(by));
			if (isElementPresent(by)) {
				return true;
			} else {
				failed(driver,
						"Element is not visible after waiting for " + GlobalKeys.elementLoadWaitTime + " Seconds");
				return false;
			}
		} catch (Exception ex) {
			failed(driver, "Element is not visible after waiting for " + GlobalKeys.elementLoadWaitTime + " Seconds, : "
					+ str);
			return false;
		}
	}

	/**
	 * Method to wait for element to load in the page
	 * 
	 * @param WebElement
	 */
	protected Boolean waitForIsClickable(By by) {
		String str = null;
		try {
			str = by.toString();
			new WebDriverWait(driver, GlobalKeys.elementLoadWaitTime)
					.until(ExpectedConditions.elementToBeClickable(by));
			if (isElementPresent(by)) {
				return true;
			} else {
				failed(driver,
						"Element is not visible after waiting for " + GlobalKeys.elementLoadWaitTime + " Seconds");
				return false;
			}
		} catch (Exception ex) {
			failed(driver, "Element is not visible after waiting for " + GlobalKeys.elementLoadWaitTime + " Seconds, : "
					+ str);
			return false;
		}
	}

	/**
	 * Method to wait for element to load in the page
	 * 
	 * @param by
	 */
	protected Boolean waitAndSwitchToFrame(By by) {
		String str = null;
		try {
			str = by.toString();
			new WebDriverWait(driver, GlobalKeys.elementLoadWaitTime)
					.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
			return true;
		} catch (Exception ex) {
			failed(driver, "Frame is not displayed after waiting for " + GlobalKeys.elementLoadWaitTime + " Seconds, : "
					+ str);
			return false;
		}
	}

	/***
	 * Method to wait for the list of elements to be displayed
	 * 
	 * @param : List<WebElement>
	 * @return : Modified By :
	 ***/
	public boolean waitForElementList(List<WebElement> elems) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, GlobalKeys.elementLoadWaitTime);
			wait.until(ExpectedConditions.visibilityOfAllElements(elems));
			return true;
		} catch (Exception Ex) {
			failed(driver,
					"Element List is not visible after waiting for " + GlobalKeys.elementLoadWaitTime + " Seconds");
			return false;
		}
	}

	public boolean waitForDropDown(WebElement weDropDown) {
		try {
			String str = weDropDown.toString();
			if (waitForIsClickable(weDropDown)) {
				for (int second = 0;; second++) {
					if (second >= 20) {
						failed(driver, "Values in dropdown are not loaded after waiting for 20 seconds");
						return false;
					}
					try {
						Select droplist = new Select(weDropDown);
						if (!droplist.getOptions().isEmpty()) {
							return true;
						}
					} catch (Exception e) {
						failed(driver,
								"Exception Caught while waiting for dropdown loading,Message is->" + e.getMessage());
						return false;
					}
					sleep(1000);
				}
			} else {
				failed(driver, "Dropdown Element is not visible, Expected Property of DropDown is->" + str);
				return false;
			}
		} catch (Exception ex) {
			failed(driver, "Exception Caught while waiting for dropdown loading,Message is->" + ex.getMessage());
			return false;
		}
	}

	/***
	 * Method to click on a link(WebElement button)
	 * 
	 * @param : WebElement
	 * @param : Element Name <<<<<<< HEAD
	 ***/
	public void clickOn(WebElement we, String elemName) {
		try {
			if (!waitForElement(we)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", we);
				
			}
			takeScreenshot(driver);
			String strProp = we.toString();
			if (isElementPresent(we)) {
				we.click();
				passed("Clicked on WebElement-" + elemName);

			} else {
				failed(driver, "Unable to click on Element " + elemName
						+ ", Element with following property is not displayed->" + strProp);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
			//logFailException(ex);
		}
	}

	public void clickElement(WebElement we, String elemName) {
		try {
			takeScreenshot(driver);
			if (isElementPresent(we)) {
				we.click();
				passed("Clicked on WebElement-" + elemName);
			} else {
				failed(driver, "Unable to click on Element " + elemName);
			}
		} catch (Exception ex) {
			logFailException(ex);
		}
	}

	public void clickOnElement(WebElement we, String elemName) {
		String strProp = we.getText();
		if (!waitForElement(we)) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", we);
		}
		takeScreenshot(driver);
		if (isElementPresent(we)) {
			we.click();
			passed("Clicked on WebElement-" + elemName);
		} else {
			failed(driver, "Unable to click on Element " + elemName
					+ ", Element with following property is not displayed->" + strProp);
		}
	}

	/***
	 * Method to click on a link(WebElement button)
	 * 
	 * @param : WebElement
	 * @param : Element Name <<<<<<< HEAD
	 ***/
	public void clickOnElementWithDynamicXpath(String xpath, String elemName) {
		try {
			sleep(200);
			WebElement we = driver.findElement(By.xpath(xpath));
			waitForElement(we);
			String strProp = we.toString();
			takeScreenshot(driver);
			if (isElementPresent(we)) {
				we.click();
				passed("Clicked on WebElement-" + elemName);
			} else {
				failed(driver, "Unable to click on Element " + elemName
						+ ", Element with following property is not displayed->" + strProp);
			}
		} catch (Exception ex) {
			failed(driver, "Exception Caught As" + ex.getMessage());
		}
	}

	/***
	 * Method to click on a link(WebElement button)
	 * 
	 * @param : WebElement
	 * @param : Element Name <<<<<<< HEAD
	 ***/
	public boolean WaitForElementWithDynamicXpath(String xpath) {
		try {
			new WebDriverWait(driver, GlobalKeys.elementLoadWaitTime)
					.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath))));
			return true;
		} catch (RuntimeException ex) {
			return false;
		}
	}

	/**
	 * Method to click on a link(WebElement link)
	 * 
	 * @param : WebElement
	 * @param : Element Name
	 */
	protected void jsClick(WebElement we, String elemName) {
		try {
			sleep(200);
			waitForElement(we);
			if (isElementPresent(we)) {
				System.out.println("yes");
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", we);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", we);
			passed("Clicked on -" + elemName + "- Element");
		} catch (RuntimeException ex) {
			failed(driver, "Uanble to click on Element-" + elemName + ", Exception is->" + ex.getMessage());
		}
	}

	public String jsGetText(WebElement we) {
		return (String) ((JavascriptExecutor) driver).executeScript("return jQuery(arguments[0]).text();", we);
	}

	public void actionsClick(WebElement we, String elemName) {
		try {
			sleep(200);
			if (!waitForElement(we)) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", we);
		}
		String strProp = we.toString();
		if (isElementPresent(we)) {
			Actions act = new Actions(driver);
			act.moveToElement(we).click(we).build().perform();
			passed("Clicked on WebElement-" + elemName);

		} else {
			failed(driver, "Unable to click on Element " + elemName
					+ ", Element with following property is not displayed->" + strProp);
		}
		} catch (Exception e) {
			failed(driver, "Exception Caught As" + e.getMessage());
		}
	}

	/**
	 * Method to Zoom in and Zoom Out(WebElement link)
	 * 
	 * @param : WebElement
	 * @param : Element Name
	 */
	protected void ZoomToPecentage(String zoomPercentage) {
		try {
			((JavascriptExecutor) driver).executeScript("document.body.style.zoom='" + zoomPercentage + "';");
			passed("Zoom In Page To" + zoomPercentage);
		} catch (RuntimeException ex) {
			failed(driver, "Failed To Zoom ,Exception is->" + ex.getMessage());
		}
	}

	/***
	 * Method to enter text in a textbox
	 * 
	 * @param : WebElement - Textbox
	 * @param : Text to be entered
	 * @return :
	 ***/
	public boolean enterText(WebElement we, String elemName, String text) { 
		boolean blnFlag;
		blnFlag = false;
		try {
			waitForElement(we);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", we);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value = '';", we);
			we.clear();
			we.sendKeys(text);
			passed("Entered value - " + text + " in the text field- " + elemName);
			blnFlag = true;
		} catch (Exception ex) {
			throw ex;
			// failed(driver, "Exception Caught As" + ex.getMessage());
		}
		return blnFlag;
	}

	/***
	 * Method to clear text in a textbox
	 * 
	 * @param : Element Name
	 * @return :
	 ***/
	public boolean clearText(WebElement we) {
		try {
			waitForIsClickable(we);
			if (isElementPresent(we)) {
				we.clear();
				return true;
			} else {
				failed(driver, "Element is not displayed, Unable to Clear text->");
				return false;
			}
		} catch (RuntimeException ex) {
			failed(driver, "Unable to clear text in the text field");
			return false;
		}
	}

	/***
	 * Method to clear text in a textbox
	 * 
	 * @param : Element Name
	 * @return :
	 ***/
	public boolean clearTextThroughKeyBoard(WebElement we) {
		try {
			if (isElementPresent(we)) {
				we.sendKeys(Keys.CONTROL + "a");
				we.sendKeys(Keys.BACK_SPACE);
				return true;
			} else {
				failed(driver, "Element is not displayed, Unable to Clear text->");
				return false;
			}
		} catch (RuntimeException ex) {
			failed(driver, "Unable to clear text in the text field");
			return false;
		}
	}

	/***
	 * Method to select the checkbox
	 * 
	 * @param : cbElement
	 * @return : Modified By :
	 ***/
	public boolean selectCheckBox(WebElement cbElement) {
		waitForIsClickable(cbElement);
		if (isElementPresent(cbElement)) {
			try {
				if (!cbElement.isSelected()) {
					cbElement.click();
				}
				passed("Selected the Checkbox Successfully");
				return true;
			} catch (Exception e) {
				failed(driver, "Unable to Select the checkbox->" + e.getMessage());
				return false;
			}
		} else {
			failed(driver, "Unable to Select the checkbox(Element is not displayed)");
			return false;
		}
	}

	public void scrollToViewElement(WebElement webElement) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
			((JavascriptExecutor) driver).executeScript("scroll", webElement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void scrollTo(WebElement webElement) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * Method to UnSelect the checkbox
	 * 
	 * @param : cbElement
	 * @return : Modified By :
	 ***/
	public boolean unSelectCheckBox(WebElement cbElement) {
		waitForIsClickable(cbElement);
		if (isElementPresent(cbElement)) {
			try {
				if (cbElement.isSelected()) {
					cbElement.click();
				}
				passed("Unchecked the checkbox");
				return true;
			} catch (Exception e) {
				failed(driver, "Unable to check the checkbox->" + e.getMessage());
				return false;
			}
		} else {
			failed(driver, "Unable to UnSelect the checkbox(Element is not displayed)");
			return false;
		}
	}

	/**
	 * Method to wait for element to load in the page
	 * 
	 * @param WebElement
	 * @return true or false
	 */
	protected Boolean waitForElement(WebElement we) {
		try {
			new WebDriverWait(driver, GlobalKeys.elementLoadWaitTime)
					.until(ExpectedConditions.elementToBeClickable(we));
			return true;
		} catch (RuntimeException ex) {
			return false;
		}
	}

	protected Boolean waitForElement(By by) {
		try {
			new WebDriverWait(driver, GlobalKeys.elementLoadWaitTime)
					.until(ExpectedConditions.elementToBeClickable(by));
			return true;
		} catch (RuntimeException ex) {
			return false;
		}
	}

	/***
	 * Method to check equality of two mobile elements
	 * 
	 * @param : MobileElement1,MobileElement2
	 * @return : boolean Modified By :
	 ***/
	public boolean AreElementsEquals(MobileElement we1, MobileElement we2) {
		try {
			waitForElement(we1);
			waitForElement(we2);
			if (isElementPresent(we1) && isElementPresent(we2)) {
				if (we1.equals(we2)) {
					passed("Both the Mobile elements are equal");
					return true;
				} else {
					failed(driver, "Both the Mobile elements are not equal");
					return false;
				}
			} else {
				failed(driver, "unable to identify both the elements");
				return false;
			}
		} catch (Exception e) {
			failed(driver, "Exception Caught while waiting for the elements ->" + e.getMessage());
			return false;
		}
	}

	/***
	 * Method to click Mobile Click On AlertButton
	 * 
	 * @param : MobileElement, ElementName
	 * @return : void Modified By :
	 ***/
	public void ClickOnAlertButton(MobileElement we, String elemName) {
		try {
			if (isElementPresent(we)) {
				we.click();
				passed("Successfully clicked on Element-" + elemName);
			} else {
				failed(driver, "Unable to click on Element " + elemName + ", Element is not present");
			}
		} catch (Exception ex) {
			failed(driver, "Uanble to click on Element-" + elemName + ", Exception is->" + ex.getMessage());
		}
	}

	/***
	 * Method to Fetch AlertText
	 * 
	 * @param : MobileElement, ElementName
	 * @return : String Modified By :
	 ***/
	public String GetAlertText(MobileElement we, String elemName) {
		try {
			if (isElementPresent(we)) {
				String alertTxt = we.getText();
				passed("Successfully get the Alert text as-" + alertTxt);
				return alertTxt;
			} else {
				failed(driver, "Unable to  get the Alert text of " + elemName + ", Element is not present");
				return null;
			}
		} catch (Exception ex) {
			failed(driver, "Uanble to click on Element-" + elemName + ", Exception is->" + ex.getMessage());
			return null;
		}
	}

	/***
	 * Method to Check Element Enabled or Not
	 * 
	 * @param : MobileElement we,String ElementName
	 * @return : boolean Modified By :
	 ***/
	public boolean IsElementEnabled(MobileElement we, String ElementName) {
		try {
			if (isElementPresent(we)) {
				boolean element = we.isEnabled();
				passed(" Element  Enabled  As-->" + element);
				return element;
			} else {
				failed(driver, "Unable to find the element");
			}
		} catch (Exception e) {
			failed(driver, "Exception Caught as" + e.getMessage());
		}
		return (Boolean) null;
	}

	public boolean IsElementEnabled(WebElement we, String ElementName) {
		try {
			if (isElementPresent(we)) {
				boolean element = we.isEnabled();
				passed(" Element  Enabled  As-->" + element);
				return element;
			} else {
				failed(driver, "Unable to find the element");
			}
		} catch (Exception e) {
			failed(driver, "Exception Caught as" + e.getMessage());
		}
		return (Boolean) null;
	}

	/***
	 * Method to Check Element Selected or Not
	 * 
	 * @param : WebElement we,String ElementName
	 * @return : boolean Modified By :
	 ***/
	public boolean IsElementSelected(WebElement we, String ElementName) {
		try {
			if (isElementPresent(we)) {
				boolean element = we.isSelected();
				passed("Element selected As-->" + element);
				return element;
			}
		} catch (Exception e) {
			failed(driver, "Exception Caught as" + e.getMessage());
		}
		return (Boolean) null;
	}

	public void ValidateElementTextInPage(MobileElement ele, String text, String pageName, String eleName) {
		try {
			waitForElement(ele);
			if (ele.getText().trim().equals(text)) {
				passed("Successfully Validated " + eleName + "Element text In " + pageName + "Page.");
			} else {
				failed_Continue(driver, "Failed To validate the " + eleName + "Element text In " + pageName
						+ " Page ,Expected " + text + "But Actual is " + ele.getText());
			}
		} catch (Exception e) {
			failed_Continue(driver, "Exception caught  as" + e.getMessage());
		}
	}

	public String getCurrentDate() {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now();
			String currentDate = dtf.format(now);
			return currentDate;
		} catch (Exception e) {
			failed(driver, "Exception caught  as" + e.getMessage());
			return null;
		}
	}

	public boolean isElementWithDynamicXpath(String xpath) {
		try {
			new WebDriverWait(driver, GlobalKeys.elementLoadWaitTime)
					.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath))));
			return true;
		} catch (RuntimeException ex) {
			return false;
		}
	}

	public String getFutureDate(int Days) {
		try {
			String strDate1 = null;
			int intervalCount = Days;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, intervalCount);
			Date date1 = calendar.getTime();
			strDate1 = dateFormat.format(date1);
			return strDate1;
		} catch (Exception e) {
			failed(driver, "Exception caught  as" + e.getMessage());
			return null;
		}
	}

	public String getDateFormatToString(String date) {
		DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat targetlFormat = new SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH);
		String formattedDate = null;
		try {
			Date format = originalFormat.parse(date);
			formattedDate = targetlFormat.format(format);
		} catch (ParseException e) {
			failed(driver, "Exception caught  as" + e.getMessage());
		}
		return formattedDate;
	}

	public String getDateFormatToStringToCompare(String date) {
		DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat targetlFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
		String formattedDate = null;
		try {
			Date format = originalFormat.parse(date);
			formattedDate = targetlFormat.format(format);
		} catch (ParseException e) {
			failed(driver, "Exception caught  as" + e.getMessage());
		}
		return formattedDate;
	}

	public String getDateFormatToStringInAndroid(String date) {
		DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat targetlFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
		String formattedDate = null;
		try {
			Date format = originalFormat.parse(date);
			formattedDate = targetlFormat.format(format);
		} catch (ParseException e) {
			failed(driver, "Exception caught  as" + e.getMessage());
		}
		return formattedDate;
	}

	public String getTimeIn24HourFormat(String time) {
		Date date = null;
		SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
		SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
		try {
			date = parseFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return displayFormat.format(date);
	}

	public String removeSpecialCharacters(String value) {
		try {
			if (value.trim() == null) {
				return null;
			} else {
				return value.replaceAll("[^\\d]", "");
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public float getFloatFormatOfString(String value) {
		try {
			if (value.trim() != null) {
				String StrFloat = value.replaceAll("[^\\d.]", "");
				return Float.parseFloat(StrFloat);
			} else {
				return (Float) null;
			}
		} catch (NumberFormatException e) {
			return (Float) null;
		}
	}

	public String generateRandomString(int length) {
		String generatedString = RandomStringUtils.randomAlphabetic(length);
		return generatedString.toLowerCase();
	}

	public String generateRandomNumber(int length) {
		String generatedString = RandomStringUtils.randomNumeric(length);
		return generatedString;
	}
	/* isNumber --- for validating numeric value */

	public boolean isNumber(String s) {
		try {
			boolean isNumeric = s.matches("-?\\d+(\\.\\d+)?");
			return isNumeric;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	protected boolean isElementPresentDynamicXpath(String xpath) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			if (element.isEnabled()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	public void waitForPageLoad(WebDriver driver, int pageLoadTimeout) {
		try {
			new WebDriverWait(driver, pageLoadTimeout).until(webDriver -> ((JavascriptExecutor) webDriver)
					.executeScript("return document.readyState").equals("complete"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Double leapOvrlapDays(String daystart, String dayEnd, String leapPrdstrt, String leapPrdend)
			throws ParseException {
		Double leapoverlapdays = 0.0;
		Date dateActStrt;
		Date dateperiodstrt;
		Date dateActEnd;
		Date dateperiodend;
		SimpleDateFormat dates = new SimpleDateFormat("yyyy/MM/dd");
		dateActStrt = dates.parse(daystart);
		dateActEnd = dates.parse(dayEnd);
		dateperiodstrt = dates.parse(leapPrdstrt);
		dateperiodend = dates.parse(leapPrdend);
		if (dateActStrt.compareTo(dateperiodstrt) == 0 && dateActEnd.compareTo(dateperiodend) == 0) {
			leapoverlapdays = 366.0;
		}
		return leapoverlapdays;
	}

	public static Double calculateDaysDiff(String daystart, String dayEnd) {
		Date date1;
		Date date2;
		SimpleDateFormat dates = new SimpleDateFormat("yyyy/MM/dd");
		Double differenceDates = 0.0;
		try {
			date1 = dates.parse(daystart);
			date2 = dates.parse(dayEnd);
			Double difference = (double) Math.abs(date1.getTime() - date2.getTime());
			differenceDates = (difference / (24 * 60 * 60 * 1000)) + 1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return differenceDates;
	}
	
	
	//naveen 
	
	public void waitForElementForCalculators(WebElement we,String ELEName) {
		try {
			if(waitForElement(we) == false) {
				if(waitForElement(we) == false) {
					if(waitForElement(we) == false) {
						if(waitForElement(we) == false) {
							System.out.println("Dispaly STATUS Name in RHP : " + waitForElement(we));
						}
					}
				}
			}else {
				System.out.println("Dispaly visibility For "+ELEName+" : " + waitForElement(we));
				sleep(1000);
			}
		} catch (Exception e) {
			failed(driver, "Exception caught  as" + e.getMessage());
		}
	}
	
    
	public static GHGCalculatorsPage navigateToGHGCalculatorPage(Data data) {
		return new GHGCalculatorsPage(driver, data);
	}
	
	public void highlightTheElement(WebElement we) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'",we);
	}
	
}
