package study.todolist.repository;

import java.util.List;
import java.util.Optional;

public interface MemoryDBRepository<T> {

    T save(T entity);

    Optional<T> findById(Long id);

    void deleteById(Long id);

    List<T> findAll();
}
