package com.ronaldfdg.jobsApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ronaldfdg.jobsApp.model.Request;
import com.ronaldfdg.jobsApp.model.User;
import com.ronaldfdg.jobsApp.service.RequestService;
import com.ronaldfdg.jobsApp.service.UserService;
import com.ronaldfdg.jobsApp.service.VacantService;

@Controller
@RequestMapping("/requests")
public class RequestController {

	@Autowired
	private RequestService serviceRequest;
	
	@Autowired
	private VacantService serviceVacant;
	
	@Autowired
	private UserService serviceUser;
	
	@GetMapping("/index")
	public String index(@RequestParam("pageNumber") int pageNumber, Model model) {
		
		Pageable pageable = PageRequest.of(pageNumber, 4, Sort.by("requestDate"));
		
		model.addAttribute("requests", serviceRequest.pageRequests(pageable));
		return "requests/listRequests";
	}
	
	@GetMapping("/apply/{id}")
	public String apply(@ModelAttribute Request request, @PathVariable("id") int id,
							Model model) {
		model.addAttribute("vacant", serviceVacant.findById(id));
		return "requests/formRequest";
	}
	
	@PostMapping("/send")
	public String apply(@ModelAttribute Request request, BindingResult result,
							RedirectAttributes attribute) {
		
		if(result.hasErrors())
			return "requests/formRequest";
		
		User user = serviceUser.findByUsername("");
		
		return "";
	}
	
}
