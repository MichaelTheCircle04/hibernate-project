package com.mtrifonov.hibernateproject.configs;

import com.mtrifonov.hibernateproject.entities.Brand;
import com.mtrifonov.hibernateproject.entities.Car;
import com.mtrifonov.hibernateproject.entities.Model;
import com.mtrifonov.hibernateproject.entities.Sale;
import com.mtrifonov.hibernateproject.entities.StatusTable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @Mikhail Trifonov
 */
@Configuration
@ComponentScan({"com.mtrifonov.dataproject.repositories"}) // удалить при запуске с бутом
@PropertySource("classpath:application.properties") // удалить при запуске с бутом
public class ProjectConfig {
    
    @Value("${files.cars}")
    private String carsLocation;
    @Value("${files.models}")
    private String modelsLocation;
    @Value("${files.brands}")
    private String brandsLocation;
    @Value("${hibernate.configuration}")
    private String hibernateConfig;
    
    @Bean
    public CommandLineRunner initDatabase(SessionFactory factory) {
        return args -> {
            BufferedReader carsReader = new BufferedReader(new FileReader(carsLocation));
            BufferedReader modelsReader = new BufferedReader(new FileReader(modelsLocation));
            BufferedReader brandsReader = new BufferedReader(new FileReader(brandsLocation));
            try (Session session = factory.openSession()) {
                session.beginTransaction();
                
                List<StatusTable> statuses = new ArrayList<>();
                for (StatusTable.Status status : StatusTable.Status.values()) {
                    statuses.add(new StatusTable(status));
                }
                statuses.forEach(s -> session.persist(s));
                Map<String, Brand> brands = brandsReader.lines().map(name -> { 
                    return new Brand(name);
                }).collect(Collectors.toMap(b -> b.getNameBrand(), b -> b)); 
                brands.entrySet().forEach(e -> session.persist(e.getValue()));
                Map<String, Model> models = modelsReader.lines().map(line -> {
                    String[] data = line.split(", ");
                    Model model = new Model();
                    model.setNameModel(data[0]);
                    model.setBrand(brands.get(data[1]));
                    return model;
                }).collect(Collectors.toMap(m -> m.getNameModel(), m -> m));
                models.entrySet().forEach(e -> session.persist(e.getValue()));
                List<Car> cars = carsReader.lines().map(line -> {
                    String[] data = line.split(", ");
                    Car car = new Car();
                    car.setModel(models.get(data[0]));
                    car.setPrice(Integer.parseInt(data[1]));
                    car.setDateProd(LocalDate.parse(data[2]));
                    car.setStatus(session.get(StatusTable.class, 1));
                    return car;
                }).toList();
                cars.forEach(c -> session.persist(c));
                session.getTransaction().commit();
            }
        };
    }
    @Bean
    public SessionFactory entityManagerFactory() throws Exception {
        org.hibernate.cfg.Configuration config = new org.hibernate.cfg.Configuration();
        Properties prop = new Properties();
        prop.load(new FileReader(hibernateConfig));
        config.setProperties(prop);
        config.addAnnotatedClass(Car.class);
        config.addAnnotatedClass(Model.class);
        config.addAnnotatedClass(Brand.class);
        config.addAnnotatedClass(Sale.class);
        config.addAnnotatedClass(StatusTable.class); 
        try {
            SessionFactory factory = config.buildSessionFactory();
            return factory;
        } catch (HibernateException e) {
            throw e;
        }
    }
}
