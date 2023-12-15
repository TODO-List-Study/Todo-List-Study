package study.todolist.domain.todo.database;

import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryDB<K, V> {
    private final ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>();

    public V findById(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }
        return map.get(key);
    }

    public void save(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }
        map.put(key, value);
    }

    public void deleteById(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }
        map.remove(key);
    }

    public List<V> findAll() {
        return map.values().stream().collect(Collectors.toList());
    }
}
