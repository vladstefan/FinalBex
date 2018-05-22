package com.db.bexlibrary.BexLibrary.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class LoginController {

  private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

  @GetMapping(value = "/permissions")
  private void tryPermissions(){
    logger.info("Test endpoint for user permissions");
  }

  @GetMapping(value = "/signout")
  private void signout(HttpServletResponse response){
    response.addHeader("Set-Cookie", "Logging out");
  }


}