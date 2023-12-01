package com.example.Supermarket.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.naming.java.javaURLContextFactory;

import com.example.Supermarket.model.Bill;
import com.example.Supermarket.model.Cashier;
import com.example.Supermarket.model.Coupon;
import com.example.Supermarket.model.Customer;
import com.example.Supermarket.model.DetailedBill;
import com.example.Supermarket.model.MemberCard;
import com.example.Supermarket.model.Product;

public class BillDAO extends DAO{

	public BillDAO() {
		super();
	}
	
	public boolean addBill(Bill bill) {
		boolean result = true;
		String sqlAddBill = "INSERT INTO tblBill(paymentDate, paymentMethod, cashierId, customerId, couponId) VALUES(?,?,?,?,?)";
		String sqlAddDetailedBill = "INSERT INTO tblDetailedBill(quantity, price, billId, productId) VALUES(?,?,?,?)";
		try {
			connection.setAutoCommit(false);
			PreparedStatement ps = connection.prepareStatement(sqlAddBill, Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, bill.getPaymentDate());
			ps.setString(2, bill.getPaymentMethod());
			ps.setInt(3, bill.getCashier().getId());
			ps.setInt(4, bill.getCustomer().getId());
			if(bill.getCoupon()!=null) {
				ps.setInt(5, bill.getCoupon().getId());
			}
			else {
				ps.setNull(5, java.sql.Types.INTEGER);
			}
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				bill.setId(rs.getInt(1));
				for(DetailedBill detailedBill:bill.getDetailedBills()) {
					ps = connection.prepareStatement(sqlAddDetailedBill, Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, detailedBill.getQuantity());
					ps.setFloat(2, detailedBill.getPrice());
					ps.setInt(3, bill.getId());
					ps.setInt(4, detailedBill.getProduct().getId());
					ps.executeUpdate();
					
					rs = ps.getGeneratedKeys();
					if(rs.next()) {
						detailedBill.setId(rs.getInt(1));
					}
				}
			}
		} catch (Exception e) {
			result = false;
			try {
				connection.rollback();
			} catch (Exception ex) {
				result = false;
				ex.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {		
				connection.setAutoCommit(true);
			}catch(Exception ex) {
				result = false;
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	public List<Bill> getAllBillsByCustomer(Date startDate, Date endDate, int customerId){
		List<Bill> bills = new ArrayList<>();
		String sqlBill = "select * from tblBill where tblBill.customerId = ? and tblBill.paymentDate between ? and ?";
		String sqlDetailedBill = "select *, sum(db.quantity*db.price) as total "
				+ "from tblDetailedBill db where db.billId = ? "
				+ "group by db.id;";
		String sqlCashier = "select m.id, m.name, m.phoneNumber, m.email, m.address, m.position "
				+ "from tblMember m where m.id = ?";
		String sqlCustomer = "select m.id, m.name, m.phoneNumber, m.email, m.address, m.position, mc.memberCode "
				+ "from tblMember m, tblMemberCard mc where m.id = ? and mc.customerId = m.id;";
		String sqlCoupon = "select * from tblCoupon c where c.id = ?";
		String sqlProduct = "select * from tblProduct where tblProduct.id = ?";
		
		try {
			PreparedStatement psBill = connection.prepareStatement(sqlBill);
			psBill.setInt(1, customerId);
			psBill.setDate(2, startDate);
			psBill.setDate(3, endDate);
			ResultSet rsBill = psBill.executeQuery();
			while(rsBill.next()) {
				Bill bill = new Bill();
				Cashier cashier = new Cashier();
				Customer customer = new Customer();
				Coupon coupon = new Coupon();
				List<DetailedBill> detailedBills = new ArrayList<>();
				MemberCard memberCard = new MemberCard();
				
				bill.setCashier(cashier);
				bill.setCustomer(customer);
				bill.setCoupon(coupon);
				bill.setDetailedBills(detailedBills);
				bill.getCustomer().setMemberCard(memberCard);
				
				bill.setId(rsBill.getInt("id"));
				bill.setPaymentDate(rsBill.getDate("paymentDate"));
				bill.setPaymentMethod(rsBill.getString("paymentMethod"));
				bill.getCashier().setId(rsBill.getInt("cashierId"));
				bill.getCustomer().setId(rsBill.getInt("customerId"));
				bill.getCoupon().setId(rsBill.getInt("couponId"));
				
				PreparedStatement psCashier = connection.prepareStatement(sqlCashier);
				psCashier.setInt(1, bill.getCashier().getId());
				ResultSet rsCashier = psCashier.executeQuery();
				if(rsCashier.next()) {
					bill.getCashier().setName(rsCashier.getString("name"));
					bill.getCashier().setPhoneNumber(rsCashier.getString("phoneNumber"));
					bill.getCashier().setEmail(rsCashier.getString("email"));
					bill.getCashier().setAddress(rsCashier.getString("address"));
					bill.getCashier().setPosition(rsCashier.getString("position"));
				}
				
				PreparedStatement psCustomer = connection.prepareStatement(sqlCustomer);
				psCustomer.setInt(1, bill.getCustomer().getId());
				ResultSet rsCustomer = psCustomer.executeQuery();
				if(rsCustomer.next()) {
					bill.getCustomer().setName(rsCustomer.getString("name"));
					bill.getCustomer().setPhoneNumber(rsCustomer.getString("phoneNumber"));
					bill.getCustomer().setEmail(rsCustomer.getString("email"));
					bill.getCustomer().setAddress(rsCustomer.getString("address"));
					bill.getCustomer().setPosition(rsCustomer.getString("position"));
					bill.getCustomer().getMemberCard().setMemberCode(rsCustomer.getString("memberCode"));
				}
				
				if(bill.getCoupon().getId()>0) {
					PreparedStatement psCoupon = connection.prepareStatement(sqlCoupon);
					psCoupon.setInt(1, bill.getCoupon().getId());
					ResultSet rsCoupon = psCoupon.executeQuery();
					if(rsCoupon.next()) {
						bill.getCoupon().setCouponCode(rsCoupon.getString("couponCode"));
						bill.getCoupon().setValue(rsCoupon.getFloat("value"));
						bill.getCoupon().setReleaseDate(rsCoupon.getDate("releaseDate"));
						bill.getCoupon().setOutOfDate(rsCoupon.getDate("outOfDate"));
					}
				}
				
				PreparedStatement psDetailedBill = connection.prepareStatement(sqlDetailedBill);
				psDetailedBill.setInt(1, bill.getId());
				ResultSet rsDetailedBill = psDetailedBill.executeQuery();
				while(rsDetailedBill.next()) {
					DetailedBill detailedBill = new DetailedBill();
					Product product = new Product();
					detailedBill.setProduct(product);
					
					detailedBill.setId(rsDetailedBill.getInt("id"));
					detailedBill.setQuantity(rsDetailedBill.getInt("quantity"));
					detailedBill.setPrice(rsDetailedBill.getFloat("price"));
					detailedBill.setTotal(rsDetailedBill.getFloat("total"));
					detailedBill.getProduct().setId(rsDetailedBill.getInt("productId"));
					
					PreparedStatement psProduct = connection.prepareStatement(sqlProduct);
					psProduct.setInt(1, detailedBill.getProduct().getId());
					ResultSet rsProduct = psProduct.executeQuery();
					if(rsProduct.next()) {
						detailedBill.getProduct().setName(rsProduct.getString("name"));
						detailedBill.getProduct().setType(rsProduct.getString("type"));
						detailedBill.getProduct().setSize(rsProduct.getInt("size"));
						detailedBill.getProduct().setDescription(rsProduct.getString("description"));
						detailedBill.getProduct().setPrice(rsProduct.getFloat("price"));
						detailedBill.getProduct().setQuantityInStock(rsProduct.getInt("quantityInStock"));
						detailedBill.getProduct().setManufacturingDate(rsProduct.getDate("manufacturingDate"));
						detailedBill.getProduct().setExpiryDate(rsProduct.getDate("expiryDate"));
					}
					bill.getDetailedBills().add(detailedBill);
				}
				
				bills.add(bill);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bills;
	}
}
