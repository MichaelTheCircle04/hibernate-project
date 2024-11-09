package com.mtrifonov.hibernateproject.entities;

/**
 *
 * @Mikhail Trifonov
 */
public interface ParentEntity {
    int getId();
    
    default boolean idPresent() {
        return getId() != 0;
    }
}
