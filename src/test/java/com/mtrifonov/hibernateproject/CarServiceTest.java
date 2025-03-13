package com.mtrifonov.hibernateproject;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.mtrifonov.hibernateproject.configs.ProjectConfig;
import com.mtrifonov.hibernateproject.dtos.CarDTO;
import com.mtrifonov.hibernateproject.dtos.ModelDTO;
import com.mtrifonov.hibernateproject.repositories.BrandRepository;
import com.mtrifonov.hibernateproject.repositories.CarRepository;
import com.mtrifonov.hibernateproject.repositories.ModelRepository;
import com.mtrifonov.hibernateproject.services.CarService;
import com.mtrifonov.hibernateproject.services.SearchService;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) 
@DataJpaTest
@ActiveProfiles("test")
@Import(value = {CarService.class, SearchService.class, CarRepository.class, BrandRepository.class, ModelRepository.class, ProjectConfig.class})
@TestPropertySource("classpath:application-test.yml")
public class CarServiceTest {

    @Autowired
    CarService carService;

    @Test
    public void testCarService() {

        assertTrue("Toyota".equals(carService.getBrandById(1).getName()));

        var res = carService.getAllModelsByBrandId(1).stream().map(ModelDTO::getName).toList();
        assertThat(res, containsInAnyOrder("Camry", "Corolla"));

        
        assertTrue("Pajero".equals(carService.getModelById(4).getName()));

        var car = carService.getCarById(5);

        assertTrue(1200000 == car.getPrice() && "Granta".equals(car.getModel()) && LocalDate.of(2024, 03, 21).equals(car.getDateProd()));

        var carModels = carService.getAllCarsByBrandId(2).stream().map(CarDTO::getModel).collect(Collectors.toSet());
        assertThat(carModels, containsInAnyOrder("Lancer", "Pajero"));
        carModels = carService.getAllCarsByModelId(4).stream().map(CarDTO::getModel).collect(Collectors.toSet());
        assertThat(carModels, containsInAnyOrder("Pajero"));
    }
}
