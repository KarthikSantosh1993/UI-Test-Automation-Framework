package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;

public class ExcelReaderUtility {
	
	public static Iterator<User> readExcelFile(String fileName) {
		File xlsxFile = new File(System.getProperty("user.dir") + "/testData/"+fileName);
		XSSFWorkbook workbook = null;
		XSSFSheet sheet;
		List<User> userList = null;
		User user;
		Row row;
		Cell emailAddressCell;
		Cell passwordCell;
		try {
			workbook = new XSSFWorkbook(xlsxFile);
			sheet = workbook.getSheet("logindata");
			Iterator<Row> rowiterator = sheet.iterator();
			userList = new ArrayList<User>();
			rowiterator.next(); // skip the first row
			while (rowiterator.hasNext()) {
				row = rowiterator.next();
				emailAddressCell = row.getCell(0);
				passwordCell = row.getCell(1);
				user = new User(emailAddressCell.toString(), passwordCell.toString());
				userList.add(user);
			}
			workbook.close();
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		return userList.iterator();
	}
}
