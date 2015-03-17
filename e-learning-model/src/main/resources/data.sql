INSERT INTO course VALUES ( 1, 'Test Course 1' );
INSERT INTO course VALUES ( 2, 'Test Course 2' );

INSERT INTO lesson VALUES ( 1, 'Test lesson 1', 1 );
INSERT INTO lesson VALUES ( 2, 'Test lesson 2', 1 );

INSERT INTO lesson VALUES ( 3, 'Test lesson 1', 2 );

INSERT INTO lesson_step VALUES ( 1, 'Test Step 1', 1 );
INSERT INTO lesson_step VALUES ( 2, 'Test Step 2', 1 );
INSERT INTO lesson_step VALUES ( 3, 'Test Step 3', 1 );
INSERT INTO lesson_step VALUES ( 4, 'Test Step 4', 1 );

INSERT INTO lesson_step VALUES ( 5, 'Test Step 1', 2 );
INSERT INTO lesson_step VALUES ( 6, 'Test Step 2', 2 );
INSERT INTO lesson_step VALUES ( 7, 'Test Step 3', 2 );
INSERT INTO lesson_step VALUES ( 8, 'Test Step 4', 2 );

INSERT INTO lesson_step VALUES ( 9, 'Test Step 1', 3 );
INSERT INTO lesson_step VALUES ( 10, 'Test Step 2', 3 );

INSERT INTO user (id, first_name, last_name, email, password, granted_authorities) VALUES ( 1, 'Gustavo', 'Orsi', 'gustavoorsi@gmail.com','password', 'ROLE_USER,ROLE_ANONYMOUS,ROLE_ADMIN' );
INSERT INTO user (id, first_name, last_name, email, password, granted_authorities) VALUES ( 2, 'John', 'Connor', 'john@hastalavistababy.com','password', 'ROLE_USER,ROLE_ANONYMOUS' );

INSERT INTO foo (id, name, description) VALUES (1, 'Foo 1', 'Description for Foo 1');
INSERT INTO foo (id, name, description) VALUES (2, 'Foo 2', 'Description for Foo 2');
INSERT INTO foo (id, name, description) VALUES (3, 'Foo 3', 'Description for Foo 3');
INSERT INTO foo (id, name, description) VALUES (4, 'Foo 4', 'Description for Foo 4');

