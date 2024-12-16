package com.mahdieh.zavar.restcountries.data;

import lombok.Data;

import java.util.Map;

@Data
public class CountryResponse {

    private Name name;
    private Map<String, Currency> currencies;

    @Data
    public static class Name {
        private String common;
    }

    @Data
    public static class Currency {
        private String name;
    }
}
