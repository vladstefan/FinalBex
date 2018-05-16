package com.db.bexlibrary.BexLibrary.entities;

public class AppUser {

  String name;
  String password;

  public AppUser(String name, String password) {
    this.name = name;
    this.password = password;
  }

  public AppUser() {

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}