CREATE DATABASE user_db;
USE user_db;

CREATE TABLE users(
	UserID int primary key AUTO_INCREMENT,
	Ho varchar(30),
    Ten varchar(20),
	Username varchar(100) NOT NULL UNIQUE,
	Email varchar(100) NOT NULL UNIQUE,
	Pass varchar(255) NOT NULL
)

