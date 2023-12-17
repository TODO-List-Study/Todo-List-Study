package study.todolist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoList{

    private Long id;
    private String title;
    private boolean checked;

    public void updateId(Long id){
        this.id = id;
    }

    public void updateTitle(String title){
        this.title = title;
    }

    public void updateChecked(){
        if (this.checked)
            this.checked = false;

        else this.checked = true;
    }
}

