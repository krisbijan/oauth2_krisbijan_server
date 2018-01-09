insert into role(id, name) values (1,'ROLE_USER');
insert into role(id, name) values (2,'ROLE_ADMIN');

insert into appuser(id, name, password) values (1,'kiki','kiki');
insert into appuser(id, name, password) values (2,'cici','cici');

insert into user_role(user_id, role_id) values (1,1);
insert into user_role(user_id, role_id) values (1,2);
insert into user_role(user_id, role_id) values (2,1);

