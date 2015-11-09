CREATE TABLE users (
  id       INTEGER PRIMARY KEY,
  name     VARCHAR(255),
  email    VARCHAR(255),
  birthday VARCHAR(255)
);

CREATE TABLE events (
  id         INTEGER PRIMARY KEY,
  name       VARCHAR(255),
  base_price DOUBLE,
  rating     VARCHAR(255)
);

CREATE TABLE tickets (
  id             INTEGER PRIMARY KEY,
  seat           INTEGER,
  is_vip         BOOLEAN,
  date           VARCHAR(255),
  price          DOUBLE,
  discount_price DOUBLE,
  event_id       INTEGER,
  user_id        INTEGER
);
