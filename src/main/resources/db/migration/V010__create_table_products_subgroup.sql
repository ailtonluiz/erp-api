CREATE TABLE products_subgroup
(
    id                BIGINT      NOT NULL AUTO_INCREMENT,
    description       VARCHAR(50) NOT NULL,
    products_group_id BIGINT      NOT NULL,
    status            VARCHAR(10) DEFAULT 'ACTIVE',
    creation_date     DATETIME    NOT NULL,
    update_date       DATETIME    NOT NULL,


    PRIMARY KEY (id),
    CONSTRAINT fk_products_subgroup_group FOREIGN KEY (products_group_id) REFERENCES products_group (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;