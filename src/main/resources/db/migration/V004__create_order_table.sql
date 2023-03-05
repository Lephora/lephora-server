CREATE TABLE lephora_order
(
    id               text PRIMARY KEY,
    commodity_sku    text           NOT NULL,
    quantity         int8           NOT NULL,
    price            numeric(12, 2) not null,
    price_unit       text           not null,
    order_status     text           not null,
    address          text           not null,
    full_name        text           not null,
    phone_number     text           not null,
    customer_id      text           not null,
    created_at       timestamptz    NOT NULL,
    created_by       text           NOT NULL,
    last_modified_at timestamptz    NOT NULL,
    last_modified_by text           NOT NULL
);

