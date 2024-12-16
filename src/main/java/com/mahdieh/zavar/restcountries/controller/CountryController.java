package com.mahdieh.zavar.restcountries.controller;

import com.mahdieh.zavar.restcountries.data.CountryResponse;
import com.mahdieh.zavar.restcountries.data.SortParameter;
import com.mahdieh.zavar.restcountries.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/{region}")
    public ResponseEntity<?> getCountriesByRegion(@PathVariable String region,
                                                  @RequestParam(required = false, defaultValue = "NAME") SortParameter sortBy,
                                                  @RequestParam(required = false, defaultValue = "true") boolean ascending) {
        List<CountryResponse> europeanCountriesSorted = countryService.getEuropeanCountriesSorted(region, sortBy, ascending);
        return ResponseEntity.ok().body(europeanCountriesSorted);
    }
}
