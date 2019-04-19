package com.cugb.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

@Alias("purchaseRecord")
public class PurchaseRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8906144972915018986L;
	
	private Long id;
	private Long userId;
	private Long productId;
	private double price;
	private int quantity;
	private double sum;
	private Timestamp purchaseTime;
	private String note;
	public Long getId() {
		return id;
	}
	public String getNote() {
		return note;
	}
	public double getPrice() {
		return price;
	}
	public Long getProductId() {
		return productId;
	}
	public Timestamp getPurchaseTime() {
		return purchaseTime;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getSum() {
		return sum;
	}
	public Long getUserId() {
		return userId;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public void setPurchaseTime(Timestamp purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
