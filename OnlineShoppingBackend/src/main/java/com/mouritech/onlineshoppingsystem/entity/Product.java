package com.mouritech.onlineshoppingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Products")
@EntityListeners(AuditingEntityListener.class)
public class Product {
	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id", length = 64)
	private String prodId;

	@Column(name = "Name", length = 255, nullable = false)
	private String prodName;

	@Column(name = "Price", nullable = false)
	private double price;

	@Column(name = "Description", nullable = false)
	private String description;

	@Column(name = "available_quantity", nullable = false)
	private int availableQuantity;

	public Product() {

	}

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "category_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public void setProdName(String name) {
		this.prodName = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double d) {
		this.price = d;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public Product(String prodId, String prodName, float price, String description, int availableQuantity,
			Category category) {
		super();
		this.prodId = prodId;
		this.prodName = prodName;
		this.price = price;
		this.description = description;
		this.availableQuantity = availableQuantity;
		this.category = category;
	}

	public Product(String prodName, float price, String description, int availableQuantity, Category category) {
		super();
		this.prodName = prodName;
		this.price = price;
		this.description = description;
		this.availableQuantity = availableQuantity;
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [prodId=" + prodId + ", prodName=" + prodName + ", price=" + price + ", description="
				+ description + ", availableQuantity=" + availableQuantity + ", category=" + category + "]";
	}

}
