CREATE TABLE IF NOT EXISTS borrower (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  address varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  phone_number varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS library_branch (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  address varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS author (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS publisher (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  address varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  phone_number varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS genre (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS book (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  title varchar(255) DEFAULT NULL,
  publisher_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (publisher_id) REFERENCES publisher (id)
);

CREATE TABLE IF NOT EXISTS book_authors (
  book_id bigint(20) NOT NULL,
  author_id bigint(20) NOT NULL,
  PRIMARY KEY (book_id,author_id),
  FOREIGN KEY (author_id) REFERENCES author (id),
  FOREIGN KEY (book_id) REFERENCES book (id)
);

CREATE TABLE IF NOT EXISTS book_genres (
  book_id bigint(20) NOT NULL,
  genre_id bigint(20) NOT NULL,
  PRIMARY KEY (book_id,genre_id),
  FOREIGN KEY (book_id) REFERENCES book (id),
  FOREIGN KEY (genre_id) REFERENCES genre (id)
);

CREATE TABLE IF NOT EXISTS book_loan (
  book_id bigint(20) NOT NULL,
  borrower_id bigint(20) NOT NULL,
  branch_id bigint(20) NOT NULL,
  date_in date DEFAULT NULL,
  date_out date DEFAULT NULL,
  due_date date DEFAULT NULL,
  PRIMARY KEY (book_id,borrower_id,branch_id),
  FOREIGN KEY (borrower_id) REFERENCES borrower (id),
  FOREIGN KEY (book_id) REFERENCES book (id),
  FOREIGN KEY (branch_id) REFERENCES library_branch (id)
);

CREATE TABLE IF NOT EXISTS book_copy (
  book_id bigint(20) NOT NULL,
  branch_id bigint(20) NOT NULL,
  amount bigint(20) DEFAULT NULL,
  PRIMARY KEY (book_id,branch_id),
  FOREIGN KEY (branch_id) REFERENCES library_branch (id),
  FOREIGN KEY (book_id) REFERENCES book (id)
);

CREATE TABLE IF NOT EXISTS user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  password varchar(255) DEFAULT NULL,
  role varchar(255) DEFAULT NULL,
  username varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (username)
);
