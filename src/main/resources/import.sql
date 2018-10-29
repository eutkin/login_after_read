INSERT INTO role VALUES (1,'ADMIN');
insert INTO user(user_id, email, name, last_name, active, password) VALUES (1, 'ff@mail.ru', 'eugene', 'utkin', 1, '$2a$10$DYQ62OzsxIptJGfha7n0zeF16Khx9Sd6Xke7PHK5e..Ik81c2icSy');
INSERT INTO user_roles VALUES (1,1);
insert into quote(id, text, author) VALUES (1, 'Hellow, World!', 1)
