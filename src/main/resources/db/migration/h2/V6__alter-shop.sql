ALTER TABLE IF EXISTS shop
    ADD CONSTRAINT shop_sale_id_fkey FOREIGN KEY (sale_id)
        REFERENCES sales (sale_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE