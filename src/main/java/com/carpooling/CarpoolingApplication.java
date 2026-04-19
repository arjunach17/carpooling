package com.carpooling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry point.
 *
 * @SpringBootApplication is a convenience annotation that combines:
 *   - @Configuration        → marks this as a Spring config class
 *   - @EnableAutoConfiguration → lets Spring Boot auto-configure beans
 *   - @ComponentScan        → scans this package and sub-packages for beans
 */
@SpringBootApplication
public class CarpoolingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarpoolingApplication.class, args);
    }
}
