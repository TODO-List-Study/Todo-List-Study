package study.todolist.domain.repository;

import org.springframework.data.repository.Repository;
import study.todolist.domain.ToDo;

public interface ToDoRepository extends Repository<ToDo, Long> {

}
