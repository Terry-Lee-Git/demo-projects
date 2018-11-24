package com.rabbit.practise.bookstore.bookservice.Controller;

import com.rabbit.practise.bookstore.bookservice.Util;
import com.rabbit.practise.bookstore.bookservice.api.BookService;
import com.rabbit.practise.bookstore.bookservice.exception.ResourceNotFoundException;
import com.rabbit.practise.bookstore.bookservice.model.Book;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
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
        Iterable<Book> iterable = bookRepository.findAll();
        return Util.toList(iterable);
    }

    @Override
    public List<Book> searchBooksByIds(@NotNull @ApiParam(value = "Tags to filter by", required = true) @Valid @RequestParam(value = "ids", required = true) List<String> ids) {
        logServiceInstance("/books/search?ids="+ids);
        if (ids == null || ids.size() == 0) {
            return null;
        }
        List<Integer> idList = new ArrayList<Integer>();
        for (String id : ids) {
            idList.add(Integer.valueOf(StringUtils.trim(id)));
        }
        slowResponse(idList);
        Iterable<Book> iterable =  bookRepository.findAll(idList);
        return Util.toList(iterable);
    }

    @Override
    public Book getBook(@NotNull @ApiParam(value = "ID of the Book", required = true) @PathVariable("id") int id) {
        logServiceInstance("/books/" + id);
        slowResponse(id);
        Book book = bookRepository.findOne(id);
        logger.info(String.format("Find book: id(%s) ---> %s:", id, book));
        if (book == null) {
            throw new ResourceNotFoundException("Can not find Book by id:" + id);
        }
        return book;
    }

    private void slowResponse(List<Integer> idList) {
        for (Integer bookId : idList) {
            if (slowResponse(bookId)) {
                return;
            }
        }
    }

    private boolean slowResponse(Integer bookId) {
        long sleepTime = calSleepTime(bookId);
        if (sleepTime > 0) {
            logger.info("Slow response, Sleep " + sleepTime + "ms for bookId:" + bookId);
            slow(sleepTime);
            return true;
        }
        return false;
    }

    private long calSleepTime(Integer bookId) {
        if (bookId < 1 || bookId > 5) {
            return 0L;
        }
        /**
         * booId -> sleepTime(ms):
         *  1 -> 1000
         *  2 -> 2000
         *  3 -> 3000
         *  4 -> 4000
         *  5 -> 30000
         */
        if (bookId == 5) {
            return 30 * 1000L;
        }
        return bookId * 1000L;
    }


    private void slow(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void logServiceInstance(String url) {
        //https://stackoverflow.com/questions/38916213/how-to-get-the-spring-boot-host-and-port-address-during-run-time
        final String port = environment.getProperty("server.port");
        logger.info(url + ", port:" + port);
    }
}
