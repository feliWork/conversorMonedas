package controller;

import java.util.Map;

public record API(String base_code, Map<String, Double> conversion_rates) {
}
