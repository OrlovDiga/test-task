package com.rincentral.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rincentral.test.models.CarFullInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Bean
    public ObjectMapper mapper() {
	    return new ObjectMapper();
    }

    @Bean
    List<CarFullInfo> storage() {
	    return new ArrayList<>();
    }


}
