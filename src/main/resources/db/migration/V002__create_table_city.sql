CREATE TABLE city
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    name     VARCHAR(150) NOT NULL,
    state_id BIGINT       NOT NULL,
    status   VARCHAR(10)  NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_city_state FOREIGN KEY (state_id) REFERENCES state (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;