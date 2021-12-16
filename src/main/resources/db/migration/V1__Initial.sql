CREATE TABLE IF NOT EXISTS uris (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `original_uri` VARCHAR(4096) NOT NULL,
    `short_path` VARCHAR(11) NULL COLLATE UTF8_BIN,
    `deleted` TINYINT(1) NOT NULL DEFAULT 0,
    `created_at` TIMESTAMP(6) NULL,
    `updated_at` TIMESTAMP(6) NULL,

    PRIMARY KEY (id),
    UNIQUE(short_path)
);