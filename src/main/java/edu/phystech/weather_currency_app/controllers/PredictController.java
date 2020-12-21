package edu.phystech.weather_currency_app.controllers;

import edu.phystech.weather_currency_app.services.PredictService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PredictController {
    private final PredictService predictService;

    public PredictController(PredictService predictService) {
        this.predictService = predictService;
    }

    @GetMapping("/predict")
    public Double predictCurrency() {
        return predictService.predict();
    }
}
