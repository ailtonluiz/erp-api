CREATE TABLE user
(
    id                   BIGINT       NOT NULL AUTO_INCREMENT,
    first_name           VARCHAR(80)  NOT NULL,
    last_name            VARCHAR(180) NOT NULL,
    email                VARCHAR(200) NOT NULL,
    password             VARCHAR(255) NOT NULL,
    creation_date        DATETIME     NOT NULL,
    update_date          DATETIME     NOT NULL,
    address_city_id      BIGINT       NOT NULL,
    address_zip_code     VARCHAR(9),
    address_street       VARCHAR(100),
    address_number       VARCHAR(5),
    address_complement   VARCHAR(60),
    address_neighborhood VARCHAR(60),
    address_phone        VARCHAR(60),
    status               VARCHAR(10) DEFAULT 'ACTIVE',

    PRIMARY KEY (id),

    CONSTRAINT fk_user_city FOREIGN KEY (address_city_id) REFERENCES city (id)


) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;
