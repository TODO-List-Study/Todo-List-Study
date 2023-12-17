package study.todolist.repository;

import java.util.List;
import java.util.Optional;

public interface MemoryDBRepository<T> {

    T save(T entity);

    Optional<T> findById(Long id);

    void delete(Long id);

    List<T> findAll();
}
