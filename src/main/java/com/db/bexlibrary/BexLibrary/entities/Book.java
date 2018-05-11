package com.db.bexlibrary.BexLibrary.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String title;

    @Nullable
    private double rating = 0.0;

    @Nullable
    private int givenRatings = 0;

    @NotNull
    private int noCopies;

    @NotNull
    private int noAvailableCopies;

    @NotNull
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "fk_bookCategory")
    private Category category;

    @ManyToMany
    @JoinTable(name = "Book_Author",joinColumns = {@JoinColumn(name="fk_idBook")}, inverseJoinColumns ={ @JoinColumn(name="fk_idAuthor")})
    private List<Author> author;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<KeyWords> keyWords;

    @JsonBackReference
    @OneToMany(mappedBy = "loanBook")
    private List<Loan>  bookLoan;

    @ManyToMany(mappedBy = "books")
    private List<User> users;

    @Column(name="img_path")
    private String imgPath;

    @Override
    public String toString() {
        return "Book{" +
                ", title='" + title + '\'' +
                '}';
    }

}
