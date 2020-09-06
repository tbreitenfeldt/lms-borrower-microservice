package com.smoothstack.lms.borrowerservice.controller;

import java.util.List;
import javax.validation.Valid;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

import com.smoothstack.lms.borrowerservice.entity.Book;
import com.smoothstack.lms.borrowerservice.entity.BookLoan;
import com.smoothstack.lms.borrowerservice.entity.LibraryBranch;
import com.smoothstack.lms.borrowerservice.entity.BookLoan.BookLoanId;
import com.smoothstack.lms.borrowerservice.service.BorrowerService;
import com.smoothstack.lms.borrowerservice.exception.ArgumentMissingException;
import com.smoothstack.lms.borrowerservice.exception.IllegalRelationReferenceException;



@RestController
@RequestMapping("/lms/borrower")
public class BorrowerController {
    private static final Logger logger = LogManager.getLogger(BorrowerController.class);

    @Autowired
    BorrowerService borrowerService;

    @PostMapping("/borrowers/book/checkout")
    public ResponseEntity<BookLoan> checkOutBook(@RequestBody @Valid BookLoanId id) {
        BookLoan response = null;
        try {
            logger.debug("request: {}", id.toString());
            response = borrowerService.checkOutBook(id);
            logger.debug("response: {}", response.toString());
        } catch (NoSuchElementException noSuchElementException) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, noSuchElementException.getMessage());
        } catch (ArgumentMissingException argumentMissingException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, argumentMissingException.getMessage());
        } catch (IllegalRelationReferenceException illegalRelationReferenceException) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception exception) {
            logger.error(exception.toString());
        }
        return new ResponseEntity<BookLoan>(response, HttpStatus.CREATED);
    }

    @PostMapping("/borrowers/book/checkin")
    public ResponseEntity<BookLoan> checkInBook(@RequestBody @Valid BookLoanId id) {
        BookLoan response = null;
        try {
            logger.debug("request: {}", id.toString());
            response = borrowerService.checkInBook(id);
            logger.debug("response: {}", response.toString());
        } catch (ArgumentMissingException argumentMissingException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, argumentMissingException.getMessage());
        } catch (IllegalRelationReferenceException illegalRelationReferenceException) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception exception) {
            logger.error(exception.toString());
        }
        return new ResponseEntity<BookLoan>(response, HttpStatus.CREATED);
    }

    @GetMapping("/branches")
    public ResponseEntity<List<LibraryBranch>> getLibraryBranches() {
        List<LibraryBranch> response = null;
        try {
            response = borrowerService.getLibraryBranches();
            logger.debug("response: {}", response.toString());
        } catch (Exception exception) {
            logger.error(exception.toString());
        }
        return new ResponseEntity<List<LibraryBranch>>(response, HttpStatus.OK);
    }

    @GetMapping("/borrowers/{borrowerId}/branches/{branchId}/available-books/")
    public ResponseEntity<List<Book>> getAvailableBooksNotCheckedOut(@PathVariable("branchId") long branchId,
            @PathVariable("borrowerId") long borrowerId) {
        List<Book> response = null;

        try {
            logger.debug("request: {}", branchId);
            response = borrowerService.getAvailableBooksNotCheckedOut(branchId, borrowerId);
            logger.debug("response: {}", response.toString());
        } catch (IllegalRelationReferenceException irre) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, irre.getMessage(), irre);
        } catch (Exception exception) {
            logger.error(exception.toString());
        }

        return new ResponseEntity<List<Book>>(response, HttpStatus.OK);
    }

    @GetMapping("/borrowers/{borrowerId}/loans")
    public ResponseEntity<List<BookLoan>> getBookLoans(@PathVariable("borrowerId") long borrowerId) {
        List<BookLoan> response = null;

        try {
            logger.debug("request: {}", borrowerId);
            response = borrowerService.getBookLoansForBorrower(borrowerId);
            logger.debug("response: {}", response.toString());
        } catch (IllegalRelationReferenceException irre) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, irre.getMessage(), irre);
        } catch (Exception exception) {
            logger.error(exception.toString());
        }

        return new ResponseEntity<List<BookLoan>>(response, HttpStatus.OK);
    }
}
