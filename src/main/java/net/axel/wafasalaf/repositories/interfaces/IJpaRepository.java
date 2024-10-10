package net.axel.wafasalaf.repositories.interfaces;

import java.util.List;
import java.util.Optional;

public interface IJpaRepository<T, ID> {
    T save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    T update(T entity);

    void delete(ID id);
}
