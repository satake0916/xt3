CREATE TABLE IF NOT EXISTS userentity(
    id bigint NOT NULL AUTO_INCREMENT,
    email varchar(256) NOT NULL,
    pass varchar(60) NOT NULL,
    createdAt datetime  default current_timestamp,
    updatedAt timestamp default current_timestamp on update current_timestamp,
    PRIMARY KEY(id)
) ENGINE = innodb DEFAULT CHARSET = UTF8MB4;
