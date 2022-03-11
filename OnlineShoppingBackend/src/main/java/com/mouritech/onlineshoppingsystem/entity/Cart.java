package com.mouritech.onlineshoppingsystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@Column(name = "cart_id")
	private String cartId;

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "customer_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Cart(String cartId, Customer customer) {
		super();
		this.cartId = cartId;
		this.customer = customer;
	}

	public Cart(Customer customer) {
		super();
		this.customer = customer;
	}

	public Cart() {
		super();
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", customer=" + customer + "]";
	}

	
	
}
