package com.vijay.commandlinerunner.comandlinerunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;


@SpringBootApplication
public class ComandLineRunnerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComandLineRunnerApplication.class);

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            LOGGER.info("Starting CLR app");
            ResponseEntity<List<Room>> rooms = restTemplate.exchange("http://localhost:8080/api/rooms", HttpMethod.GET, null, new ParameterizedTypeReference<List<Room>>() {
            });
            rooms.getBody().forEach(room -> {
                LOGGER.info(room.toString());
            });
            LOGGER.info("Ending CLR app");
        };
    }

    public static void main(String[] args) {

        SpringApplication.run(ComandLineRunnerApplication.class, args);
    }

}
