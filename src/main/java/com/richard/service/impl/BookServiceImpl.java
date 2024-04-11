package com.richard.service.impl;

import com.richard.dto.BookDto;
import com.richard.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
  @Override
  public Optional<BookDto> getBookById(Long id) {
    return null;
  }
}
