package com.richard.service.impl;
import com.richard.dto.BookDto;
import com.richard.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import com.richard.entity.Book;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class BookServiceImplTest {

  @Mock
  private BookRepository bookRepository;

  @Spy
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
    bookDto.setIsbn("1234567890");
    bookDto.setYear(2022);

    Book bookEntity = new Book();
    bookEntity.setTitle(bookDto.getTitle());
    bookEntity.setAuthor(bookDto.getAuthor());
    bookEntity.setIsbn(bookDto.getIsbn());
    bookEntity.setYear(bookDto.getYear());
    // Mock the behavior of bookRepository.save() method
    when(bookRepository.save(any(Book.class))).thenReturn(bookEntity);
    // Call the service method
    BookDto savedBook = bookService.addBook(bookDto);
    // Verify the result
    assertNotNull(savedBook);
    assertEquals(bookDto.getTitle(), savedBook.getTitle());
    assertEquals(bookDto.getAuthor(), savedBook.getAuthor());
  }

  @Test
  public void testUpdateBook() {
    // Prepare test data
    Long bookId = 1L;
    BookDto bookDto = new BookDto();
    bookDto.setId(bookId);
    bookDto.setTitle("Updated Title");
    bookDto.setAuthor("Updated Author");
    bookDto.setYear(2024); // Assuming year is also updated

    // Mock the behavior of bookRepository.findById() method
    Book existingBook = new Book();
    existingBook.setId(bookId);
    when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));

    // Mock the behavior of bookRepository.save() method
    Book updatedBookEntity = new Book();
    updatedBookEntity.setId(bookId);
    updatedBookEntity.setTitle(bookDto.getTitle());
    updatedBookEntity.setAuthor(bookDto.getAuthor());
    updatedBookEntity.setYear(bookDto.getYear());
    when(bookRepository.save(any(Book.class))).thenReturn(updatedBookEntity);

    // Mock the behavior of modelMapper.map() method
    when(modelMapper.map(updatedBookEntity, BookDto.class)).thenReturn(bookDto);

    // Call the service method
    BookDto result = bookService.updateBook(bookId, bookDto);

    // Verify the interactions
    verify(bookRepository, times(1)).findById(bookId);
    verify(bookRepository, times(1)).save(any(Book.class));
    verify(modelMapper, times(1)).map(updatedBookEntity, BookDto.class);

    // Verify the result
    assertEquals(bookDto, result);
  }

  @Test
  public void testDeleteBookById() {
    // Mock the behavior of bookRepository.existsById() method
    when(bookRepository.existsById(1L)).thenReturn(true);
    when(bookRepository.existsById(2L)).thenReturn(false);
    // Mock the behavior of bookRepository.deleteById() method
    doNothing().when(bookRepository).deleteById(any(Long.class));
    // Test deleteBookById method with existing book id
    assertTrue(bookService.deleteBookById(1L));
    // Test deleteBookById method with non-existing book id
    assertFalse(bookService.deleteBookById(2L));
  }
}
