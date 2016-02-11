-- users
INSERT INTO users (id, name, email, birthday)
VALUES (255, 'jhon', 'jhon@mail', '1987-05-05');
-- 10 tickets

INSERT INTO users (id, name, email, birthday)
VALUES (254, 'jane', 'jane@mail', '1986-10-17');
-- 3 tickets

-- events
INSERT INTO events (id, name, base_price, rating)
VALUES (255, 'Hobbit', '8000.0', 'HIGH');
-- 2015-06-06T14:00:00.000 -> total 24 tickets
--                         -> free 23 tickets
-- 2015-06-07T22:00:00.000 -> total 24 tickets
--                         -> free 24 tickets

INSERT INTO events (id, name, base_price, rating)
VALUES (254, 'The Gamer', '5000.0', 'MID');
-- 2015-10-17T20:00:00.000 -> total 12 tickets
--                         -> free 0 tickets

--
INSERT INTO event_instances (id, date, auditorium, event_id)
VALUES (255, '2015-06-06T14:00:00.000', 'auditorium one', 255),
  (254, '2015-06-07T22:00:00.000', 'auditorium one', 255),
  (253, '2015-10-17T20:00:00.000', 'auditorium two', 254);

-- tickets for event 'Hobbit'  (base price = 8_000 * 1.2 <- HIGH rating)
INSERT INTO tickets (id, seat, is_vip, price, discount_price, event_instance_id, user_id)
VALUES (255, 1, FALSE, 9600, NULL, 255, NULL),
  (254, 2, FALSE, 9600, 4800, 255, 255), -- 10
  (253, 3, FALSE, 9600, NULL, 255, NULL),
  (252, 4, FALSE, 9600, NULL, 255, NULL),
  (251, 5, TRUE, 19200, NULL, 255, NULL),
  (250, 6, TRUE, 19200, NULL, 255, NULL),
  (249, 7, TRUE, 19200, NULL, 255, NULL),
  (248, 8, TRUE, 19200, NULL, 255, NULL),
  (247, 9, TRUE, 19200, NULL, 255, NULL),
  (246, 10, TRUE, 19200, NULL, 255, NULL),
  (245, 11, FALSE, 9600, NULL, 255, NULL),
  (244, 12, FALSE, 9600, NULL, 255, NULL),
  (243, 13, FALSE, 9600, NULL, 255, NULL),
  (242, 14, FALSE, 9600, NULL, 255, NULL),
  (241, 15, FALSE, 9600, NULL, 255, NULL),
  (240, 16, FALSE, 9600, NULL, 255, NULL),
  (239, 17, FALSE, 9600, NULL, 255, NULL),
  (238, 18, FALSE, 9600, NULL, 255, NULL),
  (237, 19, FALSE, 9600, NULL, 255, NULL),
  (236, 20, FALSE, 9600, NULL, 255, NULL),
  (235, 21, FALSE, 9600, NULL, 255, NULL),
  (234, 22, FALSE, 9600, NULL, 255, NULL),
  (233, 23, FALSE, 9600, NULL, 255, NULL),
  (232, 24, FALSE, 9600, NULL, 255, NULL);

INSERT INTO tickets (id, seat, is_vip, price, discount_price, event_instance_id, user_id)
VALUES (219, 1, FALSE, 9600, NULL, 254, NULL),
  (218, 2, FALSE, 9600, NULL, 254, NULL),
  (217, 3, FALSE, 9600, NULL, 254, NULL),
  (216, 4, FALSE, 9600, NULL, 254, NULL),
  (215, 5, TRUE, 19200, NULL, 254, NULL),
  (214, 6, TRUE, 19200, NULL, 254, NULL),
  (213, 7, TRUE, 19200, NULL, 254, NULL),
  (212, 8, TRUE, 19200, NULL, 254, NULL),
  (211, 9, TRUE, 19200, NULL, 254, NULL),
  (210, 10, TRUE, 19200, NULL, 254, NULL),
  (209, 11, FALSE, 9600, NULL, 254, NULL),
  (208, 12, FALSE, 9600, NULL, 254, NULL),
  (207, 13, FALSE, 9600, NULL, 254, NULL),
  (206, 14, FALSE, 9600, NULL, 254, NULL),
  (205, 15, FALSE, 9600, NULL, 254, NULL),
  (204, 16, FALSE, 9600, NULL, 254, NULL),
  (203, 17, FALSE, 9600, NULL, 254, NULL),
  (202, 18, FALSE, 9600, NULL, 254, NULL),
  (201, 19, FALSE, 9600, NULL, 254, NULL),
  (200, 20, FALSE, 9600, NULL, 254, NULL),
  (199, 21, FALSE, 9600, NULL, 254, NULL),
  (198, 22, FALSE, 9600, NULL, 254, NULL),
  (197, 23, FALSE, 9600, NULL, 254, NULL),
  (196, 24, FALSE, 9600, NULL, 254, NULL);

-- tickets for event 'The Gamer'
INSERT INTO tickets (id, seat, is_vip, price, discount_price, event_instance_id, user_id)
VALUES (231, 1, FALSE, 5000, 5000, 253, 255), -- 1
  (230, 2, TRUE, 10000, 9500, 253, 254),
  (229, 3, FALSE, 5000, 4750, 253, 254),
  (228, 4, FALSE, 5000, 4750, 253, 254),
  (227, 5, FALSE, 5000, 5000, 253, 255), -- 2
  (226, 6, FALSE, 5000, 5000, 253, 255), -- 3
  (225, 7, FALSE, 5000, 5000, 253, 255), -- 4
  (224, 8, FALSE, 5000, 5000, 253, 255), -- 5
  (223, 9, FALSE, 5000, 5000, 253, 255), -- 6
  (222, 10, FALSE, 5000, 5000, 253, 255), -- 7
  (221, 11, FALSE, 5000, 5000, 253, 255), -- 8
  (220, 12, FALSE, 5000, 5000, 253, 255); -- 9


-- statistic








