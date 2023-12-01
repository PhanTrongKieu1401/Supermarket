package com.example.Supermarket.model;

public class Member {
	private int id;
	private String username;
	private String password;
	private String name;
	private String phoneNumber;
	private String email;
	private String address;
	private String position;
	
	public Member() {
		super();
	}

	public Member(int id, String username, String password, String name, String phoneNumber, String email,
			String address, String position) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.position = position;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address + ", position="
				+ position + "]";
	}
	
	
}
