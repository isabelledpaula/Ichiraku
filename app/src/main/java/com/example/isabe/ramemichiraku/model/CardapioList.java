package com.example.isabe.ramemichiraku.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by isabe on 04/03/2018.
 */

public class CardapioList {
    //objeto status tipo JSON
    @SerializedName("status")
    @Expose
    private Integer status;
    //objeto cardapio tipo JSON
    @SerializedName("cardapio")
    @Expose

    private List<Cardapio> cardapio;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Cardapio> getCardapio() {
        return cardapio;
    }

    public void setCardapio(List<Cardapio> cardapio) {
        this.cardapio = cardapio;
    }
}
