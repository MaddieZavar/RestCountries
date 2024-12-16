package com.mahdieh.zavar.restcountries.service;

import com.mahdieh.zavar.restcountries.client.RestCountriesClient;
import com.mahdieh.zavar.restcountries.data.CountryResponse;
import com.mahdieh.zavar.restcountries.data.SortParameter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CountryService {

    private final RestCountriesClient restCountriesClient;

    public List<CountryResponse> getEuropeanCountriesSorted(String region, SortParameter sortBy, boolean ascending) {
        List<CountryResponse> countries = restCountriesClient.getEuropeanCountries(region);

        Comparator<CountryResponse> comparator;

        switch (sortBy) {
            case NAME:
                comparator = Comparator.comparing(country -> country.getName().getCommon());
                break;
            case CURRENCY:
                comparator = Comparator.comparing(country -> {
                    Map<String, CountryResponse.Currency> currencies = country.getCurrencies();
                    return currencies.values().stream()
                            .map(CountryResponse.Currency::getName)
                            .findFirst()
                            .orElse("");
                });
                break;
            default:
                throw new IllegalArgumentException("Invalid sortBy value: " + sortBy);
        }

        if (!ascending) {
            comparator = comparator.reversed();
        }

        countries.sort(comparator);
        return countries;
    }
}
