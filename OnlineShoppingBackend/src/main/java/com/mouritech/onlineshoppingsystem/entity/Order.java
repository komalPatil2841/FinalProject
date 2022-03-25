package com.mouritech.onlineshoppingsystem.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "Orders")
public class Order {

	@Id
	@Column(name = "order_id", length = 64)
	private String orderId;
	
	@Column(name = "amount")
	@Min(100)
	@Max(50000)
	private BigDecimal amount;
	
	@Column(name = "shipping_address")
	@NotBlank(message = "Address is required")
	private String  shippingAddress;
	
	@Column(name = "ordered_on")
	@LastModifiedDate
	private LocalDate orderedOn;
	
	@Column(name = "ordered_status")
	@NotBlank(message = "Status is required")
	private String orderStatus;

	@ManyToOne(optional = false,fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private User user;



	public Order() {
		
	}



	public String getOrderId() {
		return orderId;
	}



	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}



	public BigDecimal getAmount() {
		return amount;
	}



	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}



	public String getShippingAddress() {
		return shippingAddress;
	}



	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}



	public LocalDate getOrderedOn() {
		return orderedOn;
	}



	public void setOrderedOn(LocalDate orderedOn) {
		this.orderedOn = orderedOn;
	}



	public String getOrderStatus() {
		return orderStatus;
	}



	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}







	public Order(@Min(100) @Max(50000) BigDecimal amount,
			@NotBlank(message = "Address is required") String shippingAddress, LocalDate orderedOn,
			@NotBlank(message = "Status is required") String orderStatus, User user) {
		super();
		this.amount = amount;
		this.shippingAddress = shippingAddress;
		this.orderedOn = orderedOn;
		this.orderStatus = orderStatus;
		this.user = user;
	}



	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", amount=" + amount + ", shippingAddress=" + shippingAddress
				+ ", orderedOn=" + orderedOn + ", orderStatus=" + orderStatus + ", user=" + user + "]";
	}



}
