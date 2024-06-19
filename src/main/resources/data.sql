drop table if exists tool cascade;

drop table if exists tool_type cascade;
drop table if exists tool_code cascade;
drop table if exists brand cascade;


create table brand
(
    id   bigint auto_increment primary key not null,
    name varchar(128) UNIQUE
);

create table tool_code
(
    id   bigint auto_increment primary key not null,
    code varchar(128) UNIQUE
);

create table tool_type
(
    id   bigint auto_increment primary key not null,
    type varchar(128) UNIQUE,
    daily_rental_charge decimal(10,2)
);

create table tool
(
    id           bigint auto_increment primary key not null,
    tool_code_id bigint references tool_code (id),
    tool_type_id bigint references tool_type (id),
    brand_id     bigint references brand (id)
);

insert into TOOL_CODE(CODE) values ('JAKR');
insert into TOOL_CODE(CODE) values ('LADW');
insert into TOOL_CODE(CODE) values ('JAKD');
insert into TOOL_CODE(CODE) values ('CHNS');

INSERT INTO TOOL_TYPE(TYPE, daily_rental_charge) values ('Jackhammer', 2.99);
INSERT INTO TOOL_TYPE(TYPE, daily_rental_charge) values ('Ladder', 1.99);
INSERT INTO TOOL_TYPE(TYPE, daily_rental_charge) values ('Chainsaw', 1.49);

INSERT INTO  BRAND(NAME) values ('Stihl');
INSERT INTO  BRAND(NAME) values ('Werner');
INSERT INTO  BRAND(NAME) values ('Ridgid');
INSERT INTO  BRAND(NAME) values ('DeWalt');

INSERT INTO TOOL(TOOL_CODE_ID, TOOL_TYPE_ID, BRAND_ID) VALUES (1,1,3);
INSERT INTO TOOL(TOOL_CODE_ID, TOOL_TYPE_ID, BRAND_ID) VALUES (2,2,2);
INSERT INTO TOOL(TOOL_CODE_ID, TOOL_TYPE_ID, BRAND_ID) VALUES (3,1,4);
INSERT INTO TOOL(TOOL_CODE_ID, TOOL_TYPE_ID, BRAND_ID) VALUES (4,3,1);