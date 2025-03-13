package com.mtrifonov.hibernateproject.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Set;
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
@Table(name = "models")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"brand", "cars"})
@ToString(exclude = {"brand", "cars"})
public class Model implements ParentEntity {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="model_seq")
    @SequenceGenerator(name="model_seq", sequenceName="models_model_id_seq", allocationSize = 50) 
    @Column(name = "model_id")
    private int id;
    
    @Column(name = "name_model")
    private String nameModel;
    
    @OneToMany(mappedBy = "model", cascade = CascadeType.REMOVE)
    private Set<Car> cars;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;
    
}
