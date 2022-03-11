package com.mouritech.onlineshoppingsystem.entity;

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
@Table(name = "cart_Items")
public class CartItem {

	@Id
	@Column(name = "Item_Id")
	private String itemId;

	@Column(name = "Product_Id")
	private String prodId;

	@Column(name = "Name")
	private String pname;

	@Column(name = "Product_Price", nullable = false)
	private float price;

	@Column(name = "Quantity", nullable = false)
	private int qty;

	@Column(name = "Total", nullable = false)
	private int total;

	@ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "CartId")
	private Cart cart;

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public CartItem(String itemId, String prodId, String pname, float price, int qty, int total, Cart cart) {
		super();
		this.itemId = itemId;
		this.prodId = prodId;
		this.pname = pname;
		this.price = price;
		this.qty = qty;
		this.total = total;
		this.cart = cart;
	}

	public CartItem(String prodId, String pname, float price, int qty, int total) {
		super();
		this.prodId = prodId;
		this.pname = pname;
		this.price = price;
		this.qty = qty;
		this.total = total;
	}

}
