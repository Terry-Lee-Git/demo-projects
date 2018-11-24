package com.rabbit.practise.bookstore.bookservice.Controller;

import com.rabbit.practise.bookstore.bookservice.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
