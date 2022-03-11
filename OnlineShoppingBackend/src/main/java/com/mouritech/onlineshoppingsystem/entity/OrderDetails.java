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
@Table(name = "order_details")
public class OrderDetails {

	@Id
	@Column(name = "order_id", length = 64)
	private String orderDetailsId;

	@Column(name = "unit_price", nullable = false)
	private double price;

	@Column(name = "quantity")
	private long quantity;

	@Column(name = "total_price")
	private double totalPrice;

	@Column(name = "prodId")
	private String prodId;

	@Column(name = "prodName")
	private String prodName;

	@Column(name = "comments")
	private String comments;

	@ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "orderId")
	private Order order;

	public OrderDetails() {
		super();
	}

	public String getOrderDetailsId() {
		return orderDetailsId;
	}

	public void setOrderDetailsId(String orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public OrderDetails(double price, long quantity, double totalPrice, String prodId, String prodName, String comments,
			Order order) {
		super();
		this.price = price;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.prodId = prodId;
		this.prodName = prodName;
		this.comments = comments;
		this.order = order;
	}

	public OrderDetails(String orderDetailsId, double price, long quantity, double totalPrice, String prodId,
			String prodName, String comments, Order order) {
		super();
		this.orderDetailsId = orderDetailsId;
		this.price = price;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.prodId = prodId;
		this.prodName = prodName;
		this.comments = comments;
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderDetailsId=" + orderDetailsId + ", price=" + price + ", quantity=" + quantity
				+ ", totalPrice=" + totalPrice + ", prodId=" + prodId + ", prodName=" + prodName + ", comments="
				+ comments + ", order=" + order + "]";
	}

}
