package com.db.bexlibrary.BexLibrary.repositories;

import com.db.bexlibrary.BexLibrary.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepo extends JpaRepository<Author, Long> {

    List<Author> findAuthorByAuthorNameContaining(String name);

}
