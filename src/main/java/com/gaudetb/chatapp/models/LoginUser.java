package com.gaudetb.chatapp.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class LoginUser {

	// ============> MEMBER VARIABLES <============
	
	@NotEmpty(message = "Email required")
	@Email(message = "Not a valid email address")
	private String email;
	
	@NotEmpty(message = "Password required")
	@Size(min = 8, max = 255, message = "Password must be at least 8 characters")
	private String password;

	// ============> CONSTRUCTORS <============
	
	public LoginUser() {}

	// ============> GETTERS & SETTERS <============
	
	// ---------------------------
		// Getters:

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	// ---------------------------
		// Setters:
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}