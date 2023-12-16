package study.todolist.repository;

import study.todolist.entity.TodoList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoRepository implements MemoryDBRepository<TodoList> {

    private List<TodoList> DB = new ArrayList<>();
    Long id = 0L;

    @Override
    public TodoList save(TodoList entity) {
        entity.updateId(++id);
        DB.add(entity);

        return entity;
    }

    @Override
    public Optional<TodoList> findById(Long id) {

        return DB.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst();
    }

    @Override
    public void deleteById(Long id) {

        DB.removeIf(todo -> todo.getId().equals(id));
    }

    @Override
    public List<TodoList> findAll() {

        return DB;
    }
}
