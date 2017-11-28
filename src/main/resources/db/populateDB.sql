DELETE FROM cards;
DELETE FROM trainings;
DELETE FROM status;
DELETE FROM categories;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');
INSERT INTO users (name, email, password)
VALUES ('Admin_Admin', 'admin1@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('USER', 100000),
  ('USER', 100001),
  ('ADMIN', 100002);

INSERT INTO categories(name, user_id)
VALUES ('maritime', 100000),
  ('general', 100000);
INSERT INTO categories(name, user_id)
VALUES ('general', 100001),
 ('maritime', 100001);


INSERT INTO cards (runame, engname, user_id, cat_id ) VALUES
  ('привет', 'hello', 100001, 100005),
  ('причал', 'berth', 100001, 100006),
  ('как дела', 'how are you', 100001, 100005),
  ('погрузка', 'loading', 100001, 100006),
  ('да', 'yes', 100000, 100004),
  ('нет', 'no', 100000, 100004),
  ('ответ', 'answer', 100000, 100004),
  ('судно', 'vessel', 100000, 100003),
  ('порт', 'port', 100000, 100003);

INSERT INTO trainings(name) VALUES
  ('english'),
  ('russian');

INSERT INTO status(card_id,training_id,done) VALUES

  ( 100007, 100016, FALSE ),
  ( 100007, 100017, FALSE ),
  ( 100008, 100016, FALSE ),
  ( 100008, 100017, FALSE ),
  ( 100009, 100016, FALSE ),
  ( 100009, 100017, FALSE ),
  ( 100010, 100016, FALSE ),
  ( 100010, 100017, FALSE ),
  ( 100011, 100016, FALSE ),
  ( 100011, 100017, FALSE ),
  ( 100012, 100016, FALSE ),
  ( 100012, 100017, FALSE ),
  ( 100013, 100016, FALSE ),
  ( 100013, 100017, FALSE ),
  ( 100014, 100016, FALSE ),
  ( 100014, 100017, FALSE ),
  ( 100015, 100016, FALSE ),
  ( 100015, 100017, FALSE );





