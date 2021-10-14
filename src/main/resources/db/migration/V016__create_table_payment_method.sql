CREATE TABLE payment_method
(
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    description   VARCHAR(150) NOT NULL,
    status        VARCHAR(10) DEFAULT 'ACTIVE',
    creation_date DATETIME     NOT NULL,
    update_date   DATETIME     NOT NULL,

    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;