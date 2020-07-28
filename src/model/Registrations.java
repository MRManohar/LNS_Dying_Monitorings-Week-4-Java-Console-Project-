package model;

import java.time.LocalDateTime;

public class Registrations {
	private LocalDateTime dateTime;
	private String name;
	private String userName;
	private String mobileNumber;
	private String email;
	private String password;
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public Registrations(LocalDateTime dateTime,String name, String userName, String mobileNumber, String email, String password) {
		this.dateTime = dateTime;
		this.name = name;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;	
	}
}
