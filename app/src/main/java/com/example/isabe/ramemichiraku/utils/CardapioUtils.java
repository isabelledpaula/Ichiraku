package com.example.isabe.ramemichiraku.utils;

import com.example.isabe.ramemichiraku.retrofit.CardapioService;
import com.example.isabe.ramemichiraku.retrofit.RetrofitClient;

/**
 * Created by isabe on 04/03/2018.
 */

public class CardapioUtils {
    public static final String BASE_URL = "http://broken-cherry-5811.getsandbox.com/";

    //retorna instancia do retrofit
    public static CardapioService getCardapioService() {
        return RetrofitClient.getClient(BASE_URL).create(CardapioService.class);
    }
}
