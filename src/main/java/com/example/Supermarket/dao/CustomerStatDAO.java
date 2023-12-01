package com.example.Supermarket.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.Supermarket.model.CustomerStat;

public class CustomerStatDAO extends DAO{

	public CustomerStatDAO() {
		super();
	}

	public List<CustomerStat> getAllCustomerStat(Date startDate, Date endDate){
		List<CustomerStat> customerStats = new ArrayList<>();
		String sql = "select m.id, m.name, m.phoneNumber, m.email, m.address, "
				+ "sum(tblDetailedBill.quantity*tblDetailedBill.price) as totalAmountOfPurchased, "
				+ "count(distinct tblBill.id) as totalNumberPurchased, "
				+ "sum(tblDetailedBill.quantity) as totalNumberProducts "
				+ "from tblMember m "
				+ "inner join tblBill on m.id = tblBill.customerId "
				+ "inner join tblDetailedBill on tblBill.id = tblDetailedBill.billId "
				+ "where tblBill.paymentDate between ? and ? "
				+ "group by m.id order by totalAmountOfPurchased";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDate(1, startDate);
			ps.setDate(2, endDate);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CustomerStat customerStat = new CustomerStat();
				customerStat.setId(rs.getInt("id"));
				customerStat.setName(rs.getString("name"));
				customerStat.setPhoneNumber(rs.getString("phoneNumber"));
				customerStat.setEmail(rs.getString("email"));
				customerStat.setAddress(rs.getString("address"));
				customerStat.setTotalAmountOfPurchased(rs.getFloat("totalAmountOfPurchased"));
				customerStat.setTotalNumberPurchased(rs.getInt("totalNumberPurchased"));
				customerStat.setTotalNumberProducts(rs.getInt("totalNumberProducts"));
				customerStats.add(customerStat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerStats;
	}
}
