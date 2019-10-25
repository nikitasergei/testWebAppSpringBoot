create table lift
(
    id              int8    not null,
    activation_date varchar(255),
    address         varchar(255),
    city            varchar(255),
    fact_num        varchar(255),
    is_deleted      boolean not null,
    reg_num         varchar(255),
    owner_id        int8,
    primary key (id)
);
create table owner
(
    id         int8    not null,
    address    varchar(255),
    is_deleted boolean not null,
    name       varchar(255),
    primary key (id)
);
create table tech_service_history
(
    id       int8    not null,
    is_done  boolean not null,
    pto_date varchar(255),
    to1date  varchar(255),
    to2month varchar(255),
    lift_id  int8    not null,
    user_id  int8    not null,
    primary key (id)
);
create table user_role
(
    user_id int8 not null,
    roles   varchar(255)
);
create table users
(
    id              int8    not null,
    activation_code varchar(255),
    active          boolean not null,
    email           varchar(255),
    filename        varchar(255),
    is_deleted      boolean not null,
    password        varchar(255),
    stat            varchar(255),
    username        varchar(255),
    primary key (id)
);

alter table if exists lift
    add constraint owners_lifts foreign key (owner_id) references owner;
alter table if exists tech_service_history
    add constraint lift_tsh foreign key (lift_id) references lift;
alter table if exists tech_service_history
    add constraint user_tsh foreign key (user_id) references users;
alter table if exists user_role
    add constraint users_roles foreign key (user_id) references users;