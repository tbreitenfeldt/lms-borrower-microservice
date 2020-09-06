package com.smoothstack.lms.borrowerservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.borrowerservice.entity.BookLoan;
import com.smoothstack.lms.borrowerservice.entity.BookLoan.BookLoanId;

@Repository
public interface BookLoanDAO extends JpaRepository<BookLoan, BookLoanId> {
}
