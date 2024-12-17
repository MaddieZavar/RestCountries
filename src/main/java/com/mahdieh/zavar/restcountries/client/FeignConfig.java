package com.mahdieh.zavar.restcountries.client;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!integration-test")
@EnableFeignClients
public class FeignConfig {
}
