package edu.phystech.weather_currency_app.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WeatherServiceTest {
    @Autowired
    private WeatherService weatherService;

    @Test
    void getWeatherHistory() {
        List<Double> weather = weatherService.getWeatherHistory(5);
        assertEquals(5, weather.size());
    }

    @Test
    void getWeatherHistoryInCity() {
        List<Double> weatherInParis = weatherService.getWeatherHistory(4, "Paris");
        assertEquals(4, weatherInParis.size());
    }

    @Test
    void getWeatherForecast() {
        Double forecast = weatherService.getWeatherForecast();
        assertTrue(-35 < forecast && forecast < 40);
    }

}