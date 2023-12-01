package com.example.Supermarket.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Supermarket.dao.CustomerStatDAO;
import com.example.Supermarket.model.CustomerStat;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/supermarket/member")
public class CustomerStatController {
	private CustomerStatDAO customerStatDAO = new CustomerStatDAO();
	
	@GetMapping("/stat/customer")
	public String getDataForCustomerStat(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate, HttpSession session) {
		List<CustomerStat> customerStats = customerStatDAO.getAllCustomerStat(startDate, endDate);
		double total = customerStats.stream().mapToDouble(CustomerStat::getTotalAmountOfPurchased).sum();
		session.setAttribute("customerStats", customerStats);
		session.setAttribute("total", total);
		session.setAttribute("startDate", startDate);
		session.setAttribute("endDate", endDate);
		return "redirect:/supermarket/member/manager/customer_stat";
	}
	
	@GetMapping("/stat/back")
	public String back(HttpSession session) {
		session.removeAttribute("customerStats");
		session.removeAttribute("total");
		session.removeAttribute("startDate");
		session.removeAttribute("endDate");
		return "redirect:/supermarket/member/manager/select_stat";
	}
}
