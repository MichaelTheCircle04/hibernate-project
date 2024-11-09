package com.mtrifonov.hibernateproject.repositories;

import com.mtrifonov.hibernateproject.entities.Brand;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @Mikhail Trifonov
 * 
 */
@Repository
public class BrandRepository extends ParentRepositoryImpl<Brand> {
    
    private final String sqlTemplate = """
                                       FROM Brand b
                                       JOIN FETCH b.models m
                                       JOIN FETCH m.cars c
                                       JOIN FETCH c.status s
                                       """;
    @Autowired
    public BrandRepository(SessionFactory factory) {
        super(factory, Brand.class);
    }
    
    public Brand findWithDependencies(int id) {
        return findWithDependencies(sqlTemplate, id);
    }
    
    public Brand findWithDependencies(String nameBrand) {
        return findWithDependencies(sqlTemplate, nameBrand);
    }
    
    private Brand findWithDependencies(String sql, Object arg) {
        Query<Brand> query;
        
        try (var session = factory.openSession()) {
            session.beginTransaction();
            if (arg instanceof Integer) {
                int param = (int) arg;
                sql += " WHERE b.id = :param";
                query = session.createQuery(sql, Brand.class).setParameter("param", param);
            } else {
                String param = (String) arg;
                sql += " WHERE b.nameBrand = :param";
                query = session.createQuery(sql, Brand.class).setParameter("param", param);
            }
            
            Brand brand = query.getSingleResult();
            session.getTransaction().commit();
            return brand;
        }
    }
}
