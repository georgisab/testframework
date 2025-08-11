package com.example.library;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/books")
    public List<Book> getBooks() {
        return List.of(
            new Book(1L, "Spring in Action"),
            new Book(2L, "React Quickly")
        );
    }
}
