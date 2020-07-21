package service;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidation {
	public String enterPassword() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter password");
		String password = sc.nextLine();
		System.out.println("Re-Enter password");
		String confirmPassword = sc.next();
		boolean isValidPassword = isPassword(password);
		boolean isValidConfirmPassword = isPassword(confirmPassword);
		boolean isSamePassword = password.matches(confirmPassword);
		if (isValidPassword && isValidConfirmPassword && isSamePassword) {
			return password;
		}
		else {
			System.out.println("Please try with another password.");
			return enterPassword();
		}
	}
	public static boolean isPassword(String password) {
		// Regex to check valid password. 
        String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[@#$%^&+=])"
                       + "(?=\\S+$).{8,20}$"; 
  
        // Compile the ReGex 
        Pattern p = Pattern.compile(regex); 
  
        // If the password is empty 
        // return false 
        if (password == null) { 
            return false; 
        } 
  
        // Pattern class contains matcher() method 
        // to find matching between given password 
        // and regular expression. 
        Matcher m = p.matcher(password); 
  
        // Return if the password 
        // matched the ReGex 
        return m.matches(); 
//		return false;
		
	}
}
