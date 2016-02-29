-- users
INSERT INTO users (id, name, password, email, birthday, roles)
VALUES (1, 'jhon', '$2a$16$NVlYAowTx7jt.N.UbDMta.pENK20YRB.80xkBF993eewQGpYJHf9q', 'jhon@mail', '1987-05-05',
        ARRAY ['ROLE_REGISTERED_USER']), -- 10 tickets
  (2, 'jane', '$2a$16$NVlYAowTx7jt.N.UbDMta.pENK20YRB.80xkBF993eewQGpYJHf9q', 'jane@mail', '1986-10-17',
   ARRAY ['ROLE_REGISTERED_USER']), -- 3 tickets
  (3, 'admin', '$2a$16$RbuuNgNnEs.PBTe94wSGt.hMmqYcMDJn1xKXLrHU6KgKAxPHrnc3K', 'admin@bs.org', '19876-10-17',
   ARRAY ['ROLE_REGISTERED_USER', 'ROLE_BOOKING_MANAGER']);

-- users account
INSERT INTO user_account (id, user_id, money)
VALUES (1, 1, 100000),
  (2, 2, 100000),
  (3, 3, 100000);

-- events
INSERT INTO events (id, name, base_price, rating)
VALUES (1, 'Hobbit', '8000.0', 'HIGH');
-- 2015-06-06T14:00:00.000 -> total 24 tickets
--                         -> free 23 tickets
-- 2015-06-07T22:00:00.000 -> total 24 tickets
--                         -> free 24 tickets

INSERT INTO events (id, name, base_price, rating)
VALUES (2, 'The Gamer', '5000.0', 'MID');
-- 2015-10-17T20:00:00.000 -> total 12 tickets
--                         -> free 0 tickets

INSERT INTO events (id, name, base_price, rating)
VALUES (3, 'The Gamer II', '7000.0', 'MID'),
  (4, 'Movie', '9000.0', 'MID'),
  (5, 'Kung-Fu Panda', '10000.0', 'MID'),
  (6, 'Another', '12000.0', 'MID'),
  (7, 'Star Wars: Episode 1', '8000.0', 'MID'),
  (8, 'Kung-Fu Panda 2', '5000.0', 'MID'),
  (9, 'Star Wars: Episode 10', '6000.0', 'MID');


INSERT INTO auditoriums (id, name, seats, vip_seats)
VALUES (1, 'Big party hall', 24, ARRAY [4, 5, 6, 7, 8, 9, 10]),
  (2, 'Family hall', 12, ARRAY [2]);

--
INSERT INTO shows (id, date, auditorium_id, event_id)
VALUES (1, '2015-06-06T14:00:00.000', 1, 1),
  (2, '2015-06-07T22:00:00.000', 1, 1),
  (3, '2015-10-17T20:00:00.000', 2, 2);

-- tickets for event 'Hobbit'  (base price = 8_000 * 1.2 <- HIGH rating)
INSERT INTO tickets (id, seat, is_vip, price, discount_price, show_id, user_id)
VALUES (1, 1, FALSE, 9600, NULL, 1, NULL),
  (2, 2, FALSE, 9600, 4800, 1, 1), -- 10
  (3, 3, FALSE, 9600, NULL, 1, NULL),
  (4, 4, FALSE, 9600, NULL, 1, NULL),
  (5, 5, TRUE, 19200, NULL, 1, NULL),
  (6, 6, TRUE, 19200, NULL, 1, NULL),
  (7, 7, TRUE, 19200, NULL, 1, NULL),
  (8, 8, TRUE, 19200, NULL, 1, NULL),
  (9, 9, TRUE, 19200, NULL, 1, NULL),
  (10, 10, TRUE, 19200, NULL, 1, NULL),
  (11, 11, FALSE, 9600, NULL, 1, NULL),
  (12, 12, FALSE, 9600, NULL, 1, NULL),
  (13, 13, FALSE, 9600, NULL, 1, NULL),
  (14, 14, FALSE, 9600, NULL, 1, NULL),
  (15, 15, FALSE, 9600, NULL, 1, NULL),
  (16, 16, FALSE, 9600, NULL, 1, NULL),
  (17, 17, FALSE, 9600, NULL, 1, NULL),
  (18, 18, FALSE, 9600, NULL, 1, NULL),
  (19, 19, FALSE, 9600, NULL, 1, NULL),
  (20, 20, FALSE, 9600, NULL, 1, NULL),
  (21, 21, FALSE, 9600, NULL, 1, NULL),
  (22, 22, FALSE, 9600, NULL, 1, NULL),
  (23, 23, FALSE, 9600, NULL, 1, NULL),
  (24, 24, FALSE, 9600, NULL, 1, NULL);

INSERT INTO tickets (id, seat, is_vip, price, discount_price, show_id, user_id)
VALUES (25, 1, FALSE, 9600, NULL, 2, NULL),
  (26, 2, FALSE, 9600, NULL, 2, NULL),
  (27, 3, FALSE, 9600, NULL, 2, NULL),
  (28, 4, FALSE, 9600, NULL, 2, NULL),
  (29, 5, TRUE, 19200, NULL, 2, NULL),
  (30, 6, TRUE, 19200, NULL, 2, NULL),
  (31, 7, TRUE, 19200, NULL, 2, NULL),
  (32, 8, TRUE, 19200, NULL, 2, NULL),
  (33, 9, TRUE, 19200, NULL, 2, NULL),
  (34, 10, TRUE, 19200, NULL, 2, NULL),
  (35, 11, FALSE, 9600, NULL, 2, NULL),
  (36, 12, FALSE, 9600, NULL, 2, NULL),
  (37, 13, FALSE, 9600, NULL, 2, NULL),
  (38, 14, FALSE, 9600, NULL, 2, NULL),
  (39, 15, FALSE, 9600, NULL, 2, NULL),
  (40, 16, FALSE, 9600, NULL, 2, NULL),
  (41, 17, FALSE, 9600, NULL, 2, NULL),
  (42, 18, FALSE, 9600, NULL, 2, NULL),
  (43, 19, FALSE, 9600, NULL, 2, NULL),
  (44, 20, FALSE, 9600, NULL, 2, NULL),
  (45, 21, FALSE, 9600, NULL, 2, NULL),
  (46, 22, FALSE, 9600, NULL, 2, NULL),
  (47, 23, FALSE, 9600, NULL, 2, NULL),
  (48, 24, FALSE, 9600, NULL, 2, NULL);

-- tickets for event 'The Gamer'
INSERT INTO tickets (id, seat, is_vip, price, discount_price, show_id, user_id)
VALUES (49, 1, FALSE, 5000, 5000, 3, 1), -- 1
  (50, 2, TRUE, 10000, 9500, 3, 2),
  (51, 3, FALSE, 5000, 4750, 3, 2),
  (52, 4, FALSE, 5000, 4750, 3, 2),
  (53, 5, FALSE, 5000, 5000, 3, 1), -- 2
  (54, 6, FALSE, 5000, 5000, 3, 1), -- 3
  (55, 7, FALSE, 5000, 5000, 3, 1), -- 4
  (56, 8, FALSE, 5000, 5000, 3, 1), -- 5
  (57, 9, FALSE, 5000, 5000, 3, 1), -- 6
  (58, 10, FALSE, 5000, 5000, 3, 1), -- 7
  (59, 11, FALSE, 5000, 5000, 3, 1), -- 8
  (60, 12, FALSE, 5000, 5000, 3, 1); -- 9
