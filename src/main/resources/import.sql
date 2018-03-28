insert into role(id, name) values (1001,'ROLE_USER');
insert into role(id, name) values (1000,'ROLE_ADMIN');

insert into user_entity(id, first_name, last_name, email, password) values (999999,'kiki','kiki', 'kiki@kiki.kiki', 'kiki');

insert into user_role(user_id, role_id) values (999999,1001);
insert into user_role(user_id, role_id) values (999999,1000);

