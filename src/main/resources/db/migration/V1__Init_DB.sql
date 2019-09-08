create sequence hibernate_sequence start 1 increment 1;

create table brands (id int4 not null, name varchar(255), primary key (id));

create table carbodyes (id int4 not null, name varchar(255), primary key (id));

create table cars (id int4 not null, date int8 not null, image_base64 text, sold boolean not null, id_brand int4, id_carbody int4, id_category int4, id_motor int4, id_transmission int4, id_user int4, primary key (id));

create table categories (id int4 not null, name varchar(255), primary key (id));

create table motors (id int4 not null, name varchar(255), primary key (id));

create table transmissions (id int4 not null, name varchar(255), primary key (id));

create table users (id int4 not null, login varchar(255), password varchar(255), role varchar(255), primary key (id));

alter table if exists cars add constraint FKvgjcvbkbfmbadq4mkh38spau foreign key (id_brand) references brands;

alter table if exists cars add constraint FK5bvuget1s6nen8tjsbmo68swh foreign key (id_carbody) references carbodyes;

alter table if exists cars add constraint FKdjia9p28nx1xg9fthif401i19 foreign key (id_category) references categories;

alter table if exists cars add constraint FKm8hy4tt9f7kcdpm7kgh3cq4x9 foreign key (id_motor) references motors;

alter table if exists cars add constraint FKskae8an974gmmjkvlsyiib5nw foreign key (id_transmission) references transmissions;

alter table if exists cars add constraint FKdvioc5ade00uma6fagh9yoilg foreign key (id_user) references users;