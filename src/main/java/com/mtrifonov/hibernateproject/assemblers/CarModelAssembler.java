package com.mtrifonov.hibernateproject.assemblers;

import com.mtrifonov.hibernateproject.entities.Car;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 *
 * @Mikhail Trifonov
 */
@Component
public class CarModelAssembler implements RepresentationModelAssembler<Car, EntityModel<Map<String, Object>>> {
    
    private final DataExtractor extractor;

    @Autowired
    public CarModelAssembler(DataExtractor extractor) {
        this.extractor = extractor;
    }
    
    @Override
    public EntityModel<Map<String, Object>> toModel(Car entity) {
        UriTemplate tmp1 = UriTemplate.of("http://localhost:8080/shop/by/model{?models}");
        UriTemplate tmp2 = UriTemplate.of("http://localhost:8080/shop/by/brand{?brand}");
        UriTemplate tmp3 = UriTemplate.of("http://localhost:8080/shop/{?id}");
        Map<String, Object> data = extractor.extractData(entity);
        return EntityModel.of(data, 
                Link.of(tmp1, "All " + data.get("nameModel")).expand(data.get("nameModel")),
                Link.of(tmp2, "All " + data.get("nameBrand")).expand(data.get("nameBrand")),
                Link.of(tmp3, "This car: ").expand(data.get("id")));
    }
    
}
