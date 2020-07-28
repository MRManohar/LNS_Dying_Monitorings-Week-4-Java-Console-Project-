package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import service.NumberValidation;
import utility.Accounts;
import utility.Bills;
import utility.CheckOrders;
import utility.WorkersPerticulars;

public class AdminControl {
	public void adminOptions() throws Exception{
		System.out.println("Please enter your choice");
		System.out.println("1. Check Orders\n"+"2. Workers\n"+"3. Accounts\n"+"4. Bills\n"+"5. Customers Info\n"+"6. Logout");
		
		// selecting different options for admin
		NumberValidation num =new NumberValidation();
		int choice = Integer.parseInt(num.enterNumber());
		
		switch(choice) {
		case 1: 
			// for checking orders
			CheckOrders checkOrders = new CheckOrders(); // printing orders all complete 
			checkOrders.checkDailyOrders();
			break;
		case 2:
			// for workers section
			WorkersPerticulars workersPerticulars = new WorkersPerticulars();  
			workersPerticulars.workers();
			break;
		case 3:
			// Accounting section
			Accounts accounts = new Accounts(); // yet not started
			accounts.accountsOfFirm();
			break;
		case 4:
			// Billing Section
			Bills bills = new Bills();
			bills.billsGenerating();
			break;
		case 5:
			// Customers information section
			customerInfo();
			break;
		case 6:
			//logout section
			logout(); 
			break;
		default:
			System.out.println("Please enter valid choice...!");
			adminOptions();
		}
	}
	
	private void customerInfo() throws Exception {
		try {
			// defining file path
			File file = new File("C:\\Users\\mlaks\\eclipse-workspace\\Dying_Monitroings\\BackEnd Data\\CustomerData.xls");
			//checking file whether file existing or not
			if(file.exists()) {
				System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"+"\n Avaailable Custoemrs..!\n"+"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
				FileInputStream fis = new FileInputStream(file);
				Workbook workbook = new HSSFWorkbook(fis);
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				FileOutputStream out;
				Iterator<Row> itr = sheet.iterator();
				while(itr.hasNext()){
					Row row = itr.next();
					//Printing Data
					System.out.println(row.getCell(0).getStringCellValue()+"\t"+row.getCell(1).getStringCellValue()+"\t"+row.getCell(2).getStringCellValue()+"\t"+row.getCell(3).getStringCellValue()+"\t"+row.getCell(5).getStringCellValue());
				}
				out = new FileOutputStream(file);
				workbook.write(out);
				out.close();
				workbook.close();
			}
			else
				System.out.println("Customers file does not exists");
		}
		catch(Exception e) {
			System.out.println("Error in displaying customers info");
		}
		adminOptions();
	}
	private void logout() throws Exception {	
		//Loging out
		System.out.println("\n=========================================\n"+"\nYou Successfully Logged out\n"+"\n=========================================\n");
		System.out.println("\nPlease select your choice\n1. Login\n2. Exit");
		NumberValidation num =new NumberValidation();
		int choice = Integer.parseInt(num.enterNumber());
		
		switch(choice) {
		case 1:
			System.out.println("\nRedirecting to Login Page..!\n");
			LoginAdmin loginAdmin = new LoginAdmin();
			loginAdmin.adminLogin();
			break;
		case 2:
			System.exit(0);
			break;
		default:
			System.out.println("\nPlease select valid option\n");
			logout();
		}
	}	
}
