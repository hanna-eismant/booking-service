-- users
INSERT INTO users (id, name, email, birthday)
VALUES (1, 'jhon', 'jhon@mail', '1987-05-05');
-- 10 tickets

INSERT INTO users (id, name, email, birthday)
VALUES (2, 'jane', 'jane@mail', '1986-10-17');
-- 3 tickets

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
VALUES(1, 'Big party hall', 24, ARRAY[4,5,6,7,8,9,10]),
  (2, 'Family hall', 12, ARRAY[2]);

--
INSERT INTO shows (id, date, auditorium_id, event_id)
VALUES (1, '2015-06-06T14:00:00.000', 1, 1),
  (2, '2015-06-07T22:00:00.000', 1, 1),
  (3, '2015-10-17T20:00:00.000', 2, 2);

-- tickets for event 'Hobbit'  (base price = 8_000 * 1.2 <- HIGH rating)
INSERT INTO tickets (id, seat, is_vip, price, discount_price, show_id, user_id)
VALUES (255, 1, FALSE, 9600, NULL, 1, NULL),
  (254, 2, FALSE, 9600, 4800, 1, 1), -- 10
  (253, 3, FALSE, 9600, NULL, 1, NULL),
  (252, 4, FALSE, 9600, NULL, 1, NULL),
  (251, 5, TRUE, 19200, NULL, 1, NULL),
  (250, 6, TRUE, 19200, NULL, 1, NULL),
  (249, 7, TRUE, 19200, NULL, 1, NULL),
  (248, 8, TRUE, 19200, NULL, 1, NULL),
  (247, 9, TRUE, 19200, NULL, 1, NULL),
  (246, 10, TRUE, 19200, NULL, 1, NULL),
  (245, 11, FALSE, 9600, NULL, 1, NULL),
  (244, 12, FALSE, 9600, NULL, 1, NULL),
  (243, 13, FALSE, 9600, NULL, 1, NULL),
  (242, 14, FALSE, 9600, NULL, 1, NULL),
  (241, 15, FALSE, 9600, NULL, 1, NULL),
  (240, 16, FALSE, 9600, NULL, 1, NULL),
  (239, 17, FALSE, 9600, NULL, 1, NULL),
  (238, 18, FALSE, 9600, NULL, 1, NULL),
  (237, 19, FALSE, 9600, NULL, 1, NULL),
  (236, 20, FALSE, 9600, NULL, 1, NULL),
  (235, 21, FALSE, 9600, NULL, 1, NULL),
  (234, 22, FALSE, 9600, NULL, 1, NULL),
  (233, 23, FALSE, 9600, NULL, 1, NULL),
  (232, 24, FALSE, 9600, NULL, 1, NULL);

INSERT INTO tickets (id, seat, is_vip, price, discount_price, show_id, user_id)
VALUES (219, 1, FALSE, 9600, NULL, 2, NULL),
  (218, 2, FALSE, 9600, NULL, 2, NULL),
  (217, 3, FALSE, 9600, NULL, 2, NULL),
  (216, 4, FALSE, 9600, NULL, 2, NULL),
  (215, 5, TRUE, 19200, NULL, 2, NULL),
  (214, 6, TRUE, 19200, NULL, 2, NULL),
  (213, 7, TRUE, 19200, NULL, 2, NULL),
  (212, 8, TRUE, 19200, NULL, 2, NULL),
  (211, 9, TRUE, 19200, NULL, 2, NULL),
  (210, 10, TRUE, 19200, NULL, 2, NULL),
  (209, 11, FALSE, 9600, NULL, 2, NULL),
  (208, 12, FALSE, 9600, NULL, 2, NULL),
  (207, 13, FALSE, 9600, NULL, 2, NULL),
  (206, 14, FALSE, 9600, NULL, 2, NULL),
  (205, 15, FALSE, 9600, NULL, 2, NULL),
  (204, 16, FALSE, 9600, NULL, 2, NULL),
  (203, 17, FALSE, 9600, NULL, 2, NULL),
  (202, 18, FALSE, 9600, NULL, 2, NULL),
  (201, 19, FALSE, 9600, NULL, 2, NULL),
  (200, 20, FALSE, 9600, NULL, 2, NULL),
  (199, 21, FALSE, 9600, NULL, 2, NULL),
  (198, 22, FALSE, 9600, NULL, 2, NULL),
  (197, 23, FALSE, 9600, NULL, 2, NULL),
  (196, 24, FALSE, 9600, NULL, 2, NULL);

-- tickets for event 'The Gamer'
INSERT INTO tickets (id, seat, is_vip, price, discount_price, show_id, user_id)
VALUES (231, 1, FALSE, 5000, 5000, 3, 1), -- 1
  (230, 2, TRUE, 10000, 9500, 3, 2),
  (229, 3, FALSE, 5000, 4750, 3, 2),
  (228, 4, FALSE, 5000, 4750, 3, 2),
  (227, 5, FALSE, 5000, 5000, 3, 1), -- 2
  (226, 6, FALSE, 5000, 5000, 3, 1), -- 3
  (225, 7, FALSE, 5000, 5000, 3, 1), -- 4
  (224, 8, FALSE, 5000, 5000, 3, 1), -- 5
  (223, 9, FALSE, 5000, 5000, 3, 1), -- 6
  (222, 10, FALSE, 5000, 5000, 3, 1), -- 7
  (221, 11, FALSE, 5000, 5000, 3, 1), -- 8
  (220, 12, FALSE, 5000, 5000, 3, 1); -- 9


-- statistic









