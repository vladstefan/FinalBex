package com.db.bexlibrary.BexLibrary.service;

import static com.db.bexlibrary.BexLibrary.security.SecurityConstants.PREFIX_SIZE;
import static com.db.bexlibrary.BexLibrary.security.SecurityConstants.SECRET;
import static com.db.bexlibrary.BexLibrary.security.SecurityConstants.TOKEN_PREFIX;

import com.db.bexlibrary.BexLibrary.entities.Book;
import com.db.bexlibrary.BexLibrary.entities.Loan;
import com.db.bexlibrary.BexLibrary.entities.User;
import com.db.bexlibrary.BexLibrary.pojos.LoanPOJO;
import com.db.bexlibrary.BexLibrary.pojos.SimpleLoan;
import com.db.bexlibrary.BexLibrary.repositories.BookRepo;
import com.db.bexlibrary.BexLibrary.repositories.LoanRepo;
import com.db.bexlibrary.BexLibrary.repositories.UserRepo;
import io.jsonwebtoken.Jwts;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public String getUsername(String token) {
        token = token.substring(PREFIX_SIZE);
        String username = null;
        username = Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody().getSubject();


        return username;
    }

    public List<SimpleLoan> getAllLoans() {

        List<Loan> loans = loanRepo.findLoansByIsReturnedIsFalse();
        List<SimpleLoan> simpleLoans = new ArrayList<>();
        for (Loan l : loans
                ) {
            SimpleLoan simpleLoan = new SimpleLoan(l.getId(), l.getLoanBook().getId(), l.getLoanBook().getTitle(), l.getReturnDate(), l.getLoanUser().getEmail());
            simpleLoans.add(simpleLoan);
        }
        return simpleLoans;
    }

    public Loan borrowMethod(LoanPOJO input, HttpServletRequest request) {
        Loan loan = new Loan();
        String token = request.getHeader("Cookie");
        String username = getUsername(token);
        Book book = bookRepo.findBookById(input.getBookId());
        loan.setLoanBook(book);
        int period = input.getBookPeriod();

        Timestamp currDate = Timestamp.valueOf(LocalDateTime.now());
        loan.setBorrowDate(currDate);
        LocalDateTime plusWeek = LocalDateTime.now().plus(period, ChronoUnit.WEEKS);
        Timestamp retDate = Timestamp.valueOf(plusWeek);
        System.out.println(currDate + "--------" + retDate);
        loan.setReturnDate(retDate);
        User user = userRepo.findUserByEmail(username);
        loan.setLoanUser(user);
        loan.setReturned(false);


        if (book.getNoAvailableCopies() > 0) {
            String date = retDate.toString().substring(0, 10);
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

    public void returnBookMethod(Long bookId, Long loanId) {

        loanRepo.updateLoan(loanId);
        bookRepo.updateReturnedBook(bookId);
        }

}