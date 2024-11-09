package com.mtrifonov.hibernateproject.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @Mikhail Trifonov
 */
@Entity
@Table(name = "brands")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"models"})
@ToString(exclude = {"models"})
public class Brand implements ParentEntity {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="brand_seq")
    @SequenceGenerator(name="brand_seq", sequenceName="brands_brand_id_seq", allocationSize = 1)
    @Column(name = "brand_id")
    private int id;
    
    @Column(name = "name_brand")
    private String nameBrand;
    
    @OneToMany(mappedBy = "brand", cascade = CascadeType.REMOVE)
    private Set<Model> models;

    public Brand(String nameBrand) {
        this.nameBrand = nameBrand;
    }
}
