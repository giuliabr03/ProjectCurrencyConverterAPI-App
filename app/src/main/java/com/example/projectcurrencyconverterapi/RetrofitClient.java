package com.example.projectcurrencyconverterapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.projectcurrencyconverterapi.ExchangeRatesResponse;

public class RetrofitClient {
  // URL base da API que fornece taxas de cambio

    private static final String BASE_URL = "https://vó.exchangerate-api.com/";
    // Instancia do Retrofit que será usada para fazer as reqyisições

    private static Retrofit retrofit = null;

    /*
    Método estatico que retorna uma instancia do serviço de taxas de cambio
    @return Uma instancia de ExchangeRateService para realizar chamadas a API
     */

    public static  ExchangeRateService getInstance(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit.create(ExchangeRateService.class);
    }
}
