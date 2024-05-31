CREATE TABLE IF NOT EXISTS core_tokens
(
    ID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
    USER_ID BIGINT UNSIGNED NULL COMMENT 'User ID',
    CLIENT_CODE CHAR(8) NOT NULL COMMENT 'Client Code',
    APP_CODE CHAR(8) NOT NULL COMMENT 'App Code',
    CONNECTION_NAME VARCHAR(255) NOT NULL COMMENT 'Connection for which token is generated',
    TOKEN_TYPE ENUM('ACCESS', 'REFRESH') NOT NULL COMMENT 'Type of token that is generated',
    TOKEN TEXT NOT NULL COMMENT 'Generated Token',
    IS_REVOKED BOOLEAN NOT NULL DEFAULT 0 COMMENT 'If false, means token is working',
    EXPIRES_AT TIMESTAMP COMMENT 'Time when this token will expire',

    CREATED_BY BIGINT UNSIGNED DEFAULT NULL COMMENT 'ID of the user who created this row',
    CREATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Time when this row is created',

    PRIMARY KEY (ID),
    KEY K1_USER_CLIENT_APP_CODE_CONNECTION(USER_ID, CLIENT_CODE, APP_CODE, CONNECTION_NAME),
    KEY K2_CLIENT_APP_CONNECTION(CLIENT_CODE, APP_CODE, CONNECTION_NAME)
)
ENGINE = INNODB
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
