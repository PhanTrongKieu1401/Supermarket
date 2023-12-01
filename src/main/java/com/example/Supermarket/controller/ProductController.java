package com.example.Supermarket.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Supermarket.dao.ProductDAO;
import com.example.Supermarket.model.Product;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/supermarket/product")
public class ProductController {

	private ProductDAO productDAO = new ProductDAO();
	
	@GetMapping("/search")
	public String searchProduct(@RequestParam("key") String key, HttpSession session) {
		List<Product> products = productDAO.searchProduct(key);
		session.setAttribute("products", products);
		return "manage_product";
	}
	
	@GetMapping("/check")
	public String checkProductById(@RequestParam("id") int id, HttpSession session, RedirectAttributes redirectAttributes) {
		Product product = productDAO.checkProductById(""+id);
		session.setAttribute("product", product);
		redirectAttributes.addFlashAttribute("redirectSuccess", true);
		return "redirect:/supermarket/member/payment";
	}
	
	@PostMapping("/add")
	public String addProduct(@ModelAttribute("product") Product product, HttpSession session) {
		if(productDAO.addProduct(product)) {
			session.removeAttribute("products");
		}
		return "redirect:/supermarket/member/manager/home";
	}
	
	@GetMapping("/repair/{id}")
	public String getDataForRepairProduct(@PathVariable("id") int id, Model model, HttpSession session) {
		List<Product> products = (List<Product>) session.getAttribute("products");
		for(Product product:products) {
			if(product.getId() == id) {
				model.addAttribute("product",product);
				break;
			}
		}
		return "repair_product";
	}
	
	@PutMapping("/update")
	public String updateProduct(@ModelAttribute("product") Product product, HttpSession session) {
		if(productDAO.updateProduct(product)) {
			session.removeAttribute("products");
		}
		return "redirect:/supermarket/member/manager/home";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") String id, HttpSession session) {
		if(productDAO.deleteProduct(id)) {
			List<Product> products = (List<Product>) session.getAttribute("products");
			for(Product product:products) {
				if(id.equals(product.getId())) {
					products.remove(product);
					break;
				}
			}
			session.setAttribute("products", products);
		}
		return "redirect:/supermarket/member/manage/product";
	}
}
