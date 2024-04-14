package com.richard.repository;
import com.richard.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class BookRepositoryTest {

  @Autowired
  private BookRepository bookRepository;

  @Test
  public void testFindById() {
    Book book = new Book();
    book.setTitle("Test Book");
    book.setAuthor("ss");
    bookRepository.save(book);
    Optional<Book> result = bookRepository.findById(book.getId());
    assertEquals(Optional.of(book), result);
  }
  @Test
  public void testSaveBook() {
    // Prepare test data
    Book book = new Book();
    book.setTitle("New Book");
    book.setAuthor("New Author");
    book.setIsbn("1234567890123");
    book.setYear(2024);
    // Invoke the repository method
    Book savedBook = bookRepository.save(book);
    // Verify the result
    assertEquals(book, savedBook);
  }

  @Test
  public void testDeleteBook() {
    Book book = new Book();
    book.setTitle("Test Book");
    book.setAuthor("Test Author");
    bookRepository.save(book);

    Optional<Book> savedBook = bookRepository.findById(book.getId());
    assertTrue(savedBook.isPresent());

    bookRepository.deleteById(book.getId());
    Optional<Book> deletedBook = bookRepository.findById(book.getId());
    assertFalse(deletedBook.isPresent());
  }

  @Test
  public void testGetAllBooks() {
    // Save some test books
    Book book1 = new Book();
    book1.setTitle("book1");
    book1.setAuthor("author1");
    book1.setIsbn("1234567890123");
    book1.setYear(2024);
    Book book2 = new Book();
    book2.setTitle("book2");
    book2.setAuthor("author2");
    book2.setIsbn("1234567890789");
    book2.setYear(2024);
    bookRepository.save(book1);
    bookRepository.save(book2);
    // Retrieve all books from the repository
    List<Book> books = bookRepository.findAll();
    // Check if the number of retrieved books matches the number of saved books
    assertEquals(2, books.size());
  }
}
