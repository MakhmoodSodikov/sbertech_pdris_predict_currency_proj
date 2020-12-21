package edu.phystech.weather_currency_app.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
class WeatherControllerTest {
    @Autowired
    private WeatherController weatherController;

    @Test
    void getWeather() throws Exception {
        MockMvc mvc = standaloneSetup(weatherController).build();
        mvc.perform(MockMvcRequestBuilders.get("/weather?days=2")).andExpect(status().isOk());
    }

    @Test
    void getWeatherInCity() throws Exception {
        MockMvc mvc = standaloneSetup(weatherController).build();
        mvc.perform(MockMvcRequestBuilders.get("/weather?days=2&city=Paris")).andExpect(status().isOk());
    }
}