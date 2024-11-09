package com.mtrifonov.hibernateproject.repositories;

import com.mtrifonov.hibernateproject.entities.Car;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @Mikhail Trifonov
 */
@Repository
public class CarRepository extends ParentRepositoryImpl<Car> {
    
    @Autowired
    public CarRepository(SessionFactory factory) {
        super(factory, Car.class);
    }
    
    public List<Car> findAllWithStatus(int statusId) {
        String sql = """
                    FROM Car c
                    JOIN FETCH c.model m 
                    JOIN FETCH m.brand b 
                    JOIN FETCH c.status s
                    WHERE s.id = :statusId
                    """;
        
        try (var session = factory.openSession()) {
            session.beginTransaction();
            List<Car> cars = session.createQuery(sql, Car.class).setParameter("statusId", statusId).list();
            session.getTransaction().commit();
            return cars;
        }
    }
    
    @Override
    public List<Car> findAll() {
        String sql = """
                     FROM Car car
                     JOIN FETCH car.model m JOIN FETCH car.model.brand JOIN FETCH car.status s 
                     """;
        
        try (var session = factory.openSession()) {
            session.beginTransaction();            
            List<Car> cars = session.createQuery(sql, Car.class).list();
            cars.forEach(System.out::println);
            session.getTransaction().commit();
            return cars;
        }
    }
    
    @Override
    public Car findById(int id) {
        String sql = """
                     FROM Car c
                     JOIN FETCH c.model m 
                     JOIN FETCH m.brand b 
                     JOIN FETCH c.status s
                     WHERE c.id = :id
                     """;
        
        try (var session = factory.openSession()) {
            session.beginTransaction();
            Car car = session.createQuery(sql, Car.class).setParameter("id", id).getSingleResult();
            session.getTransaction().commit();
            return car;
        }
    }
}
