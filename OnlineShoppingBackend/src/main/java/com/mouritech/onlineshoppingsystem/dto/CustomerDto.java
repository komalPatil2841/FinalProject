package com.mouritech.onlineshoppingsystem.dto;



public class CustomerDto {

	private String custEmail;  
	private String password;
	
	public CustomerDto(String custEmail, String password) {
		super();
		this.custEmail = custEmail;
		this.password = password;
	}
	public CustomerDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
