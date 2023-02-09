package BackEnd.bookstore.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import BackEnd.bookstore.domain.Book;
import BackEnd.bookstore.domain.BookRepository;
import BackEnd.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
private static final Logger log = LoggerFactory.getLogger(BookController.class);

	@Autowired
	BookRepository bookRepository;
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("/index")
	public String showIndex() {
		return "index";
	}

	@GetMapping("/books")
	public String showBooks(Model model) {
		log.info("get books");
		model.addAttribute("books", bookRepository.findAll());
		return "books";
	}

	@GetMapping("/addBook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepository.findAll());
		return "newBook";
	}
	
	@PostMapping("/saveBook")
	public String saveBook(@Valid Book book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			log.info("error with validation");
			model.addAttribute("book", book);
			model.addAttribute("categories", categoryRepository.findAll());
			return "newBook";
		}
		bookRepository.save(book);
		return "redirect:/books";
	}

	@GetMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		bookRepository.deleteById(id);
		return "redirect:/books";
	}
	
	@GetMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("editBook", bookRepository.findById(id));
		model.addAttribute("categories", categoryRepository.findAll());
		return "editBook";
	}
	
}