package com.mtrifonov.hibernateproject.repositories;

import com.mtrifonov.hibernateproject.entities.Model;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @Mikhail Trifonov
 */
@Repository
public class ModelRepository extends ParentRepositoryImpl<Model> {
    
    
    private final String sqlTemplate = """
                                       FROM Model m
                                       JOIN FETCH m.cars c
                                       JOIN FETCH m.brand b
                                       JOIN FETCH c.status s
                                       """;
    @Autowired
    public ModelRepository(SessionFactory factory) {
        super(factory, Model.class);
    }  
    
    public Model findWithDependencies(int id) {
        return findWithDependencies(sqlTemplate, id);
    }
    
    public Model findWithDependencies(String nameModel) {
        return findWithDependencies(sqlTemplate, nameModel);
    }
    
    private Model findWithDependencies(String sql, Object arg) {
        Query<Model> query;
        
        try (var session = factory.openSession()) {
            session.beginTransaction();
            if (arg instanceof Integer) {
                int param = (int) arg;
                sql += " WHERE m.id = :param";
                query = session.createQuery(sql, Model.class).setParameter("param", param);
            } else {
                String param = (String) arg;
                sql += " WHERE m.nameModel = :param";
                query = session.createQuery(sql, Model.class).setParameter("param", param);
            }   
            
            Model model = query.getSingleResult();
            session.getTransaction().commit();
            return model;
        }
    }
    
    @Override
    public List<Model> findAll() {
        String sql = """
                     FROM Model m
                     JOIN FETCH m.brand
                     """;
        
        try (var session = factory.openSession()) {
            session.beginTransaction();
            List<Model> models = session.createQuery(sql, Model.class).list();
            session.getTransaction().commit();
            return models;
        }
    }
}
