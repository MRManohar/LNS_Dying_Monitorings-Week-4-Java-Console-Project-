package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import Controls.AdminControl;
import controller.RegistrationAdmin;
import service.EmailValidation;
import service.MobileValidation;
import service.NameValidation;
import service.NumberValidation;
import service.PasswordValidation;
import service.UserNameValidation;

public class AdminPerticulars {
	public void admins() throws Exception{
		System.out.println("Please select your choice\n1. Create new Admin\n2. Available Admins\n3. Edit Admin Info\n4. Delete Admin");
		NumberValidation numberValidation = new NumberValidation();
		int ch = Integer.parseInt(numberValidation.enterNumber());
		switch(ch) {
		case 1:
			createAdmin();
			break;
		case 2:
			availableAdmins();
			break;
		case 3:
			editAdminInfo();
			break;
		case 4:
			deleteAdmin();
			break;
		default:
			System.out.println("Please enter valid choice.");
			admins();
		}
	}

	private void deleteAdmin() throws Exception{
		int z=-1;
		try {
			File file = new File("C:\\Users\\mlaks\\OneDrive\\Desktop\\FSJ\\ProGrad_S-52\\S-2-2_Week-4\\BackEnd Data\\AdminData.xls");
			System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"+"\n Available Admin's..!\n"+"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			FileInputStream fis = new FileInputStream(file);
			Workbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
			FileOutputStream out;
			
			Iterator<Row> itr = sheet.iterator();
			while(itr.hasNext()) 
			{
				z++;
				Row row = itr.next();
				if(row.getRowNum()!=0)
					System.out.println(z+" "+row.getCell(1).getStringCellValue());
				else
					System.out.println("  "+row.getCell(1).getStringCellValue());
			}
			NumberValidation numberValidation = new NumberValidation();
			int rowNum = Integer.parseInt(numberValidation.enterNumber());
//			System.out.println("Row number = "+rowNum);
			Row row = sheet.getRow(rowNum);
			sheet.removeRow(row);
			sheet.removeRowBreak(rowNum);

			out = new FileOutputStream(file);
			workbook.write(out);
			out.close();
			workbook.close();
		}
		catch(Exception e) {
			System.out.println("Error in deleteAdmin in admin perticulars");
		}
		redirectAdminPerticulars();
	}

	private void editAdminInfo() throws Exception{
		int z=-1;
		try {
		File file = new File("C:\\Users\\mlaks\\OneDrive\\Desktop\\FSJ\\ProGrad_S-52\\S-2-2_Week-4\\BackEnd Data\\AdminData.xls");
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"+"\n Available Admin's..!\n"+"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		FileInputStream fis = new FileInputStream(file);
		Workbook workbook = new HSSFWorkbook(fis);
		HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
		FileOutputStream out;
		
		Iterator<Row> itr = sheet.iterator();
		while(itr.hasNext()) 
		{
			z++;
			Row row = itr.next();
			if(row.getRowNum()!=0)
				System.out.println(z+" "+row.getCell(1).getStringCellValue());
			else
				System.out.println("  "+row.getCell(1).getStringCellValue());
		}
		NumberValidation numberValidation = new NumberValidation();
		int rowNum = Integer.parseInt(numberValidation.enterNumber());
//		System.out.println("Row number = "+rowNum);
		Row row = sheet.getRow(rowNum);
		System.out.println("Enter no of requirement edits");
		int n = Integer.parseInt(numberValidation.enterNumber());
		for(int i=1;i<=n;i++) {
			int ch = editmore();
			switch(ch) {
			case 1:
				NameValidation nameValidation = new NameValidation();
				String name = nameValidation.enterName();
				Cell cellName = row.getCell(ch);
				row.removeCell(cellName);
				row.createCell(ch).setCellValue(name);
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

				Cell cellConfirmPassword = row.getCell(ch+1);
				PasswordValidation passwordValidation = new PasswordValidation();
				String password = passwordValidation.enterPassword();
				Cell cellPassword = row.getCell(ch);
				row.removeCell(cellPassword);
				row.removeCell(cellConfirmPassword);
				row.createCell(ch).setCellValue(password);
				row.createCell(ch+1).setCellValue(password);
				break;
			case 5:
				MobileValidation mobileValidation = new MobileValidation();
				String mobileNubmer = mobileValidation.enterMobileNumber();
				Cell cellMobileNumer = row.getCell(ch+1);
				row.removeCell(cellMobileNumer);
				row.createCell(ch+1).setCellValue(mobileNubmer);
				break;
			}
		}

		out = new FileOutputStream(file);
		workbook.write(out);
		out.close();
		workbook.close();
		}
		catch(Exception e){
			System.out.println("Error in editing admin info in admin perticulars.");
		}
		redirectAdminPerticulars();
	}

	private int editmore() throws Exception{
		System.out.println("Please enter choice\n1. Name\n2. User Name\n3. Email ID\n4. Password\n5. Mobile Number");
		NumberValidation numberValidation = new NumberValidation();
		int c = Integer.parseInt(numberValidation.enterNumber());
//		System.out.println("cell number = "+c);
		return c;
	}

	private void availableAdmins() throws Exception{
		try {
		File file = new File("C:\\Users\\mlaks\\OneDrive\\Desktop\\FSJ\\ProGrad_S-52\\S-2-2_Week-4\\BackEnd Data\\AdminData.xls");
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"+"\n Available Admin's..!\n"+"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
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
		catch(Exception e) {
			System.out.println("Error in Available admins in Admin Perticulars");
		}
		redirectAdminPerticulars();
	}

	private void createAdmin() throws Exception{
		RegistrationAdmin registrationAdmin = new RegistrationAdmin();
		registrationAdmin.adminRegister(1);
		System.out.println("Redirecting to Main Section -->");
		AdminControl adminControl = new AdminControl();
		adminControl.adminOptions();
	}
	public void redirectAdminPerticulars() throws Exception{
		System.out.println("\n\nRedirect to ---> \n1. Admin Section\n2. Main Section");
		NumberValidation numberValidation = new NumberValidation();
		int c = Integer.parseInt(numberValidation.enterNumber());
		switch(c) {
		case 1:
			admins();
			break;
		case 2:
			AdminControl adminControl = new AdminControl();
			adminControl.adminOptions();
			break;
		}
	}
}
