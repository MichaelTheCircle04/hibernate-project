package com.mtrifonov.hibernateproject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @Mikhail Trifonov
 */
@Entity
@Table(name = "status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "id")
public class StatusTable implements ParentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="status_seq")
    @SequenceGenerator(name="status_seq", sequenceName="status_status_id_seq", allocationSize = 1)
    @Column(name = "status_id")
    private int id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "name_status") 
    Status status;

    public StatusTable(Status status) {
        this.status = status;
    }
    
    public enum Status {
        FOR_SALE,
        SOLD,
        RESERVED;      
    }   
}
