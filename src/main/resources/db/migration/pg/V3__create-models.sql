CREATE SEQUENCE IF NOT EXISTS public.models_model_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.models_model_id_seq
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.models
(
    model_id integer NOT NULL DEFAULT nextval('models_model_id_seq'::regclass),
    name_model character varying COLLATE pg_catalog."default" NOT NULL,
    brand_id integer NOT NULL,
    CONSTRAINT models_pkey PRIMARY KEY (model_id),
    CONSTRAINT models_brand_id_fkey FOREIGN KEY (brand_id)
        REFERENCES public.brands (brand_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.models
    OWNER to postgres;

ALTER SEQUENCE public.models_model_id_seq
    OWNED BY public.models.model_id;