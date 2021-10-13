CREATE TABLE client
(
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    name          VARCHAR(200) NOT NULL,
    email         VARCHAR(150),
    phone         VARCHAR(10),
    status        VARCHAR(10) DEFAULT 'ACTIVE',
    contact       VARCHAR(150),
    note          VARCHAR(255),
    creation_date DATETIME     NOT NULL,
    update_date   DATETIME     NOT NULL,

    PRIMARY KEY (id)

) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;