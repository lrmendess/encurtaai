CREATE TABLE IF NOT EXISTS uris (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `original_uri` VARCHAR(2048) NOT NULL,
    `short_path` VARCHAR(11) NULL COLLATE UTF8_BIN,
    `created_at` TIMESTAMP(6) NULL,
    `updated_at` TIMESTAMP(6) NULL,
    `deleted` TINYINT(1) NOT NULL DEFAULT 0,

    PRIMARY KEY (id),
    UNIQUE(short_path)
);
