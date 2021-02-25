create table account
(
    id     int auto_increment
        primary key,
    amount int null
)
    comment '账户';

create table payment
(
    id       int auto_increment
        primary key,
    from_uid int not null,
    to_uid   int not null,
    amount   int null
);
