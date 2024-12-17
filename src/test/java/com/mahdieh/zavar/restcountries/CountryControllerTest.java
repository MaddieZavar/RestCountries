package com.mahdieh.zavar.restcountries;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahdieh.zavar.restcountries.controller.CountryController;
import com.mahdieh.zavar.restcountries.data.CountryResponse;
import com.mahdieh.zavar.restcountries.data.SortParameter;
import com.mahdieh.zavar.restcountries.service.CountryService;
import com.mahdieh.zavar.restcountries.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ExtendWith(MockitoExtension.class)
public class CountryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CountryService countryService;

    @Mock
    private CurrencyService currencyService;

    @InjectMocks
    private CountryController countryController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
        mockMvc = MockMvcBuilders.standaloneSetup(countryController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @Test
    void testGetEuropeanCountriesSortedByName() throws Exception {
        List<CountryResponse> mockResponse = List.of(
                createCountryResponse("Austria", "Euro"),
                createCountryResponse("France", "Euro"),
                createCountryResponse("Germany", "Euro")
        );

        System.out.println(objectMapper.writeValueAsString(mockResponse));

        when(countryService.getEuropeanCountriesSorted("europe", SortParameter.NAME, true))
                .thenReturn(mockResponse);

         //Perform the request
        String responseContent = mockMvc.perform(get("/api/countries/europe")
                        .param("sortBy", "NAME")
                        .param("ascending", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(countryService).getEuropeanCountriesSorted("europe", SortParameter.NAME, true);

        System.out.println("Actual Response: " + responseContent);
    }

    private CountryResponse createCountryResponse(String countryName, String currencyName) {
        return new CountryResponse(
                new CountryResponse.Name(countryName),
                Map.of("default", new CountryResponse.Currency(currencyName))
        );
    }
}
