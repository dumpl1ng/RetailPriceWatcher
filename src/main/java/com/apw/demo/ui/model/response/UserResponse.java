package com.apw.demo.ui.model.response;

import java.util.List;

public class UserResponse{

	private String userId;
	private String email;
	private List<ProductSubResponse> subs;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<ProductSubResponse> getSubs() {
		return subs;
	}
	public void setSubs(List<ProductSubResponse> subs) {
		this.subs = subs;
	}
	
	
}
