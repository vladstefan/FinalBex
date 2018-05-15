package com.db.bexlibrary.BexLibrary.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class BaseEntity {

  @Id
  @GeneratedValue(
      strategy= GenerationType.AUTO,
      generator="native"
  )
  @GenericGenerator(
      name = "native",
      strategy = "native"
  )
  protected Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
