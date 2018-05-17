package com.db.bexlibrary.BexLibrary.pojos;

public class AppUserDTO {

  String name;
  String password;

  public AppUserDTO(String name, String password) {
    this.name = name;
    this.password = password;
  }

  public AppUserDTO() {

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
