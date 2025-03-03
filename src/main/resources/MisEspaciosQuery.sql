
create database MisEspaciosDB;
go

use MisEspaciosDB;
go


CREATE TABLE Users (
	id_user int identity(1,1) CONSTRAINT PK_Usuarios PRIMARY KEY,
	nickname nvarchar(50) UNIQUE NOT NULL,
	passwd nvarchar(255) NOT NULL,
	nameUser nvarchar(50) NOT NULL,
	surnameUser nvarchar(50) NOT NULL 
);

CREATE TABLE Place_Types(
	id_type int identity(1,1) CONSTRAINT PK_Types PRIMARY KEY,
	nameType nvarchar(50)
);

CREATE TABLE Places(
	id_place int identity(1,1) CONSTRAINT PK_Places PRIMARY KEY,
	namePlace nvarchar(50),
	descPlace nvarchar(250),
	posX DECIMAL(9,6),
	posY DECIMAL(9,6),
	likes int,
	id_user int,
	id_type int,
	CONSTRAINT FK_PlacesUsers FOREIGN KEY (id_user) REFERENCES Users(id_user),
	CONSTRAINT FK_PlacesTypes FOREIGN KEY (id_type) REFERENCES place_types(id_type)
);




