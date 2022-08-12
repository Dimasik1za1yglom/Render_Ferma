--liquibase formatted sql

--changeset render_ferm:1
create table account
(
    id       serial primary key,
    login    varchar(100),
    password varchar(100)
);

--changeset render_ferm:2
create table task
(
    id         serial primary key,
    name       varchar(100),
    account_id integer,
    foreign key (account_id) references account (id)
);

--changeset render_ferm:3
create table status(
    id serial primary key,
    name varchar(10)
);
--changeset render_ferm:4
insert into status(name) values ('RENDERING');
insert into status(name) values ('COMPLETE');

--changeset render_ferm:5
create table task_status (
    id serial primary key ,
    task_id integer,
    foreign key (task_id) references task(id),
    status_id integer,
    foreign key (status_id) references status(id),
    data_time TIMESTAMP

)
