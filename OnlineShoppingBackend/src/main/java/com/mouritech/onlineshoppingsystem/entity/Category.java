package com.mouritech.onlineshoppingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@Column(name = "category_id", length = 64)
	private String catId;

	@Column(name = "category_name")
	private String catName;

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public Category() {

	}

	public Category(String catId, String catName) {
		super();
		this.catId = catId;
		this.catName = catName;
	}

	@Override
	public String toString() {
		return "Category [catId=" + catId + ", catName=" + catName + "]";
	}

}
