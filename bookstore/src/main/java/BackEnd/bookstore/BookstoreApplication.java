package BackEnd.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import BackEnd.bookstore.domain.Book;
import BackEnd.bookstore.domain.BookRepository;
	
@SpringBootApplication
public class BookstoreApplication  {
		
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}


	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	
	@Bean public CommandLineRunner bookStore(BookRepository bookRepository) { 
		return (args) -> { 
				 
			 log.info("create books"); 
			 bookRepository.save(new Book("Avain hukassa", "Sanna Mander", 2017, 1234567, 35.50)); 
			 bookRepository.save(new Book("Molli ja maan ääri", "Katri Kirkkopelto", 2019, 2345678, 22.90)); 
			 bookRepository.save(new Book("Uppo-Nalle ja Kumma", "Elina Karjalainen", 1993, 3456789, 32.50));
			 
			 log.info("print all books"); for (Book book : bookRepository.findAll())
			 { System.out.println(book.toString()); }
			 
			 }; }

}
