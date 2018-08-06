CREATE SEQUENCE images_id_seq;


create table images (
    id bigint not null DEFAULT NEXTVAL('images_id_seq'),
    url varchar(255),
    user_id bigint,
    primary key (id)
);

ALTER SEQUENCE images_id_seq OWNED BY images.id;
ALTER TABLE images
ADD CONSTRAINT fk_images_users
 FOREIGN KEY(user_id) REFERENCES images(id);