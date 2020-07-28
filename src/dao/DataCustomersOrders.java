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

import model.Orders;

public class DataCustomersOrders {
Workbook  workbook = null;
	
	public HSSFWorkbook excelGenerate(Orders orders, List<Orders> list,String name) throws IOException
	{
		String filename = "C:\\Users\\mlaks\\eclipse-workspace\\Dying_Monitroings\\BackEnd Data\\Orders\\"+name+".xls";
		int i=1;
		File checkFile = new File(filename);
		FileOutputStream out;
		
		if(checkFile.exists())
		{
			//Checking if that file does exists or not

			try {
				FileInputStream fis = new FileInputStream(checkFile);
				Workbook  workbook = new HSSFWorkbook(fis);
				Sheet sheet= workbook.getSheet("Orders");
				fis.close();
				for(Orders fillSheet: list)
				{
					//It inserts from second record by keeping on increasing the row count
					int rowSize = sheet.getPhysicalNumberOfRows();
			      	Row nextRows = sheet.createRow(rowSize++);
			      	
			      	nextRows.createCell(0).setCellValue(fillSheet.getDate().toString());
			      	nextRows.createCell(1).setCellValue(fillSheet.getCountOfVarpulu());
			      	nextRows.createCell(2).setCellValue(fillSheet.getSapuriKgs());
			      	nextRows.createCell(3).setCellValue(fillSheet.getDupinKgs());
			     }
				
				out = new FileOutputStream(filename);
				workbook.write(out);
				out.close();
				workbook.close();
				
			}
			catch(Exception e){
				System.out.println("Error in DataCustomersOrders if it existing");
			}
			return null;
		}	//close if
		else {
			try
			{
				workbook = new HSSFWorkbook ();
				HSSFSheet  sheet= (HSSFSheet) workbook.createSheet("Orders");
				Row row=sheet.createRow(0);
				
				row.createCell(0).setCellValue("Date");
				row.createCell(1).setCellValue("Varpulu");
				row.createCell(2).setCellValue("Sapuri");
				row.createCell(3).setCellValue("Dupin");
							 	
				for(Orders fillSheet: list)
				{
			      	Row nextRows = sheet.createRow(i);
			      	
			      	nextRows.createCell(0).setCellValue(fillSheet.getDate().toString());
			      	nextRows.createCell(1).setCellValue(fillSheet.getCountOfVarpulu());
			      	nextRows.createCell(2).setCellValue(fillSheet.getSapuriKgs());
			      	nextRows.createCell(3).setCellValue(fillSheet.getDupinKgs());
	      	  	}
	
				out = new FileOutputStream(filename);
				workbook.write(out);
				
			}
			catch (Exception e)
			{
				System.out.println("Error in DataCustomersOrders if it is not existing");
			}			
		}
		return null;
	}
}
