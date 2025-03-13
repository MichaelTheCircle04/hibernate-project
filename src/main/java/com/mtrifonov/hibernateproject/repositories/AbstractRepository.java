package com.mtrifonov.hibernateproject.repositories;

import com.mtrifonov.hibernateproject.entities.ParentEntity;
import com.mtrifonov.hibernateproject.sql.SqlPreparator;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @Mikhail Trifonov
 * @param <T>
 */
public abstract class AbstractRepository<T extends ParentEntity> implements ParentRepository<T> {
    
    private final SessionFactory factory;
    protected final Class<T> cl;

    public AbstractRepository(SessionFactory factory, Class<T> cl) {
        this.factory = factory;
        this.cl = cl;
    }
    
    @Override
    public void deleteById(int id) {
        var p = SqlPreparator.delete().from(cl, "t").toMain().where().eq("t.id", new Integer[]{id}).toMain();
        executeQuery(p);
    }

    protected List<T> obtainResult(SqlPreparator p) {

        try (var session = factory.openSession()) {
            session.beginTransaction();
            var q = session.createQuery(p.getSql(), cl);
            var args = p.getParamArgs();
            for (int i = 0; i < args.size(); i++) {
                q.setParameter("p" + (i + 1), args.get(i));
            }
            var result = q.list();
            session.getTransaction().commit();
            return result;
        }
    }

    protected void executeQuery(SqlPreparator p) {

        try (var session = factory.openSession()) {
            session.beginTransaction();
            var q = session.createQuery(p.getSql(), cl);
            var args = p.getParamArgs();
            for (int i = 0; i < args.size(); i++) {
                q.setParameter("p" + (i + 1), args.get(i));
            }
            q.executeUpdate();
            session.getTransaction().commit();
        }
    }
}
