package Controls;

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

import controller.LoginCustomer;
import model.Orders;
import service.DataCustomersOrders;
import service.DataOrders;
import service.EmailValidation;
import service.MobileValidation;
import service.NameValidation;
import service.NumberValidation;
import service.PasswordValidation;
import service.UserNameValidation;

public class CustomerControl {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void customerOptions(String cusName) throws Exception{
		this.name = cusName;
		System.out.println("Please enter your choice");
		System.out.println("1. Orders\n2. Bill\n3. Edit My Profile\n4. Display My Profile\n5. Logout");
		NumberValidation numberValidation = new NumberValidation();
		int choice = Integer.parseInt(numberValidation.enterNumber());
		switch(choice) {
		case 1: 
			placeOrders(getName());
			break;
		case 2:
			generateBill(cusName);
			break;
		case 3:
			editProfile(getName());
			break;
		case 4:
			displayProfile(getName());
			break;
		case 5:
			logout();
			break;
		default:
			System.out.println("Please enter correct choice");
			customerOptions(getName());
		}
	}

	private void displayProfile(String name) throws Exception {
		String file = "C:\\Users\\mlaks\\OneDrive\\Desktop\\FSJ\\ProGrad_S-52\\S-2-2_Week-4\\BackEnd Data\\CustomerData.xls";
		int flag=0,j=0,rowNum=0;
		File checkFile = new File(file);
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
//					System.out.println("flag = "+flag);
					flag=0;
//					System.out.println("flag = "+flag);
					rowNum=j;
//					System.out.println("Current Row num: "+rowNum);
					break;
				}
			}
			if(flag==0) {
					Row row = sheet.getRow(rowNum);
					System.out.println("Name          \t:\t"+row.getCell(1));
					System.out.println("User Name     \t:\t"+row.getCell(2));
					System.out.println("Email ID      \t:\t"+row.getCell(3));
					System.out.println("Password      \t:\t"+row.getCell(4));
					System.out.println("Mobile Number \t:\t"+row.getCell(6));					
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

	private void editProfile(String name) throws Exception {
		String file = "C:\\Users\\mlaks\\OneDrive\\Desktop\\FSJ\\ProGrad_S-52\\S-2-2_Week-4\\BackEnd Data\\CustomerData.xls";
		int flag=0,j=0,rowNum=0;
		File checkFile = new File(file);
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
//					System.out.println("flag = "+flag);
					flag=0;
//					System.out.println("flag = "+flag);
					rowNum=j;
//					System.out.println("Current Row num: "+rowNum);
					break;
				}
			}
			if(flag==0) {
				Row row = sheet.getRow(rowNum);
				System.out.println("Before Name  = "+name);
				NumberValidation numberValidation = new NumberValidation();
//				System.out.println("Row number = "+rowNum);
				System.out.println("Enter no of requirement edits");
				int n = Integer.parseInt(numberValidation.enterNumber());
				for(int z=1;z<=n;z++) {
					int ch = editmore();
					switch(ch) {
					case 1:
						NameValidation nameValidation = new NameValidation();
						String editName = nameValidation.enterName();
						this.name = editName;
						System.out.println("After editing Name  = "+getName());
						Cell cellName = row.getCell(ch);
						row.removeCell(cellName);
						row.createCell(ch).setCellValue(editName);
						break;
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
						Cell cellConfirmPassword = row.getCell(ch+1);
						Cell cellPassword = row.getCell(ch);
						row.removeCell(cellPassword);
						row.removeCell(cellConfirmPassword);
						row.createCell(ch).setCellValue(password);
						row.createCell(ch+1).setCellValue(password);
						break;
					case 5:
						MobileValidation mobileValidation = new MobileValidation();
						String mobileNubmer = mobileValidation.enterMobileNumber();
						Cell cellMobileNumber = row.getCell(ch+1);
						row.removeCell(cellMobileNumber);
						row.createCell(ch+1).setCellValue(mobileNubmer);
						break;
					}
				}
			}
		out = new FileOutputStream(file);
		workbook.write(out);
		out.close();
		workbook.close();
		}
		catch(Exception e){
			System.out.println("Error in editing admin info in Customer controller.");
		}
		customerOptions(getName());
	}
	
	private int editmore() throws Exception{
		System.out.println("Please enter choice\n1. Name\n2. User Name\n3. Email ID\n4. Password\n5. Mobile Number");
		NumberValidation numberValidation = new NumberValidation();
		int c = Integer.parseInt(numberValidation.enterNumber());
//		System.out.println("cell number = "+c);
		return c;
	}

	private void logout() throws Exception{
		System.out.println("\n=========================================\n"+"\nYou Successfully Logged out\n"+"\n=========================================\n");
		System.out.println("\nRedirecting to Login Page..!\n");
		LoginCustomer loginCustomer = new LoginCustomer();
		loginCustomer.customerLogin();
	}

	private void generateBill(String name) throws Exception{
		cusBillGeneration(name);
	}

	public void cusBillGeneration(String name) throws Exception {
		long amt = 0,sum=0;
		try {
		File file = new File("C:\\Users\\mlaks\\OneDrive\\Desktop\\FSJ\\ProGrad_S-52\\S-2-2_Week-4\\BackEnd Data\\Orders\\"+name+".xls");
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n"+getName()+"'s Orders..!\n"+"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		FileInputStream fis = new FileInputStream(file);
		Workbook workbook = new HSSFWorkbook(fis);
		HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
		
		Iterator<Row> itr = sheet.iterator();

		System.out.println("<---------- Cost Perticulers ---------->\n"+"Amount for one single Varpu = 450/-\n"+"Amount for one KG Sapuri = 200/-\n"+"Amount for one KG Dupin = 120/-\n");
		System.out.println("\nDate of Orders\tVarpulu\t"+"Sapuri\t"+"Dupin\t"+"Amount");
		while(itr.hasNext()) 
		{
			Row row = itr.next();
			if(row.getRowNum()!=0) {
//				System.out.println("Row number = "+row.getRowNum());
				amt = totalAmount(row.getCell(1).getStringCellValue(),row.getCell(2).getStringCellValue(),row.getCell(3).getStringCellValue());
				
//				System.out.println("amount = "+amt);
				sum+=amt;
//				System.out.println("sum = "+ sum);
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
		
		customerOptions(getName());
	}

	public static void printOrderInfo(Orders orders) {
		System.out.println("<--------- Yours order datails --------->");
		System.out.println("No of Varpulu: "+ orders.getCountOfVarpulu());
		System.out.println("No of KGs of Sapuri: "+orders.getSapuriKgs());
		System.out.println("No of Kgs of Dupin: "+ orders.getDupinKgs());
	}

}