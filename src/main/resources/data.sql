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
    daily_rental_charge decimal(10, 2)
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
    id             bigint auto_increment primary key not null,
    tool_id        bigint references tool (id),
    discount       decimal(3, 2)                     not null default 0.0,
    check_out_date date                              not null default CURRENT_DATE,
    rental_days    int                               not null check (rental_days > 0)
);

insert into TOOL_CODE(CODE)
values ('JAKR');
insert into TOOL_CODE(CODE)
values ('LADW');
insert into TOOL_CODE(CODE)
values ('JAKD');
insert into TOOL_CODE(CODE)
values ('CHNS');

INSERT INTO TOOL_TYPE(TYPE, daily_rental_charge)
values ('Jackhammer', 2.99);
INSERT INTO TOOL_TYPE(TYPE, daily_rental_charge)
values ('Ladder', 1.99);
INSERT INTO TOOL_TYPE(TYPE, daily_rental_charge)
values ('Chainsaw', 1.49);

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

insert into rental_agreement
(tool_id, discount,
 check_out_date,
 rental_days)
values (select t.id
        from tool t
                 inner join tool_code tc
                            on t.tool_code_id = tc.id
                 inner join tool_type tt
                            on t.tool_type_id = tt.id
                 inner join brand b on t.brand_id = b.id
        where tc.code = 'JAKR'
          and b.NAME = 'Ridgid'
          and tt.TYPE = 'Jackhammer',
        1.01,
        '2015-3-15',
        5);

insert into rental_agreement
(tool_id, discount,
 check_out_date,
 rental_days)
values (select t.id
        from tool t
                 inner join tool_code tc
                            on t.tool_code_id = tc.id
                 inner join tool_type tt
                            on t.tool_type_id = tt.id
                 inner join brand b on t.brand_id = b.id
        where tc.code = 'LADW'
          and b.NAME = 'Werner'
          and tt.TYPE = 'Ladder',
        0.10,
        '2020-7-2',
        3);

insert into rental_agreement
(tool_id, discount,
 check_out_date,
 rental_days)
values (select t.id
        from tool t
                 inner join tool_code tc
                            on t.tool_code_id = tc.id
                 inner join tool_type tt
                            on t.tool_type_id = tt.id
                 inner join brand b on t.brand_id = b.id
        where tc.code = 'CHNS'
          and b.NAME = 'Stihl'
          and tt.TYPE = 'Chainsaw',
        0.25,
        '2015-7-2',
        5);

insert into rental_agreement
(tool_id, discount,
 check_out_date,
 rental_days)
values (select t.id
        from tool t
                 inner join tool_code tc
                            on t.tool_code_id = tc.id
                 inner join tool_type tt
                            on t.tool_type_id = tt.id
                 inner join brand b on t.brand_id = b.id
        where tc.code = 'JAKD'
          and b.NAME = 'DeWalt'
          and tt.TYPE = 'Jackhammer',
        0.00,
        '2015-9-3',
        6);

insert into rental_agreement
(tool_id, discount,
 check_out_date,
 rental_days)
values (select t.id
        from tool t
                 inner join tool_code tc
                            on t.tool_code_id = tc.id
                 inner join tool_type tt
                            on t.tool_type_id = tt.id
                 inner join brand b on t.brand_id = b.id
        where tc.code = 'JAKR'
          and b.NAME = 'Ridgid'
          and tt.TYPE = 'Jackhammer',
        0.00,
        '2015-7-2',
        9);

insert into rental_agreement
(tool_id, discount,
 check_out_date,
 rental_days)
values (select t.id
        from tool t
                 inner join tool_code tc
                            on t.tool_code_id = tc.id
                 inner join tool_type tt
                            on t.tool_type_id = tt.id
                 inner join brand b on t.brand_id = b.id
        where tc.code = 'JAKR'
          and b.NAME = 'Ridgid'
          and tt.TYPE = 'Jackhammer',
        0.50,
        '2020-7-2',
        4);
