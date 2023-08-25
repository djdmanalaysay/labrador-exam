package com.example.labrador;

import com.example.labrador.controller.BookController;
import com.example.labrador.entity.Book;
import com.example.labrador.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> mockBooks = List.of(
                new Book("Author 1", "Country 1", "ImageLink 1", "English", "Link 1", 300, "Book 1", 2022),
                new Book("Author 2", "Country 2", "ImageLink 2", "French", "Link 2", 250, "Book 2", 2021)
        );

        when(bookService.getExternalApiData()).thenReturn(objectMapper.writeValueAsString(mockBooks));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(mockBooks)));
    }
}