insert into users (id, username, password, active, is_deleted)
values (1, 'admin', '123', true, false);

insert into user_role (user_id, roles)
values (1, 'ADMIN');
