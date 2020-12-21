package edu.phystech.weather_currency_app.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PredictServiceTest {
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private CurrencyService currencyService;

    @Test
    void predict() {
        PredictService predictService = new PredictService(weatherService, currencyService);
        double predicted = predictService.predict();
        assertTrue(50 < predicted && predicted < 95);
    }
}