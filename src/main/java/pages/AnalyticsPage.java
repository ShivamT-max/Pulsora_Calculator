package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.TestBase;
import utilities.Data;

public class AnalyticsPage extends TestBase {
	protected AnalyticsPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	@FindBy(xpath = "//div/*[contains(text(),'Analytics')]")
	private WebElement lblAnalytics;

	public void validateAnalyticsDashBoardNavigationInPulsESG() {
		try {
			List<WebElement> elements = driver.findElements(By.xpath(
					"//*[text()='Analytics']//ancestor::article//following-sibling::div//article//div[@class='description']"));
			for (int i = 0; i < elements.size(); i++) {
				String eleName = elements.get(i).getText();
				if (i == 0) {
					eleName = elements.get(i).getText().toLowerCase();
				}
				clickOn(elements.get(i), eleName);
				sleep(8000);
				String xpath = "//li[contains(text(),'" + eleName + "')]";
				System.out.println(xpath);
				WebElement weName = driver.findElement(By.xpath(xpath));
				if (isElementPresent(weName)) {
					passed("Successfully validated " + (i + 1) + " Dashboard element in Analytics Page ");
					takeScreenshot(driver);
				} else {
					failed(driver, "Failed To validate " + (i + 1) + " Dashboard element  in Analytics Page");
				}
				driver.navigate().back();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateAnalyticsDashBoardNavigationInPulsESGForDiffEnvi() {
		try {
			List<WebElement> elements = driver.findElements(By.xpath(
					"//*[text()='Analytics']//ancestor::article//following-sibling::div//article//div[@class='description']"));
			for (int i = 0; i < elements.size(); i++) {
				String eleName = elements.get(i).getText();
				clickOn(elements.get(i), eleName);
				sleep(8000);
				String xpath = "//li[contains(text(),'" + eleName + "')]";
				System.out.println(xpath);
				WebElement weName = driver.findElement(By.xpath(xpath));
				if (isElementPresent(weName)) {
					passed("Successfully validated " + (i + 1) + " Dashboard element in Analytics Page ");
					takeScreenshot(driver);
				} else {
					failed(driver, "Failed To validate " + (i + 1) + " Dashboard element  in Analytics Page");
				}
				driver.navigate().back();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void VerifyNavigationToValidPage() {
		try {
			waitForElement(lblAnalytics);
			if (isElementPresent(lblAnalytics)) {
				passed("User Successfully Navigated To Anylytics Page");
			} else {
				failed(driver, "Failed To Navigate To Anylytics Page");
			}
			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
}
