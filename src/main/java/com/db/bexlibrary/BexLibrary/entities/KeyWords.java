package com.db.bexlibrary.BexLibrary.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class KeyWords {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String word;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "fk_bookKeyword")
    private Book book;

}
