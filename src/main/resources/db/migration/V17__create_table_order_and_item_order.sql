CREATE TABLE `order`
(
    id                BIGINT         NOT NULL AUTO_INCREMENT,
    code              VARCHAR(36)    NOT NULL,
    subtotal          DECIMAL(10, 2) NOT NULL,
    shipping_fee      DECIMAL(10, 2) NOT NULL,
    total_value       DECIMAL(10, 2) NOT NULL,

    client_id         BIGINT         NOT NULL,
    payment_method_id BIGINT         NOT NULL,


    status            VARCHAR(10)    NOT NULL,
    creation_date     DATETIME       NOT NULL,
    confirmation_date DATETIME       NULL,
    cancellation_date DATETIME       NULL,
    delivery_date     DATETIME       NULL,


    PRIMARY KEY (id),
    CONSTRAINT fk_order_client FOREIGN KEY (client_id) REFERENCES client (id),
    CONSTRAINT fk_order_payment_method FOREIGN KEY (payment_method_id) REFERENCES payment_method (id),
    CONSTRAINT uk_order_code UNIQUE (code)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

create table item_order
(
    id          BIGINT         NOT NULL AUTO_INCREMENT,
    quantity    SMALLINT(6)    NOT NULL,
    price_unity DECIMAL(10, 2) NOT NULL,
    price_total DECIMAL(10, 2) NOT NULL,
    note        VARCHAR(255)   NULL,
    order_id    BIGINT         NOT NULL,
    product_id  BIGINT         NOT NULL,

    PRIMARY KEY (id),
    UNIQUE KEY uk_item_order_product (order_id, product_id),

    CONSTRAINT fk_item_order_order FOREIGN KEY (order_id) REFERENCES `order` (id),
    CONSTRAINT fk_item_order_product FOREIGN KEY (product_id) REFERENCES product (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;