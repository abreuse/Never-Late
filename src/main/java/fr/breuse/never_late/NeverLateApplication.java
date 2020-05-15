package fr.breuse.never_late;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NeverLateApplication {
    public static void main(String[] args) {
        SpringApplication.run(NeverLateApplication.class, args);
    }
}
