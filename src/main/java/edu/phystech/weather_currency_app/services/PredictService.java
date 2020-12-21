package edu.phystech.weather_currency_app.services;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredictService {
    private final SimpleRegression regression;
    private final WeatherService weatherService;
    private final CurrencyService currencyService;
    private static final int DATASET_SIZE = 7;

    public PredictService(WeatherService weatherService, CurrencyService currencyService)  {
        this.weatherService = weatherService;
        this.currencyService = currencyService;
        regression = new SimpleRegression();
        fit();
    }

    public double predict() {
        Double forecast = weatherService.getWeatherForecast();
        return predict(forecast);
    }

    private void fit() {
        List<Double> weatherData = weatherService.getWeatherHistory(DATASET_SIZE);
        List<Double> currencyData = currencyService.getDollarCurrency(DATASET_SIZE);

        for (int i = 0; i < DATASET_SIZE; ++i) {
            regression.addData(weatherData.get(i), currencyData.get(i));
        }
    }

    private Double predict(Double avgTemperature) {
        return regression.predict(avgTemperature);
    }
}
