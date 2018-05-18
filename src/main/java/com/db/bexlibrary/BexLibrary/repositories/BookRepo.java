package com.db.bexlibrary.BexLibrary.repositories;

import com.db.bexlibrary.BexLibrary.entities.Author;
import com.db.bexlibrary.BexLibrary.entities.Book;
import com.db.bexlibrary.BexLibrary.entities.Category;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BookRepo extends JpaRepository<Book, Long> {

  Book findBookById(Long id);


  List<Book> findBooksByTitleContaining(String title);

  @Query("update Book b set b.noAvailableCopies=b.noAvailableCopies-1 where b.id=?1")
  @Modifying
  void updateBook(Long id);

  List<Book> findBooksByAuthor(Author a);

  List<Book> findBooksByRatingGreaterThanEqualOrderByRatingDesc(double rating);

  List<Book> findBooksByCategory(Category category);

  @Query("update Book b set b.noAvailableCopies=b.noAvailableCopies+1 where b.id=?1")
  @Modifying
  void updateReturnedBook(Long id);


}


