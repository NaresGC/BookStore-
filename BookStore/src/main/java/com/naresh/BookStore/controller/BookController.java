package com.naresh.BookStore.controller;

import com.naresh.BookStore.model.Books;
import com.naresh.BookStore.repository.BookJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookJpaRepository bookJpaRepository;

    @GetMapping(value = "/all")
    public List<Books> findAll(){
        return bookJpaRepository.findAll();
    }

    @GetMapping(value = "/{title}")
    public Books findByTitle(@PathVariable final String title){
        return bookJpaRepository.findByTitle(title);
    }

    @PostMapping(value = "/{load}")
    public Books load(@RequestBody final Books books){
        bookJpaRepository.save(books);
        return bookJpaRepository.findByTitle(books.getTitle());
    }

    @DeleteMapping(value = "/delete/{id}")
    public void  deleteBook(@PathVariable Long id){
        Books book = bookJpaRepository.findOneById(id);
        bookJpaRepository.delete(book);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Books> updateBook(@PathVariable Long id,@RequestBody final Books bookDetails){
        Books book = bookJpaRepository.findOneById(id);
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setPrice(bookDetails.getPrice());
        bookJpaRepository.save(book);
        return ResponseEntity.ok(book);
    }

    @GetMapping(value = "/filter/{price}")
    public List<Books> filterBooksByPrice(@PathVariable Float price){
        return bookJpaRepository.findByPrice(price);
    }
}
