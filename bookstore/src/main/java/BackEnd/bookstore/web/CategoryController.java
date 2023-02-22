package BackEnd.bookstore.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
	//Rest
	
	@GetMapping("/allcategories")
	public @ResponseBody List<Category> showCategories() {
		log.info("showCategories");
		return (List<Category>) categoryRepository.findAll();
		
	}
	
	 @GetMapping("/allcategories/{id}")
	 public @ResponseBody Optional<Category> findCategoryRest(@PathVariable("id") Long id) {
		 log.info("find category, id = " + id);
	     return categoryRepository.findById(id);
	    }   
	 
	 @PostMapping("/allcategories")
	 public @ResponseBody Category newCategory(@RequestBody Category newCategory) {
		log.info("save new category " + newCategory);
		return categoryRepository.save(newCategory);
	 }
	 
	@PutMapping("/allcategories/{id}")
	public @ResponseBody Category editCategory(@RequestBody Category editedCategory, @PathVariable("id") Long id) {
		log.info("edit category " + editedCategory);
		return categoryRepository.save(editedCategory);
		}

		
	@DeleteMapping("/allcategories/{id}")
	public @ResponseBody Iterable<Category> deleteCategory(@PathVariable("id") Long id) {
		log.info("delete category, id = " + id);
		categoryRepository.deleteById(id);
		return categoryRepository.findAll();
	}
	
}