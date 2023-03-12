package BackEnd.bookstore;
	
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import BackEnd.bookstore.domain.Book;
import BackEnd.bookstore.domain.BookRepository;
import BackEnd.bookstore.domain.Category;

@DataJpaTest
public class BookRepositoryTests {
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void findByAuthorShouldReturnBook() {
		List<Book> books = bookRepository.findByAuthor("Katri Kirkkopelto");
		assertThat(books.get(0).getTitle().equalsIgnoreCase("Molli ja Maan ääri"));
	}
	
	@Test
	public void createBook() {
		Book book = new Book("Muumipappa ja meri", "Tove Jansson", 1965, 1234567, 35.50, new Category("Satukirja"));
		bookRepository.save(book);
		assertNotEquals(book.getId(), null);
	}
	
	@Test
	public void deleteBook() {
		List<Book> books = bookRepository.findByAuthor("Katri Kirkkopelto");
		Book book = books.get(0);
		bookRepository.delete(book);
		List<Book> newBooks = bookRepository.findByAuthor("Katri Kirkkopelto");
		assertThat(newBooks).hasSize(0);		
	}
	
}
