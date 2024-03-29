package com.example.isabe.ramemichiraku.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by isabe on 04/03/2018.
 */

public class RetrofitClient {

    private static Retrofit retrofit = null;
    //pega o GSON e transforma no javinha
    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
