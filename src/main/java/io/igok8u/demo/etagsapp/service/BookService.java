package io.igok8u.demo.etagsapp.service;

import io.igok8u.demo.etagsapp.model.Book;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookService {
    private final Map<String, Book> storage = new HashMap<>();

    public Book findBook(String bookId) {
        Book book;
        if (!storage.containsKey(bookId)) {
            book = Book.builder().id(bookId).build();
            storage.put(bookId, book);
        } else {
            book = storage.get(bookId);
        }
        return book;
    }
}
