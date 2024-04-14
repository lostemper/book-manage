package com.richard.service;


import com.richard.dto.BookDto;
import java.util.Optional;

public interface BookService {
  Optional<BookDto> getBookById(Long id);
  BookDto addBook(BookDto bookDto);
  BookDto updateBook(Long id, BookDto bookDto);
  boolean deleteBookById(Long id);
}
