DELETE
FROM user_roles;
DELETE
FROM vote;
DELETE
FROM menu_item;
DELETE
FROM users;
DELETE
FROM restaurant;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@gmail.com', 'password'),
       ('User2', 'user2@gmail.com', 'password'),
       ('User3', 'user3@gmail.com', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('USER', 100001),
       ('USER', 100002),
       ('ADMIN', 100003);

INSERT INTO restaurant (name)
VALUES ('KFC'),
       ('Zima'),
       ('Five Guys'),
       ('McDonald');

INSERT INTO menu_item (restaurant_id, name, price)
VALUES (100005, 'Kfc Burger', 300),
       (100005, 'Kfc Shake', 250),
       (100005, 'Kfc Fries', 110),
       (100005, 'Kfc Nuggets', 550),
       (100006, 'Pelmeni', 500),
       (100006, 'Borsch', 250),
       (100006, 'Oladushki', 440),
       (100006, 'Vodka', 130),
       (100007, 'Five Guys Burger', 320),
       (100007, 'Five Guys Shake', 230),
       (100007, 'Five Guys Fries', 160),
       (100007, 'Five Guys Nuggets', 580),
       (100008, 'McDonald Burger', 315),
       (100008, 'McDonald Shake', 238),
       (100008, 'McDonald Fries', 176),
       (100008, 'McDonald Nuggets', 522);

INSERT INTO vote (restaurant_id, user_id, created)
VALUES (100005, 100000, '2022-11-01'),
       (100005, 100001, '2022-11-01'),
       (100005, 100002, '2022-11-01'),
       (100008, 100003, '2022-11-01');



