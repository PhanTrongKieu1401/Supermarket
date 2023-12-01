package com.example.Supermarket.model;

import java.sql.Date;

public class Product {
	private int id;
	private String name;
	private String type;
	private int size;
	private String description;
	private float price;
	private int quantityInStock;
	private String productionBatch;
	private Date manufacturingDate;
	private Date expiryDate;
	
	public Product() {
		super();
	}
	
	public Product(int id, String name, String type, int size, String description, float price, int quantityInStock,
			String productionBatch, Date manufacturingDate, Date expiryDate) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.size = size;
		this.description = description;
		this.price = price;
		this.quantityInStock = quantityInStock;
		this.productionBatch = productionBatch;
		this.manufacturingDate = manufacturingDate;
		this.expiryDate = expiryDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantityInStock() {
		return quantityInStock;
	}
	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	public String getProductionBatch() {
		return productionBatch;
	}
	public void setProductionBatch(String productionBatch) {
		this.productionBatch = productionBatch;
	}
	public Date getManufacturingDate() {
		return manufacturingDate;
	}
	public void setManufacturingDate(Date manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", type=" + type + ", size=" + size + ", description="
				+ description + ", price=" + price + ", quantityInStock=" + quantityInStock + ", productionBatch="
				+ productionBatch + ", manufacturingDate=" + manufacturingDate + ", expiryDate=" + expiryDate + "]";
	}
	
	
}
