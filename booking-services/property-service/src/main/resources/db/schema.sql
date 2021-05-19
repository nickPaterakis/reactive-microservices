# grant all privileges on booking.* to 'nick'@'localhost';
drop database if exists property_db;

-- SET FOREIGN_KEY_CHECKS=0;

create database if not exists property_db;
use property_db;

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

create table if not exists addresses (
	id int not null auto_increment,
    city varchar(30),
    country_id int,
    postcode varchar(30),
    street_name varchar(30),
    street_number int,
	CONSTRAINT PK_Address PRIMARY KEY (id),
	CONSTRAINT FK_Country FOREIGN KEY (country_id)
        REFERENCES countries (id)
);

CREATE TABLE IF NOT EXISTS properties (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(200),
    description VARCHAR(500),
    property_type_id int not null,
    guest_space_id int not null,
    max_guest_number INT,
    bedroom_number int,
    bath_number int,
    price_per_night int,
 	address_id int,
    owner varchar(36),
    CONSTRAINT PK_Property PRIMARY KEY (id),
	CONSTRAINT FK_Property_Type FOREIGN KEY (property_type_id)
        REFERENCES property_types (id),
 	CONSTRAINT FK_Address FOREIGN KEY (address_id)
        REFERENCES addresses (id),
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

-- SET FOREIGN_KEY_CHECKS=1;

select * from addresses a inner join countries c on a.country_id = c.id where c.name = 'Spain';

SELECT * FROM properties p
			 -- inner join addresses c on p.address_id = c.id
             inner join reservations r on p.id = r.property_id
             WHERE
             p.max_guest_number >= 1
             and
             p.address_id in (select a.id from addresses a inner join countries c on a.country_id = c.id where c.name = 'spain')
             and
             (DATE('2021-3-3') not between r.check_in and r.check_out
             or
             DATE('2021-3-5') not between r.check_in and r.check_out)
             and
             (r.check_in not between DATE('2021-3-3')  and DATE('2021-3-5')
             or
             r.check_out not between DATE('2021-3-3')  and DATE('2021-3-5'));