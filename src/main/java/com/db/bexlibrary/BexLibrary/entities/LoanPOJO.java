package com.db.bexlibrary.BexLibrary.entities;

import lombok.Data;

@Data
public class LoanPOJO {

    private Long bookId;

    private String bookTitle;

    private int bookPeriod;

    private String userEmail;

}
