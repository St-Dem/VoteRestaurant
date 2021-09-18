INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO USER_ROLES (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANTS(name, address)
VALUES ('grandRestaurant', 'Рублевка'),
       ('restaurant', 'внутри 3 кольца'),
       ('likeCafe', 'спальный район');

INSERT INTO DISHES(name)
VALUES ('fat food'),
       ('great food'),
       ('bad food'),
       ('engineer food');

INSERT INTO MENU(dish_id, price, created, restaurant_id)
VALUES (1, 100, '2021-09-01', 1),
       (2, 150, '2021-09-01', 2),
       (3, 50, '2021-09-02', 3),
       (4, 200, '2021-09-03', 2),
       (4, 250, '2021-09-04', 1);

INSERT INTO VOTES(created, user_id, restaurant_id)
VALUES ('2021-09-01', 1, 1),
       ('2021-09-02', 2, 3),
       ('2021-09-03', 1, 2),
       ('2021-09-04', 1, 1),
       ('2021-09-01', 2, 1);