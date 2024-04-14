package com.richard.repository;
import com.richard.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
}
