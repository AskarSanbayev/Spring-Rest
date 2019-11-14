CREATE SCHEMA IF NOT EXISTS company;

DROP TABLE IF EXISTS company.bus;
create table company.bus
(
    register_number integer not null check (register_number >= 1),
    deck            integer check (deck <= 2 AND deck >= 1),
    size            integer check (size >= 15),
    garage_number   integer,
    route_number    integer,
    primary key (register_number)
);
DROP TABLE IF EXISTS company.driver;
create table company.driver
(
    id            integer not null,
    birthday      date,
    name          varchar(255),
    driver_number integer check (driver_number >= 1),
    bus_number    integer,
    primary key (id)
);
DROP TABLE IF EXISTS company.garage;
create table company.garage
(
    id            integer not null,
    address       varchar(255),
    name          varchar(255),
    garage_number integer check (garage_number >= 1),
    primary key (id)
);
DROP TABLE IF EXISTS company.route;
create table company.route
(
    id                integer not null,
    route_number      integer check (route_number >= 1),
    passenger_average integer check (passenger_average >= 1),
    primary key (id)
);
DROP TABLE IF EXISTS company.stage;
create table company.stage
(
    id           integer not null,
    stage_number integer check (stage_number >= 1),
    primary key (id)
);
DROP TABLE IF EXISTS company.town;
create table company.town
(
    id   integer not null,
    name varchar(255),
    primary key (id)
);
DROP TABLE IF EXISTS company.route_stage;
create table company.route_stage
(
    route_number integer not null,
    stage_number integer not null,
    primary key (route_number, stage_number)
);
DROP TABLE IF EXISTS company.route_town;
create table company.route_town
(
    route_number integer      not null,
    town_name    varchar(255) not null,
    primary key (route_number, town_name)
);
alter table company.driver
    add constraint UK_r4o2ei511ku0f8o7anh7g034 unique (driver_number);
alter table company.garage
    add constraint UK_pt6fgcfhx49s0qyu2llirtfxk unique (garage_number);
alter table company.route
    add constraint UK_mivkw9ok977e1uj1ky0vyxcxk unique (route_number);
alter table company.stage
    add constraint UK_k7iypvu3uvu3ax4uwabi1yqpv unique (stage_number);
alter table company.town
    add constraint UK_mog9r67bqs8gcf7lpc74vyvff unique (name);

alter table company.bus
    add constraint FKf6tw0ahc09cckqt99p2uccwfb foreign key (garage_number) references company.garage (garage_number);
alter table company.bus
    add constraint FKc6kuniblso3r58lxms6b3jmhl foreign key (route_number) references company.route (route_number);
alter table company.driver
    add constraint FKiybu1wtwowfg4lr3rrf9iskp2 foreign key (bus_number) references company.bus;

insert into company.garage
values (1,'imanova31', 'first' ,1);
insert into company.garage
values (2,'saryarka21', 'second',2);
insert into company.garage
values (3,'imanbaeva30', 'third',3);
insert into company.garage
values (4,'pobeda2', 'fourth',4);
insert into company.garage
values (5,'konaev3', 'fifth',5);
insert into company.garage
values (6,'baiterek6', 'sixth',6);
insert into company.garage
values (7, 'gete44', 'seventh',7);
insert into company.garage
values (8, 'bogenbai22', 'eighth',8);
insert into company.garage
values (9,'imanova35', 'nineth',9);


insert into company.route
values (1, 1,20);
insert into company.route
values (2, 2,21);
insert into company.route
values (3, 3,23);
insert into company.route
values (4, 4,19);
insert into company.route
values (5, 5,22);
insert into company.route
values (6, 6,21);
insert into company.route
values (7, 7,21);

insert into company.bus
values (1,1, 25,1, 1);
insert into company.bus
values (2,2, 25,2, 2);
insert into company.bus
values (3,1, 22,3, 3);
insert into company.bus
values (4,2, 30, 4, 4);
insert into company.bus
values (5,1, 30, 5, 5);
insert into company.bus
values (6,2 ,33,6, 6);
insert into company.bus
values (7,1, 33,7, 7);
insert into company.bus
values (8,2, 33, 8, 1);
insert into company.bus
values (9,1 ,16,1, 7);
insert into company.bus
values (10,1, 16, 9, 7);
insert into company.bus
values (11,1, 16, 2, 3);
insert into company.bus
values (12,1, 16,3, 2);
insert into company.bus
values (13,1, 22,7, 4);
insert into company.bus
values (14,1, 22,1, 4);
insert into company.bus
values (15,2, 25,6, 5);


insert into company.stage
values (1,1);
insert into company.stage
values (2,2);
insert into company.stage
values (3,3);
insert into company.stage
values (4,4);
insert into company.stage
values (5,5);
insert into company.stage
values (6,6);
insert into company.stage
values (7,7);
insert into company.stage
values (8,8);
insert into company.stage
values (9,9);
insert into company.stage
values (10,10);


insert into company.town
values (1,'Nursultan');
insert into company.town
values (2,'Almaty');
insert into company.town
values (3,'Shymkent');
insert into company.town
values (4,'Kostanay');
insert into company.town
values (5,'Semey');
insert into company.town
values (6,'Oral');
insert into company.town
values (7,'Aktobe');
insert into company.town
values (8,'Aktau');
insert into company.town
values (9,'Arys');
insert into company.town
values (10,'Temirtau');


insert into company.driver
values (1,'1998-03-29','askar', 1, 1);
insert into company.driver
values (2,'1992-04-25','serik',  2,2);
insert into company.driver
values (3,'1994-11-2', 'nurik',  3,3);
insert into company.driver
values (4,'1995-12-3','agaris',  4,4);
insert into company.driver
values (5,'1991-05-22','mars',  5,5);
insert into company.driver
values (6,'1992-06-23','roman',  6,6);
insert into company.driver
values (7,'1992-04-9', 'arseniy', 7, 7);
insert into company.driver
values (8, '1991-07-7','danil',8, 8);
insert into company.driver
values (9, '1989-06-5','tima',  9,9);
insert into company.driver
values (10, '1981-07-5','erlan', 10, 10);
insert into company.driver
values (11, '1990-06-4','erjan', 11,11);
insert into company.driver
values (12,'1992-05-14', 'arman',12,12);
insert into company.driver
values (13,'1993-04-15', 'beka', 13,13);
insert into company.driver
values (14, '1982-07-12','seka', 14,14);
insert into company.driver
values (15,'1985-03-15','yuri',  15,15);


insert into company.route_stage
values (7, 2);
insert into company.route_stage
values (7, 3);
insert into company.route_stage
values (7, 5);
insert into company.route_stage
values (7, 6);
insert into company.route_stage
values (3, 1);
insert into company.route_stage
values (3, 3);
insert into company.route_stage
values (3, 9);
insert into company.route_stage
values (2, 2);
insert into company.route_stage
values (2, 4);
insert into company.route_stage
values (2, 7);
insert into company.route_stage
values (2, 8);
insert into company.route_stage
values (4, 1);
insert into company.route_stage
values (4, 5);
insert into company.route_stage
values (4, 10);
insert into company.route_stage
values (5, 1);
insert into company.route_stage
values (5, 3);
insert into company.route_stage
values (5, 4);
insert into company.route_stage
values (5, 7);
insert into company.route_stage
values (5, 9);
insert into company.route_stage
values (6, 2);
insert into company.route_stage
values (6, 4);
insert into company.route_stage
values (6, 8);
insert into company.route_stage
values (1, 1);
insert into company.route_stage
values (1, 4);

insert into company.route_town
values (7, 'Nursultan');
insert into company.route_town
values (7, 'Almaty');
insert into company.route_town
values (7, 'Shymkent');
insert into company.route_town
values (7, 'Arys');
insert into company.route_town
values (3, 'Oral');
insert into company.route_town
values (3, 'Aktobe');
insert into company.route_town
values (3, 'Kostanay');
insert into company.route_town
values (2, 'Semey');
insert into company.route_town
values (2, 'Almaty');
insert into company.route_town
values (2, 'Shymkent');
insert into company.route_town
values (2, 'Temirtau');
insert into company.route_town
values (4, 'Aktau');
insert into company.route_town
values (4, 'Aktobe');
insert into company.route_town
values (4, 'Oral');
insert into company.route_town
values (5, 'Arys');
insert into company.route_town
values (5, 'Shymkent');
insert into company.route_town
values (5, 'Almaty');
insert into company.route_town
values (5, 'Nursultan');
insert into company.route_town
values (5, 'Kostanay');
insert into company.route_town
values (6, 'Temirtau');
insert into company.route_town
values (6, 'Nursultan');
insert into company.route_town
values (6, 'Kostanay');
insert into company.route_town
values (1, 'Arys');
insert into company.route_town
values (1, 'Shymkent');


DROP SEQUENCE IF EXISTS driver_id;
create sequence driver_id start with 20 increment by 1;
DROP SEQUENCE IF EXISTS route_id;
create sequence route_id start with 20 increment by 1;
DROP SEQUENCE IF EXISTS garage_id;
create sequence garage_id start with 20 increment by 1;
DROP SEQUENCE IF EXISTS stage_id;
create sequence stage_id start with 20  increment by 1;
DROP SEQUENCE IF EXISTS town_id;
create sequence town_id start with 20 increment by 1;
