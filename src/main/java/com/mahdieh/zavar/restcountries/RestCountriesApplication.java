package com.mahdieh.zavar.restcountries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RestCountriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestCountriesApplication.class, args);
	}
}
