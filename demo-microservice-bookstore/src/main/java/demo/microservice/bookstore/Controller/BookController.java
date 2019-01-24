package demo.microservice.bookstore.Controller;

import demo.microservice.bookstore.Util;
import demo.microservice.bookstore.api.BookService;
import demo.microservice.bookstore.exception.ForbiddenActionException;
import demo.microservice.bookstore.exception.ResourceNotFoundException;
import demo.microservice.bookstore.model.Book;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.Logger;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

/**
 * For Swagger definition, can refer to class:
 *    https://github.com/swagger-api/swagger-codegen/blob/master/samples/server/petstore/springboot/src/main/java/io/swagger/api/PetApi.java
 *    https://www.concretepage.com/spring-5/spring-data-crudrepository-example
 */
@RestController
public class BookController implements BookService {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    BookRepository bookRepository;

    @Autowired
    Environment environment;
    

    @Override
    public List<Book> getBooks() {
        logServiceInstance("/books");
        List<Book> resultList = getAllBooks();
        logServiceInstance("/books, resultList=" + resultList);
        return resultList;
    }

    private List<Book> getAllBooks() {
        Iterable<Book> iterable = bookRepository.findAll();
        return Util.toList(iterable);
    }
    
    @Override
	public List<Book> searchBooksByKeyword(String keyword) {
		// TODO Auto-generated method stub
    	List<Book> resultList = new ArrayList<Book>();
    	if (keyword == null) {
    		return resultList;
    	}
    	keyword = StringUtils.trimWhitespace(keyword);
    	logServiceInstance("/books/search?keyword="+keyword);
    	
    	keyword = keyword.toLowerCase();
    	List<Book> books = getAllBooks();
        
        for (Book bookObj : books) {
        	if (bookObj != null 
        			&& bookObj.getName() != null && 
        			bookObj.getName().toLowerCase().contains(keyword)) {
        		resultList.add(bookObj);
        	}
        }

        logServiceInstance("/books/search?keyword=" + keyword + ", resultList:" + resultList);
        return resultList;
	}

    

    @Override
    public Book getBook(@NotNull @ApiParam(value = "ID of the Book", required = true) @PathVariable("id") int id) {
        logServiceInstance(" GET /book/" + id);
        Book book = bookRepository.findOne(id);
        logger.info(String.format("Find book: id(%s) ---> %s:", id, book));
        if (book == null) {
            throw new ResourceNotFoundException("Can not find Book by id:" + id);
        }
        return book;
    }

    @Override
    public Book addBook(@NotNull Book book) {
        logServiceInstance(" POST /book, addBook: book:" + book);
        if (book == null) {
            throw new ForbiddenActionException("Invalid input, book:" + book);
        }
        Book savedBook = bookRepository.save(book);
        logger.info(String.format("Added book: %s:", book));
        return savedBook;
    }
/*
    @Override
    public Boolean updateBook(Book book) {
        logServiceInstance(" PUT /book, updateBook: book:" + book);
        if (book == null || book.getId() <= 0) {
            throw new ForbiddenActionException("Invalid input, book:" + book);
        }
        bookRepository.save(book);
        logger.info(String.format("Updated book: %s:", book));
        return true;
    }
    */


    private void logServiceInstance(String url) {
        //https://stackoverflow.com/questions/38916213/how-to-get-the-spring-boot-host-and-port-address-during-run-time
        final String port = environment.getProperty("server.port");
        logger.info(url + ", port:" + port);
    }

	
}
