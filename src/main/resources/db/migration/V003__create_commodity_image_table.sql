CREATE TABLE commodity_image
(
    id               int8 GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    url              text NOT NULL,
    commodity_sku    text NOT NULL,
    alternative_text varchar(200)
);

ALTER TABLE commodity_image
    ADD CONSTRAINT FKf37hd6a9opqt363t60ey7vu6y FOREIGN KEY (commodity_sku) REFERENCES commodity;

