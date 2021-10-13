CREATE TABLE permission
(
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    description VARCHAR(50) NOT NULL,
    status      VARCHAR(10) NOT NULL,

    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;



