CREATE SEQUENCE IF NOT EXISTS public.brands_brand_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.brands_brand_id_seq
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.brands
(
    brand_id integer NOT NULL DEFAULT nextval('brands_brand_id_seq'::regclass),
    name_brand character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT brands_pkey PRIMARY KEY (brand_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.brands
    OWNER to postgres;

ALTER SEQUENCE public.brands_brand_id_seq
    OWNED BY public.brands.brand_id;