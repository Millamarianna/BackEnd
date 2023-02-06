package BackEnd.bookstore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import BackEnd.bookstore.domain.Book;
import BackEnd.bookstore.domain.BookRepository;

@Controller
public class BookController {
private static final Logger log = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookRepository bookRepository;
	
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
		return "newBook";
	}
	
	@PostMapping("/saveBook")
	public String saveBook(Book book) {
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
		return "editBook";
	}
	
}