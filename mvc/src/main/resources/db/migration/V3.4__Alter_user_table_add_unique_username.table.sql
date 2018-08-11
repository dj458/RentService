ALTER TABLE users DROP  COLUMN username ;

ALTER TABLE users ADD COLUMN  username varchar(255) UNIQUE;
