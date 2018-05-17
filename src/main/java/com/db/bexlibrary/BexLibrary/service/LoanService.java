package com.db.bexlibrary.BexLibrary.service;

import com.db.bexlibrary.BexLibrary.entities.*;
import com.db.bexlibrary.BexLibrary.pojos.LoanPOJO;
import com.db.bexlibrary.BexLibrary.repositories.BookRepo;
import com.db.bexlibrary.BexLibrary.repositories.LoanRepo;
import com.db.bexlibrary.BexLibrary.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LoanService {

    private LoanRepo loanRepo;

    @Autowired
    public LoanService(LoanRepo loanRepo) {
        this.loanRepo = loanRepo;
    }

    @Autowired
    BookRepo bookRepo;

    @Autowired
    UserRepo userRepo;

    public List<Loan> getAllLoans(){
        return  loanRepo.findAll();
    }

    public Loan borrowMethod(LoanPOJO input){
        Loan loan = new Loan();

        Book book = bookRepo.findBookById(input.getBookId());
        loan.setLoanBook(book);
        int period = input.getBookPeriod();

        Timestamp currDate = Timestamp.valueOf(LocalDateTime.now());
        loan.setBorrowDate(currDate);
        LocalDateTime plusWeek = LocalDateTime.now().plus(period, ChronoUnit.WEEKS);
        Timestamp retDate = Timestamp.valueOf(plusWeek);
        System.out.println(currDate + "--------" + retDate);
        loan.setReturnDate(retDate);

       // User user = userRepo.findUserByEmail(GlobalVariables.getInstance().getEmail());
       // loan.setLoanUser(user);

        loan.setReturned(false);


        if (book.getNoAvailableCopies() > 0) {
            String date=retDate.toString().substring(0,10);
            String confirmMail = "Your book (" + book.getTitle() + ") was successfully reserved. We are waiting for you at the BEX Library(1st floor) to pick it up.\n" +
                    "For more information you can contact Admin at admin@gmail.com.\n" +
                    "Please return the book until " + date + "\n" +
                    "Thank you for using BEX Library app.";

            loanRepo.save(loan);
            bookRepo.updateBook(book.getId());
//          mailSender.sendMail("bexlibrary18@gmail.com", user.getEmail(), "Confirmation email", confirmMail);


        }

        return loan;
    }

    public void returnBookMethod(Long bookId, Long loanId){

        loanRepo.updateLoan(loanId);
        bookRepo.updateReturnedBook(bookId);
    }
}
