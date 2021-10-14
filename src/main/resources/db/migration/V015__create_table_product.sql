CREATE TABLE product
(
    id                   BIGINT         NOT NULL AUTO_INCREMENT,
    description          VARCHAR(180)   NOT NULL,
    stock                INTEGER        NOT NULL DEFAULT 0,
    sale_price           DECIMAL(10, 2) NOT NULL,
    purchase_price       DECIMAL(10, 2) NOT NULL,
    unit_id              BIGINT         NOT NULL,
    brand_id             BIGINT         NOT NULL,
    products_group_id    BIGINT         NOT NULL,
    products_subgroup_id BIGINT         NOT NULL,
    provider_id          BIGINT         NOT NULL,
    status               VARCHAR(10)             DEFAULT 'ACTIVE',
    create_date        DATETIME       NOT NULL,
    update_date          DATETIME       NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_product_unit FOREIGN KEY (unit_id) REFERENCES unit (id),
    CONSTRAINT fk_product_brand FOREIGN KEY (brand_id) REFERENCES brand (id),
    CONSTRAINT fk_product_products_group FOREIGN KEY (products_group_id) REFERENCES products_group (id),
    CONSTRAINT fk_product_products_subgroup FOREIGN KEY (products_subgroup_id) REFERENCES products_subgroup (id),
    CONSTRAINT fk_product_provider FOREIGN KEY (provider_id) REFERENCES provider (id)

) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;