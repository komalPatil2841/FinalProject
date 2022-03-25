package com.mouritech.onlineshoppingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@Column(name = "cart_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cartId;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}



	public Cart() {
		super();
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + "]";
	}

	
	
}
