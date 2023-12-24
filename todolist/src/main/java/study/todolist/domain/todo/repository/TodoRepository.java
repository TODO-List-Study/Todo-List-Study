package study.todolist.domain.todo.repository;

import org.springframework.data.repository.Repository;
import study.todolist.domain.todo.entity.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends Repository<Todo, Long> {
    Optional<Todo> findById(Long id);
    Todo save(Todo Todo);
    List<Todo> findAll();
    void deleteById(Long id);

    void saveAll(List<Todo> todos);
}

