ALTER TABLE users ADD COLUMN username varchar(255) NOT NULL DEFAULT 'defaultPassword';
ALTER TABLE users ADD COLUMN password varchar(255) NOT NULL DEFAULT 'defaultPassword';
ALTER TABLE users ADD COLUMN account_expired BOOlEAN NOT NULL DEFAULT FALSE;
ALTER TABLE users ADD COLUMN account_locked BOOLEAN NOT NULL DEFAULT FALSE ;
ALTER TABLE users ADD COLUMN credential_expired BOOLEAN NOT NULL DEFAULT FALSE ;
ALTER TABLE users ADD COLUMN enabled BOOLEAN NOT NULL DEFAULT True;