package com.mahdieh.zavar.restcountries.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CurrencyResponse {
    private String currencyName;
    private List<String> countries;
}
