package model;

import controller.API;

import java.util.Map;

public class divisa {

    Map<String, Double> conversionRates;
    private String divisaInicial;


    public divisa(API api){
        this.divisaInicial = api.base_code();
        this.conversionRates = api.conversion_rates();
    }

    public double conversor(double valorConvertir, String divisaDestino) {

        for (Map.Entry<String, Double> map : conversionRates.entrySet()) {
            String codigoDivisa = map.getKey();
            Double tasaCambio = map.getValue();

            if (codigoDivisa.equalsIgnoreCase(divisaDestino)) {
                return valorConvertir * tasaCambio;
            }
        }
        return 0;
    }
}
