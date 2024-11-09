package com.mtrifonov.hibernateproject;

import com.mtrifonov.hibernateproject.configs.ProjectConfig;
import com.mtrifonov.hibernateproject.entities.Brand;
import com.mtrifonov.hibernateproject.entities.Car;
import com.mtrifonov.hibernateproject.repositories.BrandRepository;
import com.mtrifonov.hibernateproject.repositories.CarRepository;
import com.mtrifonov.hibernateproject.repositories.ModelRepository;
import com.mtrifonov.hibernateproject.repositories.StatusTableRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author 1
 */
@SpringBootApplication
public class Dataproject {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Dataproject.class, args);
        /*ApplicationContext ctx = new AnnotationConfigApplicationContext(ProjectConfig.class);
        var runner = ctx.getBean(CommandLineRunner.class);
        runner.run(args);
        
        var brandRepo = ctx.getBean(BrandRepository.class); 
        brandRepo.findAll().forEach(System.out::println);
        brandRepo.findWithDependencies(3).getModels().forEach(m -> {
            System.out.println(m);
            System.out.println(m.getCars());
        });
        
        var modelRepo = ctx.getBean(ModelRepository.class);
        modelRepo.findAll().forEach(System.out::println);
        modelRepo.findWithDependencies(6).getCars().forEach(System.out::println);
        
        var carRepository = ctx.getBean(CarRepository.class);
        System.out.println(carRepository.findById(2));
        System.out.println(carRepository.findAllWithStatus(1));
        
        var statusRepo = ctx.getBean(StatusTableRepository.class);
        statusRepo.findAll().forEach(System.out::println);*/
    }
}
