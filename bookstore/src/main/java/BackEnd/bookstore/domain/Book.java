package BackEnd.bookstore.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "Title cannot be empty.")
	@Size(min=1, max=250)
	private String title;
	private String author;
	private int publicationYear, isbn;
	private double price;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryid")
	private Category category;
	
	public Book() {
		super();
	}
	
	public Book(String title, String author, int publicationYear, int isbn, double price, Category category) {
		super();
		this.title = title;
		this.author = author; 
		this.publicationYear = publicationYear;
		this.isbn = isbn;
		this.price = price;
		this.category = category;
	}
	
	public Book(String title, String author, int publicationYear, int isbn, double price) {
		super();
		this.title = title;
		this.author = author; 
		this.publicationYear = publicationYear;
		this.isbn = isbn;
		this.price = price;
	}
	
	public Book(String title, String author) {
		super();
		this.title = title;
		this.author = author;
	}
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear
				+ ", isbn=" + isbn + ", price=" + price + ", category=" + category + "]";
	}

	
	
}
