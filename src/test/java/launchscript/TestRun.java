package launchscript;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestRun {
	public static void main(String[] args) throws AWTException {
		
		
//		String s1="Pinky";
//		System.out.println(s1.length());
//		char[] abc = s1.toCharArray();
//		for(int i=0;i<=s1.length();i++) {
//		    System.out.print(abc[s1.length()-i]);
//		}
		WebDriverManager.chromedriver().setup();		
		WebDriver driver = new ChromeDriver();
		driver.get("https://qa.pulsesg.com/login/en_US");
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://stackoverflow.com/questions/17547473/how-to-open-a-new-tab-using-selenium-webdriver-in-java");
		
		
		
}
	
}



//int,Double,String