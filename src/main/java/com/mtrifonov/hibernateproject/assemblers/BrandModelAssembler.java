package com.mtrifonov.hibernateproject.assemblers;

import com.mtrifonov.hibernateproject.entities.Brand;
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
public class BrandModelAssembler implements RepresentationModelAssembler<Brand, EntityModel<Map<String, Object>>>{

    private final DataExtractor extractor;

    @Autowired
    public BrandModelAssembler(DataExtractor extractor) {
        this.extractor = extractor;
    }
    
    @Override
    public EntityModel<Map<String, Object>> toModel(Brand entity) {
        UriTemplate tmp = UriTemplate.of("http://localhost:8080/shop/by/brand{?brands}");
        Map<String, Object> data = extractor.extractData(entity);
        return EntityModel.of(data, 
                Link.of(tmp, "All " + data.get("nameBrand")).expand(data.get("nameBrand")));
    }
    
}
