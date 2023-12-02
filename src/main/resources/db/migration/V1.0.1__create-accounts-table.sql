CREATE TABLE IF NOT EXISTS accounts (
    account_id bigint NOT NULL AUTO_INCREMENT,
    user_id bigint NOT NULL,
    account_name varchar(15) NOT NULL,
    display_name varchar(50) NOT NULL,
    profile_description varchar(500) NOT NULL,
    profile_image_url varchar(200),
    is_primary boolean NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (account_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
) ENGINE = innodb DEFAULT CHARSET = UTF8MB4;
