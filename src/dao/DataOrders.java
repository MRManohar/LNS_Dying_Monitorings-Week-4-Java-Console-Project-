package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import model.Orders;


public class DataOrders {
	String filename = "C:\\Users\\mlaks\\eclipse-workspace\\Dying_Monitroings\\BackEnd Data\\Orders.xls";
	int i=1,flag=0,j=0,rowNum=0;
	String dataVarpulu = null, dataSapuri = null, dataDupin = null/*, dataBill = null*/;
	File checkFile = new File(filename);
	Workbook  workbook = null;
	FileOutputStream out;
	public HSSFWorkbook excelGenerate(Orders orders, List<Orders> list,String name) throws IOException
	{
//		int flag=0,k=0;
		
		if(checkFile.exists())
		{
			try	{
				FileInputStream fis = new FileInputStream(checkFile);
				@SuppressWarnings("resource")
				Workbook  workbook = new HSSFWorkbook(fis);
				Sheet sheet= workbook.getSheet("Orders");
				fis.close();
				LocalDate currentDate = orders.getDate();
//				System.out.println("current Date = "+currentDate);
				for(j=1;j<sheet.getPhysicalNumberOfRows();j++) {
					flag = 1; 
					Row r = sheet.getRow(j);
					String date = r.getCell(0).getStringCellValue();
//					System.out.println("date string = "+date);
					LocalDate pastDate = LocalDate.parse(date);
//					System.out.println("past date (Local Date) = "+pastDate);
					if(r.getCell(1).getStringCellValue().equals(name)) {
//						System.out.println("Name checking ok");
						if(currentDate.isEqual(pastDate)) {
//							System.out.println("Date checking ok");
//							System.out.println("past flag = "+flag);
							flag=0;
//							System.out.println("updated flag = "+flag);
							rowNum=j;
//							System.out.println("Current Row num: "+rowNum);
							break;
						}
					}
				}
				if(flag==0) {
					Row currentRow = sheet.getRow(rowNum);
//					System.out.println("Current Row num: "+rowNum);
					dataVarpulu = currentRow.getCell(2).getStringCellValue();
					dataSapuri = currentRow.getCell(3).getStringCellValue();
					dataDupin = currentRow.getCell(4).getStringCellValue();
//					dataBill = currentRow.getCell(4).getStringCellValue();
//					long pastBill = Long.parseLong(dataBill);
					Cell cellVarpulu = currentRow.getCell(2);
					Cell cellSapuri = currentRow.getCell(3);
					Cell cellDupin = currentRow.getCell(4);
//					Cell cellBill = currentRow.getCell(4);
					currentRow.removeCell(cellVarpulu);
					currentRow.removeCell(cellSapuri);
					currentRow.removeCell(cellDupin);
//					currentRow.removeCell(cellBill);
					for(Orders fillSheet: list)	{
						currentRow.createCell(2).setCellValue(addItems(fillSheet.getCountOfVarpulu(),dataVarpulu));
						currentRow.createCell(3).setCellValue(addItems(fillSheet.getSapuriKgs(),dataSapuri));
						currentRow.createCell(4).setCellValue(addItems(fillSheet.getDupinKgs(),dataDupin));
//						long presentBill = totalBill(fillSheet.getCountOfVarpulu(),fillSheet.getSapuriKgs(),fillSheet.getDupinKgs());
//						currentRow.createCell(4).setCellValue(presentBill+pastBill);
					}
					flag=1;					
				}
				else {
					for(Orders fillSheet: list)	{
						//	It inserts from second record by keeping on increasing the row count
						int rowSize = sheet.getPhysicalNumberOfRows();
						Row nextRows = sheet.createRow(rowSize++);
						nextRows.createCell(0).setCellValue(fillSheet.getDate().toString());
						nextRows.createCell(1).setCellValue(name);
						nextRows.createCell(2).setCellValue(fillSheet.getCountOfVarpulu());
						nextRows.createCell(3).setCellValue(fillSheet.getSapuriKgs());
			      		nextRows.createCell(4).setCellValue(fillSheet.getDupinKgs());
//			      		long bill = totalBill(fillSheet.getCountOfVarpulu(),fillSheet.getSapuriKgs(),fillSheet.getDupinKgs());
//			      		nextRows.createCell(4).setCellValue(bill);
	      	  		}
				}
				
				out = new FileOutputStream(filename);
				workbook.write(out);
				out.close();
				workbook.close();
				
			}
			catch(Exception e){
				System.out.println("Error in DataOrders if it existing");
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
				row.createCell(1).setCellValue("Name of Customer");
				row.createCell(2).setCellValue("Varpulu");
				row.createCell(3).setCellValue("Sapuri");
				row.createCell(4).setCellValue("Dupin");
//				row.createCell(4).setCellValue("Total Amount");
				
			 	
				for(Orders fillSheet: list)
				{
			      	Row nextRows = sheet.createRow(i);
			      	nextRows.createCell(0).setCellValue(fillSheet.getDate().toString());
					nextRows.createCell(1).setCellValue(name);
					nextRows.createCell(2).setCellValue(fillSheet.getCountOfVarpulu());
					nextRows.createCell(3).setCellValue(fillSheet.getSapuriKgs());
		      		nextRows.createCell(4).setCellValue(fillSheet.getDupinKgs());
//			      	long bill = totalBill(fillSheet.getCountOfVarpulu(),fillSheet.getSapuriKgs(),fillSheet.getDupinKgs());
//			      	nextRows.createCell(4).setCellValue(bill);
	      	  	}
	
				out = new FileOutputStream(filename);
				workbook.write(out);
				
			}
			catch (Exception e)
			{
				System.out.println("Error in DataOrders if it is not existing");
			}			
		}
		return null;
	}
	private String addItems(String stringCellValue, String dataVarpulu) {
		int pastVarpulu = Integer.parseInt(dataVarpulu);
		int presentVarpulu = Integer.parseInt(stringCellValue);
		int updatedVarpulu = pastVarpulu + presentVarpulu;
		String varpulu = Integer.toString(updatedVarpulu);
		return varpulu;
	}
//	private long totalBill(String countOfVarpulu, String sapuriKgs, String dupinKgs) {
//		int varpulu = Integer.parseInt(countOfVarpulu);
//		int sapuri = Integer.parseInt(sapuriKgs);
//		int dupin = Integer.parseInt(dupinKgs);
//		long totalAmount = (varpulu*450)+(sapuri*200)+(dupin*120);
//		return totalAmount;
//	}

}
