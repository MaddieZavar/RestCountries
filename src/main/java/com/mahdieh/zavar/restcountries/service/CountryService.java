package com.mahdieh.zavar.restcountries.service;

import com.mahdieh.zavar.restcountries.client.RestCountriesClient;
import com.mahdieh.zavar.restcountries.data.CountryResponse;
import com.mahdieh.zavar.restcountries.data.SortParameter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryService {

    private final RestCountriesClient restCountriesClient;

    public List<CountryResponse> getEuropeanCountriesSorted(String region, SortParameter sortBy, boolean ascending) {
        List<CountryResponse> countries = restCountriesClient.getEuropeanCountries(region);
        Comparator<CountryResponse> comparator = getComparatorForSorting(sortBy);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return sortCountries(countries, comparator);
    }

    private Comparator<CountryResponse> getComparatorForSorting(SortParameter sortBy) {
        return switch (sortBy) {
            case NAME -> Comparator.comparing(country -> country.getName().getCommon());
            case CURRENCY -> Comparator.comparing(this::getFirstCurrencyName);
        };
    }

    private String getFirstCurrencyName(CountryResponse country) {
        return Optional.ofNullable(country.getCurrencies())
                .map(currencies -> currencies.values().stream()
                        .map(CountryResponse.Currency::getName)
                        .findFirst()
                        .orElse(""))
                .orElse("");
    }

    private List<CountryResponse> sortCountries(List<CountryResponse> countries, Comparator<CountryResponse> comparator) {
        countries.sort(comparator);
        return countries;
    }
}
