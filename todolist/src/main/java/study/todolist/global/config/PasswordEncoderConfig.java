package study.todolist.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
