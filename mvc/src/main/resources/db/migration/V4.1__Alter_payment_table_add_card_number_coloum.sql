ALTER TABLE payment DROP COLUMN cardNumber;
ALTER TABLE payment ADD COLUMN card_number varchar(255) NOT NULL ;