-- Eliminar datos de tablas dependientes primero
DELETE FROM Likes;
DBCC CHECKIDENT ('Likes', RESEED, 0);

DELETE FROM Comments;
DBCC CHECKIDENT ('Comments', RESEED, 0);

DELETE FROM Followers;
DBCC CHECKIDENT ('Followers', RESEED, 0);

DELETE FROM Places;
DBCC CHECKIDENT ('Places', RESEED, 0);

DELETE FROM Place_Types;
DBCC CHECKIDENT ('Place_Types', RESEED, 0);

DELETE FROM Users;
DBCC CHECKIDENT ('Users', RESEED, 0);

INSERT INTO Users (nickname, passwd, email, name_user, surname_user, profile_img, private)
VALUES
    ('user1', '$2a$10$UYwobJvayI1C9rvb8gkLCOvVM.26l5Q/ClMKcCpO9oCfPmdsCzp8K', 'user1@gmail.com','Juan', 'Pérez', NULL, 0), -- password123
    ('user2', '$2a$10$/5R8JFkH3HGkEbrq7r8ITOT4GNW5zgcg0vZt9Q85SIIITTXSy/vpm', 'user2@gmail.com' , 'Ana', 'Gómez', NULL, 0),  -- prueba1
    ('user3', '$2a$10$P9sCrltcO95yABZtQkSWNu8cUa8DCmUmUSO9D/klNnqTNKv7p1tRa', 'user3@gmail.com', 'Carlos', 'Fernández', NULL, 0); -- prueba2

-- Inserts para Place_Types
INSERT INTO Place_Types (name_type, icon, id_user)
VALUES
    ('Restaurante', 'restaurant.png', 1),
    ('Parque', 'park.png', 2),
    ('Museo', 'museum.png', 3);

-- Inserts para Places
INSERT INTO Places (name_place, desc_place, pos_x, pos_y, likes, id_user, id_type, image, private)
VALUES
    ('PizzaManía', 'Mejor pizza de la ciudad', 19.432608, -99.133209, 10, 1, 1, NULL, 0),
    ('Lilys', 'Mejor sitio de citas de la ciudad', 28.483608, -16.316620, 10, 1, 1, NULL, 0),
    ('Parque Central', 'Ideal para relajarse', 40.712776, -74.005974, 25, 2, 2, NULL, 0),
    ('Museo de Arte Moderno', 'Gran colección de arte', 48.856613, 2.352222, 15, 3, 3, NULL, 0),
    ('user1 place', 'lugar del user1', 28.502563, -16.220424, 10, 1, 2, NULL, 0),
    ('user2 place', 'lugar del user2', 28.502638, -16.211713, 10, 2, 3, NULL, 0),
    ('user3 place', 'lugar del user3', 28.506947, -16.217710, 10, 3, 2, NULL, 0);

-- Inserts para Comments
INSERT INTO Comments (comment, commnet_date, id_user, id_place)
VALUES
    ('Excelente comida!', '2025-03-20', 2, 1),
    ('Muy bonito lugar', '2025-03-21', 3, 2),
    ('Increíble colección', '2025-03-22', 1, 3);

-- Inserts para Likes
INSERT INTO Likes (id_user, id_place)
VALUES
    (1, 2),
    (2, 3),
    (3, 1);

-- Inserts para Followers
INSERT INTO Followers (id_user, id_followed)
VALUES
    (1, 2),
    (2, 3),
    (3, 1);



select * from users
select * from Places