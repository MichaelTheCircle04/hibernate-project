package com.mtrifonov.hibernateproject.repositories;

import java.util.List;

/**
 *
 * @Mikhail Trifonov
 * @param <T>
 */
public interface ParentRepository<T> {
    long count();
    void save(T t);
    void delete(T t);
    void deleteById(int id);
    T findById(int id);
    List<T> findAll();
}
