package com.hh.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    void save(T entity);
    void update(T entity);
    void delete(Long id);

    Optional<T> findById(Long id);
    List<T> findAll();
}
