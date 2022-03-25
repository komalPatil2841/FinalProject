package com.mouritech.onlineshoppingsystem.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
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
@Table(name = "order_details")
public class OrderDetails {

	@Id
	@Column(name = "order_id", length = 64)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderDetailsId;

	@Column(name = "unit_price", nullable = false)
	private BigDecimal price;

	@Column(name = "quantity")
	@Min(1)
	@Max(1000)
	private BigDecimal quantity;

	@Column(name = "total_price")
	private BigDecimal totalPrice;

	@Column(name = "prodId")
	@NotBlank(message = "Product Id required")
	private String prodId;

	@Column(name = "prodName")
	@NotBlank(message = "Product Name is required")
	private String prodName;

	@Column(name = "comments")
	@NotBlank()
	private String comments;

	@ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "orderId")
	private Order order;

	public OrderDetails() {
		super();
	}

	

	public OrderDetails(Long orderDetailsId, BigDecimal price, BigDecimal quantity, BigDecimal totalPrice,
			String prodId, String prodName, @NotBlank String comments, Order order) {
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



	public Long getOrderDetailsId() {
		return orderDetailsId;
	}



	public void setOrderDetailsId(Long orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}



	public BigDecimal getPrice() {
		return price;
	}



	public void setPrice(BigDecimal price) {
		this.price = price;
	}



	public BigDecimal getQuantity() {
		return quantity;
	}



	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}



	public BigDecimal getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
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



	@Override
	public String toString() {
		return "OrderDetails [orderDetailsId=" + orderDetailsId + ", price=" + price + ", quantity=" + quantity
				+ ", totalPrice=" + totalPrice + ", prodId=" + prodId + ", prodName=" + prodName + ", comments="
				+ comments + ", order=" + order + "]";
	}

}
