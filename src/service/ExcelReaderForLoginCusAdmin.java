package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelReaderForLoginCusAdmin {
	public boolean validateCredentials(String userName, String pass,int prof) throws IOException 
	{
		String checkUserName = "" , checkPassword = "";
		File file = null;
		switch(prof) 
		{
		case 0:
			file = new File("C:\\Users\\mlaks\\OneDrive\\Desktop\\FSJ\\ProGrad_S-52\\S-2-2_Week-4\\BackEnd Data\\AdminData.xls");
			break;
		case 1:
			file = new File("C:\\Users\\mlaks\\OneDrive\\Desktop\\FSJ\\ProGrad_S-52\\S-2-2_Week-4\\BackEnd Data\\CustomerData.xls");
			break;
		}
		
		FileInputStream fis = new FileInputStream(file);
		
		@SuppressWarnings("resource")
		Workbook workbook = new HSSFWorkbook(fis);
		HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
		
		Iterator<Row> itr = sheet.iterator();
		while(itr.hasNext()) 
		{
			//Extracting the values & performing validation
			Row row = itr.next();
			checkUserName = row.getCell(2).getStringCellValue();
			checkPassword = row.getCell(4).getStringCellValue();
			if(userName.equals(checkUserName) && pass.equals(checkPassword)) 
			{
				return true;
			}
		}
		workbook.close();
		return false;
	}
	public String findName(String userName) throws Exception {
		File file = new File("C:\\Users\\mlaks\\OneDrive\\Desktop\\FSJ\\ProGrad_S-52\\S-2-2_Week-4\\BackEnd Data\\CustomerData.xls");
		String name= "",checkUserName = "";
		FileInputStream fis =new FileInputStream(file);
		@SuppressWarnings("resource")
		Workbook workbook = new HSSFWorkbook(fis);
		HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);

		Iterator<Row> itr = sheet.iterator();
		while(itr.hasNext()) 
		{
			//Extracting the values & performing validation
			Row row = itr.next();
			checkUserName = row.getCell(2).getStringCellValue();
			if(userName.equals(checkUserName)) {
				name = row.getCell(1).getStringCellValue();
			}
		}
		return name;
	}
}
