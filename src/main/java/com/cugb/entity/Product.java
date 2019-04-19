package com.cugb.entity;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("product")
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4676541056782515467L;
	
	private Long id;
	private String productName;
	private int stock;
	private double price;
	private int version;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	private String note;

}
