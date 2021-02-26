package cn.os.myframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MyframeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyframeApplication.class, args);
    }

}
