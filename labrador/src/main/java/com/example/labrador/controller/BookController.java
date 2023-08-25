package com.example.labrador.controller;

import com.example.labrador.entity.Book;
import com.example.labrador.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String getAllBooks() {
        return bookService.getExternalApiData();
    }

    @GetMapping("/lang/en")
    public List<Book> getEnglishBooks() throws JsonProcessingException {
        String apiResponse = bookService.getExternalApiData();

        ObjectMapper objectMapper = new ObjectMapper();
        List<Book> allBooks = objectMapper.readValue(apiResponse, new TypeReference<List<Book>>() {});

        List<Book> englishBooks = allBooks.stream()
                .filter(book -> "English".equalsIgnoreCase(book.getLanguage()))
                .collect(Collectors.toList());

        return englishBooks;
    }
}