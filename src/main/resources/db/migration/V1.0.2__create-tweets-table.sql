CREATE TABLE IF NOT EXISTS tweets (
    tweet_id bigint NOT NULL AUTO_INCREMENT,
    account_id bigint NOT NULL,
    tweet_text varchar(140) NOT NULL,
    parent_tweet_id bigint,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (tweet_id),
    FOREIGN KEY (account_id) REFERENCES accounts (account_id),
    FOREIGN KEY (parent_tweet_id) REFERENCES tweets (tweet_id)
) ENGINE = innodb DEFAULT CHARSET = UTF8MB4;
