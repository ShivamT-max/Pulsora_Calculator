package pages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.TestBase;
import utilities.CSVDataReader;
import utilities.Data;

public class EventsPage extends TestBase {
	protected EventsPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	@FindBy(xpath = "//*[text()='Events']")
	private WebElement lblEventsPage;

	public void validateEventDetailsInEventsPage(String eventName, String eventType, String ObjectType, String user,
			String eventStatus) {
		try {
			driver.navigate().refresh();
			waitForElement(lblEventsPage);
			String xpathEventName = "//div[@col-id='eventName']//self::div[contains(text(),'" + eventName + "')]";
			WaitForElementWithDynamicXpath(xpathEventName);
			if (isElementPresentDynamicXpath(xpathEventName)) {
				passed("Successfully validate Event Name in Event Page As"
						+ driver.findElement(By.xpath(xpathEventName)).getText());
			} else {
				failed(driver, "Failed To validate Event Name in Event Page ");
			}
			String xpathEventType = "//div[@col-id='eventName']//self::div[contains(text(),'" + eventName
					+ "')]//following-sibling::div[@col-id='operationType']//self::div[contains(text(),'" + eventType
					+ "')]";
			WaitForElementWithDynamicXpath(xpathEventType);
			if (isElementPresentDynamicXpath(xpathEventType)) {
				passed("Successfully validate Event Type in Event Page As"
						+ driver.findElement(By.xpath(xpathEventType)).getText());
			} else {
				failed(driver, "Failed To validate Event Type in Event Page ");
			}
			String xpathObjectType = "//div[@col-id='eventName']//self::div[contains(text(),'" + eventName
					+ "')]//following-sibling::div[@col-id='objectType']//self::div[contains(text(),'" + ObjectType
					+ "')]";
			WaitForElementWithDynamicXpath(xpathObjectType);
			if (isElementPresentDynamicXpath(xpathObjectType)) {
				passed("Successfully validate Object Type in Event Page As"
						+ driver.findElement(By.xpath(xpathEventType)).getText());
			} else {
				failed(driver, "Failed To validate Object Type in Event Page ");
			}
			String xpathCreatedBy = "//div[@col-id='eventName']//self::div[contains(text(),'" + eventName
					+ "')]//following-sibling::div[@col-id='createdBy']//self::div[contains(text(),'" + user + "')]";
			WaitForElementWithDynamicXpath(xpathCreatedBy);
			if (isElementPresentDynamicXpath(xpathCreatedBy)) {
				passed("Successfully validate Event Created User in Event Page As"
						+ driver.findElement(By.xpath(xpathCreatedBy)).getText());
			} else {
				failed(driver, "Failed To validate Event Created Userin Event Page ");
			}
			String xpathEventStatus = "//div[@col-id='eventName']//self::div[contains(text(),'" + eventName
					+ "')]//following-sibling::div[@col-id='status']//div[contains(text(),'" + eventStatus + "')]";
			WaitForElementWithDynamicXpath(xpathEventStatus);
			if (isElementPresentDynamicXpath(xpathEventStatus)) {
				passed("Successfully validate Event Status in Event Page As"
						+ driver.findElement(By.xpath(xpathEventStatus)).getText());
			} else {
				failed(driver, "Failed To validate Event Status in Event Page ");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FindBy(xpath = "//*[@data-testid='SearchIcon']//following-sibling::input[@placeholder='Search']")
	private WebElement txtSearchEvents;
	@FindBy(xpath = "//button[text()='Download']")
	private WebElement btndownload;

	public void validateDownloadAndSearchEventsInEventsPage() {
		try {
			waitForElement(txtSearchEvents);
			scrollTo(txtSearchEvents);
			enterText(txtSearchEvents, "search Events TextBox", "Email");
			txtSearchEvents.sendKeys(Keys.ENTER);// Email
			sleep(3000);
			List<WebElement> weEventNames = driver
					.findElements(By.xpath("//div[@ref='eViewport']//div[@col-id='eventName']"));
			boolean searchFlag = true;
			for (WebElement we : weEventNames) {
				if (we.getText().contains("Email")) {
					continue;
				} else {
					searchFlag = false;
					break;
				}
			}
			if (searchFlag) {
				passed("Successfully Validated Search Functionality in Events Page");
			} else {
				failed(driver, "Failed To validate Search Functionality in Events Page");
			}
			sleep(2000);
			txtSearchEvents.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			txtSearchEvents.sendKeys(Keys.BACK_SPACE);
			txtSearchEvents.sendKeys(Keys.ENTER);
			List<WebElement> weAllEventNames = driver
					.findElements(By.xpath("//div[@ref='eViewport']//div[@col-id='eventName']"));
			List<String> uiEventNames = new ArrayList<>();
			for (WebElement eventName : weAllEventNames) {
				uiEventNames.add(eventName.getText());
			}
			System.out.println(uiEventNames.size());
			jsClick(btndownload, "Download Button");
			ArrayList<String> eventNames = CSVDataReader.getMultipleColumnDataFromCSV(System.getProperty("user.dir")
					+ "\\src\\test\\resources\\DataRequestFiles\\EventsFile\\pulsesg_events.csv", "Event Name");
			System.out.println(eventNames.size());
			for (int i = 1; i < uiEventNames.size(); i++) {
				if (eventNames.get(i).replaceAll("\\s", "").equals(uiEventNames.get(i - 1).replaceAll("\\s", ""))) {
					passed("Successfully validated Eventname in Downloaded Report As" + eventNames.get(i));
				} else {
					failed(driver, "Failed To validate Event name in Downloaded Report Expected As" + eventNames.get(i)
							+ "But Actual is" + uiEventNames.get(i - 1));
				}
			}
			sleep(3000);
			File folder = new File(
					System.getProperty("user.dir") + "\\src\\test\\resources\\DataRequestFiles\\EventsFile");
			FileUtils.cleanDirectory(folder);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(lblEventsPage);
			if (isElementPresent(lblEventsPage)) {
				passed("User Successfully Navigated To Events Page");
			} else {
				failed(driver, "Failed To Navigate To Events Page");
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
}
