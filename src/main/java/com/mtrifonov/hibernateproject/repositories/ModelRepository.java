package com.mtrifonov.hibernateproject.repositories;

import com.mtrifonov.hibernateproject.entities.Model;
import com.mtrifonov.hibernateproject.sql.SqlPreparator;
import java.util.List;
import java.util.NoSuchElementException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @Mikhail Trifonov
 */
@Repository
public class ModelRepository extends AbstractRepository<Model> {

    public ModelRepository(SessionFactory factory) {
        super(factory, Model.class);
    }  

    public List<Model> findByCondition(SqlPreparator p) {
        return obtainResult(p);
    }

    public Model findOneByCondition(SqlPreparator p) {
        var res = obtainResult(p);
        if (!res.isEmpty()) {
            return res.get(0);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public List<Model> findAll() {
        return obtainResult(SqlPreparator.select().from(cl, "m").joinForModel());
    }

    @Override
    public Model findById(int id) {

        var p = SqlPreparator.select()
            .from(cl, "m").joinForModel()
            .where().eq("m.id", new Integer[]{id}).toMain();
            
        var res = obtainResult(p);
        if (!res.isEmpty()) {
            return res.get(0);
        } else {
            throw new NoSuchElementException();
        }
    }
}
