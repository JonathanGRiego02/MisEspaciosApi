
create database MisEspaciosDB;
go

use MisEspaciosDB;
go


CREATE TABLE Users (
	id_user int identity(1,1) CONSTRAINT PK_Usuarios PRIMARY KEY,
	nickname nvarchar(50) UNIQUE NOT NULL UNIQUE,
    profile_img VARBINARY(MAX),
   	email nvarchar(50) UNIQUE NOT NULL,
	passwd nvarchar(255) NOT NULL,
	name_user nvarchar(50) NOT NULL,
	surname_user nvarchar(50) NOT NULL,
	private bit NOT NULl
);

CREATE TABLE Place_Types(
	id_type int identity(1,1) CONSTRAINT PK_Types PRIMARY KEY,
	name_type nvarchar(50) NOT NULL,
    	icon nvarchar(50),
    	id_user int,
   	 CONSTRAINT FK_TypesUsers FOREIGN KEY (id_user) REFERENCES Users(id_user)
);

CREATE TABLE Places(
	id_place int identity(1,1) CONSTRAINT PK_Places PRIMARY KEY,
	name_place nvarchar(50),
	desc_place nvarchar(250),
   	image VARBINARY(MAX),
    	pos_x DECIMAL(9,6),
	pos_y DECIMAL(9,6),
	likes int,
	id_user int,
	id_type int,
	private bit NOT NULl,
	CONSTRAINT FK_PlacesUsers FOREIGN KEY (id_user) REFERENCES Users(id_user),
	CONSTRAINT FK_PlacesTypes FOREIGN KEY (id_type) REFERENCES place_types(id_type)
);

CREATE TABLE Comments(
    id_comment int identity(1,1) CONSTRAINT PK_Comments PRIMARY KEY,
    comment nvarchar(250),
    commnet_date date,
    id_user int,
    id_place int,
    CONSTRAINT FK_CommentsUsers FOREIGN KEY (id_user) REFERENCES Users(id_user),
    CONSTRAINT FK_CommentsPlaces FOREIGN KEY (id_place) REFERENCES Places(id_place)
);

CREATE TABLE Likes(
    id_like int identity(1,1) CONSTRAINT PK_Likes PRIMARY KEY,
    id_user int,
    id_place int,
    CONSTRAINT FK_LikesUsers FOREIGN KEY (id_user) REFERENCES Users(id_user),
    CONSTRAINT FK_LikesPlaces FOREIGN KEY (id_place) REFERENCES Places(id_place)
);

CREATE TABLE Followers (
    id_follower int identity(1,1) CONSTRAINT PK_Followers PRIMARY KEY,
    id_user int,
    id_followed int,
    CONSTRAINT FK_FollowersUsers FOREIGN KEY (id_user) REFERENCES Users(id_user),
    CONSTRAINT FK_FollowersFollowed FOREIGN KEY (id_followed) REFERENCES Users(id_user)
)




