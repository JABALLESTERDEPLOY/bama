INSERT INTO roles(id_roles, role_name) VALUES (1,'ROLE_USER');
INSERT INTO roles(id_roles, role_name) VALUES (2,'ROLE_CLIENT');
INSERT INTO roles(id_roles, role_name) VALUES (3,'ROLE_PROV');
INSERT INTO roles(id_roles, role_name) VALUES (4,'ROLE_ADMIN');
ALTER SEQUENCE roles_id_roles_seq RESTART WITH 5;

INSERT INTO addresses (id_addresses, phone, number, street, floor, zipcode, city, province, country) VALUES (1,'111333444',12,'Calle Serrano','1A','06008','Badajoz','Badajoz','Spain');
INSERT INTO addresses (id_addresses, phone, number, street, floor, zipcode, city, province, country) VALUES (2,'555666777',13,'street456','3B','06007','Almendralejo','Badajoz','Spain');
ALTER SEQUENCE addresses_id_addresses_seq RESTART WITH 3;

INSERT INTO users(id_users, email, password, username, firstname, lastname, id_address) VALUES (1,'jaballepa@gmail.com','$2a$10$Jet.7/WUeiF6tAlKOVZtmeWXrohdLpiX7JbUOA2/2Ury/ucCnQlDq','joseantonio','Jose Antonio','Ballester Parra',1);
INSERT INTO users(id_users, email, password, username, firstname, lastname, id_address) VALUES (2,'mmarquezbarragan@gmail.com','$2a$10$Jet.7/WUeiF6tAlKOVZtmeWXrohdLpiX7JbUOA2/2Ury/ucCnQlDq','manuel','Manuel','Marquez Barragan',2);
ALTER SEQUENCE users_id_users_seq RESTART WITH 3;

INSERT INTO users_roles(id_users, id_roles) VALUES (1,1);
INSERT INTO users_roles(id_users, id_roles) VALUES (1,4);
INSERT INTO users_roles(id_users, id_roles) VALUES (2,1);
INSERT INTO users_roles(id_users, id_roles) VALUES (2,4);

INSERT INTO categories(id_categories, description) VALUES (1,'category1');
INSERT INTO categories(id_categories, description) VALUES (2,'category2');
INSERT INTO categories(id_categories, description) VALUES (3,'category3');
INSERT INTO categories(id_categories, description) VALUES (4,'category4');
INSERT INTO categories(id_categories, description) VALUES (5,'category5');
INSERT INTO categories(id_categories, description) VALUES (6,'category6');
INSERT INTO categories(id_categories, description) VALUES (7,'category7');
INSERT INTO categories(id_categories, description) VALUES (8,'category8');
INSERT INTO categories(id_categories, description) VALUES (9,'category9');
INSERT INTO categories(id_categories, description) VALUES (10,'category10');
ALTER SEQUENCE categories_id_categories_seq RESTART WITH 11;

INSERT INTO suppliers(id_suppliers, company_name, id_address) VALUES (1,'company1',2);
INSERT INTO suppliers(id_suppliers, company_name, id_address) VALUES (2,'company2',1);
INSERT INTO suppliers(id_suppliers, company_name, id_address) VALUES (3,'company3',1);
INSERT INTO suppliers(id_suppliers, company_name, id_address) VALUES (4,'company4',2);
ALTER SEQUENCE suppliers_id_suppliers_seq RESTART WITH 5;

INSERT INTO products(id_products, description, id_supplier, price, stock, id_category, status) VALUES (1,'description1',1,10,1,1,true);
INSERT INTO products(id_products, description, id_supplier, price, stock, id_category, status) VALUES (2,'description2',2,20,2,2,true);
INSERT INTO products(id_products, description, id_supplier, price, stock, id_category, status) VALUES (3,'description3',3,30,3,3,false);
INSERT INTO products(id_products, description, id_supplier, price, stock, id_category, status) VALUES (4,'description4',4,40,4,4,false);
ALTER SEQUENCE products_id_products_seq RESTART WITH 5;

INSERT INTO posts(id_posts, description, posted_date, price, product_condition, title, id_category, id_seller, id_product) VALUES (1,'description1','2021-01-01',10.9,'NEW','title1',1,1,1);
INSERT INTO posts(id_posts, description, posted_date, price, product_condition, title, id_category, id_seller, id_product) VALUES (2,'description2','2022-01-01',309.9,'USED','title2',3,2,2);
INSERT INTO posts(id_posts, description, posted_date, price, product_condition, title, id_category, id_seller, id_product) VALUES (3,'description3','2023-01-01',309.9,'SECOND_HAND','title3',5,2,3);
INSERT INTO posts(id_posts, description, posted_date, price, product_condition, title, id_category, id_seller, id_product) VALUES (4,'description4','2024-01-01',309.9,'DAMAGED','title4',2,1,4);
ALTER SEQUENCE posts_id_posts_seq RESTART WITH 4;

INSERT INTO images(id_images, url, id_product) VALUES (1, 'https://upload.wikimedia.org/wikipedia/en/5/53/Hollow_Knight_Silksong_cover_art.jpg', 1);
INSERT INTO images(id_images, url, id_product) VALUES (2, 'https://images3.memedroid.com/images/UPLOADED363/63456672d8ecb.webp', 2);
INSERT INTO images(id_images, url, id_product) VALUES (3, 'https://pbs.twimg.com/media/EKuy8r_XUAYCpip.jpg', 3);
INSERT INTO images(id_images, url, id_product) VALUES (4, 'https://media.revistagq.com/photos/5ca601b63492a9808ebf200d/16:9/w_1280,c_limit/youtubers_vegetta777_willyrex_3475.jpg', 4);
ALTER SEQUENCE images_id_images_seq RESTART WITH 5;

INSERT INTO posts_images(id_posts, id_images) VALUES(1,1);
INSERT INTO posts_images(id_posts, id_images) VALUES(1,2);
INSERT INTO posts_images(id_posts, id_images) VALUES(2,3);
INSERT INTO posts_images(id_posts, id_images) VALUES(2,4);
INSERT INTO posts_images(id_posts, id_images) VALUES(3,2);
INSERT INTO posts_images(id_posts, id_images) VALUES(3,4);
INSERT INTO posts_images(id_posts, id_images) VALUES(4,1);
INSERT INTO posts_images(id_posts, id_images) VALUES(4,3);

INSERT INTO invoices(id_invoices, invoice_date, id_buyer, id_post) VALUES(1,'2024-01-01',1,1);
INSERT INTO invoices(id_invoices, invoice_date, id_buyer, id_post) VALUES(2,'2024-01-01',1,2);
INSERT INTO invoices(id_invoices, invoice_date, id_buyer, id_post) VALUES(3,'2024-01-01',2,3);
INSERT INTO invoices(id_invoices, invoice_date, id_buyer, id_post) VALUES(4,'2024-01-01',2,4);
ALTER SEQUENCE invoices_id_invoices_seq RESTART WITH 5;

INSERT INTO reviews(id_reviews, comment, rating, id_post, id_user) VALUES(1,'No me ha gustado nada el producto.',0,1,1);
INSERT INTO reviews(id_reviews, comment, rating, id_post, id_user) VALUES(2,'Me ha encantado.',5,2,2);
INSERT INTO reviews(id_reviews, comment, rating, id_post, id_user) VALUES(3,'Ni fu ni fa.',2,3,2);
INSERT INTO reviews(id_reviews, comment, rating, id_post, id_user) VALUES(4,'Bien.',3,4,1);
ALTER SEQUENCE reviews_id_reviews_seq RESTART WITH 5;
