#grant all privileges on booking.* to 'nick'@'localhost';
#password BINARY(60),
drop database if exists user_db;

create database if not exists user_db;
use user_db;

# DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
    id binary(16),
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    email VARCHAR(100),
    phone_number VARCHAR(20),
    CONSTRAINT PK_User PRIMARY KEY (id)
);

select * from properties where owner = "76393fab-10b2-40bb-b3ef-b75a76829178";