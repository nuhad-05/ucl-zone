package com.ucl.ucl_zone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UclZoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(UclZoneApplication.class, args);
	}

    @Bean
    public RestTemplate restTemplate() {
    return new RestTemplate();
    }

}
