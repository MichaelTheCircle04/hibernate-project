-- Table: public.status

-- DROP TABLE IF EXISTS public.status;

CREATE SEQUENCE IF NOT EXISTS public.status_status_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.status_status_id_seq
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.status
(
    status_id integer NOT NULL DEFAULT nextval('status_status_id_seq'::regclass),
    name_status character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT status_pkey PRIMARY KEY (status_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.status
    OWNER to postgres;

ALTER SEQUENCE public.status_status_id_seq
    OWNED BY public.status.status_id;



