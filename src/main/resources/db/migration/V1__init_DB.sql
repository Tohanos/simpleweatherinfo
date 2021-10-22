-- drop sequence if exists city_seq;
create sequence if not exists city_seq start 1 increment 1;

-- drop table if exists cities CASCADE;
create table if not exists cities
(
    id int8 not null,
    name varchar(255),
    primary key (id)
);

-- drop sequence if exists stamp_seq;
create sequence if not exists stamp_seq start 1 increment 1;

-- drop table if exists weather_stamp CASCADE;
create table if not exists weather_stamp
(
    id int8 not null,
    time_stamp timestamp,
    temperature float4,
    primary key (id)
);

-- drop table if exists weather_data CASCADE;
create table if not exists weather_data
(
    city_id int8,
    stamp_id int8
);

alter table if exists weather_data
    add constraint weather_data_fk_city
        foreign key(city_id) references cities;

alter table if exists weather_data
    add constraint weather_data_fk_stamp
        foreign key(stamp_id) references weather_stamp;
