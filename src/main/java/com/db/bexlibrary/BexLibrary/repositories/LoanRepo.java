package com.db.bexlibrary.BexLibrary.repositories;

import com.db.bexlibrary.BexLibrary.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepo extends JpaRepository<Loan,Integer> {

    List<Loan> findLoansByIsReturnedIsFalse();
}
