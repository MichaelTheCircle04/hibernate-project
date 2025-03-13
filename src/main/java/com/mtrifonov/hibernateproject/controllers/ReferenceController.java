package com.mtrifonov.hibernateproject.controllers;

import com.mtrifonov.hibernateproject.assemblers.BrandModelAssembler;
import com.mtrifonov.hibernateproject.assemblers.CarModelAssembler;
import com.mtrifonov.hibernateproject.assemblers.ModelModelAssembler;
import com.mtrifonov.hibernateproject.dtos.BrandDTO;
import com.mtrifonov.hibernateproject.dtos.CarDTO;
import com.mtrifonov.hibernateproject.dtos.ModelDTO;
import com.mtrifonov.hibernateproject.services.CarService;
import lombok.AllArgsConstructor;
import java.util.List;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @Mikhail Trifonov
 */
@RestController
@RequestMapping("/shop")
@AllArgsConstructor
public class ReferenceController {
    
    private final CarService carService;
    private final BrandModelAssembler bma;
    private final ModelModelAssembler mma;
    private final CarModelAssembler cma;

    @GetMapping("/brands/{id}")
    public EntityModel<BrandDTO> brandById(@PathVariable int id) {
        return bma.toModel(carService.getBrandById(id));
    }

    @GetMapping("/models/by/brand/{id}")
    public List<EntityModel<ModelDTO>> modelsByBrandId(@PathVariable int id) {
        return carService.getAllModelsByBrandId(id).stream().map(mma::toModel).toList();
    }

    @GetMapping("/models/{id}")
    public EntityModel<ModelDTO> modelById(@PathVariable int id) {
        return mma.toModel(carService.getModelById(id));
    } 
    
    @GetMapping("/{id}")
    public EntityModel<CarDTO> carById(@PathVariable int id) {
        return cma.toModel(carService.getCarById(id));
    }

    @GetMapping("/by/brand/{id}")
    public List<EntityModel<CarDTO>> carsByBrandId(@PathVariable int id) {
        return carService.getAllCarsByBrandId(id).stream().map(cma::toModel).toList();
    }

    @GetMapping("/by/model/{id}")
    public List<EntityModel<CarDTO>> carsByModelId(@PathVariable int id) {
        return carService.getAllCarsByModelId(id).stream().map(cma::toModel).toList();
    }
}
