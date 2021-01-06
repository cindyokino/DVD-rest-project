DROP TABLE IF EXISTS dvd;

DROP DATABASE IF EXISTS dvdDatabase;

CREATE DATABASE dvdDatabase;

USE dvdDatabase;

CREATE TABLE dvd (

id INT NOT NULL AUTO_INCREMENT,
title VARCHAR(255),
releaseYear INT,
director VARCHAR(255),
rating VARCHAR(255),
notes VARCHAR(255),

PRIMARY KEY (id)

);

INSERT INTO dvd (title, releaseYear, director, rating, notes) VALUES

('A Great Tale', 2015, 'Sam Jones', 'PG', 'This really is a great tale!'),
('A Goood Tale', 2012, 'Joe Smith', 'PG-13', 'This is a good tale!'),
('A Super Tale', 2015, 'Sam Jones', 'PG', 'A great remake!'),
('A Super Tale', 1985, 'Joe Smith', 'PG', 'The original!'),
('A Wonderful Tale', 2015, 'Joe Smith', 'PG-13', 'Wonderful, just wonderful!'),
('Just A Tale', 2015, 'Joe Baker', 'PG', 'It is a tale!');