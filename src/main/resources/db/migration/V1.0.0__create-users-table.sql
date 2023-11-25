CREATE TABLE IF NOT EXISTS users(
    id bigint NOT NULL AUTO_INCREMENT,
    email varchar(256) NOT NULL,
    pass varchar(60) NOT NULL,
    role varchar(32) NOT NULL,
    createdAt datetime default current_timestamp,
    updatedAt datetime default current_timestamp on update current_timestamp,
    PRIMARY KEY(id)
) ENGINE = innodb DEFAULT CHARSET = UTF8MB4;
