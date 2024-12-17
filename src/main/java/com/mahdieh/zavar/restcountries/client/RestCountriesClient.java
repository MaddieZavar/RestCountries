package com.mahdieh.zavar.restcountries.client;

import com.mahdieh.zavar.restcountries.data.CountryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "restCountriesClient", url = "https://restcountries.com/v3.1")
public interface RestCountriesClient {

    @GetMapping("/region/{region}?fields=name,currencies,region")
    List<CountryResponse> getCountriesByRegion(@PathVariable("region") String region);

    @GetMapping("/all?fields=name,currencies")
    List<CountryResponse> getAllCountries();
}
