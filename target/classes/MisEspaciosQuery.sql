
create database MisEspaciosDB;
go

use MisEspaciosDB;
go


CREATE TABLE Users (
	idUser int identity(1,1) CONSTRAINT PK_Usuarios PRIMARY KEY,
	nickname nvarchar(50) UNIQUE NOT NULL,
	passwd nvarchar(255) NOT NULL,
	nameUser nvarchar(50) NOT NULL,
	surnameUser nvarchar(50) NOT NULL 
);

CREATE TABLE PlaceType(
	idType int identity(1,1) CONSTRAINT PK_Types PRIMARY KEY,
	nameType nvarchar(50)
);

CREATE TABLE Places(
	idPlace int identity(1,1) CONSTRAINT PK_Places PRIMARY KEY,
	namePlace nvarchar(50),
	descPlace nvarchar(250),
	posX DECIMAL(9,6),
	posY DECIMAL(9,6),
	likes int,
	idUser int,
	idType int,
	CONSTRAINT FK_PlacesUsers FOREIGN KEY (idUser) REFERENCES Users(idUser),
	CONSTRAINT FK_PlacesTypes FOREIGN KEY (idType) REFERENCES PlaceType(idType)
);



INSERT INTO Users (nickname, passwd, name_user, surname_user) 
VALUES 
('user1', 'password123', 'Juan', 'Pérez'),
('user2', 'securepass', 'Ana', 'Gómez'),
('user3', 'mypassword', 'Carlos', 'Fernández');


INSERT INTO place_type(name_type) 
VALUES 
('Restaurante'),
('Parque'),
('Museo');

INSERT INTO Places (name_place, desc_place, posX, posY, likes, id_user, id_type) 
VALUES 
('Pizza House', 'Pizzería con horno de leña', 40.712776, -74.005974, 120, 1, 1),
('Parque Central', 'Un gran parque con lagos y áreas verdes', 34.052235, -118.243683, 200, 2, 2),
('Museo de Historia', 'Exhibiciones sobre historia local', 41.902782, 12.496366, 85, 3, 3);
