DROP TABLE users
IF EXISTS;

CREATE TABLE users (
  id       BIGINT PRIMARY KEY,
  name     VARCHAR(255),
  email    VARCHAR(255),
  birthday VARCHAR(255)
);

DROP TABLE events
IF EXISTS;

CREATE TABLE events (
  id         BIGINT PRIMARY KEY,
  name       VARCHAR(255),
  base_price DOUBLE,
  rating     VARCHAR(255)
);

DROP TABLE tickets
IF EXISTS;

CREATE TABLE tickets (
  id             BIGINT PRIMARY KEY,
  seat           INTEGER,
  is_vip         BOOLEAN,
  date           VARCHAR(255),
  price          DOUBLE,
  discount_price DOUBLE,
  event_id       INTEGER,
  user_id        INTEGER
);
