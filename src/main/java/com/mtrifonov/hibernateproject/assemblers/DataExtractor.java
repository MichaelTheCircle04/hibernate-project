package com.mtrifonov.hibernateproject.assemblers;

import com.mtrifonov.hibernateproject.entities.Brand;
import com.mtrifonov.hibernateproject.entities.Car;
import com.mtrifonov.hibernateproject.entities.Model;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @Mikhail Trifonov
 */
@Component
public class DataExtractor {
    
    public Map<String, Object> extractData(Object arg) {
        
        Map<String, Object> data = new HashMap<>();
        if (arg instanceof Brand) {
            Brand brand = (Brand) arg;
            data.put("id", brand.getId());
            data.put("nameBrand", brand.getNameBrand());
        } else if (arg instanceof Model) {
            Model model = (Model) arg;
            data.put("id", model.getId());
            data.put("nameBrand", model.getBrand().getNameBrand());
            data.put("nameModel", model.getNameModel());
        } else {
            Car car = (Car) arg;
            data.put("id", car.getId());
            data.put("nameBrand", car.getModel().getBrand().getNameBrand());
            data.put("nameModel", car.getModel().getNameModel());
            data.put("status", car.getStatus());
            data.put("dateProd", car.getDateProd());
            data.put("price", car.getPrice());
        }
        return data;
    }
}
