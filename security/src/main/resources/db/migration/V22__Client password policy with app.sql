use security;

ALTER TABLE security.security_client_password_policy
DROP FOREIGN KEY FK1_CLIENT_PWD_POL_CLIENT_ID,
DROP INDEX UK1_CLIENT_PWD_POL_ID,
ADD APP_ID BIGINT UNSIGNED DEFAULT NULL,
ADD UNIQUE INDEX UK1_APP_ID_CLIENT_PWD_POL_ID (`APP_ID`,`CLIENT_ID`),
ADD CONSTRAINT FK1_CLIENT_PWD_POL_ID FOREIGN KEY (`CLIENT_ID`) REFERENCES `security_client` (ID) ON DELETE RESTRICT ON UPDATE RESTRICT,
ADD CONSTRAINT FK2_CLIENT_PWD_POL_ID FOREIGN KEY (`APP_ID`) REFERENCES `security_app` (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;