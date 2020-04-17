package com.ronaldfdg.jobsApp.controller;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class JobsErrorController implements ErrorController {

	@GetMapping("/error")
	public String handleError(HttpServletRequest request) {
		
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		if(status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			
			if(statusCode == HttpStatus.NOT_FOUND.value())
				return "error/404";
			else if(statusCode == HttpStatus.FORBIDDEN.value())
				return "error/403";
			
		}
		
		return "error";
	}
	
	@ModelAttribute
	public void setGenerics(Model model, HttpServletRequest request) {
		model.addAttribute("description", "Something went wrong! :(");
		model.addAttribute("timestamp", new Date());
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}

}
