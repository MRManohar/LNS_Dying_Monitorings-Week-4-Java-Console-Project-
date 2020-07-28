package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import controller.AdminControl;
import service.NumberValidation;

public class CheckOrders {
	public void checkDailyOrders() throws Exception {
		orderInPerticularDate();
	}
	
	private void orderInPerticularDate() throws Exception {
		System.out.println("Check ordres for ----->\n1. Today\n2. Past Days Orders");
		NumberValidation numberValidation = new NumberValidation();
		int choice = Integer.parseInt(numberValidation.enterNumber());
		switch(choice) {
		case 1:
			todaysOrder();
			break;
		case 2:
			pastDaysOrder();
			break;
		default:
			System.out.println("Enter valid choice");
			orderInPerticularDate();
		}
	}
	
	private void pastDaysOrder() throws Exception {
		previousDays();
	}
	
	private void todaysOrder() throws Exception {
		System.out.println("Please enter your choice\n1. Display Today\'s orders\n2. Print Today\'s orders");
		NumberValidation numberValidation = new NumberValidation();
		int choice = Integer.parseInt(numberValidation.enterNumber());
		switch(choice) {
		case 1:
			displayTodaysOrders();
			break;
		case 2: 
			printTodaysOrders();
			break;
		default:
			System.out.println("Please enter valid choice");
			todaysOrder();
		}
	}
	
	private void printTodaysOrders() throws Exception{

		File fileOrders = new File("C:\\Users\\mlaks\\eclipse-workspace\\Dying_Monitroings\\BackEnd Data\\Orders.xls");

		LocalDate date = LocalDate.now();
		LocalDate currentDate = null;
		int n=0;
		Document doc = new Document();
		try {
			PdfWriter wr = PdfWriter.getInstance(doc, new FileOutputStream("Today\'s-Orders.pdf"));
			Font boldFontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
			Font boldFontSubTitle = new Font(Font.FontFamily.TIMES_ROMAN, 12);
			
			float [] pointColumnWidths = {150F, 150F,150F,150F,150F};   
			PdfPTable table = new PdfPTable(5);
			table.setWidths(pointColumnWidths);
			
			doc.open();
			Image image1 = Image.getInstance("LNS_Dyings_Logo.png");
			//Fixed Positioning
			image1.setAbsolutePosition(350f,720f);
			//Scale to new height and new width of image
			image1.scaleAbsolute(200, 65);
			doc.add(image1);
			
//			doc.add(new Paragraph("RETAIL INVOICE").setBold().setUnderline().setTextAlignment(TextAlignment.CENTER));
			doc.add(new Paragraph("Lakshmi Narasimhas Swami Dyings              ",boldFontTitle));
			doc.add(new Paragraph("Venkateswara Kottalu, Proddatur,Kadapa",boldFontSubTitle));
			doc.add(new Paragraph("Email :- LNSDYINGS@GMAIL.COM",boldFontSubTitle));
			doc.add(new Paragraph("Contact No:- +91 - 9876543210",boldFontSubTitle));
			doc.add(new Paragraph("   ============================================================================",boldFontSubTitle));
			doc.add(new Paragraph("\n                                                                                                                          Date :- "+LocalDate.now()));
			doc.add(new Paragraph("\n                                                            Today's Orders..!\n"+"\n                             ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n"));
			
			PdfPCell cell0 = new PdfPCell(new Paragraph("S.No"));
			PdfPCell cell1 = new PdfPCell(new Paragraph("Name of Customer"));
		    PdfPCell cell2 = new PdfPCell(new Paragraph("No of Varpulu"));
		    PdfPCell cell3 = new PdfPCell(new Paragraph("No of KGs Sapuri"));
		    PdfPCell cell4 = new PdfPCell(new Paragraph("No of KGs Dupin"));
		    
		    table.addCell(cell0);
		    table.addCell(cell1);
	        table.addCell(cell2);
	        table.addCell(cell3);
	        table.addCell(cell4);
	        
	        FileInputStream fis = new FileInputStream(fileOrders);
			Workbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
			Iterator<Row> itr = sheet.iterator();
			while(itr.hasNext()){
				Row row = itr.next();
				if(row.getRowNum()!=0) {
					currentDate = LocalDate.parse(row.getCell(0).getStringCellValue());
					if(date.equals(currentDate)) {
						n++;
						String t = Integer.toString(n);
						
						cell0 = new PdfPCell(new Paragraph(t));
						cell1 = new PdfPCell(new Paragraph(row.getCell(1).getStringCellValue()));
					    cell2 = new PdfPCell(new Paragraph(row.getCell(2).getStringCellValue()));
					    cell3 = new PdfPCell(new Paragraph(row.getCell(3).getStringCellValue()));
					    cell4 = new PdfPCell(new Paragraph(row.getCell(4).getStringCellValue()));
					    
					    table.addCell(cell0);
					    table.addCell(cell1);
				        table.addCell(cell2);
				        table.addCell(cell3);
				        table.addCell(cell4);
				        			
					}
				}
			}
			
			workbook.close();
	        
	        doc.add(table);
	        
			doc.close();
			wr.close();
			System.out.println("PDF generated..");
		}
		catch(Exception e) {
			System.out.println("Error in printing pdf in PrintPDF");
		}
		AdminControl adminControl = new AdminControl();
		adminControl.adminOptions();
	}

	private void displayTodaysOrders() throws Exception {
		try {
			LocalDate date = LocalDate.now();
			LocalDate currentDate = null;
			File file = new File("C:\\Users\\mlaks\\eclipse-workspace\\Dying_Monitroings\\BackEnd Data\\Orders.xls");
			if(file.exists()) {
				System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"+"\n Today's Orders..!\n"+"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
				FileInputStream fis = new FileInputStream(file);
				Workbook workbook = new HSSFWorkbook(fis);
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				Iterator<Row> itr = sheet.iterator();
				while(itr.hasNext()){
					Row row = itr.next();
					if(row.getRowNum()!=0) {
						currentDate = LocalDate.parse(row.getCell(0).getStringCellValue());
						if(date.equals(currentDate))
							System.out.println(row.getCell(0).getStringCellValue()+"\t\t"+row.getCell(1).getStringCellValue()+"\t\t"+row.getCell(2).getStringCellValue()+"\t\t"+row.getCell(3).getStringCellValue()+"\t\t"+row.getCell(4).getStringCellValue());
					}
					else
						System.out.println(row.getCell(0).getStringCellValue()+"\t\t"+row.getCell(1).getStringCellValue()+"\t\t"+row.getCell(2).getStringCellValue()+"\t\t"+row.getCell(3).getStringCellValue()+"\t\t"+row.getCell(4).getStringCellValue());
				}
				
				
			workbook.close();
			}
			else
				System.out.println("Orders file doesnot exists");
		}
		catch(Exception e) {
				System.out.println("Error in todaysOrders in check orders");
		}
		AdminControl adminControl = new AdminControl();
		adminControl.adminOptions();
	}
	
	private void previousDays() throws Exception {
		System.out.println("Please select your choice\n1. Orders till yesterday\n2. Perticular Day of Orders");
		NumberValidation numberValidation = new NumberValidation();
		int choice = Integer.parseInt(numberValidation.enterNumber());
		switch(choice) {
		case 1:
			ordersTillYesterday();
			break;
		case 2:
			ordersForPerticularDay();
			break;
		default:
			System.out.println("Please select valid choice");
			previousDays();
		}
	}
	
	private void ordersForPerticularDay() throws Exception {
		System.out.println("Please enter your choice\n1. Display PerticularDay\'s orders\n2. Print PerticularDay\'s orders");
		NumberValidation numberValidation = new NumberValidation();
		int choice = Integer.parseInt(numberValidation.enterNumber());
		switch(choice) {
		case 1:
			displayPerticularDayOrders();
			break;
		case 2: 
			printPerticularDayOrders();
			break;
		default:
			System.out.println("Please enter valid choice");
			ordersForPerticularDay();
		}		
	}
	
	private void printPerticularDayOrders() throws Exception {

		int z= 1;
		File file = new File("C:\\Users\\mlaks\\eclipse-workspace\\Dying_Monitroings\\BackEnd Data\\Orders.xls");
		Document doc = new Document();
		try {       
			FileInputStream fis = new FileInputStream(file);
			Workbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
			String pastDate = sheet.getRow(1).getCell(0).getStringCellValue();
			String orderData = pastDate;
			System.out.println("1 "+pastDate);
			Iterator<Row> itr = sheet.iterator();
			while(itr.hasNext()){
				Row row = itr.next();
				if(row.getRowNum()!=0) {
					if(!(pastDate.equals(row.getCell(0).getStringCellValue()))) {
						z++;
						System.out.println(z+" "+row.getCell(0).getStringCellValue());
						pastDate = row.getCell(0).getStringCellValue();
						orderData = orderData.concat(","+row.getCell(0).getStringCellValue());
					}
				}
			}
			String[] orderArr = orderData.split(",");
			NumberValidation numberValidation = new NumberValidation();
			int n = Integer.parseInt(numberValidation.enterNumber());
			String date = orderArr[n-1];
			
			PdfWriter wr = PdfWriter.getInstance(doc, new FileOutputStream(date+"\'s-Orders.pdf"));
			Font boldFontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
			Font boldFontSubTitle = new Font(Font.FontFamily.TIMES_ROMAN, 12);
			
			float [] pointColumnWidths = {150F, 150F,150F,150F,150F};   
			PdfPTable table = new PdfPTable(5);
			table.setWidths(pointColumnWidths);
			
			doc.open();
			Image image1 = Image.getInstance("LNS_Dyings_Logo.png");
			//Fixed Positioning
			image1.setAbsolutePosition(350f,720f);
			//Scale to new height and new width of image
			image1.scaleAbsolute(200, 65);
			doc.add(image1);
			
//			doc.add(new Paragraph("RETAIL INVOICE").setBold().setUnderline().setTextAlignment(TextAlignment.CENTER));
			doc.add(new Paragraph("Lakshmi Narasimhas Swami Dyings              ",boldFontTitle));
			doc.add(new Paragraph("Venkateswara Kottalu, Proddatur,Kadapa",boldFontSubTitle));
			doc.add(new Paragraph("Email :- LNSDYINGS@GMAIL.COM",boldFontSubTitle));
			doc.add(new Paragraph("Contact No:- +91 - 9876543210",boldFontSubTitle));
			doc.add(new Paragraph("   ============================================================================",boldFontSubTitle));
			doc.add(new Paragraph("\n                                                                                                                          Date :- "+LocalDate.now()));
			doc.add(new Paragraph("\n                                                            "+date+"\'s-Orders..!\n"+"\n                             ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n"));
			
			PdfPCell cell0 = new PdfPCell(new Paragraph("S.No"));
			PdfPCell cell1 = new PdfPCell(new Paragraph("Name of Customer"));
		    PdfPCell cell2 = new PdfPCell(new Paragraph("No of Varpulu"));
		    PdfPCell cell3 = new PdfPCell(new Paragraph("No of KGs Sapuri"));
		    PdfPCell cell4 = new PdfPCell(new Paragraph("No of KGs Dupin"));
		    
		    table.addCell(cell0);
		    table.addCell(cell1);
	        table.addCell(cell2);
	        table.addCell(cell3);
	        table.addCell(cell4);
			
			Iterator<Row> itr1 = sheet.iterator();
			while(itr1.hasNext()) {
				Row row = itr1.next();
				if(row.getCell(0).getStringCellValue().equals(date)) {
					n++;
					String t = Integer.toString(n);
					
					cell0 = new PdfPCell(new Paragraph(t));
					cell1 = new PdfPCell(new Paragraph(row.getCell(1).getStringCellValue()));
				    cell2 = new PdfPCell(new Paragraph(row.getCell(2).getStringCellValue()));
				    cell3 = new PdfPCell(new Paragraph(row.getCell(3).getStringCellValue()));
				    cell4 = new PdfPCell(new Paragraph(row.getCell(4).getStringCellValue()));
				    
				    table.addCell(cell0);
				    table.addCell(cell1);
			        table.addCell(cell2);
			        table.addCell(cell3);
			        table.addCell(cell4);
			        
				}
			}
			workbook.close();
	        
	        doc.add(table);
	        
			doc.close();
			wr.close();
			System.out.println("PDF generated..");
		}
		catch(Exception e) {
			System.out.println("Error in printPerticularDayOrders");
		}
		
	}
	
	private void displayPerticularDayOrders() throws Exception {
		try {
			int z= 1;
			File file = new File("C:\\Users\\mlaks\\eclipse-workspace\\Dying_Monitroings\\BackEnd Data\\Orders.xls");
			if(file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				Workbook workbook = new HSSFWorkbook(fis);
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				String pastDate = sheet.getRow(1).getCell(0).getStringCellValue();
				String orderData = pastDate;
				System.out.println("1 "+pastDate);
				Iterator<Row> itr = sheet.iterator();
				while(itr.hasNext()){
					Row row = itr.next();
					if(row.getRowNum()!=0) {
						if(!(pastDate.equals(row.getCell(0).getStringCellValue()))) {
							z++;
							System.out.println(z+" "+row.getCell(0).getStringCellValue());
							pastDate = row.getCell(0).getStringCellValue();
							orderData = orderData.concat(","+row.getCell(0).getStringCellValue());
						}
					}
				}
				String[] orderArr = orderData.split(",");
				NumberValidation numberValidation = new NumberValidation();
				int n = Integer.parseInt(numberValidation.enterNumber());
				String date = orderArr[n-1];
				System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"+"\n "+date+" Orders..!\n"+"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
				Iterator<Row> itr1 = sheet.iterator();
				while(itr1.hasNext()) {
					Row row = itr1.next();
					if(row.getRowNum()==0)
						System.out.println(row.getCell(0).getStringCellValue()+"\t\t"+row.getCell(1).getStringCellValue()+"\t\t"+row.getCell(2).getStringCellValue()+"\t\t"+row.getCell(3).getStringCellValue()+"\t\t"+row.getCell(4).getStringCellValue());
					else if(row.getCell(0).getStringCellValue().equals(date))
						System.out.println(row.getCell(0).getStringCellValue()+"\t\t"+row.getCell(1).getStringCellValue()+"\t\t"+row.getCell(2).getStringCellValue()+"\t\t"+row.getCell(3).getStringCellValue()+"\t\t"+row.getCell(4).getStringCellValue());
				}
				workbook.close();
			}
			else
				System.out.println("Orders file doesnot exists");
			
		}
		catch(Exception e) {
			System.out.println("Error in displaying order in ordersForPerticularDay in check orders");
		}
		AdminControl adminControl = new AdminControl();
		adminControl.adminOptions();
	}
		
	private void ordersTillYesterday() throws Exception {
		System.out.println("Please enter your choice\n1. Display Orders Till Yesterday\n2. Print Orders Till Yesterday");
		NumberValidation numberValidation = new NumberValidation();
		int choice = Integer.parseInt(numberValidation.enterNumber());
		switch(choice) {
		case 1:
			displayOrdersTillYesterday();
			break;
		case 2: 
			printOrdersTillYesterday();
			break;
		default:
			System.out.println("Please enter valid choice");
			ordersTillYesterday();
		}
	}
	
	private void printOrdersTillYesterday() throws Exception {

		File fileOrders = new File("C:\\Users\\mlaks\\eclipse-workspace\\Dying_Monitroings\\BackEnd Data\\Orders.xls");

		LocalDate date = LocalDate.now();
		LocalDate currentDate = null;
		int n=0;
		Document doc = new Document();
		try {
			PdfWriter wr = PdfWriter.getInstance(doc, new FileOutputStream("Orders-Till-Yesterday.pdf"));
			Font boldFontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
			Font boldFontSubTitle = new Font(Font.FontFamily.TIMES_ROMAN, 12);
			
			float [] pointColumnWidths = {150F,150F, 150F,150F,150F,150F};   
			PdfPTable table = new PdfPTable(6);
			table.setWidths(pointColumnWidths);
			
			doc.open();
			Image image1 = Image.getInstance("LNS_Dyings_Logo.png");
			//Fixed Positioning
			image1.setAbsolutePosition(350f,720f);
			//Scale to new height and new width of image
			image1.scaleAbsolute(200, 65);
			doc.add(image1);
			
//			doc.add(new Paragraph("RETAIL INVOICE").setBold().setUnderline().setTextAlignment(TextAlignment.CENTER));
			doc.add(new Paragraph("Lakshmi Narasimhas Swami Dyings              ",boldFontTitle));
			doc.add(new Paragraph("Venkateswara Kottalu, Proddatur,Kadapa",boldFontSubTitle));
			doc.add(new Paragraph("Email :- LNSDYINGS@GMAIL.COM",boldFontSubTitle));
			doc.add(new Paragraph("Contact No:- +91 - 9876543210",boldFontSubTitle));
			doc.add(new Paragraph("   ============================================================================",boldFontSubTitle));
			doc.add(new Paragraph("\n                                                                                                                          Date :- "+LocalDate.now()));
			doc.add(new Paragraph("\n                                                           Till yesterday\'s Orders..!\n"+"\n                             ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n"));
			
			PdfPCell cell0 = new PdfPCell(new Paragraph("S.No"));
			PdfPCell cell01 = new PdfPCell(new Paragraph("Date"));
			PdfPCell cell1 = new PdfPCell(new Paragraph("Name of Customer"));
		    PdfPCell cell2 = new PdfPCell(new Paragraph("No of Varpulu"));
		    PdfPCell cell3 = new PdfPCell(new Paragraph("No of KGs Sapuri"));
		    PdfPCell cell4 = new PdfPCell(new Paragraph("No of KGs Dupin"));
		    
		    table.addCell(cell0);
		    table.addCell(cell01);
		    table.addCell(cell1);
	        table.addCell(cell2);
	        table.addCell(cell3);
	        table.addCell(cell4);
	        
	        FileInputStream fis = new FileInputStream(fileOrders);
			Workbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
			Iterator<Row> itr = sheet.iterator();
			while(itr.hasNext()){
				Row row = itr.next();
				if(row.getRowNum()!=0) {
					currentDate = LocalDate.parse(row.getCell(0).getStringCellValue());
					if(!(date.equals(currentDate))) {
						n++;
						String t = Integer.toString(n);
						
						cell0 = new PdfPCell(new Paragraph(t));
						cell01 = new PdfPCell(new Paragraph(row.getCell(0).getStringCellValue()));
						cell1 = new PdfPCell(new Paragraph(row.getCell(1).getStringCellValue()));
					    cell2 = new PdfPCell(new Paragraph(row.getCell(2).getStringCellValue()));
					    cell3 = new PdfPCell(new Paragraph(row.getCell(3).getStringCellValue()));
					    cell4 = new PdfPCell(new Paragraph(row.getCell(4).getStringCellValue()));
					    
					    table.addCell(cell0);
					    table.addCell(cell01);
					    table.addCell(cell1);
				        table.addCell(cell2);
				        table.addCell(cell3);
				        table.addCell(cell4);
				        			
					}
				}
			}
			
			workbook.close();
	        
	        doc.add(table);
	        
			doc.close();
			wr.close();
			System.out.println("PDF generated..");
		}
		catch(Exception e) {
			System.out.println("Error in printing pdf in PrintPDF");
		}
		AdminControl adminControl = new AdminControl();
		adminControl.adminOptions();
	}
	
	private void displayOrdersTillYesterday() throws Exception {
		try {
			LocalDate date = LocalDate.now();
			LocalDate currentDate = null;
			File file = new File("C:\\Users\\mlaks\\eclipse-workspace\\Dying_Monitroings\\BackEnd Data\\Orders.xls");
			if(file.exists()) {
				System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"+"\n Orders still yesterday..!\n"+"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			FileInputStream fis = new FileInputStream(file);
			Workbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
			
				Iterator<Row> itr = sheet.iterator();
				while(itr.hasNext()) 
				{
					Row row = itr.next();
					if(row.getRowNum()!=0) {
						currentDate = LocalDate.parse(row.getCell(0).getStringCellValue());
						if(date.isAfter(currentDate))
							System.out.println(row.getCell(0).getStringCellValue()+"\t\t"+row.getCell(1).getStringCellValue()+"\t\t"+row.getCell(2).getStringCellValue()+"\t\t"+row.getCell(3).getStringCellValue()+"\t\t"+row.getCell(4).getStringCellValue());
					}
					else
						System.out.println(row.getCell(0).getStringCellValue()+"\t\t"+row.getCell(1).getStringCellValue()+"\t\t"+row.getCell(2).getStringCellValue()+"\t\t"+row.getCell(3).getStringCellValue()+"\t\t"+row.getCell(4).getStringCellValue());
				}		
			workbook.close();
			}
			else
				System.out.println("Orders file doesnot exists");
			
		}
		catch(Exception e) {
			System.out.println("Error in pastDaysOrders in check  orders");
		}
		AdminControl adminControl = new AdminControl();
		adminControl.adminOptions();
	}
}
