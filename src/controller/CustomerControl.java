package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.Orders;
import dao.DataCustomersOrders;
import dao.DataOrders;
import service.EmailValidation;
import service.MobileValidation;
import service.NumberValidation;
import service.PasswordValidation;
import service.UserNameValidation;

public class CustomerControl {
	public void customerOptions(String name) throws Exception{
		System.out.println("Please enter your choice");
		System.out.println("1. Orders\n2. Bill\n3. Display My Orders\n4. Edit My Profile\n5. Display My Profile\n6. Logout");
		NumberValidation numberValidation = new NumberValidation();
		int choice = Integer.parseInt(numberValidation.enterNumber());
		switch(choice) {
		case 1: 
			placeOrders(name);
			break;
		case 2:
			generateBill(name);
			break;
		case 3:
			displayMyOrders(name);
			break;
		case 4:
			editProfile(name);
			break;
		case 5:
			displayProfile(name);
			break;
		case 6:
			logout();
			break;
		default:
			System.out.println("Please enter correct choice");
			customerOptions(name);
		}
	}

	private void displayMyOrders(String name) throws Exception {
		System.out.println("Please select your option\n1. To display my orders\n2. To print my orders");
		NumberValidation numberValidation = new NumberValidation();
		int choice = Integer.parseInt(numberValidation.enterNumber());
		switch(choice) {
		case 1:
			toDisplayMyOrders(name);
			break;
		case 2:
			toPrintMyOrders(name);
			break;
		default:
			System.out.println("Please select valid choice");
			displayMyOrders(name);
		}
	}

	private void toPrintMyOrders(String name) throws Exception {

		try {
			File file = new File("C:\\Users\\mlaks\\eclipse-workspace\\Dying_Monitroings\\BackEnd Data\\Orders\\"+name+".xls");
			if(file.exists()) {
				int n=0;
				Document doc = new Document();
				FileInputStream fis = new FileInputStream(file);
				Workbook workbook = new HSSFWorkbook(fis);
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				Iterator<Row> itr = sheet.iterator();
				PdfWriter wr = PdfWriter.getInstance(doc, new FileOutputStream(name+"-orders.pdf"));
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
				
//				doc.add(new Paragraph("RETAIL INVOICE").setBold().setUnderline().setTextAlignment(TextAlignment.CENTER));
				doc.add(new Paragraph("Lakshmi Narasimhas Swami Dyings              ",boldFontTitle));
				doc.add(new Paragraph("Venkateswara Kottalu, Proddatur,Kadapa",boldFontSubTitle));
				doc.add(new Paragraph("Email :- LNSDYINGS@GMAIL.COM",boldFontSubTitle));
				doc.add(new Paragraph("Contact No:- +91 - 9876543210",boldFontSubTitle));
				doc.add(new Paragraph("   ============================================================================",boldFontSubTitle));
				doc.add(new Paragraph("\n                                                                                                                          Date :- "+LocalDate.now()));
				
				doc.add(new Paragraph("\n                                                            "+name+"\'s-Orders..!\n"+"\n                             ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n"));
				
				PdfPCell cell0 = new PdfPCell(new Paragraph("S.No"));
				PdfPCell cell1 = new PdfPCell(new Paragraph("Date of Order"));
			    PdfPCell cell2 = new PdfPCell(new Paragraph("No of Varpulu"));
			    PdfPCell cell3 = new PdfPCell(new Paragraph("No of KGs Sapuri"));
			    PdfPCell cell4 = new PdfPCell(new Paragraph("No of KGs Dupin"));
			    
			    table.addCell(cell0);
			    table.addCell(cell1);
		        table.addCell(cell2);
		        table.addCell(cell3);
		        table.addCell(cell4);
		        
		        while(itr.hasNext()){
					Row row = itr.next();
					if(row.getRowNum()!=0) {
						n++;
						String t = Integer.toString(n);
						
						cell0 = new PdfPCell(new Paragraph(t));
						cell1 = new PdfPCell(new Paragraph(row.getCell(0).getStringCellValue()));
					    cell2 = new PdfPCell(new Paragraph(row.getCell(1).getStringCellValue()));
					    cell3 = new PdfPCell(new Paragraph(row.getCell(2).getStringCellValue()));
					    cell4 = new PdfPCell(new Paragraph(row.getCell(3).getStringCellValue()));
					    
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
			else
				System.out.println("Your file does not exists.");
		}
		catch(Exception e) {
			System.out.println("Error in printing my orders in customerController");
		}
		customerOptions(name);
	}

	private void toDisplayMyOrders(String name) throws Exception {
		File file = new File("C:\\Users\\mlaks\\eclipse-workspace\\Dying_Monitroings\\BackEnd Data\\Orders\\"+name+".xls");
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n"+name+"'s Orders..!\n"+"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		if(file.exists()) {
			try {
				FileInputStream fis = new FileInputStream(file);
				Workbook workbook = new HSSFWorkbook(fis);
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				Iterator<Row> itr = sheet.iterator();
				while(itr.hasNext()){
					Row row = itr.next();
						System.out.println(row.getCell(0).getStringCellValue()+"\t"+row.getCell(1).getStringCellValue()+"\t"+row.getCell(2).getStringCellValue()+"\t"+row.getCell(3).getStringCellValue());
					}
				workbook.close();
			}
			catch(Exception e) {
				System.out.println("Error in Excer Reader for customer to disply mhy orders.");
			}
		}
		else
			System.out.println("Customer file doesnot exists");
		customerOptions(name);
	}

	private void displayProfile(String name) throws Exception {
		String file = "C:\\Users\\mlaks\\eclipse-workspace\\Dying_Monitroings\\BackEnd Data\\CustomerData.xls";
		int flag=0,j=0,rowNum=0;
		File checkFile = new File(file);
		if(checkFile.exists()) {
			//		Workbook  workbook = null;
			FileOutputStream out;
			try {
				FileInputStream fis = new FileInputStream(checkFile);
				@SuppressWarnings("resource")
				Workbook  workbook = new HSSFWorkbook(fis);
				Sheet sheet= workbook.getSheetAt(0);
				fis.close();
				for(j=0;j<sheet.getPhysicalNumberOfRows();j++) {
					flag = 1;
					Row r = sheet.getRow(j);
					if(r.getCell(1).getStringCellValue().equals(name)) {
						flag=0;
						rowNum=j;
						break;
					}
				}
				if(flag==0) {
					Row row = sheet.getRow(rowNum);
					System.out.println("Name          \t:\t"+row.getCell(1));
					System.out.println("User Name     \t:\t"+row.getCell(2));
					System.out.println("Email ID      \t:\t"+row.getCell(3));
					System.out.println("Password      \t:\t"+row.getCell(4));
					System.out.println("Mobile Number \t:\t"+row.getCell(5));					
				}
				out = new FileOutputStream(file);
				workbook.write(out);
				out.close();
				workbook.close();
			}
			catch(Exception e) {
				System.out.println("Error in display my profile in customer controller");
			}
		}
		else
			System.out.println("Customer file doesnot exists");
		customerOptions(name);
	}

	private void editProfile(String name) throws Exception {
		String file = "C:\\Users\\mlaks\\eclipse-workspace\\Dying_Monitroings\\BackEnd Data\\CustomerData.xls";
		int flag=0,j=0,rowNum=0;
		File checkFile = new File(file);
//		Workbook  workbook = null;
		if(checkFile.exists()) {
			FileOutputStream out;
			try {
				FileInputStream fis = new FileInputStream(checkFile);
				@SuppressWarnings("resource")
				Workbook  workbook = new HSSFWorkbook(fis);
				Sheet sheet= workbook.getSheetAt(0);
				fis.close();
				for(j=0;j<sheet.getPhysicalNumberOfRows();j++) {
					flag = 1;
					Row r = sheet.getRow(j);
					if(r.getCell(1).getStringCellValue().equals(name)) {
						flag=0;
						rowNum=j;
						break;
					}
				}
				if(flag==0) {
					Row row = sheet.getRow(rowNum);
					NumberValidation numberValidation = new NumberValidation();
					System.out.println("Enter no of requirement edits");
					int n = Integer.parseInt(numberValidation.enterNumber());
					for(int z=1;z<=n;z++) {
						int ch = editmore();
						switch(ch) {
//						case 1:
//							NameValidation nameValidation = new NameValidation();
//							String editName = nameValidation.enterName();
//							Cell cellName = row.getCell(ch);
//							row.removeCell(cellName);
//							row.createCell(ch).setCellValue(editName);
//							break;
						case 2:
							UserNameValidation userNameValidation = new UserNameValidation();
							String userName = userNameValidation.enterUserName();
							Cell cellUserName = row.getCell(ch);
							row.removeCell(cellUserName);
							row.createCell(ch).setCellValue(userName);
							break;
						case 3:
							EmailValidation emailValidation = new EmailValidation();
							String emailID = emailValidation.enterEmail();
							Cell cellEmailID = row.getCell(ch);
							row.removeCell(cellEmailID);
							row.createCell(ch).setCellValue(emailID);
							break;
						case 4:
							PasswordValidation passwordValidation = new PasswordValidation();
							String password = passwordValidation.enterPassword();
							Cell cellPassword = row.getCell(ch);
							row.removeCell(cellPassword);
							row.createCell(ch).setCellValue(password);
							break;
						case 5:
							MobileValidation mobileValidation = new MobileValidation();
							String mobileNubmer = mobileValidation.enterMobileNumber();
							Cell cellMobileNumber = row.getCell(ch);
							row.removeCell(cellMobileNumber);
							row.createCell(ch).setCellValue(mobileNubmer);
							break;
						}
					}
				}
				out = new FileOutputStream(file);
				workbook.write(out);
				out.close();
				workbook.close();
				System.out.println("Check your updated details");
				displayProfile(name);
			}
			catch(Exception e){
				System.out.println("Error in editing admin info in Customer controller.");
			}
		}
		else
			System.out.println("Customer file doesnot exists");
		customerOptions(name);
	}
	
	private int editmore() throws Exception{
		System.out.println("Please enter choice\n1. User Name\n2. Email ID\n3. Password\n4. Mobile Number");
		NumberValidation numberValidation = new NumberValidation();
		int c = Integer.parseInt(numberValidation.enterNumber());
		if(c<5 && c>0)
			c++;
		else
			editmore();
		return c;
	}

	private void logout() throws Exception{
		System.out.println("\n=========================================\n"+"\nYou Successfully Logged out\n"+"\n=========================================\n");
		System.out.println("\nPlease select your choice\n1. Login\n2. Exit");
		NumberValidation num =new NumberValidation();
		int choice = Integer.parseInt(num.enterNumber());
		
		switch(choice) {
		case 1:
			System.out.println("\nRedirecting to Login Page..!\n");
			LoginCustomer loginCustomer = new LoginCustomer();
			loginCustomer.customerLogin();
			break;
		case 2:
			System.exit(0);
			break;
		default:
			System.out.println("\nPlease select valid option\n");
			logout();
		}
	}

	private void generateBill(String name) throws Exception{
		cusBillGeneration(name,1);
	}

	public void cusBillGeneration(String name,int  n) throws Exception {
		System.out.println("Please select your option\n1. To display bill for orders\n2. To print bill for orders");
		NumberValidation numberValidation = new NumberValidation();
		int choice = Integer.parseInt(numberValidation.enterNumber());
		switch(choice) {
		case 1:
			toDisplayBillForMyOrders(name,n);
			break;
		case 2:
			toPrintBillForMyOrders(name,n);
			break;
		default:
			System.out.println("Please select valid choice");
			cusBillGeneration(name,n);
		}
	}

	private void toPrintBillForMyOrders(String name,int a) throws Exception{
		try {
			long amt = 0,sum=0;
			File file = new File("C:\\Users\\mlaks\\eclipse-workspace\\Dying_Monitroings\\BackEnd Data\\Orders\\"+name+".xls");
			if(file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				Workbook workbook = new HSSFWorkbook(fis);
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				Iterator<Row> itr = sheet.iterator();
				
				int n=0;
				Document doc = new Document();
				
				PdfWriter wr = PdfWriter.getInstance(doc, new FileOutputStream(name+"\'s-Bill.pdf"));
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
				
//				doc.add(new Paragraph("RETAIL INVOICE").setBold().setUnderline().setTextAlignment(TextAlignment.CENTER));
				doc.add(new Paragraph("Lakshmi Narasimhas Swami Dyings              ",boldFontTitle));
				doc.add(new Paragraph("Venkateswara Kottalu, Proddatur,Kadapa",boldFontSubTitle));
				doc.add(new Paragraph("Email :- LNSDYINGS@GMAIL.COM",boldFontSubTitle));
				doc.add(new Paragraph("Contact No:- +91 - 9876543210",boldFontSubTitle));
				doc.add(new Paragraph("   ============================================================================",boldFontSubTitle));
				doc.add(new Paragraph("\n                                                                                                                          Date :- "+LocalDate.now()));
				doc.add(new Paragraph("\n                                                            "+name+"\'s Bill                                                      \n"+"\n                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n"));
				
				doc.add(new Paragraph("                Cost Perticulers ------------------>\n"+"\n                Amount for one single Varpu = 450/-"+"\n                Amount for one KG Sapuri = 200/-\n"+"                Amount for one KG Dupin = 120/-\n\n\n"));
				
				PdfPCell cell0 = new PdfPCell(new Paragraph("S.No"));
				PdfPCell cell1 = new PdfPCell(new Paragraph("Date of Order"));
			    PdfPCell cell2 = new PdfPCell(new Paragraph("No of Varpulu"));
			    PdfPCell cell3 = new PdfPCell(new Paragraph("No of KGs Sapuri"));
			    PdfPCell cell4 = new PdfPCell(new Paragraph("No of KGs Dupin"));
			    PdfPCell cell5 = new PdfPCell(new Paragraph("Amount"));
			    
			    table.addCell(cell0);
			    table.addCell(cell1);
		        table.addCell(cell2);
		        table.addCell(cell3);
		        table.addCell(cell4);
		        table.addCell(cell5);
		        
		        while(itr.hasNext()){
					Row row = itr.next();
					if(row.getRowNum()!=0) {
						n++;
						String t = Integer.toString(n);
						amt = totalAmount(row.getCell(1).getStringCellValue(),row.getCell(2).getStringCellValue(),row.getCell(3).getStringCellValue());
						sum+=amt;
						String amtStr = Long.toString(amt);
						cell0 = new PdfPCell(new Paragraph(t));
						cell1 = new PdfPCell(new Paragraph(row.getCell(0).getStringCellValue()));
					    cell2 = new PdfPCell(new Paragraph(row.getCell(1).getStringCellValue()));
					    cell3 = new PdfPCell(new Paragraph(row.getCell(2).getStringCellValue()));
					    cell4 = new PdfPCell(new Paragraph(row.getCell(3).getStringCellValue()));
					    cell5 = new PdfPCell(new Paragraph(amtStr));
						
					    table.addCell(cell0);
					    table.addCell(cell1);
				        table.addCell(cell2);
				        table.addCell(cell3);
				        table.addCell(cell4);
				        table.addCell(cell5);
				        
					}
				}
		        String sumStr = Long.toString(sum);
		        PdfPCell cell23 = new PdfPCell(new Phrase("Total"));
		        cell23.setColspan(5);
		        cell23.setRowspan(1);
		        table.addCell(cell23);
		        cell5 = new PdfPCell(new Paragraph(sumStr));
		        table.addCell(cell5);
		        
		        workbook.close();
		        
		        doc.add(table);
		        
				doc.close();
				wr.close();
				System.out.println("PDF generated..");
			}
			else
				System.out.println(name+" file does not exist.");
		}
		catch(Exception e) {
			System.out.println("Error in generating pdf for bill for my oders in customer controller");
		}
		if(a==1)
			customerOptions(name);
		else {
			AdminControl adminControl = new AdminControl();
			adminControl.adminOptions();
		}
	}

	private void toDisplayBillForMyOrders(String name,int a) throws Exception{
		long amt = 0,sum=0;
		File file = new File("C:\\Users\\mlaks\\eclipse-workspace\\Dying_Monitroings\\BackEnd Data\\Orders\\"+name+".xls");
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n"+name+"'s Orders..!\n"+"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		if(file.exists()) {
			try {
				FileInputStream fis = new FileInputStream(file);
				Workbook workbook = new HSSFWorkbook(fis);
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				Iterator<Row> itr = sheet.iterator();
				System.out.println("<---------- Cost Perticulers ---------->\n"+"Amount for one single Varpu = 450/-\n"+"Amount for one KG Sapuri = 200/-\n"+"Amount for one KG Dupin = 120/-\n");
				System.out.println("\nDate of Orders\tVarpulu\t"+"Sapuri\t"+"Dupin\t"+"Amount");
				while(itr.hasNext()){
					Row row = itr.next();
					if(row.getRowNum()!=0) {
						amt = totalAmount(row.getCell(1).getStringCellValue(),row.getCell(2).getStringCellValue(),row.getCell(3).getStringCellValue());
						sum+=amt;
						System.out.println(row.getCell(0).getStringCellValue()+"\t"+row.getCell(1).getStringCellValue()+"\t"+row.getCell(2).getStringCellValue()+"\t"+row.getCell(3).getStringCellValue()+"\t"+amt/*+"\t"+row.getCell(3).getStringCellValue()*/);
					}
				}
				System.out.println("________________________________________________");
				System.out.println("Total Amount ----------------------->\t"+sum);
				workbook.close();
			}
			catch(Exception e) {
				System.out.println("Error in Excer Reader for customer to generating Bill.");
			}
		}
		else
			System.out.println("Customer file doesnot exists");
		if(a==1)
			customerOptions(name);
		else {
			AdminControl adminControl = new AdminControl();
			adminControl.adminOptions();
		}
	}

	public static long totalAmount(String countOfVarpulu, String sapuriKgs, String dupinKgs) {
		int varpulu = Integer.parseInt(countOfVarpulu);
		int sapuri = Integer.parseInt(sapuriKgs);
		int dupin = Integer.parseInt(dupinKgs);
		long amount = (varpulu*450)+(sapuri*200)+(dupin*120);
		return amount;
	}

	private void placeOrders(String name) throws Exception{
		LocalDate date = LocalDate.now();
		System.out.println("************ Give your order ************");
		System.out.println("Enter quantity of varpulu");
		NumberValidation numberValidation = new NumberValidation();
		String varpulu = numberValidation.enterNumber();
		System.out.println("Enter no of KGs of Sapuri");
		String sapuri = numberValidation.enterNumber();
		System.out.println("Enter no og KGs of dupin");
		String dupin = numberValidation.enterNumber();
		Orders orders = new Orders(date,varpulu,sapuri,dupin);
		orders.setDate(date);
		orders.setCountOfVarpulu(varpulu);
		orders.setSapuriKgs(sapuri);
		orders.setDupinKgs(dupin);
		
		//Printing your orders
		printOrderInfo(orders);
		
		List<Orders> list = new ArrayList<Orders>();
		DataCustomersOrders dataCustomersOrders = new DataCustomersOrders();
		DataOrders dataOrder = new DataOrders(); 
		list.add(orders);
		dataCustomersOrders.excelGenerate(orders, list, name);
		dataOrder.excelGenerate(orders, list, name);
		System.out.println("\n=========================================\n"+"\nYour order successfully placed.\n"+"\n=========================================\n");
		System.out.println("\n*****************************************\n"+"\nThank you for your order\n"+"\n*****************************************\n");
		
		System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"+"Redirecting to Home Page...!\n\n");
		
		customerOptions(name);
	}

	public static void printOrderInfo(Orders orders) {
		System.out.println("\n<--------- Yours order datails --------->");
		System.out.println("No of Varpulu		\t:\t "+ orders.getCountOfVarpulu());
		System.out.println("No of KGs of Sapuri	\t:\t "+orders.getSapuriKgs());
		System.out.println("No of Kgs of Dupin	\t:\t "+ orders.getDupinKgs()+"\n");
	}

}