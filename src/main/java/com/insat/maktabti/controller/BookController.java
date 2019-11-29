package com.insat.maktabti.controller;

import com.insat.maktabti.DAO.BookDao;
import com.insat.maktabti.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    private BookDao bookDao;

    @GetMapping(value = "/Books")
    public Iterable<Book> getAllBooks() {
        Iterable<Book> books = bookDao.findAll();
        return books;
    }

    @GetMapping(value = "/Book/{id}")
    public Book getOneBook(@PathVariable int id) {
        return bookDao.findById(id);
    }

    @DeleteMapping (value = "/Book/{id}")
    public void deleteBook(@PathVariable int id) {
        bookDao.deleteById(id);
    }

    @PutMapping (value = "/Book")
    public void updateProduit(@RequestBody Book book) {
        bookDao.save(book);
    }

    @PostMapping(value = "/Book")
    public Book createBook(@RequestBody Book book) {
        Book bookAdded = bookDao.save(book);
        return bookAdded;
    }

}
