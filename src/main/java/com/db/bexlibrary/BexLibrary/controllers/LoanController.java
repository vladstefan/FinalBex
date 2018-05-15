package com.db.bexlibrary.BexLibrary.controllers;

import com.db.bexlibrary.BexLibrary.entities.Loan;
import com.db.bexlibrary.BexLibrary.entities.LoanPOJO;
import com.db.bexlibrary.BexLibrary.javamail.MailSender;
import com.db.bexlibrary.BexLibrary.repositories.BookRepo;
import com.db.bexlibrary.BexLibrary.repositories.LoanRepo;
import com.db.bexlibrary.BexLibrary.repositories.UserRepo;
import com.db.bexlibrary.BexLibrary.service.LoanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class LoanController {

  @Autowired
  LoanRepo loanRepo;
  @Autowired
  BookRepo bookRepo;
  @Autowired
  UserRepo userRepo;
  @Autowired
  MailSender mailSender;
  @Autowired
  private LoanService loanService;


  @RequestMapping(value = "/loans", method = {RequestMethod.POST, RequestMethod.OPTIONS})
  @ResponseBody
  public Loan borrowMethod(@RequestBody LoanPOJO input) {
    return loanService.borrowMethod(input);
  }

  @GetMapping("/admin")
  public List<Loan> getAllLoans() {
    return loanService.getAllLoans();
  }

}
