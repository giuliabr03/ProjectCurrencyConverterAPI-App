// Classe responsável por configurar e fornecer uma instância retrofit
//interface qe identifica o endpoint da API que será consumida pelo retrofit
package com.example.projectcurrencyconverterapi;

import retrofit2.Call; // faz uma chamada
import retrofit2.http.GET; // cria um caminho
import retrofit2.http.Path; // define o caminho

public interface ExchangeRateService {
    /**
     * Método para obter taxas de câmbio recentes
     */
    @GET("/v6/{apiKey}/latest/{baseCurrency}")

    Call<ExchangeRatesResponse> getExchangeRates(

            @Path("apiKey") String apiKey,
            // Subistitui {apiKey} na URL com valor fornecido
            @Path("baseCurrency") String baseCurrency
            // Subistitui {baseCurrency} na URl com valor fornecido
    );
}

