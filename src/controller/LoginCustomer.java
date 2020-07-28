package controller;

import java.util.Scanner;

import service.ExcelReaderForLoginCustomer;
import service.NumberValidation;



public class LoginCustomer {
	public void customerLogin() throws Exception{
		System.out.println("\nPlease select your choice\n1. Alredy Existing Customer\n2. Registration for New Customer\n3. Exit");
		NumberValidation numberValidation = new NumberValidation();
		int choice = Integer.parseInt(numberValidation.enterNumber());
		switch(choice){
		case 1:
			login();
			break;
		case 2:
			register();
			break;
		case 3:
			System.exit(0);
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
			ExcelReaderForLoginCustomer details = new ExcelReaderForLoginCustomer();
			String name = details.findName(userName);
			if(details.validateCredentials(userName, password)) {
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
