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
public class CarService {

    private final CarRepository carRepo;
    private final ModelRepository modelRepo;
    private final BrandRepository brandRepo;
    
    public BrandDTO getBrandById(int id) { 
        return DTOCreator.createBrandDTO(brandRepo.findById(id));
    }

    public List<ModelDTO> getAllModelsByBrandId(int id) { 

        var p = SqlPreparator.select()
            .from(Brand.class, "b").joinForBrandWithModels()
            .where().eq("b.id", new Integer[]{id}).toMain();

        return brandRepo.findOneByCondition(p).getModels().stream().map(DTOCreator::createModelDTO).toList();
    }

    public ModelDTO getModelById(int id) { 
        return DTOCreator.createModelDTO(modelRepo.findById(id));
    }

    public CarDTO getCarById(int id) { 
        return DTOCreator.createCarDTO(carRepo.findById(id));
    }

    public List<CarDTO> getAllCarsByBrandId(int id) { 

        var p = SqlPreparator.select()
            .from(Car.class, "c").joinForCar()
            .where().eq("b.id", new Integer[]{id}).toMain();

        return carRepo.findByCondition(p).stream().map(DTOCreator::createCarDTO).toList();
    }

    public List<CarDTO> getAllCarsByModelId(int id) { 
        var p = SqlPreparator.select() 
            .from(Model.class, "m").joinForModelWithCars()
            .where().eq("m.id", new Integer[]{id}).toMain();

        return modelRepo.findOneByCondition(p).getCars().stream().map(DTOCreator::createCarDTO).toList();
    }
}
