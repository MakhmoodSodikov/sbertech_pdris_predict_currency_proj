package edu.phystech.weather_currency_app.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
class CurrencyControllerTest {
    @Autowired
    private CurrencyController currencyController;

    @Test
    void getCurrency() throws Exception {
        MockMvc mvc = standaloneSetup(currencyController).build();
        mvc.perform(MockMvcRequestBuilders.get("/currency?days=2")).andExpect(status().isOk());
    }
}