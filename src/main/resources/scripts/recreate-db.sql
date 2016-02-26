DROP TABLE tickets
IF EXISTS;

CREATE TABLE tickets (
  id             BIGINT GENERATED BY DEFAULT AS IDENTITY (
  START WITH 50
  INCREMENT BY 1 ) PRIMARY KEY,
  seat           INTEGER NOT NULL,
  is_vip         BOOLEAN NOT NULL,
  price          DOUBLE  NOT NULL,
  discount_price DOUBLE,
  show_id        BIGINT  NOT NULL,
  user_id        BIGINT
);

DROP TABLE shows
IF EXISTS;

CREATE TABLE shows (
  id            BIGINT GENERATED BY DEFAULT AS IDENTITY (
  START WITH 10
  INCREMENT BY 1 ) PRIMARY KEY,
  date          VARCHAR(255) NOT NULL,
  auditorium_id BIGINT       NOT NULL,
  event_id      BIGINT       NOT NULL
);

DROP TABLE auditoriums
IF EXISTS;

CREATE TABLE auditoriums (
  id        BIGINT GENERATED BY DEFAULT AS IDENTITY (
  START WITH 10
  INCREMENT BY 1 ) PRIMARY KEY,
  name      VARCHAR(255) NOT NULL,
  seats     INTEGER      NOT NULL,
  vip_seats INTEGER ARRAY DEFAULT ARRAY []
  --   name  INT ARRAY DEFAULT ARRAY [],
  --   names VARCHAR(20) ARRAY[10]
);

ALTER TABLE auditoriums ADD CONSTRAINT auditorium_name UNIQUE (name);

DROP TABLE users
IF EXISTS;

CREATE TABLE users (
  id       BIGINT GENERATED BY DEFAULT AS IDENTITY (
  START WITH 10
  INCREMENT BY 1 ) PRIMARY KEY,
  name     VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email    VARCHAR(255) NOT NULL,
  birthday VARCHAR(255) NOT NULL,
  roles    VARCHAR(255) ARRAY DEFAULT ARRAY []
);

ALTER TABLE users ADD CONSTRAINT user_name UNIQUE (name);
ALTER TABLE users ADD CONSTRAINT user_email UNIQUE (email);

DROP TABLE events
IF EXISTS;

CREATE TABLE events (
  id         BIGINT GENERATED BY DEFAULT AS IDENTITY (
  START WITH 10
  INCREMENT BY 1 ) PRIMARY KEY,
  name       VARCHAR(255) NOT NULL,
  base_price DOUBLE       NOT NULL,
  rating     VARCHAR(255) NOT NULL
);

ALTER TABLE events ADD CONSTRAINT event_name UNIQUE (name);

ALTER TABLE shows ADD FOREIGN KEY (event_id) REFERENCES events (id)
  ON DELETE CASCADE;
ALTER TABLE shows ADD FOREIGN KEY (auditorium_id) REFERENCES auditoriums (id)
  ON DELETE CASCADE;

ALTER TABLE tickets ADD FOREIGN KEY (show_id) REFERENCES shows (id)
  ON DELETE CASCADE;
ALTER TABLE tickets ADD FOREIGN KEY (user_id) REFERENCES users (id)
  ON DELETE SET NULL;

DROP TABLE statistic
IF EXISTS;

CREATE TABLE statistic (
  id      BIGINT PRIMARY KEY,
  name    VARCHAR(255) NOT NULL,
  type    VARCHAR(255) NOT NULL,
  counter BIGINT
);

CREATE TABLE IF NOT EXISTS persistent_logins (
  username varchar(64) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null
);

ALTER TABLE statistic ADD CONSTRAINT statistic_name_type UNIQUE (name, type);
