package com.db.bexlibrary.BexLibrary.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Category extends BaseEntity {


    @NotNull
    private String categoryName;
    @OneToMany(mappedBy = "category")
    private List<Book> booklist;


}
