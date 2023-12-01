package com.example.Supermarket.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Supermarket.dao.MemberDAO;
import com.example.Supermarket.model.Bill;
import com.example.Supermarket.model.Cashier;
import com.example.Supermarket.model.Customer;
import com.example.Supermarket.model.DetailedBill;
import com.example.Supermarket.model.Member;
import com.example.Supermarket.model.Product;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/supermarket/member")
public class MemberController {

	private MemberDAO memberDAO = new MemberDAO();
	
	@GetMapping("/page_login")
	public String loginView() {
		return "login";
	}
	
	@GetMapping("/manager/home")
	public String managerHomeView(HttpSession session) {
		session.removeAttribute("products");
		return "manager_home";
	}
	
	@GetMapping("/manage/product")
	public String manageProductView() {
		return "manage_product";
	}
	
	@GetMapping("/manage/product/add")
	public String addProductView(Model model) {
		return "add_product";
	}
	
	@GetMapping("/manager/select_stat")
	public String selectStatView() {
		return "select_stat";
	}
	
	@GetMapping("/manager/customer_stat")
	public String customerStatView() {
		return "customer_stat";
	}
	
	@GetMapping("/cashier/home")
	public String cashierHomeView() {
		return "cashier_home";
	}
	
	@GetMapping("/payment")
	public String paymentView() {
		return "payment";
	}
	
	@GetMapping("/cashier/payment")
	public String startPayment(HttpSession session) {
		Member member = (Member) session.getAttribute("cashier");
		Cashier cashier = new Cashier();
		cashier.setId(member.getId());
		cashier.setName(member.getName());
		cashier.setPhoneNumber(member.getPhoneNumber());
		cashier.setEmail(member.getEmail());
		cashier.setAddress(member.getAddress());
		cashier.setPosition(member.getPosition());
		
		Date date = new Date();
		List<DetailedBill> detailedBills = new ArrayList<>();
		Product product = new Product();
		
		Bill bill = new Bill();
		bill.setCashier(cashier);
		bill.setDetailedBills(detailedBills);
		bill.setPaymentDate(new java.sql.Date(date.getTime()));
		bill.setPaymentMethod("Tien mat");
		
		session.setAttribute("bill", bill);
		return "redirect:/supermarket/member/payment";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model) {
		Member member = new Member();
		member.setUsername(username);
		member.setPassword(password);
		if(memberDAO.checkLogin(member)) {
			if("manager".equals(member.getPosition())) {
				session.setAttribute("manager", member);
				return "redirect:/supermarket/member/manager/home";
			}
			else if("cashier".equals(member.getPosition())) {
				session.setAttribute("cashier", member);
				return "redirect:/supermarket/member/cashier/home";
			}
		}
		return "redirect:/supermarket/member/page_login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("manager");
		session.removeAttribute("cashier");
		return "redirect:/supermarket/member/page_login";
	}
	
	
	
//	@PostMapping("/home")
//	public Map<String, String> login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
//		Member member = new Member();
//		member.setUsername(username);
//		member.setPassword(password);
//		System.out.println(member);
//		Map<String, String> map = new HashMap<>();
//		if(memberDAO.checkLogin(member)) {
//			if("manager".equals(member.getPosition())) {
//				session.setAttribute("manager", member);
//				map.put("status", "success");
//				map.put("url", "/supermarket/member/manager/home");
//			}
//			else if("cashier".equals(member.getPosition())) {
//				session.setAttribute("cashier", member);
//				map.put("status", "success");
//				map.put("url", "/supermarket/member/cashier/home");
//			}
//		}
//		else {
//			map.put("status", "failed");
//			map.put("url", "/supermarket/member/login");
//		}
//		return map;
//	}
}
