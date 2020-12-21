package edu.phystech.weather_currency_app.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrencyServiceTest {
    @Autowired
    private CurrencyService currencyService;

    @Test
    void getDollarCurrency() {
        List<Double> currency = currencyService.getDollarCurrency(3);
        assertEquals(3, currency.size());
        currency.forEach(val -> assertTrue(60 < val && val < 85));
    }
}