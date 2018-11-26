package demo.microservice.bookstore.Controller;

import demo.microservice.bookstore.model.Book;
import org.springframework.data.repository.CrudRepository;
public interface BookRepository extends CrudRepository<Book, Integer> {
}