create table if not exists user
(
    id              int auto_increment not null,
    user_name       varchar(50)        not null,
    email           varchar(255)       not null,
    password_hash   varchar(255)       not null,
    create_date     datetime           not null,
    last_login_date datetime           null,
    primary key (id)
) engine = InnoDB;