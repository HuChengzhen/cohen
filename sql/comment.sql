create table if not exists comment (
    id int not null auto_increment,
    user_id int not null,
    article_id int not null,
    comment text not null,
    comment_date datetime not null,
    primary key (id),
    foreign key fk_article (article_id)
        references article (id)
        on update cascade
        on delete restrict,
    foreign key fk_user (user_id)
        references user (id)
        on update cascade
        on delete restrict
)
