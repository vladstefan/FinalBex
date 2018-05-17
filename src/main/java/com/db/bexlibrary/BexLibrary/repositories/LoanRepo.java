package com.db.bexlibrary.BexLibrary.repositories;

import com.db.bexlibrary.BexLibrary.entities.Loan;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepo extends JpaRepository<Loan, Integer> {

  List<Loan> findLoansByIsReturnedIsFalse();

  @Query("update Loan l  set l.isReturned=1 where l.id=?1")
  @Modifying
  @Transactional
  void updateLoan(Long id);
}
