CREATE SEQUENCE IF NOT EXISTS public.shop_car_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.shop_car_id_seq
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.shop
(
    car_id integer NOT NULL DEFAULT nextval('shop_car_id_seq'::regclass),
    model_id integer NOT NULL,
    price integer NOT NULL,
    status_id integer NOT NULL,
    date_prod date NOT NULL,
    sale_id integer,
    CONSTRAINT shop_pkey PRIMARY KEY (car_id),
    CONSTRAINT shop_model_id_fkey FOREIGN KEY (model_id)
        REFERENCES public.models (model_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID,

    CONSTRAINT shop_status_id_fkey FOREIGN KEY (status_id)
        REFERENCES public.status (status_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.shop
    OWNER to postgres;

ALTER SEQUENCE public.shop_car_id_seq
    OWNED BY public.shop.car_id;
