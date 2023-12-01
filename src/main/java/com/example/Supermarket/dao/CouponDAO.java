package com.example.Supermarket.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.Supermarket.model.Coupon;
import com.example.Supermarket.model.MemberCard;

public class CouponDAO  extends DAO{

	public CouponDAO() {
		super();
	}

	public Coupon checkCoupon(String key) {
		Coupon coupon = new Coupon();
		String sql = "SELECT * FROM tblCoupon WHERE couponCode = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, key);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				coupon.setId(rs.getInt("id"));
				coupon.setCouponCode(rs.getString("couponCode"));
				coupon.setValue(rs.getFloat("value"));
				coupon.setReleaseDate(rs.getDate("releaseDate"));
				coupon.setOutOfDate(rs.getDate("outOfDate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coupon;
	}
}
