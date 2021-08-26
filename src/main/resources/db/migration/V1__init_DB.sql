drop table if exists cities CASCADE;
create table cities
(
    id int8 not null,
    name varchar(255),
    primary key (id)
);

drop table if exists weather_stamp CASCADE;
create table weather_stamp
(
    id int8 not null,
    city_id int8,
    time_stamp timestamp,
    temperature float4,
    primary key (id)
);

alter table if exists weather_stamp
    add constraint weather_data_fk_city
        foreign key(city_id) references cities;


