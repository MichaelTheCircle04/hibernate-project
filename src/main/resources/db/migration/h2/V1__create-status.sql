CREATE SEQUENCE IF NOT EXISTS status_status_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE IF NOT EXISTS status
(
    status_id integer NOT NULL DEFAULT nextval('status_status_id_seq'),
    name_status character varying NOT NULL,
    CONSTRAINT status_pkey PRIMARY KEY (status_id)
)
