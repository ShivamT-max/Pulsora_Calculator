package pages;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import base.TestBase;
import utilities.Data;

public class DataDestinationPage extends TestBase {
	Actions act = new Actions(driver);

	protected DataDestinationPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	@FindBy(xpath = "//article//*[contains(text(),'Data Destination')]")
	private WebElement lblDataDestination;
	@FindBy(xpath = "//*[@aria-label='DD GRI 2610']")
	private WebElement mouseHover;
	@FindBy(xpath = "//*[contains(text(),'PASI REPORT')]")
	private WebElement mouseHoverPasiTile;
	@FindBy(xpath = "//*[@aria-label='Execute']")
	private WebElement btnPublishDataDestination;
	@FindBy(xpath = "//span[contains(text(),'PASI_TPG')]")
	private WebElement radioSelectPublishProfile;
	@FindBy(xpath = "//button[contains(text(),'Publish')]")
	private WebElement btnPublish;
	@FindBy(xpath = "//button[text()='Done']")
	private WebElement btnDone;
	@FindBy(xpath = "//span[contains(text(),'westly-datarequest-pdf')]")
	private WebElement radioSelectPublishProfileGRI;
	@FindBy(xpath = "//button[contains(text(),'Continue')]")
	private WebElement bttnContinue;
	@FindBy(xpath = "//span[text()='TPG.pdf']")
	private WebElement bttnRdioTpg;
	@FindBy(xpath = "//div[contains(text(),'Period')]//parent::div//label")
	private WebElement drpPeriod;
	@FindBy(xpath = "//article[@aria-valuetext='Expand Icon']/article")
	private WebElement expandTopicArrow;
	@FindBy(xpath = "//ul[@role='listbox']//li")
	private List<WebElement> listPeriod;
	@FindBy(xpath = "//*[@ref='fullWidthContainer']//*[@row-id='row-group-0']")
	private WebElement expandContent;

	public void ValidationsForPASI() {
		try {
			act.moveToElement(mouseHoverPasiTile).build().perform();
			clickOn(btnPublishDataDestination, "data destination tile");
			clickOn(radioSelectPublishProfile, "select publish profile radio button");
			clickOn(btnPublish, "publish button");
			clickOn(bttnContinue, "Continue button");
			waitForElement(btnDone);
			clickOn(btnDone, "clicked on done button");
			File listOfiles[] = folder.listFiles();
			if (listOfiles.length != 0) {
				passed("File get downloaded");
				for (File fil : listOfiles) {
					if (fil.length() != 0) {
						passed("File does contains data");
					} else {
						failed(driver, "File is empty");
					}
				}
			} else {
				failAssert("File not downloaded");
			}
		} catch (Exception r) {
			failed(driver, "Exception caught" + r.getMessage());
		}
	}

	public void ValidationsForPASIForDiffEnvr() {
		try {
			String hoverXpath = "//*[contains(text(),'" + data.get("Hover") + "')]";
			WebElement hoverDyna = driver.findElement(By.xpath(hoverXpath));
			act.moveToElement(hoverDyna).build().perform();
			clickOn(btnPublishDataDestination, "data destination tile");
			clickOn(bttnRdioTpg, "select publish profile radio button");
			clickOn(btnPublish, "publish button");
			clickOn(bttnContinue, "Continue button");
			waitForElement(btnDone);
			clickOn(btnDone, "clicked on done button");
			File listOfiles[] = folder.listFiles();
			if (listOfiles.length != 0) {
				passed("File get downloaded");
				for (File fil : listOfiles) {
					if (fil.length() != 0) {
						passed("File does contains data");
					} else {
						failed(driver, "File is empty");
					}
				}
			} else {
				failAssert("File not downloaded");
			}
		} catch (Exception r) {
			failed(driver, "Exception caught" + r.getMessage());
		}
	}

	public void ValidationsForGRI() {
		try {
			act.moveToElement(mouseHover).build().perform();
			clickOn(btnPublishDataDestination, "data destination tile clicked");
			clickOn(radioSelectPublishProfileGRI, "select publish profile radio button");
			clickOn(btnPublish, "publish button");
			clickOn(bttnContinue, "File downloaded successfully");
			clickOn(btnDone, "clicked on done button");
			File listOfiles[] = folder.listFiles();
			if (listOfiles.length != 0) {
				passed("File get downloaded");
				for (File fil : listOfiles) {
					if (fil.length() != 0) {
						passed("File does contains data");
					} else {
						failAssert("File is empty");
					}
				}
			} else {
				failAssert("File not downloaded");
			}
		} catch (Exception r) {
			failed(driver, "Exception caught" + r.getMessage());
		}
	}

	public void ValidationsForGRIforDifferentEnvr() {
		try {
			String mouseHoverDynam = "//*[@aria-label='" + data.get("TileName") + "']";
			WebElement fr = driver.findElement(By.xpath(mouseHoverDynam));
			sleep(2000);
			act.moveToElement(fr).build().perform();
			clickOn(btnPublishDataDestination, "data destination tile clicked");
			List<WebElement> reportTypes = driver.findElements(
					By.xpath("//div[contains(text(),'Select Publish')]//parent::div/div/label//div//span"));
			if (data.get("ReportName").equals("uat.Westly")) {
				act.click(reportTypes.get(0)).build().perform();
			} else if (data.get("ReportName").equals("uat.ESG")) {
				act.click(reportTypes.get(2)).build().perform();
			} else if (data.get("ReportName").equals("prod.pdf")) {
				act.click(reportTypes.get(0)).build().perform();
			} else if (data.get("ReportName").equals("prod.csv")) {
				act.click(reportTypes.get(1)).build().perform();
			} else if (data.get("ReportName").equals("demo.pdf")) {
				act.click(reportTypes.get(0)).build().perform();
			} else if (data.get("ReportName").equals("demo.csv")) {
				act.click(reportTypes.get(1)).build().perform();
			}
			clickOn(btnPublish, "publish button");
			clickOn(bttnContinue, "File downloaded successfully");
			clickOn(btnDone, "clicked on done button");
			File listOfiles[] = folder.listFiles();
			if (listOfiles.length != 0) {
				passed("File get downloaded");
				for (File fil : listOfiles) {
					if (fil.length() != 0) {
						passed("File does contains data");
					} else {
						failAssert("File is empty");
					}
				}
			} else {
				failAssert("File not downloaded");
			}
		} catch (Exception r) {
			failed(driver, "Exception caught" + r.getMessage());
		}
	}

	public void VerifyDataDestinationExpandAndCustomPeriod() {
		try {
			String expandArrow = "//*[@aria-label='" + data.get("DDTile") + "']";
			clickOnElementWithDynamicXpath(expandArrow, "DDTile");
			jsClick(expandTopicArrow, "clicked on expand arrow topic");
			String expanding = expandContent.getAttribute("aria-expanded");
			if (expanding.equals("true")) {
				passed("topic name expanded");
			} else {
				failed(driver, "topic name is not expanded");
			}
			sleep(3000);
			act.moveToElement(drpPeriod).click().build().perform();
			for (int i = 0; i < listPeriod.size(); i++) {
				if (listPeriod.get(i).getText().equals(data.get("SelectPeriod"))) {
					WebElement customPeriod = listPeriod.get(i);
					waitForElement(customPeriod);
					clickOn(customPeriod, "custom period selected");
					sleep(3000);
					if (drpPeriod.getText().equals(data.get("SelectPeriod"))) {
						passed("custom period is get clicked");
					} else {
						failed(driver, "custom period is not selected");
					}
					sleep(2000);
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught" + e.getMessage());
		}
	}

	public void VerifyDataDestinationExpandAndCustomPeriodForDiffEnvr() {
		String expandArrow = "//*[@aria-label='" + data.get("DDTile") + "']";
		clickOnElementWithDynamicXpath(expandArrow, "DDTile");
		jsClick(expandTopicArrow, "clicked on expand arrow topic");
		String expanding = expandContent.getAttribute("aria-expanded");
		if (expanding.equals("true")) {
			passed("topic name expanded");
		} else {
			failed(driver, "topic name is not expanded");
		}
		sleep(3000);
		act.moveToElement(drpPeriod).click().build().perform();
		for (int i = 0; i < listPeriod.size(); i++) {
			if (listPeriod.get(i).getText().equals(data.get("SelectPeriod"))) {
				WebElement customPeriod = listPeriod.get(i);
				waitForElement(customPeriod);
				clickOn(customPeriod, "custom period selected");
				sleep(3000);
				if (drpPeriod.getText().equals(data.get("SelectPeriod"))) {
					passed("custom period is get clicked");
				} else {
					failed(driver, "custom period is not selected");
				}
				sleep(2000);
			}
		}
	}

	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(lblDataDestination);
			if (isElementPresent(lblDataDestination)) {
				passed("User Successfully Navigated To Data Destination Page");
			} else {
				failed(driver, "Failed To Navigate To Data Destination Page");
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
}
