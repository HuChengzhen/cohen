create table if not exists user
(
    id              int auto_increment  not null,
    username        varchar(50) unique  not null,
    email           varchar(255) unique not null,
    password        CHAR(60)            not null,
    create_date     datetime            not null,
    last_login_date datetime            null,
    enabled         tinyint(4)          NOT NULL DEFAULT '1',
    roles           text,
    primary key (id),
    index username_index (username)
) engine = InnoDB;