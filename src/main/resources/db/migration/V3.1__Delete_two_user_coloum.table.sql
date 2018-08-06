

ALTER TABLE authorities DROP COLUMN user_id;
ALTER TABLE authorities DROP COLUMN users_id;
ALTER TABLE authorities ADD  COLUMN user_id bigint;