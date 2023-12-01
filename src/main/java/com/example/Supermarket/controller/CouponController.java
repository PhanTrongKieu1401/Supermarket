package com.example.Supermarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Supermarket.dao.CouponDAO;
import com.example.Supermarket.dao.MemberCardDAO;
import com.example.Supermarket.model.Coupon;
import com.example.Supermarket.model.Customer;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/supermarket/member")
public class CouponController {

	private CouponDAO couponDAO = new CouponDAO();
	
	@GetMapping("/coupon")
	public String couponView() {
		return "coupon";
	}
	
	@GetMapping("/coupon/check")
	public String checkCoupon(@RequestParam("id") String id, HttpSession session) {
		Coupon coupon = couponDAO.checkCoupon(id);
		session.setAttribute("coupon", coupon);
		return "redirect:/supermarket/member/coupon";
	}
}
