package com.db.bexlibrary.BexLibrary.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String authorName;

    @ManyToMany(mappedBy ="author")
    @JsonIgnore
    private List<Book> book;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", book=" + book +
                '}';
    }

}
