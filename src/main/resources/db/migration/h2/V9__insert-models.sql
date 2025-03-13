INSERT INTO models(brand_id, name_model)
    VALUES
        (1, 'Camry'), (1, 'Corolla'), 
        (2, 'Lancer'), (2, 'Pajero'),
        (3, 'Imreza'), (4, 'GS450H'),
        (5, 'Civic'), (6, 'Rio'),
        (7, 'Solaris'), (8, 'Granta');

ALTER SEQUENCE models_model_id_seq INCREMENT BY 50;