package com.mtrifonov.hibernateproject.repositories;

import com.mtrifonov.hibernateproject.entities.Brand;
import com.mtrifonov.hibernateproject.sql.SqlPreparator;
import java.util.List;
import java.util.NoSuchElementException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @Mikhail Trifonov
 * 
 */
@Repository
public class BrandRepository extends AbstractRepository<Brand> {
                                   
    public BrandRepository(SessionFactory factory) {
        super(factory, Brand.class);
    }

    public List<Brand> findByCondition(SqlPreparator p) {
        return obtainResult(p);
    }

    public Brand findOneByCondition(SqlPreparator p) {
        var res = obtainResult(p);
        if (!res.isEmpty()) {
            return res.get(0);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Brand findById(int id) {
        return findOneByCondition(
            SqlPreparator.select().from(cl, "b").toMain()
            .where().eq("b.id", new Integer[]{id}).toMain()
            );
    }

    @Override 
    public List<Brand> findAll() {
        return findByCondition(SqlPreparator.select().from(cl, "b").toMain());
    }
}
