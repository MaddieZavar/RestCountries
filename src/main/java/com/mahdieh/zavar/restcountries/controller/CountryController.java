package com.mahdieh.zavar.restcountries.controller;

import com.mahdieh.zavar.restcountries.data.CountryResponse;
import com.mahdieh.zavar.restcountries.data.CurrencyResponse;
import com.mahdieh.zavar.restcountries.data.SortParameter;
import com.mahdieh.zavar.restcountries.service.CountryService;
import com.mahdieh.zavar.restcountries.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CountryController {

    private final CountryService countryService;
    private final CurrencyService currencyService;

    @GetMapping("/countries/{region}")
    public ResponseEntity<?> getCountriesByRegion(@PathVariable String region,
                                                  @RequestParam(required = false, defaultValue = "NAME") SortParameter sortBy,
                                                  @RequestParam(required = false, defaultValue = "true") boolean ascending) {
        List<CountryResponse> europeanCountriesSorted = countryService.getCountriesByRegionSorted(region, sortBy, ascending);
        return ResponseEntity.ok().body(europeanCountriesSorted);
    }

    @GetMapping("/currencies")
    public ResponseEntity<?> getAllCurrencies() {
        List<CurrencyResponse> currenciesWithCountries = currencyService.getCurrenciesWithCountries();
        return ResponseEntity.ok().body(currenciesWithCountries);
    }
}
