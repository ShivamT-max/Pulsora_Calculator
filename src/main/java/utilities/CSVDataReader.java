package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

public class CSVDataReader {
	public static String getDataFromCSV(String csvFilePath, String columnName, int row) {
		String data = null;
		int posColumn = 0;
		try {
			CSVReader cr = new CSVReader(new FileReader(new File(csvFilePath)));
			List<String[]> alldata = cr.readAll();
			String[] arrStrvalue = alldata.get(row);
			for (int j = 0; j < arrStrvalue.length; j++) {
				if (columnName.trim().equals(arrStrvalue[j].trim())) {
					posColumn = j;
					break;
				}
			}
			data = alldata.get(row)[posColumn];
			cr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static ArrayList getMultipleColumnDataFromCSV(String csvFilePath, String columnName) {
		int posColumn = 0;
		ArrayList csvColValues = new ArrayList<>();
		try {
			CSVReader cr = new CSVReader(new FileReader(new File(csvFilePath)));
			List<String[]> alldata = cr.readAll();
			String[] arrStrvalue = alldata.get(0);
			for (int j = 0; j < arrStrvalue.length; j++) {
				if (columnName.trim().equals(arrStrvalue[j].trim())) {
					posColumn = j;
					break;
				}
			}
			for (int i = 0; i < alldata.size(); i++) {
				String arrStrvaluePos = alldata.get(i)[posColumn];
				csvColValues.add(arrStrvaluePos);
			}
			cr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			e.printStackTrace();
		}
		return csvColValues;
	}

	public static void writeDataIntoCSV(String csvFilePath, String DatacolumnName, String rowName, int rowColNum,
			String updateData) {
		try {
			int posColumn = 0;
			CSVReader cr = null;
			try {
				cr = new CSVReader(new FileReader(new File(csvFilePath)));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			List<String[]> alldata = null;
			alldata = cr.readAll();
			String[] arrStrvalue = alldata.get(0);
			for (int j = 0; j < arrStrvalue.length; j++) {
				if (DatacolumnName.trim().equals(arrStrvalue[j].trim())) {
					posColumn = j;
					break;
				}
			}
			int posRow = 0;
			for (int i = 0; i < alldata.size(); i++) {
				if ((alldata.get(i))[rowColNum].equals(rowName)) {
					posRow = i;
					break;
				}
			}
			String[] lstValueEnterStrings = alldata.get(posRow);
			lstValueEnterStrings[posColumn] = updateData;
			CSVWriter cW = null;
			try {
				cW = new CSVWriter(new FileWriter(new File(csvFilePath)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			alldata.add(posRow, lstValueEnterStrings);
			cW.writeAll(alldata);
			cW.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
