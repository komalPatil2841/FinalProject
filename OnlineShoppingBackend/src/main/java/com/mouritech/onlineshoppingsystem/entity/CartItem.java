package com.mouritech.onlineshoppingsystem.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "cart_Items")
public class CartItem {

	@Id
	@Column(name = "Item_Id",nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;
	
	@Column(name = "Product_Id")
	@NotBlank(message = "Product Id is required")
	private String prodId;
	
	@Column(name = "Name")
	@NotBlank(message = "Product Name is required")
	private String pname;

	@Column(name = "Product_Price", nullable = false)
	@Min(100)
	@Max(100000)
	private BigDecimal price;

	@Column(name = "Quantity", nullable = false)
	@Min(1)
	@Max(1000)
	private int qty;
	
	@Column(name = "Total_Amount", nullable = false)
	@Min(1)
	private BigDecimal totalAmount;

	@ManyToOne( optional = false, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "CartId")
	private Cart cart;

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItem(Long itemId, @NotBlank(message = "Product Id is required") String prodId,
			@NotBlank(message = "Product Name is required") String pname, @Min(1) @Max(1000) BigDecimal price,
			@Min(1) @Max(1000) int qty, BigDecimal totalAmount, Cart cart) {
		super();
		this.itemId = itemId;
		this.prodId = prodId;
		this.pname = pname;
		this.price = price;
		this.qty = qty;
		this.totalAmount = totalAmount;
		this.cart = cart;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	
}
