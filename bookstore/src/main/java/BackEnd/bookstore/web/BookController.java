package BackEnd.bookstore.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/addBook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepository.findAll());
		return "newBook";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/saveBook")
	public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			log.info("error with validation");
			model.addAttribute("book", book);
			model.addAttribute("categories", categoryRepository.findAll());
			return "newBook";
		}
		bookRepository.save(book);
		return "redirect:/books";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		bookRepository.deleteById(id);
		return "redirect:/books";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("editBook", bookRepository.findById(id));
		model.addAttribute("categories", categoryRepository.findAll());
		return "editBook";
	}
	
	//Rest
	
	@GetMapping("/allbooks")
	public @ResponseBody List<Book> showBooks() {
		log.info("showBooks");
		return (List<Book>) bookRepository.findAll();
		
	}
	
	 @GetMapping("/allbooks/{id}")
	 public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long id) {
		 log.info("find book, id = " + id);
	     return bookRepository.findById(id);
	    }   
	 
	 @PostMapping("/allbooks")
	 public @ResponseBody Book newBook(@RequestBody Book newBook) {
		log.info("save new book " + newBook);
		return bookRepository.save(newBook);
	 }
	 
	@PutMapping("/allbooks/{id}")
	public @ResponseBody Book editBook(@RequestBody Book editedBook, @PathVariable("id") Long id) {
		log.info("edit book " + editedBook);
		return bookRepository.save(editedBook);
		}

		
	@DeleteMapping("/allbooks/{id}")
	public @ResponseBody Iterable<Book> deleteBook(@PathVariable("id") Long id) {
		log.info("delete book, id = " + id);
		bookRepository.deleteById(id);
		return bookRepository.findAll();
	}
}

	//Jos tekee oman RestBookController.javan:
	//@GetMapping("/allbooks")
	//public Iterable<Book> getBooks() { 
	//	log.info("//fetch and return books");
	//	return bookRepository.findAll();
	//}

	//@PostMapping("/allbooks")
	//Book newBook(@RequestBody Book newBook) {
	//	log.info("save new book " + newBook);
	//	return bookRepository.save(newBook);
	//}

	//@PutMapping("/allbooks/{id}")
	//Book editBook(@RequestBody Book editedBook, @PathVariable Long id) {
	//	log.info("edit book " + editedBook);
	//	return bookRepository.save(editedBook);
	//}

	
	//@DeleteMapping("/allbooks/{id}")
	//public Iterable<Book> deleteBook(@PathVariable Long id) {
	//	log.info("delete book, id = " + id);
	//	bookRepository.deleteById(id);
	//	return bookRepository.findAll();
	//}

	//@GetMapping("/allbooks/{id}")
	//Optional<Book> getBook(@PathVariable Long id) {
	//	log.info("find book, id = " + id);
	//	return bookRepository.findById(id);
	//}
	
//}