package com.smoothstack.lms.borrowerservice.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.smoothstack.lms.borrowerservice.dao.*;
import com.smoothstack.lms.borrowerservice.entity.*;
import com.smoothstack.lms.borrowerservice.entity.BookLoan.BookLoanId;
import com.smoothstack.lms.borrowerservice.controller.BorrowerController;


@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class BorrowerApplicationIntegrationTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private BookLoanDAO bookLoanDao;

  @Autowired
  private LibraryBranchDAO libraryBranchDao;

  @Autowired
  private BorrowerController controller;

  @Test
  public void contextLoads() throws Exception {
    assertNotNull(controller);
  }

  BookLoanId bookLoanId = new BookLoanId(1, 1, 1);

  @Test
  void checkoutBook() throws Exception {

    mockMvc.perform(post("/lms/borrower/borrowers/book/checkout")
           .contentType("application/json")
           .content(objectMapper.writeValueAsString(bookLoanId)))
           .andExpect(status().isCreated());

    List<BookLoan> bookLoans = bookLoanDao.findAll().stream()
            .filter(l -> (
              l.getId().getBorrower().getId() == 1
              &&
              l.getId().getBook().getId() == 1
              &&
              l.getDateIn() == null))
            .collect(Collectors.toList());

    assertEquals(1, bookLoans.size());
  }

  @Test
  void checkinBook() throws Exception {

    mockMvc.perform(post("/lms/borrower/borrowers/book/checkin")
           .contentType("application/json")
           .content(objectMapper.writeValueAsString(bookLoanId)))
           .andExpect(status().isCreated());

    List<BookLoan> bookLoans = bookLoanDao.findAll().stream()
      .filter(l -> (
        l.getId().getBorrower().getId() == 1
        &&
        l.getId().getBook().getId() == 1
        &&
        l.getDateIn() == null))
      .collect(Collectors.toList());

    assertEquals(1, bookLoans.size());
  }

  @Test
  void getBranches() throws Exception {

    MvcResult result = mockMvc.perform(get("/lms/borrower/branches"))
                              .andExpect(status().isOk())
                              .andReturn();

    String contentAsString = result.getResponse().getContentAsString();

    List<LibraryBranch> response = objectMapper.readValue(contentAsString, ArrayList.class);

    assertTrue(response.size() > 0);
  }

  @Test
  void getAvailableBooks() throws Exception {

    MvcResult result = mockMvc.perform(get("/lms/borrower/borrowers/1/branches/1/available-books/"))
                              .andExpect(status().isOk())
                              .andReturn();

    String contentAsString = result.getResponse().getContentAsString();
    List<Book> response = objectMapper.readValue(contentAsString, ArrayList.class);

    assertNotNull(response);
  }

  @Test
  void getLoans() throws Exception {

    mockMvc.perform(post("/lms/borrower/borrowers/book/checkout")
           .contentType("application/json")
           .content(objectMapper.writeValueAsString(bookLoanId)))
           .andExpect(status().isCreated());

    MvcResult result = mockMvc.perform(get("/lms/borrower/borrowers/1/loans"))
                              .andExpect(status().isOk())
                              .andReturn();

    String contentAsString = result.getResponse().getContentAsString();
    List<BookLoan> response = objectMapper.readValue(contentAsString, ArrayList.class);

    assertTrue(response.size() > 0);
  }
}
