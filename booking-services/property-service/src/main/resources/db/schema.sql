# grant all privileges on booking.* to 'nick'@'localhost';
drop database if exists property_db;

create database if not exists property_db;
use property_db;

-- DROP TABLE IF EXISTS property_amenities;
-- DROP TABLE IF EXISTS reservations;
-- DROP TABLE IF EXISTS images;
-- DROP TABLE IF EXISTS properties;
-- DROP TABLE IF EXISTS property_types;
-- DROP TABLE IF EXISTS countries;
-- DROP TABLE IF EXISTS guest_spaces;
-- DROP TABLE IF EXISTS amenities;

CREATE TABLE IF NOT EXISTS countries (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(80),
    CONSTRAINT PK_Country PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS property_types (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(80),
    CONSTRAINT PK_Property_Type PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS guest_spaces (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(80),
    CONSTRAINT PK_Guest_Space PRIMARY KEY (id)
);

-- CREATE TABLE IF NOT EXISTS owners (
--     id INT NOT NULL,
--     first_name VARCHAR(30),
--     last_name VARCHAR(30),
--     CONSTRAINT PK_Owner PRIMARY KEY (id)
-- );

CREATE TABLE IF NOT EXISTS properties (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(200),
    property_type_id int not null,
    guest_space_id int not null,
    max_guest_number INT,
    bedroom_number int,
    bath_number int,
    price_per_night int,
    country_id INT NOT NULL,
    owner varchar(36),
    CONSTRAINT PK_Property PRIMARY KEY (id),
    CONSTRAINT FK_Country FOREIGN KEY (country_id)
        REFERENCES countries (id),
	CONSTRAINT FK_Property_Type FOREIGN KEY (property_type_id)
        REFERENCES property_types (id),
-- 	CONSTRAINT FK_owner FOREIGN KEY (owner_id)
--         REFERENCES owners (id),
	CONSTRAINT FK_Guest_Space FOREIGN KEY (guest_space_id)
        REFERENCES guest_spaces (id)
);

CREATE TABLE IF NOT EXISTS images (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(200),
    property_id INT NOT NULL,
    CONSTRAINT PK_Image PRIMARY KEY (id),
    CONSTRAINT FK_Property_Image FOREIGN KEY (property_id)
        REFERENCES properties (id)
);

CREATE TABLE IF NOT EXISTS reservations (
    id INT NOT NULL AUTO_INCREMENT,
    check_in DATE NOT NULL,
    check_out DATE NOT NULL,
    property_id INT NOT NULL,
    CONSTRAINT PK_Reservation PRIMARY KEY (id),
    CONSTRAINT FK_Property_Reservation FOREIGN KEY (property_id)
        REFERENCES properties (id)
);

CREATE TABLE IF NOT EXISTS amenities (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(80),
    CONSTRAINT PK_Amenity PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS property_amenities (
    property_id INT NOT NULL,
    amenity_id INT NOT NULL,
    CONSTRAINT UK_Property_Amenity UNIQUE (property_id , amenity_id),
    CONSTRAINT FK_Property_Amenity FOREIGN KEY (property_id)
        REFERENCES properties (id),
    CONSTRAINT FK_Amenity FOREIGN KEY (amenity_id)
        REFERENCES amenities (id)
);

SELECT count(*) FROM properties p
             inner join countries c on p.country_id = c.id
             inner join reservations r on p.id = r.property_id
             WHERE
             p.max_guest_number >= 2
             and
             c.name = 'greece'
             and
             (DATE('2021-3-3') not between r.check_in and r.check_out
             or
             DATE('2021-3-5') not between r.check_in and r.check_out)
             and
             (r.check_in not between DATE('2021-3-3')  and DATE('2021-3-5')
             or
             r.check_out not between DATE('2021-3-3')  and DATE('2021-3-5'));