package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import model.Registrations;

public class DataCustomer {
	String filename = "C:\\Users\\mlaks\\OneDrive\\Desktop\\FSJ\\ProGrad_S-52\\S-2-2_Week-4\\BackEnd Data\\CustomerData.xls";
	int i=1;
	File checkFile = new File(filename);
	Workbook  workbook = null;
	FileOutputStream out;
	public HSSFWorkbook excelGenerate(Registrations registrations, List<Registrations> list) throws IOException
	{
		if(checkFile.exists())
		{
			//Checking if that file does exists or not
			try
			{
				FileInputStream fis = new FileInputStream(checkFile);
				Workbook  workbook = new HSSFWorkbook(fis);
				Sheet sheet= workbook.getSheet("CustomerDetails");
				fis.close();
				for(Registrations fillSheet: list)
				{
					//It inserts from second record by keeping on increasing the row count
					int rowSize = sheet.getPhysicalNumberOfRows();
			      	Row nextRows = sheet.createRow(rowSize++);
			      	nextRows.createCell(0).setCellValue(fillSheet.getDateTime().toString());
			      	nextRows.createCell(1).setCellValue(fillSheet.getName());
			      	nextRows.createCell(2).setCellValue(fillSheet.getUserName());
			      	nextRows.createCell(3).setCellValue(fillSheet.getEmail());
			      	nextRows.createCell(4).setCellValue(fillSheet.getPassword());
			      	nextRows.createCell(5).setCellValue(fillSheet.getConfirmPassword());
			      	nextRows.createCell(6).setCellValue(fillSheet.getMobileNumber());
			      	
	      	  	}
				
				out = new FileOutputStream(filename);
				workbook.write(out);
				out.close();
				workbook.close();
			}
			catch(Exception e){
				System.out.println("Error in DataCustomer if it existing");
			}
			return null;
		}	//close if
			
		//If file doesn't exists add its schema & it's first row.
		else
		{
			try
			{
				workbook = new HSSFWorkbook ();
				HSSFSheet  sheet= (HSSFSheet) workbook.createSheet("CustomerDetails");
				Row row=sheet.createRow(0);
				row.createCell(0).setCellValue("Date & Time");
				row.createCell(1).setCellValue("Name");
				row.createCell(2).setCellValue("User Name");
				row.createCell(3).setCellValue("Email-Id");
				row.createCell(4).setCellValue("Password");
				row.createCell(5).setCellValue("Confirm Password");
				row.createCell(6).setCellValue("Mobile Number");
				
				
			 	
				for(Registrations fillSheet: list)
				{
			      	Row nextRows = sheet.createRow(i);
			      	nextRows.createCell(0).setCellValue(fillSheet.getDateTime().toString());
			      	nextRows.createCell(1).setCellValue(fillSheet.getName());
			      	nextRows.createCell(2).setCellValue(fillSheet.getUserName());
			      	nextRows.createCell(3).setCellValue(fillSheet.getEmail());
			      	nextRows.createCell(4).setCellValue(fillSheet.getPassword());
			      	nextRows.createCell(5).setCellValue(fillSheet.getConfirmPassword());
			      	nextRows.createCell(6).setCellValue(fillSheet.getMobileNumber());
	      	  	}
	
				out = new FileOutputStream(filename);
				workbook.write(out);
				
			}
			catch (Exception e)
			{
				System.out.println("Error in DataCustomer if it is not existing");
			}
			return null;
		}	//close else
		
    }
}
//"C:\\Users\\admin\\Desktop\\FSJ\\ProGrad_S-52\\S-2-2_Week-4\\BackEnd Data\\CustomerData.xls"