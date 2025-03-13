package com.mtrifonov.hibernateproject.dtos;

import com.mtrifonov.hibernateproject.entities.Brand;
import com.mtrifonov.hibernateproject.entities.Car;
import com.mtrifonov.hibernateproject.entities.Model;

public class DTOCreator {

    public static CarDTO createCarDTO(Car c) {

        return CarDTO.builder()
            .id(c.getId())
            .brand(c.getModel().getBrand().getNameBrand())
            .brandId(c.getModel().getBrand().getId())
            .model(c.getModel().getNameModel())
            .modelId(c.getModel().getId())
            .price(c.getPrice())
            .status(c.getStatus().getStatus())
            .dateProd(c.getDateProd())
            .build();
    }
    
    public static BrandDTO createBrandDTO(Brand b) {

        return BrandDTO.builder()
            .id(b.getId())
            .name(b.getNameBrand())
            .build();
    }

    public static ModelDTO createModelDTO(Model m) {

        return ModelDTO.builder()
            .id(m.getId())
            .name(m.getNameModel())
            .brand(m.getBrand().getNameBrand())
            .brandId(m.getBrand().getId())
            .build();
    }
}
