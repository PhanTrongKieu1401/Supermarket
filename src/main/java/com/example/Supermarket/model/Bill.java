package com.example.Supermarket.model;

import java.sql.Date;
import java.util.List;

public class Bill {
	private int id;
	private Date paymentDate;
	private String paymentMethod;
	private float total;
	private Cashier cashier;
	private Customer customer;
	private Coupon coupon;
	private List<DetailedBill> detailedBills;
	
	public Bill() {
		super();
	}

	public Bill(int id, Date paymentDate, String paymentMethod, float total, Cashier cashier, Customer customer, Coupon coupon,
			List<DetailedBill> detailedBills) {
		super();
		this.id = id;
		this.paymentDate = paymentDate;
		this.paymentMethod = paymentMethod;
		this.total = total;
		this.cashier = cashier;
		this.customer = customer;
		this.coupon = coupon;
		this.detailedBills = detailedBills;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Cashier getCashier() {
		return cashier;
	}

	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public List<DetailedBill> getDetailedBills() {
		return detailedBills;
	}

	public void setDetailedBills(List<DetailedBill> detailedBills) {
		this.detailedBills = detailedBills;
	}

	@Override
	public String toString() {
		return "Bill [id=" + id + ", paymentDate=" + paymentDate + ", paymentMethod=" + paymentMethod + ", total="
				+ total + ", cashier=" + cashier + ", customer=" + customer + ", coupon=" + coupon + ", detailedBills="
				+ detailedBills + "]";
	}

	
}
