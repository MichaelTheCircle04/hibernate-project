package com.mtrifonov.hibernateproject.repositories;

import com.mtrifonov.hibernateproject.entities.Sale;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @Mikhail Trifonov
 */
@Repository
public class SaleRepository extends ParentRepositoryImpl<Sale> {
    
    @Autowired
    public SaleRepository(SessionFactory factory) {
        super(factory, Sale.class);
    }
    
}
