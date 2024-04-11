package com.richard.service;


import com.richard.dto.BookDto;
import java.util.Optional;

public interface BookService {
  Optional<BookDto> getBookById(Long id);
}
