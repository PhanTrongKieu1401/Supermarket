package com.example.Supermarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Supermarket.dao.MemberCardDAO;
import com.example.Supermarket.model.Customer;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/supermarket/member")
public class MemberCardController {

	private MemberCardDAO memberCardDAO = new MemberCardDAO();
	
	@GetMapping("/memberCard")
	public String memberCardView() {
		return "member_card";
	}
	
	@GetMapping("/memberCard/check")
	public String checkMemberCard(@RequestParam("id") String id, HttpSession session) {
		Customer customer = memberCardDAO.checkMemberCard(id);
		session.setAttribute("customer", customer);
		return "redirect:/supermarket/member/memberCard";
	}
}
