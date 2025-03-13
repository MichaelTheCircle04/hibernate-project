package com.mtrifonov.hibernateproject.assemblers;

import com.mtrifonov.hibernateproject.dtos.BrandDTO;
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
public class BrandModelAssembler implements RepresentationModelAssembler<BrandDTO, EntityModel<BrandDTO>>{
    
    @Override
    public EntityModel<BrandDTO> toModel(BrandDTO b) {

        var tmp1 = UriTemplate.of("http://localhost:8080/shop/by/brand{id}"); //на все автомобили этой марки
        var tmp2 = UriTemplate.of("http://localhost:8080/shop/models/by/brand/{id}"); //на все все модели этой марки

        return EntityModel.of(b, 
                Link.of(tmp1, "All " + b.getName() + " cars: ").expand(b.getId()),
                Link.of(tmp2, "All " + b.getName() + " models: ").expand(b.getId())
            );
    }   
}
