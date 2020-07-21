package Controls;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import controller.LoginAdmin;
import service.NumberValidation;
import utility.Accounts;
import utility.AdminPerticulars;
import utility.Bills;
import utility.CheckOrders;
import utility.WorkersPerticulars;

public class AdminControl {
	public void adminOptions() throws Exception{
		System.out.println("Please enter your choice\n");
		System.out.println("1. Admin\n2. Check Orders\n"+"3. Workers\n"+"4. Accounts\n"+"5. Bills\n"+"6. Customers Info\n"+"7. Logout");
		NumberValidation num =new NumberValidation();
		int choice = Integer.parseInt(num.enterNumber());
		switch(choice) {
		case 1:
			AdminPerticulars adminPerticulars = new AdminPerticulars(); // All Done 
			adminPerticulars.admins();
			break;
		case 2: 
			CheckOrders checkOrders = new CheckOrders(); // printing orders all complete 
			checkOrders.checkDailyOrders();
			break;
		case 3:
			WorkersPerticulars workersPerticulars = new WorkersPerticulars(); // All done 
			workersPerticulars.workers();
			break;
		case 4:
			Accounts accounts = new Accounts(); // yet not started
			accounts.accountsOfFirm();
			break;
		case 5:
			Bills bills = new Bills(); // all done
			bills.billsGenerating();
			break;
		case 6:
			customerInfo(); // Done
			break;
		case 7:
			logout(); // Done
			break;
		default:
			System.out.println("Please enter valid choice...!");
			adminOptions();
		}
	}
	
	private void customerInfo() throws Exception {
		File file = new File("C:\\Users\\mlaks\\OneDrive\\Desktop\\FSJ\\ProGrad_S-52\\S-2-2_Week-4\\BackEnd Data\\CustomerData.xls");
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"+"\n Today's Orders..!\n"+"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		FileInputStream fis = new FileInputStream(file);
		Workbook workbook = new HSSFWorkbook(fis);
		HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
		
		Iterator<Row> itr = sheet.iterator();
		while(itr.hasNext()) 
		{
			Row row = itr.next();
			System.out.println(row.getCell(0).getStringCellValue()+"\t\t\t"+row.getCell(1).getStringCellValue()+"\t\t\t"+row.getCell(2).getStringCellValue()+"\t\t\t"+row.getCell(3).getStringCellValue()+"\t\t\t"+row.getCell(6).getStringCellValue());
		}
		workbook.close();
		
	}
	private void logout() throws Exception {	
		System.out.println("\n=========================================\n"+"\nYou Successfully Logged out\n"+"\n=========================================\n");
		System.out.println("\nRedirecting to Login Page..!\n");
		LoginAdmin loginAdmin = new LoginAdmin();
		loginAdmin.adminLogin();
	}	
}
