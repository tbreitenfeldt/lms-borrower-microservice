package com.smoothstack.lms.borrowerservice.unit;

import java.util.List;
import java.util.ArrayList;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.smoothstack.lms.borrowerservice.entity.Book;
import com.smoothstack.lms.borrowerservice.entity.BookLoan;
import com.smoothstack.lms.borrowerservice.entity.BookLoan.BookLoanId;
import com.smoothstack.lms.borrowerservice.entity.LibraryBranch;
import com.smoothstack.lms.borrowerservice.controller.BorrowerController;
import com.smoothstack.lms.borrowerservice.service.BorrowerService;



@ExtendWith(MockitoExtension.class)
class BorrowerApplicationUnitTests {

  @Mock
  BorrowerService borrowerService;

  @InjectMocks
  BorrowerController borrowerController;

  @Test
  public void testCheckoutBook() {
      MockHttpServletRequest request = new MockHttpServletRequest();
      RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

      when(borrowerService.checkOutBook(any(BookLoanId.class))).thenReturn(new BookLoan());

      BookLoanId bookLoanId = new BookLoanId(1, 1, 1);
      ResponseEntity<BookLoan> responseEntity = borrowerController.checkOutBook(bookLoanId);

      assertTrue(responseEntity.getStatusCodeValue() == 201);
  }

  @Test
  public void testCheckInBook() {
      MockHttpServletRequest request = new MockHttpServletRequest();
      RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

      when(borrowerService.checkInBook(any(BookLoanId.class))).thenReturn(new BookLoan());

      BookLoanId bookLoanId = new BookLoanId(1, 1, 1);
      ResponseEntity<BookLoan> responseEntity = borrowerController.checkInBook(bookLoanId);

      assertTrue(responseEntity.getStatusCodeValue() == 201);
  }

  @Test
  public void testGetBranches() {
      MockHttpServletRequest request = new MockHttpServletRequest();
      RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

      when(borrowerService.getLibraryBranches()).thenReturn(new ArrayList<LibraryBranch>());
      ResponseEntity<List<LibraryBranch>> responseEntity = borrowerController.getLibraryBranches();

      assertTrue(responseEntity.getStatusCodeValue() == 200);
  }

  @Test
  public void testGetAvailableBooks() {
      MockHttpServletRequest request = new MockHttpServletRequest();
      RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

      when(borrowerService.getAvailableBooksNotCheckedOut(1, 1)).thenReturn(new ArrayList<Book>());
      ResponseEntity<List<Book>> responseEntity = borrowerController.getAvailableBooksNotCheckedOut(1, 1);

      assertTrue(responseEntity.getStatusCodeValue() == 200);
  }

  @Test
  public void testGetLoans() {
      MockHttpServletRequest request = new MockHttpServletRequest();
      RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

      when(borrowerService.getBookLoansForBorrower(1)).thenReturn(new ArrayList<BookLoan>());
      ResponseEntity<List<BookLoan>> responseEntity = borrowerController.getBookLoans(1);

      assertTrue(responseEntity.getStatusCodeValue() == 200);
  }

}
