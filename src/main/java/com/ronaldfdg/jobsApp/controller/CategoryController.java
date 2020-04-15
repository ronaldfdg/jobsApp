package com.ronaldfdg.jobsApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ronaldfdg.jobsApp.model.Category;
import com.ronaldfdg.jobsApp.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService serviceCategory;
	
	@GetMapping("/index")
	public String index(@RequestParam("page") int pageNumber, Model model) {
		
		Pageable page = PageRequest.of(pageNumber, 6, Sort.by("name"));
		Page<Category> pageCategory = serviceCategory.categoryPage(page);
		
		model.addAttribute("categories", pageCategory);
		return "categories/listCategories";
	}
	
	@GetMapping("/create")
	public String create(Category category) {
		return "categories/formCategory";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id, RedirectAttributes attribute) {
		serviceCategory.deleteById(id);
		attribute.addFlashAttribute("messageSuccess", "Se eliminó la categoría correctamente");
		return "redirect:/categories/index?page=0";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		model.addAttribute("category", serviceCategory.findById(id));
		return "categories/formCategory";
	}
	
	@PostMapping("/save")
	public String save(Category category, BindingResult result, RedirectAttributes attribute) {
		
		if(result.hasErrors()) {
			return "categories/formCategory";
		}
		
		if(serviceCategory.existsById(category.getId()))
			attribute.addFlashAttribute("messageSuccess", "Se actualizó la categoría: " + category.getName());
		else
			attribute.addFlashAttribute("messageSuccess", "Se registró la categoría con éxito!");
		
		serviceCategory.save(category);
		return "redirect:/categories/index?page=0";
		
	}
	
	
}
