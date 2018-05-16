package com.db.bexlibrary.BexLibrary.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class KeyWords extends BaseEntity {
=======
public class KeyWords {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
>>>>>>> origin/loginFix

  @NotNull
  private String word;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "fk_bookKeyword")
  private Book book;

}
