CREATE TABLE IF NOT EXISTS tweetentity(
    id bigint NOT NULL AUTO_INCREMENT,
    content varchar(140),
    PRIMARY KEY(id)
) ENGINE = innodb DEFAULT CHARSET = UTF8MB4;
