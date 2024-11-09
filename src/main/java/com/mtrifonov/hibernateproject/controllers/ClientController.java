package com.mtrifonov.hibernateproject.controllers;

import com.mtrifonov.hibernateproject.assemblers.BrandModelAssembler;
import com.mtrifonov.hibernateproject.assemblers.CarModelAssembler;
import com.mtrifonov.hibernateproject.assemblers.DataExtractor;
import com.mtrifonov.hibernateproject.assemblers.ModelModelAssembler;
import com.mtrifonov.hibernateproject.entities.Brand;
import com.mtrifonov.hibernateproject.entities.Car;
import com.mtrifonov.hibernateproject.entities.Model;
import com.mtrifonov.hibernateproject.repositories.BrandRepository;
import com.mtrifonov.hibernateproject.repositories.CarRepository;
import com.mtrifonov.hibernateproject.repositories.ModelRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @Mikhail Trifonov
 */
@RestController
@RequestMapping("/shop")
public class ClientController {
    
    private final CarRepository carRepo;
    private final ModelRepository modelRepo;
    private final BrandRepository brandRepo;
    private final BrandModelAssembler bma;
    private final ModelModelAssembler mma;
    private final CarModelAssembler cma;
    private final DataExtractor extractor;
    
    @Autowired
    public ClientController(CarRepository carRepo, ModelRepository modelRepo, 
            BrandRepository brandRepo, BrandModelAssembler bma, 
            ModelModelAssembler mma, CarModelAssembler cma,
            DataExtractor extractor) {
        this.carRepo = carRepo;
        this.modelRepo = modelRepo;
        this.brandRepo = brandRepo;
        this.bma = bma;
        this.mma = mma;
        this.cma = cma;
        this.extractor = extractor;
    }
    //done
    @GetMapping("/brands")
    public CollectionModel<EntityModel<Map<String, Object>>> allBrands() {        
        return CollectionModel.of(brandRepo.findAll().stream().map(bma::toModel).toList());
    }
    //done
    @GetMapping("/models")
    public CollectionModel<EntityModel<Map<String, Object>>> allModels() {
        return CollectionModel.of(modelRepo.findAll().stream().map(mma::toModel).toList());
    }
    //done
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> allCars() {
        List<Map<String, Object>> result = new ArrayList<>();
        carRepo.findAllWithStatus(1).forEach(c -> result.add(extractor.extractData(c)));
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }
    //done
    @GetMapping("/by/brand")
    public CollectionModel<EntityModel<Map<String, Object>>> allCarsByBrand(@RequestParam(name = "brands") String[] args) {
        BiFunction<List, Set, List> func = (l, s) -> {
            s.forEach(e -> l.add(e));
            return l;
        };
        BinaryOperator<List> operator = (res, arg) -> {
            arg.forEach(e -> res.add(e));
            return res;
        };
        return CollectionModel.of(brandRepo.findWithDependencies(args[0]).getModels().stream().map(m -> m.getCars()).map(cars -> {
            Set<EntityModel<Car>> models = new HashSet<>();
            cars.forEach(c -> {
                EntityModel model = cma.toModel(c);
                models.add(model);
            });
            return models;
        }).reduce(new ArrayList(), func, operator));
    }
    //done
    @GetMapping("/{id}")
    public EntityModel<Map<String, Object>> carById(@PathVariable int id) {
        return cma.toModel(carRepo.findById(id));
    }

    
    /*private List<Car> template(Predicate<Car> pred) {
        return carRepo.findAllWithStatus(1).stream().filter(pred).toList();
    }
    
    @GetMapping("/by/brand")
    public CollectionModel<Car> allCarsByBrand(@RequestParam(name="brands") String[] args) {
        Predicate<Car> pred = car -> {
            for (String brand : args) {
                if (brand.equals(car.getModel().getBrand().getNameBrand())) {
                    return true;
                }
            }
            return false;
        };

        return CollectionModel.of(template(pred), linkTo(methodOn(ClientController.class).allCars()).withRel("cars"));
    }
    
    @GetMapping("/by/model")
    public CollectionModel<Car> allCarsByModel(@RequestParam(name="models") String[] args) {
        Predicate<Car> pred = car -> {
            for (String model : args) {
                if (model.equals(car.getModel().getNameModel())) {
                    return true;
                }
            }
            return false;
        };

        return CollectionModel.of(template(pred), linkTo(methodOn(ClientController.class).allCars()).withRel("cars"));
    }*/
}
