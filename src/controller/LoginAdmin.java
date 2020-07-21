package controller;

import java.util.Scanner;

import Controls.AdminControl;
import service.ExcelReaderForLoginCusAdmin;
import service.NumberValidation;


public class LoginAdmin {
	public void adminLogin() throws Exception{
		System.out.println("1. Alredy Existing Admin\n2. Registration for New Admin");
		NumberValidation numberValidation = new NumberValidation();
		int choice = Integer.parseInt(numberValidation.enterNumber());
		switch(choice){
		case 1:
			login();
			break;
		case 2:
			register();
			break;
		default:
			System.out.println("Please select correct option.");
			adminLogin();
		}
	}

	private void register() throws Exception {
		RegistrationAdmin registrationAdmin = new RegistrationAdmin();
		registrationAdmin.adminRegister(0); 
	}

	private void login() throws Exception {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter user name");
			String userName = sc.nextLine();
			System.out.println("Enter password");
			String password = sc.nextLine();
			ExcelReaderForLoginCusAdmin details = new ExcelReaderForLoginCusAdmin();
			if(details.validateCredentials(userName, password, 0)) {
				System.out.println("\n=========================================\n"+"\nLogged In Successfully\n"+"\n=========================================\n");
				AdminControl adminControl = new AdminControl();
				adminControl.adminOptions();
			}
			else{
				System.out.println("\nPlease enter correct Credentials\n");
				adminLogin();
			}
			sc.close();
		}
		catch(Exception e) {
			System.out.println("Error in AdminLogin");
		}
	}
}
