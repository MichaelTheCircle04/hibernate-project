package com.mtrifonov.hibernateproject.repositories;

import com.mtrifonov.hibernateproject.entities.Car;
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
public class CarRepository extends AbstractRepository<Car> {
    
    public CarRepository(SessionFactory factory) {
        super(factory, Car.class);
    }
    
    public List<Car> findByCondition(SqlPreparator p) {
        return obtainResult(p);
    }
    
    @Override
    public List<Car> findAll() {
        return obtainResult(SqlPreparator.select().from(cl, "c").joinForCar());
    }
    
    @Override
    public Car findById(int id) {

        var p = SqlPreparator.select()
            .from(cl, "c").joinForCar()
            .where().eq("c.id", new Integer[]{id}).toMain();

        var res = obtainResult(p);
        if (!res.isEmpty()) {
            return res.get(0);
        } else {
            throw new NoSuchElementException();
        }
    }
}
