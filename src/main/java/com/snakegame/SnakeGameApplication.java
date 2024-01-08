package com.snakegame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SnakeGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnakeGameApplication.class, args);
    }

}
