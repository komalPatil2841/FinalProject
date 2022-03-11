package com.mouritech.onlineshoppingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
  //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id",length = 64)
    private String custId;
    
    @Column(name = "customer_name")
	private String custName;
    
    @Column(name = "customer_email")
	private String custEmail;
    
    @Column(name = "customer_phonenumber")
	private long custPhn;
    
    @Column(name = "customer_address")
	private String custAddress;
    
    @Column(name = "customer_password")
	private String password;
//    @Column(name = "role")
//    private String role;
//	
//	
//	@Override
//	public String toString() {
//		return "Customer [custId=" + custId + ", custName=" + custName + ", custEmail=" + custEmail + ", custPhn="
//				+ custPhn + ", custAddress=" + custAddress + ", password=" + password + ", role=" + role + "]";
//	}
//	public String getRole() {
//		return role;
//	}
//	public void setRole(String role) {
//		this.role = role;
//	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String string) {
		this.custId = string;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public long getCustPhn() {
		return custPhn;
	}
	public void setCustPhn(long custPhn) {
		this.custPhn = custPhn;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Customer() {
		super();

	}
	public Customer(String custId, String custName, String custEmail, long custPhn, String custAddress,
			String password) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.custEmail = custEmail;
		this.custPhn = custPhn;
		this.custAddress = custAddress;
		this.password = password;
	}
	public Customer(String custName, String custEmail, long custPhn, String custAddress, String password) {
		super();
		this.custName = custName;
		this.custEmail = custEmail;
		this.custPhn = custPhn;
		this.custAddress = custAddress;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", custEmail=" + custEmail + ", custPhn="
				+ custPhn + ", custAddress=" + custAddress + ", password=" + password + "]";
	}
	
	
}
