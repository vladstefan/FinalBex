package com.db.bexlibrary.BexLibrary.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.db.bexlibrary.BexLibrary.entities.Book;
import com.db.bexlibrary.BexLibrary.entities.Loan;
import com.db.bexlibrary.BexLibrary.entities.User;
import com.db.bexlibrary.BexLibrary.pojos.LoanPOJO;
import com.db.bexlibrary.BexLibrary.pojos.SimpleLoan;
import com.db.bexlibrary.BexLibrary.repositories.BookRepo;
import com.db.bexlibrary.BexLibrary.repositories.LoanRepo;
import com.db.bexlibrary.BexLibrary.repositories.UserRepo;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceTest {

  @InjectMocks
  LoanService underTest;

  @Mock
  BookRepo bookRepo;

  @Mock
  LoanRepo loanRepo;

  @Before
  public void setup(){
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void getAllLoans() {
    final Loan loan=new Loan();
    loan.setId(2L);
    final Book loanBook=new Book();
    final User loanUser=new User();
    loanBook.setTitle("Titlu");
    loanBook.setId(1L);
    loanUser.setEmail("email");
    loan.setLoanUser(loanUser);
    loan.setLoanBook(loanBook);

    when(loanRepo.findLoansByIsReturnedIsFalse()).thenReturn(Collections.singletonList(loan));
    final List<SimpleLoan> allLoans=underTest.getAllLoans();
    assertEquals("Should return a list with one value",1,allLoans.size());

  }

  @Test
  public void testAllLoansLoanRepoReturnsEmptyList(){
    when(loanRepo.findLoansByIsReturnedIsFalse()).thenReturn(Collections.emptyList());
    final List<SimpleLoan> allLoans=underTest.getAllLoans();
    assertEquals("Should return an empty list",0,allLoans.size());
  }

  @Test
  public void borrowMethodWithNoAvailableCopies() {

    Book book=new Book();
    book.setTitle("Title");
    book.setId(23L);
    book.setNoAvailableCopies(0);

    when(bookRepo.findBookById(2L)).thenReturn(book);

    LoanPOJO loanPOJO=new LoanPOJO();
    loanPOJO.setBookId(2L);
    underTest.borrowMethod(loanPOJO);

    final Loan result=underTest.borrowMethod(loanPOJO);

    assertNotNull(result);
    final Book loanBook= result.getLoanBook();

    verify(bookRepo,times(0)).save(any(Book.class));

    assertEquals("Title", loanBook.getTitle());
    assertEquals(new Long (23),loanBook.getId());
  }

  @Test
  public void borrowMethodWithAvailableCopies() {

    Book book=new Book();
    book.setTitle("Title");
    book.setId(23L);
    book.setNoAvailableCopies(2);

    when(bookRepo.findBookById(2L)).thenReturn(book);

    LoanPOJO loanPOJO=new LoanPOJO();
    loanPOJO.setBookId(2L);

    final Loan result=underTest.borrowMethod(loanPOJO);
    final Book loanBook= result.getLoanBook();

    verify(loanRepo, times(1)).save(any(Loan.class));
    verify(bookRepo,times(1)).updateBook(23L);


    assertEquals("Title", loanBook.getTitle());
    assertEquals(new Long (23),loanBook.getId());

  }
  @Test
  public void returnBookMethod() {

    underTest.returnBookMethod(2L,3L);
    verify(loanRepo,times(1)).updateLoan(anyLong());
    verify(bookRepo,times(1)).updateReturnedBook(anyLong());

  }
}
