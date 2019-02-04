package com.apw.demo.ui.model.request;

import java.util.List;

public class UserDetailsRequestModel {

	private String email;
	private String password;
	private List<ProductSubRequestModel> subs;
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<ProductSubRequestModel> getAddresses() {
		return subs;
	}
	public void setAddresses(List<ProductSubRequestModel> addresses) {
		this.subs = addresses;
	} 

	
	
/*
 * {
	"firstName":"Recker",
	"lastName":"Wang",
	"password":"123",
	"email":"wnmhhd@terpmail.umd.edu"
}
 */
	
	
	
	
}
