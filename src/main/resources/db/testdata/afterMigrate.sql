SET FOREIGN_KEY_CHECKS  = 0;

DELETE FROM brand;
DELETE FROM city;
DELETE FROM client;
DELETE FROM `group`;
DELETE FROM group_permission;
DELETE FROM item_order;
DELETE FROM `order`;
DELETE FROM payment_method;
DELETE FROM permission;
DELETE FROM product;
DELETE FROM products_group;
DELETE FROM products_subgroup;
DELETE FROM provider;
DELETE FROM state;
DELETE FROM unit;
DELETE FROM user;
DELETE FROM user_group;

SET FOREIGN_KEY_CHECKS = 1;

# ALTER TABLE TO AUTO_INCREMENT = 1
ALTER TABLE brand             AUTO_INCREMENT = 1;
ALTER TABLE city              AUTO_INCREMENT = 1;
ALTER TABLE client            AUTO_INCREMENT = 1;
ALTER TABLE `group`           AUTO_INCREMENT = 1;
ALTER TABLE group_permission  AUTO_INCREMENT = 1;
ALTER TABLE item_order        AUTO_INCREMENT = 1;
ALTER TABLE `order`           AUTO_INCREMENT = 1;
ALTER TABLE payment_method    AUTO_INCREMENT = 1;
ALTER TABLE permission        AUTO_INCREMENT = 1;
ALTER TABLE product           AUTO_INCREMENT = 1;
ALTER TABLE products_group    AUTO_INCREMENT = 1;
ALTER TABLE products_subgroup AUTO_INCREMENT = 1;
ALTER TABLE provider          AUTO_INCREMENT = 1;
ALTER TABLE state             AUTO_INCREMENT = 1;
ALTER TABLE unit              AUTO_INCREMENT = 1;
ALTER TABLE user              AUTO_INCREMENT = 1;
ALTER TABLE user_group        AUTO_INCREMENT = 1;

# INSERT states
INSERT INTO state (id, name, short_name, status, creation_date, update_date)
VALUES  (1,'Goiás','GO', 'ACTIVE', utc_timestamp -10000, utc_timestamp +10000);
INSERT INTO state (id, name, short_name, status, creation_date, update_date)
VALUES (2, 'São Paulo', 'SP', 'ACTIVE', utc_timestamp -10000, utc_timestamp +10000);
INSERT INTO state (id, name, short_name, status, creation_date, update_date)
VALUES (3, 'Rio de Janeiro', 'RJ', 'ACTIVE', utc_timestamp -10000, utc_timestamp +10000);
INSERT INTO state (id, name, short_name, status, creation_date, update_date)
VALUES (4, 'Mato Grosso', 'MT', 'ACTIVE', utc_timestamp -10000, utc_timestamp +10000);
INSERT INTO state (id, name, short_name, status, creation_date, update_date)
VALUES (5, 'Mato Grosso do Sul', 'MS', 'ACTIVE', utc_timestamp -10000, utc_timestamp +10000);
INSERT INTO state (id, name, short_name, status, creation_date, update_date)
VALUES (6, 'Acre', 'AC', 'ACTIVE', utc_timestamp -10000, utc_timestamp +10000);

#  INSERT cities
INSERT INTO city (id, name, state_id, status, creation_date, update_date)
VALUES (1,'Goiânia',1,'ACTIVE',utc_timestamp -1000000, utc_timestamp +10000);
INSERT INTO city (id, name, state_id, status, creation_date, update_date)
VALUES (2, 'Santos', 2,'ACTIVE',utc_timestamp -10000, utc_timestamp +10000);
INSERT INTO city (id, name, state_id, status, creation_date, update_date)
VALUES (3, 'Rio de Janeiro', 3,'ACTIVE',utc_timestamp -10000, utc_timestamp +10000);
INSERT INTO city (id, name, state_id, status, creation_date, update_date)
VALUES (4, 'Cuiabá', 4,'ACTIVE',utc_timestamp -10000, utc_timestamp +10000);
INSERT INTO city (id, name, state_id, status, creation_date, update_date)
VALUES (5, 'Campo Grande', 5,'ACTIVE',utc_timestamp -10000, utc_timestamp +10000);
INSERT INTO city (id, name, state_id, status, creation_date, update_date)
VALUES (6, 'Rio Branco', 6,'ACTIVE',utc_timestamp -10000, utc_timestamp +10000);

# INSERT payment_methods
INSERT INTO payment_method (id, description, status, create_date, update_date)
VALUES (1, 'Dinheiro', 'ACTIVE', utc_timestamp -10000, utc_timestamp +10000);
INSERT INTO payment_method (id, description, status, create_date, update_date)
VALUES (2, 'Cartão de crédito', 'ACTIVE', utc_timestamp -10000, utc_timestamp +10000);
INSERT INTO payment_method (id, description, status, create_date, update_date)
VALUES (3, 'Cartão de débito', 'ACTIVE', utc_timestamp -10000, utc_timestamp +10000);
INSERT INTO payment_method (id, description, status, create_date, update_date)
VALUES (4, 'Cheque', 'ACTIVE', utc_timestamp -10000, utc_timestamp +10000);

