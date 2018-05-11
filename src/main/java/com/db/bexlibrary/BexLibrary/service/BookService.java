package com.db.bexlibrary.BexLibrary.service;

import com.db.bexlibrary.BexLibrary.entities.Author;
import com.db.bexlibrary.BexLibrary.entities.Book;
import com.db.bexlibrary.BexLibrary.entities.Category;
import com.db.bexlibrary.BexLibrary.repositories.AuthorRepo;
import com.db.bexlibrary.BexLibrary.repositories.BookRepo;
import com.db.bexlibrary.BexLibrary.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    public Set<Book> findBooksByAuthorName(String name){

        List<Author> authors = new ArrayList<>();
        authors.addAll(authorRepo.findAuthorByAuthorNameContaining(name));
        List<Book> result = new ArrayList();
        for (Author a:authors
             ) {
            result.addAll(bookRepo.findBooksByAuthor(a));
        }

        Set<Book> uniqueResult = new HashSet<Book>(result);

        return uniqueResult;

    }

    public List<Book> findBooksByCathegory(String name){

        List<Category> categories = new ArrayList<>();
        categories.addAll(categoryRepo.findCategoryByCategoryName(name));

        return bookRepo.findBooksByCategory(categories.get(0));

    }

}
