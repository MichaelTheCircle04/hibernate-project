CREATE SEQUENCE IF NOT EXISTS public.sales_sale_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.sales_sale_id_seq
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.sales
(
    sale_id integer NOT NULL DEFAULT nextval('sales_sale_id_seq'::regclass),
    date_sale date NOT NULL,
    car_id integer NOT NULL,
    CONSTRAINT sales_pkey PRIMARY KEY (sale_id),
    CONSTRAINT sales_car_id_fkey FOREIGN KEY (car_id)
        REFERENCES public.shop (car_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.sales
    OWNER to postgres;

ALTER SEQUENCE public.sales_sale_id_seq
    OWNED BY public.sales.sale_id;