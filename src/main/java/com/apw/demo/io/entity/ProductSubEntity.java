package com.apw.demo.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="subs")
public class ProductSubEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8121940616824597832L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(length=30, nullable=false)
	private String subId;
	
	@Column(length=100, nullable=false)
	private String subName;
	
	@Column(length=20, nullable=false)
	private int currentPrice;
	
	@Column(length=20, nullable=false)
	private int oldPrice;
	
	@Column(length=20, nullable=false)
	private int discount;
	
	@Column(length=500, nullable=false)
	private String subUrl;
	
	@ManyToOne
	@JoinColumn(name="users_id")
	private UserEntity userDetails;

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

	public UserEntity getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserEntity userDetails) {
		this.userDetails = userDetails;
	}
}
