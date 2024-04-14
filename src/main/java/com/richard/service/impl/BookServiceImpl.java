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

  @Override
  public BookDto updateBook(Long id, BookDto bookDto) {
    Optional<Book> optionalBook = bookRepository.findById(id);
    if (optionalBook.isPresent()) {
      Book existingBook = optionalBook.get();
      // Update the existing book with new data
      existingBook.setTitle(bookDto.getTitle());
      existingBook.setAuthor(bookDto.getAuthor());
      existingBook.setYear(bookDto.getYear()); // Assuming year is also updated
      // Save the updated book
      Book updatedBook = bookRepository.save(existingBook);
      // Map the updated book entity to DTO
      return modelMapper.map(updatedBook, BookDto.class);
    } else {
      // If book with given id is not found, return null or throw an exception
      return null;
    }
  }


  private BookDto convertToDto(Book book) {
    return modelMapper.map(book, BookDto.class);
  }
}
