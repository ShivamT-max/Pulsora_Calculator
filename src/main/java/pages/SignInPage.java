package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.TestBase;
import utilities.Data;
import utilities.GlobalKeys;

public class SignInPage extends TestBase {
	public SignInPage(WebDriver driver2, Data data) {
		super(driver2, data);
	}

	@FindBy(xpath = "//*[contains(text(),'Sign in')]")
	private WebElement lblSignIn;
	@FindBy(xpath = "//input[@id='username']")
	private WebElement txtUserName;
	@FindBy(xpath = "//input[@id='password']")
	private WebElement txtPassword;
	@FindBy(xpath = "//button[contains(text(),'Sign In')]")
	private WebElement btnSignIn;

	public HomePage SignInToPulsEsGApp() {
		try {
			enterText(txtUserName, "UserName", data.get("UserName"));
			enterText(txtPassword, "Password", data.get("Password"));
			clickOn(btnSignIn, "SignInButton");
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
		return new HomePage(driver, data);
	}

	public HomePage signInToPulsEsGApp(String userName, String password) {
		try {
			enterText(txtUserName, "UserName", userName);
			enterText(txtPassword, "Password", password);
			clickOn(btnSignIn, "SignInButton");
			verifySignIn();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
		return new HomePage(driver, data);
	}

	@Override
	protected void VerifyNavigationToValidPage() {
		try {
//			waitForElement(lblSignIn);
//			if (isElementPresent(lblSignIn)) {
//				passed("User Successfully Navigated To SignIn Page");
//			} else {
//				failed(driver, "User Failed To navigate To  SignIn Page");
//			}
//			takeScreenshot(driver);
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//span[contains(text(),'Hi')]")
	private WebElement weHi;

	protected void verifySignIn() {
		try {
			waitForElement(weHi);
			if (isElementPresent(weHi)) {
				passed("User Successfully Navigated To Home Page");
			} else {
				failed(driver, "User Failed To navigate To  Home Page");
			}
			takeScreenshot(driver);
			// closeWelocmeWindows();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	@FindBy(xpath = "//h1[contains(text(),'welcome to the pulsESG family')]")
	private WebElement weWelComeMessage;
	@FindBy(xpath = "//span[@aria-label='Close']")
	private WebElement weCloseWelComePopup;

	/*
	 * protected void verifySignIn(String userName, boolean welcomePopup) { try {
	 * waitForElement(weHi); if (isElementPresent(weHi)) { //
	 * passed("User Successfully Navigated To Home Page"); if (welcomePopup) {
	 * closeWelocmeWindows(); } } else { failed(driver,
	 * "User Failed To navigate To  Home Page"); } // takeScreenshot(driver); //
	 * takeScreenshot(driver); // // if(GlobalVariables.loggedInCout==0){ //
	 * closeWelocmeWindows(); // GlobalVariables.loggedInCout++; // } //
	 * System.out.println(); // if(!GlobalVariables.loggedIn.contains(userName)){ //
	 * closeWelocmeWindows(); // } // GlobalVariables.loggedIn.add(userName); }
	 * catch (Exception e) { failed(driver, "Exception caught " + e.getMessage()); }
	 * }
	 */
	protected void verifySignIn(String userName, boolean welcomePopup) {
		try {
			waitForElement(weHi);
			if (isElementPresent(weHi)) {
				passed("User Successfully Navigated To Home Page");
				// passed("User Successfully Navigated To Home Page");
//				if (welcomePopup) {
//					closeWelocmeWindows();
//				}
			} else {
				failed(driver, "User Failed To navigate To  Home Page");
			}
			takeScreenshot(driver);
			closeWelocmeWindows();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}

	public HomePage SignInToPulsEsGApp(String userName, String password, boolean welcomePopup) {
		try {
			enterText(txtUserName, "UserName", userName);
			enterText(txtPassword, "Password", password);
			clickOn(btnSignIn, "SignInButton");
			verifySignIn();
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
		return new HomePage(driver, data);
	}

	public void closeWelocmeWindows() {
		try {
			int size = driver.findElements(By.tagName("iframe")).size();
			if (size > 0) {
				if (isElementPresent(weWelComeMessage)) {
					driver.switchTo().frame("intercom-tour-frame");
					clickOn(weCloseWelComePopup, "CloseWelComePopup");
				} else {
					System.out.println("No Welcome Message Came");
				}
			}
		} catch (Exception e) {
			failed(driver, "Exception caught " + e.getMessage());
		}
	}
}
