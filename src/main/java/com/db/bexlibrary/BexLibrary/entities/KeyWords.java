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

public class KeyWords extends BaseEntity {

  @NotNull
  private String word;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "fk_bookKeyword")
  private Book book;

}
