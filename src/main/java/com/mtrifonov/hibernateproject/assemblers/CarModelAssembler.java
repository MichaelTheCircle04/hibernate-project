package com.mtrifonov.hibernateproject.assemblers;

import com.mtrifonov.hibernateproject.dtos.CarDTO;
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
public class CarModelAssembler implements RepresentationModelAssembler<CarDTO, EntityModel<CarDTO>> {
    
    @Override
    public EntityModel<CarDTO> toModel(CarDTO c) {

        var tmp1 = UriTemplate.of("http://localhost:8080/shop/models/{id}"); //на модель
        var tmp2 = UriTemplate.of("http://localhost:8080/shop/brands/{id}"); //на марку
        var tmp3 = UriTemplate.of("http://localhost:8080/shop/{id}"); //на себя

        return EntityModel.of(c, 
                Link.of(tmp1, c.getModel() + ": ").expand(c.getModelId()),
                Link.of(tmp2, c.getBrand() + ": ").expand(c.getBrandId()),
                Link.of(tmp3, "This car: ").expand(c.getId())
            );
    } 
}
