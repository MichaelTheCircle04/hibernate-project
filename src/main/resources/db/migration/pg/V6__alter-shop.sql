ALTER TABLE IF EXISTS public.shop
    ADD CONSTRAINT shop_sale_id_fkey FOREIGN KEY (sale_id)
        REFERENCES public.sales (sale_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID