CREATE TABLE authors (
    author_id SERIAL PRIMARY KEY,
    author_name VARCHAR (50),
    gender VARCHAR (50)
);

CREATE TABLE categories(
    category_id SERIAL PRIMARY KEY,
    category_name VARCHAR (255)
);

CREATE TABLE books(
   book_id SERIAL PRIMARY KEY,
   title VARCHAR (255),
   published_date TIMESTAMP,
   author_id INT REFERENCES authors(author_id) ON UPDATE  CASCADE ON DELETE CASCADE
);

CREATE TABLE book_details (
   id SERIAL PRIMARY KEY,
   book_id INT REFERENCES books(book_id) ON UPDATE  CASCADE ON DELETE CASCADE,
   category_id INT REFERENCES categories(category_id) ON UPDATE  CASCADE ON DELETE CASCADE
);


