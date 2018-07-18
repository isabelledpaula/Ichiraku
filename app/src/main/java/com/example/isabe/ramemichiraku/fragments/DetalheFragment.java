package com.example.isabe.ramemichiraku.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.isabe.ramemichiraku.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalheFragment extends android.support.v4.app.Fragment {
    @BindView(R.id.txt_titulo_prato)
    TextView nome_prato;
    @BindView(R.id.txt_descricao)
    TextView descricao_prato;
    @BindView(R.id.txt_preco)
    TextView preco_prato;
    @BindView(R.id.img_imagem_prato)
    ImageView img_cardapio;
    @BindView(R.id.img_voltar)
    ImageView btn_voltar;

    String nome;
    String descricao;
    String preco;
    String img_url;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nome = getArguments().getString("PRATO");
        descricao = getArguments().getString("DESCRICAO");
        preco = getArguments().getString("PRECO");
        img_url = getArguments().getString("IMG_URL");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhe, container, false);
        ButterKnife.bind(this, view); //Importante
        Glide.with(getContext())
                .load(img_url)
                .into(img_cardapio);
        nome_prato.setText(nome);
        descricao_prato.setText(descricao);
        preco_prato.setText("R$ "+ preco);

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }

}
