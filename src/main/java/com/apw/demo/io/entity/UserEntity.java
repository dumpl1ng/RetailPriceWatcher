package com.apw.demo.io.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="users")
public class UserEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -602362246161650554L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(length=50, nullable=false)
	private String userId;
	
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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public List<ProductSubEntity> getSubs() {
		return subs;
	}

	public void setSubs(List<ProductSubEntity> subs) {
		this.subs = subs;
	}

	@Column(length=100, nullable=false)
	private String email;
	
	@Column(length=100, nullable=false)
	private String encryptedPassword;

	@OneToMany(mappedBy="userDetails", cascade = CascadeType.ALL)
	private List<ProductSubEntity> subs;
}
