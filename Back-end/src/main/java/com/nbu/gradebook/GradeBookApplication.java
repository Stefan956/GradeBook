package com.nbu.gradebook;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication(scanBasePackages = {
        "com.nbu.gradebook.*"
})
@EnableJpaRepositories("com.nbu.gradebook.dao")
@ComponentScan(basePackages = { "com.nbu.gradebook.*" })
@EntityScan("com.nbu.gradebook.*")
public class GradeBookApplication {
    public static void main(String[] args) {
        SpringApplication.run(GradeBookApplication.class, args);
    }

}
