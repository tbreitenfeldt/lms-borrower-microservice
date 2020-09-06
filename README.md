# Library Management System
## Borrower Service

This microservice is apart of the Library Management System project. To clone the entire project use the recursive flag and this repository:

git clone --recursive https://github.com/tbreitenfeldt/library-management-system.git

### Description 

Spring Boot microservice. Provides Library Management System functionality for a library user via REST API.

Check a book out:
`POST /borrower/borrowers/:borrowerId/branches/:branchId/books/:bookId:checkout`

Check a book in:
`POST /borrower/borrowers/:borrowerId/branches/:branchId/books/:bookId:checkin`

Get all library branches:
`GET /borrower/branches`

Get all books that are available at a branch and that are not currently checkout out by user:
`GET /borrowers/:borrowerId/branches/:branchId/available-books/`

Get all books the user has borrowed and not yet returned:
`GET /branches/:branchId/borrowers/:borrowerId`