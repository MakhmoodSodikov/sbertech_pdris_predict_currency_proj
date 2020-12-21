package edu.phystech.weather_currency_app.controllers;

import edu.phystech.weather_currency_app.services.WeatherService;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public List<Double> getWeather(@RequestParam @Nullable String days, @RequestParam @Nullable String city) {
        int daysNum = days == null ? 1 : Integer.parseInt(days);
        if (city == null) {
            return weatherService.getWeatherHistory(daysNum);
        }
        return weatherService.getWeatherHistory(daysNum, city);
    }
}
