package io.igok8u.demo.etagsapp.controller;

import io.igok8u.demo.etagsapp.model.Book;
import io.igok8u.demo.etagsapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(path = "/{bookId}")
    public ResponseEntity<Book> doGetBookById(@PathVariable("bookId") String bookId) {
        Book book = bookService.findBook(bookId);

        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(5, TimeUnit.DAYS))
                             .eTag(String.valueOf(book.hashCode()).concat("-").concat(bookId))
                             .body(book);
    }
}
