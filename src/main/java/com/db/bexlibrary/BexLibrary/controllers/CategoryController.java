package com.db.bexlibrary.BexLibrary.controllers;

import com.db.bexlibrary.BexLibrary.entities.Category;
import com.db.bexlibrary.BexLibrary.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class CategoryController {

    @Autowired
    public CategoryRepo categoryRepo;


    @GetMapping(value = "/categories")
    public List<Category> generate() {
        return categoryRepo.findAll();
    }

}
