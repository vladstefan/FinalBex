package com.db.bexlibrary.BexLibrary.controllers;

import com.db.bexlibrary.BexLibrary.entities.Category;
import com.db.bexlibrary.BexLibrary.repositories.CategoryRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class CategoryController {

  @Autowired
  public CategoryRepo categoryRepo;


  @GetMapping(value = "/categories")
  public ResponseEntity<?>  generate() {
    return new ResponseEntity<List<Category>>(categoryRepo.findAll(),HttpStatus.OK);
  }

}
