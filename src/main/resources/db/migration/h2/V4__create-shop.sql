CREATE SEQUENCE IF NOT EXISTS shop_car_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE IF NOT EXISTS shop
(
    car_id integer NOT NULL DEFAULT nextval('shop_car_id_seq'),
    model_id integer NOT NULL,
    price integer NOT NULL,
    status_id integer NOT NULL,
    date_prod date NOT NULL,
    sale_id integer,
    CONSTRAINT shop_pkey PRIMARY KEY (car_id),
    CONSTRAINT shop_model_id_fkey FOREIGN KEY (model_id)
        REFERENCES models (model_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT shop_status_id_fkey FOREIGN KEY (status_id)
        REFERENCES status (status_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
