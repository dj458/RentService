ALTER TABLE images DROP CONSTRAINT fk_images_users;
ALTER TABLE images ADD CONSTRAINT  fk_images_users FOREIGN KEY(user_id) REFERENCES users(id)