DROP TABLE tickets
IF EXISTS;

CREATE TABLE tickets (
  id             BIGINT PRIMARY KEY,
  seat           INTEGER      NOT NULL,
  is_vip         BOOLEAN      NOT NULL,
  date           VARCHAR(255) NOT NULL,
  price          DOUBLE       NOT NULL,
  discount_price DOUBLE,
  event_id       INTEGER      NOT NULL,
  user_id        INTEGER
);

DROP TABLE users
IF EXISTS;

CREATE TABLE users (
  id       BIGINT PRIMARY KEY,
  name     VARCHAR(255) NOT NULL,
  email    VARCHAR(255) NOT NULL,
  birthday VARCHAR(255) NOT NULL
);

ALTER TABLE users ADD CONSTRAINT user_name UNIQUE (name);
ALTER TABLE users ADD CONSTRAINT user_email UNIQUE (email);

DROP TABLE events
IF EXISTS;

CREATE TABLE events (
  id         BIGINT PRIMARY KEY,
  name       VARCHAR(255) NOT NULL,
  base_price DOUBLE       NOT NULL,
  rating     VARCHAR(255) NOT NULL
);

ALTER TABLE tickets ADD FOREIGN KEY (event_id) REFERENCES events (id)
  ON DELETE CASCADE;
ALTER TABLE tickets ADD FOREIGN KEY (user_id) REFERENCES users (id)
  ON DELETE SET NULL;
