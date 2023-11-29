USE security;

CREATE TABLE `security_code_access` (
  `ID` BIGINT unsigned NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
  `EMAIL_ID` VARCHAR(320) NOT NULL COMMENT 'Email id of the client',
  `CODE` CHAR(32) NOT NULL COMMENT 'Unique access code for logging in',
  `APP_ID` BIGINT unsigned NOT NULL COMMENT 'App id to which this user belongs to.',
  `CLIENT_ID` BIGINT unsigned NOT NULL COMMENT 'Client id to which this user belongs to.',
  `CREATED_BY` BIGINT unsigned DEFAULT NULL COMMENT 'ID of the user who created this row',
  `CREATED_AT` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Time when this row is created',
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `UK1_CODE_ACCESS_CODE` (`APP_ID`, `CLIENT_ID`, `CODE`) VISIBLE,
  UNIQUE INDEX `UK1_CODE_ACCESS_EMAIL_APP_CLIENT` (`APP_ID`, `CLIENT_ID`, `EMAIL_ID` ) VISIBLE,
  CONSTRAINT `FK1_CODE_CLIENT_ID` FOREIGN KEY (`CLIENT_ID`) REFERENCES `security_client` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK2_CODE_APP_ID` FOREIGN KEY (`APP_ID`) REFERENCES `security_app` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT)
ENGINE = INNODB
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;