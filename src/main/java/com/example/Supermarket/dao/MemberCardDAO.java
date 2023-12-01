package com.example.Supermarket.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.Supermarket.model.Customer;
import com.example.Supermarket.model.MemberCard;

public class MemberCardDAO extends DAO{

	public MemberCardDAO() {
		super();
	}
	
	public Customer checkMemberCard(String key) {
		Customer customer = new Customer();
		MemberCard memberCard = new MemberCard();
		customer.setMemberCard(memberCard);
		String sql = "SELECT m.id, m.name, m.phoneNumber, m.email, m.address FROM tblMember m, tblMemberCard "
				+ "WHERE m.id = tblMemberCard.customerId AND tblMemberCard.memberCode = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, key);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("name"));
				customer.setPhoneNumber(rs.getString("phoneNumber"));
				customer.setEmail(rs.getString("email"));
				customer.setAddress(rs.getString("address"));
				customer.getMemberCard().setMemberCode(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}
}
