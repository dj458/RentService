CREATE SEQUENCE shipment_id_seq START 1;

create table shipment(
id bigint not null DEFAULT NEXTVAL('shipment_id_seq'),
shipment_speed varchar (255),
shipment_cost varchar (255),
shipment_weight varchar (255),
user_id bigint,
primary key (id)
);

ALTER SEQUENCE shipment_id_seq OWNED by shipment.id;
ALTER table shipment
ADD CONSTRAINT fk_shipment_users
FOREIGN KEY(user_id) REFERENCES shipment(id)

