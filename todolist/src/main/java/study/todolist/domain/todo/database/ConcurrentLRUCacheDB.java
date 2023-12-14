package study.todolist.domain.todo.database;

import java.util.Collection;
import java.util.concurrent.*;

public class ConcurrentLRUCacheDB<K, V> {
    private final int maxSize;
    private ConcurrentHashMap<K, V> map;
    private LinkedBlockingDeque<K> queue;

    public ConcurrentLRUCacheDB(final int maxSize) {
        this.maxSize = maxSize;
        this.map = new ConcurrentHashMap<>(maxSize);
        this.queue = new LinkedBlockingDeque<>(maxSize);
    }

    public V get(K key) {
        return map.get(key);
    }

    public void put(K key, V value) {
        if (key == null) {
            return;
        }

        if (map.containsKey(key)) {
            queue.remove(key);
        } else if (queue.size() == maxSize) {
            K oldestKey = queue.removeFirst();
            map.remove(oldestKey);
        }
        queue.addLast(key);
        map.put(key, value);
    }

    public void remove(K key) {
        if (map.containsKey(key)) {
            queue.remove(key);
            map.remove(key);
        }
    }

    public Collection<V> values() {
        return map.values();
    }
}
