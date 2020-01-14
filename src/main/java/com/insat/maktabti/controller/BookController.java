package com.insat.maktabti.controller;

import com.insat.maktabti.DAO.BookDao;
import com.insat.maktabti.DAO.UserDao;
import com.insat.maktabti.domain.Book;
import com.insat.maktabti.domain.Chapter;
import com.insat.maktabti.domain.User;
import com.insat.maktabti.domain.enumerations.Type;
import com.insat.maktabti.domain.request.ReqCreateBook;
import com.insat.maktabti.domain.request.ReqExchangeBook;
import com.insat.maktabti.exception.BadRequestException;
import com.insat.maktabti.repositories.UserRepository;
import com.insat.maktabti.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.security.Principal;
import java.util.List;
import java.util.Set;

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
           System.out.print(pageable.toString());
            return  bookDao.findAllByGenreAndType(genre,Type.SELL.name(), pageable);
           // return new PageImpl<>(books, pageable, books.size());
        }
       return bookDao.findAllByType(Type.SELL.name(), pageable);

    }

    @GetMapping(value = "/my-books")
    @CrossOrigin
    public Page<Book> getMyBooks(Pageable pageable,Principal principal) {
        User currentUser = userDao.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        return bookDao.findAllByUserIdAndType(currentUser.getId(), Type.EXCHANGE.name(), pageable);

    }
    @GetMapping(value = "/exchange-books")
    @CrossOrigin
    public Page<Book> geOthersBooks(Pageable pageable,Principal principal) {
        User currentUser = userDao.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        return bookDao.findAllByTypeAndUserIdNot(Type.EXCHANGE.name(),currentUser.getId(), pageable);

    }


    @PostMapping(value = "/send-request")
    @CrossOrigin
    public Book sendRequest(@RequestBody ReqExchangeBook reqExchangeBook, Principal principal) {
      Book exchangeWithBook = bookDao.findById(reqExchangeBook.getExchangeWith());
     /* if (!exchangeWithBook.getUser().getName().equals(principal.getName())){
          throw new RuntimeException("You don't own the exchanged book");
      }*/
      Book requestedBook = bookDao.findById(reqExchangeBook.getRequestBook());
      requestedBook.getRequests().add(exchangeWithBook);
      bookDao.save(requestedBook);
      return exchangeWithBook;
    }

    @PostMapping(value = "/accept-request")
    @CrossOrigin
    public Book acceptRequest(@RequestBody ReqExchangeBook reqExchangeBook, Principal principal){

        //set type to INPROGRESS
        Book exchangeWithBook = bookDao.findById(reqExchangeBook.getExchangeWith());
        //check that you own the book
        Book requestedBook = bookDao.findById(reqExchangeBook.getRequestBook());
        exchangeWithBook.setType(Type.INPROGRESS.name());
        requestedBook.setType(Type.INPROGRESS.name());
        bookDao.save(exchangeWithBook);
        bookDao.save(requestedBook);
        return exchangeWithBook;
        //
    }

    @PostMapping(value = "/confirm-request")
    @CrossOrigin
    public Book confirmRequest(@RequestBody ReqExchangeBook reqExchangeBook, Principal principal){

        //set type to confirm
        Book exchangeWithBook = bookDao.findById(reqExchangeBook.getExchangeWith());
        //check that you own the book
        Book requestedBook = bookDao.findById(reqExchangeBook.getRequestBook());
        exchangeWithBook.setType(Type.CONFIRM.name());
        exchangeWithBook.setUser(requestedBook.getUser());
        requestedBook.setType(Type.CONFIRM.name());
        exchangeWithBook.setUser(exchangeWithBook.getUser());
        bookDao.save(exchangeWithBook);
        bookDao.save(requestedBook);
        return exchangeWithBook;
        //
    }

    @GetMapping(value = "/book/{id}")
    @CrossOrigin
    public Book getOneBook(@PathVariable int id) {
        return bookDao.findById(id);
    }

    @GetMapping(value = "/book-request/{id}")
    @CrossOrigin
    public Set<Book> getRequestBook(@PathVariable int id) {
        Book myBook = bookDao.findById(id);
        return myBook.getRequests();
    }

    @DeleteMapping (value = "/book/{id}")
    public void deleteBook(@PathVariable int id) {
        bookDao.deleteById(id);
    }

    @PutMapping (value = "/book")
    public void updateProduct(@RequestBody Book book) {
        bookDao.save(book);
    }

    @PostMapping(value = "/book")
    @CrossOrigin
    public Book createBook(@ModelAttribute ReqCreateBook reqBook, Principal principal) throws IOException {
        User currentUser = userDao.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        Book newBook = new Book();
        newBook.setName(reqBook.getName());
        newBook.setAuthor(reqBook.getAuthor());
        newBook.setGenre(reqBook.getGenre());
        newBook.setPrice(reqBook.getPrice());
        newBook.setPublisher(reqBook.getPublisher());
        newBook.setUser(currentUser);
        newBook.setType(Type.SELL.name());
        newBook.setDescription(reqBook.getDescription());
        long length = bookDao.count();
        String path = fileService.storeFile(reqBook.getImage(), "BK", length);
        newBook.setPhotoPath(path);
        return bookDao.save(newBook);
    }
    @PostMapping(value = "/exchange-book")
    @CrossOrigin
    public Book putForExchangeBook(@ModelAttribute ReqCreateBook reqBook, Principal principal) throws IOException {
        User currentUser = userDao.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        Book newBook = new Book();
        newBook.setName(reqBook.getName());
        newBook.setAuthor(reqBook.getAuthor());
        newBook.setGenre(reqBook.getGenre());
        newBook.setPrice(reqBook.getPrice());
        newBook.setPublisher(reqBook.getPublisher());
        newBook.setUser(currentUser);
        newBook.setType(Type.EXCHANGE.name());
        newBook.setDescription(reqBook.getDescription());
        long length = bookDao.count() + 1;
        String path = fileService.storeFile(reqBook.getImage(), "BK", length);
        newBook.setPhotoPath(path);
        return bookDao.save(newBook);

    }

}
