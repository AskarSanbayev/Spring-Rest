CREATE DATABASE epam
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;



CREATE SCHEMA IF NOT EXISTS company
    AUTHORIZATION postgres;


CREATE TABLE IF NOT EXISTS company.garage
(
    id integer NOT NULL,
    address character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    garage_number integer NOT NULL,
    CONSTRAINT garage_number PRIMARY KEY (garage_number),
    CONSTRAINT garage_pkey UNIQUE (id)
    ,
    CONSTRAINT uk_pt6fgcfhx49s0qyu2llirtfxk UNIQUE (garage_number)

)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;


CREATE TABLE IF NOT EXISTS company.route
(
    id integer NOT NULL,
    route_number integer NOT NULL,
    passenger_average integer,
    CONSTRAINT route_number PRIMARY KEY (route_number),
    CONSTRAINT route_pkey UNIQUE (id)
    ,
    CONSTRAINT uk_mivkw9ok977e1uj1ky0vyxcxk UNIQUE (route_number)

)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;


CREATE TABLE IF NOT EXISTS company.town
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT town_pkey PRIMARY KEY (id),
    CONSTRAINT town_name UNIQUE (name)
    ,
    CONSTRAINT uk_mog9r67bqs8gcf7lpc74vyvff UNIQUE (name)

)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;


CREATE TABLE IF NOT EXISTS company.stage
(
    id integer NOT NULL,
    stage_number integer NOT NULL,
    CONSTRAINT stage_number PRIMARY KEY (stage_number),
    CONSTRAINT stage_pkey UNIQUE (id)
    ,
    CONSTRAINT uk_k7iypvu3uvu3ax4uwabi1yqpv UNIQUE (stage_number)

)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;


CREATE TABLE IF NOT EXISTS company.bus
(
    deck integer,
    register_number integer NOT NULL,
    size integer,
    garage_number integer,
    route_number integer,
    CONSTRAINT register_number PRIMARY KEY (register_number),
    CONSTRAINT uk_7bbumoe9djtekd3fywu8uwbam UNIQUE (register_number)
    ,
    CONSTRAINT uk_t955nr7b93grbpxj9e5n5enux UNIQUE (register_number)
    ,
    CONSTRAINT fkb5j7a1cdrtv62ck00p1ht7nkx FOREIGN KEY (garage_number)
        REFERENCES company.garage (garage_number) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkc6kuniblso3r58lxms6b3jmhl FOREIGN KEY (route_number)
        REFERENCES company.route (route_number) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkf6tw0ahc09cckqt99p2uccwfb FOREIGN KEY (garage_number)
        REFERENCES company.garage (garage_number) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkfqqlhhq1coexcfnwr6b0yeb09 FOREIGN KEY (route_number)
        REFERENCES company.route (route_number) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;


CREATE TABLE IF NOT EXISTS company.driver
(
    id integer NOT NULL,
    birthday date,
    name character varying(255) COLLATE pg_catalog."default",
    driver_number integer NOT NULL,
    bus_number integer,
    CONSTRAINT driver_number PRIMARY KEY (driver_number),
    CONSTRAINT driver_pkey UNIQUE (id)
    ,
    CONSTRAINT uk_r4o2ei511ku0f8o7anh7g034 UNIQUE (driver_number)
    ,
    CONSTRAINT fkfqu17y60296mwjhmrkd5dysu2 FOREIGN KEY (bus_number)
        REFERENCES company.bus (register_number) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkiybu1wtwowfg4lr3rrf9iskp2 FOREIGN KEY (bus_number)
        REFERENCES company.bus (register_number) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS company.route_stage
(
    route_number integer,
    stage_number integer,
    CONSTRAINT route_stage_route_number_fkey FOREIGN KEY (route_number)
        REFERENCES company.route (route_number) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT route_stage_stage_number_fkey FOREIGN KEY (stage_number)
        REFERENCES company.stage (stage_number) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;


CREATE TABLE IF NOT EXISTS company.route_town
(
    route_number integer,
    town_name character varying(20) COLLATE pg_catalog."default",
    CONSTRAINT route_town_route_number_fkey FOREIGN KEY (route_number)
        REFERENCES company.route (route_number) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT route_town_town_name_fkey FOREIGN KEY (town_name)
        REFERENCES company.town (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

