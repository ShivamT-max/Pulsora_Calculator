package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBaseConnection extends Common {

    public HashMap<String, String> getDBData(String query, String dbURL, String dbUserName, String dbPassword) {
	Connection c = null;
	Statement stmt = null;
	HashMap<String, String> hmDB = new HashMap<>();
	try {
	    Class.forName(Constants.dbDriverName);
	    c = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
	   // passed("Opened database successfully");

	    stmt = c.createStatement();

	    ResultSet rs = stmt.executeQuery(query);
	    ResultSetMetaData rsMetaData = rs.getMetaData();
	    int count = rsMetaData.getColumnCount();
	    while (rs.next()) {
		for (int i = 1; i <= count; i++) {
		    hmDB.put(rsMetaData.getColumnName(i), rs.getString(i));
		}
	    }
	    stmt.close();
	    c.close();

	} catch (Exception e) {
	    failed(driver, "Exception caught , Message is : " + e.getMessage());
	}
	return hmDB;
    }

    public List<HashMap> getDBMultipleData(String query, String dbURL, String dbUserName, String dbPassword) {
	Connection c = null;
	Statement stmt = null;
	List<HashMap> DBList = new ArrayList<>();
	HashMap<String, String> hmDB = new HashMap<>();
	try {
	    Class.forName(Constants.dbDriverName);
	    c = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
	    passed("Opened database successfully");
	    stmt = c.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    ResultSetMetaData rsMetaData = rs.getMetaData();
	    int count = rsMetaData.getColumnCount();
	    List<String> colValues = new ArrayList<>();

	    while (rs.next()) {
		for (int i = 1; i <= count; i++) {
		    hmDB.put(rsMetaData.getColumnName(i), rs.getString(i));
		}
		DBList.add(hmDB);
		hmDB = new HashMap<>();
	    }
	    stmt.close();
	    c.close();
	} catch (Exception e) {
	    failed(driver, "Exception caught , Message is : " + e.getMessage());
	}
	return DBList;
    }

    // pulsesg-qa-cb-0008.cbclient.qa.pulsesg.com
}
