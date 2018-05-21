package com.db.bexlibrary.BexLibrary.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.sql.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Entity
public class User {
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "email")
  private String email;
  @NotNull
  @Column(name = "password")
  private String password;
  @NotNull
  @Column(name = "noPen")
  private int noPen = 0;
  @NotNull
  @Column(name = "isBlackList")
  private boolean isBlacklist;
  @Nullable
  @Column(name = "blackDate")
  private Date blackDate;
  @NotNull
  @Column(name = "isAdmin")
  private boolean isAdmin = false;
  @NotNull
  @Column(name = "noBorrowedBooks")
  private int noBorrowedBooks = 0;
  @JsonBackReference
  @OneToMany(mappedBy = "loanUser")
  private List<Loan> loanList;
  @ManyToMany
  @JoinTable(name = "Wishlist", joinColumns = {
      @JoinColumn(name = "fk_idUser")}, inverseJoinColumns = {@JoinColumn(name = "fk_idBook")})
  private List<Book> books;

  public User() {
  }

}