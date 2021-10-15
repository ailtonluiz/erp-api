CREATE TABLE unit
(
    id                BIGINT      NOT NULL AUTO_INCREMENT,
    description       VARCHAR(15),
    short_description VARCHAR(4),
    status            VARCHAR(10) NOT NULL DEFAULT 'ACTIVE',
    creation_date     DATETIME    NOT NULL,
    update_date       DATETIME    NOT NULL,

    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;