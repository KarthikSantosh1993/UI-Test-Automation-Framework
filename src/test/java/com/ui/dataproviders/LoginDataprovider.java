package com.ui.dataproviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtilty;
import com.utility.ExcelReaderUtility;

public class LoginDataprovider {
	@DataProvider(name = "LoginDataProvider")
	public Iterator<Object[]> loginDataprovider() {
		Gson gson = new Gson();
		File testDataFile = new File(System.getProperty("user.dir") + "/testData/logindata.json");
		FileReader fileReader=null;
		try {
			fileReader = new FileReader(testDataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		TestData data = gson.fromJson(fileReader, TestData.class);

		List<Object[]> dataToReturn = new ArrayList<Object[]>();

		for (User user : data.getData()) {
			dataToReturn.add(new Object[] { user });
		}

		return dataToReturn.iterator();
	}

	@DataProvider(name="LoginTestCSVDataProvider")
	public Iterator<User> loginCSVDataProvider() {
		return CSVReaderUtilty.readCSVFile("logindata.csv");
	}
	
	@DataProvider(name="LoginTestExcelDataProvider")
	public Iterator<User> loginExcelDataProvider() {
		return ExcelReaderUtility.readExcelFile("logindata.xlsx");
	}
}
