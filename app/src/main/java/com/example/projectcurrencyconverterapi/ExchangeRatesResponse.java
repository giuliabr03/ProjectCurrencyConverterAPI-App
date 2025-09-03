// Modelo de dados (DtD - Data Transfer Object) que representa a resposta json da API
// Json
package com.example.projectcurrencyconverterapi;

import java.util.HashMap; //import a classe Map da biblioteca Java, usada para armazenar pares da chave-valor
import java.util.Map; // Importa a classe Map da biblioteca Java, usada para armazenar pares chave-valor

public class ExchangeRatesResponse {
    // declara um mapa que arazena taxas de conversão em moedas. (ex. USd, EUR, GBP)
    // a chave (String) representa o codigp da Moeda e o valor (Double) representa a taxa de conversão

    private Map<String, Double> conversion_rates = new HashMap<>();
    //Metodo publico para acessar as taxas de cpnversão
    // Retona o mapa de taxas de conversão[

    public Map<String, Double> getConversion_rates (){
        return conversion_rates;
    }
}
