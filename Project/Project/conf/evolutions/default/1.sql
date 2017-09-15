# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table basket (
  id                            bigint not null,
  customer_email                varchar(255),
  constraint uq_basket_customer_email unique (customer_email),
  constraint pk_basket primary key (id)
);
create sequence basket_seq;

create table category (
  id                            bigint not null,
  name                          varchar(255),
  constraint pk_category primary key (id)
);
create sequence category_seq;

create table category_product (
  category_id                   bigint not null,
  product_product_id            bigint not null,
  constraint pk_category_product primary key (category_id,product_product_id)
);

create table credit_card (
  cc_id                         bigint auto_increment not null,
  name                          varchar(255),
  card_number                   varchar(255),
  month                         varchar(255),
  year                          varchar(255),
  card_cvv                      varchar(255),
  constraint pk_credit_card primary key (cc_id)
);

create table feedback (
  feedback_id                   bigint auto_increment not null,
  first_name                    varchar(255),
  last_name                     varchar(255),
  email                         varchar(255),
  phone_number                  varchar(255),
  message                       varchar(255),
  constraint pk_feedback primary key (feedback_id)
);

create table order_item (
  id                            bigint not null,
  order_id                      bigint,
  basket_id                     bigint,
  wish_list_id                  bigint,
  product_product_id            bigint,
  quantity                      integer,
  price                         double,
  constraint pk_order_item primary key (id)
);
create sequence order_item_seq;

create table product (
  product_id                    bigint auto_increment not null,
  product_name                  varchar(255),
  category                      varchar(255),
  product_price                 double,
  manufacturer                  varchar(255),
  quantity                      integer,
  constraint pk_product primary key (product_id)
);

create table request_product (
  request_product_id            bigint auto_increment not null,
  category                      varchar(255),
  product_title                 varchar(255),
  product_description           varchar(255),
  name                          varchar(255),
  mobile                        varchar(255),
  email                         varchar(255),
  constraint pk_request_product primary key (request_product_id)
);

create table review (
  product_id                    bigint auto_increment not null,
  review                        varchar(255),
  constraint pk_review primary key (product_id)
);

create table shop_order (
  id                            bigint not null,
  order_date                    timestamp,
  customer_email                varchar(255),
  constraint pk_shop_order primary key (id)
);
create sequence shop_order_seq;

create table user (
  role                          varchar(255),
  email                         varchar(255) not null,
  name                          varchar(255),
  password                      varchar(255),
  confirm_password              varchar(255),
  phone                         varchar(255),
  address                       varchar(255),
  date_of_birth                 varchar(255),
  constraint pk_user primary key (email)
);

create table wish_list (
  id                            bigint not null,
  customer_email                varchar(255),
  constraint uq_wish_list_customer_email unique (customer_email),
  constraint pk_wish_list primary key (id)
);
create sequence wish_list_seq;

alter table basket add constraint fk_basket_customer_email foreign key (customer_email) references user (email) on delete restrict on update restrict;

alter table category_product add constraint fk_category_product_category foreign key (category_id) references category (id) on delete restrict on update restrict;
create index ix_category_product_category on category_product (category_id);

alter table category_product add constraint fk_category_product_product foreign key (product_product_id) references product (product_id) on delete restrict on update restrict;
create index ix_category_product_product on category_product (product_product_id);

alter table order_item add constraint fk_order_item_order_id foreign key (order_id) references shop_order (id) on delete restrict on update restrict;
create index ix_order_item_order_id on order_item (order_id);

alter table order_item add constraint fk_order_item_basket_id foreign key (basket_id) references basket (id) on delete restrict on update restrict;
create index ix_order_item_basket_id on order_item (basket_id);

alter table order_item add constraint fk_order_item_wish_list_id foreign key (wish_list_id) references wish_list (id) on delete restrict on update restrict;
create index ix_order_item_wish_list_id on order_item (wish_list_id);

alter table order_item add constraint fk_order_item_product_product_id foreign key (product_product_id) references product (product_id) on delete restrict on update restrict;
create index ix_order_item_product_product_id on order_item (product_product_id);

alter table shop_order add constraint fk_shop_order_customer_email foreign key (customer_email) references user (email) on delete restrict on update restrict;
create index ix_shop_order_customer_email on shop_order (customer_email);

alter table wish_list add constraint fk_wish_list_customer_email foreign key (customer_email) references user (email) on delete restrict on update restrict;


# --- !Downs

alter table basket drop constraint if exists fk_basket_customer_email;

alter table category_product drop constraint if exists fk_category_product_category;
drop index if exists ix_category_product_category;

alter table category_product drop constraint if exists fk_category_product_product;
drop index if exists ix_category_product_product;

alter table order_item drop constraint if exists fk_order_item_order_id;
drop index if exists ix_order_item_order_id;

alter table order_item drop constraint if exists fk_order_item_basket_id;
drop index if exists ix_order_item_basket_id;

alter table order_item drop constraint if exists fk_order_item_wish_list_id;
drop index if exists ix_order_item_wish_list_id;

alter table order_item drop constraint if exists fk_order_item_product_product_id;
drop index if exists ix_order_item_product_product_id;

alter table shop_order drop constraint if exists fk_shop_order_customer_email;
drop index if exists ix_shop_order_customer_email;

alter table wish_list drop constraint if exists fk_wish_list_customer_email;

drop table if exists basket;
drop sequence if exists basket_seq;

drop table if exists category;
drop sequence if exists category_seq;

drop table if exists category_product;

drop table if exists credit_card;

drop table if exists feedback;

drop table if exists order_item;
drop sequence if exists order_item_seq;

drop table if exists product;

drop table if exists request_product;

drop table if exists review;

drop table if exists shop_order;
drop sequence if exists shop_order_seq;

drop table if exists user;

drop table if exists wish_list;
drop sequence if exists wish_list_seq;

