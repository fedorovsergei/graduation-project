DROP TABLE meal IF EXISTS;
DROP TABLE vote IF EXISTS;
DROP TABLE restaurant IF EXISTS;
DROP TABLE users IF EXISTS;
DROP SEQUENCE global_seq IF EXISTS;


CREATE SEQUENCE global_seq AS INTEGER START WITH 100000;


CREATE TABLE users
(
    id       INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    username varchar(255)                     NOT NULL,
    password VARCHAR(255)                     NOT NULL,
    enabled  boolean      default true,
    role     varchar(255) default 'ROLE_USER' NOT NULL,
    CONSTRAINT username UNIQUE (username)
);
CREATE UNIQUE INDEX users_unique_name ON users (username);


CREATE TABLE restaurant
(
    id   INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT restaurant_name UNIQUE (name)
);


CREATE TABLE vote
(
    id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    restaurant_id INTEGER               NOT NULL,
    user_id       INTEGER               NOT NULL,
    date_input    TIMESTAMP default now NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX user_id_date_input ON vote (user_id, date_input);
CREATE INDEX date_input_vote ON vote (date_input);


CREATE TABLE meal
(
    id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    restaurant_id INTEGER               NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    price         INTEGER               NOT NULL,
    date_input    TIMESTAMP default now NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE,
    CONSTRAINT meal_name_date UNIQUE (name, date_input)
);
CREATE INDEX date_input_meal ON meal (date_input);