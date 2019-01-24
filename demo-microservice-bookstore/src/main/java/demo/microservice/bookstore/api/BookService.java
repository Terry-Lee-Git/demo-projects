package demo.microservice.bookstore.api;

import demo.microservice.bookstore.model.Book;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
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
    @RequestMapping(value = "/book/{id}",
            produces = {"application/json" },
            method = RequestMethod.GET)
    Book getBook(@NotNull @ApiParam(value = "ID of the Book", required = true) @PathVariable("id") int id);

    @ApiOperation(
            value = "Add a book", nickname = "addBook", notes = "", tags={ "book" },
            response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Book.class),
            @ApiResponse(code = 403, message = "Invalid input")  })
    @RequestMapping(value = "/book",
            produces = {"application/json" },
            method = RequestMethod.POST)
    Book addBook(@NotNull @ApiParam(value = "Book object needs to be added into the store", required = true) @Valid @RequestBody Book book);
/*
    @ApiOperation(value = "Update an existing Book", nickname = "updateBook", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Boolean.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Book not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/book",
            produces = {"application/json" },
            consumes = { "application/json"},
            method = RequestMethod.PUT)
    Boolean updateBook(@ApiParam(value = "Book object that needs to be updated" ,required=true )  @Valid @RequestBody Book body);
    */
}
