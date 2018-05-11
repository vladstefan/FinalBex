package com.db.bexlibrary.BexLibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableAutoConfiguration
@EnableScheduling
@SpringBootApplication
@EntityScan(basePackages = "com/db/bexlibrary/BexLibrary/entities")
@ComponentScan(basePackages = "com.db.bexlibrary.BexLibrary")
public class BexLibraryApplication {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(BexLibraryApplication.class, args);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

}
