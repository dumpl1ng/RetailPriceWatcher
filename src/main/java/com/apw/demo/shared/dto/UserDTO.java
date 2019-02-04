package com.apw.demo.shared.dto;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5623258640117264623L;

	private long id;
	private String userId;
	private String email;
	private String password;
	private List<ProductSubDTO> subs;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<ProductSubDTO> getSubs() {
		return subs;
	}
	public void setSubs(List<ProductSubDTO> subs) {
		this.subs = subs;
	}
	
	
	
}
