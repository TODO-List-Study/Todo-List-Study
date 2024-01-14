package study.todolist.global;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeUtils {

    public static LocalDateTime convertToLocalTimeZone(Date date){

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
