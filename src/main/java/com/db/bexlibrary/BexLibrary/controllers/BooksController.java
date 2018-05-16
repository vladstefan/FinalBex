package com.db.bexlibrary.BexLibrary.controllers;

import com.db.bexlibrary.BexLibrary.entities.Book;
import com.db.bexlibrary.BexLibrary.repositories.BookRepo;
import com.db.bexlibrary.BexLibrary.service.BookService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
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
  public List<Book> findBooks() {
    return bookRepo.findAll();
  }


  @GetMapping(value = "/booksbyauthor")
  public Set<Book> findBookByAuthor(@RequestParam(name = "name") String name) {
    return bookService.findBooksByAuthorName(name);
  }

  @GetMapping(value = "/popular")
  public List<Book> findPopular() {
    return bookRepo.findBooksByRatingGreaterThanEqualOrderByRatingDesc(3.5).subList(0, 5);
  }

  @GetMapping(value = "booksbycategory/{category}")
  public List<Book> findByCategory(@PathVariable String category) {
    return bookService.findBooksByCathegory(category);
  }


}
