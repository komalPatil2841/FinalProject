package com.mouritech.onlineshoppingsystem.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Products")
public class Product {
	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id", length = 64)
	private String prodId;
	
	 
	@Column(name = "Name", length = 255, nullable = false)
	@NotBlank(message = "Product Name is required")
	private String prodName;

	@Column(name = "Price", nullable = false)
	@NotNull
	@Min(1)
	@Max(20000)
	private BigDecimal price;
	
	 @NotBlank(message = "Description is required")
	@Column(name = "Description", nullable = false)
	private String description;
	
	@Column(name = "available_quantity", nullable = false)
	@NotNull
	@Min(1)
	@Max(100)
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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



	public Product(@NotBlank(message = "Product Name is required") String prodName,
			@NotNull @Min(1) @Max(20000) BigDecimal price,
			@NotBlank(message = "Description is required") String description,
			@NotNull @Min(1) @Max(100) int availableQuantity, Category category) {
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
