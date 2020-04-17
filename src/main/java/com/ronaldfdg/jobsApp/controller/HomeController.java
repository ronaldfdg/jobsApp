package com.ronaldfdg.jobsApp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/search")
	public String search(@RequestParam(name = "description", required = false) String description, 
							@RequestParam(name = "categoryId", required = false) Integer categoryId, Model model) {
		

		if(categoryId != null && description != null)
			model.addAttribute("vacants", serviceVacant.findByCategoryAndDescription(categoryId, description));
		
		if(categoryId == null && description != null)
			model.addAttribute("vacants", serviceVacant.findByDescription(description));
		
		if(categoryId != null && description == null)
			model.addAttribute("vacants", serviceVacant.findByCategory(categoryId));
		
		return "home";
	}
	
	@GetMapping("/about")
	public String showAbout() {
		return "about";
	}
	
	@ModelAttribute
	public void setGenerics(Model model) {
		model.addAttribute("categories", serviceCategory.findAll());
		model.addAttribute("vacants", serviceVacant.findAllApprovedAndSalient());
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
}
