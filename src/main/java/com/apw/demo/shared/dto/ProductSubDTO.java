package com.apw.demo.shared.dto;

import java.io.Serializable;

public class ProductSubDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2387005829984191394L;

	private long id;
	
	private String subId;
	private String subName;
	private int currentPrice;
	private int oldPrice;
	private int discount;
	private String subUrl;
	private UserDTO userDetails;
	
	public UserDTO getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDTO userDetails) {
		this.userDetails = userDetails;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSubId() {
		return subId;
	}
	public void setSubId(String subId) {
		this.subId = subId;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public int getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(int currentPrice) {
		this.currentPrice = currentPrice;
	}
	public int getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(int oldPrice) {
		this.oldPrice = oldPrice;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getSubUrl() {
		return subUrl;
	}
	public void setSubUrl(String subUrl) {
		this.subUrl = subUrl;
	}
	
	
}
