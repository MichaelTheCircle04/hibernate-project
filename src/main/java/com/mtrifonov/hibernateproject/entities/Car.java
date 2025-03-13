package com.mtrifonov.hibernateproject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @Mikhail Trifonov
 */
@Entity
@Table(name = "shop")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"model", "sale", "status"})
@ToString(exclude = {"model", "sale"})
public class Car implements ParentEntity {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="shop_seq")
    @SequenceGenerator(name="shop_seq", sequenceName="shop_car_id_seq", allocationSize = 50)
    @Column(name = "car_id")
    private int id; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;

    private int price;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private StatusTable status;
    
    @Column(name = "date_prod")
    private LocalDate dateProd;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    private Sale sale;  
}
