-- Eliminar datos de Places (depende de Users y Place_Types)
DELETE FROM Places;
DBCC CHECKIDENT ('Places', RESEED, 0);

-- Eliminar datos de Place_Types
DELETE FROM Place_Types;
DBCC CHECKIDENT ('Place_Types', RESEED, 0);

-- Eliminar datos de Users
DELETE FROM Users;
DBCC CHECKIDENT ('Users', RESEED, 0);

-- Eliminar datos de Comments
DELETE FROM Comments;
DBCC CHECKIDENT ('Comments', RESEED, 0);

-- Eliminar datos de Likes
DELETE FROM Likes;
DBCC CHECKIDENT ('Likes', RESEED, 0);

-- Eliminar datos de Followers
DELETE FROM Followers;
DBCC CHECKIDENT ('Followers', RESEED, 0);

INSERT INTO Users (nickname, passwd, email, name_user, surname_user)
VALUES
('user1', '$2a$10$UYwobJvayI1C9rvb8gkLCOvVM.26l5Q/ClMKcCpO9oCfPmdsCzp8K', 'user1@gmail.com','Juan', 'P�rez'), /*password123*/
('user2', '$2a$10$/5R8JFkH3HGkEbrq7r8ITOT4GNW5zgcg0vZt9Q85SIIITTXSy/vpm', 'user2@gmail.com' , 'Ana', 'G�mez'), /* prueba1 */
('user3', '$2a$10$P9sCrltcO95yABZtQkSWNu8cUa8DCmUmUSO9D/klNnqTNKv7p1tRa', 'user3@gmail.com', 'Carlos', 'Fern�ndez'); /* prueba2 */

-- Inserts para Place_Types
INSERT INTO Place_Types (name_type, icon, id_user)
VALUES ('Restaurante', 'restaurant.png', 1);

INSERT INTO Place_Types (name_type, icon, id_user)
VALUES ('Parque', 'park.png', 2);

INSERT INTO Place_Types (name_type, icon, id_user)
VALUES ('Museo', 'museum.png', 3);

-- Inserts para Places
INSERT INTO Places (name_place, desc_place, pos_x, pos_y, likes, id_user, id_type)
VALUES ('PizzaManía', 'Mejor pizza de la ciudad', 19.432608, -99.133209, 10, 1, 1);

INSERT INTO Places (name_place, desc_place, pos_x, pos_y, likes, id_user, id_type)
VALUES ('Parque Central', 'Ideal para relajarse', 40.712776, -74.005974, 25, 2, 2);

INSERT INTO Places (name_place, desc_place, pos_x, pos_y, likes, id_user, id_type)
VALUES ('Museo de Arte Moderno', 'Gran colección de arte', 48.856613, 2.352222, 15, 3, 3);

-- Inserts para Comments
INSERT INTO Comments (comment, commnet_date, id_user, id_place)
VALUES ('Excelente comida!', '2025-03-20', 2, 1);

INSERT INTO Comments (comment, commnet_date, id_user, id_place)
VALUES ('Muy bonito lugar', '2025-03-21', 3, 2);

INSERT INTO Comments (comment, commnet_date, id_user, id_place)
VALUES ('Increíble colección', '2025-03-22', 1, 3);

-- Inserts para Likes
INSERT INTO Likes (id_user, id_place)
VALUES (1, 2);

INSERT INTO Likes (id_user, id_place)
VALUES (2, 3);

INSERT INTO Likes (id_user, id_place)
VALUES (3, 1);

-- Inserts para Followers
INSERT INTO Followers (id_user, id_followed)
VALUES (1, 2);

INSERT INTO Followers (id_user, id_followed)
VALUES (2, 3);

INSERT INTO Followers (id_user, id_followed)
VALUES (3, 1);


