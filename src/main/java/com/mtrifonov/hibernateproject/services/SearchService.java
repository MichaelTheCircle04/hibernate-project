package com.mtrifonov.hibernateproject.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.mtrifonov.hibernateproject.dtos.BrandDTO;
import com.mtrifonov.hibernateproject.dtos.CarDTO;
import com.mtrifonov.hibernateproject.dtos.DTOCreator;
import com.mtrifonov.hibernateproject.dtos.ModelDTO;
import com.mtrifonov.hibernateproject.entities.Brand;
import com.mtrifonov.hibernateproject.entities.Car;
import com.mtrifonov.hibernateproject.entities.Model;
import com.mtrifonov.hibernateproject.repositories.BrandRepository;
import com.mtrifonov.hibernateproject.repositories.CarRepository;
import com.mtrifonov.hibernateproject.repositories.ModelRepository;
import com.mtrifonov.hibernateproject.sql.SqlPreparator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SearchService {

    private CarRepository carRepo;
    private ModelRepository modelRepo;
    private BrandRepository brandRepo;

    public List<CarDTO> searchCars(
        String name, Integer[] brands, 
        Integer[] models, Integer minPrice, 
        Integer maxPrice) {

        var p = SqlPreparator.select()
            .from(Car.class, "c").joinForCar()
            .where().like(new String[]{"b.nameBrand", "m.nameModel"}, name);
        
        if (brands != null) {
            p.and().eq("b.id", brands);
        } else if (models != null) {
            p.and().eq("m.id", models);
        }

        if (minPrice != 0 && maxPrice != Integer.MAX_VALUE) {
            p.and().beetwen("price", minPrice, maxPrice);
        } else if (minPrice != 0 && maxPrice == Integer.MAX_VALUE) {
            p.and().greater("price", minPrice);
        } else if (minPrice == 0 && maxPrice != Integer.MAX_VALUE) {
            p.and().less("price", maxPrice);
        }

        return carRepo.findByCondition(p.toMain()).stream().map(DTOCreator::createCarDTO).toList();
    }

    public List<ModelDTO> searchModels(String name, Integer[] brands) {

        var p = SqlPreparator.select()
            .from(Model.class, "m").joinForModel()
            .where().like(new String[]{"b.nameBrand", "m.nameModel"}, name);
        
        if (brands != null) {
            p.and().eq("b.id", brands);
        }

        return modelRepo.findByCondition(p.toMain()).stream().map(DTOCreator::createModelDTO).toList();
    }

    public List<BrandDTO> searchBrands(String name) {

        var p = SqlPreparator.select()
            .from(Brand.class, "b").toMain()
            .where().like(new String[]{"b.nameBrand"}, name).toMain();

        return brandRepo.findByCondition(p).stream().map(DTOCreator::createBrandDTO).toList();
    }
}
