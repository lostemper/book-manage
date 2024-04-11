package com.richard.controller;
import com.richard.dto.BookDto;
import com.richard.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.util.Optional;

@RestController
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/books/{id}")
  public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
    return  null;
  }
}
