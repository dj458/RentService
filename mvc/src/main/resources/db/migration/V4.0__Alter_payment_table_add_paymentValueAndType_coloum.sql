
ALTER TABLE payment DROP COLUMN payment_type;
ALTER TABLE payment DROP COLUMN payment_value;

ALTER TABLE payment ADD COLUMN payment_type varchar(255) NOT NULL ;
ALTER TABLE payment ADD COLUMN payment_value  varchar(255) NOT NULL ;