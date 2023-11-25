CREATE TABLE IF NOT EXISTS tweets(
    id bigint NOT NULL AUTO_INCREMENT,
    accountId bigInt NOT NULL,
    text varchar(140),
    createdAt datetime default current_timestamp,
    updatedAt datetime default current_timestamp on update current_timestamp,
    PRIMARY KEY(id),
    FOREIGN KEY(accountId) REFERENCES accounts(id)
) ENGINE = innodb DEFAULT CHARSET = UTF8MB4;
