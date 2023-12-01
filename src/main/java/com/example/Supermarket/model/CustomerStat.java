package com.example.Supermarket.model;

public class CustomerStat extends Customer{
	private float totalAmountOfPurchased;
	private int totalNumberPurchased;
	private int totalNumberProducts;
	public CustomerStat() {
		super();
	}
	public CustomerStat(int id, String username, String password, String name, String phoneNumber, String email, String address, String position, 
			MemberCard memberCard, int totalAmountOfPurchased, int totalNumberPurchased, int totalNumberProducts) {
		super(id, username, password, name, phoneNumber, email, address, position, memberCard);
		this.totalAmountOfPurchased = totalAmountOfPurchased;
		this.totalNumberPurchased = totalNumberPurchased;
		this.totalNumberProducts = totalNumberProducts;
	}
	public float getTotalAmountOfPurchased() {
		return totalAmountOfPurchased;
	}
	public void setTotalAmountOfPurchased(float totalAmountOfPurchased) {
		this.totalAmountOfPurchased = totalAmountOfPurchased;
	}
	public int getTotalNumberPurchased() {
		return totalNumberPurchased;
	}
	public void setTotalNumberPurchased(int totalNumberPurchased) {
		this.totalNumberPurchased = totalNumberPurchased;
	}
	public int getTotalNumberProducts() {
		return totalNumberProducts;
	}
	public void setTotalNumberProducts(int totalNumberProducts) {
		this.totalNumberProducts = totalNumberProducts;
	}
	@Override
	public String toString() {
		return "CustomerStat [totalAmountOfPurchased=" + totalAmountOfPurchased + ", totalNumberPurchased="
				+ totalNumberPurchased + ", totalNumberProducts=" + totalNumberProducts + "]";
	}
	
	
}
