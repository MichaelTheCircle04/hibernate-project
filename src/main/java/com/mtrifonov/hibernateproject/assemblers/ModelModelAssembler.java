package com.mtrifonov.hibernateproject.assemblers;

import com.mtrifonov.hibernateproject.dtos.ModelDTO;
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
public class ModelModelAssembler implements RepresentationModelAssembler <ModelDTO, EntityModel<ModelDTO>> {
      
    @Override
    public EntityModel<ModelDTO> toModel(ModelDTO m) {

        var tmp1 = UriTemplate.of("http://localhost:8080/shop/by/model{?models}"); //на все автомобили этой модели
        var tmp2 = UriTemplate.of("http://localhost:8080/shop/brands/{id}"); //на марку этой модели

        return EntityModel.of(m, 
                Link.of(tmp1, "All " + m.getName() + " cars: ").expand(m.getName()),
                Link.of(tmp2, m.getBrand() + ": ").expand(m.getBrandId())
            );
    }
}
