create table if not exists article
(
    id            int          not null auto_increment,
    title         varchar(255) not null,
    article       LONGTEXT     null,
    author_id     int          not null,
    uploaded_date datetime     not null,
    edit_date     datetime     not null,
    primary key (id),
    foreign key fk_user (author_id)
        references user (id)
        on update cascade
        on delete restrict
) engine = InnoDB;