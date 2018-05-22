package com.db.bexlibrary.BexLibrary.pojos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;

@JsonAutoDetect
public class SimpleLoan {

  @JsonProperty
  private Long loanId;
  @JsonProperty
  private Long bookId;
  @JsonProperty
  private String title;
  @JsonProperty
  private Timestamp returnDate;
  @JsonProperty
  private String userEmail;

  public SimpleLoan(Long loanId, Long bookId, String title, Timestamp returnDate,
      String userEmail) {
    this.loanId = loanId;
    this.bookId = bookId;
    this.title = title;
    this.returnDate = returnDate;
    this.userEmail = userEmail;
  }

}
