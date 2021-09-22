INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO USER_ROLES (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANTS (name, address)
VALUES ('grandRestaurant', 'Рублевка'),
       ('restaurant', 'внутри 3 кольца'),
       ('likeCafe', 'спальный район');

INSERT INTO MENU (created, restaurant_id)
VALUES ('2021-09-01', 1),
       ('2021-09-01', 2),
       ('2021-09-02', 3),
       ('2021-09-03', 2),
       (CURRENT_DATE, 1),
       (CURRENT_DATE, 2);

INSERT INTO DISHES (name, price, menu_id)
VALUES ('fat food', 100, 1),
       ('great food', 200, 1),
       ('engineer food', 250, 2),
       ('food', 70, 3),
       ('bad food', 50, 1),
       ('meat', 300, 4),
       ('fat food', 100, 5),
       ('fat food', 90, 6),
       ('engineer food', 250, 6);

INSERT INTO VOTES (created, user_id, restaurant_id)
VALUES ('2021-09-01', 1, 1),
       ('2021-09-02', 2, 3),
       ('2021-09-03', 1, 2),
       ('2021-09-01', 2, 1),
       (CURRENT_DATE, 2, 2);