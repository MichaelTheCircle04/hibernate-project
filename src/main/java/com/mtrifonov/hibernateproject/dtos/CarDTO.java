package com.mtrifonov.hibernateproject.dtos;

import static com.mtrifonov.hibernateproject.entities.StatusTable.*;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

    private int id;
    private String brand;
    private int brandId;
    private String model;
    private int modelId;
    private int price;
    private Status status;
    private LocalDate dateProd;

}
