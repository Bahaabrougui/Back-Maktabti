package com.insat.maktabti.controller;

import com.insat.maktabti.DAO.BookDao;
import com.insat.maktabti.domain.Book;
import com.insat.maktabti.domain.User;
import com.insat.maktabti.domain.request.ReqCreateBook;
import com.insat.maktabti.repositories.UserRepository;
import com.insat.maktabti.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserRepository userDao;
    @Autowired
    private FileService fileService;

    @GetMapping(value = "/book")
    @CrossOrigin
    public Page<Book> getAllBooks(Pageable pageable, String genre) {
        if (genre != null){
            List<Book> books = bookDao.findAllByGenre(genre);
            return new PageImpl<>(books, pageable, books.size());
        }
        return bookDao.findAll(pageable);
    }

    @GetMapping(value = "/book/{id}")
    public Book getOneBook(@PathVariable int id) {
        return bookDao.findById(id);
    }

    @DeleteMapping (value = "/book/{id}")
    public void deleteBook(@PathVariable int id) {
        bookDao.deleteById(id);
    }

    @PutMapping (value = "/book")
    public void updateProduit(@RequestBody Book book) {
        bookDao.save(book);
    }

    @PostMapping(value = "/book")
    @CrossOrigin
    public ResponseEntity<Book> createBook(@ModelAttribute ReqCreateBook reqBook, Principal principal) throws IOException {
        User currentUser = userDao.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        Book newBook = new Book();
        newBook.setName(reqBook.getName());
        newBook.setAuthor(reqBook.getAuthor());
        newBook.setGenre(reqBook.getGenre());
        newBook.setPrice(reqBook.getPrice());
        newBook.setPublisher(reqBook.getPublisher());
        newBook.setUser(currentUser);
        newBook.setDescription(reqBook.getDescription());
        long lenght = bookDao.count();
        String path = fileService.storeFile(reqBook.getImage(), "BK", lenght);
        newBook.setPhotoPath(path);
        Book book = bookDao.save(newBook);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);


        }

    }

}
