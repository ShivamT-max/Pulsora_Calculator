package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.TestBase;
import utilities.Data;

public class ConfigureCatalogPage extends TestBase {
	protected ConfigureCatalogPage(WebDriver driver2, Data data) {
		super(driver2, data);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//div/*[text()='Configure Catalog']")
	private WebElement lblConfigureCatalog;
	@FindBy(xpath = "//span/li/*[text()='Custom']")
	private WebElement btnCustomCatalog;
	@FindBy(xpath = "//button[text()='Add Topic']")
	private WebElement btnAddTopic;
	@FindBy(xpath = "//button[text()='Add Metric']]")
	private WebElement btnAddMetric;
	@FindBy(xpath = "//input[@id='topicName']")
	private WebElement txtTopicName;
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement btnSave;
	@FindBy(xpath = "//button[text()='Ok']")
	private WebElement btnOK;
	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement btncancel;
	@FindBy(xpath = "//div[text()='Esg Marker']")
	private WebElement weESGMarker;

	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(lblConfigureCatalog);
			if (isElementPresent(lblConfigureCatalog)) {
				passed("User Successfully Navigated To Configure CatalogPage");
			} else {
				failed(driver, "Failed To Navigate To Configure Catalog Page");
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
}
