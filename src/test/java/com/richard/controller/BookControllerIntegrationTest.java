package com.richard.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @Order(1)
  public void testAddBook() throws Exception {
    String requestBody = "{\"title\": \"Test Book\", \"author\": \"Test Author\"}";
    mockMvc.perform(MockMvcRequestBuilders.post("/books")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody))
      .andExpect(status().isCreated());
  }

  @Test
  @Order(2)
  public void testGetAllBooks() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/books")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
  }

  @Test
  @Order(3)
  public void testGetBookById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/books/{id}", 1)
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
  }

  @Test
  @Order(4)
  public void testUpdateBook() throws Exception {
    String requestBody = "{\"title\": \"Updated Test Book\", \"author\": \"Updated Test Author\"}";
    mockMvc.perform(MockMvcRequestBuilders.put("/books/{id}", 1)
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody))
      .andExpect(status().isOk());
  }

  @Test
  @Order(5)
  public void testDeleteBook() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/books/{id}", 1)
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
  }
}
