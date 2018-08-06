CREATE SEQUENCE users_id_seq;


create table users (
    id bigint not null DEFAULT NEXTVAL('users_id_seq'),
    first_name varchar(255),
    last_name varchar(255),
    primary key (id)
);



ALTER SEQUENCE users_id_seq OWNED BY users.id;