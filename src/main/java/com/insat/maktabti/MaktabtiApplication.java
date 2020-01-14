package com.insat.maktabti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        MaktabtiApplication.class,
        Jsr310JpaConverters.class
})
@EnableJpaAuditing
public class MaktabtiApplication {
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(MaktabtiApplication.class, args);
    }


}