package com.example.isabe.ramemichiraku.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.isabe.ramemichiraku.model.Cardapio;
import com.example.isabe.ramemichiraku.R;
import com.example.isabe.ramemichiraku.adapter.CardapioAdapter;
import com.example.isabe.ramemichiraku.model.CardapioList;
import com.example.isabe.ramemichiraku.retrofit.CardapioService;
import com.example.isabe.ramemichiraku.utils.CardapioUtils;
import com.example.isabe.ramemichiraku.utils.FragmentCommunication;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaCardapioFragment extends android.support.v4.app.Fragment {
//    List<Cardapio> arrayCardapio = new ArrayList<>();

    //    private RecyclerView recyclerView;
    List<Cardapio> arrayCardapio = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter cardapioAdapter;
    @BindView(R.id.lista_cardapio)
    RecyclerView recyclerView;
    @BindView(R.id.loading_bar)
    ProgressBar loading_bar;
    private CardapioService cardapioService;
    Realm realm;

    private void buscarListCardapio() {
        //new callbeck classe estatica do retrofit pra comunicaçao com servidor
        cardapioService.getCardapio().enqueue(new Callback<CardapioList>() {
            //metodos obrigatorios de respostas
            @Override
            //servidor respondendo
            public void onResponse(Call<CardapioList> call, Response<CardapioList> response) {

                //condição se os dados foram capturados
                if (!response.isSuccessful()) {
                    Log.i("LISTA", "Erro: " + "Erro: " + response.code());
                } else {

                    //captura o objeto JSON e converte para um objeto CardapioList
                    CardapioList listaCardapio = response.body();//response body vem do servidor
                    arrayCardapio.clear();
                    for (Cardapio prato : listaCardapio.getCardapio()) {


                        Log.i("LISTA", "" + prato.getNome());

                        arrayCardapio.add(prato);
                    }
                    //Busca cardapios na base local e adiciona à lista
                    buscarBaseRealm();

                    loading_bar.setVisibility(View.GONE);
                    cardapioAdapter = new CardapioAdapter(getActivity(), arrayCardapio, communication);
                    recyclerView.setAdapter(cardapioAdapter);

                }
            }

            private void buscarBaseRealm() {
                realm = Realm.getDefaultInstance(); // opens "ichirakulrealm.realm"
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        //fazendo a busa
                        RealmResults<Cardapio> results = realm.where(Cardapio.class).findAll();
                        //adc no array
                        arrayCardapio.addAll(results);
                    }
                });
            }

            @Override
            //sem contato com servidor
            public void onFailure(Call<CardapioList> call, Throwable t) {

                Log.i("LISTA", "Falha: " + t.getMessage());
                buscarListCardapio();
            }


        });

    }
            @Override
            public void onDestroy() {
                if(!realm.isClosed()){
                    super.onDestroy();
                }
            }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_lista_cardapio, container, false);
        cardapioService = CardapioUtils.getCardapioService();
        ButterKnife.bind(this, view); //Importante


        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        buscarListCardapio();




        return view;
    }

    FragmentCommunication communication=new FragmentCommunication() {
        @Override
        public void respond(int position,String prato,String descricao, String preco, String img_url) {
            DetalheFragment detalhesFragment = new DetalheFragment();

            Bundle bundle=new Bundle();
            bundle.putString("PRATO", prato);
            bundle.putString("DESCRICAO",descricao);
            bundle.putString("PRECO",preco);
            bundle.putString("IMG_URL", img_url);

            detalhesFragment.setArguments(bundle);

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.addToBackStack(null);
            transaction.replace(R.id.layout_troca_fragment,detalhesFragment).commit();
        }

    };
}
