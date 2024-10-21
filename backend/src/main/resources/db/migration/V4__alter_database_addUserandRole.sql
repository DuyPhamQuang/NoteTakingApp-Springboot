CREATE TABLE IF NOT EXISTS users (

id int NOT NULL PRIMARY KEY,
username varchar(20) UNIQUE NOT NULL,
email varchar(50) UNIQUE NOT NULL,
password CHAR(82) NOT NULL
);

CREATE TABLE IF NOT EXISTS roles (

id int NOT NULL PRIMARY KEY,
name varchar(10) NOT NULL
);