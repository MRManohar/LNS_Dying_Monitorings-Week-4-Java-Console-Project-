package dao;

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

import model.Worker;

public class DataWorker {
	String filename = "C:\\Users\\mlaks\\eclipse-workspace\\Dying_Monitroings\\BackEnd Data\\Workers.xls";
	int i=1;
	File checkFile = new File(filename);
	Workbook  workbook = null;
	FileOutputStream out;
	public HSSFWorkbook excelGenerate(Worker worker, List<Worker> list) throws IOException
	{
		if(checkFile.exists())
		{
			//Checking if that file does exists or not
			try
			{
				FileInputStream fis = new FileInputStream(checkFile);
				Workbook  workbook = new HSSFWorkbook(fis);
				Sheet sheet= workbook.getSheetAt(0);
				fis.close();
				for(Worker fillSheet: list)
				{
					//It inserts from second record by keeping on increasing the row count
					int rowSize = sheet.getPhysicalNumberOfRows();
			      	Row nextRows = sheet.createRow(rowSize++);
			      	nextRows.createCell(0).setCellValue(fillSheet.getDate().toString());
			      	nextRows.createCell(1).setCellValue(fillSheet.getId());
			      	nextRows.createCell(2).setCellValue(fillSheet.getName());
			      	nextRows.createCell(3).setCellValue(fillSheet.getCategory());
			      	nextRows.createCell(4).setCellValue(fillSheet.getMobileNumber());
			      	nextRows.createCell(5).setCellValue(fillSheet.getAddress());
			      	
	      	  	}
				
				out = new FileOutputStream(filename);
				workbook.write(out);
				out.close();
				workbook.close();
			}
			catch(Exception e){
				System.out.println("Error in DataWorker if it existing");
			}
			return null;
		}	//close if
			
		//If file doesn't exists add its schema & it's first row.
		else
		{
			try
			{
				workbook = new HSSFWorkbook ();
				HSSFSheet  sheet= (HSSFSheet) workbook.createSheet("WorkersDetails");
				Row row=sheet.createRow(0);
				row.createCell(0).setCellValue("Date");
				row.createCell(1).setCellValue("ID");
				row.createCell(2).setCellValue("Name");
				row.createCell(3).setCellValue("Category");
				row.createCell(4).setCellValue("Mobile Number");
				row.createCell(5).setCellValue("Address");				
			 	
				for(Worker fillSheet: list)
				{
			      	Row nextRows = sheet.createRow(i);
			      	nextRows.createCell(0).setCellValue(fillSheet.getDate().toString());
			      	nextRows.createCell(1).setCellValue(fillSheet.getId());
			      	nextRows.createCell(2).setCellValue(fillSheet.getName());
			      	nextRows.createCell(3).setCellValue(fillSheet.getCategory());
			      	nextRows.createCell(4).setCellValue(fillSheet.getMobileNumber());
			      	nextRows.createCell(5).setCellValue(fillSheet.getAddress());
	      	  	}
	
				out = new FileOutputStream(filename);
				workbook.write(out);
				
			}
			catch (Exception e)
			{
				System.out.println("Error in DataWorker if it is not existing");
			}
			return null;
		}	//close else
		
    }
}
