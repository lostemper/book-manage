package com.richard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.richard.dto.BookDto;
import com.richard.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BookController.class)
public class BookControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BookService bookService;


  @Test
  public void testGetBookById() throws Exception {
    Long bookId = 1L;
    BookDto expectedBook = new BookDto();
    expectedBook.setId(bookId);
    expectedBook.setTitle("Java Book");

    when(bookService.getBookById(bookId)).thenReturn(Optional.of(expectedBook));

    mockMvc.perform(MockMvcRequestBuilders.get("/books/{id}", bookId)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isOk())
      .andExpect(jsonPath("id").value(bookId))
      .andExpect(jsonPath("title").value("Java Book"));
  }

  @Test
  public void testAddBook() throws Exception {
    // Prepare test data
    BookDto bookDto = new BookDto();
    bookDto.setTitle("Test Book");
    bookDto.setAuthor("Test Author");

    // Convert bookDto to JSON
    ObjectMapper objectMapper = new ObjectMapper();
    String bookDtoJson = objectMapper.writeValueAsString(bookDto);

    when(bookService.addBook(any(BookDto.class))).thenReturn(bookDto);

    // Perform the MVC request and validate the response
    mockMvc.perform(MockMvcRequestBuilders.post("/books")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(bookDtoJson))
      .andExpect(status().isCreated());
  }

  @Test
  public void testUpdateBook() throws Exception {
    // Prepare test data
    Long bookId = 1L;
    BookDto updatedBookDto = new BookDto();
    updatedBookDto.setTitle("Updated Title");
    updatedBookDto.setAuthor("Updated Author");

    ObjectMapper objectMapper = new ObjectMapper();

    // Perform the PUT request to update the book
    mockMvc.perform(MockMvcRequestBuilders.put("/books/{id}", bookId)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(updatedBookDto)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("title").value("Updated Title"))
      .andExpect(jsonPath("author").value("Updated Author"));
  }

}
