package com.richard.service.impl;
import com.richard.dto.BookDto;
import com.richard.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import com.richard.entity.Book;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class BookServiceImplTest {

  @Mock
  private BookRepository bookRepository;

  @Mock
  private ModelMapper modelMapper;

  @InjectMocks
  private BookServiceImpl bookService;

  @Test
  public void testGetBookById_BookFound() {
    // Mock the behavior of the BookRepository
    Long bookId = 1L;
    Book book = new Book();
    book.setId(bookId);
    book.setTitle("Test Book");
    book.setAuthor("Test Author");
    book.setYear(2022);
    book.setIsbn("1234567890");
    Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

    // Mock the behavior of the ModelMapper
    BookDto expectedBookDto = new BookDto();
    expectedBookDto.setId(bookId);
    expectedBookDto.setTitle("Test Book");
    expectedBookDto.setAuthor("Test Author");
    expectedBookDto.setYear(2022);
    expectedBookDto.setIsbn("1234567890");
    Mockito.when(modelMapper.map(book, BookDto.class)).thenReturn(expectedBookDto);

    // Invoke the method under test
    Optional<BookDto> result = bookService.getBookById(bookId);

    // Verify the result
    Assertions.assertTrue(result.isPresent());
    Assertions.assertEquals(expectedBookDto, result.get());
  }

  @Test
  public void testGetBookById_BookNotFound() {
    // Mock the behavior of the BookRepository
    Long bookId = 1L;
    Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

    // Invoke the method under test
    Optional<BookDto> result = bookService.getBookById(bookId);

    // Verify the result
    Assertions.assertTrue(result.isEmpty());
  }

  @Test
  public void testAddBook() {
    // Prepare test data
    BookDto bookDto = new BookDto();
    bookDto.setTitle("Test Book");
    bookDto.setAuthor("Test Author");

    // Call the service method
    BookDto savedBook = bookService.addBook(bookDto);

    // Verify the result
    assertNotNull(savedBook);
    assertEquals(bookDto.getTitle(), savedBook.getTitle());
    assertEquals(bookDto.getAuthor(), savedBook.getAuthor());
  }
}
