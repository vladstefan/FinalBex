package com.db.bexlibrary.BexLibrary.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Category extends BaseEntity {


    @NotNull
    private String categoryName;
    @OneToMany(mappedBy = "category")
    private List<Book> booklist;

}
