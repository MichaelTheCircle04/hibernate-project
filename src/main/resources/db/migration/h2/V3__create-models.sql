CREATE SEQUENCE IF NOT EXISTS models_model_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE IF NOT EXISTS models
(
    model_id integer NOT NULL DEFAULT nextval('models_model_id_seq'),
    name_model character varying NOT NULL,
    brand_id integer NOT NULL,
    CONSTRAINT models_pkey PRIMARY KEY (model_id),
    CONSTRAINT models_brand_id_fkey FOREIGN KEY (brand_id)
        REFERENCES brands (brand_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
)

