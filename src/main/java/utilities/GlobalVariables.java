package utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GlobalVariables {
	// Catalog
	public static String catalogName;
	public static String catalogType;
	public static String testsType = GlobalKeys.configData.get("TestsType");
	public static String metricValue;
	// Data Request
	public static String dataRequestName = "AK_DR_GHG";
	public static boolean approvalFlag = false;
	// GHG Calculators
	public static Double initialTotaltCo2e;
	public static Double tco2e;
	public static Double updatedTotaltCo2e;
	public static List<Double> listOverlaptco2 = new ArrayList<Double>();
	public static String goalName;
	public static String CustomEmissionFactorName;
	// Data Source
	public static String dataSourceName;
	public static String inputMapName;
	// DR
	public static String userName;
	public static String password;
	public static String orgName;
	public static String textMetricTableValue;
	public static String topicName;
	public static String metricName;
	public static String commentName;
	public static HashMap<String, String> catalogDetails = new HashMap<>();
	public static HashMap<String, String> commentsDetails = new HashMap<>();
	public static List<String> alLReplyComments = new ArrayList<>();
	public static String inputNumberValue;
	public static String inputValue;
	public static String inputRevised;
	public static String dueDate;
	// Data Source
	public static Double ghgCalcValueDataSource;
	public static String tableValues;
	// calcboolean GWPValueAR4;
	public static String calculatedEFValue;
	public static String calculatedEFValueBioFuel;
	public static String coversionValue;
	public static String nameOfCEF;
	public static String exceptedtco2e;
	public static String valuebefore;

}
