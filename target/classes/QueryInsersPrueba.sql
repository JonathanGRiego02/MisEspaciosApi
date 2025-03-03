-- Eliminar datos de Places (depende de Users y Place_Types)
DELETE FROM Places;
DBCC CHECKIDENT ('Places', RESEED, 0);

-- Eliminar datos de Place_Types
DELETE FROM Place_Types;
DBCC CHECKIDENT ('Place_Types', RESEED, 0);

-- Eliminar datos de Users
DELETE FROM Users;
DBCC CHECKIDENT ('Users', RESEED, 0);

INSERT INTO Users (nickname, passwd, name_user, surname_user) 
VALUES
('user1', '$2a$10$UYwobJvayI1C9rvb8gkLCOvVM.26l5Q/ClMKcCpO9oCfPmdsCzp8K', 'Juan', 'Pérez'), /*password123*/
('user2', '$2a$10$/5R8JFkH3HGkEbrq7r8ITOT4GNW5zgcg0vZt9Q85SIIITTXSy/vpm', 'Ana', 'Gómez'), /* prueba1 */
('user3', '$2a$10$P9sCrltcO95yABZtQkSWNu8cUa8DCmUmUSO9D/klNnqTNKv7p1tRa', 'Carlos', 'Fernández'); /* prueba2 */

INSERT INTO Place_Types (name_type) 
VALUES 
('Restaurante'),
('Parque'),
('Museo');

INSERT INTO Places (name_place, desc_place, posX, posY, likes, id_user, id_type) 
VALUES 
('Pizza House', 'Pizzería con horno de leña', 40.712776, -74.005974, 120, 1, 1),
('Parque Central', 'Un gran parque con lagos y áreas verdes', 34.052235, -118.243683, 200, 2, 2),
('Museo de Historia', 'Exhibiciones sobre historia local', 41.902782, 12.496366, 85, 3, 3);


