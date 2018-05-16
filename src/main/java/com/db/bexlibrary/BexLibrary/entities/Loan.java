package com.db.bexlibrary.BexLibrary.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
<<<<<<< HEAD
public class Loan extends BaseEntity {
=======
public class Loan {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private int id;
>>>>>>> origin/loginFix

  @NotNull
  private Timestamp borrowDate;

  @NotNull
  private Timestamp returnDate;

  @NotNull
  private boolean isReturned = false;

  @JsonManagedReference
  @ManyToOne
  @JoinColumn(name = "fk_loanUser")
  private User loanUser;

  @JsonManagedReference
  @ManyToOne
  @JoinColumn(name = "fk_loanBook")
  private Book loanBook;

  public boolean isReturned() {
    return isReturned;
  }

}
