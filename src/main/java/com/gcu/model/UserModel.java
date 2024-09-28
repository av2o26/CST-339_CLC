package com.gcu.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserModel 
{
	// Properties
	@NotNull(message="This is a required field!")
	@Size(min=1, max=32, message="Password must be between 1 and 32 characters.")
	private String firstName;
	
	@NotNull(message="This is a required field!")
	@Size(min=1, max=32, message="Password must be between 1 and 32 characters.")
	private String lastName;
	
	@NotNull(message="This is a required field!")
	@Size(min=1, max=32, message="Password must be between 1 and 32 characters.")
	private String email;
	
	@NotNull(message="This is a required field!")
	@Size(min=1, max=10, message="Phone number must be between 1 and 10 characters.")
	private String phone;
	
	@NotNull(message="This is a required field!")
	@Size(min=1, max=32, message="Username must be between 1 and 32 characters.")
	private String username;
	
	@NotNull(message="This is a required field!")
	@Size(min=1, max=32, message="Password must be between 1 and 32 characters.")
	private String password;

	// Getters and Setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Constructor
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phone
	 * @param username
	 * @param password
	 */
	public UserModel(
			@NotNull(message = "This is a required field!") @Size(min = 1, max = 32, message = "Password must be between 1 and 32 characters.") String firstName,
			@NotNull(message = "This is a required field!") @Size(min = 1, max = 32, message = "Password must be between 1 and 32 characters.") String lastName,
			@NotNull(message = "This is a required field!") @Size(min = 1, max = 32, message = "Password must be between 1 and 32 characters.") String email,
			@NotNull(message = "This is a required field!") @Size(min = 1, max = 10, message = "Phone number must be between 1 and 10 characters.") String phone,
			@NotNull(message = "This is a required field!") @Size(min = 1, max = 32, message = "Username must be between 1 and 32 characters.") String username,
			@NotNull(message = "This is a required field!") @Size(min = 1, max = 32, message = "Password must be between 1 and 32 characters.") String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
	}

	/**
	 * Default Constructor
	 */
	public UserModel() {
		super();
	}
}
