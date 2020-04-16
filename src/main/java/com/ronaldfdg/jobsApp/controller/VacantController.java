package com.ronaldfdg.jobsApp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ronaldfdg.jobsApp.model.Vacant;
import com.ronaldfdg.jobsApp.service.CategoryService;
import com.ronaldfdg.jobsApp.service.VacantService;
import com.ronaldfdg.jobsApp.util.Utileria;

@Controller
@RequestMapping("/vacants")
public class VacantController {
	
	@Value("${jobsApp.route.images}")
	private String route;

	@Autowired
	private VacantService serviceVacant;
	
	@Autowired
	private CategoryService serviceCategory;
	
	@GetMapping("/index")
	public String index(@RequestParam("pageNumber") int pageNumber, Model model) {
		
		Pageable pageable = PageRequest.of(pageNumber, 6, Sort.by("name"));
		
		model.addAttribute("vacants", serviceVacant.vacantsPage(pageable));
		return "vacants/listVacants";
	}
	
	@GetMapping("/create")
	public String create(@ModelAttribute("vacant") Vacant vacant) {
		return "vacants/formVacant";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		model.addAttribute("vacant", serviceVacant.findById(id));
		return "vacants/formVacant";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id, RedirectAttributes attribute) {
		serviceVacant.deleteById(id);
		attribute.addFlashAttribute("messageSuccess", "Se eliminó la vacante con éxito");
		return "redirect:/vacants/index?pageNumber=0";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("vacant") Vacant vacant, BindingResult result, 
							@RequestParam("imageName") MultipartFile multipart, RedirectAttributes attribute) {
		
		if(result.hasErrors())
			return "vacants/formVacant";
		
		if(!multipart.isEmpty()) {
			String imageVacant = Utileria.saveImage(multipart, route);
			
			if(!imageVacant.isEmpty() || imageVacant != null)
				vacant.setImage(imageVacant);
		}
		
		if(serviceVacant.existsById(vacant.getId()))
			attribute.addFlashAttribute("messageSuccess", "Se actualizó la vacante: "+vacant.getName());
		else
			attribute.addFlashAttribute("messageSuccess", "Se registró la vacante correctamente");
		
		serviceVacant.save(vacant);
		return "redirect:/vacants/index?pageNumber=0";
	}
	
	@ModelAttribute
	public void setGenerics(Model model) {
		model.addAttribute("categories", serviceCategory.findAll());
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
}
