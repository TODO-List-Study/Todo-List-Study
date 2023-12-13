package study.todolist.base;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private Boolean isDeleted;

    public BaseEntity() {
        this.isDeleted = false;
    }

    public void delete() {
        this.isDeleted = true;
    }

    public Boolean isDeleted() {
        return isDeleted;
    }
}
