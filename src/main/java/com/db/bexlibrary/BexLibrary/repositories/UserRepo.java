package com.db.bexlibrary.BexLibrary.repositories;

import com.db.bexlibrary.BexLibrary.entities.Book;
import com.db.bexlibrary.BexLibrary.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

  User findUserByEmail(String email);

  @Query("select u.books from User u where u.email=?1")
  List<Book> findFavorites(String email);



  @Query("update User user  set user.noPen=?1 where l.id=?2")
  @Modifying
  @Transactional
  void updateUserPen(int numberOfPenalties,String email);

}
