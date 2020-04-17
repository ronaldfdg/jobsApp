package com.ronaldfdg.jobsApp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ronaldfdg.jobsApp.model.User;
import com.ronaldfdg.jobsApp.service.CategoryService;
import com.ronaldfdg.jobsApp.service.UserService;
import com.ronaldfdg.jobsApp.service.VacantService;

@Controller
public class HomeController {

	@Autowired
	private VacantService serviceVacant;
	
	@Autowired
	private CategoryService serviceCategory;
	
	@Autowired
	private UserService serviceUser;
	
	@GetMapping("/")
	public String goHome() {
		return "home";
	}
	
	@GetMapping("/index")
	public String index(Authentication authentication, HttpSession session) {
		
		if(session.getAttribute("user") == null) {
			User user = serviceUser.findByUsername(authentication.getName());
			user.setPassword(null);
			session.setAttribute("user", user);
		}
		
		return "redirect:/";
	}
	
	@ModelAttribute
	public void setGenerics(Model model) {
		model.addAttribute("categories", serviceCategory.findAll());
		model.addAttribute("vacants", serviceVacant.findAllApprovedAndSalient());
	}
	
}
