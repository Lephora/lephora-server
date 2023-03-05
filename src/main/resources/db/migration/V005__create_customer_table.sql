CREATE SEQUENCE customer_id_seq;

CREATE TABLE "customer"
(
    customer_id text PRIMARY KEY DEFAULT lpad(nextval('customer_id_seq')::text, 6, '0'),
    created_at  timestamptz NOT NULL
);

