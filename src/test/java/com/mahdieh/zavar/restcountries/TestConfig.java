package com.mahdieh.zavar.restcountries;

import com.mahdieh.zavar.restcountries.client.RestCountriesClient;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public RestCountriesClient restCountriesClient() {
        return Mockito.mock(RestCountriesClient.class);
    }

}
