package study.todolist.base;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public abstract class BaseEntity {
    private static final AtomicLong idGenerator = new AtomicLong(0);
    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private Boolean isDeleted;

    public BaseEntity() {
        this.id = idGenerator.incrementAndGet();
        this.createDate = LocalDateTime.now();
        this.modifyDate = LocalDateTime.now();
        this.isDeleted = false;
    }

    public Long getId() {
        return id;
    }

    public void delete() {
        this.isDeleted = true;
        this.modifyDate = LocalDateTime.now();
    }

    public void update() {
        this.modifyDate = LocalDateTime.now();
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
