package com.mouritech.onlineshoppingsystem.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Orders")
public class Order {

	@Id
	@Column(name = "order_id", length = 64)
	private String orderId;
	
	@Column(name = "amount")
	private float amount;
	
	@Column(name = "shipping_address")
	private String  shippingAddress;
	
	@Column(name = "ordered_on")
	private LocalDate orderedOn;
	
	@Column(name = "ordered_status")
	private String orderStatus;

	@ManyToOne(cascade = CascadeType.ALL,optional = false,fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "custId")
	private Customer customer;



	public Order() {
		
	}



	public String getOrderId() {
		return orderId;
	}



	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}



	public float getAmount() {
		return amount;
	}



	public void setAmount(float amount) {
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



	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public Order(float amount, String shippingAddress, LocalDate orderedOn, String orderStatus) {
		super();
		this.amount = amount;
		this.shippingAddress = shippingAddress;
		this.orderedOn = orderedOn;
		this.orderStatus = orderStatus;
	}



	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", amount=" + amount + ", shippingAddress=" + shippingAddress
				+ ", orderedOn=" + orderedOn + ", orderStatus=" + orderStatus + ", customer=" + customer + "]";
	}



}
