package com.mahdieh.zavar.restcountries;

import com.mahdieh.zavar.restcountries.client.RestCountriesClient;
import com.mahdieh.zavar.restcountries.data.CountryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RestCountriesApplication.class, TestConfig.class })
@WebAppConfiguration
@ActiveProfiles("integration-test")
public class CountryControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private RestCountriesClient restCountriesClient;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void testGetCountriesByRegionSortedByName() throws Exception {

        when(restCountriesClient.getCountriesByRegion("europe"))
                .thenReturn(List.of(
                createCountryResponse("Germany", "Euro"),
                createCountryResponse("France", "Euro"),
                createCountryResponse("Italy", "Euro")));

         //Perform the request
       mockMvc.perform(get("/api/countries/europe")
                        .param("sortBy", "NAME")
                        .param("ascending", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name.common").value("France"))
                .andExpect(jsonPath("$[1].name.common").value("Germany"))
                .andExpect(jsonPath("$[2].name.common").value("Italy"));

    }

    private CountryResponse createCountryResponse(String countryName, String currencyName) {
        return new CountryResponse(
                new CountryResponse.Name(countryName),
                Map.of("default", new CountryResponse.Currency(currencyName))
        );
    }
}
