package com.example.Supermarket.model;

public class DetailedBill {
	private int id;
	private int quantity;
	private float price;
	private float total;
	private Product product;
	
	public DetailedBill() {
		super();
	}
	public DetailedBill(int id, int quantity, float price, float total, Product product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.total = total;
		this.product = product;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "DetailedBill [id=" + id + ", quantity=" + quantity + ", price=" + price + ", total=" + total
				+ ", product=" + product + "]";
	}
	
	
}
