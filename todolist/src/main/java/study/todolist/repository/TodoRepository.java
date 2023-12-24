package study.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.todolist.entity.todo.TodoList;

public interface TodoRepository extends JpaRepository<TodoList, Long> {


}
