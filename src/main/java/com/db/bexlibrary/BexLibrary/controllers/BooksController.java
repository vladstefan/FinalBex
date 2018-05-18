package com.db.bexlibrary.BexLibrary.controllers;

import com.db.bexlibrary.BexLibrary.entities.Book;
import com.db.bexlibrary.BexLibrary.repositories.BookRepo;
import com.db.bexlibrary.BexLibrary.service.BookService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class BooksController {


  @Autowired
  private BookRepo bookRepo;

  @Autowired
  private BookService bookService;


  @GetMapping(value = "/books")
  public ResponseEntity<?> findBooks() {
    return new ResponseEntity<List<Book>>(bookRepo.findAll(), HttpStatus.OK);
  }


  @GetMapping(value = "/booksbyauthor")
  public ResponseEntity<?> findBookByAuthor(@RequestParam(name = "name") String name) {
    return new ResponseEntity<Set<Book>>(bookService.findBooksByAuthorName(name), HttpStatus.OK);
  }

  @GetMapping(value = "/popular")
  public ResponseEntity<?> findPopular() {
    return new ResponseEntity<List<Book>>(
        bookRepo.findBooksByRatingGreaterThanEqualOrderByRatingDesc(3.5).subList(0, 5),
        HttpStatus.OK);
  }

  @GetMapping(value = "/booksbycategory/{category}")
  public ResponseEntity<?> findByCategory(@PathVariable String category) {
    return new ResponseEntity<List<Book>>(bookService.findBooksByCathegory(category),
        HttpStatus.OK);
  }
}
