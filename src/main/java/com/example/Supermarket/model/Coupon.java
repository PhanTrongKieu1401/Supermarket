package com.example.Supermarket.model;

import java.sql.Date;

public class Coupon {
	private int id;
	private String couponCode;
	private float value;
	private Date releaseDate;
	private Date outOfDate;
	public Coupon() {
		super();
	}
	public Coupon(int id, String couponCode, float value, Date releaseDate, Date outOfDate) {
		super();
		this.id = id;
		this.couponCode = couponCode;
		this.value = value;
		this.releaseDate = releaseDate;
		this.outOfDate = outOfDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Date getOutOfDate() {
		return outOfDate;
	}
	public void setOutOfDate(Date outOfDate) {
		this.outOfDate = outOfDate;
	}
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", couponCode=" + couponCode + ", value=" + value + ", releaseDate=" + releaseDate
				+ ", outOfDate=" + outOfDate + "]";
	}
	
	
}
