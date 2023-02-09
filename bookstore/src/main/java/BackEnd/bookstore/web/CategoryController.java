package BackEnd.bookstore.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import BackEnd.bookstore.domain.Category;
import BackEnd.bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {
private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	CategoryRepository categoryRepository;
	

	@GetMapping("/categories")
	public String showCategories(Model model) {
		log.info("get categories");
		model.addAttribute("categories", categoryRepository.findAll());
		return "categories";
	}

	@GetMapping("/addCategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "addCategory";
	}
	
	@PostMapping("/saveCategory")
	public String saveCategory(@Valid Category category, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			log.info("error with validation");
			model.addAttribute("category", category);
			return "addCategory";
		}
		categoryRepository.save(category);
		return "redirect:/categories";
	}

	
}