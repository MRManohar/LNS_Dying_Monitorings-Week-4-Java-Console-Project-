package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Registrations;
import service.DataCustomer;
import service.EmailValidation;
import service.MobileValidation;
import service.NameValidation;
import service.PasswordValidation;
import service.UserNameValidation;

public class RegistrationCustomer {
	public void customerRegister() throws Exception{
		LocalDateTime dateTime = LocalDateTime.now();
		try {
			System.out.println("--------------- Helloo..! ---------------\n"+"\n#########################################\n"+"\nWelcome to LAKSHMI NARASIMHA SWAMI DYINGS\n"+"\n#########################################\n"+"\n*****************************************\n"+"\nYou are registering as a Customer\n"+"\n*****************************************\n");
//			Enter Name using Name Validation
			NameValidation nameValidation = new NameValidation();
			String name = nameValidation.enterName();
//			Enter User Name using UserName Validation
			UserNameValidation userNameValidation = new UserNameValidation();
			String userName = userNameValidation.enterUserName();
//			Enter Mobile Number using Mobile Number Validation 
			MobileValidation mobileValidation = new MobileValidation();
			String mobileNumber = mobileValidation.enterMobileNumber();
//			Enter email ID using email validation
			EmailValidation emailValidation = new EmailValidation();
			String email = emailValidation.enterEmail();
//			Enter password and Confirm password using password validation
			PasswordValidation passwordValidation = new PasswordValidation();
			String password = passwordValidation.enterPassword();
			String confirmPassword = password;
//			Passing values using Registrations constructor in model package
			Registrations registrations = new Registrations(dateTime,name, userName, mobileNumber, email, password, confirmPassword);
			registrations.setDateTime(dateTime);
			registrations.setName(name);
			registrations.setUserName(userName);
			registrations.setMobileNumber(mobileNumber);
			registrations.setEmail(email);
			registrations.setPassword(password);
			registrations.setPassword(confirmPassword);
			
//			Print registrations data
			printCustomerInfo(registrations);
//			Store the date using LIST
			List<Registrations> list = new ArrayList<Registrations>();
			DataCustomer dataCustomer = new DataCustomer();
			list.add(registrations);
//			Export to excel
			dataCustomer.excelGenerate(registrations, list);
			System.out.println("\n=========================================\n"+"\nRegistration Success...!\n"+"\n=========================================\n"+"\nRedirecting to Login\n");
			LoginCustomer loginCustomer = new LoginCustomer();
			loginCustomer.customerLogin();
		}
		catch(Exception e) {
			System.out.println("Error occured at RegistrationCustomer in Registration Package");
		}
	}

	public static void printCustomerInfo(Registrations registrations) {
		System.out.println("Your datails");
		System.out.println("Your name: "+registrations.getName());
		System.out.println("Your userName: "+registrations.getUserName());
		System.out.println("Your mobile number: "+registrations.getMobileNumber());
		System.out.println("Your emial address: "+registrations.getEmail());
		System.out.println("Check your password: "+registrations.getPassword());
	}
}
