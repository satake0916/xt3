-- Insert test data for Users
INSERT INTO users (email, pass, role_type)
SELECT
    CONCAT('user', u.user_id, '@example.com'),
    '$2a$10$n3MwDFm.WZ0iqK2H4FL40eL9yeUgRnQ38y3MNqhSXra1LSJVQZdMm',
    'ADMIN'
FROM (
    SELECT 1 AS user_id UNION ALL
    SELECT 2 UNION ALL
    SELECT 3 UNION ALL
    SELECT 4 UNION ALL
    SELECT 5 UNION ALL
    SELECT 6 UNION ALL
    SELECT 7 UNION ALL
    SELECT 8 UNION ALL
    SELECT 9 UNION ALL
    SELECT 10
) AS u;

-- Insert test data for Accounts
INSERT INTO accounts (user_id, account_name, display_name, profile_description, profile_image_url, is_primary)
SELECT
    u.user_id,
    CONCAT('account', u.user_id),
    CONCAT('Account ', u.user_id),
    CONCAT('Description for Account ', u.user_id),
    'http://shincode.info/wp-content/uploads/2021/12/icon.png',
    1
FROM users u;

-- Insert test data for Tweets
INSERT INTO tweets (account_id, tweet_text)
SELECT
    a.account_id,
    CONCAT('Random tweet number ', t.tweet_id)
FROM accounts a
CROSS JOIN (
    SELECT seq.seq_id AS tweet_id
    FROM (
        SELECT (H*100 + T*10 + U) AS seq_id
        FROM (
            SELECT 0 AS H UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL
            SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9
        ) AS hundreds
        CROSS JOIN (
            SELECT 0 AS T UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL
            SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9
        ) AS tens
        CROSS JOIN (
            SELECT 0 AS U UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL
            SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9
        ) AS units
    ) AS seq
    WHERE seq_id <= 1000 -- Adjust the number of tweets here
) AS t;

-- Insert test data for Followers (random following relationships)
INSERT INTO followers (following_account_id, followed_account_id)
SELECT
    f.following_account_id,
    f.followed_account_id
FROM (
    SELECT a1.account_id AS following_account_id, a2.account_id AS followed_account_id
    FROM accounts a1
    CROSS JOIN accounts a2
    WHERE a1.account_id != a2.account_id
    ORDER BY RAND()
    LIMIT 500 -- Adjust the number of followers here
) AS f;
