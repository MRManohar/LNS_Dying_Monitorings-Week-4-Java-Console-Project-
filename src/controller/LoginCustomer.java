package controller;

import java.util.Scanner;

import Controls.CustomerControl;
import service.ExcelReaderForLoginCusAdmin;
import service.NumberValidation;



public class LoginCustomer {
	public void customerLogin() throws Exception{
		System.out.println("1. Alredy Existing Customer\n2. Registration for New Customer");
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
			customerLogin();
		}
	}

	private void register() throws Exception {
		RegistrationCustomer registrationCustomer = new RegistrationCustomer ();
		registrationCustomer.customerRegister(); 
	}

	private void login() throws Exception{
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter user name");
			String userName = sc.nextLine();
			System.out.println("Enter password");
			String password = sc.nextLine();
			ExcelReaderForLoginCusAdmin details = new ExcelReaderForLoginCusAdmin();
			String name = details.findName(userName);
			if(details.validateCredentials(userName, password, 1)) {
				System.out.println("\n=========================================\n"+"\nLogged In Successfully\n"+"\n=========================================\n");
				CustomerControl customerControl = new CustomerControl();
				customerControl.customerOptions(name);
			}
			else{
				System.out.println("\nPlease enter correct Credentials\n");
				customerLogin();
			}
			sc.close();
		}
		catch(Exception e) {
			System.out.println("Error in LoginCustomer");
		}		
	}
}
