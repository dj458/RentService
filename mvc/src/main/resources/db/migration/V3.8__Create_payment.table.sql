CREATE SEQUENCE payment_id_seq START 1;

create table payment(

id bigint not null DEFAULT NEXTVAL('payment_id_seq'),
payment_type varchar(255),
payment_value varchar (255),
user_id bigint,
primary key (id)

);

ALTER SEQUENCE payment_id_seq OWNED BY payment.id;
ALTER TABLE payment
ADD CONSTRAINT fk_payment_users
FOREIGN KEY(user_id) REFERENCES payment(id)