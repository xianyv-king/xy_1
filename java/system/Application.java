package system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("system.mapper")
@SpringBootApplication

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
