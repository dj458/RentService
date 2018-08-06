ALTER TABLE equipments
ADD COLUMN user_id bigint default null,
ADD CONSTRAINT fk_equipment_user
FOREIGN KEY (user_id)
REFERENCES users (id);