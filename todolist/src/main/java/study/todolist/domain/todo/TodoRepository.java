package study.todolist.domain.todo;

import org.springframework.stereotype.Repository;
import study.todolist.database.InMemoryDB;
import study.todolist.domain.todo.entity.Todo;

import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepository {

    private final InMemoryDB<Long, Todo> db;

    public TodoRepository(InMemoryDB<Long, Todo> db) {
        this.db = db;
    }

    public Optional<Todo> findById(Long id) {
        return db.findById(id);
    }

    public void save(Todo todo) {
        db.save(todo.getId(), todo);
    }

    public void deleteById(Long id) {
        db.deleteById(id);
    }

    public List<Todo> findAll() {
        return db.findAll();
    }
}

