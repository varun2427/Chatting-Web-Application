package com.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id@GeneratedValue@Column(name = "user_id")
	private int userId;
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	@Column(name = "phoneNum", nullable = false, unique = true)
	private String phoneNum;
	@Column(name = "country", nullable = false)
	private String country;
	@Column(name = "gender", nullable = false)
	private String gender;
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	@Column(name = "password", nullable = false)
	private String password;

	public User() {
		super();
	}

	
	public User(int userId, String username, String phoneNum, String email, String password, String country, String gender) {
		super();
		this.userId = userId;
		this.username = username;
		this.phoneNum = phoneNum;
		this.email = email;
		this.password = password;
		this.country= country;
		this.gender= gender;
	}
	
	
	
	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}

	
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", phoneNum=" + phoneNum + ", email=" + email
				+ ", password=" + password + "]";
	}

	
}
