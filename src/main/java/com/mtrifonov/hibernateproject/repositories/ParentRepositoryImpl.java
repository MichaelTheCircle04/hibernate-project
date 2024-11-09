package com.mtrifonov.hibernateproject.repositories;

import com.mtrifonov.hibernateproject.entities.ParentEntity;
import jakarta.persistence.Query;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @Mikhail Trifonov
 * @param <T>
 */
public class ParentRepositoryImpl<T extends ParentEntity> implements ParentRepository<T> {
    
    protected final SessionFactory factory;
    private final Class<T> cl;

    public ParentRepositoryImpl(SessionFactory factory, Class<T> cl) {
        this.factory = factory;
        this.cl = cl;
    }
    
    @Override
    public long count() {
        try (var session = factory.openSession()) {
            session.beginTransaction();
            String sql = "SELECT COUNT(*) FROM " + cl.getSimpleName();          
            Query query = session.createQuery(sql, Long.class);
            long result = (long) query.getSingleResult();
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public void save(T t) {
        try (var session = factory.openSession()) {
            session.beginTransaction();
            if (t.idPresent()) {
                session.merge(t);
            } else {
                session.persist(t);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(T t) {
        try (var session = factory.openSession()) {
            session.beginTransaction();
            session.remove(t);           
            session.getTransaction().commit();
        }
    }
    
    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM " + cl.getSimpleName() + " WHERE id" + " = :id";
        try (var session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery(sql, Void.class);
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public T findById(int id) {
        String sql = "FROM " + cl.getSimpleName() + " WHERE id" + " = :id";
        try (var session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery(sql, cl);
            query.setParameter("id", id);
            T entity = (T) query.getSingleResult();
            session.getTransaction().commit();
            return entity;
        }        
    }
    
    @Override
    public List<T> findAll() {
        String sql = "FROM " + cl.getSimpleName();
        try (var session = factory.openSession()) {
            session.beginTransaction();
            List<T> list = session.createQuery(sql, cl).list();
            session.getTransaction().commit();
            return list;
        }
    }
    
}
