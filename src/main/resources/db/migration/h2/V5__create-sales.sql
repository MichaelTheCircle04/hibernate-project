CREATE SEQUENCE IF NOT EXISTS sales_sale_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE IF NOT EXISTS sales
(
    sale_id integer NOT NULL DEFAULT nextval('sales_sale_id_seq'),
    date_sale date NOT NULL,
    car_id integer NOT NULL,
    CONSTRAINT sales_pkey PRIMARY KEY (sale_id),
    CONSTRAINT sales_car_id_fkey FOREIGN KEY (car_id)
        REFERENCES shop (car_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
