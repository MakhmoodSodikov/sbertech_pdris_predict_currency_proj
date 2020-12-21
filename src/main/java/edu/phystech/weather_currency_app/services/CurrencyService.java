package edu.phystech.weather_currency_app.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyService {
    private final RestTemplate restTemplate;

    public CurrencyService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public List<Double> getDollarCurrency(int days) {
        List<Double> currency = new ArrayList<>(days);
        for (int i = 0; i < days; ++i) {
            String date = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now().minusDays(i));
            currency.add(parseDollarValue(date));
        }
        return currency;
    }

    private double parseDollarValue(String date) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(createRequestUrl(date), String.class);
        String body = responseEntity.getBody();
        String start = "Доллар США</Name><Value>";
        int startIndex = body.indexOf(start) + start.length();
        int endIndex = body.indexOf('<', startIndex);
        return Double.parseDouble(body.substring(startIndex, endIndex).replace(",", "."));
    }

    private String createRequestUrl(String date) {
        return "http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date;
    }
}
