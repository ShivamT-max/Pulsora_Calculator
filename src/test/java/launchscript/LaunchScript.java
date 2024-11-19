package launchscript;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.annotations.Test;

import utilities.Constants;
import utilities.GlobalVariables;
import utilities.Util;

public class LaunchScript extends Util {
	
	@Test
	public void LaunchScript() {
		try {
			startup();
			TestNG testng = new TestNG();
			List<String> suites = new ArrayList<String>();
			if (GlobalVariables.testsType.equalsIgnoreCase(Constants.UITests)) {
				suites.add("./src/test/resources/Config/testng.xml");
			} else if (GlobalVariables.testsType.equalsIgnoreCase(Constants.APITests)) {
				suites.add("./src/test/resources/Config/testngAPI.xml");  	
			} 
			testng.setTestSuites(suites);
			testng.run();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("###################################");
			System.out.println("Script Execution Complete");
			System.out.println("####################################");
		}
	}
}

