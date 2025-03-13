INSERT INTO public.models(brand_id, name_model)
    VALUES
        (1, 'Camry'), (2, 'Lancer'),
        (3, 'Imreza'), (4, 'GS450H'),
        (5, 'Civic'), (6, 'Rio'),
        (7, 'Solaris'), (8, 'Granta');

ALTER SEQUENCE public.models_model_id_seq INCREMENT BY 50;