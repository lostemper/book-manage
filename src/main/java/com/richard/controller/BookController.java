package com.richard.controller;
import com.richard.dto.BookDto;
import com.richard.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    Optional<BookDto> book = bookService.getBookById(id);
    if (book.isPresent()) {
      BookDto bookDto = book.get();
      return ResponseEntity.ok(bookDto);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
    BookDto savedBook = bookService.addBook(bookDto);
    return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
  }
}
