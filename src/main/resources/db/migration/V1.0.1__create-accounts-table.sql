CREATE TABLE IF NOT EXISTS accounts(
    id bigint NOT NULL AUTO_INCREMENT,
    userId bigint NOT NULL,
    displayName varchar(256) NOT NULL,
    description varchar(512),
    createdAt datetime default current_timestamp,
    updatedAt datetime default current_timestamp on update current_timestamp,
    PRIMARY KEY(id),
    FOREIGN KEY(userId) REFERENCES users(id)
) ENGINE = innodb DEFAULT CHARSET = UTF8MB4;
