package demo.microservice.bookstore.Controller;
import java.util.ArrayList;
import java.util.List;

import demo.microservice.bookstore.model.Book;

public class BookStorage {
	private static final BookStorage _instance = new BookStorage();
	private List<Book> books;
	
	public static BookStorage instance() {
		return _instance;
	}
	
	private BookStorage() {
		books = new ArrayList<Book>();
		Book book0 = new Book(1, "Programming Java", 50d);
		books.add(book0);
		Book book1 = new Book(2, "A Short History of Nearly Everything", 150d);
		books.add(book1);
		Book book2 = new Book(3, "The Book of Why: The New Science of Cause and Effect", 100d);
		books.add(book2);
		Book book3 = new Book(4, "The Java Language Specification", 60d);
		books.add(book3);
	}
	
	public List<Book> getBookList() {
		return books;
	}
}
