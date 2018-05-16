package com.db.bexlibrary.BexLibrary.entities;

import lombok.Data;

@Data
public class GlobalVariables {

  private static GlobalVariables globalVariables = null;
  public String email;

  public static GlobalVariables getInstance() {
    if (globalVariables != null) {
      return globalVariables;
    } else {
      globalVariables = new GlobalVariables();
    }
    return globalVariables;
  }

}
