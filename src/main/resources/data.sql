drop table if exists tool cascade;

drop table if exists tool_type cascade;
drop table if exists tool_code cascade;
drop table if exists brand cascade;
drop table if exists rental_agreement cascade;


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
    id                  bigint auto_increment primary key not null,
    type                varchar(128) UNIQUE,
    daily_rental_charge decimal(10, 2),
    charge_weekends     boolean default true,
    charge_weekdays     boolean default true,
    charge_holidays     boolean default true
);

create table tool
(
    id           bigint auto_increment primary key not null,
    tool_code_id bigint references tool_code (id),
    tool_type_id bigint references tool_type (id),
    brand_id     bigint references brand (id),
    constraint tool_ids_unique_constraint unique (tool_code_id, tool_type_id, brand_id)
);

create table rental_agreement
(
    id                  bigint auto_increment primary key not null,
    tool_id             bigint references tool (id),
    discount            decimal(3, 2)                     not null default 0.0,
    check_out_date      date                              not null default CURRENT_DATE,
    rental_days         int                               not null check (rental_days > 0),
    due_date            date,
    charge_days         int,
    pre_discount_charge decimal(16, 2),
    discount_amount     decimal(16, 2),
    final_charge        decimal(16, 2),
    daily_rental_charge decimal(16, 2)
);

insert into TOOL_CODE(CODE)
values ('JAKR');
insert into TOOL_CODE(CODE)
values ('LADW');
insert into TOOL_CODE(CODE)
values ('JAKD');
insert into TOOL_CODE(CODE)
values ('CHNS');

INSERT INTO TOOL_TYPE(TYPE, daily_rental_charge, charge_weekdays, charge_weekends, charge_holidays)
values ('Jackhammer', 2.99, true, false, false);
INSERT INTO TOOL_TYPE(TYPE, daily_rental_charge, charge_weekdays, charge_weekends, charge_holidays)
values ('Ladder', 1.99, true, true, false);
INSERT INTO TOOL_TYPE(TYPE, daily_rental_charge, charge_weekdays, charge_weekends, charge_holidays)
values ('Chainsaw', 1.49, true, false, true);

INSERT INTO BRAND(NAME)
values ('Stihl');
INSERT INTO BRAND(NAME)
values ('Werner');
INSERT INTO BRAND(NAME)
values ('Ridgid');
INSERT INTO BRAND(NAME)
values ('DeWalt');

INSERT INTO TOOL(TOOL_CODE_ID, TOOL_TYPE_ID, BRAND_ID)
VALUES (1, 1, 3);
INSERT INTO TOOL(TOOL_CODE_ID, TOOL_TYPE_ID, BRAND_ID)
VALUES (2, 2, 2);
INSERT INTO TOOL(TOOL_CODE_ID, TOOL_TYPE_ID, BRAND_ID)
VALUES (3, 1, 4);
INSERT INTO TOOL(TOOL_CODE_ID, TOOL_TYPE_ID, BRAND_ID)
VALUES (4, 3, 1);

