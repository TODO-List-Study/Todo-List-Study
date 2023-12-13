package study.todolist.domain.repository;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.lang.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public abstract class Repository implements RepositoryInterface{

    public Object save(Long id, Object object, ConcurrentHashMap db){
        if (db.containsKey(id)){
            id++;
            save(id, object, db);
        }
        db.put(id, object);
        return db.get(id);
    }

    public Optional<Object> findById(Long id, ConcurrentHashMap db){
        return Optional.ofNullable(db.get(id));
    };

    public void deleteById(Long id, ConcurrentHashMap db){
        db.remove(id);
    }

    public List<Object> findAll(ConcurrentHashMap db){
        return db.values().stream().toList();
    }
}
