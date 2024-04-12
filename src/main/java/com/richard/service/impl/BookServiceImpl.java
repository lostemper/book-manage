package com.richard.service.impl;

import com.richard.dto.BookDto;
import com.richard.entity.Book;
import com.richard.repository.BookRepository;
import com.richard.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final ModelMapper modelMapper;

  public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
    this.bookRepository = bookRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public Optional<BookDto> getBookById(Long id) {
    Optional<Book> bookOptional = bookRepository.findById(id);
    return bookOptional.map(this::convertToDto);
  }

  @Override
  public BookDto addBook(BookDto bookDto) {
    Book bookEntity = modelMapper.map(bookDto, Book.class);
    Book savedBook = bookRepository.save(bookEntity);
    return modelMapper.map(savedBook, BookDto.class);
  }

  private BookDto convertToDto(Book book) {
    return modelMapper.map(book, BookDto.class);
  }
}
