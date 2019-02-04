package com.apw.demo.ui.model.response;

public class ProductSubResponse {

	private String subId;
	private String subName;
	private int currentPrice;
	private int oldPrice;
	private int discount;
	private String subUrl;
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
