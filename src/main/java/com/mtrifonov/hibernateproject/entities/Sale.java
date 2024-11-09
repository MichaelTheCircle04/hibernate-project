package com.mtrifonov.hibernateproject.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @Mikhail Trifonov
 */
@Entity
@Table(name = "sales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale implements ParentEntity {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sales_seq")
    @SequenceGenerator(name="sales_seq", sequenceName="sales_sale_id_seq", allocationSize = 1)    
    @Column(name = "sale_id") 
    private int id;
    
    @Column(name = "date_sale")
    private LocalDate dateSale;
    
    @OneToOne(mappedBy = "sale", cascade = CascadeType.PERSIST)
    private Car car;
    
}
