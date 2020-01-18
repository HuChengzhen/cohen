use cohen;
drop procedure if exists update_database;
delimiter $$
create procedure update_database()
begin
    declare db_version int default 0;
    select version into db_version from database_version order by id desc limit 1;
    if db_version <= 1.0 then
        CREATE TABLE `cohen`.`user_follow`
        (
            `id`       INT          NOT NULL AUTO_INCREMENT,
            `follower` INT UNSIGNED NOT NULL,
            `followed` INT UNSIGNED NULL,
            PRIMARY KEY (`id`),
            UNIQUE KEY `follower_followed_unique` (`follower`, `followed`)
        );
        insert into database_version (id, version, date) values (null, 2.0, now());
    end if;

    if db_version <= 2.0 then
        ALTER TABLE `cohen`.`user_follow`
            CHANGE COLUMN `follower` `follower` INT NOT NULL,
            CHANGE COLUMN `followed` `followed` INT NOT NULL ;
        create index follower_index on user_follow (follower);
        create index followed_index on user_follow (followed);
        alter table user_follow
            add constraint fk_follower foreign key (follower) references user (id);
        alter table user_follow
            add constraint fk_followed foreign key (followed) references user (id);
        insert into database_version (id, version, date) values (null, 3.0, now());
    end if;
end $$
delimiter ;
call update_database();