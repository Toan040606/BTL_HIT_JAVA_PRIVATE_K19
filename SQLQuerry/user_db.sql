CREATE DATABASE user_db;
USE user_db;

CREATE TABLE users(
	id int primary key AUTO_INCREMENT,
	Ho varchar(30) NOT NULL,
    Ten varchar(20) NOT NULL,
	Username varchar(100) NOT NULL UNIQUE,
	Email varchar(100) NOT NULL UNIQUE,
	Pass varchar(255) NOT NULL
);

CREATE TABLE tableInfo (
	id int primary key auto_increment,
    name varchar(100) not null,
    link varchar(100) not null
);

CREATE TABLE Food (
	foodId int primary key auto_increment,
    foodName varchar(255),
    foodPrice double,
    foodDescription varchar(255)
);

CREATE TABLE Ordering (
	orderId int primary key auto_increment,
    orderTime time,
    orderStatus varchar(50)
);

CREATE TABLE OrderFood (
	oderId int,
    foodId int,
    quantity int,
    priceWithQuantity double,
    note varchar(255)
);
