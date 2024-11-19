package utilities;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	static ExtentReports extent;
	static ExtentTest parent;
	static ExtentTest child;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void start() {
		try {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(GlobalKeys.outputDirectory + "/Results.html");
			ExtentSparkReporter sparkFail = new ExtentSparkReporter(GlobalKeys.outputDirectory + "/FailedResults.html")
					.filter().statusFilter().as(new Status[] { Status.FAIL }).apply();
			spark.config().setReportName("pulsESG Automation Report");
			spark.config().setDocumentTitle("pulsESG Automation Report");
			extent.attachReporter(spark, sparkFail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized ExtentTest getTest() throws InterruptedException {
		//Thread.sleep(200);
		return extentTest.get();
	}

	public void datasetStart(String testDataSet) {
		try {
			child = getTest().createNode(testDataSet, testDataSet);
			extentTest.set(child);
			log("info", "Test Data source is " + GlobalKeys.configData.get("TestDataSource"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void datasetEnd() {
		try {
			extentTest.set(parent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testScenarioStart(ITestResult result) {
		try {
			parent = extent.createTest(result.getMethod().getMethodName());
			extentTest.set(parent);
//			String strDeviceName;
//			if (GlobalKeys.configData.get("PlatFormName").equalsIgnoreCase("Android")) {
//			 strDeviceName = GlobalKeys.configData.get("PlatFormName") + "_"  +GlobalKeys.configData.get("Android_DeviceName")+"_OS"+"_"+GlobalKeys.configData.get("Android_DevicePlatformVersion") ;
//		
//			}
//			else {
//				strDeviceName=GlobalKeys.configData.get("PlatFormName")+"_"+GlobalKeys.configData.get("iOS_DeviceName")+"_OS"+"_"+GlobalKeys.configData.get("iOS_DevicePlatformVersion");
//			}
//			parent.assignDevice(strDeviceName);
//			System.out.println("Report Started For " + result.getMethod().getMethodName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void log(String status, String msg) {
		try {
			System.out.println(GlobalKeys.outputDirectory + " : " + msg);
			switch (status) {
			case "pass":
				getTest().pass(msg);
				break;
			case "info":
				getTest().info(msg);
				break;
			case "fail":
				getTest().fail(msg);
				break;
			case "warn":
				getTest().warning(msg);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logJson(String json) {
		try {
			getTest().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logPerf(String json) {
		try {
			getTest().warning(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logXml(String xml) {
		try {
			getTest().info(MarkupHelper.createCodeBlock(xml));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logFailScreenshot(String errMsg, String img) {
		try {
			getTest().fail(errMsg, MediaEntityBuilder.createScreenCaptureFromBase64String(img).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logFailException(Throwable throwable) {
		try {
			getTest().fail(throwable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logScreenshot(String img) {
		try {
			getTest().info(MediaEntityBuilder.createScreenCaptureFromBase64String(img).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logScreenshot1(String name) {
		try {
			getTest().info(MediaEntityBuilder.createScreenCaptureFromPath("./Screenshots/" + name + ".png").build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logScreenshotWarn(String errMsg, String img) {
		try {
			getTest().info(errMsg, MediaEntityBuilder.createScreenCaptureFromBase64String(img).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void testScenarioEnd(String name) {
		try {
			extent.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
