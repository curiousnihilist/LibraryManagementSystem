insert into book(book_id,category,description,isbn,no_of_copies,price,publisher,rack_id,title) values (1, "Engineering", "Best Physics IIT JEE Book", "978-8177091878", 20, 199, "Bharti Bhawan", "A/101", "Concepts of Physics Part 1");
insert into book(book_id,category,description,isbn,no_of_copies,price,publisher,rack_id,title) values (2, "Engineering", "Best Physics IIT JEE Book", "978-8177091879", 20, 200, "Bharti Bhawan", "A/102", "Concepts of Physics Part 2");
insert into book(book_id,category,description,isbn,no_of_copies,price,publisher,rack_id,title) values (3, "Software Engineering", "Best JAVA Book in the market.", "978-9387432291", 20, 799, "McGraw Hill Education", "A/103",  "Java - The Complete Reference");
insert into book(book_id,category,description,isbn,no_of_copies,price,publisher,rack_id,title) values (4, "Software Engineering", "Best Python book in the market", "978-4946321878",  20, 899, "O'Reilly", "A/104", "Think Python");
insert into book(book_id,category,description,isbn,no_of_copies,price,publisher,rack_id,title) values (5, "Software Engineering", "Best Book on Spring Framework", "978-8178631878",  20, 599, "Manning Publications", "A/105", "Spring in Action");
insert into book(book_id,category,description,isbn,no_of_copies,price,publisher,rack_id,title) values (6, "Economics", "Best Economics Book in the market", "823-71023654789", 20, 500 , "Unversity of Chicago Press", "B/101", "Capitalism and Freedom");


insert into author(author_id,description,name) values(1,"", "HC Verma");
insert into author(author_id,description,name) values(2,"","Herbert Schildt");
insert into author(author_id,description,name) values(3,"","Allen B Downey");
insert into author(author_id,description,name) values(4,"","Craig Walls");
insert into author(author_id,description,name) values(5,"","Milton Friedman");
insert into author(author_id,description,name) values(6,"","Stephen King");

insert into book_author(author_id,book_id) values(1,1);
insert into book_author(author_id,book_id) values(2,1);
insert into book_author(author_id,book_id) values(3,2);
insert into book_author(author_id,book_id) values(4,3);
insert into book_author(author_id,book_id) values(5,4);
insert into book_author(author_id,book_id) values(6,5);


