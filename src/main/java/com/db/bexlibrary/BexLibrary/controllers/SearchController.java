package com.db.bexlibrary.BexLibrary.controllers;

import com.db.bexlibrary.BexLibrary.entities.Book;
import com.db.bexlibrary.BexLibrary.repositories.BookRepo;
import com.db.bexlibrary.BexLibrary.service.BookService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class SearchController {

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private BookService bookService;

    @GetMapping(value="/search/{title}")
    private List<Book> searchMethod(@PathVariable String title){
        List<Book> results=new ArrayList<>();

        results.addAll(bookService.findBooksByAuthorName(title));
        results.addAll(bookRepo.findBooksByTitleContaining(title));
        return results;
    }

}
