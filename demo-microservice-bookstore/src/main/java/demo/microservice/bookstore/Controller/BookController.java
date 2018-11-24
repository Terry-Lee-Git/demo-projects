package demo.microservice.bookstore.Controller;

import demo.microservice.bookstore.api.BookService;
import demo.microservice.bookstore.exception.ResourceNotFoundException;
import demo.microservice.bookstore.model.Book;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
public class BookController implements BookService {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    Environment environment;
    

    @Override
    public List<Book> getBooks() {
        logServiceInstance("/books");
        return BookStorage.instance().getBookList();
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
    	List<Book> books = BookStorage.instance().getBookList();
        
        for (Book bookObj : books) {
        	if (bookObj != null 
        			&& bookObj.getName() != null && 
        			bookObj.getName().toLowerCase().contains(keyword)) {
        		resultList.add(bookObj);
        	}
        }
        return resultList;
	}

    

    @Override
    public Book getBook(@NotNull @ApiParam(value = "ID of the Book", required = true) @PathVariable("id") int id) {
        logServiceInstance("/books/" + id);
        List<Book> books = BookStorage.instance().getBookList();
        
        Book book = null;
        for (Book bookObj : books) {
        	if (bookObj != null && bookObj.getId() == id) {
        		book = bookObj;
        		break;
        	}
        }
        logger.info(String.format("Find book: id(%s) ---> %s:", id, book));
        if (book == null) {
            throw new ResourceNotFoundException("Can not find Book by id:" + id);
        }
        return book;
    }


    private void logServiceInstance(String url) {
        //https://stackoverflow.com/questions/38916213/how-to-get-the-spring-boot-host-and-port-address-during-run-time
        final String port = environment.getProperty("server.port");
        logger.info(url + ", port:" + port);
    }

	
}
