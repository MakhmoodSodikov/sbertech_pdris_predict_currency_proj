package edu.phystech.weather_currency_app.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {
    private static final String API_KEY = "64ee59d7904f4bbb8f3195525201812";
    private static final String DEFAULT_CITY = "Moscow";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public WeatherService(RestTemplateBuilder builder, ObjectMapper objectMapper) {
        this.restTemplate = builder.build();
        this.objectMapper = objectMapper;
    }

    public List<Double> getWeatherHistory(int days) {
        return getHistory(days, DEFAULT_CITY);
    }

    public List<Double> getWeatherHistory(int days, String city) {
        return getHistory(days, city);
    }

    public Double getWeatherForecast() {
        return getForecast(DEFAULT_CITY);
    }

    public Double getWeatherForecast(String city) {
        return getForecast(city);
    }

    private List<Double> getHistory(int days, String city) {
        List<Double> avgTemperature = new ArrayList<>(days);
        for (int i = 0; i < days; ++i) {
            String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now().minusDays(i));
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(createHistoryRequestUrl(date, city), String.class);
            avgTemperature.add(parseAverageTemperature(responseEntity));
        }
        return avgTemperature;
    }

    private Double getForecast(String city) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(createForecastRequestUrl(city), String.class);
        return parseAverageTemperature(responseEntity);
    }

    private Double parseAverageTemperature(ResponseEntity<String> responseEntity) {
        JsonNode node;
        try {
            node = objectMapper.readTree(responseEntity.getBody());
        } catch (JsonProcessingException e) {
            return null;
        }
        if (node.get("forecast") != null) {
            node = node.get("forecast").get("forecastday").get(0).get("day");
        }
        return node.get("avgtemp_c").asDouble();
    }

    private String createHistoryRequestUrl(String date, String city) {
        return "http://api.weatherapi.com/v1/history.json" + "?key=" + API_KEY + "&q=" + city + "&dt=" + date;
    }

    private String createForecastRequestUrl(String city) {
        return "http://api.weatherapi.com/v1/forecast.json" + "?key=" + API_KEY + "&q=" + city + "&days=1";
    }
}
