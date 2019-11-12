CREATE DATABASE epam
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;


create table IF NOT EXISTS garage
(
    number  SERIAL PRIMARY KEY,
    ADDRESS VARCHAR(50) NOT NULL,
    name    VARCHAR(50) NOT NULL
);


create table IF NOT EXISTS route
(
    number            SERIAL PRIMARY KEY,
    passenger_average INT NOT NULL
);


create table IF NOT EXISTS town
(
    name VARCHAR(20) PRIMARY KEY
);


create table IF NOT EXISTS stage
(
    number SERIAL PRIMARY KEY
);


create table IF NOT EXISTS bus
(
    register_number SERIAL PRIMARY KEY,
    size            INT NOT NULL,
    deck            INT NOT NULL,
    garage_number   INT,
    route_number    INT,
    FOREIGN KEY (garage_number) REFERENCES garage (number),
    FOREIGN KEY (route_number) REFERENCES route (number)
);


create table IF NOT EXISTS driver
(
    number     SERIAL PRIMARY KEY,
    name       VARCHAR(50) NOT NULL,
    birthday   date        NOT NULL,
    bus_number INT,
    FOREIGN KEY (bus_number) REFERENCES bus (register_number)
);


create table IF NOT EXISTS route_stage
(
    route_number INT,
    stage_number INT,
    FOREIGN KEY (route_number) REFERENCES route (number),
    FOREIGN KEY (stage_number) REFERENCES stage (number)
);


create table IF NOT EXISTS route_town
(
    route_number INT,
    town_name    VARCHAR(20),
    FOREIGN KEY (route_number) REFERENCES route (number),
    FOREIGN KEY (town_name) REFERENCES town (name)
);


insert into stage
values (1);
insert into stage
values (2);
insert into stage
values (3);
insert into stage
values (4);
insert into stage
values (5);
insert into stage
values (6);
insert into stage
values (7);
insert into stage
values (8);
insert into stage
values (9);
insert into stage
values (10);


insert into garage
values (1, 'imanova31', 'first');
insert into garage
values (2, 'saryarka21', 'second');
insert into garage
values (3, 'imanbaeva30', 'third');
insert into garage
values (4, 'pobeda2', 'fourth');
insert into garage
values (5, 'konaev3', 'fifth');
insert into garage
values (6, 'baiterek6', 'sixth');
insert into garage
values (7, 'gete44', 'seventh');
insert into garage
values (8, 'bogenbai22', 'eighth');
insert into garage
values (9, 'imanova35', 'nineth');


insert into town
values ('Nursultan');
insert into town
values ('Almaty');
insert into town
values ('Shymkent');
insert into town
values ('Kostanay');
insert into town
values ('Semey');
insert into town
values ('Oral');
insert into town
values ('Aktobe');
insert into town
values ('Aktau');
insert into town
values ('Arys');
insert into town
values ('Temirtau');


insert into route
values (1, 13);
insert into route
values (2, 16);
insert into route
values (3, 14);
insert into route
values (4, 21);
insert into route
values (5, 20);
insert into route
values (6, 23);
insert into route
values (7, 25);


insert into bus
values (1, 25, 1, 1, 1);
insert into bus
values (2, 25, 2, 2, 2);
insert into bus
values (3, 22, 1, 3, 3);
insert into bus
values (4, 30, 2, 4, 4);
insert into bus
values (5, 30, 1, 5, 5);
insert into bus
values (6, 33, 2, 6, 6);
insert into bus
values (7, 33, 1, 7, 7);
insert into bus
values (8, 33, 2, 8, 1);
insert into bus
values (9, 16, 1, 1, 7);
insert into bus
values (10, 16, 1, 9, 7);
insert into bus
values (11, 16, 1, 2, 3);
insert into bus
values (12, 16, 1, 3, 2);
insert into bus
values (13, 22, 1, 7, 4);
insert into bus
values (14, 22, 1, 1, 4);
insert into bus
values (15, 25, 2, 6, 5);


insert into driver
values (1, 'askar', '1998-03-29', 1);
insert into driver
values (2, 'serik', '1992-04-25', 2);
insert into driver
values (3, 'nurik', '1994-11-2', 3);
insert into driver
values (4, 'agaris', '1995-12-3', 4);
insert into driver
values (5, 'mars', '1991-05-22', 5);
insert into driver
values (6, 'roman', '1992-06-23', 6);
insert into driver
values (7, 'arseniy', '1992-04-9', 7);
insert into driver
values (8, 'danil', '1991-07-7', 8);
insert into driver
values (9, 'tima', '1989-06-5', 9);
insert into driver
values (10, 'erlan', '1981-07-5', 10);
insert into driver
values (11, 'erjan', '1990-06-4', 11);
insert into driver
values (12, 'arman', '1992-05-14', 12);
insert into driver
values (13, 'beka', '1993-04-15', 13);
insert into driver
values (14, 'seka', '1982-07-12', 14);
insert into driver
values (15, 'yuri', '1985-03-15', 15);


insert into route_stage
values (7, 2);
insert into route_stage
values (7, 3);
insert into route_stage
values (7, 5);
insert into route_stage
values (7, 6);
insert into route_stage
values (3, 1);
insert into route_stage
values (3, 3);
insert into route_stage
values (3, 9);
insert into route_stage
values (2, 2);
insert into route_stage
values (2, 4);
insert into route_stage
values (2, 7);
insert into route_stage
values (2, 8);
insert into route_stage
values (4, 1);
insert into route_stage
values (4, 5);
insert into route_stage
values (4, 10);
insert into route_stage
values (5, 1);
insert into route_stage
values (5, 3);
insert into route_stage
values (5, 4);
insert into route_stage
values (5, 7);
insert into route_stage
values (5, 9);
insert into route_stage
values (6, 2);
insert into route_stage
values (6, 4);
insert into route_stage
values (6, 8);
insert into route_stage
values (1, 1);
insert into route_stage
values (1, 4);


insert into route_town
values (7, 'Nursultan');
insert into route_town
values (7, 'Almaty');
insert into route_town
values (7, 'Shymkent');
insert into route_town
values (7, 'Arys');
insert into route_town
values (3, 'Oral');
insert into route_town
values (3, 'Aktobe');
insert into route_town
values (3, 'Kostanay');
insert into route_town
values (2, 'Semey');
insert into route_town
values (2, 'Almaty');
insert into route_town
values (2, 'Shymkent');
insert into route_town
values (2, 'Temirtau');
insert into route_town
values (4, 'Aktau');
insert into route_town
values (4, 'Aktobe');
insert into route_town
values (4, 'Oral');
insert into route_town
values (5, 'Arys');
insert into route_town
values (5, 'Shymkent');
insert into route_town
values (5, 'Almaty');
insert into route_town
values (5, 'Nursultan');
insert into route_town
values (5, 'Kostanay');
insert into route_town
values (6, 'Temirtau');
insert into route_town
values (6, 'Nursultan');
insert into route_town
values (6, 'Kostanay');
insert into route_town
values (1, 'Arys');
insert into route_town
values (1, 'Shymkent');


CREATE OR REPLACE VIEW busforroute as
SELECT DISTINCT route.number,
                count(bus.register_number) AS "busforroute"
FROM route
         INNER JOIN bus ON bus.route_number = route.number
group by (route.number)
order by(route.number);


CREATE OR REPLACE VIEW stageforroute as
SELECT route.number, count(route_stage.stage_number) AS "stageforroute"
FROM route
         INNER JOIN route_stage ON route_stage.route_number = route.number
group by (route.number)
order by(route.number);



CREATE OR REPLACE VIEW driveramount as
SELECT route.number, count(driver.number) AS "driveramount"
FROM driver
         INNER JOIN bus ON bus.register_number = driver.bus_number
         INNER JOIN route ON route.number = bus.route_number
group by (route.number)
order by(route.number);


CREATE OR REPLACE VIEW youngest_driver as
SELECT t.route_number, (select name from driver HAVING birthday = max(u.birthday)) AS "oldestdriver"
FROM driver u
         LEFT JOIN
     bus t
     ON u.bus_number = t.register_number
GROUP BY t.route_number
ORDER BY t.route_number;


CREATE OR REPLACE VIEW oldestdriver as
SELECT t.route_number, (select name from driver HAVING birthday = min(u.birthday)) AS "oldestdriver"
FROM driver u
         LEFT JOIN
     bus t
     ON u.bus_number = t.register_number
GROUP BY t.route_number
ORDER BY t.route_number;


CREATE OR REPLACE VIEW largest_bus_size as
SELECT u.route_number, (select size from bus HAVING size = max(u.size) LIMIT 1) AS "largestbussize"
FROM bus u
         LEFT JOIN route t
                   ON t.number = u.route_number
GROUP BY u.route_number
ORDER BY u.route_number;


CREATE OR REPLACE VIEW ddeckbuses as
SELECT b.route_number, COUNT(b2.deck) AS "doubledeckedbus"
FROM bus b
         LEFT JOIN bus b2 ON b.route_number = b2.route_number AND b.deck = 2 AND b2.deck = 2
GROUP BY b.route_number
ORDER BY b.route_number;


CREATE OR REPLACE VIEW towncount as
SELECT route_town.route_number,
       CASE
           WHEN COUNT(route_town.town_name) >= 3 THEN
               CAST(TRUE as varCHAR)
           ELSE
               CAST(FALSE as varCHAR)
           END As "truefalse"
FROM route_town
GROUP BY route_town.route_number
ORDER BY route_town.route_number;


CREATE OR REPLACE FUNCTION busgetter(routenumber2 int, decktype int, delimiter VARCHAR(2))
    RETURNS VARCHAR
AS
$$
declare
    results VARCHAR;
BEGIN
    results = array_to_string(array(
                                      SELECT bus.register_number
                                      FROM bus
                                      WHERE bus.route_number = routenumber2
                                        AND deck = decktype), delimiter);
    RETURN results;
END;
$$
    LANGUAGE PLPGSQL;


create table logging
(
    date        date        not null default current_date,
    table_name  varchar(50) not null,
    description text
);


create or replace function "public".is_modified() returns trigger as
$body$
begin
    if (TG_OP = 'INSERT') then
        insert into "public".logging (table_name, description)
        values (TG_RELNAME, json_strip_nulls(row_to_json(NEW)));
        return NEW;
    else
        return NULL;
    end if;
end;
$body$
    LANGUAGE plpgsql;


CREATE TRIGGER logger_trigger
    AFTER INSERT
    ON information_schema.tables
    FOR EACH ROW
EXECUTE PROCEDURE "public".is_modified();

