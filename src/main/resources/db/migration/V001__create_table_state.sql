CREATE TABLE state
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    name       VARCHAR(150) NOT NULL,
    short_name VARCHAR(2)   NOT NULL,
    status     VARCHAR(10)  NOT NULL,

    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;


