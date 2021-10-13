CREATE TABLE user_group
(
    user_id  BIGINT NOT NULL,
    group_id BIGINT NOT NULL,

    PRIMARY KEY (user_id, group_id),

    CONSTRAINT fk_user_group_group FOREIGN KEY (group_id) REFERENCES `group` (id),
    CONSTRAINT fk_user_group_user FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;