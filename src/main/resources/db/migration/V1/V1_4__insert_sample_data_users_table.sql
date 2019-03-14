BEGIN;

insert into USERS (user_name, password) values ('ferryscanner','P@ssword');
insert into USERS (user_name, password) values ('demo','P@ssword1');
insert into USERS (user_name, password) values ('ferryscanner2','P@ssword2');
insert into USERS (user_name, password) values ('ferryscanner3','P@ssword3');


insert into USER_ROLES (user_id, user_role) values (1, 'ROLE_ADMIN');
insert into USER_ROLES (user_id, user_role) values (1, 'ROLE_USER');
insert into USER_ROLES (user_id, user_role) values (2, 'ROLE_USER');
insert into USER_ROLES (user_id, user_role) values (3, 'ROLE_USER');
insert into USER_ROLES (user_id, user_role) values (4, 'ROLE_USER');
COMMIT;