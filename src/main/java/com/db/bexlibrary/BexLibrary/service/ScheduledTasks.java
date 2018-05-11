package com.db.bexlibrary.BexLibrary.service;

import com.db.bexlibrary.BexLibrary.entities.Loan;
import com.db.bexlibrary.BexLibrary.repositories.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import com.db.bexlibrary.BexLibrary.javamail.MailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    MailSender mailSender;
    @Autowired
    LoanRepo loanRepo;

    @Scheduled(fixedRate = 120000)
    public void sendReminderMethod() {
        List<Loan> loans = loanRepo.findLoansByIsReturnedIsFalse();
        for (Loan loan : loans
                ) {
            LocalDate startDate = loan.getBorrowDate().toLocalDateTime().toLocalDate();
            LocalDate endDate = loan.getReturnDate().toLocalDateTime().toLocalDate().plusDays(3);
            Period duration =Period.between(startDate, endDate);
            if (duration.getDays() < 20) {
                String body="Please return your book (" +loan.getLoanBook().getTitle()+") "+
                        "For more information you can contact Admin at admin@gmail.com.\n" +
                        "Thank you for using BEX Library app.\n" +
                        "Please do not reply to this email, as we are unable to respond from this address.";
//                mailSender.sendMail("bexlibrary18@gmail.com", loan.getLoanUser().getEmail(), "Reminder", body);
          }
        }
   }

}