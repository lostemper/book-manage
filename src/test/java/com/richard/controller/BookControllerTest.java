package com.richard.controller;

import com.richard.dto.BookDto;
import com.richard.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.print.Book;
import java.util.Optional;

import static org.mockito.Mockito.when;


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
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(bookId))
      .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Java Book"));
  }
}
