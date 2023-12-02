CREATE TABLE IF NOT EXISTS followers (
    following_account_id bigint NOT NULL,
    followed_account_id bigint NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (following_account_id, followed_account_id),
    FOREIGN KEY (following_account_id) REFERENCES accounts (account_id),
    FOREIGN KEY (followed_account_id) REFERENCES accounts (account_id)
) ENGINE = innodb DEFAULT CHARSET = UTF8MB4;
