package com.accolite.bookstore.Controller;

import com.accolite.bookstore.Model.Book;
import com.accolite.bookstore.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    private ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok().body(this.bookService.getBooks());
    }

    @GetMapping("/books/{bookIsbn}")
    private Book getBookByIsbn(@PathVariable long bookIsbn){
        return this.bookService.getBookByIsbn(bookIsbn);
    }

    @PostMapping("/book")
    private ResponseEntity<Book> addBook(@RequestBody Book book){
        return ResponseEntity.ok().body(this.bookService.addBook(book));
    }

    @PutMapping("/books/{bookIsbn}")
    private ResponseEntity<Book> updateBook(@PathVariable long bookIsbn, @RequestBody Book book){
        book.setBookIsbn(bookIsbn);
        return ResponseEntity.ok().body(this.bookService.updateBook(book));
    }
}
