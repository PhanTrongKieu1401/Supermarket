package com.example.Supermarket.controller;

import java.sql.Date;
import java.util.List;
import java.util.stream.DoubleStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Supermarket.dao.BillDAO;
import com.example.Supermarket.model.Bill;
import com.example.Supermarket.model.Coupon;
import com.example.Supermarket.model.Customer;
import com.example.Supermarket.model.CustomerStat;
import com.example.Supermarket.model.DetailedBill;
import com.example.Supermarket.model.Member;
import com.example.Supermarket.model.Product;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/supermarket/member")
public class BillController {

	private BillDAO billDAO = new BillDAO();
	
	@GetMapping("/stat/customer/{id}")
	public String getDataForCustomerDetail(@PathVariable("id") int id, HttpSession session) {
		List<CustomerStat> customerStats = (List<CustomerStat>) session.getAttribute("customerStats");
		for(CustomerStat customerStat:customerStats) {
			if(customerStat.getId() == id) {
				session.setAttribute("customerStat", customerStat);
				break;
			}
		}
		Date startDate = (Date) session.getAttribute("startDate");
		Date endDate = (Date) session.getAttribute("endDate");
		List<Bill> bills = billDAO.getAllBillsByCustomer(startDate, endDate, id);
		for(Bill bill:bills) {
			double total = bill.getDetailedBills().stream().mapToDouble(DetailedBill::getTotal).sum();
			bill.setTotal((float) total);
		}
		session.setAttribute("bills", bills);
		return "customer_detail";
	}
	
	@GetMapping("/stat/customer/bill/{id}")
	public String getDataForBillDetail(@PathVariable("id") int id, HttpSession session) {
		List<Bill> bills = (List<Bill>) session.getAttribute("bills");
		for(Bill bill : bills) {
			if(bill.getId() == id) {
				int totalNumberProducts = bill.getDetailedBills().stream().mapToInt(DetailedBill::getQuantity).sum();
				session.setAttribute("totalNumberProducts", totalNumberProducts);
				session.setAttribute("bill", bill);
				break;
			}
		}
		return "bill_detail";
	}
	
	@GetMapping("/bill/add_product")
	public String addProductToBill(@RequestParam("quantity") int quantity, HttpSession session) {
		Product product = (Product) session.getAttribute("product");
		DetailedBill detailedBill = new DetailedBill();
		detailedBill.setQuantity(quantity);
		detailedBill.setPrice(product.getPrice());
		detailedBill.setTotal(quantity * product.getPrice());
		detailedBill.setProduct(product);
		
		Bill bill = (Bill) session.getAttribute("bill");
		boolean check = true;
		for(DetailedBill detailedBillOB:bill.getDetailedBills()) {
			if(detailedBillOB.getProduct().getId() == detailedBill.getProduct().getId()) {
				check = false;
				detailedBillOB.setQuantity(detailedBill.getQuantity());
				detailedBillOB.setTotal(detailedBill.getTotal());
				break;
			}
		}
		if(check) {
			bill.getDetailedBills().add(detailedBill);
		}
		double totalAmount = bill.getDetailedBills().stream().mapToDouble(DetailedBill::getTotal).sum();
		bill.setTotal((float) totalAmount);
		int totalNumberProducts = bill.getDetailedBills().stream().mapToInt(DetailedBill::getQuantity).sum();
		session.removeAttribute("product");
		session.removeAttribute("quantity");
		session.removeAttribute("total");
		session.setAttribute("totalNumberProducts", totalNumberProducts);
		session.setAttribute("bill", bill);
		return "redirect:/supermarket/member/payment";
	}
	
	@GetMapping("/bill/repair_product/{id}")
	public String repairProductOfBill(@PathVariable("id") int id, HttpSession session) {
		Bill bill = (Bill) session.getAttribute("bill");
		DetailedBill detailedBill = bill.getDetailedBills().get(id);
		session.setAttribute("product", detailedBill.getProduct());
		session.setAttribute("quantity", detailedBill.getQuantity());
		session.setAttribute("total", detailedBill.getTotal());
		return "redirect:/supermarket/member/payment";
	}
	
	@GetMapping("/bill/delete_product/{id}")
	public String deleteProductOfBill(@PathVariable("id") int id, HttpSession session) {
		Bill bill = (Bill) session.getAttribute("bill");
		for(DetailedBill detailedBill:bill.getDetailedBills()) {
			if(detailedBill.getProduct().getId() == id) {
				bill.getDetailedBills().remove(detailedBill);
				break;
			}
		}
		double totalAmount = bill.getDetailedBills().stream().mapToDouble(DetailedBill::getTotal).sum();
		bill.setTotal((float) totalAmount);
		int totalNumberProducts = bill.getDetailedBills().stream().mapToInt(DetailedBill::getQuantity).sum();
		session.removeAttribute("product");
		session.removeAttribute("quantity");
		session.removeAttribute("total");
		session.setAttribute("totalNumberProducts", totalNumberProducts);
		session.setAttribute("bill", bill);
		return "redirect:/supermarket/member/payment";
	}
	
	@GetMapping("/bill/add_memberCard")
	public String addMemberCardToBill(HttpSession session) {
		Bill bill = (Bill) session.getAttribute("bill");
		Customer customer = (Customer) session.getAttribute("customer");
		bill.setCustomer(customer);
		session.removeAttribute("customer");
		session.setAttribute("bill", bill);
		System.out.println(bill);
		return "redirect:/supermarket/member/payment";
	}
	
	@GetMapping("/bill/add_coupon")
	public String addCouponToBill(HttpSession session) {
		Bill bill = (Bill) session.getAttribute("bill");
		Coupon coupon = (Coupon) session.getAttribute("coupon");
		bill.setCoupon(coupon);
		float total = bill.getTotal() - coupon.getValue();
		if(total<0) total=0;
		bill.setTotal(total);
		session.removeAttribute("coupon");
		session.setAttribute("bill", bill);
		return "redirect:/supermarket/member/payment";
	}
	
	@GetMapping("/bill/add")
	public String addBill(HttpSession session) {
		Bill bill = (Bill) session.getAttribute("bill");
		if(billDAO.addBill(bill)) {
			Member member = bill.getCashier();
			session.removeAttribute("bill");
		}
		return "redirect:/supermarket/member/cashier/home";
	}
}
