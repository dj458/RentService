ALTER TABLE payment DROP CONSTRAINT fk_payment_users;
ALTER TABLE payment ADD CONSTRAINT fk_payment_users FOREIGN KEY(user_id) REFERENCES users(id)