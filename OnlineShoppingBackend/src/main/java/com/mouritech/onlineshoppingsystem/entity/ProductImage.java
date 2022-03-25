package com.mouritech.onlineshoppingsystem.entity;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_image")
public class ProductImage {

	@Id
	@Column(name = "image_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long imageId;



	@Column(name = "picByte", length = Integer.MAX_VALUE)
	private byte[] picByte;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "prodId")
	private Product product;

	public ProductImage() {
		super();
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}



	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductImage(byte[] picByte) {


		this.picByte = picByte;
	}

	public ProductImage(Long imageId, byte[] picByte) {
		super();
		this.imageId = imageId;
		this.picByte = picByte;
	}

	@Override
	public String toString() {
		return "ProductImage [picByte=" + Arrays.toString(picByte) + "]";
	}



	

}