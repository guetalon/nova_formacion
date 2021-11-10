DELETE FROM EDITORIAL;
DELETE FROM BOOK;

ALTER SEQUENCE editorial_seq RESTART WITH 1;
ALTER SEQUENCE book_seq RESTART WITH 1;

INSERT INTO EDITORIAL (ID, NAME) 
VALUES (editorial_seq.nextval, 'Nova editions');

INSERT INTO BOOK (ID, TITLE, AUTHOR, PUBLISH, PAGES, DESCRIPTION, EDITORIAL_ID) 
VALUES (book_seq.nextval, 'Aprendiendo microservicios', 'NTT Data', '2020-12-01', 100, 
'Aprendiendo a construir microservicios usando Spring Boot', 1);
