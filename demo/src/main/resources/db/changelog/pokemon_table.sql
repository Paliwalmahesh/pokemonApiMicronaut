-- liquibase formatted sql

--changeset Mahesh:pokemon-table
CREATE TABLE pokemon (
	id BIGINT auto_increment NOT NULL,
	name varchar(100) NOT NULL,
	speciality INT NULL,
	imageUrl varchar(250) NOT NULL,
	CONSTRAINT pokemon_pk PRIMARY KEY (id),
	CONSTRAINT pokemon_unique_name UNIQUE KEY (name)
)
ENGINE=InnoDB
DEFAULT CHARSET=latin1
COLLATE=latin1_swedish_ci;

--changeset Mahesh:power-table-added
CREATE TABLE speciality (
	id INT auto_increment NOT NULL,
	name varchar(100) NOT NULL,
	CONSTRAINT power_pk PRIMARY KEY (id),
	CONSTRAINT power_un UNIQUE KEY (name)
)
ENGINE=InnoDB
DEFAULT CHARSET=latin1
COLLATE=latin1_swedish_ci;
--changeset Mahesh:power-table-data-added
INSERT INTO speciality (name)
	VALUES ('Grass'), ('Fire'), ('Water');


--changeset Mahesh:foreign-key-added
ALTER TABLE pokemon ADD CONSTRAINT pokemon_FK FOREIGN KEY (speciality) REFERENCES speciality(id);
