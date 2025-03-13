INSERT INTO public.brands(name_brand)
    VALUES 
        ('Toyota'), ('Mitsubishi'), 
        ('Subaru'), ('Lexus'), 
        ('Honda'), ('Kia'),
        ('Hyundai'), ('Lada');

ALTER SEQUENCE public.brands_brand_id_seq INCREMENT BY 50;