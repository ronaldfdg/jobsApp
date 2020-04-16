package com.ronaldfdg.jobsApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ronaldfdg.jobsApp.model.Role;
import com.ronaldfdg.jobsApp.model.User;
import com.ronaldfdg.jobsApp.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService serviceUser;
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("users", serviceUser.findAll());
		return "users/listUsers";
	}
	
	@GetMapping("/")
	public String goForm(@ModelAttribute User user) {
		return "users/formUser";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id, RedirectAttributes attribute) {
		serviceUser.deleteById(id);
		attribute.addFlashAttribute("messageSuccess", "Se acaba de borrar al usuario");
		return "redirect:/users/index";
	}
	
	@GetMapping("/lockOrUnlock/{id}")
	public String lockUser(@PathVariable("id") int id, RedirectAttributes attribute) {
		User user = serviceUser.findById(id);
		
		if(user.isStatus()) {
			user.setStatus(false);
			attribute.addFlashAttribute("messageSuccess", "Se ha bloqueado al usuario: " + user.getName().toUpperCase());
		} else {
			user.setStatus(true);
			attribute.addFlashAttribute("messageSuccess", "Se ha desbloqueado al usuario: " + user.getName().toUpperCase());
		}
		
		serviceUser.save(user);
		return "redirect:/users/index";
	}
	
	@PostMapping("/signup")
	public String signup(@ModelAttribute User user, BindingResult result, RedirectAttributes attribute) {
		
		if(result.hasErrors()) 
			return "users/formUser";
		
		
		Role role = new Role();
		role.setId(3);
		
		user.getRoles().add(role);
		
		serviceUser.save(user);
		attribute.addFlashAttribute("messageSuccess", "Usted ya se encuentra registrado. Ingrese y encuentre el trabajo de su anhelo");
		
		return "redirect:/";
		
	}
	
}
