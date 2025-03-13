package com.mtrifonov.hibernateproject;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.mtrifonov.hibernateproject.configs.ProjectConfig;
import com.mtrifonov.hibernateproject.dtos.CarDTO;
import com.mtrifonov.hibernateproject.dtos.ModelDTO;
import com.mtrifonov.hibernateproject.repositories.BrandRepository;
import com.mtrifonov.hibernateproject.repositories.CarRepository;
import com.mtrifonov.hibernateproject.repositories.ModelRepository;
import com.mtrifonov.hibernateproject.services.SearchService;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) 
@DataJpaTest
@ActiveProfiles("test")
@Import(value = {SearchService.class, CarRepository.class, BrandRepository.class, ModelRepository.class, ProjectConfig.class})
@TestPropertySource("classpath:application-test.yml")
public class SearchServiceTest {

    @Autowired
    SearchService searchService;

    @Test
    public void testSearchService() {

        var cars = searchService.searchCars("cam", null, new Integer[]{1}, 3600000, 4600000);
        assertTrue(cars.size() == 1 && "Camry".equals(cars.get(0).getModel()) && 4500000 == cars.get(0).getPrice());

        cars = searchService.searchCars("mit", null, new Integer[]{3}, 350000, 850000);
        var prices = cars.stream().map(CarDTO::getPrice).toList();
        assertThat(prices, containsInAnyOrder(400000, 500000, 700000, 800000));

        var result = searchService.searchCars("mit", null, null, 940000, Integer.MAX_VALUE).stream().map(CarDTO::getModel).toList();
        assertThat(result, containsInAnyOrder("Lancer", "Pajero"));

        result = searchService.searchModels("lan", new Integer[]{2}).stream().map(ModelDTO::getName).toList();
        assertThat(result, containsInAnyOrder("Lancer"));
    }
}
