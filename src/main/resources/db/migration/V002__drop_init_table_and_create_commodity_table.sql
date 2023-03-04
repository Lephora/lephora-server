DROP TABLE init_table;

CREATE SEQUENCE commodity_sku_seq;

CREATE TABLE commodity
(
    sku              text PRIMARY KEY     DEFAULT lpad(nextval('commodity_sku_seq')::text, 6, '0'),
    title            text        NOT NULL,
    description      text,
    price            numeric(10, 2)       DEFAULT '0.00',
    price_unit       text        NOT NULL DEFAULT 'RMB',
    created_at       timestamptz NOT NULL,
    created_by       text        NOT NULL,
    last_modified_at timestamptz NOT NULL,
    last_modified_by text        NOT NULL
);
