CREATE TABLE users (
  id NUMBER(19,0) GENERATED ALWAYS AS IDENTITY NOT NULL,
  username VARCHAR2(255) NOT NULL,
  email VARCHAR2(255) NOT NULL,
  name VARCHAR2(255),
  password VARCHAR2(255) NOT NULL,
  address VARCHAR2(255),
  phone VARCHAR2(255),
  enable NUMBER(1, 0),
  CONSTRAINT users_pk PRIMARY KEY (id),
    CONSTRAINT users_username_uk UNIQUE (username),
    CONSTRAINT users_email_uk UNIQUE (email)
);

CREATE TABLE role (
  id NUMBER(19,0) NOT NULL,
  name VARCHAR2(20),
  CONSTRAINT role_pk PRIMARY KEY (id)
);

INSERT INTO role VALUES (1, 'ROLE_USER');
INSERT INTO role VALUES (2, 'ROLE_MODERATOR');
INSERT INTO role VALUES (3, 'ROLE_ADMIN');

CREATE TABLE user_roles (
    user_id NUMBER(19, 0) NOT NULL,
    role_id NUMBER(19, 0) NOT NULL,
    CONSTRAINT user_roles_pk PRIMARY KEY (user_id, role_id),
    CONSTRAINT user_roles_user_fk FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT user_roles_role_fk FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE TABLE blog (
  id NUMBER(19, 0) GENERATED ALWAYS AS IDENTITY NOT NULL,
  image VARCHAR2(255),
  title VARCHAR2(255),
  create_at TIMESTAMP,
  description VARCHAR2(255),
  category_id NUMBER(19, 0),
  CONSTRAINT blog_pk PRIMARY KEY (id),
  CONSTRAINT blog_fk_category FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
);

CREATE TABLE category (
  id NUMBER(19,0) GENERATED ALWAYS AS IDENTITY NOT NULL,
  name VARCHAR2(255),
  CONSTRAINT tag_pk PRIMARY KEY (id)
);

CREATE TABLE chef (
  id NUMBER(19, 0) GENERATED ALWAYS AS IDENTITY NOT NULL,
  name VARCHAR2(255),
  address VARCHAR2(255),
  star NUMBER(19,0),
  review NUMBER(19,0),
  image VARCHAR2(255),
  working_time VARCHAR2(255),
  price NUMBER(19,0),
  distance NUMBER(19,1),
  CONSTRAINT chef_pk PRIMARY KEY (id),
);

CREATE TABLE dish (
  id NUMBER(19, 0) GENERATED ALWAYS AS IDENTITY NOT NULL,
  image VARCHAR2(255),
  name VARCHAR2(255),
  price NUMBER(19, 0),
  category_id NUMBER(19, 0),
  time VARCHAR2(255),
  distance NUMBER(19,1),
  quantity NUMBER(10, 0),
  description CLOB,
  CONSTRAINT dish_pk PRIMARY KEY (id),
  CONSTRAINT dish_fk_category FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
);

CREATE TABLE chef_dishs (
    chef_id NUMBER(19, 0) NOT NULL,
    dish_id NUMBER(19, 0) NOT NULL,
    CONSTRAINT chef_dishs_pk PRIMARY KEY (chef_id, dish_id),
    CONSTRAINT chef_dishs_chef_fk FOREIGN KEY (chef_id) REFERENCES chef(id) ON DELETE CASCADE,
    CONSTRAINT chef_dishs_dish_fk FOREIGN KEY (dish_id) REFERENCES dish(id) ON DELETE CASCADE
);

CREATE TABLE booking (
  id NUMBER(19, 0) GENERATED ALWAYS AS IDENTITY NOT NULL,
  chef_id NUMBER(19, 0),
  user_id NUMBER(19, 0),
  title VARCHAR2(255),
  description VARCHAR2(255),
  image VARCHAR2(255),
  start_time DATE,
  end_time DATE,
  email VARCHAR2(255),
  phone VARCHAR2(255),
  address VARCHAR2(255),
  hours NUMBER(19, 0),
  total_price NUMBER(19, 0),
  date_booking DATE,
  CONSTRAINT booking_pk PRIMARY KEY (id),
  CONSTRAINT booking_fk_chef FOREIGN KEY (chef_id) REFERENCES chef(id) ON DELETE CASCADE,
  CONSTRAINT booking_fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE service (
  id NUMBER(19, 0) GENERATED ALWAYS AS IDENTITY NOT NULL,
  chef_id NUMBER(19, 0),
  service_date DATE,
  CONSTRAINT service_pk PRIMARY KEY (id),
  CONSTRAINT service_fk_chef FOREIGN KEY (chef_id) REFERENCES chef(id) ON DELETE CASCADE
);

CREATE TABLE service_detail (
  service_id NUMBER(19, 0),
  dish_id NUMBER(19, 0),
  time VARCHAR2(25),
  CONSTRAINT service_detail_pk PRIMARY KEY( service_id, dish_id ),
  CONSTRAINT service_detail_fk FOREIGN KEY( service_id ) REFERENCES service ( id ) ON DELETE CASCADE,
  CONSTRAINT service_detail_dish_fk FOREIGN KEY (dish_id) REFERENCES dish(id) ON DELETE CASCADE
);

CREATE TABLE orders (
  id NUMBER(19, 0) GENERATED ALWAYS AS IDENTITY NOT NULL,
  user_id NUMBER(19, 0),
  address VARCHAR2(255),
  email VARCHAR2(255),
  phone VARCHAR2(255),
  total_price NUMBER(19, 0),
  date_order DATE,
  CONSTRAINT orders_pk PRIMARY KEY (id),
  CONSTRAINT orders_fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE order_details (
  id NUMBER(19, 0) GENERATED ALWAYS AS IDENTITY NOT NULL,
  order_id NUMBER(19, 0)  NOT NULL,
  dish_id NUMBER(19, 0) NOT NULL,
  price NUMBER(19, 0),
  quantity NUMBER(10, 0),
  sub_total NUMBER(19, 0),
  enable NUMBER(1,0),
  CONSTRAINT order_detail_pk PRIMARY KEY (id),
  CONSTRAINT order_details_fk_order FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
  CONSTRAINT order_details_dish_fk FOREIGN KEY( dish_id ) REFERENCES dish( id ) ON DELETE CASCADE,
  CONSTRAINT check_values CHECK (enable IN (0, 1, 2))
);

ALTER TABLE category MODIFY(ID GENERATED AS IDENTITY (START WITH 1));

INSERT INTO category (name) VALUES ('Appetizer');
 INSERT INTO category (name) VALUES ('Main Course');
 INSERT INTO category (name) VALUES ('Dessert');
 INSERT INTO category (name) VALUES ('Beverage');
 INSERT INTO category (name) VALUES ('Salad');
 INSERT INTO category (name) VALUES ('Snack');
 COMMIT;
 
 ALTER TABLE dish MODIFY(ID GENERATED AS IDENTITY (START WITH 1));
 INSERT INTO dish (image, name, price, category_id, time, distance, quantity, description) VALUES
  ('dish1.jpg', 'Caesar Salad', 12.99, 5, '20 minutes', 1.5, 2, 'Fresh and crispy Caesar salad with homemade dressing.');
 INSERT INTO dish (image, name, price, category_id, time, distance, quantity, description) VALUES  ('dish2.jpg', 'Margherita Pizza', 15.99, 2, '30 minutes', 2.0, 1, 'Classic Margherita pizza with tomato, mozzarella, and basil.');
  INSERT INTO dish (image, name, price, category_id, time, distance, quantity, description) VALUES ('dish3.jpg', 'Chocolate Brownie', 6.99, 3, '15 minutes', 1.0, 3, 'Decadent chocolate brownie with a gooey center.');
  INSERT INTO dish (image, name, price, category_id, time, distance, quantity, description) VALUES ('dish4.jpg', 'Iced Coffee', 4.49, 4, '5 minutes', 0.5, 1, 'Refreshing iced coffee to kickstart your day.');
  INSERT INTO dish (image, name, price, category_id, time, distance, quantity, description) VALUES ('dish5.jpg', 'Spring Rolls', 8.99, 1, '25 minutes', 1.8, 4, 'Vegetarian spring rolls with a sweet and spicy dipping sauce.');
  INSERT INTO dish (image, name, price, category_id, time, distance, quantity, description) VALUES ('dish6.jpg', 'Grilled Chicken', 18.99, 2, '35 minutes', 2.5, 1, 'Juicy grilled chicken with a savory marinade.');
 INSERT INTO dish (image, name, price, category_id, time, distance, quantity, description) VALUES  ('dish7.jpg', 'Fruit Smoothie', 7.99, 4, '10 minutes', 0.8, 1, 'Healthy fruit smoothie with a blend of fresh fruits.');
  INSERT INTO dish (image, name, price, category_id, time, distance, quantity, description) VALUES ('dish8.jpg', 'Mango Tango Salad', 14.99, 5, '30 minutes', 2.2, 2, 'Exotic mango salad with a tangy twist.');
  INSERT INTO dish (image, name, price, category_id, time, distance, quantity, description) VALUES ('dish9.jpg', 'Cheese Platter', 22.99, 1, '40 minutes', 3.0, 1, 'Artisanal cheese platter with assorted cheeses and crackers.');
  INSERT INTO dish (image, name, price, category_id, time, distance, quantity, description) VALUES ('dish10.jpg', 'Spicy Chicken Wings', 10.99, 6, '25 minutes', 1.7, 6, 'Crispy and spicy chicken wings for the ultimate flavor.');
  INSERT INTO dish (image, name, price, category_id, time, distance, quantity, description) VALUES ('dish11.jpg', 'Pasta Carbonara', 16.99, 2, '35 minutes', 2.3, 1, 'Creamy pasta carbonara with bacon and Parmesan.');
  INSERT INTO dish (image, name, price, category_id, time, distance, quantity, description) VALUES ('dish12.jpg', 'Fudge Sundae', 9.99, 3, '15 minutes', 1.2, 1, 'Indulgent fudge sundae with whipped cream and nuts.');
  INSERT INTO dish (image, name, price, category_id, time, distance, quantity, description) VALUES ('dish13.jpg', 'Green Tea Smoothie', 5.99, 4, '10 minutes', 0.7, 1, 'Refreshing green tea smoothie with a hint of sweetness.');
 INSERT INTO dish (image, name, price, category_id, time, distance, quantity, description) VALUES  ('dish14.jpg', 'Caprese Salad', 11.99, 5, '20 minutes', 1.4, 2, 'Classic Caprese salad with fresh tomatoes, mozzarella, and basil.');
  INSERT INTO dish (image, name, price, category_id, time, distance, quantity, description) VALUES ('dish15.jpg', 'Nachos Supreme', 13.99, 6, '30 minutes', 2.0, 3, 'Loaded nachos with cheese, guacamole, sour cream, and salsa.');
COMMIT;
delete form dish;
  ALTER TABLE chef MODIFY(ID GENERATED AS IDENTITY (START WITH 1));
   INSERT INTO chef (name, address, star, review, image, working_time, price, distance) VALUES
 INSERT INTO chef (name, address, star, review, image, working_time, price, distance) VALUES ('Chef John', '123 Main St', 4, 20, 'chef1.jpg', '9 AM - 6 PM', 50, 5.0);
 INSERT INTO chef (name, address, star, review, image, working_time, price, distance) VALUES ('Chef Maria', '456 Oak St', 5, 35, 'chef2.jpg', '10 AM - 7 PM', 65, 8.5);
 INSERT INTO chef (name, address, star, review, image, working_time, price, distance) VALUES ('Chef Alex', '789 Pine St', 4, 25, 'chef3.jpg', '8 AM - 5 PM', 55, 3.5);
 INSERT INTO chef (name, address, star, review, image, working_time, price, distance) VALUES ('Chef Emily', '101 Elm St', 4, 18, 'chef4.jpg', '11 AM - 8 PM', 60, 7.0);
 INSERT INTO chef (name, address, star, review, image, working_time, price, distance) VALUES ('Chef Michael', '202 Cedar St', 3, 15, 'chef5.jpg', '12 PM - 9 PM', 45, 6.2);
 INSERT INTO chef (name, address, star, review, image, working_time, price, distance) VALUES ('Chef Jessica', '303 Birch St', 5, 40, 'chef6.jpg', '7 AM - 4 PM', 70, 9.0);
COMMIT;
 INSERT INTO chef_dishs (chef_id, dish_id) VALUES
  (1, 1);
 INSERT INTO chef_dishs (chef_id, dish_id) VALUES (1, 3);
 INSERT INTO chef_dishs (chef_id, dish_id) VALUES (1, 7);
 INSERT INTO chef_dishs (chef_id, dish_id) VALUES (1, 11);
 INSERT INTO chef_dishs (chef_id, dish_id) VALUES (2, 15);
 INSERT INTO chef_dishs (chef_id, dish_id) VALUES (2, 3);
 INSERT INTO chef_dishs (chef_id, dish_id) VALUES (2, 2);
 INSERT INTO chef_dishs (chef_id, dish_id) VALUES (2, 4);
 INSERT INTO chef_dishs (chef_id, dish_id) VALUES (2, 5);
 INSERT INTO chef_dishs (chef_id, dish_id) VALUES (3, 6);
 INSERT INTO chef_dishs (chef_id, dish_id) VALUES (3, 8);
 INSERT INTO chef_dishs (chef_id, dish_id) VALUES (3, 10);
 INSERT INTO chef_dishs (chef_id, dish_id) VALUES (4, 14);
 INSERT INTO chef_dishs (chef_id, dish_id) VALUES (4, 12);
 INSERT INTO chef_dishs (chef_id, dish_id) VALUES (4, 9);
 INSERT INTO chef_dishs (chef_id, dish_id) VALUES (4, 13);
 INSERT INTO chef_dishs (chef_id, dish_id) VALUES (5, 1);
 INSERT INTO chef_dishs (chef_id, dish_id) VALUES (5, 12);
 INSERT INTO chef_dishs (chef_id, dish_id) VALUES (6, 8);
 INSERT INTO chef_dishs (chef_id, dish_id) VALUES (6, 7);
 COMMIT;

ALTER TABLE service MODIFY(ID GENERATED AS IDENTITY (START WITH 1));
 INSERT INTO service (chef_id, service_date) VALUES(1, SYSDATE + 2);
 INSERT INTO service (chef_id, service_date) VALUES(1, SYSDATE + 3);
 INSERT INTO service (chef_id, service_date) VALUES (2, SYSDATE + 2);
 INSERT INTO service (chef_id, service_date) VALUES (2, SYSDATE + 3);
 INSERT INTO service (chef_id, service_date) VALUES (3, SYSDATE + 2);
 INSERT INTO service (chef_id, service_date) VALUES (3, SYSDATE + 3);
 INSERT INTO service (chef_id, service_date) VALUES (4, SYSDATE + 2);
 INSERT INTO service (chef_id, service_date) VALUES (4, SYSDATE + 3);
 INSERT INTO service (chef_id, service_date) VALUES (5, SYSDATE + 2);
 INSERT INTO service (chef_id, service_date) VALUES (5, SYSDATE + 3);
 INSERT INTO service (chef_id, service_date) VALUES (6, SYSDATE + 2);
 INSERT INTO service (chef_id, service_date) VALUES (6, SYSDATE + 3);
  COMMIT;

  INSERT INTO service_detail (service_id, dish_id, time) VALUES
  (1, 1, '20 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (1, 3, '15 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (1, 7, '10 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (1, 11, '35 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (2, 7, '10 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (2, 11, '35 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (3, 15, '30 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (3, 3, '15 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (3, 4, '5 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (3, 5, '25 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (4, 3, '15 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (4, 2, '30 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (4, 4, '5 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (4, 5, '25 minutes');
INSERT INTO service_detail (service_id, dish_id, time) VALUES (5, 6, '35 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (5, 8, '30 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (5, 10, '25 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (6, 6, '35 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (6, 8, '30 minutes');
INSERT INTO service_detail (service_id, dish_id, time) VALUES (7, 14, '20 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (7, 12, '15 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (7, 9, '40 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (7, 13, '10 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (8, 14, '20 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (8, 12, '15 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (8, 9, '40 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (8, 13, '10 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (9, 1, '20 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (9, 12, '15 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (10, 1, '20 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (10, 12, '15 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (11, 8, '30 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (11, 7, '10 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (12, 8, '30 minutes');
 INSERT INTO service_detail (service_id, dish_id, time) VALUES (12, 7, '10 minutes');

COMMIT;

-- Chèn dữ liệu vào bảng booking với một chef_id và user_id cụ thể
INSERT INTO booking (chef_id, user_id, title, description, image, start_time, end_time, email, phone, address, hours, total_price, date_booking) VALUES
  (1, 1, 'Private Dinner', 'An intimate dinner experience', 'dinner_image.jpg', TO_DATE('2023-11-05 18:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2023-11-05 21:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'admin@gmail.com', '123456789', '54 California', 3, 150, TO_DATE('2023-11-02', 'YYYY-MM-DD'));

INSERT INTO booking (chef_id, user_id, title, description, image, start_time, end_time, email, phone, address, hours, total_price, date_booking) VALUES
  (1, 1, 'Cooking Workshop', 'Hands-on cooking experience', 'cooking_workshop_image.jpg', TO_DATE('2023-11-07 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2023-11-07 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'admin@gmail.com', '123456789', '54 California', 3, 180, TO_DATE('2023-11-03', 'YYYY-MM-DD'));

INSERT INTO booking (chef_id, user_id, title, description, image, start_time, end_time, email, phone, address, hours, total_price, date_booking) VALUES
  (1, 1, 'Anniversary Dinner', 'Celebrate a special anniversary', 'anniversary_dinner_image.jpg', TO_DATE('2023-11-12 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2023-11-12 21:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'admin@gmail.com', '123456789', '54 California', 3, 210, TO_DATE('2023-11-06', 'YYYY-MM-DD'));

INSERT INTO booking (chef_id, user_id, title, description, image, start_time, end_time, email, phone, address, hours, total_price, date_booking) VALUES
  (1, 1, 'Dinner Date', 'Romantic dinner for two', 'romantic_dinner_image.jpg', TO_DATE('2023-11-22 19:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2023-11-22 22:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'admin@gmail.com', '123456789', '54 California', 3, 210, TO_DATE('2023-11-15', 'YYYY-MM-DD'));

COMMIT;


-- INSERTs for orders
INSERT INTO orders (user_id, address, email, phone, total_price, date_order) VALUES
  (1, '54 California', 'admin@gmail.com', '113456', 0, TO_DATE('2023-11-05', 'YYYY-MM-DD'));
INSERT INTO orders (user_id, address, email, phone, total_price, date_order) VALUES  (1, '54 California', 'admin@gmail.com', '113456', 0, TO_DATE('2023-11-07', 'YYYY-MM-DD'));
 INSERT INTO orders (user_id, address, email, phone, total_price, date_order) VALUES (1, '54 California', 'admin@gmail.com', '113456', 0, TO_DATE('2023-11-12', 'YYYY-MM-DD'));
 INSERT INTO orders (user_id, address, email, phone, total_price, date_order) VALUES (1, '54 California', 'admin@gmail.com', '113456', 0, TO_DATE('2023-11-22', 'YYYY-MM-DD'));
 INSERT INTO orders (user_id, address, email, phone, total_price, date_order) VALUES (1, '54 California', 'admin@gmail.com', '113456', 0, TO_DATE('2023-11-25', 'YYYY-MM-DD'));
COMMIT;
-- INSERTs for order_details
INSERT INTO order_details (order_id, dish_id, price, quantity, sub_total, enable) VALUES
  (1, 1, 12.99, 2, 25.98, 1);
 INSERT INTO order_details (order_id, dish_id, price, quantity, sub_total, enable) VALUES (1, 3, 6.99, 3, 20.97, 2);
 INSERT INTO order_details (order_id, dish_id, price, quantity, sub_total, enable) VALUES (1, 7, 7.99, 1, 7.99, 0);
 INSERT INTO order_details (order_id, dish_id, price, quantity, sub_total, enable) VALUES (2, 15, 13.99, 3, 41.97, 1);
 INSERT INTO order_details (order_id, dish_id, price, quantity, sub_total, enable) VALUES (2, 3, 6.99, 2, 13.98, 2);
 INSERT INTO order_details (order_id, dish_id, price, quantity, sub_total, enable) VALUES (2, 2, 15.99, 1, 15.99, 0);
 INSERT INTO order_details (order_id, dish_id, price, quantity, sub_total, enable) VALUES (3, 6, 18.99, 1, 18.99, 1);
 INSERT INTO order_details (order_id, dish_id, price, quantity, sub_total, enable) VALUES (3, 8, 14.99, 2, 29.98, 2);
 INSERT INTO order_details (order_id, dish_id, price, quantity, sub_total, enable) VALUES (3, 10, 10.99, 6, 65.94, 0);
 INSERT INTO order_details (order_id, dish_id, price, quantity, sub_total, enable) VALUES (4, 14, 11.99, 2, 23.98, 1);
 INSERT INTO order_details (order_id, dish_id, price, quantity, sub_total, enable) VALUES (4, 12, 9.99, 1, 9.99, 2);
 INSERT INTO order_details (order_id, dish_id, price, quantity, sub_total, enable) VALUES (4, 9, 22.99, 1, 22.99, 0);
 INSERT INTO order_details (order_id, dish_id, price, quantity, sub_total, enable) VALUES (5, 1, 12.99, 3, 38.97, 1);
 INSERT INTO order_details (order_id, dish_id, price, quantity, sub_total, enable) VALUES (5, 12, 9.99, 1, 9.99, 2);
 INSERT INTO order_details (order_id, dish_id, price, quantity, sub_total, enable) VALUES (5, 8, 14.99, 1, 14.99, 0);
COMMIT;

