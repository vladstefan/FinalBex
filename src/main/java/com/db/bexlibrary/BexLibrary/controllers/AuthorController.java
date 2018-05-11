package com.db.bexlibrary.BexLibrary.controllers;

import com.db.bexlibrary.BexLibrary.entities.Author;

import com.db.bexlibrary.BexLibrary.repositories.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    AuthorRepo authorRepo;

    @GetMapping(value = "/authors")
    public List<Author> generate(){
        return authorRepo.findAll();
    }

}
