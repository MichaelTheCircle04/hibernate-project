CREATE SEQUENCE IF NOT EXISTS brands_brand_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE IF NOT EXISTS brands
(
    brand_id integer NOT NULL DEFAULT nextval('brands_brand_id_seq'),
    name_brand character varying NOT NULL,
    CONSTRAINT brands_pkey PRIMARY KEY (brand_id)
)


