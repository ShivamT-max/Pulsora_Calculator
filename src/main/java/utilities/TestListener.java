package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
	public ExtentReport extent;

	@Override
	public void onTestStart(ITestResult result) {
		extent.testScenarioStart(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
		try {
			extent = new ExtentReport();
			extent.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		try {
			extent.testScenarioEnd(context.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			extent.logFailException(result.getThrowable());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		try {
			System.out.println(result.getSkipCausedBy());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
