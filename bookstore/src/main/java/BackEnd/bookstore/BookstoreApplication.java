package BackEnd.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import BackEnd.bookstore.domain.Book;
import BackEnd.bookstore.domain.BookRepository;
import BackEnd.bookstore.domain.Category;
import BackEnd.bookstore.domain.CategoryRepository;
	
@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	@Autowired
	BookRepository bookRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}



	
	@Bean public CommandLineRunner bookStore(BookRepository bookRepository, CategoryRepository categoryRepository) { 
		return (args) -> { 
			log.info("create categories"); 
			 categoryRepository.save(new Category("Tietokirja"));
			 categoryRepository.save(new Category("Novelli"));
			 categoryRepository.save(new Category("Sanakirja"));
			  
			 log.info("create books");
				bookRepository.save(new Book("Avain hukassa", "Sanna Mander", 2017, 1234567, 35.50, categoryRepository.findByName("Tietokirja").get(0))); 
				bookRepository.save(new Book("Molli ja maan ääri", "Katri Kirkkopelto", 2019, 2345678, 22.90, categoryRepository.findByName("Novelli").get(0))); 
				bookRepository.save(new Book("Uppo-Nalle ja Kumma", "Elina Karjalainen", 1993, 3456789, 32.50, categoryRepository.findByName("Sanakirja").get(0)));
			
			 
			 log.info("print all books"); for (Book book : bookRepository.findAll())
			 { System.out.println(book.toString()); }
			 
			 log.info("print all books"); for (Category category : categoryRepository.findAll())
			 { System.out.println(category.toString()); }
			 
			 }; }

	
}
