package com.alvyl.springthymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringthymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringthymeleafApplication.class, args);
    }

}
