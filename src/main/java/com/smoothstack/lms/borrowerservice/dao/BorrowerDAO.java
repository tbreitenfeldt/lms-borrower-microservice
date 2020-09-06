package com.smoothstack.lms.borrowerservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.borrowerservice.entity.*;

@Repository
public interface BorrowerDAO extends JpaRepository<Borrower, Long> {
}
