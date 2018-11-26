package demo.microservice.bookstore.api;

import demo.microservice.bookstore.model.Book;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(value = "Book-Service", description = "The Book-Service API")
public interface BookService {
    @ApiOperation(
            value = "Get All books", nickname = "getBooks",
            notes = "", tags={ "book" },
            response = Book.class, responseContainer = "List"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Book.class, responseContainer = "List")
    })
    @RequestMapping(value = "/books",
            produces = {"application/json" },
            method = RequestMethod.GET)
    List<Book> getBooks();

    @ApiOperation(
            value = "Search books by name", nickname = "searchBooksByKeyword",
            notes = "Use keyword for testing.", tags={ "book" },
            response = Book.class, responseContainer = "List"
        )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Book.class, responseContainer = "List"),
            @ApiResponse(code = 403, message = "Invalid input")
        })
    @RequestMapping(value = "/books/search",
            produces = {"application/json" },
            method = RequestMethod.GET)
    List<Book> searchBooksByKeyword(@NotNull @ApiParam(value = "keyword to filter by", required = true) @Valid
                                @RequestParam(value = "keyword", required = true) String keyword);

    @ApiOperation(
            value = "Get a book by id", nickname = "getBook", notes = "", tags={ "book" },
            response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Book.class),
            @ApiResponse(code = 404, message = "Can not find the book.") })
    @RequestMapping(value = "/books/{id}",
            produces = {"application/json" },
            method = RequestMethod.GET)
    Book getBook(@NotNull @ApiParam(value = "ID of the Book", required = true) @PathVariable("id") int id);

    @ApiOperation(
            value = "Add a book", nickname = "addBook", notes = "", tags={ "book" },
            response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Book.class),
            @ApiResponse(code = 403, message = "Invalid input")  })
    @RequestMapping(value = "/books",
            produces = {"application/json" },
            method = RequestMethod.POST)
    Book addBook(@NotNull @ApiParam(value = "Book object needs to be added into the store", required = true) @Valid @RequestBody Book book);
}
