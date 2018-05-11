package com.db.bexlibrary.BexLibrary.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Data
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id") private int id;

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
