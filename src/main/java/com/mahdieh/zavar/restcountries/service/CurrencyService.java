package com.mahdieh.zavar.restcountries.service;

import com.mahdieh.zavar.restcountries.client.RestCountriesClient;
import com.mahdieh.zavar.restcountries.data.CountryResponse;
import com.mahdieh.zavar.restcountries.data.CurrencyResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CurrencyService {

    private final RestCountriesClient restCountriesClient;

    public List<CurrencyResponse> getCurrenciesWithCountries() {
        List<CountryResponse> countries = restCountriesClient.getAllCountries();

        Map<String, List<String>> currencyToCountriesMap = new HashMap<>();
        mapCurrenciesToCountries(countries, currencyToCountriesMap);
        return convertToCurrencyResponseList(currencyToCountriesMap);

    }

    private static List<CurrencyResponse> convertToCurrencyResponseList(Map<String, List<String>> currencyToCountriesMap) {
        return currencyToCountriesMap.entrySet().stream()
                .map(entry -> new CurrencyResponse(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private static void mapCurrenciesToCountries(List<CountryResponse> countries, Map<String, List<String>> currencyToCountriesMap) {
        countries.stream()
                .filter(country -> country.getCurrencies() != null)
                .forEach(country -> country.getCurrencies()
                        .forEach((code, currency) ->
                                currencyToCountriesMap
                                        .computeIfAbsent(currency.getName(), k -> new ArrayList<>())
                                        .add(Optional.ofNullable(country.getName())
                                                .map(CountryResponse.Name::getCommon)
                                                .orElse("Unknown"))
                        )
                );
    }
}
