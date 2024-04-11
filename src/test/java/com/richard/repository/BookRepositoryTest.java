package com.richard.repository;
import com.richard.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BookRepositoryTest {

  @Autowired
  private BookRepository bookRepository;

  @Test
  public void testFindById() {
    Book book = new Book();
    book.setTitle("Test Book");
    bookRepository.save(book);
    Optional<Book> result = bookRepository.findById(book.getId());
    assertEquals(Optional.of(book), result);
  }
}
