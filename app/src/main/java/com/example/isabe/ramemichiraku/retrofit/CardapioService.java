package com.example.isabe.ramemichiraku.retrofit;

import com.example.isabe.ramemichiraku.model.Cardapio;
import com.example.isabe.ramemichiraku.model.CardapioList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by isabe on 04/03/2018.
 */

public interface CardapioService {
    //call esperando cardapioList
    @GET("cardapio")
    Call<CardapioList> getCardapio();

    @POST("cardapio/new")
    Call<Cardapio> createCardapio(@Body Cardapio cardapio);

}
