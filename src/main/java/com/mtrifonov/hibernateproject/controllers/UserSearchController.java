package com.mtrifonov.hibernateproject.controllers;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mtrifonov.hibernateproject.assemblers.BrandModelAssembler;
import com.mtrifonov.hibernateproject.assemblers.CarModelAssembler;
import com.mtrifonov.hibernateproject.assemblers.ModelModelAssembler;
import com.mtrifonov.hibernateproject.dtos.BrandDTO;
import com.mtrifonov.hibernateproject.dtos.CarDTO;
import com.mtrifonov.hibernateproject.dtos.ModelDTO;
import com.mtrifonov.hibernateproject.services.SearchService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class UserSearchController {

    private final SearchService service;
    private final CarModelAssembler cma;
    private final BrandModelAssembler bma;
    private final ModelModelAssembler mma;

    @GetMapping("/cars")
    public List<EntityModel<CarDTO>> carsByName(
        @RequestParam String name,
        @RequestParam Integer[] brands, 
        @RequestParam Integer[] models,
        @RequestParam(defaultValue = "0") Integer minPrice,
        @RequestParam(defaultValue = "2147483647") Integer maxPrice) {
            
        return service.searchCars(name, brands, models, minPrice, maxPrice).stream().map(cma::toModel).toList();
    }

    @GetMapping("/brands")
    public List<EntityModel<BrandDTO>> brandsByName(@RequestParam String name) {
        return service.searchBrands(name).stream().map(bma::toModel).toList();
    } 

    @GetMapping("/models")
    public List<EntityModel<ModelDTO>> modelsByName(
        @RequestParam String name,
        @RequestParam Integer[] brands) {

        return service.searchModels(name, brands).stream().map(mma::toModel).toList();
    }
}
