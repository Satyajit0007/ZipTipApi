package com.masai.model;

import lombok.Data;

@Data
public class LogInDTO {
	private String MobileNo;
	private String password;
	private String role;
	
	
	public LogInDTO(String mobileNo, String password, String role) {
		super();
		MobileNo = mobileNo;
		this.password = password;
		this.role = role;
	}


	public String getMobileNo() {
		return MobileNo;
	}


	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
