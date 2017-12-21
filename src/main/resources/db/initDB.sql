DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS trainings;
DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR                  NOT NULL,
  email      VARCHAR                  NOT NULL,
  password   VARCHAR                  NOT NULL,
  registered TIMESTAMP DEFAULT now()  NOT NULL,
  enabled    BOOL DEFAULT TRUE        NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE categories (
  id        INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name      VARCHAR                 NOT NULL,
  user_id   INTEGER                 NOT NULL,
  date_time TIMESTAMP DEFAULT now() NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE cards (
  id        INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id   INTEGER                       NOT NULL,
  cat_id    INTEGER                       NOT NULL,
  date_time TIMESTAMP DEFAULT now()       NOT NULL,
  engname   VARCHAR                       NOT NULL,
  runame    VARCHAR                       NOT NULL,
  done      BOOL DEFAULT FALSE            NOT NULL,
  progress  INTEGER DEFAULT 0             NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (cat_id) REFERENCES categories (id) ON DELETE CASCADE
);

CREATE TABLE trainings (

  id        INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR                  NOT NULL
  /*done      BOOL DEFAULT FALSE            NOT NULL,*/
);

CREATE TABLE status (
  id        INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  card_id   INTEGER                       NOT NULL,
  training_id INTEGER                     NOT NULL,
  done      BOOL DEFAULT FALSE            NOT NULL,

  FOREIGN KEY (card_id) REFERENCES cards (id) ON DELETE CASCADE,
  FOREIGN KEY (training_id) REFERENCES trainings (id) ON DELETE CASCADE

)


