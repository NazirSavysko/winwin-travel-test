package com.winwin.travel.dataapi;

import org.apache.catalina.filters.HttpHeaderSecurityFilter;import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DataApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataApiApplication.class, args);
    }


}
