# grant all privileges on booking.* to 'nick'@'localhost';
#drop database if exists property_db;

-- SET FOREIGN_KEY_CHECKS=0;

create database if not exists property_db;
use property_db;

CREATE TABLE IF NOT EXISTS countries (
    id VARCHAR(2) NOT NULL,
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

create table if not exists addresses (
	id int not null auto_increment,
    city varchar(30),
    country_id varchar(2),
    postcode varchar(30),
    street_name varchar(30),
    street_number int,
	CONSTRAINT PK_Address PRIMARY KEY (id),
	CONSTRAINT FK_Country FOREIGN KEY (country_id)
        REFERENCES countries (id)
);

CREATE TABLE IF NOT EXISTS properties (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100),
    description VARCHAR(250),
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
    name VARCHAR(300),
    property_id INT NOT NULL,
    CONSTRAINT PK_Image PRIMARY KEY (id),
    CONSTRAINT FK_Property_Image FOREIGN KEY (property_id)
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