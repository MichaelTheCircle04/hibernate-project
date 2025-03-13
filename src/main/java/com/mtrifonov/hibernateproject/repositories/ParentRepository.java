package com.mtrifonov.hibernateproject.repositories;

import java.util.List;

/**
 *
 * @Mikhail Trifonov
 * @param <T>
 */
public interface ParentRepository<T> {
    void deleteById(int id);
    T findById(int id);
    List<T> findAll();
}
