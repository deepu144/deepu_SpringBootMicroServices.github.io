package com.deepu.laptopservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LaptopServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaptopServiceApplication.class, args);
    }

}