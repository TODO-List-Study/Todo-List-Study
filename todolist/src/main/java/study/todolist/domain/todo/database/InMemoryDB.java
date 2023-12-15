package study.todolist.domain.todo.database;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InMemoryDB<K, V> {
    private final ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>();

    public V findById(K key) {
        validateKey(key);
        return map.get(key);
    }

    public void save(K key, V value) {
        validateKey(key);
        map.put(key, value);
    }

    public void deleteById(K key) {
        validateKey(key);
        map.remove(key);
    }

    public List<V> findAll() {
        return map.values().stream().collect(Collectors.toList());
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("키는 Null이 아니여야 합니다.");
        }
    }
}
