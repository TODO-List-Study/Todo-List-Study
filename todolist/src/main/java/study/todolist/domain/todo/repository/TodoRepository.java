package study.todolist.domain.todo.repository;

import org.springframework.data.repository.Repository;
import study.todolist.domain.todo.entity.Todo;

public interface TodoRepository extends Repository<Todo, Long> {

}

