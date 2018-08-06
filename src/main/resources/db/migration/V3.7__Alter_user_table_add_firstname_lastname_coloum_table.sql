
ALTER TABLE users DROP COLUMN first_name;
ALTER TABLE users DROP COLUMN last_name;

ALTER TABLE users ADD COLUMN first_name varchar(255) NOT NULL ;
ALTER TABLE users ADD COLUMN last_name  varchar(255) NOT NULL ;