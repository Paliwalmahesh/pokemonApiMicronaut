-- liquibase formatted sql

--changeset Mahesh:pokemon-table
CREATE TABLE pokemon (
	id BIGINT auto_increment NOT NULL,
	name varchar(100) NOT NULL,
	image_url varchar(100) NOT NULL,
	CONSTRAINT pokemon_pk PRIMARY KEY (id),
	CONSTRAINT pokemon_unique_name UNIQUE KEY (name)
)
ENGINE=InnoDB
DEFAULT CHARSET=latin1
COLLATE=latin1_swedish_ci;

--changeset Mahesh:pokemon-table-power-col-added
ALTER TABLE pokemon ADD speciallity varchar(100) NULL;

--changeset Mahesh:pokemon-table-image-url-changed
ALTER TABLE pokemon MODIFY COLUMN image_url varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL;

--changeset Mahesh:pokemon-table-imageUrl-col-changed
ALTER TABLE pokemon CHANGE image_url imageUrl varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL NULL;

--changeset Mahesh:power-table-added
CREATE TABLE speciallity (
	id INT auto_increment NOT NULL,
	name varchar(100) NOT NULL,
	CONSTRAINT power_pk PRIMARY KEY (id),
	CONSTRAINT power_un UNIQUE KEY (name)
)
ENGINE=InnoDB
DEFAULT CHARSET=latin1
COLLATE=latin1_swedish_ci;

--changeset Mahesh:power-table-data-added
INSERT INTO speciallity (name)
	VALUES ('Grass'), ('Fire'), ('Water');

--changeset Mahesh:pokemon-col-dropped
ALTER TABLE pokemon DROP COLUMN speciallity;

--changeset Mahesh:pokemon-power-added
ALTER TABLE pokemon ADD speciallity INT NULL;

--changeset Mahesh:foreign-key-added
ALTER TABLE pokemon ADD CONSTRAINT pokemon_FK FOREIGN KEY (speciallity) REFERENCES speciallity(id);
