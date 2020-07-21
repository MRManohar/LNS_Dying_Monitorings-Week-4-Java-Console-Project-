package controller;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;


import model.Registrations;
import service.DataAdmin;
import service.EmailValidation;
import service.MobileValidation;
import service.NameValidation;
import service.PasswordValidation;
import service.UserNameValidation;
import utility.AdminPerticulars;


public class RegistrationAdmin {
	public void adminRegister(int n) throws Exception{
		LocalDateTime dateTime = LocalDateTime.now();
		try {
			System.out.println("--------------- Helloo..! ---------------\n"+"\n#########################################\n"+"\nWelcome to LAKSHMI NARASIMHA SWAMI DYINGS\n"+"\n#########################################\n"+"\n*****************************************\n"+"\nYou are registering as an Adminstrator\n"+"\n*****************************************\n");
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
			printAdminInfo(registrations);
//			Store the date using LIST
			List<Registrations> list = new ArrayList<Registrations>();
			DataAdmin excel = new DataAdmin();
			list.add(registrations);
//			Export to excel
			excel.excelGenerate(registrations, list); 
			System.out.println("\n=========================================\n"+"\nRegistration Success...!\n"+"\n=========================================\n");
			if(n==0) {
				System.out.println("\nRedirecting to Login\n");
				LoginAdmin loginAdmin = new LoginAdmin();
				loginAdmin.adminLogin();
			}
			else if(n == 1) {
				AdminPerticulars adminPerticulars = new AdminPerticulars();
				adminPerticulars.redirectAdminPerticulars();
			}
		}
		catch(Exception e) {
			System.out.println("Error occured at RegistrationAdmin in Registration Package");
		}
		
		
	}
		
	
	public static void printAdminInfo(Registrations registrations) {
		System.out.println("<------ Your datails ------>");
		System.out.println("Your name: "+registrations.getName());
		System.out.println("Your userName: "+registrations.getUserName());
		System.out.println("Your mobile number: "+registrations.getMobileNumber());
		System.out.println("Your emial address: "+registrations.getEmail());
		System.out.println("Check your password: "+registrations.getPassword());
	}
	
}
