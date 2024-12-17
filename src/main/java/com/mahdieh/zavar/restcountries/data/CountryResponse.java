package com.mahdieh.zavar.restcountries.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryResponse {
    @JsonProperty("name")
    private Name name;

    @JsonProperty("currencies")
    private Map<String, Currency> currencies;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Name {
        @JsonProperty("common")
        private String common;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Currency {
        @JsonProperty("name")
        private String name;
    }
}
