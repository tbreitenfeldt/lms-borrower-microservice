package com.smoothstack.lms.borrowerservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.borrowerservice.entity.BookCopy;
import com.smoothstack.lms.borrowerservice.entity.BookCopy.BookCopyId;

@Repository
public interface BookCopyDAO extends JpaRepository<BookCopy, BookCopyId> { }
