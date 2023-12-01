package com.example.Supermarket.dao;

import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;

import com.example.Supermarket.model.Member;

public class MemberDAO extends DAO{

	public MemberDAO() {
		super();
	}
	
	public boolean checkLogin(Member member) {
		boolean result = false;
		String sql = "SELECT * FROM tblMember WHERE username = ? AND password = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1,member.getUsername());
			ps.setString(2, member.getPassword());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				member.setPhoneNumber(rs.getString("phoneNumber"));
				member.setEmail(rs.getString("email"));
				member.setAddress(rs.getString("address"));
				member.setPosition(rs.getString("position"));
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
