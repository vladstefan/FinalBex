package com.db.bexlibrary.BexLibrary.controllers;

import com.db.bexlibrary.BexLibrary.entities.Author;
import com.db.bexlibrary.BexLibrary.repositories.AuthorRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

  @Autowired
  AuthorRepo authorRepo;

  @GetMapping(value = "/authors")
  public ResponseEntity<?> generate() {
    return new ResponseEntity<List<Author>>(authorRepo.findAll(),HttpStatus.OK);
  }

}
