# ---sampledata
# --- !Ups



insert into category (id,name) values ( 1,'Electronics' );
insert into category (id,name) values ( 2,'Books' ) ;
insert into category (id,name) values ( 3,'Games' ) ;
insert into category (id,name) values ( 4,'instruments' ) ;
insert into category (id,name) values ( 5,'mobiles' ) ;


insert into product (product_id, product_Name,category,product_price, manufacturer, quantity) 
values ( 1,'ThinkPad X260','Electronics',20000.00,'Lenovo',10);
insert into product (product_id, product_Name,category,product_price, manufacturer, quantity) 
values ( 2,'MacBook','Electronics',1999.00,'Apple',100);
insert into product (product_id, product_Name,category,product_price, manufacturer, quantity) 
values ( 3,'Router','Electronics',145.00,'Lenovo',10);

insert into product (product_id, product_Name,category,product_price, manufacturer, quantity) 
values ( 4,'Papillon','Books',9.99,'Penguin',10);
insert into product (product_id, product_Name,category,product_price, manufacturer, quantity) 
values ( 5,'Nelson Mandela The Long Hard Walk to Freedom','Books',9.99,'Penguin',18);
insert into product (product_id, product_Name,category,product_price, manufacturer, quantity) 
values ( 6,'100 years of solitude','Books',9.99,'Penguin',81);


insert into product (product_id, product_Name,category,product_price, manufacturer, quantity) 
values ( 7,'Resident Evil 7 Bio Hazard','Games',69.99,'Ps4',25);
insert into product (product_id, product_Name,category,product_price, manufacturer, quantity) 
values ( 8,'Final Fantasy VII','Games',79.99,'Ps4',80);
insert into product (product_id, product_Name,category,product_price, manufacturer, quantity) 
values ( 9,'Metal Gear Solid','Games',59.99,'Ps4',10);

insert into product (product_id, product_Name,category,product_price, manufacturer, quantity) 
values ( 10,'Martin D 17','Instruments',12069.99,'Martin',2);
insert into product (product_id, product_Name,category,product_price, manufacturer, quantity) 
values ( 11,'Gibson F-5','Instruments',2069.99,'Gibson',4);
insert into product (product_id, product_Name,category,product_price, manufacturer, quantity) 
values ( 12,'Ritter Royal Flora Aurum','Instruments',2500.99,'Ritter Royal',1);


insert into product (product_id, product_Name,category,product_price, manufacturer, quantity) 
values ( 13,'One Plus Three','mobiles',399.99,'HTC',150);

insert into product (product_id, product_Name,category,product_price, manufacturer, quantity) 
values ( 14,'IPhone 7','mobiles',689.99,'Apple',110);

insert into product (product_id, product_Name,category,product_price, manufacturer, quantity) 
values ( 15,'Samsung S7 ','mobiles',899.99,'Samsung',19);



insert into user (email,name,password,role) values ('admin@products.com', 'Alice Admin', 'password', 'admin');
insert into user (email,name,password,role) values ('howie@products.com', 'Howie Lynch', 'password', 'admin');
insert into user (email,name,password,role) values ('shane@products.com', 'Shane McCann', 'password', 'admin');
insert into user (email,name,password,role) values ('phil@products.com', 'Phil O Donovan', 'password', 'admin');

insert into user (email,name,password,role) values ('manager@products.com', 'Matthew Manager', 'password', 'manager');
insert into user (email,name,password,role) values ('customer@products.com', 'Cian Customer', 'password', 'customer');
